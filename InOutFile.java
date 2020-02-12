package memory;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class InOutFile extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public InOutFile() {
		
		this.setTitle("导入/导出");
		this.setSize(200, 65);
		this.setLocation(Memory.vis.getX() + 100, Memory.vis.getY() + 15);

		this.setAlwaysOnTop(true);
		JPanel jp1 = new JPanel();

		JButton inButton = new JButton("导入");
		JButton outButton = new JButton("导出");

		inButton.addActionListener(this);
		inButton.setActionCommand("导入");

		outButton.addActionListener(this);
		outButton.setActionCommand("导出");

		// 设置:窗口名词,窗口大小,窗口位置,添加按钮

		jp1.add(inButton);
		jp1.add(outButton);

		// frame.setLayout(new FlowLayout());

		this.add(jp1, BorderLayout.CENTER);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if ("导出".equals(e.getActionCommand())) {
			javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
			chooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
			int returnVal = chooser.showOpenDialog(null);
			if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
				System.out.println("You chose to open this file: " + chooser.getSelectedFile().getPath());
				String path = chooser.getSelectedFile().getAbsolutePath() + "/备份.txt";

				try {
					BufferedReader br = new BufferedReader(new FileReader("memory.txt"));
					BufferedWriter bw = new BufferedWriter(new FileWriter(path));
					System.out.println(path);
					String line;
					while ((line = br.readLine()) != null) {
						bw.write(line);
						bw.newLine();
					}
					br.close();
					bw.close();
					Tools.tip("导出成功");

				} catch (Exception ex) {

				}
			}
		} else {
			javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
			chooser.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);
			int returnVal = chooser.showOpenDialog(null);
			if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
				System.out.println("You chose to open this file: " + chooser.getSelectedFile().getPath());
				String path = chooser.getSelectedFile().getAbsolutePath();

				if (!path.endsWith("备份.txt")) {
					Tools.tip("文件名称或类型有误");
					return;
				}

				try {
					BufferedReader br = new BufferedReader(new FileReader(path));
					BufferedWriter bw = new BufferedWriter(new FileWriter("memory.txt"));
					String line;
					String regex = "\\d+[#][\\d]{4}[-][\\d]{2}[-][\\d]{2}[#][^#]+[#][^#]+[#]\\d+";

					while ((line = br.readLine()) != null) {
						if (!line.matches(regex)) {
							br.close();
							bw.close();

							Tools.tip("文件内容有误");
							return;
						}
					}

					while ((line = br.readLine()) != null) {

						bw.write(line);
						bw.newLine();
					}
					br.close();
					bw.close();

					Tools.tip("导入成功");
				} catch (Exception ex) {
					Tools.tip("文件导入出错");
				}

			}

		}
	}
}
