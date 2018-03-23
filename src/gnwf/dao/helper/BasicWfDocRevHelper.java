package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfDocRev;

public class BasicWfDocRevHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfDocRev where 1=1";
	}

	public String getOrderBy() {
		return " order by WfDocRev.DocId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfDocRev)object);
	}

	public String getSql4Amount(WfDocRev wfDocRev) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfDocRev);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfDocRev)object);
	}

	public String getSql4Delete(WfDocRev wfDocRev) {
		return "delete from WfDocRev where 1=1"+getSqlCondition(wfDocRev);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfDocRev)object,fields);
	}

	public String getSql4All(WfDocRev wfDocRev, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfDocRev)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfDocRev)object,pageVO,fields);
	}

	public String getSql4Pages(WfDocRev wfDocRev,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfDocRev.DocId "+ getSqlString()+getSqlCondition(wfDocRev)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfDocRev)+" and WfDocRev.DocId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfDocRev)object);
	}

	public String getSqlCondition(WfDocRev wfDocRev) {
		String sql = "";
		if(null != wfDocRev.getDocId())
			sql += " and WfDocRev.DocId = '"+wfDocRev.getDocId()+"'";
		if(null != wfDocRev.getTaskId())
			sql += " and WfDocRev.TaskId = '"+wfDocRev.getTaskId()+"'";
		if(null != wfDocRev.getCreateBy())
			sql += " and WfDocRev.CreateBy = '"+wfDocRev.getCreateBy()+"'";
		if(null != wfDocRev.getWfNo() && !wfDocRev.getWfNo().trim().equals(""))
			sql += " and WfDocRev.WfNo = '"+wfDocRev.getWfNo().trim()+"'";
		if(null != wfDocRev.getStartCreateDate()) 
			sql += " and WfDocRev.CreateDate >= '"+GenericUtil.dateFormat(wfDocRev.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfDocRev.getEndCreateDate()) 
			sql += " and WfDocRev.CreateDate <= '"+GenericUtil.dateFormat(wfDocRev.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfDocRev.getRevText() && !wfDocRev.getRevText().trim().equals(""))
			sql += " and WfDocRev.RevText = '"+wfDocRev.getRevText().trim()+"'";
		if(null != wfDocRev.getRevId() && !wfDocRev.getRevId().trim().equals(""))
			sql += " and WfDocRev.RevId = '"+wfDocRev.getRevId().trim()+"'";

		return sql;
	}

	public List<WfDocRev> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfDocRev> list = new ArrayList<WfDocRev>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfDocRev wfDocRev = new WfDocRev();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("DocId") || _fields[i].equals("WfDocRev.DocId"))
						wfDocRev.setDocId(rs.getInt("DocId"));
					else if(_fields[i].equals("TaskId") || _fields[i].equals("WfDocRev.TaskId"))
						wfDocRev.setTaskId(rs.getInt("TaskId"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfDocRev.CreateBy"))
						wfDocRev.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfDocRev.WfNo"))
						wfDocRev.setWfNo(rs.getString("WfNo"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfDocRev.CreateDate"))
						wfDocRev.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("RevText") || _fields[i].equals("WfDocRev.RevText"))
						wfDocRev.setRevText(rs.getString("RevText"));
					else if(_fields[i].equals("RevId") || _fields[i].equals("WfDocRev.RevId"))
						wfDocRev.setRevId(rs.getString("RevId"));

				}
				list.add(wfDocRev);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfDocRevHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfDocRev("+fields.replaceAll("WfDocRev\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfDocRev wfDocRev,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("DocId") || _fields[i].equals("WfDocRev.DocId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfDocRev.getDocId());
					}
					else if(_fields[i].equals("TaskId") || _fields[i].equals("WfDocRev.TaskId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfDocRev.getTaskId());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfDocRev.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfDocRev.getCreateBy());
					}
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfDocRev.WfNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfDocRev.getWfNo());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfDocRev.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfDocRev.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("RevText") || _fields[i].equals("WfDocRev.RevText")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfDocRev.getRevText());
					}
					else if(_fields[i].equals("RevId") || _fields[i].equals("WfDocRev.RevId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfDocRev.getRevId());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfDocRevHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfDocRev set ";
		String[] _fields = fields.replaceAll("WfDocRev\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("TaskId") || _fields[i].equals("WfDocRev.TaskId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("WfDocRev.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("WfNo") || _fields[i].equals("WfDocRev.WfNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("WfDocRev.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("RevText") || _fields[i].equals("WfDocRev.RevText"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfDocRev wfDocRev,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("TaskId") || _fields[i].equals("WfDocRev.TaskId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfDocRev.getTaskId());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfDocRev.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfDocRev.getCreateBy());
					}
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfDocRev.WfNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfDocRev.getWfNo());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfDocRev.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfDocRev.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("RevText") || _fields[i].equals("WfDocRev.RevText")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfDocRev.getRevText());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfDocRevHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfDocRev wfDocRev) {
		String _fields = "";
		if(null != wfDocRev.getDocId())
			_fields += "WfDocRev.DocId,";
		if(null != wfDocRev.getTaskId())
			_fields += "WfDocRev.TaskId,";
		if(null != wfDocRev.getCreateBy())
			_fields += "WfDocRev.CreateBy,";
		if(null != wfDocRev.getWfNo())
			_fields += "WfDocRev.WfNo,";
		if(null != wfDocRev.getCreateDate())
			_fields += "WfDocRev.CreateDate,";
		if(null != wfDocRev.getRevText())
			_fields += "WfDocRev.RevText,";
		if(null != wfDocRev.getRevId())
			_fields += "WfDocRev.RevId,";

		return _fields.substring(0, _fields.length()-1);
	}
}