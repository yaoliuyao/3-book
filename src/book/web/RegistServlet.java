package book.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.bean.Account;
import book.dao.AccountDAO;

@WebServlet("/regist")
public class RegistServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/regist/regist.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		String email = req.getParameter("email");
		
		if (username == null || password == null || gender == null || email == null) {
			req.setAttribute("message", "字段不能为空");
			req.getRequestDispatcher("/regist/result.jsp").forward(req, resp);
			return;
		}
		
		try {
			new AccountDAO().insert(new Account(username, password, gender, email));
			req.setAttribute("message", "注册成功");
			req.getRequestDispatcher("/regist/result.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("message", e.getLocalizedMessage());
			req.getRequestDispatcher("/regist/result.jsp").forward(req, resp);
		}
	}
}





