package hk.edu.polyu.comp3222.vfs.core;

import java.io.*;
import java.util.*;

/**
 * Created by michael on 2017/3/31.
 */
public class Directory implements VFSUnit{
    private Date lastMotify;
    private File content;
    private List<VFSUnit> children = new LinkedList<>();
    private VFSUnit parent;
    private long size;

    public Directory(File file, VFSUnit parent){
        this.parent = parent;
        this.content = file;
        file.mkdirs();
        for(File child: file.listFiles()){
            VFSUnit out = null;
            if(child.isFile()){
                out = new VFSFile(child, this);
            }
            else{
                out = new Directory(child, this);
            }
            children.add(out);
        }
        this.size = 0;
        this.lastMotify = new Date(this.content.lastModified());
    }

    @Override
    public boolean rename(String name) {
        String out = this.content.getParent() + "/" + name;
        File newFile = new File(out);
        if(newFile.exists()){
            return false;
        }
        else{
            this.content.renameTo(newFile);
            this.changeLastModify();
        }
        return true;
    }

    @Override
    public boolean delete() {
        for(VFSUnit child: children){
            child.delete();
        }
        this.content.delete();
        return true;
    }

    @Override
    public boolean move(Directory path) {
        if(!path.getContent().exists()){
            return false;
        }
        File out = new File(path.getPath() + "/" + this.getName());
        if(out.exists()){
            return false;
        }
        if(this.content.renameTo(out)){
            this.parent.changeLastModify();
            this.parent.changeSize(-this.size);
        }
        return false;
    }

    @Override
    public long getSize() {
        long size = 0;
        for(VFSUnit file: children){
            size += file.getSize();
        }
        return size;
    }

    public List<VFSUnit> getChildren(){
       return this.children;
    }

    @Override
    public Date getLastModify() {
        return this.lastMotify;
    }

    @Override
    public String getName() {
        return this.content.getName();
    }

    @Override
    public boolean export(File name) throws IOException{
        if(!name.exists()){
            return false;
        }
        File outFile = new File(name.toString() + '/' + this.getName());
        if(outFile.exists()){
            return false;
        }
        Directory outVFS = new Directory(name, null);
        if(this.copy(outVFS))
            return true;
        return false;
    }

    public String getPath(){
        return this.content.getPath();
    }

    @Override
    public boolean copy(Directory file) throws IOException{
        if(!file.getContent().exists()){
            return false;
        }
        File out = new File(file.getPath() + "/" + this.getName());
        if(out.exists()){
            return false;
        }
        out.mkdirs();
        Directory outUnit = new Directory(out, file);
        for(VFSUnit child: children){
            child.copy(outUnit);
        }
        file.addChild(outUnit);
        return true;
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    public boolean importFile(File file) throws IOException{
        if(file.isFile()){
            VFSUnit temp = new VFSFile(file, null);
            temp.copy(this);
        }
        else{
            VFSUnit temp = new Directory(file, null);
            temp.copy(this);
        }
        return true;
    }

    @Override
    public String getDisplayName() {
        if(this.parent == null)
            return this.getName();
        return this.parent.getDisplayName() + "/" + this.getName();
    }

    public boolean addChild(VFSUnit child){
        this.children.add(child);
        return true;
    }

    public boolean deleteChild(VFSUnit child){
        for(VFSUnit f: children){
            if(f.equals(child)){
                child.delete();
                children.remove(f);
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean isVFSFile() {
        return false;
    }

    @Override
    public File getContent() {
        return this.content;
    }

    public boolean changeSize(long size){
        this.size += size;
        if(this.parent == null){
            return true;
        }
        this.parent.changeSize(size);
        return true;
    }

    @Override
    public VFSUnit getParent() {
        return this.parent;
    }

    @Override
    public VFSUnit getChild(String name) {
        for(VFSUnit f: children){
            if(f.getName() == name)
                return f;
        }
        return null;
    }

    public boolean changeLastModify(){
        this.lastMotify = new Date(this.content.lastModified());
        this.parent.changeLastModify();
        return true;
    }
    
    public boolean equals(VFSUnit other){
        if(other.getContent().equals(this.content) && other.getLastModify() == this.lastMotify)
            return true;
        return false;
    }

    public String toString(){
        String type = "d";
        String size = String.valueOf(this.size);
        String date = this.lastMotify.toString();
        String name = this.getName();
        String out;
        out = String.format("%s %15s %s %20s",type, size, date, name);
        return out;
    }
}
