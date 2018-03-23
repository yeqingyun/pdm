package gnwf.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnwf.dao.WfJobUserRelteDAO;
import gnwf.dao.helper.WfJobUserRelteHelper;
import gnwf.vo.WfJobUserRelte;

public class WfJobUserRelteFacade {

	public void save(WfJobUserRelte wfJobUserRelte) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfJobUserRelteDAO.class).insert(wfJobUserRelte);

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

	public void update(WfJobUserRelte wfJobUserRelte) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfJobUserRelteDAO.class).update(wfJobUserRelte);

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
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfJobUserRelteDAO.class).update(sql);
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

	public void submit(WfJobUserRelte wfJobUserRelte) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfJobUserRelteDAO.class).update(wfJobUserRelte);
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

	public void review(WfJobUserRelte wfJobUserRelte) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfJobUserRelteDAO.class).update(wfJobUserRelte);
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

	public void confirm(WfJobUserRelte wfJobUserRelte) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfJobUserRelteDAO.class).update(wfJobUserRelte);
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
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfJobUserRelteDAO.class).delete(sql);
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

	public void remove(WfJobUserRelte wfJobUserRelte) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfJobUserRelteDAO.class).delete(new WfJobUserRelteHelper().getSql4Delete(wfJobUserRelte));
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

	public WfJobUserRelte findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfJobUserRelte)DAOFactory.getDAO(WfJobUserRelteDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfJobUserRelte findById(WfJobUserRelte wfJobUserRelte) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfJobUserRelte)DAOFactory.getDAO(WfJobUserRelteDAO.class).load(wfJobUserRelte);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfJobUserRelte findBy(WfJobUserRelte wfJobUserRelte) throws Exception {
		String sql = SqlUtil.getSql4All(WfJobUserRelteHelper.class,wfJobUserRelte,WfJobUserRelte.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfJobUserRelte)DAOFactory.getDAO(WfJobUserRelteDAO.class).load(sql,WfJobUserRelte.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfJobUserRelte> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfJobUserRelte>)DAOFactory.getDAO(WfJobUserRelteDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfJobUserRelte> findAll(WfJobUserRelte wfJobUserRelte) throws Exception {
		String sql = SqlUtil.getSql4All(WfJobUserRelteHelper.class,wfJobUserRelte,WfJobUserRelte.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfJobUserRelte>)DAOFactory.getDAO(WfJobUserRelteDAO.class).query(sql, WfJobUserRelte.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfJobUserRelte> find(WfJobUserRelte wfJobUserRelte,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfJobUserRelteHelper.class,wfJobUserRelte,pageVO,WfJobUserRelte.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfJobUserRelte>)DAOFactory.getDAO(WfJobUserRelteDAO.class).query(sql, WfJobUserRelte.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfJobUserRelte> find(WfJobUserRelte wfJobUserRelte) throws Exception {
		String sql = SqlUtil.getSql4All(WfJobUserRelteHelper.class,wfJobUserRelte,WfJobUserRelte.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfJobUserRelte>)DAOFactory.getDAO(WfJobUserRelteDAO.class).query(sql, WfJobUserRelte.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(String sql) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfJobUserRelteDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfJobUserRelte wfJobUserRelte) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfJobUserRelteDAO.class).amount(new WfJobUserRelteHelper().getSql4Amount(wfJobUserRelte));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}