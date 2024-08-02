package kh.project.geneJar.model.vo;

public class Order extends Medicine {
	
	private String orderNo;
	private int quantity;
	private String orderName;
	private String address;
	private String phone;
	
	public Order() {
		super();
	}

	public Order(String med, String formulation, int medNo, String orderNo, int quantity, String orderName,
			String address, String phone) {
		super(med, formulation, medNo);
		this.orderNo = orderNo;
		this.quantity = quantity;
		this.orderName = orderName;
		this.address = address;
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "'"+ orderNo + "'," + quantity+",'"+orderName+ "','"+ address + "','" + phone+"'" ;
/*		return "주문자 : " + orderName + "\t 제품명 : " + super.getMed() +"\t 주문번호 : " + orderNo + "\t 약품번호 : " + super.getMedNo() + "\t 수량 : " + quantity 
				+ "\t 배송주소 : "+ address + "\t 전화번호 : " + phone ;*/
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
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
