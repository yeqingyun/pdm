package zrprjt.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrprjt.vo.SchDtl;

public class SchDtlHelper extends BasicSchDtlHelper {
	public String getSqlString() {
		return " from SchDtl " +
               " inner join SchCfg on (SchCfg.SchId = SchDtl.SchId) " + 

               " where 1=1 ";
	}

	public List<SchDtl> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<SchDtl> list = new ArrayList<SchDtl>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				SchDtl schDtl = new SchDtl();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SchDtlId") || _fields[i].equals("SchDtl.SchDtlId"))
						schDtl.setSchDtlId(rs.getInt("SchDtlId"));
					if(_fields[i].equals("SchId") || _fields[i].equals("SchDtl.SchId"))
						schDtl.setSchId(rs.getInt("SchId"));
					if(_fields[i].equals("Status") || _fields[i].equals("SchDtl.Status"))
						schDtl.setStatus(rs.getInt("Status"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("SchDtl.CreateBy"))
						schDtl.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("LastUpd") || _fields[i].equals("SchDtl.LastUpd"))
						schDtl.setLastUpd(rs.getInt("LastUpd"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("SchDtl.CreateDate"))
						schDtl.setCreateDate(rs.getTimestamp("CreateDate"));
					if(_fields[i].equals("LastDate") || _fields[i].equals("SchDtl.LastDate"))
						schDtl.setLastDate(rs.getTimestamp("LastDate"));
					if(_fields[i].equals("DocNm") || _fields[i].equals("SchDtl.DocNm"))
						schDtl.setDocNm(rs.getString("DocNm"));
					if(_fields[i].equals("Remark") || _fields[i].equals("SchDtl.Remark"))
						schDtl.setRemark(rs.getString("Remark"));

					
					if(_fields[i].equals("SchNm") || _fields[i].equals("SchCfg.SchNm as SchNm"))
						schDtl.setSchNm(rs.getString("SchNm"));
					

				}
				list.add(schDtl);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SchDtlHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}