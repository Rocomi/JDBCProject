package kh.project.geneJar.network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class AskBoard {
	private Scanner sc = new Scanner(System.in);
	
	public void board() {
		File f = new File("AskBoard.txt");
		DataInputStream dis = null;
		try {
			dis = new DataInputStream(new BufferedInputStream(new FileInputStream(f)));
			int i=1;
			while (true) {
				System.out.println(i +": " + dis.readUTF());
				i++;
			}

		} catch (EOFException e) {

		} catch (FileNotFoundException e) {
			e.printStackTrace(); 
		} catch (IOException e) {
			e.printStackTrace(); 
		}
	}
	
	public void ask() {
		System.out.println("질문게시판입니다. 질문을 입력해 주세요 (취소입력시 작성이 취소됩니다) : ");
		String question = sc.nextLine();
		
		if(question.equals("취소")) return;
		
		File f = new File("AskBoard.txt");

		DataOutputStream dos = null;

		try {		
			dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f, true)));

			dos.writeUTF(question);

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
	}
	
	public void answer() {
		
		String[] sArr = new String[100];
		File f = new File("AskBoard.txt");

		DataInputStream dis = null;
		try {
			dis = new DataInputStream(new BufferedInputStream(new FileInputStream(f)));
			int i=0;
			while (true) {
				sArr[i] = dis.readUTF();
				i++;
			}

		} catch (NullPointerException e) {

		} catch (EOFException e) {

		} catch (FileNotFoundException e) {
			e.printStackTrace(); 
		} catch (IOException e) {
			e.printStackTrace(); 
		}
		
		System.out.print("답글 작성할 질문의 번호를 입력하세요 (0 입력시 작성취소) : ");
		int ans = sc.nextInt();
		sc.nextLine();
		if(ans<1) {
			System.out.println("작성을 취소합니다.");
			return;
		}
		System.out.println("답글을 입력하세요 :");
		
			for (int i = 99; i>=ans; i-- ) {		// 배열 데이터 모두 입력
				sArr[i] = sArr[i-1];
			}
		
		sArr[ans] = "  ㄴ>" + sc.nextLine();
		
		
		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f)));

			for (String s : sArr) {		// 배열 데이터 모두 입력
				dos.writeUTF(s);
			}

			dos.flush();

		} catch (NullPointerException e) {
			System.out.println("답글이 작성되었습니다.");
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

	}
}


