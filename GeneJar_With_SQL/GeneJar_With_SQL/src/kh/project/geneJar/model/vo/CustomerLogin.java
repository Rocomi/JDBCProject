package kh.project.geneJar.model.vo;

import java.io.Serializable;

public class CustomerLogin implements Serializable {
	
	private String id;
	private String name;
	private String password;
	private String address;
	private String phone;
	
	
	public CustomerLogin() {
		super();
	}

	public CustomerLogin(String name, String id, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	public CustomerLogin(String name, String id, String password, String address, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.address = address;
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "이름 : " + name + "\t id=" + id +  "\t password=" + password + "\t address=" + address
				+ "\t phone=" + phone;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
}
