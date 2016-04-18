package Design;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JMenuBar;
import javax.swing.JMenu;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;

import Connect.sqlite.sqliteConnection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static Connection connector = sqliteConnection.dbConnector();
	private JTextField textF_user;
	private JPasswordField passwordF_pass;
	static JLabel lbl_messenger;
	public static void exit(){System.exit(0);}
	String Admin_user="Admin", Admin_pass="admin";
	public static JFrame frame = new JFrame("Danh sách sinh viên");

	public Login() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				String msg ="Xin chào!\n"+"Đây là hệ thống được xây dựng để thử nghiệm thi trắc nghiệm tiếng anh   \n"
						+ "Để biết thêm chi tiết và các chú ý ở trong mục \" Giới thiệu \".";
							Messenger.msg(msg);
			}
			@Override
			public void windowClosing(WindowEvent e) {
				Main.Exit_app();
			}
		});
		setResizable(false);
		setFont(new Font("Times New Roman", Font.BOLD, 12));
		setTitle("Đăng nhập");
		setBounds(450, 150, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		
		JLabel lblHThngThi = new JLabel("HỆ THỐNG THI TRẮC NGHIỆM TIẾNG ANH");
		lblHThngThi.setForeground(Color.BLUE);
		lblHThngThi.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel lblMiBnng = new JLabel("Mời bạn đăng nhập hệ thống !");
		lblMiBnng.setFont(new Font("Arial", Font.BOLD, 12));
		
		JLabel lblTiKhon = new JLabel("Tài khoản:");
		lblTiKhon.setFont(new Font("Arial", Font.BOLD, 12));
		
		JButton btn_login = new JButton("Đăng nhập");
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sql = "SELECT Ma_SV,Mat_Khau,Ten,Da_Thi,Thoi_Gian from SinhVien WHERE Ma_SV = ? AND Mat_Khau = ?";
				boolean admin=textF_user.getText().equals(Admin_user) && passwordF_pass.getText().equals(Admin_pass);
				try {
					PreparedStatement pts = connector.prepareStatement(sql);
					pts.setString(1, textF_user.getText());
					pts.setString(2, passwordF_pass.getText());
					ResultSet rs = pts.executeQuery();
					if(!rs.next() && !admin ){
						String msg = "Xin lỗi !\n"+"Tài khoản, mật khẩu không đúng.";
						Messenger.msg(msg);
					}
					else if(admin){
						Messenger.msg("Hệ thống được đăng nhập từ Quả Trị Viên hệ thống. ");
						Main.Ad_logined();
					}
					else{
						Main.msv=rs.getString(1);
						Main.name=rs.getString(3);
						Logined.status=rs.getBoolean(4);
						Main.thoigian=rs.getString(5);
						Messenger.msg("Đăng nhập thành công !");
						Main.Logined();
					}
					pts.close();
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					Messenger.msg("Lỗi!"+e.getMessage());
				}
			}
		});
		btn_login.setForeground(Color.WHITE);
		btn_login.setBackground(Color.BLUE);
		btn_login.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		textF_user = new JTextField();
		textF_user.setColumns(10);
		
		passwordF_pass = new JPasswordField();
		
		JLabel lblMtKhu = new JLabel("Mật khẩu:");
		lblMtKhu.setFont(new Font("Arial", Font.BOLD, 12));
		
		lbl_messenger = new JLabel("");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(132)
							.addComponent(lblMiBnng))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(173)
							.addComponent(btn_login))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(112)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTiKhon)
								.addComponent(lblMtKhu))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(passwordF_pass, Alignment.TRAILING)
								.addComponent(textF_user, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(52)
							.addComponent(lblHThngThi))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lbl_messenger, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lbl_messenger)
					.addGap(15)
					.addComponent(lblHThngThi)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblMiBnng)
					.addGap(31)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTiKhon)
						.addComponent(textF_user, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordF_pass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMtKhu))
					.addGap(26)
					.addComponent(btn_login)
					.addContainerGap(54, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.ORANGE);
		setJMenuBar(menuBar);
		
		JMenu mnTrangCh = new JMenu("Trang chủ");
		mnTrangCh.setForeground(Color.BLACK);
		menuBar.add(mnTrangCh);
		
		JMenu mnChcNng = new JMenu("Tài khoản");
		mnChcNng.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setBounds(250, 100, 800, 500);
				Table_Scroll tbl = null;
				tbl.width=800; 
				tbl.height=500;
				tbl = new Table_Scroll();
				Main.List_Account(frame, tbl);
			}
		});
		mnChcNng.setForeground(Color.BLACK);
		menuBar.add(mnChcNng);
		
		JMenu mnNewMenu = new JMenu("Giới thiệu");
		mnNewMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Main.Information();
			}
		});
		mnNewMenu.setForeground(Color.BLACK);
		menuBar.add(mnNewMenu);
	}
	public static void Text_messenger(){
		String temp;
		String text = "                                                                                                                                    "
				+"Chú ý: Hệ thống sử dụng mã thẻ sinh viên làm tài khoản truy cập hệ thống. Vì mã thẻ sinh viên có thể nhiều người biết của nhau. "
				+ "Nên hệ thống không cho phép sinh viên trực tiếp đăng ký tài khoản. Bạn cần liên hệ với giáo viên để tạo mật khẩu !";
		try {
			while(true){
				Thread.sleep(80);
				temp = text.substring(0, 1);
				text = text.substring(1, text.length());
				text += temp;
				lbl_messenger.setText(text);
				lbl_messenger.setForeground(Color.red);
			}
		} catch (InterruptedException e) {
			Messenger.msg("Lỗi!\n"+e.getMessage());
		}
	}
}
