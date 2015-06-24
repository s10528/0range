<!--  @author:Sapei -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
<!--
	function doSubmit(param){
		if(param == "entryConfirm")
			document.forms[0].action.value = "entryConfirm";
		else
			document.forms[0].action.value = "entry.back";
		document.forms[0].submit();
	}
-->
</script>
<title>Insert title here</title>
</head>
<body>
<center>
<%@ include file="header.jsp"%>

					送信内容をご確認ください。<br>よろしければ、「登録」ボタンを押してください。<br> <br>
					<table border="1">
						<tr>
							<td colspan="2" align = "center"><b>会社情報</b></td>
						</tr>
						<tr>
							<th>会社名</th>
							<td><c:out value="${companyName }"/></td>
						</tr>
						<tr>
							<th>住所</th>
							<td><c:out value="${address }" /></td>
						</tr>
						<tr>
							<th>電話番号</th>
							<td><c:out value="${telno }" /></td>
						</tr>
						<tr>
							<td colspan="2" align = "center"><b>ご担当者様情報</b></td>
						</tr>
						<tr>
							<th>氏名(漢字)</th>
							<td><c:out value="${customerName }"/></td>
						</tr>
						<tr>
							<th>氏名(かな)</th>
							<td><c:out value="${customerKana }" /></td>
						</tr>
						<tr>
							<th>部署</th>
							<td><c:out value="${dept }" /></td>
						</tr>
						<tr>
							<th>メールアドレス</th>
							<td><c:out value="${email }" /></td>
						</tr>
					</table>

					<br>
					<form action="/0range/frontLogin" method="post">
						<input type="hidden" name="companyName" value="${companyName }">
						<input type="hidden" name="address" value="${address }">
						<input type="hidden" name="telno" value="${telno }">
						<input type="hidden" name="customerName" value="${customerName }">
						<input type="hidden" name="customerKana" value="${customerKana }">
						<input type="hidden" name="dept"value="${dept }">
						<input type="hidden"name="email" value="${email }">
						<input type="hidden"name="password" value="${password }">
						<input type="button" value="登録" onclick="doSubmit('entryConfirm');">
						<input type="button" value="戻る" onClick="doSubmit('entry');">
						<input type="hidden" name="action" value="">
					</form>
				</center>

</body>
</html>