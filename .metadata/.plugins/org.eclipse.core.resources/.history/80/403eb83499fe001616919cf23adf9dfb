/* 1. Gather requirements
 * 2. Describe the application
 * 3. Identify the main objects
 * 4. Describe interactions
 * 5. Create a class diagram
 */
import java.util.InputMismatchException;
import java.util.Scanner;
public class RPS {
private static Scanner userInput;
public static void main(String[] args) {
		startGame();
}
private static Object ComputerRPS(int Number) 
{
	int computer = Number;
	String rpsComputer = "";	
	switch (computer) 
	{
	case 0:
		  rpsComputer = "scissor";
		  break;
	case 1:
		  rpsComputer = "rock";
		  break;
	case 2:
		  rpsComputer = "paper";
		  break;
	default:
		   System.out.print("Critical error");
		   System.exit(0);
	}
	return rpsComputer;
}	
private static Object UserGuess(int guess)
{
	String rpsGuess = "";
	  switch (guess) 
	  {
	  case 0:
		  rpsGuess = "scissor";
		  break;
	  case 1:
		  rpsGuess = "rock";
		  break;
	  case 2:
		  rpsGuess = "paper";
		  break;
	  default:
	   System.out.print("Invalid input.");
	   System.exit(0);
	  }
	return rpsGuess;
}
private static void gameMaker()
{
	//declare variables
	boolean continueInput = true;
	boolean falseInput = true;
	int computer = (int) Math.floor(Math.random() * 3); 
	userInput = new Scanner(System.in);
	Object rpsComputer = ComputerRPS(computer);
	int guess;	
do{
	try {		
		  System.out.print("scissor (1), rock (2), paper (3):");
		  guess = userInput.nextInt();
		  guess--;
		  do
		  { 
		  if(guess >2 || guess <0)
		  {
			  System.out.println("Wrong input. Please enter a correct number:");
			  guess = userInput.nextInt();
			  guess--;
		  }
		  else 
		  {
			  falseInput = false;
		  }
		  }
		  while (falseInput);
		  Object rpsGuess = UserGuess(guess);
		  System.out.print("The computer is " + rpsComputer + ". You are " + rpsGuess);		  
		  //check the result of the game
		  
		  if (computer == guess) 
		  {
		   System.out.print(" too. It is a draw. ");
		  } 
		  else if (computer - guess == 1 || computer - guess == -2) 
		  {
		   System.out.print(". Computer won. ");
		  } 
		  else if (computer - guess == -1 || computer - guess == 2) 
		  {
		   System.out.print(". You won. ");
		  }		  
		  
		  continueInput = false;
		}
	catch (Exception e)
		{
			System.out.println("Try again. (" +  "Incorrect input: an integer is required)");          
			userInput.nextLine(); 
		}
}
while (continueInput == true);
}
private static void startGame()
{
	Scanner scan = new Scanner(System.in);
	int replay = 0;
	boolean continueInput = true;
	boolean falseI = true;
	String answer;
	do
	{
	//call the game.
		gameMaker();
		do
			{
			try {		
				do
				{
				System.out.print("DO you to continue Y/N: ");
			    answer = scan.nextLine();


			    switch (answer)
			    {
			    case "Y":
			    	falseI = false;
			    		break;
			    case "y": //WRITE YOUR CODE HERE
			    	falseI = false;
			            break;
			    case "N":
			    	falseI = false;
			    	replay = 1;
			    		break;
			    case "n"://exit from program;
			    	falseI = false;
			    	replay = 1;
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