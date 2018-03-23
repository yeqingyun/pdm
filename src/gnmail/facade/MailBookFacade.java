package gnmail.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnmail.dao.MailBookDAO;
import gnmail.dao.helper.MailBookHelper;
import gnmail.vo.MailBook;

public class MailBookFacade {

	public void save(MailBook mailBook) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailBookDAO.class).insert(mailBook);

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

	public void update(MailBook mailBook) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailBookDAO.class).update(mailBook);

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
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailBookDAO.class).update(sql);
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

	public void submit(MailBook mailBook) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailBookDAO.class).update(mailBook);
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

	public void review(MailBook mailBook) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailBookDAO.class).update(mailBook);
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

	public void confirm(MailBook mailBook) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailBookDAO.class).update(mailBook);
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
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailBookDAO.class).delete(sql);
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

	public void remove(MailBook mailBook) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailBookDAO.class).delete(new MailBookHelper().getSql4Delete(mailBook));
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

	public MailBook findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return (MailBook)DAOFactory.getDAO(MailBookDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public MailBook findById(MailBook mailBook) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return (MailBook)DAOFactory.getDAO(MailBookDAO.class).load(mailBook);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public MailBook findBy(MailBook mailBook) throws Exception {
		String sql = SqlUtil.getSql4All(MailBookHelper.class,mailBook,MailBook.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (MailBook)DAOFactory.getDAO(MailBookDAO.class).load(sql,MailBook.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailBook> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailBook>)DAOFactory.getDAO(MailBookDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailBook> findAll(MailBook mailBook) throws Exception {
		String sql = SqlUtil.getSql4All(MailBookHelper.class,mailBook,MailBook.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailBook>)DAOFactory.getDAO(MailBookDAO.class).query(sql, MailBook.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailBook> find(MailBook mailBook,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(MailBookHelper.class,mailBook,pageVO,MailBook.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailBook>)DAOFactory.getDAO(MailBookDAO.class).query(sql, MailBook.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailBook> find(MailBook mailBook) throws Exception {
		String sql = SqlUtil.getSql4All(MailBookHelper.class,mailBook,MailBook.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailBook>)DAOFactory.getDAO(MailBookDAO.class).query(sql, MailBook.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(String sql) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return DAOFactory.getDAO(MailBookDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(MailBook mailBook) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return DAOFactory.getDAO(MailBookDAO.class).amount(new MailBookHelper().getSql4Amount(mailBook));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}