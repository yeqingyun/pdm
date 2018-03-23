package zrsy.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrsy.vo.Btn;

public class BtnHelper extends BasicBtnHelper {
	public String getSqlString() {
		return " from Btn " +

               " where 1=1 ";
	}

	public List<Btn> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<Btn> list = new ArrayList<Btn>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				Btn btn = new Btn();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Id") || _fields[i].equals("Btn.Id"))
						btn.setId(rs.getInt("Id"));
					if(_fields[i].equals("SyId") || _fields[i].equals("Btn.SyId"))
						btn.setSyId(rs.getInt("SyId"));
					if(_fields[i].equals("Line") || _fields[i].equals("Btn.Line"))
						btn.setLine(rs.getInt("Line"));
					if(_fields[i].equals("Disable") || _fields[i].equals("Btn.Disable"))
						btn.setDisable(rs.getInt("Disable"));
					if(_fields[i].equals("Sort") || _fields[i].equals("Btn.Sort"))
						btn.setSort(rs.getInt("Sort"));
					if(_fields[i].equals("Text") || _fields[i].equals("Btn.Text"))
						btn.setText(rs.getString("Text"));
					if(_fields[i].equals("Click") || _fields[i].equals("Btn.Click"))
						btn.setClick(rs.getString("Click"));
					if(_fields[i].equals("Icon") || _fields[i].equals("Btn.Icon"))
						btn.setIcon(rs.getString("Icon"));
					if(_fields[i].equals("Img") || _fields[i].equals("Btn.Img"))
						btn.setImg(rs.getString("Img"));


				}
				list.add(btn);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("BtnHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}