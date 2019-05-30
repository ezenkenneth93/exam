package study.jsp.student.model;

public class DepartmentSearch extends Department{
	private int limitStart;
	private int listCount;
	public int getLimitStart() {
		return limitStart;
	}
	public void setLimitStart(int limitStart) {
		this.limitStart = limitStart;
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	@Override
	public String toString() {
		return "DepartmentDepartment [limitStart=" + limitStart + ", listCount=" + listCount + ", getDeptno()="
				+ getDeptno() + ", getDname()=" + getDname() + ", getLoc()=" + getLoc() + "]";
	}
	
	
}
