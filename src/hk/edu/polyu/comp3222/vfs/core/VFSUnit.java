package hk.edu.polyu.comp3222.vfs.core;

import java.io.File;
import java.util.Date;

/**
 * Created by michael on 2017/3/31.
 */
public interface VFSUnit {
    boolean rename(String name);

    boolean delete();

    boolean move(String name);

    long getSize();

    Date getLastModify();

    String getName();

    boolean export(File name);
}
