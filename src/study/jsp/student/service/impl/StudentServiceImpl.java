package study.jsp.student.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.Logger;

import study.jsp.student.model.Student;
import study.jsp.student.service.StudentService;

public class StudentServiceImpl implements StudentService {
	
	/**처리결과를 기록할 Log4j객체 생성*/
	Logger logger;
	
	/**SqlSession 참조변수 선언*/
	SqlSession sqlSession;
	
	/**생성자파라미터를 통해서 전달받음*/
	public StudentServiceImpl(SqlSession sqlSession, Logger logger) {
		this.logger = logger;
		this.sqlSession = sqlSession;
	}

	@Override
	public void addStudent(Student student) throws Exception {
		try {
			int result = sqlSession.insert("StudentMapper.insertStudentItem", student);
		}catch(NullPointerException e) {
			sqlSession.rollback();
			throw new Exception("저장된 데이터가 없습니다");
		}catch(Exception e) {
			sqlSession.rollback();
			logger.error(e.getLocalizedMessage());
			throw new Exception("데이터 저장에 실패했습니다.");
		}finally {
			sqlSession.commit();
		}
		
	}

	@Override
	public void editStudent(Student student) throws Exception {
		try {
			int result = sqlSession.update("StudentMapper.updateStudentItem", student);
		}catch(NullPointerException e) {
			sqlSession.rollback();
			throw new Exception("수정된 데이터가 없습니다");
		}catch(Exception e){
			sqlSession.rollback();
			logger.error(e.getLocalizedMessage());
			throw new Exception("데이터 수정에 실패했습니다.");
		}
		finally {
			sqlSession.commit();
		}
		
	}

	@Override
	public void deleteStudent(Student student) throws Exception {
		try {
			int result = sqlSession.delete("StudentMapper.deleteStudentItem", student);
		}catch(NullPointerException e) {
			sqlSession.rollback();
			throw new Exception("삭제할 데이터가 없습니다");
		}catch(Exception e) {
			sqlSession.rollback();
			logger.error(e.getLocalizedMessage());
			throw new Exception("데이터 삭제 실패");
		}finally {
			sqlSession.commit();
		}
		 
		
	}

	@Override
	public Student getStudentItem(Student student) throws Exception {
		Student result = null;
		
		try {
			result = sqlSession.selectOne("StudentMapper.selectStudentItem", student);
		}catch(NullPointerException e) {
			throw new Exception("조회된 데이터가 없습니다");
		}catch(Exception e) {
			logger.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회 실패");
		}finally {
			sqlSession.commit();
		}
		
		return result;
	}

	@Override
	public List<Student> getStudentList(Student student) throws Exception {
		List<Student> result = null;
		
		try {
			result = sqlSession.selectList("StudentMapper.selectStudentList");
		}catch(NullPointerException e) {
			throw new Exception("조회된 데이터가 없습니다");
		}catch(Exception e) {
			throw new Exception("데이터 조회 실패");
		}finally {
			sqlSession.commit();
		}
		return result;
	}
	
}
