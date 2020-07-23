package book;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DBHelper;

@WebServlet("/add")
public class BookAdd extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("bookName");
		String price = req.getParameter("price");
		
		if (name == null || price == null) {
			req.setAttribute("message", "参数不对");
			req.getRequestDispatcher("/bookResult.jsp").forward(req, resp);
		}
		
		String sql = "insert into book (bookname, price) values (?, ?)";
		System.out.println("要执行的语句是: " + sql);
		try (Connection connection = DBHelper.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, name);
			statement.setFloat(2, Float.parseFloat(price));
			statement.executeUpdate();
			
			req.setAttribute("message", "保存成功");
			req.getRequestDispatcher("/bookResult.jsp").forward(req, resp);
		} catch (Exception e) {
			req.setAttribute("message", e.getLocalizedMessage());
			req.getRequestDispatcher("/bookResult.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("message", "I don't know what to do");
		req.getRequestDispatcher("/bookResult.jsp").forward(req, resp);
	}
}








