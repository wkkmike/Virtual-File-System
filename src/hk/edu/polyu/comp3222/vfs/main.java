package hk.edu.polyu.comp3222.vfs;

import hk.edu.polyu.comp3222.vfs.core.VirtualDisk;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by michael on 2017/4/3.
 */
public class main {
    public static void main(String args[]){
        //C:/Users/michael/Desktop/test
        /*VirtualDisk test = new VirtualDisk("wkk", 10000000000L, "C:/Users/michael/Desktop/test/");
        File tt = new File("C:/Users/michael/Desktop/aa");
        test.importFile(tt);*/
        VirtualDisk test = null;
        try {
            FileInputStream fileIn = new FileInputStream("C:/Users/michael/Desktop/test/wkk.vfs");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            test = (VirtualDisk) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i) {
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }
        File ha = new File("C:/Users/michael/Desktop/a");
        test.exportFile(ha);
        test.getCurrentPath();
        test.listChildren();
        test.store();
    }
}
