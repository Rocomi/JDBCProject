package kh.project.geneJar.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import kh.project.geneJar.model.dao.Data;
import kh.project.geneJar.model.dao.OrderDAO;
import kh.project.geneJar.model.vo.Medicine;
import kh.project.geneJar.model.vo.Order;

public class OrderController {

	private HashMap<String, Order> oLog = new HashMap<>();
	private Data<Order> od = new OrderDAO();

	{
		try {
			for (Order o : od.fileRead()) {
				oLog.put(o.getOrderNo(), o); // 프로그램 시작시 Medicine HashMap 초기화
			}
		} catch (NullPointerException e) {
			/* 첫번째 약품 */}
	}
	
	public void addOrder(Order o) {
			od.addData(o);

	}

	public void searchOrder(String orderName) {
		
		Order o = new Order();

		Iterator it = oLog.keySet().iterator();

		for (int i = 0; it.hasNext(); i++) {

			o = oLog.get(it.next());

			if (o.getOrderName().equals(orderName)) {
				System.out.println(o);
			}
		}
		
	}

	public void orderList() {

		Order o = new Order();

		Iterator it = oLog.keySet().iterator();

		for (int i = 0; it.hasNext(); i++) {
			System.out.println(oLog.get(it.next()));
		}

	}

	public Order remove(String orderNo) {

		od.deleteData(orderNo);

		return oLog.remove(orderNo);
	}
	
	public Order getOrder(String orderNo) {
		return oLog.get(orderNo);
	}

}
