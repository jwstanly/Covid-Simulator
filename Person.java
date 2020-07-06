public class Person{

  private int x, y;
  private boolean sick;
  private boolean immune;
  private int cyclesSick = 0;

  public Person(int x, int y){
    this.x = x;
    this.y = y;
    this.sick = false;
    this.immune = false;
  }

  public Person(int x, int y, boolean sick){
    this.x = x;
    this.y = y;
    this.sick = sick;
    this.immune = false;
  }

  public void setX(int x){
    this.x = x;
  }

  public int getX(){
    return x;
  }

  public void setY(int y){
    this.y = y;
  }

  public int getY(){
    return y;
  }

  public void setSick(boolean sick){
    this.sick = sick;
  }

  public boolean isSick(){
    return sick;
  }

  public void setImmune(boolean immune){
    this.immune = immune;
  }

  public boolean isImmune(){
    return immune;
  }

  public void incrementCyclesSick(){
    cyclesSick++;
  }

  public int getCyclesSick(){
    return cyclesSick;
  }

  public boolean isNextTo(Person other){
    //right
    if(this.getX()==(other.getX()+1) && this.getY()==other.getY()){
      return true;
    }//left
    if(this.getX()==(other.getX()-1) && this.getY()==other.getY()){
      return true;
    }//up
    if(this.getY()==(other.getY()+1) && this.getX()==other.getX()){
      return true;
    }//down
    if(this.getY()==(other.getY()-1) && this.getX()==other.getX()){
      return true;
    }//upleft
    if(this.getY()==(other.getY()+1) && this.getX()==(other.getX()-1)){
      return true;
    }//upright
    if(this.getY()==(other.getY()+1) && this.getX()==(other.getX()+1)){
      return true;
    }//downleft
    if(this.getY()==(other.getY()-1) && this.getX()==(other.getX()-1)){
      return true;
    }//downright
    if(this.getY()==(other.getY()-1) && this.getX()==(other.getX()+1)){
      return true;
    }
    return false;
  }

  public String toString(){
    if(sick) return "1";
    if(!sick && immune) return "2";
    if(!sick) return "0";
    else return "ERROR";
  }

}
