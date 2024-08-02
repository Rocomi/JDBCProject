package kh.project.geneJar.veiw;

import java.util.Scanner;

import kh.project.geneJar.comp.data.Banner;
import kh.project.geneJar.comp.data.Financial;
import kh.project.geneJar.comp.data.IntroduceComp;
import kh.project.geneJar.comp.data.Map;
import kh.project.geneJar.comp.data.Stock;
import kh.project.geneJar.controller.EmployeeController;
import kh.project.geneJar.controller.LoginController;
import kh.project.geneJar.controller.MedicineController;
import kh.project.geneJar.controller.OrderController;
import kh.project.geneJar.manufacture.list.Biosimilar;
import kh.project.geneJar.manufacture.list.ChemicalMedicine;
import kh.project.geneJar.manufacture.list.Manufacture;
import kh.project.geneJar.manufacture.list.Theragene;
import kh.project.geneJar.manufacture.list.Vaccine;
import kh.project.geneJar.model.vo.CustomerLogin;
import kh.project.geneJar.model.vo.Employee;
import kh.project.geneJar.model.vo.Medicine;
import kh.project.geneJar.model.vo.Order;
import kh.project.geneJar.network.AskBoard;
import kh.project.geneJar.network.Chat;
import kh.project.geneJar.network.NetworkClient;
import kh.project.geneJar.network.NetworkServer;
import kh.project.geneJar.published.BiosimilarPub;
import kh.project.geneJar.published.ChemicalMedicinePub;
import kh.project.geneJar.published.Published;
import kh.project.geneJar.published.TheragenePub;
import kh.project.geneJar.published.VaccinePub;

public class GeneJarMenu {
	private Scanner sc = new Scanner(System.in);
	private OrderController oc = new OrderController();
	private MedicineController mc = new MedicineController();
	private NetworkClient nc = new NetworkClient();
	private Financial fnc = new Financial();

	public void mainMenu() {

		while (true) {
			System.out.println("==========================================================");
			System.out.println("|                    Gene Jar 제약회사                      |");
			System.out.println("==========================================================");
			new Banner().printBanner();
			System.out.println("1. 회사 정보");
			System.out.println("2. 회원 가입");
			System.out.println("3. 회원 로그인");
			System.out.println("4. 사원 로그인"); // 관리자, 일반사원 구분
			System.out.println("5. 고객 지원");
			System.out.println("6. 오시는 길");
			System.out.println("9. 종료");
			System.out.print("메뉴 번호 선택 : ");
			int num = sc.nextInt();
			sc.nextLine();

			switch (num) {
			case 1:
				company();
				break;
			case 2:
				join();
				break;
			case 3:
				if (memberlogIn()) {
					memberMenu();
				}
				break;
			case 4: // if 관리자 --> adminMenu, if 사원 --> empMenu
				String logIn = emplogIn();

				if (logIn == null) {

				} else if (logIn.equals("임원")) {

					adminMenu();

				} else if (logIn.equals("생산")) {

					salesMenu();

				} else if (logIn.equals("영업")) {

					accountingMenu();

				} else if (logIn.equals("인사")) {

					personnelMenu();

				} else if (logIn.equals("관리")) {

					managementMenu();

				} else {

					empMenu();

				}
				break;
			case 5:
				helpDesk();
				break;
			case 6:
				new Map().map();
				break;
			case 9:
				System.out.println("프로그램 종료.");
				return;
			default:
				System.out.println("잘못 입력하였습니다. 다시 입력해주세요");
				break;

			}
		}
	}

	public void memberMenu() {

		while (true) {
			System.out.println("==========================================================");
			System.out.println("|                         회원 메뉴                         |");
			System.out.println("==========================================================");
			System.out.println("1. 회사 정보");
			System.out.println("2. 회원정보 변경");
			System.out.println("3. 의약품 메뉴");
			System.out.println("4. 의약품 주문");
			System.out.println("5. 고객 지원");
			System.out.println("6. 오시는 길");
			System.out.println("9. 로그아웃");
			System.out.print("메뉴 번호 선택 : ");
			int num = sc.nextInt();
			sc.nextLine();

			switch (num) {
			case 1:
				company();
				break;
			case 2:
				changeData();
				break;
			case 3:
				customerMedicine();
				break;
			case 4:
				customerOrderMenu();
				break;
			case 5:
				helpDesk();
				break;
			case 6:
				new Map().map();
				break;
			case 9:
				System.out.println("로그아웃 되었습니다.");
				return;
			default:
				System.out.println("잘못 입력하였습니다. 다시 입력해주세요");
			}
		}
	}

	public void adminMenu() {

		while (true) {
			System.out.println("==========================================================");
			System.out.println("|                        관리자 메뉴                        |");
			System.out.println("==========================================================");
			System.out.println("1. 회사 정보");
			System.out.println("2. 사원 관리");
			System.out.println("3. 약품 관리");
			System.out.println("4. 약품 주문 관리");
			System.out.println("5. 회계정보 관리");
			System.out.println("6. 고객지원 관리");
			System.out.println("7. 배너 설정");
			System.out.println("8. 회원 로그인 정보관리");
			System.out.println("9. 로그아웃");
			System.out.print("메뉴 번호 선택 : ");
			int num = sc.nextInt();
			sc.nextLine();

			switch (num) {
			case 1:
				company();
				break;
			case 2:
				controlEmployee();
				break;
			case 3:
				employeeMedicine();
				break;
			case 4:
				adminOrderMenu();
				break;
			case 5:
				financialControl();
				break;
			case 6:
				ansHelpDesk();
				break;
			case 7:
				banner();
				break;
			case 8:
				logInData();
				break;
			case 9:
				System.out.println("로그아웃 되었습니다.");
				return;
			default:
				System.out.println("잘못 입력하였습니다. 다시 입력해주세요");
			}
		}
	}

	public void salesMenu() {

		while (true) {
			System.out.println("==========================================================");
			System.out.println("|                        생산팀 메뉴                        |");
			System.out.println("==========================================================");
			System.out.println("1. 회사 정보");
			System.out.println("2. 회원정보 변경");
			System.out.println("3. 전체 사원 조회");
			System.out.println("4. 약품 관리");
			System.out.println("9. 로그아웃");
			System.out.print("메뉴 번호 선택 : ");
			int num = sc.nextInt();
			sc.nextLine();

			switch (num) {
			case 1:
				company();
				break;
			case 2:
				empChangeData();
				break;
			case 3:
				new EmployeeController().someEmployee();
				break;
			case 4:
				employeeMedicine();
				break;
			case 9:
				System.out.println("로그아웃 되었습니다.");
				return;
			default:
				System.out.println("잘못 입력하였습니다. 다시 입력해주세요");
			}
		}
	}

	public void accountingMenu() {

		while (true) {
			System.out.println("==========================================================");
			System.out.println("|                        영업팀 메뉴                        |");
			System.out.println("==========================================================");
			System.out.println("1. 회사 정보");
			System.out.println("2. 회원번호 변경");
			System.out.println("3. 전체 사원 조회");
			System.out.println("4. 의약품 메뉴");
			System.out.println("5. 의약품 주문 관리");
			System.out.println("9. 로그아웃");
			System.out.print("메뉴 번호 선택 : ");
			int num = sc.nextInt();
			sc.nextLine();

			switch (num) {
			case 1:
				company();
				break;
			case 2:
				empChangeData();
				break;
			case 3:
				new EmployeeController().someEmployee();
				break;
			case 4:
				customerMedicine();
				break;
			case 5:
				adminOrderMenu();
				break;
			case 9:
				System.out.println("로그아웃 되었습니다.");
				return;
			default:
				System.out.println("잘못 입력하였습니다. 다시 입력해주세요");
			}
		}
	}

	public void personnelMenu() {

		while (true) {
			System.out.println("==========================================================");
			System.out.println("|                        인사팀 메뉴                        |");
			System.out.println("==========================================================");
			System.out.println("1. 회사 정보");
			System.out.println("2. 회원정보 변경");
			System.out.println("3. 사원 관리");
			System.out.println("4. 의약품 메뉴");
			System.out.println("9. 로그아웃");
			System.out.print("메뉴 번호 선택 : ");
			int num = sc.nextInt();
			sc.nextLine();

			switch (num) {
			case 1:
				company();
				break;
			case 2:
				empChangeData();
				break;
			case 3:
				controlEmployee();
				break;
			case 4:
				customerMedicine();
				break;
			case 9:
				System.out.println("로그아웃 되었습니다.");
				return;
			default:
				System.out.println("잘못 입력하였습니다. 다시 입력해주세요");
			}
		}
	}

	public void managementMenu() {

		while (true) {
			System.out.println("==========================================================");
			System.out.println("|                        관리팀 메뉴                        |");
			System.out.println("==========================================================");
			System.out.println("1. 회사 정보");
			System.out.println("2. 회원정보 변경");
			System.out.println("3. 전체 사원 조회");
			System.out.println("4. 의약품 메뉴");
			System.out.println("5. 회계정보 관리");
			System.out.println("6. 고객지원 관리");
			System.out.println("7. 배너 관리");
			System.out.println("9. 로그아웃");
			System.out.print("메뉴 번호 선택 : ");
			int num = sc.nextInt();
			sc.nextLine();

			switch (num) {
			case 1:
				company();
				break;
			case 2:
				empChangeData();
				break;
			case 3:
				new EmployeeController().someEmployee();
				break;
			case 4:
				customerMedicine();
				break;
			case 5:
				financialControl();
				break;
			case 6:
				ansHelpDesk();
				break;
			case 7:
				banner();
				break;
			case 9:
				System.out.println("로그아웃 되었습니다.");
				return;
			default:
				System.out.println("잘못 입력하였습니다. 다시 입력해주세요");
			}
		}
	}

	public void empMenu() {

		while (true) {
			System.out.println("==========================================================");
			System.out.println("|                         사원 메뉴                         |");
			System.out.println("==========================================================");
			System.out.println("1. 회사 정보");
			System.out.println("2. 회원정보 변경");
			System.out.println("3. 전체 사원 조회");
			System.out.println("4. 의약품 메뉴");
			System.out.println("9. 로그아웃");
			System.out.print("메뉴 번호 선택 : ");
			int num = sc.nextInt();
			sc.nextLine();

			switch (num) {
			case 1:
				company();
				break;
			case 2:
				empChangeData();
				break;
			case 3:
				new EmployeeController().someEmployee();
				break;
			case 4:
				customerMedicine();
				break;
			case 5:
				customerOrderMenu();
				break;
			case 9:
				System.out.println("로그아웃 되었습니다.");
				return;
			default:
				System.out.println("잘못 입력하였습니다. 다시 입력해주세요");
			}
		}
	}
	// -------------------------------------------------------------------------------------------회사정보

	public void company() {
		while (true) {

			System.out.println("==========================================================");
			System.out.println("|                         회사 정보                         |");
			System.out.println("==========================================================");
			System.out.println("1. 회사 소개");
			System.out.println("2. 재무 정보");
			System.out.println("3. 주가 정보");
			System.out.println("4. 제조 기술");
			System.out.println("9. 돌아가기");
			System.out.print("메뉴 번호를 누르세요 : ");
			int menuNum = sc.nextInt();
			sc.nextLine();
			System.out.println("");

			switch (menuNum) {
			case 1:
				new IntroduceComp().introduce();
				break;
			case 2:
				new Financial().financialGraph();
				break;
			case 3:
				new Stock().stockinfo();
				break;
			case 4:
				manufactureData();
				break;
			case 9:
				System.out.println("이전 메뉴로 돌아갑니다.");
				return;
			default:
				System.out.println("다시 입력해 주세요.");
				System.out.println("");
				break;

			}
		}
	}

	public void manufactureData() {
		while (true) {
			System.out.println("==========================================================");
			System.out.println("|                         제조 기술                         |");
			System.out.println("==========================================================");
			System.out.println("1. 화학 의약품");
			System.out.println("2. 유전자 치료제");
			System.out.println("3. 바이오 시밀러");
			System.out.println("4. 백 신");
			System.out.println("9. 돌아가기");
			System.out.print("메뉴 번호를 누르세요 : ");
			int menuNum = sc.nextInt();
			sc.nextLine();
			System.out.println("");

			switch (menuNum) {
			case 1:
				chemicalData();
				break;
			case 2:
				geneTheData();
				break;
			case 3:
				bioData();
				break;
			case 4:
				vaccineData();
				break;
			case 9:
				System.out.println("이전 메뉴로 돌아갑니다.");
				return;
			default:
				System.out.println("다시 입력해 주세요.");
				System.out.println("");
				break;

			}
		}
	}

	public void chemicalData() {

		while (true) {
			Manufacture mfc = new ChemicalMedicine();
			Published pcp = new ChemicalMedicinePub();

			System.out.println("==========================================================");
			System.out.println("|                        화학 의약품                        |");
			System.out.println("==========================================================");
			System.out.println("1. 화학 의약품이란?");
			System.out.println("2. 회사 보유 특허");
			System.out.println("3. 회사 연구 논문");
			System.out.println("9. 돌아가기");
			System.out.print("메뉴 번호를 누르세요 : ");
			int menuNum = sc.nextInt();
			sc.nextLine();
			System.out.println("");

			switch (menuNum) {
			case 1:
				System.out.println(mfc.information());
				break;
			case 2:
				pcp.license();
				break;
			case 3:
				pcp.paper();
				break;
			case 9:
				System.out.println("이전 메뉴로 돌아갑니다.");
				return;
			default:
				System.out.println("다시 입력해 주세요.");
				System.out.println("");
				break;

			}
		}

	}

	public void geneTheData() {
		while (true) {
			Manufacture mft = new Theragene();
			Published ptp = new TheragenePub();

			System.out.println("==========================================================");
			System.out.println("|                        유전자 치료제                       |");
			System.out.println("==========================================================");
			System.out.println("1. 유전자 치료제란?");
			System.out.println("2. 회사 보유 특허");
			System.out.println("3. 회사 연구 논문");
			System.out.println("9. 돌아가기");
			System.out.print("메뉴 번호를 누르세요 : ");
			int menuNum = sc.nextInt();
			sc.nextLine();
			System.out.println("");

			switch (menuNum) {
			case 1:
				System.out.println(mft.information());
				break;
			case 2:
				ptp.license();
				break;
			case 3:
				ptp.paper();
				break;
			case 9:
				System.out.println("이전 메뉴로 돌아갑니다.");
				return;
			default:
				System.out.println("다시 입력해 주세요.");
				System.out.println("");
				break;

			}
		}
	}

	public void bioData() {
		while (true) {
			Manufacture mfb = new Biosimilar();
			Published pbp = new BiosimilarPub();
			System.out.println("==========================================================");
			System.out.println("|                       바이오 시밀러                        |");
			System.out.println("==========================================================");
			System.out.println("1. 바이오 시밀러란?");
			System.out.println("2. 회사 보유 특허");
			System.out.println("3. 회사 연구 논문");
			System.out.println("9. 돌아가기");
			System.out.print("메뉴 번호를 누르세요 : ");
			int menuNum = sc.nextInt();
			sc.nextLine();
			System.out.println("");

			switch (menuNum) {
			case 1:
				System.out.println(mfb.information());
				break;
			case 2:
				pbp.license();
				break;
			case 3:
				pbp.paper();
				break;
			case 9:
				System.out.println("이전 메뉴로 돌아갑니다.");
				return;
			default:
				System.out.println("다시 입력해 주세요.");
				System.out.println("");
				break;

			}
		}
	}

	public void vaccineData() {
		while (true) {
			Manufacture mfv = new Vaccine();
			Published pvp = new VaccinePub();

			System.out.println("==========================================================");
			System.out.println("|                          백 신                          |");
			System.out.println("==========================================================");
			System.out.println("1. 백신이란?");
			System.out.println("2. 회사 보유 특허");
			System.out.println("3. 회사 연구 논문");
			System.out.println("9. 돌아가기");
			System.out.print("메뉴 번호를 누르세요 : ");
			int menuNum = sc.nextInt();
			sc.nextLine();
			System.out.println("");

			switch (menuNum) {
			case 1:
				System.out.println(mfv.information());
				break;
			case 2:
				pvp.license();
				break;
			case 3:
				pvp.paper();
				break;
			case 9:
				System.out.println("이전 메뉴로 돌아갑니다.");
				return;
			default:
				System.out.println("다시 입력해 주세요.");
				System.out.println("");
				break;

			}
		}
	}

	// -------------------------------------------------------------------------------------------------로그인
	// 정보

	public void join() { ////////// 회원가입 (고객용)

		while (true) {
			System.out.print("아이디 : ");
			String id = sc.nextLine();
			if (id.equals("취소")) {
				return;
			}
			System.out.print("비밀번호 : ");
			String password = sc.nextLine();
			System.out.print("이름 : ");
			String name = sc.nextLine();
			System.out.print("주소 : ");
			String address = sc.nextLine();
			System.out.print("전화번호 : ");
			String phone = sc.nextLine();

			CustomerLogin cl = new CustomerLogin(name, id, password, address, phone);
			new LoginController().join(cl);
			return;

		}
	}

	public boolean memberlogIn() { /////////////////// 로그인

		while (true) {
			System.out.print("아이디 : ");
			String id = sc.nextLine();
			if (id.equals("취소")) {
				return false;
			}
			System.out.print("비밀번호 : ");
			String password = sc.nextLine();

			if (new LoginController().logIn(id, password) != null) {
				System.out.println(new LoginController().logIn(id, password) + "님, 환영합니다!");
				return true;
			} else {
				System.out.println("틀린 아이디 또는 비밀번호입니다. 다시 입력해주세요.");
			}
		}
	}

	public String emplogIn() { /////////////////// 로그인

		while (true) {
			System.out.print("아이디 : ");
			String id = sc.nextLine();
			if (id.equals("취소")) {
				return null;
			}
			System.out.print("비밀번호 : ");
			String password = sc.nextLine();

			if (new EmployeeController().adminlogIn(id, password) != null) {
				System.out.println(new EmployeeController().adminlogIn(id, password).getName() + "님, 환영합니다!");
				return new EmployeeController().adminlogIn(id, password).getDept();
			} else {
				System.out.println("틀린 아이디 또는 비밀번호입니다. 다시 입력해주세요.");
			}
		}
	}

	public void changeData() { ///////////////////////////////////////////// 데이터 수정 완료

		while (true) {
			System.out.print("아이디 : ");
			String id = sc.nextLine();
			if (id.equals("취소")) {
				return;
			}
			System.out.print("비밀번호 : ");
			String pwd = sc.nextLine();

			String check = new LoginController().logIn(id, pwd);
			if (check == null) {
				System.out.println("없는 정보입니다.");
				return;
			}
			System.out.print("바꿀 정보(1.이름 / 2.비밀번호 / 3.주소 / 4.전화번호) : ");
			int dataType = sc.nextInt();
			sc.nextLine();
			String type = null;

			switch (dataType) {
			case 1:
				type = "CUS_NAME";
				break;
			case 2:
				type = "CUS_PWD";
				break;
			case 3:
				type = "ADDRESS";
				break;
			case 4:
				type = "PHONE";
				break;
			default:
				System.out.println("없는 데이터 타입입니다.");
				continue;
			}

			System.out.print("새로운 정보 입력 : ");
			String data = sc.nextLine();

			if (new LoginController().changeInformation(id, pwd, type, data)) {
				System.out.println("정보 변경에 성공했습니다.");
				return;
			} else {
				System.out.println("정보 변경에 실패했습니다. 다시 입력해주세요.");
			}
		}
	}

	public void empChangeData() { //////////// 정보 변경

		while (true) {
			System.out.print("아이디 : ");
			String id = sc.nextLine();
			if (id.equals("취소")) {
				return;
			}
			System.out.print("비밀번호 : ");
			String pwd = sc.nextLine();

			if (new EmployeeController().adminlogIn(id, pwd) == null) {
				System.out.println("없는 정보입니다.");
				return;
			}
			System.out.print("바꿀 정보(1.이름 / 2.비밀번호 / 3.주소 / 4.전화번호) : ");
			int dataType = sc.nextInt();
			sc.nextLine();
			String type = null;

			switch (dataType) {
			case 1:
				type = "EMP_NAME";
				break;
			case 2:
				type = "EMP_PWD";
				break;
			case 3:
				type = "ADDRESS";
				break;
			case 4:
				type = "PHONE";
				break;
			default:
				System.out.println("없는 데이터 타입입니다.");
				continue;
			}

			System.out.print("새로운 정보 입력 : ");
			String data = sc.nextLine();

			if (new EmployeeController().changeInformation(id, pwd, type, data)) {
				System.out.println("정보 변경에 성공했습니다.");
				return;
			} else {
				System.out.println("정보 변경에 실패했습니다. 다시 입력해주세요.");
			}
		}
	}

	// ----------------------------------------------------------------------------------------------------사원관리

	public void controlEmployee() {

		while (true) { // 관리자만 접근 가능

			System.out.println("==========================================================");
			System.out.println("|                       사원 관리 메뉴                       |");
			System.out.println("==========================================================");
			System.out.println("1. 사원추가");
			System.out.println("2. 사원수정");
			System.out.println("3. 사원삭제");
			System.out.println("4. 해당사원출력");
			System.out.println("5. 전체사원 출력");
			System.out.println("9. 돌아가기");
			System.out.print("메뉴 번호를 누르세요 : ");
			int menuNum = sc.nextInt();
			sc.nextLine();
			System.out.println("");

			switch (menuNum) {
			case 1:
				insertEmp();
				break;
			case 2:
				updateEmp();
				break;
			case 3:
				deleteEmp();
				break;
			case 4:
				printEmp();
				break;
			case 5:
				printAllEmp();
				break;
			case 9:
				System.out.println("이전 메뉴로 돌아갑니다.");
				return;
			default:
				System.out.println("다시 입력해 주세요.");
				System.out.println("");
				break;

			}
		}

	}

	public void insertEmp() {

		System.out.print("사원번호 : ");
		int empNo = sc.nextInt();
		sc.nextLine();
		if (empNo == 0) {
			System.out.println("CEO님의 번호 입니다. 건들지 않는것을 추천해요...");
			return;
		}
		if (new EmployeeController().inform(empNo) != null) {
			System.out.print("해당 번호의 사원이 존재합니다. 삭제하시겠습니까? (y/n) : ");

			char yn = sc.nextLine().charAt(0);
			if (yn != 'y' && yn != 'Y') {
				System.out.println("메뉴로 돌아갑니다.");
				System.out.println("");
				return;
			} else {
				System.out.println("기존 데이터를 삭제합니다.");
			}
		}

		System.out.print("사원이름 : ");
		String name = sc.nextLine();
		if (name.equals("취소")) {
			return;
		}
		System.out.print("회원가입 할 아이디 : ");
		String id = sc.nextLine();
		System.out.print("사원부서 : ");
		String dept = sc.nextLine();

		String password = "a1234";

		System.out.print("추가 정보를 더 입력하시겠습니까? (y/n) : ");
		char yn = sc.nextLine().charAt(0);
		if (yn == 'y' || yn == 'Y') {

			System.out.print("사원성별 : ");
			char gender = sc.nextLine().charAt(0);
			System.out.print("사원주소 : ");
			String address = sc.nextLine();
			System.out.print("전화번호 : ");
			String phone = sc.nextLine();
			System.out.print("사원연봉 : ");
			int salary = sc.nextInt();
			sc.nextLine();
			System.out.print("보너스 율 : ");
			double bonus = sc.nextDouble();
			sc.nextLine();

			new EmployeeController()
					.join(new Employee(empNo, id, password, name, gender, address, phone, dept, salary, bonus));
				System.out.println("사원정보가 추가되었습니다.");
				

		} else {
			new EmployeeController().join(new Employee(empNo, id, password, name, ' '," ", " ", dept, 0, 0.0));
				System.out.println("사원정보가 추가되었습니다.");
				
		}

	}

	public void updateEmp() {
		EmployeeController ec = new EmployeeController();

		System.out.print("수정할 사원번호를 입력하세요 : ");
		int empNo = sc.nextInt();
		sc.nextLine();

		if (ec.inform(empNo) == null) {
			System.out.println("해당 번호의 사원이 없습니다. ");
			return;
		}

		System.out.println(new EmployeeController().getE(empNo).getName() + "의 사원정보를 수정합니다.");
		System.out.println("사원의 어떤 정보를 수정하시겠습니까?");
		System.out.println("1. 사원 성별");
		System.out.println("2. 사원 주소");
		System.out.println("3. 전화 번호");
		System.out.println("4. 사원 부서");
		System.out.println("5. 사원 연봉");
		System.out.println("6. 보너스 율");
		System.out.println("9. 돌아가기");
		System.out.print("메뉴 번호를 누르세요 : ");
		int menuNum = sc.nextInt();
		sc.nextLine();

		switch (menuNum) {
		case 1:
			System.out.print("수정할 사원 성별 :");
			char gender = sc.nextLine().charAt(0);
			ec.changeInformation(empNo, "GENDER", Character.valueOf(gender).toString());
			System.out.println("");
			break;
		case 2:
			System.out.print("수정할 전화 번호 :");
			String phone = sc.nextLine();
			ec.changeInformation(empNo, "PHONE", phone);
			System.out.println("");
			break;
		case 3:
			System.out.print("수정할 사원 부서 :");
			String dept = sc.nextLine();
			ec.changeInformation(empNo, "DEPT", dept);
			System.out.println("");
			break;
		case 4:
			System.out.print("수정할 사원 연봉 :");
			int salary = sc.nextInt();
			sc.nextLine();
			ec.changeInformation(empNo, "SALARY", Integer.valueOf(salary).toString());
			System.out.println("");
			break;
		case 5:
			System.out.print("수정할 보너스 율 :");
			double bonus = sc.nextDouble();
			ec.changeInformation(empNo, "BONUS", Double.valueOf(bonus).toString());
			System.out.println("");
			break;
		case 9:
			System.out.println("이전 메뉴로 돌아갑니다.");
			System.out.println("");
			return;
		default:
			System.out.println("잘못 입력하셨습니다. 메인으로 돌아갑니다.");
			System.out.println("");
			return;

		}
		System.out.println("수정완료.");
		System.out.println();

	}

	public void deleteEmp() {
		EmployeeController ec = new EmployeeController();
		System.out.print("삭제할 사원 번호를 입력해주세요 : ");
		int empNo = sc.nextInt();
		sc.nextLine();

		if (ec.inform(empNo) == null) {
			System.out.println("해당 번호의 사원이 없습니다. ");
			return;
		}

		System.out.print("정말 삭제하시겠습니까? (y/n) : ");
		char yn = sc.nextLine().charAt(0);
		if (yn == 'y' || yn == 'Y') {
			if (ec.remove(empNo) == null) {
				System.out.println("사원정보 삭제가 완료되었습니다.");
				System.out.println("");
			} else {
				System.out.println("사원정보 삭제에 실패하였습니다.");
				System.out.println("");
			}

		} else {
			System.out.println("메인 메뉴로 돌아갑니다.");
			System.out.println("");
			return;
		}

	}

	public void printEmp() {

		System.out.print("출력할 사원 번호를 입력하세요 : ");
		int empNo = sc.nextInt();
		sc.nextLine();

		if (new EmployeeController().inform(empNo) == null) {
			System.out.println("해당 번호의 사원이 없습니다. ");
			return;
		} else {
			System.out.println(new EmployeeController().inform(empNo));
			System.out.println("");
		}

	}

	public void printAllEmp() {

		new EmployeeController().allEmployee();

		System.out.println("");
	}

//	--------------------------------------------------------------------------------약품메뉴

	public void customerMedicine() {
		while (true) {
			System.out.println("==========================================================");
			System.out.println("|                         의약품 메뉴                        |");
			System.out.println("==========================================================");
			System.out.println("1. 약품 정보");
			System.out.println("2. 제품명으로 약품검색");
			System.out.println("3. 질병명으로 약품검색"); // 관리자, 일반사원 구분
			System.out.println("4. 약품 주문");
			System.out.println("9. 종료");
			System.out.print("메뉴 번호 선택 : ");
			int num = sc.nextInt();
			sc.nextLine();

			switch (num) {
			case 1:
				printSomeMed();
				break;
			case 2:
				searchMed();
				break;
			case 3:
				searchTarget();
				break;
			case 4:
				customerOrderMenu();
				break;
			case 9:
				System.out.println("프로그램 종료.");
				return;
			default:
				System.out.println("잘못 입력하였습니다. 다시 입력해주세요");
				break;

			}
		}
	}

	public void employeeMedicine() {
		while (true) {
			System.out.println("==========================================================");
			System.out.println("|                     의약품 관리 메뉴                        |");
			System.out.println("==========================================================");
			System.out.println("1. 약품 정보");
			System.out.println("2. 제품명으로 약품검색");
			System.out.println("3. 질병명으로 약품검색"); // 관리자, 일반사원 구분
			System.out.println("4. 약품 추가");
			System.out.println("5. 약품 수량추가");
			System.out.println("6. 약품 삭제");
			System.out.println("9. 종료");
			System.out.print("메뉴 번호 선택 : ");
			int num = sc.nextInt();
			sc.nextLine();

			switch (num) {
			case 1:
				printAllMed();
				break;
			case 2:
				searchMed();
				break;
			case 3:
				searchTarget();
				break;
			case 4:
				addMedicine();
				break;
			case 5:
				addInven();
				break;
			case 6:
				removeMed();
				break;
			case 9:
				System.out.println("프로그램 종료.");
				return;
			default:
				System.out.println("잘못 입력하였습니다. 다시 입력해주세요");
				break;

			}
		}
	}

	public void addMedicine() {

		while (true) {
			System.out.print("약품 번호 : ");
			int medNo = sc.nextInt();
			sc.nextLine();

			if (medNo < 1) {
				System.out.println("메뉴로 돌아갑니다.");
				return;
			}

			if (mc.checkMedNo(medNo)) {
				System.out.println("해당 번호의 약품이 존재합니다.");
				continue;
			}

			System.out.print("약품 학명 : ");
			String name = sc.nextLine();
			System.out.print("약품 이름 : ");
			String med = sc.nextLine();
			System.out.print("약품 제형 : ");
			String formulation = sc.nextLine();
			System.out.print("타겟 질병 : ");
			String target = sc.nextLine();
			System.out.print("약품 생산 비용 : ");
			int prodCost = sc.nextInt();
			sc.nextLine();
			System.out.print("약품 책정 가격 : ");
			int price = sc.nextInt();
			sc.nextLine();
			System.out.print("약품 생산량 : ");
			int inven = sc.nextInt();
			sc.nextLine();

			if (mc.addMedicine(new Medicine(name, med, formulation, target, medNo, prodCost, price, inven))) {
				fnc.changeC(prodCost * inven);
				System.out.println("약품정보가 추가되었습니다.");
				System.out.println("");
				return;
			} else {
				System.out.println("같은 이름의 약품이 있습니다. 다른 제품명을 사용해 주세요.");
			}

		}
	}

	public void printSomeMed() {

		System.out.println("==========================================================");
		System.out.println("|                         약품 정보                         |");
		System.out.println("==========================================================");
		mc.someMedicine();

		System.out.println("");
	}

	public void printAllMed() {

		System.out.println("==========================================================");
		System.out.println("|                         약품 정보                         |");
		System.out.println("==========================================================");
		mc.allMedicine();

		System.out.println("");
	}

	public void searchMed() {

		System.out.print("검색할 약품 이름을 입력해주세요 : ");
		String medKey = sc.nextLine();
		System.out.println();

		if (!mc.medSearch(medKey)) {
			System.out.println(medKey + "에 해당하는 약이 없습니다. ");
		}

	}

	public void searchTarget() {

		System.out.print("검색할 질병 이름을 입력해주세요 : ");
		String targetKey = sc.nextLine();
		System.out.println();

		if (!mc.targetSearch(targetKey)) {
			System.out.println("해당 질병에 해당하는 약이 없습니다. ");
		}
	}

	public void addInven() { 

		System.out.print("재고 추가할 약품 이름을 입력해주세요 : ");
		String med = sc.nextLine();

		if (!mc.checkMed(med)) {
			System.out.println("해당 제품은 존재하지 않습니다.");
			return;
		}

		System.out.print("추가할 수량을 입력하세요 : ");
		int inven = sc.nextInt();
		sc.nextLine();

		mc.addInven(med, inven);
		fnc.changeC(mc.getMedicine(med).getProdCost() * inven);

		System.out.println("수량이 추가되었습니다.");

	}

	public void removeMed() { 

		System.out.print("삭제할 약품 이름을 입력해주세요 : ");
		String med = sc.nextLine();

		if (!mc.checkMed(med)) {
			System.out.println("해당 제품은 존재하지 않습니다.");
			return;
		}

		if (mc.remove(med) == null) {
			System.out.println(med + "가(이) 삭제되었습니다.");
		} else {
			System.out.println("삭제에 실패하였습니다.");
		}
	}

//	-------------------------------------------------------------------------------주문 메뉴

	public void customerOrderMenu() {
		while (true) {
			System.out.println("==========================================================");
			System.out.println("|                         약품 주문                         |");
			System.out.println("==========================================================");
			System.out.println("1. 제품 명으로 주문하기");
			System.out.println("2. 주문 가능한 약품");
			System.out.println("3. 주문내역 확인");
			System.out.println("9. 종료");
			System.out.print("메뉴 번호 선택 : ");
			int num = sc.nextInt();
			sc.nextLine();

			switch (num) {
			case 1:
				medOrder();
				break;
			case 2:
				remainingMed();
				break;
			case 3:
				checkOrder();
				break;
			case 9:
				System.out.println("프로그램 종료.");
				return;
			default:
				System.out.println("잘못 입력하였습니다. 다시 입력해주세요");
				break;

			}
		}
	}

	public void adminOrderMenu() {
		while (true) {
			System.out.println("==========================================================");
			System.out.println("|                       약품 주문 관리                       |");
			System.out.println("==========================================================");
			System.out.println("1. 전체 주문목록 출력");
			System.out.println("2. 회원별 주문정보 확인");
			System.out.println("3. 상품 배송");
			System.out.println("9. 종료");
			System.out.print("메뉴 번호 선택 : ");
			int num = sc.nextInt();
			sc.nextLine();

			switch (num) {
			case 1:
				orderList();
				break;
			case 2:
				checkOrder();
				break;
			case 3:
				sendOrder();
				break;
			case 9:
				System.out.println("프로그램 종료.");
				return;
			default:
				System.out.println("잘못 입력하였습니다. 다시 입력해주세요");
				break;

			}
		}
	}

	public void medOrder() {
		System.out.println("==========================================================");
		System.out.println("|                          주문                           |");
		System.out.println("==========================================================");
		System.out.print("주문할 약품 이름을 입력해 주세요 : ");
		String med = sc.nextLine();
		if (!mc.checkMed(med)) {
			System.out.println("회사에 보유하지 않은 약품입니다.");
			return;
		}
		System.out.print("주문할 수량을 입력해 주세요 : ");
		int quantity = sc.nextInt();
		sc.nextLine();
		if (quantity > 0 && mc.getMedicine(med).getInven() < quantity) {
			System.out.println("재고가 부족합니다. 다시 확인해 주세요.");
			return;
		}
		System.out.print("주문자 이름을 입력해 주세요 : ");
		String orderName = sc.nextLine();
		System.out.print("배송받을 주소를 입력해 주세요 : ");
		String address = sc.nextLine();
		System.out.print("주문자 전화번호를 입력해 주세요 : ");
		String phone = sc.nextLine();

		while (true) {

			String a = Character.valueOf((char) ((Math.random() * 26) + 97)).toString();
			String b = Character.valueOf((char) ((Math.random() * 26) + 97)).toString();
			;
			String c = Character.valueOf((char) ((Math.random() * 26) + 65)).toString();
			;
			String d = Character.valueOf((char) ((Math.random() * 26) + 65)).toString();
			;
			String e = Character.valueOf((char) ((Math.random() * 26) + 97)).toString();
			;

			String orderNo = "O" + Integer.valueOf((int) (Math.random() * 100)).toString() + a + b + c
					+ Integer.valueOf((int) (Math.random() * 10)).toString() + d
					+ Integer.valueOf((int) (Math.random() * 10)).toString() + e;

			// 주문번호 : O00aaA0A0a 형식

			if (oc.getOrder(orderNo) == null) {

				Medicine m = mc.getMedicine(med);
				oc.addOrder(new Order(m.getMed(), m.getFormulation(), m.getMedNo(), orderNo, quantity, orderName,
						address, phone));
				mc.addInven(med, -quantity);
				fnc.changeE(quantity * mc.getMedicine(med).getPrice());
				System.out.println("주문완료");
				return;

			} else {
				continue;
			}
		}
	}

	public void remainingMed() {

		mc.remainingMed();

		System.out.println("");
	}

	public void checkOrder() {
		System.out.print("주문자 이름을 입력하세요 : ");
		String orderName = sc.nextLine();

		System.out.println("주문내역 : ");
		oc.searchOrder(orderName);
	}

	public void orderList() {
		oc.orderList();
	}

	public void sendOrder() {
		System.out.print("주문 번호를 입력하세요 : ");
		String orderNo = sc.nextLine();

		if (oc.getOrder(orderNo) == null) {
			System.out.println("해당되는 주문번호가 없습니다.");
		} else {
			if (oc.remove(orderNo) == null) {
				System.out.println("배송처리 실패하였습니다.");
			} else {
				System.out.println("배송처리 되었습니다.");
			}
		}
	}

//	------------------------------------------------------------------------회계업무

	public void financialControl() {

		while (true) {
			System.out.println("==========================================================");
			System.out.println("|                         회계 관리                         |");
			System.out.println("==========================================================");
			System.out.println("1. 수익 추가");
			System.out.println("2. 지출 추가");
			System.out.println("3. 투자금액 입력");
			System.out.println("9. 종료");
			System.out.print("메뉴 번호 선택 : ");
			int num = sc.nextInt();
			sc.nextLine();

			switch (num) {
			case 1:
				System.out.print("추가할 수익 금액을 입력하세요 : ");
				new Financial().changeE(sc.nextInt());
				System.out.println("추가되었습니다.");
				break;
			case 2:
				System.out.print("추가할 지출 금액을 입력하세요 : ");
				new Financial().changeC(sc.nextInt());
				System.out.println("추가되었습니다.");
				break;
			case 3:
				System.out.print("추가할 투자 금액을 입력하세요 : ");
				new Financial().changeF(sc.nextInt());
				System.out.println("추가되었습니다.");
				break;
			case 9:
				System.out.println("프로그램 종료.");
				return;
			default:
				System.out.println("잘못 입력하였습니다. 다시 입력해주세요");
				break;

			}
		}
	}

//	---------------------------------------------------------------------------고객지원

	public void helpDesk() {

		while (true) {
			System.out.println("==========================================================");
			System.out.println("|                         고객 지원                         |");
			System.out.println("==========================================================");
			System.out.println("1. 문의 게시판");
			System.out.println("2. 문의 게시글 작성");
			System.out.println("3. 채팅 상담");
			System.out.println("9. 종료");
			System.out.print("메뉴 번호 선택 : ");
			int num = sc.nextInt();
			sc.nextLine();

			switch (num) {
			case 1:
				new AskBoard().board();
				break;
			case 2:
				new AskBoard().ask();
				break;
			case 3:
				nc.chat();
				break;
			case 9:
				System.out.println("프로그램 종료.");
				return;
			default:
				System.out.println("잘못 입력하였습니다. 다시 입력해주세요");
				break;

			}
		}
	}

	public void ansHelpDesk() {

		while (true) {
			System.out.println("==========================================================");
			System.out.println("|                         고객 지원                         |");
			System.out.println("==========================================================");
			System.out.println("1. 문의 게시판");
			System.out.println("2. 게시글 답글작성");
			System.out.println("3. 채팅 상담");
			System.out.println("9. 종료");
			System.out.print("메뉴 번호 선택 : ");
			int num = sc.nextInt();
			sc.nextLine();

			switch (num) {
			case 1:
				new AskBoard().board();
				break;
			case 2:
				new AskBoard().answer();
				break;
			case 3:
				System.out.println("상담 요청 대기중. 상담요청시 알림이 울립니다.");
				new Thread(new Chat()).start();
				new NetworkServer().start();
				break;
			case 9:
				System.out.println("프로그램 종료.");
				return;
			default:
				System.out.println("잘못 입력하였습니다. 다시 입력해주세요");
				break;

			}
		}
	}

	public void banner() {
		while (true) {
			System.out.println("==========================================================");
			System.out.println("|                         배너 메뉴                         |");
			System.out.println("==========================================================");
			System.out.println("1. 배너 출력");
			System.out.println("2. 배너 작성");
			System.out.println("9. 종료");
			System.out.print("메뉴 번호 선택 : ");
			int num = sc.nextInt();
			sc.nextLine();

			switch (num) {
			case 1:
				new Banner().printBanner();
				break;
			case 2:
				new Banner().makeBanner();
				break;
			case 9:
				System.out.println("프로그램 종료.");
				return;
			default:
				System.out.println("잘못 입력하였습니다. 다시 입력해주세요");
				break;

			}
		}
	}

	public void logInData() {
		while (true) {
			System.out.println("==========================================================");
			System.out.println("|                      회원 로그인 관리                       |");
			System.out.println("==========================================================");
			System.out.println("1. 전체회원 아이디 조회");
			System.out.println("2. 회원 비밀번호 초기화");
			System.out.println("3. 회원 삭제");
			System.out.println("9. 종료");
			System.out.print("메뉴 번호 선택 : ");
			int num = sc.nextInt();
			sc.nextLine();

			switch (num) {
			case 1:
				LoginController lc = new LoginController();
				lc.logInInfo();
				break;
			case 2:
				System.out.print("초기화할 회원의 아이디를 입력하세요 : ");
				String id = sc.nextLine();
				new LoginController().resetPassword(id);
				break;
			case 3:
				System.out.print("삭제할 회원의 아이디를 입력하세요 : ");
				String did = sc.nextLine();
				new LoginController().deleteCustomer(did);
				break;
			case 9:
				System.out.println("프로그램 종료.");
				return;
			default:
				System.out.println("잘못 입력하였습니다. 다시 입력해주세요");
				break;

			}
		}
	}
}
