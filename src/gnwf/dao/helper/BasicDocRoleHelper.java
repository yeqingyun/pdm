package gnwf.dao.helper;

import gnfs.util.GenericUtil;
import gnwf.vo.DocRole;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrsy.vo.GpMenu;

public class BasicDocRoleHelper implements SqlHelper {
	public String getSqlString() {
		return " from DocRole where 1=1";
	}

	public String getOrderBy() {
		return " order by DocRole.Type,DocRole.DocCateId,DocRole.PrjtRoleId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((DocRole)object);
	}

	public String getSql4Amount(DocRole docRole) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(docRole);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((DocRole)object);
	}

	public String getSql4Delete(DocRole docRole) {
		return "delete from DocRole where 1=1"+getSqlCondition(docRole);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((DocRole)object,fields);
	}

	public String getSql4All(DocRole docRole, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(docRole)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((DocRole)object,pageVO,fields);
	}

	public String getSql4Pages(DocRole docRole,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" DocRole.DocRoleId "+ getSqlString()+getSqlCondition(docRole)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(docRole)+" and DocRole.DocRoleId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((DocRole)object);
	}

	public String getSqlCondition(DocRole docRole) {
		String sql = "";
		if(null != docRole.getDocCateId())
			sql += " and DocRole.DocCateId = '"+docRole.getDocCateId()+"'";
		if(null != docRole.getPrjtRoleId())
			sql += " and DocRole.PrjtRoleId = '"+docRole.getPrjtRoleId()+"'";
		if(null != docRole.getType())
			sql += " and DocRole.Type = '"+docRole.getType()+"'";
		if(null != docRole.getStatus())
			sql += " and DocRole.Status = '"+docRole.getStatus()+"'";
		if(null != docRole.getCreateBy())
			sql += " and DocRole.CreateBy = '"+docRole.getCreateBy()+"'";
		if(null != docRole.getLastUpd())
			sql += " and DocRole.LastUpd = '"+docRole.getLastUpd()+"'";
		if(null != docRole.getCreateDate()) 
			sql += " and DocRole.CreateDate <= '"+GenericUtil.dateFormat(docRole.getCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != docRole.getLastUpdDate()) 
			sql += " and DocRole.LastUpdDate >= '"+GenericUtil.dateFormat(docRole.getLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != docRole.getRemark() && !docRole.getRemark().trim().equals(""))
			sql += " and DocRole.Remark = '"+docRole.getRemark().trim()+"'";

		return sql;
	}

	public List<DocRole> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<DocRole> list = new ArrayList<DocRole>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				DocRole docRole = new DocRole();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("DocCateId") || _fields[i].equals("DocRole.DocCateId"))
						docRole.setDocCateId(rs.getInt("DocCateId"));
					if(_fields[i].equals("PrjtRoleId") || _fields[i].equals("DocRole.PrjtRoleId"))
						docRole.setPrjtRoleId(rs.getInt("PrjtRoleId"));
					if(_fields[i].equals("Type") || _fields[i].equals("DocRole.Type"))
						docRole.setType(rs.getInt("Type"));
					if(_fields[i].equals("Status") || _fields[i].equals("DocRole.Status"))
						docRole.setStatus(rs.getInt("Status"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("DocRole.CreateBy"))
						docRole.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("LastUpd") || _fields[i].equals("DocRole.LastUpd"))
						docRole.setLastUpd(rs.getInt("LastUpd"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("DocRole.CreateDate"))
						docRole.setCreateDate(rs.getTimestamp("CreateDate"));
					if(_fields[i].equals("LastUpdDate") || _fields[i].equals("DocRole.LastUpdDate"))
						docRole.setLastUpdDate(rs.getTimestamp("LastUpdDate"));
					if(_fields[i].equals("Remark") || _fields[i].equals("DocRole.Remark"))
						docRole.setRemark(rs.getString("Remark"));
				}
				list.add(docRole);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DocRoleHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into DocRole("+fields.replaceAll("DocRole\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(DocRole docRole,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("DocCateId") || _fields[i].equals("DocRole.DocCateId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, docRole.getDocCateId());
					}
					else if(_fields[i].equals("PrjtRoleId") || _fields[i].equals("DocRole.PrjtRoleId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, docRole.getPrjtRoleId());
					}
					else if(_fields[i].equals("Type") || _fields[i].equals("DocRole.Type")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, docRole.getType());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("DocRole.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, docRole.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("DocRole.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, docRole.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("DocRole.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, docRole.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("DocRole.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(docRole.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("DocRole.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(docRole.getLastUpdDate().getTime()));
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("DocRole.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, docRole.getRemark());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("DocRoleHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}
	
	@Deprecated
	public String getUpdateSql(String fields,String key,String keyValue) {
//		String sql = "update DocRole set ";
//		String[] _fields = fields.replaceAll("DocRole\\.", "").split(",");
//		for(int i=0;i<_fields.length;i++) {
//					if(_fields[i].equals("Status") || _fields[i].equals("DocRole.Status"))
//						sql += _fields[i] + " = ?,";
//					if(_fields[i].equals("CreateBy") || _fields[i].equals("DocRole.CreateBy"))
//						sql += _fields[i] + " = ?,";
//					if(_fields[i].equals("LastUpd") || _fields[i].equals("DocRole.LastUpd"))
//						sql += _fields[i] + " = ?,";
//					if(_fields[i].equals("CreateDate") || _fields[i].equals("DocRole.CreateDate"))
//						sql += _fields[i] + " = ?,";
//					if(_fields[i].equals("LastUpdDate") || _fields[i].equals("DocRole.LastUpdDate"))
//						sql += _fields[i] + " = ?,";
//					if(_fields[i].equals("Remark") || _fields[i].equals("DocRole.Remark"))
//						sql += _fields[i] + " = ?,";
//
//		}
//		sql = sql.substring(0, sql.length()-1);
//		sql += " where 1=1 ";
//		return sql;
		return null;
	}

	public void pstmtUpdate(DocRole docRole,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
				if(_fields[i].equals("Status") || _fields[i].equals("DocRole.Status")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, docRole.getStatus());
				}
				if(_fields[i].equals("CreateBy") || _fields[i].equals("DocRole.CreateBy")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, docRole.getCreateBy());
				}
				if(_fields[i].equals("LastUpd") || _fields[i].equals("DocRole.LastUpd")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, docRole.getLastUpd());
				}
				if(_fields[i].equals("CreateDate") || _fields[i].equals("DocRole.CreateDate")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(docRole.getCreateDate().getTime()));
				}
				if(_fields[i].equals("LastUpdDate") || _fields[i].equals("DocRole.LastUpdDate")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(docRole.getLastUpdDate().getTime()));
				}
				if(_fields[i].equals("Remark") || _fields[i].equals("DocRole.Remark")){
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, docRole.getRemark());
				}
			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("DocRoleHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(DocRole docRole) {
		String _fields = "";
		if(null != docRole.getDocCateId())
			_fields += "DocRole.DocCateId,";
		if(null != docRole.getPrjtRoleId())
			_fields += "DocRole.PrjtRoleId,";
		if(null != docRole.getType())
			_fields += "DocRole.Type,";
		if(null != docRole.getStatus())
			_fields += "DocRole.Status,";
		if(null != docRole.getCreateBy())
			_fields += "DocRole.CreateBy,";
		if(null != docRole.getLastUpd())
			_fields += "DocRole.LastUpd,";
		if(null != docRole.getCreateDate())
			_fields += "DocRole.CreateDate,";
		if(null != docRole.getLastUpdDate())
			_fields += "DocRole.LastUpdDate,";
		if(null != docRole.getRemark())
			_fields += "DocRole.Remark,";

		return _fields.substring(0, _fields.length()-1);
	}
}