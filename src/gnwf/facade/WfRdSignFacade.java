package gnwf.facade;

import gnwf.dao.WfRdSignDAO;
import gnwf.dao.helper.WfRdSignHelper;
import gnwf.vo.WfRdSign;
import gnwf.ww.workflow.WFUtil;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

public class WfRdSignFacade {

	public void saveAndDel(WfRdSign wfRdSign) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			String sql = "delete from WfRdSign where wfnoid='"+wfRdSign.getWfNoId()+"' and taskid="+wfRdSign.getTaskId()+" and userId="+wfRdSign.getUserId();
			DAOFactory.getDAO(WfRdSignDAO.class).delete(sql);
			
			if(WFUtil.isNotNull(wfRdSign.getSignText())){
				DAOFactory.getDAO(WfRdSignDAO.class).insert(wfRdSign);
			}
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
	
	public void save(WfRdSign wfRdSign) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdSignDAO.class).insert(wfRdSign);

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

	public void update(WfRdSign wfRdSign) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdSignDAO.class).update(wfRdSign);

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
			DAOFactory.getDAO(WfRdSignDAO.class).update(sql);
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

	public void submit(WfRdSign wfRdSign) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdSignDAO.class).update(wfRdSign);
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

	public void review(WfRdSign wfRdSign) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdSignDAO.class).update(wfRdSign);
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

	public void confirm(WfRdSign wfRdSign) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdSignDAO.class).update(wfRdSign);
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
			DAOFactory.getDAO(WfRdSignDAO.class).delete(sql);
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

	public void remove(WfRdSign wfRdSign) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdSignDAO.class).delete(new WfRdSignHelper().getSql4Delete(wfRdSign));
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

	public WfRdSign findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfRdSign)DAOFactory.getDAO(WfRdSignDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfRdSign findById(WfRdSign wfRdSign) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfRdSign)DAOFactory.getDAO(WfRdSignDAO.class).load(wfRdSign);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfRdSign findBy(WfRdSign wfRdSign) throws Exception {
		String sql = SqlUtil.getSql4All(WfRdSignHelper.class,wfRdSign,WfRdSign.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfRdSign)DAOFactory.getDAO(WfRdSignDAO.class).load(sql,WfRdSign.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRdSign> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRdSign>)DAOFactory.getDAO(WfRdSignDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRdSign> findAll(WfRdSign wfRdSign) throws Exception {
		String sql = SqlUtil.getSql4All(WfRdSignHelper.class,wfRdSign,WfRdSign.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRdSign>)DAOFactory.getDAO(WfRdSignDAO.class).query(sql, WfRdSign.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRdSign> find(WfRdSign wfRdSign,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfRdSignHelper.class,wfRdSign,pageVO,WfRdSign.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRdSign>)DAOFactory.getDAO(WfRdSignDAO.class).query(sql, WfRdSign.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRdSign> find(WfRdSign wfRdSign) throws Exception {
		String sql = SqlUtil.getSql4All(WfRdSignHelper.class,wfRdSign,WfRdSign.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRdSign>)DAOFactory.getDAO(WfRdSignDAO.class).query(sql, WfRdSign.LIST_FIELDS);
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
			return DAOFactory.getDAO(WfRdSignDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfRdSign wfRdSign) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfRdSignDAO.class).amount(new WfRdSignHelper().getSql4Amount(wfRdSign));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}