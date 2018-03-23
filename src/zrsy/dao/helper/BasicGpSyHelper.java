package zrsy.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrsy.vo.GpSy;

public class BasicGpSyHelper implements SqlHelper {
	public String getSqlString() {
		return " from GpSy where 1=1";
	}

	public String getOrderBy() {
		return " order by GpSy.GpId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((GpSy)object);
	}

	public String getSql4Amount(GpSy gpSy) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(gpSy);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((GpSy)object);
	}

	public String getSql4Delete(GpSy gpSy) {
		return "delete from GpSy where 1=1"+getSqlCondition(gpSy);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((GpSy)object,fields);
	}

	public String getSql4All(GpSy gpSy, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(gpSy)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((GpSy)object,pageVO,fields);
	}

	public String getSql4Pages(GpSy gpSy,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" GpSy.GpId "+ getSqlString()+getSqlCondition(gpSy)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(gpSy)+" and GpSy.GpId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((GpSy)object);
	}

	public String getSqlCondition(GpSy gpSy) {
		String sql = "";
		if(null != gpSy.getGpId())
			sql += " and GpSy.GpId = '"+gpSy.getGpId()+"'";
		if(null != gpSy.getSyId())
			sql += " and GpSy.SyId = '"+gpSy.getSyId()+"'";

		return sql;
	}

	public List<GpSy> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<GpSy> list = new ArrayList<GpSy>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				GpSy gpSy = new GpSy();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("GpId") || _fields[i].equals("GpSy.GpId"))
						gpSy.setGpId(rs.getInt("GpId"));
					else if(_fields[i].equals("SyId") || _fields[i].equals("GpSy.SyId"))
						gpSy.setSyId(rs.getInt("SyId"));

				}
				list.add(gpSy);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpSyHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into GpSy("+fields.replaceAll("GpSy\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(GpSy gpSy,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("GpId") || _fields[i].equals("GpSy.GpId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, gpSy.getGpId());
					}
					else if(_fields[i].equals("SyId") || _fields[i].equals("GpSy.SyId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, gpSy.getSyId());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("GpSyHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update GpSy set ";
		String[] _fields = fields.replaceAll("GpSy\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(GpSy gpSy,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("GpSyHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(GpSy gpSy) {
		String _fields = "";
		if(null != gpSy.getGpId())
			_fields += "GpSy.GpId,";
		if(null != gpSy.getSyId())
			_fields += "GpSy.SyId,";

		return _fields.substring(0, _fields.length()-1);
	}
}