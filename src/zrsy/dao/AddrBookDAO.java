package zrsy.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrsy.dao.helper.AddrBookHelper;
import zrsy.vo.AddrBook;

public class AddrBookDAO extends BasicDAO implements GenericDAO {

	private AddrBookHelper addrBookHelper = new AddrBookHelper();

	public void insert(Object addrBook) throws java.sql.SQLException {
		AddrBook _addrBook = (AddrBook)addrBook;
		String fields = addrBookHelper.getFields(_addrBook);
		String sql = addrBookHelper.getInsertSql(fields);
		try {
			addrBookHelper.pstmtInsert(_addrBook, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("AddrBookDAO.insert", e);
			throw e;
		}
	}

	public void update(Object addrBook) throws java.sql.SQLException {
		AddrBook _addrBook = (AddrBook)addrBook;
		String fields = addrBookHelper.getFields(_addrBook);
		String sql = addrBookHelper.getUpdateSql(fields, "AddrBook.BookId" ,_addrBook.getBookId().toString());
		try {
			addrBookHelper.pstmtUpdate(_addrBook, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("AddrBookDAO.update", e);
			throw e;
		}
	}

	public List<AddrBook> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return addrBookHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("AddrBookDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<AddrBook> list = addrBookHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (AddrBook)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("AddrBookDAO.load", e);
			throw e;
		}
	}

	public Object load(Object addrBook) throws java.sql.SQLException {
		AddrBook _addrBook = (AddrBook)addrBook;
		String sql = "select "+AddrBook.ALL_FIELDS+addrBookHelper.getSqlString()+" and AddrBook.BookId = '"+_addrBook.getBookId()+"'";
		try {
			List<AddrBook> list = addrBookHelper.getQueryList(query(sql),AddrBook.ALL_FIELDS);
			if(list.size() > 0)
				return (AddrBook)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("AddrBookDAO.load", e);
			throw e;
		}
	}
}