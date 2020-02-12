package memory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;

public class Memory {
	static ArrayList<Things> thingsList = new ArrayList<>();
	static Visiable vis;
	static boolean isReminderExsit = false;
	static boolean isBianliExsit = false;
	static boolean isFinderExsit=false;
	static String projectName; 

	public static void main(String[] args) throws IOException, ParseException {

		
		vis = new Visiable();
		firstCheck();
		
		Tools.readData();
		//System.out.println(projectName);
		System.out.println("窗口建立完成");

		Date date = new Date();

		Timer t = new Timer();

		TimeForReminder tfr = new TimeForReminder();

		// 从date开始,每隔6个小时,检测一次是否需要复习tfr 21600000
		t.scheduleAtFixedRate(tfr, date, 10800000);
	}
	
	public static void firstCheck() throws IOException{
		
		File file = new File("name.txt");
		if(!file.exists()){
			new ProjectName();
		}else{
			BufferedReader bufferedReader = new BufferedReader(new FileReader("name.txt"));
			vis.setTitle(bufferedReader.readLine());
			bufferedReader.close();
		}
		
		
	}
}
