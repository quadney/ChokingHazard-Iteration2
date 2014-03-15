package Models;
import java.util.*;

public class PalaceCard
{
   public ArrayList<Integer> symbols;
   
   public PalaceCard(int cardType)
   {
      symbols = new ArrayList<Integer>();
      
      switch(cardType)
      {
         case 1:
            symbols.add(1);
            break;
         case 2:
            symbols.add(2);
            break;
         case 3:
            symbols.add(3);
            break;
         case 4:
            symbols.add(1);
            symbols.add(2);
            break;
         case 5:
            symbols.add(1);
            symbols.add(3);
            break;
         case 6:
            symbols.add(2);
            symbols.add(3);
            break;
      }
   }
   
   public ArrayList<Integer> getSymbols()
   {
      return symbols;
   }
   
   //Returns the point value of cards
   public int compare(PalaceCard card)
   {
      int pnts = 0;
      
      for(Integer i:card.getSymbols())
      {
         if(this.symbols.contains(i))
            pnts++;
      }
      
      return pnts;
   }
}