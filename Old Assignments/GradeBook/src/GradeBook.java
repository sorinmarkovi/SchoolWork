/* 1. Gather requirements
 * 2. Describe the application
 * 3. Identify the main objects
 * 4. Describe interactions
 * 5. Create a class diagram
 */
import java.util.InputMismatchException;
import java.util.Scanner;
public class GradeBook 
{
	
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		boolean loopBol = true;
		int x= 0;
		
		System.out.println("Please enter how many numbers you want to enter.");
		do {
			try 
			{
				x = sc.nextInt();
				loopBol = false;
			} 			
			catch (InputMismatchException e) 
			{
				System.out.println("Try again. (" + "Incorrect input: an integer is required)");
				sc.nextLine();
			}
		} 
		while (loopBol);

		int integers[] = new int[x];

		System.out.println("Please enter " + x + " number" + ((x > 1) ? "s" : " "));
		addToArray(0,integers,x);	
		
		average(integers);

		
		sc.close();
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
	public static void average(int[] data) 
	{  
	    int sum = 0;
	    double average;

	    for(int i=0; i < data.length; i++)
	    {
	        sum = sum + data[i];
	    }
	    average = (double)sum/data.length;
	    System.out.println("Average value of array element is " + average);    
	}
	// add a method to return a letter grade. 
	//TODO Add a different method
}