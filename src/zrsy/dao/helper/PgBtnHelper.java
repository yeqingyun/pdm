package zrsy.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrsy.vo.PgBtn;

public class PgBtnHelper extends BasicPgBtnHelper {
	public String getSqlString() {
		return " from PgBtn " +

               " where 1=1 ";
	}

	public List<PgBtn> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<PgBtn> list = new ArrayList<PgBtn>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				PgBtn pgBtn = new PgBtn();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("NodeId") || _fields[i].equals("PgBtn.NodeId"))
						pgBtn.setNodeId(rs.getInt("NodeId"));
					else if(_fields[i].equals("BtnId") || _fields[i].equals("PgBtn.BtnId"))
						pgBtn.setBtnId(rs.getInt("BtnId"));
					else if(_fields[i].equals("PgTyp") || _fields[i].equals("PgBtn.PgTyp"))
						pgBtn.setPgTyp(rs.getInt("PgTyp"));


				}
				list.add(pgBtn);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PgBtnHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}