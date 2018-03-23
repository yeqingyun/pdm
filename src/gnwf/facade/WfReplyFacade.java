package gnwf.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnwf.dao.WfQuesDAO;
import gnwf.dao.WfReplyDAO;
import gnwf.dao.helper.WfReplyHelper;
import gnwf.vo.WfReply;

public class WfReplyFacade {

	public void save(WfReply wfReply) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfReplyDAO.class).insert(wfReply);
			DAOFactory.getDAO(WfQuesDAO.class).update("update WfQues set Status = 10 where Status < 10 and QuesId = " + wfReply.getQuesId());
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

	public void update(WfReply wfReply) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfReplyDAO.class).update(wfReply);

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
			DAOFactory.getDAO(WfReplyDAO.class).update(sql);
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

	public void submit(WfReply wfReply) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfReplyDAO.class).update(wfReply);
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

	public void review(WfReply wfReply) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfReplyDAO.class).update(wfReply);
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

	public void confirm(WfReply wfReply) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfReplyDAO.class).update(wfReply);
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
			DAOFactory.getDAO(WfReplyDAO.class).delete(sql);
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

	public void remove(WfReply wfReply) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfReplyDAO.class).delete(new WfReplyHelper().getSql4Delete(wfReply));
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

	public WfReply findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfReply)DAOFactory.getDAO(WfReplyDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfReply findById(WfReply wfReply) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfReply)DAOFactory.getDAO(WfReplyDAO.class).load(wfReply);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfReply findBy(WfReply wfReply) throws Exception {
		String sql = SqlUtil.getSql4All(WfReplyHelper.class,wfReply,WfReply.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfReply)DAOFactory.getDAO(WfReplyDAO.class).load(sql,WfReply.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfReply> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfReply>)DAOFactory.getDAO(WfReplyDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfReply> findAll(WfReply wfReply) throws Exception {
		String sql = SqlUtil.getSql4All(WfReplyHelper.class,wfReply,WfReply.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfReply>)DAOFactory.getDAO(WfReplyDAO.class).query(sql, WfReply.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfReply> find(WfReply wfReply,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfReplyHelper.class,wfReply,pageVO,WfReply.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfReply>)DAOFactory.getDAO(WfReplyDAO.class).query(sql, WfReply.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfReply> find(WfReply wfReply) throws Exception {
		String sql = SqlUtil.getSql4All(WfReplyHelper.class,wfReply,WfReply.LIST_FIELDS);
		System.out.println("-+-+-+-"+sql);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfReply>)DAOFactory.getDAO(WfReplyDAO.class).query(sql, WfReply.LIST_FIELDS);
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
			return DAOFactory.getDAO(WfReplyDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfReply wfReply) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfReplyDAO.class).amount(new WfReplyHelper().getSql4Amount(wfReply));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}