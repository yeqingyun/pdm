package zrprjt.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrprjt.vo.Dept;

public class DeptHelper extends BasicDeptHelper {
	public String getSqlString() {
		return " from Dept " +

               " where 1=1 ";
	}

	public List<Dept> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<Dept> list = new ArrayList<Dept>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				Dept dept = new Dept();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("DeptId") || _fields[i].equals("Dept.DeptId"))
						dept.setDeptId(rs.getInt("DeptId"));
					if(_fields[i].equals("ComId") || _fields[i].equals("Dept.ComId"))
						dept.setComId(rs.getInt("ComId"));
					if(_fields[i].equals("Parent") || _fields[i].equals("Dept.Parent"))
						dept.setParent(rs.getInt("Parent"));
					if(_fields[i].equals("Leve") || _fields[i].equals("Dept.Leve"))
						dept.setLeve(rs.getInt("Leve"));
					if(_fields[i].equals("Status") || _fields[i].equals("Dept.Status"))
						dept.setStatus(rs.getInt("Status"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("Dept.CreateBy"))
						dept.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("LastUpd") || _fields[i].equals("Dept.LastUpd"))
						dept.setLastUpd(rs.getInt("LastUpd"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("Dept.CreateDate"))
						dept.setCreateDate(rs.getTimestamp("CreateDate"));
					if(_fields[i].equals("LastDate") || _fields[i].equals("Dept.LastDate"))
						dept.setLastDate(rs.getTimestamp("LastDate"));
					if(_fields[i].equals("DeptNo") || _fields[i].equals("Dept.DeptNo"))
						dept.setDeptNo(rs.getString("DeptNo"));
					if(_fields[i].equals("DeptNm") || _fields[i].equals("Dept.DeptNm"))
						dept.setDeptNm(rs.getString("DeptNm"));
					if(_fields[i].equals("Remark") || _fields[i].equals("Dept.Remark"))
						dept.setRemark(rs.getString("Remark"));


				}
				list.add(dept);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DeptHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}