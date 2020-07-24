package book.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import book.dao.LogDAO;

@WebFilter("/*")
public class FangwenRizhiFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 所有跟请求相关的数据，都被 tomcat 封装到了 request 对象
		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getRequestURI();
		String ip = req.getRemoteAddr();
		String userAgent = req.getHeader("user-agent");
		
		// 便于开发的时候查看结果
		System.out.println("url: " + url + " up: " + ip + " userAgent: " + userAgent);
		
		// 将相关数据插入到数据
		LogDAO logDAO = new LogDAO();
		try {
			logDAO.insert(url, ip, userAgent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 继续
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	@Override
	public void destroy() {
	}
}
