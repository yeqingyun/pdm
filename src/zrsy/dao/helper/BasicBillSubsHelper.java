package zrsy.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrsy.vo.BillSubs;

public class BasicBillSubsHelper implements SqlHelper {
	public String getSqlString() {
		return " from BillSubs where 1=1";
	}

	public String getOrderBy() {
		return " order by BillSubs.Id";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((BillSubs)object);
	}

	public String getSql4Amount(BillSubs billSubs) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(billSubs);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((BillSubs)object);
	}

	public String getSql4Delete(BillSubs billSubs) {
		return "delete from BillSubs where 1=1"+getSqlCondition(billSubs);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((BillSubs)object,fields);
	}

	public String getSql4All(BillSubs billSubs, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(billSubs)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((BillSubs)object,pageVO,fields);
	}

	public String getSql4Pages(BillSubs billSubs,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" BillSubs.Id "+ getSqlString()+getSqlCondition(billSubs)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(billSubs)+" and BillSubs.Id not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((BillSubs)object);
	}

	public String getSqlCondition(BillSubs billSubs) {
		String sql = "";
		if(null != billSubs.getId())
			sql += " and BillSubs.Id = '"+billSubs.getId()+"'";
		if(null != billSubs.getSyId())
			sql += " and BillSubs.SyId = '"+billSubs.getSyId()+"'";
		if(null != billSubs.getSubs() && !billSubs.getSubs().trim().equals(""))
			sql += " and BillSubs.Subs = '"+billSubs.getSubs().trim()+"'";
		if(null != billSubs.getCurrSn() && !billSubs.getCurrSn().trim().equals(""))
			sql += " and BillSubs.CurrSn = '"+billSubs.getCurrSn().trim()+"'";
		if(null != billSubs.getState())
			sql += " and BillSubs.State = '"+billSubs.getState()+"'";

		return sql;
	}

	public List<BillSubs> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<BillSubs> list = new ArrayList<BillSubs>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				BillSubs billSubs = new BillSubs();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Id") || _fields[i].equals("BillSubs.Id"))
						billSubs.setId(rs.getInt("Id"));
					else if(_fields[i].equals("SyId") || _fields[i].equals("BillSubs.SyId"))
						billSubs.setSyId(rs.getInt("SyId"));
					else if(_fields[i].equals("Subs") || _fields[i].equals("BillSubs.Subs"))
						billSubs.setSubs(rs.getString("Subs"));
					else if(_fields[i].equals("CurrSn") || _fields[i].equals("BillSubs.CurrSn"))
						billSubs.setCurrSn(rs.getString("CurrSn"));
					else if(_fields[i].equals("State") || _fields[i].equals("BillSubs.State"))
						billSubs.setState(rs.getInt("State"));

				}
				list.add(billSubs);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("BillSubsHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into BillSubs("+fields.replaceAll("BillSubs\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(BillSubs billSubs,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Id") || _fields[i].equals("BillSubs.Id")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, billSubs.getId());
					}
					else if(_fields[i].equals("SyId") || _fields[i].equals("BillSubs.SyId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, billSubs.getSyId());
					}
					else if(_fields[i].equals("Subs") || _fields[i].equals("BillSubs.Subs")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, billSubs.getSubs());
					}
					else if(_fields[i].equals("CurrSn") || _fields[i].equals("BillSubs.CurrSn")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, billSubs.getCurrSn());
					}
					else if(_fields[i].equals("State") || _fields[i].equals("BillSubs.State")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, billSubs.getState());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("BillSubsHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update BillSubs set ";
		String[] _fields = fields.replaceAll("BillSubs\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SyId") || _fields[i].equals("BillSubs.SyId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Subs") || _fields[i].equals("BillSubs.Subs"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CurrSn") || _fields[i].equals("BillSubs.CurrSn"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("State") || _fields[i].equals("BillSubs.State"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(BillSubs billSubs,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SyId") || _fields[i].equals("BillSubs.SyId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, billSubs.getSyId());
					}
					else if(_fields[i].equals("Subs") || _fields[i].equals("BillSubs.Subs")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, billSubs.getSubs());
					}
					else if(_fields[i].equals("CurrSn") || _fields[i].equals("BillSubs.CurrSn")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, billSubs.getCurrSn());
					}
					else if(_fields[i].equals("State") || _fields[i].equals("BillSubs.State")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, billSubs.getState());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("BillSubsHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(BillSubs billSubs) {
		String _fields = "";
		if(null != billSubs.getId())
			_fields += "BillSubs.Id,";
		if(null != billSubs.getSyId())
			_fields += "BillSubs.SyId,";
		if(null != billSubs.getSubs())
			_fields += "BillSubs.Subs,";
		if(null != billSubs.getCurrSn())
			_fields += "BillSubs.CurrSn,";
		if(null != billSubs.getState())
			_fields += "BillSubs.State,";

		return _fields.substring(0, _fields.length()-1);
	}
}