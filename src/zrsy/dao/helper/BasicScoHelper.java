package zrsy.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrsy.vo.Sco;

public class BasicScoHelper implements SqlHelper {
	public String getSqlString() {
		return " from Sco where 1=1";
	}

	public String getOrderBy() {
		return " order by Sco.ScoId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((Sco)object);
	}

	public String getSql4Amount(Sco sco) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(sco);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((Sco)object);
	}

	public String getSql4Delete(Sco sco) {
		return "delete from Sco where 1=1"+getSqlCondition(sco);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((Sco)object,fields);
	}

	public String getSql4All(Sco sco, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(sco)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((Sco)object,pageVO,fields);
	}

	public String getSql4Pages(Sco sco,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" Sco.ScoId "+ getSqlString()+getSqlCondition(sco)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(sco)+" and Sco.ScoId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((Sco)object);
	}

	public String getSqlCondition(Sco sco) {
		String sql = "";
		if(null != sco.getStartCreateDate()) 
			sql += " and Sco.CreateDate >= '"+GenericUtil.dateFormat(sco.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != sco.getEndCreateDate()) 
			sql += " and Sco.CreateDate <= '"+GenericUtil.dateFormat(sco.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != sco.getStartLastDate()) 
			sql += " and Sco.LastDate >= '"+GenericUtil.dateFormat(sco.getStartLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != sco.getEndLastDate()) 
			sql += " and Sco.LastDate <= '"+GenericUtil.dateFormat(sco.getEndLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != sco.getScoId())
			sql += " and Sco.ScoId = '"+sco.getScoId()+"'";
		if(null != sco.getScope())
			sql += " and Sco.Scope = '"+sco.getScope()+"'";
		if(null != sco.getStatus())
			sql += " and Sco.Status = '"+sco.getStatus()+"'";
		if(null != sco.getCreateBy())
			sql += " and Sco.CreateBy = '"+sco.getCreateBy()+"'";
		if(null != sco.getLastUpd())
			sql += " and Sco.LastUpd = '"+sco.getLastUpd()+"'";
		if(null != sco.getScoNm() && !sco.getScoNm().trim().equals(""))
			sql += " and Sco.ScoNm = '"+sco.getScoNm().trim()+"'";

		return sql;
	}

	public List<Sco> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<Sco> list = new ArrayList<Sco>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				Sco sco = new Sco();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("CreateDate") || _fields[i].equals("Sco.CreateDate"))
						sco.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastDate") || _fields[i].equals("Sco.LastDate"))
						sco.setLastDate(rs.getTimestamp("LastDate"));
					else if(_fields[i].equals("ScoId") || _fields[i].equals("Sco.ScoId"))
						sco.setScoId(rs.getInt("ScoId"));
					else if(_fields[i].equals("Scope") || _fields[i].equals("Sco.Scope"))
						sco.setScope(rs.getInt("Scope"));
					else if(_fields[i].equals("Status") || _fields[i].equals("Sco.Status"))
						sco.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("Sco.CreateBy"))
						sco.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("Sco.LastUpd"))
						sco.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("ScoNm") || _fields[i].equals("Sco.ScoNm"))
						sco.setScoNm(rs.getString("ScoNm"));

				}
				list.add(sco);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ScoHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into [ZrSy].[dbo].[Sco]("+fields.replaceAll("Sco\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(Sco sco,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("CreateDate") || _fields[i].equals("Sco.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(sco.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("Sco.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(sco.getLastDate().getTime()));
					}
					else if(_fields[i].equals("ScoId") || _fields[i].equals("Sco.ScoId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, sco.getScoId());
					}
					else if(_fields[i].equals("Scope") || _fields[i].equals("Sco.Scope")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, sco.getScope());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("Sco.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, sco.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("Sco.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, sco.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("Sco.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, sco.getLastUpd());
					}
					else if(_fields[i].equals("ScoNm") || _fields[i].equals("Sco.ScoNm")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, sco.getScoNm());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("ScoHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update [ZrSy].[dbo].[Sco] set ";
		String[] _fields = fields.replaceAll("Sco\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("CreateDate") || _fields[i].equals("Sco.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastDate") || _fields[i].equals("Sco.LastDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Scope") || _fields[i].equals("Sco.Scope"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("Sco.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("Sco.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("Sco.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("ScoNm") || _fields[i].equals("Sco.ScoNm"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(Sco sco,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("CreateDate") || _fields[i].equals("Sco.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(sco.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("Sco.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(sco.getLastDate().getTime()));
					}
					else if(_fields[i].equals("Scope") || _fields[i].equals("Sco.Scope")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, sco.getScope());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("Sco.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, sco.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("Sco.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, sco.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("Sco.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, sco.getLastUpd());
					}
					else if(_fields[i].equals("ScoNm") || _fields[i].equals("Sco.ScoNm")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, sco.getScoNm());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("ScoHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(Sco sco) {
		String _fields = "";
		if(null != sco.getCreateDate())
			_fields += "Sco.CreateDate,";
		if(null != sco.getLastDate())
			_fields += "Sco.LastDate,";
		if(null != sco.getScoId())
			_fields += "Sco.ScoId,";
		if(null != sco.getScope())
			_fields += "Sco.Scope,";
		if(null != sco.getStatus())
			_fields += "Sco.Status,";
		if(null != sco.getCreateBy())
			_fields += "Sco.CreateBy,";
		if(null != sco.getLastUpd())
			_fields += "Sco.LastUpd,";
		if(null != sco.getScoNm())
			_fields += "Sco.ScoNm,";

		return _fields.substring(0, _fields.length()-1);
	}
}