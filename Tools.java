package memory;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Tools {

	/**
	 * 增加数据
	 */
	public static void addDate(String question, String answer) throws IOException {

		ArrayList<Things> thingsList = Memory.thingsList;
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String today = simpleDateFormat.format(date);

		int id = thingsList.size() + 1;
		Things things = new Things(id, today, question, answer, 1);
		boolean flag = true;
		for (Things thing : thingsList) {
			if (thing.equals(things))
				flag = false;
		}
		if (flag) {
			thingsList.add(things);
		}

		writeData();
	}

	/**
	 * 写数据到文件中
	 */
	public static void writeData() throws IOException {

		ArrayList<Things> thingsList = Memory.thingsList;

		BufferedWriter bw = new BufferedWriter(new FileWriter("memory.txt"));

		for (Things things : thingsList) {
			bw.write(things.toString());
			bw.newLine();
		}
		bw.close();
	}

	/**
	 * 读取到集合中
	 */
	public static void readData() throws IOException {
		File filePath = new File("memory.txt");
		
		if (!filePath.exists()) {
			System.out.println("请输入记忆项目名称");//TODO
			filePath.createNewFile();
			
		}

		// new ReaderWriter().reader();
		ArrayList<Things> thingsList = Memory.thingsList;
		BufferedReader bufferedReader = new BufferedReader(new FileReader("memory.txt"));

		String line;
		while ((line = bufferedReader.readLine()) != null) {
			String[] strArr = line.split("#");
			System.out.println(strArr[0]+"   ");
			System.out.println(strArr[4]);
			Things things = new Things(Integer.parseInt(strArr[0]), strArr[1], strArr[2], strArr[3],
					Integer.parseInt(strArr[4]));
			thingsList.add(things);
		}
		bufferedReader.close();
	}

	/**
	 * 判断ThingsList集合中的things是否需要复习,若需要则开始复习
	 * 
	 * @throws ParseException
	 */
	public static void reminder() throws ParseException {

		if (!Memory.isReminderExsit) {
			ArrayList<Things> thingsList = Memory.thingsList;

			Reminder.reminderList.clear();

			boolean flag = true;

			for (Things thing : thingsList) {
				if (judge(thing)) {
					// System.out.println("添加到");
					flag = false;
					Reminder.reminderList.add(thing);
				}
			}

			if (flag) {
				// Tools.tip("没有需要复习的点");
			} else {
				System.out.println("存在reminder窗口");
				Memory.isReminderExsit = true;
				Collections.shuffle(Reminder.reminderList);
				new Reminder().reminder();// 开始复习
			}
		}

	}

	/**
	 * 判断thing是否需要复习
	 */
	private static boolean judge(Things thing) throws ParseException {

		if (thing.getTimes() <= 6) {

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date dateFrom = simpleDateFormat.parse(thing.getDate());

			int timeInterval = 1;
			for (int i = 1; i < thing.getTimes(); i++) {
				timeInterval += i;
			}

			Calendar calendar = Calendar.getInstance();

			calendar.add(Calendar.DATE, -timeInterval);

			Date dateTo = calendar.getTime();

			if (dateTo.getTime() > dateFrom.getTime()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 提示文字弹框
	 */
	public static void tip(String str) {
		final JFrame frame = new JFrame();
		frame.setTitle("提示");
		frame.setSize(200, 100);
		frame.setLocation(Memory.vis.getX() + 100, Memory.vis.getY() + 15);
		
		frame.setAlwaysOnTop(true);

		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();

		JLabel lable = new JLabel(str);
		JButton button = new JButton();
		button.setText("关闭");

		Timer t = new Timer();

		t.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				frame.dispose();
			}
		}, 1500);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		button.registerKeyboardAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}

		}, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		// 设置:窗口名词,窗口大小,窗口位置,添加按钮

		jp1.add(lable);
		jp2.add(button);

		// frame.setLayout(new FlowLayout());

		frame.add(jp1, BorderLayout.CENTER);
		frame.add(jp2, BorderLayout.SOUTH);
		frame.setVisible(true);
	}
}
