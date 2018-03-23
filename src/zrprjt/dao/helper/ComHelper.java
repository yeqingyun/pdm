package zrprjt.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrprjt.vo.Com;

public class ComHelper extends BasicComHelper {
	public String getSqlString() {
		return " from Com " +

               " where 1=1 ";
	}

	public List<Com> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<Com> list = new ArrayList<Com>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				Com com = new Com();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ComId") || _fields[i].equals("Com.ComId"))
						com.setComId(rs.getInt("ComId"));
					if(_fields[i].equals("Parent") || _fields[i].equals("Com.Parent"))
						com.setParent(rs.getInt("Parent"));
					if(_fields[i].equals("Leve") || _fields[i].equals("Com.Leve"))
						com.setLeve(rs.getInt("Leve"));
					if(_fields[i].equals("Status") || _fields[i].equals("Com.Status"))
						com.setStatus(rs.getInt("Status"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("Com.CreateBy"))
						com.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("LastUpd") || _fields[i].equals("Com.LastUpd"))
						com.setLastUpd(rs.getInt("LastUpd"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("Com.CreateDate"))
						com.setCreateDate(rs.getTimestamp("CreateDate"));
					if(_fields[i].equals("LastDate") || _fields[i].equals("Com.LastDate"))
						com.setLastDate(rs.getTimestamp("LastDate"));
					if(_fields[i].equals("ComNo") || _fields[i].equals("Com.ComNo"))
						com.setComNo(rs.getString("ComNo"));
					if(_fields[i].equals("ComNm") || _fields[i].equals("Com.ComNm"))
						com.setComNm(rs.getString("ComNm"));
					if(_fields[i].equals("Remark") || _fields[i].equals("Com.Remark"))
						com.setRemark(rs.getString("Remark"));


				}
				list.add(com);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ComHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}