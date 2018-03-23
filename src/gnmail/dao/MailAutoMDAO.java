package gnmail.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnmail.dao.helper.MailAutoMHelper;
import gnmail.vo.MailAutoM;

public class MailAutoMDAO extends BasicDAO implements GenericDAO {

	private MailAutoMHelper mailAutoMHelper = new MailAutoMHelper();

	public void insert(Object mailAutoM) throws java.sql.SQLException {
		MailAutoM _mailAutoM = (MailAutoM)mailAutoM;
		String fields = mailAutoMHelper.getFields(_mailAutoM);
		String sql = mailAutoMHelper.getInsertSql(fields);
		try {
			mailAutoMHelper.pstmtInsert(_mailAutoM, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailAutoMDAO.insert", e);
			throw e;
		}
	}

	public void update(Object mailAutoM) throws java.sql.SQLException {
		MailAutoM _mailAutoM = (MailAutoM)mailAutoM;
		String fields = mailAutoMHelper.getFields(_mailAutoM);
		String sql = mailAutoMHelper.getUpdateSql(fields, "MailAutoM.MailId" ,_mailAutoM.getMailId().toString());
		try {
			mailAutoMHelper.pstmtUpdate(_mailAutoM, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailAutoMDAO.update", e);
			throw e;
		}
	}

	public List<MailAutoM> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return mailAutoMHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailAutoMDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<MailAutoM> list = mailAutoMHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (MailAutoM)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailAutoMDAO.load", e);
			throw e;
		}
	}

	public Object load(Object mailAutoM) throws java.sql.SQLException {
		MailAutoM _mailAutoM = (MailAutoM)mailAutoM;
		String sql = "select "+MailAutoM.ALL_FIELDS+mailAutoMHelper.getSqlString()+" and MailAutoM.MailId = '"+_mailAutoM.getMailId()+"'";
		try {
			List<MailAutoM> list = mailAutoMHelper.getQueryList(query(sql),MailAutoM.ALL_FIELDS);
			if(list.size() > 0)
				return (MailAutoM)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailAutoMDAO.load", e);
			throw e;
		}
	}
}