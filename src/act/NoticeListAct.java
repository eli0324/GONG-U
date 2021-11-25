package act;

import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import svc.*;
import vo.*;

public class NoticeListAct implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int cpage = 1, psize = 10, bsize = 10, rcnt = 0, pcnt = 0, spage = 0, epage = 0;
		// ���� ��������ȣ, ������ũ��, ���ũ��, �Խñ� ����, ����������, ����������, ������������ ������ ������

		if (request.getParameter("cpage") != null)	cpage = Integer.parseInt(request.getParameter("cpage"));
		// cpage ���� ���� ��� int������ ����ȯ�Ͽ� ���

		String schtype = request.getParameter("schtype");	// �˻�����(����, ����, ����+����)
		String keyword = request.getParameter("keyword");	// �˻���
		String where = "";	// ������ where���� ������ ����
		if (keyword != null && !keyword.equals("")) {		// keyword(�˻���)�� �����Ѵٸ�
			if (schtype.equals("tc")) {
				where = " where nl_title like '%" + keyword + "%' or nl_content like '%" + keyword + "%' ";
			} else {
				where = " where nl_" + schtype + " like '%" + keyword + "%' ";
			}
		}

		NoticeListSvc noticeListSvc = new NoticeListSvc();
		rcnt = noticeListSvc.getNoticeCount(where);
		// ��ü ���ڵ�(�Խñ�) ������ ���� - �˻������� ���� ��� �� ���ǿ� �´� ���ڵ���� ������ ����
		ArrayList<NoticeList> noticeList = noticeListSvc.getNoticeList(where, cpage, psize);
		// ��� ȭ�鿡�� ������ �Խñ� ����� NoticeList�� �ν��Ͻ��� ���� �� �ִ� ArrayList�� �޾� ��

		pcnt = rcnt / psize;
		if (rcnt % psize > 0)	pcnt++;			// ��ü ������ �����̸鼭 ������ ������ ��ȣ
		spage = (cpage - 1) / bsize * bsize + 1;// ��� ���� ������ ��ȣ
		epage = spage + bsize - 1;
		if (epage > pcnt)	epage = pcnt;		// ��� ���� ������ ��ȣ

		PageInfo pageInfo = new PageInfo();
		pageInfo.setCpage(cpage);	pageInfo.setBsize(bsize);		pageInfo.setEpage(epage);
		pageInfo.setPcnt(pcnt);		pageInfo.setPsize(psize);		pageInfo.setRcnt(rcnt);
		pageInfo.setSpage(spage);	pageInfo.setKeyword(keyword);	pageInfo.setSchtype(schtype);
		// ��� ȭ�鿡�� ����¡�� �˻��� �ʿ��� �������� pageInfo �ν��Ͻ��� ����

		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("noticeList", noticeList);
		// ����¡ ���� �ν��Ͻ��� �Խñ� ��� ArrayList �ν��Ͻ��� request�� �Ӽ�(attribute)�� ������ ä ����ġ ������� �̵�

		ActionForward forward = new ActionForward();
		forward.setPath("/notice_list.jsp");

		return forward;
	}
}
