package Design;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Logined extends JDialog {
	private final JPanel contentPanel = new JPanel();
	static boolean status;
	static JLabel lbl_Name = new JLabel();
	
	//Thoát khỏi hệ thống 
	public static void exit(){System.exit(0);}
	public void setName(String name){lbl_Name.setText(name);}
	
	public Logined() {
		setTitle("Thông báo");
		setBounds(450, 150, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel label = new JLabel("HỆ THỐNG THI TRẮC NGHIỆM TIẾNG ANH");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("Arial", Font.BOLD, 16));
		JLabel lblXinCho = new JLabel("Xin chào bạn:");
		lbl_Name.setForeground(Color.GREEN);
		lbl_Name.setFont(new Font("Arial", Font.PLAIN, 16));
		
		JLabel lblThiGian = new JLabel("Thời gian làm bài:");
		lblThiGian.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lbl_time = new JLabel("60 phút");
		lbl_time.setForeground(Color.RED);
		lbl_time.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblCuHi = new JLabel("Số lượng câu hỏi: ");
		lblCuHi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblCu = new JLabel("50 câu");
		lblCu.setForeground(Color.RED);
		lblCu.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btn_Start = new JButton("Bắt đầu");
		btn_Start.setBackground(Color.ORANGE);
		btn_Start.setForeground(Color.BLACK);
		btn_Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(status){
					String msg = "Xin lỗi ! Tài khoản của bạn đã thi. \nChi tiết: \n"
							+ "Thời gian đăng nhập: " + Main.thoigian
							+ "\nĐể thi lại bạn cần liên hệ Adminstrator Database - Giáo viên"
							;
					Messenger.msg(msg);
				}
				else
					Main.Starting();
			}
		});
		
		JLabel lblChcBnThi = new JLabel("Chúc các bạn thi tốt !");
		
		JButton btn_logout = new JButton("Đăng xuất");
		btn_logout.setForeground(Color.white);
		btn_logout.setBackground(Color.gray);
		btn_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.logined.setVisible(false);
				Main.login.setVisible(true);
				Main.t4.stop();
		
			}
		});
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(44)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 324, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(87)
							.addComponent(lblXinCho)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lbl_Name, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(119)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblThiGian)
								.addComponent(lblCuHi))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCu)
								.addComponent(lbl_time, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(163)
							.addComponent(lblChcBnThi))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(99)
							.addComponent(btn_Start)
							.addGap(88)
							.addComponent(btn_logout)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(14)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblXinCho)
						.addComponent(lbl_Name))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblThiGian)
						.addComponent(lbl_time))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCuHi)
						.addComponent(lblCu))
					.addGap(18)
					.addComponent(lblChcBnThi)
					.addGap(32)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_Start)
						.addComponent(btn_logout))
					.addGap(35))
		);
		contentPanel.setLayout(gl_contentPanel);
	}
}
