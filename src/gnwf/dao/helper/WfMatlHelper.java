package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WfMatl;

public class WfMatlHelper extends BasicWfMatlHelper {
	public String getSqlString() {
		return " from WfMatl " +
               " inner join WfRd on (WfRd.WfNo = WfMatl.WfNo) " + 

               " where 1=1 ";
	}

	public List<WfMatl> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfMatl> list = new ArrayList<WfMatl>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfMatl wfMatl = new WfMatl();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("MatlId") || _fields[i].equals("WfMatl.MatlId"))
						wfMatl.setMatlId(rs.getInt("MatlId"));
					else if(_fields[i].equals("MatlType") || _fields[i].equals("WfMatl.MatlType"))
						wfMatl.setMatlType(rs.getInt("MatlType"));
					else if(_fields[i].equals("IsSubs") || _fields[i].equals("WfMatl.IsSubs"))
						wfMatl.setIsSubs(rs.getInt("IsSubs"));
					else if(_fields[i].equals("Risk") || _fields[i].equals("WfMatl.Risk"))
						wfMatl.setRisk(rs.getInt("Risk"));
					else if(_fields[i].equals("LotSize") || _fields[i].equals("WfMatl.LotSize"))
						wfMatl.setLotSize(rs.getInt("LotSize"));
					else if(_fields[i].equals("IsPanel") || _fields[i].equals("WfMatl.IsPanel"))
						wfMatl.setIsPanel(rs.getInt("IsPanel"));
					else if(_fields[i].equals("Status") || _fields[i].equals("WfMatl.Status"))
						wfMatl.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfMatl.CreateBy"))
						wfMatl.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfMatl.LastUpd"))
						wfMatl.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("MatlNo") || _fields[i].equals("WfMatl.MatlNo"))
						wfMatl.setMatlNo(rs.getString("MatlNo"));
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfMatl.WfNo"))
						wfMatl.setWfNo(rs.getString("WfNo"));
					else if(_fields[i].equals("MatlLevel") || _fields[i].equals("WfMatl.MatlLevel"))
						wfMatl.setMatlLevel(rs.getString("MatlLevel"));
					else if(_fields[i].equals("historyLevel") || _fields[i].equals("WfMatl.historyLevel"))
						wfMatl.sethistoryLevel(rs.getString("historyLevel"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfMatl.CreateDate"))
						wfMatl.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfMatl.LastUpdDate"))
						wfMatl.setLastUpdDate(rs.getTimestamp("LastUpdDate"));
					else if(_fields[i].equals("MatlName") || _fields[i].equals("WfMatl.MatlName"))
						wfMatl.setMatlName(rs.getString("MatlName"));
					else if(_fields[i].equals("MatlDesc") || _fields[i].equals("WfMatl.MatlDesc"))
						wfMatl.setMatlDesc(rs.getString("MatlDesc"));
					else if(_fields[i].equals("Supplier") || _fields[i].equals("WfMatl.Supplier"))
						wfMatl.setSupplier(rs.getString("Supplier"));
					else if(_fields[i].equals("Remark") || _fields[i].equals("WfMatl.Remark"))
						wfMatl.setRemark(rs.getString("Remark"));
					else if(_fields[i].equals("SupNo") || _fields[i].equals("WfMatl.SupNo"))
						wfMatl.setSupNo(rs.getString("SupNo"));
					else if(_fields[i].equals("MatlEvalDesc") || _fields[i].equals("WfMatl.MatlEvalDesc"))
						wfMatl.setMatlEvalDesc(rs.getString("MatlEvalDesc"));

					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfRd.WfNo as WfNo"))
						wfMatl.setWfNo(rs.getString("WfNo"));

				}
				list.add(wfMatl);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfMatlHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}