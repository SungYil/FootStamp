package com.footstamp.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.footstamp.bean.MemberBean;
import com.footstamp.bean.ProfilePageBean;
import com.footstamp.service.FollowService;
import com.footstamp.service.MemberService;

/**
 * Servlet implementation class MainServlet
 */
@Controller
public class MainServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   @Autowired
   private MemberService member;
   @Autowired
   private FollowService follow;
   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   @RequestMapping(value="/Main.do",method=RequestMethod.GET)
   protected void main(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding("utf-8");
      
      String page=request.getParameter("page");
      RequestDispatcher view=null;
      System.out.println("�뼱�뼡 �럹�씠吏��씤吏� �솗�씤�슜 : "+page);
      String id=(String) request.getSession().getAttribute("id");
      System.out.println("�꽭�꽑�뿉 ���옣�븳 �븘�씠�뵒 : "+id);
      
      //濡쒓렇�씤 �쑀吏� �젙梨낆씠 �엳�쑝�땲 荑좏궎�뿉�꽌 �떇蹂� �젙蹂닿� �엳�뼱�꽌 �쑀利덊븯寃좊떎怨� �젙�뻽�쑝硫� 諛붾줈 �솃�솕硫댁쑝濡� 媛꾨떎.
      System.out.print("pagemove doget : ");
      if(page==null || "start".equals(page)){//�떆�옉�솕硫댁씪 寃쎌슦
         System.out.println("�떆�옉 濡쒓렇�씤 �솕硫댁쑝濡� �씠�룞");
         view=request.getRequestDispatcher("/resources/views/start.jsp");//寃쎈줈 援ы빐�꽌 �꽔�뼱�빞�븳�떎
         view.forward(request, response);
      }
      else if("main".equals(page)){//硫붿씤�솕硫�
         System.out.println("硫붿씤�럹�씠吏�濡� �씠�룞");

         /*HttpSession session = request.getSession();
         String userid=(String)session.getAttribute("user_id");   
         //�븳�쉶�썝�쓽 �뙏濡쒖엵 �뒪�넗由� �젙蹂� 紐⑸줉 諛쏆븘�삤湲�
         GregorianCalendar cal = new GregorianCalendar();
         StoryService service = StoryService.getInstance();
         DiaryBean[]followingList = service.searchFollowingDiariesService(userid, new String[]{Integer.toString(cal.get(Calendar.YEAR)),
                Integer.toString(cal.get(Calendar.MONTH)+1),
                Integer.toString(cal.get(Calendar.DATE))});
         //DiaryBean�뿉 吏�湲� 怨꾩젙 ID媛� �뾾�뒗�뜲 parsing�빐�꽌 id瑜� 爰쇰궡 �봽濡쒗븘�젙蹂대벑�쓣 媛��졇���빞�븳�떎.
         MemberService memberService = MemberService.getInstance();
         MemberBean tempId = memberService.searchMemberService(userid);*/
         
         view=request.getRequestDispatcher("resources/views/main.jsp");
         view.forward(request, response);
      }
      else if("profile".equals(page)){//�봽濡쒗븘 �솕硫� 媛��뒗嫄�
         String profile_id=request.getParameter("id");//�봽濡쒗븘 李얠븘媛� �븘�씠�뵒
         
         if(profile_id==null||"".equals(profile_id)){
            profile_id="default_myimage.jpg";
         }
         MemberBean bean=new MemberBean(profile_id,"","�씠由�","010-0000-0000",profile_id+"_myimage.jpg");
         ProfilePageBean profile=new ProfilePageBean(bean,null,"1000");
         
         if(profile_id.equals(id)){
            request.setAttribute("but", "�봽濡쒗븘 �닔�젙");
         }else{
            //------------------------------------------------------------�뙏濡쒖엵�씠 �릺�뼱�엳�뒗�븘�씤吏� �븞�릺�뼱�엳�뒗 �븘�씤吏� 寃��궗�븯�윭 �꽌鍮꾩뒪瑜� �떎���삤�뒗 濡쒖쭅�씠 �엳�뼱�빞�븳�떎.
            request.setAttribute("but", "�뙏濡쒖엵 異붽�");
         }
         
         request.setAttribute("profile", profile);
         request.setAttribute("id", profile_id);
         
         //諛쏆븘�삩 �븘�씠�뵒 �젙蹂대줈 �씪湲� 紐⑸줉,�뙏濡쒖슦 �젙蹂�,�궗吏�,�븘�씠�뵒瑜� �젣怨듯빐�빞�븳�떎.
         System.out.println("�봽濡쒗븘 �씠�룞");
         view=request.getRequestDispatcher("resources/views/profile.jsp");
         view.forward(request, response);
      }
   
      else{//searchKeywordServlet愿��젴
         

         String keyword = request.getParameter("keyword");

         System.out.println("SearchKeyword-doget 寃��깋�뼱 : " + keyword);

         String result = "�뒪�넗由� �뜲�씠�꽣 �쟾�넚 �삤瑜�";
         JSONObject res = new JSONObject();
         if (keyword == null || "".equals(keyword))
            result = "�궎�썙�뱶 �쟾�넚 �삤瑜�";
         else
            result = "success";
         
         res.put("result", result);
         JSONArray list=new JSONArray();
         for(int i=0;i<2;++i){
            JSONObject obj=new JSONObject();
            obj.put("type","location");
            obj.put("name","寃고샎�떇�옣");
            list.add(obj);
         }
         for(int i=0;i<2;++i){
            JSONObject obj=new JSONObject();
            obj.put("type","tag");
            obj.put("name","寃고샎");
            list.add(obj);
         }
         for(int i=0;i<2;++i){
            JSONObject obj=new JSONObject();
            obj.put("type","profile");
            obj.put("name","寃고샎�쓽 �룷濡쒓� �릱�뒿�땲�떎.");
            obj.put("id","jr29jr");
            list.add(obj);
         }
         res.put("list",list);
         response.setCharacterEncoding("UTF-8");
         PrintWriter writer = response.getWriter();
         writer.println(res);
         writer.flush();
      }

   }
   
   @RequestMapping(value="/androidProfile", method=RequestMethod.GET)
   public String androidProfile(HttpServletRequest request,HttpServletResponse response){
      String id=request.getParameter("id");//�봽濡쒗븘 李얠븘媛� �븘�씠�뵒
      HttpSession session = request.getSession();
      //String userid=(String)session.getAttribute("id");      
      String userid="ehddlrvv";//-------------------------�븞�뱶濡쒖씠�뱶 �븘吏� �꽭�뀡�뿉�꽌 紐산볼�궡�꽌 嫄� 諛뺤븘�몢怨� �엳�쓬.
      
      if(id==null||"".equals(id)){
         id="default_myimage.jpg";
      }
      
      MemberBean bean=member.getMembers().get(id);
      
      JSONObject obj=new JSONObject();
      if(bean==null){
         obj.put("flag", "false");
         return null;
      }else{
         obj.put("flag", "true");
      }
      
      int ingCnt=follow.searchFollowCntService(id, (byte)1);//1�� �뙏濡쒖엵 -1�� �뙏濡쒖썙
      int werCnt=follow.searchFollowCntService(id,(byte)-1);
      
      ProfilePageBean profile=new ProfilePageBean(bean,null,""+ingCnt);
      System.out.println(werCnt+"werCnt");
      
      if(id.equals(userid)){
         obj.put("but", "�봽濡쒗븘 �닔�젙");
      }else{
         //------------------------------------------------------------�뙏濡쒖엵�씠 �릺�뼱�엳�뒗�븘�씤吏� �븞�릺�뼱�엳�뒗 �븘�씤吏� 寃��궗�븯�윭 �꽌鍮꾩뒪瑜� �떎���삤�뒗 濡쒖쭅�씠 �엳�뼱�빞�븳�떎.
         if(follow.searchFollowService(userid,(byte)1, id)==null){
            obj.put("but", "�뙏濡쒖썙");
         }else{
            obj.put("but", "�뙏濡쒖엵");
         }
      }
      JSONObject ob=new JSONObject();
      
      ob.put("id", bean.getId());
      ob.put("name", bean.getName());
      ob.put("call", bean.getCall());
      ob.put("profileImg", bean.getProfileImg());
      
      obj.put("profile", ob);
      obj.put("ingCnt", ingCnt);
      obj.put("werCnt", werCnt);
      
      request.setAttribute("json", obj);
      
      return "resources/views/profileResult";
   }
}