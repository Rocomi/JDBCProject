package kh.project.geneJar.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import kh.project.geneJar.model.dao.CustomerLoginDAO;
import kh.project.geneJar.model.dao.Data;
import kh.project.geneJar.model.dao.EmployeeDAO;
import kh.project.geneJar.model.vo.CustomerLogin;
import kh.project.geneJar.model.vo.Employee;

public class EmployeeController {
	private HashMap<String, Employee> eLog = new HashMap<>();
	private Data<Employee> eld = new EmployeeDAO();
	private HashMap<Integer, String> foundid = new HashMap<>(); // 관리자의 경우 사원의 아이디 없이 사원정보에 접근하기 위해 Map사용 대신 객체배열 사용
	// 번호만 사용하여 id값 정보 가져올수있는 Map을 생성 -- 검색용으로 사용하고 데이터 수정에
	private static final Employee BOSS = new Employee(0, "0a", "a1234", "엄희윤", '남', "잠실", "010-7440-****", "임원", 0,
			0.0);

	public EmployeeController() {

		try {
			for (Employee em : eld.fileRead()) {
				eLog.put(em.getId(), em); // 프로그램 시작시 Admin용 HashMap 초기화
			}
		} catch (NullPointerException e) {

		}

		try {
			for (Employee em : eld.fileRead()) {
				foundid.put(em.getEmpNo(), em.getId()); // 프로그램 시작시 id조회용 HashMap 초기화
			}
		} catch (NullPointerException e) {

		}

		if (eLog.size() == 0) { // 데이터가 날아가면 사장 정보 초기화
			join(BOSS);
		}

	}

	public void join(Employee em) { // 자동으로 회원가입 절차 진행 회원가입시 Map 생성

		eld.addData(em);

	}

	public boolean changeInformation(String id, String pwd, String dataType, String data) {

		Employee e = eLog.get(id); // id에 해당하는 객체 조회
		if (e != null) {
			if (e.getPassword().equals(pwd)) { // 저장된 비밀번호와 입력된 기존 비밀번호가 같

				eld.changeData(id, dataType, data); // 바뀐 데이터를 저장하기 위해 메소드 호출
				return true;
			}
		}
		return false;
	}

	public void changeInformation(int empNo, String dataType, String data) {

		eld.changeData(foundid.get(empNo), dataType, data); // 바뀐 데이터를 저장하기 위해 메소드 호출

	}

	public Employee remove(int empNo) {

		eLog.remove(foundid.get(empNo));

		eld.deleteData(foundid.get(empNo));

		return eLog.get(foundid.get(empNo));
	}

	public String inform(int empNo) {

		if (eLog.get(foundid.get(empNo)) == null) {
			return null;
		}
		return eLog.get(foundid.get(empNo)).toString();
	}

	public Employee getE(int empNo) {

		return eLog.get(foundid.get(empNo));

	}

	public void allEmployee() {

		Iterator it = eLog.keySet().iterator();

		for (int i = 0; it.hasNext(); i++) {
			System.out.println(eLog.get(it.next()));
		}
	}

	public void someEmployee() {

		Iterator it = eLog.keySet().iterator();

		System.out.println(eLog.get(it.next()).sample());
	}

	public Employee adminlogIn(String id, String password) {
		Employee em = eLog.get(id); // Map에서 데이터를 조회

		if (em != null) { // 데이터가 있다면..
			if (em.getPassword().equals(password)) { // 저장된 비밀번호와 전달된 비밀번호 비교
				return em;
			}
		}
		return null; // 그외 null 반환
	}

}
