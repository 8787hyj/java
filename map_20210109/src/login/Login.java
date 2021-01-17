package login;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/")
public class Login extends HttpServlet {

	String entry;
	String sRequestFirstName;
	String sRequestLastName;

	RequestDispatcher gRequestDispatcher;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("get");

		gRequestDispatcher = request.getRequestDispatcher("login/loginForm.jsp");
		gRequestDispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("post");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// 클라이언트 IP 주소 가져오기 #1

		String ipAddress = request.getRemoteAddr();
		System.out.println("클라이언트 IP 주소: " + ipAddress);

		// 클라이언트 IP 주소 가져오기 #2

//		String header = request.getHeader("X-Forwarded-For");
//		String ipAddress01 = new StringTokenizer(header, ",").nextToken().trim();
//		System.out.println("클라이언트 IP 주소: " + ipAddress01);

		// 클라이언트 IP 주소 가져오기 #3

//		String ipAddress02 = request.getRemoteAddr();
//		if (ipAddress02.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
//			InetAddress inetAddress = InetAddress.getLocalHost();
//			ipAddress = inetAddress.getHostAddress();
//		}
//		System.out.println("클라이언트IP 주소: " + ipAddress);

		sRequestFirstName = request.getParameter("firstName");
		sRequestLastName = request.getParameter("lastName");
		System.out.println("firstName=" + sRequestFirstName + "lastName=" + sRequestLastName);

//		HttpSession session = request.getSession();
//		session.setAttribute("sessionId", session.getId());
//		String sessionId = (String) session.getAttribute("sessionId");

		Map<String, Map<String, String>> mapSession = new LinkedHashMap<String, Map<String, String>>();
		Map<String, String> mapStruct = new LinkedHashMap<String, String>();

		int nCount = 0;
		String sCount = String.valueOf((nCount));

		mapStruct.put("firstName", sRequestFirstName);
		mapStruct.put("lastName", sRequestLastName);
		mapStruct.put("count", sCount);
		mapSession.put(sRequestFirstName, mapStruct);

//		while (true) {
		try {
			Thread.sleep(3000);

			int nAuth_flag;
			nAuth_flag = -1;
			nAuth_flag = auth_Result(mapSession, mapStruct);
			System.out.println("nAuth_flag=" + nAuth_flag);

			if (nAuth_flag == 0) { // 0 ����
				System.out.println("0 login success");

				gRequestDispatcher = request.getRequestDispatcher("login/loginSuccess.jsp");
				gRequestDispatcher.forward(request, response);

				return;

			} else if (nAuth_flag == 1) { // 1 ����
				System.out.println("1 login fail");

				gRequestDispatcher = request.getRequestDispatcher("login/loginFail.jsp");
				gRequestDispatcher.forward(request, response);

				return;

			} else if (nAuth_flag > 2) {
				System.out.println("3 login fail");
				gRequestDispatcher = request.getRequestDispatcher("login/loginFail.jsp");
				gRequestDispatcher.forward(request, response);

				return;
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

//		} // while

	} // doPost

	public int auth_Result(Map<String, Map<String, String>> mapSession, Map<String, String> mapStruct) {

		int nAuth_flag = -1;
		int nAuth_Count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sFirstName = null;
		String sLastName = null;
		String sAuth_Count;

		try {
			System.out.println("------------------------>Start");
			Iterator<Entry<String, Map<String, String>>> iter = mapSession.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, Map<String, String>> entry = iter.next();
				System.out.println("------------------------->Map Start");
				sFirstName = mapStruct.get("firstName");
				sLastName = mapStruct.get("lastName");
				System.out.println("---->firstName=" + sFirstName);
				System.out.println("---->lastName=" + sLastName);

				if (mapStruct.isEmpty()) {
					System.out.println("data 없음");
				} else {

					String sQuery = "SELECT officeCode FROM employees WHERE firstName = ? OR lastName = ? ";
					conn = DB.connect();
					pstmt = conn.prepareStatement(sQuery);
					pstmt.setString(1, sFirstName);
					pstmt.setString(2, sLastName);
					rs = pstmt.executeQuery();

					// ResultSet 의 MetaData를 가져온다.
					ResultSetMetaData metaData = rs.getMetaData();
					// ResultSet 의 Column의 갯수를 가져온다.
					int sizeOfColumn = metaData.getColumnCount();

					List<Map> list = new ArrayList<Map>();
					Map<String, Object> map;
					String column;

					while (rs.next()) { // ���� true 0

						nAuth_flag = rs.getInt("officeCode");

						// 내부에서 map을 초기화
						map = new HashMap<String, Object>();
						// Column의 갯수만큼 회전
						for (int indexOfcolumn = 0; indexOfcolumn < sizeOfColumn; indexOfcolumn++) {
							column = metaData.getColumnName(indexOfcolumn + 1);
							// map에 값을 입력 map.put(columnName, columnName으로 getString)
							map.put(column, rs.getString(column));
						}
						// list에 저장
						list.add(map);
						System.out.println("list=" + list.toString());

						System.out.println("rs.getInt(officeCode)=" + rs.getInt("officeCode"));

//						mapfirstName.remove(sSession);

						System.out.println("rs nAuth_flag=" + nAuth_flag);

						return nAuth_flag;

					}

//					else { // ���� false 1
//
//						nAuth_Count++;
//
//						if (nAuth_flag < 5) { // ���� false 2 , 3
//
//							sAuth_Count = String.valueOf(nAuth_Count);
////							mapStruct.replace("count", sAuth_Count);
//
//							System.out.println("sAuth_Count=" + sAuth_Count);
//							System.out.println("2 auth_Result fail");
//
//							return nAuth_flag = 2;
//
//						} else {
//
////							mapSession.remove(sSession);
//
//							System.out.println("3 auth_Result fail");
//
//							return nAuth_flag = 3;
//
//						} // ���� 2 , 3
//
//					} // rs.next() 0 ���� , 1 ����

				} // ������ ����

			} // while

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn);

		}

		System.out.println("thread Return =" + nAuth_flag);
		System.out.println("---->firstName=" + sFirstName);
		System.out.println("---->lastName=" + sLastName);
		// ���� 0 , ���� 1 (2,3)
		return nAuth_flag;
	}

}
