package com.kh.timer;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class BreakTime extends JFrame {

	public void timer() {

		JFrame frame = new JFrame("!!!!!!!!!!!!!쉬는시간!!!!!!!!!!!!!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);

		// JLabel 생성 (정각까지 남은 시간 표시)
		JLabel timeLabel = new JLabel("쉬는 시간!!!: ", SwingConstants.CENTER);
		timeLabel.setFont(new Font("SansSerif", Font.BOLD, 40));
/*   글씨체 목록
Serif		
SansSerif
Monospaced
Dialog
DialogInput*/ 
		// JTextArea 생성 (출력 영역)
		JTextArea textArea = new JTextArea(10, 30);
		textArea.setEditable(false);
		JTextArea textArea2 = new JTextArea(5, 10);
		textArea.setEditable(false);
		JTextArea textArea3 = new JTextArea(5, 10);
		textArea.setEditable(false);

		// JTextField 생성 (입력 필드)
		JTextField textField = new JTextField(30);

		// JPanel 생성 및 컴포넌트 추가
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(timeLabel, BorderLayout.NORTH);
		panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
		panel.add(new JScrollPane(textArea2), BorderLayout.WEST);
		panel.add(new JScrollPane(textArea3), BorderLayout.EAST);
		panel.add(textField, BorderLayout.SOUTH);

		frame.add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		// 타이머 설정 (정각까지 남은 시간 업데이트)
		Timer timer = new Timer(true);
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				LocalDateTime now = LocalDateTime.now();
				LocalDateTime nextHour = now.plusHours(0).withMinute(49).withSecond(30).withNano(0);
				Duration duration = Duration.between(now, nextHour);
				long hours = duration.toHours();
				long minutes = duration.toMinutes() % 60;
				long seconds = duration.getSeconds() % 60;
	
				String formattedDuration = String.format("%02d시간 %02d분 %02d초", hours, minutes, seconds);
				SwingUtilities.invokeLater(() -> timeLabel.setText(
						"(( >-<) >-<) >-<))        " + "쉬는시간 까지! : " + formattedDuration + "        ( ›´0`‹)🚬 후우-"));
			}
		}, 0, 1000);
		

		// ActionListener 추가 (텍스트 입력 처리)
		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputText = textField.getText();
				if (!inputText.isEmpty()) {
					textArea.append(inputText + "\n");
					textField.setText("");
				}
			}
		});

		textArea2.append("┏┯┯┯┯┯┯┯┓\n"
					   + "┃││^││^││┃\n"
				       + "┃│( >Д<)│┃\n"
				       + "┃││ф   ф│┃\n"
				       + "┃││U U││┃\n"
				       + "┗┷┷┷┷┷┷┷┛\n"
				       + " 살려주세요!!!!!!!");
		textArea3.append("  　｡・｡∧_∧｡・｡\n"
					   + " ｡ﾟ    　(´Д｀)　 ﾟ｡\n"
					   + " 　 　    U U)o\n"
					   + " 　 　      'ｰ'ｰ'\n"
					   + ""
					   + "　　 ∧＿∧\n"
					   + "　⊂(#`･ω･)  때려쳐!\n"
					   + "　　/　　ﾉ∪\n"
					   + "　  し―-J |‖\n"
					   + "　 　　　/⌒/⌒/\n"
					   + "   　　　  ⌒^⌒\\n"
					   + "");
	}

}
