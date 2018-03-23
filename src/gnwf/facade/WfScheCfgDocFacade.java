package gnwf.facade;
import gnwf.dao.WfScheCfgDocDAO;
import gnwf.dao.helper.WfScheCfgDocHelper;
import gnwf.vo.WfScheCfgDoc;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
public class WfScheCfgDocFacade {

	public void save(WfScheCfgDoc wfScheCfgDoc) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfScheCfgDocDAO.class).insert(wfScheCfgDoc);
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

	public void update(WfScheCfgDoc wfScheCfgDoc) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfScheCfgDocDAO.class).update(wfScheCfgDoc);
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
			DAOFactory.getDAO(WfScheCfgDocDAO.class).update(sql);
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

	public void submit(WfScheCfgDoc wfScheCfgDoc) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfScheCfgDocDAO.class).update(wfScheCfgDoc);
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

	public void review(WfScheCfgDoc wfScheCfgDoc) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfScheCfgDocDAO.class).update(wfScheCfgDoc);
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

	public void confirm(WfScheCfgDoc wfScheCfgDoc) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfScheCfgDocDAO.class).update(wfScheCfgDoc);
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
			DAOFactory.getDAO(WfScheCfgDocDAO.class).delete(sql);
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

	public void remove(WfScheCfgDoc wfScheCfgDoc) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfScheCfgDocDAO.class).delete(new WfScheCfgDocHelper().getSql4Delete(wfScheCfgDoc));
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

	public WfScheCfgDoc findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfScheCfgDoc)DAOFactory.getDAO(WfScheCfgDocDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfScheCfgDoc findById(WfScheCfgDoc wfScheCfgDoc) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfScheCfgDoc)DAOFactory.getDAO(WfScheCfgDocDAO.class).load(wfScheCfgDoc);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfScheCfgDoc findBy(WfScheCfgDoc wfScheCfgDoc) throws Exception {
		String sql = SqlUtil.getSql4All(WfScheCfgDocHelper.class,wfScheCfgDoc,WfScheCfgDoc.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfScheCfgDoc)DAOFactory.getDAO(WfScheCfgDocDAO.class).load(sql,WfScheCfgDoc.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfScheCfgDoc> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfScheCfgDoc>)DAOFactory.getDAO(WfScheCfgDocDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public List<WfScheCfgDoc> find(WfScheCfgDoc wfScheCfgDoc) throws Exception {
//		String sql = SqlUtil.getSql4Pages(WfScheCfgDocHelper.class,wfScheCfgDoc,WfScheCfgDoc.LIST_FIELDS);
//		DbConnUtil.buildDbconn(3);
//		try {
//			return (List<WfScheCfgDoc>)DAOFactory.getDAO(WfScheCfgDocDAO.class).query(sql, WfScheCfgDoc.LIST_FIELDS);
//		}
//		catch(Exception e) {
//			throw e;
//		}
//		finally {
//			DbConnUtil.closeDbconn();
//		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<WfScheCfgDoc> findAll(WfScheCfgDoc wfScheCfgDoc) throws Exception {
		String sql = SqlUtil.getSql4All(WfScheCfgDocHelper.class,wfScheCfgDoc,WfScheCfgDoc.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfScheCfgDoc>)DAOFactory.getDAO(WfScheCfgDocDAO.class).query(sql, WfScheCfgDoc.LIST_FIELDS);
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
			return DAOFactory.getDAO(WfScheCfgDocDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfScheCfgDoc wfScheCfgDoc) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfScheCfgDocDAO.class).amount(new WfScheCfgDocHelper().getSql4Amount(wfScheCfgDoc));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}
