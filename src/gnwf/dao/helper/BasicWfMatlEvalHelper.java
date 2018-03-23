package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfMatlEval;

public class BasicWfMatlEvalHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfMatlEval where 1=1";
	}

	public String getOrderBy() {
		return " order by WfMatlEval.MatlId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfMatlEval)object);
	}

	public String getSql4Amount(WfMatlEval wfMatlEval) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfMatlEval);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfMatlEval)object);
	}

	public String getSql4Delete(WfMatlEval wfMatlEval) {
		return "delete from WfMatlEval where 1=1"+getSqlCondition(wfMatlEval);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfMatlEval)object,fields);
	}

	public String getSql4All(WfMatlEval wfMatlEval, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfMatlEval)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfMatlEval)object,pageVO,fields);
	}

	public String getSql4Pages(WfMatlEval wfMatlEval,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfMatlEval.MatlId "+ getSqlString()+getSqlCondition(wfMatlEval)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfMatlEval)+" and WfMatlEval.MatlId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfMatlEval)object);
	}

	public String getSqlCondition(WfMatlEval wfMatlEval) {
		String sql = "";
		if(null != wfMatlEval.getMatlId())
			sql += " and WfMatlEval.MatlId = '"+wfMatlEval.getMatlId()+"'";
		if(null != wfMatlEval.getUserId())
			sql += " and WfMatlEval.UserId = '"+wfMatlEval.getUserId()+"'";
		if(null != wfMatlEval.getIsPass())
			sql += " and WfMatlEval.IsPass = '"+wfMatlEval.getIsPass()+"'";
		if(null != wfMatlEval.getEvaler())
			sql += " and WfMatlEval.Evaler = '"+wfMatlEval.getEvaler()+"'";
		if(null != wfMatlEval.getstatus())
			sql += " and WfMatlEval.status = '"+wfMatlEval.getstatus()+"'";
		if(null != wfMatlEval.getEvalId())
			sql += " and WfMatlEval.EvalId = '"+wfMatlEval.getEvalId()+"'";
		if(null != wfMatlEval.getWfNo() && !wfMatlEval.getWfNo().trim().equals(""))
			sql += " and WfMatlEval.WfNo = '"+wfMatlEval.getWfNo().trim()+"'";
		if(null != wfMatlEval.getStartEvalDate()) 
			sql += " and WfMatlEval.EvalDate >= '"+GenericUtil.dateFormat(wfMatlEval.getStartEvalDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfMatlEval.getEndEvalDate()) 
			sql += " and WfMatlEval.EvalDate <= '"+GenericUtil.dateFormat(wfMatlEval.getEndEvalDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfMatlEval.getEvalText() && !wfMatlEval.getEvalText().trim().equals(""))
			sql += " and WfMatlEval.EvalText = '"+wfMatlEval.getEvalText().trim()+"'";

		return sql;
	}

	public List<WfMatlEval> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfMatlEval> list = new ArrayList<WfMatlEval>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfMatlEval wfMatlEval = new WfMatlEval();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("MatlId") || _fields[i].equals("WfMatlEval.MatlId"))
						wfMatlEval.setMatlId(rs.getInt("MatlId"));
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfMatlEval.UserId"))
						wfMatlEval.setUserId(rs.getInt("UserId"));
					else if(_fields[i].equals("IsPass") || _fields[i].equals("WfMatlEval.IsPass"))
						wfMatlEval.setIsPass(rs.getInt("IsPass"));
					else if(_fields[i].equals("Evaler") || _fields[i].equals("WfMatlEval.Evaler"))
						wfMatlEval.setEvaler(rs.getInt("Evaler"));
					else if(_fields[i].equals("status") || _fields[i].equals("WfMatlEval.status"))
						wfMatlEval.setstatus(rs.getInt("status"));
					else if(_fields[i].equals("EvalId") || _fields[i].equals("WfMatlEval.EvalId"))
						wfMatlEval.setEvalId(rs.getInt("EvalId"));
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfMatlEval.WfNo"))
						wfMatlEval.setWfNo(rs.getString("WfNo"));
					else if(_fields[i].equals("EvalDate") || _fields[i].equals("WfMatlEval.EvalDate"))
						wfMatlEval.setEvalDate(rs.getTimestamp("EvalDate"));
					else if(_fields[i].equals("EvalText") || _fields[i].equals("WfMatlEval.EvalText"))
						wfMatlEval.setEvalText(rs.getString("EvalText"));

				}
				list.add(wfMatlEval);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfMatlEvalHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfMatlEval("+fields.replaceAll("WfMatlEval\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfMatlEval wfMatlEval,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("MatlId") || _fields[i].equals("WfMatlEval.MatlId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatlEval.getMatlId());
					}
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfMatlEval.UserId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatlEval.getUserId());
					}
					else if(_fields[i].equals("IsPass") || _fields[i].equals("WfMatlEval.IsPass")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatlEval.getIsPass());
					}
					else if(_fields[i].equals("Evaler") || _fields[i].equals("WfMatlEval.Evaler")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatlEval.getEvaler());
					}
					else if(_fields[i].equals("status") || _fields[i].equals("WfMatlEval.status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatlEval.getstatus());
					}
					else if(_fields[i].equals("EvalId") || _fields[i].equals("WfMatlEval.EvalId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatlEval.getEvalId());
					}
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfMatlEval.WfNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlEval.getWfNo());
					}
					else if(_fields[i].equals("EvalDate") || _fields[i].equals("WfMatlEval.EvalDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfMatlEval.getEvalDate().getTime()));
					}
					else if(_fields[i].equals("EvalText") || _fields[i].equals("WfMatlEval.EvalText")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlEval.getEvalText());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfMatlEvalHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfMatlEval set ";
		String[] _fields = fields.replaceAll("WfMatlEval\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("UserId") || _fields[i].equals("WfMatlEval.UserId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IsPass") || _fields[i].equals("WfMatlEval.IsPass"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Evaler") || _fields[i].equals("WfMatlEval.Evaler"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("status") || _fields[i].equals("WfMatlEval.status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("WfNo") || _fields[i].equals("WfMatlEval.WfNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("EvalDate") || _fields[i].equals("WfMatlEval.EvalDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("EvalText") || _fields[i].equals("WfMatlEval.EvalText"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfMatlEval wfMatlEval,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("UserId") || _fields[i].equals("WfMatlEval.UserId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatlEval.getUserId());
					}
					else if(_fields[i].equals("IsPass") || _fields[i].equals("WfMatlEval.IsPass")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatlEval.getIsPass());
					}
					else if(_fields[i].equals("Evaler") || _fields[i].equals("WfMatlEval.Evaler")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatlEval.getEvaler());
					}
					else if(_fields[i].equals("status") || _fields[i].equals("WfMatlEval.status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatlEval.getstatus());
					}
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfMatlEval.WfNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlEval.getWfNo());
					}
					else if(_fields[i].equals("EvalDate") || _fields[i].equals("WfMatlEval.EvalDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfMatlEval.getEvalDate().getTime()));
					}
					else if(_fields[i].equals("EvalText") || _fields[i].equals("WfMatlEval.EvalText")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlEval.getEvalText());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfMatlEvalHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfMatlEval wfMatlEval) {
		String _fields = "";
		if(null != wfMatlEval.getMatlId())
			_fields += "WfMatlEval.MatlId,";
		if(null != wfMatlEval.getUserId())
			_fields += "WfMatlEval.UserId,";
		if(null != wfMatlEval.getIsPass())
			_fields += "WfMatlEval.IsPass,";
		if(null != wfMatlEval.getEvaler())
			_fields += "WfMatlEval.Evaler,";
		if(null != wfMatlEval.getstatus())
			_fields += "WfMatlEval.status,";
		if(null != wfMatlEval.getEvalId())
			_fields += "WfMatlEval.EvalId,";
		if(null != wfMatlEval.getWfNo())
			_fields += "WfMatlEval.WfNo,";
		if(null != wfMatlEval.getEvalDate())
			_fields += "WfMatlEval.EvalDate,";
		if(null != wfMatlEval.getEvalText())
			_fields += "WfMatlEval.EvalText,";

		return _fields.substring(0, _fields.length()-1);
	}
}