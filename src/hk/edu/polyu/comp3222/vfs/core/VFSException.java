package hk.edu.polyu.comp3222.vfs.core;

import java.io.IOException;

/**
 * Created by michael on 2017/4/2.
 */
public class VFSException extends IOException{
    String info;
    VFSUnit unit;

    public VFSException(String info){
        this.info = info;
    }
}
