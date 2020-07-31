<%@ page import="book.bean.Account"%>
<%@ page import="book.bean.Book"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
	
<html>
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" href="${r}/css/xxx.css">
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
			<c:if test="${empty sessionScope.account}">
				<a href="${r}/login">登录</a>
			</c:if>
			<c:if test="${sessionScope.account != null}">
				欢迎您 ${account.username} ! 
				<a href="${r}/logout">注销</a>
			</c:if>
		</span>
	</header>
	
	<c:if test="${sessionScope.account != null}">
	<div>
		<form action='${r}/add' method='post'>
			<input type='text' name='bookName' placeholder='书名'>
			<input type='number' name='price' placeholder='价格'>
			<button>添加书籍</button>
		</form>
	</div>
	</c:if>
	
	<table>
		<tr>
			<th>编号</th>
			<th>书名</th>
			<th>价格</th>
			<c:if test="${sessionScope.account != null}">
			<th>操作</th>
			</c:if>
		</tr>
		
		<c:forEach var="book" items="${xxx}">
		<tr>
			<td>${book.id}</td>
			<td>${book.bookName}</td>
			<td>${book.price}</td>
			<c:if test="${sessionScope.account != null}">
			<td>
				<a href='${r}/del?id=${book.id}'>删除</a>
				<a href='${r}/edit?id=${book.id}'>修改</a>
			</td>
			</c:if>
		</tr>
		</c:forEach>
	</table>
	
	<%@ include file="/footer.jsp" %>
</body>
</html>



