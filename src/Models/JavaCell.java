package Models;

public class JavaCell extends Cell
{
   private int elevation;
   private String cellType;
   private boolean hasDeveLoper;
   private int cellId;
   
   public JavaCell(int x, int y, String cellType, int cellId)
   {
      super(x,y);
      elevation = 0;
      this.cellType = cellType;
      this.cellId = cellId;
   }
   
   public JavaCell(int x, int y, int cellId)
   {
      super(x,y);
      elevation = 0;
      cellType = "blank";
      this.cellId = cellId;
   }
   
   public int getCellId()
   {
      return cellId;
   }
   
   public void setCellId(int cellId){
	   this.cellId = cellId;
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