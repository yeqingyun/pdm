package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WfRelate;

public class WfRelateHelper extends BasicWfRelateHelper {
	public String getSqlString() {
		return " from WfRelate " +
               " inner join WfCfg on (WfCfg.FlowId = WfRelate.FlowId) " + 

               " where 1=1 ";
	}

	public List<WfRelate> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfRelate> list = new ArrayList<WfRelate>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfRelate wfRelate = new WfRelate();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("FlowId") || _fields[i].equals("WfRelate.FlowId"))
						wfRelate.setFlowId(rs.getInt("FlowId"));
					else if(_fields[i].equals("RelateId") || _fields[i].equals("WfRelate.RelateId"))
						wfRelate.setRelateId(rs.getInt("RelateId"));

					else if(_fields[i].equals("FlowName") || _fields[i].equals("WfCfg.FlowName as FlowName"))
						wfRelate.setFlowName(rs.getString("FlowName"));

				}
				list.add(wfRelate);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRelateHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}