package hk.edu.polyu.comp3222.vfs.util;

import hk.edu.polyu.comp3222.vfs.core.VFSUnit;
import hk.edu.polyu.comp3222.vfs.core.VirtualDisk;

import java.util.ArrayList;

/**
 * Created by lidawei on 03/04/2017.
 */
public class MoveCmd implements Command {
    public String command(ArrayList<String> cmd, IOService ioService, VirtualDisk disk){
        ioService.printLine("This is the mv command.");
        String dirName = null;
        String name = null;
        if (cmd.size() > 2 && !cmd.get(1).equals(null) && !cmd.get(2).equals(null) ) {
            name = cmd.get(1);
            dirName = cmd.get(2);
        }
        else {
            ioService.printLine("Wrong Argument for touch command");
        }
        disk.move(name, dirName);

        return disk.getCurrentPath();
    }
}
