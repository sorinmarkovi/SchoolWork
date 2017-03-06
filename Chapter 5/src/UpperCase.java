
/* 1. Gather requirements
 * 2. Describe the application
 * 3. Identify the main objects
 * 4. Describe interactions
 * 5. Create a class diagram
 */
import java.util.Scanner;
public class UpperCase 
{
	public static void main(String[] args) 
	{
		// TODO (Count uppercase letters) Write a program that prompts the user
		// to enter a string and displays the number of the uppercase letters in
		// the string.
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a string:");
		String s = input.nextLine(); 	//get a string from the user
		input.close();					//close the leak
		int numberOfUpperCase = countUpperCase(s);
		System.out.println("The number of uppercase letters is " + numberOfUpperCase);
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
}