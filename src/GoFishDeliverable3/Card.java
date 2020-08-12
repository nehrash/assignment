package GoFishDeliverable3;

public class Card
{
   private int suit1, value1;
   public Card(int value,int suit)
   {
      value1 = value;
      suit1 = suit;
   }
   public String toString()
   {
      String value = "";
      if(value1 > 10)
      {
         switch(value1)
         {
            case 11:
               value = "Jack of ";
               break;
            case 12:
               value = "Queen of ";
               break;
            case 13:
               value = "King of ";
               break;
         }
       }else if(value1 == 1)
       {
         value = "Ace of ";
       }else
         value = "" + value1 + " of ";
       String suit = "";
       if(suit1 >= 1 && suit1 <= 4)
       {
         switch(suit1)
         {
            case 1:
               suit = "Clubs";
               break;
            case 2:
               suit = "Diamonds";
               break;
            case 3:
               suit = "Hearts";
               break;
            case 4:
               suit = "Spades";
               break;
         }
       }
       return value + suit;
          
   }
   public int getSuit()
   {
      return suit1;
   }
   public int getValue()
   {
      return value1;
   }

}