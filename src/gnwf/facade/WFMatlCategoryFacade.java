package gnwf.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnwf.dao.WFMatlCategoryDAO;
import gnwf.dao.helper.WFMatlCategoryHelper;
import gnwf.vo.WFMatlCategory;

public class WFMatlCategoryFacade {

	public void save(WFMatlCategory wFMatlCategory) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WFMatlCategoryDAO.class).insert(wFMatlCategory);

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

	public void update(WFMatlCategory wFMatlCategory) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WFMatlCategoryDAO.class).update(wFMatlCategory);

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
			DAOFactory.getDAO(WFMatlCategoryDAO.class).update(sql);
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

	public void submit(WFMatlCategory wFMatlCategory) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WFMatlCategoryDAO.class).update(wFMatlCategory);
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

	public void review(WFMatlCategory wFMatlCategory) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WFMatlCategoryDAO.class).update(wFMatlCategory);
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

	public void confirm(WFMatlCategory wFMatlCategory) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WFMatlCategoryDAO.class).update(wFMatlCategory);
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
			DAOFactory.getDAO(WFMatlCategoryDAO.class).delete(sql);
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

	public void remove(WFMatlCategory wFMatlCategory) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WFMatlCategoryDAO.class).delete(new WFMatlCategoryHelper().getSql4Delete(wFMatlCategory));
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

	public WFMatlCategory findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WFMatlCategory)DAOFactory.getDAO(WFMatlCategoryDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WFMatlCategory findById(WFMatlCategory wFMatlCategory) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WFMatlCategory)DAOFactory.getDAO(WFMatlCategoryDAO.class).load(wFMatlCategory);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WFMatlCategory findBy(WFMatlCategory wFMatlCategory) throws Exception {
		String sql = SqlUtil.getSql4All(WFMatlCategoryHelper.class,wFMatlCategory,WFMatlCategory.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WFMatlCategory)DAOFactory.getDAO(WFMatlCategoryDAO.class).load(sql,WFMatlCategory.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WFMatlCategory> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WFMatlCategory>)DAOFactory.getDAO(WFMatlCategoryDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WFMatlCategory> findAll(WFMatlCategory wFMatlCategory) throws Exception {
		String sql = SqlUtil.getSql4All(WFMatlCategoryHelper.class,wFMatlCategory,WFMatlCategory.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WFMatlCategory>)DAOFactory.getDAO(WFMatlCategoryDAO.class).query(sql, WFMatlCategory.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WFMatlCategory> find(WFMatlCategory wFMatlCategory,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WFMatlCategoryHelper.class,wFMatlCategory,pageVO,WFMatlCategory.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WFMatlCategory>)DAOFactory.getDAO(WFMatlCategoryDAO.class).query(sql, WFMatlCategory.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WFMatlCategory> find(WFMatlCategory wFMatlCategory) throws Exception {
		String sql = SqlUtil.getSql4All(WFMatlCategoryHelper.class,wFMatlCategory,WFMatlCategory.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WFMatlCategory>)DAOFactory.getDAO(WFMatlCategoryDAO.class).query(sql, WFMatlCategory.LIST_FIELDS);
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
			return DAOFactory.getDAO(WFMatlCategoryDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WFMatlCategory wFMatlCategory) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WFMatlCategoryDAO.class).amount(new WFMatlCategoryHelper().getSql4Amount(wFMatlCategory));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}