package gnwf.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnwf.dao.WfLeaderDAO;
import gnwf.dao.helper.WfLeaderHelper;
import gnwf.vo.WfLeader;

public class WfLeaderFacade {

	public void save(WfLeader wfLeader) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfLeaderDAO.class).insert(wfLeader);

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

	public void update(WfLeader wfLeader) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfLeaderDAO.class).update(wfLeader);

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
			DAOFactory.getDAO(WfLeaderDAO.class).update(sql);
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

	public void submit(WfLeader wfLeader) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfLeaderDAO.class).update(wfLeader);
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

	public void review(WfLeader wfLeader) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfLeaderDAO.class).update(wfLeader);
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

	public void confirm(WfLeader wfLeader) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfLeaderDAO.class).update(wfLeader);
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
			DAOFactory.getDAO(WfLeaderDAO.class).delete(sql);
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

	public void remove(WfLeader wfLeader) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfLeaderDAO.class).delete(new WfLeaderHelper().getSql4Delete(wfLeader));
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

	public WfLeader findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfLeader)DAOFactory.getDAO(WfLeaderDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfLeader findById(WfLeader wfLeader) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfLeader)DAOFactory.getDAO(WfLeaderDAO.class).load(wfLeader);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfLeader findBy(WfLeader wfLeader) throws Exception {
		String sql = SqlUtil.getSql4All(WfLeaderHelper.class,wfLeader,WfLeader.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfLeader)DAOFactory.getDAO(WfLeaderDAO.class).load(sql,WfLeader.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfLeader> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfLeader>)DAOFactory.getDAO(WfLeaderDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfLeader> findAll(WfLeader wfLeader) throws Exception {
		String sql = SqlUtil.getSql4All(WfLeaderHelper.class,wfLeader,WfLeader.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfLeader>)DAOFactory.getDAO(WfLeaderDAO.class).query(sql, WfLeader.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfLeader> find(WfLeader wfLeader,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfLeaderHelper.class,wfLeader,pageVO,WfLeader.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfLeader>)DAOFactory.getDAO(WfLeaderDAO.class).query(sql, WfLeader.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfLeader> find(WfLeader wfLeader) throws Exception {
		String sql = SqlUtil.getSql4All(WfLeaderHelper.class,wfLeader,WfLeader.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfLeader>)DAOFactory.getDAO(WfLeaderDAO.class).query(sql, WfLeader.LIST_FIELDS);
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
			return DAOFactory.getDAO(WfLeaderDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfLeader wfLeader) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfLeaderDAO.class).amount(new WfLeaderHelper().getSql4Amount(wfLeader));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}