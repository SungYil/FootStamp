package com.footstamp.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.footstamp.bean.AlarmBean;
@Service
public class FollowService {

	private Map<String, HashMap<Byte,List<String>>> follow;

	private FollowService(){
	      this.follow= Collections.synchronizedMap(new HashMap<String,HashMap<Byte,List<String>>>());
	      this.follow.put("dkdkenen2006",new HashMap<Byte,List<String>>());
	      this.follow.put("shfur2006",new HashMap<Byte,List<String>>());
	      this.follow.put("id1",new HashMap<Byte,List<String>>());
	      this.follow.put("id2",new HashMap<Byte,List<String>>());
	      this.follow.put("id3",new HashMap<Byte,List<String>>());
	      this.follow.put("id4",new HashMap<Byte,List<String>>());
	      this.follow.put("id5",new HashMap<Byte,List<String>>());
	      this.follow.put("id6",new HashMap<Byte,List<String>>());
	      this.follow.put("dkendken2006",new HashMap<Byte,List<String>>());
	      this.follow.put("pjk430", new HashMap<Byte,List<String>>());
	      this.follow.put("ehddlrvv", new HashMap<Byte,List<String>>());
	      //updateData();
	      load();
	   }
	public FollowService(HashMap<String,HashMap<Byte,List<String>>> follow){
		this.follow=follow;

	}
	public void setFollow(HashMap<String, HashMap<Byte, List<String>>> follow) {
		this.follow = follow;
	}
	public Map<String, HashMap<Byte, List<String>>> getFollow() {
		return follow;
	}

	public void setFollow(Map<String, HashMap<Byte, List<String>>> follow) {
		this.follow = follow;
	}

	public String addFollowingService(String id,byte type,String followId){
		if(type!=-1||type!=1){return null;}
		if(searchFollowService(id)==null){
			this.follow.put(id,new HashMap<Byte,List<String>>());
			List<String> list1=new ArrayList<String>();
			List<String> list2=new ArrayList<String>();
			this.follow.get(id).put((byte)0,list1);
			this.follow.get(id).put((byte)1,list2);
		}
		
		if(searchFollowService(id,type,followId)!=null){
			return searchFollowService(id,type,followId);
		}
		this.follow.get(id).get(type).add(followId);
		
		if(searchFollowService(followId,type,id)==null){
			this.follow.get(followId).get(type*-1).add(id);
		}
		followAlarm(followId,id,"follow");
		
		return followId;
		
	}
	public List<String> updateFollowingService(String id,byte type,String oldFollowId,String newFollowId){
		if(type!=-1||type!=1)return null;
		if(searchFollowService(id,type,oldFollowId)==null){
			return null;
		}
		List<String> list = this.follow.get(id).get(type);
		
		for(int i=0;i<list.size();++i){
			if(oldFollowId.equals(list.get(i))){
				list.set(i, newFollowId);
				return list;
			}
		}
		return null;
	}
	public HashMap<Byte,List<String>> searchFollowService(String id){
		return this.follow.get(id);
	}
	public List<String> searchFollowService(String id,byte type){
		HashMap<Byte,List<String>> follow=this.follow.get(id);
		if(follow==null){return null;}
		Byte tp=type;
		return follow.get(tp);
	}
	public String searchFollowService(String id,byte type,String followId){
		if(followId==null)return null;
		List<String> list=searchFollowService(id,type);
		
		for(int i=0;i<list.size();++i){
			if(followId.equals(list.get(i))){
				return list.get(i);
			}
		}
		return null;
	}
	public int searchFollowCntService(String id,byte type){
		List<String> list=searchFollowService(id,type);
		if(list==null){
			return -1;
		}
		return list.size();
	}
	public String deleteFollowingService(String id, byte type,String followId){
		List<String> list=searchFollowService(id,type);
		if(list==null){
			return null;
		}
		
		int i=0;
		boolean bool=true;
		for(i=0;i<list.size();++i){
			if(list.get(i).equals(followId)){
				list.remove(i);
				bool=false;
				break;
			}
		}
		
		if(bool){
			return null;
		}
		
		List<String> followList=searchFollowService(id,(byte)(type*-1));
		for(i=0;i<list.size();++i){
			if(followList.get(i).equals(id)){
				followList.remove(i);
			}
		}
		return followId;
	}
	public void loadFollowingsService(String id){
		
	}
	public void load(){
		String path="C:\\workspace_spring_3\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\FootStamp\\follow\\";
		BufferedReader read=null;
		BufferedReader read2=null;
		try{
			Set<String> key=this.follow.keySet();
			Iterator<String> iter=key.iterator();
			while(iter.hasNext()){
				String id=iter.next();
				
				read=new BufferedReader(new FileReader(path+"follower\\"+id+"_followers.text"));
				read2=new BufferedReader(new FileReader(path+"following\\"+id+"_followings.text"));
				
				this.follow.get(id).put((byte)-1,new ArrayList<String>());//�ȷο�
				this.follow.get(id).put((byte)1,new ArrayList<String>());//�ȷ���
				
				while(true){
					String ids=read.readLine();
					if(ids==null)break;
					System.out.println(id);
					this.follow.get(id).get((byte)-1).add(ids);
					System.out.println(this.follow.get(id).get((byte)-1));
				}
				while(true){
					String ids=read2.readLine();
					if(ids==null)break;
					this.follow.get(id).get((byte)1).add(ids);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				read.close();
			}catch(Exception e){}
			try{
				read2.close();
			}catch(Exception e){}
		}
	}
	public void save(){
		String path="C:\\workspace_spring_3\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\FootStamp\\follow\\";
		PrintWriter out=null;
		PrintWriter out2=null;
		try{
			Set<String> key=this.follow.keySet();
			Iterator<String> iter=key.iterator();
			while(iter.hasNext()){
				String id=iter.next();
				
				out=new PrintWriter(new FileWriter(path+"follower\\"+id+".text"));
				out2=new PrintWriter(new FileWriter(path+"following\\"+id+".text"));
				
				List<String> list=this.follow.get(id).get((byte)-1);
				int i=0;
				for(i=0;i<list.size();++i){
					out.println(list.get((byte)i));
				}
				list=this.follow.get(id).get((byte)1);
				for(i=0;i<list.size();++i){
					out.println(list.get((byte)i));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				out.close();
			}catch(Exception e){}
			try{
				out2.close();
			}catch(Exception e){}
		}
	}
	public void followAlarm(String id,String type,String sendId){
		Calendar cal=new GregorianCalendar();
		String date=cal.get(Calendar.YEAR)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.DATE)+"/"+
				cal.get(Calendar.HOUR)+"/"+cal.get(Calendar.MINUTE)+"/"+cal.get(Calendar.SECOND);
		AlarmBean bean=new AlarmBean(sendId,id,type,"follow",""+cal.get(Calendar.YEAR),"no");
	}
	@Override
	public String toString() {
		return "FollowService [follow=" + follow + "]";
	}
	
}
