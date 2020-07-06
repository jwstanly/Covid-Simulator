import java.util.*;
import java.io.*;

public class Simulator{

  private final int width;
  private final int height;
  private final int popSize;
  private final double startingRate;
  private final double immunityRate;
  private final String filler;

  private String[][] table;
  private Person[] people;

  private int cycles = 0;

  private int cases = 0;
  private ArrayList<Integer> caseCount = new ArrayList<Integer>();

  public Simulator(int w, int h, int pS, double sR, double iR, String f){
    width = w;
    height = h;
    popSize = pS;
    startingRate = sR;
    immunityRate = iR;
    filler = f;

    //exception handeling
    if(width<0 || height<0)
      throw new IllegalArgumentException("cannot create a grid with a negative size");
    if(popSize<0)
      throw new IllegalArgumentException("simulation must contain people");
    if(startingRate<=0 || startingRate>1)
      throw new IllegalArgumentException("starting rate must be in the domain (0,1]");
    if(immunityRate<0)
      throw new IllegalArgumentException("immunity rate cannot be below zero");


    table = new String[width][height];
    people = new Person[popSize];

    fillTable(filler);

    for(int x=0;x<popSize;x++){
      int randX = (int)(Math.random()*width);
      int randY = (int)(Math.random()*height);
      boolean isSick = Math.random() < startingRate ? true : false;
      if(isSick) cases++;
      people[x] = new Person(randX, randY, isSick);
    }
    fillPeople();

  }

  //soon to be deprecated constructor (old param order)
  public Simulator(int w, int h, int pS, String f){
    this(w, h, pS, 0.03, 5, f);
  }

  public Simulator(int w, int h, int pS){
    this(w, h, pS, 0.03, 5, "-");
  }

  public Simulator(int w, int h){
    this(w, h, ((int)(0.02*w*h)), 0.03, 5, "-");
  }

  public Simulator(){
    this(20, 50, 20, 0.03, 5, "-");
  }

  public void runCycle(){
    for(int x=0;x<popSize;x++){
      movePerson(x);
      if(people[x].isSick()) people[x].incrementCyclesSick();
    }
    spreadCovid();
    beatCovid();
    fillTable(filler);
    fillPeople();
    caseCount.add(cases);
    cycles++;
  }

  public void printCycle(){
    runCycle();
    printTable();
  }

  public int[] completeSimulation(){
    if(cases==0){
      System.out.println("No cases to run simulation");
    }else{
      while(/*cases<popSize && //*/ cases!=0) runCycle(); //uncomment for testing max population infection
      for(int x=0;x<caseCount.size();x++)
        System.out.print(caseCount.get(x)+" ");
      System.out.println("\nTotal cycles: " + cycles);
    }
    //conversion to array
    int[] export = new int[caseCount.size()];
    for(int x=0;x<export.length;x++){
      export[x] = caseCount.get(x);
    }

    //infection score (area under the curve)
    int infectionScore=0;
    for(int cases : export)
    infectionScore +=  cases;
    System.out.println("Infection Score: "+infectionScore);

    return export;
  }

  public void fillPeople(){
    for(int x=0;x<popSize;x++){
      int tempX = people[x].getX();
      int tempY = people[x].getY();
      table[tempX][tempY] = people[x].toString();
    }
  }

  public void movePerson(int ID){
    int tempX = people[ID].getX();
    int tempY = people[ID].getY();
    int moveChoice = (int)(Math.random()*5);
    switch(moveChoice){
      case 0: break;
      case 1: tempY++; break;
      case 2: tempX++; break;
      case 3: tempY--; break;
      case 4: tempX--; break;
    }
    if(tempX >= 0 && tempX < width){
      people[ID].setX(tempX);
    }
    if(tempY >= 0 && tempY < height){
      people[ID].setY(tempY);
    }
  }

  public void spreadCovid(){
    for(int x=0;x<popSize;x++){
      if(people[x].isSick()){
        for(int y=0;y<popSize;y++){
          if(people[x].isNextTo(people[y]) && !people[y].isSick() && !people[y].isImmune()){
              people[y].setSick(true);
              cases++;
          }
        }
      }
    }
  }

  public void beatCovid(){
    for(int x=0;x<popSize;x++){
      if(people[x].isSick()){
        double chanceOfBeatingCovid = Math.random() * 0.0025 * people[x].getCyclesSick();
        if(chanceOfBeatingCovid > immunityRate){
          people[x].setSick(false);
          people[x].setImmune(true);
          cases--;
        }
      }
    }
  }

  public void printTable(){
    System.out.println("CYCLE: "+cycles);
    for(int x=0;x<table.length;x++){
      for(int y=0;y<table[x].length;y++){
        System.out.print(table[x][y]+" ");
      }
      System.out.print("\n");
    }
  }

  public void fillTable(String filler){
    for(int x=0;x<table.length;x++){
      for(int y=0;y<table[x].length;y++){
        table[x][y] = filler;
      }
    }
  }

}
