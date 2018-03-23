package gnwf.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnwf.dao.OptStoreDAO;
import gnwf.dao.helper.OptStoreHelper;
import gnwf.vo.OptStore;

public class OptStoreFacade {

	public void save(OptStore optStore) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(OptStoreDAO.class).insert(optStore);

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

	public void update(OptStore optStore) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(OptStoreDAO.class).update(optStore);

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
			DAOFactory.getDAO(OptStoreDAO.class).update(sql);
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

	public void submit(OptStore optStore) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(OptStoreDAO.class).update(optStore);
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

	public void review(OptStore optStore) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(OptStoreDAO.class).update(optStore);
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

	public void confirm(OptStore optStore) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(OptStoreDAO.class).update(optStore);
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
			DAOFactory.getDAO(OptStoreDAO.class).delete(sql);
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

	public void remove(OptStore optStore) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(OptStoreDAO.class).delete(new OptStoreHelper().getSql4Delete(optStore));
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

	public OptStore findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (OptStore)DAOFactory.getDAO(OptStoreDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public OptStore findById(OptStore optStore) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (OptStore)DAOFactory.getDAO(OptStoreDAO.class).load(optStore);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public OptStore findBy(OptStore optStore) throws Exception {
		String sql = SqlUtil.getSql4All(OptStoreHelper.class,optStore,OptStore.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (OptStore)DAOFactory.getDAO(OptStoreDAO.class).load(sql,OptStore.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<OptStore> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<OptStore>)DAOFactory.getDAO(OptStoreDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<OptStore> findAll(OptStore optStore) throws Exception {
		String sql = SqlUtil.getSql4All(OptStoreHelper.class,optStore,OptStore.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<OptStore>)DAOFactory.getDAO(OptStoreDAO.class).query(sql, OptStore.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<OptStore> find(OptStore optStore,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(OptStoreHelper.class,optStore,pageVO,OptStore.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<OptStore>)DAOFactory.getDAO(OptStoreDAO.class).query(sql, OptStore.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<OptStore> find(OptStore optStore) throws Exception {
		String sql = SqlUtil.getSql4All(OptStoreHelper.class,optStore,OptStore.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<OptStore>)DAOFactory.getDAO(OptStoreDAO.class).query(sql, OptStore.LIST_FIELDS);
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
			return DAOFactory.getDAO(OptStoreDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(OptStore optStore) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(OptStoreDAO.class).amount(new OptStoreHelper().getSql4Amount(optStore));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}