package zrsy.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrsy.dao.SyLogDAO;
import zrsy.dao.helper.SyLogHelper;
import zrsy.vo.SyLog;

public class SyLogFacade {

	public void save(SyLog syLog) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SyLogDAO.class).insert(syLog);

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

	public void update(SyLog syLog) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SyLogDAO.class).update(syLog);

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
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SyLogDAO.class).update(sql);
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

	public void submit(SyLog syLog) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SyLogDAO.class).update(syLog);
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

	public void review(SyLog syLog) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SyLogDAO.class).update(syLog);
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

	public void confirm(SyLog syLog) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SyLogDAO.class).update(syLog);
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
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SyLogDAO.class).delete(sql);
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

	public void remove(SyLog syLog) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SyLogDAO.class).delete(new SyLogHelper().getSql4Delete(syLog));
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

	public SyLog findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (SyLog)DAOFactory.getDAO(SyLogDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public SyLog findById(SyLog syLog) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (SyLog)DAOFactory.getDAO(SyLogDAO.class).load(syLog);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public SyLog findBy(SyLog syLog) throws Exception {
		String sql = SqlUtil.getSql4All(SyLogHelper.class,syLog,SyLog.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (SyLog)DAOFactory.getDAO(SyLogDAO.class).load(sql,SyLog.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<SyLog> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (List<SyLog>)DAOFactory.getDAO(SyLogDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<SyLog> findAll(SyLog syLog) throws Exception {
		String sql = SqlUtil.getSql4All(SyLogHelper.class,syLog,SyLog.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<SyLog>)DAOFactory.getDAO(SyLogDAO.class).query(sql, SyLog.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<SyLog> find(SyLog syLog,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(SyLogHelper.class,syLog,pageVO,SyLog.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<SyLog>)DAOFactory.getDAO(SyLogDAO.class).query(sql, SyLog.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<SyLog> find(SyLog syLog) throws Exception {
		String sql = SqlUtil.getSql4All(SyLogHelper.class,syLog,SyLog.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<SyLog>)DAOFactory.getDAO(SyLogDAO.class).query(sql, SyLog.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(String sql) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return DAOFactory.getDAO(SyLogDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(SyLog syLog) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return DAOFactory.getDAO(SyLogDAO.class).amount(new SyLogHelper().getSql4Amount(syLog));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}