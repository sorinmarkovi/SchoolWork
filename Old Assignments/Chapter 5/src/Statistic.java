/* 1. Gather requirements
 * 2. Describe the application
 * 3. Identify the main objects
 * 4. Describe interactions
 * 5. Create a class diagram
 */
import java.util.Scanner;
public class Statistic 
{
	public static void main(String[] args) 
	{
		// (Statistics: compute mean and standard deviation) In business
		// applications, you
		// are often asked to compute the mean and standard deviation of data.
		// The mean is
		// simply the average of the numbers. The standard deviation is a
		// statistic that tells
		// you how tightly all the various data are clustered around the mean in
		// a set of data.
		// For example, what is the average age of the students in a class? How
		// close are the
		// ages? If all the students are the same age, the deviation is 0.
		// Write a program that prompts the user to enter ten numbers, and
		// displays the
		// mean and standard deviations of these numbers.
		mathPart();
	}
	public static void mathPart()
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter ten numbers:");
		// Enter amount
		double sum = 0;
		double sumsq = 0;
		for (int i = 0; i < 10; i++) 
		{
			double n = input.nextDouble();
			sum += n;
			sumsq += Math.pow(n, 2);
		}
		System.out.println("The mean is " + sum / 10);
		System.out.println("The standard deviation is " + Math.sqrt(((sumsq - Math.pow(sum, 2) / 10)) / 9));
		input.close();
	}
}