<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>My JSP Page</title>
<!-- Twitter Bootstrap3 & jQuery -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
<script src="http://code.jquery.com/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1 class="page-header">학생 상세보기</h1>

		<!-- 조회결과를 출력하기 위한 표 시작 -->
		<table class="table table-bordered">
			<tbody>
				<tr>
					<th class="info text-center">학생번호</th>
					<td>${item.getStudno()}</td>
				</tr>
				<tr>
					<th class="info text-center">이름</th>
					<td>${item.getName()}</td>
				</tr>
				<tr>
					<th class="info text-center">아이디</th>
					<td>${item.getUserid()}</td>
				</tr>
				<tr>
					<th class="info text-center">학년</th>
					<td>${item.getGrade()}</td>
				</tr>
				<tr>
					<th class="info text-center">주민번호</th>
					<td>${item.getIdnum()}</td>
				</tr>
				<tr>
					<th class="info text-center">생일</th>
					<td>${item.getBirthdate()}</td>
				</tr>
				<tr>
					<th class="info text-center">전화번호</th>
					<td>${item.getTel()}</td>
				</tr>
				<tr>
					<th class="info text-center">키</th>
					<td>${item.getHeight()}</td>
				</tr>
				<tr>
					<th class="info text-center">체중</th>
					<td>${item.getWeight()}</td>
				</tr>
				<tr>
					<th class="info text-center">학과이름</th>
					<td>${item.getDname()}</td>
				</tr>
				<tr>
					<th class="info text-center">담당교수</th>
					<td>${item.getPname()}</td>
				</tr>
			</tbody>
		</table>
		<!-- 조회결과를 출력하기 위한 표 끝 -->

		<!-- 버튼 시작 -->
		<div class="text-center">
			<a href="stud_list.do" class="btn btn-primary">목록</a> 
		<a href="stud_add.do" class="btn btn-info">추가</a> 
		<a href="stud_edit.do?studno=${item.getStudno()}" class="btn btn-warning">수정</a>
			<a href="stud_delete.do?studno=${item.getStudno()}" class="btn btn-danger">삭제</a>
		</div>
	</div>
</body>
</html>