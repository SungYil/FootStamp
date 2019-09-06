package com.footstamp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.footstamp.bean.AlarmBean;
import com.footstamp.bean.MessageBean;
@Service
public class MessageService {
   
   //키 : 채팅방 id 값 : 채팅목록(키:날짜 값 : 메시지)
   private Map<String,HashMap<String,MessageBean>> messages;
   //키 : 채팅방id 값 : 채팅회원목록 (키 : 채팅회원id, 값 : 회원 이름)
   private Map<String,HashMap<String,String>> chatPeople;

   public MessageService() {
      //채팅방 예쩨목록
      //ehddlrvv_jr2900jr_2016_10_05_24_18_40_32.txt
      //jr3000jr_ehddlrvv_2017_01_10_18_52_13.txt
      //jr3100jr_ehddlrvv_2017_01_10_18_52_13.txt
      //ehddlrvv_jr3200jr_2017_01_10_18_52_13.txt
      //ehddlrvv_jr3300jr_2017_01_10_18_52_13.txt
      messages=Collections.synchronizedMap(new HashMap<String,HashMap<String,MessageBean>> ());
      chatPeople=Collections.synchronizedMap(new HashMap<String,HashMap<String,String>> ());
      //메시지 임시 데이터
      messages.put("/messages/ehddlrvv_jr2900jr_2016_10_05_24_18_40_32.txt",new HashMap<String,MessageBean> ());
      messages.put("/messages/jr3000jr_ehddlrvv_2016_03_10_10_52_13.txt",new HashMap<String,MessageBean> ());
      messages.put("/messages/jr3100jr_ehddlrvv_2015_06_05_13_50_11.txt",new HashMap<String,MessageBean> ());
      messages.put("/messages/ehddlrvv_jr3200jr_2016_07_11_07_53_23.txt",new HashMap<String,MessageBean> ());
      
      HashMap<String,MessageBean> lis1=messages.get("/messages/ehddlrvv_jr2900jr_2016_10_05_24_18_40_32.txt");
      lis1.put("2016_09_25_12_25_30",new MessageBean("/messages/ehddlrvv_jr3200jr_2016_07_11_07_53_23.txt", "ehddlrvv","2016_09_25_12_25_30","첫번째 댓글을 달았습니다.","false"));
      lis1.put("2017_04_23_12_25_30",new MessageBean("/messages/ehddlrvv_jr3200jr_2016_07_11_07_53_23.txt", "jr3200jr","2017_04_23_12_25_30","두번째 댓글을 달았습니다.","false"));
      lis1.put("2016_09_24_05_25_30",new MessageBean("/messages/ehddlrvv_jr3200jr_2016_07_11_07_53_23.txt", "ehddlrvv","2016_09_24_05_25_30","세번째 댓글을 달았습니다.","false"));
      lis1.put("2016_09_15_05_27_30",new MessageBean("/messages/ehddlrvv_jr3200jr_2016_07_11_07_53_23.txt", "jr3200jr","2016_09_15_05_27_30","네번째 댓글을 달았습니다.","false"));
      lis1.put("2016_09_24_10_25_30",new MessageBean("/messages/ehddlrvv_jr3200jr_2016_07_11_07_53_23.txt", "jr3200jr","2016_09_24_10_25_30","다섯번째 댓글을 달았습니다.","false"));
      
      HashMap<String,MessageBean> lis2=messages.get("/messages/jr3000jr_ehddlrvv_2016_03_10_10_52_13.txt");
                           lis2.put("2016_04_30_20_15_30",new MessageBean("/messages/jr3000jr_ehddlrvv_2016_03_10_10_52_13.txt", "jr3000jr","2016_04_30_20_15_30","아니다 치킨먹자 치킨","false"));
                           lis2.put("2017_01_20_20_15_30",new MessageBean("/messages/jr3000jr_ehddlrvv_2016_03_10_10_52_13.txt", "ehddlrvv","2017_01_20_20_15_30","아니다 치킨먹자 치킨","false"));
      HashMap<String,MessageBean> lis3=messages.get("/messages/jr3100jr_ehddlrvv_2015_06_05_13_50_11.txt");
                           lis3.put("2016_09_24_12_25_30",new MessageBean("/messages/jr3100jr_ehddlrvv_2015_06_05_13_50_11.txt", "ehddlrvv","2016_09_24_12_25_30","내 댓글에 답좀 달아라","false"));
                           lis3.put("2016_03_30_20_15_30",new MessageBean("/messages/jr3100jr_ehddlrvv_2016_03_10_10_52_13.txt", "ehddlrvv","2016_03_30_20_15_30","아니다 치킨먹자 치킨","false"));
      HashMap<String,MessageBean> lis4=messages.get("/messages/ehddlrvv_jr3200jr_2016_07_11_07_53_23.txt");
                           lis4.put("2017_10_23_12_25_30",new MessageBean("/messages/ehddlrvv_jr3200jr_2016_07_11_07_53_23.txt", "ehddlrvv","2017_10_23_12_25_30","일기의 내용은 뭘까요?","false"));
                           lis4.put("2017_10_30_20_15_30",new MessageBean("/messages/ehddlrvv_jr3200jr_2016_07_11_07_53_23.txt", "jr3200jr","2017_10_30_20_15_30","아니다 치킨먹자 치킨","false"));
      
      
      //채팅인원 임시 데이터
      chatPeople.put("/messages/ehddlrvv_jr2900jr_2016_10_05_24_18_40_32.txt",new HashMap<String,String>());
      chatPeople.put("/messages/jr3000jr_ehddlrvv_2016_03_10_10_52_13.txt",new HashMap<String,String>());
      chatPeople.put("/messages/jr3100jr_ehddlrvv_2015_06_05_13_50_11.txt",new HashMap<String,String>());
      chatPeople.put("/messages/ehddlrvv_jr3200jr_2016_07_11_07_53_23.txt",new HashMap<String,String>());
      
      HashMap<String,String> list1=chatPeople.get("/messages/ehddlrvv_jr2900jr_2016_10_05_24_18_40_32.txt");
      list1.put("ehddlrvv","박성일");
      list1.put("jr2900jr","이은호");
      HashMap<String,String> list2=chatPeople.get("/messages/jr3000jr_ehddlrvv_2016_03_10_10_52_13.txt");
      list2.put("ehddlrvv","박성일");
      list2.put("jr3000jr","황용주");
      HashMap<String,String> list3=chatPeople.get("/messages/jr3100jr_ehddlrvv_2015_06_05_13_50_11.txt");
      list3.put("ehddlrvv","박성일");
      list3.put("jr3100jr","김예은");
      HashMap<String,String> list4=chatPeople.get("/messages/ehddlrvv_jr3200jr_2016_07_11_07_53_23.txt");
      list4.put("ehddlrvv","박성일");
      list4.put("jr3200jr","박동익");
   
   
   }
      
   public Map<String, HashMap<String, MessageBean>> getMessages() {
      return messages;
   }

   public void setMessages(Map<String, HashMap<String, MessageBean>> messages) {
      this.messages = Collections.synchronizedMap(messages);
   }

   public Map<String, HashMap<String, String>> getChatPeople() {
      return chatPeople;
   }

   public void setChatPeople(Map<String, HashMap<String, String>> chatPeople) {
      this.chatPeople = Collections.synchronizedMap(chatPeople);
   }

   
   public String searchChattinRoom(String[] personIds){
      if(personIds==null)
         return null;
      
      Iterator<String> keyList=chatPeople.keySet().iterator();//채팅방 아이디
      while(keyList.hasNext()){
         //받은 인원목록이 채팅방에 모두 있나 확인하기 위한 변수
         int cnt=0;
         String roomId=keyList.next();//채팅방의 키를 저장해둔다.
         HashMap<String,String> personidsMap=chatPeople.get(roomId);//해당 채팅방의 인원을 가져온다.
         Iterator<String> personIdsList=personidsMap.keySet().iterator();
         while(personIdsList.hasNext()){
            String personId=personIdsList.next();//한명의 키를 가져온다.
            for(int i=0;i<personIds.length;++i){//전달인자로 받은 사람목록만큼 반복
               if(personId.equals(personIds[i])){//일치하는 경우 숫자를 증가시킨다.
                  ++cnt;
                  break;
               }
            }
            if(cnt==personIds.length)//전달받은 인원이 다 있을 경우
               break; 
         }
         if(cnt==personIds.length)
            return roomId;
      }
      return null;
   }
   //0번 아이디,1번 이름  방 식별키가 url 경로다.
   public int addChattingRoom(String roomId,String[] senderInfo,String[] receiverInfo){
      if(roomId==null)//방 안받을 경우
         return 0;
      
      if(messages.get(roomId)!=null)//있는 방일 경우
         return 0;
      
      messages.put(roomId,new HashMap<String,MessageBean>());
      HashMap<String,String> chatPeople=new HashMap<String,String>();
      chatPeople.put(senderInfo[0], senderInfo[1]);
      chatPeople.put(receiverInfo[0], receiverInfo[1]);
      this.chatPeople.put(roomId,chatPeople);
      return 1;
   }
   //메시지 보내기(채팅방에 메시지 추가)
   public int sendMessage(String roomId,MessageBean message){
      if(roomId==null || roomId.equals(""))//채팅룸이 잘못 입력됐을 경우
         return 0;
      if(message==null || message.equals(""))//메시지가 잘못 입력됐을 경우
         return 0;
      
      if(messages.get(roomId)==null)//없는 방일 경우
         return 0;
      if(!roomId.equals(message.getRoomId())){//채팅방이 서로 다를 경우
         return 0;
      }
      //채팅방에 해당 사용자가 없을 경우
      HashMap<String,String> list=chatPeople.get(roomId);
      if(list.get(message.getWriterId())==null){//채팅방에 없는 사람일 경우
         return 0;
      }
      
      HashMap<String,MessageBean> messageList=messages.get(roomId);
      messageList.put(message.getDate(), message);
      return 1;
   }
   //메시지 알람 보내기(강제로 생성해서 해놨다 나중에 고쳐라)
   public int messageAlarm(String senderId,String receiverId,MessageBean message){
      //알림에 보내기 위한 과정
      return new AlarmService().sendAlarm(senderId,"message", new AlarmBean(senderId,receiverId,"message",null,message.getDate(), "false"));
   }
   
   //한명이 자신과 대화중인 채팅방 목록 가져오기
   public String[] searchChattingRooms(String personId){
      if(personId==null)
         return null;
      
      ArrayList<String> list=new ArrayList<String>();
      Iterator<String> keyList=chatPeople.keySet().iterator();//채팅방 아이디
      while(keyList.hasNext()){
         //받은 인원이 채팅방에 있나 확인하기 위한 변수
         String roomId=keyList.next();//채팅방의 키를 저장해둔다.
         HashMap<String,String> personidsMap=chatPeople.get(roomId);//해당 채팅방의 인원을 가져온다.
         Iterator<String> personIdsList=personidsMap.keySet().iterator();
         while(personIdsList.hasNext()){
            String person=personIdsList.next();//한명의 키를 가져온다.
            if(person.equals(personId)){//일치하는 경우 넣는다.
               list.add(roomId);
               break;
            }
         }
      }
      if(list.size()==0)
         return null;
      String[] result=new String[list.size()];
      for(int i=0;i<list.size();++i)
         result[i]=list.get(i);
      return result;
      
   }
   
   public MessageBean getLastMessage(String roomId){
      HashMap<String,MessageBean> list=messages.get(roomId);
      if(list.size()==0)//메시지 없을 경우
         return null;
      String max=Collections.max(list.keySet());
      return list.get(max);
   }
   
   public ArrayList<String[]> searchMessages(String roomId){
      HashMap<String,MessageBean> list=messages.get(roomId);
      if(list==null)//메시지 하나도 없을 경우
         return null;
      ArrayList<String[]> result=new ArrayList<String[]>();
      Iterator<MessageBean> iter=list.values().iterator();
      while(iter.hasNext()){
         MessageBean message=iter.next();
         String[] info=message.getAll();
         result.add(new String[]{info[0],info[1],info[2],info[3],info[4]});//혹시  몰라서 정보 다보낸다.나중에 수정
      }
      return result;
   }
   
   public int deleteMessage(String roomId,String date){
      if(roomId==null || roomId.equals(""))//잘못된 아이디
         return 0;
      if(date==null || date.equals(""))// 잘못된 날짜
         return 0;
      
      HashMap<String,MessageBean> list=messages.get(roomId);
      if(list==null)//존재하지 않는 채팅방
         return 0;
      MessageBean result=list.remove(date);
      if(result==null)//삭제 실패할 경우(존재하지 않는 메시지)
         return 0;
      return 1;
   }
   
   public int deleteChattingRoom(String roomId){
      if(roomId==null || roomId.equals("")){
         return 0;
      }
      
      HashMap<String,MessageBean> result=messages.remove(roomId);
      if(result==null)//메시지 삭제 실패(없는 채팅방)일 경우,채팅인원목록 제거 안해도된다. 
         return 0;
      chatPeople.remove(roomId);
      return 1;
   }
   
   @Override
   public String toString() {
      return "MessageService [messages=" + messages + ", chatPeople="
            + chatPeople + "]";
   }

}