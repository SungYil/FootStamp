package com.footstamp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.footstamp.bean.MemberBean;
@Service
public class MemberService {
	
	private Map<String, MemberBean> members;

	public  MemberService() {
		System.out.println("MemberService-default constructor호출");
		members=Collections.synchronizedMap(new HashMap<String,MemberBean> ());
		members.put("jr2900jr",new MemberBean("jr2900jr","qs1933qs","이은호","010-000-0000",""));
		members.put("jr3000jr",new MemberBean("jr3000jr","qs1933qs","황용주","010-000-0000",""));
		members.put("jr3100jr",new MemberBean("jr3100jr","qs1933qs","김예은","010-000-0000",""));
		members.put("jr3200jr",new MemberBean("jr3200jr","qs1933qs","박동익","010-000-0000",""));
		members.put("jr3300jr",new MemberBean("jr3300jr","qs1933qs","이준섭","010-000-0000",""));
		members.put("jr3400jr",new MemberBean("jr3400jr","qs1933qs","박준기","010-000-0000",""));//이건 새메시지 보내기로 확인
		members.put("jr1135jr",new MemberBean("jr1135jr","qs1933qs","이은호","010-000-0000",""));
		members.put("jr1136jr",new MemberBean("jr1136jr","qs1933qs","이은호","010-000-0000",""));
		members.put("jr1137jr",new MemberBean("jr1137jr","qs1933qs","이은호","010-000-0000",""));
		members.put("dkdkenen2006",new MemberBean("dkdkenen2006","qs1933qs","노력","010-000-0000","dkdkenen2006_myimage.jpg"));
		members.put("shfur2006",new MemberBean("shfur2006","qs1933qs","김소미","010-000-0000","shfur2006_myimage.gif"));
	     
		members.put("id1",new MemberBean("id1","qs1933qs","아이듸","010-000-0000","id1_myimage"));
        members.put("id2",new MemberBean("id2","qs1933qs","넘버","010-000-0000","id2_myimage.gif"));
        members.put("id3",new MemberBean("id3","qs1933qs","오란씨","010-000-0000","id3_myimage.jpg"));
        members.put("id4",new MemberBean("id4","qs1933qs","클라씨","010-000-0000","id4_myimage.jpg"));
        members.put("id5",new MemberBean("id5","qs1933qs","모방","010-000-0000","id5_myimage.png"));
        members.put("id6",new MemberBean("id6","qs1933qs","레토","010-000-0000","default_myimage.png"));
        members.put("dkdkenen2006",new MemberBean("dkdkenen2006","ehfoal12","박성일","010-000-0000","shfur2006_myimage.gif"));
        members.put("ehddlrvv",new MemberBean("ehddlrvv","asd7845!","박동익","010-000-0000","shfur2006_myimage.gif"));
	}
	
	public Map<String, MemberBean> getMembers() {
		return members;
	}

	public void setMembers(Map<String, MemberBean> members) {
		this.members = Collections.synchronizedMap(members);
	}
	
	public int addMemberService(MemberBean member){
		if(member==null)
			return 0;
		String id=member.getId();
		if(searchMemberService(id)!=null)//이미 존재하는 아이디일 경우
			return 0;
		members.put(id,member);
		return 1;
	}
	
	public int checkPassword(String id,String pwd){
		MemberBean member=members.get(id);
		if(member==null)//없는 아이디일 경우
			return 2;
		if(pwd==null || pwd.equals("")){//비밀번호가 null,null String일 경우
			return 3;
		}
		
		if(member.getPwd().equals(pwd)){
			return 1;
		}
		return 0;
	}
	
	public String searchId(String call){
		
		if( call==null || call.equals(""))
			return null;
		
		Iterator<MemberBean> list=members.values().iterator();
		while(list.hasNext()){
			MemberBean member=list.next();
			if(member.getCall().equals(call)){
				return member.getId();
			}
		}
		return null;
	}
	
	public String searchPwd(String type,String keyword){
		if( type==null || type.equals(""))//타입 선택 안햇을 경우
			return null;
		
		if(keyword==null || keyword.equals(""))//키워드를 입력안했을 경우
			return null;
		
		Iterator<MemberBean> list=members.values().iterator();
		
		if("id".equals(type)){
			while(list.hasNext()){
				MemberBean member=list.next();
				if(member.getId().equals(keyword)){
					return member.getPwd();
				}
			}
		}
		else{
			while(list.hasNext()){
				MemberBean member=list.next();
				if(member.getCall().equals(keyword)){
					return member.getPwd();
				}
			}
		}
		return null;
	}
	
	public ArrayList<String[]> searchMembersServiceByNameAndId(String keyword){
		if(keyword== null || keyword.equals(""))
			return null;
		
		ArrayList<String[]> result=new ArrayList<String[]> ();
		Iterator<MemberBean> list=members.values().iterator();
		while(list.hasNext()){
			MemberBean member=list.next();
			String name=member.getName();
			String id=member.getId();
			if(name.contains(keyword) || id.contains(keyword) ){
				result.add(new String[]{name,id});
			}
		}
		return result;
	}
	
	public MemberBean searchMemberService(String id){
		return members.get(id);//없을 경우 null
	}
	
	public MemberBean updateMemberService(String id,MemberBean memberInfo){
		if(id==null || id.equals(""))//아이디 미 입력시
			return null;
		if(memberInfo==null)//변경할 정보가 없을 경우
			return null;
		if(!id.equals(memberInfo.getId()))//아이디가 일치하지 않을 경우
			return null;
		
		MemberBean member=searchMemberService(id);
		
		if(member==null)//없는 아이디일 경우
			return null;
		
		members.put(id, memberInfo);//기존에 있던 정보 덮어쓰기(이거 오바같은데..)
		return memberInfo;
	}
	
	public int deleteMemberService(String id){
		
		if(members.remove(id)==null)//삭제 실패할 경우(없는 아이디)
			return 0;
		
		return 1;
	}
	//저장은 일단 서비스 구현다하고 생각하자
	//중간 저장된 파일 불러오기
	public void load(){
		
	}
	//중간 저장용(50명 가입시)
	public void save(){
		
	}
	//디비에서 꺼내오기
	public void dateTransfer(){
		
	}
	//디비에 하루마다 저장
	public void updateDate(){
		
	}
	@Override
	public String toString() {
		return "MemberService [members=" + members + "]";
	}
	
}
