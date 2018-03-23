package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;
import gnwf.vo.WfRdRisk;

public class BasicWfRdRiskHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfRdRisk where 1=1";
	}

	public String getOrderBy() {
		return " order by WfRdRisk.WfNo";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfRdRisk)object);
	}

	public String getSql4Amount(WfRdRisk wfRdRisk) {
		System.out.println("select count(*) as amount "+getSqlString()+getSqlCondition(wfRdRisk));
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfRdRisk);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfRdRisk)object);
	}

	public String getSql4Delete(WfRdRisk wfRdRisk) {
		return "delete from WfRdRisk where 1=1"+getSqlCondition(wfRdRisk);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfRdRisk)object,fields);
	}

	public String getSql4All(WfRdRisk wfRdRisk, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfRdRisk)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfRdRisk)object,pageVO,fields);
	}

	public String getSql4Pages(WfRdRisk wfRdRisk,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfRdRisk.WfNo "+ getSqlString()+getSqlCondition(wfRdRisk)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfRdRisk)+" and WfRdRisk.WfNo not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfRdRisk)object);
	}

	public String getSqlCondition(WfRdRisk wfRdRisk) {
		String sql = "";
		if(null != wfRdRisk.getWfNo())
			sql += " and WfRdRisk = '" + wfRdRisk.getWfNo() + "'";
		if(null != wfRdRisk.getRiskId())
			sql += " and WfRdRisk.RiskId = '" + wfRdRisk.getRiskId() + "'";
		return sql;
	}

	public List<WfRdRisk> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfRdRisk> list = new ArrayList<WfRdRisk>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfRdRisk wfRdRisk = new WfRdRisk();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("RiskId") || _fields[i].equals("WfRdRisk.RiskId"))
						wfRdRisk.setRiskId(rs.getString("RiskId"));
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfRdRisk.WfNo"))
						wfRdRisk.setWfNo(rs.getString("WfNo"));
				}
				list.add(wfRdRisk);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdRiskHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfRdRisk("+fields.replaceAll("WfRdRisk\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfRdRisk wfRdRisk,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
				if(_fields[i].equals("RiskId") || _fields[i].equals("WfRdRisk.RiskId")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRdRisk.getRiskId());
				}
				else if(_fields[i].equals("WfNo") || _fields[i].equals("WfRdRisk.WfNo")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRdRisk.getWfNo());
				}
			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfRdRiskHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfRdRisk set ";
		String[] _fields = fields.replaceAll("WfRdRisk\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
			if(_fields[i].equals("WfNo") || _fields[i].equals("WfRdRisk.WfNo")) {
				sql += _fields[i] + " = ?,";
			}
			else if(_fields[i].equals("RiskId") || _fields[i].equals("WfRdRisk.RiskId")) {
				sql += _fields[i] + " = ?,";
			}
		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfRdRisk wfRdRisk,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
				if(_fields[i].equals("WfNo") || _fields[i].equals("WfRdRisk.WfNo")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRdRisk.getWfNo());
				}
				else if(_fields[i].equals("RiskId") || _fields[i].equals("WfRdRisk.RiskId")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfRdRisk.getRiskId());
				}
			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfRdRiskHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfRdRisk wfRdRisk) {
		String _fields = "";
		if(null != wfRdRisk.getWfNo())
			_fields += "WfRdRisk.WfNo,";
		if(null != wfRdRisk.getRiskId())
			_fields += "WfRdRisk.RiskId,";
		return _fields.substring(0, _fields.length()-1);
	}
}