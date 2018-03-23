package gnmail.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnmail.vo.MailAutoM;

public class BasicMailAutoMHelper implements SqlHelper {
	public String getSqlString() {
		return " from MailAutoM where 1=1";
	}

	public String getOrderBy() {
		return " order by MailAutoM.MailId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((MailAutoM)object);
	}

	public String getSql4Amount(MailAutoM mailAutoM) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(mailAutoM);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((MailAutoM)object);
	}

	public String getSql4Delete(MailAutoM mailAutoM) {
		return "delete from MailAutoM where 1=1"+getSqlCondition(mailAutoM);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((MailAutoM)object,fields);
	}

	public String getSql4All(MailAutoM mailAutoM, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(mailAutoM)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((MailAutoM)object,pageVO,fields);
	}

	public String getSql4Pages(MailAutoM mailAutoM,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" MailAutoM.MailId "+ getSqlString()+getSqlCondition(mailAutoM)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(mailAutoM)+" and MailAutoM.MailId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((MailAutoM)object);
	}

	public String getSqlCondition(MailAutoM mailAutoM) {
		String sql = "";
		if(null != mailAutoM.getMailId())
			sql += " and MailAutoM.MailId = '"+mailAutoM.getMailId()+"'";
		if(null != mailAutoM.getWfNO() && !mailAutoM.getWfNO().trim().equals(""))
			sql += " and MailAutoM.WfNO = '"+mailAutoM.getWfNO().trim()+"'";
		if(null != mailAutoM.getTaskId())
			sql += " and MailAutoM.TaskId = '"+mailAutoM.getTaskId()+"'";
		if(null != mailAutoM.getCreateBy())
			sql += " and MailAutoM.CreateBy = '"+mailAutoM.getCreateBy()+"'";
		if(null != mailAutoM.getStartCreateDate()) 
			sql += " and MailAutoM.CreateDate >= '"+GenericUtil.dateFormat(mailAutoM.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != mailAutoM.getEndCreateDate()) 
			sql += " and MailAutoM.CreateDate <= '"+GenericUtil.dateFormat(mailAutoM.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != mailAutoM.getAcceptBy())
			sql += " and MailAutoM.AcceptBy = '"+mailAutoM.getAcceptBy()+"'";
		if(null != mailAutoM.getStartAcceptDate()) 
			sql += " and MailAutoM.AcceptDate >= '"+GenericUtil.dateFormat(mailAutoM.getStartAcceptDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != mailAutoM.getEndAcceptDate()) 
			sql += " and MailAutoM.AcceptDate <= '"+GenericUtil.dateFormat(mailAutoM.getEndAcceptDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != mailAutoM.getTitle() && !mailAutoM.getTitle().trim().equals(""))
			sql += " and MailAutoM.Title = '"+mailAutoM.getTitle().trim()+"'";
		if(null != mailAutoM.getMailText() && !mailAutoM.getMailText().trim().equals(""))
			sql += " and MailAutoM.MailText = '"+mailAutoM.getMailText().trim()+"'";
		if(null != mailAutoM.getTaskUri() && !mailAutoM.getTaskUri().trim().equals(""))
			sql += " and MailAutoM.TaskUri = '"+mailAutoM.getTaskUri().trim()+"'";
		if(null != mailAutoM.getStatus())
			sql += " and MailAutoM.Status = '"+mailAutoM.getStatus()+"'";
		if(null != mailAutoM.getRemark() && !mailAutoM.getRemark().trim().equals(""))
			sql += " and MailAutoM.Remark = '"+mailAutoM.getRemark().trim()+"'";

		return sql;
	}

	public List<MailAutoM> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<MailAutoM> list = new ArrayList<MailAutoM>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				MailAutoM mailAutoM = new MailAutoM();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("MailId") || _fields[i].equals("MailAutoM.MailId"))
						mailAutoM.setMailId(rs.getInt("MailId"));
					else if(_fields[i].equals("WfNO") || _fields[i].equals("MailAutoM.WfNO"))
						mailAutoM.setWfNO(rs.getString("WfNO"));
					else if(_fields[i].equals("TaskId") || _fields[i].equals("MailAutoM.TaskId"))
						mailAutoM.setTaskId(rs.getInt("TaskId"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("MailAutoM.CreateBy"))
						mailAutoM.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("MailAutoM.CreateDate"))
						mailAutoM.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("AcceptBy") || _fields[i].equals("MailAutoM.AcceptBy"))
						mailAutoM.setAcceptBy(rs.getInt("AcceptBy"));
					else if(_fields[i].equals("AcceptDate") || _fields[i].equals("MailAutoM.AcceptDate"))
						mailAutoM.setAcceptDate(rs.getTimestamp("AcceptDate"));
					else if(_fields[i].equals("Title") || _fields[i].equals("MailAutoM.Title"))
						mailAutoM.setTitle(rs.getString("Title"));
					else if(_fields[i].equals("MailText") || _fields[i].equals("MailAutoM.MailText"))
						mailAutoM.setMailText(rs.getString("MailText"));
					else if(_fields[i].equals("TaskUri") || _fields[i].equals("MailAutoM.TaskUri"))
						mailAutoM.setTaskUri(rs.getString("TaskUri"));
					else if(_fields[i].equals("Status") || _fields[i].equals("MailAutoM.Status"))
						mailAutoM.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("Remark") || _fields[i].equals("MailAutoM.Remark"))
						mailAutoM.setRemark(rs.getString("Remark"));

				}
				list.add(mailAutoM);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailAutoMHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into MailAutoM("+fields.replaceAll("MailAutoM\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(MailAutoM mailAutoM,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("MailId") || _fields[i].equals("MailAutoM.MailId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailAutoM.getMailId());
					}
					else if(_fields[i].equals("WfNO") || _fields[i].equals("MailAutoM.WfNO")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailAutoM.getWfNO());
					}
					else if(_fields[i].equals("TaskId") || _fields[i].equals("MailAutoM.TaskId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailAutoM.getTaskId());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("MailAutoM.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailAutoM.getCreateBy());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("MailAutoM.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(mailAutoM.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("AcceptBy") || _fields[i].equals("MailAutoM.AcceptBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailAutoM.getAcceptBy());
					}
					else if(_fields[i].equals("AcceptDate") || _fields[i].equals("MailAutoM.AcceptDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(mailAutoM.getAcceptDate().getTime()));
					}
					else if(_fields[i].equals("Title") || _fields[i].equals("MailAutoM.Title")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailAutoM.getTitle());
					}
					else if(_fields[i].equals("MailText") || _fields[i].equals("MailAutoM.MailText")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailAutoM.getMailText());
					}
					else if(_fields[i].equals("TaskUri") || _fields[i].equals("MailAutoM.TaskUri")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailAutoM.getTaskUri());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("MailAutoM.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailAutoM.getStatus());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("MailAutoM.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailAutoM.getRemark());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("MailAutoMHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update MailAutoM set ";
		String[] _fields = fields.replaceAll("MailAutoM\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("CreateBy") || _fields[i].equals("MailAutoM.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("MailAutoM.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("AcceptBy") || _fields[i].equals("MailAutoM.AcceptBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("AcceptDate") || _fields[i].equals("MailAutoM.AcceptDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Title") || _fields[i].equals("MailAutoM.Title"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("MailText") || _fields[i].equals("MailAutoM.MailText"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("TaskUri") || _fields[i].equals("MailAutoM.TaskUri"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("MailAutoM.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Remark") || _fields[i].equals("MailAutoM.Remark"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(MailAutoM mailAutoM,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("CreateBy") || _fields[i].equals("MailAutoM.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailAutoM.getCreateBy());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("MailAutoM.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(mailAutoM.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("AcceptBy") || _fields[i].equals("MailAutoM.AcceptBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailAutoM.getAcceptBy());
					}
					else if(_fields[i].equals("AcceptDate") || _fields[i].equals("MailAutoM.AcceptDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(mailAutoM.getAcceptDate().getTime()));
					}
					else if(_fields[i].equals("Title") || _fields[i].equals("MailAutoM.Title")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailAutoM.getTitle());
					}
					else if(_fields[i].equals("MailText") || _fields[i].equals("MailAutoM.MailText")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailAutoM.getMailText());
					}
					else if(_fields[i].equals("TaskUri") || _fields[i].equals("MailAutoM.TaskUri")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailAutoM.getTaskUri());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("MailAutoM.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailAutoM.getStatus());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("MailAutoM.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailAutoM.getRemark());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("MailAutoMHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(MailAutoM mailAutoM) {
		String _fields = "";
		if(null != mailAutoM.getMailId())
			_fields += "MailAutoM.MailId,";
		if(null != mailAutoM.getWfNO())
			_fields += "MailAutoM.WfNO,";
		if(null != mailAutoM.getTaskId())
			_fields += "MailAutoM.TaskId,";
		if(null != mailAutoM.getCreateBy())
			_fields += "MailAutoM.CreateBy,";
		if(null != mailAutoM.getCreateDate())
			_fields += "MailAutoM.CreateDate,";
		if(null != mailAutoM.getAcceptBy())
			_fields += "MailAutoM.AcceptBy,";
		if(null != mailAutoM.getAcceptDate())
			_fields += "MailAutoM.AcceptDate,";
		if(null != mailAutoM.getTitle())
			_fields += "MailAutoM.Title,";
		if(null != mailAutoM.getMailText())
			_fields += "MailAutoM.MailText,";
		if(null != mailAutoM.getTaskUri())
			_fields += "MailAutoM.TaskUri,";
		if(null != mailAutoM.getStatus())
			_fields += "MailAutoM.Status,";
		if(null != mailAutoM.getRemark())
			_fields += "MailAutoM.Remark,";

		return _fields.substring(0, _fields.length()-1);
	}
}