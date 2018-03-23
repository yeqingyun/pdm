package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WfCate;

public class WfCateHelper extends BasicWfCateHelper {
	public String getSqlString() {
		return " from WfCate " +

               " where 1=1 ";
	}

	public List<WfCate> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfCate> list = new ArrayList<WfCate>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfCate wfCate = new WfCate();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("CateId") || _fields[i].equals("WfCate.CateId"))
						wfCate.setCateId(rs.getInt("CateId"));
					else if(_fields[i].equals("CateParent") || _fields[i].equals("WfCate.CateParent"))
						wfCate.setCateParent(rs.getInt("CateParent"));
					else if(_fields[i].equals("CateLevel") || _fields[i].equals("WfCate.CateLevel"))
						wfCate.setCateLevel(rs.getInt("CateLevel"));
					else if(_fields[i].equals("Status") || _fields[i].equals("WfCate.Status"))
						wfCate.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfCate.CreateBy"))
						wfCate.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfCate.LastUpd"))
						wfCate.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfCate.CreateDate"))
						wfCate.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfCate.LastUpdDate"))
						wfCate.setLastUpdDate(rs.getTimestamp("LastUpdDate"));
					else if(_fields[i].equals("CateName") || _fields[i].equals("WfCate.CateName"))
						wfCate.setCateName(rs.getString("CateName"));

					if(_fields[i].equals("Sort") || _fields[i].equals("WfCate.Sort"))
						wfCate.setSort(rs.getInt("Sort"));

				}
				list.add(wfCate);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfCateHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}