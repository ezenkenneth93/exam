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
import study.jsp.student.model.Student;
import study.jsp.student.service.StudentService;
import study.jsp.student.service.impl.StudentServiceImpl;

@WebServlet("/stud_add_ok.do")
public class StudAddOk extends BaseController {
	private static final long serialVersionUID = -4937897388049359241L;

	@Override
	public String doRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* (1)필요한 Helper + Log4j객체 생성하기 */
		WebHelper web = WebHelper.getInstance(request, response);
		Logger logger = LogManager.getFormatterLogger(request.getRequestURI());
		
		/* (2)stud_add로부터 입력값 전달받기 --> 만약에 입력값이 없을경우--> redirect()*/
		
		String name = web.getString("name");
		String userid = web.getString("userid");
		int grade  = web.getInt("grade");
		String idnum= web.getString("idnum");
		String birthdate= web.getString("birthdate");
		String tel= web.getString("tel");
		int height= web.getInt("height");
		int weight= web.getInt("weight");
		int deptno=web.getInt("deptno");
		int profno=web.getInt("profno");
		
		
		//전달받은 파라미터는 로그로 값을 확인하는것이 좋다
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
		
		/* (3)필수항목에 대한 입력여부 검사하기 */
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
		/* (4)저장을 위한 JavaBeans구성하기 */
		Student student = new Student();
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
		
		/* (5)SqlSession객체 생성, Service 객체 생성하기 --> DB접속*/
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSession();
		StudentService studentService = new StudentServiceImpl(sqlSession, logger);
		
		/* (6)Service를 통한 SQL수행--> 저장한다는거지 */
		try{
			studentService.addStudent(student);
			web.redirect("stud_list.do", "저장 완료");
		}catch(Exception e){
			web.redirect(null, e.getLocalizedMessage());
			return null;
		}finally{
			sqlSession.close();
		}
		return null;
	}

}
