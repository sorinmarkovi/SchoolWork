/* 1. Gather requirements
 * 2. Describe the application
 * 3. Identify the main objects
 * 4. Describe interactions
 * 5. Create a class diagram
 */
public class MillionCoins 
{
	public static void main(String[] args) 
	{
		// TODO Write a program that simulates flipping a coin one million times and displays the number of heads and tails.
		coinFlip();
	}
	public static void coinFlip()
	{
		//declare variables to use
		int counter = 0;	//going to be the thing we use to figure out if it's heads or tails
		int heads = 0;		//heads counter; if the counter is 1, one is added. 
		int tails = 0;		//tails counter; if the counter is not one, one is added. 
		for (int i = 0; i < 1e6; i++) //i is the counter, if i is less than a million, then add one to i
		{
			counter = (int) Math.floor((Math.random() * 2)); //set counter to a random number 
			if (counter == 1) 
			{
				heads++;
			} 
			else 
			{
				tails++;
			}
		}
		System.out.println("The number of heads is " + heads);
		System.out.println("The number of tails is " + tails);
	}
}