package hk.edu.polyu.comp3222.vfs.util;

import hk.edu.polyu.comp3222.vfs.core.VFSUnit;
import hk.edu.polyu.comp3222.vfs.core.VirtualDisk;

/**
 * Created by lidawei on 03/04/2017.
 */
public class RenameCmd extends Command {
    public VFSUnit command(String[] cmd, IOService ioService, VirtualDisk disk){
        String originName = null;
        String newName = null;
        if (cmd.length > 1 && !cmd[1].equals(null) && !cmd[2].equals(null)) {
            originName = cmd[1];
            newName = cmd[2];
        } else {
            ioService.printLine("Wrong Argument for touch command");
        }
        ioService.printLine("This is the rename command");
        disk.rename(originName, newName);
        return disk.getCurrentPath();
    }
}
