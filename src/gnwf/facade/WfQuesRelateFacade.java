package gnwf.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnwf.dao.WfQuesRelateDAO;
import gnwf.dao.helper.WfQuesRelateHelper;
import gnwf.vo.WfQuesRelate;

public class WfQuesRelateFacade {

	public void save(WfQuesRelate wfQuesRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfQuesRelateDAO.class).insert(wfQuesRelate);

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

	public void update(WfQuesRelate wfQuesRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfQuesRelateDAO.class).update(wfQuesRelate);

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
			DAOFactory.getDAO(WfQuesRelateDAO.class).update(sql);
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

	public void submit(WfQuesRelate wfQuesRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfQuesRelateDAO.class).update(wfQuesRelate);
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

	public void review(WfQuesRelate wfQuesRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfQuesRelateDAO.class).update(wfQuesRelate);
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

	public void confirm(WfQuesRelate wfQuesRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfQuesRelateDAO.class).update(wfQuesRelate);
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
			DAOFactory.getDAO(WfQuesRelateDAO.class).delete(sql);
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

	public void remove(WfQuesRelate wfQuesRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfQuesRelateDAO.class).delete(new WfQuesRelateHelper().getSql4Delete(wfQuesRelate));
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

	public WfQuesRelate findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfQuesRelate)DAOFactory.getDAO(WfQuesRelateDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfQuesRelate findById(WfQuesRelate wfQuesRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfQuesRelate)DAOFactory.getDAO(WfQuesRelateDAO.class).load(wfQuesRelate);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfQuesRelate findBy(WfQuesRelate wfQuesRelate) throws Exception {
		String sql = SqlUtil.getSql4All(WfQuesRelateHelper.class,wfQuesRelate,WfQuesRelate.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfQuesRelate)DAOFactory.getDAO(WfQuesRelateDAO.class).load(sql,WfQuesRelate.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfQuesRelate> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfQuesRelate>)DAOFactory.getDAO(WfQuesRelateDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfQuesRelate> findAll(WfQuesRelate wfQuesRelate) throws Exception {
		String sql = SqlUtil.getSql4All(WfQuesRelateHelper.class,wfQuesRelate,WfQuesRelate.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfQuesRelate>)DAOFactory.getDAO(WfQuesRelateDAO.class).query(sql, WfQuesRelate.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfQuesRelate> find(WfQuesRelate wfQuesRelate,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfQuesRelateHelper.class,wfQuesRelate,pageVO,WfQuesRelate.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfQuesRelate>)DAOFactory.getDAO(WfQuesRelateDAO.class).query(sql, WfQuesRelate.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfQuesRelate> find(WfQuesRelate wfQuesRelate) throws Exception {
		String sql = SqlUtil.getSql4All(WfQuesRelateHelper.class,wfQuesRelate,WfQuesRelate.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfQuesRelate>)DAOFactory.getDAO(WfQuesRelateDAO.class).query(sql, WfQuesRelate.LIST_FIELDS);
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
			return DAOFactory.getDAO(WfQuesRelateDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfQuesRelate wfQuesRelate) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfQuesRelateDAO.class).amount(new WfQuesRelateHelper().getSql4Amount(wfQuesRelate));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}