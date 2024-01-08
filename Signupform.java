import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class Signupform extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signupform frame = new Signupform();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Signupform() {
		
		final Connection con = DB.dbconnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("First Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(28, 72, 102, 20);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Last Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(28, 133, 102, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Signup Form");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(173, 11, 118, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("USER ID");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(28, 195, 73, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Password");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(28, 252, 91, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Email ID");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setBounds(29, 314, 72, 14);
		contentPane.add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		textField.setBounds(205, 72, 127, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(205, 132, 127, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(205, 194, 127, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(205, 261, 127, 20);
		contentPane.add(passwordField);
		
		textField_3 = new JTextField();
		textField_3.setBounds(205, 313, 127, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("signup");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String first=textField.getText();
					String last=textField_1.getText();
					String user=textField_2.getText();
					String password=passwordField.getText();
					String email=textField_3.getText();
					
					PreparedStatement pst=con.prepareStatement("insert into signup(firstname,lastname,userid,password,email) values(?,?,?,?,?)");
					pst.setString(1, first);
					pst.setString(2, last);
					pst.setString(3, user);
					pst.setString(4, password);
					pst.setString(5, email);
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"data added");
					
					
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					passwordField.setText("");
					textField_3.setText("");
					
					
					
				}
				catch(Exception er) {
					System.out.println(er);
				}
			}
		});
		btnNewButton.setBounds(160, 372, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Login l=new Login();
				l.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(259, 372, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}
