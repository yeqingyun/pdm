package gnwf.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;
import gnwf.dao.WfRiskReplyDAO;
import gnwf.dao.helper.QuesRespHelper;
import gnwf.dao.helper.WfRiskReplyHelper;
import gnwf.vo.WfRiskReply;

public class WfRiskReplyFacade {

	public void save(WfRiskReply wfRiskReply) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRiskReplyDAO.class).insert(wfRiskReply);

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

	public void update(WfRiskReply wfRiskReply) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRiskReplyDAO.class).update(wfRiskReply);

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
			DAOFactory.getDAO(WfRiskReplyDAO.class).update(sql);
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

	public void submit(WfRiskReply wfRiskReply) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRiskReplyDAO.class).update(wfRiskReply);
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

	public void review(WfRiskReply wfRiskReply) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRiskReplyDAO.class).update(wfRiskReply);
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

	public void confirm(WfRiskReply wfRiskReply) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRiskReplyDAO.class).update(wfRiskReply);
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
			DAOFactory.getDAO(WfRiskReplyDAO.class).delete(sql);
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

	public void remove(WfRiskReply wfRiskReply) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRiskReplyDAO.class).delete(new WfRiskReplyHelper().getSql4Delete(wfRiskReply));
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

	public WfRiskReply findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfRiskReply)DAOFactory.getDAO(WfRiskReplyDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfRiskReply findById(WfRiskReply wfRiskReply) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfRiskReply)DAOFactory.getDAO(WfRiskReplyDAO.class).load(wfRiskReply);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfRiskReply findBy(WfRiskReply wfRiskReply) throws Exception {
		String sql = SqlUtil.getSql4All(QuesRespHelper.class,wfRiskReply,WfRiskReply.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfRiskReply)DAOFactory.getDAO(WfRiskReplyDAO.class).load(sql,WfRiskReply.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRiskReply> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRiskReply>)DAOFactory.getDAO(WfRiskReplyDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRiskReply> findAll(WfRiskReply wfRiskReply) throws Exception {
		String sql = SqlUtil.getSql4All(WfRiskReplyHelper.class,wfRiskReply,WfRiskReply.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRiskReply>)DAOFactory.getDAO(WfRiskReplyDAO.class).query(sql, WfRiskReply.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRiskReply> find(WfRiskReply wfRiskReply,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfRiskReplyHelper.class,wfRiskReply,pageVO,WfRiskReply.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRiskReply>)DAOFactory.getDAO(WfRiskReplyDAO.class).query(sql, WfRiskReply.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRiskReply> find(WfRiskReply wfRiskReply) throws Exception {
		String sql = SqlUtil.getSql4All(WfRiskReplyHelper.class,wfRiskReply,WfRiskReply.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRiskReply>)DAOFactory.getDAO(WfRiskReplyDAO.class).query(sql, WfRiskReply.SELF_FIELDS);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(String sql) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfRiskReplyDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfRiskReply wfRiskReply) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfRiskReplyDAO.class).amount(new WfRiskReplyHelper().getSql4Amount(wfRiskReply));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}