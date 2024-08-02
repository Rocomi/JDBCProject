package kh.project.geneJar.model.vo;

import java.io.Serializable;

public class Employee extends CustomerLogin implements Serializable{
	
	private int empNo;
	private char gender;
	private String phone;
	private String dept;
	private int salary;
	private double bonus;
	

	public Employee() {
		super();
	}
	
	public Employee(int empNo, String id, String password, String name) {  // id 생성용 생성자 (오버로드)
		super(id, password, name);
		this.empNo = empNo;
	}

	public Employee(int empNo, String id, String password, String name, char gender, String address, String phone, String dept,
			int salary, double bonus) {
		super(id, password, name, address, phone);
		this.empNo = empNo;
		this.gender = gender;
		this.dept = dept;
		this.salary = salary;
		this.bonus = bonus;
	}


	@Override
	public String toString() {
		return String.format("'%d','%s','%s','%s','%c','%s','%s','%s',%d,%f"
				,empNo,super.getName(), super.getId(), super.getPassword(), gender, super.getAddress(), phone, dept, salary, bonus);
/*		return String.format("%s\t 사원번호 = %d\t 부서 = %s\t 성별 = %c\t 전화번호 = %s\t id = %s\t password = %s\t 월급 = %d\t 보너스 = %.3f"
				,super.getName(),empNo, dept, gender, phone, super.getId(), super.getPassword(), salary, bonus);
*/	}
	
	public String sample() {
	
		return String.format("%s\t 사원번호 = %d\t 부서 = %s\t 성별 = %c\t 전화번호 = %s\t", super.getName(), empNo, dept, gender, phone);
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

}
