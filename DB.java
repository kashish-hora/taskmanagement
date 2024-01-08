import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.*;
public class DB {
	Connection con=null;
	PreparedStatement pst;
	public static Connection dbconnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/taskmanager","root","google@2820");
				return con;
					
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	

}
