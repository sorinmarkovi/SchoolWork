public class Animal 
	{
	protected static int xer = 213;
	public static void main(String[] args) 
	{
		Animal.sleep();
	}
	public Animal() 
	{
		System.out.println("A new animal has been created!");
	}

	public static void sleep() 
	{
		System.out.println("An animal sleeps...");

	}

	public static void eat() 
	{
		System.out.println("An animal eats...");

	}

}
