package book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import book.utils.DBHelper;

public class LogDAO {
	public void insert(String url, String ip, String userAgent) throws Exception {
		String sql = "insert into book_fangwen_rizhi (url, ip, ua) values (?, ?, ?)";
		try (Connection conn = DBHelper.getConnection();
				PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, url);
			st.setString(2, ip);
			st.setString(3, userAgent);
			st.executeUpdate();
		}
	}
	
	public static void main(String[] args) throws Exception {
		LogDAO logDAO = new LogDAO();
		logDAO.insert("/xxx", "1.2.1.1", "IE");
		System.out.println("≤Â»Î≥…π¶!");
	}
}



