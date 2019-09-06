<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="detail_form"
	action="${pageContext.request.contextPath }/Story.do" method="get">
</form>
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
							<a id="location"></a>
						</span>
					</div>
					<!-- 날짜 및 날씨 정보 표기 부분 -->
					<div class="col-md-6" style="margin-top: 5px;" id="date"></div>
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
							작성자 : <label id="writer" style="font-weight: bold;"></label>
							<hr>
						</div>
						<!-- 스토리 내용 넣는 부분 -->
						<div id="content" class="col-md-6 pre-scrollable"
							style="height: 190px;">원장은 국회의 동의를 얻어 대통령이 임명하고, 그 임기는 4년으로
						</div>
					</div>
					<!-- 하단의 공개여부 목록 넣는 부분 -->
					<div class="col-md-12">
						<hr>
						<!-- 좋아요 부분 -->
						<div class="col-md-2">
							<span id="likeImg" class="glyphicon glyphicon-heart-empty icons"
								style="font-size: 13px;" aria-hidden="true"> &nbsp;<a
								id="likeCount"></a></span>
						</div>
						<!-- 댓글 부분 -->
						<div class="col-md-2" style="margin-left: 10px;">
							<span id="review_icon" class="glyphicon glyphicon-comment icons"
								style="font-size: 13px;" aria-hidden="true"> &nbsp;<a
								id="reviewCount"></a></span>
						</div>
						<!-- 공유하기 부분 -->
						<div class="col-md-2" style="margin-left: 10px;">
							<span class="glyphicon glyphicon-share-alt icons"
								style="font-size: 13px;" aria-hidden="true"></span>
						</div>
						<br>
						<hr>
						<!-- 댓글 정보 목록 -->
						<div class="col-md-12 reviews">
							<!-- 댓글에 scroll주기 -->
							<div class="col-md-12 pre-scrollable" style="height: 100px;">
								<div id="review_list"></div>
							</div>
							<div class="col-md-12">
								<form action="" method="get">
									<div id="user_id" class="col-md-2 text-center"
										style="padding-top: 5px;">${sessionScope.user_id }</div>
									<div class="col-md-8">
										<input id="add_content" style="padding-top: 5px;" type="text"
											class="form-control" placeholder="여기에 댓글을 입력하세요">
									</div>
									<div class="col-md-2">
										<button id='add_review' class="btn btn-default" type="button">등록</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- Modal body끝 -->
		</div>
		<!-- Modal Content끝 -->
	</div>
</div>
<script type="text/javascript">
	$('.reviews').hide();
	$('#review_icon').unbind('click').bind('click', function() {
		//서버에서 댓글 목록가져와야한다.
		$.ajax({
			url : "/FootStamp/review.do",
			type : "post",
			data : {
				type : "getList",
				isStory : "true",
				storyId : "storyId",//스토리(or 일기) 식별키
				kind : "review",
			},
			success : function(res){
				var data=JSON.parse(res);
				if(data.result=="success"){//성공일 경우
					$("#review_list").html("");
					var list=data.reviewList;
					for(var i=0;i<list.length;++i){
						var date=list[i].year+"."+list[i].month+"."+list[i].day+" "+list[i].hour+"시"+list[i].min+"분";
						$("#review_list").append("<div id="+list[i].reviewId+"><a>"+list[i].id+"</a>&nbsp;&nbsp;"
								+list[i].content
								+ '&nbsp;'
								+ '<span style="color: #b4b4b4; font-size:10px" >'
								+date
								+'</span>'
								+"</div>");
						var login_id=$(".main_logo").attr("id");
						if(list[i].id==login_id){
							$("#"+list[i].reviewId).children("span").append('<span class="delete glyphicon glyphicon-trash" style="color:black;font-size:11px;" aria-hidden="true">');
						}
					}
					$(".delete").unbind('click').bind('click', function(){
						var id=$(this).parent().parent().attr("id");
						var reviewId=$(this).parent().parent().attr("id");
						$.ajax({
							url : "/FootStamp/review.do",
							type : "get",
							data : {
								type : "delete",
								kind : "review",
								isStory : "true",
								storyId : "스토리 아이디",//찾는속도 높이려고
								reviewId : reviewId
							},
							success : function(res){
								if(res.trim()=="success"){//성공시 삭제
									$("#"+id).remove();
									var number=Number($("#reviewCount").html());
									$("#reviewCount").html(number-1);
								}
							},
							error : function(){
								
							}
						})
					});
				}
			},
			error : function(){
				
			}
		});
		$('.reviews').toggle();
	});
	$("#showModal").on("hidden.bs.modal", function() {
		$('.reviews').hide();
	})
	//.unbind('click').bind
	$('#add_content').on('keypress',function(e){
	    if ( e.which == 13 ){
			return false;
	    }
   })
	
	$("#add_review").unbind('click').bind('click',function() {
		add_review();
	});
	
	function add_review(){
		var today=new Date();
		var date_form=(today.getYear()+1900)+"."+(today.getMonth()+1)+"."+today.getDate()+" "+today.getHours()+"시"+today.getMinutes()+"분";
		//댓글 길이때문에 post
		$.ajax({
			url : "/FootStamp/review.do",
			type : "post",
			data : {
				type : "add",
				isStory : "true",
				storyId : "storyId",//스토리일 경우(임시로 넣어둔거다)
				kind : "review",
				reviewWriterId : '${sessionScope.user_id }',
				reviewDate : date_form,
				content: $("#add_content").val()
			},
			success : function(res){
				var id=res.trim();
				$("#review_list").append("<div id="+id+"><a>"
						+ '${sessionScope.user_id}'
						+ "</a>&nbsp;&nbsp;"
						+ $("#add_content").val()
						+ '&nbsp;'
						+ '<span style="color: #b4b4b4; font-size:10px" >'
						+ date_form
						+ '<span class="delete glyphicon glyphicon-trash" style="color:black;font-size:11px;" aria-hidden="true">'
						+ '</span>' 
						+ "</div>");
					$("#add_content").val("");
					var number=Number($("#reviewCount").html());
					$("#reviewCount").html(number+1);
					$(".delete").unbind('click').bind('click', function(){
						var id=$(this).parent().parent().attr("id");
						console.log(id);
						$.ajax({
							url : "/FootStamp/review.do",
							type : "get",
							data : {
								type : "delete",
								isStory : "true",
								storyId: "스토리아이디",//임시
								kind : "review",
								reviewId : id//자기 식별키
							},
							success : function(res){
								if(res.trim()=="success"){//성공시 삭제
									var number=Number($("#reviewCount").html());
									$("#reviewCount").html(number-1);
									$("#"+id).remove();
								}
							},
							error : function(){
								
							}
						})
					});
			},
			error : function(){
				
			}
		});
	}
</script>