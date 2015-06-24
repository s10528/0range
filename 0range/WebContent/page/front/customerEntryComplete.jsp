<!--  @author:Tanaka -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="header.jsp" %>
<form action="/0range/page/front/frontLogin.jsp">
	<center>

		<p>
			システム利用登録を完了しました。<br> お客様の顧客IDは、<font size="+1"><b>${customerID } </b></font>です。<br>
			<br> ログイン時には、顧客IDとパスワードが必要となりますので、<br> 顧客IDを忘れないようにご注意ください。
		</p>
		<br>
			<input type="submit" value="ログイン画面を表示" >
			<input type="hidden" name="action" value="entry">
			<input type="hidden" name="back" value="back">
	</center>
</form>
</body>
</html>