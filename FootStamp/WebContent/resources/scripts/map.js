/**
 * Map에서 스토리 등록, 조회, 수정 시 날짜정보 조회 부분
 */

// 날짜 선택 후 적용 버튼 눌렀을 때
$('#daterange').on('apply.daterangepicker', function(ev, picker) {
	var s_year = picker.startDate.format('YYYY');
	var s_month = picker.startDate.format('MM');
	var s_day = picker.startDate.format('DD');
	var s_hour = picker.startDate.format('HH');
	var s_min = picker.startDate.format('mm');
	
	var e_year = picker.endDate.format('YYYY');
	var e_month = picker.endDate.format('MM');
	var e_day = picker.endDate.format('DD');
	var e_hour = picker.endDate.format('HH');
	var e_min = picker.endDate.format('mm');
	
	//선택 날짜에 따른 Date 객체 생성
	var s_d = new Date(s_year+"-"+s_month+"-"+s_day);
	var e_d = new Date(e_year+"-"+e_month+"-"+e_day);
	//유효성 체크
	if(s_d==null || e_d==null){
		alert("날짜입력 형식에 오류가 있습니다!");
		return false;
	}
	//날짜 버튼 색상 변경
	$('#daterange').css('background-color', 'white');
	$('#daterange').css('color', '#FF5A5A');
	$('#daterange').hover(function(){
		$('#daterange').css('background-color', '#FF5A5A');
		$('#daterange').css('color', 'white');
	},function(){
		$('#daterange').css('background-color', 'white');
		$('#daterange').css('color', '#FF5A5A');
	});	
	//현재 날짜 체크
	var temp_d = new Date(d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate());
	//아이디 유효성 체크도 있어야
	//현재 날짜 보다 미래 날짜인지 검사
	if(e_d > temp_d){
		alert("스토리 작성 날짜는 오늘까지만 가능합니다.");
	}
	//현재 날짜 이내일 경우
	else
	{
		//요일 정보 생성
		var days = ["일","월","화","수","목","금","토"];
		var dayOfS = days[s_d.getDay()];
		var dayOfE = days[e_d.getDay()];
		/* $('#cur_date').html(s_year+"."+s_month+"."+s_day+".("+s_hour+":"+s_min+") ~"+
								e_month+"."+e_day+"."+e_hour+"시"+e_min+"분"); */
		//같은 요일이 아닐 경우
		if(dayOfS!=dayOfE)
			$('#cur_date').html(picker.startDate.format('YYYY.MM.DD('+dayOfS+')HH:mm')+" ~ "+picker.endDate.format('MM.DD('+dayOfE+')HH:mm'));
		//같은 요일일 경우
		else
			$('#cur_date').html(picker.startDate.format('YYYY.MM.DD('+dayOfS+')HH:mm')+" ~ "+picker.endDate.format('HH:mm'));
	
		//SearchStory 서블릿으로 요청 전송
		var url_location = $('#dayForm').attr('action');
		$.ajax({
			url:url_location,
			type:"get",
			data:{//계정 정보와 스토리 검색할 시작일, 종료일 지정
				key : "calenderSearch",
				"id":"Test ID",
				"s_year":s_year,
				"s_mon":s_month,
				"s_day":s_day,
				"s_hour":s_hour,
				"s_min":s_min,
				"e_year":e_year,
				"e_mon":e_month,
				"e_day":e_day,
				"e_hour":e_hour,
				"e_min":e_min
			},
			success:function(response){
				var data=JSON.parse(response);
				console.log(data);
				if(data.result.trim()=="success"){
					//수정 성공시 메세지 출력 후 테이블 갱신
					alert(picker.endDate.format('MM월DD일')+"스토리가 추가 또는 조회 완료");
					//성공 시 지도에 마커 생성하여 반영하는 부분
					deletAllMarkers();//기존의 모든 마커를 다지운다.
					deleteAllPath();//기존의 path(선)지운다.
					var array = [];
					var contentArray = [];
					array.push({
						lat : 37.3563989,
						lng : 126.4160863
					});
					array.push({
						lat : 37.5663989,
						lng : 126.6260863
					});
					array.push({
						lat : 37.5763989,
						lng : 126.9860863
					});
					array.push({
						lat : 37.5669989,
						lng : 126.8230863
					});
					array.push({
						lat : 37.5560989,
						lng : 126.9100863
					});
					array.push({
						lat : 37.5660989,
						lng : 126.9200863
					});
					prePositionArray=array;//어쩔수 없이 customMap의 전역변수에 넣어줘야함 차후 수정 요망
					contentArray.push('<div class="hovereffect imageClick"><img src="../images/a5e7e91b081cc7be5f93fa09360dfbb7.png" alt="..." width="100px" height="100px"><div class="overlay"><h6>♥ 12</h6><p><a href="#">review 13</a></p> </div></div>');
					contentArray.push('<div class="hovereffect imageClick"><img src="../images/a5e7e91b081cc7be5f93fa09360dfbb7.png" alt="..." width="100px" height="100px"><div class="overlay"><h6>♥ 12</h6><p><a href="#">review 13</a></p> </div></div>');
					contentArray.push('<div class="hovereffect imageClick"><img src="../images/a5e7e91b081cc7be5f93fa09360dfbb7.png" alt="..." width="100px" height="100px"><div class="overlay"><h6>♥ 12</h6><p><a href="#">review 13</a></p> </div></div>');
					contentArray.push('<div class="hovereffect imageClick"><img src="../images/a5e7e91b081cc7be5f93fa09360dfbb7.png" alt="..." width="100px" height="100px"><div class="overlay"><h6>♥ 12</h6><p><a href="#">review 13</a></p> </div></div>');
					contentArray.push('<div class="hovereffect imageClick"><img src="../images/a5e7e91b081cc7be5f93fa09360dfbb7.png" alt="..." width="100px" height="100px"><div class="overlay"><h6>♥ 12</h6><p><a href="#">review 13</a></p> </div></div>');
					contentArray.push('<div class="hovereffect imageClick"><img src="../images/a5e7e91b081cc7be5f93fa09360dfbb7.png" alt="..." width="100px" height="100px"><div class="overlay"><h6>♥ 12</h6><p><a href="#">review 13</a></p> </div></div>');
					fitBound(array, contentArray, map);//자동으로 센터와 줌사이즈 설정
					drawPath(array);
				}
				else{
					alert(data.result)
				}
			},
			error:function(){
				alert("날짜 전송 중 문제가 생겼습니다!")
			}
		});
	}
});

function showFile(img,pan){
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
	console.log(pan);
	reader.onload = function(rst) {
		if(pan==1){
		$('#file_input_space').attr('style', 'display : none;');
		$('#sel_image_space').removeAttr('style');
		$('#sel_image_space').html('');
		$('#sel_image_space').append(
				'<img align="center" src="' + rst.target.result
						+ '" class="img_change_color" ">'); // append 메소드를
		}else{								// 사용해서 이미지 추가
			$('#revise_story_image').removeAttr('scr');
			$('#revise_story_image').attr('src',rst.target.result);
			
		}
		// 이미지는 base64 문자열로 추가
		// 이 방법을 응용하면 선택한 이미지를 미리보기 할 수 있음
	}
	reader.readAsDataURL(file[0]);
}

$(function(){
	$('#daterange').click(function(){
		//날짜 버튼 색상 변경
		$('#daterange').css('background-color', '#FF5A5A');
		$('#daterange').css('color', 'white');
	});
	
	$('#file_input_space').on('click',function(){
		$('#file').trigger('click');
	});
	$('#sel_image_space').on('click',function(){
		$('#file').trigger('click');
	});
	$('#revise_story_image').on('click',function(){
		$('#revise_file').trigger('click');
	})
});