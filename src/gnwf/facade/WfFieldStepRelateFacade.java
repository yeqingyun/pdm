package gnwf.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnwf.dao.WfFieldStepRelateDAO;
import gnwf.dao.helper.WfFieldStepRelateHelper;
import gnwf.vo.WfFieldStepRelate;

public class WfFieldStepRelateFacade {

	public void save(WfFieldStepRelate wfFieldStepRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfFieldStepRelateDAO.class).insert(wfFieldStepRelate);

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

	public void update(WfFieldStepRelate wfFieldStepRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfFieldStepRelateDAO.class).update(wfFieldStepRelate);

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
			DAOFactory.getDAO(WfFieldStepRelateDAO.class).update(sql);
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

	public void submit(WfFieldStepRelate wfFieldStepRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfFieldStepRelateDAO.class).update(wfFieldStepRelate);
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

	public void review(WfFieldStepRelate wfFieldStepRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfFieldStepRelateDAO.class).update(wfFieldStepRelate);
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

	public void confirm(WfFieldStepRelate wfFieldStepRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfFieldStepRelateDAO.class).update(wfFieldStepRelate);
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
			DAOFactory.getDAO(WfFieldStepRelateDAO.class).delete(sql);
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

	public void remove(WfFieldStepRelate wfFieldStepRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfFieldStepRelateDAO.class).delete(new WfFieldStepRelateHelper().getSql4Delete(wfFieldStepRelate));
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

	public WfFieldStepRelate findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfFieldStepRelate)DAOFactory.getDAO(WfFieldStepRelateDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfFieldStepRelate findById(WfFieldStepRelate wfFieldStepRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfFieldStepRelate)DAOFactory.getDAO(WfFieldStepRelateDAO.class).load(wfFieldStepRelate);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfFieldStepRelate findBy(WfFieldStepRelate wfFieldStepRelate) throws Exception {
		String sql = SqlUtil.getSql4All(WfFieldStepRelateHelper.class,wfFieldStepRelate,WfFieldStepRelate.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfFieldStepRelate)DAOFactory.getDAO(WfFieldStepRelateDAO.class).load(sql,WfFieldStepRelate.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfFieldStepRelate> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfFieldStepRelate>)DAOFactory.getDAO(WfFieldStepRelateDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfFieldStepRelate> findAll(WfFieldStepRelate wfFieldStepRelate) throws Exception {
		String sql = SqlUtil.getSql4All(WfFieldStepRelateHelper.class,wfFieldStepRelate,WfFieldStepRelate.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfFieldStepRelate>)DAOFactory.getDAO(WfFieldStepRelateDAO.class).query(sql, WfFieldStepRelate.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfFieldStepRelate> find(WfFieldStepRelate wfFieldStepRelate,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfFieldStepRelateHelper.class,wfFieldStepRelate,pageVO,WfFieldStepRelate.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfFieldStepRelate>)DAOFactory.getDAO(WfFieldStepRelateDAO.class).query(sql, WfFieldStepRelate.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfFieldStepRelate> find(WfFieldStepRelate wfFieldStepRelate) throws Exception {
		String sql = SqlUtil.getSql4All(WfFieldStepRelateHelper.class,wfFieldStepRelate,WfFieldStepRelate.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfFieldStepRelate>)DAOFactory.getDAO(WfFieldStepRelateDAO.class).query(sql, WfFieldStepRelate.LIST_FIELDS);
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
			return DAOFactory.getDAO(WfFieldStepRelateDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfFieldStepRelate wfFieldStepRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfFieldStepRelateDAO.class).amount(new WfFieldStepRelateHelper().getSql4Amount(wfFieldStepRelate));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}