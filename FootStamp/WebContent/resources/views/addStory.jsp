<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FootStamp에 오신 것을 환영합니다!</title>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/css/bootstrap-select.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<link href="http://fonts.googleapis.com/earlyaccess/nanumgothiccoding.css" rel="stylesheet">

<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- Latest compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/js/bootstrap-select.min.js"></script>

<!-- (Optional) Latest compiled and minified JavaScript translation files -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/js/i18n/defaults-*.min.js"></script>

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>



<!-- 바디부 시작 -->
<body>

	<%-- <!-- Modal시작 -->
	<div class="modal fade" id="registerModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- 데이터 전송하기 위해 만든 부분 -->
				<form action="${pageContext.request.contextPath}/AddStory" enctype="multipart/form-data" accept-charset="utf-8"	id="storyForm">
				<!-- 모달 헤더 -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<!-- 헤더부 구분하기 위해 쓴다. -->
					<div class="container-fluid">								
						<div class="col-md-4" style="float:left;">
							<span style="margin-top:10px;color:#FF5A5A;"class="glyphicon glyphicon-map-marker" aria-hidden="true">
								<a id="location">지명정보입력부분</a>
							</span>
						</div>
						<!-- 날짜 및 날씨 정보 표기 부분 -->
						<div class="col-md-4" style="margin-top:5px;">
							<!-- 시간 정보 시, 분 선택 부분 시작 -->
							<div class="col-md-2">
								<select id="hour"class="selectpicker show-tick" data-width="fit" title="12" data-header="시(hour)">
								  <c:forEach var="i" begin="1" end="24">
								  	<option>${i}</option>
								  </c:forEach>
								</select>
							</div>
							<div class="col-md-1 text-center" style="padding:0px;padding-left:52px;padding-top:5px;">:</div>
							<!-- 분 -->
							<div class="col-md-2">
							<select id="min" class="selectpicker show-tick" data-width="fit"  title="00" data-header="분(min)">
							  <c:forEach var="i" begin="0" end="59">
							  	<option>${i}</option>
							  </c:forEach>
							</select>
							</div>
							</div>
							<!-- 시간 정보 시, 분 선택 부분 끝 -->
							<!-- 날씨 입력 부분 -->
							<div class="col-md-4" style="margin-top:5px;">
								<select id="weather" class="selectpicker show-tick" data-width="fit">
								   	<option>맑음</option>
								   	<option>흐림</option>
								   	<option>비</option>
								   	<option>눈</option>
								</select>
							</div>
							<!-- 날씨 입력 부분 끝 -->
					</div>
				</div>
				<div class="modal-body">
					<!-- 모달 내의 영역 구분을 위해 준다. -->
					<div class="container-fluid">
							<!-- 사진파일 이미지 넣는 부분 -->
							<div class="col-md-6">
								<input type="file" name="file" id="file" class="inputfile" />
								 <label	for="file">Choose a file (Click me)</label>
							</div>
							<!-- 스토리 내용 넣는 부분 -->
							<div class="col-md-6">
								<textarea id="story_content" class="form-control" rows="12"></textarea>
							</div>
							<!-- 하단의 공개여부 목록 넣는 부분 -->
							<div class="col-md-12">
								<hr>
								<div class="col-md-7">
										<select id="open" class="selectpicker show-tick" data-width="fit">
										   	<option id="yes" data-icon="glyphicon-globe">공개</option>
										   	<option id="no" data-icon="glyphicon-lock">비공개</option>
										</select>
										<select id="share" class="selectpicker show-tick" data-width="fit">
										   	<option data-icon="glyphicon-share">공유</option>
										   	<option data-icon="glyphicon-lock">비공유</option>
										</select>
								</div>
								<div class="col-md-5">
										<button class="btn text-center" style="margin-left:70px;background-color:#FF5A5A;border-color:#FF5A5A;color:white;" 
											type="button" id="confirm_btn">저장</button>
										<button type="button"
											class="btn btn-default text-center" id="confirm_btn">취소</button>
								
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div> --%>
</body>

<script src="${pageContext.request.contextPath}/resources/scripts/addStory.js"></script>
</html>