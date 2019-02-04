package Applet;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.util.Random;

import Player.Player;
import Player.playerShot;

@SuppressWarnings("static-access")
public class screen extends Applet implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	Random r = new Random();
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public static int amount = 50, speed = 5;
	
	public static boolean shoot = false;
	
	static Thread th;
	
	private Player  p  = new Player((int)(screenSize.getWidth() / 2 - 50), (int)(screenSize.getHeight() / 2));
	private playerShot[] pShot;
	
	public static boolean moveLeft;
	public static boolean moveRight;

	Image player = Toolkit.getDefaultToolkit().getImage("Images/spaceShip.png");
	Image shot = Toolkit.getDefaultToolkit().getImage("Images/playerShot.png");
	
	// double buffering
	private Image dbImage;
	private Graphics dbg;

	public void init() {
		//System.out.println(screenSize.getWidth() + " , " + screenSize.getHeight() + "\n\n" + p.getX() + " , " + p.getY() + "\n");
		setSize(screenSize);
		setBackground(new Color(0, 0, 0));
		pShot = new playerShot[100];
	}// end of init

	public void start() {
		th = new Thread(this);
		th.start();
	}// end of start

	@SuppressWarnings("deprecation")
	public void stop() {
		th.stop();
	}// end of stop

	@SuppressWarnings("deprecation")
	public void destroy() {
		th.stop();
	}// end of destroy

	public void run() {
		this.requestFocus();
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
		while (true) 
		{
			if (moveLeft && p.getX() > 0) {
				p.move(-speed);
			}//end of if
			else if(moveRight && p.getX() < screenSize.getWidth() - player.getWidth(this))
			{
				p.move(speed);
			}//end of else
			
			repaint();
			
			try {
				Thread.sleep(10);
			} // end of try
			catch (InterruptedException ex) {
				// do nothing
			} // end of catch
			Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		
		}//end of while
	}// end of run

	public boolean keyDown(Event e, int key) 
	{		
		if (key == Event.LEFT) 
		{
			moveLeft = true;
		}//end of if
		else if (key == Event.RIGHT) 
		{
			moveRight = true;
		} //end of else
		else if (key == 32) // space bar
		{
			System.out.println("space");
			for(int i = 0; i < pShot.length; i++)
			{
				if (pShot[i] == null)
				{
					p.generateShot();
					//System.out.println("shot " + i + pShot[i]);
					
					System.out.println(pShot[i].getX());
					
					break;
				}//end of if
			}//end of for
		} // end of else
		return true;
	}// end of keyDown()
	
	public boolean keyUp(Event e, int key) 
	{		
		if (key == Event.LEFT) 
		{
			moveLeft = false;
		} //end of if
		else if (key == Event.RIGHT) 
		{
			moveRight = false;
		}//end of else
		return true;
	}// end of keyUp()
	
	public void update(Graphics g) 
	{				
		if (dbImage == null) 
		{
			dbImage = createImage(this.getSize().width, this.getSize().height);
			dbg = dbImage.getGraphics();
		} // end of if
		
		dbg.setColor(getBackground());
		dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);

		dbg.setColor(getForeground());

		paint(dbg);
		
		g.drawImage(dbImage, 0, 0, this);
	}// end of update

	public void paint(Graphics g) 
	{	
		g.drawImage(player, p.getX(), p.getY(), this);
		
		for(int i = 0; i < pShot.length; i++)
		{
			if (pShot[i] != null)
			{
				g.drawImage(shot, pShot[i].x,  pShot[i].y,  (ImageObserver) this);
				//g.drawImage(shot, pShot[i].x2, pShot[i].y2, (ImageObserver) this);
			}//end of if
		}//end of for
	}// end of paint
}// end of class
