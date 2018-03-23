package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfJobUser;

public class BasicWfJobUserHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfJobUser where 1=1";
	}

	public String getOrderBy() {
		return " order by WfJobUser.UserId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfJobUser)object);
	}

	public String getSql4Amount(WfJobUser wfJobUser) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfJobUser);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfJobUser)object);
	}

	public String getSql4Delete(WfJobUser wfJobUser) {
		return "delete from WfJobUser where 1=1"+getSqlCondition(wfJobUser);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfJobUser)object,fields);
	}

	public String getSql4All(WfJobUser wfJobUser, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfJobUser)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfJobUser)object,pageVO,fields);
	}

	public String getSql4Pages(WfJobUser wfJobUser,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfJobUser.UserId "+ getSqlString()+getSqlCondition(wfJobUser)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfJobUser)+" and WfJobUser.UserId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfJobUser)object);
	}

	public String getSqlCondition(WfJobUser wfJobUser) {
		String sql = "";
		if(null != wfJobUser.getUserId())
			sql += " and WfJobUser.UserId = '"+wfJobUser.getUserId()+"'";
		if(null != wfJobUser.getJobId())
			sql += " and WfJobUser.JobId = '"+wfJobUser.getJobId()+"'";

		return sql;
	}

	public List<WfJobUser> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfJobUser> list = new ArrayList<WfJobUser>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfJobUser wfJobUser = new WfJobUser();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("UserId") || _fields[i].equals("WfJobUser.UserId"))
						wfJobUser.setUserId(rs.getInt("UserId"));
					else if(_fields[i].equals("JobId") || _fields[i].equals("WfJobUser.JobId"))
						wfJobUser.setJobId(rs.getInt("JobId"));

				}
				list.add(wfJobUser);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfJobUserHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfJobUser("+fields.replaceAll("WfJobUser\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfJobUser wfJobUser,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("UserId") || _fields[i].equals("WfJobUser.UserId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfJobUser.getUserId());
					}
					else if(_fields[i].equals("JobId") || _fields[i].equals("WfJobUser.JobId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfJobUser.getJobId());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfJobUserHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfJobUser set ";
		String[] _fields = fields.replaceAll("WfJobUser\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfJobUser wfJobUser,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfJobUserHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfJobUser wfJobUser) {
		String _fields = "";
		if(null != wfJobUser.getUserId())
			_fields += "WfJobUser.UserId,";
		if(null != wfJobUser.getJobId())
			_fields += "WfJobUser.JobId,";

		return _fields.substring(0, _fields.length()-1);
	}
}