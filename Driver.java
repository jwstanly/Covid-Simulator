import java.util.*;
import java.io.*;

public class Driver{

  public static void main(String args[]){

    //STARTING CONDITIONS
    int width;
    int height;
    int popSize;
    String filler;

    //default values
    width = 20;
    height = 50;
    popSize = 20;
    filler = "-";

    //checks for passed arguments
    switch(args.length){
      case 4:
        filler = args[3];
      case 3:
        popSize = Integer.parseInt(args[2]);
      case 2:
        width = Integer.parseInt(args[0]);
        height = Integer.parseInt(args[1]);
    }

    Simulator test = new Simulator(width, height, popSize, filler);

    Scanner kb = new Scanner(System.in);
    boolean run = true;
    while(run){
      String input = kb.next();
      if(input.equals("stop")){
        run = false;
      }
      test.runCycle();
    }


  }
}
