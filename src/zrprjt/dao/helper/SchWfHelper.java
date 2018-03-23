package zrprjt.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrprjt.vo.SchWf;

public class SchWfHelper extends BasicSchWfHelper {
	public String getSqlString() {
		return " from SchWf " +
               " inner join SchCfg on (SchCfg.SchId = SchWf.SchId) " + 

               " where 1=1 ";
	}

	public List<SchWf> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<SchWf> list = new ArrayList<SchWf>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				SchWf schWf = new SchWf();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SchId") || _fields[i].equals("SchWf.SchId"))
						schWf.setSchId(rs.getInt("SchId"));
					if(_fields[i].equals("FlowId") || _fields[i].equals("SchWf.FlowId"))
						schWf.setFlowId(rs.getInt("FlowId"));
					if(_fields[i].equals("Status") || _fields[i].equals("SchWf.Status"))
						schWf.setStatus(rs.getInt("Status"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("SchWf.CreateBy"))
						schWf.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("LastUpd") || _fields[i].equals("SchWf.LastUpd"))
						schWf.setLastUpd(rs.getInt("LastUpd"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("SchWf.CreateDate"))
						schWf.setCreateDate(rs.getTimestamp("CreateDate"));
					if(_fields[i].equals("LastDate") || _fields[i].equals("SchWf.LastDate"))
						schWf.setLastDate(rs.getTimestamp("LastDate"));


				}
				list.add(schWf);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SchWfHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}