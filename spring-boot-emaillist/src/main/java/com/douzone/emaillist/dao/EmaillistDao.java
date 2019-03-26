package com.douzone.emaillist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.douzone.emaillist.vo.EmaillistVo;

// dao 는 저장과 같은 의미 ==> Repository
// 이도저도 아닌 메일보내는 클래스와 같은 것들을 ~~

@Repository
public class EmaillistDao {

	public boolean insert(EmaillistVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "insert "
						 + "into "
						 	  + "emaillist "
					   + "values(null, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getFirstName());
			pstmt.setString(2, vo.getLastName());
			pstmt.setString(3, vo.getEmail());
			
			int count = pstmt.executeUpdate();
			result = (count == 1);
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { // resource release...
				if(pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public List<EmaillistVo> getList() {
		List<EmaillistVo> list = new ArrayList<EmaillistVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select "
							  + "no, "
							  + "first_name, "
							  + "last_name, "
							  + "email "
						+ "from "
							  + "emaillist "
						+ "order by no desc";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				long no = rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String email = rs.getString(4);
				
				EmaillistVo eVo = new EmaillistVo();
				eVo.setNo(no);
				eVo.setFirstName(firstName);
				eVo.setLastName(lastName);
				eVo.setEmail(email);
				
				list.add(eVo);
				
			}
			
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try { // resource release...
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver loading fail : " + e);
		}
		return conn;
	}
}
