<%@ page import="book.bean.Account"%>
<%@ page import="book.bean.Book"%>
<%@      page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"  %>
	
<%
	List<Book> books = (List<Book>) request.getAttribute("xxx");
	pageContext.setAttribute("yigeshu", "33333");
%>
	
<html>
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" href="css/xxx.css">
	<style>
		form {
			padding-bottom: 1em;
			width: 98%;
			display: flex;
			justify-content: space-between;
		}
		header {
			display: grid;
			grid-template-columns: 1fr auto;
		}
		header span {
			padding: 1em 2em;
		}
	</style>
</head>

<body>
	<header>
		<h3>163 书籍管理系统</h3>
		<span>
		<%
			Object o = session.getAttribute("account");
		
			if (o == null) {
				out.print("<a href='/book/login'>登录</a>");
			} else {
				Account account = (Account) o;
				out.println("欢迎您 " 
					+ account.getUsername() 
					+ " (" + account.getGender() + ")<a href='/book/logout'>  注销</a>");
			}
		%>
		</span>
	</header>
	
	<% if (session.getAttribute("account") != null) { %>
	<div>
		<form action='/book/add' method='post'>
			<input type='text' name='bookName' placeholder='书名'>
			<input type='number' name='price' placeholder='价格'>
			<button>添加书籍</button>
		</form>
	</div>
	<% } %>
	
	<table>
		<tr>
			<th>编号</th>
			<th>书名</th>
			<th>价格</th>
			
			<% if (session.getAttribute("account") != null) { %>
			<th>操作</th>
			<% } %>
		</tr>
		
		<% for (Book book : books) { %>
		<tr>
			<td><%= book.id %></td>
			<td><%= book.bookName %></td>
			<td><%= book.price %></td>
			
			<% if (session.getAttribute("account") != null) { %>
			
			<td>
				<a href='/book/del?id=<%= book.id %>'>删除</a>
				<a href='/book/edit?id=<%= book.id %>'>修改</a>
			</td>
			
			<% } %>
			
		</tr>
		<% } %>
	</table>
	
	<%@ include file="footer.jsp" %>
</body>
</html>



