package study.jsp.student.controller;

import java.io.IOException;
import java.util.List;

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
import study.jsp.student.model.Department;
import study.jsp.student.model.Professor;
import study.jsp.student.service.DepartmentService;
import study.jsp.student.service.ProfessorService;
import study.jsp.student.service.impl.DepartmentServiceImpl;
import study.jsp.student.service.impl.ProfessorServiceImpl;

@WebServlet("/stud_add.do")
public class StudAdd extends BaseController {

	private static final long serialVersionUID = 4843881456318264217L;

	@Override
	public String doRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*************************************************DEpartment*****************************************************************************/
		/**(1)필요한 Helper + Log4j객체 생성하기*/
		WebHelper web = WebHelper.getInstance(request, response);
		Logger logger = LogManager.getFormatterLogger(request.getRequestURI());
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSession();
		
		/**(2)Service객체 생성하기 --> DB접속*/
		DepartmentService departmentService = new DepartmentServiceImpl(sqlSession, logger);

		/**(3)Service를 통한 SQL수행*/
		//조회결과를 담을 List
		List<Department> list;
		try{
			list = departmentService.getDepartmentList(null);
		}catch(Exception e){
			web.redirect(null, e.getLocalizedMessage());
			return null;
		}finally{
			sqlSession.close();
		}
		
		/*************************************************Professor*****************************************************************************/
		/**(1)필요한 Helper + Log4j객체 생성하기*/
		Logger logger2 = LogManager.getFormatterLogger(request.getRequestURI());
		SqlSession sqlSession2 = MyBatisConnectionFactory.getSqlSession();
		/**(2)Service객체 생성하기 --> DB접속*/
		ProfessorService professorService= new ProfessorServiceImpl(sqlSession2, logger2);

		/**(3)Service를 통한 SQL수행*/
		//조회결과를 담을 List
		List<Professor> plist;
		try{
			plist = professorService.getProfessorList(null);
		}catch(Exception e){
			web.redirect(null, e.getLocalizedMessage());
			return null;
		}finally{
			sqlSession.close();
		}
		
		request.setAttribute("list", list);
		request.setAttribute("plist", plist);
		return "stud_add";
	}
	
}
