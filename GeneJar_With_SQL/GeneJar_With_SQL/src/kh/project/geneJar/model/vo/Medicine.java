package kh.project.geneJar.model.vo;

public class Medicine {
	
	private String name;
	private String med;
	private String formulation;
	private String target;
	private int medNo;
	private int prodCost;
	private int price;
	private int inven;
	
	public Medicine() {
		super();
	}
	
	
	public Medicine(String med, String formulation, int medNo) {
		super();
		this.med = med;
		this.formulation = formulation;
		this.medNo = medNo;
	}


	public Medicine(String name, String med, String formulation, String target, int medNo, int prodCost, int price,  int inven) {
		super();
		this.name = name;
		this.med = med;
		this.formulation = formulation;
		this.target = target;
		this.medNo = medNo;
		this.prodCost = prodCost;
		this.price = price;
		this.inven = inven;
		
	}
	
	public String sumeInfo() {
		return String.format("제품명 : %-10s\t 제형 : %-20s\t 효능 : %-13s\t 가격 : %-10d\t 남은 수량 : %d\t", med, formulation, target, price, inven);
	}

	@Override
	public String toString() {
		return String.format("'%s','%s','%s','%s',%d,%d,%d,%d",name, med, target, formulation, medNo, prodCost, price, inven);
		//return String.format("약품명 : %-35s\t 제품명 : %-10s\t 제형 : %-20s\t 효능 : %-13s\t 약품번호 : %d\t 생산비용 : %-10d\t 가격 : %-10d\t 재고수량 : %d",name, med, formulation, target, medNo, prodCost, price, inven);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMed() {
		return med;
	}

	public void setMed(String med) {
		this.med = med;
	}

	public String getFormulation() {
		return formulation;
	}

	public void setFormulation(String formulation) {
		this.formulation = formulation;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public int getMedNo() {
		return medNo;
	}

	public void setMedNo(int medNo) {
		this.medNo = medNo;
	}

	public void setProdCost(int prodCost) {
		this.prodCost = prodCost;
	}
	
	public int getProdCost() {
		return prodCost;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getPrice() {
		return prodCost;
	}
	
	public int getInven() {
		return inven;
	}
	
	public void setInven(int inven) {
		this.inven = inven;
	}

	
}
