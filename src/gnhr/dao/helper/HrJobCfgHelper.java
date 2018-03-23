package gnhr.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnhr.vo.HrJobCfg;

public class HrJobCfgHelper extends BasicHrJobCfgHelper {
	public String getSqlString() {
		return " from HrJobCfg " +
			   " inner join Dept on Dept.DeptId = HrJobCfg.DeptId" +
			   " inner join Com on Com.ComId = HrJobCfg.ComId" +	
               " where 1=1 ";
	}

	public List<HrJobCfg> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<HrJobCfg> list = new ArrayList<HrJobCfg>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				HrJobCfg hrJobCfg = new HrJobCfg();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("JobId") || _fields[i].equals("HrJobCfg.JobId"))
						hrJobCfg.setJobId(rs.getInt("JobId"));
					else if(_fields[i].equals("ComId") || _fields[i].equals("HrJobCfg.ComId"))
						hrJobCfg.setComId(rs.getInt("ComId"));
					else if(_fields[i].equals("DeptId") || _fields[i].equals("HrJobCfg.DeptId"))
						hrJobCfg.setDeptId(rs.getInt("DeptId"));
					else if(_fields[i].equals("JobName") || _fields[i].equals("HrJobCfg.JobName"))
						hrJobCfg.setJobName(rs.getString("JobName"));
					else if(_fields[i].equals("DefQty") || _fields[i].equals("HrJobCfg.DefQty"))
						hrJobCfg.setDefQty(rs.getInt("DefQty"));
					else if(_fields[i].equals("FactQty") || _fields[i].equals("HrJobCfg.FactQty"))
						hrJobCfg.setFactQty(rs.getInt("FactQty"));
					else if(_fields[i].equals("Remark") || _fields[i].equals("HrJobCfg.Remark"))
						hrJobCfg.setRemark(rs.getString("Remark"));
					else if(_fields[i].equals("Status") || _fields[i].equals("HrJobCfg.Status"))
						hrJobCfg.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("HrJobCfg.CreateBy"))
						hrJobCfg.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("HrJobCfg.CreateDate"))
						hrJobCfg.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("HrJobCfg.LastUpd"))
						hrJobCfg.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("HrJobCfg.LastUpdDate"))
						hrJobCfg.setLastUpdDate(rs.getTimestamp("LastUpdDate"));
					else if(_fields[i].equals("DeptNm") || _fields[i].equals("Dept.DeptNm"))
						hrJobCfg.setDeptNm(rs.getString("DeptNm"));
					else if(_fields[i].equals("ComNm") || _fields[i].equals("Com.ComNm"))
						hrJobCfg.setComNm(rs.getString("ComNm"));


				}
				list.add(hrJobCfg);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("HrJobCfgHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}