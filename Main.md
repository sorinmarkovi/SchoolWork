This is going to be going over 6.8, where some people were having issues with exactly figuring out what to do.

```
public static void main(String[] args)
{
		  System.out.printf("%-15s%-15s|    %-15s%-15s\n","Centigrade","Fahrenheit","Fahrenheit","Centigrade");
		  //Left-justifying within the specified width. You know this by the %-15s. This is the meat of what a lot of people struggled with.
		  System.out.println( String.format("%62s"," ").replace(' ', '-') );
		  //This just gives a cute little line under the table.

		  for (int c = 40, f = 120  ; c >=31; c--, f-=10) {
		    /*this is telling us how many times to run this loop.
		     * The biggest part about this is understanding why we are choosing the numbers for the variables.
		     * The 31 is because that's as low as the Centigrade goes down (on that side).
		     * Then the f-=10 is because it is going down by 10 from 120 each run. It's basically saying f = f-10.
		     */
		   System.out.printf("%-15.1f%-15.1f|    %-15.1f%-15.2f\n",(float)c,centigradeToFahrenheit(c),(float)f, fahrenheitToCentigrade(f));
		   //This is where the magic happens, really. We can still see that there are the same specifications on the width and everything of the table.
		  }
		 }
		//Here is the small method that converts Centigrade to Fahrenheit
		 public static double centigradeToFahrenheit(double centigrade)
    {
		  return (9.0 / 5) * centigrade + 32;
		 }
		 /** Convert from Fahrenheit to centigrade */
		 public static double fahrenheitToCentigrade(double fahrenheit)
     {
		  return (5.0 / 9) * (fahrenheit - 32);
		 }
		}
```

6.21 was relatively straightforward using just a very long switch statement to be able to go through all of the different possibilities.
```
public static void main(String[] args)
{
  Scanner input = new Scanner(System.in);
  System.out.print("Enter a string: ");
  String s = input.nextLine();
  String s2 = "";

  for (int i = 0; i < s.length(); i++)
  {

   int number = getNumber(s.toUpperCase().charAt(i));
   if (number !=0)
   {
    s2 = s2 +number;
   }
   else
   {
    s2 = s2+s.charAt(i);
   }
  }
  System.out.println(s2);
 }

 static int getNumber(char uppercaseLetter)
 {
  int n;

  switch (uppercaseLetter)
  {
  case 'A':
  case 'B':
  case 'C':
   n = 2;
   break;

  case 'D':
  case 'E':
  case 'F':
   n = 3;
   break;

  case 'G':
  case 'H':
  case 'I':
   n = 4;
   break;

  case 'J':
  case 'K':
  case 'L':
   n = 5;
   break;

  case 'M':
  case 'N':
  case 'O':
   n = 6;
   break;

  case 'P':
  case 'Q':
  case 'R':
  case 'S':
   n = 7;
   break;

  case 'T':
  case 'U':
  case 'V':
   n = 8;
   break;

  case 'W':
  case 'X':
  case 'Y':
  case 'Z':
   n = 9;
   break;

  default:
   n = 0;
   break;
  }
  return n;
 }
}
```

Finally, we get to 6.25. This one is mainly dealing with a bit of math.

```
public static void main(String[] args)
{
  Scanner input = new Scanner(System.in);
  while (true)
  {
   System.out.print("Enter the number of milliseconds (enter 0 to stop):");
   long number = input.nextLong();
   if (number == 0)
    break;
   System.out.println("Converted time is " + convertMillis(number));
  }
  System.out.println("Program stops!!");
 }

 public static String convertMillis(long millis)
 {
  long totalMilliseconds = millis;
  // Obtain the total seconds since midnight, Jan 1, 1970
  long totalSeconds = totalMilliseconds / 1000;
  // Compute the current second in the minute in the hour
  long currentSecond = totalSeconds % 60;
  // Obtain the total minutes
  long totalMinutes = totalSeconds / 60;
  // Compute the current minute in the hour
  long currentMinute = totalMinutes % 60;
  // Obtain the total hours
  long totalHours = totalMinutes / 60;
  return totalHours + ":" + currentMinute + ":" + currentSecond;
 }
}
```
