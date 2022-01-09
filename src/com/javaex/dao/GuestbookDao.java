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
//  GuestbookDao sql에서 가져온 쿼리문을 자바에 적용시킴 list insert select update delete 존재함
//	GuestbookVo (valueObject 오브젝트클래스) 필드 생성자 메소드gs 투스트링 존재
//	addList.jsp 메인html
//	add.jsp 실제 등록 기능 가진 페이지..얘를 거치고 addList로 리다이렉션함
//	deleteForm.jsp 유저가 비번치는 화면
//	delete.jsp 실제 지우는 기능 가진 페이지..얘를 거치고 addList로 리다이렉션함

	
	// 0. import java.sql.*;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//필드
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	
	//생성자
	
	//메소드gs
	
	//메소드일반		
	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	public void getclose() {
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
	
	
	////////////////////////////////////////////
	//list
	////////////////////////////////////////////
	//가져올때 <>generic써줘야함
	//리스트값을 바깥으로 보내줘야해서 get과 return이 필요함
			//타입:리스트		//name	
	public List<GuestbookVo> getList(){ //get이 있으면 리턴
		
		List<GuestbookVo> guestbookList = new ArrayList<GuestbookVo>();//바깥으로 보내는 값
		
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
		
	}
	
	
	
////////////////////////////////////////////
//insert --> 특정 no 출력
////////////////////////////////////////////
	public GuestbookVo insert(GuestbookVo guestVo) {
		
		GuestbookVo guestbookvo = new GuestbookVo();
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
			System.out.println("로딩완료");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
			query += " INSERT INTO guestbook ";
			query += " VALUES (seq_person_id.nextval, ?, ?, ?, SYSDATE) ";
			// System.out.println(query);

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			// 바인딩(물음표넣어주기)
			pstmt.setString(1, guestVo.getName()); // ?(물음표) 중 1번째, 순서중요
			pstmt.setString(2, guestVo.getPassword()); // ?(물음표) 중 2번째, 순서중요
			pstmt.setString(3, guestVo.getContent()); // ?(물음표) 중 3번째, 순서중요
			
			int count = pstmt.executeUpdate(); //실행만 쿼리,나머지는 업데이트
				
			// 4.결과처리
//			int no = rs.getInt("no"); 
//			String name = rs.getString("name");
//			String password = rs.getString("password");
//			String content = rs.getString("content");
//			String regDate = rs.getString("reg_date");
//			guestbookvo = new GuestbookVo(no, name, password, content, regDate);
//			
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

		
			return guestbookvo;
			
	}
	
	

////////////////////////////////////////////
//select --> 특정 no 출력
////////////////////////////////////////////
	public GuestbookVo select(int index) {
		GuestbookVo guestbookVo = null;
		
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행 --> 완성된 sql문을 가져와서 작성할것
			String query = "";
			query += " SELECT no, ";
			query += " 		  name, ";
			query += " 		  password, ";
			query += " 		  content, ";
			query += " 		  to_char(reg_date, 'YYYY/MM/DD AMHH:MI:SS') reg_date ";
			query += " FROM guestbook ";
			query += " WHERE no like ? ";

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setInt(1, index); // ?(물음표) 중 1번째, 순서중요
			
			rs = pstmt.executeQuery();

			// 4.결과처리
			while(rs.next()) {
				
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String reg_date = rs.getString("reg_date");
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		getclose();
		return guestbookVo;

	}

	
////////////////////////////////////////////
//delete
////////////////////////////////////////////

	public int delete(int index) {
		
		int count = 0;
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
			String query = ""; // 쿼리문 문자열만들기, ? 주의
	         query += " delete guestbook ";
	         query += " where no = ? ";
	         // System.out.println(query);

	         pstmt = conn.prepareStatement(query); // 쿼리로 만들기

	         pstmt.setInt(1, index); // ?(물음표) 중 1번째, 순서중요
	      

	         count = pstmt.executeUpdate(); 
	
			// 4.결과처리

				
//				int no = rs.getInt("no");
//				String name = rs.getString("name");
//				String password = rs. getString("password");
//				String content = rs.getString("content");
//				String regDate = rs.getString("reg_date");
//				guestbookvo = new Guestbookvo(no, name, password, content, regDate);
//				
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

	
		return count;
	
	
	}
		
}

