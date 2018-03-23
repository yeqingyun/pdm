package gnwf.dao;
import gnwf.dao.helper.MailToHelper;
import gnwf.vo.MailTo;

import java.util.List;

import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
public class MailToDAO extends BasicDAO implements GenericDAO {

	private MailToHelper mailToHelper = new MailToHelper();

	public void insert(Object mailTo) throws java.sql.SQLException {
		MailTo _mailTo = (MailTo)mailTo;
		String fields = mailToHelper.getFields(_mailTo);
		String sql = mailToHelper.getInsertSql(fields);
		try {
			mailToHelper.pstmtInsert(_mailTo, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailToDAO.insert", e);
			throw e;
		}
	}

	public void update(Object mailTo) throws java.sql.SQLException {
		MailTo _mailTo = (MailTo)mailTo;
		String fields = mailToHelper.getFields(_mailTo);
		String sql = mailToHelper.getUpdateSql(fields, "MailTo.MailId" ,_mailTo.getMailId().toString());
		try {
			mailToHelper.pstmtUpdate(_mailTo, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailToDAO.update", e);
			throw e;
		}
	}

	public List<MailTo> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return mailToHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailToDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<MailTo> list = mailToHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (MailTo)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailToDAO.load", e);
			throw e;
		}
	}

	public Object load(Object mailTo) throws java.sql.SQLException {
		MailTo _mailTo = (MailTo)mailTo;
		String sql = "select "+MailTo.ALL_FIELDS+mailToHelper.getSqlString()+" and MailTo.MailId = '"+_mailTo.getMailId()+"'";
		try {
			List<MailTo> list = mailToHelper.getQueryList(query(sql),MailTo.ALL_FIELDS);
			if(list.size() > 0)
				return (MailTo)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailToDAO.load", e);
			throw e;
		}
	}
}
