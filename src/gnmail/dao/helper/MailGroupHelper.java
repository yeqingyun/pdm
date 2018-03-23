package gnmail.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnmail.vo.MailGroup;

public class MailGroupHelper extends BasicMailGroupHelper {
	public String getSqlString() {
		return " from MailGroup " +

               " where 1=1 ";
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
}