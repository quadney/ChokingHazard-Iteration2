package Models;

public class JavaCell extends Cell
{
   private int elevation;
   private String cellType;
   private boolean hasDeveLoper;
   
   public JavaCell(int x, int y, String cellType)
   {
      super(x,y);
      elevation = 0;
      this.cellType = cellType;
   }
   
   public JavaCell(int x, int y)
   {
      super(x,y);
      elevation = 0;
      cellType = "blank";
   }
   
   public int getElevation()
   {
      return elevation;
   }
   
   public String getCellType()
   {
      return cellType;
   }
   
   public void setElevation(int e)
   {
      elevation = e;
   }
   
   public void setCellType(String ct)
   {
      cellType = ct;
   }
   
   public void setDeveloper(){
	   this.hasDeveLoper = true;
   }
   
   public void removeDeveloper(){
	   this.hasDeveLoper = false;
   }
   
   public boolean hasDeveloper(){
	   return hasDeveLoper;
   }
}