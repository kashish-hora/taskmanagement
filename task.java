import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class task extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					task frame = new task();
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
	Connection con=null;
	private JTextField textField_1;
	public task() {
		con=(Connection) DB.dbconnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 508);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 202, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MY TASK");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(211, 22, 90, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Important Task");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(20, 103, 132, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Other Tasks");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(20, 220, 113, 14);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(194, 105, 154, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		final JButton btnNewButton = new JButton("Add Task");
		btnNewButton.addActionListener(new ActionListener() {
		

			public void actionPerformed(ActionEvent e) {
				try {
				String imp=textField.getText();
	
				String other=textField_1.getText();
				PreparedStatement pst=con.prepareStatement("insert into todo(important,other)values(?,?)");
				pst.setString(1, imp);
				pst.setString(2, other);
				
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "task added");
				textField.setText("");
				textField_1.setText("");
				
				
				int a;
				PreparedStatement pst1=con.prepareStatement("select * from todo");
				ResultSet rs=pst1.executeQuery();
				ResultSetMetaData rd=(ResultSetMetaData)rs.getMetaData();
				
				a=rd.getColumnCount();
				DefaultTableModel df=(DefaultTableModel) table.getModel();
				df.setRowCount(0);
				while(rs.next())
				{
					Vector v2=new Vector();
					for(int i=1;i<=a;i++) {
						v2.add(rs.getString("id"));
						
						v2.add(rs.getString("important"));
						v2.add(rs.getString("other"));
						
						
					}
					df.addRow(v2);
				}
				
				
				
				
				
				
				
			}
				catch(Exception er) {
					System.out.println(er);
				}
			}
			
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(46, 420, 106, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Edit Task");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel df=(DefaultTableModel)table.getModel();
				int s=table.getSelectedRow();
				int id=Integer.parseInt(df.getValueAt(s, 0).toString());
				
			
				
				try {
					String imp=textField.getText();
					String other=textField_1.getText();
				
					PreparedStatement pst=con.prepareStatement("update todo set important=?,other=? where id=?");
					pst.setString(1,imp);
					pst.setString(2,other);
					pst.setInt(3, id);
					
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "task updated");
					textField.setText("");
					textField_1.setText("");
					
					int a;
					 pst=con.prepareStatement("select * from todo");
					ResultSet rs=pst.executeQuery();
					ResultSetMetaData rd=(ResultSetMetaData) rs.getMetaData();
					a=rd.getColumnCount();
					DefaultTableModel df1=(DefaultTableModel) table.getModel();
					df1.setRowCount(0);
					while(rs.next())
					{
						Vector v2=new Vector();
						for(int i=1;i<=a;i++) {
							v2.add(rs.getString("id"));
							v2.add(rs.getString("important"));
							v2.add(rs.getString("other"));
							
							
						}
						df.addRow(v2);
					}
					
					
					
				}
				catch(Exception er) {
					System.out.println(er);
				}
			}
		
				
				
			
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(189, 420, 112, 25);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Done");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBounds(344, 420, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(403, 93, 266, 257);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel df=(DefaultTableModel)table.getModel();
				int selected=table.getSelectedRow();
				int id=Integer.parseInt(df.getValueAt(selected, 0).toString());
				textField.setText(df.getValueAt(selected, 1).toString());
				textField_1.setText(df.getValueAt(selected, 2).toString());
				btnNewButton.setEnabled(false);
				
				
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Important", "Other"
			}
		));
		
		textField_1 = new JTextField();
		textField_1.setBounds(194, 179, 154, 171);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
	}
}
