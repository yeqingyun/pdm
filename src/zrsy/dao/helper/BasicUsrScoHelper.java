package zrsy.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrsy.vo.UsrSco;

public class BasicUsrScoHelper implements SqlHelper {
	public String getSqlString() {
		return " from UsrSco where 1=1";
	}

	public String getOrderBy() {
		return " order by UsrSco.UsrId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((UsrSco)object);
	}

	public String getSql4Amount(UsrSco usrSco) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(usrSco);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((UsrSco)object);
	}

	public String getSql4Delete(UsrSco usrSco) {
		return "delete from UsrSco where 1=1"+getSqlCondition(usrSco);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((UsrSco)object,fields);
	}

	public String getSql4All(UsrSco usrSco, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(usrSco)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((UsrSco)object,pageVO,fields);
	}

	public String getSql4Pages(UsrSco usrSco,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" UsrSco.UsrId "+ getSqlString()+getSqlCondition(usrSco)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(usrSco)+" and UsrSco.UsrId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((UsrSco)object);
	}

	public String getSqlCondition(UsrSco usrSco) {
		String sql = "";
		if(null != usrSco.getUsrId())
			sql += " and UsrSco.UsrId = '"+usrSco.getUsrId()+"'";
		if(null != usrSco.getScoId())
			sql += " and UsrSco.ScoId = '"+usrSco.getScoId()+"'";

		return sql;
	}

	public List<UsrSco> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<UsrSco> list = new ArrayList<UsrSco>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				UsrSco usrSco = new UsrSco();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("UsrId") || _fields[i].equals("UsrSco.UsrId"))
						usrSco.setUsrId(rs.getInt("UsrId"));
					else if(_fields[i].equals("ScoId") || _fields[i].equals("UsrSco.ScoId"))
						usrSco.setScoId(rs.getInt("ScoId"));

				}
				list.add(usrSco);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("UsrScoHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into UsrSco("+fields.replaceAll("UsrSco\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(UsrSco usrSco,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("UsrId") || _fields[i].equals("UsrSco.UsrId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, usrSco.getUsrId());
					}
					else if(_fields[i].equals("ScoId") || _fields[i].equals("UsrSco.ScoId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, usrSco.getScoId());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("UsrScoHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update UsrSco set ";
		String[] _fields = fields.replaceAll("UsrSco\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(UsrSco usrSco,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("UsrScoHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(UsrSco usrSco) {
		String _fields = "";
		if(null != usrSco.getUsrId())
			_fields += "UsrSco.UsrId,";
		if(null != usrSco.getScoId())
			_fields += "UsrSco.ScoId,";

		return _fields.substring(0, _fields.length()-1);
	}
}