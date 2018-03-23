package zrsy.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrsy.dao.IpAddrDAO;
import zrsy.dao.helper.IpAddrHelper;
import zrsy.vo.IpAddr;

public class IpAddrFacade {

	public void save(IpAddr ipAddr) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(IpAddrDAO.class).insert(ipAddr);

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

	public void update(IpAddr ipAddr) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(IpAddrDAO.class).update(ipAddr);

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
			DAOFactory.getDAO(IpAddrDAO.class).update(sql);
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

	public void submit(IpAddr ipAddr) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(IpAddrDAO.class).update(ipAddr);
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

	public void review(IpAddr ipAddr) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(IpAddrDAO.class).update(ipAddr);
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

	public void confirm(IpAddr ipAddr) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(IpAddrDAO.class).update(ipAddr);
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
			DAOFactory.getDAO(IpAddrDAO.class).delete(sql);
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

	public void remove(IpAddr ipAddr) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(IpAddrDAO.class).delete(new IpAddrHelper().getSql4Delete(ipAddr));
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

	public IpAddr findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (IpAddr)DAOFactory.getDAO(IpAddrDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public IpAddr findById(IpAddr ipAddr) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (IpAddr)DAOFactory.getDAO(IpAddrDAO.class).load(ipAddr);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public IpAddr findBy(IpAddr ipAddr) throws Exception {
		String sql = SqlUtil.getSql4All(IpAddrHelper.class,ipAddr,IpAddr.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (IpAddr)DAOFactory.getDAO(IpAddrDAO.class).load(sql,IpAddr.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<IpAddr> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (List<IpAddr>)DAOFactory.getDAO(IpAddrDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<IpAddr> findAll(IpAddr ipAddr) throws Exception {
		String sql = SqlUtil.getSql4All(IpAddrHelper.class,ipAddr,IpAddr.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<IpAddr>)DAOFactory.getDAO(IpAddrDAO.class).query(sql, IpAddr.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<IpAddr> find(IpAddr ipAddr,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(IpAddrHelper.class,ipAddr,pageVO,IpAddr.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<IpAddr>)DAOFactory.getDAO(IpAddrDAO.class).query(sql, IpAddr.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<IpAddr> find(IpAddr ipAddr) throws Exception {
		String sql = SqlUtil.getSql4All(IpAddrHelper.class,ipAddr,IpAddr.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<IpAddr>)DAOFactory.getDAO(IpAddrDAO.class).query(sql, IpAddr.LIST_FIELDS);
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
			return DAOFactory.getDAO(IpAddrDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(IpAddr ipAddr) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return DAOFactory.getDAO(IpAddrDAO.class).amount(new IpAddrHelper().getSql4Amount(ipAddr));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}