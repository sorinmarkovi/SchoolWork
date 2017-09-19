public class Dog extends Animal 
{
	public static void main(String[] args) 
	{
		Animal.sleep();
		Dog.sleep();
		Animal.eat();
		Dog.eat();
		System.out.println(xer);
	}
	public Dog()
	{
		super();
		System.out.println("A new dog has been created!");
	}
	public static void sleep() 
	{
		System.out.println("A dog sleeps...");
	}
	public static void eat() 
	{
		System.out.println("A dog eats...");

	}
}
