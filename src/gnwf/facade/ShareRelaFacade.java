package gnwf.facade;

import gnwf.dao.ShareRelaDAO;
import gnwf.dao.helper.ShareRelaHelper;
import gnwf.vo.ShareRela;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;


public class ShareRelaFacade {

	public void save(ShareRela shareRela) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ShareRelaDAO.class).insert(shareRela);

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

	public void update(ShareRela shareRela) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ShareRelaDAO.class).update(shareRela);

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
			DAOFactory.getDAO(ShareRelaDAO.class).update(sql);
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

	public void submit(ShareRela shareRela) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ShareRelaDAO.class).update(shareRela);
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

	public void review(ShareRela shareRela) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ShareRelaDAO.class).update(shareRela);
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

	public void confirm(ShareRela shareRela) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ShareRelaDAO.class).update(shareRela);
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
			DAOFactory.getDAO(ShareRelaDAO.class).delete(sql);
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

	public void remove(ShareRela shareRela) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ShareRelaDAO.class).delete(new ShareRelaHelper().getSql4Delete(shareRela));
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

	public ShareRela findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (ShareRela)DAOFactory.getDAO(ShareRelaDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public ShareRela findById(ShareRela shareRela) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (ShareRela)DAOFactory.getDAO(ShareRelaDAO.class).load(shareRela);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public ShareRela findBy(ShareRela shareRela) throws Exception {
		String sql = SqlUtil.getSql4All(ShareRelaHelper.class,shareRela,ShareRela.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (ShareRela)DAOFactory.getDAO(ShareRelaDAO.class).load(sql,ShareRela.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<ShareRela> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<ShareRela>)DAOFactory.getDAO(ShareRelaDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<ShareRela> findAll(ShareRela shareRela) throws Exception {
		String sql = SqlUtil.getSql4All(ShareRelaHelper.class,shareRela,ShareRela.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<ShareRela>)DAOFactory.getDAO(ShareRelaDAO.class).query(sql, ShareRela.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<ShareRela> find(ShareRela shareRela,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(ShareRelaHelper.class,shareRela,pageVO,ShareRela.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<ShareRela>)DAOFactory.getDAO(ShareRelaDAO.class).query(sql, ShareRela.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<ShareRela> find(ShareRela shareRela) throws Exception {
		String sql = SqlUtil.getSql4All(ShareRelaHelper.class,shareRela,ShareRela.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<ShareRela>)DAOFactory.getDAO(ShareRelaDAO.class).query(sql, ShareRela.LIST_FIELDS);
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
			return DAOFactory.getDAO(ShareRelaDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(ShareRela shareRela) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(ShareRelaDAO.class).amount(new ShareRelaHelper().getSql4Amount(shareRela));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}