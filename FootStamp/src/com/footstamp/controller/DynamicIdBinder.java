package com.footstamp.controller;

import java.util.StringTokenizer;

public class DynamicIdBinder {
	/**
	 * 스토리일 경우: 계정id_s_2017_01_10_18_52(년,월,일,시,분,초)
	 * 일기일 경우: 계정id_d_2017_01_10(년,월,일)
	 */
	public static String bulletinIdAssemble(String isStory, String id, String[] date){
		String res=null;
		if(isStory.equals("true")){//스토리
			res=id+"_s_"+date[0]+'_'+date[1]+'_'+date[2]+'_'+date[3]+'_'+date[4]+'_'+date[5];
		}
		else{//일기
			
			res=id+"_d_"+date[0]+'_'+date[1]+'_'+date[2];
		}
		return res;
	}
	public static String[] bulletinIdDisassemble(String id){
		StringTokenizer stok = new StringTokenizer(id, "_");
		int i=0;
		String[] res = new String[stok.countTokens()]; 
		while(stok.hasMoreTokens()){
			res[i]=stok.nextToken();
			i++;
		}
		return res;	
	}
	/**
	 * 일기리뷰 : 계정Id_rLd__2017_01_10_19_11_11 (id review like day 년월일시분초)
	 * 		 계정Id_rCd__2017_01_10_19_11_11 (id review Comment day 년월일시분초)
	 * 스토리리뷰 : 계정Id_rLs_2017_01_10_19_11_11(review like story 년월일시분초)
	 *   	  계정Id_rCs_2017_01_10_19_11_11(review Comment story 년월일시분초) 
	 */
	public static String reviewIdAssemble(String id, String isStory, String isLike, String[] date){
		String res = null;
		String freg = null;
		if(isStory.equals("true")){//스토리
			if(isLike.equals("true")){//스토리리뷰이면서 좋아요일 경우
				freg="rLs";
			}
			else{//스토리리뷰이면서 댓글일 경우
				freg="rCs";
			}
		}
		else{//일기
			if(isLike.equals("true")){//일기리뷰이면서 좋아요일 경우
				freg="rLd";
			}
			else{//일기리뷰이면서 댓글일 경우
				freg="rCd";
			}
		}
		res=id+'_'+freg+'_'+date[0]+'_'+date[1]+'_'+date[2]+'_'+date[3]+'_'+date[4]+'_'+date[5];
		return res;
	}
	public static String[] reviewIdDisassemble(String reviewId){
		StringTokenizer stok = new StringTokenizer(reviewId, "_");
		int i=0;
		String[] res = new String[stok.countTokens()]; 
		while(stok.hasMoreTokens()){
			res[i]=stok.nextToken();
			i++;
		}
		return res;
	}
	/**
	 * 스토리 이미지:/resources/story/사용자Id_2017_01_10_18_52_13.jpg
	 * 프로필 이미지:/resources/profile/사용자Id_myImg.jpg
	 */
	public static String imgIdAssemble(String isStory, String id, String[] date, String extension){
		String res =null;
		String path="/resources/";
		if(isStory.equals("true"))//스토리 일 경우
			res= path+"story/"+id+'_'+date[0]+'_'+date[1]+'_'+date[2]+'_'+date[3]+'_'+date[4]+'_'+date[5]+'.'+extension;
		else
			res= path+"profile/"+id+"_myImg."+extension;
		return res;
	}
	
	public static String[] imgIdDisassemble(String imgId){
		StringTokenizer stok = new StringTokenizer(imgId, "/_.");
		int i=0;
		String[] res = new String[stok.countTokens()]; 
		while(stok.hasMoreTokens()){
			res[i]=stok.nextToken();
			i++;
		}
		return res;
	}
	/**
	 * 메시지파일경로 및 파일명 :/messages/senderId_receiverId_2017_01_10_18_52_13.txt
	 */
	public static String chattingRoomIdAssemble(String senderId, String receiverId, String[] date){
		String path="/messages/";
		String res= path+senderId+'_'+receiverId+'_'+date[0]+'_'+date[1]+'_'+date[2]+'_'+date[3]+'_'+date[4]+'_'+date[5]+".txt";
		return res;
	}
	public static String[] chattingRoomIdDisassemble(String chattingRoomId){
		StringTokenizer stok = new StringTokenizer(chattingRoomId, "/_.");
		int i=0;
		String[] res = new String[stok.countTokens()]; 
		while(stok.hasMoreTokens()){
			res[i]=stok.nextToken();
			i++;
		}
		return res;
	}
}
