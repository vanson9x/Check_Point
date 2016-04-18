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
import javax.swing.LayoutStyle.ComponentPlacement;

import Admin.ad_logined;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Waring extends JDialog {

	private final JPanel contentPanel = new JPanel();
	//Thoát khỏi hệ thống 
	public static void exit(){System.exit(0);}
		
	public Waring() {
		setTitle("Cảnh báo");
		setResizable(false);
		setBounds(450, 150, 480, 160);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblNu = new JLabel("Nếu bỏ thi kết quả vẫn được lưu lại và bạn không được phép thi lại !");
		
		JLabel lbl_text2 = new JLabel("                                Bạn có chắc chắn thoát không ?");
		
		JButton btn_yes = new JButton("Có");
		btn_yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.start.Save_database(Main.msv,Main.start.Result());
				setVisible(false);
				Main.logined.setVisible(false);
				Main.login.lbl_messenger.setText("");
				Messenger.msg("Để xem điểm click Tài Khoản tại màn hình đăng nhập.");
				Main.login.frame = new JFrame();
				Main.login.setVisible(true);
			}
		});
		
		JButton btn_no = new JButton("Không");
		btn_no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl_text2)
						.addComponent(lblNu, GroupLayout.PREFERRED_SIZE, 467, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addGap(55)
					.addComponent(btn_yes, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 203, Short.MAX_VALUE)
					.addComponent(btn_no)
					.addGap(130))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNu)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lbl_text2)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_yes)
						.addComponent(btn_no))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
	}
}
