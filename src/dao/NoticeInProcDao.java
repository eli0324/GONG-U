package dao;

import static db.JdbcUtil.*;      // JdbcUtil Ŭ������ ��� ������� �����Ӱ� ����ϰڴٴ� �ǹ�
import java.util.*;
import javax.sql.*;
import java.sql.*;
import vo.*;

public class NoticeInProcDao {
   private static NoticeInProcDao noticeInProcDao;
   private Connection conn;
   
   private NoticeInProcDao() {}
   public static NoticeInProcDao getInstance() {
      // �̱��� ������� �ν��Ͻ� ���� ����
      if (noticeInProcDao == null)   noticeInProcDao = new NoticeInProcDao();
      return noticeInProcDao;
   }
   
   public void setConnection(Connection conn) {
      this.conn = conn;
   }

		
		public int noticeInsert(NoticeList notice) {
			Statement stmt = null;
			ResultSet rs = null;
			int result = 0, nIdx = 1;
			
			try {
				stmt = conn.createStatement();
				String sql = "select max(nl_idx) from t_notice_list";
				rs = stmt.executeQuery(sql);
				if(rs.next()) nIdx = rs.getInt(1) + 1;
				
				sql = "insert into t_notice_list (nl_idx, nl_kind, nl_title, nl_content, ai_idx) values (" + 
				nIdx + ", '" + notice.getNl_kind() + "', '" + notice.getNl_title() + "', '" + notice.getNl_content() + "', 1)";
				result = stmt.executeUpdate(sql);
				if (result == 1) result = nIdx;
				//insert�� ���������� result�� �۹�ȣ�� �����Ͽ� ����(�ۺ��� ȭ������ �̵��� �۹�ȣ�� �ʿ��ϹǷ� )
				
			} catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(rs);		close(stmt);
			}
			
			return result;
		}
}
