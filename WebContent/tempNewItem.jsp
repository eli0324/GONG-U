<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.mainBox {margin:auto; width:1200px; height:900px; color:gray; border:1px solid gray;}

</style>
</head>
<body>
<table class="mainBox">
<tr><td height="50" colspan="2"	align="left">
	<jsp:include page="tempTop.jsp" /><!-- 화면 상단 include 페이지 -->
</td></tr>
<hr />
<tr>
<td width="*" align="center">
	<h2>신상품 목록 영역</h2>
</td>
</tr>
<tr><td height="50" colspan="2"	align="left"><br />
	<jsp:include page="tempBottom.jsp" /><!-- 화면 하단 include 페이지 -->
</td></tr>
</table>
</body>
</html>