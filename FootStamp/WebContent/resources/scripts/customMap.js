var map;// 사용자 프로필에서 달력 눌러서 지도는 그대로 놔둬야 하기 때문에 필요
var markersPosition;// 스토리 등록이나 스토리 수정시 필요
var markers = [];
var mapCount = 0;
var flightPath;// 마커 이어주는 선들
var prePositionArray;

// 초기 실행 함수
function initMap(elementId, isEditPage, positionArray, content) {

	markersPosition = undefined;
	var mapOptions = {// 우리가 제공하는 맵 옵션
		streetViewControl : false,
		mapTypeControl : false,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	}

	map = new google.maps.Map(document.getElementById(elementId), mapOptions);// 맵객체
																				// 생성
	if (isEditPage == true) {
		prePositionArray = positionArray;// 등록 페이지에서만 전역변수 사용
		map
				.addListener(
						'click',
						function(e) {// 맵을 클릭 했을때 이벤트 발생//사실 스토리 등록시 클릭 막아야함
							deleteAllPath();
							drawPath(prePositionArray);
							if (typeof (markersPosition) != 'undefined') {
								if (markersPosition != e.latLng) {
									deleteMarker(markers[markers.length - 1]);
								}
							}
							var content = '<input type="button" value="스토리등록" id="registStoryBtn"><br><input type="button" value="등록취소" id="cancelStoryBtn">';
							markersPosition = e.latLng;// 내부에서 등록시 인포윈도우 창 떠야함
														// 그이외에는 없는것같다
							addMarker(markersPosition, content, map);

							flightPath.getPath().push(markersPosition);// 마커
																		// 추가될시
																		// 마크
																		// 추가된곳
																		// 까지
																		// 그림그리기
						});
	}
	if (typeof (positionArray) == 'undefined') {// 첫등록인경우
		defaultCenter();// 등록은 디폴트 위치랑 zoom사이즈 설정해야함
	} else {// 기존의 일기일 경우
		fitBound(positionArray, content, map);// 자동으로 센터와 줌사이즈 설정
		drawPath(positionArray);
	}

}
function defaultCenter() {// 서울특별시 방배본동
	map.setCenter({
		lat : 37.4941271,
		lng : 126.98877620000007
	});
	map.setZoom(9);
}
function fitBound(positionArray, contentArray, ownMap) {// 마커 여러개일때 center, zoom
														// 자동으로 잡음
	var bounds = new google.maps.LatLngBounds();// 분리 필요함
	for (var i = 0; i < positionArray.length; i++) {
		bounds.extend(positionArray[i]);
		addMarker(positionArray[i], contentArray[i], ownMap);
	}
	if (positionArray.length > 1) {
		ownMap.fitBounds(bounds);
	} else {
		ownMap.setCenter(positionArray[0]);
		ownMap.setZoom(13);
	}

}
function addMarker(location, content, ownMap) {// 내부에서 무조건 info윈도우 생성하는 메소드
												// 부름//맵에 클릭 리스너에서
												// 호출되면..content(스토리등록,취소)
	var marker = new google.maps.Marker({
		position : location,
		map : ownMap
	});
	markers.push(marker);
	marker.addListener('click', function() {// 마커 클릭시 확대해서 보는 리스너
		ownMap.setZoom(19);
		ownMap.setCenter(marker.getPosition());
	});
	addInfoWindow(marker, content)// addInfoWindow부를때 content를 뭐를 줘야하나
}

function addInfoWindow(marker, content) {// 파라미터 머 필요할지 생각해야함
	if (typeof (content) == 'undefined')// 검색어로 위치검색할때는 정보창이 필요없으므로 리턴
		return false;
	var infowindow = new google.maps.InfoWindow({// 정보창
		maxWidth : 152,
		maxHeight : 300,
		content : content

	});

	infowindow.open(map, marker);// 어디에 정보창 띄울건지.

	// info window의 관련된 이벤트를 다는 곳
	google.maps.event.addListener(
					infowindow,
					'domready',
					function() {
						$('.glyphicon-edit').unbind('click').bind('click',function() {
							var imgPath = $(this).parent('div').prev().find('img').attr('src');// 이미지 src
																	// 이걸 가지고
																	// 서버에서
																	// 정보가져와
																	// 수정모달에
																	// 내용띄움
						$(this).parent('div').prev().find('img').attr('id','modify_story_image');
						$.ajax({
							type : "GET",
							url : "/FootStamp/Story.do",
							data : {
									"imgId" : imgPath,
									"key" : "detailStory"
							},
							success : function(res) {
									var data = JSON.parse(res);
									if (data.result.trim() == 'ok') {// 성공적으로 받아온경우
										$('#reviseLocation').html(data.location);
										$('#reviseHour').val(data.hour);
										$('#reviseMin').html(data.min);
										$("#reviseWeather").val(data.weather.trim()).prop("selected",true)
										$('#revise_story_content').html(data.content);
										$('#reviseModal').modal('show');
										$('#revise_story_image').attr('src',$('#path').html()+ "/resources/images/"+ data.img+"?num="+ data.num);
									} else {
										$('#modify_story_image').removeAttr('id');// 실패일 경우 id값 제거
									}
							},
							error : function(a, b,c) {/* 서버측오류 */
								console.log("asdfasdf");
								console.log(a);
								console.log(b);
								console.log(c);
							}
						});
						});
						// ------------------------스토리 이미지 단일 클릭 시 상세 정보 보여주기
						// (문의:동익)----------------------
						$('.imageClick').unbind('click').bind('click',function() {
							var url = $("#detail_form").attr("action");// 이미지 소스 받아온다.
							var imageSrc = $(this).children('img').attr('src');
							$.ajax({
								type : "GET",
								url : url,
								data : {
									"userId" : "(customMap의 132line)임시 아이디이니 나중에 수정해주세요",
									"imgId" : imageSrc,
									"key" : "detailStory"
								},
								success : function(res) {
									var data = JSON.parse(res);
									var date = data.year+ "."+ data.month+ "."+ data.day+ "("+ data.dayOfWeek+ ")"+ " "+ data.hour+ ":"
																	+ data.min+ " "+ data.weather;
									if (data.result == "ok") {// 성공한경우에 등록   사진 넣는 부분있어야한다.
																// $("#story_image").attr("src",data.img)//data.img
																// 처리하면된다.
										$("#location").html(data.location);
										$("#date").html(date);
										$("#writer").html(data.writer);
										$("#content").html(data.content);
										$("#likeCount").html(data.likeCnt);
										if (data.isLike == "true") {// 좋아요 등록한거
											$("#likeImg").attr("class","on glyphicon glyphicon-heart icons");
										} else {
											$("#likeImg").attr("class","off glyphicon glyphicon-heart-empty icons");
										}
										$("#likeImg").css("color","red");// 서버가서 삭제하거나 추가하는 부분 추가해야한다.(ajax로 처리)
										$("#likeImg").unbind('click').bind('click',function() {// 좋아요 목록에 추가
										var count = Number($("#likeCount").html());
										if ($(this).hasClass("on")) {// 좋아요 취소
										$(this).attr("class","off glyphicon glyphicon-heart-empty icons");
										$("#likeCount").html(count - 1);
										$.ajax({
											url : "/FootStamp/review.do",
											type : "get",
											data : {
												type : "delete",
												kind : "like",
												isStory : "true",
												storyId : "storyId",// 스토리일 경우(임시로 넣어둔거)
												id : "좋아요식별키"// 자기 식별키
											},
										success : function(res) {// 등록 실패하는 경우고려?
											console.log("취소");
										},
										error : function() {
										}
										});
																					} else {// 좋아요
																							// 등록
																						$(
																								this)
																								.attr(
																										"class",
																										"on glyphicon glyphicon-heart icons");
																						$(
																								"#likeCount")
																								.html(
																										count + 1);
																						var today = new Date();
																						var date_form = (today
																								.getYear() + 1900)
																								+ "."
																								+ (today
																										.getMonth() + 1)
																								+ "."
																								+ today
																										.getDate()
																								+ " "
																								+ today
																										.getHours()
																								+ "시"
																								+ today
																										.getMinutes()
																								+ "분";
																						$
																								.ajax({
																									url : "/FootStamp/review.do",
																									type : "post",
																									data : {
																										type : "add",
																										isStory : "true",
																										id : "storyId",// 스토리일
																														// 경우(임시로
																														// 넣어둔거)
																										kind : "like",
																										writer : '${sessionScope.user_id }',
																										date : date_form
																									},
																									success : function(
																											res) {
																										// 등록
																										// 실패하는
																										// 경우
																										// 고려?
																										console
																												.log("등록");

																									},
																									error : function() {

																									}
																								});
																					}
																				});
																$(
																		"#reviewCount")
																		.html(
																				data.reviewCnt);

																$('#showModal')
																		.modal(
																				"show");
															}
														},
														error : function(a, b,
																c) {/* 서버측오류 */
															console.log(a);
															console.log(b);
															console.log(c);
														}
													});
										});
						// info window가 두번 클릭되지 않기 위해
						$('.glyphicon-trash')
								.unbind('click')
								.bind(
										'click',
										function() {
											var result = confirm('정말로 스토리를 삭제 하시겠습니까?');
											if (result) {
												$(this).parent('div').prev()
														.find('img')
														.attr('src');// 이미지
																		// src
																		// 이걸
																		// 가지고
																		// 삭제요청
											} else {
											}
										});
						$('#registStoryBtn').click(function() {
							geocodeLatLng();
							$('#registerModal').modal('show');
							$('#story_content').val('');
							$('#sel_image_space').html('');
							$('#file_input_space').removeAttr('style');
						});
						$('#cancelStoryBtn').click(function() {
							infowindow.close();
							deleteMarker(marker);
							deleteAllPath();
							drawPath(prePositionArray);
						});

						// google 지도의 infowindow 의 스타일 셀렉
						var iwOuter = $('.gm-style-iw');
						// iw의 위치를 먼저 잡아주는 position div셀렉
						var iwBackground = iwOuter.prev();
						// 뒷배경의 그림자 삭제
						iwBackground.children(':nth-child(2)').css({
							'display' : 'none'
						});
						// 뒷배경 삭제
						iwBackground.children(':nth-child(4)').css({
							'border' : 'black',
							'display' : 'none'
						});
						// 하단의 화살표 그림자
						iwBackground.children(':nth-child(1)').css({
							'display' : 'none'
						});
						// 하단의 화살표 그림자 색 변경
						iwBackground.children(':nth-child(3)').find('div')
								.children().css({
									'box-shadow' : '0px 0px',
									'z-index' : '1'
								});
						// 클로즈 버튼 선택
						var iwCloseBtn = iwOuter.next();
						// 클로즈 버튼 display none
						iwCloseBtn.css({
							display : 'none'
						});
						if ($('.iw-content').height() < 140) {
							$('.iw-bottom-gradient').css({
								display : 'none'
							});
						}
					});

}

function centerControl(controlDiv, map, center, zoom) {// 지도에서 원래 center와
														// 원래zoom으로 돌아가려고 할때
	// Set CSS for the control border.
	var controlUI = document.createElement('div');
	controlUI.style.backgroundColor = '#fff';
	controlUI.style.border = '2px solid #fff';
	controlUI.style.borderRadius = '3px';
	controlUI.style.boxShadow = '0 2px 6px rgba(0,0,0,.3)';
	controlUI.style.cursor = 'pointer';
	controlUI.style.marginRight = '10px';
	controlUI.style.marginBottom = '0px';
	controlUI.style.marginTop = '0px';
	controlDiv.appendChild(controlUI);

	// Set CSS for the control interior.
	var controlText = document.createElement('div');
	controlText.style.color = '#737373';

	controlText.style.fontSize = '16px';
	controlText.style.lineHeight = '30px';
	controlText.style.paddingLeft = '4px';
	controlText.style.paddingRight = '4px';

	controlText.innerHTML = '<span class="glyphicon glyphicon-resize-full" aria-hidden="true"></span>';
	controlUI.appendChild(controlText);

	// Setup the click event listeners: simply set the map to Chicago.
	controlUI.addEventListener('mouseover', function() {
		controlText.style.color = '#000';
	})
	controlUI.addEventListener('mouseout', function() {
		controlText.style.color = '#737373';
	})

	controlUI.addEventListener('click', function() {
		map.setCenter(center);
		map.setZoom(zoom);
	});

}

// 모든 마커를 다 지운다.
function deletAllMarkers() {
	for (var i = 0; i < markers.length; i++) {
		markers[i].setMap(null);
	}
	markers = [];
	markersPosition = undefined;
}
function deleteMarker(marker) {
	marker.setMap(null);
}

function addSearchBar() {
	// Create the search box and link it to the UI element.
	var input = document.getElementById('pac-input');
	var searchBox = new google.maps.places.SearchBox(input);
	map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
	// Bias the SearchBox results towards current map's viewport.
	map.addListener('bounds_changed', function() {
		searchBox.setBounds(map.getBounds());
	});

	var markers = [];

	searchBox.addListener('places_changed', function() {
		var places = searchBox.getPlaces();
		if (places.length == 0) {
			return;
		}

		markers.forEach(function(marker) {
			marker.setMap(null);
		});
		markers = [];

		// For each place, get the icon, name and location.
		var bounds = new google.maps.LatLngBounds();
		places.forEach(function(place) {
			var icon = {
				url : place.icon,
				size : new google.maps.Size(71, 71),
				origin : new google.maps.Point(0, 0),
				anchor : new google.maps.Point(17, 34),
				scaledSize : new google.maps.Size(25, 25)
			};

			// Create a marker for each place.
			markers.push(new google.maps.Marker({
				map : map,
				icon : icon,
				title : place.name,
				position : place.geometry.location
			}));

			if (place.geometry.viewport) {
				// Only geocodes have viewport.
				bounds.union(place.geometry.viewport);
			} else {
				bounds.extend(place.geometry.location);
			}
		});
		map.fitBounds(bounds);
	});
}

function drawLine(map, path) {// 전달인자 : 뿌려줄 맵객체 ,적용시킬 PolyLine객체. 내용 : 구글맵 상단에
								// 잇는 버튼에 선그리기, 선지우기 이벤트를 다는 리스너
	document.getElementById('addline').onclick = function() {
		path.setMap(map);
	}
	document.getElementById('removeline').onclick = function() {
		path.setMap(null);
	}
}

// 모든 선을 지워준다
function deleteAllPath() {
	flightPath.setMap(null);
}
function drawPath(pathPostions) {// 하루 단위 일기의 path그리기
	flightPath = new google.maps.Polyline({
		path : pathPostions,
		strokeColor : "#EB2E1A",
		strokeOpacity : 2,
		strokeWeight : 2
	});
	flightPath.setMap(map);
	return flightPath;
}

function geocodeLatLng() {
	var geocoder = new google.maps.Geocoder;
	var loc = $('#modalLocation');
	geocoder.geocode({
		'location' : markersPosition
	}, function(results, status) {
		if (status === google.maps.GeocoderStatus.OK) {
			if (results[1]) {
				loc.html(results[1].formatted_address);
			} else {
				loc.html('지역명이 존재하지 않습니다.');
			}
		} else {
			loc.html(status);

		}
	});
}
