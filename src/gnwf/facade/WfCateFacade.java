package gnwf.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnwf.dao.WfCateDAO;
import gnwf.dao.helper.WfCateHelper;
import gnwf.vo.WfCate;

public class WfCateFacade {

	public void save(WfCate wfCate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfCateDAO.class).insert(wfCate);
//			if(wfCate.getWfCfgs() != null && wfCate.getWfCfgs().size() > 0) {
//				DAOFactory.getDAO(gnwf.dao.WfCfgDAO.class).delete("delete from WfCfg where WfCfg.CateId = " +wfCate.getCateId());
//				for(int i=0; i<wfCate.getWfCfgs().size(); i++) {
//					if(wfCate.getWfCfgs().get(i) != null && wfCate.getWfCfgs().get(i).getCateId() != null) {
//						wfCate.getWfCfgs().get(i).setCateId(wfCate.getCateId());
//						DAOFactory.getDAO(gnwf.dao.WfCfgDAO.class).insert(wfCate.getWfCfgs().get(i));
//					}
//				}
//			}

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

	public void update(WfCate wfCate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfCateDAO.class).update(wfCate);
//			if(wfCate.getWfCfgs() != null && wfCate.getWfCfgs().size() > 0) {
//				DAOFactory.getDAO(gnwf.dao.WfCfgDAO.class).delete("delete from WfCfg where WfCfg.CateId = " +wfCate.getCateId());
//				for(int i=0; i<wfCate.getWfCfgs().size(); i++) {
//					if(wfCate.getWfCfgs().get(i) != null && wfCate.getWfCfgs().get(i).getCateId() != null) {
//						wfCate.getWfCfgs().get(i).setCateId(wfCate.getCateId());
//						DAOFactory.getDAO(gnwf.dao.WfCfgDAO.class).insert(wfCate.getWfCfgs().get(i));
//					}
//				}
//			}

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
			DAOFactory.getDAO(WfCateDAO.class).update(sql);
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

	public void submit(WfCate wfCate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfCateDAO.class).update(wfCate);
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

	public void review(WfCate wfCate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfCateDAO.class).update(wfCate);
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

	public void confirm(WfCate wfCate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfCateDAO.class).update(wfCate);
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
			DAOFactory.getDAO(WfCateDAO.class).delete(sql);
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

	public void remove(WfCate wfCate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfCateDAO.class).delete(new WfCateHelper().getSql4Delete(wfCate));
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

	public WfCate findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfCate)DAOFactory.getDAO(WfCateDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfCate findById(WfCate wfCate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfCate)DAOFactory.getDAO(WfCateDAO.class).load(wfCate);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfCate findBy(WfCate wfCate) throws Exception {
		String sql = SqlUtil.getSql4All(WfCateHelper.class,wfCate,WfCate.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfCate)DAOFactory.getDAO(WfCateDAO.class).load(sql,WfCate.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfCate> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfCate>)DAOFactory.getDAO(WfCateDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfCate> findAll(WfCate wfCate) throws Exception {
		String sql = SqlUtil.getSql4All(WfCateHelper.class,wfCate,WfCate.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfCate>)DAOFactory.getDAO(WfCateDAO.class).query(sql, WfCate.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfCate> find(WfCate wfCate,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfCateHelper.class,wfCate,pageVO,WfCate.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfCate>)DAOFactory.getDAO(WfCateDAO.class).query(sql, WfCate.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfCate> find(WfCate wfCate) throws Exception {
		String sql = SqlUtil.getSql4All(WfCateHelper.class,wfCate,WfCate.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfCate>)DAOFactory.getDAO(WfCateDAO.class).query(sql, WfCate.LIST_FIELDS);
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
			return DAOFactory.getDAO(WfCateDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfCate wfCate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfCateDAO.class).amount(new WfCateHelper().getSql4Amount(wfCate));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}