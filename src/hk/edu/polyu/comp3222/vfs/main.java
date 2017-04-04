package hk.edu.polyu.comp3222.vfs;

import hk.edu.polyu.comp3222.vfs.core.VirtualDisk;

import java.io.File;

/**
 * Created by michael on 2017/4/3.
 */
public class main {
    public static void main(String args[]){
        VirtualDisk test = new VirtualDisk("C:/Users/michael/Desktop/testaa", 10000000000L);
        File tt = new File("C:/Users/michael/Desktop/aa/linux");
        test.importFile(tt);
        test.displayCurrentPath();
        test.listChildren();
    }
}
