package Admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import Connect.sqlite.sqliteConnection;
import Design.Login;
import Design.Main;
import Design.Messenger;
import Design.Table_Scroll;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ad_logined extends JFrame {

	private JPanel contentPane;
	private JTextField textF_name;
	private JTextField textF_class;
	private Connection connection = sqliteConnection.dbConnector();
	private static PreparedStatement pts;
	private JTextField textF_user;
	private JPasswordField passwordF_pass;
	public static JCheckBox ckBox_boy, ckBox_girl, ckBox_eTrue, ckBox_eFalse;
	static boolean ck_name, ck_user, ck_pass, ck_sex, ck_exam, error;
	
	void checkSelect(JCheckBox a, JCheckBox b){
		String s=null;
		if(a.getText().equals("Nam")) s="Giới tính";
		else s="Tình trạng";
		if(a.isSelected() && b.isSelected())
			Messenger.msg("Chỉ được phép chọn 1 "+s);
		else if(!a.isSelected() && !b.isSelected())
			Messenger.msg("Phải chọn 1 "+s);
		else if(a.getText().equals("Nam")) ck_sex=true;
		else ck_exam=true;
			
	}
	
	boolean get_Select(JCheckBox a, JCheckBox b){
		if(a.isSelected()!=b.isSelected()){
			if(a.isSelected()) return true;
			else return false;
		}
		else if(a.isSelected() && b.isSelected()){
			Messenger.msg("Không được chọn cả 2");
			return false;
		}
		return false;
	}
	
	void check_JText(JTextField jT){
		String object = "";
		if(jT==textF_name) object="Họ và Tên"; 
		else object="Tài khoản";
		if(jT.getText().equals(""))
			Messenger.msg("Không được bỏ trống " + object);
		else{
			if(jT==textF_name) ck_name=true;
				else ck_user=true;
		}
	}
	
	String get_Text(JTextField jT){
		if(!jT.getText().equals(""))
			return jT.getText();
		else
			return null;
	}
	
	String get_Text(JPasswordField jP){
		if(!jP.getText().equals(""))
			return jP.getText();
		else
		return null;
	}
	
	void check_JPass(){
		if(passwordF_pass.getText().equals(""))
			Messenger.msg("Không được bỏ trống Mật khẩu");
		else ck_pass=true;
	}
	
	public ad_logined() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				setVisible(false);
				JFrame frame = new JFrame("Danh sách đã được cập nhật");
				Login.frame = frame;
				Main.login.setVisible(true);
			}
		});
		setTitle("Adminstrator");
		setBounds(450, 150, 450, 350);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblH = new JLabel("HỆ THỐNG THAY ĐỔI DỮ LIỆU CƠ SỞ - DATABASE");
		lblH.setFont(new Font("Arial", Font.BOLD, 15));
		
		JLabel lblTiKhon = new JLabel("Họ Và Tên:");
		lblTiKhon.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblMtKhu = new JLabel("Tài khoản:");
		lblMtKhu.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel label = new JLabel("");
		
		JLabel lblMtKhu_1 = new JLabel("Mật khẩu:");
		lblMtKhu_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblLp = new JLabel("Lớp:");
		lblLp.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNewLabel = new JLabel("Giới tính:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		textF_name = new JTextField();
		textF_name.setColumns(10);
		
		textF_class = new JTextField();
		textF_class.setColumns(10);
		
		ckBox_boy = new JCheckBox("Nam");
		ckBox_girl = new JCheckBox("Nữ");
		
		JLabel lblTnhTrng = new JLabel("Tình trạng:");
		lblTnhTrng.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		ckBox_eTrue = new JCheckBox("Đã thi");
		ckBox_eFalse = new JCheckBox("Chưa thi");
		
		JButton btn_Add = new JButton("Thêm");
		btn_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
				String sql="INSERT INTO SinhVien(Ten,Ma_SV,Mat_Khau,Lop,Gioi_Tinh,Da_Thi) VALUES (?,?,?,?,?,?)";
				try {
					pts = connection.prepareStatement(sql);
					check_JText(textF_name);
					if(ck_name){
						pts.setString(1, textF_name.getText());
						check_JText(textF_user);
						if(ck_user){
							pts.setString(2, textF_user.getText());
							check_JPass();
							if(ck_pass){
								pts.setString(3, passwordF_pass.getText());
								pts.setString(4, textF_class.getText());
								checkSelect(ckBox_boy, ckBox_girl);
								if(ck_sex){
									if(ckBox_boy.isSelected()) pts.setBoolean(5, true);
									else pts.setBoolean(5, false);
									checkSelect(ckBox_eTrue, ckBox_eFalse);
									if(ck_exam){
										if(ckBox_eTrue.isSelected()) pts.setBoolean(6, true);
										else pts.setBoolean(6, false);
										
										if(pts.executeUpdate()==1){
											Messenger.msg("Thêm thành công !");
											pts.close();
										}
									}
								}
							}
						}
					}
				//-----------------------------------------------
				} catch (SQLException e) {
					if(e.getMessage().equals("[SQLITE_CONSTRAINT]  Abort due to constraint violation (column Ma_SV is not unique)"))
					Messenger.msg("Lỗi: Đã tồn tại tài khoản này trên hệ thống !");
				}
			}
		});
		

		btn_Add.setBackground(Color.ORANGE);
		btn_Add.setForeground(Color.WHITE);
		btn_Add.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JButton btn_Edit = new JButton("Sửa");
		btn_Edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql,key;
				check_JText(textF_user);
				
				if(ck_user){
					key=" WHERE Ma_SV=\'"+textF_user.getText()+"\'";
					if(get_Text(textF_name)!=null){
						sql = "UPDATE SinhVien SET Ten=\'" + textF_name.getText()+"\'"+key;
						Update(sql);
					}
					if(get_Text(passwordF_pass)!=null){
						sql = "UPDATE SinhVien SET Mat_Khau=\'"+ passwordF_pass.getText()+"\'"+key;
						Update(sql);
					}
					if(get_Text(textF_class)!=null){
						sql = "UPDATE SinhVien SET Lop=\'"+ textF_class.getText()+"\'"+key;
						Update(sql);
					}
					if(ckBox_boy.isSelected() != ckBox_girl.isSelected()){
						sql = "UPDATE SinhVien SET Gioi_Tinh=?"+key;
						Update(sql,get_Select(ckBox_boy, ckBox_girl));
					}
					if(ckBox_eTrue.isSelected() != ckBox_eFalse.isSelected()){
						sql = "UPDATE SinhVien SET Da_Thi=?"+key;
						Update(sql,get_Select(ckBox_eTrue, ckBox_eFalse));
						// Nếu tài khoản đã thi rồi mà muốn cho thi lại thì cần thay đổi giá trị 
						// Tl_Dung và Thoi_Gian trong database
						if(ckBox_eFalse.isSelected()){
							sql = "UPDATE SinhVien SET Tl_Dung=?, Thoi_Gian=?"+key;
							PreparedStatement pts;
							try {
								pts = connection.prepareStatement(sql);
								pts.setInt(1, 0);
								pts.setString(2, " ");
								pts.executeUpdate();
								pts.close();
							} catch (SQLException e1) {
								Messenger.msg("Lỗi!\n"+e1.getMessage());
							}
						}
					}
					
					if(!error) 
						Messenger.msg("Cập nhật thông tin thành công");
				}
			}
			
			private void Update(String sql){
				try {
					pts = connection.prepareStatement(sql);
					pts.executeUpdate();
					pts.close();
				} catch (SQLException e) {
					error=true;
					Messenger.msg("Lỗi !\n"+e.getMessage());
				}
			}
			private void Update(String sql, boolean b){
				System.out.println(b);
				try {
					pts = connection.prepareStatement(sql);
					pts.setBoolean(1, b);
					pts.executeUpdate();
					pts.close();
				} catch (SQLException e) {
					Messenger.msg("Lỗi!/n"+e.getMessage());
				}
			}
		});
		btn_Edit.setForeground(Color.BLACK);
		btn_Edit.setBackground(Color.GREEN);
		btn_Edit.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JButton btn_Delete = new JButton("Xóa");
		btn_Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql="";
				check_JText(textF_user);
				if(ck_user){
					sql="DELETE FROM SinhVien WHERE Ma_SV=\""+textF_user.getText()+"\"";
					try {
						PreparedStatement pts = connection.prepareStatement(sql);
						if(pts.executeUpdate()==1)
							Messenger.msg("Xóa tài khoản \""+textF_user.getText()+"\" thành công !");
						else
							Messenger.msg("Không tồn tại tài khoản \""+textF_user.getText()+"\"");
					} catch (SQLException e1) {
						Messenger.msg("Lỗi!\n"+e1.getMessage());
					}
				}
			}
		});
		btn_Delete.setForeground(Color.WHITE);
		btn_Delete.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_Delete.setBackground(Color.RED);
		
		textF_user = new JTextField();
		textF_user.setColumns(10);
		
		passwordF_pass = new JPasswordField();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(31, Short.MAX_VALUE)
					.addComponent(lblH)
					.addGap(25))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(103)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTnhTrng)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTiKhon)
								.addComponent(lblMtKhu)
								.addComponent(lblMtKhu_1)
								.addComponent(label)
								.addComponent(lblLp)
								.addComponent(lblNewLabel))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(15)
									.addComponent(btn_Edit, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
									.addGap(27)
									.addComponent(btn_Delete, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(textF_user, Alignment.LEADING)
									.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
										.addGap(4)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(ckBox_eTrue)
											.addComponent(ckBox_boy))
										.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(ckBox_girl)
											.addComponent(ckBox_eFalse)))
									.addComponent(textF_class, Alignment.LEADING)
									.addComponent(textF_name, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
									.addComponent(passwordF_pass)))))
					.addGap(43))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(66)
					.addComponent(btn_Add, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(276, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblH)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTiKhon)
						.addComponent(textF_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMtKhu)
						.addComponent(textF_user, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblMtKhu_1)
							.addComponent(passwordF_pass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLp)
						.addComponent(textF_class, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(ckBox_boy)
						.addComponent(ckBox_girl))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTnhTrng)
						.addComponent(ckBox_eTrue)
						.addComponent(ckBox_eFalse))
					.addPreferredGap(ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_Add)
						.addComponent(btn_Delete)
						.addComponent(btn_Edit))
					.addGap(32))
		);
		contentPane.setLayout(gl_contentPane);
	}
	public static void exit(){
		System.exit(0);
	}
}
