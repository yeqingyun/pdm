package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfJobUserRelte;

public class BasicWfJobUserRelteHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfJobUserRelte where 1=1";
	}

	public String getOrderBy() {
		return " order by WfJobUserRelte.JobId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfJobUserRelte)object);
	}

	public String getSql4Amount(WfJobUserRelte wfJobUserRelte) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfJobUserRelte);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfJobUserRelte)object);
	}

	public String getSql4Delete(WfJobUserRelte wfJobUserRelte) {
		return "delete from WfJobUserRelte where 1=1"+getSqlCondition(wfJobUserRelte);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfJobUserRelte)object,fields);
	}

	public String getSql4All(WfJobUserRelte wfJobUserRelte, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfJobUserRelte)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfJobUserRelte)object,pageVO,fields);
	}

	public String getSql4Pages(WfJobUserRelte wfJobUserRelte,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfJobUserRelte.JobId "+ getSqlString()+getSqlCondition(wfJobUserRelte)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfJobUserRelte)+" and WfJobUserRelte.JobId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfJobUserRelte)object);
	}

	public String getSqlCondition(WfJobUserRelte wfJobUserRelte) {
		String sql = "";
		if(null != wfJobUserRelte.getJobId())
			sql += " and WfJobUserRelte.JobId = '"+wfJobUserRelte.getJobId()+"'";
		if(null != wfJobUserRelte.getUserId())
			sql += " and WfJobUserRelte.UserId = '"+wfJobUserRelte.getUserId()+"'";

		return sql;
	}

	public List<WfJobUserRelte> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfJobUserRelte> list = new ArrayList<WfJobUserRelte>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfJobUserRelte wfJobUserRelte = new WfJobUserRelte();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("JobId") || _fields[i].equals("WfJobUserRelte.JobId"))
						wfJobUserRelte.setJobId(rs.getInt("JobId"));
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfJobUserRelte.UserId"))
						wfJobUserRelte.setUserId(rs.getInt("UserId"));

				}
				list.add(wfJobUserRelte);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfJobUserRelteHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfJobUserRelte("+fields.replaceAll("WfJobUserRelte\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfJobUserRelte wfJobUserRelte,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("JobId") || _fields[i].equals("WfJobUserRelte.JobId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfJobUserRelte.getJobId());
					}
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfJobUserRelte.UserId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfJobUserRelte.getUserId());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfJobUserRelteHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfJobUserRelte set ";
		String[] _fields = fields.replaceAll("WfJobUserRelte\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfJobUserRelte wfJobUserRelte,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfJobUserRelteHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfJobUserRelte wfJobUserRelte) {
		String _fields = "";
		if(null != wfJobUserRelte.getJobId())
			_fields += "WfJobUserRelte.JobId,";
		if(null != wfJobUserRelte.getUserId())
			_fields += "WfJobUserRelte.UserId,";

		return _fields.substring(0, _fields.length()-1);
	}
}