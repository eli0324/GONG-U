package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class NoticeListSvc {
	public int getNoticeCount(String where) {
	// ��Ͽ��� ����� ��ü �Խñ� ������ �����ϴ� �޼ҵ�
		int rcnt = 0;	// ��ü �Խñ� ������ ������ �������� ������ ��
		Connection conn = getConnection();
		NoticeListDao noticeListDao = NoticeListDao.getInstance();
		noticeListDao.setConnection(conn);
		rcnt = noticeListDao.getNoticeCount(where);
		close(conn);

		return rcnt;
	}

	public ArrayList<NoticeList> getNoticeList(String where, int cpage, int psize) {
	// ���ȭ�鿡�� ������ �Խñ� ����� NoticeList�� �ν��Ͻ��� ������ �� �ִ� ArrayList�� �����ϴ� �޼ҵ�
		ArrayList<NoticeList> noticeList = null;
		// �Խñ� ����� ������  ArrayList�� NoticeList�� �ν��Ͻ��� ������
		Connection conn = getConnection();
		NoticeListDao noticeListDao = NoticeListDao.getInstance();
		noticeListDao.setConnection(conn);
		noticeList = noticeListDao.getNoticeList(where, cpage, psize);
		close(conn);

		return noticeList;
	}
}
