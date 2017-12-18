package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JTextField;

public class TestWindow {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestWindow window = new TestWindow();
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
	public TestWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 301, 426);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblFirstCv = new JLabel("First CV");
		lblFirstCv.setForeground(Color.WHITE);
		lblFirstCv.setBackground(Color.WHITE);
		lblFirstCv.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFirstCv.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirstCv.setBounds(24, 11, 98, 42);
		frame.getContentPane().add(lblFirstCv);
		
		JLabel lblSecondCv = new JLabel("Second CV");
		lblSecondCv.setHorizontalAlignment(SwingConstants.CENTER);
		lblSecondCv.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSecondCv.setForeground(Color.WHITE);
		lblSecondCv.setBounds(157, 11, 98, 42);
		frame.getContentPane().add(lblSecondCv);
		
		textField = new JTextField();
		textField.setBounds(10, 53, 124, 301);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
