package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WFMatlCategory;

public class WFMatlCategoryHelper extends BasicWFMatlCategoryHelper {
	public String getSqlString() {
		return " from WFMatlCategory " +

               " where 1=1 ";
	}

	public List<WFMatlCategory> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WFMatlCategory> list = new ArrayList<WFMatlCategory>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WFMatlCategory wFMatlCategory = new WFMatlCategory();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("CategoryId") || _fields[i].equals("WFMatlCategory.CategoryId"))
						wFMatlCategory.setCategoryId(rs.getInt("CategoryId"));
					else if(_fields[i].equals("Status") || _fields[i].equals("WFMatlCategory.Status"))
						wFMatlCategory.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("Desc2") || _fields[i].equals("WFMatlCategory.Desc2"))
						wFMatlCategory.setDesc2(rs.getString("Desc2"));
					else if(_fields[i].equals("CategoryNo") || _fields[i].equals("WFMatlCategory.CategoryNo"))
						wFMatlCategory.setCategoryNo(rs.getString("CategoryNo"));
					else if(_fields[i].equals("Desc3") || _fields[i].equals("WFMatlCategory.Desc3"))
						wFMatlCategory.setDesc3(rs.getString("Desc3"));
					else if(_fields[i].equals("CategoryName") || _fields[i].equals("WFMatlCategory.CategoryName"))
						wFMatlCategory.setCategoryName(rs.getString("CategoryName"));
					else if(_fields[i].equals("Desc1") || _fields[i].equals("WFMatlCategory.Desc1"))
						wFMatlCategory.setDesc1(rs.getString("Desc1"));


				}
				list.add(wFMatlCategory);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WFMatlCategoryHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}