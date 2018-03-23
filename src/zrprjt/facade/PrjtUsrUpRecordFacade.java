package zrprjt.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrprjt.dao.PrjtUsrUpRecordDAO;
import zrprjt.dao.helper.PrjtUsrUpRecordHelper;
import zrprjt.vo.PrjtUsrUpRecord;

public class PrjtUsrUpRecordFacade {

	public void save(PrjtUsrUpRecord PrjtUsrUpRecord) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtUsrUpRecordDAO.class).insert(PrjtUsrUpRecord);

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

	public void update(PrjtUsrUpRecord PrjtUsrUpRecord) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtUsrUpRecordDAO.class).update(PrjtUsrUpRecord);

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
			DAOFactory.getDAO(PrjtUsrUpRecordDAO.class).update(sql);
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

	public void submit(PrjtUsrUpRecord PrjtUsrUpRecord) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtUsrUpRecordDAO.class).update(PrjtUsrUpRecord);
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

	public void review(PrjtUsrUpRecord PrjtUsrUpRecord) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtUsrUpRecordDAO.class).update(PrjtUsrUpRecord);
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

	public void confirm(PrjtUsrUpRecord PrjtUsrUpRecord) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtUsrUpRecordDAO.class).update(PrjtUsrUpRecord);
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
			DAOFactory.getDAO(PrjtUsrUpRecordDAO.class).delete(sql);
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

	public void remove(PrjtUsrUpRecord PrjtUsrUpRecord) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtUsrUpRecordDAO.class).delete(new PrjtUsrUpRecordHelper().getSql4Delete(PrjtUsrUpRecord));
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

	public PrjtUsrUpRecord findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (PrjtUsrUpRecord)DAOFactory.getDAO(PrjtUsrUpRecordDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public PrjtUsrUpRecord findById(PrjtUsrUpRecord PrjtUsrUpRecord) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (PrjtUsrUpRecord)DAOFactory.getDAO(PrjtUsrUpRecordDAO.class).load(PrjtUsrUpRecord);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public PrjtUsrUpRecord findBy(PrjtUsrUpRecord PrjtUsrUpRecord) throws Exception {
		String sql = SqlUtil.getSql4All(PrjtUsrUpRecordHelper.class,PrjtUsrUpRecord,PrjtUsrUpRecord.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (PrjtUsrUpRecord)DAOFactory.getDAO(PrjtUsrUpRecordDAO.class).load(sql,PrjtUsrUpRecord.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PrjtUsrUpRecord> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (List<PrjtUsrUpRecord>)DAOFactory.getDAO(PrjtUsrUpRecordDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PrjtUsrUpRecord> findAll(PrjtUsrUpRecord PrjtUsrUpRecord) throws Exception {
		String sql = SqlUtil.getSql4All(PrjtUsrUpRecordHelper.class,PrjtUsrUpRecord,PrjtUsrUpRecord.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<PrjtUsrUpRecord>)DAOFactory.getDAO(PrjtUsrUpRecordDAO.class).query(sql, PrjtUsrUpRecord.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PrjtUsrUpRecord> find(PrjtUsrUpRecord PrjtUsrUpRecord,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(PrjtUsrUpRecordHelper.class,PrjtUsrUpRecord,pageVO,PrjtUsrUpRecord.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<PrjtUsrUpRecord>)DAOFactory.getDAO(PrjtUsrUpRecordDAO.class).query(sql, PrjtUsrUpRecord.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
	
//	public String getSql4Pages(PrjtUsrUpRecord PrjtUsrUpRecord,PageVO pageVO, String fields,String sqlString,String conDitionSQl) {
//		int pageSize = pageVO.getPageSize();
//		int pages = pageSize*pageVO.getPage()-pageSize;
//		String mstr = "select top "+pages+" PrjtUsrUpRecord.Id"+ sqlString+conDitionSQl+getOrderBy();
//		String sql = "select top "+pageSize+" "+sqlString + conDitionSQl +" and PrjtUsrUpRecord.Id not in("+mstr+") "+getOrderBy();
//
//		return sql;
//	}
	
	@SuppressWarnings("unchecked")
	public List<PrjtUsrUpRecord> find(PageVO pageVO,String sqlString, String conDitionSQl) throws Exception {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" PrjtUsrUpRecord.Id"+" "+sqlString+" "+conDitionSQl+" order by PrjtUsrUpRecord.RoleId ";
		String sql = "select top "+pageSize+" "+PrjtUsrUpRecord.LIST_FIELDS+" "+sqlString +" "+conDitionSQl +" and PrjtUsrUpRecord.Id not in("+mstr+") "+" order by PrjtUsrUpRecord.RoleId ";
		//String sql = SqlUtil.getSql4Pages(PrjtUsrUpRecordHelper.class,PrjtUsrUpRecord,pageVO,PrjtUsrUpRecord.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<PrjtUsrUpRecord>)DAOFactory.getDAO(PrjtUsrUpRecordDAO.class).query(sql, PrjtUsrUpRecord.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PrjtUsrUpRecord> find(PrjtUsrUpRecord PrjtUsrUpRecord) throws Exception {
		String sql = SqlUtil.getSql4All(PrjtUsrUpRecordHelper.class,PrjtUsrUpRecord,zrprjt.vo.PrjtUsrUpRecord.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<PrjtUsrUpRecord>)DAOFactory.getDAO(PrjtUsrUpRecordDAO.class).query(sql, zrprjt.vo.PrjtUsrUpRecord.LIST_FIELDS);
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
			return DAOFactory.getDAO(PrjtUsrUpRecordDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(PrjtUsrUpRecord PrjtUsrUpRecord) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return DAOFactory.getDAO(PrjtUsrUpRecordDAO.class).amount(new PrjtUsrUpRecordHelper().getSql4Amount(PrjtUsrUpRecord));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

}