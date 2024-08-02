package kh.project.geneJar.manufacture.list;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class ChemicalMedicine extends JFrame implements Manufacture  {

	public String information() {
		
		setTitle("화학 의약품");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon imageIcon = new ImageIcon("ChemicalMedicine.jpg");
        JLabel imageLabel = new JLabel(imageIcon);
        add(imageLabel);

        // JFrame을 중앙에 배치
        setLocationRelativeTo(null);
        
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
        });
        
		return "특정 환자군을 타깃하지 않고 다수의 환자에게 폭 넓게 쓰일 수 있는 화학물질"
				+ "로 만들어진 의약품, 대부분이 처방전 없이 구매 가능하며 경구투약 용이";
	}	// 화학의약품

}
