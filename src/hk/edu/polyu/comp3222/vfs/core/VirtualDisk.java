package hk.edu.polyu.comp3222.vfs.core;

import java.io.*;
import java.util.Date;
import hk.edu.polyu.comp3222.vfs.util.Parser;
import hk.edu.polyu.comp3222.vfs.util.PathParser;

/**
 * A virtual disk.
 */
public class VirtualDisk{
    private long size;
    private long occupySize;
    public File ROOT_PATH;
    private Date lastModify;
    private VFSUnit currentPath;
    private VFSUnit content;
    private File info;
    private File log;
    /*
     * Default constructor.
     */

    public VirtualDisk(String path, long size){
        File root = new File(path);
        this.ROOT_PATH = root;
        File content = new File(path + "/root");
        content.mkdir();
        this.content = new Directory(content, null);
        this.currentPath = this.content;
        this.size = size;
        this.occupySize = 0;
        this.lastModify = new Date(content.lastModified());
        File info = new File(path + "/info.txt");
        File log = new File(path + "/log.txt");
        this.info = info;
        this.log = log;
        try{
            info.createNewFile();
            log.createNewFile();
        }
        catch (IOException e){
            System.out.println("Create fail");
            info.delete();
            log.delete();
            content.delete();
            root.delete();
        }
        try(PrintWriter out = new PrintWriter(info)) {
            out.println(String.valueOf(this.size));
            out.println(String.valueOf(this.occupySize));
            out.println(String.valueOf(this.lastModify.getTime()));
        }
        catch(FileNotFoundException e){
            System.out.println("Create fail");
            info.delete();
            log.delete();
            content.delete();
            root.delete();
        }
    }

    public VirtualDisk(File path){
        this.ROOT_PATH = path;
        File content = new File(path.toString() + "/" + "root");
        if(!content.exists()){
            content.mkdirs();
        }
        this.content = new Directory(content, null);
        this.currentPath = this.content;
        File info = new File(path.toString() + "/info.txt");
        this.info = info;
        File log = new File(path.toString() + "/log.txt");
        this.log = log;
        try {
            BufferedReader br = new BufferedReader(new FileReader(info));
            String line = br.readLine();
            this.size = Long.parseLong(line);
            line = br.readLine();
            this.occupySize = Long.parseLong(line);
            line = br.readLine();
            this.lastModify = new Date(Long.parseLong(line));
        }
        catch (IOException e){
            System.out.println("Get info failed");
        }
    }

    public long getSize(){
        return size;
    }

    public long getRemainSize(){
        return size - occupySize;
    }

    public Date getLastModify(){
        return this.lastModify;
    }

    public boolean dispose(){
        if(!this.content.delete()){
            return false;
        }
        this.info.delete();
        this.log.delete();
        this.ROOT_PATH.delete();
        return true;
    }

    public boolean changeDirectory(){
        this.currentPath  = this.content;
        return true;
    }

    public boolean changeDirectory(String path){
        PathParser parser = new PathParser(path, currentPath.getContent());
        String[] element = parser.getElement();
        VFSUnit temp = currentPath;
        for(String e: element){
            temp = temp.getChild(e);
            if(temp == null)
                return false;
        }
        currentPath = temp;
        return true;
    }

    public boolean importFile(File importFile){
        if(!importFile.exists()){
            return false;
        }
        try {
            this.currentPath.importFile(importFile);
        }
        catch (IOException e){
            System.out.println("import fail");
        }
        this.refresh();
        return true;
    }

    public boolean listChildren(){
        for(VFSUnit f: this.currentPath.getChildren()){
            System.out.println(f.getName());
            System.out.println(f.toString());
        }
        return true;
    }

    public boolean rename(String originName, String newName){
        VFSUnit temp = this.currentPath.getChild(originName);
        if(temp == null)
            return false;
        if(!temp.rename(newName))
            return false;
        return true;
    }

    public boolean delete(String name){
        VFSUnit temp = this.currentPath.getChild(name);
        if(temp == null)
            return false;
        if(!temp.delete())
            return false;
        this.refresh();
        return true;
    }

    public boolean createDirectory(String name){
        VFSUnit temp = this.currentPath.getChild(name);
        if(temp != null)
            return false;
        File out = new File(this.currentPath.getPath() + "/" + name);
        if(!out.mkdirs())
            return false;
        VFSUnit newDir = new Directory(out, this.currentPath);
        this.refresh();
        return this.currentPath.addChild(newDir);
    }

    public boolean createFile(String name){
        VFSUnit temp = this.currentPath.getChild(name);
        if(temp != null)
            return false;
        File out = new File(this.currentPath.getPath() + "/" + name);
        try{
            out.createNewFile();
        }
        catch(IOException e){
            return false;
        }
        VFSUnit newFile = new VFSFile(out,this.currentPath);
        this.refresh();
        return this.currentPath.addChild(newFile);
    }

    public boolean displayCurrentPath(){
        System.out.println(this.currentPath.getDisplayName());
        return true;
    }

    private void refresh(){
        this.occupySize = this.content.getSize();
        this.lastModify = this.content.getLastModify();
        try (PrintWriter out = new PrintWriter(this.info);){
            out.println(String.valueOf(this.size));
            out.println(String.valueOf(this.occupySize));
            out.println(String.valueOf(this.lastModify.getTime()));
        }
        catch(FileNotFoundException e){
            System.out.println("Refresh fail");
        }
    }
}
