<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript">
  <!--
  function doublePush(btn) {
    btn.disabled=true;
   }
   -->
  </script>
<title>Insert title here</title>
</head>
<body>
	<center>
		<img src="/0range/image/fullneflower_logo.png"><br>
		<h2>FullneFlower 受発注管理システム</h2>
		<hr>
		<font color="red">${errMsg }</font>
		<p>社員番号とパスワードを入力してください。</p>
		<form action="/0range/login" method="post">
			<table>
				<tr>
					<td>社員番号</td>
					<td><input type="text" name="staffId" value="1001"></td>
				</tr>
				<tr>
					<td>パスワード</td>
					<td><input type="password" name="password" value="12345678"></td>
				</tr>

			</table><br>
			<input type="submit" value="ログイン" onClick="javascript:doublePush(this)">
			<input type="hidden" name="action" value="backLogin">
			<input type="reset" value="クリア">
		</form>
		<br>
	</center>
</body>
</html>



