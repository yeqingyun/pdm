package gnmail.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnmail.dao.MailTmplGDAO;
import gnmail.dao.helper.MailTmplGHelper;
import gnmail.vo.MailTmplG;

public class MailTmplGFacade {

	public void save(MailTmplG mailTmplG) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailTmplGDAO.class).insert(mailTmplG);

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

	public void update(MailTmplG mailTmplG) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailTmplGDAO.class).update(mailTmplG);

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
			DAOFactory.getDAO(MailTmplGDAO.class).update(sql);
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

	public void submit(MailTmplG mailTmplG) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailTmplGDAO.class).update(mailTmplG);
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

	public void review(MailTmplG mailTmplG) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailTmplGDAO.class).update(mailTmplG);
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

	public void confirm(MailTmplG mailTmplG) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailTmplGDAO.class).update(mailTmplG);
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
			DAOFactory.getDAO(MailTmplGDAO.class).delete(sql);
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

	public void remove(MailTmplG mailTmplG) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailTmplGDAO.class).delete(new MailTmplGHelper().getSql4Delete(mailTmplG));
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

	public MailTmplG findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return (MailTmplG)DAOFactory.getDAO(MailTmplGDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public MailTmplG findById(MailTmplG mailTmplG) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return (MailTmplG)DAOFactory.getDAO(MailTmplGDAO.class).load(mailTmplG);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public MailTmplG findBy(MailTmplG mailTmplG) throws Exception {
		String sql = SqlUtil.getSql4All(MailTmplGHelper.class,mailTmplG,MailTmplG.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (MailTmplG)DAOFactory.getDAO(MailTmplGDAO.class).load(sql,MailTmplG.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailTmplG> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailTmplG>)DAOFactory.getDAO(MailTmplGDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailTmplG> findAll(MailTmplG mailTmplG) throws Exception {
		String sql = SqlUtil.getSql4All(MailTmplGHelper.class,mailTmplG,MailTmplG.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailTmplG>)DAOFactory.getDAO(MailTmplGDAO.class).query(sql, MailTmplG.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailTmplG> find(MailTmplG mailTmplG,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(MailTmplGHelper.class,mailTmplG,pageVO,MailTmplG.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailTmplG>)DAOFactory.getDAO(MailTmplGDAO.class).query(sql, MailTmplG.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailTmplG> find(MailTmplG mailTmplG) throws Exception {
		String sql = SqlUtil.getSql4All(MailTmplGHelper.class,mailTmplG,MailTmplG.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailTmplG>)DAOFactory.getDAO(MailTmplGDAO.class).query(sql, MailTmplG.LIST_FIELDS);
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
			return DAOFactory.getDAO(MailTmplGDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(MailTmplG mailTmplG) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return DAOFactory.getDAO(MailTmplGDAO.class).amount(new MailTmplGHelper().getSql4Amount(mailTmplG));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}