package gnmail.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnmail.vo.MailCfg;

public class MailCfgHelper extends BasicMailCfgHelper {
	public String getSqlString() {
		return " from MailCfg " +

               " where 1=1 ";
	}

	public List<MailCfg> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<MailCfg> list = new ArrayList<MailCfg>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				MailCfg mailCfg = new MailCfg();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("CfgId") || _fields[i].equals("MailCfg.CfgId"))
						mailCfg.setCfgId(rs.getInt("CfgId"));
					else if(_fields[i].equals("UserId") || _fields[i].equals("MailCfg.UserId"))
						mailCfg.setUserId(rs.getInt("UserId"));
					else if(_fields[i].equals("MailAddr") || _fields[i].equals("MailCfg.MailAddr"))
						mailCfg.setMailAddr(rs.getString("MailAddr"));
					else if(_fields[i].equals("Pwd") || _fields[i].equals("MailCfg.Pwd"))
						mailCfg.setPwd(rs.getString("Pwd"));
					else if(_fields[i].equals("MailName") || _fields[i].equals("MailCfg.MailName"))
						mailCfg.setMailName(rs.getString("MailName"));
					else if(_fields[i].equals("MailSign") || _fields[i].equals("MailCfg.MailSign"))
						mailCfg.setMailSign(rs.getString("MailSign"));
					else if(_fields[i].equals("ServIpAddr") || _fields[i].equals("MailCfg.ServIpAddr"))
						mailCfg.setServIpAddr(rs.getString("ServIpAddr"));
					else if(_fields[i].equals("Smpt") || _fields[i].equals("MailCfg.Smpt"))
						mailCfg.setSmpt(rs.getString("Smpt"));
					else if(_fields[i].equals("Pop3") || _fields[i].equals("MailCfg.Pop3"))
						mailCfg.setPop3(rs.getString("Pop3"));
					else if(_fields[i].equals("Remark") || _fields[i].equals("MailCfg.Remark"))
						mailCfg.setRemark(rs.getString("Remark"));
					else if(_fields[i].equals("Status") || _fields[i].equals("MailCfg.Status"))
						mailCfg.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("MailCfg.CreateBy"))
						mailCfg.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("MailCfg.CreateDate"))
						mailCfg.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("MailCfg.LastUpd"))
						mailCfg.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("MailCfg.LastUpdDate"))
						mailCfg.setLastUpdDate(rs.getTimestamp("LastUpdDate"));


				}
				list.add(mailCfg);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailCfgHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}