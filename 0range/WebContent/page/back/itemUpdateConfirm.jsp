<!--  @author:Wada -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 作成者:和田 -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
<!--
	function doSubmit(param){
		if(param == "updateConfirm")
			document.forms[0].action.value = "updateConfirm";
		else
			document.forms[0].action.value = "update2";
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

		以下の内容で商品情報を更新します。<br> 更新内容を確認の上、「更新」ボタンを押してください。<br> <br>


			<form action="/0range/controller" method="post">
			<table border="1">
				<tr>
					<th>商品番号</th>
					<td><fmt:formatNumber value="${itemNo }"  pattern="0000"/><input type="hidden" name="itemNo" value="${ itemNo}"></td>
				</tr>
				<tr>
					<th>商品名</th>
					<td>${ itemName}<input type="hidden" name="itemName" value="${ itemName}"></td>
				</tr>
				<tr>
					<th>商品画像(URL)</th>
					<td><img src="${itemUrl }" width="200px" height="150px" />
					<input type="hidden"  name="itemUrl" value="${itemUrl}"></td>
				</tr>
				<tr>
					<th>単価</th>
					<td><fmt:formatNumber value="${unitPrice }" type="currency" currencySymbol="&yen;"/>-<input type="hidden"  name="unitPrice" value="${unitPrice}"></td>
				</tr>
				<tr>
					<th>寸法</th>
					<td>${size }cm<input type="hidden" name="size" value="${size }"></td>
				</tr>

				<tr>
					<th>種別</th>
					<td>${itemAssortmentName}<input type="hidden" name="itemAssortmentCode" value="${itemAssortmentCode}"></td>
				</tr>

				<tr>
					<th>カテゴリー</th>
					<td>${ itemCategoryName}<input type="hidden" name="itemCategoryCode" value="${ itemCategoryCode}"></td>
				</tr>



			</table>

			<br>




			<br>
			<input type="button" value="更新" onclick = "doSubmit('updateConfirm');">
			<input type="button" value="戻る" onclick = "doSubmit('update');">
			<input type="hidden" name="action" value="">
			</form>
	</center>



		</td>
	</tr>

</table>
</body>
</html>