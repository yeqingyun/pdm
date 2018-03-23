package gnmail.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnmail.vo.MailDoc;

public class MailDocHelper extends BasicMailDocHelper {
	public String getSqlString() {
		return " from MailDoc " +
               " inner join Mail on (Mail.MailId = MailDoc.MailId) " + 

               " where 1=1 ";
	}

	public List<MailDoc> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<MailDoc> list = new ArrayList<MailDoc>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				MailDoc mailDoc = new MailDoc();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("DocId") || _fields[i].equals("MailDoc.DocId"))
						mailDoc.setDocId(rs.getInt("DocId"));
					else if(_fields[i].equals("MailId") || _fields[i].equals("MailDoc.MailId"))
						mailDoc.setMailId(rs.getInt("MailId"));
					else if(_fields[i].equals("DocName") || _fields[i].equals("MailDoc.DocName"))
						mailDoc.setDocName(rs.getString("DocName"));
					else if(_fields[i].equals("UriLink") || _fields[i].equals("MailDoc.UriLink"))
						mailDoc.setUriLink(rs.getString("UriLink"));

					else if(_fields[i].equals("MailId") || _fields[i].equals("Mail.MailId as MailId"))
						mailDoc.setMailId(rs.getInt("MailId"));

				}
				list.add(mailDoc);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailDocHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}