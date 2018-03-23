package gnmail.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnmail.dao.MailAutoMDAO;
import gnmail.dao.helper.MailAutoMHelper;
import gnmail.vo.MailAutoM;

public class MailAutoMFacade {

	public void save(MailAutoM mailAutoM) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailAutoMDAO.class).insert(mailAutoM);

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

	public void update(MailAutoM mailAutoM) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailAutoMDAO.class).update(mailAutoM);

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
			DAOFactory.getDAO(MailAutoMDAO.class).update(sql);
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

	public void submit(MailAutoM mailAutoM) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailAutoMDAO.class).update(mailAutoM);
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

	public void review(MailAutoM mailAutoM) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailAutoMDAO.class).update(mailAutoM);
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

	public void confirm(MailAutoM mailAutoM) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailAutoMDAO.class).update(mailAutoM);
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
			DAOFactory.getDAO(MailAutoMDAO.class).delete(sql);
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

	public void remove(MailAutoM mailAutoM) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailAutoMDAO.class).delete(new MailAutoMHelper().getSql4Delete(mailAutoM));
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

	public MailAutoM findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return (MailAutoM)DAOFactory.getDAO(MailAutoMDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public MailAutoM findById(MailAutoM mailAutoM) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return (MailAutoM)DAOFactory.getDAO(MailAutoMDAO.class).load(mailAutoM);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public MailAutoM findBy(MailAutoM mailAutoM) throws Exception {
		String sql = SqlUtil.getSql4All(MailAutoMHelper.class,mailAutoM,MailAutoM.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (MailAutoM)DAOFactory.getDAO(MailAutoMDAO.class).load(sql,MailAutoM.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailAutoM> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailAutoM>)DAOFactory.getDAO(MailAutoMDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailAutoM> findAll(MailAutoM mailAutoM) throws Exception {
		String sql = SqlUtil.getSql4All(MailAutoMHelper.class,mailAutoM,MailAutoM.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailAutoM>)DAOFactory.getDAO(MailAutoMDAO.class).query(sql, MailAutoM.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailAutoM> find(MailAutoM mailAutoM,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(MailAutoMHelper.class,mailAutoM,pageVO,MailAutoM.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailAutoM>)DAOFactory.getDAO(MailAutoMDAO.class).query(sql, MailAutoM.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailAutoM> find(MailAutoM mailAutoM) throws Exception {
		String sql = SqlUtil.getSql4All(MailAutoMHelper.class,mailAutoM,MailAutoM.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailAutoM>)DAOFactory.getDAO(MailAutoMDAO.class).query(sql, MailAutoM.LIST_FIELDS);
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
			return DAOFactory.getDAO(MailAutoMDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(MailAutoM mailAutoM) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return DAOFactory.getDAO(MailAutoMDAO.class).amount(new MailAutoMHelper().getSql4Amount(mailAutoM));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}