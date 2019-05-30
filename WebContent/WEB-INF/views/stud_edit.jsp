<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>My JSP Page</title>
<!-- Twitter Bootstrap3 & jQuery -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1 class="page-header">학생 수정</h1>
		
		<!-- 추가를 위한 HTML폼 시작 -->
		<form class="form-horizontal" method="post" action="stud_edit_ok.do">
			<div class="form-group hidden">
				<label for="studno" class="col-sm-2 control-label">학생번호</label>
				<div class='col-sm-10'>
					<input type="text" class="form-control hidden" id="studno" name="studno" value="${item.getStudno()}">
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">학생이름</label>
				<div class='col-sm-10'>
					<input type="text" class="form-control" id="name" name="name" value="${item.getName()}">
				</div>
			</div>
			<div class="form-group">
				<label for="userid" class="col-sm-2 control-label">아이디</label>
				<div class='col-sm-10'>
					<input type="text" class="form-control" id="userid" name="userid" value="${item.getUserid()}">
				</div>
			</div>
			<div class="form-group">
				<label for="grade" class="col-sm-2 control-label">학년</label>
				<div class='col-sm-10'>
					<input type="text" class="form-control" id="grade" name="grade" value="${item.getGrade()}">
				</div>
			</div>
			<div class="form-group">
				<label for="idnum" class="col-sm-2 control-label">주민번호</label>
				<div class='col-sm-10'>
					<input type="text" class="form-control" id="idnum" name="idnum" value="${item.getIdnum()}">
				</div>
			</div>
			<div class="form-group">
				<label for="birthdate" class="col-sm-2 control-label">생일</label>
				<div class='col-sm-10'>
					<input type="text" class="form-control" id="birthdate" name="birthdate" value="${item.getBirthdate()}">
				</div>
			</div>
			<div class="form-group">
				<label for="tel" class="col-sm-2 control-label">전화번호</label>
				<div class='col-sm-10'>
					<input type="text" class="form-control" id="tel" name="tel" value="${item.getTel()}">
				</div>
			</div>
			<div class="form-group">
				<label for="height" class="col-sm-2 control-label">키</label>
				<div class='col-sm-10'>
					<input type="text" class="form-control" id="height" name="height" value="${item.getHeight()}">
				</div>
			</div>
			<div class="form-group">
				<label for="weight" class="col-sm-2 control-label">체중</label>
				<div class='col-sm-10'>
					<input type="text" class="form-control" id="weight" name="weight" value="${item.getWeight()}">
				</div>
			</div>
			<div class="form-group">
				<label for="deptno" class="col-sm-2 control-label">소속학과</label>
				<div class='col-sm-10'>
					<select name="deptno" class="form-control">
						<option value="">----소속학과를 선택하세요----</option>
						<c:forEach var="d" items="${dlist}" varStatus="status">
							<c:choose>
								<c:when test="${d.getDeptno() == item.getDeptno()}">
									<option value="${d.getDeptno()}" selected>${d.getDname()}</option>
								</c:when>
								<c:otherwise>
									<option value="${d.getDeptno()}">${d.getDname()}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="profno" class="col-sm-2 control-label">담당교수</label>
				<div class='col-sm-10'>
					<select id="profno" name="profno" class="form-control">
						<option value="">----담당교수를 선택하세요----</option>
						<c:forEach var="p" items="${plist}" varStatus="status">
							<c:choose>
								<c:when test="${p.getProfno() == item.getProfno()}">
									<option value="${p.getProfno()}" selected>${p.getName()}</option>
								</c:when>
								<c:otherwise>
									<option value="${p.getProfno()}">${p.getName()}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
				<div class='col-sm-offset-2 col-sm-10'>
					<button type="submit" class="btn btn-default">수정하기</button>
				</div>
			</div>
		</form>
		<!-- 추가를 위한 HTML폼 끝 -->
	</div>
</body>
</html>