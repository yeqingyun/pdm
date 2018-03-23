package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WfMatlEval;

public class WfMatlEvalHelper extends BasicWfMatlEvalHelper {
	public String getSqlString() {
		return " from WfMatlEval " +
               " inner join WfMatl on (WfMatl.MatlId = WfMatlEval.MatlId) " + 

               " where 1=1 ";
	}

	public List<WfMatlEval> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfMatlEval> list = new ArrayList<WfMatlEval>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfMatlEval wfMatlEval = new WfMatlEval();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("MatlId") || _fields[i].equals("WfMatlEval.MatlId"))
						wfMatlEval.setMatlId(rs.getInt("MatlId"));
					else if(_fields[i].equals("UserId") || _fields[i].equals("WfMatlEval.UserId"))
						wfMatlEval.setUserId(rs.getInt("UserId"));
					else if(_fields[i].equals("IsPass") || _fields[i].equals("WfMatlEval.IsPass"))
						wfMatlEval.setIsPass(rs.getInt("IsPass"));
					else if(_fields[i].equals("Evaler") || _fields[i].equals("WfMatlEval.Evaler"))
						wfMatlEval.setEvaler(rs.getInt("Evaler"));
					else if(_fields[i].equals("status") || _fields[i].equals("WfMatlEval.status"))
						wfMatlEval.setstatus(rs.getInt("status"));
					else if(_fields[i].equals("EvalId") || _fields[i].equals("WfMatlEval.EvalId"))
						wfMatlEval.setEvalId(rs.getInt("EvalId"));
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfMatlEval.WfNo"))
						wfMatlEval.setWfNo(rs.getString("WfNo"));
					else if(_fields[i].equals("EvalDate") || _fields[i].equals("WfMatlEval.EvalDate"))
						wfMatlEval.setEvalDate(rs.getTimestamp("EvalDate"));
					else if(_fields[i].equals("EvalText") || _fields[i].equals("WfMatlEval.EvalText"))
						wfMatlEval.setEvalText(rs.getString("EvalText"));

					else if(_fields[i].equals("MatlNo") || _fields[i].equals("WfMatl.MatlNo as MatlNo"))
						wfMatlEval.setMatlNo(rs.getString("MatlNo"));

				}
				list.add(wfMatlEval);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfMatlEvalHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}