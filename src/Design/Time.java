package Design;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Time {

	static int h,m,s; 
	static String time, date;
	
	public Time() {
		// TODO Auto-generated constructor stub
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf_time = new SimpleDateFormat("hh:mm:ss");
		time = sdf_time.format(cal.getTime());
		SimpleDateFormat sdf_date = new SimpleDateFormat("dd/MM/yyyy");
		date = sdf_date.format(cal.getTime());
		
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new Time();
//	}

	 String getHour(){ return time.substring(0,2);} // lay gio
	 String getMinute(){ return time.substring(3,5);} // lay phut
	 String getMinisec(){ return time.substring(6,8);} // lay giay
}
