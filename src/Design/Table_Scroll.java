package Design;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import Connect.sqlite.sqliteConnection;

public class Table_Scroll extends JPanel{
     JTable jt;
     static String[][] data;
     static String[] columns={"Tài khoản","Họ và Tên","Lớp","Giới tính","Trả lời đúng","Tình Trạng","Thời gian đã thi"};
	 public static int width=500;
	 public static int height=500;
     static Connection conn = sqliteConnection.dbConnector();
     @SuppressWarnings("serial")
	public Table_Scroll()
     {  
    	  data = getData();
          jt = new JTable(data, columns){
               public Component prepareRenderer(TableCellRenderer r, int data, int columns)
               {
                   Component c = super.prepareRenderer(r, data, columns);
    
                   if (data % 2 == 0)
                       c.setBackground(Color.WHITE);
    
                   else
                       c.setBackground(Color.LIGHT_GRAY);
    
                   return c;
               }
         };
  
  
         jt.setPreferredScrollableViewportSize(new Dimension(width-50, height));
         jt.setFillsViewportHeight(true);
  
         JScrollPane jps = new JScrollPane(jt);
         add(jps);
     }
 
     public static String[][] getData() {
    	int i=0;
    	String data[][] = new String[100][100];
    	String sql = "SELECT Ma_SV,Ten,Lop,Gioi_Tinh,Tl_Dung,Da_Thi,Thoi_Gian FROM SinhVien";
    	try {
			PreparedStatement pts = conn.prepareStatement(sql);
			ResultSet rs = pts.executeQuery();
			while(rs.next()){
				data[i][0] = rs.getString(1);
				data[i][1] = rs.getString(2);
				data[i][2] = rs.getString(3);
				if(rs.getBoolean(4)) 
					data[i][3] = "Nam";
				else 
					data[i][3] = "Nữ";
				
				if(rs.getInt(5)==0)
					data[i][4] = " ";
				else
					data[i][4] = String.valueOf(rs.getInt(5));
				
				if(rs.getBoolean(6)) 
					data[i][5] = "Đã thi";
				else 
					data[i][5] = "Chưa thi";
				
				if(rs.getString(7)!=null) 
					data[i][6] = rs.getString(7);
				else 
					data[i][6] = " ";
				
				i++;
			}
			pts.close();
			rs.close();
		} catch (SQLException e) {
			Messenger.msg("Lỗi!\n"+e.getMessage());
		}
    	return data;
	}
}
