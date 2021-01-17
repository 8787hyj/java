package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

	public static Connection connect() {

		Connection con = null;

		try {

			String sDriverName = "org.mariadb.jdbc.Driver";
			String sUrl = "jdbc:mariadb://211.187.165.254:5537/classicmodels";
			String sUser = "root";
			String sPwd = "otroot";
//			String sUrl = "jdbc:mariadb://192.168.0.43:5537/";
//			String sUser = "otroot";
//			String sPwd = "otroot0224";

			Class.forName(sDriverName);
			con = DriverManager.getConnection(sUrl, sUser, sPwd);
			System.out.println("connection success");

		} catch (ClassNotFoundException e) {
			System.out.println("Exception: DriverManager fail :" + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Exception: connection fail :" + e.getMessage());
			e.printStackTrace();
		}
		return con;

	}
}
