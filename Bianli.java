package memory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Bianli extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	static ArrayList<Things> reminderList = Memory.thingsList;
	static Random r = new Random();

	LinkedHashSet<Integer> indexSet = new LinkedHashSet<>();

	ArrayList<Integer> indexList = new ArrayList<Integer>();

	int index = 0;

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

	{
		while (indexSet.size() != reminderList.size()) {
			indexSet.add(r.nextInt(reminderList.size()));
		}
		indexList.addAll(indexSet);
		System.out.println("将按下面角标顺序遍历知识点集合" + indexList);
	}

	public Bianli() {
		Windowslistener bianliListener = new Windowslistener("bianli");
		this.addWindowListener(bianliListener);

		int index = 0;
		// 设置:窗口名词,窗口大小,窗口位置,添加按钮
		// 设置关闭释放内存,
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		label2.setLineWrap(true);

		this.setAlwaysOnTop(true);
		this.setTitle("头脑风暴");
		this.setSize(400, 150);
		this.setLocation(Memory.vis.getX(), Memory.vis.getY() + 137);
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

		label1.setText(reminderList.get(indexList.get(index++)).getQuestion());
		label2.setText("");
		label2.setVisible(false);

		showContens();
		// 设置关闭释放内存,
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);

		System.out.println("头脑风暴窗口建立完成");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean flag = true;

		if ("记得".equals(e.getActionCommand()) && flag) {
			remenberButton.setVisible(false);
			forgetButton.setVisible(false);
			nextButton.setVisible(true);

			label2.setText(reminderList.get(indexList.get(index)).getAnswer() + "\nvery good!");
			label2.setVisible(true);

		} else if ("忘了".equals(e.getActionCommand()) && flag) {
			remenberButton.setVisible(false);
			forgetButton.setVisible(false);
			nextButton.setVisible(true);

			label2.setText(reminderList.get(indexList.get(index)).getAnswer());
			label2.setVisible(true);

		} else if ("下一个".equals(e.getActionCommand()) && flag) {
			System.out.println("点击了下一个," + "目前角标为" + (index) + ",遍历集合大小为" + indexList.size() + "还剩复习条数"
					+ (indexList.size() - 1 - index));
			index++;
			if (index >= indexList.size()) {
				System.out.println("index大于或等于集合长度了");

				remenberButton.setVisible(false);
				forgetButton.setVisible(false);
				nextButton.setVisible(false);
				Tools.tip("头脑风暴模式完成");

			} else {
				remenberButton.setVisible(true);
				forgetButton.setVisible(true);
				nextButton.setVisible(false);

				label1.setText(reminderList.get(indexList.get(index)).getQuestion());
				label2.setText("");
				label2.setVisible(false);
			}
		}
	}

	private void showContens() {
		
		mb1 = new JPanel();
		mb2 = new JPanel();
		mb3 = new JPanel(new BorderLayout());

		Color bg = new Color(12638681);
		mb3.setBackground(bg);

		mb1.add(label1);
		mb3.add(label2, BorderLayout.CENTER);

		mb2.add(remenberButton);
		mb2.add(forgetButton);
		mb2.add(nextButton);

		this.add(mb1, BorderLayout.NORTH);
		this.add(mb2, BorderLayout.SOUTH);
		this.add(mb3, BorderLayout.CENTER);

	}

}
