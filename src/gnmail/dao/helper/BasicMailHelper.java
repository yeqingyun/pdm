package gnmail.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnmail.vo.Mail;

public class BasicMailHelper implements SqlHelper {
	public String getSqlString() {
		return " from Mail where 1=1";
	}

	public String getOrderBy() {
		return " order by Mail.MailId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((Mail)object);
	}

	public String getSql4Amount(Mail mail) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(mail);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((Mail)object);
	}

	public String getSql4Delete(Mail mail) {
		return "delete from Mail where 1=1"+getSqlCondition(mail);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((Mail)object,fields);
	}

	public String getSql4All(Mail mail, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(mail)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((Mail)object,pageVO,fields);
	}

	public String getSql4Pages(Mail mail,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" Mail.MailId "+ getSqlString()+getSqlCondition(mail)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(mail)+" and Mail.MailId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((Mail)object);
	}

	public String getSqlCondition(Mail mail) {
		String sql = "";
		if(null != mail.getMailId())
			sql += " and Mail.MailId = '"+mail.getMailId()+"'";
		if(null != mail.getCfgId())
			sql += " and Mail.CfgId = '"+mail.getCfgId()+"'";
		if(null != mail.getOexId())
			sql += " and Mail.OexId = '"+mail.getOexId()+"'";
		if(null != mail.getSenderId())
			sql += " and Mail.SenderId = '"+mail.getSenderId()+"'";
		if(null != mail.getSender() && !mail.getSender().trim().equals(""))
			sql += " and Mail.Sender = '"+mail.getSender().trim()+"'";
		if(null != mail.getAcceptId())
			sql += " and Mail.AcceptId = '"+mail.getAcceptId()+"'";
		if(null != mail.getAccept() && !mail.getAccept().trim().equals(""))
			sql += " and Mail.Accept = '"+mail.getAccept().trim()+"'";
		if(null != mail.getTitle() && !mail.getTitle().trim().equals(""))
			sql += " and Mail.Title = '"+mail.getTitle().trim()+"'";
		if(null != mail.getMailText() && !mail.getMailText().trim().equals(""))
			sql += " and Mail.MailText = '"+mail.getMailText().trim()+"'";
		if(null != mail.getStartMailDate()) 
			sql += " and Mail.MailDate >= '"+GenericUtil.dateFormat(mail.getStartMailDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != mail.getEndMailDate()) 
			sql += " and Mail.MailDate <= '"+GenericUtil.dateFormat(mail.getEndMailDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != mail.getRemark() && !mail.getRemark().trim().equals(""))
			sql += " and Mail.Remark = '"+mail.getRemark().trim()+"'";
		if(null != mail.getStatus())
			sql += " and Mail.Status = '"+mail.getStatus()+"'";
		if(null != mail.getCreateBy())
			sql += " and Mail.CreateBy = '"+mail.getCreateBy()+"'";
		if(null != mail.getStartCreateDate()) 
			sql += " and Mail.CreateDate >= '"+GenericUtil.dateFormat(mail.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != mail.getEndCreateDate()) 
			sql += " and Mail.CreateDate <= '"+GenericUtil.dateFormat(mail.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != mail.getLastUpd())
			sql += " and Mail.LastUpd = '"+mail.getLastUpd()+"'";
		if(null != mail.getStartLastUpdDate()) 
			sql += " and Mail.LastUpdDate >= '"+GenericUtil.dateFormat(mail.getStartLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != mail.getEndLastUpdDate()) 
			sql += " and Mail.LastUpdDate <= '"+GenericUtil.dateFormat(mail.getEndLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";

		return sql;
	}

	public List<Mail> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<Mail> list = new ArrayList<Mail>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				Mail mail = new Mail();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("MailId") || _fields[i].equals("Mail.MailId"))
						mail.setMailId(rs.getInt("MailId"));
					else if(_fields[i].equals("CfgId") || _fields[i].equals("Mail.CfgId"))
						mail.setCfgId(rs.getInt("CfgId"));
					else if(_fields[i].equals("OexId") || _fields[i].equals("Mail.OexId"))
						mail.setOexId(rs.getInt("OexId"));
					else if(_fields[i].equals("SenderId") || _fields[i].equals("Mail.SenderId"))
						mail.setSenderId(rs.getInt("SenderId"));
					else if(_fields[i].equals("Sender") || _fields[i].equals("Mail.Sender"))
						mail.setSender(rs.getString("Sender"));
					else if(_fields[i].equals("AcceptId") || _fields[i].equals("Mail.AcceptId"))
						mail.setAcceptId(rs.getInt("AcceptId"));
					else if(_fields[i].equals("Accept") || _fields[i].equals("Mail.Accept"))
						mail.setAccept(rs.getString("Accept"));
					else if(_fields[i].equals("Title") || _fields[i].equals("Mail.Title"))
						mail.setTitle(rs.getString("Title"));
					else if(_fields[i].equals("MailText") || _fields[i].equals("Mail.MailText"))
						mail.setMailText(rs.getString("MailText"));
					else if(_fields[i].equals("MailDate") || _fields[i].equals("Mail.MailDate"))
						mail.setMailDate(rs.getTimestamp("MailDate"));
					else if(_fields[i].equals("Remark") || _fields[i].equals("Mail.Remark"))
						mail.setRemark(rs.getString("Remark"));
					else if(_fields[i].equals("Status") || _fields[i].equals("Mail.Status"))
						mail.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("Mail.CreateBy"))
						mail.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("Mail.CreateDate"))
						mail.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("Mail.LastUpd"))
						mail.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("Mail.LastUpdDate"))
						mail.setLastUpdDate(rs.getTimestamp("LastUpdDate"));

				}
				list.add(mail);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into Mail("+fields.replaceAll("Mail\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public int pstmtInsert(Mail mail,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
//			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			PreparedStatement pstmt = DbConnUtil.getDbconn().getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("MailId") || _fields[i].equals("Mail.MailId")) {
						index++;
						pstmt.setInt(index, mail.getMailId());
					}
					else if(_fields[i].equals("CfgId") || _fields[i].equals("Mail.CfgId")) {
						index++;
						pstmt.setInt(index, mail.getCfgId());
					}
					else if(_fields[i].equals("OexId") || _fields[i].equals("Mail.OexId")) {
						index++;
						pstmt.setInt(index, mail.getOexId());
					}
					else if(_fields[i].equals("SenderId") || _fields[i].equals("Mail.SenderId")) {
						System.out.println(mail.getSenderId());
						index++;
						pstmt.setInt(index, mail.getSenderId());
					}
					else if(_fields[i].equals("Sender") || _fields[i].equals("Mail.Sender")) {
						index++;
						pstmt.setString(index, mail.getSender());
					}
					else if(_fields[i].equals("AcceptId") || _fields[i].equals("Mail.AcceptId")) {
						index++;
						pstmt.setInt(index, mail.getAcceptId());
					}
					else if(_fields[i].equals("Accept") || _fields[i].equals("Mail.Accept")) {
						index++;
						pstmt.setString(index, mail.getAccept());
					}
					else if(_fields[i].equals("Title") || _fields[i].equals("Mail.Title")) {
						index++;
						pstmt.setString(index, mail.getTitle());
					}
					else if(_fields[i].equals("MailText") || _fields[i].equals("Mail.MailText")) {
						index++;
						pstmt.setString(index, mail.getMailText());
					}
					else if(_fields[i].equals("MailDate") || _fields[i].equals("Mail.MailDate")) {
						index++;
						pstmt.setTimestamp(index, new java.sql.Timestamp(mail.getMailDate().getTime()));
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("Mail.Remark")) {
						index++;
						pstmt.setString(index, mail.getRemark());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("Mail.Status")) {
						index++;
						pstmt.setInt(index, mail.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("Mail.CreateBy")) {
						index++;
						pstmt.setInt(index, mail.getCreateBy());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("Mail.CreateDate")) {
						index++;
						pstmt.setTimestamp(index, new java.sql.Timestamp(mail.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("Mail.LastUpd")) {
						index++;
						pstmt.setInt(index, mail.getLastUpd());
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("Mail.LastUpdDate")) {
						index++;
						pstmt.setTimestamp(index, new java.sql.Timestamp(mail.getLastUpdDate().getTime()));
					}

			}
			
			//DbConnUtil.getDbconn().getCurrentPstmt().execute();
			pstmt.executeUpdate();
			ResultSet rsKey = pstmt.getGeneratedKeys();
			rsKey.next();
			return rsKey.getInt(1);
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("MailHelper.pstmtInsert SQLException", e);
			e.printStackTrace();
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update Mail set ";
		String[] _fields = fields.replaceAll("Mail\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("CfgId") || _fields[i].equals("Mail.CfgId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("OexId") || _fields[i].equals("Mail.OexId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("SenderId") || _fields[i].equals("Mail.SenderId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Sender") || _fields[i].equals("Mail.Sender"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("AcceptId") || _fields[i].equals("Mail.AcceptId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Accept") || _fields[i].equals("Mail.Accept"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Title") || _fields[i].equals("Mail.Title"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("MailText") || _fields[i].equals("Mail.MailText"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("MailDate") || _fields[i].equals("Mail.MailDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Remark") || _fields[i].equals("Mail.Remark"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("Mail.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("Mail.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("Mail.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("Mail.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpdDate") || _fields[i].equals("Mail.LastUpdDate"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(Mail mail,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("CfgId") || _fields[i].equals("Mail.CfgId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mail.getCfgId());
					}
					else if(_fields[i].equals("OexId") || _fields[i].equals("Mail.OexId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mail.getOexId());
					}
					else if(_fields[i].equals("SenderId") || _fields[i].equals("Mail.SenderId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mail.getSenderId());
					}
					else if(_fields[i].equals("Sender") || _fields[i].equals("Mail.Sender")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mail.getSender());
					}
					else if(_fields[i].equals("AcceptId") || _fields[i].equals("Mail.AcceptId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mail.getAcceptId());
					}
					else if(_fields[i].equals("Accept") || _fields[i].equals("Mail.Accept")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mail.getAccept());
					}
					else if(_fields[i].equals("Title") || _fields[i].equals("Mail.Title")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mail.getTitle());
					}
					else if(_fields[i].equals("MailText") || _fields[i].equals("Mail.MailText")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mail.getMailText());
					}
					else if(_fields[i].equals("MailDate") || _fields[i].equals("Mail.MailDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(mail.getMailDate().getTime()));
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("Mail.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mail.getRemark());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("Mail.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mail.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("Mail.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mail.getCreateBy());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("Mail.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(mail.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("Mail.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mail.getLastUpd());
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("Mail.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(mail.getLastUpdDate().getTime()));
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("MailHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(Mail mail) {
		String _fields = "";
		if(null != mail.getMailId())
			_fields += "Mail.MailId,";
		if(null != mail.getCfgId())
			_fields += "Mail.CfgId,";
		if(null != mail.getOexId())
			_fields += "Mail.OexId,";
		if(null != mail.getSenderId())
			_fields += "Mail.SenderId,";
		if(null != mail.getSender())
			_fields += "Mail.Sender,";
		if(null != mail.getAcceptId())
			_fields += "Mail.AcceptId,";
		if(null != mail.getAccept())
			_fields += "Mail.Accept,";
		if(null != mail.getTitle())
			_fields += "Mail.Title,";
		if(null != mail.getMailText())
			_fields += "Mail.MailText,";
		if(null != mail.getMailDate())
			_fields += "Mail.MailDate,";
		if(null != mail.getRemark())
			_fields += "Mail.Remark,";
		if(null != mail.getStatus())
			_fields += "Mail.Status,";
		if(null != mail.getCreateBy())
			_fields += "Mail.CreateBy,";
		if(null != mail.getCreateDate())
			_fields += "Mail.CreateDate,";
		if(null != mail.getLastUpd())
			_fields += "Mail.LastUpd,";
		if(null != mail.getLastUpdDate())
			_fields += "Mail.LastUpdDate,";

		return _fields.substring(0, _fields.length()-1);
	}
}