package Models;

public class JavaCell extends Cell
{
   private int elevation;
   private String cellType;
   
   public JavaCell(int x, int y)
   {
      super(x,y);
      elevation = 0;
      cellType = "BLANK";
   }
   
   public int getElevation()
   {
      return elevation;
   }
   
   public String getSpaceType()
   {
      return spaceType;
   }
   
   public void setElevation(int e)
   {
      elevation = e;
   }
   
   public void setCellType(String ct)
   {
      cellType = ct;
   }
}