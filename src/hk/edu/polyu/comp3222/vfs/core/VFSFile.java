package hk.edu.polyu.comp3222.vfs.core;

import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * Created by michael on 2017/3/31.
 */
public class VFSFile implements VFSUnit {
    private Date lastMotify;
    private File content;
    private long size;
    private VFSUnit parent;

    public VFSFile(File newFile, VFSUnit parent){
        this.content = newFile;
        this.lastMotify = new Date(newFile.lastModified());
        this.size = newFile.length();
        this.parent = parent;
    }

    public boolean rename(String newName){
        String parentName = this.parent.getPath();
        String out = parentName + "/" + newName;
        File newFile = new File(out);
        if(newFile.exists()){
            return false;
        }
        this.content.renameTo(newFile);
        this.changeLastModify();
        long changeSize = this.size - this.content.length();
        this.changeSize(changeSize);
        return true;
    }

    @Override
    public boolean delete() {
        long size = this.size;
        if(!this.content.delete())
            return false;
        this.changeSize(-size);
        return true;
    }


    public boolean move(Directory path) {
        if(!path.getContent().exists()){
            return false;
        }
        File newFile = new File(path + "/" + this.content.getName());
        if(newFile.exists()){
            return false;
        }
        if(this.content.renameTo(newFile)) {
            this.changeLastModify();
            this.changeSize(-this.size);
            return true;
        }
        return false;
    }

    @Override
    public long getSize() {
        return this.size;
    }

    @Override
    public Date getLastModify() {
        return lastMotify;
    }

    @Override
    public String getName() {
        return this.content.getName();
    }

    public String getPath(){
        return this.content.getPath();
    }



    @Override
    public boolean export(File name) throws IOException{
        if(!name.exists())
            return false;
        File outFile = new File(name.toString() + '/' + this.getName());
        if(outFile.exists()){
            return false;
        }
        Directory outVFS = new Directory(name, null);
        this.copy(outVFS);
        return false;
    }

    public boolean copy(Directory file) throws IOException{
        if(!file.getContent().exists()){
            return false;
        }
        File out = new File(file.getPath() + "/" + this.getName());
        if(out.exists()){
            return false;
        }
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(this.content);
            output = new FileOutputStream(out);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        }
        finally {
            input.close();
            output.close();
        }
        VFSUnit child = new VFSFile(out, file);
        file.addChild(child);
        child.getParent().changeSize(child.getSize());
        return true;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public boolean isVFSFile() {
        return true;
    }

    @Override
    public File getContent() {
        return this.content;
    }

    public boolean equals(VFSUnit other){
        if(other.getContent().equals(this.getContent()) && this.lastMotify == other.getLastModify())
            return true;
        return false;
    }

    public String toString(){
        String type = "f";
        String size = String.valueOf(this.size);
        String date = this.lastMotify.toString();
        String name = this.getName();
        String out;
        out = String.format("%s %15s %s %20s",type, size, date, name);
        return out;
    }

    @Override
    public boolean changeLastModify() {
        this.lastMotify = new Date(this.content.lastModified());
        this.parent.changeLastModify();
        return true;
    }

    @Override
    public boolean changeSize(long size) {
        this.size += size;
        this.parent.changeSize(size);
        return true;
    }

    @Override
    public VFSUnit getParent() {
        return this.parent;
    }

    @Override
    public VFSUnit getChild(String name) {
        return null;
    }

    @Override
    public List<VFSUnit> getChildren() {
        return null;
    }

    @Override
    public boolean addChild(VFSUnit child) {
        return false;
    }

    @Override
    public boolean deleteChild(VFSUnit child) {
        return false;
    }

    @Override
    public boolean importFile(File file) throws IOException {
        return false;
    }

    @Override
    public String getDisplayName() {
        String out = this.parent.getDisplayName() + "/" + this.getName();
        return out;
    }
}
