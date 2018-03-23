package gnwf.dao.helper;

import gnwf.vo.ShareRela;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;


public class BasicShareRelaHelper implements SqlHelper {
	public String getSqlString() {
		return " from ShareRela where 1=1";
	}

	public String getOrderBy() {
		return " order by ShareRela.WfDocId,ShareRela.UsrId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((ShareRela)object);
	}

	public String getSql4Amount(ShareRela shareRela) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(shareRela);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((ShareRela)object);
	}

	public String getSql4Delete(ShareRela shareRela) {
		return "delete from ShareRela where 1=1"+getSqlCondition(shareRela);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((ShareRela)object,fields);
	}

	public String getSql4All(ShareRela shareRela, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(shareRela)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((ShareRela)object,pageVO,fields);
	}

	public String getSql4Pages(ShareRela shareRela,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" ShareRela.FileNo "+ getSqlString()+getSqlCondition(shareRela)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(shareRela)+" and ShareRela.FileNo not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((ShareRela)object);
	}

	public String getSqlCondition(ShareRela shareRela) {
		String sql = "";
		if(null != shareRela.getWfDocId() )
			sql += " and ShareRela.WfDocId = '"+shareRela.getWfDocId()+"'";
		if(null != shareRela.getUsrId())
			sql += " and ShareRela.UsrId = '"+shareRela.getUsrId()+"'";
		if(null != shareRela.getRemark() && !shareRela.getRemark().trim().equals(""))
			sql += " and ShareRela.Remark = '"+shareRela.getRemark().trim()+"'";
		if(null != shareRela.getStatus())
			sql += " and ShareRela.Status = '"+shareRela.getStatus()+"'";
		if(null != shareRela.getCreateBy())
			sql += " and ShareRela.CreateBy = '"+shareRela.getCreateBy()+"'";
		if(null != shareRela.getStartCreateDate()) 
			sql += " and ShareRela.CreateDate >= '"+GenericUtil.dateFormat(shareRela.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != shareRela.getEndCreateDate()) 
			sql += " and ShareRela.CreateDate <= '"+GenericUtil.dateFormat(shareRela.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != shareRela.getLastUpd())
			sql += " and ShareRela.LastUpd = '"+shareRela.getLastUpd()+"'";
		if(null != shareRela.getStartLastDate()) 
			sql += " and ShareRela.LastDate >= '"+GenericUtil.dateFormat(shareRela.getStartLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != shareRela.getEndLastDate()) 
			sql += " and ShareRela.LastDate <= '"+GenericUtil.dateFormat(shareRela.getEndLastDate(), "yyyy-MM-dd 00:00:00")+"'";

		return sql;
	}

	public List<ShareRela> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<ShareRela> list = new ArrayList<ShareRela>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				ShareRela shareRela = new ShareRela();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("WfDocId") || _fields[i].equals("ShareRela.WfDocId"))
						shareRela.setWfDocId(rs.getInt("WfDocId"));
					else if(_fields[i].equals("UsrId") || _fields[i].equals("ShareRela.UsrId"))
						shareRela.setUsrId(rs.getInt("UsrId"));
					else if(_fields[i].equals("Remark") || _fields[i].equals("ShareRela.Remark"))
						shareRela.setRemark(rs.getString("Remark"));
					else if(_fields[i].equals("Status") || _fields[i].equals("ShareRela.Status"))
						shareRela.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("ShareRela.CreateBy"))
						shareRela.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("ShareRela.CreateDate"))
						shareRela.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("ShareRela.LastUpd"))
						shareRela.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("LastDate") || _fields[i].equals("ShareRela.LastDate"))
						shareRela.setLastDate(rs.getTimestamp("LastDate"));

				}
				list.add(shareRela);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ShareRelaHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into ShareRela("+fields.replaceAll("ShareRela\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(ShareRela shareRela,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("WfDocId") || _fields[i].equals("ShareRela.WfDocId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, shareRela.getWfDocId());
					}
					else if(_fields[i].equals("UsrId") || _fields[i].equals("ShareRela.UsrId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, shareRela.getUsrId());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("ShareRela.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, shareRela.getRemark());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("ShareRela.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, shareRela.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("ShareRela.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, shareRela.getCreateBy());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("ShareRela.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(shareRela.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("ShareRela.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, shareRela.getLastUpd());
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("ShareRela.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(shareRela.getLastDate().getTime()));
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("ShareRelaHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,ShareRela _shareRela) {
		String sql = "update ShareRela set ";
		String[] _fields = fields.replaceAll("ShareRela\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Remark") || _fields[i].equals("ShareRela.Remark"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("ShareRela.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("ShareRela.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("ShareRela.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("ShareRela.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastDate") || _fields[i].equals("ShareRela.LastDate"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
//		sql += " where "+key+" = '"+keyValue+"'";
		sql += " where ShareRela.WfDocId = '"+_shareRela.getWfDocId()+"'"+" and ShareRela.UsrId = '"+_shareRela.getUsrId()+"'";
		return sql;
	}

	public void pstmtUpdate(ShareRela shareRela,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
				    if(_fields[i].equals("Remark") || _fields[i].equals("ShareRela.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, shareRela.getRemark());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("ShareRela.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, shareRela.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("ShareRela.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, shareRela.getCreateBy());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("ShareRela.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(shareRela.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("ShareRela.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, shareRela.getLastUpd());
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("ShareRela.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(shareRela.getLastDate().getTime()));
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("ShareRelaHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(ShareRela shareRela) {
		String _fields = "";
		if(null != shareRela.getWfDocId())
			_fields += "ShareRela.WfDocId,";
		if(null != shareRela.getUsrId())
			_fields += "ShareRela.UsrId,";
		if(null != shareRela.getRemark())
			_fields += "ShareRela.Remark,";
		if(null != shareRela.getStatus())
			_fields += "ShareRela.Status,";
		if(null != shareRela.getCreateBy())
			_fields += "ShareRela.CreateBy,";
		if(null != shareRela.getCreateDate())
			_fields += "ShareRela.CreateDate,";
		if(null != shareRela.getLastUpd())
			_fields += "ShareRela.LastUpd,";
		if(null != shareRela.getLastDate())
			_fields += "ShareRela.LastDate,";

		return _fields.substring(0, _fields.length()-1);
	}

	@Override
	public String getUpdateSql(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}
}