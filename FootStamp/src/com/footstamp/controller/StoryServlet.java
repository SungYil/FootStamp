package com.footstamp.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.footstamp.bean.DetailStorySaveBean;
import com.footstamp.bean.DetailStoryViewBean;
import com.footstamp.bean.LocationResultBean;
import com.footstamp.bean.TagResultBean;
import com.footstamp.service.ReviewService;
import com.footstamp.service.StoryService;
import com.oreilly.servlet.MultipartRequest;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

/**
 * Servlet implementation class StoryServlet
 */
@Controller
public class StoryServlet extends HttpServlet {
   @Autowired
   private StoryService storyService; 
   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   /*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
      String key = request.getParameter("key");
      if("tagPage".equals(key)){//태그 검색화면 가는거
         String tagName=request.getParameter("tagName");
         //가져온 태그명으로 조회하는 과정이 있어야한다.(조회결과 개수도 필요하다)
         int tagCnt=100240;
         request.setAttribute("tagName", tagName);
         request.setAttribute("tagCnt",tagCnt);
          
         TagResultBean bean=new TagResultBean("23.jpg","tagResult","13","13");//원래는 beanList여야한다. 검색결과라서!!
         request.setAttribute("tagResult", bean);
         RequestDispatcher view=request.getRequestDispatcher("resources/views/tagSearch.jsp");
         view.forward(request, response);
      }
      else if("locationPage".equals(key)){//위치검색 결과
         String locationName=request.getParameter("locationName");
         //가져온 태그명으로 조회하는 과정이 있어야한다.(조회결과 개수도 필요하다)
         System.out.println("PageMove-location page로 이동 중..위치명 : "+locationName);
         LocationResultBean bean=new LocationResultBean("23.13","134","23.jpg","9","7","locationResult");//원래는 beanList여야한다. 검색 결과라서!
         request.setAttribute("location", bean);
         
         request.setAttribute("locationName",locationName);
         
         //검색 결과 스토리정보들을 가져와야하는 부분
         // 검색 결과 -> 
         
         RequestDispatcher view=request.getRequestDispatcher("resources/views/mapSearch.jsp");
         view.forward(request, response);
      }
      DetailStory관련
      else if("detailStory".equals(key)){
         request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
         //세션에서 ID반환
         HttpSession session=request.getSession();
         String user_id = (String)session.getAttribute("user_id");
         //이미지 ID반환
         String imgId = request.getParameter("imgId");
         System.out.println("StoryServlet-상세보기 클릭 이미지 : "+imgId);
         String[] tempArr = DynamicIdBinder.imgIdDisassemble(imgId);
         System.out.println("StoryServlet-상세보기 클릭 이미지 경로 : "+Arrays.toString(tempArr));
         //id,년,월,일,시,분,초
         String [] storyIdList = new String[]{tempArr[2],tempArr[3],tempArr[4],tempArr[5],tempArr[6],tempArr[7],tempArr[8]};
      
         //StoryService 객체 참조 반환
         StoryService service = StoryService.getInstance();
         String tempStoryId =  DynamicIdBinder.bulletinIdAssemble("true", user_id, storyIdList);
         String[]result = service.searchStoryService(user_id, tempStoryId);
         //ReviewService 객체 참조 반환
         ReviewService reviewService = ReviewService.getInstance();
         String[] reviewCnt = reviewService.getReviewsCount(tempStoryId);
         //(지명0,위도1,경도2,날짜3,날씨4,이미지파일명5,내용6,공개여부7,공유여부8,스토리식별정보9,작성자명10) 
         Random ran=new Random();         
         JSONObject res = new JSONObject();
         res.put("result","ok");

         res.put("location",result[0]);
         //날짜는 저장한 그대로 뿌려준다.
         res.put("date",result[3]);
         res.put("year", 2017);
         res.put("month", 2);
         res.put("day", 15);
         res.put("dayOfWeek","화");
         res.put("hour", 15);
         res.put("min", 40);
         res.put("weather", result[4]);
         res.put("img",result[5]);
         res.put("content", result[6]);
         res.put("id",result[9]);//스토리 식별키
         res.put("writer", result[10]);
         res.put("likeCnt",reviewCnt[0]);
         res.put("reviewCnt",reviewCnt[1]);
         res.put("isLike","true");//내가 좋아요 눌렀는지(이걸로 좋아요 색깔 구분해야한다)
         res.put("num",ran.nextInt()%100);
         
         
          * 리뷰 상세보기는 여기서 안해도 된다.
          * JSONArray reviewList=new JSONArray();
         String[][]showReviewList = reviewService.searchByBulletinId(tempStoryId);
         res[0] = bean.getWriterId();
         res[1] = bean.getReviewId();
         res[2] = bean.getBulletinId();
         res[3] = bean.getIsLike();
         res[4] = bean.getIsStory();
         res[5] = bean.getDate();
         res[6] = bean.getContent();
         for(int i = 0 ; i< showReviewList.length;++i){
            JSONObject obj=new JSONObject();
            res.put("img","story/admin_0000_00_00.jpg");
            res.put("num",ran.nextInt()%1000);
            obj.put("id",showReviewList[0]);
            obj.put("reviewId",showReviewList[1]);//리뷰 식별키
            obj.put("date", showReviewList[5]);
            obj.put("content",showReviewList[6]);
            obj.put("year", 2017);
            obj.put("month", 2);
            obj.put("day", 15);
            obj.put("hour", 7);
            obj.put("min", 30);
            reviewList.add(obj);
            
         }
         res.put("reviewList", reviewList);
         PrintWriter writer = response.getWriter();
         writer.println(res);
      }
      mapsearch관련
      else if("mapSearch".equals(key)){
           PrintWriter out=response.getWriter();
            String keyword=request.getParameter("keyword");
            JSONObject result =new JSONObject();
            //지도 검색 결과 반환
            JSONObject res=new JSONObject();
            JSONArray locationList=new JSONArray();
               JSONObject obj=new JSONObject();
               obj.put("lat","37.5563989");
               obj.put("lng","126.9160863");
               locationList.add(obj);
               JSONObject obj2=new JSONObject();
               obj2.put("lat","37.5663989");
               obj2.put("lng","126.9260863");
               locationList.add(obj2);
               JSONObject obj3=new JSONObject();
               obj3.put("lat","37.5663989");
               obj3.put("lng","126.9360863");
               locationList.add(obj3);
            result.put("locationList", locationList);
            

            if(keyword!=null&&!"".equals(keyword))
               result.put("result","success");
            else
               result.put("result","fail");
            
           
            out.println(result);
      }
      searchStroyServlet관련
      else if("calenderSearch".equals(key)){
         request.setCharacterEncoding("utf-8");
         HttpSession session = request.getSession();
         String id = (String)session.getAttribute("user_id");
         String s_year = request.getParameter("s_year");
         String s_mon = request.getParameter("s_mon");
         String s_day = request.getParameter("s_day");
         String s_hour = request.getParameter("s_hour");
         String s_min = request.getParameter("s_min");
         String e_year = request.getParameter("e_year");
         String e_mon = request.getParameter("e_mon");
         String e_day = request.getParameter("e_day");
         String e_hour = request.getParameter("e_hour");
         String e_min = request.getParameter("e_min");
         
         System.out.println("SearchStory-doget : 사용자아이디 - "+id+"시작날짜- "+s_year+"년"+s_mon+"월"+s_day+"일"+s_hour+"시"+s_min+"분"+
                                         "\n종료날짜 - "+e_year+"년"+e_mon+"월"+e_day+"일"+e_hour+"시"+e_min+"분");

         int s_yearNum, s_monNum, s_dayNum, s_hourNum, s_minNum,
               e_yearNum, e_monNum, e_dayNum, e_hourNum, e_minNum;
         String result = "날짜전송오류";
         String[][] searchList = null;         
         JSONObject storyResult =new JSONObject();
         try {
            if(id==null||"".equals(id))
               result = "아이디전송오류";
            else
               result = "success";
            
             * number format예외처리
             
            s_yearNum = Integer.parseInt(s_year);
            s_monNum = Integer.parseInt(s_mon);
            s_dayNum = Integer.parseInt(s_day);
            s_hourNum = Integer.parseInt(s_hour);
            s_minNum = Integer.parseInt(s_min);
            e_yearNum = Integer.parseInt(e_year);
            e_monNum = Integer.parseInt(e_mon);
            e_dayNum = Integer.parseInt(e_day);
            e_hourNum = Integer.parseInt(e_hour);
            e_minNum = Integer.parseInt(e_min);
            //시작일과 끝날짜를 주어서 목록 받아온다.
            StoryService storyService = StoryService.getInstance();
            searchList = storyService.searchStoriesService(id, s_year+"_"+s_mon+"_"+s_day+"_"+s_hour+"_"+s_min+"_00", e_year+"_"+e_mon+"_"+e_day+"_"+e_hour+"_"+e_min+"_59");
            //이미지파일명0, 위도1, 고도2, 좋아요 개수3, 리뷰 개수4      
               JSONArray resultList=new JSONArray();
               for(int i = 0; i < searchList.length;++i){
                  JSONObject obj=new JSONObject();
                  obj.put("img",searchList[i][0]);
                  obj.put("lat",searchList[i][1]);
                  obj.put("lng",searchList[i][2]);
                  obj.put("likeCnt",searchList[i][3]);
                  obj.put("reviewCnt",searchList[i][4]);
                 resultList.add(obj);
               }
               //찾은 위치정보 목록 주고
               storyResult.put("locationList", resultList);   
         } catch (NumberFormatException e) {
            result = "날짜전송오류";
         } finally {
            //결과 주고
            storyResult.put("result", result);
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println(storyResult);
            writer.flush();
         }
      }
   }

   *//**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    *//*
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setCharacterEncoding("utf-8");
       String key=request.getParameter("key");
       addStory관련
       if("addStory".equals(key)){
             String path = getServletContext().getRealPath("/resources/images");
            PrintWriter outImg = response.getWriter();
            outImg.println("path :"+ path);
            //페이지로부터 넘어온 데이터 목록
            MultipartRequest multi = new MultipartRequest(request, path,"utf-8");
            HttpSession session = request.getSession();
            String id = (String)session.getAttribute("user_id");
            String year = multi.getParameter("year");
            String mon = multi.getParameter("mon");
            String day = multi.getParameter("day");
            String userChoiceDate = multi.getParameter("date");
            String lat = multi.getParameter("lat");
             String lng = multi.getParameter("lng");
             String location = multi.getParameter("location");
             String content = multi.getParameter("content");
             String hour = multi.getParameter("hour");
             String min = multi.getParameter("min");
             String weather =multi.getParameter("weather");
             String open = multi.getParameter("open");
             String share = multi.getParameter("share");
             String fileTitle=multi.getFilesystemName("img");
             System.out.println("AddStory-dopost : 등록정보"
                   +"\n 날짜 : "+userChoiceDate
                    +"\n 위도 : "+lat
                    +"\n 경도 : "+lng
                      +"\n 지명 : "+location
                      +"\n 내용 : "+content
                      +"\n 시간 : "+hour
                      +"\n 분 : "+min 
                      +"\n 날씨 : "+weather
                      +"\n 공개여부: "+open
                      +"\n 공유여부 : "+share
                      +"\n 업로드 파일명 :"+fileTitle);
             
            StringTokenizer token=new StringTokenizer(fileTitle,".");//에러터짐(준기)
            String file=null;
            
            while(token.hasMoreTokens()){//파일 확장자 따오기
               file=token.nextToken();
            }
            //저장할 이미지 파일 양식을 만든다.
             GregorianCalendar cal = new GregorianCalendar();
             SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
             //이미지 파일명 생성한다.
             String imgFilePath = DynamicIdBinder.imgIdAssemble("true", id, 
                   new String[]{Integer.toString(cal.get(Calendar.YEAR)),
                   Integer.toString(cal.get(Calendar.MONTH)+1),
                   Integer.toString(cal.get(Calendar.DATE)), 
                   Integer.toString(cal.get(Calendar.HOUR_OF_DAY)),
                   Integer.toString(cal.get(Calendar.MINUTE)),
                   Integer.toString(cal.get(Calendar.SECOND))},file
                   );
             //스토리식별정보를 생성한다.
             String storyId = DynamicIdBinder.bulletinIdAssemble("true", id, new String[]{Integer.toString(cal.get(Calendar.YEAR)),
                   Integer.toString(cal.get(Calendar.MONTH)+1),
                   Integer.toString(cal.get(Calendar.DATE)), 
                   Integer.toString(cal.get(Calendar.HOUR_OF_DAY)),
                   Integer.toString(cal.get(Calendar.MINUTE)),
                   Integer.toString(cal.get(Calendar.SECOND))});
             //저장할 객체를 생성한다
             DetailStorySaveBean saveBean=new DetailStorySaveBean(location,
                   lat,lng,year+"_"+mon+"_"+day+"_"+hour+"_"+min+"_00",weather,imgFilePath,content
                   ,open,share,null,id,storyId);//저장하기 위해 서버로 보낼 saveBean생성
             //Service객체에 저장시도
             StoryService storyService = StoryService.getInstance();
             //추가결과
             String result = "fail";
             if(null!=storyService.addStoryService(id, storyId, saveBean))
                result = "success";
             //사진파일명지정
            File old=new File(path+"\\"+fileTitle);//기존파일명 가져옴
            File newFile=new File(path+"\\"+id+"_"+dateFormat+"."+file.toLowerCase());//내가원하는 파일명으로 파일생성
            if(newFile.isFile()){//기존에 저 이름으로 있다면 걔 삭제
               newFile.delete();
            }
            old.renameTo(newFile);//기존 파일명 교체   
            //결과 반환
            JSONObject resultObj=new JSONObject();
            System.out.println(path+"\\"+id+"_"+dateFormat+"."+file.toLowerCase());
            //추가된 하루 일기 정보 반환
            String[][] dayList = storyService.searchDiaryService(storyId,new String[]{year,mon,day});
             JSONArray resultList=new JSONArray();
               for(int i = 0; i < dayList.length;++i){
                  JSONObject obj=new JSONObject();
                  obj.put("img",dayList[i][0]);
                  obj.put("lat",dayList[i][1]);
                  obj.put("lng",dayList[i][2]);
                  obj.put("likeCnt",dayList[i][3]);
                  obj.put("reviewCnt",dayList[i][4]);
                 resultList.add(obj);
               }
               //찾은 위치정보 목록 주고
               resultObj.put("locationList", resultList);
               resultObj.put("result","success");
            PrintWriter writer = response.getWriter();
            writer.print(resultObj);
       }
       reviseServlet관련
       else if("modifyStory".equals(key)){
            Random ran=new Random();
            String path = getServletContext().getRealPath("/resources/images");

            MultipartRequest multi = new MultipartRequest(request, path, "utf-8");
            String location = multi.getParameter("location");
            String content = multi.getParameter("content");
            String hour = multi.getParameter("hour");
            String min = multi.getParameter("min");
            String weather = multi.getParameter("weather");
            String open = multi.getParameter("open");
            String share = multi.getParameter("share");
            String fileTitle = multi.getFilesystemName("img");

            System.out.println("ReviseStory-dopost : 전달받은 정보" 
            + "\n   위치 : "+ location 
            + "\n   내용 : " + content 
            + "\n   시 : " + hour
            + "\n   분 : " + min 
            + "\n   날씨 : " + weather 
            + "\n   공개여부 : " + open 
            + "\n   공유여부 : " + share 
            + "\n   파일 이륾 : " + fileTitle);
            
            if(fileTitle==null){//-------------------------------------파일 사진업로드가 되지않았을시 디비에서 그전에 저장했던 파일명을 가져오는 로직을 넣어야한다.
               fileTitle="1540.jpg";
            }
            
            StringTokenizer token=new StringTokenizer(fileTitle,".");
            String file=null;
            
            while(token.hasMoreTokens()){//파일 확장자 따오기
               file=token.nextToken();
            }
            
            File old=new File(path+"\\"+fileTitle);//기존파일명 가져옴
            File newFile=new File(path+"\\"+hour+min+"."+file.toLowerCase());//내가원하는 파일명으로 파일생성
            if(newFile.isFile()){//기존에 저 이름으로 있다면 걔 삭제
               newFile.delete();
            }
            old.renameTo(newFile);//기존 파일명 교체
            System.out.println(path+"\\"+hour+min+"."+file.toLowerCase());
            
            
            String result = "스토리 데이터 전송 오류";
            JSONObject res = new JSONObject();
            int hourNum, minNum;
            try {
               if(location==null||"".equals(location))
                  result = "위치 전송 오류";
               else
                  result = "success";
               hourNum = Integer.parseInt(hour);
               minNum = Integer.parseInt(min);
               
                * number format유효성검증
                
               s_yearNum = Integer.parseInt(s_year);
               s_monNum = Integer.parseInt(s_mon);
               s_dayNum = Integer.parseInt(s_day);
               s_hourNum = Integer.parseInt(s_hour);
               s_minNum = Integer.parseInt(s_min);
               e_yearNum = Integer.parseInt(e_year);
               e_monNum = Integer.parseInt(e_mon);
               e_dayNum = Integer.parseInt(e_day);
               e_hourNum = Integer.parseInt(e_hour);
               e_minNum = Integer.parseInt(e_min);
               
               //날짜 검증 통과했으니 DB에서 데이터를 받아오면 된다.
               
               result = "success";
               
            } catch (NumberFormatException e) {
               System.out.println("ReviseStory-dopost :NumberFormatException발생 "+hour+" "+min);
               result = "수정 날짜 입력 형식 오류";
            } finally {
               DetailStoryViewBean modiBean=new DetailStoryViewBean(location,//수정한 게시글 Bean으로 생성
                     "2017.01.08"+hour+"."+min,weather,hour+min+"."+file.toLowerCase(),
                     content,null,"id","img","idKey","like","reviewCnt","reviewContent",
                     "WriterID","writerImg","reviewDate");
               
               //res.put("modiInfo",modiBean);
               
               res.put("fileName",hour+min+"."+file.toLowerCase());
               res.put("result", result);
               res.put("num",ran.nextInt()%100);//똑같은 파일이름이 나왔을시 이미지를 업데이트 시켜주기 위해서 중복 방지용으로 같이 보낸다.
               response.setCharacterEncoding("UTF-8");
               PrintWriter writer = response.getWriter();
               writer.println(res);
               writer.flush();
            }
       }
   }*/
}