package zrsy.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrsy.vo.IpAddr;

public class BasicIpAddrHelper implements SqlHelper {
	public String getSqlString() {
		return " from IpAddr where 1=1";
	}

	public String getOrderBy() {
		return " order by IpAddr.IpId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((IpAddr)object);
	}

	public String getSql4Amount(IpAddr ipAddr) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(ipAddr);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((IpAddr)object);
	}

	public String getSql4Delete(IpAddr ipAddr) {
		return "delete from IpAddr where 1=1"+getSqlCondition(ipAddr);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((IpAddr)object,fields);
	}

	public String getSql4All(IpAddr ipAddr, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(ipAddr)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((IpAddr)object,pageVO,fields);
	}

	public String getSql4Pages(IpAddr ipAddr,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" IpAddr.IpId "+ getSqlString()+getSqlCondition(ipAddr)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(ipAddr)+" and IpAddr.IpId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((IpAddr)object);
	}

	public String getSqlCondition(IpAddr ipAddr) {
		String sql = "";
		if(null != ipAddr.getStartCreateDate()) 
			sql += " and IpAddr.CreateDate >= '"+GenericUtil.dateFormat(ipAddr.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != ipAddr.getEndCreateDate()) 
			sql += " and IpAddr.CreateDate <= '"+GenericUtil.dateFormat(ipAddr.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != ipAddr.getStartLastDate()) 
			sql += " and IpAddr.LastDate >= '"+GenericUtil.dateFormat(ipAddr.getStartLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != ipAddr.getEndLastDate()) 
			sql += " and IpAddr.LastDate <= '"+GenericUtil.dateFormat(ipAddr.getEndLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != ipAddr.getIpId())
			sql += " and IpAddr.IpId = '"+ipAddr.getIpId()+"'";
		if(null != ipAddr.getIsWide())
			sql += " and IpAddr.IsWide = '"+ipAddr.getIsWide()+"'";
		if(null != ipAddr.getStatus())
			sql += " and IpAddr.Status = '"+ipAddr.getStatus()+"'";
		if(null != ipAddr.getCreateBy())
			sql += " and IpAddr.CreateBy = '"+ipAddr.getCreateBy()+"'";
		if(null != ipAddr.getLastUpd())
			sql += " and IpAddr.LastUpd = '"+ipAddr.getLastUpd()+"'";
		if(null != ipAddr.getIpSegment() && !ipAddr.getIpSegment().trim().equals(""))
			sql += " and IpAddr.IpSegment = '"+ipAddr.getIpSegment().trim()+"'";
		if(null != ipAddr.getAddrDesc() && !ipAddr.getAddrDesc().trim().equals(""))
			sql += " and IpAddr.AddrDesc = '"+ipAddr.getAddrDesc().trim()+"'";

		return sql;
	}

	public List<IpAddr> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<IpAddr> list = new ArrayList<IpAddr>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				IpAddr ipAddr = new IpAddr();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("CreateDate") || _fields[i].equals("IpAddr.CreateDate"))
						ipAddr.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastDate") || _fields[i].equals("IpAddr.LastDate"))
						ipAddr.setLastDate(rs.getTimestamp("LastDate"));
					else if(_fields[i].equals("IpId") || _fields[i].equals("IpAddr.IpId"))
						ipAddr.setIpId(rs.getInt("IpId"));
					else if(_fields[i].equals("IsWide") || _fields[i].equals("IpAddr.IsWide"))
						ipAddr.setIsWide(rs.getInt("IsWide"));
					else if(_fields[i].equals("Status") || _fields[i].equals("IpAddr.Status"))
						ipAddr.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("IpAddr.CreateBy"))
						ipAddr.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("IpAddr.LastUpd"))
						ipAddr.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("IpSegment") || _fields[i].equals("IpAddr.IpSegment"))
						ipAddr.setIpSegment(rs.getString("IpSegment"));
					else if(_fields[i].equals("AddrDesc") || _fields[i].equals("IpAddr.AddrDesc"))
						ipAddr.setAddrDesc(rs.getString("AddrDesc"));

				}
				list.add(ipAddr);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("IpAddrHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into [ZrSy].[dbo].[IpAddr]("+fields.replaceAll("IpAddr\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(IpAddr ipAddr,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("CreateDate") || _fields[i].equals("IpAddr.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(ipAddr.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("IpAddr.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(ipAddr.getLastDate().getTime()));
					}
					else if(_fields[i].equals("IpId") || _fields[i].equals("IpAddr.IpId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, ipAddr.getIpId());
					}
					else if(_fields[i].equals("IsWide") || _fields[i].equals("IpAddr.IsWide")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, ipAddr.getIsWide());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("IpAddr.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, ipAddr.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("IpAddr.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, ipAddr.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("IpAddr.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, ipAddr.getLastUpd());
					}
					else if(_fields[i].equals("IpSegment") || _fields[i].equals("IpAddr.IpSegment")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, ipAddr.getIpSegment());
					}
					else if(_fields[i].equals("AddrDesc") || _fields[i].equals("IpAddr.AddrDesc")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, ipAddr.getAddrDesc());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			e.printStackTrace();
			Logger.getLogger(this.getClass()).error("IpAddrHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update [ZrSy].[dbo].[IpAddr] set ";
		String[] _fields = fields.replaceAll("IpAddr\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("CreateDate") || _fields[i].equals("IpAddr.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastDate") || _fields[i].equals("IpAddr.LastDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IsWide") || _fields[i].equals("IpAddr.IsWide"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("IpAddr.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("IpAddr.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("IpAddr.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IpSegment") || _fields[i].equals("IpAddr.IpSegment"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("AddrDesc") || _fields[i].equals("IpAddr.AddrDesc"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(IpAddr ipAddr,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("CreateDate") || _fields[i].equals("IpAddr.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(ipAddr.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("IpAddr.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(ipAddr.getLastDate().getTime()));
					}
					else if(_fields[i].equals("IsWide") || _fields[i].equals("IpAddr.IsWide")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, ipAddr.getIsWide());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("IpAddr.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, ipAddr.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("IpAddr.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, ipAddr.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("IpAddr.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, ipAddr.getLastUpd());
					}
					else if(_fields[i].equals("IpSegment") || _fields[i].equals("IpAddr.IpSegment")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, ipAddr.getIpSegment());
					}
					else if(_fields[i].equals("AddrDesc") || _fields[i].equals("IpAddr.AddrDesc")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, ipAddr.getAddrDesc());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("IpAddrHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(IpAddr ipAddr) {
		String _fields = "";
		if(null != ipAddr.getCreateDate())
			_fields += "IpAddr.CreateDate,";
		if(null != ipAddr.getLastDate())
			_fields += "IpAddr.LastDate,";
		if(null != ipAddr.getIpId())
			_fields += "IpAddr.IpId,";
		if(null != ipAddr.getIsWide())
			_fields += "IpAddr.IsWide,";
		if(null != ipAddr.getStatus())
			_fields += "IpAddr.Status,";
		if(null != ipAddr.getCreateBy())
			_fields += "IpAddr.CreateBy,";
		if(null != ipAddr.getLastUpd())
			_fields += "IpAddr.LastUpd,";
		if(null != ipAddr.getIpSegment())
			_fields += "IpAddr.IpSegment,";
		if(null != ipAddr.getAddrDesc())
			_fields += "IpAddr.AddrDesc,";

		return _fields.substring(0, _fields.length()-1);
	}
}