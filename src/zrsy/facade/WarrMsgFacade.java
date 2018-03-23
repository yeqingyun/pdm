package zrsy.facade;
import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DSN;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;

import zrsy.dao.WarrMsgDAO;
import zrsy.dao.helper.WarrMsgHelper;
import zrsy.vo.WarrMsg;
public class WarrMsgFacade {

	public void save(WarrMsg warrMsg) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WarrMsgDAO.class).insert(warrMsg);
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

	public void update(WarrMsg warrMsg) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WarrMsgDAO.class).update(warrMsg);
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
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WarrMsgDAO.class).update(sql);
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

	public void submit(WarrMsg warrMsg) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WarrMsgDAO.class).update(warrMsg);
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

	public void review(WarrMsg warrMsg) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WarrMsgDAO.class).update(warrMsg);
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

	public void confirm(WarrMsg warrMsg) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WarrMsgDAO.class).update(warrMsg);
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
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WarrMsgDAO.class).delete(sql);
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

	public void remove(WarrMsg warrMsg) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WarrMsgDAO.class).delete(new WarrMsgHelper().getSql4Delete(warrMsg));
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

	public WarrMsg findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			return (WarrMsg)DAOFactory.getDAO(WarrMsgDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WarrMsg findById(WarrMsg warrMsg) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			return (WarrMsg)DAOFactory.getDAO(WarrMsgDAO.class).load(warrMsg);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WarrMsg findBy(WarrMsg warrMsg) throws Exception {
		String sql = SqlUtil.getSql4All(WarrMsgHelper.class,warrMsg,WarrMsg.LIST_FIELDS);
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			return (WarrMsg)DAOFactory.getDAO(WarrMsgDAO.class).load(sql,WarrMsg.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WarrMsg> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			return (List<WarrMsg>)DAOFactory.getDAO(WarrMsgDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WarrMsg> find(WarrMsg warrMsg) throws Exception {
//		String sql = SqlUtil.getSql4Pages(WarrMsgHelper.class,warrMsg,WarrMsg.LIST_FIELDS);
//		DbConnUtil.buildDbconn(DSN.DB_CONN);
//		try {
//			return (List<WarrMsg>)DAOFactory.getDAO(WarrMsgDAO.class).query(sql, WarrMsg.LIST_FIELDS);
//		}
//		catch(Exception e) {
//			throw e;
//		}
//		finally {
//			DbConnUtil.closeDbconn();
//		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<WarrMsg> findAll(WarrMsg warrMsg) throws Exception {
		String sql = SqlUtil.getSql4All(WarrMsgHelper.class,warrMsg,WarrMsg.LIST_FIELDS);
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			return (List<WarrMsg>)DAOFactory.getDAO(WarrMsgDAO.class).query(sql, WarrMsg.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(String sql) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			return DAOFactory.getDAO(WarrMsgDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WarrMsg warrMsg) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			return DAOFactory.getDAO(WarrMsgDAO.class).amount(new WarrMsgHelper().getSql4Amount(warrMsg));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}
