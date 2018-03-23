package zrsy.dao.helper;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;
import org.frm.comn.GenericUtil;
import zrsy.vo.WarrMsg;
public class BasicWarrMsgHelper implements SqlHelper {
	public String getSqlString() {
		return " from WarrMsg where 1=1";
	}

	public String getOrderBy() {
		return " order by WarrMsg.MsgId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WarrMsg)object);
	}

	public String getSql4Amount(WarrMsg warrMsg) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(warrMsg);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WarrMsg)object);
	}

	public String getSql4Delete(WarrMsg warrMsg) {
		return "delete from WarrMsg where 1=1"+getSqlCondition(warrMsg);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WarrMsg)object,fields);
	}

	public String getSql4All(WarrMsg warrMsg, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(warrMsg)+getOrderBy();
	}

	public String getSql4Pages(Object object, String fields) {
		return getSql4Pages((WarrMsg)object,fields);
	}

	public String getSql4Pages(WarrMsg warrMsg, String fields) {
		int currentPage = warrMsg.getPageNext().getCurrentPage();
		int pageSize = warrMsg.getPageNext().getPageSize();
		int pages = pageSize*currentPage-pageSize;
		String mstr = "select top "+pages+" WarrMsg.MsgId "+ getSqlString()+getSqlCondition(warrMsg)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(warrMsg)+" and WarrMsg.MsgId not in("+mstr+") "+getOrderBy();
		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WarrMsg)object);
	}

	public String getSqlCondition(WarrMsg warrMsg) {
		String sql = "";
		if(null != warrMsg.getMsgId())
			sql += " and WarrMsg.MsgId = "+warrMsg.getMsgId();
		if(null != warrMsg.getSender())
			sql += " and WarrMsg.Sender = "+warrMsg.getSender();
		if(null != warrMsg.getAccept())
			sql += " and WarrMsg.Accept = "+warrMsg.getAccept();
		if(null != warrMsg.getTitle() && !warrMsg.getTitle().trim().equals(""))
			sql += " and WarrMsg.Title = '"+warrMsg.getTitle().trim()+"'";
		if(null != warrMsg.getMsgText() && !warrMsg.getMsgText().trim().equals(""))
			sql += " and WarrMsg.MsgText = '"+warrMsg.getMsgText().trim()+"'";
		if(null != warrMsg.getUriLink() && !warrMsg.getUriLink().trim().equals(""))
			sql += " and WarrMsg.UriLink = '"+warrMsg.getUriLink().trim()+"'";
		if(null != warrMsg.getStatus())
			sql += " and WarrMsg.Status = "+warrMsg.getStatus();
		if(null != warrMsg.getCreateBy())
			sql += " and WarrMsg.CreateBy = "+warrMsg.getCreateBy();
		if(null != warrMsg.getStartCreateDate())
			sql += " and WarrMsg.CreateDate >= '"+GenericUtil.dateFormat(warrMsg.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != warrMsg.getEndCreateDate())
			sql += " and WarrMsg.CreateDate <= '"+GenericUtil.dateFormat(warrMsg.getEndCreateDate(), "yyyy-MM-dd 59:59:00")+"'";
		if(null != warrMsg.getLastUpd())
			sql += " and WarrMsg.LastUpd = "+warrMsg.getLastUpd();
		if(null != warrMsg.getStartLastUpdDate())
			sql += " and WarrMsg.LastUpdDate >= '"+GenericUtil.dateFormat(warrMsg.getStartLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != warrMsg.getEndLastUpdDate())
			sql += " and WarrMsg.LastUpdDate <= '"+GenericUtil.dateFormat(warrMsg.getEndLastUpdDate(), "yyyy-MM-dd 59:59:00")+"'";
		return sql;
	}

	public List<WarrMsg> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WarrMsg> list = new ArrayList<WarrMsg>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WarrMsg warrMsg = new WarrMsg();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("MsgId") || _fields[i].equals("WarrMsg.MsgId"))
						warrMsg.setMsgId(new Integer(rs.getInt("MsgId")));
					else if(_fields[i].equals("Sender") || _fields[i].equals("WarrMsg.Sender"))
						warrMsg.setSender(new Integer(rs.getInt("Sender")));
					else if(_fields[i].equals("Accept") || _fields[i].equals("WarrMsg.Accept"))
						warrMsg.setAccept(new Integer(rs.getInt("Accept")));
					else if(_fields[i].equals("Title") || _fields[i].equals("WarrMsg.Title"))
						warrMsg.setTitle(rs.getString("Title"));
					else if(_fields[i].equals("MsgText") || _fields[i].equals("WarrMsg.MsgText"))
						warrMsg.setMsgText(rs.getString("MsgText"));
					else if(_fields[i].equals("UriLink") || _fields[i].equals("WarrMsg.UriLink"))
						warrMsg.setUriLink(rs.getString("UriLink"));
					else if(_fields[i].equals("Status") || _fields[i].equals("WarrMsg.Status"))
						warrMsg.setStatus(new Integer(rs.getInt("Status")));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WarrMsg.CreateBy"))
						warrMsg.setCreateBy(new Integer(rs.getInt("CreateBy")));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WarrMsg.CreateDate"))
						warrMsg.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WarrMsg.LastUpd"))
						warrMsg.setLastUpd(new Integer(rs.getInt("LastUpd")));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WarrMsg.LastUpdDate"))
						warrMsg.setLastUpdDate(rs.getTimestamp("LastUpdDate"));
				}
				list.add(warrMsg);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WarrMsgHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WarrMsg("+fields.replaceAll("WarrMsg\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WarrMsg warrMsg,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
				if(_fields[i].equals("MsgId") || _fields[i].equals("WarrMsg.MsgId")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, warrMsg.getMsgId().intValue());
				}
				else if(_fields[i].equals("Sender") || _fields[i].equals("WarrMsg.Sender")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, warrMsg.getSender().intValue());
				}
				else if(_fields[i].equals("Accept") || _fields[i].equals("WarrMsg.Accept")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, warrMsg.getAccept().intValue());
				}
				else if(_fields[i].equals("Title") || _fields[i].equals("WarrMsg.Title")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, warrMsg.getTitle());
				}
				else if(_fields[i].equals("MsgText") || _fields[i].equals("WarrMsg.MsgText")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, warrMsg.getMsgText());
				}
				else if(_fields[i].equals("UriLink") || _fields[i].equals("WarrMsg.UriLink")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, warrMsg.getUriLink());
				}
				else if(_fields[i].equals("Status") || _fields[i].equals("WarrMsg.Status")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, warrMsg.getStatus().intValue());
				}
				else if(_fields[i].equals("CreateBy") || _fields[i].equals("WarrMsg.CreateBy")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, warrMsg.getCreateBy().intValue());
				}
				else if(_fields[i].equals("CreateDate") || _fields[i].equals("WarrMsg.CreateDate")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(warrMsg.getCreateDate().getTime()));
				}
				else if(_fields[i].equals("LastUpd") || _fields[i].equals("WarrMsg.LastUpd")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, warrMsg.getLastUpd().intValue());
				}
				else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WarrMsg.LastUpdDate")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(warrMsg.getLastDate().getTime()));
				}
			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WarrMsgHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WarrMsg set ";
		String[] _fields = fields.replaceAll("WarrMsg\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
				if(_fields[i].equals("Sender") || _fields[i].equals("WarrMsg.Sender"))
					sql += _fields[i] + " = ?,";
				if(_fields[i].equals("Accept") || _fields[i].equals("WarrMsg.Accept"))
					sql += _fields[i] + " = ?,";
				if(_fields[i].equals("Title") || _fields[i].equals("WarrMsg.Title"))
					sql += _fields[i] + " = ?,";
				if(_fields[i].equals("MsgText") || _fields[i].equals("WarrMsg.MsgText"))
					sql += _fields[i] + " = ?,";
				if(_fields[i].equals("UriLink") || _fields[i].equals("WarrMsg.UriLink"))
					sql += _fields[i] + " = ?,";
				if(_fields[i].equals("Status") || _fields[i].equals("WarrMsg.Status"))
					sql += _fields[i] + " = ?,";
				if(_fields[i].equals("CreateBy") || _fields[i].equals("WarrMsg.CreateBy"))
					sql += _fields[i] + " = ?,";
				if(_fields[i].equals("CreateDate") || _fields[i].equals("WarrMsg.CreateDate"))
					sql += _fields[i] + " = ?,";
				if(_fields[i].equals("LastUpd") || _fields[i].equals("WarrMsg.LastUpd"))
					sql += _fields[i] + " = ?,";
				if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WarrMsg.LastUpdDate"))
					sql += _fields[i] + " = ?,";
		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WarrMsg warrMsg,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
				if(_fields[i].equals("Sender") || _fields[i].equals("WarrMsg.Sender")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, warrMsg.getSender().intValue());
				}
				else if(_fields[i].equals("Accept") || _fields[i].equals("WarrMsg.Accept")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, warrMsg.getAccept().intValue());
				}
				else if(_fields[i].equals("Title") || _fields[i].equals("WarrMsg.Title")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, warrMsg.getTitle());
				}
				else if(_fields[i].equals("MsgText") || _fields[i].equals("WarrMsg.MsgText")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, warrMsg.getMsgText());
				}
				else if(_fields[i].equals("UriLink") || _fields[i].equals("WarrMsg.UriLink")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, warrMsg.getUriLink());
				}
				else if(_fields[i].equals("Status") || _fields[i].equals("WarrMsg.Status")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, warrMsg.getStatus().intValue());
				}
				else if(_fields[i].equals("CreateBy") || _fields[i].equals("WarrMsg.CreateBy")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, warrMsg.getCreateBy().intValue());
				}
				else if(_fields[i].equals("CreateDate") || _fields[i].equals("WarrMsg.CreateDate")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(warrMsg.getCreateDate().getTime()));
				}
				else if(_fields[i].equals("LastUpd") || _fields[i].equals("WarrMsg.LastUpd")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, warrMsg.getLastUpd().intValue());
				}
				else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WarrMsg.LastUpdDate")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(warrMsg.getLastDate().getTime()));
				}
			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WarrMsgHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WarrMsg warrMsg) {
		String _fields = "";
		if(null != warrMsg.getMsgId())
			_fields += "WarrMsg.MsgId,";
		if(null != warrMsg.getSender())
			_fields += "WarrMsg.Sender,";
		if(null != warrMsg.getAccept())
			_fields += "WarrMsg.Accept,";
		if(null != warrMsg.getTitle() && !warrMsg.getTitle().trim().equals(""))
			_fields += "WarrMsg.Title,";
		if(null != warrMsg.getMsgText() && !warrMsg.getMsgText().trim().equals(""))
			_fields += "WarrMsg.MsgText,";
		if(null != warrMsg.getUriLink() && !warrMsg.getUriLink().trim().equals(""))
			_fields += "WarrMsg.UriLink,";
		if(null != warrMsg.getStatus())
			_fields += "WarrMsg.Status,";
		if(null != warrMsg.getCreateBy())
			_fields += "WarrMsg.CreateBy,";
		if(null != warrMsg.getCreateDate())
			_fields += "WarrMsg.CreateDate,";
		if(null != warrMsg.getLastUpd())
			_fields += "WarrMsg.LastUpd,";
		if(null != warrMsg.getLastDate())
			_fields += "WarrMsg.LastUpdDate,";
		return _fields.substring(0, _fields.length()-1);
	}

	@Override
	public String getSql4Pages(Object arg0, PageVO arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}
}