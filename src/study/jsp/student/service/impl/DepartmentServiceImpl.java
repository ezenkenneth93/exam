package study.jsp.student.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.Logger;

import study.jsp.student.model.Department;
import study.jsp.student.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService{

	/**(1)처리결과를 기록할 Log4J객체 생성*/
	Logger logger;
	
	/**(2)Mybatis*/
	SqlSession sqlSession;
	
	/**(3)생성자를 통한 객체 생성-->파라미터로 접속정보를 가진 SqlSession객체와 Logger객체를 전달받는다.*/
	public DepartmentServiceImpl(SqlSession sqlSession, Logger logger) {
		this.sqlSession = sqlSession;
		this.logger = logger;
	}
	
	@Override
	public List<Department> getDepartmentList(Department department) throws Exception {
		
		List<Department> result = null;
		
		try {
			result = sqlSession.selectList("DepartmentMapper.selectDepartmentList", department);
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
	public Department getDepartmentItem(Department department) throws Exception {
		Department result = null;
		
		try {
			result = sqlSession.selectOne("DepartmentMapper.selectDepartmentItem", department);
		}catch(NullPointerException e) {
			throw new Exception("조회된 데이터가 없습니다");
		}catch(Exception e) {
			throw new Exception("데이터 조회 실패");
		}finally {
			sqlSession.commit();
		}
		
		return result;
	}

	@Override
	public void addDepartment(Department department) throws Exception {
		try {
			int result = sqlSession.insert("DepartmentMapper.insertDepartmentItem", department);
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
	public void editDepartment(Department department) throws Exception {
		try {
			int result = sqlSession.update("DepartmentMapper.updateDepartmentItem", department);
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
	public void deleteDepartment(Department department) throws Exception {
		try {
			
			int result = sqlSession.delete("DepartmentMapper.deleteDepartmentItem", department);
		}catch (NullPointerException e) {
			sqlSession.rollback();
			throw new Exception("삭제할 데이터가 없습니다.");
		}catch(Exception e) {
			sqlSession.rollback();
			logger.error(e.getLocalizedMessage());
			throw new Exception("데이터 삭제 실패");
		}finally {
			sqlSession.commit();
		}
		
	}

	@Override
	public int getDepartmentCount(Department department) throws Exception {
		int result = 0;
		
		try {
			result = sqlSession.selectOne("DepartmentMapper.selectDepartmentCount", department);
		}catch(Exception e) {
			logger.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}
		
		return result;
	}

	@Override
	public List<Department> getDepartmentJoinList(Department department) throws Exception {
		
		List<Department> result = null;
		try {
			result = sqlSession.selectList("DepartmentMapper.selectDepartmentJoinList", department);
		}catch(NullPointerException e) {
			throw new Exception("조회된 데이터가 없습니다.");
		}catch(Exception e) {
			logger.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다");
		}
		return result;
	}
	
}
