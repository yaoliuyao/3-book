package book.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import book.bean.Account;
import book.dao.AccountDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	@Override
	public void init() throws ServletException {
		System.out.println(">>>> Login 我被实例化");
	}
	@Override
	public void destroy() {
		System.out.println(">>>> Login 我正被销毁");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		if (username == null || password == null) {
			req.setAttribute("message", "字段不能为空");
			req.getRequestDispatcher("/regist/result.jsp").forward(req, resp);
			return;
		}
		
		try {
			Account account = new AccountDAO().check(username, password);
			
			if (account == null) {
				req.getSession().setAttribute("account", null);
				
				req.setAttribute("message", "用户名或者密码错误");
				req.getRequestDispatcher("/login/result.jsp").forward(req, resp);
			} else {
				HttpSession session = req.getSession();
				session.setAttribute("account", account);
				
				req.setAttribute("message", "登录成功!!!");
				req.setAttribute("page", "/list");
				req.getRequestDispatcher("/login/result.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			req.getSession().setAttribute("account", null);
			
			req.setAttribute("message", e.getLocalizedMessage());
			req.getRequestDispatcher("/login/result.jsp").forward(req, resp);
		}
	}
}
