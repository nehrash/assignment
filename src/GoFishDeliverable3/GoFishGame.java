package GoFishDeliverable3;

import java.util.Scanner;

public class GoFishGame
{
   public static Scanner input = new Scanner(System.in);
   private Deck deckOfCards;
   private Deck playerHand;
   private Deck sysHand;
   private boolean win;
   private int playerBooks, sysBooks;
   
   public GoFishGame()
   {
      deckOfCards = new Deck();
      deckOfCards.fillDeck();
      playerHand = new Deck();
      sysHand = new Deck();
      win = false;
      playerBooks = playerBooks = 0;
   }

   public void startGame()
   {
      System.out.println("[1] Start Game");
      int startGame = input.nextInt();
      while(startGame > 2 || startGame < 1)
      {
         System.out.println("Invalid input \nTry Again.");
         startGame = input.nextInt();
      }
      switch(startGame)
      {
         case 1:
            playGoFish();
            break;
         case 2:
            System.out.println("\n\nRULES:\n" +
                               "The User and the Oppent(Computer) both start with" +
                               " 7 cards. The user\nasks for a Card by entering the value" +
                               " as a number. Ace is 1 and Jack,\nQueen,King are 11,12,13." +
                               "If the card you've asked for is contained in\nthe deck of" +
                               " the oppent then you get all of their cards with that value.\n" +
                               "If guessed incorrectly the player must draw from the Table Deck." +
                               " If the\ncard drawn from the Table Deck then the player goes again." +
                               "The game ends\nif either the Table Deck, User Hand, or Computer" +
                               " Hand are empty. The \nplayer with the most Books, which are 4 cards" +
                               " of the same value, wins \nthe game.\n\t\tPress Enter to start the Game");
            input.nextLine();
            input.nextLine();
            playGoFish();
            break;
      }
      
   }
   public void playGoFish()
   {
      initialHands();
     // checkBegginingDeal();
      
      do
      {
         playerTurn();
         checkGameOver();
         
         compTurn();
         checkGameOver();
         
      }while(!win);
      
      System.out.println("Game Over!");
      Winner();
   }
   
   public void initialHands()
   {
      for(int i = 0; i<7; i++)
      {
         playerHand.insertCard(deckOfCards.deleteAnyCard());
         sysHand.insertCard(deckOfCards.deleteAnyCard());
      }
   }
   
  /* public void checkBegginingDeal()
   {
      int userBegDeal = playerHand.checkBookBegginingDeal();
      if(userBegDeal != 0)
      {
         System.out.println("Wow! You got extremely lucky and got a book " +
                            "on the deal of the value " + userBegDeal + 
                            "\nThat puts you at 1 Book already!");
         //pause();
         playerBooks++;
      }

      int cpBegDeal = sysHand.checkBookBegginingDeal();
      if(cpBegDeal != 0)
      {
         System.out.println("Wow! The computer got extremely lucky and got a " +
                            "book on the deal of the value " + cpBegDeal + 
                            "\nThat puts the computer at 1 Book already!");
         //pause();
         sysBooks++;
      }
   }*/
   
   public void playerTurn()
   {
      boolean retry = false;
      do
      {  
         retry = false;
         if(!win)
         {
            Card drawnCard = null;
            System.out.println(playerHand);
            System.out.println("Which value would you like to ask for?");
            int value = input.nextInt();
            while(playerHand.getCount(value) == 0)
            {
               System.out.println("already in your deck, " +
                                     "Please enter another");
               value = input.nextInt();
            }
            int hits = sysHand.getCount(value);
            if(hits == 0)
            {
               System.out.println("Go Fish!");
               drawnCard = deckOfCards.deleteAnyCard();
               if(drawnCard.getValue() == value)
               {
                  playerHand.insertCard(drawnCard);
                  retry = true;
                  System.out.println("Drawn Card: " + drawnCard);
                  System.out.println("Lucky Draw! Go again.");
               }else
               {
                 System.out.println("Drawn Card: " + drawnCard);
                 playerHand.insertCard(drawnCard);
               }
               int countAfterGoFish = playerHand.getCount(drawnCard.getValue());
               if(countAfterGoFish == 4)
               {
                  playerBooks++;
                  System.out.println("You now have " + playerBooks + " Books\n" +
                                     "And the system has" + sysBooks + " Books");
                  for(int i = 0; i<4; i++)
                  {
                      playerHand.deleteValue(drawnCard.getValue());
                  }
               }
               if(retry)
               {
                  checkGameOver();
               } 
            }
         }
      }while(retry);
   }
   
   public void compTurn()
   {
      boolean retryComp = false;
      do
      {
         retryComp = false;
         if(!win)
         {
            Card sysGuessCard = null;
            Card random = sysHand.deleteAnyCard();
            sysHand.insertCard(random);
            int Value = random.getValue();
            int sysScore = playerHand.getCount(Value);
            if(sysScore == 0)
            {
               sysGuessCard = deckOfCards.deleteAnyCard();
               if(sysGuessCard.getValue() == Value)
               {
                  sysHand.insertCard(sysGuessCard);
                  retryComp = true;
               }else
               {
                  System.out.println( "Your turn.");
                  sysHand.insertCard(sysGuessCard); 
               }                                                             
               int sysFinalScore = sysHand.getCount(sysGuessCard.getValue());
               if(sysFinalScore == 4) 
               {
                  sysBooks++;
                  for(int i = 0; i<4; i++)
                  {
                      sysHand.deleteValue(sysGuessCard.getValue());
                  }
               }
               if(retryComp)
               {
                  checkGameOver();
               } 
            }else if(!retryComp && sysScore >= 1)
            {
               for(int i = 0; i < sysScore; i++)
               {
                  sysHand.insertCard(playerHand.deleteValue(Value));
               }
               System.out.println("The computer took " + sysScore + " of your cards!");
               retryComp = false;
               int cpCountAfterSteal = sysHand.getCount(Value);
               if(cpCountAfterSteal == 4)
               {
                  sysBooks++;
                  System.out.println("The computer just got a book from stealing your card(s)" +
                                     " with the value " + Value + "\n" +
                                     "The computer now has : " + sysBooks + " Books\n" +
                                     "You currently have : " + playerBooks + " Books");
                 // pause();
                  for(int i = 0; i<4; i++)
                  {
                      sysHand.deleteValue(Value);
                  }
                }
            }
         }
      }while(retryComp);
   }
   
   public void checkGameOver()
   {
      win = (deckOfCards.getSize() == 0 || playerHand.getSize() == 0
                        || sysHand.getSize() == 0);
   }
   
   public void Winner()
   {
      if(sysBooks > playerBooks)
      {
         System.out.println("you lost");
      }else if(playerBooks > sysBooks)
      {
         System.out.println("you are the winner");
      }else if(playerBooks == sysBooks)
      {
         System.out.println("its a tie");
      }
   }
   
}