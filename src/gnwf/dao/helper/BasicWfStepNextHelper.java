package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfStepNext;

public class BasicWfStepNextHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfStepNext where 1=1";
	}

	public String getOrderBy() {
		return " order by WfStepNext.StepId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfStepNext)object);
	}

	public String getSql4Amount(WfStepNext wfStepNext) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfStepNext);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfStepNext)object);
	}

	public String getSql4Delete(WfStepNext wfStepNext) {
		return "delete from WfStepNext where 1=1"+getSqlCondition(wfStepNext);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfStepNext)object,fields);
	}

	public String getSql4All(WfStepNext wfStepNext, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfStepNext)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfStepNext)object,pageVO,fields);
	}

	public String getSql4Pages(WfStepNext wfStepNext,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfStepNext.StepId "+ getSqlString()+getSqlCondition(wfStepNext)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfStepNext)+" and WfStepNext.StepId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfStepNext)object);
	}

	public String getSqlCondition(WfStepNext wfStepNext) {
		String sql = "";
		if(null != wfStepNext.getStepId())
			sql += " and WfStepNext.StepId = '"+wfStepNext.getStepId()+"'";
		if(null != wfStepNext.getNextId())
			sql += " and WfStepNext.NextId = '"+wfStepNext.getNextId()+"'";

		return sql;
	}

	public List<WfStepNext> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfStepNext> list = new ArrayList<WfStepNext>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfStepNext wfStepNext = new WfStepNext();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("StepId") || _fields[i].equals("WfStepNext.StepId"))
						wfStepNext.setStepId(rs.getInt("StepId"));
					else if(_fields[i].equals("NextId") || _fields[i].equals("WfStepNext.NextId"))
						wfStepNext.setNextId(rs.getInt("NextId"));

				}
				list.add(wfStepNext);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfStepNextHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfStepNext("+fields.replaceAll("WfStepNext\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfStepNext wfStepNext,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("StepId") || _fields[i].equals("WfStepNext.StepId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStepNext.getStepId());
					}
					else if(_fields[i].equals("NextId") || _fields[i].equals("WfStepNext.NextId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStepNext.getNextId());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfStepNextHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfStepNext set ";
		String[] _fields = fields.replaceAll("WfStepNext\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfStepNext wfStepNext,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfStepNextHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfStepNext wfStepNext) {
		String _fields = "";
		if(null != wfStepNext.getStepId())
			_fields += "WfStepNext.StepId,";
		if(null != wfStepNext.getNextId())
			_fields += "WfStepNext.NextId,";

		return _fields.substring(0, _fields.length()-1);
	}
}