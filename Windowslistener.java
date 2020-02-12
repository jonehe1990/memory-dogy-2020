package memory;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Windowslistener implements WindowListener {
	String name;

	public Windowslistener(String name) {
		super();
		this.name = name;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("响应窗口1事件");
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

		System.out.println("响应窗口2事件");
		if ("reminder".equals(name)) {
			System.out.println("Memory.isReminderExsit=false;");
			Memory.isReminderExsit = false;
		}

		if ("bianli".equals(name)) {
			System.out.println("Memory.isBianliExsit=false;");
			Memory.isBianliExsit = false;
		}
		
		if("finder".equals(name)){
			System.out.println("Memory.isFinderExsit=false;");
			Memory.isFinderExsit = false;
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("响应窗口3事件");
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("响应窗口3事件");
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("响应窗口4事件");
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("响应窗口5事件");
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("响应窗口6事件");
	}

}
