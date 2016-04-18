package Design;
import javax.swing.JButton;
import javax.swing.JFrame;
import Admin.ad_logined;

public class Main {
	public static Login login;
	static Logined logined;
	static Starting start;
	static ad_logined ad_logined;
	static String msv, name, thoigian;
	static Information info;
	static Thread t1,t2,t3,t4;
	
	public static void main(String[] args) {
		System.out.println("Main");
		Login();
	}
	
	public static void Login(){
		System.out.println("Login");
		login = new Login();
		t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				Table_Scroll.getData();
				login.setVisible(true);
			}
		});
		t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				Login.Text_messenger();
			}
		});
		t1.start();
		t2.start();
	}
	
	// Danh sách các học sinh đã có tài khoản
	public static void List_Account(JFrame frame, Table_Scroll tbl_Scr){
		System.out.println("List_Account");
		frame.add(tbl_Scr);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	// Cửa sổ đăng nhập
	public static void Logined(){
		System.out.println("Logined");
		logined = new Logined();
		login.setVisible(false);
		logined.setName(name);
		logined.setVisible(true);
	}
	
	// Cửa sổ thi
	public static void Starting(){
		System.out.println("Staring");
		start = new Starting();
		t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				logined.setVisible(false);
				start.setVisible(true);
				start.setDefaultCloseOperation(0);
			}
		});
		
		t4 = new Thread(new Runnable() {
			@Override
			public void run() {
				start.setTime();
			}
		});
		t3.start(); 
		t4.start();
	}
	
	public static void Ad_logined(){
		System.out.println("ad_logined");
		ad_logined = new ad_logined();
		login.setVisible(false);
		ad_logined.setVisible(true);
	}
	
	//Thông tin phần mềm và tác giả
	public static void Information(){
		info = new Information();
		info.setVisible(true);
	}
	
	// Thoát hoàn toàn các cửa sổ trả lại tài nguyên cho hệ thống ngắt tất cả các kết nối tới dữ liệu
	public static void Exit_app(){
		System.out.println("Exit_app");
		login.exit();
		logined.exit();
		start.exit();
		ad_logined.exit();
		info.exit();
	}
	
}
