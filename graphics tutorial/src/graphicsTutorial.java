import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;

/*
 * Author: Carson Brown
 * Date: December 21
 * Version: 1
 */ 
@SuppressWarnings("serial")
public class graphicsTutorial extends JComponent
{
	static Dimension screenSize;
	JFrame frame;
	
	static Player[] p;
	
	static int dice1, dice2, d1x, d1y, d2x, d2y, dSize = 200;
	
	static Random r = new Random();
	
	 public static void main(String[] a) 
	 {
		 	screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		    JFrame window = new JFrame();
		    window.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    window.setBounds(0, 0, screenSize.width, screenSize.height);
		    window.getContentPane().add(new graphicsTutorial());
		    window.setVisible(true);
		    p = new Player[2];
		    
		    p[0] = new Player(0, 50, 100, 1500);
		    
		    run();
		  }//end of main
	 
	 public static void run()
	 {
		dice1 = r.nextInt(6) + 1;
		dice2 = r.nextInt(6) + 1;
		System.out.println(dice1 + " , " + dice2);
		
		for(int i = 0; i < p.length; i++)
		{
		
			p[i].move(dice1 + dice2);
		}
		}//end of run
	 
	public void paint(Graphics g)
	{
		
		d1x = screenSize.width / 2 - dSize - 50;
		d1y = screenSize.height / 2 - dSize / 2;
		d2x = screenSize.width / 2 + dSize - 50;
		d2y = screenSize.height / 2 - dSize / 2;
		
		g.setColor(Color.BLACK);
		g.fillRect(d1x, d1y, dSize, dSize);
		g.fillRect(d2x, d2y, dSize, dSize);
		
		g.setColor(Color.RED);
		int R = (int) Math.floor(dSize / 13);
		switch(dice1)
		{
		case 1:
			g.fillOval(d1x + (dSize / 2 - R), d1y + (dSize / 2 - R), R * 2, R * 2); //centre dot
			break;
			
		case 2:
			g.fillOval(d1x + R * 2, d1y + R * 2, R * 2, R * 2);//top left dot
			g.fillOval(d1x + (dSize - R * 4), d1y + (dSize - R * 4), R * 2, R * 2);//bottom right dot
			break;
			
		case 3:
			g.fillOval(d1x + R * 2, d1y + R * 2, R * 2, R * 2);//top left dot
			g.fillOval(d1x + (dSize / 2 - R), d1y + (dSize / 2 - R), R * 2, R * 2);//centre dot
			g.fillOval(d1x + (dSize - R * 4), d1y + (dSize - R * 4), R * 2, R * 2);//bottom right dot
			break;
			
		case 4:
			g.fillOval(d1x + R * 2, d1y + R * 2, R * 2, R * 2);//top left dot
			g.fillOval(d1x + (dSize - R * 4), d1y + R * 2, R * 2, R * 2);//top right dot
			g.fillOval(d1x + R * 2, d1y + (dSize - R * 4), R * 2, R * 2);//bottom left dot
			g.fillOval(d1x + (dSize - R * 4), d1y + (dSize - R * 4), R * 2, R * 2);//bottom right dot
			break;
			
		case 5:
			g.fillOval(d1x + R * 2, d1y + R * 2, R * 2, R * 2);//top left dot
			g.fillOval(d1x + (dSize - R * 4), d1y + R * 2, R * 2, R * 2);//top right dot
			g.fillOval(d1x + (dSize / 2 - R), d1y + (dSize / 2 - R), R * 2, R * 2); //centre dot
			g.fillOval(d1x + R * 2, d1y + (dSize - R * 4), R * 2, R * 2);//bottom left dot
			g.fillOval(d1x + (dSize - R * 4), d1y + (dSize - R * 4), R * 2, R * 2);//bottom right dot
			break;
			
		case 6:
			g.fillOval(d1x + R * 2, d1y + R * 2, R * 2, R * 2);//top left dot
			g.fillOval(d1x + (dSize - R * 4), d1y + R * 2, R * 2, R * 2);//top right dot
			g.fillOval(d1x + R * 2, d1y + (dSize / 2 - R), R * 2, R * 2); //centre left dot
			g.fillOval(d1x + (dSize - R * 4), d1y + (dSize / 2 - R), R * 2, R * 2); //centre right dot
			g.fillOval(d1x + R * 2, d1y + (dSize - R * 4), R * 2, R * 2);//bottom left dot
			g.fillOval(d1x + (dSize - R * 4), d1y + (dSize - R * 4), R * 2, R * 2);//bottom right dot
			break;
		}//end of switch
		
		switch(dice2)
		{
		case 1:
			g.fillOval(d2x + (dSize / 2 - R), d2y + (dSize / 2 - R), R * 2, R * 2); //centre dot
			break;
			
		case 2:
			g.fillOval(d2x + R * 2, d2y + R * 2, R * 2, R * 2);//top left dot
			g.fillOval(d2x + (dSize - R * 4), d2y + (dSize - R * 4), R * 2, R * 2);//bottom right dot
			break;
			
		case 3:
			g.fillOval(d2x + R * 2, d2y + R * 2, R * 2, R * 2);//top left dot
			g.fillOval(d2x + (dSize / 2 - R), d2y + (dSize / 2 - R), R * 2, R * 2);//centre dot
			g.fillOval(d2x + (dSize - R * 4), d2y + (dSize - R * 4), R * 2, R * 2);//bottom right dot
			break;
			
		case 4:
			g.fillOval(d2x + R * 2, d2y + R * 2, R * 2, R * 2);//top left dot
			g.fillOval(d2x + (dSize - R * 4), d2y + R * 2, R * 2, R * 2);//top right dot
			g.fillOval(d2x + R * 2, d2y + (dSize - R * 4), R * 2, R * 2);//bottom left dot
			g.fillOval(d2x + (dSize - R * 4), d2y + (dSize - R * 4), R * 2, R * 2);//bottom right dot
			break;
			
		case 5:
			g.fillOval(d2x + R * 2, d2y + R * 2, R * 2, R * 2);//top left dot
			g.fillOval(d2x + (dSize - R * 4), d2y + R * 2, R * 2, R * 2);//top right dot
			g.fillOval(d2x + (dSize / 2 - R), d2y + (dSize / 2 - R), R * 2, R * 2); //centre dot
			g.fillOval(d2x + R * 2, d2y + (dSize - R * 4), R * 2, R * 2);//bottom left dot
			g.fillOval(d2x + (dSize - R * 4), d2y + (dSize - R * 4), R * 2, R * 2);//bottom right dot
			break;
			
		case 6:
			g.fillOval(d2x + R * 2, d2y + R * 2, R * 2, R * 2);//top left dot
			g.fillOval(d2x + (dSize - R * 4), d2y + R * 2, R * 2, R * 2);//top right dot
			g.fillOval(d2x + R * 2, d2y + (dSize / 2 - R), R * 2, R * 2); //centre left dot
			g.fillOval(d2x + (dSize - R * 4), d2y + (dSize / 2 - R), R * 2, R * 2); //centre right dot
			g.fillOval(d2x + R * 2, d2y + (dSize - R * 4), R * 2, R * 2);//bottom left dot
			g.fillOval(d2x + (dSize - R * 4), d2y + (dSize - R * 4), R * 2, R * 2);//bottom right dot
			break;
		}//end of switch
	}//end of paint
}//end of class
