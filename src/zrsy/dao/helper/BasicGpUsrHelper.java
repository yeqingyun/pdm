package zrsy.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrsy.vo.GpUsr;

public class BasicGpUsrHelper implements SqlHelper {
	public String getSqlString() {
		return " from GpUsr where 1=1";
	}

	public String getOrderBy() {
		return " order by GpUsr.GpId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((GpUsr)object);
	}

	public String getSql4Amount(GpUsr gpUsr) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(gpUsr);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((GpUsr)object);
	}

	public String getSql4Delete(GpUsr gpUsr) {
		return "delete from GpUsr where 1=1"+getSqlCondition(gpUsr);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((GpUsr)object,fields);
	}

	public String getSql4All(GpUsr gpUsr, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(gpUsr)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((GpUsr)object,pageVO,fields);
	}

	public String getSql4Pages(GpUsr gpUsr,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" GpUsr.GpId "+ getSqlString()+getSqlCondition(gpUsr)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(gpUsr)+" and GpUsr.GpId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((GpUsr)object);
	}

	public String getSqlCondition(GpUsr gpUsr) {
		String sql = "";
		if(null != gpUsr.getGpId())
			sql += " and GpUsr.GpId = '"+gpUsr.getGpId()+"'";
		if(null != gpUsr.getUsrId())
			sql += " and GpUsr.UsrId = '"+gpUsr.getUsrId()+"'";

		return sql;
	}

	public List<GpUsr> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<GpUsr> list = new ArrayList<GpUsr>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				GpUsr gpUsr = new GpUsr();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("GpId") || _fields[i].equals("GpUsr.GpId"))
						gpUsr.setGpId(rs.getInt("GpId"));
					if(_fields[i].equals("UsrId") || _fields[i].equals("GpUsr.UsrId"))
						gpUsr.setUsrId(rs.getInt("UsrId"));

				}
				list.add(gpUsr);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpUsrHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into GpUsr("+fields.replaceAll("GpUsr\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(GpUsr gpUsr,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("GpId") || _fields[i].equals("GpUsr.GpId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, gpUsr.getGpId());
					}
					else if(_fields[i].equals("UsrId") || _fields[i].equals("GpUsr.UsrId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, gpUsr.getUsrId());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("GpUsrHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update GpUsr set ";
		String[] _fields = fields.replaceAll("GpUsr\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(GpUsr gpUsr,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("GpUsrHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(GpUsr gpUsr) {
		String _fields = "";
		if(null != gpUsr.getGpId())
			_fields += "GpUsr.GpId,";
		if(null != gpUsr.getUsrId())
			_fields += "GpUsr.UsrId,";

		return _fields.substring(0, _fields.length()-1);
	}
}