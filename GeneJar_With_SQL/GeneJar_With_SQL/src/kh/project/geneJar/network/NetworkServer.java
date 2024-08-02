package kh.project.geneJar.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class NetworkServer extends Thread {

	public void run() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			int port = 3000;

			ServerSocket server = null;

			try {
				server = new ServerSocket(port);

				Socket socket = server.accept();
				System.out.println("\n*****************고객님이 상담을 요청합니다. 서버로 이동해주세요.*******************");


			} catch (SocketException e) {
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (server != null)
						server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
