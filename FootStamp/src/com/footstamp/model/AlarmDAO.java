/*package com.footstamp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.datasource.BaseDAO;


public class AlarmDAO {
	*//**
	 * key : id
	 *//*
	private Map<String,ArrayList<AlarmDTO>> alarm;
	private static AlarmDAO instance;
	
	static {
		instance=new AlarmDAO();
	}
	private AlarmDAO(){
		this.alarm=Collections.synchronizedMap(new HashMap<String,ArrayList<AlarmDTO>>());
	}
	public void daoUpdate(){
		BaseDAO dao=new BaseDAO();
		Connection con=null;
		Statement st=null;
		ResultSet re=null;
		try{
			
			System.out.println(removeAll());
			
			con=dao.getConnection();
			st=con.createStatement();
			re=st.executeQuery("select * from footproject_alarm_tb");
			
			while(re.next()){
				String id=re.getString(5);
				if(this.alarm.get(id)==null){
					this.alarm.put(id, new ArrayList<AlarmDTO>());
				}
				alarm.get(id).add(new AlarmDTO(id,re.getInt(2),
						re.getString(1),re.getString(3),re.getInt(4)));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
	}
	
	
	public Map<String, ArrayList<AlarmDTO>> getAlarm() {
		return alarm;
	}
	public void setAlarm(Map<String, ArrayList<AlarmDTO>> alarm) {
		this.alarm = alarm;
	}
	public static AlarmDAO getInstance() {
		return instance;
	}
	
	public int addAlarm(String id,AlarmDTO alarm){
		if(alarm==null){return 0;}
		if(searchById(id)==null){
			this.alarm.put(id, new ArrayList<AlarmDTO>());
		}
		this.alarm.get(id).add(alarm);
		
		BaseDAO dao=new BaseDAO();
		Connection con=null;
		PreparedStatement ps=null;
		boolean bool=false;
		try{          
			con=dao.getConnection();
			ps=con.prepareStatement("insert into footproject_alarm_tb values(?,?,?,?,?)");
			ps.setString(1, alarm.getAlarmContent());
			ps.setInt(2, alarm.getParticipant());
			ps.setString(3, alarm.getDate());
			ps.setInt(4,alarm.isRead());
			ps.setString(5, id);
			ps.executeUpdate();
			bool=true;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		if(bool==false){return 0;}
		return 1;
	}
	public int addAlarm(String id,int participant,String alarmContent,String date,int isRead){
		
		return addAlarm(id,new AlarmDTO(id,participant,alarmContent,date,isRead));
	}
	public int addAlarm(HashMap<String,ArrayList<AlarmDTO>> alarm){
		Set<String> key=alarm.keySet();
		Iterator<String> iter=key.iterator();
	
		while(iter.hasNext()){
			String id=iter.next();
			ArrayList<AlarmDTO> list=alarm.get(id);
			for(int i=0;i<list.size();++i){
				addAlarm(id,list.get(i));
			}
		}
		
		return 1;
	}
	public ArrayList<AlarmDTO> searchById(String id){
		daoUpdate();
		return this.alarm.get(id);
	}
	public HashMap<String,ArrayList<AlarmDTO>> searchByIds(String id[]){
		if(this.alarm.size()==0)return null;
		HashMap<String,ArrayList<AlarmDTO>> alarm=new HashMap<String,ArrayList<AlarmDTO>>();
	
		for(int i=0;i<id.length;++i){
			if(searchById(id[i])==null){
				return null;
			}
			alarm.put(id[i],this.alarm.get(id));
		}
		return alarm;
	}
	public ArrayList<AlarmDTO> searchByParticipant(String id,int participant){
		daoUpdate();
		if(this.alarm.size()==0){return null;}
		ArrayList<AlarmDTO> temp=new ArrayList<AlarmDTO>();
		ArrayList<AlarmDTO> alarm=this.alarm.get(id);
		
		for(int i=0;i<alarm.size();++i){
			if(alarm.get(i).getParticipant()==participant){
				temp.add(alarm.get(i));
			}
		}
		
		return temp;
	}
	public HashMap<String,ArrayList<AlarmDTO>> searchByParticipant(int participant){
		if(this.alarm.size()==0){return null;}
		
		HashMap<String,ArrayList<AlarmDTO>> temp=new HashMap<String,ArrayList<AlarmDTO>>();
		
		Set<String> key=this.alarm.keySet();
		Iterator<String> iter=key.iterator();
		
		while(iter.hasNext()){
			String id=iter.next();
			ArrayList<AlarmDTO> list=searchByParticipant(id,participant);
			if(list!=null&&list.size()!=0){
				temp.put(id, list);
			}
		}
		return temp;
	}
	public ArrayList<AlarmDTO> searchByIsRead(String id,int isRead){
		daoUpdate();
		if(this.alarm.size()==0){return null;}
		ArrayList<AlarmDTO> alarm=searchById(id);
		if(alarm==null)return null;
		
		
		ArrayList<AlarmDTO> temp=new ArrayList<AlarmDTO>();
		
		for(int i=0;i<alarm.size();++i){
			if(alarm.get(i).isRead()==isRead){
				temp.add(alarm.get(i));
			}
		}
		
		return temp;
	}
	public ArrayList<AlarmDTO> removeById(String id){
		if(this.alarm.size()==0)return null;
		if(id==null)return null;
		if(searchById(id)==null){return null;
		}
		BaseDAO dao=new BaseDAO();
		Connection con=null;
		PreparedStatement ps=null;
		try{
			con=dao.getConnection();
			ps=con.prepareStatement("delete from footproject_alarm_tb where id=?");
			ps.setString(1, id);
			ps.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return this.alarm.remove(id);
	}
	public HashMap<String,ArrayList<AlarmDTO>> removeByIds(String[] id){
		if(this.alarm.size()==0)return null;
		
		HashMap<String,ArrayList<AlarmDTO>> alarm=searchByIds(id);
		if(alarm==null){
			return null;
		}
		
		Set<String> key=alarm.keySet();
		Iterator<String> iter=key.iterator();
		HashMap<String,ArrayList<AlarmDTO>> temp=new HashMap<String, ArrayList<AlarmDTO>>();
		while(iter.hasNext()){
			String ids=iter.next();
			temp.put(ids, removeById(ids));
		}
		return temp;
	}
	public int removeAll(){
		Set<String> key=this.alarm.keySet();
		
		Iterator<String> iter=key.iterator();
		ArrayList<String> keys=new ArrayList<String>();
		while(iter.hasNext()){
			String id=iter.next();
			if(this.alarm.get(id)==null){
				return 0;
			}
			keys.add(id);
		}
		for(int i=0;i<keys.size();++i){
			this.alarm.remove(keys.get(i));
		}
		return 1;
	}
	public ArrayList<AlarmDTO> replace(String id,ArrayList<AlarmDTO> alarm){
		BaseDAO dao=new BaseDAO();
		Connection con=null;
		Statement st=null;
		PreparedStatement ps=null;
		
		try{
			con=dao.getConnection();
			st=con.createStatement();
			ps=con.prepareStatement("delete from footproject_alarm_tb where id='?'");
			ps.setString(1,id);
			ps=con.prepareStatement("insert into footproject_alarm_tb values(?,?,?,?,?)");
			for(int i=0;i<alarm.size();++i){
				AlarmDTO temp=alarm.get(i);
			ps.setString(1, temp.getAlarmContent());
			ps.setInt(2, temp.getParticipant());
			ps.setString(3, temp.getDate());
			ps.setInt(4,temp.isRead());
			ps.setString(5, id);
			ps.executeUpdate();
			}
			
		}catch(Exception e){
			e.getStackTrace();
		}
		return this.alarm.replace(id, alarm);
	}
	public void saveAlarm(HashMap<String,ArrayList<AlarmDTO>> alarms){
		Set<String> key=alarm.keySet();
		Iterator<String> iter=key.iterator();
		
		while(iter.hasNext()){
			this.removeById(iter.next());
		}
		
		this.addAlarm(alarms);
		
	}
	public Map<String,ArrayList<AlarmDTO>> loadAlarm(){
		Set<String> key=alarm.keySet();
		Iterator<String> iter=key.iterator();
		while(iter.hasNext()){
			this.removeById(iter.next());
		}
		daoUpdate();
		
		return this.alarm;
	}
	
}

*/