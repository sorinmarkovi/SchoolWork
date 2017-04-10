import java.util.InputMismatchException;
import java.util.Scanner;

public class PopQuiz 
{
	static Scanner input = new Scanner(System.in);
	public static void main(String[] args) 
	{
		startPoint();
	}
	
	public static void startPoint()
	{
		int numberChosen = (int) numberChosen();
		returningDoubleWithADoWhile(numberChosen);
		System.out.println();
		returningDoubleWithAFor(numberChosen);
		//trialReturn(numberChosen);
		replay();
	}
	public static double returningDoubleWithADoWhile(int numberChosen)
	{
		double sum = 0;
		int i = 1;
		do 
		{
			sum += i;
		    System.out.println(sum);
		    i++;
		}
		while (i <= numberChosen);
		return sum;	
	}
	
	public static double trialReturn(int numberChosen)
	{
		double sum = 0;
		for (int i = 1; i <= numberChosen; i++) 
		{
		    sum += i;
		    System.out.println(sum);
		}
		return sum;
	}
	
	public static double returningDoubleWithAFor(int numberChosen)
	{
		double sum = 0;
		for (int i = 1; i <= numberChosen; i++) 
		{
		    sum += i;
		    System.out.println(sum);
		}
		return sum;
	}
	
	public static int numberChosen()
	{
		
		int numberChosen= 0;
		boolean continueInput = true;
		do
		{
			try 
			{		
				System.out.println("Enter the number: ");
				numberChosen = input.nextInt();
				continueInput = false;
			}
				
			catch (InputMismatchException e)
				{
					System.out.println("Try again. (" +  "Incorrect input: an integer is required)");          
					input.nextLine(); 
				}
		}
		while (continueInput == true);
		return numberChosen;
	}
	public static void replay() 
	{
		Scanner scan = new Scanner(System.in);
		int replay = 0;
		boolean continueInput = true;
		boolean falseI = true;
		String answer;
		do 
		{
			do 
			{
				try 
				{
					do 
					{
						System.out.print("DO you to continue Y/N: ");
						answer = scan.nextLine();

						switch (answer) 
						{
						case "Y":
							startPoint();
							falseI = false;
							break;
						case "y":
							startPoint();
							falseI = false;
							break;
						case "N":
							falseI = false;
							System.exit(0);
							replay = 1;
							break;
						case "n":// exit from program;
							System.exit(0);
							falseI = false;
							replay = 1;
							break;
						default:
							System.out.println("invalid choice");
							falseI = true;
							break;
						}
					} 
					while (falseI);
				} 
				catch (InputMismatchException e) 
				{
					do 
					{
						System.out.println("Try again. (" + "Incorrect input: an integer is required)");
						replay = scan.nextInt();
						falseI = true;
					} while (falseI);
				}
				continueInput = false;
			} 
			while (continueInput == true);
		} 
		while (replay == 0);
		scan.close();
	}
}