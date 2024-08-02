package kh.project.geneJar.controller;

import java.util.HashMap;
import java.util.Iterator;

import kh.project.geneJar.model.dao.Data;
import kh.project.geneJar.model.dao.MedicineDAO;
import kh.project.geneJar.model.vo.Medicine;

public class MedicineController {

	private HashMap<String, Medicine> mLog = new HashMap<>();
	private HashMap<String, String> foundTarget = new HashMap<>();
	private Data<Medicine> md = new MedicineDAO();

	{
		try {
			for (Medicine m : md.fileRead()) {
				mLog.put(m.getMed(), m); // 프로그램 시작시 Medicine HashMap 초기화
			}
		} catch (NullPointerException e) {
			/* 첫번째 약품 */}

		try {
			for (Medicine m : md.fileRead()) {
				foundTarget.put(m.getTarget(), m.getMed()); // 프로그램 시작시 id조회용 HashMap 초기화
			}
		} catch (NullPointerException e) {
			/* 약품이 없을때 */}
	}

	public boolean checkMed(String med) {
		if (mLog.containsKey(med)) {
			return true;
		}
		return false;
	}

	public boolean checkMedNo(int medNo) {

		Medicine m = new Medicine();

		Iterator it = mLog.keySet().iterator();

		for (int i = 0; it.hasNext(); i++) {

			m = mLog.get(it.next());

			if (m.getMedNo() == medNo) {
				return true;
			}
		}
		return false;
	}

	public boolean addMedicine(Medicine m) {

		if (!mLog.containsKey(m.getMed())) {
			mLog.put(m.getMed(), m);
			foundTarget.put(m.getTarget(), m.getMed());
			md.addData(m);

			return true;
		}
		return false;
	}

	public void allMedicine() {

		Medicine m = new Medicine();

		Iterator it = mLog.keySet().iterator();

		for (int i = 0; it.hasNext(); i++) {

			m = mLog.get(it.next());

			System.out.println(m);
		}

	}

	public void someMedicine() {

		Medicine m = new Medicine();

		Iterator it = mLog.keySet().iterator();

		for (int i = 0; it.hasNext(); i++) {

			m = mLog.get(it.next());

			System.out.println(m.sumeInfo());
		}

	}

	public boolean medSearch(String medKey) {

		String med;
		int count = 0;

		Iterator it = mLog.keySet().iterator();

		for (int i = 0; it.hasNext(); i++) {
			med = (String) it.next();
			if (med.contains(medKey)) {

				System.out.println(mLog.get(med).sumeInfo());
				count++;
			}
		}
		if (count == 0)
			return false;

		return true;
	}

	public boolean targetSearch(String targetKey) {

		String med;
		int count = 0;

		Iterator it = foundTarget.keySet().iterator();

		for (int i = 0; it.hasNext(); i++) {
			med = foundTarget.get(it.next());

			if (mLog.get(med).getTarget().contains(targetKey)) {

				System.out.println(mLog.get(med).sumeInfo());
				count++;
			}
		}
		if (count == 0)
			return false;

		return true;
	}

	public void addInven(String med, int inven) {

		mLog.get(med).setInven(mLog.get(med).getInven() + inven); // 재고 수량 추가

		Medicine m = mLog.get(med);
		mLog.put(med, m);

		md.changeData(med, "INVEN", "INVEN + " + Integer.valueOf(inven).toString());
	}

	public Medicine remove(String med) {

		md.deleteData(med);

		return mLog.remove(med);
	}
	
	public Medicine getMedicine(String med) {
		return mLog.get(med);
	}
	
	public void remainingMed() {

		Medicine m = new Medicine();

		Iterator it = mLog.keySet().iterator();

		for (int i = 0; it.hasNext(); i++) {
			
			m = mLog.get(it.next());
			
			if(m.getInven() > 0)
			System.out.println(m.sumeInfo());
			
		}

	}
}
