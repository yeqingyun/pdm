package zrsy.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrsy.vo.Node;

public class NodeHelper extends BasicNodeHelper {
	public String getSqlString() {
		return " from Node " +
               " inner join Menu on (Menu.Id = Node.MenuId) " + 
               " inner join SyDef on (SyDef.SyId = Node.SyId) " + 

               " where 1=1 ";
	}

	public List<Node> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<Node> list = new ArrayList<Node>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				Node node = new Node();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Id") || _fields[i].equals("Node.Id"))
						node.setId(rs.getInt("Id"));
					if(_fields[i].equals("SyId") || _fields[i].equals("Node.SyId"))
						node.setSyId(rs.getInt("SyId"));
					if(_fields[i].equals("Parent") || _fields[i].equals("Node.Parent"))
						node.setParent(rs.getInt("Parent"));
					if(_fields[i].equals("MenuId") || _fields[i].equals("Node.MenuId"))
						node.setMenuId(rs.getInt("MenuId"));
					if(_fields[i].equals("NodeWidth") || _fields[i].equals("Node.NodeWidth"))
						node.setNodeWidth(rs.getInt("NodeWidth"));
					if(_fields[i].equals("CheckBox") || _fields[i].equals("Node.CheckBox"))
						node.setCheckBox(rs.getInt("CheckBox"));
					if(_fields[i].equals("Leve") || _fields[i].equals("Node.Leve"))
						node.setLeve(rs.getInt("Leve"));
					if(_fields[i].equals("Sort") || _fields[i].equals("Node.Sort"))
						node.setSort(rs.getInt("Sort"));
					if(_fields[i].equals("Text") || _fields[i].equals("Node.Text"))
						node.setText(rs.getString("Text"));
					if(_fields[i].equals("Url") || _fields[i].equals("Node.Url"))
						node.setUrl(rs.getString("Url"));

					if(_fields[i].equals("MenuNm") || _fields[i].equals("Menu.Text as MenuNm"))
						node.setMenuNm(rs.getString("MenuNm"));
					if(_fields[i].equals("SyName") || _fields[i].equals("SyDef.SyName as SyName"))
						node.setSyName(rs.getString("SyName"));

				}
				list.add(node);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("NodeHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}