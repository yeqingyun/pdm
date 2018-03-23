package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WfDept;

public class WfDeptHelper extends BasicWfDeptHelper {
	public String getSqlString() {
		return " from WfDept " +
               " inner join WfCfg on (WfCfg.FlowId = WfDept.FlowId) " + 

               " where 1=1 ";
	}

	public List<WfDept> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfDept> list = new ArrayList<WfDept>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfDept wfDept = new WfDept();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("FlowId") || _fields[i].equals("WfDept.FlowId"))
						wfDept.setFlowId(rs.getInt("FlowId"));
					else if(_fields[i].equals("DeptId") || _fields[i].equals("WfDept.DeptId"))
						wfDept.setDeptId(rs.getInt("DeptId"));

					else if(_fields[i].equals("FlowName") || _fields[i].equals("WfCfg.FlowName as FlowName"))
						wfDept.setFlowName(rs.getString("FlowName"));

				}
				list.add(wfDept);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfDeptHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}