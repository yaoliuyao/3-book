package book.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.dao.BookDAO;

@WebServlet("/add")
public class BookAddServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("bookName");
		String price = req.getParameter("price");

		if (name == null || price == null) {
			req.setAttribute("message", "参数不对");
			req.getRequestDispatcher("/book/bookResult.jsp").forward(req, resp);
			return;
		}

		try {
			BookDAO bookDAO = new BookDAO();
			bookDAO.insert(name, Float.parseFloat(price));

			req.setAttribute("message", "保存成功");
			req.getRequestDispatcher("/book/bookResult.jsp").forward(req, resp);
		} catch (Exception e) {
			req.setAttribute("message", e.getLocalizedMessage());
			req.getRequestDispatcher("/book/bookResult.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("message", "I don't know what to do");
		req.getRequestDispatcher("/book/bookResult.jsp").forward(req, resp);
	}
}
