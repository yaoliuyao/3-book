<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册页面</title>
<style>
	label {
		display: flex;
	}
	label span {
		width: 100px;
	}
</style>
</head>
<body>

<h3>注册页面</h3>

<div>
	<form action="${pageContext.request.contextPath}/regist" method="post">
		<label>
			<span>用户名</span>
			<input name="username">
		</label>
		<label>
			<span>密码</span>
			<input type="password" name="password">
		</label>
		<label>
			<span>性别</span>
			<input type="radio" name="gender" value="男">男
			<input type="radio" name="gender" value="女">女
		</label>
		<label>
			<span>邮箱</span>
			<input name="email">
		</label>
		<button>点击进行注册</button>
	</form>
</div>

<%@ include file="/footer.jsp" %>
</body>
</html>







