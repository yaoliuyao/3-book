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
			req.setAttribute("message", "��������");
			req.getRequestDispatcher("/bookResult.jsp").forward(req, resp);
		}

		try {
			Book book = new BookDAO().get(Integer.parseInt(id));
			
			req.setAttribute("book", book);
			req.getRequestDispatcher("/bookEdit.jsp").forward(req, resp);
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
			req.setAttribute("message", "��������");
			req.getRequestDispatcher("/bookResult.jsp").forward(req, resp);
		}
		
		try {
			BookDAO bookDAO = new BookDAO();
			bookDAO.update(Integer.parseInt(id), name, Float.parseFloat(price));
			
			req.setAttribute("message", "���³ɹ�");
			req.getRequestDispatcher("/bookResult.jsp").forward(req, resp);
		} catch (Exception e) {
			req.setAttribute("message", e.getLocalizedMessage());
			req.getRequestDispatcher("/bookResult.jsp").forward(req, resp);
		}
	}
}




