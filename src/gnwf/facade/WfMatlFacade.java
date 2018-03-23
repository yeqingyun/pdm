package gnwf.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnwf.dao.WfMatlDAO;
import gnwf.dao.helper.WfMatlHelper;
import gnwf.vo.WfMatl;

public class WfMatlFacade {

	public void save(WfMatl wfMatl) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfMatlDAO.class).insert(wfMatl);
			if(wfMatl.getWfMatlEvals() != null && wfMatl.getWfMatlEvals().size() > 0) {
				DAOFactory.getDAO(gnwf.dao.WfMatlEvalDAO.class).delete("delete from WfMatlEval where WfMatlEval.MatlId = " +wfMatl.getMatlId());
				for(int i=0; i<wfMatl.getWfMatlEvals().size(); i++) {
					if(wfMatl.getWfMatlEvals().get(i) != null && wfMatl.getWfMatlEvals().get(i).getMatlId() != null) {
						wfMatl.getWfMatlEvals().get(i).setMatlId(wfMatl.getMatlId());
						DAOFactory.getDAO(gnwf.dao.WfMatlEvalDAO.class).insert(wfMatl.getWfMatlEvals().get(i));
					}
				}
			}

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

	public void update(WfMatl wfMatl) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfMatlDAO.class).update(wfMatl);
			if(wfMatl.getWfMatlEvals() != null && wfMatl.getWfMatlEvals().size() > 0) {
				DAOFactory.getDAO(gnwf.dao.WfMatlEvalDAO.class).delete("delete from WfMatlEval where WfMatlEval.MatlId = " +wfMatl.getMatlId());
				for(int i=0; i<wfMatl.getWfMatlEvals().size(); i++) {
					if(wfMatl.getWfMatlEvals().get(i) != null && wfMatl.getWfMatlEvals().get(i).getMatlId() != null) {
						wfMatl.getWfMatlEvals().get(i).setMatlId(wfMatl.getMatlId());
						DAOFactory.getDAO(gnwf.dao.WfMatlEvalDAO.class).insert(wfMatl.getWfMatlEvals().get(i));
					}
				}
			}

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
			DAOFactory.getDAO(WfMatlDAO.class).update(sql);
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

	public void submit(WfMatl wfMatl) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfMatlDAO.class).update(wfMatl);
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

	public void review(WfMatl wfMatl) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfMatlDAO.class).update(wfMatl);
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

	public void confirm(WfMatl wfMatl) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfMatlDAO.class).update(wfMatl);
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
			DAOFactory.getDAO(WfMatlDAO.class).delete(sql);
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

	public void remove(WfMatl wfMatl) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfMatlDAO.class).delete(new WfMatlHelper().getSql4Delete(wfMatl));
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

	public WfMatl findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfMatl)DAOFactory.getDAO(WfMatlDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfMatl findById(WfMatl wfMatl) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfMatl)DAOFactory.getDAO(WfMatlDAO.class).load(wfMatl);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfMatl findBy(WfMatl wfMatl) throws Exception {
		String sql = SqlUtil.getSql4All(WfMatlHelper.class,wfMatl,WfMatl.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfMatl)DAOFactory.getDAO(WfMatlDAO.class).load(sql,WfMatl.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfMatl> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfMatl>)DAOFactory.getDAO(WfMatlDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfMatl> findAll(WfMatl wfMatl) throws Exception {
		String sql = SqlUtil.getSql4All(WfMatlHelper.class,wfMatl,WfMatl.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfMatl>)DAOFactory.getDAO(WfMatlDAO.class).query(sql, WfMatl.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfMatl> find(WfMatl wfMatl,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfMatlHelper.class,wfMatl,pageVO,WfMatl.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfMatl>)DAOFactory.getDAO(WfMatlDAO.class).query(sql, WfMatl.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfMatl> find(WfMatl wfMatl) throws Exception {
		String sql = SqlUtil.getSql4All(WfMatlHelper.class,wfMatl,WfMatl.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfMatl>)DAOFactory.getDAO(WfMatlDAO.class).query(sql, WfMatl.LIST_FIELDS);
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
			return DAOFactory.getDAO(WfMatlDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfMatl wfMatl) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfMatlDAO.class).amount(new WfMatlHelper().getSql4Amount(wfMatl));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}