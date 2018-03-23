package zrsy.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrsy.vo.BillSubs;

public class BillSubsHelper extends BasicBillSubsHelper {
	public String getSqlString() {
		return " from BillSubs " +
               " inner join SyDef on (SyDef.SyId = BillSubs.SyId) " + 

               " where 1=1 ";
	}

	public List<BillSubs> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<BillSubs> list = new ArrayList<BillSubs>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				BillSubs billSubs = new BillSubs();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Id") || _fields[i].equals("BillSubs.Id"))
						billSubs.setId(rs.getInt("Id"));
					else if(_fields[i].equals("SyId") || _fields[i].equals("BillSubs.SyId"))
						billSubs.setSyId(rs.getInt("SyId"));
					else if(_fields[i].equals("Subs") || _fields[i].equals("BillSubs.Subs"))
						billSubs.setSubs(rs.getString("Subs"));
					else if(_fields[i].equals("CurrSn") || _fields[i].equals("BillSubs.CurrSn"))
						billSubs.setCurrSn(rs.getString("CurrSn"));
					else if(_fields[i].equals("State") || _fields[i].equals("BillSubs.State"))
						billSubs.setState(rs.getInt("State"));

					else if(_fields[i].equals("SyId") || _fields[i].equals("SyDef.SyId as SyId"))
						billSubs.setSyId(rs.getInt("SyId"));

				}
				list.add(billSubs);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("BillSubsHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}