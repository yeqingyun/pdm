package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WfCfgRelate;

public class WfCfgRelateHelper extends BasicWfCfgRelateHelper {
	public String getSqlString() {
		return " from WfCfgRelate " +
				" left join WfCfg on (WfCfgRelate.RelateId=WfCfg.flowId)" +
				" where 1=1 ";
	}

	public List<WfCfgRelate> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfCfgRelate> list = new ArrayList<WfCfgRelate>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfCfgRelate wfCfgRelate = new WfCfgRelate();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("RelateId") || _fields[i].equals("WfCfgRelate.RelateId"))
						wfCfgRelate.setRelateId(rs.getInt("RelateId"));
					else if(_fields[i].equals("FlowId") || _fields[i].equals("WfCfgRelate.FlowId"))
						wfCfgRelate.setFlowId(rs.getInt("FlowId"));

					else if(_fields[i].equals("FlowName") || _fields[i].equals("WfCfg.FlowName"))
						wfCfgRelate.setFlowName(rs.getString("FlowName"));
				}
				list.add(wfCfgRelate);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfCfgRelateHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}