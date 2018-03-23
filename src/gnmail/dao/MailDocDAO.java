package gnmail.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnmail.dao.helper.MailDocHelper;
import gnmail.vo.MailDoc;

public class MailDocDAO extends BasicDAO implements GenericDAO {

	private MailDocHelper mailDocHelper = new MailDocHelper();

	public void insert(Object mailDoc) throws java.sql.SQLException {
		MailDoc _mailDoc = (MailDoc)mailDoc;
		String fields = mailDocHelper.getFields(_mailDoc);
		String sql = mailDocHelper.getInsertSql(fields);
		try {
			mailDocHelper.pstmtInsert(_mailDoc, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailDocDAO.insert", e);
			throw e;
		}
	}

	public void update(Object mailDoc) throws java.sql.SQLException {
		MailDoc _mailDoc = (MailDoc)mailDoc;
		String fields = mailDocHelper.getFields(_mailDoc);
		String sql = mailDocHelper.getUpdateSql(fields, "MailDoc.DocId" ,_mailDoc.getDocId().toString());
		try {
			mailDocHelper.pstmtUpdate(_mailDoc, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailDocDAO.update", e);
			throw e;
		}
	}

	public List<MailDoc> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return mailDocHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailDocDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<MailDoc> list = mailDocHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (MailDoc)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailDocDAO.load", e);
			throw e;
		}
	}

	public Object load(Object mailDoc) throws java.sql.SQLException {
		MailDoc _mailDoc = (MailDoc)mailDoc;
		String sql = "select "+MailDoc.ALL_FIELDS+mailDocHelper.getSqlString()+" and MailDoc.DocId = '"+_mailDoc.getDocId()+"'";
		try {
			List<MailDoc> list = mailDocHelper.getQueryList(query(sql),MailDoc.ALL_FIELDS);
			if(list.size() > 0)
				return (MailDoc)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailDocDAO.load", e);
			throw e;
		}
	}
}