package kh.project.geneJar.network;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Chat extends JFrame implements Runnable {

	private Scanner sc = new Scanner(System.in);
	private int port = 8080;
	private PrintWriter pw = null;
	private BufferedReader stream = null;
	private ServerSocket server = null;
		
	public void run() {
		
		setTitle("Chat JDP");
		setSize(400, 20);
		JTextField textField = new JTextField(40);
		JTextArea textArea = new JTextArea(40, 20);
		
		textArea.setEditable(false);
        
		getContentPane().add(textField, BorderLayout.SOUTH);
		getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);
		pack();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pw != null) {
					String ans = textField.getText();
					textArea.append("전송 : " + ans + "\n");
					pw.println(ans);
					pw.flush();
					textField.setText("");
				}
			}
		});

		// JFrame을 중앙에 배치
		setLocationRelativeTo(null);

		SwingUtilities.invokeLater(() -> {
			setVisible(true);
		});

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
