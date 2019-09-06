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
<link href="${pageContext.request.contextPath }/resources/styles/login_nav.css" rel="stylesheet">

<!-- script -->
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/scripts/reLogin.js"></script>

<title>재 로그인</title>
</head>
<body>
	<div class="container">
		<form id="login_form" action="${pageContext.request.contextPath }/member.do" method="post">
			<!-- 타이틀-->
			<div class="form-group">
				<a id="title" href="${pageContext.request.contextPath }/Main.do?page=start">FootStamp</a>
			</div>
			<div class="form-group">
				<input type="text" id="user_id" name="user_id" class="form-control input-lg"
					placeholder="아이디">
			</div>
			<div class="form-group">
				<input type="password" id="user_pwd" name="user_pwd"
					class="form-control input-lg" placeholder="비밀번호">
			</div>
			<div class="form-group error_message">
				${requestScope.error }
			</div>
			<div class="form-group">
				<input id="login_btn" class="btn btn-lg btn-primary btn-block" type="button" value="로그인">
			</div>
			<div class="form-group">
				<div class="col-md-3">	
				<label> 
					<input type="checkbox" name="login_maintain" value="maintain">로그인 유지
				</label>
				</div>
				<div class="col-md-4 col-md-offset-5">	
					<a href="${pageContext.request.contextPath }/member.do?type=find_page&target=id">아이디</a> / <a href="${pageContext.request.contextPath }/member.do?type=find_page&target=pwd">비밀번호 찾기</a>
				</div>
			</div>
		</form>

	</div>
	<!-- /container -->
</body>
</html>