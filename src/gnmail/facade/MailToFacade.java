package gnmail.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnmail.dao.MailToDAO;
import gnmail.dao.helper.MailToHelper;
import gnmail.vo.MailTo;

public class MailToFacade {

	public void save(MailTo mailTo) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailToDAO.class).insert(mailTo);

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

	public void update(MailTo mailTo) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailToDAO.class).update(mailTo);

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
			DAOFactory.getDAO(MailToDAO.class).update(sql);
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

	public void submit(MailTo mailTo) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailToDAO.class).update(mailTo);
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

	public void review(MailTo mailTo) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailToDAO.class).update(mailTo);
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

	public void confirm(MailTo mailTo) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailToDAO.class).update(mailTo);
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
			DAOFactory.getDAO(MailToDAO.class).delete(sql);
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

	public void remove(MailTo mailTo) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailToDAO.class).delete(new MailToHelper().getSql4Delete(mailTo));
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

	public MailTo findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return (MailTo)DAOFactory.getDAO(MailToDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public MailTo findById(MailTo mailTo) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return (MailTo)DAOFactory.getDAO(MailToDAO.class).load(mailTo);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public MailTo findBy(MailTo mailTo) throws Exception {
		String sql = SqlUtil.getSql4All(MailToHelper.class,mailTo,MailTo.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (MailTo)DAOFactory.getDAO(MailToDAO.class).load(sql,MailTo.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailTo> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailTo>)DAOFactory.getDAO(MailToDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailTo> findAll(MailTo mailTo) throws Exception {
		String sql = SqlUtil.getSql4All(MailToHelper.class,mailTo,MailTo.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailTo>)DAOFactory.getDAO(MailToDAO.class).query(sql, MailTo.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailTo> find(MailTo mailTo,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(MailToHelper.class,mailTo,pageVO,MailTo.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailTo>)DAOFactory.getDAO(MailToDAO.class).query(sql, MailTo.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailTo> find(MailTo mailTo) throws Exception {
		String sql = SqlUtil.getSql4All(MailToHelper.class,mailTo,MailTo.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailTo>)DAOFactory.getDAO(MailToDAO.class).query(sql, MailTo.LIST_FIELDS);
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
			return DAOFactory.getDAO(MailToDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(MailTo mailTo) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return DAOFactory.getDAO(MailToDAO.class).amount(new MailToHelper().getSql4Amount(mailTo));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}