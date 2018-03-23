package zrprjt.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrprjt.vo.SchDtl;

public class BasicSchDtlHelper implements SqlHelper {
	public String getSqlString() {
		return " from SchDtl where 1=1";
	}

	public String getOrderBy() {
		return " order by SchDtl.SchDtlId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((SchDtl)object);
	}

	public String getSql4Amount(SchDtl schDtl) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(schDtl);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((SchDtl)object);
	}

	public String getSql4Delete(SchDtl schDtl) {
		return "delete from SchDtl where 1=1"+getSqlCondition(schDtl);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((SchDtl)object,fields);
	}

	public String getSql4All(SchDtl schDtl, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(schDtl)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((SchDtl)object,pageVO,fields);
	}

	public String getSql4Pages(SchDtl schDtl,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" SchDtl.SchDtlId "+ getSqlString()+getSqlCondition(schDtl)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(schDtl)+" and SchDtl.SchDtlId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((SchDtl)object);
	}

	public String getSqlCondition(SchDtl schDtl) {
		String sql = "";
		if(null != schDtl.getSchDtlId())
			sql += " and SchDtl.SchDtlId = '"+schDtl.getSchDtlId()+"'";
		if(null != schDtl.getSchId())
			sql += " and SchDtl.SchId = '"+schDtl.getSchId()+"'";
		if(null != schDtl.getStatus())
			sql += " and SchDtl.Status = '"+schDtl.getStatus()+"'";
		if(null != schDtl.getCreateBy())
			sql += " and SchDtl.CreateBy = '"+schDtl.getCreateBy()+"'";
		if(null != schDtl.getLastUpd())
			sql += " and SchDtl.LastUpd = '"+schDtl.getLastUpd()+"'";
		if(null != schDtl.getStartCreateDate()) 
			sql += " and SchDtl.CreateDate >= '"+GenericUtil.dateFormat(schDtl.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != schDtl.getEndCreateDate()) 
			sql += " and SchDtl.CreateDate <= '"+GenericUtil.dateFormat(schDtl.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != schDtl.getStartLastDate()) 
			sql += " and SchDtl.LastDate >= '"+GenericUtil.dateFormat(schDtl.getStartLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != schDtl.getEndLastDate()) 
			sql += " and SchDtl.LastDate <= '"+GenericUtil.dateFormat(schDtl.getEndLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != schDtl.getDocNm() && !schDtl.getDocNm().trim().equals(""))
			sql += " and SchDtl.DocNm = '"+schDtl.getDocNm().trim()+"'";
		if(null != schDtl.getRemark() && !schDtl.getRemark().trim().equals(""))
			sql += " and SchDtl.Remark = '"+schDtl.getRemark().trim()+"'";

		return sql;
	}

	public List<SchDtl> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<SchDtl> list = new ArrayList<SchDtl>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				SchDtl schDtl = new SchDtl();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SchDtlId") || _fields[i].equals("SchDtl.SchDtlId"))
						schDtl.setSchDtlId(rs.getInt("SchDtlId"));
					if(_fields[i].equals("SchId") || _fields[i].equals("SchDtl.SchId"))
						schDtl.setSchId(rs.getInt("SchId"));
					if(_fields[i].equals("Status") || _fields[i].equals("SchDtl.Status"))
						schDtl.setStatus(rs.getInt("Status"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("SchDtl.CreateBy"))
						schDtl.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("LastUpd") || _fields[i].equals("SchDtl.LastUpd"))
						schDtl.setLastUpd(rs.getInt("LastUpd"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("SchDtl.CreateDate"))
						schDtl.setCreateDate(rs.getTimestamp("CreateDate"));
					if(_fields[i].equals("LastDate") || _fields[i].equals("SchDtl.LastDate"))
						schDtl.setLastDate(rs.getTimestamp("LastDate"));
					if(_fields[i].equals("DocNm") || _fields[i].equals("SchDtl.DocNm"))
						schDtl.setDocNm(rs.getString("DocNm"));
					if(_fields[i].equals("Remark") || _fields[i].equals("SchDtl.Remark"))
						schDtl.setRemark(rs.getString("Remark"));

				}
				list.add(schDtl);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SchDtlHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into SchDtl("+fields.replaceAll("SchDtl\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(SchDtl schDtl,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SchDtlId") || _fields[i].equals("SchDtl.SchDtlId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schDtl.getSchDtlId());
					}
					else if(_fields[i].equals("SchId") || _fields[i].equals("SchDtl.SchId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schDtl.getSchId());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("SchDtl.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schDtl.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("SchDtl.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schDtl.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("SchDtl.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schDtl.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("SchDtl.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(schDtl.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("SchDtl.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(schDtl.getLastDate().getTime()));
					}
					else if(_fields[i].equals("DocNm") || _fields[i].equals("SchDtl.DocNm")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, schDtl.getDocNm());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("SchDtl.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, schDtl.getRemark());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("SchDtlHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update SchDtl set ";
		String[] _fields = fields.replaceAll("SchDtl\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Status") || _fields[i].equals("SchDtl.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("SchDtl.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("SchDtl.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("SchDtl.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastDate") || _fields[i].equals("SchDtl.LastDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("DocNm") || _fields[i].equals("SchDtl.DocNm"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Remark") || _fields[i].equals("SchDtl.Remark"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(SchDtl schDtl,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Status") || _fields[i].equals("SchDtl.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schDtl.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("SchDtl.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schDtl.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("SchDtl.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schDtl.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("SchDtl.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(schDtl.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("SchDtl.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(schDtl.getLastDate().getTime()));
					}
					else if(_fields[i].equals("DocNm") || _fields[i].equals("SchDtl.DocNm")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, schDtl.getDocNm());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("SchDtl.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, schDtl.getRemark());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("SchDtlHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(SchDtl schDtl) {
		String _fields = "";
		if(null != schDtl.getSchDtlId())
			_fields += "SchDtl.SchDtlId,";
		if(null != schDtl.getSchId())
			_fields += "SchDtl.SchId,";
		if(null != schDtl.getStatus())
			_fields += "SchDtl.Status,";
		if(null != schDtl.getCreateBy())
			_fields += "SchDtl.CreateBy,";
		if(null != schDtl.getLastUpd())
			_fields += "SchDtl.LastUpd,";
		if(null != schDtl.getCreateDate())
			_fields += "SchDtl.CreateDate,";
		if(null != schDtl.getLastDate())
			_fields += "SchDtl.LastDate,";
		if(null != schDtl.getDocNm())
			_fields += "SchDtl.DocNm,";
		if(null != schDtl.getRemark())
			_fields += "SchDtl.Remark,";

		return _fields.substring(0, _fields.length()-1);
	}
}