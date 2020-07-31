<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录页面</title>
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

<h3>登录页面</h3>

<div>
	<form action="${pageContext.request.contextPath}/login" method="post">
		<label>
			<span>用户名</span>
			<input name="username">
		</label>
		<label>
			<span>密码</span>
			<input type="password" name="password">
		</label>
		<button>登录</button>
	</form>
</div>


	<%@ include file="/footer.jsp" %>

</body>
</html>







