package hk.edu.polyu.comp3222.vfs.core;

import java.io.File;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by michael on 2017/3/31.
 */
public class Directory {
    private Date lastMotify;
    private File content;
    private Map<String, VFSFile> child = new LinkedHashMap<>();
    private Directory parent;

    public Directory(String name, Directory parent){
            this.parent = parent;

    }
}
