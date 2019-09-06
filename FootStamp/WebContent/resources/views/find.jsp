<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- jquery -->
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- 부트스트랩 -->
<link href="${pageContext.request.contextPath }/resources/bootstrap-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath }/resources/bootstrap-dist/js/bootstrap.min.js"></script>

<!-- css -->
<link href="${pageContext.request.contextPath }/resources/styles/find_nav.css" rel="stylesheet">

<!-- script -->
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/scripts/find_page.js"></script>
<title>찾기</title>
</head>
<body>
	<!-- 메인 네비게이션 -->
	<nav class="navbar navbar-default">
		<div class="container">
			<!--타이틀 -->
			<div class="navbar-header">
				<div class="collapse navbar-collapse navbar-default">
					<ul class="nav nav-pills pull-right nav-filter">
					<c:choose>
						<c:when test="${requestScope.type=='id' }">
							<li id="find_id" data-toggle="tab" class="active">
								<a role="presentation"  href="#"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>아이디 찾기</a>
							</li>
							<li id="find_pwd" data-toggle="tab">
								<a role="presentation" href="#"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>비밀번호 찾기</a>
							</li>
						</c:when>
						<c:otherwise>
							<li id="find_id" data-toggle="tab">
								<a role="presentation"  href="#"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>아이디 찾기</a>
							</li>
							<li id="find_pwd" data-toggle="tab" class="active">
								<a role="presentation" href="#"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>비밀번호 찾기</a>
							</li>
						</c:otherwise>
					</c:choose>
						
					</ul>
				</div>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="col-md-6">
			<div id="window" class="panel panel-warning">
				<div id="find_id_window" class="panel-body">
					<h1>아이디 찾기</h1>
					<h3>연락처,이메일을 입력하세요</h3>
					<form action="${pageContext.request.contextPath }/member.do" method="get">
						<input id="find_id_input" name="find_id_input" type="text" class="form-control" placeholder="이메일 or 연락처">
					</form>
					<div class="form-group">
						<input id="id_check_btn" class="btn btn-info" type="button" value="확인">
						<a class="btn btn-info back_btn"  href="${pageContext.request.contextPath }/Main.do?page=start">돌아가기</a>
					</div>
				</div>
				<div id="find_pwd_window" class="panel-body">
					<h1>비밀번호 찾기</h1>
					<h3>연락처,이메일을 입력하세요</h3>
					<form action="" method="get">
						<input id="find_pwd_input" name="find_pwd_input" type="text" class="form-control" placeholder="아이디 or 이메일 or 연락처">
					</form>
					<div class="form-group">
						<input id="pwd_check_btn" class="btn btn-info" type="button" value="확인">
						<a class="btn btn-info back_btn"  href="${pageContext.request.contextPath }/Main.do?page=start">돌아가기</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>