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


public class ReviewDAO {
	private static ReviewDAO instance = new ReviewDAO();
	private HashMap<String,ReviewDTO> reviews;
	public ReviewDAO() {
		this.reviews = new HashMap<String, ReviewDTO>();
	}
	
	public static ReviewDAO getInstance() {
		return instance;
	}

	public static void setInstance(ReviewDAO instance) {
		ReviewDAO.instance = instance;
	}

	public HashMap<String, ReviewDTO> getReviews() {
		return reviews;
	}
	public void setReviews(HashMap<String, ReviewDTO> reviews) {
		this.reviews = reviews;
	}
	
	public byte addReview(ReviewDTO review){
		String[] arry = review.getAll();
		return addReview(arry[0],arry[1],Byte.parseByte(arry[2]),arry[3],arry[4],Byte.parseByte(arry[5]),arry[6]);
	}
	public byte addReview(String reviewId, String content, byte isLike, String date, String writerId, byte isStory, String bulletinId){
		byte res = 1;
		BaseDAO bdao = new BaseDAO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = bdao.getConnection();
			pstmt = conn.prepareStatement("insert into review_tb values(?,?,?,?,?,?,?)");
			pstmt.setString(1, reviewId);
			pstmt.setString(2, content);
			pstmt.setByte(3, isLike);
			pstmt.setString(4, date);
			pstmt.setString(5, writerId);
			pstmt.setByte(6, isStory);
			pstmt.setString(7, bulletinId);
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
	public byte addReview(String[] infoList){
		return addReview(infoList[0],infoList[1],Byte.parseByte(infoList[2]),infoList[3],infoList[4], Byte.parseByte(infoList[5]),infoList[6]);
	}
	public int addReviewList(ReviewDTO[] review){
		return 1;//나중에 구현
	}
	@SuppressWarnings("null")
	public HashMap<String,ReviewDTO> searchByWriterId(String writerId){
		HashMap<String,ReviewDTO> map = null;
		BaseDAO bdao = new BaseDAO();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String reviewId=null;
		try {
			conn=bdao.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from review_tb where writer_id='"+writerId+"'");
			while (rs.next()) {
				if(map==null)
					map = new HashMap<String, ReviewDTO>();
				map.put(reviewId, new ReviewDTO(reviewId, rs.getString("content"), rs.getByte("isLike"), rs.getString("day"), rs.getString("writer_id"), rs.getByte("isStory"),rs.getString("bulletin_id")));
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
		return map;
	}
	public ReviewDTO searchByReviewId(String reviewId){
		BaseDAO bdao = new BaseDAO();
		ReviewDTO dto = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn=bdao.getConnection();
			 stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from review_tb where review_id='" + reviewId+"'");
			while (rs.next()) {
					dto = new ReviewDTO(rs.getString("review_id"), rs.getString("content"), rs.getByte("isLike"), rs.getString("day"), rs.getString("writer_id"), rs.getByte("isStory"), rs.getString("bulletin_id"));
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
	public ArrayList<ReviewDTO> searchByBulletinId(String bulletinId){
		BaseDAO bdao = new BaseDAO();
		ArrayList<ReviewDTO> list = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn=bdao.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from review_tb where bulletin_id='" + bulletinId+"'");
			while (rs.next()) {
				if(list!=null){
					list = new ArrayList<ReviewDTO>();
				}
				list.add(new ReviewDTO(rs.getString("review_id"), rs.getString("content"), rs.getByte("isLike"), rs.getString("day"), rs.getString("writer_id"), rs.getByte("isStory"),rs.getString("bulletin_id")));
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
		return list;
	}
	public int deleteByReviewId(String reviewId){
		int res = 1;
		BaseDAO bdao = new BaseDAO();
		Statement stmt= null;
		Connection con=null;
		try {
			con = bdao.getConnection();
			stmt = con.createStatement();
			res=stmt.executeUpdate("delete from review_tb where review_id='" + reviewId+"'");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res = 0;
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
	public int deleteAllByBulletinId(String bulletinId){
		int res = 1;
		BaseDAO bdao = new BaseDAO();
		Statement stmt= null;
		Connection con=null;
		try {
			con = bdao.getConnection();
			stmt = con.createStatement();
			res=stmt.executeUpdate("delete from review_tb where bulletin_id='" + bulletinId+"'");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res = 0;
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
	public int deleteByWriterId(String writerId){
		int res = 1;
		BaseDAO bdao = new BaseDAO();
		Statement stmt= null;
		Connection con=null;
		try {
			con = bdao.getConnection();
			stmt = con.createStatement();
			res=stmt.executeUpdate("delete from review_tb where writer_id='" + writerId+"'");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res = 0;
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
	public ReviewDTO modifyComment(String content, String reviewId){
		int res;
		BaseDAO bdao = new BaseDAO();
		ReviewDTO dto = null;
		Statement stmt= null;
		Connection con=null;
		try {
			con = bdao.getConnection();
			stmt = con.createStatement();
			res=stmt.executeUpdate("update review_tb set content='"+content+"' where review_id='"+reviewId+"'");
			if(res!=0){
				dto = searchByReviewId(reviewId);
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
	public int getReviewCount(byte isLike, String bulletinId){
		int count=0;
		BaseDAO bdao = new BaseDAO();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn=bdao.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from review_tb where bulletin_id='"+bulletinId+"' and isLike='"+isLike+"'");
			while (rs.next()) {
					count++;
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
		return count;
	}
	public ArrayList<String> getWriterList(String bulletinId){
		ArrayList<String> res = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		BaseDAO bdao = new BaseDAO();
		try {
			conn=bdao.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select distinct writer_id from review_tb where isLike=1 and bulletin_id='"+bulletinId+"'");
			while (rs.next()) {
				if(res==null)
					res = new ArrayList<String>();
				res.add(rs.getString("writer_id"));
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
	@Override
	public String toString() {
		return "ReviewDAO [reviews=" + reviews + "]";
	}
	public void saveReview(HashMap<String,ReviewDTO> reviews){
		this.reviews = reviews;
		BaseDAO dao = null;
		Connection conn = null;
		Statement stmt = null;
		try {
			dao = new BaseDAO();
			conn = dao.getConnection();
			stmt = conn.createStatement();
			stmt.executeQuery("delete from review_tb");
			Iterator<String> iter = this.reviews.keySet().iterator();
			while(iter.hasNext()){
				addReview(this.reviews.get(iter.next()));
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
	public HashMap<String,ReviewDTO> loadReview(){
		this.reviews.clear();
		BaseDAO bdao = new BaseDAO();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = bdao.getConnection();
			stmt = conn.createStatement();
			ReviewDTO doc = null;
			rs = stmt.executeQuery("select * from review_tb");
			if (rs != null) {
				while (rs.next()) {
					doc = new ReviewDTO(rs.getString("review_id"), rs.getString("content"), rs.getByte("isLike"), rs.getString("day"), rs.getString("writer_id"), rs.getByte("isStory"), rs.getString("bulletin_id"));
					this.reviews.put(rs.getString("review_id"), doc);
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
		return this.reviews;
	}
}
*/