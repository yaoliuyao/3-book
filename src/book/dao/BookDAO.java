package book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import book.bean.Book;
import book.utils.DBHelper;

public class BookDAO {
	public Book get(int id) throws Exception {
		String sql = "select id, bookName, price from book where id = ?";
		System.out.println("要执行的 SQL: " + sql);
		
		try (Connection connection = DBHelper.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, id);
			
			try (ResultSet resultSet = statement.executeQuery()) {
				resultSet.next();
				return new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getFloat(3));
			}
		}
	}
	
	public List<Book> list() throws Exception {
		List<Book> books = new ArrayList<Book>();
		
		String sql = "select id, bookName, price from book";
		try (Connection connection = DBHelper.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()) {
			while(resultSet.next()) {
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				float price = resultSet.getFloat(3);
				
				Book book = new Book(id, name, price);
				books.add(book);
			}
			return books;
		}
	}
	
	public void insert(String bookName, float price) throws Exception {
		String sql = "insert into book (bookname, price) values (?, ?)";
		System.out.println("要执行的语句是: " + sql);
		
		try (Connection connection = DBHelper.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, bookName);
			statement.setFloat(2, price);
			statement.executeUpdate();
		}
	}

	public void update(int id, String bookName, float price) throws Exception {
		String sql = "update book set bookName=?, price=? where id=?";
		System.out.println("要执行的 SQL: " + sql);
		
		try (Connection connection = DBHelper.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			
			statement.setString(1, bookName);
			statement.setFloat(2, price);
			statement.setInt(3, id);
			int rows = statement.executeUpdate();
			
			if (rows == 0) throw new Exception("没有更新到任何语句");
		}
	}
	
	public void delete(int id) throws Exception {
		String sql = "delete  from book where id = ?";
		try (Connection connection = DBHelper.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, id);
			int rows = statement.executeUpdate();
			System.out.println("Delete rows count: " + rows);
		}
	}

	public static void main(String[] args) throws Exception {
		BookDAO bookDAO = new BookDAO();
		System.out.println(bookDAO.get(32).bookName);
	}
}






