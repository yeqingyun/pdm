package gnwf.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnwf.dao.WfStepNextDAO;
import gnwf.dao.helper.WfStepNextHelper;
import gnwf.vo.WfStepNext;

public class WfStepNextFacade {

	public void save(WfStepNext wfStepNext) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfStepNextDAO.class).insert(wfStepNext);

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

	public void update(WfStepNext wfStepNext) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfStepNextDAO.class).update(wfStepNext);

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
			DAOFactory.getDAO(WfStepNextDAO.class).update(sql);
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

	public void submit(WfStepNext wfStepNext) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfStepNextDAO.class).update(wfStepNext);
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

	public void review(WfStepNext wfStepNext) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfStepNextDAO.class).update(wfStepNext);
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

	public void confirm(WfStepNext wfStepNext) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfStepNextDAO.class).update(wfStepNext);
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
			DAOFactory.getDAO(WfStepNextDAO.class).delete(sql);
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

	public void remove(WfStepNext wfStepNext) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfStepNextDAO.class).delete(new WfStepNextHelper().getSql4Delete(wfStepNext));
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

	public WfStepNext findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfStepNext)DAOFactory.getDAO(WfStepNextDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfStepNext findById(WfStepNext wfStepNext) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfStepNext)DAOFactory.getDAO(WfStepNextDAO.class).load(wfStepNext);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfStepNext findBy(WfStepNext wfStepNext) throws Exception {
		String sql = SqlUtil.getSql4All(WfStepNextHelper.class,wfStepNext,WfStepNext.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfStepNext)DAOFactory.getDAO(WfStepNextDAO.class).load(sql,WfStepNext.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfStepNext> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfStepNext>)DAOFactory.getDAO(WfStepNextDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfStepNext> findAll(WfStepNext wfStepNext) throws Exception {
		String sql = SqlUtil.getSql4All(WfStepNextHelper.class,wfStepNext,WfStepNext.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfStepNext>)DAOFactory.getDAO(WfStepNextDAO.class).query(sql, WfStepNext.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfStepNext> find(WfStepNext wfStepNext,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfStepNextHelper.class,wfStepNext,pageVO,WfStepNext.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfStepNext>)DAOFactory.getDAO(WfStepNextDAO.class).query(sql, WfStepNext.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfStepNext> find(WfStepNext wfStepNext) throws Exception {
		String sql = SqlUtil.getSql4All(WfStepNextHelper.class,wfStepNext,WfStepNext.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfStepNext>)DAOFactory.getDAO(WfStepNextDAO.class).query(sql, WfStepNext.LIST_FIELDS);
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
			return DAOFactory.getDAO(WfStepNextDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfStepNext wfStepNext) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfStepNextDAO.class).amount(new WfStepNextHelper().getSql4Amount(wfStepNext));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}