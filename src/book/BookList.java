package book;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DBHelper;


@WebServlet("/list")
public class BookList extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("进入 list 中，进行购物");
		
		String sql = "select id, bookName, price from book";
		
		List<Book> books = new ArrayList<Book>();
		
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
			
			// 将 books 对象（数据），保存到了 req 的 xxx 上
			req.setAttribute("xxx", books);
			
			// 让 /book/linshigong 所代表的那个 Servlet 去接力处理接下来的逻辑
			req.getRequestDispatcher("/bookList.jsp").forward(req, resp);
		} catch (Exception e) {
			req.setAttribute("message", "Error Ocurred.");
			req.getRequestDispatcher("/bookResult.jsp").forward(req, resp);
		}
	}
}



