package zrsy.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrsy.vo.GpBtn;

public class BasicGpBtnHelper implements SqlHelper {
	public String getSqlString() {
		return " from GpBtn where 1=1";
	}

	public String getOrderBy() {
		return " order by GpBtn.GpId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((GpBtn)object);
	}

	public String getSql4Amount(GpBtn gpBtn) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(gpBtn);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((GpBtn)object);
	}

	public String getSql4Delete(GpBtn gpBtn) {
		return "delete from GpBtn where 1=1"+getSqlCondition(gpBtn);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((GpBtn)object,fields);
	}

	public String getSql4All(GpBtn gpBtn, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(gpBtn)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((GpBtn)object,pageVO,fields);
	}

	public String getSql4Pages(GpBtn gpBtn,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" GpBtn.GpId "+ getSqlString()+getSqlCondition(gpBtn)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(gpBtn)+" and GpBtn.GpId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((GpBtn)object);
	}

	public String getSqlCondition(GpBtn gpBtn) {
		String sql = "";
		if(null != gpBtn.getGpId())
			sql += " and GpBtn.GpId = '"+gpBtn.getGpId()+"'";
		if(null != gpBtn.getNodeId())
			sql += " and GpBtn.NodeId = '"+gpBtn.getNodeId()+"'";
		if(null != gpBtn.getBtnId())
			sql += " and GpBtn.BtnId = '"+gpBtn.getBtnId()+"'";

		return sql;
	}

	public List<GpBtn> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<GpBtn> list = new ArrayList<GpBtn>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				GpBtn gpBtn = new GpBtn();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("GpId") || _fields[i].equals("GpBtn.GpId"))
						gpBtn.setGpId(rs.getInt("GpId"));
					if(_fields[i].equals("NodeId") || _fields[i].equals("GpBtn.NodeId"))
						gpBtn.setNodeId(rs.getInt("NodeId"));
					if(_fields[i].equals("BtnId") || _fields[i].equals("GpBtn.BtnId"))
						gpBtn.setBtnId(rs.getInt("BtnId"));

				}
				list.add(gpBtn);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpBtnHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into GpBtn("+fields.replaceAll("GpBtn\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(GpBtn gpBtn,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("GpId") || _fields[i].equals("GpBtn.GpId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, gpBtn.getGpId());
					}
					else if(_fields[i].equals("NodeId") || _fields[i].equals("GpBtn.NodeId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, gpBtn.getNodeId());
					}
					else if(_fields[i].equals("BtnId") || _fields[i].equals("GpBtn.BtnId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, gpBtn.getBtnId());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("GpBtnHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update GpBtn set ";
		String[] _fields = fields.replaceAll("GpBtn\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(GpBtn gpBtn,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("GpBtnHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(GpBtn gpBtn) {
		String _fields = "";
		if(null != gpBtn.getGpId())
			_fields += "GpBtn.GpId,";
		if(null != gpBtn.getNodeId())
			_fields += "GpBtn.NodeId,";
		if(null != gpBtn.getBtnId())
			_fields += "GpBtn.BtnId,";

		return _fields.substring(0, _fields.length()-1);
	}
}