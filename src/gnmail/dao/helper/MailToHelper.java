package gnmail.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnmail.vo.MailTo;

public class MailToHelper extends BasicMailToHelper {
	public String getSqlString() {
		return " from MailTo " +

               " where 1=1 ";
	}

	public List<MailTo> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<MailTo> list = new ArrayList<MailTo>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				MailTo mailTo = new MailTo();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("MailId") || _fields[i].equals("MailTo.MailId"))
						mailTo.setMailId(rs.getInt("MailId"));
					else if(_fields[i].equals("ToId") || _fields[i].equals("MailTo.ToId"))
						mailTo.setToId(rs.getInt("ToId"));
					else if(_fields[i].equals("ToMail") || _fields[i].equals("MailTo.ToMail"))
						mailTo.setToMail(rs.getString("ToMail"));
					else if(_fields[i].equals("ToType") || _fields[i].equals("MailTo.ToType"))
						mailTo.setToType(rs.getInt("ToType"));
					else if(_fields[i].equals("ToName") || _fields[i].equals("MailTo.ToName"))
						mailTo.setToName(rs.getString("ToName"));


				}
				list.add(mailTo);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailToHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}