package gnwf.dao.helper;

import gnwf.vo.WfStepUser;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

public class BasicWfStepUserHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfStepUser where 1=1";
	}

	public String getOrderBy() {
		return " order by WfStepUser.StepId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfStepUser)object);
	}

	public String getSql4Amount(WfStepUser wfStepUser) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfStepUser);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfStepUser)object);
	}

	public String getSql4Delete(WfStepUser wfStepUser) {
		return "delete from WfStepUser where 1=1"+getSqlCondition(wfStepUser);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfStepUser)object,fields);
	}

	public String getSql4All(WfStepUser wfStepUser, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfStepUser)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfStepUser)object,pageVO,fields);
	}

	public String getSql4Pages(WfStepUser wfStepUser,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfStepUser.StepId "+ getSqlString()+getSqlCondition(wfStepUser)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfStepUser)+" and WfStepUser.StepId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfStepUser)object);
	}

	public String getSqlCondition(WfStepUser wfStepUser) {
		String sql = "";
		if(null != wfStepUser.getStepId())
			sql += " and WfStepUser.StepId = '"+wfStepUser.getStepId()+"'";
		if(null != wfStepUser.getUserId())
			sql += " and WfStepUser.UserId = '"+wfStepUser.getUserId()+"'";
		if(null != wfStepUser.getUserType())
			sql += " and WfStepUser.UserType = '"+wfStepUser.getUserType()+"'";

		return sql;
	}

	public List<WfStepUser> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfStepUser> list = new ArrayList<WfStepUser>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfStepUser wfStepUser = new WfStepUser();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("StepId") || _fields[i].equals("WfStepUser.StepId"))
						wfStepUser.setStepId(rs.getInt("StepId"));
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfStepUser.UserId"))
						wfStepUser.setUserId(rs.getInt("UserId"));
					else if(_fields[i].equals("UserType") || _fields[i].equals("WfStepUser.UserType"))
						wfStepUser.setUserType(rs.getInt("UserType"));

				}
				list.add(wfStepUser);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfStepUserHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfStepUser("+fields.replaceAll("WfStepUser\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfStepUser wfStepUser,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("StepId") || _fields[i].equals("WfStepUser.StepId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStepUser.getStepId());
					}
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfStepUser.UserId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStepUser.getUserId());
					}
					else if(_fields[i].equals("UserType") || _fields[i].equals("WfStepUser.UserType")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStepUser.getUserType());
					}
					else if(_fields[i].equals("PrjtRoleId") || _fields[i].equals("WfStepUser.PrjtRoleId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStepUser.getPrjtRoleId());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfStepUserHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfStepUser set ";
		String[] _fields = fields.replaceAll("WfStepUser\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfStepUser wfStepUser,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfStepUserHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfStepUser wfStepUser) {
		String _fields = "";
		if(null != wfStepUser.getStepId())
			_fields += "WfStepUser.StepId,";
		if(null != wfStepUser.getUserId())
			_fields += "WfStepUser.UserId,";
		if(null != wfStepUser.getUserType())
			_fields += "WfStepUser.UserType,";
		if(null != wfStepUser.getPrjtRoleId())
			_fields += "WfStepUser.PrjtRoleId,";

		return _fields.substring(0, _fields.length()-1);
	}
}