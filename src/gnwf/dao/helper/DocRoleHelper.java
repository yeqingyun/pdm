package gnwf.dao.helper;

import gnwf.vo.DocRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.frm.jdbc.DbConnUtil;


public class DocRoleHelper extends BasicDocRoleHelper {
	public String getSqlString() {
		return " from DocRole " +
               " where 1=1 ";
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
						docRole.setType(rs.getInt("Status"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("DocRole.CreateBy"))
						docRole.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("DocRole.CreateDate"))
						docRole.setCreateDate(rs.getDate("CreateDate"));
					if(_fields[i].equals("LastUpd") || _fields[i].equals("DocRole.LastUpd"))
						docRole.setLastUpd(rs.getInt("LastUpd"));
					if(_fields[i].equals("LastUpdDate") || _fields[i].equals("DocRole.LastUpdDate"))
						docRole.setLastUpdDate(rs.getDate("LastUpdDate"));
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

	public void update(DocRole docRole,String fields) throws SQLException {
		String sql = "update DocRole set ";
		String[] _fields = fields.replaceAll("DocRole\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Status") || _fields[i].equals("DocRole.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("DocRole.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("DocRole.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("DocRole.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpdDate") || _fields[i].equals("DocRole.LastUpdDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Remark") || _fields[i].equals("DocRole.Remark"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where DocRole.DocCateId="+docRole.getDocCateId()+" and DocRole.PrjtRoleId="+docRole.getPrjtRoleId()+" and DocRole.Type="+docRole.getType()+" "+getOrderBy();

		int index=0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Status") || _fields[i].equals("DocRole.Status")) {
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
			Logger.getLogger(this.getClass()).error("DocRoleHelper.update SQLException", e);
			throw new java.sql.SQLException();
		}
	}
}