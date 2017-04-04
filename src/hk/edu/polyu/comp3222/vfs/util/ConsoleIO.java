package hk.edu.polyu.comp3222.vfs.util;

import java.io.PrintStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by lidawei on 03/04/2017.
 */
public class ConsoleIO implements IOService {

    protected Scanner input;
    /**
     * used for printing information to users when the program runs normally
     */
    protected PrintStream output;

    private Queue<String> inputTest;
    private Queue<String> outputTest;

    private boolean isTest;


    /**
     * constructor
     * @param isTest whether it is testing
     * @param strings strings needed for testing
     */
    ConsoleIO(boolean isTest, String[] strings){
        this.isTest=isTest;
        if(isTest){
            inputTest= new LinkedList<>();
            outputTest= new LinkedList<>();
            Collections.addAll(inputTest, strings);
        }
        else{
            input = new Scanner(System.in);
            output=new PrintStream(System.out);
        }
    }
    @Override
    public String readLine() {
        if(isTest){
            String nextLine = inputTest.element();
            inputTest.remove();
            return nextLine;
        }
        else{
            return input.nextLine();
        }
    }

    @Override
    public void printLine(String str) {
        if(isTest){
            outputTest.add(str);
        }
        else{
            output.print(str);
        }
    }
}
