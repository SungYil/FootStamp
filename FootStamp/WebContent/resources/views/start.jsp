<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!-- jquery -->
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- 부트스트랩 -->
<link href="${pageContext.request.contextPath }/resources/bootstrap-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath }/resources/bootstrap-dist/js/bootstrap.min.js"></script>

<!-- css -->
<link href="${pageContext.request.contextPath }/resources/styles/start_nav.css" rel="stylesheet">

<!-- script -->
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/scripts/login.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/scripts/join.js"></script>
<title>Insert title here</title>
</head>
<body>
	<!-- 메인 네비게이션 -->
	<nav class="navbar navbar-default">
		<div class="container">
			<!--타이틀 -->
			<div class="col-md-3">
				<div class="navbar-header">
					<a id="title" class="navbar-brand" href="${pageContext.request.contextPath }/Main.do?page=start">FootStamp</a>
				</div>
			</div>
			<!--타이틀  끝-->
			<!-- 로그인 -->
			<form id="login_form" action="${pageContext.request.contextPath }/member.do" method="post">
				<div class="col-md-3 col-md-offset-2">
					<input id="user_id" name="user_id" type="text" class="form-control" placeholder="아이디">
				</div>
				<div class="col-md-3">
					<input id="user_pwd" name="user_pwd" type="password" class="form-control" placeholder="비밀번호">
				</div>
				<div class="col-md-1">
					<input id="login_btn" class="btn btn-info" type="button" value="로그인">
				</div>
				<div class="col-md-3 col-md-offset-2">
					<input id="login_maintain" name="login_maintain" type="checkbox" value="maintain"> 
					<label for="login_maintain">로그인 유지</label>
				</div>
				<div class="col-md-3">
					<a href="${pageContext.request.contextPath }/member.do?type=find_page&target=id">아이디</a>
					/
					<a href="${pageContext.request.contextPath }/member.do?type=find_page&target=pwd">비밀번호 찾기</a>
				</div>
			</form>
			<!-- 로그인  끝-->
		</div>
	</nav>
	<!-- 메인 네비게이션 끝 -->
	
	<!-- 메인 container -->
	<div class="container">
		<div class="col-md-6">
			<img alt="이미지가 없습니다." src="${pageContext.request.contextPath }/resources/images/23.jpg">
		</div>
		<div class="col-md-5">
			<div class="panel panel-warning">
				<div class="panel-body">
					<h1>회원가입</h1>
					<br>
					<!-- 회원가입 form -->
					<form id="join_form" action="${pageContext.request.contextPath }/member.do" method="post">
						<div class="form-group">
							<input id="juser_id" name="juser_id" type="text" class="form-control" placeholder="아이디">
						</div>
						<div class="form-group">
							<input id="juser_pwd" name="juser_pwd" type="password" class="form-control" placeholder="비밀번호">
						</div>
						<div class="form-group">
							<input id="juser_pwd_check" name="juser_pwd_check" type="password" class="form-control" placeholder="비밀번호 확인">
						</div>
						<div class="form-group">
							<input id="juser_name" name="juser_name" type="text" class="form-control" placeholder="이름">
						</div>
						<div class="form-group">
							<input id="juser_call" name="juser_call" type="text" class="form-control" placeholder="연락처">
						</div>
						<div class="form-group">
							<a href="#">약관 및 개인정보 취급방침</a>
							<input id="agree" type="checkbox" name="agree" value="agree">
						</div>
						<!-- 오류 메시지 제공하는 부분 -->
						<div class="error_message">
						  	
						</div>
						<div class="form-group">
							<input id="join_btn" class="btn btn-info" type="button" value="가입하기">
						</div>
					</form><!-- 회원 가입 form 끝 -->
				</div><!-- 패널 body 끝 -->
			</div><!-- 패널 끝 -->
		</div><!-- col 끝 -->
	</div><!-- 메인 container 끝 -->
</body>
</html>