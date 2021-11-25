package dao;

import static db.JdbcUtil.*;      // JdbcUtil Ŭ������ ��� ������� �����Ӱ� ����ϰڴٴ� �ǹ�
import java.util.*;
import javax.sql.*;
import java.sql.*;
import vo.*;

public class NoticeListDao {
	  private static NoticeListDao noticeListDao;
	   private Connection conn;
	   
	   private NoticeListDao() {}  
	   
	   public static NoticeListDao getInstance() { 
	      if (noticeListDao == null)   noticeListDao = new NoticeListDao();
	      return noticeListDao;
	   }
	   
	   public void setConnection(Connection conn) {
	      this.conn = conn;
	   }

	   public int getNoticeCount(String where) {
		  //���ǿ� �´� �Խñ��� �� ������ �����ϴ� �޼ҵ�
			Statement stmt = null;
			ResultSet rs = null;
			int rcnt = 0; 	// ������ ��(���ڵ� ����)�� ������ ������ ���� ���� ��츦 ����Ͽ� 0���� �ʱ�ȭ 
			
			
			try {
				stmt = conn.createStatement();
				String sql = " select count(*) from t_notice_list " + where;
				System.out.println(sql);
				rs = stmt.executeQuery(sql);
				if(rs.next()) rcnt = rs.getInt(1);			
			} catch(Exception e) {
				System.out.println("NoticeListDao Ŭ������getNoticeCount() �޼ҵ� ���� ");
				e.printStackTrace();
			} finally {
				close(rs);	close(stmt); // ������� �ݾ������ 
			}
			
			return rcnt;
		}
		public ArrayList<NoticeList> getNoticeList(String where, int cpage, int psize) {
			//��Ͽ��� ������ �Խñ� ����� ArrayList<NoticeList> �� �����ϴ� �޼ҵ� 
			Statement stmt = null;  //������ ���� ������ �����ϰ� ���ִ� ��
			ResultSet rs = null;	// ����Ʈ���� �� �޾Ƽ� �۾��� ��
			ArrayList<NoticeList> noticeList = new ArrayList<NoticeList>();
			// ArrayList�� size()�޼ҵ带 �̿��ϸ� ArrayList �ȿ� �����Ͱ� ���� ��� �˾Ƴ� �� �ֱ� ������ ������ ä�� �۾��ص� �� 
			NoticeList notice = null; // �ϳ��� �Խñ� �������� ������ �ν��Ͻ��� ArrayList�� ����� ��ü 
			
			try {
				stmt = conn.createStatement();
				int snum = (cpage - 1) * psize;
				String sql = "select nl_idx, nl_kind, nl_title, nl_readcnt, nl_date from t_notice_list " + 
						where + " order by nl_idx desc limit " + snum + ", " + psize; 
				System.out.println(sql);
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					notice = new NoticeList();	 // ArrayList�� ������ NoticeList�� �ν��Ͻ� ����
					notice.setNl_idx(rs.getInt("nl_idx"));
					notice.setNl_kind(rs.getString("nl_kind"));
					notice.setNl_title(rs.getString("nl_title"));
					notice.setNl_readcnt(rs.getInt("nl_readcnt"));
					notice.setNl_date(rs.getString("nl_date"));
					
					noticeList.add(notice);
				} // rs�� ����ִ� ���ڵ���� NoticeList�� �ν��Ͻ��� ������ �� ArrayList�� noticeList�����ʴ�� ����
					
			} catch(Exception e) {
				System.out.println("NoticeListDao Ŭ������getNoticeList() �޼ҵ� ���� ");
				e.printStackTrace();
			}
			
			return noticeList;
		}
}



