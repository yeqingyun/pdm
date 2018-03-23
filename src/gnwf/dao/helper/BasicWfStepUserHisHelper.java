package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfStepUserHis;

public class BasicWfStepUserHisHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfStepUserHis where 1=1";
	}

	public String getOrderBy() {
		return " order by WfStepUserHis.StepID";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfStepUserHis)object);
	}

	public String getSql4Amount(WfStepUserHis wfStepUserHis) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfStepUserHis);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfStepUserHis)object);
	}

	public String getSql4Delete(WfStepUserHis wfStepUserHis) {
		return "delete from WfStepUserHis where 1=1"+getSqlCondition(wfStepUserHis);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfStepUserHis)object,fields);
	}

	public String getSql4All(WfStepUserHis wfStepUserHis, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfStepUserHis)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfStepUserHis)object,pageVO,fields);
	}

	public String getSql4Pages(WfStepUserHis wfStepUserHis,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfStepUserHis.StepID "+ getSqlString()+getSqlCondition(wfStepUserHis)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfStepUserHis)+" and WfStepUserHis.StepID not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfStepUserHis)object);
	}

	public String getSqlCondition(WfStepUserHis wfStepUserHis) {
		String sql = "";
		if(null != wfStepUserHis.getStepID())
			sql += " and WfStepUserHis.StepID = '"+wfStepUserHis.getStepID()+"'";
		if(null != wfStepUserHis.getOwner())
			sql += " and WfStepUserHis.Owner = '"+wfStepUserHis.getOwner()+"'";
		if(null != wfStepUserHis.getTaskType())
			sql += " and WfStepUserHis.TaskType = '"+wfStepUserHis.getTaskType()+"'";
		if(null != wfStepUserHis.getUserId())
			sql += " and WfStepUserHis.UserId = '"+wfStepUserHis.getUserId()+"'";

		return sql;
	}

	public List<WfStepUserHis> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfStepUserHis> list = new ArrayList<WfStepUserHis>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfStepUserHis wfStepUserHis = new WfStepUserHis();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("StepID") || _fields[i].equals("WfStepUserHis.StepID"))
						wfStepUserHis.setStepID(rs.getInt("StepID"));
					else if(_fields[i].equals("Owner") || _fields[i].equals("WfStepUserHis.Owner"))
						wfStepUserHis.setOwner(rs.getInt("Owner"));
					else if(_fields[i].equals("TaskType") || _fields[i].equals("WfStepUserHis.TaskType"))
						wfStepUserHis.setTaskType(rs.getInt("TaskType"));
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfStepUserHis.UserId"))
						wfStepUserHis.setUserId(rs.getInt("UserId"));

				}
				list.add(wfStepUserHis);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfStepUserHisHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfStepUserHis("+fields.replaceAll("WfStepUserHis\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfStepUserHis wfStepUserHis,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("StepID") || _fields[i].equals("WfStepUserHis.StepID")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStepUserHis.getStepID());
					}
					else if(_fields[i].equals("Owner") || _fields[i].equals("WfStepUserHis.Owner")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStepUserHis.getOwner());
					}
					else if(_fields[i].equals("TaskType") || _fields[i].equals("WfStepUserHis.TaskType")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStepUserHis.getTaskType());
					}
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfStepUserHis.UserId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStepUserHis.getUserId());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfStepUserHisHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfStepUserHis set ";
		String[] _fields = fields.replaceAll("WfStepUserHis\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfStepUserHis wfStepUserHis,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfStepUserHisHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfStepUserHis wfStepUserHis) {
		String _fields = "";
		if(null != wfStepUserHis.getStepID())
			_fields += "WfStepUserHis.StepID,";
		if(null != wfStepUserHis.getOwner())
			_fields += "WfStepUserHis.Owner,";
		if(null != wfStepUserHis.getTaskType())
			_fields += "WfStepUserHis.TaskType,";
		if(null != wfStepUserHis.getUserId())
			_fields += "WfStepUserHis.UserId,";

		return _fields.substring(0, _fields.length()-1);
	}
}