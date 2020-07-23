package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/nishishui")
public class NishiYigeServlet extends HttpServlet {
	@Override
	public void init() throws ServletException {
		System.out.println("我正在被初始化，我是 WoshiYigeServlet 类");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().write("hello, world");
	}
}




