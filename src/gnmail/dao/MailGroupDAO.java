package gnmail.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnmail.dao.helper.MailGroupHelper;
import gnmail.vo.MailGroup;

public class MailGroupDAO extends BasicDAO implements GenericDAO {

	private MailGroupHelper mailGroupHelper = new MailGroupHelper();

	public void insert(Object mailGroup) throws java.sql.SQLException {
		MailGroup _mailGroup = (MailGroup)mailGroup;
		String fields = mailGroupHelper.getFields(_mailGroup);
		String sql = mailGroupHelper.getInsertSql(fields);
		try {
			mailGroupHelper.pstmtInsert(_mailGroup, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailGroupDAO.insert", e);
			throw e;
		}
	}

	public void update(Object mailGroup) throws java.sql.SQLException {
		MailGroup _mailGroup = (MailGroup)mailGroup;
		String fields = mailGroupHelper.getFields(_mailGroup);
		String sql = mailGroupHelper.getUpdateSql(fields, "MailGroup.GroupId" ,_mailGroup.getGroupId().toString());
		try {
			mailGroupHelper.pstmtUpdate(_mailGroup, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailGroupDAO.update", e);
			throw e;
		}
	}

	public List<MailGroup> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return mailGroupHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailGroupDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<MailGroup> list = mailGroupHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (MailGroup)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailGroupDAO.load", e);
			throw e;
		}
	}

	public Object load(Object mailGroup) throws java.sql.SQLException {
		MailGroup _mailGroup = (MailGroup)mailGroup;
		String sql = "select "+MailGroup.ALL_FIELDS+mailGroupHelper.getSqlString()+" and MailGroup.GroupId = '"+_mailGroup.getGroupId()+"'";
		try {
			List<MailGroup> list = mailGroupHelper.getQueryList(query(sql),MailGroup.ALL_FIELDS);
			if(list.size() > 0)
				return (MailGroup)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailGroupDAO.load", e);
			throw e;
		}
	}
}