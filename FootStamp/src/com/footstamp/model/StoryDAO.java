/*package com.footstamp.model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.datasource.BaseDAO;

public class StoryDAO {
	private static StoryDAO instance = new StoryDAO();
	private HashMap<String,StoryDTO> storys;

	public StoryDAO() {
		this.storys=new HashMap<String,StoryDTO>();
	}
	

	public static StoryDAO getInstance() {
		return instance;
	}


	public static void setInstance(StoryDAO instance) {
		StoryDAO.instance = instance;
	}


	public HashMap<String, StoryDTO> getStorys() {
		return storys;
	}


	public void setStorys(HashMap<String, StoryDTO> storys) {
		this.storys = storys;
	}


	public byte addStory(StoryDTO story){
		String[] res=story.getAll();
		return addStory(res);
		
	}
	public byte addStory(String storyId, String content, String date, byte isOpen, byte isShare, String weather, String location,
			String latitude, String longitude, String writerId, String storyImg){
		byte res = 1;
		BaseDAO bdao = new BaseDAO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = bdao.getConnection();
			pstmt = conn.prepareStatement("insert into story_tb values(?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, storyId);
			pstmt.setString(2, content);
			pstmt.setString(3, date);
			pstmt.setByte(4, isOpen);
			pstmt.setByte(5, isShare);
			pstmt.setString(6, weather);
			pstmt.setString(7, location);
			pstmt.setString(8, latitude);
			pstmt.setString(9, longitude);
			pstmt.setString(10, writerId);
			pstmt.setString(11, storyImg);
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
	public byte addStory(String[] infoList){
		return addStory(infoList[0],infoList[1],infoList[2],Byte.parseByte(infoList[3]),Byte.parseByte(infoList[4]),
				infoList[5],infoList[6],infoList[7],infoList[8],infoList[9],infoList[10]);
	}
	public int deleteStory(String storyId){
		int res=0;
		BaseDAO bdao = new BaseDAO();
		Statement stmt= null;
		Connection con=null;
		try {
			con = bdao.getConnection();
			stmt = con.createStatement();
			res= stmt.executeUpdate("delete from story_tb where story_id='" + storyId+"'");
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
	public int deleteStory(String[] storyId){
		int res=0;
		BaseDAO bdao = new BaseDAO();
		Statement stmt= null;
		Connection con=null;
		try {
			con = bdao.getConnection();
			stmt = con.createStatement();
			for(String str:storyId){
				res= stmt.executeUpdate("delete from story_tb where story_id='" + str+"'");
				res++;
			}
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
	public StoryDTO modifyStory(String storyId, String content, String date, byte isOpen, byte isShare, String weather, String location,
			String latitude, String longitude, String writerId, String storyImg) {
		int res;
		BaseDAO bdao = new BaseDAO();
		StoryDTO dto = null;
		Statement stmt= null;
		Connection con=null;
		try {
			con = bdao.getConnection();
			stmt = con.createStatement();
			res=stmt.executeUpdate("update story_tb set content='"+content+"', day='"+date+"', isOpen="+isOpen+", isShare="+isShare+", weather='"+weather+"', location='"+location+"', latitude='"+latitude+"', longitude='"+longitude+"', writer_id='"+writerId+"', story_img='"+storyImg+"' where story_id='"+storyId+"'");
			if(res!=0){
				dto = new StoryDTO(storyId, content, date, isOpen, isShare, weather, location, latitude, longitude, writerId, storyImg);
			}
		} 
		catch (Exception e) {
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
		return dto;
	}
	public StoryDTO modifyStory(String[] infoList) {
		return modifyStory(infoList[0], infoList[1], infoList[2], Byte.parseByte(infoList[3]), Byte.parseByte(infoList[4]), infoList[5], infoList[6], infoList[7], infoList[8], infoList[9],infoList[10] );
	}
	public StoryDTO modifyStory(String storyId, String[] infoList) {
		return modifyStory(storyId,infoList[0], infoList[1], Byte.parseByte(infoList[2]), Byte.parseByte(infoList[3]), infoList[4], infoList[5], infoList[6], infoList[7], infoList[8], infoList[9]);
	}
	public StoryDTO modifyStory(String storyId, String[] infoList, byte isOpen, byte isShare) {
		return modifyStory(storyId, infoList[0],infoList[1],isOpen, isShare,infoList[2],infoList[3],infoList[4],infoList[5],infoList[6],infoList[7]);
	}
	public StoryDTO modifyStory(String storyId, StoryDTO story){
		String[] res =story.getAll();
		return modifyStory(storyId, res[1], res[2], Byte.parseByte(res[3]), Byte.parseByte(res[4]), res[5], res[6], res[7], res[8], res[9],res[10]);
	}
	public StoryDTO searchStory(String storyId){
		BaseDAO bdao = new BaseDAO();
		StoryDTO dto = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn=bdao.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from story_tb where story_id='" + storyId+"'");
			while (rs.next()) {
					dto = new StoryDTO(rs.getString("story_id"), rs.getString("content"), rs.getString("day"), rs.getByte("isOpen"), rs.getByte("isShare"), rs.getString("weather"), rs.getString("location"),rs.getString("latitude"), rs.getString("longitude"),rs.getString("writer_id"), rs.getString("story_img"));
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
		return dto;
	}
	public ArrayList<StoryDTO> searchStorysByDate(String id, String date){
		BaseDAO bdao = new BaseDAO();
		ArrayList<StoryDTO> dtoList = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn=bdao.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from story_tb where writer_id='"+id+"' and day like '%"+date+"%'");
			while (rs.next()) {
				if(dtoList==null)
					dtoList = new ArrayList<StoryDTO>();
				dtoList.add(new StoryDTO(rs.getString("story_id"), rs.getString("content"), rs.getString("day"), rs.getByte("isOpen"), rs.getByte("isShare"), rs.getString("weather"), rs.getString("location"),rs.getString("latitude"), rs.getString("longitude"),rs.getString("writer_id"), rs.getString("story_img")));
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
		return dtoList;
	}
	public ArrayList<StoryDTO> searchStorysByDate(String id, String startDate, String endDate){
		BaseDAO bdao = new BaseDAO();
		ArrayList<StoryDTO> dtoList = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn=bdao.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from story_tb where writer_id='"+id+"'and day between '"+startDate+"' and '"+endDate+"'");
			while (rs.next()) {
				if(dtoList==null)
					dtoList = new ArrayList<StoryDTO>();
				dtoList.add(new StoryDTO(rs.getString("story_id"), rs.getString("content"), rs.getString("day"), rs.getByte("isOpen"), rs.getByte("isShare"), rs.getString("weather"), rs.getString("location"),rs.getString("latitude"), rs.getString("longitude"),rs.getString("writer_id"), rs.getString("story_img")));
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
		return dtoList;
	}
	public ArrayList<StoryDTO> searchStoryByLocation(String location){
		BaseDAO bdao = new BaseDAO();
		ArrayList<StoryDTO> dtoList = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn=bdao.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from story_tb where location like '%"+location+"%'");
			while (rs.next()) {
				if(dtoList==null)
					dtoList = new ArrayList<StoryDTO>();
				dtoList.add(new StoryDTO(rs.getString("story_id"), rs.getString("content"), rs.getString("day"), rs.getByte("isOpen"), rs.getByte("isShare"), rs.getString("weather"), rs.getString("location"),rs.getString("latitude"), rs.getString("longitude"),rs.getString("writer_id"), rs.getString("story_img")));
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
		return dtoList;
	}
	public ArrayList<StoryDTO> searchStorysById(String id){
		BaseDAO bdao = new BaseDAO();
		ArrayList<StoryDTO> dtoList = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn=bdao.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from story_tb where writer_id='"+id+"'");
			while (rs.next()) {
				if(dtoList==null)
					dtoList = new ArrayList<StoryDTO>();
				dtoList.add(new StoryDTO(rs.getString("story_id"), rs.getString("content"), rs.getString("day"), rs.getByte("isOpen"), rs.getByte("isShare"), rs.getString("weather"), rs.getString("location"),rs.getString("latitude"), rs.getString("longitude"),rs.getString("writer_id"), rs.getString("story_img")));
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
		return dtoList;
	}
	public ArrayList<StoryDTO> searchStorysByLocation(String id, String location){
		BaseDAO bdao = new BaseDAO();
		ArrayList<StoryDTO> dtoList = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn=bdao.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from story_tb where writer_id='"+id+"' and location like '%"+location+"%'");
			while (rs.next()) {
				if(dtoList==null)
					dtoList = new ArrayList<StoryDTO>();
				dtoList.add(new StoryDTO(rs.getString("story_id"), rs.getString("content"), rs.getString("day"), rs.getByte("isOpen"), rs.getByte("isShare"), rs.getString("weather"), rs.getString("location"),rs.getString("latitude"), rs.getString("longitude"),rs.getString("writer_id"), rs.getString("story_img")));
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
		return dtoList;
	}
	public void backUpStorys() {
		this.storys.clear();
		BaseDAO bdao = new BaseDAO();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = bdao.getConnection();
			stmt = conn.createStatement();
			StoryDTO doc = null;
			rs = stmt.executeQuery("select * from story_tb");
			if (rs != null) {
				while (rs.next()) {
					doc = new StoryDTO(rs.getString("story_id"), rs.getString("content"), rs.getString("day"), rs.getByte("isOpen"),
							rs.getByte("isShare"), rs.getString("weather"), rs.getString("location"), rs.getString("latitude"), 
							rs.getString("longitude"), rs.getString("writer_id"),rs.getString("story_img"));
					this.storys.put(rs.getString("story_id"), doc);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				bdao.closeDBObjects(rs, stmt,conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public void saveStory(HashMap<String,StoryDTO> storys){
		this.storys = storys;
		BaseDAO dao = null;
		Connection conn = null;
		Statement stmt = null;
		try {
			dao = new BaseDAO();
			conn = dao.getConnection();
			stmt = conn.createStatement();
			stmt.executeQuery("delete from story_tb");
			Iterator<String> iter = this.storys.keySet().iterator();
			while(iter.hasNext()){
				addStory(this.storys.get(iter.next()));
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
	public HashMap<String,StoryDTO> loadStory(){
		this.storys.clear();
		BaseDAO bdao = new BaseDAO();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = bdao.getConnection();
			stmt = conn.createStatement();
			StoryDTO doc = null;
			rs = stmt.executeQuery("select * from story_tb");
			if (rs != null) {
				while (rs.next()) {
					doc = new StoryDTO(rs.getString("story_id"), rs.getString("content"), rs.getString("day"), rs.getByte("isOpen"),
							rs.getByte("isShare"), rs.getString("weather"), rs.getString("location"), rs.getString("latitude"), 
							rs.getString("longitude"), rs.getString("writer_id"),rs.getString("story_img"));
					this.storys.put(rs.getString("story_id"), doc);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				bdao.closeDBObjects(rs, stmt,conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return this.storys;
	}
}
*/