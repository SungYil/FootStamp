/*package com.footstamp.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.datasource.BaseDAO;

public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();
	private HashMap<String, MemberDTO> members;

	public MemberDAO() {
		members = new HashMap<String, MemberDTO>();
	}

	
	public static MemberDAO getInstance() {
		return instance;
	}


	public static void setInstance(MemberDAO instance) {
		MemberDAO.instance = instance;
	}


	public HashMap<String, MemberDTO> getMembers() {
		return members;
	}

	public void setMembers(HashMap<String, MemberDTO> members) {
		this.members = members;
	}


	public int addMember(MemberDTO member) {
	      BaseDAO dao = new BaseDAO();
	      Connection conn = null;
	      Statement stmt = null;
	      if (member == null)
	         return 0;

	      try {
	         conn = dao.getConnection();
	         stmt = conn.createStatement();
	         // 등록된 데이터가 있을경우
	         stmt.executeUpdate("insert into member values('" + member.getId()
	               + "','" + member.getPwd() + "','" + member.getName()
	               + "','" + member.getCall() + "','" + member.getProfileImg()
	               + "')");
	         dao.closeDBObjects(null, stmt, conn);
	         return 1;
	      } catch (Exception e) {
	         e.printStackTrace();
	         return 0;
	      }
	   }

	  
	public int addMemeber(String id, String pwd, String name, String call,
			String profileImg) {
		return addMember(new MemberDTO(id, pwd, name, call, profileImg));
	   }


	public int addMemeber(String[] infoList) {
		return addMember(new MemberDTO(infoList[0], infoList[1], infoList[2],
				infoList[3], infoList[4]));
	}


	public int deleteMember(String id) {
	
		MemberDTO member = members.get(id);
	    
		if (member == null) {// 성공했으면 널 안나온다.
	    
			return 0;
	
		}

		// members.remove(id);// 여기온거면 존재하는 id이므로 삭제하면된다
		// return 1;
		try {
			BaseDAO dao = new BaseDAO();
	        
			Connection conn = dao.getConnection();
	        
			Statement stmt = conn.createStatement();
	        
			int delete = stmt.executeUpdate("delete member where id='" + id
					+ "'");
			dao.closeDBObjects(null, stmt, conn);
			if (delete == 1)// 이때만 성공한거다
				return 1;
			else
				return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} catch (Exception e) {
	      
			e.printStackTrace();
	   
		}
		return 0;
	}

	public MemberDTO modifyMember(String id, MemberDTO member) {
		if (id.equals(member.getId())) {// 아이디는 바꾸지 않을 경우
			members.put(id, member);
			return modifyAll(id, member.getPwd(), member.getName(),
					member.getCall(), member.getProfileImg());
	      } 
		else {// 아이디 바꿀 경우
			deleteMember(id);// 기존 아이디 정보 제거
			addMember(member);
			return member;
	      }
	   }
	
	public MemberDTO modifyInfo(String id, String type, String changeInfo) {
		MemberDTO member = members.get(id);
		if (member == null) {
			return null;
		}
		// 목록에서 수정?
		if ("name".equals(type))
			member.setName(changeInfo);
		else if ("pwd".equals(type))
			member.setPwd(changeInfo);
		else if ("call".equals(type))
			member.setCall(changeInfo);
		else
			member.setProfileImg(changeInfo);
		try {
			System.out.println("되니?");
			BaseDAO dao = new BaseDAO();
			Connection conn = dao.getConnection();
			Statement stmt = conn.createStatement();
			int num = stmt.executeUpdate("update member set " + type + "='"
					+ changeInfo + "' where id='" + id + "'");
			System.out.println(num);
			dao.closeDBObjects(null, stmt, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	   
	public MemberDTO modifyPwd(String id, String pwd) {
		return modifyInfo(id, "pwd", pwd);
	}

	
	public MemberDTO modifyCall(String id, String call) {
		return modifyInfo(id, "call", call);
	}

	
	public MemberDTO modifyName(String id, String name) {
		return modifyInfo(id, "name", name);
	}

	
	public MemberDTO modifyProfileImg(String id, String profileImg) {
		return modifyInfo(id, "profileImg", profileImg);
	}

	public MemberDTO modifyAll(String id, String pwd, String name, String call,
			String profileImg) {
		MemberDTO member = members.get(id);
		if (member == null)
			return null;
		member.setPwd(pwd);
		member.setName(name);
		member.setCall(call);
		member.setProfileImg(profileImg);
		member.setProfileImg(profileImg);
	      try {
	         System.out.println("되니?");
	         BaseDAO dao = new BaseDAO();
	         Connection conn = dao.getConnection();
	         Statement stmt = conn.createStatement();
	         int num = stmt.executeUpdate("update member set pwd='" + pwd
	               + "',name='" + name + "',call='" + call + "',profileImg='"
	               + profileImg + "' where id='" + id + "'");
	         System.out.println(num);
	         dao.closeDBObjects(null, stmt, conn);
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return member;
	   }

	   public String searchIdByCall(String call) {
	      try {
	         BaseDAO dao = new BaseDAO();
	         Connection conn = dao.getConnection();
	         Statement stmt = conn.createStatement();
	         // 등록된 데이터가 있을경우
	         ResultSet result = stmt
	               .executeQuery("select id from member where call='" + call
	                     + "'");
	         // members = new HashMap<String, MemberDTO>();
	         result.next();
	         String pwd = result.getString("id");
	         dao.closeDBObjects(result, stmt, conn);
	         return pwd;
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return null;
	   }

	   public String searchPwd(String type, String keyword) {
	      try {
	         BaseDAO dao = new BaseDAO();
	         Connection conn = dao.getConnection();
	         Statement stmt = conn.createStatement();
	         // 등록된 데이터가 있을경우
	         ResultSet result = stmt
	               .executeQuery("select pwd from member where " + type + "='"
	                     + keyword + "'");
	         // members = new HashMap<String, MemberDTO>();
	         result.next();
	         String pwd = result.getString("pwd");
	         dao.closeDBObjects(result, stmt, conn);
	         return pwd;
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return null;
	   }

	   public String searchPwdById(String id) {
	      return searchPwd("id", id);
	   }

	   public String searchPwdByCall(String call) {
	      return searchPwd("call", call);
	   }

	   // 완전 일치하는 1개
	   public MemberDTO searchMember(String type, String keyword) {
	      try {
	         BaseDAO dao = new BaseDAO();
	         Connection conn = dao.getConnection();
	         Statement stmt = conn.createStatement();
	         // 등록된 데이터가 있을경우
	         ResultSet result = stmt.executeQuery("select * from member where "
	               + type + "='" + keyword + "'");
	         result.next();
	         String id = result.getString("id");
	         String pwd = result.getString("pwd");
	         String name = result.getString("name");
	         String call = result.getString("call");
	         String profileImg = result.getString("profileImg");
	         dao.closeDBObjects(result, stmt, conn);
	         return new MemberDTO(id, pwd, name, call, profileImg);
	      } catch (Exception e) {
	         System.out.println("설마 여기?..");
	         e.printStackTrace();
	      }
	      return null;
	   }

	   public MemberDTO searchMemberById(String id) {
	      return searchMember("id", id);
	   }

	   public MemberDTO searchMemberByName(String name) {
	      return searchMember("name", name);
	   }

	   public List<MemberDTO> searchmembers(String type, String keyword) {
	      ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
	      Iterator<MemberDTO> iter = members.values().iterator();

	      if ("nameAndId".equals(type)) {
	         while (iter.hasNext()) {
	            MemberDTO member = iter.next();
	            if (member.getId().contains(keyword)
	                  || member.getName().contains(keyword))
	               list.add(member);
	         }
	      } else if ("id".equals(type)) {
	         while (iter.hasNext()) {
	            MemberDTO member = iter.next();
	            if (member.getId().contains(keyword))
	               list.add(member);
	         }
	      } else {
	         while (iter.hasNext()) {
	            MemberDTO member = iter.next();
	            if (member.getName().contains(keyword))
	               list.add(member);
	         }
	      }
	      return Collections.synchronizedList(list);
	   }

	public void saveMember(HashMap<String, MemberDTO> members){
		this.members = members;
		BaseDAO dao = null;
		Connection conn = null;
		Statement stmt = null;
		try {
			dao = new BaseDAO();
			conn = dao.getConnection();
			stmt = conn.createStatement();
			stmt.executeQuery("delete from member");
			Iterator<String> iter = this.members.keySet().iterator();
			while(iter.hasNext()){
				addMember(this.members.get(iter.next()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				dao.closeDBObjects(null, stmt, conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public HashMap<String, MemberDTO> loadMember(){
		this.members.clear();
		try {
			BaseDAO dao = new BaseDAO();
			Connection conn = dao.getConnection();
			Statement stmt = conn.createStatement();
			// 등록된 데이터가 있을경우
			ResultSet result = stmt.executeQuery("select * from member");
			// members = new HashMap<String, MemberDTO>();
			while (result.next()) {
				String id = result.getString("id");
				String pwd = result.getString("pwd");
				String name = result.getString("name");
				String call = result.getString("call");
				String profileImg = result.getString("profileImg");
				members.put(id, new MemberDTO(id, pwd, name, call, profileImg));
			}
			dao.closeDBObjects(null, stmt, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.members;
	}
}
*/