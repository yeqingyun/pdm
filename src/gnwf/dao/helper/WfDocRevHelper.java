package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WfDocRev;

public class WfDocRevHelper extends BasicWfDocRevHelper {
	public String getSqlString() {
		return " from WfDocRev " +
               " inner join WfDoc on (WfDoc.DocId = WfDocRev.DocId) " + 

               " where 1=1 ";
	}

	public List<WfDocRev> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfDocRev> list = new ArrayList<WfDocRev>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfDocRev wfDocRev = new WfDocRev();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("DocId") || _fields[i].equals("WfDocRev.DocId"))
						wfDocRev.setDocId(rs.getInt("DocId"));
					else if(_fields[i].equals("TaskId") || _fields[i].equals("WfDocRev.TaskId"))
						wfDocRev.setTaskId(rs.getInt("TaskId"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfDocRev.CreateBy"))
						wfDocRev.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfDocRev.WfNo"))
						wfDocRev.setWfNo(rs.getString("WfNo"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfDocRev.CreateDate"))
						wfDocRev.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("RevText") || _fields[i].equals("WfDocRev.RevText"))
						wfDocRev.setRevText(rs.getString("RevText"));
					else if(_fields[i].equals("RevId") || _fields[i].equals("WfDocRev.RevId"))
						wfDocRev.setRevId(rs.getString("RevId"));

					else if(_fields[i].equals("DocName") || _fields[i].equals("WfDoc.DocId as DocName"))
						wfDocRev.setDocName(rs.getString("DocName"));

				}
				list.add(wfDocRev);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfDocRevHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}