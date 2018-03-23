package zrprjt.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrprjt.vo.PrjtTyp;

public class BasicPrjtTypHelper implements SqlHelper {
	public String getSqlString() {
		return " from PrjtTyp where 1=1";
	}

	public String getOrderBy() {
		return " order by PrjtTyp.TypId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((PrjtTyp)object);
	}

	public String getSql4Amount(PrjtTyp prjtTyp) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(prjtTyp);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((PrjtTyp)object);
	}

	public String getSql4Delete(PrjtTyp prjtTyp) {
		return "delete from PrjtTyp where 1=1"+getSqlCondition(prjtTyp);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((PrjtTyp)object,fields);
	}

	public String getSql4All(PrjtTyp prjtTyp, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(prjtTyp)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((PrjtTyp)object,pageVO,fields);
	}

	public String getSql4Pages(PrjtTyp prjtTyp,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" PrjtTyp.TypId "+ getSqlString()+getSqlCondition(prjtTyp)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(prjtTyp)+" and PrjtTyp.TypId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((PrjtTyp)object);
	}

	public String getSqlCondition(PrjtTyp prjtTyp) {
		String sql = "";
		if(null != prjtTyp.getTypId())
			sql += " and PrjtTyp.TypId = '"+prjtTyp.getTypId()+"'";
		if(null != prjtTyp.getStatus())
			sql += " and PrjtTyp.Status = '"+prjtTyp.getStatus()+"'";
		if(null != prjtTyp.getCreateBy())
			sql += " and PrjtTyp.CreateBy = '"+prjtTyp.getCreateBy()+"'";
		if(null != prjtTyp.getLastUpd())
			sql += " and PrjtTyp.LastUpd = '"+prjtTyp.getLastUpd()+"'";
		if(null != prjtTyp.getStartCreateDate()) 
			sql += " and PrjtTyp.CreateDate >= '"+GenericUtil.dateFormat(prjtTyp.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != prjtTyp.getEndCreateDate()) 
			sql += " and PrjtTyp.CreateDate <= '"+GenericUtil.dateFormat(prjtTyp.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != prjtTyp.getStartLastDate()) 
			sql += " and PrjtTyp.LastDate >= '"+GenericUtil.dateFormat(prjtTyp.getStartLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != prjtTyp.getEndLastDate()) 
			sql += " and PrjtTyp.LastDate <= '"+GenericUtil.dateFormat(prjtTyp.getEndLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != prjtTyp.getTypNm() && !prjtTyp.getTypNm().trim().equals(""))
			sql += " and PrjtTyp.TypNm = '"+prjtTyp.getTypNm().trim()+"'";
		if(null != prjtTyp.getRemark() && !prjtTyp.getRemark().trim().equals(""))
			sql += " and PrjtTyp.Remark = '"+prjtTyp.getRemark().trim()+"'";

		return sql;
	}

	public List<PrjtTyp> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<PrjtTyp> list = new ArrayList<PrjtTyp>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				PrjtTyp prjtTyp = new PrjtTyp();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("TypId") || _fields[i].equals("PrjtTyp.TypId"))
						prjtTyp.setTypId(rs.getInt("TypId"));
					if(_fields[i].equals("Status") || _fields[i].equals("PrjtTyp.Status"))
						prjtTyp.setStatus(rs.getInt("Status"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtTyp.CreateBy"))
						prjtTyp.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("LastUpd") || _fields[i].equals("PrjtTyp.LastUpd"))
						prjtTyp.setLastUpd(rs.getInt("LastUpd"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtTyp.CreateDate"))
						prjtTyp.setCreateDate(rs.getTimestamp("CreateDate"));
					if(_fields[i].equals("LastDate") || _fields[i].equals("PrjtTyp.LastDate"))
						prjtTyp.setLastDate(rs.getTimestamp("LastDate"));
					if(_fields[i].equals("TypNm") || _fields[i].equals("PrjtTyp.TypNm"))
						prjtTyp.setTypNm(rs.getString("TypNm"));
					if(_fields[i].equals("Remark") || _fields[i].equals("PrjtTyp.Remark"))
						prjtTyp.setRemark(rs.getString("Remark"));

				}
				list.add(prjtTyp);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtTypHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into PrjtTyp("+fields.replaceAll("PrjtTyp\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(PrjtTyp prjtTyp,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("TypId") || _fields[i].equals("PrjtTyp.TypId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtTyp.getTypId());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("PrjtTyp.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtTyp.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtTyp.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtTyp.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("PrjtTyp.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtTyp.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtTyp.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtTyp.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("PrjtTyp.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtTyp.getLastDate().getTime()));
					}
					else if(_fields[i].equals("TypNm") || _fields[i].equals("PrjtTyp.TypNm")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtTyp.getTypNm());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("PrjtTyp.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtTyp.getRemark());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("PrjtTypHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update PrjtTyp set ";
		String[] _fields = fields.replaceAll("PrjtTyp\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Status") || _fields[i].equals("PrjtTyp.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtTyp.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("PrjtTyp.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtTyp.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastDate") || _fields[i].equals("PrjtTyp.LastDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("TypNm") || _fields[i].equals("PrjtTyp.TypNm"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Remark") || _fields[i].equals("PrjtTyp.Remark"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(PrjtTyp prjtTyp,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Status") || _fields[i].equals("PrjtTyp.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtTyp.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtTyp.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtTyp.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("PrjtTyp.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, prjtTyp.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtTyp.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtTyp.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("PrjtTyp.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(prjtTyp.getLastDate().getTime()));
					}
					else if(_fields[i].equals("TypNm") || _fields[i].equals("PrjtTyp.TypNm")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtTyp.getTypNm());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("PrjtTyp.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, prjtTyp.getRemark());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("PrjtTypHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(PrjtTyp prjtTyp) {
		String _fields = "";
		if(null != prjtTyp.getTypId())
			_fields += "PrjtTyp.TypId,";
		if(null != prjtTyp.getStatus())
			_fields += "PrjtTyp.Status,";
		if(null != prjtTyp.getCreateBy())
			_fields += "PrjtTyp.CreateBy,";
		if(null != prjtTyp.getLastUpd())
			_fields += "PrjtTyp.LastUpd,";
		if(null != prjtTyp.getCreateDate())
			_fields += "PrjtTyp.CreateDate,";
		if(null != prjtTyp.getLastDate())
			_fields += "PrjtTyp.LastDate,";
		if(null != prjtTyp.getTypNm())
			_fields += "PrjtTyp.TypNm,";
		if(null != prjtTyp.getRemark())
			_fields += "PrjtTyp.Remark,";

		return _fields.substring(0, _fields.length()-1);
	}
}