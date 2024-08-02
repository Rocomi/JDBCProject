package com.kh.timer;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;
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

public class BreakTime_Test {
	
	private Scanner sc = new Scanner(System.in);
	private int port = 8080;
	private PrintWriter pw = null;
	private BufferedReader stream = null;
	private ServerSocket server = null;

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


		// JPanel 생성 및 컴포넌트 추가
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(timeLabel, BorderLayout.NORTH);
		panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
		panel.add(new JScrollPane(textArea2), BorderLayout.WEST);
		panel.add(new JScrollPane(textArea3), BorderLayout.EAST);

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
				LocalDateTime nextHour = now.plusHours(0).withMinute(50).withSecond(0).withNano(0);
				Duration duration = Duration.between(now, nextHour);
				long hours = duration.toHours();
				long minutes = duration.toMinutes() % 60;
				long seconds = duration.getSeconds() % 60;
	
				String formattedDuration = String.format("%02d시간 %02d분 %02d초", hours, minutes, seconds);
				SwingUtilities.invokeLater(() -> timeLabel.setText(
						"(( >-<) >-<) >-<))        " + "쉬는시간 까지! : " + formattedDuration + "        ( ›´0`‹)🚬 후우-"));
			}
		}, 0, 1000);
		


		

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
		
		while (true) {
			try {

				server = new ServerSocket(port);

				textArea.append(".......요청 대기중.......\n");

				Socket socket = server.accept();
				textArea.append("사용자 연결\n\n");

				stream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				pw = new PrintWriter(socket.getOutputStream());

				while (true) {
					String message = stream.readLine(); // 한줄씩 입력 받기

					if (message == null) {
						textArea.append("상대가 채팅을 종료하였습니다.\n\n");
						break;
					}
					if(socket.getInetAddress().getHostAddress().equals("192.168.10.17")) {
						textArea.append("기다운쟁이님 : " + message + "\n");
					}else {
						textArea.append("고객님 : " + message + "\n");
					}
					
					
				}

			} catch (SocketException e) {
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (pw != null)
						pw.close();
					if (stream != null)
						stream.close();
					if (server != null)
						server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


}
