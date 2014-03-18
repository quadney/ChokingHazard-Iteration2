package Models;

public abstract class Cell {
   protected int xVal;
   protected int yVal;   
   
   public Cell(int x, int y) {
      xVal = x;
      yVal = y;
   }
   
   public int getX() {
      return xVal;
   }
   
   public int getY() {
      return yVal;
   }
   
   public void setX(int x) {
      xVal = x;
   }
   
   public void setY(int y) {
      yVal = y;
   }
}
