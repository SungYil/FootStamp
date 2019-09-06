/*package com.footstamp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.AllocationExpression;

import com.datasource.BaseDAO;


public class TagNameDAO {
	private static TagNameDAO instance = new TagNameDAO();
	private ArrayList<TagNameDTO> tags;
	public TagNameDAO(){
		this.tags=new ArrayList<TagNameDTO>();
	}
	
	public static TagNameDAO getInstance() {
		return instance;
	}

	public static void setInstance(TagNameDAO instance) {
		TagNameDAO.instance = instance;
	}
	

	public ArrayList<TagNameDTO> getTags() {
		return tags;
	}

	public void setTags(ArrayList<TagNameDTO> tags) {
		this.tags = tags;
	}
	
	public int confirmTag(String storyId, String tagName){
		int res=0;
		BaseDAO bdao = new BaseDAO();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn=bdao.getConnection();
			 stmt = conn.createStatement();
			rs = stmt.executeQuery("select tag_name from tag_name_tb where story_id='" + storyId+"'");
			while (rs.next()) {
					if(tagName.equals(rs.getString("tag_name")))
							res=1;
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				bdao.closeDBObjects(rs, stmt, conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}
	public ArrayList<String> searchStorysByTagName(String tagName){
		ArrayList<String> res=null;
		BaseDAO bdao = new BaseDAO();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn=bdao.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select distinct story_id from tag_name_tb where tag_name='" + tagName+"'");
			while (rs.next()) {
				if(res==null)
					res = new ArrayList<String>();
				res.add(rs.getString("story_id"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				bdao.closeDBObjects(rs, stmt, conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}
	public int addTag(String storyId, String tag){
		byte res = 1;
		BaseDAO bdao = new BaseDAO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = bdao.getConnection();
			pstmt = conn.prepareStatement("insert into tag_name_tb values(?,?)");
			pstmt.setString(1, storyId);
			pstmt.setString(2, tag);
			pstmt.executeUpdate();

		} catch (Exception e) {
			res = 0;
			e.printStackTrace();
		}
		finally{
			try {
				bdao.closeDBObjects(null, pstmt, conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}
	public int addTags(String storyId, String[] tags){
		byte res = 1;
		BaseDAO bdao = new BaseDAO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = bdao.getConnection();
			for(String ary:tags){
				pstmt = conn.prepareStatement("insert into tag_name_tb values(?,?)");
				pstmt.setString(1, storyId);
				pstmt.setString(2, ary);
				pstmt.executeUpdate();	
			}

		} catch (Exception e) {
			res = 0;
			e.printStackTrace();
		}
		finally{
			try {
				bdao.closeDBObjects(null, pstmt, conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}
	public TagNameDTO modifyTag(String storyId, String content){
		return null;
	}
	public TagNameDTO modifyTag(String storyId, TagNameDTO tagName){
		return null;
	}
	public int deleteTag(String storyId, String tagName){
		int res=0;
		BaseDAO bdao = new BaseDAO();
		Statement stmt= null;
		Connection con=null;
		try {
			con = bdao.getConnection();
			stmt = con.createStatement();
			res= stmt.executeUpdate("delete from tag_name_tb where story_id='" + storyId+"' and tag_name='"+tagName+"'");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				bdao.closeDBObjects(null, stmt, con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}
	public int deleteAllTags(String storyId){
		int res=0;
		BaseDAO bdao = new BaseDAO();
		Statement stmt= null;
		Connection con=null;
		try {
			con = bdao.getConnection();
			stmt = con.createStatement();
			res= stmt.executeUpdate("delete from tag_name_tb where story_id='" + storyId+"'");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				bdao.closeDBObjects(null, stmt, con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}
	public ArrayList<TagNameDTO> searchByStory(String storyId){
		ArrayList<TagNameDTO> res=null;
		BaseDAO bdao = new BaseDAO();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn=bdao.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from tag_name_tb where story_id='" + storyId+"'");
			while (rs.next()) {
				if(res==null)
					res = new ArrayList<TagNameDTO>();
				res.add(new TagNameDTO(rs.getString("story_id"), rs.getString("tag_name")));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				bdao.closeDBObjects(rs, stmt, conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}
	// 실시간으로 접근하는건 불러오는 메소드 구현
	public void load() {
		
	}
	
	public void saveTagName(List<TagNameDTO> tags){
		this.tags = (ArrayList<TagNameDTO>)tags;
		BaseDAO dao = null;
		Connection conn = null;
		Statement stmt = null;
		try {
			dao = new BaseDAO();
			conn = dao.getConnection();
			stmt = conn.createStatement();
			// 있던 테이블 날리기
			stmt.executeQuery("delete from tag_name_tb");
			for(TagNameDTO dto:this.tags){
				stmt.executeUpdate("insert into tag_name_tb values('"+dto.getStoryId()+"','"+dto.getTag()+"')");
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
	public List<TagNameDTO> loadTagName(){
		this.tags.clear();
		BaseDAO dao = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet result = null;
		try {
			dao = new BaseDAO();
			conn = dao.getConnection();
			stmt = conn.createStatement();
			// 등록된 데이터가 있을경우
			result = stmt.executeQuery("select * from tag_name_tb");
			// members = new HashMap<String, MemberDTO>();
			while (result.next()) {
				this.tags.add(new TagNameDTO(result.getString("story_id"),result.getString("tag_name")));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				dao.closeDBObjects(result, stmt, conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return this.tags;
	}
}	
*/