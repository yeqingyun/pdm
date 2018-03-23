package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WfMatlPro;

public class WfMatlProHelper extends BasicWfMatlProHelper {
	public String getSqlString() {
		return " from WfMatlPro " +

               " where 1=1 ";
	}

	public List<WfMatlPro> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfMatlPro> list = new ArrayList<WfMatlPro>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfMatlPro wfMatlPro = new WfMatlPro();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("MatlProId") || _fields[i].equals("WfMatlPro.MatlProId"))
						wfMatlPro.setMatlProId(rs.getInt("MatlProId"));
					else if(_fields[i].equals("IsPurPass") || _fields[i].equals("WfMatlPro.IsPurPass"))
						wfMatlPro.setIsPurPass(rs.getInt("IsPurPass"));
					else if(_fields[i].equals("IsMatPass") || _fields[i].equals("WfMatlPro.IsMatPass"))
						wfMatlPro.setIsMatPass(rs.getInt("IsMatPass"));
					else if(_fields[i].equals("IsManagerPass") || _fields[i].equals("WfMatlPro.IsManagerPass"))
						wfMatlPro.setIsManagerPass(rs.getInt("IsManagerPass"));
					else if(_fields[i].equals("PurRevDate") || _fields[i].equals("WfMatlPro.PurRevDate"))
						wfMatlPro.setPurRevDate(rs.getTimestamp("PurRevDate"));
					else if(_fields[i].equals("MatRevDate") || _fields[i].equals("WfMatlPro.MatRevDate"))
						wfMatlPro.setMatRevDate(rs.getTimestamp("MatRevDate"));
					else if(_fields[i].equals("ManagerRevDate") || _fields[i].equals("WfMatlPro.ManagerRevDate"))
						wfMatlPro.setManagerRevDate(rs.getTimestamp("ManagerRevDate"));
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfMatlPro.WfNo"))
						wfMatlPro.setWfNo(rs.getString("WfNo"));
					else if(_fields[i].equals("ProName") || _fields[i].equals("WfMatlPro.ProName"))
						wfMatlPro.setProName(rs.getString("ProName"));
					else if(_fields[i].equals("DesignName") || _fields[i].equals("WfMatlPro.DesignName"))
						wfMatlPro.setDesignName(rs.getString("DesignName"));
					else if(_fields[i].equals("ManageName") || _fields[i].equals("WfMatlPro.ManageName"))
						wfMatlPro.setManageName(rs.getString("ManageName"));
					else if(_fields[i].equals("ProDesc") || _fields[i].equals("WfMatlPro.ProDesc"))
						wfMatlPro.setProDesc(rs.getString("ProDesc"));
					else if(_fields[i].equals("CurStep") || _fields[i].equals("WfMatlPro.CurStep"))
						wfMatlPro.setCurStep(rs.getString("CurStep"));
					else if(_fields[i].equals("ManagerReview") || _fields[i].equals("WfMatlPro.ManagerReview"))
						wfMatlPro.setManagerReview(rs.getString("ManagerReview"));
					else if(_fields[i].equals("ManagerRemark") || _fields[i].equals("WfMatlPro.ManagerRemark"))
						wfMatlPro.setManagerRemark(rs.getString("ManagerRemark"));
					else if(_fields[i].equals("CurVersion") || _fields[i].equals("WfMatlPro.CurVersion"))
						wfMatlPro.setCurVersion(rs.getString("CurVersion"));
					else if(_fields[i].equals("ProCost") || _fields[i].equals("WfMatlPro.ProCost"))
						wfMatlPro.setProCost(rs.getString("ProCost"));
					else if(_fields[i].equals("PurReview") || _fields[i].equals("WfMatlPro.PurReview"))
						wfMatlPro.setPurReview(rs.getString("PurReview"));
					else if(_fields[i].equals("PurRemark") || _fields[i].equals("WfMatlPro.PurRemark"))
						wfMatlPro.setPurRemark(rs.getString("PurRemark"));
					else if(_fields[i].equals("MatReveiw") || _fields[i].equals("WfMatlPro.MatReveiw"))
						wfMatlPro.setMatReveiw(rs.getString("MatReveiw"));
					else if(_fields[i].equals("MatRemark") || _fields[i].equals("WfMatlPro.MatRemark"))
						wfMatlPro.setMatRemark(rs.getString("MatRemark"));


				}
				list.add(wfMatlPro);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfMatlProHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}