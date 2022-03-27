package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static Connection DBconnect() {  // return con
		// DB에 접속정보 저장을 위한 Connection클래스의 객체 con선언
		Connection con = null;

		// DB에 접속할 계정정보
		String user = "KAITE";
		String password = "1111";

		// DB에 접속할 주소정보
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		// String url = "jdbc:oracle:thin:@192.168.0.44:1521:XE";
		// String url = "jdbc:oracle:thin:@121.65.47.77:1522:XE";

		try {
			// (1) 오라클 데이터베이스 드라이버
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// (2) 오라클 데이터베이스 접속정보
			con = DriverManager.getConnection(url, user, password);

			System.out.println("DB접속 성공!");
		} catch (ClassNotFoundException cne) {
			cne.printStackTrace();
			System.out.println("DB접속 실패 : 드라이버 로딩 실패");
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("DB접속 실패 : 접속 정보 오류");
		}

		return con;
	}

}
