<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String user_id =(String)session.getAttribute("user_id");
// 세션에 속성으로 저장되어 있는 user_id 의 값을 받아옴(값이 없으면 로그인을 하지 않은 상태)
%>

<div style="position:relative; left:100px;">
<img src="img/logo.png" width="100" height="100" />
</div>
<!-- 로그인을 안했으면 'Login'Join' 링크를 'Logout'과 'MyPage' 링크를 걸어줌  -->
<!-- 로그인 여부는 세션에 'user_id'라는 속성에 값이 있으면 로그인 상태, 없으면 비로그인 상태로 취급  -->
<div style="text-align:right; position:relative; right:30px; ">
<% if(user_id == null){ %>
<a href="#">로그인</a> | <a href="#">회원가입</a>
<%}else{ %>
<a href="#">로그아웃</a> | <a href="#">마이페이지</a>
<%} %>
