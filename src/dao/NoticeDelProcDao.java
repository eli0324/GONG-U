package dao;

import static db.JdbcUtil.*;      // JdbcUtil Ŭ������ ��� ������� �����Ӱ� ����ϰڴٴ� �ǹ�
import java.util.*;
import javax.sql.*;
import java.sql.*;
import vo.*;

public class NoticeDelProcDao {
	   private static NoticeDelProcDao noticeDelProcDao;
	   private Connection conn;
	   
	   private NoticeDelProcDao() {}
	   public static NoticeDelProcDao getInstance() {
	      // �̱��� ������� �ν��Ͻ� ���� ����
	      if (noticeDelProcDao == null)   noticeDelProcDao = new NoticeDelProcDao();
	      return noticeDelProcDao;
	   }
	   
	   public void setConnection(Connection conn) {
	      this.conn = conn;
	   }
	   
		public int noticeDelete(int idx) {
			Statement stmt = null;
			int result = 0;
			
			try {
				stmt = conn.createStatement();
				String sql = "delete from t_notice_list where nl_idx = " + idx;
				result = stmt.executeUpdate(sql);

				
			} catch(Exception e) {
				System.out.println("NoticeDelProcDao Ŭ������ noticeDelete() �޼ҵ� ����");
				e.printStackTrace();
			}finally {
				close(stmt);
			}
			
			return result;
		}
}

