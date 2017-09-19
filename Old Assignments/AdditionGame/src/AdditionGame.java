import java.util.Scanner;
public class AdditionGame 
{
	public static void main(String[] args) 
	{
		//Generate two random numbers
		//have the user try and add them
		//if the user enters a letter, throw exceptions
		//tell the user that they got it when they figure it out. 
		int randNum1= (int) Math.floor(Math.random() * 100); 
		int randNum2= (int) Math.floor(Math.random() * 100);
		
		Scanner info = new Scanner(System.in);
		System.out.println(randNum1 +" + "+ randNum2);
		
		int sumNum = randNum1 + randNum2;
		
		int userSum;
		
		System.out.println("What is the sum?");
		try
		{
			userSum = info.nextInt();
			
			info.close();
			
			if (userSum == sumNum)
				{
					System.out.println("You got it!");
				}
			else
				{
					
				while (userSum != sumNum)
					{
						System.out.println("Try again.");
						userSum = info.nextInt();
					}
					System.out.println("You got it!");
				}	
		}
		catch(Exception e)
		{
			System.out.println("Please enter integers only");
		}
	}
}