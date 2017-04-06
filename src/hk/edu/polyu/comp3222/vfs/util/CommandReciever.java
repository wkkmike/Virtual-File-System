package hk.edu.polyu.comp3222.vfs.util;

import hk.edu.polyu.comp3222.vfs.core.VirtualDisk;

import java.util.ArrayList;

import static hk.edu.polyu.comp3222.vfs.util.CommandType.*;

/**
 * Created by lidawei on 05/04/2017.
 */
public class CommandReciever {
    public CommandReciever() {
    }

    public void execute(CommandType c, ArrayList<String> cmd, IOService output, VirtualDisk disk) {
        switch (c) {
            case COPY:
                CopyCmd copy = new CopyCmd();
                copy.command(cmd, output, disk);
                break;
            case CD:
                NavigationCmd cd = new NavigationCmd();
                cd.command(cmd, output, disk);
                break;
            case LIST:
                ListCmd ls = new ListCmd();
                ls.command(cmd, output, disk);
                break;
            case MKDIR:
                MkdirCmd mk = new MkdirCmd();
                mk.command(cmd, output, disk);
                break;
            case MOVE:
                MoveCmd mv = new MoveCmd();
                mv.command(cmd, output, disk);
                break;
            case QUIT:
                QuitCmd q = new QuitCmd();
                q.command(cmd, output, disk);
                break;
            case RENAME:
                RenameCmd r = new RenameCmd();
                r.command(cmd, output, disk);
                break;
            case SEARCH:
                SearchCmd s = new SearchCmd();
                s.command(cmd, output, disk);
                break;
            case HELP:
                HelpCmd h = new HelpCmd();
                h.command(cmd, output, disk);
                break;
            case TOUCH:
                TouchCmd t = new TouchCmd();
                t.command(cmd, output, disk);
                break;

        }

    }
}
