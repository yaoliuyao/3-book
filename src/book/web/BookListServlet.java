package book.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.bean.Book;
import book.dao.BookDAO;

@WebServlet("/list")
public class BookListServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("进入 list 中，进行购物");

		try {
			List<Book> books = new BookDAO().list();

			// 将 books 对象（数据），保存到了 req 的 xxx 上
			req.setAttribute("xxx", books);

			// 让 /book/linshigong 所代表的那个 Servlet 去接力处理接下来的逻辑
			req.getRequestDispatcher("/book/bookList.jsp").forward(req, resp);
		} catch (Exception e) {
			req.setAttribute("message", "Error Ocurred.");
			req.getRequestDispatcher("/book/bookResult.jsp").forward(req, resp);
		}
	}
}
