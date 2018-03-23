package zrprjt.facade;

import java.sql.ResultSet;
import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrprjt.dao.DriverDAO;
import zrprjt.dao.DriverDtlDAO;
import zrprjt.dao.helper.DriverHelper;
import zrprjt.vo.Driver;
import zrprjt.vo.DriverDtl;

public class DriverFacade {

	public void save(Driver driver) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			ResultSet rs = new DriverDAO().query("select max(DriveId) as max_id from Driver");
			int pk = 0;
			if(rs != null && rs.next()) {
				pk = rs.getInt("max_id");
			}
			driver.setDriveId(pk + 1);
			DAOFactory.getDAO(DriverDAO.class).insert(driver);
			if(driver.getDriverDtls() != null && driver.getDriverDtls().size() > 0) {
				DAOFactory.getDAO(DriverDtlDAO.class).delete("delete from DriverDtl where DriverDtl.DriveId = " +driver.getDriveId() );
				for(int i=0; i<driver.getDriverDtls().size(); i++) {
					DriverDtl dtl = driver.getDriverDtls().get(i);
					dtl.setDriveId(pk+ 1);
					DAOFactory.getDAO(DriverDtlDAO.class).insert(dtl);
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

	public void update(Driver driver) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DriverDAO.class).update(driver);
			if(driver.getDriverDtls() != null && driver.getDriverDtls().size() > 0) {
				DAOFactory.getDAO(DriverDtlDAO.class).delete("delete from DriverDtl where DriverDtl.DriveId = " +driver.getDriveId() );
				for(int i=0; i<driver.getDriverDtls().size(); i++) {
					DriverDtl dtl = driver.getDriverDtls().get(i);
					dtl.setDriveId(driver.getDriveId());
					DAOFactory.getDAO(DriverDtlDAO.class).insert(dtl);
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
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DriverDAO.class).update(sql);
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

	public void submit(Driver driver) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DriverDAO.class).update(driver);
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

	public void review(Driver driver) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DriverDAO.class).update(driver);
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

	public void confirm(Driver driver) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DriverDAO.class).update(driver);
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
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DriverDAO.class).delete(sql);
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

	public void remove(Driver driver) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DriverDAO.class).delete(new DriverHelper().getSql4Delete(driver));
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

	public Driver findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (Driver)DAOFactory.getDAO(DriverDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public Driver findById(Driver driver) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (Driver)DAOFactory.getDAO(DriverDAO.class).load(driver);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public Driver findBy(Driver driver) throws Exception {
		String sql = SqlUtil.getSql4All(DriverHelper.class,driver,Driver.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (Driver)DAOFactory.getDAO(DriverDAO.class).load(sql,Driver.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Driver> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (List<Driver>)DAOFactory.getDAO(DriverDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Driver> findAll(Driver driver) throws Exception {
		String sql = SqlUtil.getSql4All(DriverHelper.class,driver,Driver.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<Driver>)DAOFactory.getDAO(DriverDAO.class).query(sql, Driver.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Driver> find(Driver driver,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(DriverHelper.class,driver,pageVO,Driver.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<Driver>)DAOFactory.getDAO(DriverDAO.class).query(sql, Driver.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Driver> find(Driver driver) throws Exception {
		String sql = SqlUtil.getSql4All(DriverHelper.class,driver,Driver.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<Driver>)DAOFactory.getDAO(DriverDAO.class).query(sql, Driver.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(String sql) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return DAOFactory.getDAO(DriverDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(Driver driver) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return DAOFactory.getDAO(DriverDAO.class).amount(new DriverHelper().getSql4Amount(driver));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}