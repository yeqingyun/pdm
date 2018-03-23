package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import gnwf.vo.WfRdRisk;

public class WfRdRiskHelper extends BasicWfRdRiskHelper {
	@Override
	public String getSqlCondition(WfRdRisk wfRdRisk) {
		String sql = super.getSqlCondition(wfRdRisk);
		return sql;
	}
	
	public List<WfRdRisk> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfRdRisk> list = new ArrayList<WfRdRisk>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfRdRisk wfRdRisk = new WfRdRisk();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("RiskId") || _fields[i].equals("WfRdRisk.RiskId"))
						wfRdRisk.setRiskId(rs.getString("RiskId"));
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfRdRisk.WfNo"))
						wfRdRisk.setWfNo(rs.getString("WfNo"));
				}
				list.add(wfRdRisk);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdRiskHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}