package hk.edu.polyu.comp3222.vfs.core;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by michael on 2017/3/31.
 */
public interface VFSUnit {
    boolean rename(String name);

    boolean delete();

    boolean move(Directory path);

    long getSize();

    Date getLastModify();

    String getName();

    String getPath();

    boolean export(File name) throws IOException;

    boolean copy(Directory name) throws IOException;

    boolean isDirectory();

    boolean isVFSFile();

    File getContent();

    boolean equals(VFSUnit other);

    String toString();

    boolean changeLastModify();

    boolean changeSize(long size);

    VFSUnit getParent();

    VFSUnit getChild(String name);

    List<VFSUnit> getChildren();

    boolean addChild(VFSUnit child);

    boolean deleteChild(VFSUnit child);

    boolean importFile(File file) throws IOException;

    String getDisplayName();
}
