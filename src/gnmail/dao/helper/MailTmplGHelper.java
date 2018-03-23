package gnmail.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnmail.vo.MailTmplG;

public class MailTmplGHelper extends BasicMailTmplGHelper {
	public String getSqlString() {
		return " from MailTmplG " +
               " inner join MailGroup on (MailGroup.GroupId = MailTmplG.GroupId) " + 
               " inner join MailTmpl on (MailTmpl.TmplId = MailTmplG.TmplId) " + 

               " where 1=1 ";
	}

	public List<MailTmplG> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<MailTmplG> list = new ArrayList<MailTmplG>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				MailTmplG mailTmplG = new MailTmplG();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("TmplId") || _fields[i].equals("MailTmplG.TmplId"))
						mailTmplG.setTmplId(rs.getInt("TmplId"));
					else if(_fields[i].equals("GroupId") || _fields[i].equals("MailTmplG.GroupId"))
						mailTmplG.setGroupId(rs.getInt("GroupId"));

					else if(_fields[i].equals("GroupId") || _fields[i].equals("MailGroup.GroupId as GroupId"))
						mailTmplG.setGroupId(rs.getInt("GroupId"));
					else if(_fields[i].equals("TmplId") || _fields[i].equals("MailTmpl.TmplId as TmplId"))
						mailTmplG.setTmplId(rs.getInt("TmplId"));

				}
				list.add(mailTmplG);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailTmplGHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}