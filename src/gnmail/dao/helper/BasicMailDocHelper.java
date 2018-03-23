package gnmail.dao.helper;

import gnmail.vo.MailDoc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

public class BasicMailDocHelper implements SqlHelper {
	public String getSqlString() {
		return " from MailDoc where 1=1";
	}

	public String getOrderBy() {
		return " order by MailDoc.DocId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((MailDoc)object);
	}

	public String getSql4Amount(MailDoc mailDoc) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(mailDoc);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((MailDoc)object);
	}

	public String getSql4Delete(MailDoc mailDoc) {
		return "delete from MailDoc where 1=1"+getSqlCondition(mailDoc);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((MailDoc)object,fields);
	}

	public String getSql4All(MailDoc mailDoc, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(mailDoc)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((MailDoc)object,pageVO,fields);
	}

	public String getSql4Pages(MailDoc mailDoc,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" MailDoc.DocId "+ getSqlString()+getSqlCondition(mailDoc)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(mailDoc)+" and MailDoc.DocId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((MailDoc)object);
	}

	public String getSqlCondition(MailDoc mailDoc) {
		String sql = "";
		if(null != mailDoc.getDocId())
			sql += " and MailDoc.DocId = '"+mailDoc.getDocId()+"'";
		if(null != mailDoc.getMailId())
			sql += " and MailDoc.MailId = '"+mailDoc.getMailId()+"'";
		if(null != mailDoc.getDocName() && !mailDoc.getDocName().trim().equals(""))
			sql += " and MailDoc.DocName = '"+mailDoc.getDocName().trim()+"'";
		if(null != mailDoc.getUriLink() && !mailDoc.getUriLink().trim().equals(""))
			sql += " and MailDoc.UriLink = '"+mailDoc.getUriLink().trim()+"'";

		return sql;
	}

	public List<MailDoc> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<MailDoc> list = new ArrayList<MailDoc>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				MailDoc mailDoc = new MailDoc();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("DocId") || _fields[i].equals("MailDoc.DocId"))
						mailDoc.setDocId(rs.getInt("DocId"));
					else if(_fields[i].equals("MailId") || _fields[i].equals("MailDoc.MailId"))
						mailDoc.setMailId(rs.getInt("MailId"));
					else if(_fields[i].equals("DocName") || _fields[i].equals("MailDoc.DocName"))
						mailDoc.setDocName(rs.getString("DocName"));
					else if(_fields[i].equals("UriLink") || _fields[i].equals("MailDoc.UriLink"))
						mailDoc.setUriLink(rs.getString("UriLink"));

				}
				list.add(mailDoc);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailDocHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into MailDoc("+fields.replaceAll("MailDoc\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(MailDoc mailDoc,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("DocId") || _fields[i].equals("MailDoc.DocId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailDoc.getDocId());
					}
					else if(_fields[i].equals("MailId") || _fields[i].equals("MailDoc.MailId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailDoc.getMailId());
					}
					else if(_fields[i].equals("DocName") || _fields[i].equals("MailDoc.DocName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailDoc.getDocName());
					}
					else if(_fields[i].equals("UriLink") || _fields[i].equals("MailDoc.UriLink")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailDoc.getUriLink());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("MailDocHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update MailDoc set ";
		String[] _fields = fields.replaceAll("MailDoc\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("MailId") || _fields[i].equals("MailDoc.MailId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("DocName") || _fields[i].equals("MailDoc.DocName"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("UriLink") || _fields[i].equals("MailDoc.UriLink"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(MailDoc mailDoc,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("MailId") || _fields[i].equals("MailDoc.MailId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailDoc.getMailId());
					}
					else if(_fields[i].equals("DocName") || _fields[i].equals("MailDoc.DocName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailDoc.getDocName());
					}
					else if(_fields[i].equals("UriLink") || _fields[i].equals("MailDoc.UriLink")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailDoc.getUriLink());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("MailDocHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(MailDoc mailDoc) {
		String _fields = "";
		if(null != mailDoc.getDocId())
			_fields += "MailDoc.DocId,";
		if(null != mailDoc.getMailId())
			_fields += "MailDoc.MailId,";
		if(null != mailDoc.getDocName())
			_fields += "MailDoc.DocName,";
		if(null != mailDoc.getUriLink())
			_fields += "MailDoc.UriLink,";

		return _fields.substring(0, _fields.length()-1);
	}
}