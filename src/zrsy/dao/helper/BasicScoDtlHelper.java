package zrsy.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrsy.vo.ScoDtl;

public class BasicScoDtlHelper implements SqlHelper {
	public String getSqlString() {
		return " from ScoDtl where 1=1";
	}

	public String getOrderBy() {
		return " order by ScoDtl.ScoId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((ScoDtl)object);
	}

	public String getSql4Amount(ScoDtl scoDtl) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(scoDtl);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((ScoDtl)object);
	}

	public String getSql4Delete(ScoDtl scoDtl) {
		return "delete from ScoDtl where 1=1"+getSqlCondition(scoDtl);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((ScoDtl)object,fields);
	}

	public String getSql4All(ScoDtl scoDtl, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(scoDtl)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((ScoDtl)object,pageVO,fields);
	}

	public String getSql4Pages(ScoDtl scoDtl,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" ScoDtl.ScoId "+ getSqlString()+getSqlCondition(scoDtl)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(scoDtl)+" and ScoDtl.ScoId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((ScoDtl)object);
	}

	public String getSqlCondition(ScoDtl scoDtl) {
		String sql = "";
		if(null != scoDtl.getScoId())
			sql += " and ScoDtl.ScoId = '"+scoDtl.getScoId()+"'";
		if(null != scoDtl.getComId())
			sql += " and ScoDtl.ComId = '"+scoDtl.getComId()+"'";
		if(null != scoDtl.getDetpId())
			sql += " and ScoDtl.DetpId = '"+scoDtl.getDetpId()+"'";
		if(null != scoDtl.getUsrId())
			sql += " and ScoDtl.UsrId = '"+scoDtl.getUsrId()+"'";

		return sql;
	}

	public List<ScoDtl> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<ScoDtl> list = new ArrayList<ScoDtl>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				ScoDtl scoDtl = new ScoDtl();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ScoId") || _fields[i].equals("ScoDtl.ScoId"))
						scoDtl.setScoId(rs.getInt("ScoId"));
					else if(_fields[i].equals("ComId") || _fields[i].equals("ScoDtl.ComId"))
						scoDtl.setComId(rs.getInt("ComId"));
					else if(_fields[i].equals("DetpId") || _fields[i].equals("ScoDtl.DetpId"))
						scoDtl.setDetpId(rs.getInt("DetpId"));
					else if(_fields[i].equals("UsrId") || _fields[i].equals("ScoDtl.UsrId"))
						scoDtl.setUsrId(rs.getInt("UsrId"));

				}
				list.add(scoDtl);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ScoDtlHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into [ZrSy].[dbo].[ScoDtl]("+fields.replaceAll("ScoDtl\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(ScoDtl scoDtl,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ScoId") || _fields[i].equals("ScoDtl.ScoId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, scoDtl.getScoId());
					}
					else if(_fields[i].equals("ComId") || _fields[i].equals("ScoDtl.ComId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, scoDtl.getComId());
					}
					else if(_fields[i].equals("DetpId") || _fields[i].equals("ScoDtl.DetpId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, scoDtl.getDetpId());
					}
					else if(_fields[i].equals("UsrId") || _fields[i].equals("ScoDtl.UsrId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, scoDtl.getUsrId());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("ScoDtlHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update ScoDtl set ";
		String[] _fields = fields.replaceAll("ScoDtl\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(ScoDtl scoDtl,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("ScoDtlHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(ScoDtl scoDtl) {
		String _fields = "";
		if(null != scoDtl.getScoId())
			_fields += "ScoDtl.ScoId,";
		if(null != scoDtl.getComId())
			_fields += "ScoDtl.ComId,";
		if(null != scoDtl.getDetpId())
			_fields += "ScoDtl.DetpId,";
		if(null != scoDtl.getUsrId())
			_fields += "ScoDtl.UsrId,";

		return _fields.substring(0, _fields.length()-1);
	}
}