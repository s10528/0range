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
		if(param == "updateTransmit")
			document.forms[0].action.value = "updateTransmit";
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
		<font color="red">
		<p> ${errMsg }</p>
		<p> ${errMsg2 }</p>
		<p> ${errMsg3 }</p>
		<p> ${errMsg4 }</p>
		<p> ${errMsg5 }</p>
		<p> ${errMsg6 }</p>
		</font><br>
		商品情報を更新します。<br> 更新内容を入力して、「送信」ボタンを押してください。<br> <br>



		<form action="/0range/controller" method="post">
			<table border="1">
				<tr>
					<th>商品番号</th>
					<td><fmt:formatNumber value="${itemVO.itemNo }"  pattern="0000"/><input type="hidden" name="itemNo" value="${itemVO.itemNo}"></td>
				</tr>
				<tr>
					<th>商品名</th>
					<td><input type="text" name="itemName" value="${ itemVO.itemName}"></td>
				</tr>
				<tr>
					<th>商品画像(URL)</th>
					<td><input type="text"  name="itemUrl" value="${itemVO.itemUrl}"></td>
				</tr>
				<tr>
					<th>単価</th>
					<td><input type="text"  name="unitPrice" value="${itemVO.unitPrice}"></td>
				</tr>
				<tr>
					<th>寸法</th>
					<td><input type="text" name="size" value="${itemVO.size }"></td>
				</tr>


				<%--
				<tr>
					<th>種別</th>
					assortList
					<td><select name="itemAssortmentCode" >
							<option value="1" <c:if test="${itemVO.itemAssortmentCode ==  1} selected</c:if>>花束</option>
							<option value="2">鉢植</option>
							<option value="3" selected>アレンジメント</option>
					</select></td>
				</tr>
				--%>
			<!-- 高橋サンプル -->
				<tr>
					<th>種別</th>
					<td><select name="itemAssortmentCode" >
				<c:forEach var="assortVo" items="${assortList}">
					<option value="${assortVo.itemAssortmentCode}"
						<c:if test="${assortVo.itemAssortmentCode == itemVO.itemAssortmentCode}"> selected</c:if>
					>
						<c:out value="${assortVo.itemAssortmentName}" />
					</option>
				</c:forEach>
			</select></td>
				</tr>
			<!-- 高橋サンプル -->

				<tr>
				<th>カテゴリー</th>
					<td><select name="itemCategoryCode">
					<c:forEach var="categoryVo" items="${categoryList}">
					<option value="${categoryVo.itemCategoryCode}"
					<c:if test="${categoryVo.itemCategoryCode == itemVO.itemCategoryCode}"> selected</c:if>>
					<c:out value="${categoryVo.itemCategoryName}" />
				</option>
				</c:forEach>
				</select></td>
			</tr>
			</table>
			<br>

			<input type="button" value="送信" onclick = "doSubmit('updateTransmit');">
			<input type="button" value="戻る" onclick = "doSubmit('itemMaintenance');">
			<input type="hidden" name="action" value="">
		</form>
	</center>
		</td>
	</tr>

</table>
</body>
</html>