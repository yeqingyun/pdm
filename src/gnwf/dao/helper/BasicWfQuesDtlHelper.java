package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfQuesDtl;

public class BasicWfQuesDtlHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfQuesDtl where 1=1";
	}

	public String getOrderBy() {
		return " order by WfQuesDtl.QuesDtlId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfQuesDtl)object);
	}

	public String getSql4Amount(WfQuesDtl wfQuesDtl) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfQuesDtl);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfQuesDtl)object);
	}

	public String getSql4Delete(WfQuesDtl wfQuesDtl) {
		return "delete from WfQuesDtl where 1=1"+getSqlCondition(wfQuesDtl);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfQuesDtl)object,fields);
	}

	public String getSql4All(WfQuesDtl wfQuesDtl, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfQuesDtl)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfQuesDtl)object,pageVO,fields);
	}

	public String getSql4Pages(WfQuesDtl wfQuesDtl,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfQuesDtl.QuesDtlId "+ getSqlString()+getSqlCondition(wfQuesDtl)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfQuesDtl)+" and WfQuesDtl.QuesDtlId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfQuesDtl)object);
	}

	public String getSqlCondition(WfQuesDtl wfQuesDtl) {
		String sql = "";
		if(null != wfQuesDtl.getQuesDtlId())
			sql += " and WfQuesDtl.QuesDtlId = '"+wfQuesDtl.getQuesDtlId()+"'";
		if(null != wfQuesDtl.getQuesId())
			sql += " and WfQuesDtl.QuesId = '"+wfQuesDtl.getQuesId()+"'";
		if(null != wfQuesDtl.getUserId())
			sql += " and WfQuesDtl.UserId = '"+wfQuesDtl.getUserId()+"'";
		if(null != wfQuesDtl.getStatus())
			sql += " and WfQuesDtl.Status = '"+wfQuesDtl.getStatus()+"'";
		if(null != wfQuesDtl.getCreateBy())
			sql += " and WfQuesDtl.CreateBy = '"+wfQuesDtl.getCreateBy()+"'";
		if(null != wfQuesDtl.getLastUpd())
			sql += " and WfQuesDtl.LastUpd = '"+wfQuesDtl.getLastUpd()+"'";
		if(null != wfQuesDtl.getStartCreateDate()) 
			sql += " and WfQuesDtl.CreateDate >= '"+GenericUtil.dateFormat(wfQuesDtl.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfQuesDtl.getEndCreateDate()) 
			sql += " and WfQuesDtl.CreateDate <= '"+GenericUtil.dateFormat(wfQuesDtl.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfQuesDtl.getStartLastUpdDate()) 
			sql += " and WfQuesDtl.LastUpdDate >= '"+GenericUtil.dateFormat(wfQuesDtl.getStartLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfQuesDtl.getEndLastUpdDate()) 
			sql += " and WfQuesDtl.LastUpdDate <= '"+GenericUtil.dateFormat(wfQuesDtl.getEndLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfQuesDtl.getQuesTxt() && !wfQuesDtl.getQuesTxt().trim().equals(""))
			sql += " and WfQuesDtl.QuesTxt = '"+wfQuesDtl.getQuesTxt().trim()+"'";
		if(null != wfQuesDtl.getTitle() && !wfQuesDtl.getTitle().trim().equals(""))
			sql += " and WfQuesDtl.Title = '"+wfQuesDtl.getTitle().trim()+"'";

		return sql;
	}

	public List<WfQuesDtl> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfQuesDtl> list = new ArrayList<WfQuesDtl>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfQuesDtl wfQuesDtl = new WfQuesDtl();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("QuesDtlId") || _fields[i].equals("WfQuesDtl.QuesDtlId"))
						wfQuesDtl.setQuesDtlId(rs.getInt("QuesDtlId"));
					else if(_fields[i].equals("QuesId") || _fields[i].equals("WfQuesDtl.QuesId"))
						wfQuesDtl.setQuesId(rs.getInt("QuesId"));
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfQuesDtl.UserId"))
						wfQuesDtl.setUserId(rs.getInt("UserId"));
					else if(_fields[i].equals("Status") || _fields[i].equals("WfQuesDtl.Status"))
						wfQuesDtl.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfQuesDtl.CreateBy"))
						wfQuesDtl.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfQuesDtl.LastUpd"))
						wfQuesDtl.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfQuesDtl.CreateDate"))
						wfQuesDtl.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfQuesDtl.LastUpdDate"))
						wfQuesDtl.setLastUpdDate(rs.getTimestamp("LastUpdDate"));
					else if(_fields[i].equals("QuesTxt") || _fields[i].equals("WfQuesDtl.QuesTxt"))
						wfQuesDtl.setQuesTxt(rs.getString("QuesTxt"));
					else if(_fields[i].equals("Title") || _fields[i].equals("WfQuesDtl.Title"))
						wfQuesDtl.setTitle(rs.getString("Title"));

				}
				list.add(wfQuesDtl);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfQuesDtlHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfQuesDtl("+fields.replaceAll("WfQuesDtl\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfQuesDtl wfQuesDtl,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("QuesDtlId") || _fields[i].equals("WfQuesDtl.QuesDtlId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQuesDtl.getQuesDtlId());
					}
					else if(_fields[i].equals("QuesId") || _fields[i].equals("WfQuesDtl.QuesId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQuesDtl.getQuesId());
					}
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfQuesDtl.UserId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQuesDtl.getUserId());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("WfQuesDtl.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQuesDtl.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfQuesDtl.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQuesDtl.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfQuesDtl.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQuesDtl.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfQuesDtl.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfQuesDtl.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfQuesDtl.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfQuesDtl.getLastUpdDate().getTime()));
					}
					else if(_fields[i].equals("QuesTxt") || _fields[i].equals("WfQuesDtl.QuesTxt")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQuesDtl.getQuesTxt());
					}
					else if(_fields[i].equals("Title") || _fields[i].equals("WfQuesDtl.Title")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQuesDtl.getTitle());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfQuesDtlHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfQuesDtl set ";
		String[] _fields = fields.replaceAll("WfQuesDtl\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("UserId") || _fields[i].equals("WfQuesDtl.UserId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("WfQuesDtl.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("WfQuesDtl.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("WfQuesDtl.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("WfQuesDtl.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfQuesDtl.LastUpdDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("QuesTxt") || _fields[i].equals("WfQuesDtl.QuesTxt"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Title") || _fields[i].equals("WfQuesDtl.Title"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfQuesDtl wfQuesDtl,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("UserId") || _fields[i].equals("WfQuesDtl.UserId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQuesDtl.getUserId());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("WfQuesDtl.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQuesDtl.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfQuesDtl.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQuesDtl.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfQuesDtl.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQuesDtl.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfQuesDtl.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfQuesDtl.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfQuesDtl.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfQuesDtl.getLastUpdDate().getTime()));
					}
					else if(_fields[i].equals("QuesTxt") || _fields[i].equals("WfQuesDtl.QuesTxt")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQuesDtl.getQuesTxt());
					}
					else if(_fields[i].equals("Title") || _fields[i].equals("WfQuesDtl.Title")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQuesDtl.getTitle());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfQuesDtlHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfQuesDtl wfQuesDtl) {
		String _fields = "";
		if(null != wfQuesDtl.getQuesDtlId())
			_fields += "WfQuesDtl.QuesDtlId,";
		if(null != wfQuesDtl.getQuesId())
			_fields += "WfQuesDtl.QuesId,";
		if(null != wfQuesDtl.getUserId())
			_fields += "WfQuesDtl.UserId,";
		if(null != wfQuesDtl.getStatus())
			_fields += "WfQuesDtl.Status,";
		if(null != wfQuesDtl.getCreateBy())
			_fields += "WfQuesDtl.CreateBy,";
		if(null != wfQuesDtl.getLastUpd())
			_fields += "WfQuesDtl.LastUpd,";
		if(null != wfQuesDtl.getCreateDate())
			_fields += "WfQuesDtl.CreateDate,";
		if(null != wfQuesDtl.getLastUpdDate())
			_fields += "WfQuesDtl.LastUpdDate,";
		if(null != wfQuesDtl.getQuesTxt())
			_fields += "WfQuesDtl.QuesTxt,";
		if(null != wfQuesDtl.getTitle())
			_fields += "WfQuesDtl.Title,";

		return _fields.substring(0, _fields.length()-1);
	}
}