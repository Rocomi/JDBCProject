package kh.project.geneJar.comp.data;

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

public class Financial { // 재무정보

	public void financialGraph() {
		int total = 0; // 회사 총 자산
		int earn = 0; // 회사 수익
		int cost = 0; // 회사 지출
		int funding = 0; // 투자금

		
		File f = new File("Financial.txt");

		DataInputStream dis = null;
		try {
			dis = new DataInputStream(new BufferedInputStream(new FileInputStream(f)));

			earn = dis.readInt();
			cost = dis.readInt();
			funding = dis.readInt();

		} catch (NullPointerException ex) {

		} catch (EOFException ex) {

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		total = earn + funding - cost;

		System.out.println("========== 회사 자산 현황 ==========");
		System.out.println();
		System.out.println();

		for (int i = 0; i < 100; i++) {
			System.out.print("█");
		}
		System.out.println(" 회사자산규모 " + total/10000 + "만원");
		System.out.println();

		for (int i = 0; i < (double)funding / total * 100; i++) {
			System.out.print("█");
		}
		System.out.println(" 투자금액 " + funding/10000 + "만원");
		System.out.println();

		for (int i = 0; i < (double)earn / total * 100; i++) {
			System.out.print("█");
		}
		System.out.println(" 수익규모 " + earn/10000 + "만원");
		System.out.println();

		for (int i = 0; i < (double)cost / total * 100; i++) {
			System.out.print("█");
		}
		System.out.println(" 지출규모 " + cost/10000 + "만원");
		System.out.println();


	}

	public void changeE(int e) {
		
		int[] iArr = new int[3];

		File f = new File("Financial.txt");

		DataInputStream dis = null;
		try {
			dis = new DataInputStream(new BufferedInputStream(new FileInputStream(f)));
			int i = 0;
			while (true) {
				iArr[i] = dis.readInt();
				i++;
			}

		} catch (EOFException ex) {

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		iArr[0] += e;

		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f)));

			for (int i : iArr) { // 배열 데이터 모두 입력
				dos.writeInt(i);
			}

			dos.flush();

		} catch (NullPointerException ex) {

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (dos != null)
					dos.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void changeC(int c) {

		int[] iArr = new int[3];
		File f = new File("Financial.txt");

		DataInputStream dis = null;
		try {
			dis = new DataInputStream(new BufferedInputStream(new FileInputStream(f)));
			int i = 0;
			while (true) {
				iArr[i] = dis.readInt();
				i++;
			}

		} catch (EOFException ex) {

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		iArr[1] += c;

		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f)));

			for (int i : iArr) { // 배열 데이터 모두 입력
				dos.writeInt(i);
			}

			dos.flush();

		} catch (NullPointerException ex) {

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (dos != null)
					dos.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void changeF(int fi) {

		int[] iArr = new int[3];

		File f = new File("Financial.txt");

		DataInputStream dis = null;
		try {
			dis = new DataInputStream(new BufferedInputStream(new FileInputStream(f)));
			int i = 0;
			while (true) {
				iArr[i] = dis.readInt();
				i++;
			}

		} catch (EOFException ex) {

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		iArr[2] += fi;

		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f)));

			for (int i : iArr) { // 배열 데이터 모두 입력
				dos.writeInt(i);
			}

			dos.flush();

		} catch (NullPointerException ex) {

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (dos != null)
					dos.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
