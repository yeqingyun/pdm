package gnmail.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnmail.vo.MailGroup;

public class BasicMailGroupHelper implements SqlHelper {
	public String getSqlString() {
		return " from MailGroup where 1=1";
	}

	public String getOrderBy() {
		return " order by MailGroup.GroupId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((MailGroup)object);
	}

	public String getSql4Amount(MailGroup mailGroup) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(mailGroup);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((MailGroup)object);
	}

	public String getSql4Delete(MailGroup mailGroup) {
		return "delete from MailGroup where 1=1"+getSqlCondition(mailGroup);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((MailGroup)object,fields);
	}

	public String getSql4All(MailGroup mailGroup, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(mailGroup)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((MailGroup)object,pageVO,fields);
	}

	public String getSql4Pages(MailGroup mailGroup,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" MailGroup.GroupId "+ getSqlString()+getSqlCondition(mailGroup)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(mailGroup)+" and MailGroup.GroupId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((MailGroup)object);
	}

	public String getSqlCondition(MailGroup mailGroup) {
		String sql = "";
		if(null != mailGroup.getGroupId())
			sql += " and MailGroup.GroupId = '"+mailGroup.getGroupId()+"'";
		if(null != mailGroup.getUserId())
			sql += " and MailGroup.UserId = '"+mailGroup.getUserId()+"'";
		if(null != mailGroup.getGroupParent())
			sql += " and MailGroup.GroupParent = '"+mailGroup.getGroupParent()+"'";
		if(null != mailGroup.getGroupName() && !mailGroup.getGroupName().trim().equals(""))
			sql += " and MailGroup.GroupName = '"+mailGroup.getGroupName().trim()+"'";
		if(null != mailGroup.getGroupLevel())
			sql += " and MailGroup.GroupLevel = '"+mailGroup.getGroupLevel()+"'";
		if(null != mailGroup.getStatus())
			sql += " and MailGroup.Status = '"+mailGroup.getStatus()+"'";
		if(null != mailGroup.getStartCreateDate()) 
			sql += " and MailGroup.CreateDate >= '"+GenericUtil.dateFormat(mailGroup.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != mailGroup.getEndCreateDate()) 
			sql += " and MailGroup.CreateDate <= '"+GenericUtil.dateFormat(mailGroup.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != mailGroup.getLastUpd())
			sql += " and MailGroup.LastUpd = '"+mailGroup.getLastUpd()+"'";
		if(null != mailGroup.getStartLastUpdDate()) 
			sql += " and MailGroup.LastUpdDate >= '"+GenericUtil.dateFormat(mailGroup.getStartLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != mailGroup.getEndLastUpdDate()) 
			sql += " and MailGroup.LastUpdDate <= '"+GenericUtil.dateFormat(mailGroup.getEndLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";

		return sql;
	}

	public List<MailGroup> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<MailGroup> list = new ArrayList<MailGroup>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				MailGroup mailGroup = new MailGroup();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("GroupId") || _fields[i].equals("MailGroup.GroupId"))
						mailGroup.setGroupId(rs.getInt("GroupId"));
					else if(_fields[i].equals("UserId") || _fields[i].equals("MailGroup.UserId"))
						mailGroup.setUserId(rs.getInt("UserId"));
					else if(_fields[i].equals("GroupParent") || _fields[i].equals("MailGroup.GroupParent"))
						mailGroup.setGroupParent(rs.getInt("GroupParent"));
					else if(_fields[i].equals("GroupName") || _fields[i].equals("MailGroup.GroupName"))
						mailGroup.setGroupName(rs.getString("GroupName"));
					else if(_fields[i].equals("GroupLevel") || _fields[i].equals("MailGroup.GroupLevel"))
						mailGroup.setGroupLevel(rs.getInt("GroupLevel"));
					else if(_fields[i].equals("Status") || _fields[i].equals("MailGroup.Status"))
						mailGroup.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("MailGroup.CreateDate"))
						mailGroup.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("MailGroup.LastUpd"))
						mailGroup.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("MailGroup.LastUpdDate"))
						mailGroup.setLastUpdDate(rs.getTimestamp("LastUpdDate"));

				}
				list.add(mailGroup);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailGroupHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into MailGroup("+fields.replaceAll("MailGroup\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(MailGroup mailGroup,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("GroupId") || _fields[i].equals("MailGroup.GroupId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailGroup.getGroupId());
					}
					else if(_fields[i].equals("UserId") || _fields[i].equals("MailGroup.UserId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailGroup.getUserId());
					}
					else if(_fields[i].equals("GroupParent") || _fields[i].equals("MailGroup.GroupParent")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailGroup.getGroupParent());
					}
					else if(_fields[i].equals("GroupName") || _fields[i].equals("MailGroup.GroupName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailGroup.getGroupName());
					}
					else if(_fields[i].equals("GroupLevel") || _fields[i].equals("MailGroup.GroupLevel")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailGroup.getGroupLevel());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("MailGroup.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailGroup.getStatus());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("MailGroup.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(mailGroup.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("MailGroup.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailGroup.getLastUpd());
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("MailGroup.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(mailGroup.getLastUpdDate().getTime()));
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("MailGroupHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update MailGroup set ";
		String[] _fields = fields.replaceAll("MailGroup\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("UserId") || _fields[i].equals("MailGroup.UserId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("GroupParent") || _fields[i].equals("MailGroup.GroupParent"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("GroupName") || _fields[i].equals("MailGroup.GroupName"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("GroupLevel") || _fields[i].equals("MailGroup.GroupLevel"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("MailGroup.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("MailGroup.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("MailGroup.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpdDate") || _fields[i].equals("MailGroup.LastUpdDate"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(MailGroup mailGroup,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("UserId") || _fields[i].equals("MailGroup.UserId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailGroup.getUserId());
					}
					else if(_fields[i].equals("GroupParent") || _fields[i].equals("MailGroup.GroupParent")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailGroup.getGroupParent());
					}
					else if(_fields[i].equals("GroupName") || _fields[i].equals("MailGroup.GroupName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailGroup.getGroupName());
					}
					else if(_fields[i].equals("GroupLevel") || _fields[i].equals("MailGroup.GroupLevel")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailGroup.getGroupLevel());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("MailGroup.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailGroup.getStatus());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("MailGroup.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(mailGroup.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("MailGroup.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailGroup.getLastUpd());
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("MailGroup.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(mailGroup.getLastUpdDate().getTime()));
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("MailGroupHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(MailGroup mailGroup) {
		String _fields = "";
		if(null != mailGroup.getGroupId())
			_fields += "MailGroup.GroupId,";
		if(null != mailGroup.getUserId())
			_fields += "MailGroup.UserId,";
		if(null != mailGroup.getGroupParent())
			_fields += "MailGroup.GroupParent,";
		if(null != mailGroup.getGroupName())
			_fields += "MailGroup.GroupName,";
		if(null != mailGroup.getGroupLevel())
			_fields += "MailGroup.GroupLevel,";
		if(null != mailGroup.getStatus())
			_fields += "MailGroup.Status,";
		if(null != mailGroup.getCreateDate())
			_fields += "MailGroup.CreateDate,";
		if(null != mailGroup.getLastUpd())
			_fields += "MailGroup.LastUpd,";
		if(null != mailGroup.getLastUpdDate())
			_fields += "MailGroup.LastUpdDate,";

		return _fields.substring(0, _fields.length()-1);
	}
}