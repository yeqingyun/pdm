package zrsy.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrsy.vo.ChlnV;

public class BasicChlnVHelper implements SqlHelper {
	public String getSqlString() {
		return " from ChlnV where 1=1";
	}

	public String getOrderBy() {
		return " order by ChlnV.FileNm";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((ChlnV)object);
	}

	public String getSql4Amount(ChlnV chlnV) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(chlnV);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((ChlnV)object);
	}

	public String getSql4Delete(ChlnV chlnV) {
		return "delete from ChlnV where 1=1"+getSqlCondition(chlnV);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((ChlnV)object,fields);
	}

	public String getSql4All(ChlnV chlnV, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(chlnV)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((ChlnV)object,pageVO,fields);
	}

	public String getSql4Pages(ChlnV chlnV,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" ChlnV.FileNm "+ getSqlString()+getSqlCondition(chlnV)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(chlnV)+" and ChlnV.FileNm not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((ChlnV)object);
	}

	public String getSqlCondition(ChlnV chlnV) {
		String sql = "";
		if(null != chlnV.getFileNm() && !chlnV.getFileNm().trim().equals(""))
			sql += " and ChlnV.FileNm = '"+chlnV.getFileNm().trim()+"'";
		if(null != chlnV.getChlnNo())
			sql += " and ChlnV.ChlnNo = '"+chlnV.getChlnNo()+"'";
		if(null != chlnV.getCreateBy())
			sql += " and ChlnV.CreateBy = '"+chlnV.getCreateBy()+"'";
		if(null != chlnV.getStartCreateDate()) 
			sql += " and ChlnV.CreateDate >= '"+GenericUtil.dateFormat(chlnV.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != chlnV.getEndCreateDate()) 
			sql += " and ChlnV.CreateDate <= '"+GenericUtil.dateFormat(chlnV.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != chlnV.getLastUpd())
			sql += " and ChlnV.LastUpd = '"+chlnV.getLastUpd()+"'";
		if(null != chlnV.getStartLastDate()) 
			sql += " and ChlnV.LastDate >= '"+GenericUtil.dateFormat(chlnV.getStartLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != chlnV.getEndLastDate()) 
			sql += " and ChlnV.LastDate <= '"+GenericUtil.dateFormat(chlnV.getEndLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != chlnV.getChlnTyp() && !chlnV.getChlnTyp().trim().equals(""))
			sql += " and ChlnV.ChlnTyp = '"+chlnV.getChlnTyp().trim()+"'";

		return sql;
	}

	public List<ChlnV> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<ChlnV> list = new ArrayList<ChlnV>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				ChlnV chlnV = new ChlnV();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("FileNm") || _fields[i].equals("ChlnV.FileNm"))
						chlnV.setFileNm(rs.getString("FileNm"));
					else if(_fields[i].equals("ChlnNo") || _fields[i].equals("ChlnV.ChlnNo"))
						chlnV.setChlnNo(rs.getInt("ChlnNo"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("ChlnV.CreateBy"))
						chlnV.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("ChlnV.CreateDate"))
						chlnV.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("ChlnV.LastUpd"))
						chlnV.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("LastDate") || _fields[i].equals("ChlnV.LastDate"))
						chlnV.setLastDate(rs.getTimestamp("LastDate"));
					else if(_fields[i].equals("ChlnTyp") || _fields[i].equals("ChlnV.ChlnTyp"))
						chlnV.setChlnTyp(rs.getString("ChlnTyp"));

				}
				list.add(chlnV);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ChlnVHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into ChlnV("+fields.replaceAll("ChlnV\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(ChlnV chlnV,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("FileNm") || _fields[i].equals("ChlnV.FileNm")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, chlnV.getFileNm());
					}
					else if(_fields[i].equals("ChlnNo") || _fields[i].equals("ChlnV.ChlnNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, chlnV.getChlnNo());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("ChlnV.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, chlnV.getCreateBy());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("ChlnV.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(chlnV.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("ChlnV.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, chlnV.getLastUpd());
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("ChlnV.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(chlnV.getLastDate().getTime()));
					}
					else if(_fields[i].equals("ChlnTyp") || _fields[i].equals("ChlnV.ChlnTyp")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, chlnV.getChlnTyp());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("ChlnVHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update ChlnV set ";
		String[] _fields = fields.replaceAll("ChlnV\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ChlnNo") || _fields[i].equals("ChlnV.ChlnNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("ChlnV.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("ChlnV.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("ChlnV.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastDate") || _fields[i].equals("ChlnV.LastDate"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(ChlnV chlnV,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ChlnNo") || _fields[i].equals("ChlnV.ChlnNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, chlnV.getChlnNo());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("ChlnV.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, chlnV.getCreateBy());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("ChlnV.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(chlnV.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("ChlnV.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, chlnV.getLastUpd());
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("ChlnV.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(chlnV.getLastDate().getTime()));
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("ChlnVHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(ChlnV chlnV) {
		String _fields = "";
		if(null != chlnV.getFileNm())
			_fields += "ChlnV.FileNm,";
		if(null != chlnV.getChlnNo())
			_fields += "ChlnV.ChlnNo,";
		if(null != chlnV.getCreateBy())
			_fields += "ChlnV.CreateBy,";
		if(null != chlnV.getCreateDate())
			_fields += "ChlnV.CreateDate,";
		if(null != chlnV.getLastUpd())
			_fields += "ChlnV.LastUpd,";
		if(null != chlnV.getLastDate())
			_fields += "ChlnV.LastDate,";
		if(null != chlnV.getChlnTyp())
			_fields += "ChlnV.ChlnTyp,";

		return _fields.substring(0, _fields.length()-1);
	}
}