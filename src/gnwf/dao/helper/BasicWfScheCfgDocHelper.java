package gnwf.dao.helper;

import gnwf.vo.WfScheCfgDoc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

public class BasicWfScheCfgDocHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfScheCfgDoc where 1=1";
	}

	public String getOrderBy() {
		return " order by WfScheCfgDoc.DocId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfScheCfgDoc) object);
	}

	public String getSql4Amount(WfScheCfgDoc wfScheCfgDoc) {
		return "select count(*) as amount " + getSqlString()
				+ getSqlCondition(wfScheCfgDoc);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfScheCfgDoc) object);
	}

	public String getSql4Delete(WfScheCfgDoc wfScheCfgDoc) {
		return "delete from WfScheCfgDoc where 1=1"
				+ getSqlCondition(wfScheCfgDoc);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfScheCfgDoc) object, fields);
	}

	public String getSql4All(WfScheCfgDoc wfScheCfgDoc, String fields) {
		return "select " + fields + getSqlString()
				+ getSqlCondition(wfScheCfgDoc) + getOrderBy();
	}

	public String getSql4Pages(Object object, String fields) {
		return getSql4Pages((WfScheCfgDoc) object, fields);
	}

	public String getSql4Pages(WfScheCfgDoc wfScheCfgDoc, String fields) {
		int currentPage = wfScheCfgDoc.getPageNext().getCurrentPage();
		int pageSize = wfScheCfgDoc.getPageNext().getPageSize();
		int pages = pageSize * currentPage - pageSize;
		String mstr = "select top " + pages + " WfScheCfgDoc.DocId "
				+ getSqlString() + getSqlCondition(wfScheCfgDoc) + getOrderBy();
		String sql = "select top " + pageSize + " " + fields + getSqlString()
				+ getSqlCondition(wfScheCfgDoc)
				+ " and WfScheCfgDoc.DocId not in(" + mstr + ") "
				+ getOrderBy();
		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfScheCfgDoc) object);
	}

	public String getSqlCondition(WfScheCfgDoc wfScheCfgDoc) {
		String sql = "";
		if (null != wfScheCfgDoc.getDocId())
			sql += " and WfScheCfgDoc.DocId = " + wfScheCfgDoc.getDocId();
		if (null != wfScheCfgDoc.getCfgId())
			sql += " and WfScheCfgDoc.CfgId = " + wfScheCfgDoc.getCfgId();
		if (null != wfScheCfgDoc.getDocName()
				&& !wfScheCfgDoc.getDocName().trim().equals(""))
			sql += " and WfScheCfgDoc.DocName = '"
					+ wfScheCfgDoc.getDocName().trim() + "'";
		if (null != wfScheCfgDoc.getSort())
			sql += " and WfScheCfgDoc.Sort = " + wfScheCfgDoc.getSort();
		return sql;
	}

	public List<WfScheCfgDoc> getQueryList(ResultSet rs, String fields)
			throws java.sql.SQLException {
		ArrayList<WfScheCfgDoc> list = new ArrayList<WfScheCfgDoc>();
		String[] _fields = fields.split(",");
		try {
			while (rs.next()) {
				WfScheCfgDoc wfScheCfgDoc = new WfScheCfgDoc();
				for (int i = 0; i < _fields.length; i++) {
					if (_fields[i].equals("DocId")
							|| _fields[i].equals("WfScheCfgDoc.DocId"))
						wfScheCfgDoc.setDocId(new Integer(rs.getInt("DocId")));
					else if (_fields[i].equals("CfgId")
							|| _fields[i].equals("WfScheCfgDoc.CfgId"))
						wfScheCfgDoc.setCfgId(new Integer(rs.getInt("CfgId")));
					else if (_fields[i].equals("DocName")
							|| _fields[i].equals("WfScheCfgDoc.DocName"))
						wfScheCfgDoc.setDocName(rs.getString("DocName"));
					else if (_fields[i].equals("Sort")
							|| _fields[i].equals("WfScheCfgDoc.Sort"))
						wfScheCfgDoc.setSort(new Integer(rs.getInt("Sort")));
				}
				list.add(wfScheCfgDoc);
			}
		} catch (java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error(
					"WfScheCfgDocHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfScheCfgDoc("
				+ fields.replaceAll("WfScheCfgDoc\\.", "") + ")values(";
		int length = fields.split(",").length;
		for (int i = 0; i < length - 1; i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfScheCfgDoc wfScheCfgDoc, String sql, String fields)
			throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for (int i = 0; i < _fields.length; i++) {
				if (_fields[i].equals("DocId")
						|| _fields[i].equals("WfScheCfgDoc.DocId")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt()
							.setInt(index, wfScheCfgDoc.getDocId().intValue());
				} else if (_fields[i].equals("CfgId")
						|| _fields[i].equals("WfScheCfgDoc.CfgId")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt()
							.setInt(index, wfScheCfgDoc.getCfgId().intValue());
				} else if (_fields[i].equals("DocName")
						|| _fields[i].equals("WfScheCfgDoc.DocName")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt()
							.setString(index, wfScheCfgDoc.getDocName());
				} else if (_fields[i].equals("Sort")
						|| _fields[i].equals("WfScheCfgDoc.Sort")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt()
							.setInt(index, wfScheCfgDoc.getSort().intValue());
				}else if (_fields[i].equals("IsMust")
						|| _fields[i].equals("WfScheCfgDoc.IsMust")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt()
							.setInt(index, wfScheCfgDoc.getIsMust().intValue());
				}
			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error(
					"WfScheCfgDocHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields, String key, String keyValue) {
		String sql = "update WfScheCfgDoc set ";
		String[] _fields = fields.replaceAll("WfScheCfgDoc\\.", "").split(",");
		for (int i = 0; i < _fields.length; i++) {
			if (_fields[i].equals("CfgId")
					|| _fields[i].equals("WfScheCfgDoc.CfgId"))
				sql += _fields[i] + " = ?,";
			if (_fields[i].equals("DocName")
					|| _fields[i].equals("WfScheCfgDoc.DocName"))
				sql += _fields[i] + " = ?,";
			if (_fields[i].equals("Sort")
					|| _fields[i].equals("WfScheCfgDoc.Sort"))
				sql += _fields[i] + " = ?,";
			if (_fields[i].equals("IsMust")
					|| _fields[i].equals("WfScheCfgDoc.IsMust"))
				sql += _fields[i] + " = ?,";
		}
		sql = sql.substring(0, sql.length() - 1);
		sql += " where " + key + " = '" + keyValue + "'";
		return sql;
	}

	public void pstmtUpdate(WfScheCfgDoc wfScheCfgDoc, String sql, String fields)
			throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for (int i = 0; i < _fields.length; i++) {
				if (_fields[i].equals("CfgId")
						|| _fields[i].equals("WfScheCfgDoc.CfgId")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt()
							.setInt(index, wfScheCfgDoc.getCfgId().intValue());
				}
				if (_fields[i].equals("DocName")
						|| _fields[i].equals("WfScheCfgDoc.DocName")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt()
							.setString(index, wfScheCfgDoc.getDocName());
				} else if (_fields[i].equals("Sort")
						|| _fields[i].equals("WfScheCfgDoc.Sort")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt()
							.setInt(index, wfScheCfgDoc.getSort().intValue());
				} else if (_fields[i].equals("IsMust")
						|| _fields[i].equals("WfScheCfgDoc.IsMust")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt()
							.setInt(index, wfScheCfgDoc.getIsMust().intValue());
				}
			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error(
					"WfScheCfgDocHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfScheCfgDoc wfScheCfgDoc) {
		String _fields = "";
		if (null != wfScheCfgDoc.getDocId())
			_fields += "WfScheCfgDoc.DocId,";
		if (null != wfScheCfgDoc.getCfgId())
			_fields += "WfScheCfgDoc.CfgId,";
		if (null != wfScheCfgDoc.getDocName()
				&& !wfScheCfgDoc.getDocName().trim().equals(""))
			_fields += "WfScheCfgDoc.DocName,";
		if (null != wfScheCfgDoc.getSort())
			_fields += "WfScheCfgDoc.Sort,";
		if (null != wfScheCfgDoc.getIsMust())
			_fields += "WfScheCfgDoc.IsMust,";
		return _fields.substring(0, _fields.length() - 1);
	}

	@Override
	public String getSql4Pages(Object arg0, PageVO arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}
}