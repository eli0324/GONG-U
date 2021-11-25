package dao;

import static db.JdbcUtil.*;      // JdbcUtil Ŭ������ ��� ������� �����Ӱ� ����ϰڴٴ� �ǹ�
import java.util.*;
import javax.sql.*;
import java.sql.*;
import vo.*;

public class NoticePreviewDao {
	   private static NoticePreviewDao noticePreviewDao;
	   private Connection conn;
	   
	   private NoticePreviewDao() {}
	   
	   public static NoticePreviewDao getInstance() {
	      // �̱��� ������� �ν��Ͻ� ���� ����
	      if (noticePreviewDao == null)   noticePreviewDao = new NoticePreviewDao();
	      return noticePreviewDao;
	   }
	   
	   public void setConnection(Connection conn) {
	      this.conn = conn;
	   }
	   
	   public int readcntUpdate(int idx) {
		   //Ư�� �Խñ��� ��ȸ���� 1���� ��Ű�� �޼ҵ� 
			Statement stmt = null;	//���� ����� �ν��Ͻ�
			int result = 0;			// ����(update) ��� ����� ����
			
			try {
				stmt = conn.createStatement();
				String sql = "update t_notice_list set nl_readcnt = nl_readcnt + 1 where nl_idx = " + idx;
				result = stmt.executeUpdate(sql);
				
			} catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(stmt);
			}	
			return result;
		   }
}
