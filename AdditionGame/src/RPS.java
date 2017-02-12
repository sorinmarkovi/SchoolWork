import java.util.Scanner;

public class RPS {

	public static void main(String[] args) {
		
		//generates random Rock Paper or Scissors for comp.
		//takes user input on the two
		//compares
		
		
		Scanner userInput = new Scanner(System.in);
		 
		  System.out.print("scissor (0), rock (1), paper (2):");
		  int guess = userInput.nextInt();
		  userInput.close();
		  int computer = (int) (Math.random() * 3);
		  String rpsComputer = "";
		 
		  switch (computer) {
		  case 0:
			  rpsComputer = "scissor";
			  break;
		  case 1:
			  rpsComputer = "rock";
			  break;
		  case 2:
			  rpsComputer = "paper";
			  break;
		  }
		 
		  String rpsGuess = "";
		  switch (guess) {
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
		 
		  System.out.print("The computer is " + rpsComputer + ". You are "
		    + rpsGuess);
		 
		  if (computer == guess) {
		   System.out.print(" too. It is a draw");
		  } else if (computer - guess == 1 || computer - guess == -2) {
		   System.out.print(". Computer won.");
		  } else if (computer - guess == -1 || computer - guess == 2) {
		   System.out.print(". You won.");
		  }
		 
		 }
		 
		}