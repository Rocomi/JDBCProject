package kh.project.geneJar.controller;

import java.util.HashMap;
import java.util.Iterator;

import kh.project.geneJar.model.dao.EmployeeDAO;
import kh.project.geneJar.model.dao.CustomerLoginDAO;
import kh.project.geneJar.model.dao.Data;
import kh.project.geneJar.model.vo.Employee;
import kh.project.geneJar.model.vo.CustomerLogin;

public class LoginController {
	private HashMap<String, CustomerLogin> cLog = new HashMap<>();
	private Data<CustomerLogin> cld = new CustomerLoginDAO();

	public LoginController() {

		try {
			for (CustomerLogin c : cld.fileRead()) {
				cLog.put(c.getId(), c); // 프로그램 시작시 Customer용 HashMap 초기화
			}
		} catch (NullPointerException e) {
		}
	}

	public void join(CustomerLogin c) { // 회원가입시 Map 생성

		cld.addData(c);
	}

	public String logIn(String id, String password) {
		CustomerLogin c = cLog.get(id); // Map에서 데이터를 조회

		if (c != null) { // 데이터가 있다면..
			if (c.getPassword().equals(password)) { // 저장된 비밀번호와 전달된 비밀번호 비교
				return c.getName();
			}
		}
		return null; // 그외 null 반환
	}

	public boolean changeInformation(String id, String pwd, String dataType, String data) {

		CustomerLogin c = cLog.get(id); // id에 해당하는 객체 조회

		if (c != null) {
			if (c.getPassword().equals(pwd)) { // 저장된 비밀번호와 입력된 기존 비밀번호가 같

				cld.changeData(id, dataType, data); // 바뀐 데이터를 저장하기 위해 메소드 호출

				return true;
			}
		}
		return false;
	}

	public void logInInfo() {

		Iterator it = cLog.keySet().iterator();

		for (int i = 0; it.hasNext(); i++) {
			System.out.println(cLog.get(it.next()));

		}
	}

	public void resetPassword(String id) {
		CustomerLogin c = cLog.get(id); // id에 해당하는 객체 조회
		CustomerLogin[] cArr = new CustomerLogin[cLog.size()];
		if (c != null) {

			c.setPassword("1234");

			Iterator it = cLog.keySet().iterator();

			for (int i = 0; it.hasNext(); i++) {

				cArr[i] = cLog.get(it.next()); // 배열에 바뀐 데이터를 포함한 모든 데이터 주입

			}
			cld.changeData(id, "CUS_PWD", "1234"); // 바뀐 배열을 저장하기 위해 메소드 호출
			System.out.println("초기화 완료되었습니다.");
		}
	}

	public void deleteCustomer(String id) { ///// 메인에 적용 안함
		cld.deleteData(id);
	}
}