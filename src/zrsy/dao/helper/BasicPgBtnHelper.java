package zrsy.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrsy.vo.PgBtn;

public class BasicPgBtnHelper implements SqlHelper {
	public String getSqlString() {
		return " from PgBtn where 1=1";
	}

	public String getOrderBy() {
		return " order by PgBtn.NodeId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((PgBtn)object);
	}

	public String getSql4Amount(PgBtn pgBtn) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(pgBtn);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((PgBtn)object);
	}

	public String getSql4Delete(PgBtn pgBtn) {
		return "delete from PgBtn where 1=1"+getSqlCondition(pgBtn);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((PgBtn)object,fields);
	}

	public String getSql4All(PgBtn pgBtn, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(pgBtn)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((PgBtn)object,pageVO,fields);
	}

	public String getSql4Pages(PgBtn pgBtn,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" PgBtn.NodeId "+ getSqlString()+getSqlCondition(pgBtn)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(pgBtn)+" and PgBtn.NodeId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((PgBtn)object);
	}

	public String getSqlCondition(PgBtn pgBtn) {
		String sql = "";
		if(null != pgBtn.getNodeId())
			sql += " and PgBtn.NodeId = '"+pgBtn.getNodeId()+"'";
		if(null != pgBtn.getBtnId())
			sql += " and PgBtn.BtnId = '"+pgBtn.getBtnId()+"'";
		if(null != pgBtn.getPgTyp())
			sql += " and PgBtn.PgTyp = '"+pgBtn.getPgTyp()+"'";

		return sql;
	}

	public List<PgBtn> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<PgBtn> list = new ArrayList<PgBtn>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				PgBtn pgBtn = new PgBtn();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("NodeId") || _fields[i].equals("PgBtn.NodeId"))
						pgBtn.setNodeId(rs.getInt("NodeId"));
					else if(_fields[i].equals("BtnId") || _fields[i].equals("PgBtn.BtnId"))
						pgBtn.setBtnId(rs.getInt("BtnId"));
					else if(_fields[i].equals("PgTyp") || _fields[i].equals("PgBtn.PgTyp"))
						pgBtn.setPgTyp(rs.getInt("PgTyp"));

				}
				list.add(pgBtn);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PgBtnHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into PgBtn("+fields.replaceAll("PgBtn\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(PgBtn pgBtn,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("NodeId") || _fields[i].equals("PgBtn.NodeId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, pgBtn.getNodeId());
					}
					else if(_fields[i].equals("BtnId") || _fields[i].equals("PgBtn.BtnId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, pgBtn.getBtnId());
					}
					else if(_fields[i].equals("PgTyp") || _fields[i].equals("PgBtn.PgTyp")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, pgBtn.getPgTyp());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("PgBtnHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update PgBtn set ";
		String[] _fields = fields.replaceAll("PgBtn\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(PgBtn pgBtn,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("PgBtnHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(PgBtn pgBtn) {
		String _fields = "";
		if(null != pgBtn.getNodeId())
			_fields += "PgBtn.NodeId,";
		if(null != pgBtn.getBtnId())
			_fields += "PgBtn.BtnId,";
		if(null != pgBtn.getPgTyp())
			_fields += "PgBtn.PgTyp,";

		return _fields.substring(0, _fields.length()-1);
	}
}