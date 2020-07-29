package book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import book.bean.Account;
import book.utils.DBHelper;

public class AccountDAO {
	public Account check (String username, String password) throws Exception {
		String sql = "select id, username, gender, email from account where username = ? and password = ?";
		System.out.println("要执行的语句是: " + sql);
		
		try (Connection connection = DBHelper.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, username);
			statement.setString(2, password);
			try (ResultSet rs = statement.executeQuery()) {
				if (rs.next()) {
					Account account = new Account();
					account.setId(rs.getInt(1));
					account.setUsername(rs.getString(2));
					account.setGender(rs.getString(3));
					account.setEmail(rs.getString(4));
					return account;
				} else {
					return null;
				}
			}
		}
	}
	
    public void insert(Account account) throws Exception {
    	String sql = "insert into account (username, password, gender, email) values (?, ?, ?, ?)";
		System.out.println("要执行的语句是: " + sql);
		
		try (Connection connection = DBHelper.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, account.getUsername());
			statement.setString(2, account.getPassword());
			statement.setString(3, account.getGender());
			statement.setString(4, account.getEmail());
			statement.executeUpdate();
		}
    }
    
    // 可选，做个测试
    public static void main(String[] args) throws Exception {
    	System.out.println(new AccountDAO().check("abc", "123456"));
	}
}





