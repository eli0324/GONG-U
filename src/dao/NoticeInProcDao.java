package dao;

import static db.JdbcUtil.*;      // JdbcUtil 클래스의 모든 멤버들을 자유롭게 사용하겠다는 의미
import java.util.*;
import javax.sql.*;
import java.sql.*;
import vo.*;

public class NoticeInProcDao {
   private static NoticeInProcDao noticeInProcDao;
   private Connection conn;
   
   private NoticeInProcDao() {}
   public static NoticeInProcDao getInstance() {
      // 싱글톤 방식으로 인스턴스 낭비를 줄임
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
				//insert에 성공했으면 result에 글번호를 저장하여 리턴(글보기 화면으로 이동시 글번호가 필요하므로 )
				
			} catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(rs);		close(stmt);
			}
			
			return result;
		}
}
