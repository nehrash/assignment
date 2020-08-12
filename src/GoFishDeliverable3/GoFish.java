package GoFishDeliverable3;

import java.util.Scanner;

public class GoFish
{   
   public static Scanner input = new Scanner(System.in);
   
   public static void main(String[]args)
   {
      boolean program = true;
      int menu;
      do
      {
         System.out.println("\n\n\n[1] Test Deck Hand Class\n[2] Play Go Fish");
         menu = input.nextInt();
         switch(menu)
         {
            case 1:
               testDecks();
               break;
            case 2:
               GoFishGame game = new GoFishGame();
               game.playGoFish();
               break;
            
         }
         
      }while(program);
      System.out.println("endd!");
   }
   
   
   public static void testDecks()
   {
      boolean repTest = true;
      int hand;
      Deck fullDeck;
      Deck emptyHand;
      do
      {
         System.out.println("Choose a deck?\n" +
                            "1 Empty Hand\n2 Full Deck\n");
         hand = input.nextInt();
         switch(hand)
         {
            case 1:
               emptyHand = new Deck();
               repTest = testDeck(emptyHand);
               break;
            case 2:
               fullDeck = new Deck();
               fullDeck.fillDeck();
               repTest = testDeck(fullDeck);
               break;
         }
      }while(repTest);
   }
   
   public static boolean testDeck(Deck deck)
   {
       Card temp;
       int menu;
       boolean DeckTest = true;
       do
       {
            System.out.println("[1] Insert Card\n"+
                               "[2] Print Total number of Cards in Deck\n" +
                               "[3] Display Entire Deck hand\n" +
                               "[4] Change/Restart Decks\n[5] Exit ");
            menu = input.nextInt();
            switch(menu)
            {
               case 1:
                  int suit, value;
                  System.out.println("Please enter the Card you'd like to insert\n" +
                                     "\nSuits:\n" +
                                     "\t1 = Clubs\n\t2 = Diamonds\n\t3 = Hearts\n\t4 = Spades");
                  suit = input.nextInt();
                  System.out.println("enter any number from that card.");
                  value = input.nextInt();
                  Card insert = new Card(value, suit);
                  deck.insertCard(insert);
                  break;
             
               case 2:
                  System.out.println("Total number of Cards in Deck : " + deck.getSize());
                  break;
               case 3:
                  System.out.println(deck);
                  break;
               case 4:
                  DeckTest = false;
                  return true;
               case 5:
                  DeckTest = false;
                  return false;
            }
       }while(DeckTest);
       return true;
   }
}