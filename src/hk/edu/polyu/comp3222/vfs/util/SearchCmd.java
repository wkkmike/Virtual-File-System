package hk.edu.polyu.comp3222.vfs.util;

import hk.edu.polyu.comp3222.vfs.core.VFSUnit;
import hk.edu.polyu.comp3222.vfs.core.VirtualDisk;

/**
 * Created by lidawei on 03/04/2017.
 */
public class SearchCmd extends Command {
    public VFSUnit command(String[] cmd, IOService ioService, VirtualDisk disk){
        if(cmd.length == 1){
            ioService.printLine("search command needs at least one argument");
        }else{
            String[] seachPath = cmd[1].split("/");
            //ioService.printLine(seachPath[0]);
            //ioService.printLine(seachPath[1]);
            VFSUnit fileSystemUnit = getItem(seachPath, ioService);
            if(fileSystemUnit != null){
                ioService.printLine(fileSystemUnit.getPath());
            }else{
                ioService.printLine("no such file found in this VFS");
            }

        }
        return disk.CurrentDir;
    }
}
