package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.MemberController;
import com.kh.model.vo.Member;

// View : 사용자가 보게될 시각적인 요소(화면) 출력 및 입력
public class MemberMenu {
	// Scanenr 객체 생성
	private Scanner sc = new Scanner(System.in);
	// MemberController 객체 생성
	private MemberController mc = new MemberController();

	/**
	 * 사용자가 보게 될 첫 화면 : 메인메뉴(화면)
	 */
	public void mainMenu() {

		while (true) {

			System.out.println("========== 회원 관리 프로그램 ==========");
			System.out.println("1. 회원 추가"); // C (Create)
			System.out.println("2. 전체 회원 조회"); // R (Read)
			System.out.println("3. 회원 아이디로 검색"); // R (Read)
			System.out.println("4. 회원 정보 수정"); // U (Update)
			System.out.println("5. 회원 탈퇴"); // D (Delete)
			System.out.println("9. 프로그램 종료");

			System.out.print(">> 메뉴번호 : ");
			int menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 1:
				addMenu();
				break;
			case 2:
				mc.selectList();
				break;
			case 3:
				searchById();
				break;
			case 4:
				updateById();
				break;
			case 5:
				deleteById();
				break;
			case 9:
				System.out.println("프로그램을 종료합니다...");
				return;
			}
		}
	}

	private void updateById() {
		System.out.println("----- 회원 정보 수정 -----");
		
		System.out.print("회원 아이디 : ");
		String id = sc.nextLine();

		System.out.print("바꿀 정보(1.이름 / 2.비밀번호 / 3.주소 / 4.전화번호 / 5.이메일 / 6.취미) : ");
		int dataType = sc.nextInt();
		sc.nextLine();
		String column = null;

		switch (dataType) {
		case 1:
			column = "USERNAME";
			break;
		case 2:
			column = "USERPW";
			break;
		case 3:
			column = "ADDRESS";
			break;
		case 4:
			column = "PHONE";
			break;
		case 5:
			column = "EMAIL";
			break;
		case 6:
			column = "HOBBY";
			break;
		default:
			System.out.println("없는 데이터 타입입니다.");
			break;
		}

		System.out.print("수정할 정보 입력 : ");
		String data = sc.nextLine();
		
		mc.updateById(id, column, data);

	}

	private void deleteById() {
		System.out.print("삭제할 아이디 : ");
		String id = sc.nextLine();

		mc.deleteById(id);

	}

	private void searchById() {
		System.out.print("조회할 아이디 : ");
		String id = sc.nextLine();

		mc.searchById(id);

	}

	/**
	 * 회원 추가를 위한 메뉴(화면)
	 */
	public void addMenu() {
		System.out.print("아이디 : ");
		String userId = sc.nextLine();

		System.out.print("비밀번호 : ");
		String userPw = sc.nextLine();

		System.out.print("이름 : ");
		String name = sc.nextLine();

		System.out.print("성별 (M/F) : ");
		char gender = sc.nextLine().toUpperCase().charAt(0);

		// 회원 추가 요청 --> Controller에게 요청
		mc.insertMember(userId, userPw, name, gender);
	}

	/**
	 * 요청 처리 후 성공했을 경우 사용자에게 표시할 화면
	 * 
	 * @param message 성공메세지
	 */
	public void displaySuccess(String message) {
		System.out.println("서비스 요청 성공 : " + message);
	}

	/**
	 * 요청 처리 후 실패했을 경우 사용자에게 표시할 화면
	 * 
	 * @param message 실패메세지
	 */
	public void displayFailed(String message) {
		System.out.println("서비스 요청 실패 : " + message);
	}

	/**
	 * 조회 결과가 없을때 사용자에게 표시할 화면
	 * 
	 * @param message 결과에 메세지
	 */
	public void dispalyNoData(String message) {

		System.out.println(message);

	}

	/**
	 * 조회 결과가 여러 행일때 사용자에게 표시할 화면
	 * 
	 * @param list 조회된 회원 정보가 담겨있는 리스트
	 */
	public void dispalyMemberList(ArrayList<Member> list) {
		System.out.println("----조회 결과----");

		/*
		 * for (int i =0;i< list.size();i++) { System.out.println(list.get(i)); }
		 */

		for (Member m : list) {
			System.out.println(m);
		}

	}

	/**
	 * 조회결과 Member객체를 화면에 표시
	 * 
	 * @param m 조회된 회원 정보
	 */
	public void displayMember(Member m) {
		System.out.println("----조회 결과----");
		System.out.println(m);

	}
}
