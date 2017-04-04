package hk.edu.polyu.comp3222.vfs.util;

import hk.edu.polyu.comp3222.vfs.core.Directory;
import hk.edu.polyu.comp3222.vfs.core.VFSUnit;
import hk.edu.polyu.comp3222.vfs.core.VirtualDisk;

/**
 * Created by lidawei on 03/04/2017.
 */
public class NavigationCmd extends Command {
    public VFSUnit command(String[] cmd, IOService ioService, VirtualDisk disk){
        //ioService.printLine(String.valueOf(cmd.length));
        if(cmd.length == 1){
             disk.setCurrentPath(disk.getCurrentPath());
        }else{
            String[] seachPath = cmd[1].split("/");
            //ioService.printLine(seachPath[0]);
            //seachPath
            if(fileSystemUnit.getClass() == Directory.class){
                ioService.printLine(seachPath[0]);
                CurrentDir = (Directory) fileSystemUnit;
                //return CurrentDir;
            }else if(fileSystemUnit.getClass() != Directory.class){
                ioService.printLine("The target path is not a directory");
            }else{
                ioService.printLine("This directory is not found on this VFS");
            }
        }
        return CurrentDir;

    }
}
