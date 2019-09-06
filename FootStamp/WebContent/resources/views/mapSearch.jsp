<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FootStamp에 오신 것을 환영합니다!</title>

<!-- --------------------------------------------------------------css파일----------------------------------------------------------------- -->
<!-- 부트스트랩 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/styles/style.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/styles/diary.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/styles/map.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/styles/mapStyle.css">


<!-- 한글폰트 -->
<link href="http://fonts.googleapis.com/earlyaccess/hanna.css"
	rel="stylesheet">
<link
	href="http://fonts.googleapis.com/earlyaccess/nanumgothiccoding.css"
	rel="stylesheet">
<!-- 영문폰트 -->
<link href="https://fonts.googleapis.com/css?family=Nunito+Sans"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Tangerine"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.css" />
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/css/bootstrap-select.min.css">

<!-- --------------------------------------------------------------jquery파일----------------------------------------------------------------- -->
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>
<!--구글키 -->
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAN3ihyeLl7l0tNw4lN33p4cE53DXS0Gy0&libraries=places"
	type="text/javascript"></script>

<!-- --------------------------------------------------------------js파일----------------------------------------------------------------- -->
<script type="text/javascript" src="//cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/js/bootstrap-select.min.js"></script>
<script	src="${pageContext.request.contextPath }/resources/scripts/customMap.js"></script>
<script	src="${pageContext.request.contextPath }/resources/scripts/searchDiary.js"></script>
</head>
<!-- 바디부 시작 -->
<body>
	<!-- 헤더부 포함 -->
	<jsp:include page="header.jsp" />
	<div class="container">
		<div class="col-md-6" style="margin-left: 250px; margin-top: 25px;">
			<form id="dayForm"
				action="${pageContext.request.contextPath}/SearchStory">
				<!-- 검색한 지명 들어가는 부분 -->
				<div class="col-md-12" style="font-family: nanumgothiccoding;">
					<a style="color: #FF5A5A; font-family: hanna; font-size: 40px;">${requestScope.locationName }</a>
					검색결과
				</div>
				<!-- 지도 이미지 들어가는 곳 -->
				<div id="registMap"></div>
				<!-- 지도 이미지 들어가는 곳 -->
			</form>
		</div>
	</div>
	<!-- 상세보기창 -->
	<jsp:include page="showStory.jsp" />
	<!-- container끝 -->
	<!-- Modal시작 -->
	<div class="modal fade" id="showModal"
		style="font-family: nanumgothiccoding; font-size: 11px;">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- 모달 헤더 -->
				<div class="modal-header">
					<!-- 헤더부 구분하기 위해 -->
					<div class="container-fluid">
						<div class="col-md-6">
							<span style="margin-top: 5px; color: #FF5A5A;"
								class="glyphicon glyphicon-map-marker" aria-hidden="true">
								<a id="location">지명정보입력부분</a>
							</span>
						</div>
						<!-- 날짜 및 날씨 정보 표기 부분 -->
						<div class="col-md-6" style="margin-top: 5px;">
							2016.01.20(화)11:30 맑음</div>
					</div>
				</div>
				<div class="modal-body">
					<!-- 모달 내의 영역 구분을 위해 준다. -->
					<div class="container-fluid">
						<!-- 사진파일 이미지 넣는 부분 -->
						<div class="row-fluid">
							<div class="col-md-6">
								<img class="img-rounded"
									src="../images/a5e7e91b081cc7be5f93fa09360dfbb7.png"
									id="story_image" alt="..." width="260px" height="256px">
							</div>
							<div class="col-md-6">
								작성자 : <label style="font-weight: bold;">ehddlrvv</label>
								<hr>
							</div>
							<!-- 스토리 내용 넣는 부분 -->
							<div class="col-md-6 pre-scrollable" style="height: 190px;">원장은
								국회의 동의를 얻어 대통령이 임명하고, 그 임기는 4년으로 하며, 1차에 한하여 중임할 수 있다. 국회는 선전포고,
								국군의 외국에의 파견 또는 외국군대의 대한민국 영역안에서의 주류에 대한 동의권을 가진다. 탄핵결정은 공직으로부터
								파면함에 그친다. 그러나, 이에 의하여 민사상이나 형사상의 책임이 면제되지는 아니한다.수 있다. 국회는 선전포고,
								국군의 외국에의 파견 또는 외국군대의 대한민국 영역안에서의 주류에 대한 동의권을 가진다. 탄핵결정은 공직으로부터
								파면함에 그친다. 그러나, 이에 의하여 민사상이나 형사상의 책임이 면제되지는 아니한다.</div>
						</div>
						<!-- 하단의 공개여부 목록 넣는 부분 -->
						<div class="col-md-12">
							<hr>
							<!-- 좋아요 부분 -->
							<div class="col-md-2">
								<span class="glyphicon glyphicon-heart-empty icons"
									style="font-size: 13px;" aria-hidden="true"> <a>x13</a></span>
							</div>
							<!-- 댓글 부분 -->
							<div class="col-md-2" style="margin-left: 10px;">
								<span class="glyphicon glyphicon-comment icons"
									style="font-size: 13px;" aria-hidden="true"> <a>x17</a></span>
							</div>
							<!-- 공유하기 부분 -->
							<div class="col-md-2" style="margin-left: 10px;">
								<span class="glyphicon glyphicon-share-alt icons"
									style="font-size: 13px;" aria-hidden="true">
							</div>
						</div>
						<!-- 댓글 정보 목록 -->
						<div class="col-md-12 reviews">
							<div class="col-md-12">
								<hr>
							</div>
							<!-- 댓글에 scroll주기 -->
							<div class="col-md-12 pre-scrollable" style="height: 100px;">
								<span class="icons"> <a> <img
										src="http://placehold.it/20x20" alt="..." class="img-circle">
										<!-- 계정아이디와 프로필 사진, 날짜 들어가는 부분 --> ehddlrvv
								</a>&nbsp;&nbsp; 지도일기 너무 이쁘네요<a style="float: right">2016.12.12</a></span><br>
								<span class="reviews icons"> <a> <img
										src="http://placehold.it/20x20" alt="..." class="img-circle">
										<!-- 계정아이디와 프로필 사진, 날짜 들어가는 부분 --> ehddlrvv
								</a>&nbsp;&nbsp; 지도일기 너무 이쁘네요<a style="float: right">2016.12.12</a></span><br>
								<span class="reviews icons"> <a> <img
										src="http://placehold.it/20x20" alt="..." class="img-circle">
										<!-- 계정아이디와 프로필 사진, 날짜 들어가는 부분 --> ehddlrvv
								</a>&nbsp;&nbsp; 지도일기 너무 이쁘네요<a style="float: right">2016.12.12</a></span><br>
							</div>
						</div>
					</div>
				</div>
				<!-- Modal body끝 -->
			</div>
			<!-- Modal Content끝 -->


		</div>
	</div>
<!-- 헤더부 포함 -->
   <jsp:include page="header.jsp"/>
   <div class="container">
      <div class="col-md-6" style="margin-left:250px;margin-top:25px;">
         <form id="dayForm" action="${pageContext.request.contextPath}/SearchStory">
	       	<!-- 검색한 지명 들어가는 부분 -->
	       	<div class="col-md-12" style="font-family:nanumgothiccoding;">
	       	<a id="locationName" style="color:#FF5A5A;font-family:hanna;font-size:40px;">${requestScope.locationName}</a> 검색결과</div>  
	      
	         <!-- 지도 이미지 들어가는 곳 -->
	         <div class="col-md-12" id="registMap" width="300px" height="300px"></div>
	         <!-- 지도 이미지 들어가는 곳 -->
         </form>
      </div>      
   </div>
<!-- container끝 -->
    
	         
	<!-- 상세보기 Modal!! -->
	<jsp:include page="showStory.jsp"/>
	
   <input id="pac-input" class="controls" type="text"/>
</body>
<script type="text/javascript">
	$('#showModal').modal('show');
	$('.reviews').hide();
	$('.glyphicon-comment').click(function() {
		$('.reviews').show();
	});
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/map.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/header.js"></script>

</html>
