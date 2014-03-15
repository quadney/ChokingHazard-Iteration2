package Models;

public class Point {
	
	int xVal;
	int yVal;

	public Point()
	{
		// blank constructor
	}
	
	public Point( int x, int y)
	{
		this.xVal = x;
		this.yVal = y;
	}
	
	public int getX()
	{
		return xVal;
	}
	
	public int getY()
	{
		return yVal;
	}
}
