package svc;

import static db.JdbcUtil.*;      // JdbcUtil Ŭ������ ��� ������� �����Ӱ� ����ϰڴٴ� �ǹ�
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class NoticeInProcSvc {
   public int noticeInsert(NoticeList notice) {
      int result = 0;
      Connection conn = getConnection();
      NoticeInProcDao noticeInProcDao = NoticeInProcDao.getInstance();
      noticeInProcDao.setConnection(conn);
      result = noticeInProcDao.noticeInsert(notice);
      
      if (result >= 1)   commit(conn);
      else             rollback(conn);
      close(conn);
      
      return result;
   }
}