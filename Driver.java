import java.util.*;
import java.io.*;

public class Driver{

  public static void main(String args[]) throws IOException{

    //set to true for debugging features
    boolean debugging = false;

    //STARTING CONDITIONS
    int width;
    int height;
    int popSize;
    double startingRate;
    double immunityRate;
    String filler;

    //default values
    width = 20;
    height = 50;
    popSize = 20;
    startingRate = 0.03;
    immunityRate = 5;
    filler = "-";

    //checks for passed arguments
    switch(args.length){
      case 6:
        filler = args[5];
      case 5:
        startingRate = Double.parseDouble(args[3]);
        immunityRate = Double.parseDouble(args[4]);
      case 3:
        popSize = Integer.parseInt(args[2]);
      case 2:
        width = Integer.parseInt(args[0]);
        height = Integer.parseInt(args[1]);
    }

    if(debugging)
      System.out.println("PASSED ARGS: "+
                        "\nwidth: "+width+
                        "\nheight: "+height+
                        "\npopSize: "+popSize+
                        "\nstartingRate: "+startingRate+
                        "\nimmunityRate: "+immunityRate+
                        "\nfiller: \""+filler+"\"");//*/

    Scanner kb = new Scanner(System.in);
    int decision=0;
    while(decision<=5){

      System.out.print("\n1) run cycle\n2) complete simulation\n3) export simulations\n4) modify parameters\n5) manual mode\n6) quit\n\nSELECTION: ");
      decision = kb.nextInt();

      switch(decision){
        case 1:
          Simulator cycles = new Simulator(width, height, popSize, startingRate, immunityRate, filler);
          System.out.println("Type any key and then press enter to run new cycle. Type \"stop\" to quit iterating cycles");
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
          Simulator completelySimulated = new Simulator(width, height, popSize, startingRate, immunityRate, filler);
          completelySimulated.completeSimulation();
          break;

        case 3:
          System.out.print("# of sumulations: ");
          int rows = kb.nextInt();

          int[][] exports = new int[rows][];

          for(int x=0;x<rows;x++){
            Simulator temp = new Simulator(width, height, popSize, startingRate, immunityRate, filler);
            int[] collumn = temp.completeSimulation();
            exports[x] = collumn;
          }

          File data= new File("data x"+rows+".csv");
          FileWriter fw = new FileWriter(data);
          PrintWriter pw = new PrintWriter(fw);

          //find largest simulation for collumn size
          int collumns = 0;
          for(int x=0;x<rows;x++)
            if(exports[x].length > collumns)
              collumns = exports[x].length;

          for(int y=0;y<collumns;y++){
            String row=(y+1)+",";
            for(int x=0;x<rows;x++){
              if(y < exports[x].length){
                row+=exports[x][y]+",";
              }
              else row+=",";
            }
            pw.println(row);
          }
          pw.close();
          fw.close();
          break;

        case 4:
          System.out.print("Print new command line arguments, split by \",\": ");
          String input = kb.next();
          String[] newArgs = input.split(",");
          switch(newArgs.length){
            case 6:
              filler = newArgs[3];
            case 5:
              startingRate = Double.parseDouble(newArgs[3]);
              immunityRate = Double.parseDouble(newArgs[4]);
            case 3:
              popSize = Integer.parseInt(newArgs[2]);
            case 2:
              width = Integer.parseInt(newArgs[0]);
              height = Integer.parseInt(newArgs[1]);
          }
          break;

        //this is case 4 then case 3 combined... sorry for repetitive code
        case 5:
          //case 4
          System.out.print("Print new command line arguments, split by \",\": ");
          String input2 = kb.next();
          String[] newArgs2 = input2.split(",");
          switch(newArgs2.length){
            case 6:
              filler = newArgs2[3];
            case 5:
              startingRate = Double.parseDouble(newArgs2[3]);
              immunityRate = Double.parseDouble(newArgs2[4]);
            case 3:
              popSize = Integer.parseInt(newArgs2[2]);
            case 2:
              width = Integer.parseInt(newArgs2[0]);
              height = Integer.parseInt(newArgs2[1]);
          }

          //case 3
          System.out.print("# of sumulations: ");
          int rows2 = kb.nextInt();

          int[][] exports2 = new int[rows2][];

          for(int x=0;x<rows2;x++){
            Simulator temp = new Simulator(width, height, popSize, filler);
            int[] collumn = temp.completeSimulation();
            exports2[x] = collumn;
          }

          File data2= new File("data x"+rows2+".csv");
          FileWriter fw2 = new FileWriter(data2);
          PrintWriter pw2 = new PrintWriter(fw2);

          //find largest simulation for collumn size
          int collumns2 = 0;
          for(int x=0;x<rows2;x++)
            if(exports2[x].length > collumns2)
              collumns2 = exports2[x].length;

          for(int y=0;y<collumns2;y++){
            String row=(y+1)+",";
            for(int x=0;x<rows2;x++){
              if(y < exports2[x].length){
                row+=exports2[x][y]+",";
              }
              else row+=",";
            }
            pw2.println(row);
          }
          pw2.close();
          fw2.close();
          break;
      }
    }
  }
}
