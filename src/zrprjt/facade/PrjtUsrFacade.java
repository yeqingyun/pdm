package zrprjt.facade;

import java.util.Date;
import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrprjt.dao.PrjtUsrDAO;
import zrprjt.dao.PrjtUsrUpRecordDAO;
import zrprjt.dao.helper.PrjtUsrHelper;
import zrprjt.vo.PrjtUsr;
import zrprjt.vo.PrjtUsrUpRecord;

public class PrjtUsrFacade {

	public void save(PrjtUsr prjtUsr) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtUsrDAO.class).insert(prjtUsr);

			
			    PrjtUsrUpRecord prjtUsrUpRecord = new PrjtUsrUpRecord();
			    prjtUsrUpRecord.setCreateBy(prjtUsr.getLastUpd());
			    prjtUsrUpRecord.setCreateDate(new Date());
			    prjtUsrUpRecord.setPrjtUsrId(prjtUsr.getId());
			    prjtUsrUpRecord.setUsrId(prjtUsr.getUsrId());
			    prjtUsrUpRecord.setPriority(prjtUsr.getPriority());
			    prjtUsrUpRecord.setUpTyp(1);
				DAOFactory.getDAO(PrjtUsrUpRecordDAO.class).insert(prjtUsrUpRecord);
			
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

	public void update(PrjtUsr prjtUsr) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtUsrDAO.class).update(prjtUsr);
			
			/**
			if(prjtUsr.getUsrId().intValue()!=prjtUsr.getOldUsrId().intValue()){
				
				//插入一条UpTyp为0的记录
				PrjtUsrUpRecord pur2 = new PrjtUsrUpRecord(); 
				pur2.setCreateBy(prjtUsr.getLastUpd());
				pur2.setCreateDate(new Date());
				pur2.setPrjtUsrId(prjtUsr.getId());
				pur2.setUsrId(prjtUsr.getOldUsrId());
				pur2.setPriority(prjtUsr.getPriority());
				pur2.setUpTyp(0);
				DAOFactory.getDAO(PrjtUsrUpRecordDAO.class).insert(pur2);
				
				//插入一条UpTyp为 1的记录
				PrjtUsrUpRecord prjtUsrUpRecord = new PrjtUsrUpRecord();
				prjtUsrUpRecord.setCreateBy(prjtUsr.getLastUpd());
				prjtUsrUpRecord.setCreateDate(new Date());
				prjtUsrUpRecord.setPrjtUsrId(prjtUsr.getId());
				prjtUsrUpRecord.setUsrId(prjtUsr.getUsrId());
				prjtUsrUpRecord.setPriority(prjtUsr.getPriority());
				prjtUsrUpRecord.setUpTyp(1);
				DAOFactory.getDAO(PrjtUsrUpRecordDAO.class).insert(prjtUsrUpRecord);
				
			}
			
			if(prjtUsr.getStatus().intValue()!=prjtUsr.getOldStatus().intValue()){
				PrjtUsrUpRecord pur2 = new PrjtUsrUpRecord(); 
				pur2.setCreateBy(prjtUsr.getLastUpd());
				pur2.setCreateDate(new Date());
				pur2.setPrjtUsrId(prjtUsr.getId());
				pur2.setUsrId(prjtUsr.getOldUsrId());
				pur2.setPriority(prjtUsr.getPriority());
				pur2.setUpTyp(prjtUsr.getStatus());
				DAOFactory.getDAO(PrjtUsrUpRecordDAO.class).insert(pur2);
			}
			**/
			
			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			e.printStackTrace();
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
			DAOFactory.getDAO(PrjtUsrDAO.class).update(sql);
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

	public void submit(PrjtUsr prjtUsr) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtUsrDAO.class).update(prjtUsr);
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

	public void review(PrjtUsr prjtUsr) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtUsrDAO.class).update(prjtUsr);
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

	public void confirm(PrjtUsr prjtUsr) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtUsrDAO.class).update(prjtUsr);
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
			DAOFactory.getDAO(PrjtUsrDAO.class).delete(sql);
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

	public void remove(PrjtUsr prjtUsr) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtUsrDAO.class).delete(new PrjtUsrHelper().getSql4Delete(prjtUsr));
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

	public PrjtUsr findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (PrjtUsr)DAOFactory.getDAO(PrjtUsrDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public PrjtUsr findById(PrjtUsr prjtUsr) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (PrjtUsr)DAOFactory.getDAO(PrjtUsrDAO.class).load(prjtUsr);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public PrjtUsr findBy(PrjtUsr prjtUsr) throws Exception {
		String sql = SqlUtil.getSql4All(PrjtUsrHelper.class,prjtUsr,PrjtUsr.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (PrjtUsr)DAOFactory.getDAO(PrjtUsrDAO.class).load(sql,PrjtUsr.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PrjtUsr> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			System.out.println("1111111111"+fields);
			System.out.println("2222222222"+sql);
			return (List<PrjtUsr>)DAOFactory.getDAO(PrjtUsrDAO.class).query(sql, fields);
			
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PrjtUsr> findAll(PrjtUsr prjtUsr) throws Exception {
		String sql = SqlUtil.getSql4All(PrjtUsrHelper.class,prjtUsr,PrjtUsr.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<PrjtUsr>)DAOFactory.getDAO(PrjtUsrDAO.class).query(sql, PrjtUsr.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PrjtUsr> find(PrjtUsr prjtUsr,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(PrjtUsrHelper.class,prjtUsr,pageVO,PrjtUsr.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<PrjtUsr>)DAOFactory.getDAO(PrjtUsrDAO.class).query(sql, PrjtUsr.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
	
//	public String getSql4Pages(PrjtUsr prjtUsr,PageVO pageVO, String fields,String sqlString,String conDitionSQl) {
//		int pageSize = pageVO.getPageSize();
//		int pages = pageSize*pageVO.getPage()-pageSize;
//		String mstr = "select top "+pages+" PrjtUsr.Id"+ sqlString+conDitionSQl+getOrderBy();
//		String sql = "select top "+pageSize+" "+sqlString + conDitionSQl +" and PrjtUsr.Id not in("+mstr+") "+getOrderBy();
//
//		return sql;
//	}
	
	@SuppressWarnings("unchecked")
	public List<PrjtUsr> find(PageVO pageVO,String sqlString, String conDitionSQl) throws Exception {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" PrjtUsr.Id"+" "+sqlString+" "+conDitionSQl+" order by PrjtUsr.RoleId ";
		String sql = "select top "+pageSize+" "+PrjtUsr.LIST_FIELDS+" "+sqlString +" "+conDitionSQl +" and PrjtUsr.Id not in("+mstr+") "+" order by PrjtUsr.RoleId ";
		//String sql = SqlUtil.getSql4Pages(PrjtUsrHelper.class,prjtUsr,pageVO,PrjtUsr.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<PrjtUsr>)DAOFactory.getDAO(PrjtUsrDAO.class).query(sql, PrjtUsr.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<PrjtUsr> findPrjtUsrs(String sqlString, String conDitionSQl) throws Exception {
		String sql = "select "+PrjtUsr.ADDRBOOK_LIST_FIELDS+" "+sqlString +" "+conDitionSQl +" order by PrjtUsr.RoleId ";
		DbConnUtil.buildDbconn(1);
		try {
			return (List<PrjtUsr>)DAOFactory.getDAO(PrjtUsrDAO.class).query(sql, PrjtUsr.ADDRBOOK_LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
	

	@SuppressWarnings("unchecked")
	public List<PrjtUsr> find(PrjtUsr prjtUsr) throws Exception {
		String sql = SqlUtil.getSql4All(PrjtUsrHelper.class,prjtUsr,PrjtUsr.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<PrjtUsr>)DAOFactory.getDAO(PrjtUsrDAO.class).query(sql, PrjtUsr.LIST_FIELDS);
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
			return DAOFactory.getDAO(PrjtUsrDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(PrjtUsr prjtUsr) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return DAOFactory.getDAO(PrjtUsrDAO.class).amount(new PrjtUsrHelper().getSql4Amount(prjtUsr));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public Boolean chekPrjtUsrExist(PrjtUsr prjtUsr) {
		try {
			PrjtUsr checkPrjtUsr = new PrjtUsr();
			checkPrjtUsr.setPrjtNo(prjtUsr.getPrjtNo());
			checkPrjtUsr.setRoleId(prjtUsr.getRoleId());
			checkPrjtUsr.setUsrId(prjtUsr.getUsrId());
			String sql ="select "+PrjtUsr.SELF_FIELDS+ " from PrjtUsr where PrjtNo = '"+checkPrjtUsr.getPrjtNo()+
					     "' and RoleId = "+checkPrjtUsr.getRoleId()+" and UsrId = "+checkPrjtUsr.getUsrId();
			PrjtUsr pj =  findById(sql,PrjtUsr.SELF_FIELDS);
			if(pj!=null){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}