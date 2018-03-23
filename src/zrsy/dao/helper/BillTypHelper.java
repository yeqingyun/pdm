package zrsy.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrsy.vo.BillTyp;

public class BillTypHelper extends BasicBillTypHelper {
	public String getSqlString() {
		return " from BillTyp " +
               " inner join BillSubs on (BillSubs.Id = BillTyp.SubsId) " + 

               " where 1=1 ";
	}

	public List<BillTyp> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<BillTyp> list = new ArrayList<BillTyp>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				BillTyp billTyp = new BillTyp();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Id") || _fields[i].equals("BillTyp.Id"))
						billTyp.setId(rs.getInt("Id"));
					else if(_fields[i].equals("SubsId") || _fields[i].equals("BillTyp.SubsId"))
						billTyp.setSubsId(rs.getInt("SubsId"));
					else if(_fields[i].equals("TypNm") || _fields[i].equals("BillTyp.TypNm"))
						billTyp.setTypNm(rs.getString("TypNm"));
					else if(_fields[i].equals("Code") || _fields[i].equals("BillTyp.Code"))
						billTyp.setCode(rs.getString("Code"));

					else if(_fields[i].equals("Id") || _fields[i].equals("BillSubs.Id as Id"))
						billTyp.setId(rs.getInt("Id"));

				}
				list.add(billTyp);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("BillTypHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}