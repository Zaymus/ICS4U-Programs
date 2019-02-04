/* 
 * Description: a program that generates a "snowflake" by using parameters given by the user
 * Author: Carson Brown
 * Date: October 24 2018
 * Version: V2
 */

package Snowflake;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileSystemView;

public class SnowflakeTest extends JComponent {
	public SnowflakeTest() {
	}

	private static final long serialVersionUID = 1L;
	static int X, Y, sides = 6, R = 255, G = 255, B = 255, endX, endY, size = 200, r = 5, d = 0, p2X, p2W;

	static String Dpath = "C:\\Users\\Owner\\Desktop\\mySnowflake.png";

	static JButton save = new JButton("Save");

	static JSlider snowflakeSize = new JSlider(JSlider.HORIZONTAL, 10, 400, 200);
	static JSlider snowflakeSides = new JSlider(JSlider.HORIZONTAL, 3, 10, 6);
	static JSlider Reduction = new JSlider(JSlider.HORIZONTAL, 2, 6, 3);
	static JSlider snowflakecolorRed = new JSlider(JSlider.HORIZONTAL, 0, 255, 255);
	static JSlider snowflakecolorGreen = new JSlider(JSlider.HORIZONTAL, 0, 255, 255);
	static JSlider snowflakecolorBlue = new JSlider(JSlider.HORIZONTAL, 0, 255, 255);
	static JSlider snowflakecolorRotate = new JSlider(JSlider.HORIZONTAL, 0, 720, 360);
	static JFrame window;
	static JPanel panel1 = new JPanel();
	static JPanel panel2;

	static JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

	static Font font = new Font("Serif", Font.BOLD, 48);

	static BufferedImage image;

	public static void main(String[] a) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		window = new JFrame("Brown,Carson Snowflake Creator");
		window.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(0, 0, screenSize.width, screenSize.height);
		window.getContentPane().add(new SnowflakeTest());
		window.setVisible(true);
		window.getContentPane().add(panel1);

		panel2 = new JPanel();
		window.getContentPane().add(panel2);

		panel1.setBounds(0, 0, (window.getWidth() / 3), window.getHeight());
		panel1.setBackground(new Color(0));
		panel1.setLayout(null);
		System.out.println("Panel 1: " + panel1.getX() + " , " + panel1.getHeight());

		panel2.setBounds(panel1.getWidth(), 0, (window.getWidth() - (panel1.getWidth())), window.getHeight());
		panel2.setBackground(new Color(0));
		panel2.setLayout(null);
		p2X = panel2.getX();
		p2W = panel2.getWidth();
		System.out.println("Panel 2: " + p2X + " , " + p2W);

		X = (panel1.getWidth() * 2);
		Y = (panel2.getHeight() / 2);

		panel1.add(snowflakeSize);
		panel1.add(snowflakeSides);
		panel1.add(Reduction);
		panel1.add(snowflakecolorRed);
		panel1.add(snowflakecolorGreen);
		panel1.add(snowflakecolorBlue);
		panel1.add(snowflakecolorRotate);
		panel1.add(save);

		snowflakeSize.setBounds((panel1.getWidth() / 3), ((panel1.getHeight() / 7) * 1) - 200, 200, 30);
		snowflakeSides.setBounds((panel1.getWidth() / 3), ((panel1.getHeight() / 7) * 2) - 200, 200, 30);
		Reduction.setBounds((panel1.getWidth() / 3), ((panel1.getHeight() / 7) * 3) - 200, 200, 30);
		snowflakecolorRed.setBounds((panel1.getWidth() / 3), ((panel1.getHeight() / 7) * 4) - 200, 200, 30);
		snowflakecolorGreen.setBounds((panel1.getWidth() / 3), ((panel1.getHeight() / 7) * 5) - 200, 200, 30);
		snowflakecolorBlue.setBounds((panel1.getWidth() / 3), ((panel1.getHeight() / 7) * 6) - 200, 200, 30);
		snowflakecolorRotate.setBounds((panel1.getWidth() / 3), ((panel1.getHeight() / 7) * 7) - 200, 200, 30);

		save.setFont(font);
		save.setBounds(50, ((panel1.getHeight() / 6) * 6) - 200, 200, 100);

		snowflakeSize.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				size = snowflakeSize.getValue();
				window.repaint();
			}
		});

		snowflakeSides.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				sides = snowflakeSides.getValue();
				window.repaint();
			}
		});

		Reduction.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				r = Reduction.getValue();
				window.repaint();
			}
		});

		snowflakecolorRed.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				R = snowflakecolorRed.getValue();
				window.repaint();
			}
		});

		snowflakecolorGreen.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				G = snowflakecolorGreen.getValue();
				window.repaint();
			}
		});

		snowflakecolorBlue.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				B = snowflakecolorBlue.getValue();
				window.repaint();
			}
		});

		snowflakecolorRotate.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				d = snowflakecolorRotate.getValue();
				window.repaint();
			}
		});

		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = new File("");

				try {
					// image = new Robot().createScreenCapture(new
					// Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
					// System.out.println("Panel 2: " + panel2.getX() + " , " + panel2.getHeight());

					image = new Robot().createScreenCapture(new Rectangle(p2X, 100, p2W, (panel2.getHeight() - 100)));

				} catch (HeadlessException e2) {
					e2.printStackTrace();
				} catch (AWTException e2) {
					e2.printStackTrace();
				}
				fc.setDialogTitle("Choose a directory to save your file: ");
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				int returnValue = fc.showSaveDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					if (fc.getSelectedFile().isDirectory()) {
						file = new File(fc.getSelectedFile(), "mySnowflake.png");
					}
				}

				try {
					ImageIO.write(image, "png", file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	public void paint(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g.setColor(new Color(255, 255, 255));

		g2.setFont(font);

		g2.drawString("Size", (panel1.getWidth() / 3) + 60, ((panel1.getHeight() / 7) * 1) - 210);
		g2.drawString("# of sides", (panel1.getWidth() / 3) + 0, ((panel1.getHeight() / 7) * 2) - 210);
		g2.drawString("Size Reduction", (panel1.getWidth() / 3) + 0, ((panel1.getHeight() / 7) * 3) - 210);
		g2.drawString("Red", (panel1.getWidth() / 3) + 60, ((panel1.getHeight() / 7) * 4) - 210);
		g2.drawString("Green", (panel1.getWidth() / 3) + 50, ((panel1.getHeight() / 7) * 5) - 210);
		g2.drawString("Blue", (panel1.getWidth() / 3) + 55, ((panel1.getHeight() / 7) * 6) - 210);
		g2.drawString("Rotate", (panel1.getWidth() / 3) + 50, ((panel1.getHeight() / 7) * 7) - 210);

		g2.setStroke(new BasicStroke(3));
		g.setColor(new Color(R, G, B));

		drawStar(X, Y, size, g);
	}

	private Graphics drawStar(int x, int y, int s, Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (s > 1) {

			for (int i = 1; i <= sides; i++) {

				endX = (int) ((int) x + (s * Math.cos((2 * Math.PI / sides) * i)));
				endY = (int) ((int) y - (s * Math.sin((2 * Math.PI / sides) * i)));

				Point2D point = rotate(x, y, endX, endY, d);

				g2.drawLine(x, y, (int) (point.getX()), (int) (point.getY()));

				drawStar((int) (point.getX()), (int) (point.getY()), (int) (s / r), g);
			}
		}
		return g2;
	}

	public Point2D rotate(int Xpiv, int Ypiv, int x, int y, int a) {
		Point2D result = new Point2D.Double();
		Point2D point = new Point2D.Double();

		point.setLocation(x, y);

		AffineTransform rotation = new AffineTransform();

		double angleToRadian = (a * Math.PI / 180);
		rotation.rotate(angleToRadian, Xpiv, Ypiv);
		rotation.transform(point, result);

		return result;
	}

	public void dialog(String m) {
		System.out.println(m);
	}
}