<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FootStamp에 오신 것을 환영합니다</title>

<link
	href="${pageContext.request.contextPath }/resources/bootstrap-dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- 부트스트랩 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/styles/ion.calendar.css">
<link
	href="${pageContext.request.contextPath }/resources/styles/message.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/styles/style.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/styles/diary.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/styles/mapStyle.css">
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<script
	src="${pageContext.request.contextPath }/resources/bootstrap-dist/js/bootstrap.min.js"></script>


<!-- Latest compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/js/bootstrap-select.min.js"></script>

<!-- (Optional) Latest compiled and minified JavaScript translation files -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/js/i18n/defaults-*.min.js"></script>

<!-- 구글키 -->
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAN3ihyeLl7l0tNw4lN33p4cE53DXS0Gy0&libraries=places"
	type="text/javascript"></script>
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath }/resources/scripts/message.js"></script>
<script
	src="${pageContext.request.contextPath }/resources/scripts/customMap.js"></script>
<script
	src="${pageContext.request.contextPath }/resources/scripts/customMap2.js"></script>

<!-- 한글폰트 -->
<link href="http://fonts.googleapis.com/earlyaccess/hanna.css"
	rel="stylesheet">
<link href="http://fonts.googleapis.com/earlyaccess/nanumgothiccoding.css" rel="stylesheet">
<!-- 영문폰트 -->
<link href="https://fonts.googleapis.com/css?family=Nunito+Sans"
	rel="stylesheet">
<!-- font-family: 'Nunito Sans', sans-serif; -->
<link href="https://fonts.googleapis.com/css?family=Tangerine"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans"
	rel="stylesheet">
</head>
<!-- 헤더부 포함 -->
<%-- <%@include file="header.jsp" %> --%>
<jsp:include page="header.jsp" />
<!-- 바디부 시작 -->
<body>
	<div class="container">
		<!-- 메세지 아이콘 팝오버 -->
		<jsp:include page="message.jsp"/>
		<div id="icon" class="col-md-1 col-md-offset-6">
			<div id="message" data-container="body" data-placement="bottom"
				data-html="true" data-content="너 나오긴해?">
				<!-- 새로운 메시지가있는지 확인한뒤 있을시 !를 추가한다 -->
				<div>
					<!-- style="top:-40px;" -->
					<span id="messenger" class="glyphicon glyphicon-send"
						aria-hidden="true"></span>
				</div>
				<c:if test="${cnt != '0' }">
					<div id="alarm">!</div>
				</c:if>
			</div>
		</div> 
	</div>
	<!-- 달력넣는부분하고싶다! -->
	
	<!-- 상세보기창 -->
	<jsp:include page="showStory.jsp" />
</body>
<script type="text/javascript">
	//댓글 숨기기
	$('.reviews').hide();
	$(function() {
		$('.diary_review').click(function() {
			$('.reviews').show();
		});
	});
</script>
</html>
