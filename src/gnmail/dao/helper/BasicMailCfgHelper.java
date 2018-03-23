package gnmail.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnmail.vo.MailCfg;

public class BasicMailCfgHelper implements SqlHelper {
	public String getSqlString() {
		return " from MailCfg where 1=1";
	}

	public String getOrderBy() {
		return " order by MailCfg.CfgId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((MailCfg)object);
	}

	public String getSql4Amount(MailCfg mailCfg) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(mailCfg);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((MailCfg)object);
	}

	public String getSql4Delete(MailCfg mailCfg) {
		return "delete from MailCfg where 1=1"+getSqlCondition(mailCfg);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((MailCfg)object,fields);
	}

	public String getSql4All(MailCfg mailCfg, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(mailCfg)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((MailCfg)object,pageVO,fields);
	}

	public String getSql4Pages(MailCfg mailCfg,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" MailCfg.CfgId "+ getSqlString()+getSqlCondition(mailCfg)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(mailCfg)+" and MailCfg.CfgId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((MailCfg)object);
	}

	public String getSqlCondition(MailCfg mailCfg) {
		String sql = "";
		if(null != mailCfg.getCfgId())
			sql += " and MailCfg.CfgId = '"+mailCfg.getCfgId()+"'";
		if(null != mailCfg.getUserId())
			sql += " and MailCfg.UserId = '"+mailCfg.getUserId()+"'";
		if(null != mailCfg.getMailAddr() && !mailCfg.getMailAddr().trim().equals(""))
			sql += " and MailCfg.MailAddr = '"+mailCfg.getMailAddr().trim()+"'";
		if(null != mailCfg.getPwd() && !mailCfg.getPwd().trim().equals(""))
			sql += " and MailCfg.Pwd = '"+mailCfg.getPwd().trim()+"'";
		if(null != mailCfg.getMailName() && !mailCfg.getMailName().trim().equals(""))
			sql += " and MailCfg.MailName = '"+mailCfg.getMailName().trim()+"'";
		if(null != mailCfg.getMailSign() && !mailCfg.getMailSign().trim().equals(""))
			sql += " and MailCfg.MailSign = '"+mailCfg.getMailSign().trim()+"'";
		if(null != mailCfg.getServIpAddr() && !mailCfg.getServIpAddr().trim().equals(""))
			sql += " and MailCfg.ServIpAddr = '"+mailCfg.getServIpAddr().trim()+"'";
		if(null != mailCfg.getSmpt() && !mailCfg.getSmpt().trim().equals(""))
			sql += " and MailCfg.Smpt = '"+mailCfg.getSmpt().trim()+"'";
		if(null != mailCfg.getPop3() && !mailCfg.getPop3().trim().equals(""))
			sql += " and MailCfg.Pop3 = '"+mailCfg.getPop3().trim()+"'";
		if(null != mailCfg.getRemark() && !mailCfg.getRemark().trim().equals(""))
			sql += " and MailCfg.Remark = '"+mailCfg.getRemark().trim()+"'";
		if(null != mailCfg.getStatus())
			sql += " and MailCfg.Status = '"+mailCfg.getStatus()+"'";
		if(null != mailCfg.getCreateBy())
			sql += " and MailCfg.CreateBy = '"+mailCfg.getCreateBy()+"'";
		if(null != mailCfg.getStartCreateDate()) 
			sql += " and MailCfg.CreateDate >= '"+GenericUtil.dateFormat(mailCfg.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != mailCfg.getEndCreateDate()) 
			sql += " and MailCfg.CreateDate <= '"+GenericUtil.dateFormat(mailCfg.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != mailCfg.getLastUpd())
			sql += " and MailCfg.LastUpd = '"+mailCfg.getLastUpd()+"'";
		if(null != mailCfg.getStartLastUpdDate()) 
			sql += " and MailCfg.LastUpdDate >= '"+GenericUtil.dateFormat(mailCfg.getStartLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != mailCfg.getEndLastUpdDate()) 
			sql += " and MailCfg.LastUpdDate <= '"+GenericUtil.dateFormat(mailCfg.getEndLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";

		return sql;
	}

	public List<MailCfg> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<MailCfg> list = new ArrayList<MailCfg>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				MailCfg mailCfg = new MailCfg();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("CfgId") || _fields[i].equals("MailCfg.CfgId"))
						mailCfg.setCfgId(rs.getInt("CfgId"));
					else if(_fields[i].equals("UserId") || _fields[i].equals("MailCfg.UserId"))
						mailCfg.setUserId(rs.getInt("UserId"));
					else if(_fields[i].equals("MailAddr") || _fields[i].equals("MailCfg.MailAddr"))
						mailCfg.setMailAddr(rs.getString("MailAddr"));
					else if(_fields[i].equals("Pwd") || _fields[i].equals("MailCfg.Pwd"))
						mailCfg.setPwd(rs.getString("Pwd"));
					else if(_fields[i].equals("MailName") || _fields[i].equals("MailCfg.MailName"))
						mailCfg.setMailName(rs.getString("MailName"));
					else if(_fields[i].equals("MailSign") || _fields[i].equals("MailCfg.MailSign"))
						mailCfg.setMailSign(rs.getString("MailSign"));
					else if(_fields[i].equals("ServIpAddr") || _fields[i].equals("MailCfg.ServIpAddr"))
						mailCfg.setServIpAddr(rs.getString("ServIpAddr"));
					else if(_fields[i].equals("Smpt") || _fields[i].equals("MailCfg.Smpt"))
						mailCfg.setSmpt(rs.getString("Smpt"));
					else if(_fields[i].equals("Pop3") || _fields[i].equals("MailCfg.Pop3"))
						mailCfg.setPop3(rs.getString("Pop3"));
					else if(_fields[i].equals("Remark") || _fields[i].equals("MailCfg.Remark"))
						mailCfg.setRemark(rs.getString("Remark"));
					else if(_fields[i].equals("Status") || _fields[i].equals("MailCfg.Status"))
						mailCfg.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("MailCfg.CreateBy"))
						mailCfg.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("MailCfg.CreateDate"))
						mailCfg.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("MailCfg.LastUpd"))
						mailCfg.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("MailCfg.LastUpdDate"))
						mailCfg.setLastUpdDate(rs.getTimestamp("LastUpdDate"));

				}
				list.add(mailCfg);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailCfgHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into MailCfg("+fields.replaceAll("MailCfg\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(MailCfg mailCfg,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("CfgId") || _fields[i].equals("MailCfg.CfgId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailCfg.getCfgId());
					}
					else if(_fields[i].equals("UserId") || _fields[i].equals("MailCfg.UserId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailCfg.getUserId());
					}
					else if(_fields[i].equals("MailAddr") || _fields[i].equals("MailCfg.MailAddr")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailCfg.getMailAddr());
					}
					else if(_fields[i].equals("Pwd") || _fields[i].equals("MailCfg.Pwd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailCfg.getPwd());
					}
					else if(_fields[i].equals("MailName") || _fields[i].equals("MailCfg.MailName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailCfg.getMailName());
					}
					else if(_fields[i].equals("MailSign") || _fields[i].equals("MailCfg.MailSign")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailCfg.getMailSign());
					}
					else if(_fields[i].equals("ServIpAddr") || _fields[i].equals("MailCfg.ServIpAddr")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailCfg.getServIpAddr());
					}
					else if(_fields[i].equals("Smpt") || _fields[i].equals("MailCfg.Smpt")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailCfg.getSmpt());
					}
					else if(_fields[i].equals("Pop3") || _fields[i].equals("MailCfg.Pop3")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailCfg.getPop3());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("MailCfg.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailCfg.getRemark());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("MailCfg.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailCfg.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("MailCfg.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailCfg.getCreateBy());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("MailCfg.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(mailCfg.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("MailCfg.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailCfg.getLastUpd());
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("MailCfg.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(mailCfg.getLastUpdDate().getTime()));
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("MailCfgHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update MailCfg set ";
		String[] _fields = fields.replaceAll("MailCfg\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("UserId") || _fields[i].equals("MailCfg.UserId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("MailAddr") || _fields[i].equals("MailCfg.MailAddr"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Pwd") || _fields[i].equals("MailCfg.Pwd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("MailName") || _fields[i].equals("MailCfg.MailName"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("MailSign") || _fields[i].equals("MailCfg.MailSign"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("ServIpAddr") || _fields[i].equals("MailCfg.ServIpAddr"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Smpt") || _fields[i].equals("MailCfg.Smpt"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Pop3") || _fields[i].equals("MailCfg.Pop3"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Remark") || _fields[i].equals("MailCfg.Remark"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("MailCfg.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("MailCfg.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("MailCfg.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("MailCfg.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpdDate") || _fields[i].equals("MailCfg.LastUpdDate"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(MailCfg mailCfg,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("UserId") || _fields[i].equals("MailCfg.UserId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailCfg.getUserId());
					}
					else if(_fields[i].equals("MailAddr") || _fields[i].equals("MailCfg.MailAddr")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailCfg.getMailAddr());
					}
					else if(_fields[i].equals("Pwd") || _fields[i].equals("MailCfg.Pwd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailCfg.getPwd());
					}
					else if(_fields[i].equals("MailName") || _fields[i].equals("MailCfg.MailName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailCfg.getMailName());
					}
					else if(_fields[i].equals("MailSign") || _fields[i].equals("MailCfg.MailSign")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailCfg.getMailSign());
					}
					else if(_fields[i].equals("ServIpAddr") || _fields[i].equals("MailCfg.ServIpAddr")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailCfg.getServIpAddr());
					}
					else if(_fields[i].equals("Smpt") || _fields[i].equals("MailCfg.Smpt")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailCfg.getSmpt());
					}
					else if(_fields[i].equals("Pop3") || _fields[i].equals("MailCfg.Pop3")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailCfg.getPop3());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("MailCfg.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, mailCfg.getRemark());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("MailCfg.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailCfg.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("MailCfg.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailCfg.getCreateBy());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("MailCfg.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(mailCfg.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("MailCfg.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, mailCfg.getLastUpd());
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("MailCfg.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(mailCfg.getLastUpdDate().getTime()));
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("MailCfgHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(MailCfg mailCfg) {
		String _fields = "";
		if(null != mailCfg.getCfgId())
			_fields += "MailCfg.CfgId,";
		if(null != mailCfg.getUserId())
			_fields += "MailCfg.UserId,";
		if(null != mailCfg.getMailAddr())
			_fields += "MailCfg.MailAddr,";
		if(null != mailCfg.getPwd())
			_fields += "MailCfg.Pwd,";
		if(null != mailCfg.getMailName())
			_fields += "MailCfg.MailName,";
		if(null != mailCfg.getMailSign())
			_fields += "MailCfg.MailSign,";
		if(null != mailCfg.getServIpAddr())
			_fields += "MailCfg.ServIpAddr,";
		if(null != mailCfg.getSmpt())
			_fields += "MailCfg.Smpt,";
		if(null != mailCfg.getPop3())
			_fields += "MailCfg.Pop3,";
		if(null != mailCfg.getRemark())
			_fields += "MailCfg.Remark,";
		if(null != mailCfg.getStatus())
			_fields += "MailCfg.Status,";
		if(null != mailCfg.getCreateBy())
			_fields += "MailCfg.CreateBy,";
		if(null != mailCfg.getCreateDate())
			_fields += "MailCfg.CreateDate,";
		if(null != mailCfg.getLastUpd())
			_fields += "MailCfg.LastUpd,";
		if(null != mailCfg.getLastUpdDate())
			_fields += "MailCfg.LastUpdDate,";

		return _fields.substring(0, _fields.length()-1);
	}
}