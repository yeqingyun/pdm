package zrprjt.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrprjt.dao.PrjtPointDAO;
import zrprjt.dao.helper.PrjtPointHelper;
import zrprjt.vo.PrjtPoint;

public class PrjtPointFacade {

	public void save(PrjtPoint prjtPoint) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtPointDAO.class).insert(prjtPoint);
			
//			
//			if(prjtPoint.getPrjtPointMsgs() != null && prjtPoint.getPrjtPointMsgs().size() > 0) {
//				DAOFactory.getDAO(zrprjt.dao.PrjtPointMsgDAO.class).delete("delete from PrjtPointMsg where PrjtPointMsg.PrjtPointNo = " +prjtPoint.getPrjtPointNo());
//				for(int i=0; i<prjtPoint.getPrjtPointMsgs().size(); i++) {
//					if(prjtPoint.getPrjtPointMsgs().get(i) != null && prjtPoint.getPrjtPointMsgs().get(i).getPrjtPointNo() != null) {
//						//PrjtPoint.getPrjtPointMsgs().get(i).setPrjtPointNo(PrjtPoint.getPrjtPointNo());
//						DAOFactory.getDAO(zrprjt.dao.PrjtPointMsgDAO.class).insert(prjtPoint.getPrjtPointMsgs().get(i));
//					}
//				}
//			}
//			if(prjtPoint.getPrjtPointWfs() != null && prjtPoint.getPrjtPointWfs().size() > 0) {
//				DAOFactory.getDAO(zrprjt.dao.PrjtPointWfDAO.class).delete("delete from PrjtPointWf where PrjtPointWf.PrjtPointNo = " +prjtPoint.getPrjtPointNo());
//				for(int i=0; i<prjtPoint.getPrjtPointWfs().size(); i++) {
//					if(prjtPoint.getPrjtPointWfs().get(i) != null && prjtPoint.getPrjtPointWfs().get(i).getPrjtPointNo() != null) {
//						//PrjtPoint.getPrjtPointWfs().get(i).setPrjtPointNo(PrjtPoint.getPrjtPointNo());
//						DAOFactory.getDAO(zrprjt.dao.PrjtPointWfDAO.class).insert(prjtPoint.getPrjtPointWfs().get(i));
//					}
//				}
//			}

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

	public void update(PrjtPoint prjtPoint) throws Exception {
		DbConnUtil.buildDbconn(1); 
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtPointDAO.class).update(prjtPoint);
			
//			if(prjtPoint.getPrjtPointMsgs() != null && prjtPoint.getPrjtPointMsgs().size() > 0) {
//				DAOFactory.getDAO(zrprjt.dao.PrjtPointMsgDAO.class).delete("delete from PrjtPointMsg where PrjtPointMsg.PrjtPointNo = " +prjtPoint.getPrjtPointNo());
//				for(int i=0; i<prjtPoint.getPrjtPointMsgs().size(); i++) {
//					if(prjtPoint.getPrjtPointMsgs().get(i) != null && prjtPoint.getPrjtPointMsgs().get(i).getPrjtPointNo() != null) {
//						//PrjtPoint.getPrjtPointMsgs().get(i).setPrjtPointNo(PrjtPoint.getPrjtPointNo());
//						DAOFactory.getDAO(zrprjt.dao.PrjtPointMsgDAO.class).update(prjtPoint.getPrjtPointMsgs().get(i));
//					}
//				}
//			}
//			if(prjtPoint.getPrjtPointWfs() != null && prjtPoint.getPrjtPointWfs().size() > 0) {
//				DAOFactory.getDAO(zrprjt.dao.PrjtPointWfDAO.class).delete("delete from PrjtPointWf where PrjtPointWf.PrjtPointNo = " +prjtPoint.getPrjtPointNo());
//				for(int i=0; i<prjtPoint.getPrjtPointWfs().size(); i++) {
//					if(prjtPoint.getPrjtPointWfs().get(i) != null && prjtPoint.getPrjtPointWfs().get(i).getPrjtPointNo() != null) {
//						//PrjtPoint.getPrjtPointWfs().get(i).setPrjtPointNo(PrjtPoint.getPrjtPointNo());
//						DAOFactory.getDAO(zrprjt.dao.PrjtPointWfDAO.class).update(prjtPoint.getPrjtPointWfs().get(i));
//					}
//				}
//			}

			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			e.printStackTrace();
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
			DAOFactory.getDAO(PrjtPointDAO.class).update(sql);
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

	public void submit(PrjtPoint prjtPoint) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtPointDAO.class).update(prjtPoint);
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

	public void review(PrjtPoint prjtPoint) throws Exception { 
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtPointDAO.class).update(prjtPoint);
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

	public void confirm(PrjtPoint prjtPoint) throws Exception { 
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtPointDAO.class).update(prjtPoint);
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
			DAOFactory.getDAO(PrjtPointDAO.class).delete(sql);
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

	public void remove(PrjtPoint prjtPoint) throws Exception { 
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtPointDAO.class).delete(new PrjtPointHelper().getSql4Delete(prjtPoint));
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

	public PrjtPoint findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (PrjtPoint)DAOFactory.getDAO(PrjtPointDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public PrjtPoint findById(PrjtPoint prjtPoint) throws Exception { 
		DbConnUtil.buildDbconn(1);
		try {
			return (PrjtPoint)DAOFactory.getDAO(PrjtPointDAO.class).load(prjtPoint);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public PrjtPoint findBy(PrjtPoint prjtPoint) throws Exception {
		String sql = SqlUtil.getSql4All(PrjtPointHelper.class,prjtPoint,prjtPoint.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (PrjtPoint)DAOFactory.getDAO(PrjtPointDAO.class).load(sql,prjtPoint.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PrjtPoint> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (List<PrjtPoint>)DAOFactory.getDAO(PrjtPointDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PrjtPoint> findAll(PrjtPoint prjtPoint) throws Exception {
		String sql = SqlUtil.getSql4All(PrjtPointHelper.class,prjtPoint,prjtPoint.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<PrjtPoint>)DAOFactory.getDAO(PrjtPointDAO.class).query(sql, prjtPoint.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PrjtPoint> find(PrjtPoint prjtPoint,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(PrjtPointHelper.class,prjtPoint,pageVO,prjtPoint.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<PrjtPoint>)DAOFactory.getDAO(PrjtPointDAO.class).query(sql, prjtPoint.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PrjtPoint> find(PrjtPoint PrjtPoint) throws Exception {
		String sql = SqlUtil.getSql4All(PrjtPointHelper.class,PrjtPoint,PrjtPoint.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<PrjtPoint>)DAOFactory.getDAO(PrjtPointDAO.class).query(sql, PrjtPoint.LIST_FIELDS);
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
			return DAOFactory.getDAO(PrjtPointDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(PrjtPoint prjtPoint) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return DAOFactory.getDAO(PrjtPointDAO.class).amount(new PrjtPointHelper().getSql4Amount(prjtPoint));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}