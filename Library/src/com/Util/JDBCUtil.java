package com.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {
	// 鏁寸悊涓�笅閲嶅鐨勪唬鐮�
	// 鍖呰鍓�姝�
	// 1鍔犺浇椹卞姩
	// 2寤虹珛杩炴帴
	// 3鍑嗗sql
	// 4寰楀埌鎵ц瀵硅薄
	public static String sql = "";
	public static String url = "jdbc:mysql://localhost:3306/day0510?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
	public static String user = "root";
	public static String pwd = "root";
	public static Connection con = null;
	public static PreparedStatement ps = null;
	public static ResultSet rs = null;

	static {// 闈欐�浠ｇ爜鍧�
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 闈欐�鏂规硶鏇存柟渚胯皟鐢ㄤ簺
	public static PreparedStatement getPs() {
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ps;
	}

	// 鍏抽棴璧勬簮
	public static void close() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
