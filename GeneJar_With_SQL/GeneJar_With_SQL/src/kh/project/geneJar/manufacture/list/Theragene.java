package kh.project.geneJar.manufacture.list;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Theragene extends JFrame implements Manufacture {

	public String information() {
		
		setTitle("유전자 치료제");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon imageIcon = new ImageIcon("Theragene.jpg");
        JLabel imageLabel = new JLabel(imageIcon);
        add(imageLabel);

        // JFrame을 중앙에 배치
        setLocationRelativeTo(null);
        
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
        });
        
		return "유전물질을 포함하는 의약품으로, 유전자를 분자 수준에서 "
				+ "교정할 수 있어 단일 유전자 질환 및 암 등의 치료와 예방에 활용 가능성이 높은 약품";
	} // 유전자 치료제, CAR T cell, 등

}
