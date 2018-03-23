package zrprjt.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrprjt.vo.DriverDtl;

public class BasicDriverDtlHelper implements SqlHelper {
	public String getSqlString() {
		return " from DriverDtl where 1=1";
	}

	public String getOrderBy() {
		return " order by DriverDtl.DriveId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((DriverDtl)object);
	}

	public String getSql4Amount(DriverDtl driverDtl) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(driverDtl);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((DriverDtl)object);
	}

	public String getSql4Delete(DriverDtl driverDtl) {
		return "delete from DriverDtl where 1=1"+getSqlCondition(driverDtl);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((DriverDtl)object,fields);
	}

	public String getSql4All(DriverDtl driverDtl, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(driverDtl)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((DriverDtl)object,pageVO,fields);
	}

	public String getSql4Pages(DriverDtl driverDtl,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" DriverDtl.DriveId "+ getSqlString()+getSqlCondition(driverDtl)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(driverDtl)+" and DriverDtl.DriveId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((DriverDtl)object);
	}

	public String getSqlCondition(DriverDtl driverDtl) {
		String sql = "";
		if(null != driverDtl.getDriveId())
			sql += " and DriverDtl.DriveId = '"+driverDtl.getDriveId()+"'";
		if(null != driverDtl.getFlowId())
			sql += " and DriverDtl.FlowId = '"+driverDtl.getFlowId()+"'";
		if(null != driverDtl.getStepId())
			sql += " and DriverDtl.StepId = '"+driverDtl.getStepId()+"'";

		return sql;
	}

	public List<DriverDtl> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<DriverDtl> list = new ArrayList<DriverDtl>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				DriverDtl driverDtl = new DriverDtl();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("DriveId") || _fields[i].equals("DriverDtl.DriveId"))
						driverDtl.setDriveId(rs.getInt("DriveId"));
					else if(_fields[i].equals("FlowId") || _fields[i].equals("DriverDtl.FlowId"))
						driverDtl.setFlowId(rs.getInt("FlowId"));
					else if(_fields[i].equals("StepId") || _fields[i].equals("DriverDtl.StepId"))
						driverDtl.setStepId(rs.getInt("StepId"));

				}
				list.add(driverDtl);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DriverDtlHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into DriverDtl("+fields.replaceAll("DriverDtl\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(DriverDtl driverDtl,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("DriveId") || _fields[i].equals("DriverDtl.DriveId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, driverDtl.getDriveId());
					}
					else if(_fields[i].equals("FlowId") || _fields[i].equals("DriverDtl.FlowId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, driverDtl.getFlowId());
					}
					else if(_fields[i].equals("StepId") || _fields[i].equals("DriverDtl.StepId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, driverDtl.getStepId());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("DriverDtlHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update DriverDtl set ";
		String[] _fields = fields.replaceAll("DriverDtl\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(DriverDtl driverDtl,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("DriverDtlHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(DriverDtl driverDtl) {
		String _fields = "";
		if(null != driverDtl.getDriveId())
			_fields += "DriverDtl.DriveId,";
		if(null != driverDtl.getFlowId())
			_fields += "DriverDtl.FlowId,";
		if(null != driverDtl.getStepId())
			_fields += "DriverDtl.StepId,";

		return _fields.substring(0, _fields.length()-1);
	}
}