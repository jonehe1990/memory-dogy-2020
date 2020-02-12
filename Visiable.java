package memory;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

//窗体类

public class Visiable extends JFrame implements ActionListener {

	/**
	 * 
	 */
	static int x;
	static int y;

	private static final long serialVersionUID = 1L;
	// 添加面板
	JPanel mb1;
	JPanel mb2;
	JPanel mb3;

	// 添加一般标签:
	JLabel label1;
	JLabel label2;

	// 添加文本框:
	JTextField question;
	JTextField answer;

	// 添加密码框:
	// JPasswordField(10)

	// static JButton upButton=new JButton("上");
	// static JButton downButton=new JButton("下");
	static JButton checkButton=new JButton("查找");
	static JButton InOutButton = new JButton("导出/导入");
	static JButton button = new JButton("提交");
	static JButton bianliButton = new JButton("头脑风暴");

	public Visiable() {
		System.out.println("主窗口");
		question = new JTextField(20);
		answer = new JTextField(20);

		// 设置:窗口名词,窗口大小,窗口位置,添加按钮
		this.setTitle("MemorySoftware");
		this.setSize(400, 135);
		this.setLocation(500, 300);
		this.setAlwaysOnTop(true);

		// 设置关闭释放内存,
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 设置窗口不允许变化
		this.setResizable(false);

		// 由于JAVAm默认是边界布局管理器,但是要使用流式布局管理器
		// this.setLayout(new FlowLayout());

		// 网格布局
		// this.setLayout(new GridLayout(3,1));//3行1列

		// 新建三个面板,两个标签
		mb1 = new JPanel();
		mb2 = new JPanel();
		mb3 = new JPanel();

		// 给按钮加标识
		InOutButton.setActionCommand("inout");
		button.setActionCommand("提交");
		bianliButton.setActionCommand("遍历");
		checkButton.setActionCommand("查找");

		button.registerKeyboardAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if ("".equals(question.getText()) || "".equals(answer.getText())) {
					Tools.tip("请确保非空输入");
				} else {
					if(question.getText().indexOf('#')!=-1||answer.getText().indexOf('#')!=-1){
						Tools.tip("输入内容包含#号,请用'井'代替");
						return;
					}
					try {
						Tools.addDate(question.getText(), answer.getText());
						question.setText("");
						answer.setText("");
						Tools.tip("录入成功");
					} catch (IOException e1) {

					}
				}
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

		label1 = new JLabel("问题提纲");
		label2 = new JLabel("答案摘要");

		button.addActionListener(this);
		InOutButton.addActionListener(this);
		bianliButton.addActionListener(this);
		checkButton.addActionListener(this);
		
		// 将按钮添加到不同的面板上
		mb1.add(label1);
		mb1.add(question);

		mb2.add(label2);
		mb2.add(answer);

		mb3.add(button);
		mb3.add(checkButton);
		mb3.add(bianliButton);
		mb3.add(InOutButton);

		// 把面板添加到窗体上

		// this.add(button,BorderLayout.CENTER);
		this.add(mb1, BorderLayout.NORTH);
		this.add(mb2, BorderLayout.CENTER);
		this.add(mb3, BorderLayout.SOUTH);
		// this.add(leftButton,BorderLayout.WEST);
		// this.add(rightButton,BorderLayout.EAST);

		// 设置窗体可见
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if ("提交".equals(e.getActionCommand()) && "".equals(question.getText())
				|| "提交".equals(e.getActionCommand()) && "".equals(answer.getText())) {
			Tools.tip("请确保非空输入");
		} else if ("提交".equals(e.getActionCommand())) {
			if(question.getText().indexOf('#')!=-1||answer.getText().indexOf('#')!=-1){
				Tools.tip("输入内容包含#号,请用'井'代替");
				return;
			}
			
			try {
				Tools.addDate(question.getText(), answer.getText());
				question.setText("");
				answer.setText("");
				Tools.tip("录入成功");
			} catch (IOException e1) {

			}
		}

		if ("inout".equals(e.getActionCommand())) {
			System.out.println("ok");
			new InOutFile();

		}
		if ("遍历".equals(e.getActionCommand())) {
			if (Memory.thingsList.size() != 0) {
				if (!Memory.isBianliExsit) {
					Memory.isBianliExsit = true;
					System.out.println("开始头脑风暴");
					new Bianli();
				} else {
					Tools.tip("头脑风暴窗口已经存在");
				}
			} else {
				Tools.tip("请先添加数据");
			}

		}
		
		if("查找".equals(e.getActionCommand())){
			if (!Memory.isFinderExsit) {
				Memory.isFinderExsit = true;
				System.out.println("开始头脑风暴");
				new ThingFInder();
			} else {
				Tools.tip("查找窗口已经存在");
			}
		}

	}

}
