package gnwf.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import org.frm.jdbc.DbConnUtil;

import gnwf.dao.helper.WfReplyHelper;
import gnwf.vo.WfReply;

public class WfReplyDAO extends BasicDAO implements GenericDAO {

	private WfReplyHelper wfReplyHelper = new WfReplyHelper();

	public void insert(Object wfReply) throws java.sql.SQLException {
		WfReply _wfReply = (WfReply)wfReply;
		String fields = wfReplyHelper.getFields(_wfReply);
		String sql = wfReplyHelper.getInsertSql(fields);
		try {
			wfReplyHelper.pstmtInsert(_wfReply, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfReplyDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfReply) throws java.sql.SQLException {
		WfReply _wfReply = (WfReply)wfReply;
		String fields = wfReplyHelper.getFields(_wfReply);
		String sql = wfReplyHelper.getUpdateSql(fields, "WfReply.ReplyId" ,_wfReply.getReplyId().toString());
		try {
			wfReplyHelper.pstmtUpdate(_wfReply, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfReplyDAO.update", e);
			throw e;
		}
	}

	public List<WfReply> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfReplyHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfReplyDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfReply> list = wfReplyHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfReply)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfReplyDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfReply) throws java.sql.SQLException {
		WfReply _wfReply = (WfReply)wfReply;
		String sql = "select "+WfReply.ALL_FIELDS+wfReplyHelper.getSqlString()+" and WfReply.ReplyId = '"+_wfReply.getReplyId()+"'";
		try {
			List<WfReply> list = wfReplyHelper.getQueryList(query(sql),WfReply.ALL_FIELDS);
			if(list.size() > 0)
				return (WfReply)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfReplyDAO.load", e);
			throw e;
		}
	}
	
	public int getMaxQuesReplyId (String sql) throws Exception{
		int maxId =0;
		try {
			DbConnUtil.buildDbconn(3);
			ResultSet  rs = query(sql);
			while(rs.next()){
				maxId = rs.getInt("ReplyId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maxId;
	}
}