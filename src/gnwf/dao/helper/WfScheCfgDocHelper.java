package gnwf.dao.helper;

import gnwf.vo.WfScheCfgDoc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class WfScheCfgDocHelper extends BasicWfScheCfgDocHelper {
	public String getSqlString() {
		return " from WfScheCfgDoc inner join WfScheCfg on(WfScheCfg.CfgId = WfScheCfgDoc.CfgId) where 1=1";
	}
	

	public String getOrderBy() {
		return " order by WfScheCfgDoc.Sort";
	}

	public List<WfScheCfgDoc> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfScheCfgDoc> list = new ArrayList<WfScheCfgDoc>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfScheCfgDoc wfScheCfgDoc = new WfScheCfgDoc();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("DocId") || _fields[i].equals("WfScheCfgDoc.DocId"))
						wfScheCfgDoc.setDocId(new Integer(rs.getInt("DocId")));
					else if(_fields[i].equals("CfgId") || _fields[i].equals("WfScheCfgDoc.CfgId"))
						wfScheCfgDoc.setCfgId(new Integer(rs.getInt("CfgId")));
					else if(_fields[i].equals("DocName") || _fields[i].equals("WfScheCfgDoc.DocName"))
						wfScheCfgDoc.setDocName(rs.getString("DocName"));
					else if(_fields[i].equals("Sort") || _fields[i].equals("WfScheCfgDoc.Sort"))
						wfScheCfgDoc.setSort(new Integer(rs.getInt("Sort")));
					else if(_fields[i].equals("IsMust") || _fields[i].equals("WfScheCfgDoc.IsMust"))
						wfScheCfgDoc.setIsMust(new Integer(rs.getInt("IsMust")));
					else if(_fields[i].equals("UserRole") || _fields[i].equals("WfScheCfgDoc.UserRole"))
						wfScheCfgDoc.setUserRole(new Integer(rs.getInt("UserRole")));
					else if(_fields[i].equals("StepId") || _fields[i].equals("WfScheCfgDoc.StepId"))
						wfScheCfgDoc.setStepId(new Integer(rs.getInt("StepId")));
					
					//,WfScheCfg.ScheName as ScheName
					else if(_fields[i].equals("CfgName") || _fields[i].equals("WfScheCfg.CfgName as CfgName"))
						wfScheCfgDoc.setCfgName(rs.getString("CfgName"));
					//Usr
					else if(_fields[i].equals("UsrName")||_fields[i].equals("Usr.UsrName") || _fields[i].equals("Usr.UsrName as UsrName"))
						wfScheCfgDoc.setUsrName(rs.getString("UsrName"));
					else if(_fields[i].equals("Id")||_fields[i].equals("Usr.Id"))
						wfScheCfgDoc.setUsrId(rs.getInt("Id"));
					
					else if(_fields[i].equals("Sort")||_fields[i].equals("WfStep.Sort as Sort"))
						wfScheCfgDoc.setSort(rs.getInt("Sort"));
					
					
					
				}
				list.add(wfScheCfgDoc);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfScheCfgDocHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}
