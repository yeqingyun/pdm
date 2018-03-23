package zrsy.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrsy.vo.BillTyp;

public class BasicBillTypHelper implements SqlHelper {
	public String getSqlString() {
		return " from BillTyp where 1=1";
	}

	public String getOrderBy() {
		return " order by BillTyp.Id";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((BillTyp)object);
	}

	public String getSql4Amount(BillTyp billTyp) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(billTyp);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((BillTyp)object);
	}

	public String getSql4Delete(BillTyp billTyp) {
		return "delete from BillTyp where 1=1"+getSqlCondition(billTyp);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((BillTyp)object,fields);
	}

	public String getSql4All(BillTyp billTyp, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(billTyp)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((BillTyp)object,pageVO,fields);
	}

	public String getSql4Pages(BillTyp billTyp,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" BillTyp.Id "+ getSqlString()+getSqlCondition(billTyp)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(billTyp)+" and BillTyp.Id not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((BillTyp)object);
	}

	public String getSqlCondition(BillTyp billTyp) {
		String sql = "";
		if(null != billTyp.getId())
			sql += " and BillTyp.Id = '"+billTyp.getId()+"'";
		if(null != billTyp.getSubsId())
			sql += " and BillTyp.SubsId = '"+billTyp.getSubsId()+"'";
		if(null != billTyp.getTypNm() && !billTyp.getTypNm().trim().equals(""))
			sql += " and BillTyp.TypNm = '"+billTyp.getTypNm().trim()+"'";
		if(null != billTyp.getCode() && !billTyp.getCode().trim().equals(""))
			sql += " and BillTyp.Code = '"+billTyp.getCode().trim()+"'";

		return sql;
	}

	public List<BillTyp> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<BillTyp> list = new ArrayList<BillTyp>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				BillTyp billTyp = new BillTyp();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Id") || _fields[i].equals("BillTyp.Id"))
						billTyp.setId(rs.getInt("Id"));
					else if(_fields[i].equals("SubsId") || _fields[i].equals("BillTyp.SubsId"))
						billTyp.setSubsId(rs.getInt("SubsId"));
					else if(_fields[i].equals("TypNm") || _fields[i].equals("BillTyp.TypNm"))
						billTyp.setTypNm(rs.getString("TypNm"));
					else if(_fields[i].equals("Code") || _fields[i].equals("BillTyp.Code"))
						billTyp.setCode(rs.getString("Code"));

				}
				list.add(billTyp);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("BillTypHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into BillTyp("+fields.replaceAll("BillTyp\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(BillTyp billTyp,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Id") || _fields[i].equals("BillTyp.Id")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, billTyp.getId());
					}
					else if(_fields[i].equals("SubsId") || _fields[i].equals("BillTyp.SubsId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, billTyp.getSubsId());
					}
					else if(_fields[i].equals("TypNm") || _fields[i].equals("BillTyp.TypNm")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, billTyp.getTypNm());
					}
					else if(_fields[i].equals("Code") || _fields[i].equals("BillTyp.Code")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, billTyp.getCode());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("BillTypHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update BillTyp set ";
		String[] _fields = fields.replaceAll("BillTyp\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SubsId") || _fields[i].equals("BillTyp.SubsId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("TypNm") || _fields[i].equals("BillTyp.TypNm"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Code") || _fields[i].equals("BillTyp.Code"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(BillTyp billTyp,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SubsId") || _fields[i].equals("BillTyp.SubsId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, billTyp.getSubsId());
					}
					else if(_fields[i].equals("TypNm") || _fields[i].equals("BillTyp.TypNm")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, billTyp.getTypNm());
					}
					else if(_fields[i].equals("Code") || _fields[i].equals("BillTyp.Code")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, billTyp.getCode());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("BillTypHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(BillTyp billTyp) {
		String _fields = "";
		if(null != billTyp.getId())
			_fields += "BillTyp.Id,";
		if(null != billTyp.getSubsId())
			_fields += "BillTyp.SubsId,";
		if(null != billTyp.getTypNm())
			_fields += "BillTyp.TypNm,";
		if(null != billTyp.getCode())
			_fields += "BillTyp.Code,";

		return _fields.substring(0, _fields.length()-1);
	}
}