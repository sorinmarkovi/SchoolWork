
/* 1. Gather requirements
 * 2. Describe the application
 * 3. Identify the main objects
 * 4. Describe interactions
 * 5. Create a class diagram
 */
import java.util.InputMismatchException;
import java.util.Scanner;
public class UpperCase 
{
	public static void main(String[] args) 
	{
		// TODO (Count uppercase letters) Write a program that prompts the user to enter a string and displays the number of the uppercase letters in the string.
		sentenceConstructor();
	}
	public static int countUpperCase(String s)
	{
		int numberOfUpperCase = 0;

		for (int i = s.length() - 1; i >= 0; i--) //start by having a counter equal to the length of s - 1, while i is more than 0, and then subtract one from i
		{
			if ('A' <= s.charAt(i) && s.charAt(i) <= 'Z') 
			{
				numberOfUpperCase++;
			}
		}
		return numberOfUpperCase;
	}
	public static void sentenceConstructor()
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a string:");
		String s = input.nextLine(); 	//get a string from the user
						//close the leak
		int numberOfUpperCase = countUpperCase(s);
		System.out.println("The number of uppercase letters is " + numberOfUpperCase);
		replayGame();
		input.close();
	}
	private static void replayGame()
	{
		Scanner scan = new Scanner(System.in);
		boolean continueInput = true;
		boolean falseI = true;
		int replay = 0;
		String answer;
		do
		{
			do
				{
				try 
				{		
					do
					{
					System.out.print("Do you to continue Y/N: ");
				    answer = scan.nextLine();
				    
				    switch (answer)
					    {
					    case "Y":
					    	falseI = false;
					    	sentenceConstructor();
					    		break;
					    case "y":
					    	falseI = false;
					    	sentenceConstructor();
					            break;
					    case "N"://exit from program;
					    	System.exit(0);
					    		break;
					    case "n":
					    	System.exit(0);
					            break;
					    default :
					             System.out.println("invalid choice");
					             falseI = true;
					             break;
					   }
					}
					while(falseI);
				}
				catch (InputMismatchException e)
					{
						do
						{
						System.out.println("Try again. (" +  "Incorrect input: an integer is required)");          
						replay = scan.nextInt(); 
						falseI = true;
						}
						while(falseI);
					}
				continueInput = false;
				}
			while (continueInput == true);	
		}
		while (replay == 0);
		scan.close();	
	}
}