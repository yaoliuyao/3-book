package book.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.bean.Book;
import book.dao.BookDAO;

@WebServlet("/edit")
public class BookEditServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");

		if (id == null) {
			req.setAttribute("message", "参数错误");
			req.getRequestDispatcher("/book/bookResult.jsp").forward(req, resp);
			return;
		}

		try {
			Book book = new BookDAO().get(Integer.parseInt(id));

			req.setAttribute("book", book);
			req.getRequestDispatcher("/book/bookEdit.jsp").forward(req, resp);
		} catch (Exception e) {
			req.setAttribute("message", e.getLocalizedMessage());
			req.getRequestDispatcher("/book/bookResult.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String name = req.getParameter("bookName");
		String price = req.getParameter("price");

		if (id == null || name == null || price == null) {
			req.setAttribute("message", "参数不对");
			req.getRequestDispatcher("/book/bookResult.jsp").forward(req, resp);
			return;
		}

		try {
			BookDAO bookDAO = new BookDAO();
			bookDAO.update(Integer.parseInt(id), name, Float.parseFloat(price));

			req.setAttribute("message", "更新成功");
			req.getRequestDispatcher("/book/bookResult.jsp").forward(req, resp);
		} catch (Exception e) {
			req.setAttribute("message", e.getLocalizedMessage());
			req.getRequestDispatcher("/book/bookResult.jsp").forward(req, resp);
		}
	}
}
