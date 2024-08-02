package kh.project.geneJar.model.dao;


public interface Data<T> {
	
	public void addData(T t);
	
	public T[] fileRead();
	
	public void changeData(String id, String dataType, String data);
	
	public void deleteData(String id);

}
