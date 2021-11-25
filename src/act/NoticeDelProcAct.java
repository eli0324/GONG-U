package act;

import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import svc.*;
import vo.*;

public class NoticeDelProcAct implements Action {
	   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		      request.setCharacterEncoding("utf-8");
		            
		      int idx = Integer.parseInt(request.getParameter("idx"));	//글번호(필수이므로 바로 형변환 가능)

		      NoticeDelProcSvc noticeDelProcSvc = new NoticeDelProcSvc();
		      // DB를 제외한 비즈니스 로직을 처리하기 위한 인스턴스 생성
		      int result = noticeDelProcSvc.noticeDelete(idx);
		      
		      if (result == 0) // 글 삭제에 실패했으면
		    	  response.setContentType("text/html; charset=utf-8");
		    	  PrintWriter out = response.getWriter();
		    	  out.println("<script>");
		    	  out.println("alert('글삭제에 실패했습니다.\n다시 시도해 주십시오.');");
		    	  out.println("history.back();");  	  
		    	  out.println("</script>");
		      
		      ActionForward forward = new ActionForward();
		      forward.setRedirect(true); //dispatch가 아닌 sendRedirect 방식으로 이동한다는 설정
		      forward.setPath("/gongU/notice_list.brd");
		      
		      return forward;
		   }
		}

