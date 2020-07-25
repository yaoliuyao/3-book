package book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import book.bean.Account;
import utils.DBHelper;

public class AccountDAO {
    public void insert(Account account) throws Exception {
    	String sql = "insert into account (username, password, gender, email) values (?, ?, ?, ?)";
		System.out.println("Ҫִ�е������: " + sql);
		
		try (Connection connection = DBHelper.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, account.getUsername());
			statement.setString(2, account.getPassword());
			statement.setString(3, account.getGender());
			statement.setString(4, account.getEmail());
			statement.executeUpdate();
		}
    }
    
    // ��ѡ����������
    public static void main(String[] args) throws Exception {
		Account account = new Account("abc", "123456", "��", "bunanbunv@younanyounv.com");
		new AccountDAO().insert(account);
	}
}

