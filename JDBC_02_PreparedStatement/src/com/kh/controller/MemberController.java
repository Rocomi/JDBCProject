package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

// Controller : View를 통해서 사용자가 요청한 기능에 대해 처리하는 역할
//				요청받은 메소드에서 전달된 데이터를 가공처리한 후 DAO로 전달하여 호출
//				DAO로부터 반환받은 결과에 따라 성공인지 실패인지를 판단 후 응답
public class MemberController {

	/**
	 * 회원 추가 요청을 처리할 메소드
	 * @param userId 회원아이디
	 */
	public void insertMember(String userId, String userPw, String name, char gender) {
		// view로부터 전달받은 값들을 dao에게 바로 전달하지 않고 
		// 어딘가(Member)에 담아서 전달
		
		// 기본생성자 생성 후 setter 메소드 하나하나 저장
		// 매개변수생성자를 사용하여 객체 생성
		Member m = new Member(userId, userPw, name, gender);
		
		int result = new MemberDao().insertMember(m);
		
		if (result > 0) {
			// DAO의 결과 : 성공했을 경우 성공화면 표시
			new MemberMenu().displaySuccess("회원 추가 성공!");
		} else {
			// DAO의 결과 : 실패했을 경우 실패화면 표시
			new MemberMenu().displayFailed("회원 추가 실패!");
		}
	}
	/**
	 * 전체 회원 목록 조회 메소드
	 */
	public void selectList() {
		ArrayList<Member> list = new MemberDao().selectList();
		
		// 조회된 결과에 따라 사용자에게 결과 표시
		if (list.isEmpty()) {
			new MemberMenu().dispalyNoData("전체 조회 결과가 없음!");
		} else {
			new MemberMenu().dispalyMemberList(list);
		}
	}
	
	public void searchById(String id) {
		Member m = new MemberDao().search(id);
		if (m == null) {
			new MemberMenu().dispalyNoData(id+"에 해당하는 조회 결과가 없음!");
		}else {
			new MemberMenu().displayMember(m);
		}
	}
	
	public void deleteById(String id) {
		
		int result = new MemberDao().delete(id);
		
		if (result == 0) {
			new MemberMenu().displayFailed("회원 탈퇴 실패.");
		}else {
			new MemberMenu().displaySuccess("회원 탈퇴 성공.");
		}
	}
	public void updateById(String id, String column, String data) {
		
		int result = new MemberDao().update(id, column, data);
		
		if (result == 0) {
			new MemberMenu().displayFailed("회원 수정 실패.");
		}else {
			new MemberMenu().displaySuccess("회원 수정 성공.");
		}
		
	}
	/** 
	 * 사용자가 입력한 이름을 키워드로 하여 회원정보 검색
	 * @param keyword 이름에 대한 키워드 값
	 */
	public void searchByName(String keyword) {
		
		ArrayList<Member> list = new MemberDao().selectByUserName(keyword);
		
		if (list.isEmpty()) {
			new MemberMenu().dispalyNoData(keyword+"에 해당하는 조회 결과가 없음!");
		}else {
			new MemberMenu().displayMemberList(list);
		}
		
	}
}
