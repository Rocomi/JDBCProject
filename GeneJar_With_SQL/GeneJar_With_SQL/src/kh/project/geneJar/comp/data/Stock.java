package kh.project.geneJar.comp.data;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Stock extends JFrame{	//주가 정보
	
	public void stockinfo() {
		setTitle("주가 정보");
		setSize(700, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ImageIcon imageIcon = new ImageIcon("Stock.jpg");
		JLabel imageLabel = new JLabel(imageIcon);
		add(imageLabel);

		// JFrame을 중앙에 배치
		setLocationRelativeTo(null);

		SwingUtilities.invokeLater(() -> {
			setVisible(true);
		});
	}
}
