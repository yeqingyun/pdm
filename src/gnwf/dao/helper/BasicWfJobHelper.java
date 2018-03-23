package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfJob;

public class BasicWfJobHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfJob where 1=1";
	}

	public String getOrderBy() {
		return " order by WfJob.JobId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfJob)object);
	}

	public String getSql4Amount(WfJob wfJob) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfJob);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfJob)object);
	}

	public String getSql4Delete(WfJob wfJob) {
		return "delete from WfJob where 1=1"+getSqlCondition(wfJob);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfJob)object,fields);
	}

	public String getSql4All(WfJob wfJob, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfJob)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfJob)object,pageVO,fields);
	}

	public String getSql4Pages(WfJob wfJob,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfJob.JobId "+ getSqlString()+getSqlCondition(wfJob)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfJob)+" and WfJob.JobId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfJob)object);
	}

	public String getSqlCondition(WfJob wfJob) {
		String sql = "";
		if(null != wfJob.getJobId())
			sql += " and WfJob.JobId = '"+wfJob.getJobId()+"'";
		if(null != wfJob.getFlowId())
			sql += " and WfJob.FlowId = '"+wfJob.getFlowId()+"'";
		if(null != wfJob.getIsUpdOrLoad())
			sql += " and WfJob.IsUpdOrLoad = '"+wfJob.getIsUpdOrLoad()+"'";
		if(null != wfJob.getAnnexUpdOrLoad() && !wfJob.getAnnexUpdOrLoad().trim().equals(""))
			sql += " and WfJob.AnnexUpdOrLoad = '"+wfJob.getAnnexUpdOrLoad().trim()+"'";

		return sql;
	}

	public List<WfJob> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfJob> list = new ArrayList<WfJob>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfJob wfJob = new WfJob();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("JobId") || _fields[i].equals("WfJob.JobId"))
						wfJob.setJobId(rs.getInt("JobId"));
					else if(_fields[i].equals("FlowId") || _fields[i].equals("WfJob.FlowId"))
						wfJob.setFlowId(rs.getInt("FlowId"));
					else if(_fields[i].equals("IsUpdOrLoad") || _fields[i].equals("WfJob.IsUpdOrLoad"))
						wfJob.setIsUpdOrLoad(rs.getInt("IsUpdOrLoad"));
					else if(_fields[i].equals("AnnexUpdOrLoad") || _fields[i].equals("WfJob.AnnexUpdOrLoad"))
						wfJob.setAnnexUpdOrLoad(rs.getString("AnnexUpdOrLoad"));

				}
				list.add(wfJob);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfJobHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfJob("+fields.replaceAll("WfJob\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfJob wfJob,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("JobId") || _fields[i].equals("WfJob.JobId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfJob.getJobId());
					}
					else if(_fields[i].equals("FlowId") || _fields[i].equals("WfJob.FlowId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfJob.getFlowId());
					}
					else if(_fields[i].equals("IsUpdOrLoad") || _fields[i].equals("WfJob.IsUpdOrLoad")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfJob.getIsUpdOrLoad());
					}
					else if(_fields[i].equals("AnnexUpdOrLoad") || _fields[i].equals("WfJob.AnnexUpdOrLoad")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfJob.getAnnexUpdOrLoad());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfJobHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfJob set ";
		String[] _fields = fields.replaceAll("WfJob\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("IsUpdOrLoad") || _fields[i].equals("WfJob.IsUpdOrLoad"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("AnnexUpdOrLoad") || _fields[i].equals("WfJob.AnnexUpdOrLoad"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfJob wfJob,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("IsUpdOrLoad") || _fields[i].equals("WfJob.IsUpdOrLoad")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfJob.getIsUpdOrLoad());
					}
					else if(_fields[i].equals("AnnexUpdOrLoad") || _fields[i].equals("WfJob.AnnexUpdOrLoad")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfJob.getAnnexUpdOrLoad());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfJobHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfJob wfJob) {
		String _fields = "";
		if(null != wfJob.getJobId())
			_fields += "WfJob.JobId,";
		if(null != wfJob.getFlowId())
			_fields += "WfJob.FlowId,";
		if(null != wfJob.getIsUpdOrLoad())
			_fields += "WfJob.IsUpdOrLoad,";
		if(null != wfJob.getAnnexUpdOrLoad())
			_fields += "WfJob.AnnexUpdOrLoad,";

		return _fields.substring(0, _fields.length()-1);
	}
}