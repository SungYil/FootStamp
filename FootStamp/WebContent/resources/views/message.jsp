<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<form id="search_form" action="${pageContext.request.contextPath }/message.do" method="get">
	</form>
	
	<!-- 검색결과 팝업 -->
	<div class="result">
	</div>
	<!-- 새 메시지 보내기 -->
	<div id="send_message" class="panel panel-default">
		<div class="panel-body">
			<div class="col-md-12">
				<h1>새 메시지</h1>
			</div>
			<!-- 여기서 검색 방법을 제공해야한다.. -->
			<div class="col-md-12">
				<input type="text" placeholder="이름을 입력하세요" data-container="body" 
				data-placement="bottom" data-html="true" data-content=".">
			</div>
			<div class="col-md-12">
				<h4>받는 사람</h4>
			</div>
			<!-- 검색할때마다 실시간으로 추가해주는 부분 -->
			<div class="col-md-9"></div>
			<!-- 키보드 이벤트 달아서 키보드 입력이 들어올때마다 비동기로 요청?아니면 그냥 친구목록은 다가져온다 -->
			<!-- 메시지 입력하는 부분 -->
			<div class="col-md-12">
				<textarea style="width: 100%; height: 100px;"></textarea>
			</div>
			<div class="col-md-1">
				<input type="button" class="btn btn-default" value="전송">
			</div>
		</div>
	</div>
	<!-- 새 메시지 보내기 -->

	<!-- 한명과의 메시지 목록보기 -->
	<div id="detail_message" class="panel panel-default">
		<div class="panel-body">
			<div class="col-md-12">박성일</div>
			<div class="col-md-12 pre-scrollable"></div>
			<!-- 메시지 입력하는 부분 -->
			<form id="send_message_form"
				action="${pageContext.request.contextPath }/message.do"
				method="post">
				<div class="col-md-10">
					<textarea style="width: 100%; height: 100px;"></textarea>
				</div>
				<div class="col-md-2">
					<input type="button" class="btn btn-default" value="전송">
				</div>
			</form>
		</div>
	</div>
	<!-- 한명과의 메시지 목록보기 -->

	<!-- 메시지 버튼누르면 나오는 메시지 관련 목록 보여주는 부분 -->
	<div id="path" style="display:none">${pageContext.request.contextPath }</div>
	
	<div id="list" class="panel panel-default">
		<div class="panel-body">
			<div class="col-md-2">
				<h3>Message</h3>
			</div>
			<div class="col-md-3 col-md-offset-10">
				<a href="#">새 메시지</a>
			</div>
			<!-- 이걸 비동기로 받아와서 만들어야한다. -->
			<div class="col-md-12"></div>
		</div>
	</div>
</body>
