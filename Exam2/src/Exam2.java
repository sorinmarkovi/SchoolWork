public class Exam2 
{
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		double x = 2.2;
		//System.out.print(customMethod(x));
		customMethod(x);
	}
	public static void customMethod(double x)
	{
		int x1 = 0;
		if((x % .2) == 0)
		{
			System.out.println("he");
			x1 = (int) Math.floor(x);
		}
		else 
		{
			x1 = (int) Math.ceil(x);
		}
		System.out.println(x1);
	}
}