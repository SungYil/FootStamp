$(function(){
	$("#login_btn").on("click",function(){
		//아이디 조건 8~16자리 시작은 알파벳
		var id_check=/[^0-9가-힣ㄱ-ㅎㅏ-ㅣ]{1}[^가-힣ㄱ-ㅎㅏ-ㅣ]{7,15}/;
		//비밀번호 조건 한글빼고 8~16글자
		var pwd_check=/[^가-힣ㄱ-ㅎㅏ-ㅣ]{8,16}/;
		//아이디,비밀번호가 조건에 맞을경우
		var user_id=$("#user_id").val();
		var user_pwd=$("#user_pwd").val();
		//일치하는 부분과 아이디가 완전 일치하는지 확인하려고
		var id_part=id_check.exec(user_id);//부분일치하는것도 가져와서
		var pwd_part=pwd_check.exec(user_pwd);
		if(user_id==""){
			$(".error_message").html("아아디를 입력하세요");
		}
		else if(user_pwd==""){
			$(".error_message").html("비밀번호를 입력하세요");
		}
		else if( id_part==null || id_part[0]!=user_id ){
			$(".error_message").html("아이디는 8~16글자 가능합니다(한글제외,숫자로 시작 불가능).");
		}
		else if( pwd_part==null || pwd_part[0]!=user_pwd ){
			$(".error_message").html("비밀번호는 8~16글자 가능합니다(한글 제외).");
		}
		else{
			$("#login_form").submit();
		}
	})
})
