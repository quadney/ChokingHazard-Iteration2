package Models;

public class Cell 
{
   private int xVal;
   private int yVal;
   
   private int elevation;

   //String refering to the type of cell, village, rice, palace, etc.
   private String cellType;
   
   
   public Cell(int x, int y)
   {
      xVal = x;
      yVal = y;
      elevation = 0;
      cellType = "BLANK";
   }
   
   public int getX()
   {
      return xVal;
   }
   
   public int getY()
   {
      return yVal;
   }
   
   public int getElevation()
   {
      return elevation;
   }
   
   public String getCellType()
   {
      return cellType;
   }
}
