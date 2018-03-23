package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WfQuesDtl;

public class WfQuesDtlHelper extends BasicWfQuesDtlHelper {
	public String getSqlString() {
		return " from WfQuesDtl " +
               " inner join WfQues on (WfQues.QuesId = WfQuesDtl.QuesId) " + 

               " where 1=1 ";
	}

	public List<WfQuesDtl> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfQuesDtl> list = new ArrayList<WfQuesDtl>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfQuesDtl wfQuesDtl = new WfQuesDtl();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("QuesDtlId") || _fields[i].equals("WfQuesDtl.QuesDtlId"))
						wfQuesDtl.setQuesDtlId(rs.getInt("QuesDtlId"));
					else if(_fields[i].equals("QuesId") || _fields[i].equals("WfQuesDtl.QuesId"))
						wfQuesDtl.setQuesId(rs.getInt("QuesId"));
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfQuesDtl.UserId"))
						wfQuesDtl.setUserId(rs.getInt("UserId"));
					else if(_fields[i].equals("Status") || _fields[i].equals("WfQuesDtl.Status"))
						wfQuesDtl.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfQuesDtl.CreateBy"))
						wfQuesDtl.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfQuesDtl.LastUpd"))
						wfQuesDtl.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfQuesDtl.CreateDate"))
						wfQuesDtl.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfQuesDtl.LastUpdDate"))
						wfQuesDtl.setLastUpdDate(rs.getTimestamp("LastUpdDate"));
					else if(_fields[i].equals("QuesTxt") || _fields[i].equals("WfQuesDtl.QuesTxt"))
						wfQuesDtl.setQuesTxt(rs.getString("QuesTxt"));
					else if(_fields[i].equals("Title") || _fields[i].equals("WfQuesDtl.Title"))
						wfQuesDtl.setTitle(rs.getString("Title"));

					else if(_fields[i].equals("QuesId") || _fields[i].equals("WfQues.QuesId as QuesId"))
						wfQuesDtl.setQuesId(rs.getInt("QuesId"));

				}
				list.add(wfQuesDtl);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfQuesDtlHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}