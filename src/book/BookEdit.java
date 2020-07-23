package book;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DBHelper;

@WebServlet("/edit")
public class BookEdit extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		
		if (id == null) {
			req.setAttribute("message", "参数错误");
			req.getRequestDispatcher("/bookResult.jsp").forward(req, resp);
		}

		String sql = "select id, bookName, price from book where id = ?";
		System.out.println("要执行的 SQL: " + sql);
		
		try (Connection connection = DBHelper.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, Integer.parseInt(id));
			
			try (ResultSet resultSet = statement.executeQuery()) {
				resultSet.next();
				
				Book book = new Book(
						resultSet.getInt(1),
						resultSet.getString(2),
						resultSet.getFloat(3));
				
				req.setAttribute("book", book);
				req.getRequestDispatcher("/bookEdit.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			req.setAttribute("message", e.getLocalizedMessage());
			req.getRequestDispatcher("/bookResult.jsp").forward(req, resp);		
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf8");
		
		String id = req.getParameter("id");
		String name = req.getParameter("bookName");
		String price = req.getParameter("price");
		
		if (id == null || name == null || price == null) {
			req.setAttribute("message", "参数不对");
			req.getRequestDispatcher("/bookResult.jsp").forward(req, resp);
		}
		
		String sql = "update book set bookName=?, price=? where id=?";
		System.out.println("要执行的 SQL: " + sql);
		
		try (Connection connection = DBHelper.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			
			statement.setString(1, name);
			statement.setFloat(2, Float.parseFloat(price));
			statement.setInt(3, Integer.parseInt(id));
			int rows = statement.executeUpdate();
			
			if (rows == 0) throw new Exception("没有更新到任何语句");
			
			req.setAttribute("message", "更新成功");
			req.getRequestDispatcher("/bookResult.jsp").forward(req, resp);
		} catch (Exception e) {
			req.setAttribute("message", e.getLocalizedMessage());
			req.getRequestDispatcher("/bookResult.jsp").forward(req, resp);
		}
	}
}





