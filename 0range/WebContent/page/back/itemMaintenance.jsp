<!--  @author:Wada -->
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
		if(param == "insert")
			document.forms[0].action.value = "insert";
		else
			document.forms[0].action.value = "delete";
		document.forms[0].submit();
	}
-->
</script>
<title>Insert title here</title>
</head>
<body>
<table width ="100%" height="100%" rules= "cols" >
	<tr>
		<td colspan="2" align = "center" valign="top">
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

		商品情報のメンテナンスを行います。<br> 新規に取扱商品を追加する場合は、「商品追加」ボタンを押してください。<br>
		登録済み商品の情報を変更するには、商品名のリンクをクリックしてください。<br>
		商品を削除する場合は、リストの右にある「削除」をチェックし、「削除」ボタンを押してください。<br> <br>
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
					<th>削除</th>
				</tr>
				<tr>
				<c:forEach var="itemvoList" items="${itemvoList }">
					<tr>
					<td><fmt:formatNumber value="${itemvoList.itemNo }" pattern="0000"/></td>
						<!-- <td><c:out value="${itemvoList.itemNo }" /></td> -->

						<td>
							<a href="/0range/controller?action=update&itemNo=${itemvoList.itemNo }">
								<c:out value="${itemvoList.itemName }" />
							</a>
						</td>
						<!-- <td><c:out value="${itemvoList.unitPrice }" /></td> -->
						<td><fmt:formatNumber value="${itemvoList.unitPrice }" type="currency" currencySymbol="&yen;"/>-</td>
						<td><c:out value="${itemvoList.size }" />cm</td>
						<td><c:out value="${itemvoList.itemAssortmentName }" /></td>
						<td><c:out value="${itemvoList.itemCategoryName }" /></td>
						<td align="center"><input type="checkbox" name="delete" value="${itemvoList.itemNo }"></td>
					</tr>
					</c:forEach>
			</table>

			<br>
			<input type="button" value="商品追加" onclick = "doSubmit('insert');">
			<input type="button" value="削除" onclick = "doSubmit('delete');">
			<input type="hidden" name="action" value="">
			</form>
	</center>
	</td>
	</tr>

</table>
</body>
</html>