package zrprjt.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrprjt.dao.DriverRdDAO;
import zrprjt.dao.helper.DriverRdHelper;
import zrprjt.vo.DriverRd;

public class DriverRdFacade {

	public void save(DriverRd driverRd) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DriverRdDAO.class).insert(driverRd);

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

	public void update(DriverRd driverRd) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DriverRdDAO.class).update(driverRd);

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
			DAOFactory.getDAO(DriverRdDAO.class).update(sql);
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

	public void submit(DriverRd driverRd) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DriverRdDAO.class).update(driverRd);
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

	public void review(DriverRd driverRd) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DriverRdDAO.class).update(driverRd);
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

	public void confirm(DriverRd driverRd) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DriverRdDAO.class).update(driverRd);
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
			DAOFactory.getDAO(DriverRdDAO.class).delete(sql);
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

	public void remove(DriverRd driverRd) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DriverRdDAO.class).delete(new DriverRdHelper().getSql4Delete(driverRd));
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

	public DriverRd findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (DriverRd)DAOFactory.getDAO(DriverRdDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public DriverRd findById(DriverRd driverRd) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (DriverRd)DAOFactory.getDAO(DriverRdDAO.class).load(driverRd);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public DriverRd findBy(DriverRd driverRd) throws Exception {
		String sql = SqlUtil.getSql4All(DriverRdHelper.class,driverRd,DriverRd.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (DriverRd)DAOFactory.getDAO(DriverRdDAO.class).load(sql,DriverRd.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<DriverRd> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (List<DriverRd>)DAOFactory.getDAO(DriverRdDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<DriverRd> findAll(DriverRd driverRd) throws Exception {
		String sql = SqlUtil.getSql4All(DriverRdHelper.class,driverRd,DriverRd.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<DriverRd>)DAOFactory.getDAO(DriverRdDAO.class).query(sql, DriverRd.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<DriverRd> find(DriverRd driverRd,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(DriverRdHelper.class,driverRd,pageVO,DriverRd.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<DriverRd>)DAOFactory.getDAO(DriverRdDAO.class).query(sql, DriverRd.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<DriverRd> find(DriverRd driverRd) throws Exception {
		String sql = SqlUtil.getSql4All(DriverRdHelper.class,driverRd,DriverRd.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<DriverRd>)DAOFactory.getDAO(DriverRdDAO.class).query(sql, DriverRd.LIST_FIELDS);
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
			return DAOFactory.getDAO(DriverRdDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(DriverRd driverRd) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return DAOFactory.getDAO(DriverRdDAO.class).amount(new DriverRdHelper().getSql4Amount(driverRd));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}