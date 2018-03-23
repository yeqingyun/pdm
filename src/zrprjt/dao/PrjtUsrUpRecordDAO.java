package zrprjt.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;

import zrprjt.dao.helper.PrjtUsrUpRecordHelper;
import zrprjt.vo.PrjtUsrUpRecord;

public class PrjtUsrUpRecordDAO extends BasicDAO implements GenericDAO {

	private PrjtUsrUpRecordHelper PrjtUsrUpRecordHelper = new PrjtUsrUpRecordHelper();

	public void insert(Object PrjtUsrUpRecord) throws java.sql.SQLException {
		PrjtUsrUpRecord _PrjtUsrUpRecord = (PrjtUsrUpRecord)PrjtUsrUpRecord;
		String fields = PrjtUsrUpRecordHelper.getFields(_PrjtUsrUpRecord);
		String sql = PrjtUsrUpRecordHelper.getInsertSql(fields);
		try {
			PrjtUsrUpRecordHelper.pstmtInsert(_PrjtUsrUpRecord, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtUsrUpRecordDAO.insert", e);
			throw e;
		}
	}

	public void update(Object PrjtUsrUpRecord) throws java.sql.SQLException {
		//TODO
//		PrjtUsrUpRecord _PrjtUsrUpRecord = (PrjtUsrUpRecord)PrjtUsrUpRecord;
//		String fields = PrjtUsrUpRecordHelper.getFields(_PrjtUsrUpRecord);
//		String sql = PrjtUsrUpRecordHelper.getUpdateSql(fields, "PrjtUsrUpRecord.RoleId" ,_PrjtUsrUpRecord.getRoleId().toString());
//		try {
//			PrjtUsrUpRecordHelper.pstmtUpdate(_PrjtUsrUpRecord, sql, fields);
//		}
//		catch(java.sql.SQLException e) {
//			Logger.getLogger(this.getClass()).error("PrjtUsrUpRecordDAO.update", e);
//			throw e;
//		}
	}

	public List<PrjtUsrUpRecord> query(String sql, String fields) throws java.sql.SQLException {
		try {
//			String usrSql = "select "+ Usr.SELF_FIELDS+" from Usr ";
//			List<Usr> usrs =  new UsrHelper().getQueryList(query(usrSql),Usr.SELF_FIELDS);
			List<PrjtUsrUpRecord> PrjtUsrUpRecords =  PrjtUsrUpRecordHelper.getQueryList(query(sql),fields);
			
//			for(PrjtUsrUpRecord pu : PrjtUsrUpRecords){
//				for(Usr u:usrs){
//					if(pu.getUsrId().intValue()==u.getId().intValue()){
//						pu.setUserName(u.getUsrName());
//					}
//				}
//			}
			return PrjtUsrUpRecords;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtUsrUpRecordDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<PrjtUsrUpRecord> list = PrjtUsrUpRecordHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (PrjtUsrUpRecord)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtUsrUpRecordDAO.load", e);
			throw e;
		}
	}

	public Object load(Object PrjtUsrUpRecord) throws java.sql.SQLException {
		return null;
//		PrjtUsrUpRecord _PrjtUsrUpRecord = (PrjtUsrUpRecord)PrjtUsrUpRecord;
//		String sql = "select "+PrjtUsrUpRecord.ALL_FIELDS+PrjtUsrUpRecordHelper.getSqlString()
//				+" and PrjtUsrUpRecord.Id = "+_PrjtUsrUpRecord.getId();
//		
//		try {
//			List<PrjtUsrUpRecord> list = PrjtUsrUpRecordHelper.getQueryList(query(sql),PrjtUsrUpRecord.ALL_FIELDS);
//			if(list.size() > 0)
//				return (PrjtUsrUpRecord)list.get(0);
//			else
//				return null;
//		}
//		catch(java.sql.SQLException e) {
//			Logger.getLogger(this.getClass()).error("PrjtUsrUpRecordDAO.load", e);
//			throw e;
//		}
	}
}