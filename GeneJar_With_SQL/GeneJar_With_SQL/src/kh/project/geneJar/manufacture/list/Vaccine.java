package kh.project.geneJar.manufacture.list;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;


public class Vaccine extends JFrame implements Manufacture {


	public String information() {
		
		setTitle("백신");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon imageIcon = new ImageIcon("Vaccine.jpg");
        JLabel imageLabel = new JLabel(imageIcon);
        add(imageLabel);

        // JFrame을 중앙에 배치
        setLocationRelativeTo(null);
        
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
        });
        
        
		return "특정 질병에 대한 면역력을 강화시킬 목적으로 투여하는 의약품으로"
				+ "항원 단백질 또는 미생물체를 포함하여 체내에서 면역반응을 유도하여 자가적으로 항체생성.";
	}	// 백신

}
