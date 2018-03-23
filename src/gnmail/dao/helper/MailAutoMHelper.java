package gnmail.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnmail.vo.MailAutoM;

public class MailAutoMHelper extends BasicMailAutoMHelper {
	public String getSqlString() {
		return " from MailAutoM " +
               " inner join Mail on (Mail.MailId = MailAutoM.MailId) " + 

               " where 1=1 ";
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

					else if(_fields[i].equals("MailId") || _fields[i].equals("Mail.MailId as MailId"))
						mailAutoM.setMailId(rs.getInt("MailId"));

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
}