package kh.project.geneJar.run;

import kh.project.geneJar.veiw.GeneJarMenu;

public class Run {

	public static void main(String[] args) {
		
//		new GeneJarMenu().mainMenu();
		new GeneJarMenu().adminMenu();
//		new CustomerLoginDAO().fileRead();
		
/*		for (Employee e : new EmployeeDAO().fileRead()) {
			System.out.println(e);
		}
		;*/
	}

}


/*
현재 customerDAO의 데이터베이스 호출만 가능하게 해 놓았음...
회원가입으로 데이터베이스에 저장도 가능함

*/