package gnmail.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnmail.dao.helper.MailTmplGHelper;
import gnmail.vo.MailTmplG;

public class MailTmplGDAO extends BasicDAO implements GenericDAO {

	private MailTmplGHelper mailTmplGHelper = new MailTmplGHelper();

	public void insert(Object mailTmplG) throws java.sql.SQLException {
		MailTmplG _mailTmplG = (MailTmplG)mailTmplG;
		String fields = mailTmplGHelper.getFields(_mailTmplG);
		String sql = mailTmplGHelper.getInsertSql(fields);
		try {
			mailTmplGHelper.pstmtInsert(_mailTmplG, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailTmplGDAO.insert", e);
			throw e;
		}
	}

	public void update(Object mailTmplG) throws java.sql.SQLException {
		MailTmplG _mailTmplG = (MailTmplG)mailTmplG;
		String fields = mailTmplGHelper.getFields(_mailTmplG);
		String sql = mailTmplGHelper.getUpdateSql(fields, "MailTmplG.TmplId" ,_mailTmplG.getTmplId().toString());
		try {
			mailTmplGHelper.pstmtUpdate(_mailTmplG, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailTmplGDAO.update", e);
			throw e;
		}
	}

	public List<MailTmplG> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return mailTmplGHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailTmplGDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<MailTmplG> list = mailTmplGHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (MailTmplG)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailTmplGDAO.load", e);
			throw e;
		}
	}

	public Object load(Object mailTmplG) throws java.sql.SQLException {
		MailTmplG _mailTmplG = (MailTmplG)mailTmplG;
		String sql = "select "+MailTmplG.ALL_FIELDS+mailTmplGHelper.getSqlString()+" and MailTmplG.TmplId = '"+_mailTmplG.getTmplId()+"'";
		try {
			List<MailTmplG> list = mailTmplGHelper.getQueryList(query(sql),MailTmplG.ALL_FIELDS);
			if(list.size() > 0)
				return (MailTmplG)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailTmplGDAO.load", e);
			throw e;
		}
	}
}