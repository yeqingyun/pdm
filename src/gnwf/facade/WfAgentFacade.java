package gnwf.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnwf.dao.WfAgentDAO;
import gnwf.dao.helper.WfAgentHelper;
import gnwf.vo.WfAgent;

public class WfAgentFacade {

	public void save(WfAgent wfAgent) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfAgentDAO.class).insert(wfAgent);

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

	public void update(WfAgent wfAgent) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfAgentDAO.class).update(wfAgent);

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
			DAOFactory.getDAO(WfAgentDAO.class).update(sql);
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

	public void submit(WfAgent wfAgent) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfAgentDAO.class).update(wfAgent);
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

	public void review(WfAgent wfAgent) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfAgentDAO.class).update(wfAgent);
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

	public void confirm(WfAgent wfAgent) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfAgentDAO.class).update(wfAgent);
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
			DAOFactory.getDAO(WfAgentDAO.class).delete(sql);
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

	public void remove(WfAgent wfAgent) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfAgentDAO.class).delete(new WfAgentHelper().getSql4Delete(wfAgent));
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

	public WfAgent findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfAgent)DAOFactory.getDAO(WfAgentDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfAgent findById(WfAgent wfAgent) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfAgent)DAOFactory.getDAO(WfAgentDAO.class).load(wfAgent);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfAgent findBy(WfAgent wfAgent) throws Exception {
		String sql = SqlUtil.getSql4All(WfAgentHelper.class,wfAgent,WfAgent.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfAgent)DAOFactory.getDAO(WfAgentDAO.class).load(sql,WfAgent.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfAgent> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfAgent>)DAOFactory.getDAO(WfAgentDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfAgent> findAll(WfAgent wfAgent) throws Exception {
		String sql = SqlUtil.getSql4All(WfAgentHelper.class,wfAgent,WfAgent.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfAgent>)DAOFactory.getDAO(WfAgentDAO.class).query(sql, WfAgent.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfAgent> find(WfAgent wfAgent,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfAgentHelper.class,wfAgent,pageVO,WfAgent.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfAgent>)DAOFactory.getDAO(WfAgentDAO.class).query(sql, WfAgent.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfAgent> find(WfAgent wfAgent) throws Exception {
		String sql = SqlUtil.getSql4All(WfAgentHelper.class,wfAgent,WfAgent.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfAgent>)DAOFactory.getDAO(WfAgentDAO.class).query(sql, WfAgent.LIST_FIELDS);
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
			return DAOFactory.getDAO(WfAgentDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfAgent wfAgent) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfAgentDAO.class).amount(new WfAgentHelper().getSql4Amount(wfAgent));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}