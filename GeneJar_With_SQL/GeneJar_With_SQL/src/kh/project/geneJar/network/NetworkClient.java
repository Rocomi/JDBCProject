package kh.project.geneJar.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NetworkClient {
	Scanner sc = new Scanner(System.in);

	public void chat() {

		InetAddress iNet;
		try {
			iNet = InetAddress.getLocalHost();

			String ip = iNet.getHostAddress();
			int port = 8080;
			int port2 = 3000;

			Socket socket = null;
			BufferedReader br = null;
			PrintWriter pw = null;

			try {
				
				new Socket(ip, port2);
				socket = new Socket(ip, port);

				if (socket != null) {

					System.out.println("서버와 연결되었습니다. 채팅을 종료하시려면 '/취소'를 입력해 주세요.");

					br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					pw = new PrintWriter(socket.getOutputStream());

					while (true) {
						System.out.print("메세지 입력 : ");
						String message = sc.nextLine();

						if (message.equals("/취소")) {
							break;
						}

						pw.println(message);
						pw.flush();

						String receive = br.readLine();
						System.out.println("상담사 : " + receive);

					}

				}

			}catch (SocketException e) {
			}catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null)
						br.close();
					if (pw != null)
						pw.close();
					if (socket != null)
						socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
	}
}
