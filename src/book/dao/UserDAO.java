package book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import utils.DBHelper;

public class UserDAO {
	public void insert(String url, String ip, String userAgent) throws Exception {
		String sql = "insert into user (url, ip, ua) values (?, ?, ?)";
		try (Connection conn = DBHelper.getConnection();
				PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, url);
			st.setString(2, ip);
			st.setString(3, userAgent);
			st.executeUpdate();
		}
	}
	
	public static void main(String[] args) throws Exception {
		UserDAO logDAO = new UserDAO();
		logDAO.insert("/xxx", "1.2.1.1", "IE");
		System.out.println("≤Â»Î≥…π¶!");
	}
}



