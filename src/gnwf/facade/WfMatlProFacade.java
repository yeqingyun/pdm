package gnwf.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnwf.dao.WfMatlProDAO;
import gnwf.dao.helper.WfMatlProHelper;
import gnwf.vo.WfMatlPro;

public class WfMatlProFacade {

	public void save(WfMatlPro wfMatlPro) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfMatlProDAO.class).insert(wfMatlPro);

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

	public void update(WfMatlPro wfMatlPro) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfMatlProDAO.class).update(wfMatlPro);

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
			DAOFactory.getDAO(WfMatlProDAO.class).update(sql);
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

	public void submit(WfMatlPro wfMatlPro) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfMatlProDAO.class).update(wfMatlPro);
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

	public void review(WfMatlPro wfMatlPro) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfMatlProDAO.class).update(wfMatlPro);
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

	public void confirm(WfMatlPro wfMatlPro) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfMatlProDAO.class).update(wfMatlPro);
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
			DAOFactory.getDAO(WfMatlProDAO.class).delete(sql);
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

	public void remove(WfMatlPro wfMatlPro) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfMatlProDAO.class).delete(new WfMatlProHelper().getSql4Delete(wfMatlPro));
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

	public WfMatlPro findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfMatlPro)DAOFactory.getDAO(WfMatlProDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfMatlPro findById(WfMatlPro wfMatlPro) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfMatlPro)DAOFactory.getDAO(WfMatlProDAO.class).load(wfMatlPro);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfMatlPro findBy(WfMatlPro wfMatlPro) throws Exception {
		String sql = SqlUtil.getSql4All(WfMatlProHelper.class,wfMatlPro,WfMatlPro.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfMatlPro)DAOFactory.getDAO(WfMatlProDAO.class).load(sql,WfMatlPro.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfMatlPro> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfMatlPro>)DAOFactory.getDAO(WfMatlProDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfMatlPro> findAll(WfMatlPro wfMatlPro) throws Exception {
		String sql = SqlUtil.getSql4All(WfMatlProHelper.class,wfMatlPro,WfMatlPro.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfMatlPro>)DAOFactory.getDAO(WfMatlProDAO.class).query(sql, WfMatlPro.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfMatlPro> find(WfMatlPro wfMatlPro,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfMatlProHelper.class,wfMatlPro,pageVO,WfMatlPro.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfMatlPro>)DAOFactory.getDAO(WfMatlProDAO.class).query(sql, WfMatlPro.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfMatlPro> find(WfMatlPro wfMatlPro) throws Exception {
		String sql = SqlUtil.getSql4All(WfMatlProHelper.class,wfMatlPro,WfMatlPro.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfMatlPro>)DAOFactory.getDAO(WfMatlProDAO.class).query(sql, WfMatlPro.LIST_FIELDS);
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
			return DAOFactory.getDAO(WfMatlProDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfMatlPro wfMatlPro) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfMatlProDAO.class).amount(new WfMatlProHelper().getSql4Amount(wfMatlPro));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}