public class CardGame 
{
	public static void main(String[] args) 
	{
		//generate random card
		//display card
		 int card = (int) (Math.random() * 52.0); 	// picks a card from 0-51
		  int rank = card / 4; 						// determine the rank of the card 0-12
		  int suit = card % 4; 						// determine the suit of the card 0-3
		  String strRank = "";
		  String strSuit = "";
		 //determines the rank of the card
		  switch (rank) 
		  {
			  case 0:
			   strRank = "Ace";
			   break;
			  case 10:
			   strRank = "Jack";
			   break;
			  case 11:
			   strRank = "Queen";
			   break;
			  case 12:
			   strRank = "King";
			   break;
			  default:
			   strRank = "" + (rank + 1);
			   break;
		  }
		  //determines the suit of the card
		  switch (suit) 
		  {
			  case 0:
			   strSuit = "Clubs";
			   break;
			  case 1:
			   strSuit = "Diamonds";
			   break;
			  case 2:
			   strSuit = "Hearts";
			   break;
			  case 3:
			   strSuit = "Spades";
			   break;
		  }
		   //prints out the card
		  System.out.print("The card you picked is the " + strRank +" of " + strSuit );
		 }
		 
		}
	