package dao;

import static db.JdbcUtil.*;      // JdbcUtil 클래스의 모든 멤버들을 자유롭게 사용하겠다는 의미
import java.util.*;
import javax.sql.*;
import java.sql.*;
import vo.*;

public class NoticeDelProcDao {
	   private static NoticeDelProcDao noticeDelProcDao;
	   private Connection conn;
	   
	   private NoticeDelProcDao() {}
	   public static NoticeDelProcDao getInstance() {
	      // 싱글톤 방식으로 인스턴스 낭비를 줄임
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
				System.out.println("NoticeDelProcDao 클래스의 noticeDelete() 메소드 오류");
				e.printStackTrace();
			}finally {
				close(stmt);
			}
			
			return result;
		}
}

