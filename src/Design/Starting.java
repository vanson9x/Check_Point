package Design;

import java.sql.*;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import Connect.sqlite.sqliteConnection;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class Starting extends JDialog {
	static Connection connection = sqliteConnection.dbConnector();
	static JLabel lbl_lasstime = new JLabel("00:00:00");
	static JLabel lbl_cau = new JLabel("Câu 1: ");
	static JLabel lbl_cauhoi = new JLabel("New label");
	static JCheckBox ckBox_A = new JCheckBox("Đáp án A");
	static JCheckBox ckBox_B = new JCheckBox("Đáp án B");
	static JCheckBox ckBox_C = new JCheckBox("Đáp án C");
	static JCheckBox ckBox_D = new JCheckBox("Đáp án D");
	static JButton btn_send = new JButton("Chuyển câu »");
	static boolean rep,sle,loop;
	
	static int cau=1,temp;
	private final JPanel contentPanel = new JPanel();
	private JTextField textF_next;
	protected static String[][] data = new String[55][10];
	protected static String[] Select = new String[55];
	
	// Nạp dữ liệu từ SQL vào RAM
	protected static void Get_DATA(){
		int i=1;
		String sql="SELECT NoiDung,A,B,C,D,Answer FROM CauHoi";
		try {
			PreparedStatement pts = connection.prepareStatement(sql);
			ResultSet rs = pts.executeQuery();
			while(rs.next()){
				data[i][1]=rs.getString(1);
				data[i][2]=rs.getString(2);
				data[i][3]=rs.getString(3);
				data[i][4]=rs.getString(4);
				data[i][5]=rs.getString(5);
				data[i][6]=rs.getString(6);
				i++;
			}
			pts.close();
			rs.close();
		} catch (SQLException e) {
			Messenger.msg("Lỗi!\n"+e.getMessage());
		}
	}
	
	//Hiển thị nội dung nên màn hình
	static void Load_data(int cau){
		if(cau==50){
			btn_send.setText("NỘP BÀI THI");
			btn_send.setBackground(Color.orange);
			btn_send.setForeground(Color.BLACK);
		}
		else{
			btn_send.setText("Chuyển câu »");
			btn_send.setBackground(Color.green);
			btn_send.setForeground(Color.black);
		}
		
		lbl_cau.setText("Cau: "+String.valueOf(cau));
		lbl_cauhoi.setText(data[cau][1]);
		ckBox_A.setText(data[cau][2]);
		ckBox_B.setText(data[cau][3]);
		ckBox_C.setText(data[cau][4]);
		ckBox_D.setText(data[cau][5]);
	}
	
	//Thoát khỏi hệ thống 
	public static void exit(){System.exit(0);}
		
	//Thiết lập thời gian bắt đầu và còn lại
	public void setTime(){
		int h,m,s;
		Time time = new Time();
		h = 0; m = 59; s = 59;
		lbl_lasstime.setText(h+":"+m+":0");
		lbl_lasstime.setForeground(Color.green);
		while (h!=0 || m!=0 || s!=0){
			 try {
				Thread.sleep(1000);
				s--;
				lbl_lasstime.setText(h+":"+m+":"+s);
				if(m==15){
					Messenger.msg("Thời gian còn lại: 15 phút");
					lbl_lasstime.setForeground(Color.RED);
				}
				if(s==0 && (h!=0 || m!=0)){
					if(m==0){h--;m=59;s=60;}
					else{m--;s=60;}
				}
			} catch (InterruptedException e) {
				Messenger.msg("Lỗi!\n"+e.getMessage());
			}
		 } 
		String msg = "Hết giờ làm bài !\n"+"Để xem điểm click Tài Khoản tại màn hình đăng nhập.";
		Messenger.msg(msg);
		Save_database(Main.msv,Result());
	} 
	
	// Cập nhật dữ liệu vào database
	public static void Save_database(String msv, int result) {
		String query = "UPDATE SinhVien SET Tl_Dung = ?, Thoi_Gian = ?, Da_Thi = ? " + "WHERE Ma_SV = ?";
		try {
			PreparedStatement pts = connection.prepareStatement(query);
			String thoigian = Time.date + " - " + Time.time;
			//////////////////////////
			pts.setInt(1, result);
			pts.setString(2, thoigian);
			pts.setBoolean(3, true);
			pts.setString(4, msv);
			//////////////////////////
			pts.executeUpdate();
			pts.close();
			System.out.println("Update Database sccussfull!");
		} catch (SQLException e) {
			Messenger.msg("Lỗi!\n"+e.getMessage());
		}
		Main.start.setVisible(false);
		Main.logined.setVisible(true);
	}
	
	//Ket qua chon
	public static String select_CheckBox(){
		if(ckBox_A.isSelected()) return ckBox_A.getText();
		else if(ckBox_B.isSelected()) return ckBox_B.getText();
		else if(ckBox_C.isSelected()) return ckBox_C.getText();
		else if(ckBox_D.isSelected()) return ckBox_D.getText();
		else return null;
	}
	
	// Đánh dấu lại lựa chọn
	public static void Replace_Select(String s){
		if(s!=null){
			if(ckBox_A.getText().equals(s)) ckBox_A.setSelected(true);
			else if(ckBox_B.getText().equals(s)) ckBox_B.setSelected(true);
			else if(ckBox_C.getText().equals(s)) ckBox_C.setSelected(true);
			else if(ckBox_D.getText().equals(s)) ckBox_D.setSelected(true);
		}
	}
	
	//Kiem tra vi pham chon nhieu dap an hoac k chon dap an nao
	public static int is_CheckBox(){
		int count=0;
		if(ckBox_A.isSelected()) count++;
		if(ckBox_B.isSelected()) count++;
		if(ckBox_C.isSelected()) count++;
		if(ckBox_D.isSelected()) count++;
		return count;
	}
	
	//Bo chon cac dap an
	public static void not_checkBox(){
		ckBox_A.setSelected(false);
		ckBox_B.setSelected(false);
		ckBox_C.setSelected(false);
		ckBox_D.setSelected(false);
	}
	
	// Số lượng đáp án trả lời đúng
	public static int Result(){
		int kq=0;
		for(int i=1;i<=50;i++)
			if(data[i][6].equals(Select[i]))
				kq++;
		return kq;
	}
	/**
	 * Create the dialog.
	 */
	public Starting() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				Waring war = new Waring();
				war.setDefaultCloseOperation(0);
				war.setVisible(true);
			}
			@Override
			public void windowOpened(WindowEvent arg0) {
				Get_DATA();
				Load_data(1);
				cau++;
			}
		});
		setResizable(false);
		setTitle("Bài thi");
		setBounds(350, 100, 640, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel label = new JLabel("HỆ THỐNG THI TRẮC NGHIỆM TIẾNG ANH");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("Arial", Font.BOLD, 18));
		
		JLabel lblKtThc = new JLabel("Thời gian còn lại |");
		lblKtThc.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_lasstime.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_cau.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_send.setForeground(Color.BLACK);
		btn_send.setBackground(Color.GREEN);
		btn_send.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(btn_send.getText().equals("NỘP BÀI THI") && textF_next.getText().equals("")){
					Save_database(Main.msv,Result());
					Messenger.msg("Nộp bài hoàn tất!\n"+"Để xem điểm click Tài Khoản tại màn hình đăng nhập.");
				}
				else{
					if(is_CheckBox()>1){Messenger.msg("Không được chọn 2 đáp án");}
					else if(textF_next.getText().equals("") && cau <51){
						
							Select[cau-1]=select_CheckBox();
							not_checkBox();
							Load_data(cau);
							Replace_Select(Select[cau]);
							loop=true;
					}
					else{ // Chỉ ra địa chỉ cần chuyển đến
						if(loop){Select[cau-1]=select_CheckBox(); loop=false;}
						else Select[temp]=select_CheckBox();
						try{
							cau=Integer.parseInt(textF_next.getText());
							if(cau<1 || cau>50) Messenger.msg("Vui lòng nhập trong khoảng 1 - 50 ");
							else{
								Load_data(cau);
								not_checkBox();
								Replace_Select(Select[cau]);
								textF_next.setText("");
								temp=cau;
							}
						}catch(Exception e){
							Messenger.msg("Chỉ được nhập số !");
						}
					}
					cau++;
				}
			}
		});
		
		JLabel lblDiChuyn = new JLabel("Để di chuyển đến câu bất kì nhập câu vào ô nào:");
		lblDiChuyn.setFont(new Font("Tahoma", Font.ITALIC, 11));
		
		textF_next = new JTextField();
		textF_next.setColumns(10);
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(0)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(61)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lbl_cau)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lbl_cauhoi, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblKtThc)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lbl_lasstime)
									.addGap(69))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(108)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(ckBox_A)
										.addComponent(ckBox_C))
									.addGap(158))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblDiChuyn)
									.addPreferredGap(ComponentPlacement.UNRELATED)))
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textF_next, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addComponent(ckBox_D)
								.addComponent(ckBox_B))
							.addGap(245))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(119)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE)))
					.addGap(38))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(240)
					.addComponent(btn_send)
					.addContainerGap(332, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(23)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(40)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_cauhoi)
						.addComponent(lbl_cau))
					.addGap(48)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(ckBox_A)
						.addComponent(ckBox_B))
					.addGap(41)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(ckBox_C)
						.addComponent(ckBox_D))
					.addGap(50)
					.addComponent(btn_send, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(26)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textF_next, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDiChuyn))
					.addGap(53)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKtThc)
						.addComponent(lbl_lasstime))
					.addGap(28))
		);
		ckBox_A.setFont(new Font("Arial", Font.PLAIN, 12));
		ckBox_C.setFont(new Font("Arial", Font.PLAIN, 12));
		ckBox_B.setFont(new Font("Arial", Font.PLAIN, 12));
		ckBox_D.setFont(new Font("Arial", Font.PLAIN, 12));
		contentPanel.setLayout(gl_contentPanel);
	}
}
