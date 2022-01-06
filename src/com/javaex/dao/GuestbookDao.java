package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestbookVo;

public class GuestbookDao {
//쿼리
	
	//필드
	
	
	//생성자
	
	//메소드gs
	
	//메소드일반
	//list는 가져올때 <>generic써줘야함
	//value object
	public List<GuestbookVo> getList(){//get이 있으면 리턴해줘야지
		
		List<GuestbookVo> guestbookList = new ArrayList<GuestbookVo>();//마지막에 이거 리턴함
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		    // 3. SQL문 준비 / 바인딩 / 실행
		    //문자열
			String query = "";
			query += " select no, ";
			query += "        name, ";
			query += "        password, ";
			query += "        content,";
			query += "        to_char(reg_date, 'YYYY-MM-DD AMHH:MI:SS') reg_date";
			query += " from guestbook ";
			query += " order by reg_date asc ";
		    
			System.out.println(query + "쿼리 출력 테스트");
			
			//쿼리
			pstmt = conn.prepareStatement(query);
			
			//바인딩(물음표맞추기)--지금은 없음
			
			//실행
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			while(rs.next()){
				int no = rs.getInt("no"); 
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String regDate = rs.getString("reg_date");
				
				GuestbookVo guestbookVo = new GuestbookVo(no, name, password, content, regDate);//생성자 순으로 묶어준다음 리스트 모양으로 만들기
				guestbookList.add(guestbookVo);//리스트모양만든거 추가해줌
			}
			
			
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		        if (rs != null) {
		            rs.close();
		        }                
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }

		}
		return guestbookList;
		
	} //getList()
}
