
public class Helper{

  public static <T> void print2DArray(T array[][]){
    for(int x=0;x<array.length;x++){
      for(int y=0;y<array[x].length;y++){
        System.out.print(array[x][y]+" ");
      }
      System.out.print("\n");
    }
  }

  public static <T> void fill2DArray(T array[][], T filler){
    for(int x=0;x<array.length;x++){
      for(int y=0;y<array[x].length;y++){
        array[x][y] = filler;
      }
    }
  }
}
