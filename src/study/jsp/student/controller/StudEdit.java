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
import study.jsp.student.model.Student;
import study.jsp.student.service.DepartmentService;
import study.jsp.student.service.ProfessorService;
import study.jsp.student.service.StudentService;
import study.jsp.student.service.impl.DepartmentServiceImpl;
import study.jsp.student.service.impl.ProfessorServiceImpl;
import study.jsp.student.service.impl.StudentServiceImpl;

@WebServlet("/stud_edit.do")
public class StudEdit extends BaseController {
	private static final long serialVersionUID = -2751116206830384533L;
	@Override
	public String doRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 선 조회 후 수정  */
		/* (1)Helper, Logger객체 생성 + 이전페이지에서 전달된 교수번호 받기
		--> stud_view에서 조회된 데이터의 studno를get방식을 사용하여 이 페이지로 보내는것이다. */
		WebHelper web = WebHelper.getInstance(request, response);
		Logger logger = LogManager.getFormatterLogger(request.getRequestURI());
		
		int studno = web.getInt("studno");
		logger.debug("수정할 데이터의 studno= " + studno);
	
		/* (2)수정 대상을 조회하기 위한 beans생성 */	
		Student student = new Student();
		student.setStudno(studno);
		
		/* (3)service객체 생성하기 */
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSession();
		SqlSession sqlSession2 = MyBatisConnectionFactory.getSqlSession();
		StudentService studentService= new StudentServiceImpl(sqlSession2, logger);
		
		//드롭다운을 구성할때 새로 추가되는 구문
		DepartmentService departmentService = new DepartmentServiceImpl(sqlSession, logger);
		ProfessorService professorService = new ProfessorServiceImpl(sqlSession, logger);
		
		/* (4)service를 통한 SQL수행 */
		Student item = null;
		List<Department> dlist = null;
		List<Professor> plist = null;
		try{
			//단일행 조회
			item = studentService.getStudentItem(student);
			//다중행 조회
			dlist= departmentService.getDepartmentList(null);
			plist = professorService.getProfessorList(null);
		}catch(Exception e){
			web.redirect(null,e.getLocalizedMessage());
			return null;
		}finally{
			sqlSession.close();
		}
		
		request.setAttribute("dlist", dlist);
		request.setAttribute("plist", plist);
		request.setAttribute("item", item);
		
		return "stud_edit";
	}
    

}
