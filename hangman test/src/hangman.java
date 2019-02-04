import java.util.Scanner;

public class hangman 
{
	static String[] wordsList = {"hangman", "physics", "iridocyclitis", "coffee", "antidisestablishmentarianism", "oneiromancy", "tostitos"};;
	static int y;
	static String random;
	static int count;
	static int life = 6;
	static String guess;
	
	static Scanner sc = new Scanner(System.in);
	
	public static void word()
    {
       y = (int) (Math.random() * wordsList.length);
       random = wordsList[y];
       count = random.length();
    }//end of word()

	public static void printWord()
	{
		System.out.println("");
		for(int i = 0; i < count; i++)
		{
			if (i == random.indexOf(guess))
			{
				System.out.println(" " + guess);
			}
			else
				{
				System.out.print(" _");
				}
    	}//end of for
	}
	
    public static void main (String[] args)
    {
    		word();
	    
	    	for(int i = 0; i < count; i++)
	    	{
	    		System.out.print(" _");
	    	}//end of for
	    	
	    	guess = sc.next();
	    	
	    	
    }//end of main
}//end of class
