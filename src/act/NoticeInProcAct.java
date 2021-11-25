package act;

import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import svc.*;
import vo.*;

public class NoticeInProcAct implements Action {
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      request.setCharacterEncoding("utf-8");
      
      NoticeList notice = new NoticeList();      // ����ڰ� �Է��� �������� ���� �������� ������ �ν��Ͻ� ����
      notice.setNl_kind(request.getParameter("nl_kind"));
      notice.setNl_title(request.getParameter("nl_title").trim().replace("'", "''"));
      notice.setNl_content(request.getParameter("nl_content").trim().replace("'", "''"));
      // ����� �Խñ� �����͸� �޾ƿ� notice�ν��Ͻ��� ����(�Ű������� ����ϱ� ���ϱ� ����)
      
      NoticeInProcSvc noticeInProcSvc = new NoticeInProcSvc();
      // DB�� ������ ����Ͻ� ������ ó���ϱ� ���� �ν��Ͻ� ����
      int result = noticeInProcSvc.noticeInsert(notice);
      
      if (result == 0) // �� ��� ����������
    	  response.setContentType("text/html; charset=utf-8");
    	  PrintWriter out = response.getWriter();
    	  out.println("<script>");
    	  out.println("alert('�۵�Ͽ� �����߽��ϴ�.\n�ٽ� �õ��� �ֽʽÿ�.');");
    	  out.println("history.back();");  	  
    	  out.println("</script>");
      
      ActionForward forward = new ActionForward();
      forward.setRedirect(true); //dispatch�� �ƴ� sendRedirect ������� �̵��Ѵٴ� ����
      forward.setPath("/gongU/notice_view.brd?cpage=1&idx=" + result);
      
      return forward;
   }
}