Q : What do you hope to get out of this class.

A: I hope to work further into Computer Science, and I look forward to working in teams.  

Q: Use a print statement to sum 1/1 - 1/6 and 1/6 - 1/1.

A: I will include code after this.

Q: Explain what you find

A: Due to integers not being able to have fractions, it rounds.

Q: Use integers to sum 1/1 - 1/6 and 1/6 - 1/1.

A: I will include code after this

Q: Explain what you find

A: Due to integers not being able to have fractions, it rounds.

Q: Use doubles to sum 1/1 - 1/6 and 1/6 - 1/1.

A: I will include code after this.

Q: Explain what you find

A: It adds them properly because it does NOT round the fractions.

Q: Demonstrate the use of a scanner to read in a double.

A: See code.

Q: Demonstrate the use of an if statement

A: See code.

Q: Each person has their own branch

Q: This assignment is uploaded to each person's own branch and one member from each team merges their personal branch up to the dev branch.

```java
import java.util.InputMismatchException;
import java.util.Scanner;
public class HW1Review
{
	public static void main(String[] args)
	{
		starterSum();
		// TODO Use integers to sum 1/1 - 1/6 and 1/6 - 1/1.
			// TODO explain what you find
		starterSum();
		doubleSum();
		chooseNumber();
		ifElseState();
	}
	public static void starterSum()
	{
		// TODO Use a print statement to sum 1/1 - 1/6 and 1/6 - 1/1.
				System.out.println(1/1 + 1/2 + 1/3 + 1/4 + 1/5  + 1/6);
				System.out.println(1/6 + 1/5 + 1/4 + 1/3 + 1/2  + 1/1);
					//TODO explain what you find
						//both ways are equal to 1 when done this way.
						//the reason is because they round up or down.
	}
	public static void doubleSum()
	{
		// TODO Use doubles to sum 1/1 - 1/6 and 1/6 - 1/1.
				System.out.println(1.0/1.0 + 1.0/2.0 + 1.0/3.0 + 1.0/4.0 + 1.0/5.0  + 1.0/6.0);
				System.out.println(1.0/6.0 + 1.0/5.0 + 1.0/4.0 + 1.0/3.0 + 1.0/2.0  + 1.0/1.0);
					// TODO explain what you find
						// we see that the answer is different with doubles
						//because of the fact that double doesn't round.
	}
	public static void chooseNumber()
	{
		// TODO Demonstrate the use of a scanner to read in a double.
		double chosenNumDoub = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter a double.");
		try
		{
			chosenNumDoub = sc.nextDouble();
			System.out.println(chosenNumDoub);
		}
		catch (InputMismatchException e)
		{
			System.out.println("Error: Improper entry detected. Try again.");
			chooseNumber();
		}
	}
	public static void ifElseState()
	{
		// TODO Demonstrate the use of an if statement
		int x = 30;
	      if( x < 20 )
	      {
	         System.out.print("This is if statement");
	      }
	      else
	      {
	         System.out.print("This is else statement");
	      }
	}
}
```
