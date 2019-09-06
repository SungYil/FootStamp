<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FootStamp에 오신 것을 환영합니다!</title>
<!-- 부트스트랩 -->
<link href="${pageContext.request.contextPath}/resources/bootstrap-dist/css/bootstrap.min.css" rel="stylesheet">







<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<script src="${pageContext.request.contextPath}/resources/bootstrap-dist/js/bootstrap.min.js"></script>

<!-- css 파일 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/diary.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/profile.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/mapStyle.css">
<!-- 한글폰트 -->
<link href="http://fonts.googleapis.com/earlyaccess/hanna.css" rel="stylesheet">
<link href="http://fonts.googleapis.com/earlyaccess/nanumgothiccoding.css" rel="stylesheet">
<!-- 영문폰트 -->
<link href="https://fonts.googleapis.com/css?family=Nunito+Sans" rel="stylesheet"><!-- font-family: 'Nunito Sans', sans-serif; -->
<link href="https://fonts.googleapis.com/css?family=Tangerine" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
<%-- 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/bootstrap-dist/css/datepicker3.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap-dist/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap-dist/js/bootstrap-datepicker.kr.js"></script>

 --%>
 
<!-- 구글키 -->
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAN3ihyeLl7l0tNw4lN33p4cE53DXS0Gy0&libraries=places"
	type="text/javascript"></script>

<script
	src="${pageContext.request.contextPath }/resources/scripts/customMap.js"></script>
<script
	src="${pageContext.request.contextPath }/resources/scripts/customMap2.js"></script>
	<script
	src="${pageContext.request.contextPath }/resources/scripts/profileModal.js"></script>
</head>
	<!-- 헤더부 포함 -->
	<jsp:include page="header.jsp"/>
<!-- 바디부 시작 -->
<body>
	<div class="container">
	<br>
	<div class="col-md-3"></div>
	<!-- 프로필 사진 부분 시작 -->
	<div class="col-md-2 profile_content">
		<img src="${pageContext.request.contextPath}/resources/images/profile/${profile.memberBean['profileImg']}" id="profile_image" alt="..." class="img-circle" width="130px" height="130px">
		<!-- 계정 아이디 -->			
		<h3 class="text-center" id="user_id">${profile.memberBean['id'] }</h3>
	</div>
	<!-- 프로필 사진 부분 끝 -->
	<!-- 상단 팔로우 팔로워 부분 시작 -->
	<div class="col-md-3 profile_content">	
		<table class="col-md-12 profile_table">
			<thead>
				<tr>
				<th class="text-center"><a style="color:#8c8c8c">스토리</a></th>
				<th class="text-center" id="follower"><a style="color:#8c8c8c">팔로워</a></th>
				<th class="text-center" id="following"><a style="color:#8c8c8c">팔로잉</a></th>
				</tr>
			</thead>
			<tr>
				<td class="text-center" id="user_diary_count"><a>1</a></td>
				<td class="text-center" id="user_follower_count"><a>${profile.followCnt }</a></td>
				<td class="text-center" id="user_following_count"><a>${profile.followCnt }</a></td>
			</tr>
		</table>
		<hr>
		<button id="profile_modify" style="margin-left:20px;background-color:#FF5A5A;"type="button" class="btn btn-default">
		<a style="padding-left:60px;padding-right:60px;color:white">프로필 수정</a></button>
	</div><br>	
	<!-- 상단 팔로우 팔로워 부분 끝 -->
	
	<div class="col-md-4"></div>
	<div class="col-md-12" style="margin-bottom:30px;"></div>
	
	
	
	<!-- 상세보기창 -->
	<jsp:include page="showStory.jsp" />


</div>
	<!-- 팔로잉 모달 -->
	<!-- 서버에서 팔로잉 리스트를 받아와서 일정 갯수만큼 FOR문돌려서 출력.지금은 임시 데이터로 2개의 행만 삽입해두었다. -->
	<div class="modal fade" id="fallow_modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 id= "messag8" class="modal-title">팔로잉</h4>
				</div>
				<div class="modal-body">
				<div class="container-fluid">
					
            		<div class="row">
             			<div class="col-md-1">
						<img src="${pageContext.request.contextPath}/resources/images/a5e7e91b081cc7be5f93fa09360dfbb7.png"
						 class="img-circle" width="40px" height="40px">
						</div>
						
						<div class="col-md-3">
						<table>
							<tr><td>shfur</td></tr>
							<tr><td>이름</td></tr>
						</table>
						</div>
						
						<div class="col-md-2 col-md-offset-6">
						<button style="margin-left:20px;background-color:#FF5A5A;" type="button" class="btn btn-default following_class">
						팔로잉</button>
						</div>
					</div>
					<br>
					
					<div class="row">
             			<div class="col-md-1">
						<img src="${pageContext.request.contextPath}/resources/images/a5e7e91b081cc7be5f93fa09360dfbb7.png"
						 class="img-circle" width="40px" height="40px">
						</div>
						<div class="col-md-3">
						<table>
							<tr><td>shfu123r</td></tr>
							<tr><td>이름2</td></tr>
						</table>
						</div>
						<div class="col-md-2 col-md-offset-6">
						<button style="margin-left:20px;background-color:#FF5A5A;" type="button" class="btn btn-default following_class">
						팔로잉</button>
						</div>
					</div>
				</div>
				</div>
				
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	
	<!-- 팔로우 팔로잉 처리하는 모달 끝 -->
	<!-- 서버에서 팔로우리스트를 받아와 일정 갯수만큼 FOR문돌려서 출력.지금은 임시 데이터로 2개의 행만 삽입해두었다. -->
	<div class="modal fade" id="follower_modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 id= "messag8" class="modal-title">팔로워</h4>
				</div>
				<div class="modal-body" style="max-height: calc(10vh - 20px);overflow-y: auto;">
				<div class="container-fluid">
            		<div class="row">
             			<div class="col-md-1">
						<img src="${pageContext.request.contextPath}/resources/images/a5e7e91b081cc7be5f93fa09360dfbb7.png"
						 class="img-circle" width="40px" height="40px">
						</div>
						
						<div class="col-md-3">
						<table>
							<tr><td>shfur</td></tr>
							<tr><td>이름</td></tr>
						</table>
						</div>
						
						<div class="col-md-2 col-md-offset-6">
						<button style="margin-left:20px;background-color:#FF5A5A;" type="button" class="btn btn-default following_class">
						팔로잉</button>
						</div>
					</div>
					<br>
					
					<div class="row">
             			<div class="col-md-1">
						<img src="${pageContext.request.contextPath}/resources/images/a5e7e91b081cc7be5f93fa09360dfbb7.png"
						 class="img-circle" width="40px" height="40px">
						</div>
						<div class="col-md-3">
						<table>
							<tr><td>shfu1r</td></tr>
							<tr><td>이름2</td></tr>
						</table>
						</div>
						<div class="col-md-2 col-md-offset-6">
						<button style="margin-left:20px;background-color:#FF5A5A;" type="button" class="btn btn-default following_class">
						팔로잉</button>
						</div>
					</div>
				</div>
				</div>
				`
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- 팔로우 팔로잉 처리하는 모달 끝 -->
	<div style="display: none;">
	<!-- 전화번호랑 이름을 페이지에다가 숨긴다는 전제하에 일단 임시 데이터 삽입 -->
		<div id="names">박성</div>
		<div id="numbers">01044444444</div>
	</div>
	
	<div class="modal fade" id="profile_modify_modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 id= "modify_top_message" class="modal-title">계정 Password를 입력해주세요</h4>
				</div>
				<div class="modal-body">
				<div id="profile_modify_modalbody" class="container-fluid">
						<!--  암호를 입력받는 바디부.  -->
						<div id="change_modal_content_passinp">
							<div class="row col-md-offset-1" style="margin-top: 50px; margin-bottom: 50px;">
								<form id="password" action="${pageContext.request.contextPath}/member.do"><table>
									<tr>
										<td rowspan='20'><h3>암호</h3>&nbsp;&nbsp;&nbsp;&nbsp;</td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
										<td rowspan='50'>
										<input type="text" size="54"
											class="form-control" placeholder="Password"></td>
									</tr>
								</table>								
								</form>
							</div>
							<div class="row col-md-offset-5">
								<button id="pofilemodal_pass_inp" type="button"	class="btn btn_my_color">입력</button>
							</div>
						</div>

						<!--  버튼을 출력하는 바디부  -->
						<div id="change_modal_content_button" style="display: none">
							<div class="row" style="margin-top: 50px; margin-bottom: 50px;">
								<button id="profile_modify_myInfo" type="button"
									class="btn btn_my_color btn-lg btn-block">계정 정보 수정</button>
							</div>
							<div class="row" style="margin-top: 50px; margin-bottom: 50px;">
								<button id="profile_modify_img" type="button"
									class="btn btn_my_color btn-lg btn-block">프로필 사진 수정</button>
							</div>
							<div class="row" style="margin-top: 50px; margin-bottom: 50px;">
								<button id="profile_modify_stopBut" type="button"
									class="btn btn_my_color btn-lg btn-block">그만하기</button>
							</div>
						</div>
						
						
						<div id="change_modal_content_image" style="display:none;">
							<form id="file_upload" method="post" action="${pageContext.request.contextPath}/member.do"
							name="file_upload" enctype="multipart/form-data" accept-charset="utf-8">
							
								<input type="file" id="img" name="img" accept="image/*" class="btn btn_my_color" 
								style="margin : 15px;" onchange="showFile(this)">
							</form>
							
							<div id="select_img_file" style="width:200px;height:200px;"></div>
							<button id="profile_modify_image" type="button"
									class="btn btn_my_color btn-lg btn-block">업로드</button>
						</div>
						
						
						<div id="change_modal_content_infoInput" style="display:none;">
							<form id="modify_form" action="${pageContext.request.contextPath}/modifyInfo.do" accept-charset="utf-8">
							<div class="row" style="margin-bottom: 15px;">
								<label class="col-sm-3 control-label text-right" for="textinput">계정 ID</label>
								<div class="col-sm-9">
									<input type='text' class="form-control col-sm-10"
									 placeholder="UserId" id="userid_input" name="userid_input">
								</div>
							</div>
							<div class="row" style="margin-bottom: 15px;">
								<label class="col-sm-3 control-label text-right" for="textinput">비밀번호</label>
								<div class="col-sm-9">
									<input type='text' class="form-control col-sm-10" 
									placeholder="Password" id="password_input" name="password_input">
								</div>
							</div>
							<div class="row" style="margin-bottom: 15px;">
								<label class="col-sm-3 control-label text-right" for="textinput">비밀번호 확인</label>
								<div class="col-sm-9">
									<input type='text' class="form-control col-sm-10" 
									placeholder="Password" id="passwordAccept_input" name="passwordAccept_input">
									
								</div>
							</div>
							<div class="row" style="margin-bottom: 15px;">
								<label class="col-sm-3 control-label text-right" for="textinput" >이름</label>
								<div class="col-sm-9">
									<input type='text' class="form-control col-sm-10"
									 placeholder="Name" id="name_input" name="name_input">
								</div>
							</div>
							<div class="row" style="margin-bottom: 15px;">
								<label class="col-sm-3 control-label text-right" for="textinput">연락처</label>
								<div class="col-sm-9">
									<input type='text' class="form-control col-sm-10"
									 placeholder="PhoneNumber" id="phoneNumber_input" name="phoneNumber_input">
								</div>
							</div>
							</form>
								<button id="profile_modify_ok" type="button"
									class="btn btn_my_color">확인</button>
								<button id="profile_modify_cancel" type="button"
									class="btn btn_my_color">취소</button>
						
						</div>
					</div>
				</div>
				
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	

</body>

</html>
