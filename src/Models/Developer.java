package Models;

public class Developer
{
   private Cell location;
   private JavaPlayer owner;
   
   public Developer(JavaPlayer p)
   {
      //developer not on the board yet, so location is null
      location = null;
      owner = p;
   }
   
   public void setLocation(Cell c)
   {
      location = c;
   }
   
   public void setOwner(JavaPlayer p)
   {
      owner = p;
   }
   
   public Cell getLocation()
   {
      return location;
   }
   
   public JavaPlayer getOwner()
   {
      return owner;
   }
}