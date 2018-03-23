package gnhr.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnhr.dao.HrJobCfgDAO;
import gnhr.dao.helper.HrJobCfgHelper;
import gnhr.vo.HrJobCfg;

public class HrJobCfgFacade {

	public void save(HrJobCfg hrJobCfg) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(HrJobCfgDAO.class).insert(hrJobCfg);

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

	public void update(HrJobCfg hrJobCfg) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(HrJobCfgDAO.class).update(hrJobCfg);

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
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(HrJobCfgDAO.class).update(sql);
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

	public void submit(HrJobCfg hrJobCfg) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(HrJobCfgDAO.class).update(hrJobCfg);
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

	public void review(HrJobCfg hrJobCfg) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(HrJobCfgDAO.class).update(hrJobCfg);
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

	public void confirm(HrJobCfg hrJobCfg) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(HrJobCfgDAO.class).update(hrJobCfg);
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
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(HrJobCfgDAO.class).delete(sql);
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

	public void remove(HrJobCfg hrJobCfg) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(HrJobCfgDAO.class).delete(new HrJobCfgHelper().getSql4Delete(hrJobCfg));
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

	public HrJobCfg findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return (HrJobCfg)DAOFactory.getDAO(HrJobCfgDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public HrJobCfg findById(HrJobCfg hrJobCfg) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return (HrJobCfg)DAOFactory.getDAO(HrJobCfgDAO.class).load(hrJobCfg);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public HrJobCfg findBy(HrJobCfg hrJobCfg) throws Exception {
		String sql = SqlUtil.getSql4All(HrJobCfgHelper.class,hrJobCfg,HrJobCfg.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (HrJobCfg)DAOFactory.getDAO(HrJobCfgDAO.class).load(sql,HrJobCfg.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<HrJobCfg> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return (List<HrJobCfg>)DAOFactory.getDAO(HrJobCfgDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<HrJobCfg> findAll(HrJobCfg hrJobCfg) throws Exception {
		String sql = SqlUtil.getSql4All(HrJobCfgHelper.class,hrJobCfg,HrJobCfg.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (List<HrJobCfg>)DAOFactory.getDAO(HrJobCfgDAO.class).query(sql, HrJobCfg.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<HrJobCfg> find(HrJobCfg hrJobCfg,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(HrJobCfgHelper.class,hrJobCfg,pageVO,HrJobCfg.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (List<HrJobCfg>)DAOFactory.getDAO(HrJobCfgDAO.class).query(sql, HrJobCfg.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<HrJobCfg> find(HrJobCfg hrJobCfg) throws Exception {
		String sql = SqlUtil.getSql4All(HrJobCfgHelper.class,hrJobCfg,HrJobCfg.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (List<HrJobCfg>)DAOFactory.getDAO(HrJobCfgDAO.class).query(sql, HrJobCfg.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(String sql) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return DAOFactory.getDAO(HrJobCfgDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(HrJobCfg hrJobCfg) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return DAOFactory.getDAO(HrJobCfgDAO.class).amount(new HrJobCfgHelper().getSql4Amount(hrJobCfg));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}