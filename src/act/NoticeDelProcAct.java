package act;

import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import svc.*;
import vo.*;

public class NoticeDelProcAct implements Action {
	   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		      request.setCharacterEncoding("utf-8");
		            
		      int idx = Integer.parseInt(request.getParameter("idx"));	//�۹�ȣ(�ʼ��̹Ƿ� �ٷ� ����ȯ ����)

		      NoticeDelProcSvc noticeDelProcSvc = new NoticeDelProcSvc();
		      // DB�� ������ ����Ͻ� ������ ó���ϱ� ���� �ν��Ͻ� ����
		      int result = noticeDelProcSvc.noticeDelete(idx);
		      
		      if (result == 0) // �� ������ ����������
		    	  response.setContentType("text/html; charset=utf-8");
		    	  PrintWriter out = response.getWriter();
		    	  out.println("<script>");
		    	  out.println("alert('�ۻ����� �����߽��ϴ�.\n�ٽ� �õ��� �ֽʽÿ�.');");
		    	  out.println("history.back();");  	  
		    	  out.println("</script>");
		      
		      ActionForward forward = new ActionForward();
		      forward.setRedirect(true); //dispatch�� �ƴ� sendRedirect ������� �̵��Ѵٴ� ����
		      forward.setPath("/gongU/notice_list.brd");
		      
		      return forward;
		   }
		}

