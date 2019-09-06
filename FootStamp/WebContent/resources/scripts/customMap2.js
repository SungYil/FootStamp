$( function() {
	// 처음 메인화면에서 게시물 가져오는 부분
	// 서블릿에서 받아와야할 정보
	var array = [];
	var contentArray = [];
	array.push({
		lat : 37.5563989,
		lng : 126.9160863
	});
	array.push({
		lat : 37.5663989,
		lng : 126.9260863
	});
	// pre-scrollable
	contentArray
			.push('<div class="hovereffect imageClick"><img src="../images/a5e7e91b081cc7be5f93fa09360dfbb7.png" alt="..." width="100px" height="100px"><div class="overlay"><h6>♥ 12</h6><p><a href="#">test</a></p> </div></div>');
	contentArray
			.push('<div class="hovereffect imageClick"><img src="../images/a5e7e91b081cc7be5f93fa09360dfbb7.png" alt="..." width="100px" height="100px"><div class="overlay"><h6>♥ 12</h6><p><a href="#">test</a></p> </div></div>');
	$(
			'<div class="container"><div class="col-md-3"></div><div class="col-md-6"><div class="panel panel-default"><div class="panel-heading" id="panel_head"><div class="diary_profile"><img src="http://placehold.it/20x20" alt="..." class="img-circle" width="20px" height="20px"><a style="font-family: "Josefin Sans", sans-serif;">ehddlrvv</a></div><div class="diary_date"><a style="font-family: "Josefin Sans", sans-serif;">16.01.01</a></div></div><div class="panel-body">'
					+ '<div class="diary_map" style="height:350px; width:507px;" id="map_diary_'
					+ mapCount
					+ '"></div><br><span class="like_img glyphicon glyphicon-heart-empty icons" style="color:red;"aria-hidden="true"><a>13</a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	<span class="glyphicon glyphicon-comment icons" aria-hidden="true"><a>17</a></span>&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-share-alt icons" aria-hidden="true"><a>x5</a></span><br><hr><a class="diary_like">좋아요</a><a class="diary_review" id="write_review">댓글달기</a><a class="diary_share">공유하기</a><hr><div class="reviews icons"><div class="review_list"><a><img src="http://placehold.it/20x20" alt="..." class="img-circle">ehddlrvv</a>&nbsp;&nbsp; 지도일기 너무 이쁘네요</div></div></div></div></div></div>')
			.appendTo('body');
	initMap('map_diary_' + mapCount, false, array, contentArray);// 스토리가 있을
																	// 경우
	mapCount += 1;
	$(
			'<div class="container"><div class="col-md-3"></div><div class="col-md-6"><div class="panel panel-default"><div class="panel-heading" id="panel_head"><div class="diary_profile"><img src="http://placehold.it/20x20" alt="..." class="img-circle" width="20px" height="20px"><a style="font-family: "Josefin Sans", sans-serif;">ehddlrvv</a></div><div class="diary_date"><a style="font-family: "Josefin Sans", sans-serif;">16.01.01</a></div></div><div class="panel-body">'
					+ '<div class="diary_map" style="height:350px; width:507px;" id="map_diary_'
					+ mapCount
					+ '"></div><br><span class="like_img glyphicon glyphicon-heart-empty icons" style="color:red;"aria-hidden="true"><a>13</a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	<span class="glyphicon glyphicon-comment icons" aria-hidden="true"><a>17</a></span>&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-share-alt icons" aria-hidden="true"><a>x5</a></span><br><hr><a class="diary_like">좋아요</a><a class="diary_review" id="write_review">댓글달기</a><a class="diary_share">공유하기</a><hr><div class="reviews icons"><div class="review_list"><a><img src="http://placehold.it/20x20" alt="..." class="img-circle">ehddlrvv</a>&nbsp;&nbsp; 지도일기 너무 이쁘네요</div></div></div></div></div></div>')
			.appendTo('body');
	initMap('map_diary_' + mapCount, false, array, contentArray);// 스토리가 있을
																	// 경우
	mapCount += 1;
	// 클릭이벤트 달기!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		$(".reviews").hide();
		$('.diary_review')
				.unbind('click')
				.bind(
						'click',
						function() {
							var review_cnt = $(this).parent().children("span")
									.eq(1).find('a');// 댓글 추가,삭제시 변해야한다.
							var target = $(this);
							var writer = $(this).parent().parent().children(
									"div").eq(0).find("div").find('a').html();
							var date = $(this).parent().parent()
									.children("div").eq(0).find("div").eq(1)
									.find('a').html();
							$
									.ajax({
										url : "/FootStamp/review.do",
										type : "post",
										data : {
											type : "getList",
											isStory : "false",
											writerId : writer,// 일기 작성자
											date : date,// 날짜
											kind : "review",
										},
										success : function(res) {
											var review_add = target.parent()
													.find(".reviews");
											review_add.html("");
											var data = JSON.parse(res);
											if (data.result == "success") {// 성공일
																			// 경우
												var list = data.reviewList;
												review_add.append('<div class="review_list pre-scrollable" style="height : 200px"><div>');
												var diary_review = target
														.parent().find(
																".review_list");
												for (var i = 0; i < list.length; ++i) {
													var date = list[i].year
															+ "."
															+ list[i].month
															+ "." + list[i].day
															+ " "
															+ list[i].hour
															+ "시" + list[i].min
															+ "분";
													diary_review.append("<div id="
																	+ list[i].reviewId
																	+ "><a>"
																	+ list[i].id
																	+ "</a>&nbsp;&nbsp;"
																	+ list[i].content
																	+ '&nbsp;'
																	+ '<span style="color: #b4b4b4; font-size:10px" >'
																	+ date
																	+ '</span>'
																	+ "</div>");
													var login_id = $(".main_logo").attr("id");
													console.log(list[i].id == login_id);
													if (list[i].id == login_id) {
														$("#"+ list[i].reviewId).children("span").append('<span class="delete glyphicon glyphicon-trash" style="color:black;font-size:11px;" aria-hidden="true">');
													}
												}
												review_add
														.append('<div class="col-md-12" style=" padding-right: 0px; padding-left :0px;">'
																+ '<div class="col-md-2 text-center"	style=" padding-right: 0px; padding-left :0px; padding-top: 5px;">'
																+ login_id
																+ '</div>'
																+ '<div class="col-md-9">'
																+ '<input style="padding-top: 5px;" type="text" class="add_content form-control" placeholder="여기에 댓글을 입력하세요">'
																+ '</div>'
																+ '<div class="col-md-1" style=" padding-right: 0px; padding-left :0px;">'
																+ '<button class="btn btn-default add_review" type="button">등록</button>'
																+ '</div>'
																+ '</div>');
												$(".add_review").unbind('click').bind('click',function() {
													console.log("너 안되면 개새기1");
													var reviews = $(this).parent().parent().parent();
													console.log(reviews);
													add_review(date,review_cnt,writer,reviews);
												});
												delete_review(writer, date,
														review_cnt);
											}
										},
										error : function() {

										}
									});
							$(this).parent().find(".reviews").toggle();
						});
		$(".diary_like")
				.unbind('click')
				.bind(
						'click',
						function() {
							var writer = $(this).parent().parent().children(
									"div").eq(0).find("div").find('a').html();
							var num = $(this).parent().children(".like_img")
									.find('a').html();
							var date = $(this).parent().parent()
									.children("div").eq(0).find("div").eq(1)
									.find('a').html();
							var today = new Date();
							var date_form = (today.getYear() + 1900) + "."
									+ (today.getMonth() + 1) + "."
									+ today.getDate() + " " + today.getHours()
									+ "시" + today.getMinutes() + "분";
							console.log(num);
							if ($(this).html() == "좋아요") {
								$(this).html("좋아요 취소");
								$(this).parent().children(".like_img")
										.find('a').html(Number(num) + 1);
								$(this)
										.parent()
										.children(".like_img")
										.attr("class",
												"like_img glyphicon glyphicon-heart icons on");
								$.ajax({
									url : "/FootStamp/review.do",
									type : "post",
									data : {
										type : "add",
										isStory : "false",
										diaryWriterId : writer,
										date : date,
										kind : "like",
										reviewDate : date_form,
										reviewWriterId : $(".main_logo").attr(
												"id")
									},
									success : function(res) {
										// 등록 실패하는 경우 고려?
										console.log("등록");

									},
									error : function() {

									}
								});
							} else {
								$(this).html("좋아요");
								$(this).parent().children(".like_img")
										.find('a').html(Number(num) - 1);
								$(this)
										.parent()
										.children(".like_img")
										.attr("class",
												"like_img glyphicon glyphicon-heart-empty icons off");
								$.ajax({
									url : "/FootStamp/review.do",
									type : "get",
									data : {
										type : "delete",
										kind : "like",
										isStory : "false",
										diaryWriterId : writer,
										date : date,
										id : "좋아요식별키"// 자기 식별키
									},
									success : function(res) {
										// 등록 실패하는 경우 고려?
										console.log("취소");
									},
									error : function() {

									}
								});
							}
			});

	$(window).scroll(function() {
						// 서블릿에서 받아와야할 정보
						var array = [];
						var contentArray = [];
						array.push({
							lat : 37.5563989,
							lng : 126.9160863
						});
						array.push({
							lat : 37.5663989,
							lng : 126.9260863
						});

						var scrollHeight = $(window).scrollTop()
								+ $(window).height();
						var documentHeight = $(document).height();
						if (scrollHeight == documentHeight) {
							$('<div class="container"><div class="col-md-3"></div><div class="col-md-6"><div class="panel panel-default"><div class="panel-heading" id="panel_head"><div class="diary_profile"><img src="http://placehold.it/20x20" alt="..." class="img-circle" width="20px" height="20px"><a style="font-family: "Josefin Sans", sans-serif;">ehddlrvv</a></div><div class="diary_date"><a style="font-family: "Josefin Sans", sans-serif;">16.01.01</a></div></div><div class="panel-body">'
									+ '<div class="diary_map" style="height:350px; width:507px;" id="map_diary_'
									+ mapCount
									+ '"></div><br><span class="like_img glyphicon glyphicon-heart-empty icons" style="color:red;"aria-hidden="true"><a>13</a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	<span class="glyphicon glyphicon-comment icons" aria-hidden="true"><a>17</a></span>&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-share-alt icons" aria-hidden="true"><a>x5</a></span><br><hr><a class="diary_like">좋아요</a><a class="diary_review" id="write_review">댓글달기</a><a class="diary_share">공유하기</a><hr><span class="reviews icons"><div class="review_list"><a><img src="http://placehold.it/20x20" alt="..." class="img-circle">ehddlrvv</a>&nbsp;&nbsp; 지도일기 너무 이쁘네요</div></div></div></div></div></div>')
							.appendTo('body');
							contentArray.push('<div class="hovereffect imageClick"><img src="../images/a5e7e91b081cc7be5f93fa09360dfbb7.png" alt="..." width="100px" height="100px"><div class="overlay"><h6>♥ 12</h6><p><a href="#">review 13</a></p> </div></div>');
							contentArray.push('<div class="hovereffect imageClick"><img src="../images/a5e7e91b081cc7be5f93fa09360dfbb7.png" alt="..." width="100px" height="100px"><div class="overlay"><h6>♥ 12</h6><p><a href="#">review 13</a></p> </div></div>');
							initMap('map_diary_' + mapCount, false, array,contentArray);// 스토리가 있을 경우
							mapCount += 1;
							$(".reviews").last().hide();
							$('.diary_review').unbind('click').bind('click',function() {
								var review_cnt = $(this)
										.parent().children("span").eq(1).find('a');// 댓글 추가,삭제시
													// 변해야한다.
								var target = $(this);
								var writer = $(this).parent().parent().children("div").eq(0).find("div").find('a').html();
								var date = $(this).parent().parent().children("div").eq(0).find("div").eq(1).find('a').html();
								$.ajax({
									url : "/FootStamp/review.do",
									type : "post",
									data : {
										type : "getList",
										isStory : "false",
										writerId : writer,// 일기
															// 작성자
										date : date,// 날짜
										kind : "review",
									},
									success : function(res) {
										var review_add = target.parent()
												.find(".reviews");
										review_add.html("");
										var data = JSON.parse(res);
										if (data.result == "success") {// 성공
											var list = data.reviewList;
											review_add.append('<div class="review_list pre-scrollable" style="height : 200px"><div>');
											var diary_review = target.parent().find(".review_list");
											for (var i = 0; i < list.length; ++i) {
												var date = list[i].year
														+ "."
														+ list[i].month
														+ "."
														+ list[i].day
														+ " "
														+ list[i].hour
														+ "시"
														+ list[i].min
														+ "분";
												diary_review
														.append("<div id="
																+ list[i].reviewId
																+ "><a>"
																+ list[i].id
																+ "</a>&nbsp;&nbsp;"
																+ list[i].content
																+ '&nbsp;'
																+ '<span style="color: #b4b4b4; font-size:10px" >'
																+ date
																+ '</span>'
																+ "</div>");
												var login_id = $(".main_logo").attr("id");
												console.log(list[i].id == login_id);
												if (list[i].id == login_id) {$("#"+ list[i].reviewId).children("span").append(
																	'<span class="delete glyphicon glyphicon-trash" style="color:black;font-size:11px;" aria-hidden="true">');
												}
											}
											review_add.append('<div class="col-md-12" style=" padding-right: 0px; padding-left :0px;">'
															+ '<div class="col-md-2 text-center"	style=" padding-right: 0px; padding-left :0px; padding-top: 5px;">'
															+ login_id
															+ '</div>'
															+ '<div class="col-md-9">'
															+ '<input style="padding-top: 5px;" type="text" class="add_content form-control" placeholder="여기에 댓글을 입력하세요">'
															+ '</div>'
															+ '<div class="col-md-1" style=" padding-right: 0px; padding-left :0px;">'
															+ '<button class="btn btn-default add_review" type="button">등록</button>'
															+ '</div>'
															+ '</div>');
											$(".add_review").unbind('click').bind('click',function() {
												console.log("너 안되면 개새기2");
												var reviews = $(this).parent().parent().parent();
												add_review(date,review_cnt,writer,reviews);
											});
											delete_review(writer,date,review_cnt);
										}
									},
									error : function() {

									}
								});
								$(this).parent().find(".reviews").toggle();
							});
							$(".diary_like")
									.unbind('click')
									.bind(
											'click',
											function() {
												var writer = $(this).parent()
														.parent().children(
																"div").eq(0)
														.find("div").find('a')
														.html();
												var num = $(this).parent()
														.children(".like_img")
														.find('a').html();
												var date = $(this).parent()
														.parent().children(
																"div").eq(0)
														.find("div").eq(1)
														.find('a').html();
												var today = new Date();
												var date_form = (today
														.getYear() + 1900)
														+ "."
														+ (today.getMonth() + 1)
														+ "."
														+ today.getDate()
														+ " "
														+ today.getHours()
														+ "시"
														+ today.getMinutes()
														+ "분";
												console.log(num);
												if ($(this).html() == "좋아요") {
													$(this).html("좋아요 취소");
													$(this).parent().children(
															".like_img").find(
															'a').html(
															Number(num) + 1);
													$(this)
															.parent()
															.children(
																	".like_img")
															.attr("class",
																	"like_img glyphicon glyphicon-heart icons on");
													$.ajax({
														url : "/FootStamp/review.do",
														type : "post",
														data : {
															type : "add",
															isStory : "false",
															diaryWriterId : writer,
															date : date,
															kind : "like",
															reviewDate : date_form,
															reviewWriterId : $(
																	".main_logo")
																	.attr(
																			"id")
														},
														success : function(
																res) {
															// 등록 실패하는
															// 경우 고려?
															console
																	.log("등록");

														},
														error : function() {

														}
													});
												} else {
													$(this).html("좋아요");
													$(this).parent().children(
															".like_img").find(
															'a').html(
															Number(num) - 1);
													$(this).parent().children(".like_img").attr("class","like_img glyphicon glyphicon-heart-empty icons off");
													$.ajax({
														url : "/FootStamp/review.do",
														type : "get",
														data : {
															type : "delete",
															kind : "like",
															isStory : "false",
															diaryWriterId : writer,
															date : date,
															id : "좋아요식별키"// 자기 식별키
														},
														success : function(
																res) {
															// 등록 실패하는
															// 경우 고려?
															console
																	.log("취소");
														},
														error : function() {

														}
													});
												}
											});
						}

					});

	$('#yourDiary')
			.click(
					function() {
						// 서블릿에서 받아와야할 정보
						var array = [];
						var contentArray = [];
						array.push({
							lat : 37.5563989,
							lng : 126.9160863
						});
						array.push({
							lat : 37.5663989,
							lng : 126.9260863
						});
						contentArray
								.push('<a href="#" class="outer_a"><div class="outerDiv"><div class="imgDiv"><img width="100" height="100" src="https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcTCHJc0oSlnUtVsUtNyXqJSWbtmUP6fNunZ0DojOqGCmCusMbQl" class="mapImg" id="img1"></div>'
										+ '<div class="reviewDiv"><ul class="review_menu"><li><span class="glyphicon glyphicon-heart-empty icons" aria-hidden="true">X</span><span>30</span></li><li><span class="glyphicon glyphicon-comment icons" aria-hidden="true">X</span><span>2</span></li></ul></div></div></a>');
						contentArray
								.push('<a href="#" class="outer_a"><div class="outerDiv"><div class="imgDiv"><img width="100" height="100" src="https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcTCHJc0oSlnUtVsUtNyXqJSWbtmUP6fNunZ0DojOqGCmCusMbQl" class="mapImg" id="img1"></div>'
										+ '<div class="reviewDiv"><ul class="review_menu"><li><span class="glyphicon glyphicon-heart-empty icons" aria-hidden="true">X</span><span>123</span></li><li><span class="glyphicon glyphicon-comment icons" aria-hidden="true">X</span><span>2</span></li></ul></div></div></a>');
						detail();
						initMap('map', false, array, contentArray)// 임의의 위치 2개
																	// 준다
						mapCount += 1;
					});
	$('#startDate')
			.click(
					function() {
						var array = [];
						var contentArray = [];
						array.push({
							lat : 37.5563989,
							lng : 126.9160863
						});
						array.push({
							lat : 37.5663989,
							lng : 126.9260863
						});
						contentArray
								.push('<a href="#" class="outer_a"><div class="outerDiv"><div class="imgDiv"><img width="100" height="100" src="https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcTCHJc0oSlnUtVsUtNyXqJSWbtmUP6fNunZ0DojOqGCmCusMbQl" class="mapImg" id="img1"></div><div class="reviewDiv"><ul class="review_menu"><li><span class="glyphicon glyphicon-heart-empty icons" aria-hidden="true">X</span><span>1</span></li><li><span class="glyphicon glyphicon-comment icons" aria-hidden="true">X</span><span>2</span></li></ul></div></div><div class="editDiv"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></div></a></a>');
						contentArray
								.push('<a href="#" class="outer_a"><div class="outerDiv"><div class="imgDiv"><img width="100" height="100" src="https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcTCHJc0oSlnUtVsUtNyXqJSWbtmUP6fNunZ0DojOqGCmCusMbQl" class="mapImg" id="img1"></div><div class="reviewDiv"><ul class="review_menu"><li><span class="glyphicon glyphicon-heart-empty icons" aria-hidden="true">X</span><span>1</span></li><li><span class="glyphicon glyphicon-comment icons" aria-hidden="true">X</span><span>2</span></li></ul></div></div><div class="editDiv"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></div></a></a>');
						detail();
						initMap('map', true, array, contentArray);// 스토리가 있을
																	// 경우
					});
	function add_review(date, review_cnt, diary_writer, reviews) {
		var today = new Date();
		var date_form = (today.getYear() + 1900) + "." + (today.getMonth() + 1)
				+ "." + today.getDate() + " " + today.getHours() + "시"
				+ today.getMinutes() + "분";
		var id = $(".main_logo").attr("id");// 로그인 아이디
		var content = reviews.find(".add_content").val();
		// 댓글 길이때문에 post
		$.ajax({
			url : "/FootStamp/review.do",
			type : "post",
			data : {
				type : "add",
				isStory : "false",
				date : (today.getYear() + 1900) + "."
						+ (today.getMonth() + 1) + "." + today.getDate(),// 일기를
				diaryWriterId : diary_writer,
				reviewWriterId : id,
				reviewDate : date_form,
				content : content,
				kind : "review"
			},
			success : function(res) {
				var id = res.trim();
				reviews
						.find(".review_list")
						.append(
								"<div id="
										+ id
										+ "><a>"
										+ $(".main_logo").attr("id")
										+ "</a>&nbsp;&nbsp;"
										+ content
										+ '&nbsp;'
										+ '<span style="color: #b4b4b4; font-size:10px" >'
										+ date_form
										+ '<span class="delete glyphicon glyphicon-trash" style="color:black;font-size:11px;" aria-hidden="true">'
										+ '</span>' + "</div>");
				reviews.find(".add_content").val("");
				review_cnt.html(Number(review_cnt.html()) + 1);
				delete_review(diary_writer, date, review_cnt);
			},
			error : function() {

			}
		});
	}

	function delete_review(writer, date, review_cnt) {
		$(".delete").unbind('click').bind('click', function() {
			var id = $(this).parent().parent().attr("id");
			$.ajax({
				url : "/FootStamp/review.do",
				type : "get",
				data : {
					type : "delete",
					kind : "review",
					isStory : "false",
					writerId : writer,
					date : date,
					reviewId : id
				},
				success : function(res) {
					if (res.trim() == "success") {// 성공시 삭제
						review_cnt.html(Number(review_cnt.html()) - 1);
						console.log($("#" + id).attr("id"));
						$("#" + id).remove();
					}
				},
				error : function() {

				}
			})
		});
	}
})
