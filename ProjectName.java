package memory;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class ProjectName {

	final JFrame frame = new JFrame();
	JPanel jp1 = new JPanel();
	JPanel jp2 = new JPanel();
	JLabel lable ;
	JTextField text;
	JButton button;
	
	public ProjectName() {
		
		frame.setTitle("项目名称");

		frame.setSize(200, 100);

		frame.setLocation(Memory.vis.getX() + 100, Memory.vis.getY() + 0);

		frame.setAlwaysOnTop(true);

		lable = new JLabel("名称");
		text=new JTextField(10);

		
		
		button = new JButton();
		button.setText("提交");

		//Timer t = new Timer();

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if("".equals(text.getText())){
					Tools.tip("输入为空");
					return;
				}
				
				
				try {
					writeName(text.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(text.getText());
				Memory.vis.setTitle(text.getText());
				
				frame.dispose();
			}
		});

		button.registerKeyboardAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if("".equals(text.getText())){
					Tools.tip("输入为空");
					return;
				}
				try {
					writeName(text.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Memory.vis.setTitle(text.getText());
				//TODO
				//为项目添加名称
				frame.dispose();
			}

		}, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		// 设置:窗口名词,窗口大小,窗口位置,添加按钮

		jp1.add(lable);
		jp1.add(text);
		jp2.add(button);

		// frame.setLayout(new FlowLayout());

		frame.add(jp1, BorderLayout.CENTER);
		frame.add(jp2, BorderLayout.SOUTH);
		frame.setVisible(true);
	}
	
	public void writeName(String name) throws IOException{
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("name.txt"));
		bufferedWriter.write(name);
		bufferedWriter.close();
	}
}
