package zrsy.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrsy.vo.Node;

public class BasicNodeHelper implements SqlHelper {
	public String getSqlString() {
		return " from Node where 1=1";
	}

	public String getOrderBy() {
		return " order by Node.Id";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((Node)object);
	}

	public String getSql4Amount(Node node) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(node);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((Node)object);
	}

	public String getSql4Delete(Node node) {
		return "delete from Node where 1=1"+getSqlCondition(node);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((Node)object,fields);
	}

	public String getSql4All(Node node, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(node)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((Node)object,pageVO,fields);
	}

	public String getSql4Pages(Node node,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" Node.Id "+ getSqlString()+getSqlCondition(node)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(node)+" and Node.Id not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((Node)object);
	}

	public String getSqlCondition(Node node) {
		String sql = "";
		if(null != node.getId())
			sql += " and Node.Id = '"+node.getId()+"'";
		if(null != node.getSyId())
			sql += " and Node.SyId = '"+node.getSyId()+"'";
		if(null != node.getParent())
			sql += " and Node.Parent = '"+node.getParent()+"'";
		if(null != node.getMenuId())
			sql += " and Node.MenuId = '"+node.getMenuId()+"'";
		if(null != node.getNodeWidth())
			sql += " and Node.NodeWidth = '"+node.getNodeWidth()+"'";
		if(null != node.getCheckBox())
			sql += " and Node.CheckBox = '"+node.getCheckBox()+"'";
		if(null != node.getLeve())
			sql += " and Node.Leve = '"+node.getLeve()+"'";
		if(null != node.getSort())
			sql += " and Node.Sort = '"+node.getSort()+"'";
		if(null != node.getText() && !node.getText().trim().equals(""))
			sql += " and Node.Text = '"+node.getText().trim()+"'";
		if(null != node.getUrl() && !node.getUrl().trim().equals(""))
			sql += " and Node.Url = '"+node.getUrl().trim()+"'";

		return sql;
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

	public String getInsertSql(String fields) {
		String sql = "insert into Node("+fields.replaceAll("Node\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(Node node,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Id") || _fields[i].equals("Node.Id")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, node.getId());
					}
					else if(_fields[i].equals("SyId") || _fields[i].equals("Node.SyId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, node.getSyId());
					}
					else if(_fields[i].equals("Parent") || _fields[i].equals("Node.Parent")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, node.getParent());
					}
					else if(_fields[i].equals("MenuId") || _fields[i].equals("Node.MenuId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, node.getMenuId());
					}
					else if(_fields[i].equals("NodeWidth") || _fields[i].equals("Node.NodeWidth")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, node.getNodeWidth());
					}
					else if(_fields[i].equals("CheckBox") || _fields[i].equals("Node.CheckBox")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, node.getCheckBox());
					}
					else if(_fields[i].equals("Leve") || _fields[i].equals("Node.Leve")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, node.getLeve());
					}
					else if(_fields[i].equals("Sort") || _fields[i].equals("Node.Sort")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, node.getSort());
					}
					else if(_fields[i].equals("Text") || _fields[i].equals("Node.Text")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, node.getText());
					}
					else if(_fields[i].equals("Url") || _fields[i].equals("Node.Url")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, node.getUrl());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("NodeHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update Node set ";
		String[] _fields = fields.replaceAll("Node\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SyId") || _fields[i].equals("Node.SyId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Parent") || _fields[i].equals("Node.Parent"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("MenuId") || _fields[i].equals("Node.MenuId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("NodeWidth") || _fields[i].equals("Node.NodeWidth"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CheckBox") || _fields[i].equals("Node.CheckBox"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Leve") || _fields[i].equals("Node.Leve"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Sort") || _fields[i].equals("Node.Sort"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Text") || _fields[i].equals("Node.Text"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Url") || _fields[i].equals("Node.Url"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(Node node,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SyId") || _fields[i].equals("Node.SyId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, node.getSyId());
					}
					else if(_fields[i].equals("Parent") || _fields[i].equals("Node.Parent")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, node.getParent());
					}
					else if(_fields[i].equals("MenuId") || _fields[i].equals("Node.MenuId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, node.getMenuId());
					}
					else if(_fields[i].equals("NodeWidth") || _fields[i].equals("Node.NodeWidth")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, node.getNodeWidth());
					}
					else if(_fields[i].equals("CheckBox") || _fields[i].equals("Node.CheckBox")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, node.getCheckBox());
					}
					else if(_fields[i].equals("Leve") || _fields[i].equals("Node.Leve")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, node.getLeve());
					}
					else if(_fields[i].equals("Sort") || _fields[i].equals("Node.Sort")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, node.getSort());
					}
					else if(_fields[i].equals("Text") || _fields[i].equals("Node.Text")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, node.getText());
					}
					else if(_fields[i].equals("Url") || _fields[i].equals("Node.Url")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, node.getUrl());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("NodeHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(Node node) {
		String _fields = "";
		if(null != node.getId())
			_fields += "Node.Id,";
		if(null != node.getSyId())
			_fields += "Node.SyId,";
		if(null != node.getParent())
			_fields += "Node.Parent,";
		if(null != node.getMenuId())
			_fields += "Node.MenuId,";
		if(null != node.getNodeWidth())
			_fields += "Node.NodeWidth,";
		if(null != node.getCheckBox())
			_fields += "Node.CheckBox,";
		if(null != node.getLeve())
			_fields += "Node.Leve,";
		if(null != node.getSort())
			_fields += "Node.Sort,";
		if(null != node.getText())
			_fields += "Node.Text,";
		if(null != node.getUrl())
			_fields += "Node.Url,";

		return _fields.substring(0, _fields.length()-1);
	}
}