package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WfItem;

public class WfItemHelper extends BasicWfItemHelper {
	public String getSqlString() {
		return " from WfItem " +

               " where 1=1 ";
	}

	public List<WfItem> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfItem> list = new ArrayList<WfItem>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfItem wfItem = new WfItem();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ItemId") || _fields[i].equals("WfItem.ItemId"))
						wfItem.setItemId(rs.getInt("ItemId"));
					else if(_fields[i].equals("Status") || _fields[i].equals("WfItem.Status"))
						wfItem.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("FlowId") || _fields[i].equals("WfItem.FlowId"))
						wfItem.setFlowId(rs.getInt("FlowId"));
					else if(_fields[i].equals("OrderBy") || _fields[i].equals("WfItem.OrderBy"))
						wfItem.setOrderBy(rs.getString("OrderBy"));
					else if(_fields[i].equals("ItemNo") || _fields[i].equals("WfItem.ItemNo"))
						wfItem.setItemNo(rs.getString("ItemNo"));
					else if(_fields[i].equals("Desc2") || _fields[i].equals("WfItem.Desc2"))
						wfItem.setDesc2(rs.getString("Desc2"));
					else if(_fields[i].equals("Desc5") || _fields[i].equals("WfItem.Desc5"))
						wfItem.setDesc5(rs.getString("Desc5"));
					else if(_fields[i].equals("ItemName") || _fields[i].equals("WfItem.ItemName"))
						wfItem.setItemName(rs.getString("ItemName"));
					else if(_fields[i].equals("Desc3") || _fields[i].equals("WfItem.Desc3"))
						wfItem.setDesc3(rs.getString("Desc3"));
					else if(_fields[i].equals("Desc4") || _fields[i].equals("WfItem.Desc4"))
						wfItem.setDesc4(rs.getString("Desc4"));
					else if(_fields[i].equals("Desc1") || _fields[i].equals("WfItem.Desc1"))
						wfItem.setDesc1(rs.getString("Desc1"));


				}
				list.add(wfItem);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfItemHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}