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

@WebServlet("/del")
public class BookDelete extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idString = req.getParameter("id");
		
		if (idString == null) {
			req.setAttribute("message", "id should not be null. Delete Action ignored.");
			req.getRequestDispatcher("/bookResult.jsp").forward(req, resp);
		}
		
		String sql = "delete  from book where id = ?";
		try (Connection connection = DBHelper.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, Integer.parseInt(idString));
			int rows = statement.executeUpdate();
			System.out.println("Delete rows count: " + rows);
			
			req.setAttribute("message", "Operation Success!");
			req.getRequestDispatcher("/bookResult.jsp").forward(req, resp);
		} catch (Exception e) {
			req.setAttribute("message", "Error: " + e.getLocalizedMessage());
			req.getRequestDispatcher("/bookResult.jsp").forward(req, resp);
		}
	}
}





