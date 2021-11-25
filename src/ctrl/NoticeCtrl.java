package ctrl;

import java.io.*;
import javax.servlet.*;	//
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;	// request, response, session 객체를 사용하기위해 import
import act.*;
import vo.*;

@WebServlet("*.brd")	// 공지사항 관련 모든 요청을 처리하는 서블릿 클래스
public class NoticeCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public NoticeCtrl() {
        super();
    }
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//get과 post 두가지 모두의 요청을 처리하는 메소드
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
		case "/notice_list.brd" :		// 게시글 목록 요청
			action = new NoticeListAct();
			break;
		case "/notice_in_form.brd" :	// 게시글 등록 폼 요청
			action = new NoticeInAct();
			break;
		case "/notice_in_proc.brd" :	// 게시글 처리 요청
			action = new NoticeInProcAct();
			break;
		case "/notice_preview.brd" :		// 게시들 조회수 증가 요청 
			action = new NoticePreviewAct();
			break;
		case "/notice_view.brd" :		// 게시들 보기 화면 요청
			action = new NoticeViewAct();
			break;
		case "/notice_up_form.brd" :	// 게시글 수정 폼 요청
			action = new NoticeUpAct();
			break;
		case "/notice_up_proc.brd" :	// 게시글 수정 처리 요청
			action = new NoticeUpProcAct();
			break;
		case "/notice_del_proc.brd" :	// 게시글 삭제 처리 요청
			action = new NoticeDelProcAct();
			break;
		}
		
		try {
			forward = action.execute(request, response);
			// 처리 및 실행후 이동할 경로와 방법을 받아옴
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if (forward != null) { // 널이 아닐경우
			if (forward.isRedirect()) { 
				response.sendRedirect(forward.getPath());//sendRedirect 로 가겠다는 
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
