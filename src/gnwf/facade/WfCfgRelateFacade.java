package gnwf.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnwf.dao.WfCfgRelateDAO;
import gnwf.dao.helper.WfCfgRelateHelper;
import gnwf.vo.WfCfgRelate;

public class WfCfgRelateFacade {

	public void save(WfCfgRelate wfCfgRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfCfgRelateDAO.class).insert(wfCfgRelate);

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

	public void update(WfCfgRelate wfCfgRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfCfgRelateDAO.class).update(wfCfgRelate);

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
			DAOFactory.getDAO(WfCfgRelateDAO.class).update(sql);
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

	public void submit(WfCfgRelate wfCfgRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfCfgRelateDAO.class).update(wfCfgRelate);
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

	public void review(WfCfgRelate wfCfgRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfCfgRelateDAO.class).update(wfCfgRelate);
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

	public void confirm(WfCfgRelate wfCfgRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfCfgRelateDAO.class).update(wfCfgRelate);
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
			DAOFactory.getDAO(WfCfgRelateDAO.class).delete(sql);
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

	public void remove(WfCfgRelate wfCfgRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfCfgRelateDAO.class).delete(new WfCfgRelateHelper().getSql4Delete(wfCfgRelate));
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

	public WfCfgRelate findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfCfgRelate)DAOFactory.getDAO(WfCfgRelateDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfCfgRelate findById(WfCfgRelate wfCfgRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfCfgRelate)DAOFactory.getDAO(WfCfgRelateDAO.class).load(wfCfgRelate);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfCfgRelate findBy(WfCfgRelate wfCfgRelate) throws Exception {
		String sql = SqlUtil.getSql4All(WfCfgRelateHelper.class,wfCfgRelate,WfCfgRelate.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfCfgRelate)DAOFactory.getDAO(WfCfgRelateDAO.class).load(sql,WfCfgRelate.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfCfgRelate> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfCfgRelate>)DAOFactory.getDAO(WfCfgRelateDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfCfgRelate> findAll(WfCfgRelate wfCfgRelate) throws Exception {
		String sql = SqlUtil.getSql4All(WfCfgRelateHelper.class,wfCfgRelate,WfCfgRelate.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfCfgRelate>)DAOFactory.getDAO(WfCfgRelateDAO.class).query(sql, WfCfgRelate.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfCfgRelate> find(WfCfgRelate wfCfgRelate,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfCfgRelateHelper.class,wfCfgRelate,pageVO,WfCfgRelate.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfCfgRelate>)DAOFactory.getDAO(WfCfgRelateDAO.class).query(sql, WfCfgRelate.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfCfgRelate> find(WfCfgRelate wfCfgRelate) throws Exception {
		String sql = SqlUtil.getSql4All(WfCfgRelateHelper.class,wfCfgRelate,WfCfgRelate.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfCfgRelate>)DAOFactory.getDAO(WfCfgRelateDAO.class).query(sql, WfCfgRelate.LIST_FIELDS);
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
			return DAOFactory.getDAO(WfCfgRelateDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfCfgRelate wfCfgRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfCfgRelateDAO.class).amount(new WfCfgRelateHelper().getSql4Amount(wfCfgRelate));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}