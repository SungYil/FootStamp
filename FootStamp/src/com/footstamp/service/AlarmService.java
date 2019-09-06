package com.footstamp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.footstamp.bean.AlarmBean;
@Service
public class AlarmService {

	private Map<String,HashMap<String,List<AlarmBean>>> alarms;

	public AlarmService(){
		this.alarms= Collections.synchronizedMap(new HashMap<String,HashMap<String,List<AlarmBean>>>());
	}
	
	public Map<String, HashMap<String, List<AlarmBean>>> getAlarms() {
		return alarms;
	}
	
	public void setAlarms(Map<String, HashMap<String, List<AlarmBean>>> alarms) {
		this.alarms = alarms;
	}

	public void setAlarms(HashMap<String, HashMap<String, List<AlarmBean>>> alarms) {
		this.alarms = alarms;
	}
	
	/**
	 * 수신자에게 알림을 추가
	 * @param id 수신자
	 * @param type 타입
	 * @param bean 알림내용
	 * @return 추가 성공여부 성공시 1리턴
	 */
	public int sendAlarm(String id,String type,AlarmBean bean){
		List<AlarmBean> list=this.searchAlarm(id, type);
		if(list==null){
			this.alarms.get(id).put(type, new ArrayList<AlarmBean>());
		}
		list.add(bean);
		/*if(type.equals("message")){
			
		}else if(type.equals("follow")){
			
		}else if(type.equals("review")){
			
		}*/
		return 1;
	}
	/**
	 * 수신자에게 해당 알림내용 추가.
	 * @param id 수신자
	 * @param bean 알림내용
	 * @return 추가성공여부 추가성공시 1리턴
	 */
	public int sendAlarm(String id,AlarmBean bean){
		return sendAlarm(id,bean.getType(),bean);
	}
	public int sendAlarm(AlarmBean bean){
		return sendAlarm(bean.getReceiver(),bean.getType(),bean);
	}
	public HashMap<String,List<AlarmBean>> searchAlarm(String id){
		return this.alarms.get(id);
	}
	public List<AlarmBean> searchAlarm(String id, String type){
		HashMap<String,List<AlarmBean>> alarm=this.searchAlarm(id);
		if(alarm==null){
			return null;
		}
		return alarm.get(type);
	}
	/**
	 * 사용자 수신자의 타입에 한개의 알림에대해 읽었는지 안읽었는지 확인 후 변경
	 * @param id 수신자
	 * @param type 타입
	 * @param bean 알림내용
	 * @return 안읽었다면 읽음으로 바꾸고 1리턴, 바뀐게 없다면 0리턴.
	 */
	public int checkAlarm(String id,String type,AlarmBean bean){
		List<AlarmBean> list=searchAlarm(id,type);
		if(list==null){
			return 0;
		}
		for(int i=0;i<list.size();++i){
			if(list.get(i)==bean){
				bean.setIsRead("true");
				list.set(i,bean);
				return 1;
			}
		}
		return 0;
	}
	/**
	 * 해당 수신자의 타입에 대해 안읽은 알림이 있는지 체크
	 * @param id 수신자
	 * @param type 타입
	 * @return 안읽은 것이 있다면 1리턴.
	 */
	public int checkAlarm(String id,String type){
		List<AlarmBean> list=searchAlarm(id,type);
		if(list==null){
			return 0;
		}
		for(int i=0;i<list.size();++i){
			AlarmBean temp=list.get(i);
			if(temp.getIsRead().equals("false")){
				return 1;
			}
		}
		return 0;
	}
	/**
	 * 전달인자로 받은 수신자에 안읽은 알림이 있는지 모든 타입에 검사
	 * @param id 수신자
	 * @return 바꾼 성공여부
	 */
	public int changeCheckAlarm(String id){
		HashMap<String,List<AlarmBean>> hs=searchAlarm(id);
		if(hs==null){
			return 0;
		}
		Set<String> key=hs.keySet();
		Iterator<String> iter=key.iterator();
		int i=0;
		while(iter.hasNext()){
			changeCheckAlarm(id,iter.next());
		}
		return 1;
	}
	/**
	 * 해당 수신자의 전달받은 타입 목록에 안읽은 알람 목록을 모두 읽음으로 바꾼다.
	 * @param id 수신자
	 * @param type 타입
	 * @return 성공여부
	 */
	public int changeCheckAlarm(String id,String type){
		List<AlarmBean> list=searchAlarm(id, type);
		if(list==null){
			return 0;
		}
		for(int i=0;i<list.size();++i){
			AlarmBean bean=list.get(i);
			if("false".equals(bean.getIsRead())){
				bean.setReceiver("true");
				list.set(i,bean);
			}
		}
		return 1;
	}
	public int searchAlarmCnt(String id,String type){
		List<AlarmBean> list=searchAlarm(id,type);
		if(list==null){
			return -1;
		}
		return list.size();
	}
	public void load(){
		System.out.println("빈로드");
	}
	public void save(){
		System.out.println("빈세이브");
	}
	public void dataTransfer(){
		
	}
	public void updateData(){
		
	}
	@Override
	public String toString() {
		return "AlarmService [alarms=" + alarms + "]";
	}
	
}
