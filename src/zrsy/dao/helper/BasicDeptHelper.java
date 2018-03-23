package zrsy.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrsy.vo.Dept;

public class BasicDeptHelper implements SqlHelper {
	public String getSqlString() {
		return " from Dept where 1=1";
	}

	public String getOrderBy() {
		return " order by Dept.DeptId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((Dept)object);
	}

	public String getSql4Amount(Dept dept) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(dept);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((Dept)object);
	}

	public String getSql4Delete(Dept dept) {
		return "delete from Dept where 1=1"+getSqlCondition(dept);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((Dept)object,fields);
	}

	public String getSql4All(Dept dept, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(dept)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((Dept)object,pageVO,fields);
	}

	public String getSql4Pages(Dept dept,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" Dept.DeptId "+ getSqlString()+getSqlCondition(dept)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(dept)+" and Dept.DeptId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((Dept)object);
	}

	public String getSqlCondition(Dept dept) {
		String sql = "";
		if(null != dept.getDeptId())
			sql += " and Dept.DeptId = '"+dept.getDeptId()+"'";
		if(null != dept.getComId())
			sql += " and Dept.ComId = '"+dept.getComId()+"'";
		if(null != dept.getParent())
			sql += " and Dept.Parent = '"+dept.getParent()+"'";
		if(null != dept.getLeve())
			sql += " and Dept.Leve = '"+dept.getLeve()+"'";
		if(null != dept.getStatus())
			sql += " and Dept.Status = '"+dept.getStatus()+"'";
		if(null != dept.getCreateBy())
			sql += " and Dept.CreateBy = '"+dept.getCreateBy()+"'";
		if(null != dept.getLastUpd())
			sql += " and Dept.LastUpd = '"+dept.getLastUpd()+"'";
		if(null != dept.getStartCreateDate()) 
			sql += " and Dept.CreateDate >= '"+GenericUtil.dateFormat(dept.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != dept.getEndCreateDate()) 
			sql += " and Dept.CreateDate <= '"+GenericUtil.dateFormat(dept.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != dept.getStartLastDate()) 
			sql += " and Dept.LastDate >= '"+GenericUtil.dateFormat(dept.getStartLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != dept.getEndLastDate()) 
			sql += " and Dept.LastDate <= '"+GenericUtil.dateFormat(dept.getEndLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != dept.getDeptNo() && !dept.getDeptNo().trim().equals(""))
			sql += " and Dept.DeptNo = '"+dept.getDeptNo().trim()+"'";
		if(null != dept.getDeptNm() && !dept.getDeptNm().trim().equals(""))
			sql += " and Dept.DeptNm = '"+dept.getDeptNm().trim()+"'";
		if(null != dept.getRemark() && !dept.getRemark().trim().equals(""))
			sql += " and Dept.Remark = '"+dept.getRemark().trim()+"'";

		return sql;
	}

	public List<Dept> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<Dept> list = new ArrayList<Dept>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				Dept dept = new Dept();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("DeptId") || _fields[i].equals("Dept.DeptId"))
						dept.setDeptId(rs.getInt("DeptId"));
					if(_fields[i].equals("ComId") || _fields[i].equals("Dept.ComId"))
						dept.setComId(rs.getInt("ComId"));
					if(_fields[i].equals("Parent") || _fields[i].equals("Dept.Parent"))
						dept.setParent(rs.getInt("Parent"));
					if(_fields[i].equals("Leve") || _fields[i].equals("Dept.Leve"))
						dept.setLeve(rs.getInt("Leve"));
					if(_fields[i].equals("Status") || _fields[i].equals("Dept.Status"))
						dept.setStatus(rs.getInt("Status"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("Dept.CreateBy"))
						dept.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("LastUpd") || _fields[i].equals("Dept.LastUpd"))
						dept.setLastUpd(rs.getInt("LastUpd"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("Dept.CreateDate"))
						dept.setCreateDate(rs.getTimestamp("CreateDate"));
					if(_fields[i].equals("LastDate") || _fields[i].equals("Dept.LastDate"))
						dept.setLastDate(rs.getTimestamp("LastDate"));
					if(_fields[i].equals("DeptNo") || _fields[i].equals("Dept.DeptNo"))
						dept.setDeptNo(rs.getString("DeptNo"));
					if(_fields[i].equals("DeptNm") || _fields[i].equals("Dept.DeptNm"))
						dept.setDeptNm(rs.getString("DeptNm"));
					if(_fields[i].equals("Remark") || _fields[i].equals("Dept.Remark"))
						dept.setRemark(rs.getString("Remark"));

				}
				list.add(dept);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DeptHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into Dept("+fields.replaceAll("Dept\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(Dept dept,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("DeptId") || _fields[i].equals("Dept.DeptId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, dept.getDeptId());
					}
					else if(_fields[i].equals("ComId") || _fields[i].equals("Dept.ComId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, dept.getComId());
					}
					else if(_fields[i].equals("Parent") || _fields[i].equals("Dept.Parent")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, dept.getParent());
					}
					else if(_fields[i].equals("Leve") || _fields[i].equals("Dept.Leve")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, dept.getLeve());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("Dept.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, dept.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("Dept.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, dept.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("Dept.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, dept.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("Dept.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(dept.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("Dept.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(dept.getLastDate().getTime()));
					}
					else if(_fields[i].equals("DeptNo") || _fields[i].equals("Dept.DeptNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, dept.getDeptNo());
					}
					else if(_fields[i].equals("DeptNm") || _fields[i].equals("Dept.DeptNm")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, dept.getDeptNm());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("Dept.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, dept.getRemark());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("DeptHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update Dept set ";
		String[] _fields = fields.replaceAll("Dept\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ComId") || _fields[i].equals("Dept.ComId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Parent") || _fields[i].equals("Dept.Parent"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Leve") || _fields[i].equals("Dept.Leve"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("Dept.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("Dept.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("Dept.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("Dept.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastDate") || _fields[i].equals("Dept.LastDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("DeptNo") || _fields[i].equals("Dept.DeptNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("DeptNm") || _fields[i].equals("Dept.DeptNm"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Remark") || _fields[i].equals("Dept.Remark"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(Dept dept,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ComId") || _fields[i].equals("Dept.ComId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, dept.getComId());
					}
					else if(_fields[i].equals("Parent") || _fields[i].equals("Dept.Parent")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, dept.getParent());
					}
					else if(_fields[i].equals("Leve") || _fields[i].equals("Dept.Leve")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, dept.getLeve());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("Dept.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, dept.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("Dept.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, dept.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("Dept.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, dept.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("Dept.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(dept.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("Dept.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(dept.getLastDate().getTime()));
					}
					else if(_fields[i].equals("DeptNo") || _fields[i].equals("Dept.DeptNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, dept.getDeptNo());
					}
					else if(_fields[i].equals("DeptNm") || _fields[i].equals("Dept.DeptNm")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, dept.getDeptNm());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("Dept.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, dept.getRemark());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("DeptHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(Dept dept) {
		String _fields = "";
		if(null != dept.getDeptId())
			_fields += "Dept.DeptId,";
		if(null != dept.getComId())
			_fields += "Dept.ComId,";
		if(null != dept.getParent())
			_fields += "Dept.Parent,";
		if(null != dept.getLeve())
			_fields += "Dept.Leve,";
		if(null != dept.getStatus())
			_fields += "Dept.Status,";
		if(null != dept.getCreateBy())
			_fields += "Dept.CreateBy,";
		if(null != dept.getLastUpd())
			_fields += "Dept.LastUpd,";
		if(null != dept.getCreateDate())
			_fields += "Dept.CreateDate,";
		if(null != dept.getLastDate())
			_fields += "Dept.LastDate,";
		if(null != dept.getDeptNo())
			_fields += "Dept.DeptNo,";
		if(null != dept.getDeptNm())
			_fields += "Dept.DeptNm,";
		if(null != dept.getRemark())
			_fields += "Dept.Remark,";

		return _fields.substring(0, _fields.length()-1);
	}
}