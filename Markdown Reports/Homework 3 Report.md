## Problem 1 Your Code:

```java
public class stockMain
{
	public static void main(String[] args)
	{
        Stock stock1 = new Stock("ORCL", "Oracle Corporation");
        stock1.setCurrentPrice(34.5);
        stock1.setCurrentPrice(34.35);
        System.out.println("Stock name: " + stock1.getName() + " Symbol: " + stock1.getSymbol());
        System.out.println("previous price: " + stock1.getPreviousClosingPrice());
        System.out.println("current price: " + stock1.getCurrentPrice());
        System.out.println("Percent changed: " + stock1.getChangePercent());
    }
}

public class Stock
{
    private String symbol;
    private String name;
    private double previousClosingPrice;
    private double currentPrice;
    public Stock(String symbol, String name)
    {
        this.symbol = symbol;
        this.name = name;
    }
    public String getSymbol()
    {
        return symbol;
    }
    public void setSymbol(String symbol)
    {
        this.symbol = symbol;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public double getCurrentPrice()
    {
        return currentPrice;
    }
    public void setCurrentPrice(double currentPrice)
    {
        this.previousClosingPrice = this.currentPrice;
        this.currentPrice = currentPrice;
    }

    public double getPreviousClosingPrice()
    {
        return previousClosingPrice;
    }
    public void setPreviousClosingPrice(double previousClosingPrice)
    {
        this.previousClosingPrice = previousClosingPrice;
    }
    public double getChangePercent()
    {
        return (currentPrice - previousClosingPrice) / previousClosingPrice;
    }
}
```

## Problem 1 Partner Code:

```java
~no partner~
```

## Problem 1 Output:

```java
Stock name: Oracle Corporation Symbol: ORCL
previous price: 34.5
current price: 34.35
Percent changed: -0.00434782608695648

```

## Problem 2 Your Code:

```java
public class fanMain
{
	public static void main(String[] args)
	{
        Fan fan1 = new Fan();
        fan1.setSpeed(Fan.FAST);
        fan1.setOn(true);
        fan1.setRadius(10);
        fan1.setColor("yellow");
        Fan fan2 = new Fan();
        fan2.setSpeed(Fan.MEDIUM);
        fan2.setRadius(5);
        fan2.setColor("blue");
        fan2.setOn(false);
        System.out.println("Fan #1: "+fan1.toString());
        System.out.println("Fan #2: "+fan2.toString());
    }
}

public class Fan
{
	public static final int SLOW = 1;
	public static final int MEDIUM = 2;
	public static final int FAST = 3;
	private int mSpeed;
	private boolean mOn;
	private double mRadius;
	private String mColor;
	public Fan()
	{
		mSpeed = 1;
		mRadius = 5;
		mColor = "white";
	}
	public int getSpeed()
	{
		return mSpeed;
	}
	public void setSpeed(int speed)
	{
		mSpeed = speed;
	}
	public boolean isOn()
	{
		return mOn;
	}
	public void setOn(boolean on)
	{
		mOn = on;
	}
	public double getRadius()
	{
		return mRadius;
	}
	public void setRadius(double radius)
	{
		mRadius = radius;
	}
	public String getColor()
	{
		return mColor;
	}
	public void setColor(String color)
	{
		mColor = color;
	}
	public String toString()
	{
		if (isOn())
		{
			return "Speed: " + getSpeed() + " Color: " + getColor() + " Radius: " + getRadius();
		}
		else
		{
			return "Color: " + getColor() + " Radius: " + getRadius() + ". The fan is OFF.";
		}
	}
}
```

## Problem 2 Partner Code:

```java
~no partner~
```

## Problem 2 Output:

```java
Fan #1: Speed: 3 Color: yellow Radius: 10.0
Fan #2: Color: blue Radius: 5.0. The fan is OFF.
```
