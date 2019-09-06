<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FootStamp에 오신 것을 환영합니다!</title>




<link href="${pageContext.request.contextPath }/resources/bootstrap-dist/css/bootstrap.min.css" rel="stylesheet">
<!-- 부트스트랩 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/style.css">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/tagSearch.css">

<!-- 한글폰트 -->
<link href="http://fonts.googleapis.com/earlyaccess/hanna.css" rel="stylesheet">
<!-- 영문폰트 -->
<link href="https://fonts.googleapis.com/css?family=Nunito+Sans" rel="stylesheet"><!-- font-family: 'Nunito Sans', sans-serif; -->
<link href="https://fonts.googleapis.com/css?family=Tangerine" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<script src="${pageContext.request.contextPath }/resources/bootstrap-dist/js/bootstrap.min.js"></script>


</head>
	<!-- 헤더부 포함 -->
	<%-- <%@include file="header.jsp" %> --%>
	<jsp:include page="header.jsp"/>
<!-- 바디부 시작 -->
<body>
	<div class="container">
	<!-- 메세지 부분 -->
	<span id="tags" class="glyphicon glyphicon-tags" aria-hidden="true"></span><br><br>
	<div class="col-md-12 text-center"style="font-size:15px;">${requestScope.tagName }</div>
	<div class="col-md-12 text-center">${requestScope.tagCnt }</div>
	<div class="col-md-3"></div>
	<div class="col-md-9">인기 게시물</div>
	<div class="col-md-12"><hr></div>
	<!-- 검색 결과 사진 목록 들어가는 부분 -->
	<div class="col-md-12">
		<div class="col-md-3"></div>
		
		<div class="col-md-2">
			<div class="hovereffect imageClick">
				<img src="${pageContext.request.contextPath }/resources/images/${tagResult.storyImg }" alt="..." width="163px" height="163px">
					 <div class="overlay">
	                <h6>♥ ${tagResult.likeCnt }</h6>
						<p> 
							<a href="#">review : ${tagResult.reviewCnt }</a>
						</p> 
		            </div>
            </div>
		</div>
		<div class="col-md-2">
			<div class="hovereffect imageClick">
				<img src="${pageContext.request.contextPath }/resources/images/a5e7e91b081cc7be5f93fa09360dfbb7.png" alt="..." width="163px" height="163px">
					 <div class="overlay">
		                <h6>♥ 12</h6>
						<p> 
							<a href="#">review 13</a>
						</p> 
		            </div>
            </div>
		</div>
		<div class="col-md-2">
			<div class="hovereffect imageClick">
				<img src="${pageContext.request.contextPath }/resources/images/a5e7e91b081cc7be5f93fa09360dfbb7.png" alt="..." width="163px" height="163px">
					 <div class="overlay">
		                <h6>♥ 12</h6>
						<p> 
							<a href="#">review 13</a>
						</p> 
		            </div>
            </div>
		</div>
	</div>
	<div class="col-md-12">
		<div class="col-md-3"></div>
		<div class="col-md-2">
			<div class="hovereffect imageClick">
				<img src="${pageContext.request.contextPath }/resources/images/a5e7e91b081cc7be5f93fa09360dfbb7.png" alt="..." width="163px" height="163px">
					 <div class="overlay">
		                <h6>♥ 12</h6>
						<p> 
							<a href="#">review 13</a>
						</p> 
		            </div>
            </div>
		</div>
		<div class="col-md-2">
			<div class="hovereffect imageClick">
				<img src="${pageContext.request.contextPath }/resources/images/a5e7e91b081cc7be5f93fa09360dfbb7.png" alt="..." width="163px" height="163px">
					 <div class="overlay">
		                <h6>♥ 12</h6>
						<p> 
							<a href="#">review 13</a>
						</p> 
		            </div>
            </div>
		</div>
		<div class="col-md-2">
			<div class="hovereffect imageClick">
				<img src="${pageContext.request.contextPath }/resources/images/a5e7e91b081cc7be5f93fa09360dfbb7.png" alt="..." width="163px" height="163px">
					 <div class="overlay">
		                <h6>♥ 12</h6>
						<p> 
							<a href="#">review 13</a>
						</p> 
		            </div>
            </div>
		</div>
		<div class="col-md-3"></div>
	</div>
	<div class="col-md-12">
		<div class="col-md-3"></div>
		<div class="col-md-2">
			<div class="hovereffect imageClick">
				<img src="${pageContext.request.contextPath }/resources/images/a5e7e91b081cc7be5f93fa09360dfbb7.png" alt="..." width="163px" height="163px">
					 <div class="overlay">
		                <h6>♥ </h6>
						<p> 
							<a href="#">review 13</a>
						</p> 
		            </div>
            </div>
		</div>
		<div class="col-md-2">
			<div class="hovereffect imageClick">
				<img src="${pageContext.request.contextPath }/resources/images/a5e7e91b081cc7be5f93fa09360dfbb7.png" alt="..." width="163px" height="163px">
					 <div class="overlay">
		                <h6>♥ 12</h6>
						<p> 
							<a href="#">review 13</a>
						</p> 
		            </div>
            </div>
		</div>
		<div class="col-md-2">
			<div class="hovereffect imageClick">
				<img src="${pageContext.request.contextPath }/resources/images/a5e7e91b081cc7be5f93fa09360dfbb7.png" alt="..." width="163px" height="163px">
					 <div class="overlay">
		                <h6>♥ 12</h6>
						<p> 
							<a href="#">review 13</a>
						</p> 
		            </div>
            </div>
		</div>
		<div class="col-md-3"></div>
	</div>
	</div>
</body>
<script type="text/javascript">
//댓글 숨기기
$('.reviews').hide();
$(function(){
	//사용자가 선택한 이미지의 경로명 가져오는 부분
	$('.imageClick').click(function(){
		alert($(this).children('img').attr('src'));
	});
});
</script>
</html>