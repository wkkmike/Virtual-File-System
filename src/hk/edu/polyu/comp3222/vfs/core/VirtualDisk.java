package hk.edu.polyu.comp3222.vfs.core;

import java.io.File;
import java.util.Date;

/**
 * A virtual disk.
 */
public class VirtualDisk{
    private String name;
    private final long size;
    private long remainSize;
    public final File ROOT_PATH;
    private Date lastModify;
    /*
     * Default constructor.
     */
    public VirtualDisk(String root, String name, long size){
        this.name = name;
        this.size = size;
        this.remainSize = size;
        this.lastModify =
    }

    public long getSize(){
        return size;
    }

    public long getRemainSize(){
        return remainSize;
    }

    public boolean importFile(String path, File importFile){
        if(importFile == null){
            return false;
        }
        if(importFile.getTotalSpace() > remainSize){
            return false;
        }

        return true;
    }
}
