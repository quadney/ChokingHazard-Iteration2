package Models;
import java.util.*;

public class PalaceCard
{
   private ArrayList<Integer> symbols;
   private String cardType;
   
   public PalaceCard(int cardType)
   {
      symbols = new ArrayList<Integer>();
      
      switch(cardType)
      {
         case 1:
            symbols.add(1);
            this.cardType = "palaceCard_DRUM";
            break;
         case 2:
            symbols.add(2);
            this.cardType = "palaceCard_MASK";
            break;
         case 3:
            symbols.add(3);
            this.cardType = "palaceCard_PUPPET";
            break;
         case 4:
            symbols.add(1);
            symbols.add(2);
            this.cardType = "palaceCard_MASK_DRUM";
            break;
         case 5:
            symbols.add(1);
            symbols.add(3);
            this.cardType = "palaceCard_PUPPET_DRUM";
            break;
         case 6:
            symbols.add(2);
            symbols.add(3);
            this.cardType = "palaceCard_PUPPET_MASK";
            break;
      }
   }
   
   public ArrayList<Integer> getSymbols()
   {
      return symbols;
   }
   
   public String getType(){
	   return this.cardType;
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