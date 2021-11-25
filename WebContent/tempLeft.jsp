<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
String uri = request.getRequestURI();  // http와 도메인 부분, 쿼리스트링을 뺸 주소값
int pos = uri.lastIndexOf('/');		 // 받아온 주소값에서 마지막 슬래시의 위치를 저장
String file = uri.substring(pos + 1);	// 마지막 슬래시의 위치 다음글자부터 끝까지 잘라옴 (파일명만 추출)

String mLnk="", nLnk="", bLnk="";
if(file.equals("template.jsp")) mLnk = " class='lnk'";
else if(file.equals("tempNewItem.jsp")) nLnk = " class='lnk'";
else if(file.equals("tempBestItem.jsp")) bLnk = " class='lnk'";
%>
<style>
.lnk { font-weight: bold;}
</style>
<h2>incLeft</h2>
<a href="template.jsp"<%=mLnk %> >메인화면</a><br /><br />
<a href="tempNewItem.jsp"<%=nLnk %> >신상품 목록</a><br /><br />
<a href="tempBestItem.jsp"<%=bLnk %> >인기상품 목록</a><br /><br />

