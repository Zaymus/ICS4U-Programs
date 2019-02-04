import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class screen {
	int size = 16; // grid dimensions
	int emptySpace;
	int x = 0; // current player x coordinate
	int y = 0; // current player y coordinate
	int lx = 0; // previous player x coordinate
	int ly = 0; // previous player y coordinate
	String shipHit; // last ship name that was hit (s2, s4, s7...)
	JPanel[][] grid = new JPanel[size][size]; // array of panels the size of the grid dimension
	String[][] ships = new String[size][size];// array of ship locations per coordinate on the grid
	String[][] sunkShips = new String[size][size];// array of all hit/sunk ship locations per coordinate on the grid
	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					screen window = new screen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public screen() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GRAY);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		ships();
		setup();
	}

	public void setup() {

		//////////////////////////// playing-field\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new JPanel();
				grid[i][j].setBounds(i * 1600 / size + 160, j * 1600 / size + 50, 1600 / size, 1600 / size);
				grid[i][j].setBackground(Color.CYAN);
				grid[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				frame.getContentPane().add(grid[i][j]);
				grid[0][0].setBorder(BorderFactory.createLineBorder(Color.red, 5));
			}
		}

		//////////////////////////// player-aiming\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

		frame.getContentPane().setFocusable(true);
		frame.getContentPane().addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
					System.out.println("right");
					if (x == size - 1) {
						x = 0;
						grid[size - 1][y].setBorder(BorderFactory.createLineBorder(Color.black));
					} else {
						x++;
						lx = x - 1;
						ly = y;
					}
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
					System.out.println("left");
					if (x == 0) {
						x = size - 1;
						grid[0][y].setBorder(BorderFactory.createLineBorder(Color.black));
					} else {
						x--;
						lx = x + 1;
						ly = y;
					}
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
					System.out.println("down");
					if (y == size - 1) {
						y = 0;
						grid[x][size - 1].setBorder(BorderFactory.createLineBorder(Color.black));
					} else {
						y++;
						ly = y - 1;
						lx = x;
					}
				} else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
					System.out.println("up");
					if (y == 0) {
						y = size - 1;
						grid[x][0].setBorder(BorderFactory.createLineBorder(Color.black));
					} else {
						y--;
						ly = y + 1;
						lx = x;
					}

					////////////////////////////// firing-mechanism\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

				} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					System.out.println("Fire");
					fire();
				}
				if (grid[x][y].getBorder() != BorderFactory.createLineBorder(Color.gray)) {
					grid[lx][ly].setBorder(BorderFactory.createLineBorder(Color.black));
				}
				grid[x][y].setBorder(BorderFactory.createLineBorder(Color.red, 5));
				System.out.println("(" + x + "," + y + ")");

			}
		});
	}

	////////////////////////////// ship-locations\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	public void ships() {

		ships[15][15] = "s2-1";
		ships[14][15] = "s2-1";

		ships[0][0] = "s2-2";
		ships[0][1] = "s2-2";

		ships[2][0] = "s5-1";
		ships[2][1] = "s5-1";
		ships[2][2] = "s5-1";
		ships[2][3] = "s5-1";
		ships[2][4] = "s5-1";

		ships[15][1] = "s4";
		ships[14][1] = "s4";
		ships[13][1] = "s4";
		ships[12][1] = "s4";

		ships[11][3] = "s5";
		ships[12][3] = "s5";
		ships[13][3] = "s5";
		ships[14][3] = "s5";
		ships[15][3] = "s5";

		ships[15][5] = "s7";
		ships[15][6] = "s7";
		ships[15][7] = "s7";
		ships[15][8] = "s7";
		ships[15][9] = "s7";
		ships[15][10] = "s7";
		ships[15][11] = "s7";
	}

	/////////////////////////////// hit/miss/sunk-system\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	public void fire() {
		if (ships[x][y] != null) {
			shipHit = ships[x][y];
			sunkShips[x][y] = ships[x][y];
			ships[x][y] = null;
			System.out.println("Hit");
			System.out.println(shipHit);
			hit();

		} else {
			System.out.println("Miss");
			miss();
		}
	}

	public void hit() {
		grid[x][y].setBackground(Color.orange);
		/*
		 * if (x == size - 1 && y == 0) { if (ships[x - 1][y] != shipHit && ships[x][y +
		 * 1] != shipHit) { sunk(); } } else if (x == 0 && y == 0) { if (ships[x + 1][y]
		 * != shipHit && ships[x][y + 1] != shipHit) { sunk(); } } else if (x == size -
		 * 1 && y == size - 1) { if (ships[x - 1][y] != shipHit && ships[x][y - 1] !=
		 * shipHit) { sunk(); } } else if (x == 0 && y == size - 1) { if (ships[x +
		 * 1][y] != shipHit && ships[x][y - 1] != shipHit) { sunk(); } } else if (x > 0
		 * && x < size - 1 && y == 0) { if (ships[x + 1][y] != shipHit && ships[x -
		 * 1][y] != shipHit && ships[x][y + 1] != shipHit) { sunk(); } } else if (x > 0
		 * && x < size - 1 && y == size - 1) { if (ships[x + 1][y] != shipHit && ships[x
		 * - 1][y] != shipHit && ships[x][y - 1] != shipHit) { sunk(); } } else if (y >
		 * 0 && y < size - 1 && x == 0) { if (ships[x][y + 1] != shipHit && ships[x][y -
		 * 1] != shipHit && ships[x + 1][y] != shipHit) { sunk(); } } else if (y > 0 &&
		 * y < size - 1 && x == size - 1) { if (ships[x][y + 1] != shipHit && ships[x][y
		 * - 1] != shipHit && ships[x - 1][y] != shipHit) { sunk(); } } else { if
		 * (ships[x + 1][y] != shipHit && ships[x - 1][y] != shipHit && ships[x][y + 1]
		 * != shipHit && ships[x][y - 1] != shipHit) { sunk(); } }
		 */

	}

	public void miss() {
		if (grid[x][y].getBackground() != Color.red && grid[x][y].getBackground() != Color.orange) {
			grid[x][y].setBackground(Color.white);
		} else {
			if (grid[x][y].getBackground() == Color.orange) {
				grid[x][y].setBackground(Color.orange);
			} else {
				grid[x][y].setBackground(Color.red);
			}
		}
	}

	/////////////////////////////// sunk-ship-identification\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	public void sunk() {
		System.out.println("\n\n Sunk{");
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[r].length; c++) {
				if (sunkShips[r][c] == shipHit) {
					System.out.println("(" + r + "," + c + ")");
					grid[r][c].setBackground(Color.red);
				}
			}
		}
		System.out.println("\n\n}");
		gameOver();
	}

	public void gameOver() {
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[r].length; c++) {
				if (ships[r][c] == null) {
					emptySpace++;
				}
			}
		}
		if (emptySpace == ((size - 1) * (size - 1))) {
			System.exit(0);
		}
	}
}