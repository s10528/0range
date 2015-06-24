<!--  @author:Sanpei -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--
<script>
-->
<!--
	function doSubmit(param){
		if(param == "entryCheck")
			document.forms[0].action.value = "entryCheck";
		else if(param == "entry.back")
			document.forms[0].action.value = "entryCheck";
		else
			document.forms[0].action.value = "clear";
		document.forms[0].submit();
	}
-->
<!--
</script>
 -->
<title>Insert title here</title>
</head>
<body>
<center>
		<%@ include file="header.jsp" %>

		<p>
			システム利用登録を行います。<br>
			下記の情報を入力して、送信ボタンを押してください。
		</p><br>
		<form action="/0range/frontLogin" method="post">
			<table border="0">
				<tr>
					<td colspan="3" align = "center"><b>会社情報</b></td>
				</tr>
				<tr>
					<th>会社名</th>
					<td><input type="text" name="companyName" value="${customerVO.companyName }"></td>
					<td><font color="red"> ${errMsg1 }</font></td>
				</tr>
				<tr>
					<th>住所</th>
					<td><input type="text" name="address" size="30" value="${customerVO.address }"></td>
					<td><font color="red"> ${errMsg2 }</font></td>
				</tr>
				<tr>
					<th>電話番号</th>
					<td><input type="text" name="telno" value="${customerVO.telno }"></td>
					<td><font color="red"> ${errMsg3 }</font></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3" align = "center"><b>ご担当者様情報</b></td>
				</tr>
				<tr>
					<th>氏名（漢字）</th>
					<td><input type="text" name="customerName" value="${customerVO.customerName }"></td>
					<td><font color="red"> ${errMsg4 }</font></td>
				</tr>
				<tr>
					<th>氏名（かな）</th>
					<td><input type="text" name="customerKana" value="${customerVO.customerKana }"></td>
					<td><font color="red"> ${errMsg5 }</font></td>
				</tr>
				<tr>
					<th>部署</th>
					<td><input type="text" name="dept" value="${customerVO.dept }"></td>
					<td><font color="red"> ${errMsg6 }</font></td>
				</tr>
				<tr>
					<th>メールアドレス</th>
					<td><input type="text" name="email" value="${customerVO.email }"></td>
					<td><font color="red"> ${errMsg7 }</font></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3" align = "center"><b>認証用情報</b></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type="password" name="password"></td>
					<td><font color="red"> ${errMsg8 }</font></td>
				</tr>
				<tr>
					<th>パスワード（確認）</th>
					<td><input type="password" name="passwordConfirm"></td>
					<td><font color="red"> ${errMsg9 }</font></td>
				</tr>
			</table>

			<br>

			<input type="submit" value="送信" >
			<input type="hidden" name="action" value="entryCheck">
			<!-- <input type="button" value="送信" onclick = "doSubmit('entryCheck');"> -->
			<input type="reset" value="クリア">
			<!-- <input type="button" value="戻る" onclick = "doSubmit('entry');"> -->
			<!-- <input type="hidden" name="action" value="">  -->
			</form>
		</center>

</body>
</html>
