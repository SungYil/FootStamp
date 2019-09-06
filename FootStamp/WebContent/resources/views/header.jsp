<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!-- 페이지 상단바 시작 -->
<style>
</style>
   <header >
      <nav class="navbar navbar-center navbar-fixed-top" style="background-color:white;border-color: #E7E7E7;"role="navigation">
         <div class="navbar-header">
            <form action="${pageContext.request.contextPath}/Main.do" id="search" class="form-inline" style="margin-top:5px;" onsubmit="return false;">
	            	<img id="${sessionScope.user_id }"class="main_logo" style="float:left;margin-left:330px;margin-top:5px;"src="${pageContext.request.contextPath}/resources/images/FootStamper_logo.png" height="35px" width="35px">               
	            	<a class="navbar-brand main_logo" href="#" style="color: #EB3232;font-size:40px;font-family: 'Tangerine', cursive;">FootStamp</a>
	                  <div class="form-group">
	                     <!-- 검색할 키워드 -->
	                     <input style="margin-left:47px" type="text" class="form-control input-sm text-center" id="searchKeyword" placeholder="Search"                             
	                        data-content="<div id='my_popover' ></div>" data-html="true" data-trigger='manual' data-toggle="popover">
	                  </div>
               <span style="margin-left:100px;padding-top:20px;color:#828282;" class="glyphicon glyphicon-user" id='header_user'aria-hidden="true"></span>
               <span style="margin-left:10px;padding-top:20px;color:#FF0000;" class="glyphicon glyphicon-map-marker" id='header_map'aria-hidden="true"></span>
               <span style="margin-left:10px;padding-top:20px;color:#828282;" class="glyphicon glyphicon-bullhorn" id='header_alarm'aria-hidden="true"></span>   
            </form>
         </div>
      </nav>
   </header>
   <!-- 페이지 상단바 끝-->
<script type="text/javascript">
	$("#searchKeyword").on('shown.bs.popover', function(){
		 $("#my_popover").parent().parent().css("min-width","180px");
		 $("#my_popover").parent().parent().css("max-width","180px");
		 $("#my_popover").parent().parent().css("left","550px");
		 $("#my_popover>div>a").css("color","red");
	});
   //키보드 이벤트
   $('#searchKeyword').keyup(function(event){
	   if(event.keyCode==13){
	   }
      var keyword = $('#searchKeyword').val();
      if(keyword!=''){
         var url_location = $('#search').attr('action');
         $.ajax({
            url : url_location,
            type : "get",
            data : {
            	page : "searchKey",
               "keyword" : keyword
            },
            success : function(response) {
               var data = JSON.parse(response);
               if (data.result.trim() == "success") {
            	   var list=data.list;
                  //검색 결과 팝오버
                  $('#searchKeyword').popover('show');
                  //검색 결과에 따라 popover변경
                  $("#searchKeyword").on('shown.bs.popover', function(){
                      $('#my_popover').html("");
                      for(var i=0;i<list.length;++i){
                    	  if(list[i].type=="profile"){
                    	  	$('#my_popover').append("<div class='searh_result'><span "
                    	  			+"id="+list[i].id
                    	  			+" style='font-size:10px;' class='profile glyphicon glyphicon-user' aria-hidden='true'></span>"
                    	  			+"<a style='font-size:12px;margin:5px;text-decoration:none;'>"
                    	  			+list[i].name
                    	  			+"</a></div>"
                    	  			);
                    	  }
                    	  else if(list[i].type=="tag"){
                    		  $('#my_popover').append("<div class='searh_result'><span "
                      	  			+" style='font-size:10px;' class='tag glyphicon glyphicon-tag' aria-hidden='true'></span>"
                      	  			+"<a style='font-size:12px;margin:5px;text-decoration:none;'>"
                      	  			+list[i].name
                      	  			+"</a></div>"
                      	  			);
                    	  }
                    	  else{
                    		  $('#my_popover').append("<div class='searh_result'><span "
                        	  			+" style='font-size:10px;' class='location glyphicon glyphicon-map-marker' aria-hidden='true'></span>"
                        	  			+"<a style='font-size:12px;margin:5px;text-decoration:none;'>"
                        	  			+list[i].name
                        	  			+"</a></div>"
                        	  			);
                    	  }
                      }
                      $('.searh_result').on("click",function(){
                    	  var cur=$(this);
                    	  //아이디가 있다는건 프로필 검색이다
                    	  var id=cur.children("span").attr("id");
                    	  if(id){//아이디,프로필 검색일 경우
                    		  location.replace('/FootStamp/Main.do?page=profile&id='+id);
                    	  }
                    	  //지도,태그 검색일 경우
                    	  else{
                    		  var name=cur.children('a').html();
                    		  if(cur.children("span").hasClass('location')){//지도 검색일 경우(아직 미구현)
                    			  location.replace('/FootStamp/Story.do?key=locationPage&locationName='+name);
                    		  }
                    		  else{//태그 검색일 경우
                    			  location.replace('/FootStamp/Story.do?key=tagPage&tagName='+name);
                    		  }
                    	  }
                		});
                  });
               } else {
                  console.log(data.result)
               }
            },
            error : function() {
               alert("keyword error!")
            }
         }); 
      }
   });
   //검색 키워드 팝오버 생성
   $('#searchKeyword').popover({
       html : true,
       title: null,
       placement:"bottom"
    }); 
   //프로필 아이콘 클릭 시
   $('#header_user').click(function(){
      location.replace('/FootStamp/Main.do?page=profile&id=${sessionScope.user_id}');
   });
   //맵 아이콘 클릭시
   $('.glyphicon-map-marker').click(function(){
      location.replace('/FootStamp/resources/views/map.jsp');
   });
   //메인로고 클릭시
   $('.main_logo').click(function(){
	   location.replace('/FootStamp/Main.do?page=main');
   });
   //엔터키 비활성화
   $('#searchKeyword').keypress(function(e){
	    if ( e.which == 13 ) return false;
   })
</script>
