/* 1. Gather requirements
 * 2. Describe the application
 * 3. Identify the main objects
 * 4. Describe interactions
 * 5. Create a class diagram
 */
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Quiz 
{
	public static void main(String[] args) 
	{
		// TODO Create a program that takes 4 numbers from the user and tells what the largest and the smallest numbers are.
		numberSelector();
	}
	public static void numberSelector() 
	{
		// declare
		Scanner numberOfNums = new Scanner(System.in);
		Scanner sc = new Scanner(System.in);
		boolean loopBol = true;
		int x= 0;
		
		System.out.println("Please enter how many numbers you want to enter.");
		do {
			try 
			{
				x = numberOfNums.nextInt();
				loopBol = false;
			} 			
			catch (InputMismatchException e) 
			{
				System.out.println("Try again. (" + "Incorrect input: an integer is required)");
				numberOfNums.nextLine();
			}
		} 
		while (loopBol);
		int integers[] = new int[x];
		System.out.println("Please enter " + x + " number" + ((x > 1) ? "s" : " "));
		addToArray(0,integers,x);	
		System.out.println("The minimum value is: " + getMinValue(integers));
		System.out.println("The maximum value is: " + getMaxValue(integers));
		//System.out.println("\n" + Arrays.sort(integers));
		Arrays.sort(integers);
		System.out.println("The sorted int array is:");
		for (int number : integers) 
		{
			System.out.print(" " + number + " ");
		}
		sc.close();
		numberOfNums.close();
	}
	private static int getMinValue(int[] integers) 
	{
		// get the minimum value from the array
		int minValue = integers[0];

		for (int x = 1; x < integers.length; x++) 
		{
			if (integers[x] < minValue) 
			{
				minValue = integers[x];
			}
		}
		return minValue;
	}
	private static int getMaxValue(int[] integers) 
	
	{
		// get the max value from the array
		int maxValue = integers[0];

		for (int x = 1; x < integers.length; x++) 
		{
			if (integers[x] > maxValue) 
			{
				maxValue = integers[x];
			}
		}
		return maxValue;

	}
	private static int[] addToArray(int j, int[] a, int x)
	{
		Scanner sc = new Scanner(System.in);	
		try
		{
			for (int i = j ; i < x; i++)
			{
				a[i] = sc.nextInt();
				j++;
			}
		}	
		catch(Exception e)
		{
			System.out.println("Please enter a proper number: ");
			addToArray(j, a, x);
		}	
		sc.close();
		return a;
	}
}