package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfCate;

public class BasicWfCateHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfCate where 1=1";
	}

	public String getOrderBy() {
		return " order by WfCate.Sort";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfCate)object);
	}

	public String getSql4Amount(WfCate wfCate) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfCate);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfCate)object);
	}

	public String getSql4Delete(WfCate wfCate) {
		return "delete from WfCate where 1=1"+getSqlCondition(wfCate);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfCate)object,fields);
	}

	public String getSql4All(WfCate wfCate, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfCate)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfCate)object,pageVO,fields);
	}

	public String getSql4Pages(WfCate wfCate,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfCate.CateId "+ getSqlString()+getSqlCondition(wfCate)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfCate)+" and WfCate.CateId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfCate)object);
	}

	public String getSqlCondition(WfCate wfCate) {
		String sql = "";
		if(null != wfCate.getCateId())
			sql += " and WfCate.CateId = '"+wfCate.getCateId()+"'";
		if(null != wfCate.getCateParent())
			sql += " and WfCate.CateParent = '"+wfCate.getCateParent()+"'";
		if(null != wfCate.getCateLevel())
			sql += " and WfCate.CateLevel = '"+wfCate.getCateLevel()+"'";
		if(null != wfCate.getStatus())
			sql += " and WfCate.Status = '"+wfCate.getStatus()+"'";
		if(null != wfCate.getCreateBy())
			sql += " and WfCate.CreateBy = '"+wfCate.getCreateBy()+"'";
		if(null != wfCate.getLastUpd())
			sql += " and WfCate.LastUpd = '"+wfCate.getLastUpd()+"'";
		if(null != wfCate.getStartCreateDate()) 
			sql += " and WfCate.CreateDate >= '"+GenericUtil.dateFormat(wfCate.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfCate.getEndCreateDate()) 
			sql += " and WfCate.CreateDate <= '"+GenericUtil.dateFormat(wfCate.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfCate.getStartLastUpdDate()) 
			sql += " and WfCate.LastUpdDate >= '"+GenericUtil.dateFormat(wfCate.getStartLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfCate.getEndLastUpdDate()) 
			sql += " and WfCate.LastUpdDate <= '"+GenericUtil.dateFormat(wfCate.getEndLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfCate.getCateName() && !wfCate.getCateName().trim().equals(""))
			sql += " and WfCate.CateName = '"+wfCate.getCateName().trim()+"'";
		
		if(null != wfCate.getSort())
			sql += " and WfCate.Sort = '"+wfCate.getSort()+"'";

		return sql;
	}

	public List<WfCate> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfCate> list = new ArrayList<WfCate>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfCate wfCate = new WfCate();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("CateId") || _fields[i].equals("WfCate.CateId"))
						wfCate.setCateId(rs.getInt("CateId"));
					else if(_fields[i].equals("CateParent") || _fields[i].equals("WfCate.CateParent"))
						wfCate.setCateParent(rs.getInt("CateParent"));
					else if(_fields[i].equals("CateLevel") || _fields[i].equals("WfCate.CateLevel"))
						wfCate.setCateLevel(rs.getInt("CateLevel"));
					else if(_fields[i].equals("Status") || _fields[i].equals("WfCate.Status"))
						wfCate.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfCate.CreateBy"))
						wfCate.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfCate.LastUpd"))
						wfCate.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfCate.CreateDate"))
						wfCate.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfCate.LastUpdDate"))
						wfCate.setLastUpdDate(rs.getTimestamp("LastUpdDate"));
					else if(_fields[i].equals("CateName") || _fields[i].equals("WfCate.CateName"))
						wfCate.setCateName(rs.getString("CateName"));
					
					if(_fields[i].equals("Sort") || _fields[i].equals("WfCate.Sort"))
						wfCate.setSort(rs.getInt("Sort"));

				}
				list.add(wfCate);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfCateHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfCate("+fields.replaceAll("WfCate\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfCate wfCate,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("CateId") || _fields[i].equals("WfCate.CateId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCate.getCateId());
					}
					else if(_fields[i].equals("CateParent") || _fields[i].equals("WfCate.CateParent")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCate.getCateParent());
					}
					else if(_fields[i].equals("CateLevel") || _fields[i].equals("WfCate.CateLevel")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCate.getCateLevel());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("WfCate.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCate.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfCate.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCate.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfCate.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCate.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfCate.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfCate.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfCate.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfCate.getLastUpdDate().getTime()));
					}
					else if(_fields[i].equals("CateName") || _fields[i].equals("WfCate.CateName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfCate.getCateName());
					}

					
					if(_fields[i].equals("Sort") || _fields[i].equals("WfCate.Sort")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCate.getSort());
					}
			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfCateHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfCate set ";
		String[] _fields = fields.replaceAll("WfCate\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("CateParent") || _fields[i].equals("WfCate.CateParent"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CateLevel") || _fields[i].equals("WfCate.CateLevel"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("WfCate.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("WfCate.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("WfCate.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("WfCate.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfCate.LastUpdDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CateName") || _fields[i].equals("WfCate.CateName"))
						sql += _fields[i] + " = ?,";
					
					
					if(_fields[i].equals("Sort") || _fields[i].equals("WfCate.Sort"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfCate wfCate,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("CateParent") || _fields[i].equals("WfCate.CateParent")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCate.getCateParent());
					}
					else if(_fields[i].equals("CateLevel") || _fields[i].equals("WfCate.CateLevel")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCate.getCateLevel());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("WfCate.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCate.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfCate.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCate.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfCate.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCate.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfCate.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfCate.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfCate.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfCate.getLastUpdDate().getTime()));
					}
					else if(_fields[i].equals("CateName") || _fields[i].equals("WfCate.CateName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfCate.getCateName());
					}

					
					if(_fields[i].equals("Sort") || _fields[i].equals("WfCate.Sort")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCate.getSort());
					}
			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfCateHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfCate wfCate) {
		String _fields = "";
		if(null != wfCate.getCateId())
			_fields += "WfCate.CateId,";
		if(null != wfCate.getCateParent())
			_fields += "WfCate.CateParent,";
		if(null != wfCate.getCateLevel())
			_fields += "WfCate.CateLevel,";
		if(null != wfCate.getStatus())
			_fields += "WfCate.Status,";
		if(null != wfCate.getCreateBy())
			_fields += "WfCate.CreateBy,";
		if(null != wfCate.getLastUpd())
			_fields += "WfCate.LastUpd,";
		if(null != wfCate.getCreateDate())
			_fields += "WfCate.CreateDate,";
		if(null != wfCate.getLastUpdDate())
			_fields += "WfCate.LastUpdDate,";
		if(null != wfCate.getCateName())
			_fields += "WfCate.CateName,";
		
		if(null != wfCate.getSort())
			_fields += "WfCate.Sort,";

		return _fields.substring(0, _fields.length()-1);
	}
}