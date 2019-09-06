$(function() {
	//회원 가입 요청시
	$("#join_btn").on("click",function() 
			{
					// 유효성 검증
					var id_check = /[^0-9가-힣ㄱ-ㅎㅏ-ㅣ][^가-힣ㄱ-ㅎㅏ-ㅣ]{7,15}/;// 아이디
					var pwd_check = /[^가-힣ㄱ-ㅎㅏ-ㅣ]{8,16}/;// 비밀번호
					var name_check = /[가-힣]{1,20}/;// 이름 
					var call_check = /[^. \-\(\)0-9]/;// 전화번호인지 이메일인지 구분하기 위해서
					var phone_check = /^([0-9]{3}|\([0-9]\){3})[-. ]?([0-9]{3,4})[-. ]?([0-9]{4}$)/;// 전화번호
					var email_check=/^([a-zA-Z0-9]{1,40})@[a-zA-Z0-9]{2,20}.[a-zA-Z0-9]{2,10}(.[a-zA-Z0-9]{2,10})?/;
					// 사용자가 입력한 정보
					var user_id = $("#juser_id").val();
					var user_pwd = $("#juser_pwd").val();
					var user_pwd_check = $("#juser_pwd_check").val();
					var user_name = $("#juser_name").val();
					var user_call = $("#juser_call").val();

					// 부분일치하는거 제외하기 위해서
					var id_part = id_check.exec(user_id);// 부분일치하는것도 가져와서
					var pwd_part = pwd_check.exec(user_pwd);
					var pwd_check_part = pwd_check.exec(user_pwd_check);
					var name_part = name_check.exec(user_name);
					console.log($("#agree").is(":checked"));
					//약관 동의 여부 체큰
					if(!$("#agree").is(":checked")){
						$(".error_message").html(
						"약관에 동의해야 가입 가능합니다.");
					}
					// 아이디 확인(중복 체크는 서버에서)
					else if (user_id == "") {
						$(".error_message").html("아아디를 입력하세요");
					} else if (id_part == null || id_part[0] != user_id) {
						$(".error_message").html(
								"아이디는 8~16글자 가능합니다(한글제외,숫자로 시작 불가능).");
					}
					
					// 비밀번호,비밀번호 조건 확인(비밀번호 틀린건 서버에서)
					else if (user_pwd == "") {
						$(".error_message").html("비밀번호를 입력하세요");
					} else if (user_pwd_check == "") {
						$(".error_message").html("비밀번호 확인을 입력하세요");
					} else if (pwd_part == null || pwd_part[0] != user_pwd) {
						$(".error_message").html(
								"비밀번호는 8~16글자 가능합니다(한글 제외).");
					} else if (pwd_check_part == null
							|| pwd_check_part[0] != user_pwd_check) {
						$(".error_message").html(
								"비밀번호 확인은 8~16글자 가능합니다(한글 제외).");
					} else if (user_pwd != user_pwd_check) {
						$(".error_message").html("비밀번호와 비밀번호 확인이 다릅니다.");
					}

					// 이름 확인
					else if (user_name == "") {
						$(".error_message").html("이름을 입력하세요.");
					} else if (name_part == null
							|| name_part[0] != user_name) {
						$(".error_message").html("이름은 한글 20글자까지 가능합니다.");
					}

					// 이메일,전화번호 공통
					else if (user_call == "") {
						$(".error_message").html("이메일이나 연락처를 입력하세요.");
					}

					// 이메일
					else if (call_check.test(user_call)) {
						var email_part = email_check.exec(user_call);
						if (email_part == null || email_part[0] != user_call) {
							$(".error_message").html("올바른 이메일 주소를 입력해주세요.");
						}
						else{
							join(user_id,user_pwd);
						}
					}
					// 연락처
					else if (!call_check.test(user_call)) {
						var phone_part = phone_check.exec(user_call);
						if (phone_part == null || phone_part[0] != user_call) {
							$(".error_message").html("연락처가 정확하지 않습니다.");
						}
						else{
							join(user_id,user_pwd);
						}
					}
					// 여기오는 경우는 없다..사실 위에서 다 해결된다.
					else {
						
					}
	});//회원가입 요청 끝
	
	//회원가입 비동기 처리하는 부분
	function join(user_id,user_pwd){
		var param = $("#join_form").serialize();
		var url = $("#join_form").attr("action");
		$.ajax({
			type : "post",
			data : param+"&type=join",
			url : url,
			success : function(res) {
				// 성공할 경우 로그인 화면(홈)으로
				// 실패할 경우 메시지를 제공한다.
				if(res.trim()=="success"){
					$(".error_message").html("");
					//로그인 어떻게 시키지? ㅋㅋ
					$("#user_id").val(user_id);
					$("#user_pwd").val(user_pwd);
					$("#login_form").submit();
				}
			},
			error : function() {
				// 에러 페이지 제공해야한다.
			}
		})
	}
});