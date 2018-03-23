package gnwf.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnwf.dao.WfDocRevDAO;
import gnwf.dao.helper.WfDocRevHelper;
import gnwf.vo.WfDocRev;

public class WfDocRevFacade {

	public void save(WfDocRev wfDocRev) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfDocRevDAO.class).insert(wfDocRev);

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

	public void update(WfDocRev wfDocRev) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfDocRevDAO.class).update(wfDocRev);

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
			DAOFactory.getDAO(WfDocRevDAO.class).update(sql);
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

	public void submit(WfDocRev wfDocRev) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfDocRevDAO.class).update(wfDocRev);
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

	public void review(WfDocRev wfDocRev) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfDocRevDAO.class).update(wfDocRev);
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

	public void confirm(WfDocRev wfDocRev) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfDocRevDAO.class).update(wfDocRev);
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
			DAOFactory.getDAO(WfDocRevDAO.class).delete(sql);
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

	public void remove(WfDocRev wfDocRev) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfDocRevDAO.class).delete(new WfDocRevHelper().getSql4Delete(wfDocRev));
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

	public WfDocRev findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfDocRev)DAOFactory.getDAO(WfDocRevDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfDocRev findById(WfDocRev wfDocRev) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfDocRev)DAOFactory.getDAO(WfDocRevDAO.class).load(wfDocRev);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfDocRev findBy(WfDocRev wfDocRev) throws Exception {
		String sql = SqlUtil.getSql4All(WfDocRevHelper.class,wfDocRev,WfDocRev.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfDocRev)DAOFactory.getDAO(WfDocRevDAO.class).load(sql,WfDocRev.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfDocRev> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfDocRev>)DAOFactory.getDAO(WfDocRevDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfDocRev> findAll(WfDocRev wfDocRev) throws Exception {
		String sql = SqlUtil.getSql4All(WfDocRevHelper.class,wfDocRev,WfDocRev.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfDocRev>)DAOFactory.getDAO(WfDocRevDAO.class).query(sql, WfDocRev.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfDocRev> find(WfDocRev wfDocRev,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfDocRevHelper.class,wfDocRev,pageVO,WfDocRev.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfDocRev>)DAOFactory.getDAO(WfDocRevDAO.class).query(sql, WfDocRev.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfDocRev> find(WfDocRev wfDocRev) throws Exception {
		String sql = SqlUtil.getSql4All(WfDocRevHelper.class,wfDocRev,WfDocRev.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfDocRev>)DAOFactory.getDAO(WfDocRevDAO.class).query(sql, WfDocRev.LIST_FIELDS);
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
			return DAOFactory.getDAO(WfDocRevDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfDocRev wfDocRev) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfDocRevDAO.class).amount(new WfDocRevHelper().getSql4Amount(wfDocRev));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}