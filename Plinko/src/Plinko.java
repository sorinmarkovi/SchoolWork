import java.util.InputMismatchException;
import java.util.Scanner;
public class Plinko 
{
	static Scanner input = new Scanner(System.in);
	public static void main(String[] args) 
	{
		//Make a plinko game
		startPoint();
	}
	public static void startPoint() 
	{
		int numberOfBalls = numberOfBalls();
		int numberOfSlots = numberOfSlots();
		gameMaker(numberOfBalls, numberOfSlots);
		input.close();
	}
	public static void gameMaker(int numberOfBalls, int numberOfSlots) 
	{
		int[] slots = new int[numberOfSlots + 1];
		rotationFinder(numberOfBalls, numberOfSlots, slots);
		ballVisualizer(numberOfBalls, numberOfSlots, slots);
		replayGame();
	}
	public static void rotationFinder(int numberOfBalls, int numberOfSlots, int[] slots) 
	{
		String rotation;
		for (int i = 0; i < numberOfBalls; i++) 
		{
			int sum = 0;
			for (int j = 0; j < numberOfSlots; j++) 
			{
				int randomNumber = (int) (Math.random() * 2);
				sum += randomNumber;
				if (randomNumber == 0)
					rotation = "L";
				else
					rotation = "R";
				System.out.print(rotation);
			}
			slots[sum]++;
			System.out.println();
		}
	}
	public static void ballVisualizer(int numberOfBalls, int numberOfSlots, int[] slots) 
	{
		String[] ball = new String[numberOfSlots + 1];
		for (int i = numberOfBalls; i > 0; i--) 
		{
			for (int j = 0; j <= numberOfSlots; j++) 
			{
				if (i == slots[j]) 
				{
					ball[j] = "0";
					slots[j]--;
				} 
				else
					ball[j] = " ";
				System.out.print(ball[j]);
			}
			System.out.println();
		}
		binVisualizer(numberOfSlots);
	}
	public static void binVisualizer(int numberOfSlots)
	{
		for (int i = 1; i <= numberOfSlots ; i++) 
		{
			System.out.print(i);	
		}
		System.out.println("");
	}
	public static void replayGame() 
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
							replay = 1;
							break;
						case "n":// exit from program;
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
	public static int numberOfBalls()
	{
		
		int numberOfBalls = 0;
		boolean continueInput = true;
		do
		{
			try 
			{		
				System.out.println("Enter the number of balls to drop: ");
				numberOfBalls = input.nextInt();
				continueInput = false;
			}
				
			catch (InputMismatchException e)
				{
					System.out.println("Try again. (" +  "Incorrect input: an integer is required)");          
					input.nextLine(); 
				}
		}
		while (continueInput == true);
		return numberOfBalls;
	}
	public static int numberOfSlots()
	{
		int numberOfSlots = 0;
		boolean continueInput = true;
		do
		{
			try 
			{		
				System.out.println("Enter the number of slots in the bean machine: ");
				numberOfSlots = input.nextInt();
				continueInput = false;
			}
				
			catch (InputMismatchException e)
				{
					System.out.println("Try again. (" +  "Incorrect input: an integer is required)");          
					input.nextLine(); 
				}
		}
		while (continueInput == true);
		return numberOfSlots;
	}
}