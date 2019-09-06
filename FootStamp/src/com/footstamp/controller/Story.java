package com.footstamp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilePermission;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.footstamp.bean.DetailStorySaveBean;
import com.footstamp.service.StoryService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class Story {
	StoryService storyService = new StoryService();
	String url = "192.168.0.17";
	
	@RequestMapping("/FootPrintView.do")
	public ModelAndView footPrintView(HttpServletRequest request,String year, String month,@RequestHeader(value="User-Agent")String userAgent){
	
		System.out.println("Story-footPrintView 발자취 보기 클릭 : "+year+"_"+month+" 검색");
		
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("resources/views/androidResult");
		if(userAgent.contains("Android")){//안드로이드 요청이 왔음
			
			String userId = null;
			//---------------------userId, year, month에 해당하는 일기 목록 가져와야함(그리고 일기의 스토리의 최신날짜에 해당하는 위치도 필요)
			Cookie[] cookies =request.getCookies();
			for(Cookie cookie:cookies){
				String res = cookie.getName();
				if(res.equals("id")){
					System.out.println("id값="+cookie.getValue());	
					userId = cookie.getValue();
					//쿠키에 아이디에 해당하는 알람목록 받아와야한다.
					break;
				}
			}
			//해당하는 달에 일기 존재하는지 반환
			String [][] monthList = storyService.searchDiariesExisit(userId, new String[]{year,month});
			JSONArray jArray = new JSONArray();
			if(monthList!=null){
				for(int i = 0 ; i < monthList.length; ++i){
					if(null==monthList[i])
						break;
					JSONObject obj= new JSONObject();
					obj.put("day", monthList[i][0].substring(8, 10));
					obj.put("recentStoryLocation", monthList[i][1]);
					jArray.add(obj);
					System.out.println("기기로 응답할 발자취 내용 생성중.."+monthList[i][0].substring(8, 10)+"일 "+monthList[i][1]);
				}
			}
			mnv.addObject("json", jArray);	
			System.out.println(jArray.toJSONString());
		}
		return mnv;
	}
	/**
	 * 상세보기 요청 처리
	 * @param request 요청 객체
	 * @param imgName 이미지 파일명(확장자포함)
	 * @param userAgent 요청 기기
	 * @return ModelAndView
	 */
	@RequestMapping("/StoryView.do")
	public ModelAndView storyView(HttpServletRequest request,String imgName,@RequestHeader(value="User-Agent")String userAgent){
		System.out.println("---------------------Story-storyView : 스토리 상세보기 시작..");
		String userId=null;
		Cookie[] cookies =request.getCookies();
		if(cookies!=null){
			for(Cookie cookie:cookies){
				String res = cookie.getName();
				if(res.equals("id")){
					userId = cookie.getValue();
					break;
				}
			}	
		}
		//사실 파라미터에 session도 있어야함 그래야 요청보낸 아이디가 그 스토리를 좋아요를 했는지 안했는지 판별해서 jsonObject에 담아서 보낼수 있
		ModelAndView mnv = new ModelAndView();
		System.out.println("Story-storyView : 전달받은 이미지이름 - "+imgName);
		//imgName 식별정보로 모든 값 가져온다
		
		if(userAgent.contains("Android")){//안드로이드 요청이 왔음
			mnv.setViewName("resources/views/androidResult");
			String imgPath ="http://"+url+":8089/FootStamp/resources/images/";
			JSONObject obj = new JSONObject();
			//---------------------------------상세정보 조회-----------------------------
			//반환순서 :(지명,위도,경도,날짜,날씨,이미지파일명,내용,공개여부,공유여부,작성자명) 
			String[]story = storyService.searchStoryService(userId, imgName);
			if(story!=null&&story.length>0){
				obj.put("result","success");
				obj.put("location", story[0]);
				obj.put("time", story[3].substring(11));
				obj.put("weather", story[4]);
				obj.put("storyImgUrl", imgPath+story[5]);
				obj.put("storyContent", story[6]);
				obj.put("isOpen", story[7]);
				obj.put("isShare", story[8]);
				obj.put("userId", story[9]);
				obj.put("lat",story[1]);
				obj.put("lng",story[2]);
				obj.put("storyId", story[5]);
			}
			else
				obj.put("result","fail");
			
//******************************프로필이미지 임시로저장 및 좋아요 여부 및 좋아요 및 댓글 개수 반환 트루**********************************
			obj.put("profileImgUrl", imgPath+"americano.jpg");
			//session에 id에 해당하는 사람이 이 스토리에 좋아요를 눌렀으면 1을 아니면 0을 보낸다.
			//session아이디와 story정보에 좋아요 정보에 id들과 비교하는 구문이 필
			obj.put("isIlikeThis", "true");
			obj.put("likeCnt", "3");
			obj.put("commentCnt", "10");
			mnv.addObject("json", obj);	
			System.out.println("StoryView-응답 내용 : "+obj);
		}
		System.out.println("---------------------Story-storyView : 스토리 상세보기 끝..");
		return mnv;
	}
	@RequestMapping("/AramView.do")
	public ModelAndView aramView(HttpServletRequest request,@RequestHeader(value="User-Agent")String userAgent){
	
		
		
		ModelAndView mnv = new ModelAndView();
		
		
		
		if(userAgent.contains("Android")){//안드로이드 요청이 왔음
			mnv.setViewName("resources/views/androidResult");
			String imgPath ="http://"+url+":8089/FootStamp/resources/img/";
			String userId;
			Cookie[] cookies =request.getCookies();
			for(Cookie cookie:cookies){
				String res = cookie.getName();
				if(res.equals("id")){
					System.out.println("id값="+cookie.getValue());	
					userId = cookie.getValue();
					//쿠키에 아이디에 해당하는 알람목록 받아와야한다.
					break;
				}
			}
			//----------임시 test용 데이터--------
			JSONArray jArray = new JSONArray();
			JSONObject obj1= new JSONObject();
			obj1.put("aramImg",imgPath+"noumincaffe.jpg");
			obj1.put("userId", "ehddlr_rkdms");
			obj1.put("aramKind", "review");
			obj1.put("isLike", true);
			obj1.put("date", "13.03.21-13:20");
			
			JSONObject obj2= new JSONObject();
			obj2.put("aramImg", imgPath+"face.png");
			obj2.put("userId", "qhqo_dydwn");
			obj2.put("aramKind", "follow");
			obj2.put("isLike", true);
			obj2.put("date", "14.03.21-13:20");
			
			JSONObject obj3= new JSONObject();
			obj3.put("aramImg", imgPath+"hellostranger.jpg");
			obj3.put("userId", "wnsrl_tjdal");
			obj3.put("aramKind", "review");
			obj3.put("isLike", true);
			obj3.put("date", "15.03.21-13:20");
			
			
			
			jArray.add(obj1);
			jArray.add(obj2);
			jArray.add(obj3);
		
			mnv.addObject("json", jArray);	
		}
		return mnv;
	}
	@RequestMapping("/CommentView.do")
	public ModelAndView commentView(String identyKey,@RequestHeader(value="User-Agent")String userAgent){
		ModelAndView mnv = new ModelAndView();
		if(userAgent.contains("Android")){//안드로이드 요청이 왔음
			mnv.setViewName("resources/views/androidResult");
			String imgPath ="http://"+url+":8089/FootStamp/resources/img/";
			
			//----------임시 test용 데이터--------
			
			JSONArray jArray = new JSONArray();
			JSONObject obj1= new JSONObject();
		
			obj1.put("profileImg",imgPath+"face.png");
			obj1.put("userId", "ehddlr_rkdms");
			obj1.put("comment", "누구랑 갔냐??");
			obj1.put("date", "13.03.21-13:20");
			
			JSONObject obj2= new JSONObject();
			obj2.put("profileImg",imgPath+"face.png");
			obj2.put("userId", "wnsrl_tjdal");
			obj2.put("comment", "나도 tjdal랑 갔는데 ㅎㅎ");
			obj2.put("date", "13.03.21-13:20");
			
			JSONObject obj3= new JSONObject();
			obj3.put("profileImg",imgPath+"face.png");
			obj3.put("userId", "qhqo_dydwn");
			obj3.put("comment", "나도 dydwn랑 가고싶다 ㅜ");
			obj3.put("date", "13.03.21-13:20");
			
			
			
			
			jArray.add(obj1);
			jArray.add(obj2);
			jArray.add(obj3);
			
		
		
			mnv.addObject("json", jArray);	
		}
		return mnv;
	}
	@RequestMapping("/AddComment.do")
	public ModelAndView addComment(HttpServletRequest request, String identyKey,String comment,@RequestHeader(value="User-Agent")String userAgent){
		ModelAndView mnv = new ModelAndView();
		
		String userId=null;
		Cookie[] cookies =request.getCookies();
		for(Cookie cookie:cookies){
			String res = cookie.getName();
			if(res.equals("id")){
				userId = cookie.getValue();
				//쿠키에 아이디에 해당하는 알람목록 받아와야한다.
				break;
			}
		}
		System.out.println("id="+userId+"/identyKey="+identyKey+"/comment="+comment);
		if(userAgent.contains("Android")){//안드로이드 요청이 왔음
			mnv.setViewName("resources/views/androidResult");
			String imgPath ="http://"+url+":8089/FootStamp/resources/img/";
			
			//----------임시 test용 데이터--------
			//userId,identyKey,comment로 댓글 추가하고, 목록 다 가져오기
			JSONArray jArray = new JSONArray();
			JSONObject obj1= new JSONObject();
		
			obj1.put("profileImg",imgPath+"face.png");
			obj1.put("userId", "ehddlr_rkdms");
			obj1.put("comment", "누구랑 갔냐??");
			obj1.put("date", "13.03.21-13:20");
			
			JSONObject obj2= new JSONObject();
			obj2.put("profileImg",imgPath+"face.png");
			obj2.put("userId", "wnsrl_tjdal");
			obj2.put("comment", "나도 tjdal랑 갔는데 ㅎㅎ");
			obj2.put("date", "13.03.21-13:20");
			
			JSONObject obj3= new JSONObject();
			obj3.put("profileImg",imgPath+"face.png");
			obj3.put("userId", "qhqo_dydwn");
			obj3.put("comment", "나도 dydwn랑 가고싶다 ㅜ");
			obj3.put("date", "13.03.21-13:20");
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy.MM.dd-HH:mm");
			String date = simpleDateFormat.format(new Date());
			JSONObject obj4 = new JSONObject();
			obj4.put("profileImg",imgPath+"face.png");
			obj4.put("userId", userId);
			obj4.put("comment", comment);
			obj4.put("date",date);
			
			jArray.add(obj1);
			jArray.add(obj2);
			jArray.add(obj3);
			jArray.add(obj4);
			
		
		
			mnv.addObject("json", jArray);	
		}
		return mnv;
	}
	@RequestMapping("/DiaryView.do")
	public ModelAndView diaryView(String kind, HttpServletRequest request,@RequestHeader(value="User-Agent")String userAgent){
		ModelAndView mnv = new ModelAndView();
		//kind = "main","profile", "addStory"
		String userId=null;
		Cookie[] cookies =request.getCookies();
		if(cookies!=null){
			for(Cookie cookie:cookies){
				String res = cookie.getName();
				if(res.equals("id")){
					userId = cookie.getValue();
					break;
				}
			}	
		}
		System.out.println("Story-diaryView - id="+userId+"/kind="+kind+"/요청기기="+userAgent);
		//해당하는 사용자 아이디와 date로 해당 날짜의 일기를 준다.
		if(userAgent.contains("Android")){//안드로이드 요청이 왔음
			mnv.setViewName("resources/views/androidResult");
			String imgPath ="http://"+url+":8089/FootStamp/resources/images/";
			//스토리 등록일 경우
			if("addStory".equals(kind)){
				String year = request.getParameter("year");
				String month = request.getParameter("month");
				String day = request.getParameter("day");
				JSONArray jArray = new JSONArray();
				//--------------------------------------추가후 하루 일기정보를 받아온다.-------------------------------
				String[][] diary = storyService.searchDiaryService(userId, new String[]{year,month,day});
				if(diary!=null&&diary.length>0){
					jArray.add("success");//스토리가 있을 경우
					for(int i = 0 ; i < diary.length; ++i){
						JSONObject obj= new JSONObject();
						//오름차순으로 정렬된 하루 스토리 정보 목록(이미지파일명, 위도, 고도, 좋아요 개수, 리뷰 개수) 2차원배열
						obj.put("storyImg",imgPath+diary[i][0]);
						obj.put("lat", diary[i][1]);
						obj.put("lng", diary[i][2]);
						jArray.add(obj);
					}			
				}
				else
					jArray.add("fail");//스토리가 있을 경우
				mnv.addObject("json", jArray);	
				System.out.println("footPrintView-addStory 응답할 내용 : "+jArray);
			}
			else if("main".equals(kind)){//홈화면일때
//				if(date.equals("현재날짜")){//홈화면 처음 몇개 가져올때
//					
//				}
//				else{//스크롤 이벤트 발생
//					 //date값이 실제 날짜임(17.02.03)
//				}
				JSONArray jArray = new JSONArray();
				//-------------하나 시작------------//
				JSONObject obj= new JSONObject();
					obj.put("profileImgUrl", imgPath+"face.png");
					obj.put("userId", "pjk430");
					obj.put("likeCnt", "10");
					obj.put("commentCnt", "12");
					obj.put("isIlikeThis", true);
					obj.put("date", "16.04.30");
		
					JSONArray jStoryArray = new JSONArray();
					JSONObject jStoryObj1 = new JSONObject();
						jStoryObj1.put("storyImgUrl",imgPath+"dongik.jpg");
						jStoryObj1.put("lat",37.530659);
						jStoryObj1.put("lng",126.95967059999998);
						
					JSONObject jStoryObj2 = new JSONObject();
						jStoryObj2.put("storyImgUrl",imgPath+"dongik2.jpg");
						jStoryObj2.put("lat",37.5518838);
						jStoryObj2.put("lng",126.988065);
						
					JSONObject jStoryObj3 = new JSONObject();
						jStoryObj3.put("storyImgUrl",imgPath+"icelatte.jpg");
						jStoryObj3.put("lat",37.5473509);
						jStoryObj3.put("lng",127.00687570000002);
						jStoryArray.add(jStoryObj1);
						jStoryArray.add(jStoryObj2);
						jStoryArray.add(jStoryObj3);
						
					obj.put("stories", jStoryArray);
					//-------------하나 끝------------//
					//-------------하나 시작------------//
					JSONObject obj2= new JSONObject();
						obj2.put("profileImgUrl", imgPath+"profile_default.png");
						obj2.put("userId", "tjdal");
						obj2.put("likeCnt", "200");
						obj2.put("commentCnt", "100");
						obj2.put("isIlikeThis", false);
						obj2.put("date", "16.04.29");
			
						jStoryArray = new JSONArray();
						jStoryObj1 = new JSONObject();
							jStoryObj1.put("storyImgUrl",imgPath+"dongik.jpg");
							jStoryObj1.put("lat",37.530659);
							jStoryObj1.put("lng",126.95967059999998);
							
						jStoryObj2 = new JSONObject();
							jStoryObj2.put("storyImgUrl",imgPath+"dongik2.jpg");
							jStoryObj2.put("lat",37.5518838);
							jStoryObj2.put("lng",126.988065);
							
						jStoryObj3 = new JSONObject();
							jStoryObj3.put("storyImgUrl",imgPath+"americano.jpg");
							jStoryObj3.put("lat",37.5473509);
							jStoryObj3.put("lng",127.00687570000002);
							jStoryArray.add(jStoryObj1);
							jStoryArray.add(jStoryObj2);
							jStoryArray.add(jStoryObj3);
							
						obj2.put("stories", jStoryArray);
						//-------------하나 끝------------//
						//-------------하나 시작------------//
						JSONObject obj3= new JSONObject();
							obj3.put("profileImgUrl", imgPath+"face.png");
							obj3.put("userId", "tjdal_wnsrl");
							obj3.put("likeCnt", "1");
							obj3.put("commentCnt", "2");
							obj3.put("isIlikeThis", false);
							obj3.put("date", "16.04.28");
				
							jStoryArray = new JSONArray();
							jStoryObj1 = new JSONObject();
								jStoryObj1.put("storyImgUrl",imgPath+"dongik.jpg");
								jStoryObj1.put("lat",37.530659);
								jStoryObj1.put("lng",126.95967059999998);
								
							jStoryObj2 = new JSONObject();
								jStoryObj2.put("storyImgUrl",imgPath+"dongik2.jpg");
								jStoryObj2.put("lat",37.5518838);
								jStoryObj2.put("lng",126.988065);
								
							jStoryObj3 = new JSONObject();
								jStoryObj3.put("storyImgUrl",imgPath+"americano.jpg");
								jStoryObj3.put("lat",37.5473509);
								jStoryObj3.put("lng",127.00687570000002);
								jStoryArray.add(jStoryObj1);
								jStoryArray.add(jStoryObj2);
								jStoryArray.add(jStoryObj3);
								
							obj3.put("stories", jStoryArray);
							//-------------하나 끝------------//
				jArray.add(obj);
				jArray.add(obj2);
				jArray.add(obj);
				mnv.addObject("json", jArray);
//				
			}
			else{//프로필화면일때
				//누구의 프로필에 해당하는 일기 목록 가져오는지를 판별하기 위해(쿠키에 있는 아이디 아님)
				String profileId = (String)request.getParameter("profileID");
				System.out.println("profile화면 요청시 ID:"+profileId);
				JSONArray jArray = new JSONArray();
				//-------------하나 시작------------//
				JSONObject obj= new JSONObject();
					obj.put("profileImgUrl", imgPath+"face.png");
					obj.put("userId", "pjk430");
					obj.put("likeCnt", "10");
					obj.put("commentCnt", "12");
					obj.put("isIlikeThis", true);
					obj.put("date", "16.04.30");
		
					JSONArray jStoryArray = new JSONArray();
					JSONObject jStoryObj1 = new JSONObject();
						jStoryObj1.put("storyImgUrl",imgPath+"hellostanger2.jpg");
						jStoryObj1.put("lat",37.530659);
						jStoryObj1.put("lng",126.95967059999998);
						
					JSONObject jStoryObj2 = new JSONObject();
						jStoryObj2.put("storyImgUrl",imgPath+"dongik2.jpg");
						jStoryObj2.put("lat",37.5518838);
						jStoryObj2.put("lng",126.988065);
						
					JSONObject jStoryObj3 = new JSONObject();
						jStoryObj3.put("storyImgUrl",imgPath+"hellostranger.jpg");
						jStoryObj3.put("lat",37.5473509);
						jStoryObj3.put("lng",127.00687570000002);
						jStoryArray.add(jStoryObj1);
						jStoryArray.add(jStoryObj2);
						jStoryArray.add(jStoryObj3);
						
					obj.put("stories", jStoryArray);
					//-------------하나 끝------------//
					//-------------하나 시작------------//
					JSONObject obj2= new JSONObject();
						obj2.put("profileImgUrl", imgPath+"noumincaffe.jpg");
						obj2.put("userId", "tjdal");
						obj2.put("likeCnt", "200");
						obj2.put("commentCnt", "100");
						obj2.put("isIlikeThis", false);
						obj2.put("date", "16.04.29");
			
						jStoryArray = new JSONArray();
						jStoryObj1 = new JSONObject();
							jStoryObj1.put("storyImgUrl",imgPath+"dongik.jpeg");
							jStoryObj1.put("lat",37.530659);
							jStoryObj1.put("lng",126.95967059999998);
							
						jStoryObj2 = new JSONObject();
							jStoryObj2.put("storyImgUrl",imgPath+"dongik2.jpg");
							jStoryObj2.put("lat",37.5518838);
							jStoryObj2.put("lng",126.988065);
							
						jStoryObj3 = new JSONObject();
							jStoryObj3.put("storyImgUrl",imgPath+"hellostranger.jpg");
							jStoryObj3.put("lat",37.5473509);
							jStoryObj3.put("lng",127.00687570000002);
							jStoryArray.add(jStoryObj1);
							jStoryArray.add(jStoryObj2);
							jStoryArray.add(jStoryObj3);
							
						obj2.put("stories", jStoryArray);
						//——————하나 끝——————//
						//——————하나 시작——————//
						JSONObject obj3= new JSONObject();
							obj3.put("profileImgUrl", imgPath+"face.png");
							obj3.put("userId", "tjdal_wnsrl");
							obj3.put("likeCnt", "1");
							obj3.put("commentCnt", "2");
							obj3.put("isIlikeThis", false);
							obj3.put("date", "16.04.28");
				
							jStoryArray = new JSONArray();
							jStoryObj1 = new JSONObject();
								jStoryObj1.put("storyImgUrl",imgPath+"dongik.jpeg");
								jStoryObj1.put("lat",37.530659);
								jStoryObj1.put("lng",126.95967059999998);
								
							jStoryObj2 = new JSONObject();
								jStoryObj2.put("storyImgUrl",imgPath+"dongik2.jpg");
								jStoryObj2.put("lat",37.5518838);
								jStoryObj2.put("lng",126.988065);
								
							jStoryObj3 = new JSONObject();
								jStoryObj3.put("storyImgUrl",imgPath+"hellostranger.jpg");
								jStoryObj3.put("lat",37.5473509);
								jStoryObj3.put("lng",127.00687570000002);
								jStoryArray.add(jStoryObj1);
								jStoryArray.add(jStoryObj2);
								jStoryArray.add(jStoryObj3);
								
							obj3.put("stories", jStoryArray);
							//——————하나 끝——————//
				jArray.add(obj);
				jArray.add(obj2);
				jArray.add(obj);
				mnv.addObject("json", jArray);
			}
				
		}
		return mnv;
	}
	@RequestMapping("/AddLike.do")
	public ModelAndView addLike(HttpServletRequest request,@RequestHeader(value="User-Agent")String userAgent){
		//imgName 식별정보로 모든 값 가져온다
		String userId=null;
		Cookie[] cookies =request.getCookies();
		for(Cookie cookie:cookies){
			String res = cookie.getName();
			if(res.equals("id")){
				System.out.println("id값="+cookie.getValue());	
				userId = cookie.getValue();
				//쿠키에 아이디에 해당하는 알람목록 받아와야한다.
				break;
			}
		}
		ModelAndView mnv = new ModelAndView();
		String kind = (String)request.getParameter("kind");
		
		
		if(userAgent.contains("Android")){//안드로이드 요청이 왔음
			mnv.setViewName("resources/views/androidResult");
			JSONObject obj = new JSONObject();
			if("story".equals(kind)){//스토리 좋아요경우 스토리 식별키로 
				String storyIdentyKey = (String)request.getParameter("identyKey");
				System.out.println("스토리 식별키"+storyIdentyKey);
			}
			else{//일기 좋아요경우 일기 작성자 아이디와 작성일자로 식별함
				String diaryWriterId = (String)request.getParameter("diaryWriterId");
				String date = (String)request.getParameter("date");
				System.out.println("일기작성자:"+diaryWriterId+"/작성일:"+date);
			}
			//좋아요; 추가 성공시  
			obj.put("success", "true");
			mnv.addObject("json", obj);	
		}
		return mnv;
	}
	@RequestMapping("/DeleteLike.do")
	public ModelAndView deleteLike(HttpServletRequest request,@RequestHeader(value="User-Agent")String userAgent){
		//사실 파라미터에 session도 있어야함 그래야 요청보낸 아이디가 그 스토리를 좋아요를 했는지 안했는지 판별해서 jsonObject에 담아서 보낼수 있
		ModelAndView mnv = new ModelAndView();
		
		//imgName 식별정보로 모든 값 가져온다
		String userId=null;
		Cookie[] cookies =request.getCookies();
		for(Cookie cookie:cookies){
			String res = cookie.getName();
			if(res.equals("id")){
				System.out.println("id값="+cookie.getValue());	
				userId = cookie.getValue();
				//쿠키에 아이디에 해당하는 알람목록 받아와야한다.
				break;
			}
		}
		String kind = (String)request.getParameter("kind");
		if(userAgent.contains("Android")){//안드로이드 요청이 왔음
			mnv.setViewName("resources/views/androidResult");
			JSONObject obj = new JSONObject();
			if("story".equals(kind)){//스토리 좋아요경우 스토리 식별키로 
				String storyIdentyKey = (String)request.getParameter("identyKey");
				System.out.println("스토리 식별키"+storyIdentyKey);
			}
			else{//일기 좋아요경우 일기 작성자 아이디와 작성일자로 식별함
				String diaryWriterId = (String)request.getParameter("diaryWriterId");
				String date = (String)request.getParameter("date");
				System.out.println("일기작성자:"+diaryWriterId+"/작성일:"+date);
			}
			//좋아요; 삭제 성공시  
			obj.put("success", "true");
			mnv.addObject("json", obj);	
		}
		return mnv;
	}
	@RequestMapping("/addAndroidStory.do")
    public ModelAndView addAndroidStory(HttpSession session,HttpServletRequest request,HttpServletResponse response)throws IOException{
		System.out.println("---------------------Story-addAndroidStory : 스토리 등록 호출.."+session.getId());
		request.setCharacterEncoding("utf-8");
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("resources/views/androidResult");
        String userId = null;
		Cookie[] cookies =request.getCookies();
		if(cookies!=null){
			for(Cookie cookie:cookies){
				String res = cookie.getName();
				if(res.equals("id")){
					userId = cookie.getValue();
					break;
				}
			}	
		}
		//실제경로 반환
		String path = request.getServletContext().getRealPath("/resources/images");
		String imgPath ="http://"+url+":8089/FootStamp/resources/images/";
		
	      int sizeLimit = 10 * 1024 * 1024 ; // 5메가까지 제한 넘어서면 예외발생
	       MultipartRequest multi = new MultipartRequest(request, path,sizeLimit,"utf-8",new DefaultFileRenamePolicy());
	      
	       Enumeration files = multi.getFileNames();
	      
	       String weather = multi.getParameter("weather"); //1.맑음  2.흐림 3.비 4.눈
	       String content=URLDecoder.decode(multi.getParameter("content"),"UTF-8");   //스토리내용
	       String gonyou=multi.getParameter("gonyou");     //ok , no (둘중하나로 반환)
	       String gongae=multi.getParameter("gongae");     //ok , no (둘중하나로 반환)
	       //date 2017.2.6형식
	       String date = multi.getParameter("date");       // 2017.02.07_16:36 형식으로 넘어옴
	       String year = multi.getParameter("year");       // 2017
	       String month = multi.getParameter("month");     // 02
	       String day = multi.getParameter("day");         // 07
	       String hour = multi.getParameter("hour");         // 16
	       String min = multi.getParameter("min");           // 37
	       String sec = multi.getParameter("sec");           // 37
	       String locationName = URLDecoder.decode(multi.getParameter("locationName"),"UTF-8"); // 서울특별시 ...
	       String lat = multi.getParameter("lat");           // 37.1230123..
	       String lng = multi.getParameter("lng");           // 129.0123123..
	       
	       System.out.println("내용 : "+content+"\nweather : "+weather);
	       System.out.println("\n공유여부 : "+gonyou+"\n공개여부 : "+gongae);
	       System.out.println("\n사용자 요청 날짜 : "+date);
	       System.out.println("\n스토리 등록 시간 : "+year+"."+month+"."+day+"_"+hour+":"+min+":"+sec);
	       System.out.println("\n위도,경도("+lat+","+lng+") -> "+locationName);
	       //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ스토리 식별 정보 만든다.ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ          
           String storyBulletinId = DynamicIdBinder.bulletinIdAssemble("true", userId, new String[]{year,month,day,hour,min,sec});
	       String name=null;
	       String fileName=null;
           String file=null;
	       //파일 정보가 있다면
	       while(files.hasMoreElements()) {
	            name = (String)files.nextElement();
	            fileName = multi.getFilesystemName(name);
	            System.out.println("Story-androidStory 이미지 업로드 완료 이름 : " + name);
	            System.out.println("Story-androidStory 이미지 업로드 완료 파일명 : " + fileName);
	            StringTokenizer token=new StringTokenizer(fileName,".");
	            while(token.hasMoreTokens()){//파일 확장자 따오기
	                file=token.nextToken();
	             }
	            File old=new File(path+"\\"+fileName);//기존파일명 가져옴
	            File newFile=new File(path+"\\"+storyBulletinId+"."+file.toLowerCase());//내가원하는 파일명으로 파일생성
	            if(newFile.isFile())//기존에 저 이름으로 있다면 걔 삭제
	               newFile.delete();
	            old.renameTo(newFile);//기존 파일명 교체
	            System.out.println("Story-androidStory 이미지 저장 경로: "+path+"\\"+storyBulletinId+"."+file.toLowerCase());
	             /*           이클립스 문제 : 서버 폴더 (getRealPath())에 이미지를 저장.이렇게 저장할 시 이클립스 path에는 이미지가 저장이 되지않는다.그래서 이클립스가 코드 갱신할 때 이클립스 지 path에 맞춰서 서버 path를 갱신,등록했던 이미지가 날아가게 되어 서버폴더에서 이클립스 폴더로 복사하는 코드를 넣었다. */
	            FileInputStream original=new FileInputStream(path+"\\"+storyBulletinId+"."+file.toLowerCase());
	            FileOutputStream copy=new FileOutputStream("C:\\workspace_spring_3\\FootStamp\\WebContent\\resources\\images\\"+storyBulletinId+"."+file.toLowerCase());
	            FilePermission permission = new FilePermission("C:\\workspace_spring_3\\FootStamp\\WebContent\\resources\\images\\"+storyBulletinId+"."+file.toLowerCase(), "write");
	           
	            try{
	               copy.getChannel().transferFrom(original.getChannel(),0,original.getChannel().size());
	               
	            }catch(Exception e){
	               e.printStackTrace();
	            }finally{
	               original.close();
	               copy.close();
	            }

	            //--------------------------------- 이클립스 문제 코드 끝-----------------------------------
	       }
           //--------------------------------- 서비스 객체에 스토리 추가한다. -----------------------------------
	       JSONArray jArray = new JSONArray();
	       storyService.addStoryService(userId, storyBulletinId+"."+file.toLowerCase(), 
	    		   new DetailStorySaveBean(locationName, lat, lng, date, 
	    				   weather, storyBulletinId+"."+file.toLowerCase(), content, gongae, gonyou, null, userId));
	       JSONObject obj = new JSONObject();
	       if(storyService.getStories().get(userId).get(storyBulletinId+"."+file.toLowerCase())==null){
	    	   System.err.println("Story-androidStory : 서비스 객체 등록 에러발생");
	    	   obj.put("result", "fail");
	       }
	       else
	    	   obj.put("result", "success");
			jArray.add(obj);
			mnv.addObject("json", jArray);	
			System.out.println("Story-addAndroidStory응답할 내용 : "+jArray);
			System.out.println("---------------------Story-addAndroidStory : 스토리 등록 끝.."+session.getId());
			return mnv;
     }
	@RequestMapping("/modifyAndroidStory.do")
    public ModelAndView modifyAndroidStory(HttpSession session,HttpServletRequest request,HttpServletResponse response)throws IOException{
		System.out.println("---------------------Story-addAndroidStory : 스토리 수정 호출.."+session.getId());
		request.setCharacterEncoding("utf-8");
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("resources/views/androidResult");
        String userId = null;
		Cookie[] cookies =request.getCookies();
		if(cookies!=null){
			for(Cookie cookie:cookies){
				String res = cookie.getName();
				if(res.equals("id")){
					userId = cookie.getValue();
					break;
				}
			}	
		}
		//실제경로 반환
		String path = request.getServletContext().getRealPath("/resources/images");
		String imgPath ="http://"+url+":8089/FootStamp/resources/images/";
		
	      int sizeLimit = 10 * 1024 * 1024 ; // 5메가까지 제한 넘어서면 예외발생
	       MultipartRequest multi = new MultipartRequest(request, path,sizeLimit,"utf-8",new DefaultFileRenamePolicy());
	      
	       Enumeration files = multi.getFileNames();
	       //수정일 경우 식별정보도 받아온다!
	       String storyId = multi.getParameter("storyId");  //ehddlrvv_s_2017_10_10_10_10_10.jpg
	       String weather = multi.getParameter("weather"); //1.맑음  2.흐림 3.비 4.눈
	       String content=URLDecoder.decode(multi.getParameter("content"),"UTF-8");   //스토리내용
	       String gonyou=multi.getParameter("gonyou");     //ok , no (둘중하나로 반환)
	       String gongae=multi.getParameter("gongae");     //ok , no (둘중하나로 반환)
	       //date 2017.2.6형식
	       String date = multi.getParameter("date");       // 2017.02.07_16:36 형식으로 넘어옴
	       String locationName = URLDecoder.decode(multi.getParameter("locationName"),"UTF-8"); // 서울특별시 ...
	       String lat = multi.getParameter("lat");           // 37.1230123..
	       String lng = multi.getParameter("lng");           // 129.0123123..
	       StringTokenizer stok = new StringTokenizer(storyId, ".");
	       storyId = stok.nextToken();
	       System.out.println("수정내용 : "+content+"\nweather : "+weather);
	       System.out.println("\n공유여부 : "+gonyou+"\n공개여부 : "+gongae);
	       System.out.println("\n사용자 수정요청 날짜 : "+date);
	       System.out.println("\n위도,경도("+lat+","+lng+") -> "+locationName);
	       //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ스토리 식별 정보 만든다.ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ          
           String name=null;
	       String fileName=null;
           String file=null;
	       //파일 정보가 있다면
	       while(files.hasMoreElements()) {
	            name = (String)files.nextElement();
	            fileName = multi.getFilesystemName(name);
	            System.out.println("Story-modifyAndroidStory 이미지 업로드 완료 이름 : " + name);
	            System.out.println("Story-modifyAndroidStory 이미지 업로드 완료 파일명 : " + fileName);
	            StringTokenizer token=new StringTokenizer(fileName,".");
	            while(token.hasMoreTokens()){//파일 확장자 따오기
	                file=token.nextToken();
	             }
	            File old=new File(path+"\\"+fileName);//기존파일명 가져옴
	            File newFile=new File(path+"\\"+storyId+"."+file.toLowerCase());//내가원하는 파일명으로 파일생성
	            if(newFile.isFile()){//기존에 저 이름으로 있다면 걔 삭제
	               newFile.delete();
	               System.out.println("수정할 사진이 존재합니다.");
	            }
	            old.renameTo(newFile);//기존 파일명 교체
	            System.out.println("Story-modifyAndroidStory 이미지 저장 경로: "+path+"\\"+storyId+"."+file.toLowerCase());
	             /*           이클립스 문제 : 서버 폴더 (getRealPath())에 이미지를 저장.이렇게 저장할 시 이클립스 path에는 이미지가 저장이 되지않는다.그래서 이클립스가 코드 갱신할 때 이클립스 지 path에 맞춰서 서버 path를 갱신,등록했던 이미지가 날아가게 되어 서버폴더에서 이클립스 폴더로 복사하는 코드를 넣었다. */
	            FileInputStream original=new FileInputStream(path+"\\"+storyId+"."+file.toLowerCase());
	            FileOutputStream copy=new FileOutputStream("C:\\workspace_spring_3\\FootStamp\\WebContent\\resources\\images\\"+storyId+"."+file.toLowerCase());
	            FilePermission permission = new FilePermission("C:\\workspace_spring_3\\FootStamp\\WebContent\\resources\\images\\"+storyId+"."+file.toLowerCase(), "write");
	           
	            try{
	               copy.getChannel().transferFrom(original.getChannel(),0,original.getChannel().size());
	               
	            }catch(Exception e){
	               e.printStackTrace();
	            }finally{
	               original.close();
	               copy.close();
	            }

	            //--------------------------------- 이클립스 문제 코드 끝-----------------------------------
	       }
           //--------------------------------- 서비스 객체에 스토리 추가한다. -----------------------------------
	       JSONArray jArray = new JSONArray();
	       storyService.updateStoryService(userId, storyId+"."+file.toLowerCase(),  new DetailStorySaveBean(locationName, lat, lng, date, 
	    				   weather, storyId+"."+file.toLowerCase(), content, gongae, gonyou, null, userId));
	       JSONObject obj = new JSONObject();
	       if(storyService.getStories().get(userId).get(storyId+"."+file.toLowerCase())==null){
	    	   System.err.println("Story-modifyAndroudStory : 서비스 객체 등록 에러발생");
	    	   obj.put("result", "fail");
	       }
	       else{
	    	   obj.put("result", "success");
	    	   System.out.println(storyService.getStories().get(userId).get(storyId+"."+file.toLowerCase()));
	       }
			jArray.add(obj);
			mnv.addObject("json", jArray);	
			System.out.println("Story-modifyAndroudStory응답할 내용 : "+jArray);
			System.out.println("---------------------Story-addAndroidStory : 스토리 수정 끝.."+session.getId());
			return mnv;
     }
	@RequestMapping("/deleteAndroidStory.do")
	public ModelAndView deleteAndroidStory(String storyId,HttpServletRequest request,@RequestHeader(value="User-Agent")String userAgent){
		System.out.println("-------------------------Story-deleteAndroidStory : "+storyId+"삭제시작");
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("resources/views/androidResult");
		String userId=null;
		Cookie[] cookies =request.getCookies();
		for(Cookie cookie:cookies){
			String res = cookie.getName();
			if(res.equals("id")){
				userId = cookie.getValue();
				//쿠키에 아이디에 해당하는 알람목록 받아와야한다.
				break;
			}
		}
		System.out.println("삭제 1차키 : "+userId+", 2차키 : "+storyId);
		DetailStorySaveBean resultVal = storyService.deleteStoryService(userId, storyId);
	    JSONArray jArray = new JSONArray();
	    JSONObject obj = new JSONObject();
		if(resultVal !=null)
			obj.put("result", "success");
		else
			obj.put("result", "fail");
		jArray.add(obj);
		mnv.addObject("json", jArray);	
		System.out.println("Story-deleteAndroidStory응답할 내용 : "+jArray);
		System.out.println("-------------------------Story-deleteAndroidStory : "+storyId+"삭제끝");
		return null;
	}
}