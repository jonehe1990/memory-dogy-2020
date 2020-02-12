package memory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Reminder extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static ArrayList<Things> reminderList = new ArrayList<Things>();

	int IndexCount = 0;

	boolean ifRemenber = true;

	String notes = null;
	JPanel mb1;
	JPanel mb2;
	JPanel mb3;

	JLabel label1 = new JLabel();
	JTextArea label2 = new JTextArea();

	JButton remenberButton;
	JButton forgetButton;
	JButton nextButton;

	public void reminder() {
		// 设置:窗口名词,窗口大小,窗口位置,添加按钮
		// 设置关闭释放内存,
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Windowslistener windowslistener = new Windowslistener("reminder");

		this.addWindowListener(windowslistener);

		// 设置前端显示
		this.setAlwaysOnTop(true);

		label2.setLineWrap(true);
		this.setTitle("下面知识点应该复习啦");
		this.setSize(400, 150);
		this.setLocation(Memory.vis.getX(), Memory.vis.getY() - 10);
		remenberButton = new JButton("还记得");
		remenberButton.addActionListener(this);
		remenberButton.setActionCommand("记得");

		forgetButton = new JButton("忘记了");
		forgetButton.addActionListener(this);
		forgetButton.setActionCommand("忘了");

		nextButton = new JButton("下一个");
		nextButton.setVisible(false);
		nextButton.addActionListener(this);
		nextButton.setActionCommand("下一个");

		label1.setText(reminderList.get(IndexCount).getQuestion());

		label2.setText("");

		label2.setVisible(false);

		showContens();
		// 设置关闭释放内存,
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean flag = true;

		if ("记得".equals(e.getActionCommand()) && flag) {

			ifRemenber = true;

			remenberButton.setVisible(false);
			forgetButton.setVisible(false);
			nextButton.setVisible(true);

			label2.setText(reminderList.get(IndexCount).getAnswer() + "\nvery good!");
			label2.setVisible(true);

		} else if ("忘了".equals(e.getActionCommand()) && flag) {

			ifRemenber = false;

			remenberButton.setVisible(false);
			forgetButton.setVisible(false);
			nextButton.setVisible(true);

			label2.setText(reminderList.get(IndexCount).getAnswer());
			label2.setVisible(true);

		} else if ("下一个".equals(e.getActionCommand()) && flag) {

			if (ifRemenber) {
				Things thing = reminderList.get(IndexCount);

				thing.setTimes(thing.getTimes() + 1);
				int index = Memory.thingsList.indexOf(thing);
				Memory.thingsList.set(index, thing);

				// 输出标记
				System.out.println("角标为" + IndexCount + "事件为[" + thing + "]");

				try {
					Tools.writeData();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			IndexCount++;
			System.out.println("当前角标为" + IndexCount + "  " + "回忆列表集合长度为" + reminderList.size() + "进入判断");
			if (IndexCount >= reminderList.size()) {

				System.out.println("count大于或等于集合长度了");
				

				remenberButton.setVisible(false);
				forgetButton.setVisible(false);
				nextButton.setVisible(false);
				
				Tools.tip("复习完成");
				flag = false;

			} else {

				System.out.println("count小于集合长度");
				remenberButton.setVisible(true);
				forgetButton.setVisible(true);
				nextButton.setVisible(false);

				label1.setText(reminderList.get(IndexCount).getQuestion());
				label2.setText("");
				label2.setVisible(false);
			}

		}
	}

	private void showContens() {
		mb1 = new JPanel();

		Color bg = new Color(12638681);
		mb2 = new JPanel();

		mb3 = new JPanel(new BorderLayout());
		mb3.setBackground(bg);

		mb1.add(label1);
		mb3.add(label2);

		mb2.add(remenberButton);
		mb2.add(forgetButton);
		mb2.add(nextButton);

		this.add(mb1, BorderLayout.NORTH);
		this.add(mb2, BorderLayout.SOUTH);
		this.add(mb3, BorderLayout.CENTER);

	}

}
