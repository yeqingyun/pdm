package zrsy.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrsy.vo.GpNode;

public class BasicGpNodeHelper implements SqlHelper {
	public String getSqlString() {
		return " from GpNode where 1=1";
	}

	public String getOrderBy() {
		return " order by GpNode.GpId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((GpNode)object);
	}

	public String getSql4Amount(GpNode gpNode) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(gpNode);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((GpNode)object);
	}

	public String getSql4Delete(GpNode gpNode) {
		return "delete from GpNode where 1=1"+getSqlCondition(gpNode);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((GpNode)object,fields);
	}

	public String getSql4All(GpNode gpNode, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(gpNode)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((GpNode)object,pageVO,fields);
	}

	public String getSql4Pages(GpNode gpNode,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" GpNode.GpId "+ getSqlString()+getSqlCondition(gpNode)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(gpNode)+" and GpNode.GpId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((GpNode)object);
	}

	public String getSqlCondition(GpNode gpNode) {
		String sql = "";
		if(null != gpNode.getGpId())
			sql += " and GpNode.GpId = '"+gpNode.getGpId()+"'";
		if(null != gpNode.getNodeId())
			sql += " and GpNode.NodeId = '"+gpNode.getNodeId()+"'";

		return sql;
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

	public String getInsertSql(String fields) {
		String sql = "insert into GpNode("+fields.replaceAll("GpNode\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(GpNode gpNode,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("GpId") || _fields[i].equals("GpNode.GpId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, gpNode.getGpId());
					}
					else if(_fields[i].equals("NodeId") || _fields[i].equals("GpNode.NodeId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, gpNode.getNodeId());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("GpNodeHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update GpNode set ";
		String[] _fields = fields.replaceAll("GpNode\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(GpNode gpNode,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("GpNodeHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(GpNode gpNode) {
		String _fields = "";
		if(null != gpNode.getGpId())
			_fields += "GpNode.GpId,";
		if(null != gpNode.getNodeId())
			_fields += "GpNode.NodeId,";

		return _fields.substring(0, _fields.length()-1);
	}
}