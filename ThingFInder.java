package memory;

import java.awt.BorderLayout;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class ThingFInder extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel mb1 = new JPanel();
	JPanel mb2 = new JPanel();

	JTextField input = new JTextField(20);

	JButton finder = new JButton("查找");

	// 添加文本框:
	JTextArea result = new JTextArea();

	public ThingFInder() {
		result.setLineWrap(true);
		result.setEditable(false);
		this.setMenuBar(new MenuBar());
		this.setAlwaysOnTop(true);
		

		JScrollPane jScrollPane = new JScrollPane(result);

		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		this.setTitle("查找");
		this.setSize(400, 200);
		this.setLocation(Memory.vis.getX(), Memory.vis.getY() - 10);
		this.setResizable(false);

		finder.addActionListener(this);
		finder.setActionCommand("查找");
		finder.registerKeyboardAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				finder();
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

		Windowslistener windowslistener = new Windowslistener("finder");
		this.addWindowListener(windowslistener);

		mb1.add(input);
		mb1.add(finder);

		mb2.setLayout(new BorderLayout());

		this.add(mb1, BorderLayout.NORTH);
		this.add(jScrollPane);

		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		finder();
	}

	public void finder() {
		// TODO Auto-generated method stub

		result.setText("");

		String keyWord = input.getText();

		if (keyWord == null) {
			Tools.tip("查找内容为空");
			return;
		}

		ArrayList<Things> findList = new ArrayList<>();

		for (Things thing : Memory.thingsList) {
			if (thing.getQuestion().contains(keyWord)) {
				findList.add(thing);
			}
		}

		if (findList.size() == 0) {
			Tools.tip("未查到相关内容");
			return;
		}

		for (int i = 0; i < findList.size(); i++) {
			Things thing = findList.get(i);
			result.append("问题:" + thing.getQuestion() + "\n");
			result.append("答案:" + thing.getAnswer() + "\n");
			result.append("==============================\n");
		}
	}
}
