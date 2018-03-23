package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WFMatlCategory;

public class BasicWFMatlCategoryHelper implements SqlHelper {
	public String getSqlString() {
		return " from WFMatlCategory where 1=1";
	}

	public String getOrderBy() {
		return " order by WFMatlCategory.CategoryId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WFMatlCategory)object);
	}

	public String getSql4Amount(WFMatlCategory wFMatlCategory) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wFMatlCategory);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WFMatlCategory)object);
	}

	public String getSql4Delete(WFMatlCategory wFMatlCategory) {
		return "delete from WFMatlCategory where 1=1"+getSqlCondition(wFMatlCategory);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WFMatlCategory)object,fields);
	}

	public String getSql4All(WFMatlCategory wFMatlCategory, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wFMatlCategory)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WFMatlCategory)object,pageVO,fields);
	}

	public String getSql4Pages(WFMatlCategory wFMatlCategory,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WFMatlCategory.CategoryId "+ getSqlString()+getSqlCondition(wFMatlCategory)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wFMatlCategory)+" and WFMatlCategory.CategoryId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WFMatlCategory)object);
	}

	public String getSqlCondition(WFMatlCategory wFMatlCategory) {
		String sql = "";
		if(null != wFMatlCategory.getCategoryId())
			sql += " and WFMatlCategory.CategoryId = '"+wFMatlCategory.getCategoryId()+"'";
		if(null != wFMatlCategory.getStatus())
			sql += " and WFMatlCategory.Status = '"+wFMatlCategory.getStatus()+"'";
		if(null != wFMatlCategory.getDesc2() && !wFMatlCategory.getDesc2().trim().equals(""))
			sql += " and WFMatlCategory.Desc2 = '"+wFMatlCategory.getDesc2().trim()+"'";
		if(null != wFMatlCategory.getCategoryNo() && !wFMatlCategory.getCategoryNo().trim().equals(""))
			sql += " and WFMatlCategory.CategoryNo = '"+wFMatlCategory.getCategoryNo().trim()+"'";
		if(null != wFMatlCategory.getDesc3() && !wFMatlCategory.getDesc3().trim().equals(""))
			sql += " and WFMatlCategory.Desc3 = '"+wFMatlCategory.getDesc3().trim()+"'";
		if(null != wFMatlCategory.getCategoryName() && !wFMatlCategory.getCategoryName().trim().equals(""))
			sql += " and WFMatlCategory.CategoryName = '"+wFMatlCategory.getCategoryName().trim()+"'";
		if(null != wFMatlCategory.getDesc1() && !wFMatlCategory.getDesc1().trim().equals(""))
			sql += " and WFMatlCategory.Desc1 = '"+wFMatlCategory.getDesc1().trim()+"'";

		return sql;
	}

	public List<WFMatlCategory> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WFMatlCategory> list = new ArrayList<WFMatlCategory>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WFMatlCategory wFMatlCategory = new WFMatlCategory();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("CategoryId") || _fields[i].equals("WFMatlCategory.CategoryId"))
						wFMatlCategory.setCategoryId(rs.getInt("CategoryId"));
					else if(_fields[i].equals("Status") || _fields[i].equals("WFMatlCategory.Status"))
						wFMatlCategory.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("Desc2") || _fields[i].equals("WFMatlCategory.Desc2"))
						wFMatlCategory.setDesc2(rs.getString("Desc2"));
					else if(_fields[i].equals("CategoryNo") || _fields[i].equals("WFMatlCategory.CategoryNo"))
						wFMatlCategory.setCategoryNo(rs.getString("CategoryNo"));
					else if(_fields[i].equals("Desc3") || _fields[i].equals("WFMatlCategory.Desc3"))
						wFMatlCategory.setDesc3(rs.getString("Desc3"));
					else if(_fields[i].equals("CategoryName") || _fields[i].equals("WFMatlCategory.CategoryName"))
						wFMatlCategory.setCategoryName(rs.getString("CategoryName"));
					else if(_fields[i].equals("Desc1") || _fields[i].equals("WFMatlCategory.Desc1"))
						wFMatlCategory.setDesc1(rs.getString("Desc1"));

				}
				list.add(wFMatlCategory);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WFMatlCategoryHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WFMatlCategory("+fields.replaceAll("WFMatlCategory\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WFMatlCategory wFMatlCategory,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("CategoryId") || _fields[i].equals("WFMatlCategory.CategoryId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wFMatlCategory.getCategoryId());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("WFMatlCategory.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wFMatlCategory.getStatus());
					}
					else if(_fields[i].equals("Desc2") || _fields[i].equals("WFMatlCategory.Desc2")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wFMatlCategory.getDesc2());
					}
					else if(_fields[i].equals("CategoryNo") || _fields[i].equals("WFMatlCategory.CategoryNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wFMatlCategory.getCategoryNo());
					}
					else if(_fields[i].equals("Desc3") || _fields[i].equals("WFMatlCategory.Desc3")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wFMatlCategory.getDesc3());
					}
					else if(_fields[i].equals("CategoryName") || _fields[i].equals("WFMatlCategory.CategoryName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wFMatlCategory.getCategoryName());
					}
					else if(_fields[i].equals("Desc1") || _fields[i].equals("WFMatlCategory.Desc1")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wFMatlCategory.getDesc1());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WFMatlCategoryHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WFMatlCategory set ";
		String[] _fields = fields.replaceAll("WFMatlCategory\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Status") || _fields[i].equals("WFMatlCategory.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Desc2") || _fields[i].equals("WFMatlCategory.Desc2"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CategoryNo") || _fields[i].equals("WFMatlCategory.CategoryNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Desc3") || _fields[i].equals("WFMatlCategory.Desc3"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CategoryName") || _fields[i].equals("WFMatlCategory.CategoryName"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Desc1") || _fields[i].equals("WFMatlCategory.Desc1"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WFMatlCategory wFMatlCategory,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Status") || _fields[i].equals("WFMatlCategory.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wFMatlCategory.getStatus());
					}
					else if(_fields[i].equals("Desc2") || _fields[i].equals("WFMatlCategory.Desc2")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wFMatlCategory.getDesc2());
					}
					else if(_fields[i].equals("CategoryNo") || _fields[i].equals("WFMatlCategory.CategoryNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wFMatlCategory.getCategoryNo());
					}
					else if(_fields[i].equals("Desc3") || _fields[i].equals("WFMatlCategory.Desc3")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wFMatlCategory.getDesc3());
					}
					else if(_fields[i].equals("CategoryName") || _fields[i].equals("WFMatlCategory.CategoryName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wFMatlCategory.getCategoryName());
					}
					else if(_fields[i].equals("Desc1") || _fields[i].equals("WFMatlCategory.Desc1")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wFMatlCategory.getDesc1());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WFMatlCategoryHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WFMatlCategory wFMatlCategory) {
		String _fields = "";
		if(null != wFMatlCategory.getCategoryId())
			_fields += "WFMatlCategory.CategoryId,";
		if(null != wFMatlCategory.getStatus())
			_fields += "WFMatlCategory.Status,";
		if(null != wFMatlCategory.getDesc2())
			_fields += "WFMatlCategory.Desc2,";
		if(null != wFMatlCategory.getCategoryNo())
			_fields += "WFMatlCategory.CategoryNo,";
		if(null != wFMatlCategory.getDesc3())
			_fields += "WFMatlCategory.Desc3,";
		if(null != wFMatlCategory.getCategoryName())
			_fields += "WFMatlCategory.CategoryName,";
		if(null != wFMatlCategory.getDesc1())
			_fields += "WFMatlCategory.Desc1,";

		return _fields.substring(0, _fields.length()-1);
	}
}