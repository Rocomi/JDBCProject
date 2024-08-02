package kh.project.geneJar.comp.data;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Map extends JFrame {
	
	public void map() {
		setTitle("오시는 길");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ImageIcon imageIcon = new ImageIcon("map.jpg");
		JLabel imageLabel = new JLabel(imageIcon);
		add(imageLabel);

		// JFrame을 중앙에 배치
		setLocationRelativeTo(null);

		SwingUtilities.invokeLater(() -> {
			setVisible(true);
		});
		System.out.println("송파구 오금로 22길 7-3, 진짜빌딩");
	}
}
