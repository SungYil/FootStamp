package com.footstamp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.footstamp.bean.MemberBean;
import com.footstamp.bean.MessageBean;
import com.footstamp.service.MemberService;
import com.footstamp.service.MessageService;

/**
 * Servlet implementation class Message
 */
@Controller
public class MessageServlet {
   @Autowired
   private MessageService messageService;
   @Autowired
   private MemberService memberService;

   // 채팅방 목록 불러오기
   @RequestMapping("/chattingRoomList.do")
   public ModelAndView chattingRoomList(HttpServletResponse response,
         HttpServletRequest request) throws IOException {
      System.out.println("채팅방 목록");
      response.setCharacterEncoding("utf-8");
      // 아이디를 찾아내는 부분
      String id = null;
      // 쿠키를 구한다.
      Cookie[] cookies = request.getCookies();
      // 쿠키가 존재하는지 확인
      if (cookies != null) {
         for (int i = 0; i < cookies.length; ++i) {
            System.out.println("쿠키 나옴? : " + cookies[i].getName() + "/"
                  + cookies[i].getValue());
            // 쿠키에 id 쿠키가 있는지 확인
            if ("id".equals(cookies[i].getName())) {
               // 아이디를 가져온다
               id = cookies[i].getValue();
                //id = "dkdkenen2006";
            }
         }
      }
      // 아이디에 해당하는 채팅방 아이디 가져오는 부분
      String[] ids = messageService.searchChattingRooms(id);

      // 서버 루트 구하는거
      String path1 = request.getServletContext().getContextPath();

      // 그 사람에 해당하는 메시지 목록 받아오는 부분이 있어야한다.
      JSONArray chattingRoomList = new JSONArray();
      for (int i = 0; i < ids.length; ++i) {
         JSONObject obj = new JSONObject();
         HashMap<String, String> people = messageService.getChatPeople()
               .get(ids[i]);
         Iterator<String> keyIter = people.keySet().iterator();
         while (keyIter.hasNext()) {
            String key = keyIter.next();
            if (!key.equals(id)) {
               String name = people.get(key);
               obj.put("name", name);
               break;
            }
         }
         MessageBean message = messageService.getLastMessage(ids[i]);
         String[] info = message.getAll();
         System.out
               .println("======================================================");
         for (int j = 0; j < info.length; ++j)
            System.out.println(info[j]);

         // StringTokenizer token=new StringTokenizer(info)
         StringTokenizer token = new StringTokenizer(info[2], "_");
         int cnt = 0;
         String[] date = new String[6];
         while (token.hasMoreTokens()) {
            date[cnt] = token.nextToken();
            ++cnt;
         }
         obj.put("id", ids[i]);
         obj.put("message", info[3]);
         obj.put("date", date[0] + "." + date[1] + "." + date[2]);
         // 이미지 가져와야한다.현재는 임시로 이미지 넣어놨다.
         obj.put("imgUrl", "http://192.168.0.17:8089" + path1
               + "/resources/images/23.jpg");// 이것만 나중에 수정해주면된다.
         chattingRoomList.add(obj);

      }
      ModelAndView mnv = new ModelAndView();
      mnv.addObject("chattingRoomList", chattingRoomList);
      mnv.setViewName("/resources/views/chattingRoomListByMobile");
      return mnv;
   }

   // 채팅방 나가기
   @RequestMapping("/exitChattingRoom.do")
   public ModelAndView exitChattingRoom(HttpServletRequest request,
         HttpServletResponse response) throws IOException {
      System.out.println("채팅방 나가기 요청");
      // 아이디를 찾아내는 부분
      String id = null;
      // 쿠키를 구한다.
      Cookie[] cookies = request.getCookies();
      // 쿠키가 존재하는지 확인
      if (cookies != null) {
         for (int i = 0; i < cookies.length; ++i) {
            // 쿠키에 id 쿠키가 있는지 확인
            if ("id".equals(cookies[i].getName())) {
               // 아이디를 가져온다
               id = cookies[i].getValue();
            }
         }
      }
      // 채팅방 삭제는 채팅방을 삭제하는게 아니다. 채팅방 삭제는 채팅방에 인원이 0이 되면 삭제
      // 여기서 채팅방 삭제 요청은 사용자가 채팅방에서 나가는거다.즉 채팅방 인원 목록에서 사용자를 빼면된다.
      String chat_id = request.getParameter("chat_id");
      System.out.println("채팅방 번호 : " + chat_id);
      HashMap<String, String> people = messageService.getChatPeople().get(
            chat_id);
      String result = null;
      if (people.remove(id) != null) {// 널아니면 삭제 성공한 경우
         result = "1";
      } else {// 삭제 실패한 경우
         result = "-1";
      }
      ModelAndView mnv = new ModelAndView();
      mnv.addObject("result", result);
      mnv.setViewName("/resources/views/exitChattingRoom");
      return mnv;
   }

   // 채팅목록 불러오기
   @RequestMapping("/chattingList.do")
   public ModelAndView chattingList(HttpServletResponse response,
         HttpServletRequest request) throws IOException {
      System.out.println("채팅목록");
      // 채팅방 아이디 구하는것
      String chattingRoomId = request.getParameter("chat_id");
      System.out.println("채팅방 아이디 : " + chattingRoomId);
      response.setCharacterEncoding("utf-8");

      // 아이디에 해당하는 채팅목록 가져오는 부분
      HashMap<String, MessageBean> messages = messageService.getMessages()
            .get(chattingRoomId);
      // 트리로 바꾸면 트리는 자동 오름차순 정렬
      TreeMap<String, MessageBean> tree = new TreeMap<String, MessageBean>(
            messages);
      // 메시지 정보 목록
      Iterator<MessageBean> list = tree.values().iterator();
      // 메시지 정보를 넣을 JSONArray
      JSONArray messageList = new JSONArray();
      // 있는 개수만큼 반복
      while (list.hasNext()) {
         MessageBean message = list.next();// 찾은 1개의 메시지
         JSONObject obj = new JSONObject();
         obj.put("date", message.getDate());// 날짜
         obj.put("message", message.getContent());// 메시지
         obj.put("writerId", message.getWriterId());// 작성자
         messageList.add(obj);
      }
      ModelAndView mnv = new ModelAndView();
      mnv.addObject("chattingList", messageList);
      mnv.setViewName("/resources/views/chattingList");
      return mnv;
   }

   // 채팅방에서 메시지 보내기(한명에게)
   @RequestMapping("/sendMessageForOne.do")
   public void messageForOne(HttpServletRequest request,
         HttpServletResponse response, MessageBean msg) throws IOException {
      System.out.println("한명한테 메시지 보내기");
      System.out.println("메시지 정보" + msg);
      msg.setIsRead("false");
      // 메시지가 전송됏으면 저장
      if (msg != null)
         messageService.getMessages().get(msg.getRoomId())
               .put(msg.getDate(), msg);
      PrintWriter out = response.getWriter();
      out.println(0);
      out.flush();
   }

   // 전체 유저에서 검색한 키워드가 포함된 유저들 가져오기
   @RequestMapping("/searchPeople.do")
   public void searchPeople(HttpServletRequest request,
         HttpServletResponse response) throws IOException {
      System.out.println("사람검색");
      // 검새겅 구하는 부분
      String keyword = request.getParameter("keyword");
      System.out.println("검색어 아이디 : " + keyword);

      // 검색목록 가져오기
      ArrayList<String[]> list = memberService
            .searchMembersServiceByNameAndId(keyword);

      response.setCharacterEncoding("utf-8");
      System.out.println("검색어 결과 개수 : " + list.size());
      // 데이터를 보내는 부분
      JSONArray searchList = new JSONArray();
      for (int i = 0; i < list.size(); ++i) {
         String[] info = list.get(i);
         System.out.println(info[0] + "/" + info[1] + "/" + info[2]);
         JSONObject obj = new JSONObject();
         obj.put("name", info[0]);
         obj.put("id", info[1]);
         obj.put("imgUrl",
               "http://192.168.0.17:8089/FootStamp/resources/images/23.jpg");
         searchList.add(obj);
      }
      PrintWriter out = response.getWriter();
      out.println(searchList);
   }

   @RequestMapping("/sendMessage.do")
   public void send(HttpServletRequest request, HttpServletResponse response)
         throws IOException, ParseException {
      String id_list = request.getParameter("id_list");
      // 로그인 아이디 구하기
      // 아이디를 찾아내는 부분
      String id = null;
      // 쿠키를 구한다.
      Cookie[] cookies = request.getCookies();
      // 쿠키가 존재하는지 확인
      if (cookies != null) {
         for (int i = 0; i < cookies.length; ++i) {
            // 쿠키에 id 쿠키가 있는지 확인
            if ("id".equals(cookies[i].getName())) {
               // 아이디를 가져온다
               id = cookies[i].getValue();
            }
         }
      }

      JSONParser jsonparser = new JSONParser();
      // 아아디를 저장할 jsonarray
      JSONArray ary = null;

      // 안드로이드에서 가져온 String으로 초기화
      ary = (JSONArray) jsonparser.parse(id_list);
      
      // 메시지 꺼내기
      String message = request.getParameter("message");
      // 날자 꺼내기
      String date = request.getParameter("date");
      // 전달받은 내용 다 확인
      System.out.println("메시지" + message);
      System.out.println("받는 사람들 : " + ary);
      System.out.println("날짜 : " + date);
      //채팅방들 구하는거
      for(int i=0;i<ary.size();++i){
         String other_id=(String)ary.get(i);
         String roomId=messageService.searchChattinRoom(new String[]{id,other_id});
         if(roomId!=null){//있을경우 해당 방에 메시지 추가
            System.out.println("방번호 : "+roomId);
            messageService.getMessages().get(roomId).put(date,new MessageBean(roomId, id, date, message, "false"));
         }
         else{//없을 경우 방만들어서 추가
            roomId="/message/"+other_id+"_"+id+"_"+date+".txt";//아이디 만들어서 빈 메시지방 만들어주면된다.
            MemberBean sender=memberService.searchMemberService(id);
            MemberBean reciver=memberService.searchMemberService(other_id);
            messageService.addChattingRoom(roomId,new String[]{sender.getId(),sender.getName()},new String[]{reciver.getId(),reciver.getName()});
            messageService.getMessages().get(roomId).put(date,new MessageBean(roomId, id, date, message, "false"));
         }
      }
      // 간혹 디폴트 컨텐츠 타입이 다른 브라우저에서 오류날 수 있기 때문이다.
      response.setCharacterEncoding("utf-8");
      // 응답 보내기
      PrintWriter out = response.getWriter();
      // 성공시 0 실패시 1
      out.println("0");
   }
}