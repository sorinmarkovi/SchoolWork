import java.util.stream.IntStream;
public class ArraySum 
{
	public static void main(String[] args) 
	{
		// TODO sum 1 - 10
		mainSum();
		loopSum();
	}
	public static void mainSum ()
	{
		int[] a = {1,2,3,4,5,6,7,8,9,10};
		int sum = IntStream.of(a).sum();
		System.out.println("The sum is " + sum);
	}
	public static void loopSum()
	{
		int [] a = new int[11];
		for (int x = 0; x < 11; x++)
		{
			a[x] = x;
		}
		int sum = IntStream.of(a).sum();
		System.out.println("The sum is " + sum);
	}
}