package study.jsp.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import study.jsp.helper.BaseController;
import study.jsp.helper.WebHelper;
import study.jsp.student.dao.MyBatisConnectionFactory;
import study.jsp.student.model.StudentDepartmentProfessor;
import study.jsp.student.service.StudentService;
import study.jsp.student.service.impl.StudentServiceImpl;
@WebServlet("/stud_edit_ok.do")
public class StudEditOk extends BaseController {
	@Override
	public String doRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* (1) WebHelper, Logger객체 생성 + prof_edit페이지로부터 데이터 전달받기 */
		WebHelper web = WebHelper.getInstance(request, response);
		Logger logger = LogManager.getFormatterLogger();
		
		int studno = web.getInt("studno");
		String name = web.getString("name");
		String userid = web.getString("userid");
		int grade  = web.getInt("grade");
		String idnum= web.getString("idnum");
		String birthdate= web.getString("birthdate");
		String tel= web.getString("tel");
		int height= web.getInt("height");
		int weight= web.getInt("weight");
		int deptno=web.getInt("deptno");
		String dname=web.getString("dname");
		int profno=web.getInt("profno");
		String pname=web.getString("pname");
		
		//전달받은 파라미터는 로그로 값을 확인하는것이 좋다.
		logger.debug("studno=" + studno);
		logger.debug("name=" + name);
		logger.debug("userid=" + userid);
		logger.debug("grade=" + grade);
		logger.debug("idnum=" + idnum);
		logger.debug("birthdate=" + birthdate);
		logger.debug("tel=" + tel);
		logger.debug("height=" + height);
		logger.debug("weight=" + weight);
		logger.debug("deptno=" + deptno);
		logger.debug("profno=" + profno);
		
		//필수항목에 대한 입력 여부 검사하기
		if(name == null){
			web.redirect(null,"이름을 입력하세요.");
			 return null;
		}
		if(userid == null){
			web.redirect(null,"아이디를 입력하세요.");
			 return null;
		}
		if(grade < 0){
			web.redirect(null,"학년을 입력하세요.");
			 return null;
		}
		if(idnum == null){
			web.redirect(null,"주민번호를 입력하세요.");
			 return null;
		}
		if(birthdate == null){
			web.redirect(null,"생일을 입력하세요.");
			 return null;
		}
		if(tel == null){
			web.redirect(null,"전화번호를 입력하세요.");
			 return null;
		}
		if(height < 0){
			web.redirect(null,"키를 입력하세요.");
			 return null;
		}
		if(weight < 0){
			web.redirect(null,"체중을 입력하세요.");
			 return null;
		}
		if(deptno < 0){
			web.redirect(null,"학과번호를 입력하세요.");
			 return null;
		}
		
		
		/* (2) 전달받은 데이터를 포함하는 Beans객체 생성 */
		StudentDepartmentProfessor student = new StudentDepartmentProfessor();
		student.setStudno(studno);
		student.setName(name);
		student.setUserid(userid);
		student.setGrade(grade);
		student.setIdnum(idnum);
		student.setBirthdate(birthdate);
		student.setTel(tel);
		student.setHeight(height);
		student.setWeight(weight);
		student.setDeptno(deptno);
		student.setProfno(profno);
		student.setDname(dname);
		student.setPname(pname);
		
		/* (3) service객체 생성, DB접속 */
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSession();
		StudentService studentService = new StudentServiceImpl(sqlSession, logger);
		
		/* (4) Service객체를 통해서 Update구문 실행*/
		
		try{
			studentService.editStudent(student);
		} catch(Exception e){
			 web.redirect(null, e.getLocalizedMessage());
			 return null;
		}finally{
			sqlSession.close();
		}
		
		/* (5) 다시 해당 데이터를 조회하여 참조변수에 담은 후 있다면 UI에 출력--> get방식을 통해 prof_view페이지로 전달
				없다면...뭐해야되냐... */
		String url = "stud_view.do?studno=" + student.getStudno();
		web.redirect(url,"데이터 수정 완료");
		return null;
	}
	

}
