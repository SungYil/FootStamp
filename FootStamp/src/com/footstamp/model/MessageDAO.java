/*package com.footstamp.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.datasource.BaseDAO;

public class MessageDAO {
	private static HashMap<String, ArrayList<String>> chatPeople;
	private static HashMap<String, ArrayList<MessageDTO>> messages;
	private static HashMap<String, String> messageUrls;//이걸 테이블에 저장
	
	static{
		chatPeople=new HashMap<String,ArrayList<String>>();
		messages=new HashMap<String,ArrayList<MessageDTO>>();
		messageUrls=new HashMap<String,String>();
	}
	private MessageDAO() {
		chatPeople = new HashMap<String, ArrayList<String>>();
		messages = new HashMap<String, ArrayList<MessageDTO>>();
		messageUrls = new HashMap<String, String>();
	}

	// 채팅방이 한개 만들 경우
	public MessageDAO(String roomId, String[] chatPeople,
			MessageDTO[] messages, String messageUrl) {
		this();
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<MessageDTO> list2 = new ArrayList<MessageDTO>();
		for (int i = 0; i < chatPeople.length; ++i)
			list.add(chatPeople[i]);
		this.chatPeople.put(roomId, list);

		for (int i = 0; i < messages.length; ++i)
			list2.add(messages[i]);
		this.messages.put(roomId, list2);

		messageUrls.put(roomId, messageUrl);
	}

	public MessageDAO(String roomId, ArrayList<String> chatPeople,
			ArrayList<MessageDTO> messages, String messageUrl) {
		this();
		this.chatPeople.put(roomId, chatPeople);
		this.messages.put(roomId, messages);
		messageUrls.put(roomId, messageUrl);
	}

	public MessageDAO(String[] roomIds, ArrayList<String[]> chatPeople,
			ArrayList<MessageDTO[]> messages, String[] messageUrls) {
		for (int i = 0; i < roomIds.length; ++i) {
			ArrayList<String> id = new ArrayList<String>();
			for (int j = 0; j < chatPeople.get(i).length; ++j) {
				String[] ids = chatPeople.get(i);
				id.add(ids[i]);
			}
			ArrayList<MessageDTO> message = new ArrayList<MessageDTO>();
			for (int j = 0; j < messages.get(i).length; ++j) {
				MessageDTO[] messageList = messages.get(i);
				message.add(messageList[i]);
			}
			this.chatPeople.put(roomIds[i], id);
			this.messages.put(roomIds[i], message);
			this.messageUrls.put(roomIds[i], messageUrls[i]);
		}
	}

	public MessageDAO(HashMap<String, ArrayList<String>> chatPeople,
			HashMap<String, ArrayList<MessageDTO>> messages,
			HashMap<String, String> messageUrls) {
		super();
		this.messages = messages;
		this.chatPeople = chatPeople;
		this.messageUrls = messageUrls;
	}

	public HashMap<String, ArrayList<MessageDTO>> getMessages() {
		return messages;
	}

	public void setMessages(HashMap<String, ArrayList<MessageDTO>> messages) {
		this.messages = messages;
	}

	public HashMap<String, ArrayList<String>> getChatPeople() {
		return chatPeople;
	}

	public void setChatPeople(HashMap<String, ArrayList<String>> chatPeople) {
		this.chatPeople = chatPeople;
	}

	public HashMap<String, String> getMessageUrls() {
		return messageUrls;
	}

	public void setMessageUrls(HashMap<String, String> messageUrls) {
		this.messageUrls = messageUrls;
	}

	public int addChattingRoom(String roomId, ArrayList<String> chatPeople,
			ArrayList<MessageDTO> messages, String messageUrl) {
		if (messageUrls.get(roomId) != null)// 이미 존재하는 방번호일 경우
			return 0;
		this.messages.put(roomId, messages);
		this.chatPeople.put(roomId, chatPeople);
		this.messageUrls.put(roomId, messageUrl);
		return 1;
	}

	public int addChattingRoom(String roomId, String[] chatPeople,
			MessageDTO[] messages, String messageUrl) {
		if (messageUrls.get(roomId) != null)// 이미 존재하는 방번호일 경우
			return 0;
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<MessageDTO> list2 = new ArrayList<MessageDTO>();

		for (int i = 0; i < chatPeople.length; ++i)
			list.add(chatPeople[i]);
		this.chatPeople.put(roomId, list);

		for (int i = 0; i < messages.length; ++i)
			list2.add(messages[i]);
		this.messages.put(roomId, list2);

		this.messageUrls.put(roomId, messageUrl);
		return 1;
	}

	public int addChattingRooms(String[] roomIds,
			ArrayList<String[]> chatPeople, ArrayList<MessageDTO[]> messages,
			String[] messageUrls) {
		int cnt = 0;
		for (int i = 0; i < roomIds.length; ++i)
			cnt += addChattingRoom(roomIds[i], chatPeople.get(i),
					messages.get(i), messageUrls[i]);
		return cnt;
	}

	public int addPerson(String roomId, String personId) {
		ArrayList<String> list = chatPeople.get(roomId);
		if (list == null)
			return 0;
		list.add(personId);
		return 1;
	}

	public int addPeople(String roomId, String[] personIds) {
		int cnt = 0;
		for (int i = 0; i < personIds.length; ++i)
			cnt += addPerson(roomId, personIds[i]);
		return cnt;
	}

	public int addMessage(String roomId, MessageDTO message) {
		ArrayList<MessageDTO> list = messages.get(roomId);
		if (list == null)
			return 0;
		list.add(message);
		return 1;
	}

	public int addMessages(String roomId, MessageDTO[] messages) {
		int cnt = 0;
		for (int i = 0; i < messages.length; ++i)
			cnt += addMessage(roomId, messages[i]);
		return cnt;
	}

	public int addMessageUrl(String roomId, String messageUrl) {
		String url = messageUrls.get(roomId);
		if (url != null)// 이미 존재하는 방번호일 경우
			return 0;

		messageUrls.put(roomId, messageUrl);
		return 1;
	}

	public int addMessageUrls(String roomId, String[] messageUrls) {
		int cnt = 0;
		for (int i = 0; i < messageUrls.length; ++i)
			cnt += addMessageUrl(roomId, messageUrls[i]);
		return cnt;
	}

	public int deleteRoom(String roomId) {
		// 실패하는 경우
		String url = messageUrls.get(roomId);
		if (url == null)// 없을 경우
			return 0;
		deletePeopleAll(roomId);
		deleteMessageAll(roomId);
		deleteMessageUrl(roomId);
		return 1;
	}

	public int deleteRooms(String[] roomIds) {
		int cnt = 0;
		for (int i = 0; i < roomIds.length; ++i)
			cnt += deleteRoom(roomIds[i]);
		return cnt;
	}

	public int deletePerson(String roomId, String personId) {
		ArrayList<String> list = chatPeople.get(roomId);
		int index = searchPersonIndex(roomId, personId);
		if (index == -1)// 없을 경우
			return 0;
		list.remove(index);
		return 1;
	}

	public int deletePeople(String roomId, String[] personIds) {
		int cnt = 0;
		for (int i = 0; i < personIds.length; ++i) {
			cnt += deletePerson(roomId, personIds[i]);
		}
		return cnt;
	}

	public int deletePeopleAll(String roomId) {
		ArrayList<String> list = chatPeople.get(roomId);
		int cnt = list.size();
		list.clear();// 비워버린다.
		return cnt;
	}

	public int deleteMessage(String roomId, MessageDTO message) {
		ArrayList<MessageDTO> list = messages.get(roomId);
		for (int i = 0; i < list.size(); ++i) {
			if (list.get(i).equals(message)) {
				list.remove(i);
				return 1;
			}
		}
		return 0;
	}

	public int deleteMessages(String roomId, MessageDTO[] messages) {
		int cnt = 0;
		for (int i = 0; i < messages.length; ++i) {
			cnt += deleteMessage(roomId, messages[i]);
		}
		return cnt;
	}

	public int deleteMessageAll(String roomId) {
		ArrayList<MessageDTO> list = messages.get(roomId);
		int cnt = list.size();
		list.clear();// 비워버린다.
		return cnt;
	}

	public int deleteMessageUrl(String roomId) {
		String url = messageUrls.get(roomId);
		if (url == null)// 없을 경우
			return 0;
		messageUrls.remove(roomId);
		return 1;
	}

	public int deleteMessageUrls(String[] roomIds) {
		int cnt = 0;
		for (int i = 0; i < roomIds.length; ++i)
			cnt += deleteMessageUrl(roomIds[i]);
		return cnt;
	}

	public String searchPerson(String roomId, String personId) {
		ArrayList<String> list = chatPeople.get(roomId);
		int index = searchPersonIndex(roomId, personId);
		if (index == -1)// 없을 경우
			return null;
		return list.get(index);
	}

	public int searchPersonIndex(String roomId, String personId) {
		ArrayList<String> list = chatPeople.get(roomId);
		for (int i = 0; i < list.size(); ++i) {
			if (list.get(i).equals(personId)) {
				list.remove(i);
				return i;
			}
		}
		return -1;
	}

	public List<String> searchPeople(String roomId) {
		ArrayList<String> list = chatPeople.get(roomId);
		return Collections.synchronizedList(list);
	}

	public MessageDTO searchMessage(String roomId, String date) {
		ArrayList<MessageDTO> list = messages.get(roomId);
		if (list == null)// 해당 채팅방이 없을 경우
			return null;
		if (date == null)// 날짜를 입력하지 않았을 경우
			return null;
		for (int i = 0; i < list.size(); ++i) {
			MessageDTO temp = list.get(i);
			if (temp.getDate().equals(date))
				return temp;
		}
		return null;
	}

	public List<MessageDTO> searchMessages(String roomId) {
		ArrayList<MessageDTO> list = messages.get(roomId);
		if (list == null) {// 해당 채팅방이 없을경우
			return null;
		}
		list.clear();
		return Collections.synchronizedList(list);
	}

	public String searchMessageUrl(String roomId) {
		return messageUrls.get(roomId);
	}

	public List<String> searchMessageUrls(String[] roomId) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < roomId.length; ++i) {
			String temp = searchMessageUrl(roomId[i]);
			if (temp != null)
				list.add(temp);
		}
		return Collections.synchronizedList(list);
	}

	public void save() {
		try {
			BaseDAO dao = new BaseDAO();
			Connection conn = dao.getConnection();
			Statement stmt = conn.createStatement();
			//테이블 초기화
			stmt.executeUpdate("delete from message");
			Iterator<String> urls=messageUrls.values().iterator();
			Iterator<String> roomIds=messageUrls.keySet().iterator();
			while(urls.hasNext()){
				String url=urls.next();
				String roomId=roomIds.next();
				stmt.executeUpdate("insert into message values('"+roomId+"','"+url+"')");
			}
			dao.closeDBObjects(null, stmt, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void load() {
		try {
			BaseDAO dao = new BaseDAO();
			Connection conn = dao.getConnection();
			Statement stmt = conn.createStatement();
			// 등록된 데이터가 있을경우
			ResultSet result = stmt.executeQuery("select * from message");
			messageUrls = new HashMap<String, String>();
			while (result.next()) {
				String roomId = result.getString("room_id");
				String messageUrl = result.getString("message_url");
				messageUrls.put(roomId, messageUrl);
			}
			dao.closeDBObjects(null, stmt, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "MessageDAO [messages=" + messages + ", chatPeople="
				+ chatPeople + ", messageUrls=" + messageUrls + "]";
	}
	public void fileRead(){
		BufferedReader read=null;
		String path="C:/eclipse/j2ee_workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/FootProject";
		try{
			Set<String> key=MessageDAO.messageUrls.keySet();
			Iterator<String> iter=key.iterator();
			while(iter.hasNext()){
				String roomId=iter.next();
				
				read=new BufferedReader(new FileReader(path+MessageDAO.messageUrls.get(roomId)));
				ArrayList<String> chat=new ArrayList<String>();
				chat.add(read.readLine());
				chat.add(read.readLine());
				chatPeople.put(roomId, chat);
				
				ArrayList<MessageDTO> con=new ArrayList<MessageDTO>();
				while(true){
					String date=read.readLine();
					String chatContent=read.readLine();
					if(date==null||chatContent==null){
						break;
					}
					con.add(new MessageDTO(read.readLine(),read.readLine()));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				read.close();
			}catch(Exception e){}
		}
	}
	public void fileWrite(){
		PrintWriter out=null;
		String path="C:/eclipse/j2ee_workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/FootProject";
		try{
			Set<String> key=messageUrls.keySet();
			Iterator<String> iter=key.iterator();
			while(iter.hasNext()){
				String roomId=iter.next();
				out=new PrintWriter(new FileWriter(path+messages.get(roomId)));
				ArrayList<String> chatList=chatPeople.get(roomId);
				for(int i=0;i<chatList.size();++i){
					out.println(chatList.get(i));
				}
				ArrayList<MessageDTO> lists=messages.get(roomId);
				
				for(int i=0;i<lists.size();++i){
					MessageDTO dto=lists.get(i);
					out.println(dto.getDate());
					out.println(dto.getContent());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				out.close();
			}catch(Exception e){
			}
		}
	}
	public void saveMessage(HashMap<String, String> messageUrls){
		Set<String> key=MessageDAO.messageUrls.keySet();
		Iterator<String> iter=key.iterator();
		
		while(iter.hasNext()){
			deleteRoom(iter.next());
		}
		
		key=messageUrls.keySet();
		iter=key.iterator();
		
		while(iter.hasNext()){
			String roomId=iter.next();
			this.messageUrls.put(roomId, messageUrls.get(roomId));
		}
		fileWrite();
	}
	public HashMap<String, String> loadMessage(){
		Set<String> key=MessageDAO.messageUrls.keySet();
		Iterator<String> iter=key.iterator();
		
		while(iter.hasNext()){
			deleteRoom(iter.next());
		}
		fileRead();
		
		return this.messageUrls;
	}

}
*/