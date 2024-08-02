package kh.project.geneJar.published;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class ChemicalMedicinePub extends JFrame implements Published {
	
	public void license() {
		setTitle("화학 의약품");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon imageIcon = new ImageIcon("ChemicalLicense.jpg");
        JLabel imageLabel = new JLabel(imageIcon);
        add(imageLabel);

        // JFrame을 중앙에 배치
        setLocationRelativeTo(null);
        
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
        });
	}

	public void paper() {
		setTitle("화학 의약품");
        setSize(750, 900);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon imageIcon = new ImageIcon("chemicalMedicinePaper.jpg");
        JLabel imageLabel = new JLabel(imageIcon);
        add(imageLabel);

        // JFrame을 중앙에 배치
        setLocationRelativeTo(null);
        
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
        });
	}


}
