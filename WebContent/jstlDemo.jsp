<%@page import="book.bean.Account"%>
<%@page import="book.bean.Book"%>
<%@page import="java.util.List"%>
<%@page import="book.dao.BookDAO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%
	request.setAttribute("books", new BookDAO().list());

	Account account = new Account();
	account.setUsername("tomcat");
	account.setPassword("jerry");
	session.setAttribute("account", account);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>JSTL 示例</title>
</head>
<body>
JSTL 示例

<%-- 判断 if --%>

<c:if test="${empty sessionScope.account}">
	<p>请您点击登陆</p>
</c:if>

<c:if test="${sessionScope.account != null}">
	<ul>
		<li>账号: ${account.username}</li>
		<li>密码: ${account.password}</li>
	</ul>
</c:if>

<h5>书籍列表</h5>
<ul>
<c:forEach items="${books}" var="book" varStatus="s" begin="2" step="1">
	<c:if test="${s.first}">
	-----------------------------------
	</c:if>
	<c:if test="${book.price > 1000}">
		<li>${s.count} :  ${book.bookName} / ${book.price} </li>
	</c:if>
</c:forEach>
</ul>

<hr>

<%-- 加强版的 c:if/else; switch --%>
<c:forEach items="${books}" var="book" varStatus="s" begin="2" step="1">
	<c:choose>
		<c:when test="${book.price > 1000}">
			<li>贵的书:  ${book.bookName}</li>
		</c:when>
		<c:when test="${book.price < 100}">
			<li>推荐书籍:  ${book.bookName}</li>
		</c:when>
		<c:otherwise>
			<li>${s.count} :  ${book.bookName} / ${book.price} </li>
		</c:otherwise>
	</c:choose>
</c:forEach>

<hr>

<%-- forEach 的一种特殊 --%>
<c:forTokens items="123:456:abc:ddddddd:哈哈" delims=":" var="e">
	<li>${e}</li>
</c:forTokens>

<hr>

<c:url value="/add" var="aaa">
	<c:param name="hello" value="1111" />
	<c:param name="world" value="2222" />
</c:url>
<a href="${aaa}">hahaha</a>

<hr>

<% session.setAttribute("aaa", 2333); %>
<%--c:set property="aaa" scope="session" value="2333" var></c:set> --%>

<hr>

<%--
<c:import url="aaa.jsp" ></c:import>
--%>

<hr>

<fmt:formatNumber value="4000000" />
<fmt:formatNumber value="4000000" pattern="###,##" />
<fmt:formatNumber value="4000000" type="currency" currencySymbol="$" />

<hr>

<fmt:formatDate value="<%= new java.util.Date() %>" pattern="yyyy 年 MM 月 HH:mm:ss" />

<hr>

${fn:length("hello")}
${fn:indexOf("hello", "l")}
${fn:replace("hello", "l", "L")}
${fn:toUpperCase("hello")}
${fn:trim("   hello  ")}

<c:if test="${fn:contains('hello', 'h')}">
	hello 里面是有 h 的
</c:if>

</body>
</html>




