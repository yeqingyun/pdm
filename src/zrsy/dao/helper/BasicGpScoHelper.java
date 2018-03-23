package zrsy.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrsy.vo.GpSco;

public class BasicGpScoHelper implements SqlHelper {
	public String getSqlString() {
		return " from GpSco where 1=1";
	}

	public String getOrderBy() {
		return " order by GpSco.GpId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((GpSco)object);
	}

	public String getSql4Amount(GpSco gpSco) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(gpSco);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((GpSco)object);
	}

	public String getSql4Delete(GpSco gpSco) {
		return "delete from GpSco where 1=1"+getSqlCondition(gpSco);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((GpSco)object,fields);
	}

	public String getSql4All(GpSco gpSco, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(gpSco)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((GpSco)object,pageVO,fields);
	}

	public String getSql4Pages(GpSco gpSco,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" GpSco.GpId "+ getSqlString()+getSqlCondition(gpSco)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(gpSco)+" and GpSco.GpId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((GpSco)object);
	}

	public String getSqlCondition(GpSco gpSco) {
		String sql = "";
		if(null != gpSco.getGpId())
			sql += " and GpSco.GpId = '"+gpSco.getGpId()+"'";
		if(null != gpSco.getScoId())
			sql += " and GpSco.ScoId = '"+gpSco.getScoId()+"'";

		return sql;
	}

	public List<GpSco> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<GpSco> list = new ArrayList<GpSco>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				GpSco gpSco = new GpSco();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("GpId") || _fields[i].equals("GpSco.GpId"))
						gpSco.setGpId(rs.getInt("GpId"));
					else if(_fields[i].equals("ScoId") || _fields[i].equals("GpSco.ScoId"))
						gpSco.setScoId(rs.getInt("ScoId"));

				}
				list.add(gpSco);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpScoHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into GpSco("+fields.replaceAll("GpSco\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(GpSco gpSco,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("GpId") || _fields[i].equals("GpSco.GpId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, gpSco.getGpId());
					}
					else if(_fields[i].equals("ScoId") || _fields[i].equals("GpSco.ScoId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, gpSco.getScoId());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("GpScoHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update GpSco set ";
		String[] _fields = fields.replaceAll("GpSco\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(GpSco gpSco,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("GpScoHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(GpSco gpSco) {
		String _fields = "";
		if(null != gpSco.getGpId())
			_fields += "GpSco.GpId,";
		if(null != gpSco.getScoId())
			_fields += "GpSco.ScoId,";

		return _fields.substring(0, _fields.length()-1);
	}
}