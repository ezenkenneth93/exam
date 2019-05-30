package study.jsp.student.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import study.jsp.helper.BaseController;
import study.jsp.helper.PageHelper;
import study.jsp.helper.WebHelper;
import study.jsp.student.dao.MyBatisConnectionFactory;
import study.jsp.student.model.StudentDepartmentProfessor;
import study.jsp.student.service.StudentJoinService;
import study.jsp.student.service.impl.StudentJoinServiceImpl;

@WebServlet("/stud_list.do")
public class StudList extends BaseController{
	private static final long serialVersionUID = 91535313756791745L;

	@Override
	public String doRun(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		/* (1) 필요한 Helper + Log4j객체 생성하기 */
		//--> study.jsp.helper.WebHelper
		WebHelper web = WebHelper.getInstance(request, response);
		//--> org.apache.logging.log4j.Logger(LogManager);
		Logger logger = LogManager.getFormatterLogger(request.getRequestURI());
		
		//검색어 파라미터 받기 + Beans설정
		String keyword = web.getString("keyword", "");
		StudentDepartmentProfessor studentDepartmentProfessor = new StudentDepartmentProfessor();
		studentDepartmentProfessor.setName(keyword);
		
		//현재 페이지 번호에 대한 파라미터 받기
		int nowPage = web.getInt("page", 1);
		
		/* (2) service객체 생성하기 --> DB접속 */
		//-->import="org.apache.ibatis.session.SqlSession"
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSession();
		//-->"study.jsp.myschool.service.ProfessorService"
		//-->study.jsp.myschool.service.impl.ProfessorServiceImpl
		StudentJoinService studentJoinService = new StudentJoinServiceImpl(sqlSession, logger);
		
		//전체 데이터 수 조회하기
		int totalCount = 0;
		try{
			totalCount = studentJoinService.getStudentCount(studentDepartmentProfessor);
		}catch(Exception e){
			sqlSession.close();
			web.redirect(null, e.getLocalizedMessage());
			return null;
		}
		
		//페이지 번호에 대한 연산 수행 후 조회조건값 지정을 위한 Beans에 추가하기
		PageHelper pageHelper = PageHelper.getInstance(nowPage, totalCount, 10, 5);
		studentDepartmentProfessor.setLimitStart(pageHelper.getLimitStart());
		studentDepartmentProfessor.setListCount(pageHelper.getListCount());
		
		/* (3) service를 통한 SQL 수행 */
		List<StudentDepartmentProfessor> list = null;
		try{
			list = studentJoinService.getStudentJoinList(studentDepartmentProfessor);
		}catch(Exception e){
			web.redirect(null, e.getLocalizedMessage());
			return null;
		}finally{
			sqlSession.close();
		}
		
		request.setAttribute("list", list);
		request.setAttribute("keyword", keyword);
		keyword = URLEncoder.encode(keyword,"utf-8");
		request.setAttribute("pageHelper", pageHelper);
		request.setAttribute("nowPage", nowPage);
		
		return "stud_list";
	}

}
