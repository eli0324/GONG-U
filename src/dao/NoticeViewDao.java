package dao;

import static db.JdbcUtil.*;      // JdbcUtil Ŭ������ ��� ������� �����Ӱ� ����ϰڴٴ� �ǹ�
import java.util.*;
import javax.sql.*;
import java.sql.*;
import vo.*;


public class NoticeViewDao {
	  private static NoticeViewDao noticeViewDao;
	   private Connection conn;
	   
	   private NoticeViewDao() {} // ������ �� private �̸� �ܺο��� �����Ҽ� ����
	   //�ܺο����� �Ժη� �ν��Ͻ��� �������� ���ϰ� private���� �����ڸ� ������ 
	   
	   public static NoticeViewDao getInstance() { // �ν��Ͻ� ȣ���ϸ�
	      // �̱��� ������� �ν��Ͻ� ���� ����
	      if (noticeViewDao == null)   noticeViewDao = new NoticeViewDao();
	      // ������ �ν��ϰ� ��� ������ ���Ӱ� ������ �ʰ�, ���� ��쿡�� ���� �����ϰ� ��  
	      return noticeViewDao;
	   }
	   
	   public void setConnection(Connection conn) {
	      this.conn = conn;
	   }
	   
	   public NoticeList getNoticeInfo(int idx) {
		  //������ �۹�ȣ(idx)�� �ش��ϴ� �Խñ� ������ NoticeList�� �ν��Ͻ��� ��� �����ϴ� �޼ҵ�
			Statement stmt = null;
			ResultSet rs = null;
			NoticeList notice = null; //�����Ͱ� ���� ��� null�� �����ϰ� �� 
			
			try {
				stmt = conn.createStatement();
				String sql = "select * from t_notice_list where nl_idx = " + idx;
				rs = stmt.executeQuery(sql);
				if(rs.next()) {
					notice = new NoticeList();	 // rs�� ��� �����͵��� ������ �ν��Ͻ� ���� 
					notice.setNl_idx(idx);
					notice.setNl_kind(rs.getString("nl_kind"));
					notice.setNl_title(rs.getString("nl_title"));
					notice.setNl_content(rs.getString("nl_content"));
					notice.setNl_readcnt(rs.getInt("nl_readcnt"));
					notice.setNl_date(rs.getString("nl_date"));
					notice.setAi_idx(rs.getInt("ai_idx"));
				} // rs�� ������� else ���� �׳� notice�� null�� ����ִ� ���·� ������ 
					
			} catch(Exception e) {
				System.out.println("NoticeViewDao Ŭ������getNoticeInfo() �޼ҵ� ���� ");
				e.printStackTrace();
			}
			
			return notice;
		}
}


