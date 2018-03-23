package zrprjt.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrprjt.vo.Usr;

public class BasicUsrHelper implements SqlHelper {
	public String getSqlString() {
		return " from Usr where 1=1";
	}

	public String getOrderBy() {
		return " order by Usr.Id";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((Usr)object);
	}

	public String getSql4Amount(Usr usr) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(usr);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((Usr)object);
	}

	public String getSql4Delete(Usr usr) {
		return "delete from Usr where 1=1"+getSqlCondition(usr);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((Usr)object,fields);
	}

	public String getSql4All(Usr usr, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(usr)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((Usr)object,pageVO,fields);
	}

	public String getSql4Pages(Usr usr,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" Usr.Id "+ getSqlString()+getSqlCondition(usr)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(usr)+" and Usr.Id not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((Usr)object);
	}

	public String getSqlCondition(Usr usr) {
		String sql = "";
		if(null != usr.getId())
			sql += " and Usr.Id = '"+usr.getId()+"'";
		if(null != usr.getComId())
			sql += " and Usr.ComId = '"+usr.getComId()+"'";
		if(null != usr.getDeptId())
			sql += " and Usr.DeptId = '"+usr.getDeptId()+"'";
		if(null != usr.getStatus())
			sql += " and Usr.Status = '"+usr.getStatus()+"'";
		if(null != usr.getCreateBy())
			sql += " and Usr.CreateBy = '"+usr.getCreateBy()+"'";
		if(null != usr.getLastUpd())
			sql += " and Usr.LastUpd = '"+usr.getLastUpd()+"'";
		if(null != usr.getStartCreateDate()) 
			sql += " and Usr.CreateDate >= '"+GenericUtil.dateFormat(usr.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != usr.getEndCreateDate()) 
			sql += " and Usr.CreateDate <= '"+GenericUtil.dateFormat(usr.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != usr.getStartLastDate()) 
			sql += " and Usr.LastDate >= '"+GenericUtil.dateFormat(usr.getStartLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != usr.getEndLastDate()) 
			sql += " and Usr.LastDate <= '"+GenericUtil.dateFormat(usr.getEndLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != usr.getLogin() && !usr.getLogin().trim().equals(""))
			sql += " and Usr.Login = '"+usr.getLogin().trim()+"'";
		if(null != usr.getPwd() && !usr.getPwd().trim().equals(""))
			sql += " and Usr.Pwd = '"+usr.getPwd().trim()+"'";
		if(null != usr.getUsrNo() && !usr.getUsrNo().trim().equals(""))
			sql += " and Usr.UsrNo = '"+usr.getUsrNo().trim()+"'";
		if(null != usr.getUsrName() && !usr.getUsrName().trim().equals(""))
			sql += " and Usr.UsrName = '"+usr.getUsrName().trim()+"'";
		if(null != usr.getEmail() && !usr.getEmail().trim().equals(""))
			sql += " and Usr.Email = '"+usr.getEmail().trim()+"'";
		if(null != usr.getRemark() && !usr.getRemark().trim().equals(""))
			sql += " and Usr.Remark = '"+usr.getRemark().trim()+"'";

		return sql;
	}

	public List<Usr> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<Usr> list = new ArrayList<Usr>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				Usr usr = new Usr();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Id") || _fields[i].equals("Usr.Id"))
						usr.setId(rs.getInt("Id"));
					if(_fields[i].equals("ComId") || _fields[i].equals("Usr.ComId"))
						usr.setComId(rs.getInt("ComId"));
					if(_fields[i].equals("DeptId") || _fields[i].equals("Usr.DeptId"))
						usr.setDeptId(rs.getInt("DeptId"));
					if(_fields[i].equals("Status") || _fields[i].equals("Usr.Status"))
						usr.setStatus(rs.getInt("Status"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("Usr.CreateBy"))
						usr.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("LastUpd") || _fields[i].equals("Usr.LastUpd"))
						usr.setLastUpd(rs.getInt("LastUpd"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("Usr.CreateDate"))
						usr.setCreateDate(rs.getTimestamp("CreateDate"));
					if(_fields[i].equals("LastDate") || _fields[i].equals("Usr.LastDate"))
						usr.setLastDate(rs.getTimestamp("LastDate"));
					if(_fields[i].equals("Login") || _fields[i].equals("Usr.Login"))
						usr.setLogin(rs.getString("Login"));
					if(_fields[i].equals("Pwd") || _fields[i].equals("Usr.Pwd"))
						usr.setPwd(rs.getString("Pwd"));
					if(_fields[i].equals("UsrNo") || _fields[i].equals("Usr.UsrNo"))
						usr.setUsrNo(rs.getString("UsrNo"));
					if(_fields[i].equals("UsrName") || _fields[i].equals("Usr.UsrName"))
						usr.setUsrName(rs.getString("UsrName"));
					if(_fields[i].equals("Email") || _fields[i].equals("Usr.Email"))
						usr.setEmail(rs.getString("Email"));
					if(_fields[i].equals("Remark") || _fields[i].equals("Usr.Remark"))
						usr.setRemark(rs.getString("Remark"));

				}
				list.add(usr);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("UsrHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into Usr("+fields.replaceAll("Usr\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(Usr usr,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Id") || _fields[i].equals("Usr.Id")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, usr.getId());
					}
					else if(_fields[i].equals("ComId") || _fields[i].equals("Usr.ComId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, usr.getComId());
					}
					else if(_fields[i].equals("DeptId") || _fields[i].equals("Usr.DeptId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, usr.getDeptId());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("Usr.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, usr.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("Usr.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, usr.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("Usr.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, usr.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("Usr.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(usr.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("Usr.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(usr.getLastDate().getTime()));
					}
					else if(_fields[i].equals("Login") || _fields[i].equals("Usr.Login")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, usr.getLogin());
					}
					else if(_fields[i].equals("Pwd") || _fields[i].equals("Usr.Pwd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, usr.getPwd());
					}
					else if(_fields[i].equals("UsrNo") || _fields[i].equals("Usr.UsrNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, usr.getUsrNo());
					}
					else if(_fields[i].equals("UsrName") || _fields[i].equals("Usr.UsrName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, usr.getUsrName());
					}
					else if(_fields[i].equals("Email") || _fields[i].equals("Usr.Email")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, usr.getEmail());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("Usr.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, usr.getRemark());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("UsrHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update Usr set ";
		String[] _fields = fields.replaceAll("Usr\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ComId") || _fields[i].equals("Usr.ComId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("DeptId") || _fields[i].equals("Usr.DeptId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("Usr.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("Usr.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("Usr.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("Usr.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastDate") || _fields[i].equals("Usr.LastDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Login") || _fields[i].equals("Usr.Login"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Pwd") || _fields[i].equals("Usr.Pwd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("UsrNo") || _fields[i].equals("Usr.UsrNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("UsrName") || _fields[i].equals("Usr.UsrName"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Email") || _fields[i].equals("Usr.Email"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Remark") || _fields[i].equals("Usr.Remark"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(Usr usr,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ComId") || _fields[i].equals("Usr.ComId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, usr.getComId());
					}
					else if(_fields[i].equals("DeptId") || _fields[i].equals("Usr.DeptId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, usr.getDeptId());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("Usr.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, usr.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("Usr.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, usr.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("Usr.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, usr.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("Usr.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(usr.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("Usr.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(usr.getLastDate().getTime()));
					}
					else if(_fields[i].equals("Login") || _fields[i].equals("Usr.Login")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, usr.getLogin());
					}
					else if(_fields[i].equals("Pwd") || _fields[i].equals("Usr.Pwd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, usr.getPwd());
					}
					else if(_fields[i].equals("UsrNo") || _fields[i].equals("Usr.UsrNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, usr.getUsrNo());
					}
					else if(_fields[i].equals("UsrName") || _fields[i].equals("Usr.UsrName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, usr.getUsrName());
					}
					else if(_fields[i].equals("Email") || _fields[i].equals("Usr.Email")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, usr.getEmail());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("Usr.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, usr.getRemark());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("UsrHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(Usr usr) {
		String _fields = "";
		if(null != usr.getId())
			_fields += "Usr.Id,";
		if(null != usr.getComId())
			_fields += "Usr.ComId,";
		if(null != usr.getDeptId())
			_fields += "Usr.DeptId,";
		if(null != usr.getStatus())
			_fields += "Usr.Status,";
		if(null != usr.getCreateBy())
			_fields += "Usr.CreateBy,";
		if(null != usr.getLastUpd())
			_fields += "Usr.LastUpd,";
		if(null != usr.getCreateDate())
			_fields += "Usr.CreateDate,";
		if(null != usr.getLastDate())
			_fields += "Usr.LastDate,";
		if(null != usr.getLogin())
			_fields += "Usr.Login,";
		if(null != usr.getPwd())
			_fields += "Usr.Pwd,";
		if(null != usr.getUsrNo())
			_fields += "Usr.UsrNo,";
		if(null != usr.getUsrName())
			_fields += "Usr.UsrName,";
		if(null != usr.getEmail())
			_fields += "Usr.Email,";
		if(null != usr.getRemark())
			_fields += "Usr.Remark,";

		return _fields.substring(0, _fields.length()-1);
	}
}