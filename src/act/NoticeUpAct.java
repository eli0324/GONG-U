package act;

import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import svc.*;
import vo.*;


public class NoticeUpAct implements Action {
	  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	      request.setCharacterEncoding("utf-8");
	      
	      int idx = Integer.parseInt(request.getParameter("idx"));	//�۹�ȣ(�ʼ��̹Ƿ� �ٷ� ����ȯ ����)
	      int cpage = Integer.parseInt(request.getParameter("cpage"));	//��������ȣ    
	      String schtype = request.getParameter("schtype");	//�˻�����
	      String keyword = request.getParameter("keyword");	//�˻���
	      
	      PageInfo pageInfo = new PageInfo();	//����¡�� �ʿ��� ������ ������ �ν��Ͻ� ���� 
	      pageInfo.setCpage(cpage);
	      pageInfo.setSchtype(schtype);
	      pageInfo.setKeyword(keyword);
	      
	      
	      
	      NoticeUpSvc noticeUpSvc = new NoticeUpSvc();
	      NoticeList notice = noticeUpSvc.getNoticeInfo(idx);
	      // ������ �ϳ��� �Խñ� ������ NoticeList Ŭ������ �ν��Ͻ��� �޾ƿ� 
	      
	      if(notice == null) { //�ش� �Խñ��� ������
	    	  response.setContentType("text/html; charset=utf=8");
	    	  PrintWriter out = response.getWriter();
	    	  out.println("<script>");
	    	  out.println("alert('�������� �ʴ� �Խñ� �Դϴ�');");
	    	  out.println("history.back();");
	    	  out.println("</script>");
 	      }
	      
	      
	      request.setAttribute("notice", notice);
	      request.setAttribute("pageInfo", pageInfo);
	      //view ���Ϸ� ������ �� �ν��Ͻ��� request��ü�� �Ӽ�(attribute)���� ������ 
	      
	      
	      ActionForward forward = new ActionForward();
	      forward.setPath("/notice_up_form.jsp");
	      //ActionForward Ŭ������ redirect true�� �ƴ� false�� ���������ν� �̵������ ����ġ ������� ������ 
	      // ����ġ ������� �̵��ؾ� request��ü�� �̵��� ���Ϸ� �ѱ�� �־� request�ȿ� �ִ� �Ӽ����� ����� �� ���� 
	      
	      return forward;
	   }
}


