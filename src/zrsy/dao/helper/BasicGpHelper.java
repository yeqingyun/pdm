package zrsy.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrsy.vo.Gp;

public class BasicGpHelper implements SqlHelper {
	public String getSqlString() {
		return " from Gp where 1=1";
	}

	public String getOrderBy() {
		return " order by Gp.GpId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((Gp)object);
	}

	public String getSql4Amount(Gp gp) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(gp);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((Gp)object);
	}

	public String getSql4Delete(Gp gp) {
		return "delete from Gp where 1=1"+getSqlCondition(gp);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((Gp)object,fields);
	}

	public String getSql4All(Gp gp, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(gp)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((Gp)object,pageVO,fields);
	}

	public String getSql4Pages(Gp gp,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" Gp.GpId "+ getSqlString()+getSqlCondition(gp)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(gp)+" and Gp.GpId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((Gp)object);
	}

	public String getSqlCondition(Gp gp) {
		String sql = "";
		if(null != gp.getGpId())
			sql += " and Gp.GpId = '"+gp.getGpId()+"'";
		if(null != gp.getSyId())
			sql += " and Gp.SyId = '"+gp.getSyId()+"'";
		if(null != gp.getGpName() && !gp.getGpName().trim().equals(""))
			sql += " and Gp.GpName = '"+gp.getGpName().trim()+"'";
		if(null != gp.getRemark() && !gp.getRemark().trim().equals(""))
			sql += " and Gp.Remark = '"+gp.getRemark().trim()+"'";

		return sql;
	}

	public List<Gp> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<Gp> list = new ArrayList<Gp>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				Gp gp = new Gp();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("GpId") || _fields[i].equals("Gp.GpId"))
						gp.setGpId(rs.getInt("GpId"));
					if(_fields[i].equals("SyId") || _fields[i].equals("Gp.SyId"))
						gp.setSyId(rs.getInt("SyId"));
					if(_fields[i].equals("GpName") || _fields[i].equals("Gp.GpName"))
						gp.setGpName(rs.getString("GpName"));
					if(_fields[i].equals("Remark") || _fields[i].equals("Gp.Remark"))
						gp.setRemark(rs.getString("Remark"));

				}
				list.add(gp);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into Gp("+fields.replaceAll("Gp\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(Gp gp,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("GpId") || _fields[i].equals("Gp.GpId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, gp.getGpId());
					}
					else if(_fields[i].equals("SyId") || _fields[i].equals("Gp.SyId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, gp.getSyId());
					}
					else if(_fields[i].equals("GpName") || _fields[i].equals("Gp.GpName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, gp.getGpName());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("Gp.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, gp.getRemark());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("GpHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update Gp set ";
		String[] _fields = fields.replaceAll("Gp\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SyId") || _fields[i].equals("Gp.SyId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("GpName") || _fields[i].equals("Gp.GpName"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Remark") || _fields[i].equals("Gp.Remark"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(Gp gp,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SyId") || _fields[i].equals("Gp.SyId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, gp.getSyId());
					}
					else if(_fields[i].equals("GpName") || _fields[i].equals("Gp.GpName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, gp.getGpName());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("Gp.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, gp.getRemark());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("GpHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(Gp gp) {
		String _fields = "";
		if(null != gp.getGpId())
			_fields += "Gp.GpId,";
		if(null != gp.getSyId())
			_fields += "Gp.SyId,";
		if(null != gp.getGpName())
			_fields += "Gp.GpName,";
		if(null != gp.getRemark())
			_fields += "Gp.Remark,";

		return _fields.substring(0, _fields.length()-1);
	}
}