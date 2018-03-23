package zrprjt.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrprjt.vo.SchCfg;
import zrprjt.vo.SchWf;

public class SchCfgHelper extends BasicSchCfgHelper {
	public String getSqlString() {
		return " from SchCfg " +
			   "left join PrjtTyp on (PrjtTyp.TypId = SchCfg.TypId) "+
			   "left join SchWf on (SchWf.SchId = SchCfg.SchId) "+
			   "left join WfCfg on (SchWf.FlowId = WfCfg.FlowId) "+
               " where 1=1 ";
	}
	
	public String getOrderBy() {
		return " order by SchCfg.SchNo";
	}
	

	public List<SchCfg> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<SchCfg> list = new ArrayList<SchCfg>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				SchCfg schCfg = new SchCfg();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SchId") || _fields[i].equals("SchCfg.SchId"))
						schCfg.setSchId(rs.getInt("SchId"));
					if(_fields[i].equals("Parent") || _fields[i].equals("SchCfg.Parent"))
						schCfg.setParent(rs.getInt("Parent"));
//					if(_fields[i].equals("FlowId") || _fields[i].equals("SchCfg.FlowId"))
//						schCfg.setFlowId(rs.getInt("FlowId"));
					if(_fields[i].equals("TypId") || _fields[i].equals("SchCfg.TypId"))
						schCfg.setTypId(rs.getInt("TypId"));
					if(_fields[i].equals("SchNo") || _fields[i].equals("SchCfg.SchNo"))
						schCfg.setSchNo(rs.getInt("SchNo"));
					if(_fields[i].equals("Leve") || _fields[i].equals("SchCfg.Leve"))
						schCfg.setLeve(rs.getInt("Leve"));
					if(_fields[i].equals("CfgTime") || _fields[i].equals("SchCfg.CfgTime"))
						schCfg.setCfgTime(rs.getInt("CfgTime"));
					if(_fields[i].equals("Status") || _fields[i].equals("SchCfg.Status"))
						schCfg.setStatus(rs.getInt("Status"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("SchCfg.CreateBy"))
						schCfg.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("LastUpd") || _fields[i].equals("SchCfg.LastUpd"))
						schCfg.setLastUpd(rs.getInt("LastUpd"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("SchCfg.CreateDate"))
						schCfg.setCreateDate(rs.getTimestamp("CreateDate"));
					if(_fields[i].equals("LastDate") || _fields[i].equals("SchCfg.LastDate"))
						schCfg.setLastDate(rs.getTimestamp("LastDate"));
					if(_fields[i].equals("SchNm") || _fields[i].equals("SchCfg.SchNm"))
						schCfg.setSchNm(rs.getString("SchNm"));
					if(_fields[i].equals("Remark") || _fields[i].equals("SchCfg.Remark"))
						schCfg.setRemark(rs.getString("Remark"));
					
					if(_fields[i].equals("prjtTypNm") || _fields[i].equals("PrjtTyp.TypNm as prjtTypNm"))
						schCfg.setPrjtTypNm(rs.getString("prjtTypNm"));
					
					if(_fields[i].equals("PredecessorLink") || _fields[i].equals("SchCfg.PredecessorLink"))
						schCfg.setPredecessorLink(rs.getString("PredecessorLink"));
					if(_fields[i].equals("Critical") || _fields[i].equals("SchCfg.Critical"))
						schCfg.setCritical(rs.getInt("Critical"));
					if(_fields[i].equals("Summary") || _fields[i].equals("SchCfg.Summary"))
						schCfg.setSummary(rs.getInt("Summary"));
					if(_fields[i].equals("Milestone") || _fields[i].equals("SchCfg.Milestone"))
						schCfg.setMilestone(rs.getInt("Milestone"));
					if(_fields[i].equals("FlowName") || _fields[i].equals("WfCfg.FlowName as FlowName"))
					{
						schCfg.setFlowName(rs.getString("FlowName"));
					}
					if(_fields[i].equals("FlowId") || _fields[i].equals("SchWf.FlowId as FlowId"))
					{
						SchWf schWf = new SchWf();
						schWf.setSchId(schCfg.getSchId());
						schWf.setFlowId(rs.getInt("FlowId"));
						schCfg.setSchWf(schWf);
					}
					
					if(_fields[i].equals("ManualStart") || _fields[i].equals("SchCfg.ManualStart"))
						schCfg.setManualStart(rs.getInt("ManualStart"));

				}
				list.add(schCfg);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SchCfgHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}