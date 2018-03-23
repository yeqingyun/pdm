package zrsy.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrsy.vo.GpNode;

public class GpNodeHelper extends BasicGpNodeHelper {
	public String getSqlString() {
		return " from GpNode " +
               " inner join Gp on (Gp.GpId = GpNode.GpId) " + 
               " inner join Node on (Node.Id = GpNode.NodeId) " + 

               " where 1=1 ";
	}

	public List<GpNode> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<GpNode> list = new ArrayList<GpNode>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				GpNode gpNode = new GpNode();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("GpId") || _fields[i].equals("GpNode.GpId"))
						gpNode.setGpId(rs.getInt("GpId"));
					if(_fields[i].equals("NodeId") || _fields[i].equals("GpNode.NodeId"))
						gpNode.setNodeId(rs.getInt("NodeId"));

					if(_fields[i].equals("GpName") || _fields[i].equals("Gp.GpName as GpName"))
						gpNode.setGpName(rs.getString("GpName"));
					if(_fields[i].equals("NodeNm") || _fields[i].equals("Node.Text as NodeNm"))
						gpNode.setNodeNm(rs.getString("NodeNm"));

				}
				list.add(gpNode);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpNodeHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}