package hk.edu.polyu.comp3222.vfs.core;

import java.io.File;
import java.util.Date;

/**
 * Created by michael on 2017/3/31.
 */
public class VFSFile implements VFSUnit {
    private Date lastMotify;
    private File content;
    private String path;
    private Directory parent;
    private long size;

    public VFSFile(File newFile, Directory parent){
        this.content = newFile;
        lastMotify = new Date(newFile.lastModified());
        size = newFile.getTotalSpace();
        this.parent = parent;
    }

    public boolean rename(String newName){
        String parentName = this.content.getParent();
        String out = parentName + "/" + newName;
        File newFile = new File(out);
        if(newFile.exists()){
            return false;
        }
        this.content.renameTo(newFile);
        lastMotify = new Date(newFile.lastModified());
        size = newFile.getTotalSpace();
        return true;
    }

    @Override
    public boolean delete() {
        return this.content.delete();
    }

    @Override
    public boolean move(String name) {
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

    @Override
    public boolean export(File name) {
        return false;
    }
}
