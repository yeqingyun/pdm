package zrsy.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrsy.dao.BillTypDAO;
import zrsy.dao.helper.BillTypHelper;
import zrsy.vo.BillTyp;

public class BillTypFacade {

	public void save(BillTyp billTyp) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(BillTypDAO.class).insert(billTyp);

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

	public void update(BillTyp billTyp) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(BillTypDAO.class).update(billTyp);

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
			DAOFactory.getDAO(BillTypDAO.class).update(sql);
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

	public void submit(BillTyp billTyp) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(BillTypDAO.class).update(billTyp);
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

	public void review(BillTyp billTyp) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(BillTypDAO.class).update(billTyp);
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

	public void confirm(BillTyp billTyp) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(BillTypDAO.class).update(billTyp);
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
			DAOFactory.getDAO(BillTypDAO.class).delete(sql);
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

	public void remove(BillTyp billTyp) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(BillTypDAO.class).delete(new BillTypHelper().getSql4Delete(billTyp));
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

	public BillTyp findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (BillTyp)DAOFactory.getDAO(BillTypDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public BillTyp findById(BillTyp billTyp) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (BillTyp)DAOFactory.getDAO(BillTypDAO.class).load(billTyp);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public BillTyp findBy(BillTyp billTyp) throws Exception {
		String sql = SqlUtil.getSql4All(BillTypHelper.class,billTyp,BillTyp.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (BillTyp)DAOFactory.getDAO(BillTypDAO.class).load(sql,BillTyp.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<BillTyp> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (List<BillTyp>)DAOFactory.getDAO(BillTypDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<BillTyp> findAll(BillTyp billTyp) throws Exception {
		String sql = SqlUtil.getSql4All(BillTypHelper.class,billTyp,BillTyp.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<BillTyp>)DAOFactory.getDAO(BillTypDAO.class).query(sql, BillTyp.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<BillTyp> find(BillTyp billTyp,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(BillTypHelper.class,billTyp,pageVO,BillTyp.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<BillTyp>)DAOFactory.getDAO(BillTypDAO.class).query(sql, BillTyp.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<BillTyp> find(BillTyp billTyp) throws Exception {
		String sql = SqlUtil.getSql4All(BillTypHelper.class,billTyp,BillTyp.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<BillTyp>)DAOFactory.getDAO(BillTypDAO.class).query(sql, BillTyp.LIST_FIELDS);
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
			return DAOFactory.getDAO(BillTypDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(BillTyp billTyp) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return DAOFactory.getDAO(BillTypDAO.class).amount(new BillTypHelper().getSql4Amount(billTyp));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}