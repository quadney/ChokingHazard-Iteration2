package Models;

public abstract class Player
{
   protected String name;
   protected String color;
   
   public Player(String nm, String c)
   {
      name = nm;
      color = c;
   }
   
   public String getName()
   {
      return name;
   }
   
   public String getColor()
   {
      return color;
   }
}