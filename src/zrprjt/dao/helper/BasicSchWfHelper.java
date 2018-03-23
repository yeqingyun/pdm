package zrprjt.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrprjt.vo.SchWf;

public class BasicSchWfHelper implements SqlHelper {
	public String getSqlString() {
		return " from SchWf where 1=1";
	}

	public String getOrderBy() {
		return " order by SchWf.SchId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((SchWf)object);
	}

	public String getSql4Amount(SchWf schWf) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(schWf);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((SchWf)object);
	}

	public String getSql4Delete(SchWf schWf) {
		return "delete from SchWf where 1=1"+getSqlCondition(schWf);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((SchWf)object,fields);
	}

	public String getSql4All(SchWf schWf, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(schWf)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((SchWf)object,pageVO,fields);
	}

	public String getSql4Pages(SchWf schWf,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" SchWf.SchId "+ getSqlString()+getSqlCondition(schWf)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(schWf)+" and SchWf.SchId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((SchWf)object);
	}

	public String getSqlCondition(SchWf schWf) {
		String sql = "";
		if(null != schWf.getSchId())
			sql += " and SchWf.SchId = '"+schWf.getSchId()+"'";
		if(null != schWf.getFlowId())
			sql += " and SchWf.FlowId = '"+schWf.getFlowId()+"'";
		if(null != schWf.getStatus())
			sql += " and SchWf.Status = '"+schWf.getStatus()+"'";
		if(null != schWf.getCreateBy())
			sql += " and SchWf.CreateBy = '"+schWf.getCreateBy()+"'";
		if(null != schWf.getLastUpd())
			sql += " and SchWf.LastUpd = '"+schWf.getLastUpd()+"'";
		if(null != schWf.getStartCreateDate()) 
			sql += " and SchWf.CreateDate >= '"+GenericUtil.dateFormat(schWf.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != schWf.getEndCreateDate()) 
			sql += " and SchWf.CreateDate <= '"+GenericUtil.dateFormat(schWf.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != schWf.getStartLastDate()) 
			sql += " and SchWf.LastDate >= '"+GenericUtil.dateFormat(schWf.getStartLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != schWf.getEndLastDate()) 
			sql += " and SchWf.LastDate <= '"+GenericUtil.dateFormat(schWf.getEndLastDate(), "yyyy-MM-dd 00:00:00")+"'";

		return sql;
	}

	public List<SchWf> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<SchWf> list = new ArrayList<SchWf>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				SchWf schWf = new SchWf();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SchId") || _fields[i].equals("SchWf.SchId"))
						schWf.setSchId(rs.getInt("SchId"));
					if(_fields[i].equals("FlowId") || _fields[i].equals("SchWf.FlowId"))
						schWf.setFlowId(rs.getInt("FlowId"));
					if(_fields[i].equals("Status") || _fields[i].equals("SchWf.Status"))
						schWf.setStatus(rs.getInt("Status"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("SchWf.CreateBy"))
						schWf.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("LastUpd") || _fields[i].equals("SchWf.LastUpd"))
						schWf.setLastUpd(rs.getInt("LastUpd"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("SchWf.CreateDate"))
						schWf.setCreateDate(rs.getTimestamp("CreateDate"));
					if(_fields[i].equals("LastDate") || _fields[i].equals("SchWf.LastDate"))
						schWf.setLastDate(rs.getTimestamp("LastDate"));

				}
				list.add(schWf);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SchWfHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into SchWf("+fields.replaceAll("SchWf\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(SchWf schWf,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SchId") || _fields[i].equals("SchWf.SchId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schWf.getSchId());
					}
					else if(_fields[i].equals("FlowId") || _fields[i].equals("SchWf.FlowId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schWf.getFlowId());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("SchWf.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schWf.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("SchWf.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schWf.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("SchWf.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schWf.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("SchWf.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(schWf.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("SchWf.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(schWf.getLastDate().getTime()));
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("SchWfHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update SchWf set ";
		String[] _fields = fields.replaceAll("SchWf\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Status") || _fields[i].equals("SchWf.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("SchWf.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("SchWf.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("SchWf.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastDate") || _fields[i].equals("SchWf.LastDate"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(SchWf schWf,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Status") || _fields[i].equals("SchWf.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schWf.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("SchWf.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schWf.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("SchWf.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, schWf.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("SchWf.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(schWf.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("SchWf.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(schWf.getLastDate().getTime()));
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("SchWfHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(SchWf schWf) {
		String _fields = "";
		if(null != schWf.getSchId())
			_fields += "SchWf.SchId,";
		if(null != schWf.getFlowId())
			_fields += "SchWf.FlowId,";
		if(null != schWf.getStatus())
			_fields += "SchWf.Status,";
		if(null != schWf.getCreateBy())
			_fields += "SchWf.CreateBy,";
		if(null != schWf.getLastUpd())
			_fields += "SchWf.LastUpd,";
		if(null != schWf.getCreateDate())
			_fields += "SchWf.CreateDate,";
		if(null != schWf.getLastDate())
			_fields += "SchWf.LastDate,";

		return _fields.substring(0, _fields.length()-1);
	}
}