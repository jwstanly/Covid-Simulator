import java.util.ArrayList;

public class Simulator{

  private final int width;
  private final int height;
  private final int popSize;
  private final String filler;

  private String[][] table;
  private Person[] people;

  private int cycles = 0;

  private int cases = 0;
  private ArrayList<Integer> caseCount = new ArrayList<Integer>();

  public Simulator(int w, int h, int pS, String f){
    width = w;
    height = h;
    popSize = pS;
    filler = f;

    table = new String[width][height];
    people = new Person[popSize];

    fillTable(filler);

    for(int x=0;x<popSize;x++){
      int randX = (int)(Math.random()*width);
      int randY = (int)(Math.random()*height);
      boolean isSick = Math.random() < 0.15 ? true : false;
      if(isSick) cases++;
      people[x] = new Person(randX, randY, isSick);
    }
    fillPeople();

  }

  public Simulator(int w, int h, int pS){
    this(w, h, pS, "-");
  }

  public Simulator(int w, int h){
    this(w, h, ((int)(0.02*w*h)), "-");
  }

  public Simulator(){
    this(20, 50, 20, "-");
  }

  public void runCycle(){
    for(int x=0;x<popSize;x++) movePerson(x);
    spreadCovid();
    fillTable(filler);
    fillPeople();
    caseCount.add(cases);
    cycles++;
  }

  public void printCycle(){
    runCycle();
    printTable();
  }

  public void completeSimulation(){
    if(cases==0){
      System.out.println("No cases to run simulation");
    }else{
      while(cases<popSize) runCycle();
      for(int x=0;x<caseCount.size();x++)
        System.out.print(caseCount.get(x)+" ");
      System.out.println("\nTotal cycles: " + cycles);
    }
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
          if(people[x].isNextTo(people[y]) && !people[y].isSick()){
              people[y].setSick(true);
              cases++;
              //System.out.println("new COVID case!");
          }
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
