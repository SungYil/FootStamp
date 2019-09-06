/*package com.footstamp.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import com.datasource.BaseDAO;
import com.datasource.DynamicPathBinder;

public class FollowDAO {
	private static FollowDAO instance;
	private Map<String,FollowDTO> follow;//db저장용
	private Map<String,LinkedList<String>> following;
	private Map<String,LinkedList<String>> follower;
	
	static{
		instance=new FollowDAO();
	}
	private FollowDAO(){
		follow=Collections.synchronizedMap(new HashMap<String,FollowDTO>());
		following=Collections.synchronizedMap(new HashMap<String,LinkedList<String>>());
		follower= Collections.synchronizedMap(new HashMap<String,LinkedList<String>>());
	}
	public void FileSave(){
		PrintWriter out=null;
		PrintWriter out2=null;
		try{
			Set<String> key=this.follow.keySet();
			Iterator<String> iter=key.iterator();
			
			while(iter.hasNext()){
				String id=iter.next();
				
				System.out.println(id);
				FollowDTO dto=this.follow.get(id);
				
				String path="C:/eclipse/j2ee_workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/FootProject";
				out=new PrintWriter(new FileWriter(DynamicPathBinder.loadFollowPath(dto.getFollower())));
				// follower id����Ʈ �����ͼ� text�� �ִ´�.
				out2=new PrintWriter(new FileWriter(dto.getFollowing()));
				
				LinkedList<String> wer=this.follower.get(id);
				LinkedList<String> wing=this.following.get(id);
				
				for(int i=0;i<wer.size();++i){
					out.println(wer.get(i));
				}
				for(int i=0;i<wing.size();++i){
					out2.println(wing.get(i));
				}
				out.flush();
				out2.flush();
			}	
			
		}
		catch(Exception e){
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
		}
		finally{
			try{
				out.close();
			}catch(Exception e){}
			try{
				out2.close();
			}catch(Exception e){}
		}
	}
	public void FileLoad(){
		BufferedReader read=null;
		BufferedReader read2=null;
		dbLoad();
		
		try{
			Set<String> key=this.follow.keySet();
			Iterator<String> iter=key.iterator();
			
			while(iter.hasNext()){
				String id=iter.next();
				
				FollowDTO dto=this.follow.get(id);
				LinkedList<String> wing=new LinkedList<String>();
				LinkedList<String> wer=new LinkedList<String>();
				String path="C:/eclipse/j2ee_workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/FootProject";
				
				read=new BufferedReader(new FileReader(DynamicPathBinder.loadFollowPath(dto.getFollower())));
				read2=new BufferedReader(new FileReader(DynamicPathBinder.loadFollowPath(dto.getFollowing())));
				
				while(true){
					String str=read.readLine();
					if(str==null)break;
					wing.add(str);
				}
				while(true){
					String str=read2.readLine();
					if(str==null)break;
					wer.add(str);
				}
				
				this.follower.put(id,wer);
				this.following.put(id,wing);
			}
		}catch(Exception e){
			e.getStackTrace();
		}finally{
			try{
				read.close();
			}catch(Exception e){}
			try{
				read2.close();
			}catch(Exception e){}
		}
	}
	
	public void dbLoad(){
		BaseDAO dao=new BaseDAO();
		Connection con=null;
		Statement st=null;
		ResultSet re=null;
		try{
			con=dao.getConnection();
			st=con.createStatement();
			re=st.executeQuery("select * from footproject_follow_tb");
			
			while(re.next()){
				String id=re.getString(1);
				
				this.follow.put(id,new FollowDTO(re.getString(3),re.getString(2)));
				this.follower.put(id, new LinkedList<String>());
				this.following.put(id, new LinkedList<String>());
			}
		
			
		}catch(Exception e){
			e.getStackTrace();
		}
		
	}
	public int dbSave(String id,String follower,String following){
		BaseDAO dao=new BaseDAO();
		Connection con=null;
		PreparedStatement ps=null;
		int bool=0;
		try{          
			con=dao.getConnection();
			ps=con.prepareStatement("insert into footproject_follow_tb values(?,?,?)");
			ps.setString(1, id);
			ps.setString(2, follower);
			ps.setString(3, following);
			ps.executeUpdate();
			bool=1;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return bool;
	}
	public static FollowDAO getInstance(){
		return instance;
	}
	public Map<String, FollowDTO> getFollow() {
		return follow;
	}
	public void setFollow(Map<String, FollowDTO> follow) {
		this.follow = follow;
	}
	public Map<String, LinkedList<String>> getFollowing() {
		return following;
	}
	public void setFollowing(Map<String, LinkedList<String>> following) {
		this.following = following;
	}
	public Map<String, LinkedList<String>> getfollower() {
		return follower;
	}
	public void setfollower(HashMap<String, LinkedList<String>> follower) {
		this.follower = follower;
	}
	*//**
	 * 전달받은 id에 해당 종류에 팔로우 추가
	 * @param id 아이디
	 * @param kind 팔로워인지 팔로잉인지
	 * @param follow 팔로우 추가할 아이디
	 * @return 실패시 0리턴 성공 1리턴
	 *//*
	public int addFollow(String id,int kind,String follow){
		if(this.searchById(id)==0){
			dbSave(id,id+"followers.txt",id+"followings.txt");
			this.follow.put(id, new FollowDTO(id+"followings.text",id+"followers.text"));
			this.follower.put(id, new LinkedList<String>());
			this.following.put(id, new LinkedList<String>());
		}
		if(this.searchById(id, kind,follow)!=null){
			return 0;
		}
		
		if(kind==0){
			this.follower.get(id).add(follow);
		}else{
			this.following.get(id).add(follow);
		}
		
		
		return 1;
	}
	*//**
	 * 전달받은 id에 팔로워 팔로잉 다 추가
	 * @param id 아이디
	 * @param following 팔로잉 아이디
	 * @param follower 팔로워 아이디
	 * @return 성공시 1리턴
	 *//*
	public int addFollow(String id,String following, String follower){
		if(searchById(id)==0){
			this.follow.put(id, new FollowDTO(id+"followings.text",id+"followers.text"));
			this.follower.put(id, new LinkedList<String>());
			this.following.put(id, new LinkedList<String>());
			dbSave(id,id+"followers.txt",id+"followings.txt");
		}
		if(this.searchById(id, 0,follower)!=null){
			return 0;
		}
		if(this.searchById(id, 1,following)!=null){
			return 0;
		}
		this.following.get(id).add(following);
		this.follower.get(id).add(follower);
		return 1;
	}
	*//**
	 * 전달받은 id에 팔로워 리스트 팔로잉 리스트 추가
	 * @param id 아이디
	 * @param following 팔로잉 리스트
	 * @param follower 팔로워 리스트
	 * @return 성공시 1리턴
	 *//*
	public int addFollow(String id,LinkedList<String> following,LinkedList<String> follower){
		int followingSize;
		int followerSize;
		
		if(following==null)	followingSize=0;
		else followingSize=following.size();
		if(follower==null) followerSize=0;
		else followerSize=follower.size();
		
		if(searchById(id)==0){
			this.follow.put(id, new FollowDTO(id+"followings.text",id+"followers.text"));
			this.follower.put(id, new LinkedList<String>());
			this.following.put(id, new LinkedList<String>());
			dbSave(id,id+"followers.txt",id+"followings.txt");
		}
		
		for(int i=0;i<followerSize;++i){
			if(searchById(id,0,follower.get(i))!=null){
				return 0;
			}
		}
		for(int i=0;i<followingSize;++i){
			if(searchById(id,1,following.get(i))!=null){
				return 0;
			}
		}
		
		for(int i=0;i<followerSize;++i){
			this.follower.get(id).add(follower.get(i));
		}
		for(int i=0;i<followingSize;++i){
			this.following.get(id).add(following.get(i));
		}
		return 1;
		
	}
	*//**
	 * 넘어온 id에 해당하는 파일이 있는지 없는지 검사.
	 * @param id id값
	 * @return 0이면 없음, 1있음
	 *//*
	public int searchById(String id){
		Set<String> key=this.follow.keySet();
		Iterator<String> iter=key.iterator();
		
		while(iter.hasNext()){
			if(iter.next().equals(id)){
				return 1;
			}
		}
		return 0;
	}
	*//**
	 * 해당 아이디에 kind값에 해당하는 아이디 리스트가 있는지 검사
	 * @param id 아이디
	 * @param kind 종류
	 * @return
	 *//*
	public LinkedList<String> searchById(String id,int kind){
		if(kind==0){
			if(this.follower.size()==0)return null;
			return this.follower.get(id);
		}
		return this.following.get(id);
		
	}
	*//**
	 * 전달받은 아이디에 대한 팔로잉또는 팔로워 한명검색
	 * @param id 아이디
	 * @param kind 종류
	 * @param searchId 찾을아이디
	 * @return 찾을시 찾은 아이디 리턴
	 *//*
	public String searchById(String id,int kind,String searchId){
		if(kind==0){
			if(this.follower.size()==0)return null;
			LinkedList<String> emp=this.follower.get(id);
			
			for(int i=0;i<emp.size();++i){
				String temp=emp.get(i);
				if(searchId.equals(temp)){
					return temp;
				}
			}
		}else{
			if(this.following.size()==0)return null;
			LinkedList<String> emp=this.following.get(id);
			
			for(int i=0;i<emp.size();++i){
				String temp=emp.get(i);
				if(searchId.equals(temp)){
					return temp;
				}
			}
		}
		return null;
	}
	*//**
	 * 전달받은 아이디의 팔로워또는 팔로잉 한개의 아이디 삭제
	 * @param id 아이디
	 * @param kind 종류
	 * @param followId 삭제할 아이디
	 * @return 삭제할시 삭제당한 아이디 리턴 
	 *//*
	public String delectByFollowId(String id,int kind,String followId){
		String find=searchById(id,kind,followId);
		if(find==null){
			return null;
		}
		if(kind==0){
			LinkedList<String> list=this.follower.get(id);
			for(int i=0;i<list.size();++i){
				if(followId.equals(list.get(i))){
					list.remove(i);
					return followId;
				}
			}
		}
		else{
			LinkedList<String> list=this.following.get(id);
			for(int i=0;i<list.size();++i){
				if(followId.equals(list.get(i))){
					list.remove(i);
					return followId;
				}
			}
		}
		return null;
	}
	*//**
	 * 전달받은 아이디의 팔로잉 또는 팔로워 리스트삭제
	 * @param id 아이디
	 * @param kind 종류
	 * @return 삭제한 아이디 리스트
	 *//*
	public LinkedList<String> delectById(String id,int kind){
		LinkedList<String> list=searchById(id,kind);
		if(list==null){
			return null;
		}
		
		if(this.follower.get(id)==null&&this.following.get(id)==null){
			BaseDAO dao=new BaseDAO();
			Connection con=null;
			PreparedStatement ps=null;
			try{
				con=dao.getConnection();
				ps=con.prepareStatement("delete from footproject_follow_tb where id=?");
				ps.setString(1, id);
				ps.executeUpdate();
				
				if(kind==0){
					this.follower.remove(id);
				}else{
					this.following.remove(id);
				}
			}catch(Exception e){
				e.getStackTrace();
			}
			
		}
		return list;
	}
	*//**
	 * 전달받은 아이디의 팔로잉 팔로워 모두삭제
	 * @param id 아이디
	 * @return 성공여부 성공시 1리턴
	 *//*
	public int delectById(String id){
		if(searchById(id,0)==null){
			return 0;
		}
		if(searchById(id,1)==null){
			return 0;
		}
		
		
		BaseDAO dao=new BaseDAO();
		Connection con=null;
		PreparedStatement ps=null;
		try{
			con=dao.getConnection();
			System.out.println("삭제할 id"+id);
			ps=con.prepareStatement("delete from footproject_follow_tb where id=?");
			ps.setString(1, id);
			ps.executeUpdate();
			
			this.follower.remove(id);
			this.following.remove(id);
			this.follow.remove(id);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return 1;
	}
	*//**
	 * 수정메소드 해당 아이의 팔로잉 또는 팔로워 리스트를 전달받은 리스트로 수정
	 * @param id 아이디
	 * @param kind 종류
	 * @param follow 아이디 리스트
	 * @return 수정하기전 아이디 리스트
	 *//*
	public LinkedList<String> modifyFollow(String id,int kind,LinkedList<String> follow){
		if(kind==0){
			if(this.follower.size()!=follow.size()){return null;}
			LinkedList<String> list=this.follower.get(id);
			
			for(int i=0;i<list.size();++i){
				list.set(i, follow.get(i));
			}
		}else{
			if(this.following.size()!=follow.size()){return null;}
			LinkedList<String> list=this.following.get(id);
			for(int i=0;i<list.size();++i){
				list.set(i, follow.get(i));
			}
		}
		
		return follow;
	}
	public String modifyFollow(String id,int kind, String followId,String replaceId){
		if(this.searchById(id,kind,followId)==null)return null;
		
		if(kind==0){
			LinkedList<String> list=this.follower.get(id);
			for(int i=0;i<list.size();++i){
				if(followId.equals(list.get(i))){
					list.set(i, replaceId);
					return replaceId;
				}
			}
		}else{
			LinkedList<String> list=this.following.get(id);
			for(int i=0;i<list.size();++i){
				if(followId.equals(list.get(i))){
					list.set(i, replaceId);
					return replaceId;
				}
			}
		}
		return null;
	}
	public HashMap<String, LinkedList<String>> modifyFollow(String id,int kind,HashMap<String,LinkedList<String>> follow){
		if(kind==0){
			this.follower=follow;
		}else{
			this.following=follow;
		}
		return follow;
	}
	public void saveFollow(HashMap<String,FollowDTO> follow){
		
		Set<String> key=this.follow.keySet();
		Iterator<String> iter=key.iterator();
		
		while(iter.hasNext()){
			String id=iter.next();
			if(this.delectById(id)==0){
				System.out.println(id+"의 FollowDAO saveFollow 삭제오류!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			}
		}
		
		key=follow.keySet();
		iter=key.iterator();
		
		while(iter.hasNext()){
			String id=iter.next();
			this.follow.put(id,follow.get(id));
		}
		this.FileLoad();
		
		key=this.follow.keySet();
		iter=key.iterator();
		while(iter.hasNext()){
			String id=iter.next();
			FollowDTO dto=this.follow.get(id);
			dbSave(id, dto.getFollower(), dto.getFollowing());
		}
	}
	public Map<String,FollowDTO> loadFollow(){
		Set<String> key=this.follow.keySet();
		Iterator<String> iter=key.iterator();
		
		while(iter.hasNext()){
			String id=iter.next();
			if(this.delectById(id)==0){
				System.out.println(id+"의 FollowDAO saveFollow 삭제오류!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			}
		}
		
		dbLoad();
		return this.follow;
	}
}
*/