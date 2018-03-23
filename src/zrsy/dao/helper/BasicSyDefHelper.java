package zrsy.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrsy.vo.SyDef;

public class BasicSyDefHelper implements SqlHelper {
	public String getSqlString() {
		return " from SyDef where 1=1";
	}

	public String getOrderBy() {
		return " order by SyDef.SyId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((SyDef)object);
	}

	public String getSql4Amount(SyDef syDef) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(syDef);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((SyDef)object);
	}

	public String getSql4Delete(SyDef syDef) {
		return "delete from SyDef where 1=1"+getSqlCondition(syDef);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((SyDef)object,fields);
	}

	public String getSql4All(SyDef syDef, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(syDef)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((SyDef)object,pageVO,fields);
	}

	public String getSql4Pages(SyDef syDef,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" SyDef.SyId "+ getSqlString()+getSqlCondition(syDef)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(syDef)+" and SyDef.SyId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((SyDef)object);
	}

	public String getSqlCondition(SyDef syDef) {
		String sql = "";
		if(null != syDef.getSyId())
			sql += " and SyDef.SyId = '"+syDef.getSyId()+"'";
		if(null != syDef.getSyName() && !syDef.getSyName().trim().equals(""))
			sql += " and SyDef.SyName = '"+syDef.getSyName().trim()+"'";
		if(null != syDef.getSyText() && !syDef.getSyText().trim().equals(""))
			sql += " and SyDef.SyText = '"+syDef.getSyText().trim()+"'";

		return sql;
	}

	public List<SyDef> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<SyDef> list = new ArrayList<SyDef>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				SyDef syDef = new SyDef();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SyId") || _fields[i].equals("SyDef.SyId"))
						syDef.setSyId(rs.getInt("SyId"));
					if(_fields[i].equals("SyName") || _fields[i].equals("SyDef.SyName"))
						syDef.setSyName(rs.getString("SyName"));
					if(_fields[i].equals("SyText") || _fields[i].equals("SyDef.SyText"))
						syDef.setSyText(rs.getString("SyText"));

				}
				list.add(syDef);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SyDefHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into SyDef("+fields.replaceAll("SyDef\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(SyDef syDef,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SyId") || _fields[i].equals("SyDef.SyId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, syDef.getSyId());
					}
					else if(_fields[i].equals("SyName") || _fields[i].equals("SyDef.SyName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, syDef.getSyName());
					}
					else if(_fields[i].equals("SyText") || _fields[i].equals("SyDef.SyText")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, syDef.getSyText());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("SyDefHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update SyDef set ";
		String[] _fields = fields.replaceAll("SyDef\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SyName") || _fields[i].equals("SyDef.SyName"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("SyText") || _fields[i].equals("SyDef.SyText"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(SyDef syDef,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SyName") || _fields[i].equals("SyDef.SyName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, syDef.getSyName());
					}
					else if(_fields[i].equals("SyText") || _fields[i].equals("SyDef.SyText")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, syDef.getSyText());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("SyDefHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(SyDef syDef) {
		String _fields = "";
		if(null != syDef.getSyId())
			_fields += "SyDef.SyId,";
		if(null != syDef.getSyName())
			_fields += "SyDef.SyName,";
		if(null != syDef.getSyText())
			_fields += "SyDef.SyText,";

		return _fields.substring(0, _fields.length()-1);
	}
}