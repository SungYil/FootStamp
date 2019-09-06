$(function(){
	
	$("#find_pwd_window").hide();
	$("#find_id_window").hide();
	//페이지 첫 요청시
	if($("#find_id").hasClass("active")){
		$("#find_id_window").show();
	}
	else{
		$("#find_pwd_window").show();
	}
	//아아디 찾기 요청할 경우
	$("#id_check_btn").on("click",function(){
		find("find_id",$("#find_id_input").val());
	})
	//비밀번호 찾기 요쳥할 경우
	$("#pwd_check_btn").on("click",function(){
		find("find_pwd",$("#find_pwd_input").val());
	})
	$("#find_pwd").on("click", function() {
		$("#find_pwd_window").show();
		$("#find_id_window").hide();
	});
	
	$("#find_id").on("click", function() {
		$("#find_id_window").show();
		$("#find_pwd_window").hide();
	});
	
	$(".back_btn").on("click",function(){
		location.href="${pageContext.request.contextPath }/move?page=start"
	})
	function find(type,keyword){
		$.ajax({
			url : "/FootStamp/member.do",
			type : "get",
			data : {
				type : type,
				keyword : keyword
			},
			success : function(res){
				var result=res.trim();
				if(result=="success"){
					alert("전송했습니다.")
				}
				else{
					alert("잘못된 입력입니다.")
				}
			},
			error : function(){
				
			}
		})
	}
});