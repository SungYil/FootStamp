$(function(){
	

	$('#following').on('click',function(){ //팔로잉 
		$('#fallow_modal').modal('show');
	});
	$('#follower').on('click',function(){
		$('#follower_modal').modal('show');

	});
	$('.following_class').on('click',function(){
		var style=$(this).attr("style");
		var id=$(this).parent().prev().find('td');
		console.log(id.eq(0).html());
		var url_location = $('#password').attr('action');
		var real_url = url_location+'/fal.do?id='+id.eq(0).html();
		if(style=="margin-left:20px;background-color:#FF5A5A;"){
			$(this).attr("style","margin-left:20px;background-color:#FFFFFF;")
			$(this).html("팔로우")
		}else{
			$(this).attr("style","margin-left:20px;background-color:#FF5A5A;")
			$(this).html("팔로잉")
		}
		$.ajax({
			type : 'get',
			url : real_url,
			success : function(res){
				alert("ok");
			}
		
		});
	});
	
	
	
	
	$('#profile_modify').on('click',function(){ //프로필 처음 암호입력
		$('#modify_top_message').removeAttr('style');
		$('#change_modal_content_passinp').removeAttr('style');
		$('#change_modal_content_button').attr('style','display : none;');
		$('#change_modal_content_infoInput').attr('style','display : none;');
		$('#change_modal_content_image').attr('style','display : none;');
		$('#select_img_file').html('');
		
		$('#profile_modify_modal').find('input').val('');
		$('#profile_modify_modal').modal('show');
	});
	$('#profile_modify_stopBut').on('click',function(){
		$('#profile_modify_modal').modal('hide');
	});
	$('#profile_modify_myInfo').on('click',function(){
		$('#change_modal_content_infoInput').removeAttr('style');
		$('#userid_input').val($('#user_id').html());
		$('#name_input').val($('#names').html());
		$('#phoneNumber_input').val($('#numbers').html());
		$('#change_modal_content_button').attr('style','display : none;');
	});
	$('#profile_modify_cancel').on('click',function(){
		$('#profile_modify_modal').modal('hide');
	});
	$('#profile_modify_img').on('click',function(){
		$('#change_modal_content_button').attr('style','display : none;');
		$('#change_modal_content_image').removeAttr('style');
	});
	$('#profile_modify_image').on('click',function(){
		var formData=new FormData();
		formData.append('file',$('#file_upload').children('input')[0].files[0]);
		var url_location = $('#file_upload').attr('action')+'?type=profileImg_Modify';
		$.ajax({
			url : url_location,
			type : 'post',
			data : formData,
			async: false,
		    cache: false,
		    contentType: false,
		    processData: false,
			success: function(res){
				alert('업로드 성공!');
				res=res.trim();
				console.log(res);
				var location=$('#profile_image').attr('src');
				$('#profile_image').removeAttr('src');
				$('#profile_image').attr('src',location+"?num=1");
				$('#profile_modify_modal').modal('hide');
			},
			error : function(){
				alert('업로드 실패!');
			}
		});
	/*	$('#file_upload').submit();*/
	});
	$('#profile_modify_ok').on('click',function(){
		var dat = $('#modify_form').serialize();
		dat+='&type=profile_Modify';
		var url_location = $('#modify_form').attr('action');
		$.ajax({
			type : 'post',
			data:dat,
			url:url_location,
			success:function(res){
				alert('ok');
				$('#profile_modify_modal').modal('hide');
			}
		})
	});
	$('#pofilemodal_pass_inp').on('click',function(){
		var dat=$(this).parent().prev().find('input');
		var url_location = $('#password').attr('action');
		if(dat.val()==''){
			alert("아무것도 입력안하셨습니다.");
		}
		else{
			var out='id='+$('#user_id').html()+'&password='+dat.val()+'&type=check_password';
			$.ajax({
				type : 'post',
				data : out,
				url : url_location,
				success : function(res){
					$('#modify_top_message').attr('style','display : none;');
					$('#change_modal_content_passinp').attr('style','display : none;');
					$('#change_modal_content_button').removeAttr('style');
				}
			});
		}
	})
	
});

function showFile(img){
	var file = img.files;

	if(file[0].size > 1024 * 1024){
		alert('1MB 이상의 파일은 업로드 불가.');
		return;
	}
	else if(file[0].type.indexOf('image') < 0){ // 선택한 파일이 이미지인지 확인
		alert('이미지 파일만 선택하세요.');
		return;
	}
	
	var reader = new FileReader(); // FileReader 객체 사용
	reader.onload = function(rst){
		var text="height:200px;width:200px;"
		$('#select_img_file').html('');
		$('#select_img_file').append('<img align="center" src="' + rst.target.result + '" style="height:200px;width:200;">'); // append 메소드를 사용해서 이미지 추가
		// 이미지는 base64 문자열로 추가
		// 이 방법을 응용하면 선택한 이미지를 미리보기 할 수 있음
	}
	reader.readAsDataURL(file[0]); 
}