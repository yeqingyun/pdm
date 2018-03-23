package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfItem;

public class BasicWfItemHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfItem where 1=1";
	}

	public String getOrderBy() {
		return " order by WfItem.ItemId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfItem)object);
	}

	public String getSql4Amount(WfItem wfItem) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfItem);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfItem)object);
	}

	public String getSql4Delete(WfItem wfItem) {
		return "delete from WfItem where 1=1"+getSqlCondition(wfItem);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfItem)object,fields);
	}

	public String getSql4All(WfItem wfItem, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfItem)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfItem)object,pageVO,fields);
	}

	public String getSql4Pages(WfItem wfItem,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfItem.ItemId "+ getSqlString()+getSqlCondition(wfItem)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfItem)+" and WfItem.ItemId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfItem)object);
	}

	public String getSqlCondition(WfItem wfItem) {
		String sql = "";
		if(null != wfItem.getItemId())
			sql += " and WfItem.ItemId = '"+wfItem.getItemId()+"'";
		if(null != wfItem.getStatus())
			sql += " and WfItem.Status = '"+wfItem.getStatus()+"'";
		if(null != wfItem.getFlowId())
			sql += " and WfItem.FlowId = '"+wfItem.getFlowId()+"'";
		if(null != wfItem.getOrderBy() && !wfItem.getOrderBy().trim().equals(""))
			sql += " and WfItem.OrderBy = '"+wfItem.getOrderBy().trim()+"'";
		if(null != wfItem.getItemNo() && !wfItem.getItemNo().trim().equals(""))
			sql += " and WfItem.ItemNo = '"+wfItem.getItemNo().trim()+"'";
		if(null != wfItem.getDesc2() && !wfItem.getDesc2().trim().equals(""))
			sql += " and WfItem.Desc2 = '"+wfItem.getDesc2().trim()+"'";
		if(null != wfItem.getDesc5() && !wfItem.getDesc5().trim().equals(""))
			sql += " and WfItem.Desc5 = '"+wfItem.getDesc5().trim()+"'";
		if(null != wfItem.getItemName() && !wfItem.getItemName().trim().equals(""))
			sql += " and WfItem.ItemName = '"+wfItem.getItemName().trim()+"'";
		if(null != wfItem.getDesc3() && !wfItem.getDesc3().trim().equals(""))
			sql += " and WfItem.Desc3 = '"+wfItem.getDesc3().trim()+"'";
		if(null != wfItem.getDesc4() && !wfItem.getDesc4().trim().equals(""))
			sql += " and WfItem.Desc4 = '"+wfItem.getDesc4().trim()+"'";
		if(null != wfItem.getDesc1() && !wfItem.getDesc1().trim().equals(""))
			sql += " and WfItem.Desc1 = '"+wfItem.getDesc1().trim()+"'";

		return sql;
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

	public String getInsertSql(String fields) {
		String sql = "insert into WfItem("+fields.replaceAll("WfItem\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfItem wfItem,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ItemId") || _fields[i].equals("WfItem.ItemId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfItem.getItemId());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("WfItem.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfItem.getStatus());
					}
					else if(_fields[i].equals("FlowId") || _fields[i].equals("WfItem.FlowId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfItem.getFlowId());
					}
					else if(_fields[i].equals("OrderBy") || _fields[i].equals("WfItem.OrderBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfItem.getOrderBy());
					}
					else if(_fields[i].equals("ItemNo") || _fields[i].equals("WfItem.ItemNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfItem.getItemNo());
					}
					else if(_fields[i].equals("Desc2") || _fields[i].equals("WfItem.Desc2")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfItem.getDesc2());
					}
					else if(_fields[i].equals("Desc5") || _fields[i].equals("WfItem.Desc5")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfItem.getDesc5());
					}
					else if(_fields[i].equals("ItemName") || _fields[i].equals("WfItem.ItemName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfItem.getItemName());
					}
					else if(_fields[i].equals("Desc3") || _fields[i].equals("WfItem.Desc3")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfItem.getDesc3());
					}
					else if(_fields[i].equals("Desc4") || _fields[i].equals("WfItem.Desc4")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfItem.getDesc4());
					}
					else if(_fields[i].equals("Desc1") || _fields[i].equals("WfItem.Desc1")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfItem.getDesc1());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfItemHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfItem set ";
		String[] _fields = fields.replaceAll("WfItem\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Status") || _fields[i].equals("WfItem.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("FlowId") || _fields[i].equals("WfItem.FlowId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("OrderBy") || _fields[i].equals("WfItem.OrderBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("ItemNo") || _fields[i].equals("WfItem.ItemNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Desc2") || _fields[i].equals("WfItem.Desc2"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Desc5") || _fields[i].equals("WfItem.Desc5"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("ItemName") || _fields[i].equals("WfItem.ItemName"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Desc3") || _fields[i].equals("WfItem.Desc3"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Desc4") || _fields[i].equals("WfItem.Desc4"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Desc1") || _fields[i].equals("WfItem.Desc1"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfItem wfItem,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Status") || _fields[i].equals("WfItem.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfItem.getStatus());
					}
					else if(_fields[i].equals("FlowId") || _fields[i].equals("WfItem.FlowId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfItem.getFlowId());
					}
					else if(_fields[i].equals("OrderBy") || _fields[i].equals("WfItem.OrderBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfItem.getOrderBy());
					}
					else if(_fields[i].equals("ItemNo") || _fields[i].equals("WfItem.ItemNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfItem.getItemNo());
					}
					else if(_fields[i].equals("Desc2") || _fields[i].equals("WfItem.Desc2")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfItem.getDesc2());
					}
					else if(_fields[i].equals("Desc5") || _fields[i].equals("WfItem.Desc5")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfItem.getDesc5());
					}
					else if(_fields[i].equals("ItemName") || _fields[i].equals("WfItem.ItemName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfItem.getItemName());
					}
					else if(_fields[i].equals("Desc3") || _fields[i].equals("WfItem.Desc3")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfItem.getDesc3());
					}
					else if(_fields[i].equals("Desc4") || _fields[i].equals("WfItem.Desc4")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfItem.getDesc4());
					}
					else if(_fields[i].equals("Desc1") || _fields[i].equals("WfItem.Desc1")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfItem.getDesc1());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfItemHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfItem wfItem) {
		String _fields = "";
		if(null != wfItem.getItemId())
			_fields += "WfItem.ItemId,";
		if(null != wfItem.getStatus())
			_fields += "WfItem.Status,";
		if(null != wfItem.getFlowId())
			_fields += "WfItem.FlowId,";
		if(null != wfItem.getOrderBy())
			_fields += "WfItem.OrderBy,";
		if(null != wfItem.getItemNo())
			_fields += "WfItem.ItemNo,";
		if(null != wfItem.getDesc2())
			_fields += "WfItem.Desc2,";
		if(null != wfItem.getDesc5())
			_fields += "WfItem.Desc5,";
		if(null != wfItem.getItemName())
			_fields += "WfItem.ItemName,";
		if(null != wfItem.getDesc3())
			_fields += "WfItem.Desc3,";
		if(null != wfItem.getDesc4())
			_fields += "WfItem.Desc4,";
		if(null != wfItem.getDesc1())
			_fields += "WfItem.Desc1,";

		return _fields.substring(0, _fields.length()-1);
	}
}