package gnmail.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnmail.vo.MailTmpl;

public class MailTmplHelper extends BasicMailTmplHelper {
	public String getSqlString() {
		return " from MailTmpl " +
               " inner join MailCfg on (MailCfg.CfgId = MailTmpl.CfgId) " + 

               " where 1=1 ";
	}

	public List<MailTmpl> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<MailTmpl> list = new ArrayList<MailTmpl>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				MailTmpl mailTmpl = new MailTmpl();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("TmplId") || _fields[i].equals("MailTmpl.TmplId"))
						mailTmpl.setTmplId(rs.getInt("TmplId"));
					else if(_fields[i].equals("CfgId") || _fields[i].equals("MailTmpl.CfgId"))
						mailTmpl.setCfgId(rs.getInt("CfgId"));
					else if(_fields[i].equals("UserId") || _fields[i].equals("MailTmpl.UserId"))
						mailTmpl.setUserId(rs.getInt("UserId"));
					else if(_fields[i].equals("TmplName") || _fields[i].equals("MailTmpl.TmplName"))
						mailTmpl.setTmplName(rs.getString("TmplName"));
					else if(_fields[i].equals("TmplText") || _fields[i].equals("MailTmpl.TmplText"))
						mailTmpl.setTmplText(rs.getString("TmplText"));
					else if(_fields[i].equals("Type") || _fields[i].equals("MailTmpl.Type"))
						mailTmpl.setType(rs.getInt("Type"));
					else if(_fields[i].equals("IsAuto") || _fields[i].equals("MailTmpl.IsAuto"))
						mailTmpl.setIsAuto(rs.getInt("IsAuto"));
					else if(_fields[i].equals("AutoStart") || _fields[i].equals("MailTmpl.AutoStart"))
						mailTmpl.setAutoStart(rs.getTimestamp("AutoStart"));
					else if(_fields[i].equals("AutoCycle") || _fields[i].equals("MailTmpl.AutoCycle"))
						mailTmpl.setAutoCycle(rs.getInt("AutoCycle"));
					else if(_fields[i].equals("Status") || _fields[i].equals("MailTmpl.Status"))
						mailTmpl.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("MailTmpl.CreateBy"))
						mailTmpl.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("MailTmpl.CreateDate"))
						mailTmpl.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("MailTmpl.LastUpd"))
						mailTmpl.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("MailTmpl.LastUpdDate"))
						mailTmpl.setLastUpdDate(rs.getTimestamp("LastUpdDate"));

					else if(_fields[i].equals("CfgId") || _fields[i].equals("MailCfg.CfgId as CfgId"))
						mailTmpl.setCfgId(rs.getInt("CfgId"));

				}
				list.add(mailTmpl);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailTmplHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}