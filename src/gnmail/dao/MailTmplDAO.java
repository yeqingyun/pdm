package gnmail.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnmail.dao.helper.MailTmplHelper;
import gnmail.vo.MailTmpl;

public class MailTmplDAO extends BasicDAO implements GenericDAO {

	private MailTmplHelper mailTmplHelper = new MailTmplHelper();

	public void insert(Object mailTmpl) throws java.sql.SQLException {
		MailTmpl _mailTmpl = (MailTmpl)mailTmpl;
		String fields = mailTmplHelper.getFields(_mailTmpl);
		String sql = mailTmplHelper.getInsertSql(fields);
		try {
			mailTmplHelper.pstmtInsert(_mailTmpl, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailTmplDAO.insert", e);
			throw e;
		}
	}

	public void update(Object mailTmpl) throws java.sql.SQLException {
		MailTmpl _mailTmpl = (MailTmpl)mailTmpl;
		String fields = mailTmplHelper.getFields(_mailTmpl);
		String sql = mailTmplHelper.getUpdateSql(fields, "MailTmpl.TmplId" ,_mailTmpl.getTmplId().toString());
		try {
			mailTmplHelper.pstmtUpdate(_mailTmpl, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailTmplDAO.update", e);
			throw e;
		}
	}

	public List<MailTmpl> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return mailTmplHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailTmplDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<MailTmpl> list = mailTmplHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (MailTmpl)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailTmplDAO.load", e);
			throw e;
		}
	}

	public Object load(Object mailTmpl) throws java.sql.SQLException {
		MailTmpl _mailTmpl = (MailTmpl)mailTmpl;
		String sql = "select "+MailTmpl.ALL_FIELDS+mailTmplHelper.getSqlString()+" and MailTmpl.TmplId = '"+_mailTmpl.getTmplId()+"'";
		try {
			List<MailTmpl> list = mailTmplHelper.getQueryList(query(sql),MailTmpl.ALL_FIELDS);
			if(list.size() > 0)
				return (MailTmpl)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailTmplDAO.load", e);
			throw e;
		}
	}
}