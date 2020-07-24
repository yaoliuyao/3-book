package book.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.bean.Book;

@WebServlet("/linshigong")
public class LinshigongServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		List<Book> books = (List<Book>) req.getAttribute("xxx");

		String html = "";
		
		for (Book book : books) {
			html += "<tr>"
	                 + "<td>" + book.id + "</td>"
	                 + "<td>" + book.bookName + "</td>"
	                 + "<td>" + book.price + "</td>"
	                 + "<td><a href='/book/del?id=" + book.id + "'>删除</a></td>"
	                 + "<td><a href='/book/edit?id=" + book.id + "'>修改</a></td>"
	                 + "</tr>\n";
		}
		 
		html = "<html>"
				+ "<head>"
				+ "<meta charset=utf8>"
				+ "<meta http-equiv=\"Cache-Control\" content=\"no-store\" />"
				+ "<link rel='stylesheet' href='/book/css/xxx.css'>"
				+ "</head>"
				+ "<body>"
				+ "<div>"
				+ "  <form action='/book/add' method='post'>"
				+ "      <input type='text' name='bookName' placeholder='书名'>"
				+ "      <input type='number' name='price' placeholder='价格'>"
				+ "      <button>添加书籍</button>"
				+ "  </form>"
				+ "</div>"
				+ "<table>"
				+ html
				+ "</table></body>"
				+ "</html>";
		resp.getWriter().write(html);
	}
}
