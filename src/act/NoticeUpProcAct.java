package act;

import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import java.net.URLEncoder;
import svc.*;
import vo.*;

public class NoticeUpProcAct  implements Action {
	   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		      request.setCharacterEncoding("utf-8");
		            
		      int idx = Integer.parseInt(request.getParameter("idx"));	//�۹�ȣ(�ʼ��̹Ƿ� �ٷ� ����ȯ ����)
		      int cpage = Integer.parseInt(request.getParameter("cpage"));	//��������ȣ    
		      String schtype = request.getParameter("schtype");	//�˻�����
		      String keyword = request.getParameter("keyword");	//�˻���
		      
		      String args = "?idx=" + idx + "&cpage=" + cpage;
		      if (schtype != null && keyword != null && !schtype.equals("") && !keyword.equals("")){
		      		args += "&schtype=" + schtype + "&keyword=" + URLEncoder.encode(keyword, "UTF-8");
		      } //������ �ִ� ��쿡�� ������Ʈ������ �����Ŵ 
		      
		      NoticeList notice = new NoticeList();      // ����ڰ� �����Ϸ��� �������� ���� �������� ������ �ν��Ͻ� ����
		      notice.setNl_idx(idx);
		      notice.setNl_kind(request.getParameter("nl_kind"));
		      notice.setNl_title(request.getParameter("nl_title").trim().replace("'", "''"));
		      notice.setNl_content(request.getParameter("nl_content").trim().replace("'", "''"));
		      // ����� �Խñ� �����͸� �޾ƿ� notice�ν��Ͻ��� ����(�Ű������� ����ϱ� ���ϱ� ����)
		      
		      NoticeUpProcSvc noticeUpProcSvc = new NoticeUpProcSvc();
		      // DB�� ������ ����Ͻ� ������ ó���ϱ� ���� �ν��Ͻ� ����
		      int result = noticeUpProcSvc.noticeUpdate(notice);
		      
		      if (result == 0) // �� ������ ����������
		    	  response.setContentType("text/html; charset=utf-8");
		    	  PrintWriter out = response.getWriter();
		    	  out.println("<script>");
		    	  out.println("alert('�ۼ����� �����߽��ϴ�.\n�ٽ� �õ��� �ֽʽÿ�.');");
		    	  out.println("history.back();");  	  
		    	  out.println("</script>");
		      
		      ActionForward forward = new ActionForward();
		      forward.setRedirect(true); //dispatch�� �ƴ� sendRedirect ������� �̵��Ѵٴ� ����
		      forward.setPath("/gongU/notice_view.brd" + args);
		      
		      return forward;
		   }
		}
