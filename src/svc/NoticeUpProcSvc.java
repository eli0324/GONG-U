package svc;

import static db.JdbcUtil.*;      // JdbcUtil Ŭ������ ��� ������� �����Ӱ� ����ϰڴٴ� �ǹ�
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class NoticeUpProcSvc {
	   public int noticeUpdate(NoticeList notice) {
		      int result = 0;
		      Connection conn = getConnection();
		      NoticeUpProcDao noticeUpProcDao = NoticeUpProcDao.getInstance();
		      noticeUpProcDao.setConnection(conn);
		      result = noticeUpProcDao.noticeUpdate(notice);
		      
		      if (result >= 1)   commit(conn);
		      else             	 rollback(conn);
		      close(conn);
		      
		      return result;
		   }
		}
