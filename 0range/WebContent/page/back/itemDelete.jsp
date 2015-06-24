<!--  @author:Sanpei -->
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
		if(param == "deleteConfirm")
			document.forms[0].action.value = "deleteConfirm";
		else
			document.forms[0].action.value = "itemMaintenance";
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

		下記の商品情報を削除します。<br> 内容を確認の上、「削除」ボタンを押してください。<br> <br>


			<font color="red">${errMsg }</font>
			<form action="/0range/controller" method="post">
			<table border="1">
				<tr>
					<th>商品番号</th>
					<th>商品名</th>
					<th>単価</th>
					<th>寸法</th>
					<th>種別</th>
					<th>カテゴリー</th>
				</tr>

				<tr>
				<c:forEach var="itemDelList" items="${itemDelList }">
					<tr>
						<td><fmt:formatNumber value="${itemDelList.itemNo }" pattern="0000"/></td>
						<td><c:out value="${itemDelList.itemName }" /></td>
						<td><fmt:formatNumber value="${itemDelList.unitPrice }" type="currency" currencySymbol="&yen;"/>-</td>
						<td><c:out value="${itemDelList.size }" />cm</td>
						<td><c:out value="${itemDelList.itemAssortmentName }" /></td>
						<td><c:out value="${itemDelList.itemCategoryName }" /></td>
						<c:set var ="delete" value="${itemDelList.itemNo }"  scope="request"/>
					</tr>
						<input type="hidden" name="itemNo" value="<c:out value="${itemDelList.itemNo }" />">
					</c:forEach>
			</table>



			<input type="button" value="削除" onclick = "doSubmit('deleteConfirm');">
			<input type="button" value="戻る" onclick = "doSubmit('back');">
			<input type="hidden" name="action" value="">
			</form>



	</center>
		</td>
	</tr>

</table>
</body>
</html>