package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WfQuesRelate;

public class WfQuesRelateHelper extends BasicWfQuesRelateHelper {
	public String getSqlString() {
		return " from WfQuesRelate " +
               " inner join WfQues on (WfQues.QuesId = WfQuesRelate.QuesId) " + 
               " inner join WfRd on (WfRd.WfNo = WfQuesRelate.WfNo) " + 

               " where 1=1 ";
	}

	public List<WfQuesRelate> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfQuesRelate> list = new ArrayList<WfQuesRelate>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfQuesRelate wfQuesRelate = new WfQuesRelate();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("QuesId") || _fields[i].equals("WfQuesRelate.QuesId"))
						wfQuesRelate.setQuesId(rs.getInt("QuesId"));
					else if(_fields[i].equals("IsRisk") || _fields[i].equals("WfQuesRelate.IsRisk"))
						wfQuesRelate.setIsRisk(rs.getInt("IsRisk"));
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfQuesRelate.WfNo"))
						wfQuesRelate.setWfNo(rs.getString("WfNo"));

					else if(_fields[i].equals("QuesId") || _fields[i].equals("WfQues.QuesId as QuesId"))
						wfQuesRelate.setQuesId(rs.getInt("QuesId"));
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfRd.WfNo as WfNo"))
						wfQuesRelate.setWfNo(rs.getString("WfNo"));

				}
				list.add(wfQuesRelate);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfQuesRelateHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}