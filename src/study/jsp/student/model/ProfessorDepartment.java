package study.jsp.student.model;

/**
 * join문에 의해 select되는 컬럼들은 Professor데이터를 근간으로 하여 학과 이름만 추가하는 형태이므로,
 * 기존의 Professor 클래스를 상속받아 학과 이름에 해당하는 dname값만 추가하는 형태로 표현할 수 있다.
 * 
 * 하지만 반드시 상속을 해야하는 것은 아니다, 상속 없이 모든 멤버변수를 새롭게 작성해도 무관하다.
 * */
public class ProfessorDepartment extends Professor{
	private String dname;
	
	private int limitStart;
	private int listCount;

	
	public String getDname() {
		return dname;
	}


	public void setDname(String dname) {
		this.dname = dname;
	}


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
		return "ProfessorDepartment [dname=" + dname + ", limitStart=" + limitStart + ", listCount=" + listCount
				+ ", getProfno()=" + getProfno() + ", getName()=" + getName() + ", getUserid()=" + getUserid()
				+ ", getPosition()=" + getPosition() + ", getSal()=" + getSal() + ", getHiredate()=" + getHiredate()
				+ ", getComm()=" + getComm() + ", getDeptno()=" + getDeptno() + "]";
	}
	
	
}
