
$(function() {
	$('#revise_story_image').on('click',function(){
		
	});
	
	// 비공개일 경우 공유여부 선택못하게
	$('#reviseOpen').change(function() {
		var check = $('#open option:selected').val();
		if (check == '비공개') {
			$('#share').prop('disabled', 'disabled');
		} else {
			$("#share").removeAttr("disabled");
		}
	});

	// 공개일 경우
	$('#reviseYes').click(function() {
		if ($("#reviseShare").prop('disabled'))
			$("#reviseShare").removeAttr("disabled");
	});
	// 비공개일 경우
	$('#reviseNo').click(function() {
		$('reviseShare').prop('disabled', 'disabled');
	});
	$('#revise_btn').click(function() {
		// 시(hour) 정보 받아오기
		var selected_hour = $('#reviseHour').val();
		// 분(min) 정보 받아오기
		var selected_min = $('#reviseMin').val();
		if (selected_hour == "" || selected_min == "") {
			alert("시간 정보를 입력해주세요!");
			return false;
		}
		// 날씨 정보 받아오기
		var selected_weather = $('#reviseWeather option:selected').val();
		// 공개여부 정보 받아오기
		var selected_open = $('#reviseOpen option:selected').val();
		// 공유여부 정보 받아오기
		var selected_share = $('#reviseShare option:selected').val();
		// 위치명 가져오기 ( 위도, 경도도 받아와서 전송해야 )
		var location = $('#reviseLocation').html();
		// 일기 내용 받아오기
		var content = $('#revise_story_content').val();
		var url_location = $('#reviseStoryForm').attr('action');
		url_location+="?key=modifyStory";
		
		var formData=new FormData();
		formData.append("location",location);
		formData.append("content",content);
		formData.append("hour",selected_hour);
		formData.append("min",selected_min);
		formData.append("weather",selected_weather);
		formData.append("open",selected_open);
		formData.append("share",selected_share);
		formData.append("img",$('#revise_file')[0].files[0]);
		
		/*$.ajax({
			url : url_location,
			type : "post",
			data : {// 입력한 스토리 정보 전송(위치 정보, 날짜
					// 정보(년,월,일,시,분),사진,내용,공개여부,공유여부,날씨정보)
				"location" : location,
				"content" : content,
				"hour" : selected_hour,
				"min" : selected_min,
				"weather" : selected_weather,
				"open" : selected_open,
				"share" : selected_share
			
			 * , "s_mon":s_month, "s_day":s_day, "s_hour":s_hour, "s_min":s_min,
			 * "e_year":e_year, "e_mon":e_month, "e_day":e_day, "e_hour":e_hour,
			 * "e_min":e_min
			 
			},
			success : function(response) {
				var data = JSON.parse(response);
				console.log(data);
				if (data.result.trim() == "success") {
					alert("데이터 전송 성공");
				} else {
					alert(data.result)
				}
			},
			error : function() {
				alert("날짜 전송 중 문제가 생겼습니다!")
			}
		});*/
		
		$.ajax({
			url : url_location,
			type : "post",
			data : formData,
			processData: false,
			contentType: false,
			success:function(res){
				console.log(res);
				var data = JSON.parse(res);
				if (data.result.trim() == "success") {
					alert("데이터 전송 성공");
					$('#reviseModal').modal('hide');
					console.log($('#path').html()+'/resources/images/'+data.fileName);
					$('#modify_story_image').removeAttr('src');
					$('#modify_story_image').attr('src',$('#path').html()+'/resources/images/'+data.fileName+"?="+data.num);
					console.log($('#modify_story_image'));
					$('#modify_story_image').removeAttr('id');
					$('#modify_story_image').attr('src','');
				} else {
					alert(data.result);
				}
				
			},
			error:function(){
				alert("등록실패");
			}
		});
	});
	$('#revise_cancel_btn').click(function(){
		$('#reviseModal').modal('hide');
		//값지우는거 필요
	});
});
