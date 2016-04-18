package Connect.sqlite;

import java.sql.*;

import javax.swing.JOptionPane;

import Design.Main;
import Design.Messenger;

public class sqliteConnection {

	public static Connection dbConnector(){
		Connection connect = null;
		//Link Local Database
		String url="jdbc:sqlite:data.sqlite";
		try {
			Class.forName("org.sqlite.JDBC");
			connect = DriverManager.getConnection(url);
			System.out.println("Connection database successful !");
		} catch (ClassNotFoundException e) {
			Messenger.msg("Lỗi!\n"+e.getMessage());
		} catch (SQLException e) {
			Messenger.msg("Lỗi!\n"+e.getMessage());
		}
		return connect;
	}

}
