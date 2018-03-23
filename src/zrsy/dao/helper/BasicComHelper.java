package zrsy.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrsy.vo.Com;

public class BasicComHelper implements SqlHelper {
	public String getSqlString() {
		return " from Com where 1=1";
	}

	public String getOrderBy() {
		return " order by Com.ComId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((Com)object);
	}

	public String getSql4Amount(Com com) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(com);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((Com)object);
	}

	public String getSql4Delete(Com com) {
		return "delete from Com where 1=1"+getSqlCondition(com);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((Com)object,fields);
	}

	public String getSql4All(Com com, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(com)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((Com)object,pageVO,fields);
	}

	public String getSql4Pages(Com com,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" Com.ComId "+ getSqlString()+getSqlCondition(com)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(com)+" and Com.ComId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((Com)object);
	}

	public String getSqlCondition(Com com) {
		String sql = "";
		if(null != com.getComId())
			sql += " and Com.ComId = '"+com.getComId()+"'";
		if(null != com.getParent())
			sql += " and Com.Parent = '"+com.getParent()+"'";
		if(null != com.getLeve())
			sql += " and Com.Leve = '"+com.getLeve()+"'";
		if(null != com.getStatus())
			sql += " and Com.Status = '"+com.getStatus()+"'";
		if(null != com.getCreateBy())
			sql += " and Com.CreateBy = '"+com.getCreateBy()+"'";
		if(null != com.getLastUpd())
			sql += " and Com.LastUpd = '"+com.getLastUpd()+"'";
		if(null != com.getStartCreateDate()) 
			sql += " and Com.CreateDate >= '"+GenericUtil.dateFormat(com.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != com.getEndCreateDate()) 
			sql += " and Com.CreateDate <= '"+GenericUtil.dateFormat(com.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != com.getStartLastDate()) 
			sql += " and Com.LastDate >= '"+GenericUtil.dateFormat(com.getStartLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != com.getEndLastDate()) 
			sql += " and Com.LastDate <= '"+GenericUtil.dateFormat(com.getEndLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != com.getComNo() && !com.getComNo().trim().equals(""))
			sql += " and Com.ComNo = '"+com.getComNo().trim()+"'";
		if(null != com.getComNm() && !com.getComNm().trim().equals(""))
			sql += " and Com.ComNm = '"+com.getComNm().trim()+"'";
		if(null != com.getRemark() && !com.getRemark().trim().equals(""))
			sql += " and Com.Remark = '"+com.getRemark().trim()+"'";

		return sql;
	}

	public List<Com> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<Com> list = new ArrayList<Com>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				Com com = new Com();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ComId") || _fields[i].equals("Com.ComId"))
						com.setComId(rs.getInt("ComId"));
					if(_fields[i].equals("Parent") || _fields[i].equals("Com.Parent"))
						com.setParent(rs.getInt("Parent"));
					if(_fields[i].equals("Leve") || _fields[i].equals("Com.Leve"))
						com.setLeve(rs.getInt("Leve"));
					if(_fields[i].equals("Status") || _fields[i].equals("Com.Status"))
						com.setStatus(rs.getInt("Status"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("Com.CreateBy"))
						com.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("LastUpd") || _fields[i].equals("Com.LastUpd"))
						com.setLastUpd(rs.getInt("LastUpd"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("Com.CreateDate"))
						com.setCreateDate(rs.getTimestamp("CreateDate"));
					if(_fields[i].equals("LastDate") || _fields[i].equals("Com.LastDate"))
						com.setLastDate(rs.getTimestamp("LastDate"));
					if(_fields[i].equals("ComNo") || _fields[i].equals("Com.ComNo"))
						com.setComNo(rs.getString("ComNo"));
					if(_fields[i].equals("ComNm") || _fields[i].equals("Com.ComNm"))
						com.setComNm(rs.getString("ComNm"));
					if(_fields[i].equals("Remark") || _fields[i].equals("Com.Remark"))
						com.setRemark(rs.getString("Remark"));

				}
				list.add(com);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ComHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into Com("+fields.replaceAll("Com\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(Com com,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ComId") || _fields[i].equals("Com.ComId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, com.getComId());
					}
					else if(_fields[i].equals("Parent") || _fields[i].equals("Com.Parent")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, com.getParent());
					}
					else if(_fields[i].equals("Leve") || _fields[i].equals("Com.Leve")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, com.getLeve());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("Com.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, com.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("Com.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, com.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("Com.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, com.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("Com.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(com.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("Com.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(com.getLastDate().getTime()));
					}
					else if(_fields[i].equals("ComNo") || _fields[i].equals("Com.ComNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, com.getComNo());
					}
					else if(_fields[i].equals("ComNm") || _fields[i].equals("Com.ComNm")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, com.getComNm());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("Com.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, com.getRemark());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("ComHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update Com set ";
		String[] _fields = fields.replaceAll("Com\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Parent") || _fields[i].equals("Com.Parent"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Leve") || _fields[i].equals("Com.Leve"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("Com.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("Com.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("Com.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("Com.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastDate") || _fields[i].equals("Com.LastDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("ComNo") || _fields[i].equals("Com.ComNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("ComNm") || _fields[i].equals("Com.ComNm"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Remark") || _fields[i].equals("Com.Remark"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(Com com,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Parent") || _fields[i].equals("Com.Parent")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, com.getParent());
					}
					else if(_fields[i].equals("Leve") || _fields[i].equals("Com.Leve")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, com.getLeve());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("Com.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, com.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("Com.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, com.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("Com.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, com.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("Com.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(com.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("Com.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(com.getLastDate().getTime()));
					}
					else if(_fields[i].equals("ComNo") || _fields[i].equals("Com.ComNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, com.getComNo());
					}
					else if(_fields[i].equals("ComNm") || _fields[i].equals("Com.ComNm")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, com.getComNm());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("Com.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, com.getRemark());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("ComHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(Com com) {
		String _fields = "";
		if(null != com.getComId())
			_fields += "Com.ComId,";
		if(null != com.getParent())
			_fields += "Com.Parent,";
		if(null != com.getLeve())
			_fields += "Com.Leve,";
		if(null != com.getStatus())
			_fields += "Com.Status,";
		if(null != com.getCreateBy())
			_fields += "Com.CreateBy,";
		if(null != com.getLastUpd())
			_fields += "Com.LastUpd,";
		if(null != com.getCreateDate())
			_fields += "Com.CreateDate,";
		if(null != com.getLastDate())
			_fields += "Com.LastDate,";
		if(null != com.getComNo())
			_fields += "Com.ComNo,";
		if(null != com.getComNm())
			_fields += "Com.ComNm,";
		if(null != com.getRemark())
			_fields += "Com.Remark,";

		return _fields.substring(0, _fields.length()-1);
	}
}