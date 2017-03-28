import java.util.InputMismatchException;
import java.util.Scanner;
public class Plinko 
{
	public static void main(String[] args) {
		
		// TODO Make a bean machine game
		startPoint();
	}
	public static void startPoint() 
	{
		Scanner input = new Scanner(System.in);
		int numberOfBalls;
		int numberOfSlots;
<<<<<<< HEAD
		try {
			System.out.println("Enter the number of balls to drop: ");
=======
		try 
		{
			System.out.print("Enter the number of balls to drop: ");
>>>>>>> 9a299aceeb2010c2f9de4a05dea67c697b08d06e
			numberOfBalls = input.nextInt();
		} 
		catch (Exception e) 
		{
			System.out.println("Try again. (" + "Incorrect input: an integer is required)");
			System.out.println("Enter the number of balls to drop: ");
			numberOfBalls = input.nextInt();
		}
		try 
		{
<<<<<<< HEAD
			System.out.println("Enter the number of slots in the bean machine: ");
			numberOfSlots = input.nextInt();
		} 
		catch (Exception e) {
=======
			System.out.print("Enter the number of slots in the bean machine: ");
			numberOfSlots = input.nextInt();
		} 
		catch (Exception e) 
		{
>>>>>>> 9a299aceeb2010c2f9de4a05dea67c697b08d06e
			System.out.println("Try again. (" + "Incorrect input: an integer is required)");
			System.out.println("Enter the number of slots in the bean machine: ");
			numberOfSlots = input.nextInt();
		}
		gameMaker(numberOfBalls, numberOfSlots);
		replayGame();
		input.close();
	}
<<<<<<< HEAD
	public static void gameMaker(int numberOfBalls, int numberOfSlots) {
=======
	public static void gameMaker(int numberOfBalls, int numberOfSlots) 
	{
>>>>>>> 9a299aceeb2010c2f9de4a05dea67c697b08d06e
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
					ball[j] = "O";
					slots[j]--;
				} else
					ball[j] = " ";
				System.out.print(ball[j]);
			}
			System.out.println();
		}
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
							System.exit(0);
							falseI = false;
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