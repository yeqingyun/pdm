package gnwf.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnwf.dao.WfMatlEvalDAO;
import gnwf.dao.helper.WfMatlEvalHelper;
import gnwf.vo.WfMatlEval;

public class WfMatlEvalFacade {

	public void save(WfMatlEval wfMatlEval) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfMatlEvalDAO.class).insert(wfMatlEval);

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

	public void update(WfMatlEval wfMatlEval) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfMatlEvalDAO.class).update(wfMatlEval);

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
			DAOFactory.getDAO(WfMatlEvalDAO.class).update(sql);
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

	public void submit(WfMatlEval wfMatlEval) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfMatlEvalDAO.class).update(wfMatlEval);
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

	public void review(WfMatlEval wfMatlEval) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfMatlEvalDAO.class).update(wfMatlEval);
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

	public void confirm(WfMatlEval wfMatlEval) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfMatlEvalDAO.class).update(wfMatlEval);
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
			DAOFactory.getDAO(WfMatlEvalDAO.class).delete(sql);
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

	public void remove(WfMatlEval wfMatlEval) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfMatlEvalDAO.class).delete(new WfMatlEvalHelper().getSql4Delete(wfMatlEval));
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

	public WfMatlEval findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfMatlEval)DAOFactory.getDAO(WfMatlEvalDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfMatlEval findById(WfMatlEval wfMatlEval) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfMatlEval)DAOFactory.getDAO(WfMatlEvalDAO.class).load(wfMatlEval);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfMatlEval findBy(WfMatlEval wfMatlEval) throws Exception {
		String sql = SqlUtil.getSql4All(WfMatlEvalHelper.class,wfMatlEval,WfMatlEval.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfMatlEval)DAOFactory.getDAO(WfMatlEvalDAO.class).load(sql,WfMatlEval.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfMatlEval> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfMatlEval>)DAOFactory.getDAO(WfMatlEvalDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfMatlEval> findAll(WfMatlEval wfMatlEval) throws Exception {
		String sql = SqlUtil.getSql4All(WfMatlEvalHelper.class,wfMatlEval,WfMatlEval.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfMatlEval>)DAOFactory.getDAO(WfMatlEvalDAO.class).query(sql, WfMatlEval.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfMatlEval> find(WfMatlEval wfMatlEval,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfMatlEvalHelper.class,wfMatlEval,pageVO,WfMatlEval.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfMatlEval>)DAOFactory.getDAO(WfMatlEvalDAO.class).query(sql, WfMatlEval.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfMatlEval> find(WfMatlEval wfMatlEval) throws Exception {
		String sql = SqlUtil.getSql4All(WfMatlEvalHelper.class,wfMatlEval,WfMatlEval.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfMatlEval>)DAOFactory.getDAO(WfMatlEvalDAO.class).query(sql, WfMatlEval.LIST_FIELDS);
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
			return DAOFactory.getDAO(WfMatlEvalDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfMatlEval wfMatlEval) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfMatlEvalDAO.class).amount(new WfMatlEvalHelper().getSql4Amount(wfMatlEval));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}