
	// 비공개일 경우 공유여부 선택못하게
	$('#open').change(function() {
		var check = $('#open option:selected').val();
		if (check == '비공개') {
			$('#share').prop('disabled', 'disabled');
		} else {
			$("#share").removeAttr("disabled");
		}
	});

	// 공개일 경우
	$('#yes').click(function() {
		if ($("#share").prop('disabled'))
			$("#share").removeAttr("disabled");
	});
	// 비공개일 경우
	$('#no').click(function() {
		$('share').prop('disabled', 'disabled');
	});
	// 저장 버튼 클릭하였을때
	$('#save_btn').click(function() {
		alert('저장');
		// 시(hour) 정보 받아오기
		var selected_hour = $('#hour option:selected').val();
		// 분(min) 정보 받아오기
		var selected_min = $('#min option:selected').val();
		if (selected_hour == "" || selected_min == "") {
			alert("시간 정보를 입력해주세요!");
			return false;
		}
		// 날씨 정보 받아오기
		var selected_weather = $('#weather option:selected').val();
		// 공개여부 정보 받아오기
		var selected_open = $('#open option:selected').val();
		// 공유여부 정보 받아오기
		var selected_share = $('#share option:selected').val();
		// 위치명 가져오기 ( 위도, 경도도 받아와서 전송해야 )
		var location = $('#location').html();
		//위도 경도 가져오기
		var lat=markersPosition.lat();
		var lng=markersPosition.lng();
		// 일기 내용 받아오기
		var content = $('#story_content').val();
		var url_location = $('#storyForm').attr('action');
		/*$.ajax({
			url : url_location,
			type : "post",
			fileElementId:'file',
			data : {// 입력한 스토리 정보 전송(위치 정보, 날짜
					// 정보(년,월,일,시,분),사진,내용,공개여부,공유여부,날씨정보)
				"location" : location,
				"lat":lat,
				"lng":lng,
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
					$('#registerModal').hide();
					deletAllMarkers();
					
					
				} else {
					alert(data.result)
				}
			},
			error : function() {
				alert("날짜 전송 중 문제가 생겼습니다!")
			}
		});*/
		
		//console.log($('#file')[0]);
		var formData = new FormData();
		formData.append('location',location);
		formData.append('lat',lat);
		formData.append('lng',lng);
		formData.append('content',content);
		formData.append('hour',selected_hour);
		formData.append('min',selected_min);
		formData.append('weather',selected_weather);
		formData.append('open',selected_open);
		formData.append('share',selected_share);
		formData.append('img', $('input[type=file]')[0].files[0]);
		
		$.ajax({
			url: url_location,
		    type: 'POST',
		    data: formData,
		    async: false,
		    cache: false,
		    contentType: false,
		    processData: false,
		    success: function (returndata) {
		      alert("등록성공");
		      $('#registerModal').modal('hide');
		     //어떻게 받아와서 처리 하는지??(준기)
		     
				deleteAllPath();//기존의 path(선)지운다.
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
				array.push(markersPosition);//전역변수로 등록시 지도에 클릭한 위치
				deletAllMarkers();//기존의 모든 마커를 다지운다.
//				deleteMarker(markers[markers.length-1]);
				prePositionArray=array;//어쩔수 없이 customMap의 전역변수에 넣어줘야함 차후 수정 요망
				contentArray.push('<div class="hovereffect imageClick"><img src="../images/a5e7e91b081cc7be5f93fa09360dfbb7.png" alt="..." width="100px" height="100px"><div class="overlay"><h6>♥ 12</h6><p><a href="#">review 13</a></p> </div></div>');
				contentArray.push('<div class="hovereffect imageClick"><img src="../images/a5e7e91b081cc7be5f93fa09360dfbb7.png" alt="..." width="100px" height="100px"><div class="overlay"><h6>♥ 12</h6><p><a href="#">review 13</a></p> </div></div>');
				contentArray.push('<div class="hovereffect imageClick"><img src="../images/a5e7e91b081cc7be5f93fa09360dfbb7.png" alt="..." width="100px" height="100px"><div class="overlay"><h6>♥ 12</h6><p><a href="#">review 13</a></p> </div></div>');
				
				fitBound(array, contentArray, map);//자동으로 센터와 줌사이즈 설정
				drawPath(array);
		    },
			error : function() {
				alert("날짜 전송 중 문제가 생겼습니다!")
			}
		});
	});
	$('#cancel_btn').click(function(){
		$('#registerModal').modal('hide');

		
	});