package kh.project.geneJar.manufacture.list;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Biosimilar extends JFrame implements Manufacture {

	
	public String information() {
		
		setTitle("바이오 시밀러"); // 창 제목 설정
        setSize(800, 600);		// 창 사이즈 설정
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		// 종료 방법 선택
        ImageIcon imageIcon = new ImageIcon("Biosimilar.jpg");	// 불러올 이미지 검색
        JLabel imageLabel = new JLabel(imageIcon);				// 이미지를 JFrame에서 사용할수 있는 형식으로
        add(imageLabel);										// JFrame에 변환한 이미지 출력

        // JFrame을 중앙에 배치 = null
        setLocationRelativeTo(null);		// JFrame 출력 위치
        
        SwingUtilities.invokeLater(() -> {	// SwingUtilities 의 메소드 이용하여 화면을 보이도록 설정
            setVisible(true);					
        });
        
		
		return "특허가 만료된 오리지널 의약품과 품질 및 비임상, 임상 비교 동등성이 입증된 "
				+ "복제약으로 성분을 비슷하게 제작하나 분자구조를 변형하여 더 효과적으로 만든 의약품이다.";

	}

}
