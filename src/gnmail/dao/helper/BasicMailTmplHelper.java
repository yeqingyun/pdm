package gnmail.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnmail.vo.MailTmpl;

public class BasicMailTmplHelper implements SqlHelper {
	public String getSqlString() {
		return " from MailTmpl where 1=1";
	}

	public String getOrderBy() {
		return " order by MailTmpl.TmplId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((MailTmpl)object);
	}

	public String getSql4Amount(MailTmpl mailTmpl) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(mailTmpl);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((MailTmpl)object);
	}

	public String getSql4Delete(MailTmpl mailTmpl) {
		return "delete from MailTmpl where 1=1"+getSqlCondition(mailTmpl);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((MailTmpl)object,fields);
	}

	public String getSql4All(MailTmpl mailTmpl, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(mailTmpl)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((MailTmpl)object,pageVO,fields);
	}

	public String getSql4Pages(MailTmpl mailTmpl,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" MailTmpl.TmplId "+ getSqlString()+getSqlCondition(mailTmpl)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(mailTmpl)+" and MailTmpl.TmplId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((MailTmpl)object);
	}

	public String getSqlCondition(MailTmpl mailTmpl) {
		String sql = "";
		if(null != mailTmpl.getTmplId())
			sql += " and MailTmpl.TmplId = '"+mailTmpl.getTmplId()+"'";
		if(null != mailTmpl.getCfgId())
			sql += " and MailTmpl.CfgId = '"+mailTmpl.getCfgId()+"'";
		if(null != mailTmpl.getUserId())
			sql += " and MailTmpl.UserId = '"+mailTmpl.getUserId()+"'";
		if(null != mailTmpl.getTmplName() && !mailTmpl.getTmplName().trim().equals(""))
			sql += " and MailTmpl.TmplName = '"+mailTmpl.getTmplName().trim()+"'";
		if(null != mailTmpl.getTmplText() && !mailTmpl.getTmplText().trim().equals(""))
			sql += " and MailTmpl.TmplText = '"+mailTmpl.getTmplText().trim()+"'";
		if(null != mailTmpl.getType())
			sql += " and MailTmpl.Type = '"+mailTmpl.getType()+"'";
		if(null != mailTmpl.getIsAuto())
			sql += " and MailTmpl.IsAuto = '"+mailTmpl.getIsAuto()+"'";
		if(null != mailTmpl.getStartAutoStart()) 
			sql += " and MailTmpl.AutoStart >= '"+GenericUtil.dateFormat(mailTmpl.getStartAutoStart(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != mailTmpl.getEndAutoStart()) 
			sql += " and MailTmpl.AutoStart <= '"+GenericUtil.dateFormat(mailTmpl.getEndAutoStart(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != mailTmpl.getAutoCycle())
			sql += " and MailTmpl.AutoCycle = '"+mailTmpl.getAutoCycle()+"'";
		if(null != mailTmpl.getStatus())
			sql += " and MailTmpl.Status = '"+mailTmpl.getStatus()+"'";
		if(null != mailTmpl.getCreateBy())
			sql += " and MailTmpl.CreateBy = '"+mailTmpl.getCreateBy()+"'";
		if(null != mailTmpl.getStartCreateDate()) 
			sql += " and MailTmpl.CreateDate >= '"+GenericUtil.dateFormat(mailTmpl.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != mailTmpl.getEndCreateDate()) 
			sql += " and MailTmpl.CreateDate <= '"+GenericUtil.dateFormat(mailTmpl.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != mailTmpl.getLastUpd())
			sql += " and MailTmpl.LastUpd = '"+mailTmpl.getLastUpd()+"'";
		if(null != mailTmpl.getStartLastUpdDate()) 
			sql += " and MailTmpl.LastUpdDate >= '"+GenericUtil.dateFormat(mailTmpl.getStartLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != mailTmpl.getEndLastUpdDate()) 
			sql += " and MailTmpl.LastUpdDate <= '"+GenericUtil.dateFormat(mailTmpl.getEndLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";

		return sql;
	}

	public List<MailTmpl> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<MailTmpl> list = new ArrayList<MailTmpl>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				MailTmpl mailTmpl = new MailTmpl();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("TmplId") || _fields[i].equals("MailTmpl.TmplId"))
						mailTmpl.setTmplId(rs.getInt("TmplId"));
					else if(_fields[i].equals("CfgId") || _fields[i].equals("MailTmpl.CfgId"))
						mailTmpl.setCfgId(rs.getInt("CfgId"));
					else if(_fields[i].equals("UserId") || _fields[i].equals("MailTmpl.UserId"))
						mailTmpl.setUserId(rs.getInt("UserId"));
					else if(_fields[i].equals("TmplName") || _fields[i].equals("MailTmpl.TmplName"))
						mailTmpl.setTmplName(rs.getString("TmplName"));
					else if(_fields[i].equals("TmplText") || _fields[i].equals("MailTmpl.TmplText"))
						mailTmpl.setTmplText(rs.getString("TmplText"));
					else if(_fields[i].equals("Type") || _fields[i].equals("MailTmpl.Type"))
						mailTmpl.setType(rs.getInt("Type"));
					else if(_fields[i].equals("IsAuto") || _fields[i].equals("MailTmpl.IsAuto"))
						mailTmpl.setIsAuto(rs.getInt("IsAuto"));
					else if(_fields[i].equals("AutoStart") || _fields[i].equals("MailTmpl.AutoStart"))
						mailTmpl.setAutoStart(rs.getTimestamp("AutoStart"));
					else if(_fields[i].equals("AutoCycle") || _fields[i].equals("MailTmpl.AutoCycle"))
						mailTmpl.setAutoCycle(rs.getInt("AutoCycle"));
					else if(_fields[i].equals("Status") || _fields[i].equals("MailTmpl.Status"))
						mailTmpl.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("MailTmpl.CreateBy"))
						mailTmpl.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("MailTmpl.CreateDate"))
						mailTmpl.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("MailTmpl.LastUpd"))
						mailTmpl.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("MailTmpl.LastUpdDate"))
						mailTmpl.setLastUpdDate(rs.getTimestamp("LastUpdDate"));

				}
				list.add(mailTmpl);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailTmplHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into MailTmpl("+fields.replaceAll("MailTmpl\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(MailTmpl mailTmpl,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("TmplId") || _fields[i].equals("MailTmpl.TmplId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailTmpl.getTmplId());
					}
					else if(_fields[i].equals("CfgId") || _fields[i].equals("MailTmpl.CfgId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailTmpl.getCfgId());
					}
					else if(_fields[i].equals("UserId") || _fields[i].equals("MailTmpl.UserId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailTmpl.getUserId());
					}
					else if(_fields[i].equals("TmplName") || _fields[i].equals("MailTmpl.TmplName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailTmpl.getTmplName());
					}
					else if(_fields[i].equals("TmplText") || _fields[i].equals("MailTmpl.TmplText")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailTmpl.getTmplText());
					}
					else if(_fields[i].equals("Type") || _fields[i].equals("MailTmpl.Type")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailTmpl.getType());
					}
					else if(_fields[i].equals("IsAuto") || _fields[i].equals("MailTmpl.IsAuto")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailTmpl.getIsAuto());
					}
					else if(_fields[i].equals("AutoStart") || _fields[i].equals("MailTmpl.AutoStart")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(mailTmpl.getAutoStart().getTime()));
					}
					else if(_fields[i].equals("AutoCycle") || _fields[i].equals("MailTmpl.AutoCycle")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailTmpl.getAutoCycle());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("MailTmpl.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailTmpl.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("MailTmpl.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailTmpl.getCreateBy());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("MailTmpl.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(mailTmpl.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("MailTmpl.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailTmpl.getLastUpd());
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("MailTmpl.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(mailTmpl.getLastUpdDate().getTime()));
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("MailTmplHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update MailTmpl set ";
		String[] _fields = fields.replaceAll("MailTmpl\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("CfgId") || _fields[i].equals("MailTmpl.CfgId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("UserId") || _fields[i].equals("MailTmpl.UserId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("TmplName") || _fields[i].equals("MailTmpl.TmplName"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("TmplText") || _fields[i].equals("MailTmpl.TmplText"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Type") || _fields[i].equals("MailTmpl.Type"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IsAuto") || _fields[i].equals("MailTmpl.IsAuto"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("AutoStart") || _fields[i].equals("MailTmpl.AutoStart"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("AutoCycle") || _fields[i].equals("MailTmpl.AutoCycle"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("MailTmpl.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("MailTmpl.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("MailTmpl.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("MailTmpl.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpdDate") || _fields[i].equals("MailTmpl.LastUpdDate"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(MailTmpl mailTmpl,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("CfgId") || _fields[i].equals("MailTmpl.CfgId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailTmpl.getCfgId());
					}
					else if(_fields[i].equals("UserId") || _fields[i].equals("MailTmpl.UserId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailTmpl.getUserId());
					}
					else if(_fields[i].equals("TmplName") || _fields[i].equals("MailTmpl.TmplName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailTmpl.getTmplName());
					}
					else if(_fields[i].equals("TmplText") || _fields[i].equals("MailTmpl.TmplText")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailTmpl.getTmplText());
					}
					else if(_fields[i].equals("Type") || _fields[i].equals("MailTmpl.Type")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailTmpl.getType());
					}
					else if(_fields[i].equals("IsAuto") || _fields[i].equals("MailTmpl.IsAuto")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailTmpl.getIsAuto());
					}
					else if(_fields[i].equals("AutoStart") || _fields[i].equals("MailTmpl.AutoStart")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(mailTmpl.getAutoStart().getTime()));
					}
					else if(_fields[i].equals("AutoCycle") || _fields[i].equals("MailTmpl.AutoCycle")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailTmpl.getAutoCycle());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("MailTmpl.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailTmpl.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("MailTmpl.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailTmpl.getCreateBy());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("MailTmpl.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(mailTmpl.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("MailTmpl.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailTmpl.getLastUpd());
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("MailTmpl.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(mailTmpl.getLastUpdDate().getTime()));
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("MailTmplHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(MailTmpl mailTmpl) {
		String _fields = "";
		if(null != mailTmpl.getTmplId())
			_fields += "MailTmpl.TmplId,";
		if(null != mailTmpl.getCfgId())
			_fields += "MailTmpl.CfgId,";
		if(null != mailTmpl.getUserId())
			_fields += "MailTmpl.UserId,";
		if(null != mailTmpl.getTmplName())
			_fields += "MailTmpl.TmplName,";
		if(null != mailTmpl.getTmplText())
			_fields += "MailTmpl.TmplText,";
		if(null != mailTmpl.getType())
			_fields += "MailTmpl.Type,";
		if(null != mailTmpl.getIsAuto())
			_fields += "MailTmpl.IsAuto,";
		if(null != mailTmpl.getAutoStart())
			_fields += "MailTmpl.AutoStart,";
		if(null != mailTmpl.getAutoCycle())
			_fields += "MailTmpl.AutoCycle,";
		if(null != mailTmpl.getStatus())
			_fields += "MailTmpl.Status,";
		if(null != mailTmpl.getCreateBy())
			_fields += "MailTmpl.CreateBy,";
		if(null != mailTmpl.getCreateDate())
			_fields += "MailTmpl.CreateDate,";
		if(null != mailTmpl.getLastUpd())
			_fields += "MailTmpl.LastUpd,";
		if(null != mailTmpl.getLastUpdDate())
			_fields += "MailTmpl.LastUpdDate,";

		return _fields.substring(0, _fields.length()-1);
	}
}