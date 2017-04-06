package hk.edu.polyu.comp3222.vfs.client;

import hk.edu.polyu.comp3222.vfs.Executer;
import hk.edu.polyu.comp3222.vfs.core.VirtualDisk;
import hk.edu.polyu.comp3222.vfs.util.*;
import java.util.StringTokenizer;

/**
 * Created by lidawei on 04/04/2017.
 */
public class ClientOff extends Client{
    public ClientOff(VirtualDisk disk){
        super(disk);
    }

    @Override
    public void action(){
        while(true){
            String input = Executer.clientCmd.readLine();
            IOService output = null;
            StringTokenizer st=new StringTokenizer(input," ");
            String[] cmd = new String[10];
            int count = 0;
            while(st.hasMoreTokens()) {
                cmd[count++] = st.nextToken();
            }
            CommandType c = CommandType.valueOf(cmd[0]);
            switch (c){
                case COPY:
                    CopyCmd copy = new CopyCmd();
                    copy.command(cmd,output, disk);
                    break;
                case CD:
                    NavigationCmd cd = new NavigationCmd();
                    cd.command(cmd,output, disk);
                    break;
                case LIST:
                    ListCmd ls = new ListCmd();
                    ls.command(cmd, output,disk);
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
}
