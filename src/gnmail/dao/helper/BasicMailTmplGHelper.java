package gnmail.dao.helper;

import gnmail.vo.MailTmplG;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

public class BasicMailTmplGHelper implements SqlHelper {
	public String getSqlString() {
		return " from MailTmplG where 1=1";
	}

	public String getOrderBy() {
		return " order by MailTmplG.TmplId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((MailTmplG)object);
	}

	public String getSql4Amount(MailTmplG mailTmplG) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(mailTmplG);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((MailTmplG)object);
	}

	public String getSql4Delete(MailTmplG mailTmplG) {
		return "delete from MailTmplG where 1=1"+getSqlCondition(mailTmplG);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((MailTmplG)object,fields);
	}

	public String getSql4All(MailTmplG mailTmplG, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(mailTmplG)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((MailTmplG)object,pageVO,fields);
	}

	public String getSql4Pages(MailTmplG mailTmplG,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" MailTmplG.TmplId "+ getSqlString()+getSqlCondition(mailTmplG)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(mailTmplG)+" and MailTmplG.TmplId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((MailTmplG)object);
	}

	public String getSqlCondition(MailTmplG mailTmplG) {
		String sql = "";
		if(null != mailTmplG.getTmplId())
			sql += " and MailTmplG.TmplId = '"+mailTmplG.getTmplId()+"'";
		if(null != mailTmplG.getGroupId())
			sql += " and MailTmplG.GroupId = '"+mailTmplG.getGroupId()+"'";

		return sql;
	}

	public List<MailTmplG> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<MailTmplG> list = new ArrayList<MailTmplG>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				MailTmplG mailTmplG = new MailTmplG();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("TmplId") || _fields[i].equals("MailTmplG.TmplId"))
						mailTmplG.setTmplId(rs.getInt("TmplId"));
					else if(_fields[i].equals("GroupId") || _fields[i].equals("MailTmplG.GroupId"))
						mailTmplG.setGroupId(rs.getInt("GroupId"));

				}
				list.add(mailTmplG);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailTmplGHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into MailTmplG("+fields.replaceAll("MailTmplG\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(MailTmplG mailTmplG,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("TmplId") || _fields[i].equals("MailTmplG.TmplId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailTmplG.getTmplId());
					}
					else if(_fields[i].equals("GroupId") || _fields[i].equals("MailTmplG.GroupId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailTmplG.getGroupId());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("MailTmplGHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update MailTmplG set ";
		String[] _fields = fields.replaceAll("MailTmplG\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(MailTmplG mailTmplG,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("MailTmplGHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(MailTmplG mailTmplG) {
		String _fields = "";
		if(null != mailTmplG.getTmplId())
			_fields += "MailTmplG.TmplId,";
		if(null != mailTmplG.getGroupId())
			_fields += "MailTmplG.GroupId,";

		return _fields.substring(0, _fields.length()-1);
	}
}