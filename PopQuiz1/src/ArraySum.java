import java.util.stream.IntStream;
public class ArraySum 
{
	static int [] a = new int[11];
	public static void main(String[] args) 
	{
		// TODO sum 1 - 10
		fillArray();
	}
	public static void fillArray()
	{
		for (int x = 0; x < 11; x++)
		{
			a[x] = x;
		}
		sumArray(a);
	}
	public static void sumArray(int a[])
	{
		int sum = IntStream.of(a).sum();
		printSum(sum);
	}
	public static void printSum(int sum)
	{
		System.out.println("The sum is " + sum);
		
	}
	
	
}