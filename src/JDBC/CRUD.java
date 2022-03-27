package JDBC;

//SQL :  쿼리문 , 데이터베이스에 저장된 값을 불러내는 방법 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUD {

	// CRUD
	// C : CREATE(생성)
	// R : READ(조회)
	// U : UPDATE(수정)
	// D : DELETE(삭제)

	
	// 데이터 기본타입 : 문자형 , 정수형 , 실수형 , 논리형
	// char int double boolean
	// String, Scanner ,Connection , ResultSet , CRUD 등등은.. 참조형 데이터타입이다.
	// 참조형 데이터타입의 기본값은 " null "
	Connection con = null;
	// DB에 접속하기 위한 객체
	Statement stmt = null;
	// SQL문을 사용하기 위한 객체
	ResultSet rs = null;
	// SQL문을 조회된 내용을 저장하는 객체

	

	// [1] 준비단계 : con(먼저선언), stmt(나중) => try ,catch문
	// [2] 작성단계 : sql
	// [3] 실행단계 : execute : CUD (int타입 executeUpdate) , R(ResultSet 타입 executeQuery)
	// [4] 확인단계 : 검사할때만 while, if
	// [5] 해제단계 : close
	
	
	public void insert1() {  // 반환값 x
		try {
			// 준비단계
			this.con = DBConnection.DBconnect();
			this.stmt = this.con.createStatement();

			// SQL문 작성
			String sql = "INSERT INTO JAVASQL VALUES('JAVA3', 'SQL3')";

			// SQL문 실행
			int result = this.stmt.executeUpdate(sql);

			// 실행결과 확인
			System.out.println("결과 : " + result);
			if (result > 0) {
				System.out.println("insert success!");
			} else {
				System.out.println("insert fail!");
			}

			// stmt문과 DB접속 해제
			this.stmt.close();
			this.con.close();
		} catch (SQLException var3) {
			var3.printStackTrace();
		}

	}

	public void insert() {
		try {
			
			// [1] 준비단계
			this.con = DBConnection.DBconnect();
			this.stmt = this.con.createStatement();
			
			// [2] 작성단계
			String sql = "INSERT INTO NAVER VALUES('아이디3', '패스워드', '이름', 24, '여', 'EMAIL@NAVER.COM', '010-1234-5678')";
			
			// [3] 실행단계
			int result = this.stmt.executeUpdate(sql);
			
			// [4] 확인단계
			System.out.println("결과 : " + result);
			if (result > 0) {
				System.out.println("네이버 회원가입 성공!");
			} else {
				System.out.println("회원가입 실패!");
			}
			// [5] 해제단계
			this.stmt.close();
			this.con.close();
		} catch (SQLException var3) {
			var3.printStackTrace();
		} 
	}
	// 회원정보 조회 SELECT
		public void select() {
			
			try {
				
				this.con = DBConnection.DBconnect();
				this.stmt = this.con.createStatement();
				String sql = "SELECT * FROM NAVER";
				// sql에서 조회한 정보를 rs객체에 담는다.
				this.rs = this.stmt.executeQuery(sql);
				
				// 튜플(데이터 레코드 )갯수 만큼 반복문
				while (this.rs.next()) {
					// rs.next() : 데이터 타입은 boolean, 
					//레코드가 존재할 경우 true 
					//더이상 존재하지 않을 경우 false, 반복문을 종료
					System.out.println("아이디 : " + this.rs.getString(1));
					System.out.println("패스워드 : " + this.rs.getString(2));
					System.out.println("이름 : " + this.rs.getString(3));
					System.out.println("나이 : " + this.rs.getInt(4));
					// number타입으로 가져왔기 때문에 getInt로 함.
					System.out.println("성별 : " + this.rs.getString(5));
					System.out.println("이메일 : " + this.rs.getString(6));
					System.out.println("전화번호 : " + this.rs.getString(7));
					System.out.println();
				}

				this.stmt.close();
				this.con.close();
			} catch (SQLException var2) {
				var2.printStackTrace();
			}

		}

		// 회원정보 수정 : UPDATE 
		public void update() {
			try {
				// 문제가 없으면 try문 실행 ,catch문 실행x
				this.con = DBConnection.DBconnect();
				this.stmt = this.con.createStatement();
				String sql = "UPDATE NAVER SET NAGE=15 WHERE NID='아이디3'";
				int result = this.stmt.executeUpdate(sql);
				if (result > 0) {
					System.out.println("update success!");
				} else {
					System.out.println("update fail!");
				}
				
				this.stmt.close();
				this.con.close();
				
			} catch (SQLException var3) { // 문제가 생기면 try문 실행 X ,catch문 실행
				var3.printStackTrace();
			}finally {//문제가 생기건 , 문제가 생기지 않건 ,  무조건 실행
				// 여기원래 없나?
			}

		}
		

		
		// 회원정보 삭제 DELDTE()
		public void delete() {
			try {  // catch문 해당할 수도 있으므로 try-catch문을 사용
				
				//[1] 준비단계 
				this.con = DBConnection.DBconnect();
				this.stmt = this.con.createStatement();  
				
				//[2] 작성단계 
				String sql = "DELETE FROM NAVER WHERE NID='아이디3'";
				
				//[3]실행단계 :CUDl(int 타입 executeUpdate) , R(ResultSet타입 exrcuteQuery)
				//여기서 result의 이름은 아무거나 써줘도 됨
				int result = this.stmt.executeUpdate(sql);
				System.out.println("결과 : " + result);
				
				// [4] 확인단계 : while, if
				if (result > 0) {
					System.out.println("DELETE SUCCESS!");
				} else {
					System.out.println("DELETE FAIL!");
				}
				
				// [5] 해제단계 : close 나중에 쓴 애를 먼저, 먼저쓴애를 나중에 닫아준다.
				// 이 단계를 안해도 실행은 되지만 안쓴다면 계속 접속이되어 메모리에 지장이 갈 수 있다.
				this.stmt.close();
				this.con.close();
			} catch (SQLException var3) {
				var3.printStackTrace();
			}

		}
	}