package gnmail.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnmail.dao.helper.MailHelper;
import gnmail.vo.Mail;

public class MailDAO extends BasicDAO implements GenericDAO {

	private MailHelper mailHelper = new MailHelper();

	public void insert(Object mail) throws java.sql.SQLException {
		Mail _mail = (Mail)mail;
		String fields = mailHelper.getFields(_mail);
		String sql = mailHelper.getInsertSql(fields);
		try {
			mailHelper.pstmtInsert(_mail, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailDAO.insert", e);
			throw e;
		}
	}
	
	public int save(Object mail) throws java.sql.SQLException {
		int mailId = -1;
		Mail _mail = (Mail)mail;
		String fields = mailHelper.getFields(_mail);
		String sql = mailHelper.getInsertSql(fields);
		try {
			mailId = mailHelper.pstmtInsert(_mail, sql, fields);
			return mailId;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailDAO.insert", e);
			throw e;
		}
	}

	public void update(Object mail) throws java.sql.SQLException {
		Mail _mail = (Mail)mail;
		String fields = mailHelper.getFields(_mail);
		String sql = mailHelper.getUpdateSql(fields, "Mail.MailId" ,_mail.getMailId().toString());
		try {
			mailHelper.pstmtUpdate(_mail, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailDAO.update", e);
			throw e;
		}
	}

	public List<Mail> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return mailHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<Mail> list = mailHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (Mail)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailDAO.load", e);
			throw e;
		}
	}

	public Object load(Object mail) throws java.sql.SQLException {
		Mail _mail = (Mail)mail;
		String sql = "select "+Mail.ALL_FIELDS+mailHelper.getSqlString()+" and Mail.MailId = '"+_mail.getMailId()+"'";
		try {
			List<Mail> list = mailHelper.getQueryList(query(sql),Mail.ALL_FIELDS);
			if(list.size() > 0)
				return (Mail)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailDAO.load", e);
			throw e;
		}
	}
}