<%@page import="study.jsp.student.service.impl.StudentJoinServiceImpl"%>
<%@page import="study.jsp.student.service.StudentJoinService"%>
<%@page import="study.jsp.student.model.StudentDepartmentProfessor"%>
<%@page import="study.jsp.helper.PageHelper"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="study.jsp.student.dao.MyBatisConnectionFactory"%>
<%@page import="study.jsp.helper.WebHelper"%>
<%@page import="org.apache.logging.log4j.Logger"%>
<%@page import="org.apache.logging.log4j.LogManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<title>My JSP Page</title>
<!-- Twitter Bootstrap3 & jQuery -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="page-header clearfix">
			<h1 class='pull-left'>학생목록</h1>

			<div style='margin-top: 30px;' class="pull-right">
				<form method='get' action='stud_list.do' style="width: 300px;">
					<div class='input-group'>
						<input type="text" name='keyword' class='form-control'
							placeholder="학생이름 검색" value="${keyword}" /> <span
							class="input-group-btn">
							<button class="btn btn-success" type="submit">
								<i class='glyphicon glyphicon-search'></i>
							</button>
						</span>
					</div>
				</form>
			</div>

			<div style='margin-top: 30px' class="pull-right">
				<a href="stud_add.do" class="btn btn-primary">학생추가</a>
			</div>
		</div>

		<!-- 조회 결과를 출력하기 위한 표 시작 -->
		<table class="table">
			<thead>
				<tr>
					<th class="info text-center">학생번호</th>
					<th class="info text-center">이름</th>
					<th class="info text-center">아이디</th>
					<th class="info text-center">학년</th>
					<th class="info text-center">주민번호</th>
					<th class="info text-center">생일</th>
					<th class="info text-center">전화번호</th>
					<th class="info text-center">키</th>
					<th class="info text-center">체중</th>
					<th class="info text-center">학과번호</th>
					<th class="info text-center">학과이름</th>
					<th class="info text-center">교수번호</th>
					<th class="info text-center">교수이름</th>
				</tr>
			</thead>
			<tbody>

				<c:choose>
					<c:when test="${list.size() > 0}">
						<c:forEach var="i" items="${list}" varStatus="status">
							<tr>
								<td class="text-center">${i.getStudno()}</td>
								<td class="text-center"><a
									href="stud_view.do?studno=${i.getStudno()}">${i.getName()}</a></td>
								<td class="text-center">${i.getUserid()}</td>
								<td class="text-center">${i.getGrade()}</td>
								<td class="text-center">${i.getIdnum()}</td>
								<td class="text-center">${i.getBirthdate()}</td>
								<td class="text-center">${i.getTel()}</td>
								<td class="text-center">${i.getHeight()}</td>
								<td class="text-center">${i.getWeight()}</td>
								<td class="text-center">${i.getDeptno()}</td>
								<td class="text-center">${i.getDname()}</td>
								<td class="text-center">${i.getProfno()}</td>
								<td class="text-center">${i.getPname()}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="8" class="text-center">조회된 데이터가 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		<!-- 조회결과를 출력하기 위한 표 끝 -->

		<!-- 페이지 번호 -->
		<nav class='text-center'>
			<ul class="pagination">
				<!-- 이전그룹 -->
				<c:choose>
					<c:when test="${pageHelper.getPrevPage()>0}">
						<li><a
							href="stud_list.do?page=${pageHelper.getPrevPage()}&keyword=${keyword}">
								<span aria-hidden="true">&laquo;</span>
						</a></li>
					</c:when>
					<c:otherwise>
						<li class='disabled'><a href="#"><span aria-hidden="true">&laquo;</span></a>
						</li>
					</c:otherwise>
				</c:choose>


				<!-- 페이지 번호 -->
				<c:forEach var="i" begin="${pageHelper.getStartPage()}"
					end="${pageHelper.getEndPage()}" step="1">
					<c:choose>
						<c:when test="${i == nowPage}">
							<li class='active'><a href="#">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="stud_list.do?page=${i}&keyword=${keyword}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>


				<!-- 다음 그룹 -->
				<c:choose>
					<c:when test="${pageHelper.getNextPage() > 0}">
						<li><a
							href="stud_list.do?page=${pageHelper.getNextPage()}&keyword=${keyword}">
								<span aria-hidden="true">&raquo;</span>
						</a></li>
					</c:when>
					
					<c:otherwise>
						<li class="disabled"><a href="#"><span aria-hidden="true">&raquo;</span></a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>
	</div>
</body>
</html>
