package gnwf.facade;
import gnwf.dao.AddrBookDAO;
import gnwf.dao.helper.AddrBookHelper;
import gnwf.vo.AddrBook;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DSN;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;

public class AddrBookFacade {

	public void save(AddrBook addrBook) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(AddrBookDAO.class).insert(addrBook);
			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void update(AddrBook addrBook) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(AddrBookDAO.class).update(addrBook);
			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void update(String sql) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(AddrBookDAO.class).update(sql);
			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void submit(AddrBook addrBook) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(AddrBookDAO.class).update(addrBook);
			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void review(AddrBook addrBook) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(AddrBookDAO.class).update(addrBook);
			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void confirm(AddrBook addrBook) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(AddrBookDAO.class).update(addrBook);
			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void remove(String sql) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(AddrBookDAO.class).delete(sql);
			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void remove(AddrBook addrBook) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(AddrBookDAO.class).delete(new AddrBookHelper().getSql4Delete(addrBook));
			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public AddrBook findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			return (AddrBook)DAOFactory.getDAO(AddrBookDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public AddrBook findById(AddrBook addrBook) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			return (AddrBook)DAOFactory.getDAO(AddrBookDAO.class).load(addrBook);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public AddrBook findBy(AddrBook addrBook) throws Exception {
		String sql = SqlUtil.getSql4All(AddrBookHelper.class,addrBook,AddrBook.LIST_FIELDS);
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			return (AddrBook)DAOFactory.getDAO(AddrBookDAO.class).load(sql,AddrBook.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<AddrBook> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			return (List<AddrBook>)DAOFactory.getDAO(AddrBookDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<AddrBook> find(AddrBook addrBook) throws Exception {
//		String sql = SqlUtil.getSql4Pages(AddrBookHelper.class,addrBook,AddrBook.LIST_FIELDS);
//		DbConnUtil.buildDbconn(DSN.DB_CONN);
//		try {
//			return (List<AddrBook>)DAOFactory.getDAO(AddrBookDAO.class).query(sql, AddrBook.LIST_FIELDS);
//		}
//		catch(Exception e) {
//			throw e;
//		}
//		finally {
//			DbConnUtil.closeDbconn();
//		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<AddrBook> findAll(AddrBook addrBook) throws Exception {
		String sql = SqlUtil.getSql4All(AddrBookHelper.class,addrBook,AddrBook.LIST_FIELDS);
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			return (List<AddrBook>)DAOFactory.getDAO(AddrBookDAO.class).query(sql, AddrBook.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(String sql) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			return DAOFactory.getDAO(AddrBookDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(AddrBook addrBook) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			return DAOFactory.getDAO(AddrBookDAO.class).amount(new AddrBookHelper().getSql4Amount(addrBook));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}