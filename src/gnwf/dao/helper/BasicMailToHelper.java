package gnwf.dao.helper;
import gnwf.vo.MailTo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

public class BasicMailToHelper implements SqlHelper {
	public String getSqlString() {
		return " from MailTo where 1=1";
	}

	public String getOrderBy() {
		return " order by MailTo.MailId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((MailTo)object);
	}

	public String getSql4Amount(MailTo mailTo) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(mailTo);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((MailTo)object);
	}

	public String getSql4Delete(MailTo mailTo) {
		return "delete from MailTo where 1=1"+getSqlCondition(mailTo);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((MailTo)object,fields);
	}

	public String getSql4All(MailTo mailTo, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(mailTo)+getOrderBy();
	}

	public String getSql4Pages(Object object, String fields) {
		return getSql4Pages((MailTo)object,fields);
	}

	public String getSql4Pages(MailTo mailTo, String fields) {
		int currentPage = mailTo.getPageNext().getCurrentPage();
		int pageSize = mailTo.getPageNext().getPageSize();
		int pages = pageSize*currentPage-pageSize;
		String mstr = "select top "+pages+" MailTo.MailId "+ getSqlString()+getSqlCondition(mailTo)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(mailTo)+" and MailTo.MailId not in("+mstr+") "+getOrderBy();
		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((MailTo)object);
	}

	public String getSqlCondition(MailTo mailTo) {
		String sql = "";
		if(null != mailTo.getMailId())
			sql += " and MailTo.MailId = "+mailTo.getMailId();
		if(null != mailTo.getToId())
			sql += " and MailTo.ToId = "+mailTo.getToId();
		if(null != mailTo.getToMail() && !mailTo.getToMail().trim().equals(""))
			sql += " and MailTo.ToMail = '"+mailTo.getToMail().trim()+"'";
		if(null != mailTo.getToType())
			sql += " and MailTo.ToType = "+mailTo.getToType();
		if(null != mailTo.getToName() && !mailTo.getToName().trim().equals(""))
			sql += " and MailTo.ToName = '"+mailTo.getToName().trim()+"'";
		return sql;
	}

	public List<MailTo> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<MailTo> list = new ArrayList<MailTo>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				MailTo mailTo = new MailTo();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("MailId") || _fields[i].equals("MailTo.MailId"))
						mailTo.setMailId(new Integer(rs.getInt("MailId")));
					else if(_fields[i].equals("ToId") || _fields[i].equals("MailTo.ToId"))
						mailTo.setToId(new Integer(rs.getInt("ToId")));
					else if(_fields[i].equals("ToMail") || _fields[i].equals("MailTo.ToMail"))
						mailTo.setToMail(rs.getString("ToMail"));
					else if(_fields[i].equals("ToType") || _fields[i].equals("MailTo.ToType"))
						mailTo.setToType(new Integer(rs.getInt("ToType")));
					else if(_fields[i].equals("ToName") || _fields[i].equals("MailTo.ToName"))
						mailTo.setToName(rs.getString("ToName"));
				}
				list.add(mailTo);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailToHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into MailTo("+fields.replaceAll("MailTo\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(MailTo mailTo,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
				if(_fields[i].equals("MailId") || _fields[i].equals("MailTo.MailId")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailTo.getMailId().intValue());
				}
				else if(_fields[i].equals("ToId") || _fields[i].equals("MailTo.ToId")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailTo.getToId().intValue());
				}
				else if(_fields[i].equals("ToMail") || _fields[i].equals("MailTo.ToMail")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailTo.getToMail());
				}
				else if(_fields[i].equals("ToType") || _fields[i].equals("MailTo.ToType")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailTo.getToType().intValue());
				}
				else if(_fields[i].equals("ToName") || _fields[i].equals("MailTo.ToName")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailTo.getToName());
				}
			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("MailToHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update MailTo set ";
		String[] _fields = fields.replaceAll("MailTo\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
				if(_fields[i].equals("ToId") || _fields[i].equals("MailTo.ToId"))
					sql += _fields[i] + " = ?,";
				if(_fields[i].equals("ToMail") || _fields[i].equals("MailTo.ToMail"))
					sql += _fields[i] + " = ?,";
				if(_fields[i].equals("ToType") || _fields[i].equals("MailTo.ToType"))
					sql += _fields[i] + " = ?,";
				if(_fields[i].equals("ToName") || _fields[i].equals("MailTo.ToName"))
					sql += _fields[i] + " = ?,";
		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(MailTo mailTo,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
				if(_fields[i].equals("ToId") || _fields[i].equals("MailTo.ToId")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailTo.getToId().intValue());
				}
				if(_fields[i].equals("ToMail") || _fields[i].equals("MailTo.ToMail")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailTo.getToMail());
				}
				else if(_fields[i].equals("ToType") || _fields[i].equals("MailTo.ToType")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailTo.getToType().intValue());
				}
				else if(_fields[i].equals("ToName") || _fields[i].equals("MailTo.ToName")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailTo.getToName());
				}
			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("MailToHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(MailTo mailTo) {
		String _fields = "";
		if(null != mailTo.getMailId())
			_fields += "MailTo.MailId,";
		if(null != mailTo.getToId())
			_fields += "MailTo.ToId,";
		if(null != mailTo.getToMail() && !mailTo.getToMail().trim().equals(""))
			_fields += "MailTo.ToMail,";
		if(null != mailTo.getToType())
			_fields += "MailTo.ToType,";
		if(null != mailTo.getToName() && !mailTo.getToName().trim().equals(""))
			_fields += "MailTo.ToName,";
		return _fields.substring(0, _fields.length()-1);
	}

	@Override
	public String getSql4Pages(Object arg0, PageVO arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}
}