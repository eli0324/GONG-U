package ctrl;

import java.io.*;
import javax.servlet.*;	//
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;	// request, response, session ��ü�� ����ϱ����� import
import act.*;
import vo.*;

@WebServlet("*.brd")	// �������� ���� ��� ��û�� ó���ϴ� ���� Ŭ����
public class NoticeCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public NoticeCtrl() {
        super();
    }
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//get�� post �ΰ��� ����� ��û�� ó���ϴ� �޼ҵ�
		request.setCharacterEncoding("utf-8");
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestUri.substring(contextPath.length());
		System.out.println("requestUri : " + requestUri);	//requestUri : /mvcBoard/notice_in_form.brd
		System.out.println("contextPath : " + contextPath);//contextPath : /mvcBoard
		System.out.println("command : " + command);//command : /notice_in_form.brd
		
		ActionForward  forward = null;
		Action action = null;
		
		switch (command) {
		case "/notice_list.brd" :		// �Խñ� ��� ��û
			action = new NoticeListAct();
			break;
		case "/notice_in_form.brd" :	// �Խñ� ��� �� ��û
			action = new NoticeInAct();
			break;
		case "/notice_in_proc.brd" :	// �Խñ� ó�� ��û
			action = new NoticeInProcAct();
			break;
		case "/notice_preview.brd" :		// �Խõ� ��ȸ�� ���� ��û 
			action = new NoticePreviewAct();
			break;
		case "/notice_view.brd" :		// �Խõ� ���� ȭ�� ��û
			action = new NoticeViewAct();
			break;
		case "/notice_up_form.brd" :	// �Խñ� ���� �� ��û
			action = new NoticeUpAct();
			break;
		case "/notice_up_proc.brd" :	// �Խñ� ���� ó�� ��û
			action = new NoticeUpProcAct();
			break;
		case "/notice_del_proc.brd" :	// �Խñ� ���� ó�� ��û
			action = new NoticeDelProcAct();
			break;
		}
		
		try {
			forward = action.execute(request, response);
			// ó�� �� ������ �̵��� ��ο� ����� �޾ƿ�
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if (forward != null) { // ���� �ƴҰ��
			if (forward.isRedirect()) { 
				response.sendRedirect(forward.getPath());//sendRedirect �� ���ڴٴ� 
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
