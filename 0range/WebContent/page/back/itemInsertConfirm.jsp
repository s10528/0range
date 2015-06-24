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
		if(param == "insertConfirm")
			document.forms[0].action.value = "insertConfirm";
		else
			document.forms[0].action.value = "insert2";
		document.forms[0].submit();
	}
-->
</script>
<title>Insert title here</title>
</head>
<body>
<table width ="100%" height="100%" rules= "cols" >
	<tr>
		<td colspan="2" align = "center">
			<%@ include file="header.jsp" %>
		</td>
	</tr>

	<tr>
		<td width = "30%" valign="top">
			<%@ include file="menu.jsp" %>
		</td>

		<td valign="top">
		<center>
		<h2>商品情報メンテナンス</h2>

		以下の内容で商品情報を登録します。<br> 登録内容を確認の上、「登録」ボタンを押してください。<br> <br>


			<table border="1">
				<tr>
					<th>商品番号</th>
					<td><fmt:formatNumber value="${itemNo }"  pattern="0000"/></td>
				</tr>
				<tr>
					<th>商品名</th>
					<td><c:out value="${itemName }" /></td>
				</tr>
				<tr>
					<th>商品画像</th>
					<td><img src="${itemUrl }"  width="200px" height="150px" /></td>
				</tr>
				<tr>
					<th>単価</th>
					<td><fmt:formatNumber value="${unitPrice }" type="currency" currencySymbol="&yen;"/>-</td>
				</tr>
				<tr>
					<th>寸法</th>
					<td><c:out value="${size }" />cm</td>
				</tr>
				<tr>
					<th>種別</th>
					<td><c:out value="${itemAssortmentName }" /></td>
				</tr>
				<tr>
					<th>カテゴリー</th>
					<td><c:out value="${itemCategoryName }" /></td>
				</tr>
			</table>

			<br>
			<form action="/0range/controller" method="post">
				<input type="hidden" name="itemNo" value="${itemNo }">
				<input type="hidden" name="itemName" value="${itemName }">
				<input type="hidden" name="itemUrl" value="${itemUrl }">
				<input type="hidden" name="unitPrice" value="${unitPrice }">
				<input type="hidden" name="size" value="${size }">
				<input type="hidden" name="itemAssortmentName" value="${itemAssortmentName }">
				<input type="hidden" name="itemAssortmentCode" value="${itemAssortmentCode }">
				<input type="hidden" name="itemCategoryCode" value="${itemCategoryCode }">
				<input type="hidden" name="itemCategoryName" value="${itemCategoryName }">


				<input type="button"  value="登録"onclick="doSubmit('insertConfirm');">
				<input type="button"  value="戻る" onClick="doSubmit('insert2');">
				<input type="hidden" name="action" value="">




			</form>



	</center>

		</td>
	</tr>

</table>
</body>
</html>