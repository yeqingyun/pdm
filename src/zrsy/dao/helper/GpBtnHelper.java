package zrsy.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrsy.vo.GpBtn;

public class GpBtnHelper extends BasicGpBtnHelper {
	public String getSqlString() {
		return " from GpBtn " +
               " inner join Btn on (Btn.Id = GpBtn.BtnId) " + 
               " inner join Gp on (Gp.GpId = GpBtn.GpId) " + 

               " where 1=1 ";
	}

	public List<GpBtn> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<GpBtn> list = new ArrayList<GpBtn>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				GpBtn gpBtn = new GpBtn();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("GpId") || _fields[i].equals("GpBtn.GpId"))
						gpBtn.setGpId(rs.getInt("GpId"));
					if(_fields[i].equals("NodeId") || _fields[i].equals("GpBtn.NodeId"))
						gpBtn.setNodeId(rs.getInt("NodeId"));
					if(_fields[i].equals("BtnId") || _fields[i].equals("GpBtn.BtnId"))
						gpBtn.setBtnId(rs.getInt("BtnId"));

					if(_fields[i].equals("BtnNm") || _fields[i].equals("Btn.Text as BtnNm"))
						gpBtn.setBtnNm(rs.getString("BtnNm"));
					if(_fields[i].equals("GpName") || _fields[i].equals("Gp.GpName as GpName"))
						gpBtn.setGpName(rs.getString("GpName"));

				}
				list.add(gpBtn);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpBtnHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}