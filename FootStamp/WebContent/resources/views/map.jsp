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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/diary.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/map.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/mapStyle.css">


<!-- 한글폰트 -->
<link href="http://fonts.googleapis.com/earlyaccess/hanna.css" rel="stylesheet">
<link href="http://fonts.googleapis.com/earlyaccess/nanumgothiccoding.css" rel="stylesheet">
<!-- 영문폰트 -->
<link href="https://fonts.googleapis.com/css?family=Nunito+Sans" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Tangerine" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
<link rel="stylesheet"   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.css" /><!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/css/bootstrap-select.min.css">

<!-- --------------------------------------------------------------jquery파일----------------------------------------------------------------- -->
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script> 
<!--구글키 -->
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAN3ihyeLl7l0tNw4lN33p4cE53DXS0Gy0&libraries=places"
	type="text/javascript"></script>

<!-- --------------------------------------------------------------js파일----------------------------------------------------------------- -->
<script type="text/javascript" src="//cdn.jsdelivr.net/momentjs/latest/mo
ment.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/js/bootstrap-select.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/scripts/customMap.js"></script>
<script src="${pageContext.request.contextPath }/resources/scripts/searchDiary.js"></script>

</head>
<!-- 바디부 시작 -->
<body>
<!-- 헤더부 포함 -->
   <jsp:include page="header.jsp"/>
   <div class="container">
      <div class="col-md-6" style="margin-left:250px;margin-top:50px;">
         <form id="dayForm" action="${pageContext.request.contextPath}/Story.do">
         <span class="glyphicon glyphicon-calendar" aria-hidden="true">
            <!-- 현재날짜 정보 받아와 출력해야하는 부분 -->
            <span id="cur_date">2016.01.03(화)13:24</span>
         </span>
         <!-- 데이터피커 --> 
         <button id="daterange" type="button" class="btn btn-default">
      	  날짜 선택</button><br>
         <!-- 지도 이미지 들어가는 곳 -->
         <div id="registMap"></div>
         <!-- 지도 이미지 들어가는 곳 -->
         </form>
      </div>      
   </div>
   <!-- 상세보기창 -->
	<jsp:include page="showStory.jsp" />
<!-- container끝 -->
   <!-- modal 포함 --><!-- Modal시작 -->
   <div class="modal fade" id="registerModal">
      <div class="modal-dialog">
         <div class="modal-content">
            <!-- 데이터 전송하기 위해 만든 부분 -->
            <form action="${pageContext.request.contextPath}/Story.do" enctype="multipart/form-data"
               id="storyForm">
             
            <!-- 모달 헤더 -->
            <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal"
                  aria-label="Close">
                  <span aria-hidden="true">&times;</span>
               </button>
               <!-- 헤더부 구분하기 위해 쓴다. -->
               <div class="container-fluid">                        
                  <div class="col-md-4" style="float:left;">
                     <span id="modalLocation" style="margin-top:10px;color:#FF5A5A;"class="glyphicon glyphicon-map-marker" aria-hidden="true">지명정보입력부분</span>
                  </div>
                  <!-- 날짜 및 날씨 정보 표기 부분 -->
                  <div class="col-md-4" style="margin-top:5px;">
                     <!-- 시간 정보 시, 분 선택 부분 시작 -->
                     <div class="col-md-2">
                        <select id="hour" name="hour" class="selectpicker show-tick" data-width="fit" title="12" data-header="시(hour)">
                          <c:forEach var="i" begin="1" end="24">
                             <option>${i}</option>
                          </c:forEach>
                        </select>
                     </div>
                     <div class="col-md-1 text-center" style="padding:0px;padding-left:52px;padding-top:5px;">:</div>
                     <!-- 분 -->
                     <div class="col-md-2">
                     <select id="min" name="min" class="selectpicker show-tick" data-width="fit"  title="00" data-header="분(min)">
                       <c:forEach var="i" begin="0" end="59">
                          <option>${i}</option>
                       </c:forEach>
                     </select>
                     </div>
                    </div>
                     <!-- 시간 정보 시, 분 선택 부분 끝 -->
                     <!-- 날씨 입력 부분 -->
                     <div class="col-md-4" style="margin-top:5px;">
                        <select id="weather" name="weather" class="selectpicker show-tick" data-width="fit">
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
                    	
                     	  <!-- 이미지 입력받는부분 -->
               		<input type="file" name="file" id="file" accept="image/*" class="inputfile" onchange="showFile(this,1)" style="display:none" />
                    
                       	<div id="file_input_space">
								<label for="file" id="choose_file" style="color:#FF5A5A;"><span class="glyphicon glyphicon-plus"></span>
								Choose a file (Click me)</label>
						</div>
						<div id="sel_image_space" style="display:none">
							
						</div>
								<!-- <img class="img-rounded"
                           src="../images/a5e7e91b081cc7be5f93fa09360dfbb7.png"
                           id="story_image" alt="..." width="260px" height="256px"> -->
                     </div>
                     <!-- 스토리 내용 넣는 부분 -->
                     <div class="col-md-6">
                        <textarea id="story_content" name="story_content" class="form-control" rows="12"></textarea>
                     </div>
                     <!-- 하단의 공개여부 목록 넣는 부분 -->
                     <div class="col-md-12">
                        <hr>
                        <div class="col-md-7">
                              <select id="open" name="open" class="selectpicker show-tick" data-width="fit">
                                    <option id="yes" data-icon="glyphicon-globe">공개</option>
                                    <option id="no" data-icon="glyphicon-lock">비공개</option>
                              </select>
                              <select id="share" name="share" class="selectpicker show-tick" data-width="fit">
                                    <option data-icon="glyphicon-share">공유</option>
                                    <option data-icon="glyphicon-lock">비공유</option>
                              </select>
                        </div>
                        <div class="col-md-5">
                              <button class="btn text-center" style="margin-left:70px;background-color:#FF5A5A;border-color:#FF5A5A;color:white;" 
                                 type="button" id="save_btn">저장</button>
                              <button type="button"
                                 class="btn btn-default text-center" id="cancel_btn">취소</button>
                        
                        </div>
                     </div>
                  </div>
               </div>
            </form>
         </div>
      </div>
   </div>   
   <!-- 모달끝 -->
   <!-- 수정Modal시작 -->
	<div class="modal fade" id="reviseModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- 데이터 전송하기 위해 만든 부분 -->
				<form action="${pageContext.request.contextPath}/Story.do" id="reviseStoryForm" enctype="multipart/form-data">
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
								<a id="reviseLocation">지명정보입력부분</a>
							</span>
						</div>
						<!-- 날짜 및 날씨 정보 표기 부분 -->
						<div class="col-md-4" style="margin-top:5px;">
							<!-- 시간 정보 시, 분 선택 부분 시작 -->
							<div class="col-md-2">
								<select class="selectpicker show-tick" data-width="fit" disabled>
								  	<option id="reviseHour"></option>
								</select>
							</div>
							<div class="col-md-1 text-center" style="padding:0px;padding-left:52px;padding-top:5px;">:</div>
							<!-- 분 -->
							<div class="col-md-2">
							<select class="selectpicker show-tick" data-width="fit" disabled>
							  	<option id="reviseMin"></option>
							</select>
							</div>
							</div>
							<!-- 시간 정보 시, 분 선택 부분 끝 -->
							<!-- 날씨 입력 부분 -->
							<div class="col-md-4" style="margin-top:5px;">
								<select id="reviseWeather" class="selectpicker show-tick" data-width="fit">
								   	<option value="맑음">맑음</option>
								   	<option value="흐림">흐림</option>
								   	<option value="비">비</option>
								   	<option value="눈">눈</option>
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
								<!-- <img class="img-rounded" src="../images/a5e7e91b081cc7be5f93fa09360dfbb7.png" 
								id="revise_story_image" alt="..." width="260px" height="256px"> -->
								<div id="path" style="display:none;">${pageContext.request.contextPath}</div><!-- js파일에서 알아오기 경로알기위해 -->
								
								<input type="file" name="revise_file" id="revise_file" accept="image/*" 
								class="inputfile" onchange="showFile(this,2)" style="display:none" />
								
								<img class="img-rounded img_change_color"  src="${pageContext.request.contextPath}/resources/images/${requestScope.fileName}" 
								id="revise_story_image" alt="..." width="260px" height="256px">
							</div>
							<!-- 스토리 내용 넣는 부분 -->
							<div class="col-md-6">
								<textarea id="revise_story_content" class="form-control" rows="12">
								</textarea>
							</div>
							
							
							<!-- 하단의 공개여부 목록 넣는 부분 -->
							<div class="col-md-12">
								<hr>
								<div class="col-md-7">
										<select id="reviseOpen" class="selectpicker show-tick" data-width="fit">
										   	<option id="reviseYes" data-icon="glyphicon-globe">공개</option>
										   	<option id="reviseNo" data-icon="glyphicon-lock">비공개</option>
										</select>
										<select id="reviseShare" class="selectpicker show-tick" data-width="fit">
										   	<option data-icon="glyphicon-share">공유</option>
										   	<option data-icon="glyphicon-lock">비공유</option>
										</select>
								</div>
								<div class="col-md-5">
										<button class="btn text-center" style="margin-left:70px;background-color:#FF5A5A;border-color:#FF5A5A;color:white;" 
											type="button" id="revise_btn">수정</button>
										<button type="button"
											class="btn btn-default text-center" id="revise_cancel_btn">취소</button>
								
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- 상세보기 Modal!! -->
	<jsp:include page="showStory.jsp"/>
	
	<!--수정 모달끝 -->
   <input id="pac-input" class="controls" type="text"
		placeholder="Search Box">
</body>
<script type="text/javascript">
var d = new Date();
//날짜 선택 API 객체 생성
$('#daterange').daterangepicker({
   //시간 pick도 가능하게
    timePicker: true,
    //시간 단위 30분 단위
    timePickerIncrement: 1,
    //일기는 현재날짜 기준으로 가능 API 제공 
    /* maxDate:d, */
    locale: { 
       applyLabel:'완료',
       cancelLabel: '취소' 
    } 
});
//클릭 시 모달 창 생성

//엔터키 비활성화
   $('#pac-input').keypress(function(e){
	    if ( e.which == 13 ){
	    	return false;
	    } 
	    	
   });
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/addStory.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/map.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/header.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/reviseStory.js"></script>

</html>
