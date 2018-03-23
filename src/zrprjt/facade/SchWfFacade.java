package zrprjt.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrprjt.dao.SchWfDAO;
import zrprjt.dao.helper.SchWfHelper;
import zrprjt.vo.SchWf;

public class SchWfFacade {

	public void save(SchWf schWf) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SchWfDAO.class).insert(schWf);

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

	public void update(SchWf schWf) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SchWfDAO.class).update(schWf);

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
			DAOFactory.getDAO(SchWfDAO.class).update(sql);
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

	public void submit(SchWf schWf) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SchWfDAO.class).update(schWf);
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

	public void review(SchWf schWf) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SchWfDAO.class).update(schWf);
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

	public void confirm(SchWf schWf) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SchWfDAO.class).update(schWf);
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
			DAOFactory.getDAO(SchWfDAO.class).delete(sql);
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

	public void remove(SchWf schWf) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SchWfDAO.class).delete(new SchWfHelper().getSql4Delete(schWf));
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

	public SchWf findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (SchWf)DAOFactory.getDAO(SchWfDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public SchWf findById(SchWf schWf) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (SchWf)DAOFactory.getDAO(SchWfDAO.class).load(schWf);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public SchWf findBy(SchWf schWf) throws Exception {
		String sql = SqlUtil.getSql4All(SchWfHelper.class,schWf,SchWf.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (SchWf)DAOFactory.getDAO(SchWfDAO.class).load(sql,SchWf.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<SchWf> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (List<SchWf>)DAOFactory.getDAO(SchWfDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<SchWf> findAll(SchWf schWf) throws Exception {
		String sql = SqlUtil.getSql4All(SchWfHelper.class,schWf,SchWf.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<SchWf>)DAOFactory.getDAO(SchWfDAO.class).query(sql, SchWf.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<SchWf> find(SchWf schWf,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(SchWfHelper.class,schWf,pageVO,SchWf.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<SchWf>)DAOFactory.getDAO(SchWfDAO.class).query(sql, SchWf.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<SchWf> find(SchWf schWf) throws Exception {
		String sql = SqlUtil.getSql4All(SchWfHelper.class,schWf,SchWf.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<SchWf>)DAOFactory.getDAO(SchWfDAO.class).query(sql, SchWf.LIST_FIELDS);
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
			return DAOFactory.getDAO(SchWfDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(SchWf schWf) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return DAOFactory.getDAO(SchWfDAO.class).amount(new SchWfHelper().getSql4Amount(schWf));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}