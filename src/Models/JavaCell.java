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
   
   public boolean isBorder()
   {
	   return false; //TODO
   }
   
   public boolean hasAdjacentLandSpaceTile()	//TODO: finish brett
   {
	   int x = this.getX();
	   int y = this.getY();
	  /* 
	   if ((x + 1) == 14)
	   {
		   if (map.isTileOrLand(x+1,y))
			   return true;
	   }
	   
	   if (x- 1 == 0)
	   {
		   if (map.isTileOrLand(x-1,y))
			   return true;
	   }
	   if (y + 1 == 14)
	   {
		   if (map.isTileOrLand(x,y+1))
			   return true;
	   }
	   if (y - 1 == 0)
	   {
		   if (board.isTileOrLand(x,y-1))
			   return true;
	   }
	   */ 
	   return false;
		   
   }
   
}