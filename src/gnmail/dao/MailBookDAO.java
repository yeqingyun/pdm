package gnmail.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnmail.dao.helper.MailBookHelper;
import gnmail.vo.MailBook;

public class MailBookDAO extends BasicDAO implements GenericDAO {

	private MailBookHelper mailBookHelper = new MailBookHelper();

	public void insert(Object mailBook) throws java.sql.SQLException {
		MailBook _mailBook = (MailBook)mailBook;
		String fields = mailBookHelper.getFields(_mailBook);
		String sql = mailBookHelper.getInsertSql(fields);
		try {
			mailBookHelper.pstmtInsert(_mailBook, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailBookDAO.insert", e);
			throw e;
		}
	}

	public void update(Object mailBook) throws java.sql.SQLException {
		MailBook _mailBook = (MailBook)mailBook;
		String fields = mailBookHelper.getFields(_mailBook);
		String sql = mailBookHelper.getUpdateSql(fields, "MailBook.BookId" ,_mailBook.getBookId().toString());
		try {
			mailBookHelper.pstmtUpdate(_mailBook, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailBookDAO.update", e);
			throw e;
		}
	}

	public List<MailBook> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return mailBookHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailBookDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<MailBook> list = mailBookHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (MailBook)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailBookDAO.load", e);
			throw e;
		}
	}

	public Object load(Object mailBook) throws java.sql.SQLException {
		MailBook _mailBook = (MailBook)mailBook;
		String sql = "select "+MailBook.ALL_FIELDS+mailBookHelper.getSqlString()+" and MailBook.BookId = '"+_mailBook.getBookId()+"'";
		try {
			List<MailBook> list = mailBookHelper.getQueryList(query(sql),MailBook.ALL_FIELDS);
			if(list.size() > 0)
				return (MailBook)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailBookDAO.load", e);
			throw e;
		}
	}
}