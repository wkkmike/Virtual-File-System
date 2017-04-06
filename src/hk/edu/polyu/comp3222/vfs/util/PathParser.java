package hk.edu.polyu.comp3222.vfs.util;

/**
 * Created by michael on 2017/4/2.
 */
public class PathParser {
    private String path;

    public PathParser(String command, String currentPath){
        String paths[] = command.split("/");
        if(paths[0] == "."){
            path = currentPath + "/";
            for(int i=1; i<paths.length; i++){
                path += paths[i];
            }
        }
        else{
            path = command;
        }
    }

    public String getPath(){
        return path;
    }
}
