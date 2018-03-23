package gnmail.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnmail.dao.MailDocDAO;
import gnmail.dao.helper.MailDocHelper;
import gnmail.vo.MailDoc;

public class MailDocFacade {

	public void save(MailDoc mailDoc) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailDocDAO.class).insert(mailDoc);

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

	public void update(MailDoc mailDoc) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailDocDAO.class).update(mailDoc);

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
			DAOFactory.getDAO(MailDocDAO.class).update(sql);
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

	public void submit(MailDoc mailDoc) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailDocDAO.class).update(mailDoc);
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

	public void review(MailDoc mailDoc) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailDocDAO.class).update(mailDoc);
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

	public void confirm(MailDoc mailDoc) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailDocDAO.class).update(mailDoc);
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
			DAOFactory.getDAO(MailDocDAO.class).delete(sql);
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

	public void remove(MailDoc mailDoc) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailDocDAO.class).delete(new MailDocHelper().getSql4Delete(mailDoc));
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

	public MailDoc findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return (MailDoc)DAOFactory.getDAO(MailDocDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public MailDoc findById(MailDoc mailDoc) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return (MailDoc)DAOFactory.getDAO(MailDocDAO.class).load(mailDoc);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public MailDoc findBy(MailDoc mailDoc) throws Exception {
		String sql = SqlUtil.getSql4All(MailDocHelper.class,mailDoc,MailDoc.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (MailDoc)DAOFactory.getDAO(MailDocDAO.class).load(sql,MailDoc.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailDoc> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailDoc>)DAOFactory.getDAO(MailDocDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailDoc> findAll(MailDoc mailDoc) throws Exception {
		String sql = SqlUtil.getSql4All(MailDocHelper.class,mailDoc,MailDoc.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailDoc>)DAOFactory.getDAO(MailDocDAO.class).query(sql, MailDoc.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailDoc> find(MailDoc mailDoc,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(MailDocHelper.class,mailDoc,pageVO,MailDoc.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailDoc>)DAOFactory.getDAO(MailDocDAO.class).query(sql, MailDoc.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailDoc> find(MailDoc mailDoc) throws Exception {
		String sql = SqlUtil.getSql4All(MailDocHelper.class,mailDoc,MailDoc.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailDoc>)DAOFactory.getDAO(MailDocDAO.class).query(sql, MailDoc.LIST_FIELDS);
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
			return DAOFactory.getDAO(MailDocDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(MailDoc mailDoc) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return DAOFactory.getDAO(MailDocDAO.class).amount(new MailDocHelper().getSql4Amount(mailDoc));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}