public class Player 
{
	int panel, width, height, balance;
	
	Player(int panel, int width, int height, int balance)
	{
		this.panel = panel;
		this.width = width;
		this.height = height;
		this.balance = balance;
	}//end of constructor
	
	public void move(int s)
	{
		panel += s;
	}//end of move
}//end of class
