package com.footstamp.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.footstamp.service.FollowService;
import com.footstamp.service.MemberService;

/**
 * Servlet implementation class FallowServlet
 */
@Controller
public class FallowServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   @Autowired
   private FollowService followService;
   @Autowired
   private MemberService memberService;
   
   @RequestMapping(value="/fal.do", method=RequestMethod.GET)
   protected void follow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      System.out.println("FllowServlet-doget : "+request.getParameter("id"));
      System.out.println(request.getServletContext().getRealPath(""));
      HttpSession session = request.getSession();
      //String userId=(String)session.getAttribute("user_id"); android test瑜� �쐞�빐 二쇱꽍 泥섎━. 
      //System.out.println(session.getAttribute("user_id"));
      
      String userId="ehddlrvv";
      
      String key=request.getParameter("key");
      JSONObject obj = new JSONObject();
      //System.out.println("---------------------------------------------------------------");
      
      if("following".equals(key)){
         JSONArray list=new JSONArray();
         JSONArray profileImg=new JSONArray();
         JSONArray name=new JSONArray();
         List<String> lists=followService.searchFollowService(userId, (byte)1);
         
         System.out.println(lists.size());
         
         for(int i=0;i<lists.size();++i){
            String followingId=lists.get(i);//�꽌移��빐�꽌 �봽濡쒗븘 紐⑸줉�쓣 媛��졇�솕怨� 嫄곌린�꽌 i踰덈갑�뿉 �뙏濡쒖엵 �븘�씠�뵒 ���옣
            list.add(followingId);
            System.out.println(followingId);
            name.add(memberService.searchMemberService(followingId).getName());
            profileImg.add(memberService.searchMemberService(followingId).getProfileImg());
            //profileImg.add(e)
         }
         obj.put("followingId",name);
         obj.put("followingList", list);
         obj.put("imageList", profileImg);
         obj.put("num", lists.size());
         
      }else if("follower".equals(key)){
         JSONArray list=new JSONArray();
         JSONArray profileImg=new JSONArray();
         JSONArray name=new JSONArray();
         JSONArray isfollow=new JSONArray();
         List<String> lists=followService.searchFollowService(userId, (byte)-1);
         List<String> lis=followService.searchFollowService(userId, (byte)1);//�뙏濡쒖썙以� �궡媛� �뙏濡쒖엵 �븯怨좎엳�뒗 �븘�씠媛� �엳�뒗吏� 寃��궗�븯湲� �쐞�빐 �뙏濡쒖엵 紐⑸줉�쓣 媛��졇�삩�떎.
         
         for(int i=0;i<lists.size();++i){
            int che=0;
            for(int j=0;j<lis.size();++j){
               if(lists.get(i).equals(lis.get(j))){
                  isfollow.add("�뙏濡쒖엵");
                  che=1;
                  break;
               }
            }
            if(che!=1){
               isfollow.add("�뙏濡쒖썙");
            }
         }
         
         
         for(int i=0;i<lists.size();++i){
            String followerId=lists.get(i);//�꽌移��빐�꽌 �봽濡쒗븘 紐⑸줉�쓣 媛��졇�솕怨� 嫄곌린�꽌 i踰덈갑�뿉 �뙏濡쒖엵 �븘�씠�뵒 ���옣
            list.add(followerId);
         
            name.add(memberService.searchMemberService(followerId).getName());
            profileImg.add(memberService.searchMemberService(followerId).getProfileImg());
            //profileImg.add(e)
         }
         obj.put("isfollow",isfollow);
         obj.put("followingId",name);
         obj.put("followingList", list);
         obj.put("imageList", profileImg);
         obj.put("num", lists.size());
         
      }else if("profile".equals(key)){
         String event=request.getParameter("event");
         String folId=request.getParameter("checkFal");
         
         if("follower".equals(event)){
            String str=followService.deleteFollowingService(userId,(byte)1, folId);
            System.out.println("follower�꽑�깮�떆 �궘�젣 �씪�뼱�굹�빞 �븯�뒗�뜲..."+str);
         }else{
            String str=followService.addFollowingService(userId, (byte)1, folId);
            System.out.println("�씠媛� -1�빐�꽌 臾댁뼵媛� �씪�뼱�궓 "+str);
         }
         
         int ingCnt=followService.searchFollowCntService(userId, (byte)1);
         int werCnt=followService.searchFollowCntService(userId,(byte)-1);
         
         obj.put("ingCnt", ingCnt);
         obj.put("werCnt",werCnt);
      }
      
      
      obj.put("follow", "no");//----------------------�뙏濡쒖슦 �릺�엳�쑝硫� ok �븘�땲�씪硫� no瑜� 援ы븯�뒗 濡쒖쭅�쓣 �꽔�뼱�빞 �븳�떎.
      
      response.setCharacterEncoding("UTF-8");
      PrintWriter writer = response.getWriter();
      writer.println(obj);
      writer.flush();
      
   }

}