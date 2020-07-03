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

    Scanner kb = new Scanner(System.in);
    int decision=0;
    while(decision<=3){

      System.out.print("\n1) run cycle\n2) complete simulation\n3) modify parameters\n4) quit\n\nSELECTION: ");
      decision = kb.nextInt();

      switch(decision){
        case 1:
          Simulator cycles = new Simulator(width, height, popSize, filler);
          System.out.println("Type any key and enter to run new cycle. Type \"stop\" to quit iterating cycles");
          boolean run = true;
          while(run){
            String input = kb.next();
            if(input.equals("stop")){
              run = false;
            }
            cycles.printCycle();
          }
          break;

        case 2:
          Simulator completelySimulated = new Simulator(width, height, popSize, filler);
          completelySimulated.completeSimulation();
          break;

        case 3:
          System.out.print("Print new command line arguments, split by \",\": ");
          String input = kb.next();
          String[] newArgs = input.split(",");
          switch(newArgs.length){
            case 4:
              filler = newArgs[3];
            case 3:
              popSize = Integer.parseInt(newArgs[2]);
            case 2:
              width = Integer.parseInt(newArgs[0]);
              height = Integer.parseInt(newArgs[1]);
          }
      }
    }
  }
}
