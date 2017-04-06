package hk.edu.polyu.comp3222.vfs.util;

import hk.edu.polyu.comp3222.vfs.core.*;

import java.util.ArrayList;

/**
 * Created by lidawei on 03/04/2017.
 */
public class TouchCmd implements Command {
    public String command(ArrayList<String> cmd, IOService ioService, VirtualDisk disk){
        //ioService.printLine("This is the touch handler.");
        String fileName = null;
        if (cmd.size() > 1 && !cmd.get(1).equals(null)) {
            fileName = cmd.get(1);
        } else {
            ioService.printLine("Wrong Argument for touch command");
        }
        disk.createFile(fileName,null);
        return disk.getCurrentPath();
    }
}
