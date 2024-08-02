package kh.project.geneJar.comp.data;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Banner {

	public void printBanner() {

		File f = new File("Banner.txt");
		DataInputStream dis = null;
		try {
			dis = new DataInputStream(new BufferedInputStream(new FileInputStream(f)));

			System.out.print(dis.readUTF());

		} catch (EOFException e) {

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void makeBanner() {

		StringBuilder sb = new StringBuilder();
		File f = new File("Banner.txt");
		BufferedReader br = new BufferedReader((new InputStreamReader(System.in)));
		DataOutputStream dos = null;

		System.out.println("배너를 작성하세요 : ");

		try {

			for (int i = 0;; i++) {
				String str = br.readLine() + "\n";
				if (str.equals("끝\n")) {
					break;
				}
				sb.append(str);
			}

			try { // 입력받은 객체를 데이터로 분리하여 파일에 저장 (이어쓰기)
				dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f)));

				dos.writeUTF(sb.toString());

				dos.flush();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (dos != null)
						dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
