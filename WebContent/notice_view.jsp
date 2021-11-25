<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="vo.*" %>
<%
PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");	//페이징 정보가 들어있는 인스턴스
NoticeList notice = (NoticeList)request.getAttribute("notice"); // 게시글 정보가 들어있는 인스턴스

String args = "?cpage=" + pageInfo.getCpage();
if(pageInfo.getKeyword() != null && !pageInfo.getKeyword().equals("")){
	args += "&schtype=" + pageInfo.getSchtype() + "&keyword=" + pageInfo.getKeyword();
} //검색조건이 있을 경우에만 쿼리스트링으로 만들어줌 
	
String kind = "공지";
if(notice.getNl_kind().equals("b"))  kind = "점검";
else if (notice.getNl_kind().equals("c"))  kind = "이벤트";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
function callDel() {
	if (confirm('정말 삭제 하시겠습니까?')) {
		location.href="notice_del_proc.brd?idx=<%=notice.getNl_idx() %>";
	}
}
</script>
</head>
<body>
<h2>공지사항 보기 </h2>
글종류 : <%=kind %> /조회수 : <%=notice.getNl_readcnt() %><br />
글제목 : <%=notice.getNl_title() %><br />
글 내용 :<%=notice.getNl_content().replace("\r\n","<br />") %><br />
작성일 : <%=notice.getNl_date() %>
<br /><br />
<input type="button" value="목록으로" onclick="location.href='notice_list.brd<%=args%>';"/>
<input type="button" value="글 수정" onclick="location.href='notice_up_form.brd<%=args %>&idx=<%=notice.getNl_idx() %>';"/>
<input type="button" value="글 삭제" onclick="callDel();"/>
</body>
</html>