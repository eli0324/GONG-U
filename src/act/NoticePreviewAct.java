package act;

import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import java.net.*;
import svc.*;
import vo.*;


public class NoticePreviewAct implements Action{
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

	      
	      NoticePreviewSvc noticePreviewSvc = new NoticePreviewSvc();
	      int result = noticePreviewSvc.readcntUpdate(idx);
	      

	      ActionForward forward = new ActionForward();
	      forward.setRedirect(true); //dispatch�� �ƴ� sendRedirect ������� �̵��Ѵٴ� ����
	      forward.setPath("/gongU/notice_view.brd" + args);
	      
	      return forward;
	  }
}
