package Design;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JTextArea;

public class Information extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public static void exit(){System.exit(0);}
	
	public Information() {
		setResizable(false);
		setTitle("Thông tin");
		setBounds(400, 150, 550, 370);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblTnPhnMm = new JLabel("Tên phần mềm:");
		lblTnPhnMm.setForeground(Color.BLUE);
		lblTnPhnMm.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblPhnMmThi = new JLabel("Phần mềm thi trắc nghiệm tiếng anh");
		
		JLabel lblPhinBn = new JLabel("Phiên bản:");
		lblPhinBn.setForeground(Color.BLUE);
		lblPhinBn.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel label = new JLabel("1.0.0.1");
		
		JLabel lblMtT = new JLabel("Mô tả:");
		lblMtT.setForeground(Color.BLUE);
		lblMtT.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblThcHin = new JLabel("Phần mềm được thiết kế nhằm tăng hiệu quả trong thi cử.  ");
		
		JLabel lblHngnCh = new JLabel("Hướng đến chủ chương hiện đại hóa giáo dục của Bộ Giáo Dục và Đào Tạo");
		
		JLabel lblLu = new JLabel("Lưu ý:");
		lblLu.setForeground(Color.BLUE);
		lblLu.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNhmPhtTrin = new JLabel("Nhóm phát triển:");
		lblNhmPhtTrin.setForeground(Color.BLUE);
		lblNhmPhtTrin.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblCopyr = new JLabel("Copyright: vanson9x.it@gmail.com");
		lblCopyr.setForeground(Color.ORANGE);
		
		JLabel lblComanyFithou = new JLabel("Comany: FITHOU.EDU.VN");
		lblComanyFithou.setForeground(Color.ORANGE);
		
		JTextArea txtrdfasdfadf = new JTextArea();
		txtrdfasdfadf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtrdfasdfadf.setText("- Sử dụng thẻ sinh viên làm tài khoản đăng nhập"
				+ "\n- Sinh viên đăng kí tài khoản thông qua giáo viên"
				+"\n- Chỉ khi giáo viên sửa thông tin sinh viên mới được thi lại");
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textArea.setText("- Phạm Văn Sơn"+"\n- Nguyễn Đăng Sách"+"\n- Cao Thị Thoa");
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textArea_1.setText("- Đỗ Thị Thu Hiền" + "\n- Vũ Thị Hương");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(51)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNhmPhtTrin)
								.addComponent(lblLu)
								.addComponent(lblHngnCh)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblMtT)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblThcHin))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblPhinBn)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblTnPhnMm)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblPhnMmThi))
								.addComponent(txtrdfasdfadf, GroupLayout.PREFERRED_SIZE, 432, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblCopyr)
							.addPreferredGap(ComponentPlacement.RELATED, 221, Short.MAX_VALUE)
							.addComponent(lblComanyFithou)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTnPhnMm)
						.addComponent(lblPhnMmThi))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPhinBn)
						.addComponent(label))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMtT)
						.addComponent(lblThcHin))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblHngnCh)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblLu)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtrdfasdfadf, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(lblNhmPhtTrin)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
						.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCopyr)
						.addComponent(lblComanyFithou)))
		);
		contentPanel.setLayout(gl_contentPanel);
	}
}
