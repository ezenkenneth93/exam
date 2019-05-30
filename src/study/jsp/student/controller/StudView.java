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
import study.jsp.student.service.StudentJoinService;
import study.jsp.student.service.impl.StudentJoinServiceImpl;

@WebServlet("/stud_view.do")
public class StudView extends BaseController {

	private static final long serialVersionUID = 1142568175196934479L;

	@Override
	public String doRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WebHelper web = WebHelper.getInstance(request, response);
		Logger logger = LogManager.getFormatterLogger(request.getRequestURI());
		/* (1)이전페이지에서 전달된 교수번호 받기 */
		int studno = web.getInt("studno");
		logger.debug("studno=" + studno);
		
		if(studno==0){
			web.redirect(null,"학생번호가 없습니다");
			return null;
		}
		
		/* (2)조회를 위한 Beans객체 생성 */
		StudentDepartmentProfessor studentDepartmentProfessor = new StudentDepartmentProfessor();
		studentDepartmentProfessor.setStudno(studno);
		
		/* (3)Service 객체 생성하기--> DB접속 */
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSession();
		StudentJoinService studentJoinService = new StudentJoinServiceImpl(sqlSession, logger);
		
		/* (4)Service를 통한 SQL수행 */
		 StudentDepartmentProfessor item = null;
		try{
			item = studentJoinService.getStudentJoinItem(studentDepartmentProfessor);
		} catch(Exception e){
			web.redirect(null,e.getLocalizedMessage());
			return null;
		} finally{
			//sqlSession.close();
		}
		
		request.setAttribute("item", item);
		return "stud_view";
	}
	

}
