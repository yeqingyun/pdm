package gnmail.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnmail.vo.Mail;

public class MailHelper extends BasicMailHelper {
	public String getSqlString() {
		return " from Mail " +
               " inner join MailCfg on (MailCfg.CfgId = Mail.CfgId) " + 

               " where 1=1 ";
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

					else if(_fields[i].equals("CfgId") || _fields[i].equals("MailCfg.CfgId as CfgId"))
						mail.setCfgId(rs.getInt("CfgId"));

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
}