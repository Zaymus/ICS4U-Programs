import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CarTester {

	private JFrame frame;
	private JTextField txtMake;
	private JTextField txtModel;
	private JTextField txtYear;
	private JTextField txtColor;
	private JTextField txtTrim;
	private JTextField txtBasePrice;
	
	public optionsCar car;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CarTester window = new CarTester();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CarTester() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 501, 501);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblMake = new JLabel("Make");
		lblMake.setBounds(21, 33, 92, 26);
		frame.getContentPane().add(lblMake);
		
		JLabel lblModel = new JLabel("Model");
		lblModel.setBounds(21, lblMake.getY() * 2, 92, 26);
		frame.getContentPane().add(lblModel);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(21, lblMake.getY() * 3, 92, 26);
		frame.getContentPane().add(lblYear);
		
		JLabel lblColor = new JLabel("Color");
		lblColor.setBounds(21, lblMake.getY() * 4 , 92, 26);
		frame.getContentPane().add(lblColor);
		
		JLabel lblTrim = new JLabel("Trim");
		lblTrim.setBounds(21, lblMake.getY() * 5 , 92, 26);
		frame.getContentPane().add(lblTrim);
		
		JLabel lblBasePrice = new JLabel("Base Price");
		lblBasePrice.setBounds(21, lblMake.getY() * 6 , 108, 26);
		frame.getContentPane().add(lblBasePrice);
		
		txtMake = new JTextField();
		txtMake.setBounds(158, lblMake.getY(), 186, 32);
		frame.getContentPane().add(txtMake);
		txtMake.setColumns(10);
		
		txtModel = new JTextField();
		txtModel.setBounds(158, lblMake.getY() * 2 , 186, 32);
		frame.getContentPane().add(txtModel);
		txtModel.setColumns(10);
		
		txtYear = new JTextField();
		txtYear.setBounds(158, lblMake.getY() * 3 , 186, 32);
		frame.getContentPane().add(txtYear);
		txtYear.setColumns(10);
		
		txtColor = new JTextField();
		txtColor.setBounds(158, lblMake.getY() * 4 , 186, 32);
		frame.getContentPane().add(txtColor);
		txtColor.setColumns(10);
		
		txtTrim = new JTextField();
		txtTrim.setBounds(158, lblMake.getY() * 5 , 186, 32);
		frame.getContentPane().add(txtTrim);
		txtTrim.setColumns(10);
		
		txtBasePrice = new JTextField();
		txtBasePrice.setBounds(158, lblMake.getY() * 6 , 186, 32);
		frame.getContentPane().add(txtBasePrice);
		txtBasePrice.setColumns(10);
		
		JCheckBox chckbxNavigationSystem = new JCheckBox("Navigation system");
		chckbxNavigationSystem.setBounds(0, 247, 207, 35);
		frame.getContentPane().add(chckbxNavigationSystem);
		
		JCheckBox chckbxDvdSystem = new JCheckBox("DVD system");
		chckbxDvdSystem.setBounds(220, 247, 179, 35);
		frame.getContentPane().add(chckbxDvdSystem);
		
		JCheckBox chckbxTowPackage = new JCheckBox("Tow Package");
		chckbxTowPackage.setBounds(0, 286, 179, 35);
		frame.getContentPane().add(chckbxTowPackage);
		
		JLabel EstimatePrice = new JLabel("Approximate Price: $");
		EstimatePrice.setBounds(21, 383, 323, 26);
		frame.getContentPane().add(EstimatePrice);
		
		JButton btnSendInfo = new JButton("Push specs");
		btnSendInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//String make, String model, String color, String trim, int year, double price
				car = new optionsCar(txtMake.getText(), txtModel.getText(), txtColor.getText(), 
						txtTrim.getText(), Integer.parseInt(txtYear.getText()), 
						Double.parseDouble(txtBasePrice.getText()), chckbxNavigationSystem.isSelected(),
						chckbxDvdSystem.isSelected(), chckbxTowPackage.isSelected());
				
				EstimatePrice.setText("Approximate Price: $" + car.estimatePrice());
			}
		});
		btnSendInfo.setBounds(21, 338, 186, 35);
		frame.getContentPane().add(btnSendInfo);
		
		JButton button = new JButton("Complete sale");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EstimatePrice.setText("Price: $" + car.getPrice());
			}
		});
		button.setBounds(213, 338, 186, 35);
		frame.getContentPane().add(button);
	}
}
