package com.footstamp.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.footstamp.bean.DetailStorySaveBean;
import com.footstamp.bean.DiaryBean;
import com.footstamp.controller.DynamicIdBinder;

/**
 * 스토리 서비스 class
 * @author 동익
 *
 */
@Service
public class StoryService {
	@Autowired
	private ReviewService review;
   /**
    * 스토리 객체 포함 
    * Key - 1차 : id / 2차 : 스토리 식별키 / Value - 스토리상세정보
    */
   private Map<String,HashMap<String,DetailStorySaveBean>> stories;
   /**
    * default constructor
    */
   public StoryService() {
      super();
      this.stories = Collections.synchronizedMap(new HashMap<String, HashMap<String,DetailStorySaveBean>>());
      System.out.println("StoryService-default constructor생성중.."); 
      // 스토리일 경우: 계정id_s_2017_01_10_18_52(년,월,일,시,분,초)
      HashMap<String, DetailStorySaveBean> tempMap = new HashMap<String, DetailStorySaveBean>();
     

      //2017년02월05일 임시데이터 삽입
		tempMap.put("ehddlrvv_s_2017_02_05_18_52_00.jpg",
				new DetailStorySaveBean("서울특별시 동작구 사당2동 145-23", "37.485736", "126.981392", "2017.02.05_18:52", "1", 
						"ehddlrvv_s_2017_02_05_18_52_00.jpg", "내용1", "ok", "ok", new String[]{""}, "ehddlrvv"));
		tempMap.put("ehddlrvv_s_2017_02_05_18_53_00.jpg", 
				new DetailStorySaveBean("서울특별시 서초구 방배4동 1754", "37.488196", "126.982615", "2017.02.05_18:53", "2", 
						"ehddlrvv_s_2017_02_05_18_53_00.jpg", "내용2", "ok", "ok", new String[]{""}, "ehddlrvv"));
		tempMap.put("ehddlrvv_s_2017_02_05_18_54_00.jpg", 
				new DetailStorySaveBean("서울특별시 서초구 방배4동 851-6", "37.489129", "126.992970", "2017.02.05_18:54", "3", 
						"ehddlrvv_s_2017_02_05_18_54_00.jpg", "내용3", "ok", "ok", new String[]{""}, "ehddlrvv"));
      //2017년02월06일 임시데이터 삽입
		tempMap.put("ehddlrvv_s_2017_02_06_18_52_00.jpg", 
				new DetailStorySaveBean("대한민국 서울특별시 관악구 청룡12길 17-15", "37.47713570000001", "126.94458580000003", "2017.02.06_18:52", "1", 
						"ehddlrvv_s_2017_02_06_18_52_00.jpg", "내용1", "ok", "ok", new String[]{""}, "ehddlrvv"));
		tempMap.put("ehddlrvv_s_2017_02_06_18_53_00.jpg", 
				new DetailStorySaveBean("서울시 동작구2", "37.4971502", "126.98620519999997", "2017.02.06_18:53", "2", 
						"ehddlrvv_s_2017_02_06_18_53_00.jpg", "내용2", "ok", "ok", new String[]{""}, "ehddlrvv"));
		tempMap.put("ehddlrvv_s_2017_02_06_18_54_00.jpg", 
				new DetailStorySaveBean("대한민국 서울특별시 서초구 도구로 112", "37.4863452", "126.98614769999995", "2017.02.06_18:54", "3", 
						"ehddlrvv_s_2017_02_06_18_54_00.jpg", "내용3", "ok", "ok", new String[]{""}, "ehddlrvv"));
      //2017년02월07일 임시데이터 삽입
      tempMap.put("ehddlrvv_s_2017_02_07_18_53_00.jpg", 
				new DetailStorySaveBean("서울특별시 마포구 도화동 156-4", "37.537686", "126.947118", "2017.02.07_18:53", "4", 
						"ehddlrvv_s_2017_02_07_18_53_00.jpg", "내용1", "ok", "ok", new String[]{""}, "ehddlrvv"));
      tempMap.put("ehddlrvv_s_2017_02_07_18_52_00.jpg", 
				new DetailStorySaveBean("서울특별시 용산구 이촌1동 302-6", "37.516990", "126.960014", "2017.02.07_18:52", "1", 
						"ehddlrvv_s_2017_02_07_18_52_00.jpg", "내용2", "ok", "ok", new String[]{""}, "ehddlrvv"));
      tempMap.put("ehddlrvv_s_2017_02_07_18_54_00.jpg", 
				new DetailStorySaveBean("서울특별시 용산구 이태원동 534", "37.535871", "126.987369", "2017.02.07_18:54", "2", 
						"ehddlrvv_s_2017_02_07_18_54_00.jpg", "내용3", "ok", "ok", new String[]{""}, "ehddlrvv"));
      tempMap.put("ehddlrvv_s_2017_02_07_18_55_00.png", 
				new DetailStorySaveBean("서울특별시 성동구 옥수동 78", "37.540985", "127.018011", "2017.02.07_18:55", "3", 
						"ehddlrvv_s_2017_02_07_18_55_00.png", "내용4", "ok", "ok", new String[]{""}, "ehddlrvv"));
      
      this.stories.put("ehddlrvv", tempMap);
   }
   /**
    * overloaded constructor
    * @param stories
    */
   public StoryService(HashMap<String, HashMap<String, DetailStorySaveBean>> stories) {
      super();
      this.stories = stories;
   }
   public Map<String, HashMap<String, DetailStorySaveBean>> getStories() {
      return stories;
   }
   public void setStories(
         Map<String, HashMap<String, DetailStorySaveBean>> stories) {
      this.stories = stories;
   }
   public DetailStorySaveBean addStoryService(String id, String storyId, DetailStorySaveBean story){
      if("".equals(id)||id==null||"".equals(storyId)||storyId==null||null==story)
         return null;
      if(null==story.getDate()||"".equals(story.getDate()))
         return null;
      if(this.stories.get(id)==null)
         this.stories.put(id, new HashMap<String, DetailStorySaveBean>());   
      return this.stories.get(id).put(storyId, story);
   }
   public DetailStorySaveBean updateStoryService(String id,String storyId, DetailStorySaveBean story){
      if("".equals(id)||id==null||"".equals(storyId)||storyId==null||null==story)
         return null;
      if(null==story.getDate()||"".equals(story.getDate()))
         return null;
      if(this.stories.get(id)==null)
         return null;
      System.out.println("updateStoryService의 수정할 내용 - "+story);
      return this.stories.get(id).replace(storyId, story);
   }
   public DetailStorySaveBean deleteStoryService(String id, String storyId){
      if("".equals(id)||id==null||"".equals(storyId)||storyId==null)
         return null;
      if(this.stories.get(id)==null)
         return null;
      return this.stories.get(id).remove(storyId);
   } 
   /**
    * 스토리 상세보기
    * @param id 계정 아이디
    * @param storyId 스토리 식별정보
    * @return 스토리 상세정보 (지명,위도,경도,날짜,날씨,이미지파일명,내용,공개여부,공유여부,작성자명) 
    */
   public String[] searchStoryService(String id,String storyId){
      if("".equals(id)||id==null||"".equals(storyId)||storyId==null)
         return null;
      if(this.stories.get(id)==null)
         return null;
      //리턴할 정보 String 배열 생성
      DetailStorySaveBean temp = this.stories.get(id).get(storyId);
      //태그명 목록은 넘겨주지 않는다. 내용에 포함되므로
      return new String[]{
            temp.getPostion(),
            temp.getLatitude(),
            temp.getLongitude(),
            temp.getDate(),
            temp.getWeather(),
            temp.getStoryImgId(),
            temp.getContent(),
            temp.getOpen(),
            temp.getShare(),
            temp.getWriterId()
      };
   }
   /**
    * 스토리 기간 검색
    * @param id 계정 아이디
    * @param starDate 검색 시작시간(년_월_일_시_분_초)
    * @param endTime 검색 종료시간(년_월_일_시_분_초)
    * @return 오름차순으로 정렬된 시작시간~종료시간 스토리 정보 목록(이미지파일명, 위도, 고도, 좋아요 개수, 리뷰 개수 )2차원배열
    */
   public String[][] searchStoriesService(String id, String startTime,String endTime){
      if("".equals(id)||id==null||"".equals(startTime)||startTime==null||"".equals(endTime)||endTime==null)
         return null;
      if(this.stories.get(id)==null)
         return null;

      System.out.println("StoryService-searchDiaryService : \n검색 시작시간 - "+startTime+
    		  												"\n검색 끝시간 - "+endTime);
      Set key=this.stories.get(id).keySet();
      Iterator iter=key.iterator();
      //반환 배열
      String[][] returnList=null;
      //검색결과를 임시 저장할 배열
      List<DetailStorySaveBean> searchList = new ArrayList<DetailStorySaveBean>();
      //keySet비교하기
      System.out.println("StoryService-searchDiaryService 검색 시작..");
      while(iter.hasNext()){
         String inti=(String)iter.next();
         //날짜비교 
         String userCurDate = this.stories.get(id).get(inti).getDate();
         if(userCurDate.compareTo(startTime)>=0&&userCurDate.compareTo(endTime)<=0){
               searchList.add(this.stories.get(id).get(inti));
               System.out.println("일치 결과 날짜 - "+userCurDate);
         }
      }
      System.out.println("StoryService-searchDiaryService 검색 끝..");
      //일기가 존재할 경우에만
      if(searchList.size()>0){
         //순서대로 반환하기 위한 list
         //List<DetailStorySaveBean> sortList = new ArrayList<DetailStorySaveBean>();
         //날짜순으로 정렬
         Collections.sort(searchList, new Comparator<DetailStorySaveBean>(){
               public int compare(DetailStorySaveBean obj1, DetailStorySaveBean obj2)
               {
                     return obj1.getDate().compareToIgnoreCase(obj2.getDate());
               }
         });
         //검색된 날짜 개수만큼 String 이차원 배열을 생성한다.
         if(searchList.size()>0){
            returnList = new String[searchList.size()][];
            int i;
            //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ아직 리뷰서비스안됨-----------------------
            //이미지파일과 좋아요 수, 리뷰 개수를 String[][]에 넣어 반환한다.
            for(i = 0 ; i < searchList.size();++i){
               //String[]reviewCnt = review.getReviewsCount(searchList.get(i).getStoryImgId());
               returnList[i] = new String[]{
                  searchList.get(i).getStoryImgId(), // 이미지파일명(식별정보용)
                  searchList.get(i).getLatitude(), // 위도
                  searchList.get(i).getLongitude()//, // 경도
                  //reviewCnt[0], // 좋아요 개수
                  //reviewCnt[1] // 리뷰 개수
               };
               System.out.println("StoryService-searchStoriesService 일기 조회결과 생성중.."+returnList[i][0]);
            }
         }
      }
      //일기 없을 경우 null반환
      return returnList;
      
   }
   /**
    * 스토리 하루 일기 검색
    * @param id 검색할 계정 id
    * @param date 검색할 날짜(년,월,일)
    * @return 오름차순으로 정렬된 하루 스토리 정보 목록(이미지파일명, 위도, 고도, 좋아요 개수, 리뷰 개수) 2차원배열
    */
   public String[][] searchDiaryService(String id, String []date){
      if("".equals(id)||id==null||"".equals(date)||date==null)
         return null;
      String startTime = date[0]+"."+date[1]+"."+date[2]+"_00:00"; //yyyy.MM.dd_00:00 형식
      String endTime = date[0]+"."+date[1]+"."+date[2]+"_23:59";   //yyyy.MM.dd_11:59 형식

      return searchStoriesService(id, startTime, endTime);
   }
   /**
    * 해당하는 달의 일기목록을 반환해준다.
    * @param id 검색할 계정 id
    * @param date 검색할 날짜(년,월)
    * @return 오름차순으로 정렬된 일 + 지명 목록 
    */
   public String[][] searchDiariesExisit(String id, String []date){
      System.out.println("---------------------------StoryService-searchDiariesExisit 발자취 검색 시작..");
      if("".equals(id)||id==null||date==null||date.length<1)
          return null;
      Set key=this.stories.get(id).keySet();
      Iterator iter=key.iterator();
      //찾을 년도와 월
      String searchMonth = date[0]+"."+date[1];
      //반환 배열
      String[][] returnList=null;
      //검색결과를 임시 저장할 배열
      List<DetailStorySaveBean> searchList = new ArrayList<DetailStorySaveBean>();
      //keySet비교하기
      System.out.println(searchMonth);
      while(iter.hasNext()){
         String inti=(String)iter.next();
         //날짜비교 
         String userCurDate = this.stories.get(id).get(inti).getDate();
         if(userCurDate.contains(searchMonth)){
               searchList.add(this.stories.get(id).get(inti));
         }
      }
      if(searchList.size()>0){
          //순서대로 반환하기 위한 list
          //List<DetailStorySaveBean> sortList = new ArrayList<DetailStorySaveBean>();
          //날짜순으로 정렬
          Collections.sort(searchList, new Comparator<DetailStorySaveBean>(){
                public int compare(DetailStorySaveBean obj1, DetailStorySaveBean obj2)
                {
                      return obj1.getDate().compareToIgnoreCase(obj2.getDate());
                }
          });
          //검색된 날짜 개수만큼 String 이차원 배열을 생성한다.
          if(searchList.size()>0){
             returnList = new String[searchList.size()][];
             int i;
             int returnIndex = -1;
             //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ아직 리뷰서비스안됨-----------------------
             //이미지파일과 좋아요 수, 리뷰 개수를 String[][]에 넣어 반환한다.
             for(i = 0 ; i < searchList.size()-1;++i){
            	 if(!searchList.get(i).getDate().substring(0,10).equals(searchList.get(i+1).getDate().substring(0,10))){
	               returnList[++returnIndex] = new String[]{
	                   searchList.get(i).getDate(),    // 날짜 반환
	                   searchList.get(i).getPostion()  // 지명 반환
	                };
	               System.out.println("StoryService-searchDiariesExisit 발자취 조회결과 생성중.."+returnList[returnIndex][0]);
	            }
             }   
             returnList[++returnIndex] = new String[]{
            	searchList.get(searchList.size()-1).getDate(),
            	searchList.get(searchList.size()-1).getPostion() 
             };
             System.out.println("StoryService-searchDiariesExisit 발자취 조회결과 생성중.."+returnList[returnIndex][0]);
	          
          }
       }
      System.out.println("---------------------------StoryService-searchDiariesExisit 발자취 검색 쫑료..");
      
      return returnList;
   }
   /**
    * 한회원의 일기 목록 조회(현재 2016년까지 가능)
    * @param id 검색할 계정 id
    * @param nextDate 검색 기준 날짜(년,월,일)
    * @return 하루 스토리 정보 목록와 일기 좋아요 개수, 댓글 개수 포함한 객체 배열 ( 2개 미만 반환 시 게시물 존재하지 않는 것 )
    */
   public DiaryBean[] searchDiariesService(String id,String[] nextDate){
      if("".equals(id)||id==null||"".equals(nextDate)||nextDate==null)
         return null;
      //감소할 년,월,일
      int year, month, day;
      //하루 감소할 떄 사용할 달력 객체
      GregorianCalendar yesterDay = null;      
      //하루 검색 결과 목록
      String[][] dayResult = null;
      //검색 결과 임시 저장 배열 생성
      List<DiaryBean> searchList = new ArrayList<DiaryBean>();
      //일기 2개씩 반환
      int isSecond = 0;
      while(isSecond!=2){
         //검색 결과를하루 검색 결과 배열에 저장
         dayResult = searchDiaryService(id, nextDate);
         //하루 검색결과가 존재할 경우
         if(dayResult!=null){
            //일기 개수 1증가
            isSecond++;
            //일기 식별정보 생성
            String diaryId = DynamicIdBinder.bulletinIdAssemble("false", id, nextDate);
            String[]reviewCnt = review.getReviewsCount(diaryId);
            //검색 결과 임지 저장 배열에 찾은 하루 검색 결과 저장
            searchList.add(new DiaryBean(dayResult, 
                  reviewCnt[0], 
                  reviewCnt[1], 
                  diaryId));
         }
         //년월일 시분초 감소
         try{
            year = Integer.parseInt(nextDate[0]);
            month = Integer.parseInt(nextDate[1]);
            day = Integer.parseInt(nextDate[2]);
            //하루 감소할 떄 사용할 달력 객체
            yesterDay = new GregorianCalendar(year,month-1,day);   
            //하루 감소
             yesterDay.add(Calendar.DATE, -1);
             //어제날짜로 nextDate변환
             nextDate = new String[]{
                   Integer.toString(yesterDay.get(Calendar.YEAR)),
                   Integer.toString(yesterDay.get(Calendar.MONTH)+1),
                   Integer.toString(yesterDay.get(Calendar.DATE))
                   };
         }
         catch(NumberFormatException e){
            System.out.println("StoryService-searchDiariesService:날짜 변환오류");
         }
         //2016년이면 멈추게
         if(nextDate[0].equals("2016"))
            break;
      }
      return searchList.toArray(new DiaryBean[searchList.size()]);
   }
   /**
    * 여러 회원의 일기 목록 반환
    * @param ids 조회할 회원 아이디 목록
    * @param nextDate 검색할 다음날짜
    * @return 여러 회원의 일기 목록
    */
   public HashMap<String, List<DiaryBean>> searchMembersDiariesService(String[]ids,String[] nextDate){
      return null;
   }
   /**
    * 팔로잉 회원 들의 일기 목록 반환
    * @param id 팔로잉 조회 기준 아이디
    * @return 여러 회원의 일기 목록(오름차순으로 정렬된 하루 스토리 정보 목록(이미지파일명, 위도, 고도, 좋아요 개수, 리뷰 개수) 2차원배열과 일기 좋아요, 일기 댓글 정보 목록)
    */
   public DiaryBean[] searchFollowingDiariesService(String id){
      return null;
   }
   /**
    * 팔로잉 회원 들의 전달날짜의 일기 목록 반환(01월12일(17:22)현재 모든 팔로워들의 해당날짜 일기 반환)
    * @param id 팔로잉 조회 기준 아이디
    * @param nextDate 조회할 전달날짜 정보(년,월,일)
    * @return 팔로잉 회원들의 해당 일자의 스토리 정보 목록(이미지파일명, 위도, 고도, 좋아요 개수, 리뷰 개수)과 일기 좋아요 개수,댓글 개수 포함한 객체 배열 
    */
   public DiaryBean[] searchFollowingDiariesService(String id,String[]nextDate){
      //일기식별정보 선언
      String diaryId = null;
      //반환하기 위한 임시배열
      List<DiaryBean> returnList = new ArrayList<DiaryBean>();
       /*//1이 following 이거 주석 풀면 팔로잉 목록 받아오는 부분 수행!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      FollowService followService = FollowService.getInstance();
       List<String> followingList = followService.searchFollowService(id, Byte.parseByte("1"));*/
      //임시 아이디 배열
      List<String> followingList = new ArrayList<String>();
      followingList.add("임시1");
      followingList.add("임시2");
      followingList.add("임시3");
      followingList.add("임시4");
      followingList.add("임시5");
      //임시로 저장할 배열 목록
      String[][][]tempList = new String[followingList.size()][][];
      //팔로워마다 비교
      for(int i = 0; i < followingList.size();++i)
         //각각에 해당하는 일기가 존재하는지 검사
         tempList[i] = searchDiaryService(followingList.get(i), nextDate);

      //비교날짜에서 찾은 목록이 3개이상인지 검사
      for(int i = 0; i< tempList.length;++i){
         if(tempList[i]!=null){
            //일기 식별정보 생성
            diaryId = DynamicIdBinder.bulletinIdAssemble("false", id, nextDate);
            //singleton 객체 참조 
            String[]reviewCnt = review.getReviewsCount(diaryId);
            returnList.add(new DiaryBean(tempList[i], 
                  reviewCnt[0], reviewCnt[1], diaryId));
         }
      }
      return returnList.toArray(new DiaryBean[returnList.size()]);
   }
   /**
    * 위치 검색 결과
    * @param location 검색할 위치 키워드
    * @return 좋아요 높은 순 10개 스토리 상세정보 반환
    */
   public List<String[]> searchLocationStoriesService(String location){
      String id = null;
      String date = null;
      String diaryId = null;
      //좋아요 개수 비교하기 위한 HashMap생성
      ArrayList<String[]>likeCntCompare = new ArrayList<String[]>();
      //반환하기 위한 임시 배열생성
      List<String[]> returnList = new ArrayList<String[]>();   
      //전체 회원에서 조회
      Set key=this.stories.keySet();
      Iterator iter=key.iterator();
      //keySet비교하기
      while(iter.hasNext()){
         id=(String)iter.next();
         Set key2 = this.stories.get(id).keySet();
         Iterator iter2 = key2.iterator();
         while(iter2.hasNext()){
            date = (String)iter2.next();
            if(this.stories.get(id).get(date).getPostion().contains(location)){
               //singleton 객체 참조 
               String reviewCnt = review.getReviewCount("true",diaryId);
               likeCntCompare.add(new String[]{id,date,reviewCnt});
            }
         }
      }
      //좋아요 카운트 한것 내림차순으로 설정
      Collections.sort(likeCntCompare, new Comparator<String[]>(){
            public int compare(String[] obj1, String[] obj2)
            {
                  return obj1[2].compareToIgnoreCase(obj2[2]);
            }
      });
      //가장 뒤에서 부터 10개만
      for(int i = 0; i < likeCntCompare.size(); ++i){
         //해당하는 일자의 스토리 한개 정보를 가져와야한다.
         returnList.add(searchStoryService(likeCntCompare.get(i)[0], likeCntCompare.get(i)[1]));
         if(i>=9)
            break;
      }
      return returnList;
   }
   /**
    * 사용자 글 내의 위치명 검사
    * @param id 사용자 id
    * @param location 위치명
    * @return 오름차순으로 정렬된 검색 결과 스토리 정보 목록(이미지파일명, 위도, 고도, 좋아요 개수, 리뷰 개수) 2차원배열
    */
   public String[][] searchUserLocationStoriesService(String id, String location){
      return null;
   }
   /**
    * 태그명 검색 결과
    * @param tag 태그명
    * @return 좋아요 높은 순 10개 스토리 상세정보 반환
    */
   public List<String[]> searchTagStoriesService(String tag){
      String id = null;
      String date = null;
      String diaryId = null;
      //좋아요 개수 비교하기 위한 HashMap생성
      ArrayList<String[]>likeCntCompare = new ArrayList<String[]>();
      //반환하기 위한 임시 배열생성
      List<String[]> returnList = new ArrayList<String[]>();   
      //전체 회원에서 조회
      Set key=this.stories.keySet();
      Iterator iter=key.iterator();
      //keySet비교하기
      while(iter.hasNext()){
         id=(String)iter.next();
         Set key2 = this.stories.get(id).keySet();
         Iterator iter2 = key2.iterator();
         while(iter2.hasNext()){
            date = (String)iter2.next();
            if(this.stories.get(id).get(date).getTags()!=null){
               String []tagList = this.stories.get(id).get(date).getTags();
               for(int i = 0; i<tagList.length; ++i){
                  //singleton 객체 참조 
                  String reviewCnt = review.getReviewCount("true",diaryId);
                  likeCntCompare.add(new String[]{id,date,reviewCnt});
               }
            }
         }
      }
      //좋아요 카운트 한것 오름차순으로 설정
      Collections.sort(likeCntCompare, new Comparator<String[]>(){
            public int compare(String[] obj1, String[] obj2)
            {
                  return obj1[2].compareToIgnoreCase(obj2[2]);
            }
      });
      //가장 뒤에서 부터 10개만
      for(int i = likeCntCompare.size()-1; i != -1; --i){
         //해당하는 일자의 스토리 한개 정보를 가져와야한다.
         returnList.add(searchStoryService(likeCntCompare.get(i)[0], likeCntCompare.get(i)[1]));
         if(i==likeCntCompare.size()-10)
            break;
      }
      return returnList;
   }
   /**
    * 사용자 글 내의 태그명 검사
    * @param id 사용자 id
    * @param tag 태그명
    * @return 오름차순으로 정렬된 검색 결과 스토리 정보 목록(이미지파일명, 위도, 고도, 좋아요 개수, 리뷰 개수) 2차원배열
    */
   public String[][] searchUserTagsStoriesService(String id,String tag){
      return null;
   }
   /**
    * url에 존재하는 파일에서 스토리 정보 업데이트
    */
   public void load(){
      return;
   }
   /**
    * url에 파일명을 생성하여 스토리 정보 업데이트
    */
   public void save(){
      return;
   }
   /**
    * DB에 현재 데이터를 넘겨준다.
    */
   public void dataTransfer(){
      //interface호출
      return;
   }
   @Override
   public String toString() {
      return "StoryService [stories=" + stories + "]";
   }
}