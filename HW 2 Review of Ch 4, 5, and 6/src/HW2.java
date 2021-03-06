import java.util.Scanner;
public class HW2 
{
	public static void main(String[] args) 
	{
		//geometry();
		//hexBinary();
		//combination();
		craps();
	}
	public static void geometry()
	{
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		//area of a hexagon, Area = (6 * s^2)/ (4 * tan(pi/6))\
		// Write a program that prompts the user to enter the side of a hexagon and displays
		//TODO Try/Catch
		System.out.print("Enter the side: ");
		double side = input.nextDouble();
		double area = (6 * Math.pow(side, 2))/ (4 * Math.tan((Math.PI/6)));
		System.out.println("The area of the hexagon is " + area); 
	}
	public static void hexBinary()
	{
		//TODO prompt user to enter hex, display binary number
		//Just copied fromhttp://soultionmanual.blogspot.com/2016/07/chapter-4-exercise-12-introduction-to.html
		Scanner input = new Scanner(System.in);
		  System.out.print("Enter a hex digit: ");
		  String hexString = input.nextLine();
		 
		  // Check if the hex string has exactly one character
		  if (hexString.length() != 1) {
		   System.out.println("You must enter exactly one character");
		   System.exit(1);
		  }
		 
		  // Display decimal value for the hex digit
		  char ch = Character.toUpperCase(hexString.charAt(0));
		  int value = 0;
		  if (ch <= 'F' && ch >= 'A') {
		   value = ch - 'A' + 10;
		  } else if (Character.isDigit(ch)) {
		   value = ch - '0';
		  } else {
		   System.out.println(ch + " is an invalid input");
		   System.exit(0);
		  }
		 
		  System.out.println("The decimal value for hex digit " + ch + " is "
		    + Integer.toBinaryString(value));
	}
	public static void combination()
	{
		//Write a program that displays all possible combinations for picking two numbers from integers 1 to 7.
		int counter = 0;
        for (int i = 1; i < 8; i++)
        {
            for (int j = i + 1; j < 8; j++) 
            {
                System.out.println(i + " " + j);
                counter++;
            }
        }
        System.out.println("Total combination is: " + counter);
	}
	public static void craps(String[] args)
	{
		//http://soultionmanual.blogspot.com/2016/08/chapter-6-exercise-32-introduction-to.html
		int win = 0, lose = 0;
		 
		  for (int i = 0; i < 10000; i++) {
		   if (craps() == 1) {
		    win++;
		   } else {
		    lose++;
		   }
		  }
		 
		  System.out.println("Number of winning game is " + win);
		  System.out.println("Number of losing game is " + lose);
		  System.out.println("The chance of winning is " + (win / 10000.0));
		 }
		 
		 // Generate random number in integer
		 public static int intRandom(int lowerBound, int upperBound) {
		  return (int) (lowerBound + Math.random()
		    * (upperBound - lowerBound + 1));
		 }
		 
		 public static int roll() {
		  int dice1 = intRandom(1, 6);
		  int dice2 = intRandom(1, 6);
		  int sum = dice1 + dice2;
		 
		  System.out.println("You roll " + dice1 + " + " + dice2 + " = " + sum);
		 
		  return sum;
		 
		 }
		 
		 public static int craps() {
		 
		  int first = 0;
		  int status = 0; // 0 = neither lose or win, 1 = win , -1 = lose
		 
		  // First roll
		 
		  first = roll();
		  // check craps
		  switch (first) {
		  case 2:
		  case 3:
		  case 12:
		   System.out.println("You lose");
		   status = -1;
		   break;
		  case 7:
		  case 11:
		   System.out.println("You win");
		   status = 1;
		   break;
		  default:
		   System.out.println("The point is " + first);
		   // next roll
		   int next;
		   do {
		    next = roll();
		 
		   } while (!(next == first || next == 7));
		 
		   if (next == 7) {
		    System.out.println("You lose");
		    status = -1;
		   } else {
		    System.out.println("You win");
		    status = 1;
		   }
		 
		  }
		 
		  return status;
		 
		 }
}