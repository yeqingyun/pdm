package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfQuesRelate;

public class BasicWfQuesRelateHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfQuesRelate where 1=1";
	}

	public String getOrderBy() {
		return " order by WfQuesRelate.QuesId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfQuesRelate)object);
	}

	public String getSql4Amount(WfQuesRelate wfQuesRelate) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfQuesRelate);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfQuesRelate)object);
	}

	public String getSql4Delete(WfQuesRelate wfQuesRelate) {
		return "delete from WfQuesRelate where 1=1"+getSqlCondition(wfQuesRelate);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfQuesRelate)object,fields);
	}

	public String getSql4All(WfQuesRelate wfQuesRelate, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfQuesRelate)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfQuesRelate)object,pageVO,fields);
	}

	public String getSql4Pages(WfQuesRelate wfQuesRelate,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfQuesRelate.QuesId "+ getSqlString()+getSqlCondition(wfQuesRelate)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfQuesRelate)+" and WfQuesRelate.QuesId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfQuesRelate)object);
	}

	public String getSqlCondition(WfQuesRelate wfQuesRelate) {
		String sql = "";
		if(null != wfQuesRelate.getQuesId())
			sql += " and WfQuesRelate.QuesId = '"+wfQuesRelate.getQuesId()+"'";
		if(null != wfQuesRelate.getIsRisk())
			sql += " and WfQuesRelate.IsRisk = '"+wfQuesRelate.getIsRisk()+"'";
		if(null != wfQuesRelate.getWfNo() && !wfQuesRelate.getWfNo().trim().equals(""))
			sql += " and WfQuesRelate.WfNo = '"+wfQuesRelate.getWfNo().trim()+"'";

		return sql;
	}

	public List<WfQuesRelate> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfQuesRelate> list = new ArrayList<WfQuesRelate>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfQuesRelate wfQuesRelate = new WfQuesRelate();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("QuesId") || _fields[i].equals("WfQuesRelate.QuesId"))
						wfQuesRelate.setQuesId(rs.getInt("QuesId"));
					else if(_fields[i].equals("IsRisk") || _fields[i].equals("WfQuesRelate.IsRisk"))
						wfQuesRelate.setIsRisk(rs.getInt("IsRisk"));
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfQuesRelate.WfNo"))
						wfQuesRelate.setWfNo(rs.getString("WfNo"));

				}
				list.add(wfQuesRelate);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfQuesRelateHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfQuesRelate("+fields.replaceAll("WfQuesRelate\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfQuesRelate wfQuesRelate,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("QuesId") || _fields[i].equals("WfQuesRelate.QuesId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQuesRelate.getQuesId());
					}
					else if(_fields[i].equals("IsRisk") || _fields[i].equals("WfQuesRelate.IsRisk")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfQuesRelate.getIsRisk());
					}
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfQuesRelate.WfNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfQuesRelate.getWfNo());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfQuesRelateHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfQuesRelate set ";
		String[] _fields = fields.replaceAll("WfQuesRelate\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfQuesRelate wfQuesRelate,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfQuesRelateHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfQuesRelate wfQuesRelate) {
		String _fields = "";
		if(null != wfQuesRelate.getQuesId())
			_fields += "WfQuesRelate.QuesId,";
		if(null != wfQuesRelate.getIsRisk())
			_fields += "WfQuesRelate.IsRisk,";
		if(null != wfQuesRelate.getWfNo())
			_fields += "WfQuesRelate.WfNo,";

		return _fields.substring(0, _fields.length()-1);
	}
}