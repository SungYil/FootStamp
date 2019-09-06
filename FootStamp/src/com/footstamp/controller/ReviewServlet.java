package com.footstamp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.footstamp.bean.ReviewBean;
import com.footstamp.service.ReviewService;

/**
 * Servlet implementation class ReviewServlet
 */
@Controller
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ReviewService reviewService;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String type = request.getParameter("type");
		if ("delete".equals(type)) {// 삭제 요청시
			String date=null;//일기
			String writerId=null;//일기
			String bulletinId = request.getParameter("bulletinId");
			String isStory = request.getParameter("isStory");
			String reviewId=request.getParameter("reviewId");
			String isLike = request.getParameter("isLike");
			reviewService.deleteByReviewId(bulletinId, reviewId);
			if ("true".equals(isStory)) {// 스토리일 경우
				
			}
			else{
				date=request.getParameter("date");
				writerId=request.getParameter("writerId");
			}
			
			out.println("success");
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String type = request.getParameter("type");//리뷰 등록인지 목록 요청인지
		if ("add".equals(type)) {// 등록 요청시
			String isStory = request.getParameter("isStory");// 스토리인지 일기인지 구분
			String isLike = request.getParameter("isLike");// 좋아요 댓글 구분
			if("false".equals(isStory)){//일기일 경우
				if(true){//좋아요 등록
					String reviewWriter = request.getParameter("reviewWriterId");//리뷰작성자 아이디
					String diaryWriterId = request.getParameter("diaryWriterId");//일기 작성자 아이디
					String date = request.getParameter("date");//일기 작성일
					String content = request.getParameter("content");
					System.out.println(content);
					
					SimpleDateFormat datefomat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
					String reviewDate = datefomat.format(new GregorianCalendar().getTime());//리뷰 작성일
					StringTokenizer stok1 = new StringTokenizer(reviewDate, "_");
					int i=0;
					String[] res2 = new String[stok1.countTokens()]; 
					while(stok1.hasMoreTokens()){
						res2[i]=stok1.nextToken();
						i++;
					}
					String reviewId = DynamicIdBinder.reviewIdAssemble(reviewWriter, "false", "true", res2);

					StringTokenizer stok = new StringTokenizer(date, ".");
					i=0;
					String[] res = new String[stok.countTokens()]; 
					while(stok.hasMoreTokens()){
						res[i]=stok.nextToken();
						i++;
					}
					String diaryId = DynamicIdBinder.bulletinIdAssemble("false", diaryWriterId, res);//일기 식별정보 부여
					reviewService.addReview(diaryId, new ReviewBean(reviewWriter,reviewId,diaryId,isLike,isStory,reviewDate,content));
					
					
				}
//				else{//댓글 등록
//					System.out.println("123");
//					String reviewWriter = request.getParameter("reviewWriterId");//리뷰작성자 아이디
//					String diaryWriterId = request.getParameter("diaryWriterId");//일기 작성자 아이디
//					String date = request.getParameter("date");//일기 작성일
//					String content = request.getParameter("content");
//					SimpleDateFormat datefomat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
//					String reviewDate = datefomat.format(new GregorianCalendar().getTime());//리뷰 작성일
//					StringTokenizer stok1 = new StringTokenizer(reviewDate, "_");
//					int i=0;
//					String[] res2 = new String[stok1.countTokens()]; 
//					while(stok1.hasMoreTokens()){
//						res2[i]=stok1.nextToken();
//						i++;
//					}
//					String reviewId = DynamicIdBinder.reviewIdAssemble(reviewWriter, "false", "true", res2);
//
//					StringTokenizer stok = new StringTokenizer(date, ".");
//					i=0;
//					String[] res = new String[stok.countTokens()]; 
//					while(stok.hasMoreTokens()){
//						res[i]=stok.nextToken();
//						i++;
//					}
//					String diaryId = DynamicIdBinder.bulletinIdAssemble("false", diaryWriterId, res);//일기 식별정보 부여
//					ReviewService.getInstance().addReview(diaryId, new ReviewBean(reviewWriter,reviewId,diaryId,isLike,isStory,reviewDate,content));
//				}
			}
			else{//스토리일 경우
				if("true".equals(isLike)){//좋아요 등록
					
				}
				else{//댓글 등록
					
				}
			}
		} 
//			String bulletinId = request.getParameter("bulletinId");//스토리 또는 일기 아이디
//			String reviewWriterId = request.getParameter("reviewWriterId");//리뷰작성자 아이디
//			String year = request.getParameter("year");
//			String month = request.getParameter("month");
//			String day = request.getParameter("day");
//			String hour = request.getParameter("hour");
//			String min = request.getParameter("min");
//			String sec = request.getParameter("sec");
//			String content = request.getParameter("content");//내용(댓글일 경우 사용자가 입력한 내용, 좋아요일 경우 "좋아요")
//			String[] date = {year, month, day, hour, min, sec};//리뷰 등록일
//			String sumDate = year+"_"+month+"_"+day+"_"+hour+"_"+min+"_"+sec;
//			
//			String reviewId=DynamicIdBinder.reviewIdAssemble(reviewWriterId, isStory, isLike, date);//리뷰 식별키 받아옴
//			System.out.println((reviewId));
//			ReviewService.getInstance().addReview(bulletinId, 
//			new ReviewBean(reviewWriterId, reviewId, bulletinId, isLike, isStory, sumDate, content));
			
			
			
//			String storyId = null;//스토리 식별
//			String date =null;//일기 식별
//			String diaryWriterId =null;//일기 식별
//			if ("true".equals(isStory)) {// 스토리일 경우(일기는 날짜로 구분)
//				storyId = request.getParameter("storyId");
//			}
//			else{
//				date=request.getParameter("date");
//				diaryWriterId=request.getParameter("diaryWriterId");
//			}
//			if ("review".equals(kind)) {// 댓글일 경우
//				System.out.println("댓글 추가할 경우");
//				content = request.getParameter("content");
//			}
//		
//			// 리뷰 아이디를 주면된다.
//			out.println("reviewId");
//			// 좋아요 등록실패하면 줄 아이디 정해두자(댓글이든 좋아요든)
		
		else if ("getList".equals(type)) {// 목록 요청할 경우
			String isStory=request.getParameter("isStory");
			if("false".equals(isStory)){//일기의 리뷰목록을 요청
				String writerId = request.getParameter("writerId");
				String date = request.getParameter("date");//토크나이져 필요
				StringTokenizer stok = new StringTokenizer(date, ".");
				int i=0;
				String[] res = new String[stok.countTokens()]; 
				while(stok.hasMoreTokens()){
					res[i]=stok.nextToken();
					i++;
				}
				String diaryId = DynamicIdBinder.bulletinIdAssemble("false", writerId, res);//일기 식별키만들기
//				System.out.println("다이어리 아이디"+diaryId);
				reviewService.searchByBulletinId(diaryId);//null처리 필요
			}
			else{//스토리의 리뷰목록을 요청
				
			}	
			JSONObject res = new JSONObject();
			JSONArray reviewList = new JSONArray();
			for (int i = 0; i < 30; ++i) {
				JSONObject obj = new JSONObject();
				obj.put("id", "dkdkenen2006");
				obj.put("content", "잘 지내시나 보내요?");
				obj.put("year", 2017);
				obj.put("month", 2);
				obj.put("day", 15);
				obj.put("hour", 7);
				obj.put("min", 30);
				obj.put("reviewId", i + "id");// 리뷰 식별키
				reviewList.add(obj);
			}
			res.put("reviewList", reviewList);
			res.put("result", "success");
			out.println(res);
		}
	}
}
