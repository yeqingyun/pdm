package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WfCfg;

public class WfCfgHelper extends BasicWfCfgHelper {
	public String getSqlString() {
		return " from WfCfg " +
               " left join WfCate on (WfCate.CateId = WfCfg.CateId) " + 
               " left join Com on(Com.ComId = WfCfg.ComId) " +
               " left join Dept on(Dept.DeptId = WfCfg.DeptId) " +
               " where 1=1 ";
	}

	public List<WfCfg> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfCfg> list = new ArrayList<WfCfg>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfCfg wfCfg = new WfCfg();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("FlowId") || _fields[i].equals("WfCfg.FlowId"))
						wfCfg.setFlowId(rs.getInt("FlowId"));
					else if(_fields[i].equals("ComId") || _fields[i].equals("WfCfg.ComId"))
						wfCfg.setComId(rs.getInt("ComId"));
					else if(_fields[i].equals("DeptId") || _fields[i].equals("WfCfg.DeptId"))
						wfCfg.setDeptId(rs.getInt("DeptId"));
					else if(_fields[i].equals("CateId") || _fields[i].equals("WfCfg.CateId"))
						wfCfg.setCateId(rs.getInt("CateId"));
					else if(_fields[i].equals("IsFirstStep") || _fields[i].equals("WfCfg.IsFirstStep"))
						wfCfg.setIsFirstStep(rs.getInt("IsFirstStep"));
					else if(_fields[i].equals("Sphere") || _fields[i].equals("WfCfg.Sphere"))
						wfCfg.setSphere(rs.getInt("Sphere"));
					else if(_fields[i].equals("RelateId") || _fields[i].equals("WfCfg.RelateId"))
						wfCfg.setRelateId(rs.getInt("RelateId"));
					else if(_fields[i].equals("Limit") || _fields[i].equals("WfCfg.Limit"))
						wfCfg.setLimit(rs.getInt("Limit"));
					else if(_fields[i].equals("Status") || _fields[i].equals("WfCfg.Status"))
						wfCfg.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfCfg.CreateBy"))
						wfCfg.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfCfg.LastUpd"))
						wfCfg.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("HasQuesMoudle") || _fields[i].equals("WfCfg.HasQuesMoudle"))
						wfCfg.setHasQuesMoudle(rs.getInt("HasQuesMoudle"));
					else if(_fields[i].equals("ScheCfgId") || _fields[i].equals("WfCfg.ScheCfgId"))
						wfCfg.setScheCfgId(rs.getInt("ScheCfgId"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfCfg.CreateDate"))
						wfCfg.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfCfg.LastUpdDate"))
						wfCfg.setLastUpdDate(rs.getTimestamp("LastUpdDate"));
					else if(_fields[i].equals("FlowDesc") || _fields[i].equals("WfCfg.FlowDesc"))
						wfCfg.setFlowDesc(rs.getString("FlowDesc"));
					else if(_fields[i].equals("FlowName") || _fields[i].equals("WfCfg.FlowName"))
						wfCfg.setFlowName(rs.getString("FlowName"));
					else if(_fields[i].equals("FlowUri") || _fields[i].equals("WfCfg.FlowUri"))
						wfCfg.setFlowUri(rs.getString("FlowUri"));
					else if(_fields[i].equals("PrintUri") || _fields[i].equals("WfCfg.PrintUri"))
						wfCfg.setPrintUri(rs.getString("PrintUri"));
					else if(_fields[i].equals("SpecialClass") || _fields[i].equals("WfCfg.SpecialClass"))
						wfCfg.setSpecialClass(rs.getString("SpecialClass"));
					else if(_fields[i].equals("AddRdExtendUri") || _fields[i].equals("WfCfg.AddRdExtendUri"))
						wfCfg.setAddRdExtendUri(rs.getString("AddRdExtendUri"));
					else if(_fields[i].equals("FlowCode") || _fields[i].equals("WfCfg.FlowCode"))
						wfCfg.setFlowCode(rs.getString("FlowCode"));

					else if(_fields[i].equals("CateName") || _fields[i].equals("WfCate.CateName as CateName"))
						wfCfg.setCateName(rs.getString("CateName"));
					else if(_fields[i].equals("ComNm") || _fields[i].equals("Com.ComNm"))
						wfCfg.setComName(rs.getString("ComNm"));
					else if(_fields[i].equals("DeptNm") || _fields[i].equals("Dept.DeptNm"))
						wfCfg.setDeptName(rs.getString("DeptNm"));
					
					else if(_fields[i].equals("NeedQues") || _fields[i].equals("WfCfg.NeedQues"))
						wfCfg.setNeedQues(rs.getInt("NeedQues"));
					

				}
				list.add(wfCfg);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfCfgHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}