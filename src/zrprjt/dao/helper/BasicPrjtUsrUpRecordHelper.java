package zrprjt.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrprjt.vo.PrjtUsrUpRecord;


public class BasicPrjtUsrUpRecordHelper implements SqlHelper {
	public String getSqlString() {
		return " from PrjtUsrUpRecord where 1=1";
	}

	public String getOrderBy() {
		return " order by PrjtUsrUpRecord.Id";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((PrjtUsrUpRecord)object);
	}

	public String getSql4Amount(PrjtUsrUpRecord prjtUsrUpRecord) { 
		//System.out.println("select count(*) as amount "+getSqlString()+getSqlCondition(prjtUsrUpRecord));
		return "select count(*) as amount "+getSqlString()+getSqlCondition(prjtUsrUpRecord);
	}
	

	public String getSql4Delete(Object object) {
		return getSql4Delete((PrjtUsrUpRecord)object);
	}

	public String getSql4Delete(PrjtUsrUpRecord prjtUsrUpRecord) { 
		return "delete from PrjtUsrUpRecord where 1=1"+getSqlCondition(prjtUsrUpRecord);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((PrjtUsrUpRecord)object,fields);
	}
 
	public String getSql4All(PrjtUsrUpRecord prjtUsrUpRecord, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(prjtUsrUpRecord)+getOrderBy(); 
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((PrjtUsrUpRecord)object,pageVO,fields);
	}

	public String getSql4Pages(PrjtUsrUpRecord prjtUsrUpRecord,PageVO pageVO, String fields) { 
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" PrjtUsrUpRecord.Id"+ getSqlString()+getSqlCondition(prjtUsrUpRecord)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(prjtUsrUpRecord)+" and PrjtUsrUpRecord.Id not in("+mstr+") "+getOrderBy();

		return sql;
	}
	
	public String getSql4Pages(PrjtUsrUpRecord prjtUsrUpRecord,PageVO pageVO, String fields,String sqlString,String conDitionSQl) {
		int pageSize = pageVO.getPageSize(); 
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" PrjtUsrUpRecord.Id"+ sqlString+conDitionSQl+getOrderBy();
		String sql = "select top "+pageSize+" "+sqlString + conDitionSQl +" and PrjtUsrUpRecord.Id not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((PrjtUsrUpRecord)object);
	}

	public String getSqlCondition(PrjtUsrUpRecord prjtUsrUpRecord) { 
		String sql = "";
		if(null != prjtUsrUpRecord.getId())
			sql += " and PrjtUsrUpRecord.Id = '"+prjtUsrUpRecord.getId()+"'";
		if(null != prjtUsrUpRecord.getPrjtUsrId())
			sql += " and PrjtUsrUpRecord.PrjtUsrId = '"+prjtUsrUpRecord.getPrjtUsrId()+"'";
		if(null != prjtUsrUpRecord.getUsrId())
			sql += " and PrjtUsrUpRecord.UsrId = '"+prjtUsrUpRecord.getUsrId()+"'";
//		
//		if(null != prjtUsrUpRecord.getCreateBy())
//			sql += " and PrjtUsrUpRecord.CreateBy = '"+prjtUsrUpRecord.getCreateBy()+"'";
//			sql += " and PrjtUsrUpRecord.CreateDate <= '"+GenericUtil.dateFormat(prjtUsrUpRecord.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != prjtUsrUpRecord.getUpTyp())
			sql += " and PrjtUsrUpRecord.UpTyp = '"+prjtUsrUpRecord.getUpTyp()+"'";
		
		
		return sql;
	}

	public List<PrjtUsrUpRecord> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<PrjtUsrUpRecord> list = new ArrayList<PrjtUsrUpRecord>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				PrjtUsrUpRecord PrjtUsrUpRecord = new PrjtUsrUpRecord();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Id") || _fields[i].equals("PrjtUsrUpRecord.Id"))
						PrjtUsrUpRecord.setId(rs.getInt("Id"));
					if(_fields[i].equals("PrjtUsrId") || _fields[i].equals("PrjtUsrUpRecord.PrjtUsrId"))
						PrjtUsrUpRecord.setPrjtUsrId(rs.getInt("PrjtUsrId"));
					if(_fields[i].equals("UsrId") || _fields[i].equals("PrjtUsrUpRecord.UsrId"))
						PrjtUsrUpRecord.setUsrId(rs.getInt("UsrId"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtUsrUpRecord.CreateBy"))
						PrjtUsrUpRecord.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtUsrUpRecord.CreateDate"))
						PrjtUsrUpRecord.setCreateDate(rs.getTimestamp("CreateDate"));
					if(_fields[i].equals("UpTyp") || _fields[i].equals("PrjtUsrUpRecord.UpTyp"))
						PrjtUsrUpRecord.setUpTyp(rs.getInt("UpTyp"));

				}
				list.add(PrjtUsrUpRecord);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtUsrUpRecordHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into PrjtUsrUpRecord("+fields.replaceAll("PrjtUsrUpRecord\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(PrjtUsrUpRecord PrjtUsrUpRecord,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Id") || _fields[i].equals("PrjtUsrUpRecord.Id")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, PrjtUsrUpRecord.getId());
					}
					else if(_fields[i].equals("UsrId") || _fields[i].equals("PrjtUsrUpRecord.UsrId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, PrjtUsrUpRecord.getUsrId());
					}
					else if(_fields[i].equals("PrjtUsrId") || _fields[i].equals("PrjtUsrUpRecord.PrjtUsrId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, PrjtUsrUpRecord.getPrjtUsrId());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtUsrUpRecord.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, PrjtUsrUpRecord.getCreateBy());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtUsrUpRecord.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(PrjtUsrUpRecord.getCreateDate().getTime()));
					}
					
					else if(_fields[i].equals("UpTyp") || _fields[i].equals("PrjtUsrUpRecord.UpTyp")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, PrjtUsrUpRecord.getUpTyp());
					}
					
			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			e.printStackTrace();
			Logger.getLogger(this.getClass()).error("PrjtUsrUpRecordHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		//TODO
//		String sql = "update PrjtUsrUpRecord set ";
//		String[] _fields = fields.replaceAll("PrjtUsrUpRecord\\.", "").split(",");
//		for(int i=0;i<_fields.length;i++) {
//					if(_fields[i].equals("Status") || _fields[i].equals("PrjtUsrUpRecord.Status"))
//						sql += _fields[i] + " = ?,";
//					if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtUsrUpRecord.CreateBy"))
//						sql += _fields[i] + " = ?,";
//					if(_fields[i].equals("LastUpd") || _fields[i].equals("PrjtUsrUpRecord.LastUpd"))
//						sql += _fields[i] + " = ?,";
//					if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtUsrUpRecord.CreateDate"))
//						sql += _fields[i] + " = ?,";
//					if(_fields[i].equals("LastDate") || _fields[i].equals("PrjtUsrUpRecord.LastDate"))
//						sql += _fields[i] + " = ?,";
//					if(_fields[i].equals("Priority") || _fields[i].equals("PrjtUsrUpRecord.Priority"))
//						sql += _fields[i] + " = ?,";
//					
//					if(_fields[i].equals("Priority") || _fields[i].equals("PrjtUsrUpRecord.Priority"))
//						sql += _fields[i] + " = ?,";
//					
//					if(_fields[i].equals("PrjtTypId") || _fields[i].equals("PrjtUsrUpRecord.PrjtTypId"))
//						sql += _fields[i] + " = ?,";
//					
//					if(_fields[i].equals("UsrType") || _fields[i].equals("PrjtUsrUpRecord.UsrType"))
//						sql += _fields[i] + " = ?,";
//					
//					
//
//
//		}
//		sql = sql.substring(0, sql.length()-1);
//		sql += " where "+key+" = '"+keyValue+"'";
		return null;
	}

	public void pstmtUpdate(PrjtUsrUpRecord PrjtUsrUpRecord,String sql,String fields) throws java.sql.SQLException {
		//TODO
//		String[] _fields = fields.split(",");
//		int index = 0;
//		try {
//			DbConnUtil.getDbconn().buildPreparedStatement(sql);
//			for(int i=0;i<_fields.length;i++) {
//					if(_fields[i].equals("Status") || _fields[i].equals("PrjtUsrUpRecord.Status")) {
//						index++;
//						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, PrjtUsrUpRecord.getStatus());
//					}
//					else if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtUsrUpRecord.CreateBy")) {
//						index++;
//						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, PrjtUsrUpRecord.getCreateBy());
//					}
//					else if(_fields[i].equals("LastUpd") || _fields[i].equals("PrjtUsrUpRecord.LastUpd")) {
//						index++;
//						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, PrjtUsrUpRecord.getLastUpd());
//					}
//					else if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtUsrUpRecord.CreateDate")) {
//						index++;
//						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(PrjtUsrUpRecord.getCreateDate().getTime()));
//					}
//					else if(_fields[i].equals("LastDate") || _fields[i].equals("PrjtUsrUpRecord.LastDate")) {
//						index++;
//						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(PrjtUsrUpRecord.getLastDate().getTime()));
//					}
//
//					if(_fields[i].equals("Priority") || _fields[i].equals("PrjtUsrUpRecord.Priority")) {
//						index++;
//						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, PrjtUsrUpRecord.getPriority());
//					}
//					
//					if(_fields[i].equals("PrjtTypId") || _fields[i].equals("PrjtUsrUpRecord.PrjtTypId")) {
//						index++;
//						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, PrjtUsrUpRecord.getPrjtTypId());
//					}
//					
////					if(_fields[i].equals("UsrType") || _fields[i].equals("PrjtUsrUpRecord.UsrType")) {
////						index++;
////						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, PrjtUsrUpRecord.getUsrType());
////					}
//					
//					
//					
//					
//			}
//		DbConnUtil.getDbconn().getCurrentPstmt().execute();
//		}
//		catch(Exception e) {
//			Logger.getLogger(this.getClass()).error("PrjtUsrUpRecordHelper.pstmtUpdate SQLException", e);
//			throw new java.sql.SQLException();
//		}
	}

	public String getFields(PrjtUsrUpRecord PrjtUsrUpRecord) {
		String _fields = "";
		if(null != PrjtUsrUpRecord.getId())
			_fields += "PrjtUsrUpRecord.Id,";
//		if(null != PrjtUsrUpRecord.getPrjtUsrId())
//			_fields += "PrjtUsrUpRecord.RoleId,";
		if(null != PrjtUsrUpRecord.getPrjtUsrId())
			_fields += "PrjtUsrUpRecord.PrjtUsrId,";
		if(null != PrjtUsrUpRecord.getUsrId())
			_fields += "PrjtUsrUpRecord.UsrId,";
		if(null != PrjtUsrUpRecord.getCreateBy())
			_fields += "PrjtUsrUpRecord.CreateBy,";
		if(null != PrjtUsrUpRecord.getCreateDate())
			_fields += "PrjtUsrUpRecord.CreateDate,";
		if(null != PrjtUsrUpRecord.getUpTyp())
			_fields += "PrjtUsrUpRecord.UpTyp,";

		return _fields.substring(0, _fields.length()-1);
	}
}