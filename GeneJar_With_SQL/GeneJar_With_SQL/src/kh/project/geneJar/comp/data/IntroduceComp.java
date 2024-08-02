package kh.project.geneJar.comp.data;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class IntroduceComp extends JFrame {	// 회사소개
	public void introduce() {
		setTitle("회사 소개");
		setSize(1200, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ImageIcon imageIcon = new ImageIcon("Introduce.jpg");
		JLabel imageLabel = new JLabel(imageIcon);
		add(imageLabel);

		// JFrame을 중앙에 배치
		setLocationRelativeTo(null);

		SwingUtilities.invokeLater(() -> {
			setVisible(true);
		});
	}
}
