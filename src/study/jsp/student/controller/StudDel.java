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

@WebServlet("/stud_delete.do")
public class StudDel extends BaseController {
	private static final long serialVersionUID = -202652410443175912L;

	@Override
	public String doRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 이 페이지는 stud_view로부터 studno을 전달받고 해당 데이터를 DB에서 삭제하는 기능을 한다. */
		
		/**(1) WebHelper, Logger만들기*/
		WebHelper web = WebHelper.getInstance(request, response);
		Logger logger = LogManager.getFormatterLogger();
		
		/**(2) 전달된 파라미터 수신하기, 수신후에는 로그에 출력해보는게 좋다*/
		int studno = web.getInt("studno");
		logger.debug("studno=" + studno);

		if(studno == 0){
			web.redirect(null,"삭제할 데이터가 없습니다!");
			return null;
		}
		/**(3) 전달된 파라미터를 포함하는 Beans객체 생성*/
		Student student = new Student();
		student.setStudno(studno);
		
		/**(4) DB접속처리*/
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSession();
		
		/**(5) Service객체 생성*/
		StudentService studentService = new StudentServiceImpl(sqlSession, logger);
		
		/**(6) Service객체를 사용하여 SQL구문 실행, 예외처리(예외가 발생하면 프로그램 종료)*/
		try{
			studentService.deleteStudent(student);
		}catch(Exception e){
			web.redirect(null, e.getLocalizedMessage());
			return null;
		}finally{
			sqlSession.close();
		}
		
		/**(7) 교수 리스트로 페이지 이동, 삭제완료 메시지 출력*/
		web.redirect("stud_list.do", "데이터삭제 완료");
		
		return null;
	}
    

}
