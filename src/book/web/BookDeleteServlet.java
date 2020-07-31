package book.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.dao.BookDAO;

@WebServlet("/del")
public class BookDeleteServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idString = req.getParameter("id");

		if (idString == null) {
			req.setAttribute("message", "id should not be null. Delete Action ignored.");
			req.getRequestDispatcher("/book/bookResult.jsp").forward(req, resp);
			return;
		}

		try {
			BookDAO bookDAO = new BookDAO();
			bookDAO.delete(Integer.parseInt(idString));

			req.setAttribute("message", "Operation Success!");
			req.getRequestDispatcher("/book/bookResult.jsp").forward(req, resp);
		} catch (Exception e) {
			req.setAttribute("message", "Error: " + e.getLocalizedMessage());
			req.getRequestDispatcher("/book/bookResult.jsp").forward(req, resp);
		}
	}
}
