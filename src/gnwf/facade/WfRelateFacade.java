package gnwf.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnwf.dao.WfRelateDAO;
import gnwf.dao.helper.WfRelateHelper;
import gnwf.vo.WfRelate;

public class WfRelateFacade {

	public void save(WfRelate wfRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRelateDAO.class).insert(wfRelate);

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

	public void update(WfRelate wfRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRelateDAO.class).update(wfRelate);

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
			DAOFactory.getDAO(WfRelateDAO.class).update(sql);
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

	public void submit(WfRelate wfRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRelateDAO.class).update(wfRelate);
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

	public void review(WfRelate wfRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRelateDAO.class).update(wfRelate);
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

	public void confirm(WfRelate wfRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRelateDAO.class).update(wfRelate);
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
			DAOFactory.getDAO(WfRelateDAO.class).delete(sql);
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

	public void remove(WfRelate wfRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRelateDAO.class).delete(new WfRelateHelper().getSql4Delete(wfRelate));
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

	public WfRelate findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfRelate)DAOFactory.getDAO(WfRelateDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfRelate findById(WfRelate wfRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfRelate)DAOFactory.getDAO(WfRelateDAO.class).load(wfRelate);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfRelate findBy(WfRelate wfRelate) throws Exception {
		String sql = SqlUtil.getSql4All(WfRelateHelper.class,wfRelate,WfRelate.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfRelate)DAOFactory.getDAO(WfRelateDAO.class).load(sql,WfRelate.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRelate> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRelate>)DAOFactory.getDAO(WfRelateDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRelate> findAll(WfRelate wfRelate) throws Exception {
		String sql = SqlUtil.getSql4All(WfRelateHelper.class,wfRelate,WfRelate.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRelate>)DAOFactory.getDAO(WfRelateDAO.class).query(sql, WfRelate.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRelate> find(WfRelate wfRelate,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfRelateHelper.class,wfRelate,pageVO,WfRelate.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRelate>)DAOFactory.getDAO(WfRelateDAO.class).query(sql, WfRelate.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRelate> find(WfRelate wfRelate) throws Exception {
		String sql = SqlUtil.getSql4All(WfRelateHelper.class,wfRelate,WfRelate.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRelate>)DAOFactory.getDAO(WfRelateDAO.class).query(sql, WfRelate.LIST_FIELDS);
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
			return DAOFactory.getDAO(WfRelateDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfRelate wfRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfRelateDAO.class).amount(new WfRelateHelper().getSql4Amount(wfRelate));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}