<%@page import="book.Book"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<%
	List<Book> books = (List<Book>) request.getAttribute("xxx");
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
	</style>
</head>

<body>
	<header>
		<h3>163 书籍管理系统</h3>
	</header>
	<div>
		<form action='/book/add' method='post'>
			<input type='text' name='bookName' placeholder='书名'>
			<input type='number' name='price' placeholder='价格'>
			<button>添加书籍</button>
		</form>
	</div>
	<table>
		<tr>
			<th>编号</th>
			<th>书名</th>
			<th>价格</th>
			<th>操作</th>
		</tr>
		
		<% for (Book book : books) { %>
		<tr>
			<td><%= book.id %></td>
			<td><%= book.bookName %></td>
			<td><%= book.price %></td>
			<td>
				<a href='/book/del?id=<%= book.id %>'>删除</a>
				<a href='/book/edit?id=<%= book.id %>'>修改</a>
			</td>
		</tr>
		<% } %>
	</table>
</body>
</html>