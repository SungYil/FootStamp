var height;
		$(function() {
			$(".result").hide();
			$('#message').popover();
			$("#list").hide();
			$("#send_message").hide();
			$("#detail_message").hide();
			//창키면 알람 제거해준다.
			$('#message').on('show.bs.popover', function() {
				$(this).attr("data-content", $("#list").html());
				$("#alarm").remove();
			})
			//메시지 창 누르고 처리해야될부분 여기다 이벤트 달아도된다.
			$('#message').on('shown.bs.popover',function() {
				id="search_friend";
				$(".popover-content>div>div:eq(1)>a").attr("id","new_message");
				//새 메시지 보내기(여러명한테)
				$("#new_message").on("click",function(){
					//설정하는 부분
					$(".popover-content").html($("#send_message").html());
					$(".popover-content>div>div:eq(1)>input[type=text]").attr("id","search_friend");
					$('#search_friend').popover();
					//창 나오기전에
					$('#search_friend').on('show.bs.popover',function(){
					});
					//창 나오고나서
					$('#search_friend').on('shown.bs.popover',function(){
						$(".popover-content:eq(1)").addClass("result");
					});
					//포커스아웃은 좀 고민하고 달자
//					$('#search_friend').on("focusout",function(){
//						$('#search_friend').popover("hide");
//					})
					$(".popover-content>div>div:eq(3)").attr("id","send_list");
					$(".popover-content>div>div:eq(4) textarea").attr("id","message_area");
					$(".popover-content>div>div:eq(5) input[type=button]").attr("id","send");
					$("#search_friend").on("keypress",function(event){
						//그냥 암것도 안한다.
						if(event.which==13){
						}
					});
					$("#search_friend").on("click",function(event){
					});
					$("#search_friend").on("keyup",function(event){
						$('#search_friend').popover("show");
						var url=$("#search_form").attr("action");
						//서버가서 등록된 친구목록에서 일치하는거 가져와야한다.
						if(event.which!=13){
							$.ajax({
								url : url,
								type : "get",
								data : {
									keyword : $(this).val(),
									type : "search"
								},
								success : function(res){
									var response=JSON.parse(res);
									var list=response.list;
									if(response.result=="success"){
										$(".result:eq(1)").html("");
										$(".result:eq(1)").append("<ul class='list-group'>");
										for(var i=0;i<list.length;++i){
											$(".result:eq(1)").append('<li class="list-group-item">'
													+list[i].name
													+"("
													+list[i].id
													+")"
													+"</li>");
										}
										$(".result:eq(1)").append("</ul>");
										$(".list-group-item").on("click",function(){
											$("#search_friend").val("");
											$("#send_list").append($(this).html()+" ");
											$("#search_friend").popover("hide");
										});
									}
								},
								error : function(){
									
								}
							})
						}
					});
					$("#send").on("click",function(){
						var people=$("#send_list").html();
						send_to_people(people);						
					});
				});
				//대화 상대 목록 불러오기
				chat_people();
				//한명과 상세대화 하기
				$(".talk").on("click",function(){
					var name=$(this).children("div").eq(1).html();
					$(".popover-content").html($("#detail_message").html());
					go_detail(name);
					detail();
				});
				//한명과의 대화 전체 삭제하기
				$(".talk>div:last").on("click",function(){
					var target=$(this);
					remove_all(target);
				});
			});
			//메시지 보내는 메소드(여러명한테)
			function send_to_people(people) {
				var today = new Date();
				var data = $("#message_area").val();
				$("#message_area").val("");
				var url = $("#send_message_form").attr(
						"action");
				var year=today.getYear() + 1900;
				var month=today.getMonth() + 1;
				var day=today.getDate();
				var hour=today.getHours();
				var min=today.getMinutes();
				var sec=today.getSeconds();
				var date=year+"."+month+"."+day+" "+hour+":"+min;
				$.ajax({
						url : url,
						data : {
							type : "send_to_people",
							year : year,
							month : month,
							day : day,
							hour : hour,
							minute : min,
							second : sec,
							contents : data,
							//이름말고 수신자,발신자 아이디써야한다.
							sender : "이은호",
							receiver : people
						},
						type : "post",
						success : function(res) {
							$("#send_list").html("");
							//성공시 그냥 있는 데이터로 메시지 목록에 한개 추가해주면되지않나?
							if (res.trim() == "success") {
								alert("메시지 전송을 성공했습니다.");
							}
							else{
								alert("메시지 전송을 실패했습니다.");
							}
						},
						error : function() {

						}
				});
			}
			//메시지 보내는 메소드(상세보기 창에서 한명한테)
			function send(people) {
				var today = new Date();
				var data = $("#message_area").val();
				$("#message_area").val("");
				var url = $("#send_message_form").attr(
						"action");
				var year=today.getYear() + 1900;
				var month=today.getMonth() + 1;
				var day=today.getDate();
				var hour=today.getHours();
				var min=today.getMinutes();
				var sec=today.getSeconds();
				var date=year+"."+month+"."+day+" "+hour+":"+min;
				$.ajax({
						url : url,
						data : {
							type : "send",
							year : year,
							month : month,
							day : day,
							hour : hour,
							minute : min,
							second : sec,
							contents : data,
							//이름말고 수신자,발신자 아이디써야한다.
							sender : "이은호",
							receiver : people
						},
						type : "post",
						success : function(res) {
							var result=JSON.parse(res);
							var list=result.list;
							//성공시 그냥 있는 데이터로 메시지 목록에 한개 추가해주면되지않나?
							if (result.result == "success") {
								//미친짓 같은대?...
								$("#message_list").append(
											"<div class='col-md-10 col-md-offset-2'>"
													+ "<div class='panel panel-default'>"
														+ "<div class='panel-body'>"
															+ "<div class='col-md-12'>"
															+result.contents
															+"</div>"
															+ "<div class='col-md-5 col-md-offset-8'>"+date+"</div>"
														+ "</div>"
													+ "</div>"
											+ "</div>");
								//등록성공시 이벤트 달아야한다.
								$("#message_list>div>div:last").on("click", function() {
									var target=$(this);
									remove(target);
								});
								//메시지 등록하면 맨 아래로 이동해줘야한다.
								var test=$("#message_list>div:last").css('height');
								//실제로 추가되는 높이 계산하려고
								height+=132;
								$(".pre-scrollable").scrollTop(height);
							}
						},
						error : function() {

						}
				});
			}
			//한명과의 메시지 전부 삭제
			function remove_all(target){
				var name=target.children("div").eq(1).html();
				var url = $("#send_message_form").attr(
				"action");
				//서블릿가서 제거하는 부분 필요하다 .수신자,발신자 아이디 필요하다.이름말고
				$.ajax({
					type : "get",
					data : {
						type :"remove_all",
						sender : "이은호",
						receiver : name,
					},
					url : url,
					success : function(res){
						//성공시 삭제한다.
						if(res.trim()=="success")
							target.remove();
						//실패시 삭제 안한다.
						else{//실패한 경우
							alert("메시지 삭제를 실패했습니다.");
						}
					},
					error :function(){
						
					}
				});
			}
			//한개의 메시지 삭제
			function remove(target){
				var date = target.find("div").last().html();
				var url = $("#send_message_form").attr(
				"action");
				//서블릿가서 제거하는 부분 필요하다 .수신자,발신자 아이디 필요하다.이름말고
				 $.ajax({
					type : "get",
					data : {
						type :"remove",
						sender : "이은호",
						receiver : "박성일",
						date : date
					},
					url : url,
					success : function(res){
						/*var response=JSON.parse(res);
						//성공시 삭제한다.
						 if(response.result=="success")
							target.remove();
						//실패시 삭제 안한다.
						else{//실패한 경우
							alert("메시지 삭제를 실패했습니다.");
						} */
						if(res.trim()=="success")
							target.remove();
						//실패시 삭제 안한다.
						else{//실패한 경우
							alert("메시지 삭제를 실패했습니다.");
						}
					},
					error :function(){
						
					}
				});
			};
			//상세보기창에서 불러야하는 함수
			function detail(){
				//아이디값 부여하는 부분
				$(".popover>div:eq(1) div>div:eq(1)").attr("id", "message_list");
				$(".popover>div:eq(1) div>form div:eq(0) textarea").attr("id","message_area");
				$(".popover>div:eq(1) div>form div:eq(1) input[type=button]").attr("id", "send");
				$("#send").on("click",function(){
					var name=$(".popover-content>div>div:eq(0)>h1").html();
					send(name);
				});
				//스크롤바 높이 구하는 창
				$("#message_area").on("keypress",function(event){
					if(event.which==13){
						var name=$(".popover-content>div>div:eq(0)>h1").html();
						send(name);
					}
				})
				//단일 메시지 클릭이벤트 추가
				$("#message_list>div>div").on("click", function() {
					var target=$(this);
					remove(target);
				});
			}
			
			//상세보기창으로 가기위한 함수
			function go_detail(name){
				//메시지 알림꺼주는거 나중에 구현하자.
				//데이터 가져오는 부분이다.(발신자 정보(아이디,사진,이름),수신자 정보(아이디,사진,이름),메시지 정보목록(메시지내용,날짜,식별정보)을 가져온다)
				var url = $("#send_message_form").attr(
				"action");
				//서블릿가서 제거하는 부분 필요하다 .수신자,발신자 아이디 필요하다.이름말고
				 $.ajax({
					url : url,
					type : "post",
					data : {
						type :"getMessage",
						//수신자,발신자는 원래 아이디 줘야한다.
						sender : "이은호",
						receiver : name,
					},
					success : function(res){
						var response=JSON.parse(res);
						if(response.result=="success"){
							var list=response.list;
							$(".popover-content>div>div:eq(0)").html("<h1>"+response.receiver+"</h>");
							for(var i=0;i<list.length;++i){
								var date=list[i].year+"."+list[i].month+"."+list[i].day+" "+list[i].hour+":"+list[i].min;
								$("#message_list").append(
										"<div class='col-md-10'>"
												+ "<div class='panel'>"
													+ "<div class='panel-body'>"
														+ "<div class='col-md-12'>"
														+list[i].contents
														+"</div>"
														+ "<div class='col-md-5'>"+date+"</div>"
													+ "</div>"
												+ "</div>"
										+ "</div>");
								if(list[i].type=="me")
									$("#message_list>div:last").addClass("col-md-offset-2");
								$("#message_list>div:last>div").addClass("panel-default");
								$("#message_list>div:last>div>div>div:last").addClass("col-md-offset-8");
								//등록성공시 이벤트 달아야한다.
								$("#message_list>div>div:last").on("click", function() {
									var target=$(this);
									remove(target);
								});
							}
							height=132*list.length;
						}
					},
					error :function(){
						
					}
				});
				//받아왔다고 가정
			};
			//채팅하는 사람 목록가져오는 메소드(스크롤 달아야한다.)
			function chat_people(){
				var url = $("#send_message_form").attr(
				"action");
				//서블릿가서 제거하는 부분 필요하다 .수신자,발신자 아이디 필요하다.이름말고
				 $.ajax({
					url : url,
					type : "post",
					data : {
						type :"chat_people",
						//수신자 원래 아이디 줘야한다.
						sender : "이은호",
					},
					success : function(res){
						var response=JSON.parse(res);
						if(response.result=="success"){
							var list=response.list;
							for(var i=0;i<list.length;++i){
								var date=list[i].year+"."+list[i].month+"."+list[i].day;
								$(".popover-content>div>div:last").append(
									"<div class='panel-body talk'>"
										+"<div class='col-md-1'>"
											+"<img src="+$('#path').html()+"/resources/images/"+list[i].img+" alt=이미지가 없습니다. class=img-circle width=40px height=40px>"
										+"</div>"
										+"<div class='col-md-3'>"
											+list[i].person
										+"</div>"
										+"<div class='col-md-2 col-md-offset-6'>"
											+date
										+"</div>"
										+"<div class='col-md-10'>"
											+list[i].contents
										+"</div>"
										+"<div class='col-md-1'>"
											+"<span class='glyphicon glyphicon-trash aria-hidden=true'></span>"
										+"</div>"
									+"</div>" 
								);
								$(".talk:last").on("click",function(){
									var name=$(this).children("div").eq(1).html();
									$(".popover-content").html($("#detail_message").html());
									go_detail(name);
									$(".pre-scrollable").scrollTop(height);
									detail();
								});
								$(".talk:last>div:last").on("click",function(){
									var target=$(this).parent();
									remove_all(target);
									return false;
								});
							}
						}
					},
					error :function(){
						
					}
				});
			}
		})