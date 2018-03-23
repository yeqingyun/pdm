package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WfJobUserRelte;

public class WfJobUserRelteHelper extends BasicWfJobUserRelteHelper {
	public String getSqlString() {
		return " from WfJobUserRelte " +

               " where 1=1 ";
	}

	public List<WfJobUserRelte> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfJobUserRelte> list = new ArrayList<WfJobUserRelte>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfJobUserRelte wfJobUserRelte = new WfJobUserRelte();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("JobId") || _fields[i].equals("WfJobUserRelte.JobId"))
						wfJobUserRelte.setJobId(rs.getInt("JobId"));
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfJobUserRelte.UserId"))
						wfJobUserRelte.setUserId(rs.getInt("UserId"));


				}
				list.add(wfJobUserRelte);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfJobUserRelteHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}