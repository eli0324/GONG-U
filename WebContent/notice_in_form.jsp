<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>공지사항 등록 폼</h2>
<form name="frmNotice" action="notice_in_proc.brd" method="post">
글종류 : <input type="radio" name="nl_kind" value="a" id="kinda" checked="checked" /><label for="kinda">공지</label>
<input type="radio" name="nl_kind" value="b" id="kindb"  /><label for="kindb">점검</label>
<input type="radio" name="nl_kind" value="c" id="kindc"  /><label for="kindc">이벤트</label>
<br />
글제목 : <input type="text" name="nl_title" />
<br />
글 내용 : <textarea name="nl_content" cols="50" rows="5"></textarea>
<br /><br />
<input type="button" value="목록으로" onclick="location.href='notice_list.brd';" />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="submit" value="공지사항 등록" />
</form>
</body>
</html>