package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfDoc;

public class BasicWfDocHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfDoc where 1=1";
	}

	public String getOrderBy() {
		return " order by WfDoc.DocId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfDoc)object);
	}

	public String getSql4Amount(WfDoc wfDoc) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfDoc);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfDoc)object);
	}

	public String getSql4Delete(WfDoc wfDoc) {
		return "delete from WfDoc where 1=1"+getSqlCondition(wfDoc);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfDoc)object,fields);
	}

	public String getSql4All(WfDoc wfDoc, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfDoc)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfDoc)object,pageVO,fields);
	}

	public String getSql4Pages(WfDoc wfDoc,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfDoc.DocId "+ getSqlString()+getSqlCondition(wfDoc)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfDoc)+" and WfDoc.DocId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfDoc)object);
	}

	public String getSqlCondition(WfDoc wfDoc) {
		String sql = "";
		if(null != wfDoc.getDocId())
			sql += " and WfDoc.DocId = '"+wfDoc.getDocId()+"'";
		if(null != wfDoc.getFileNo() && !wfDoc.getFileNo().trim().equals(""))
			sql += " and WfDoc.FileNo = '"+wfDoc.getFileNo().trim()+"'";
		if(null != wfDoc.getTaskId())
			sql += " and WfDoc.TaskId = '"+wfDoc.getTaskId()+"'";
		if(null != wfDoc.getStatus())
			sql += " and WfDoc.Status = '"+wfDoc.getStatus()+"'";
		if(null != wfDoc.getCreateBy())
			sql += " and WfDoc.CreateBy = '"+wfDoc.getCreateBy()+"'";
		if(null != wfDoc.getLastUpd())
			sql += " and WfDoc.LastUpd = '"+wfDoc.getLastUpd()+"'";
		if(null != wfDoc.getCateId())
			sql += " and WfDoc.CateId = '"+wfDoc.getCateId()+"'";
		if(null != wfDoc.getFlowId())
			sql += " and WfDoc.FlowId = '"+wfDoc.getFlowId()+"'";
		if(null != wfDoc.getDeptDocId())
			sql += " and WfDoc.DeptDocId = '"+wfDoc.getDeptDocId()+"'";
		if(null != wfDoc.getWfNo() && !wfDoc.getWfNo().trim().equals(""))
			sql += " and WfDoc.WfNo = '"+wfDoc.getWfNo().trim()+"'";
		if(null != wfDoc.getUriLink() && !wfDoc.getUriLink().trim().equals(""))
			sql += " and WfDoc.UriLink = '"+wfDoc.getUriLink().trim()+"'";
		if(null != wfDoc.getStartCreateDate()) 
			sql += " and WfDoc.CreateDate >= '"+GenericUtil.dateFormat(wfDoc.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfDoc.getEndCreateDate()) 
			sql += " and WfDoc.CreateDate <= '"+GenericUtil.dateFormat(wfDoc.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfDoc.getStartLastUpdDate()) 
			sql += " and WfDoc.LastUpdDate >= '"+GenericUtil.dateFormat(wfDoc.getStartLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfDoc.getEndLastUpdDate()) 
			sql += " and WfDoc.LastUpdDate <= '"+GenericUtil.dateFormat(wfDoc.getEndLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfDoc.getDocName() && !wfDoc.getDocName().trim().equals(""))
			sql += " and WfDoc.DocName like '%"+wfDoc.getDocName().trim()+"%'";
		if(null != wfDoc.getDocVer() && !wfDoc.getDocVer().trim().equals(""))
			sql += " and WfDoc.DocVer = '"+wfDoc.getDocVer().trim()+"'";
		if(null != wfDoc.getProjectNo() && !wfDoc.getProjectNo().trim().equals(""))
			sql += " and WfDoc.ProjectNo = '"+wfDoc.getProjectNo().trim()+"'";
		
		if(null != wfDoc.getDocType() )
			sql += " and WfDoc.DocType = '"+wfDoc.getDocType()+"'";
		

		return sql;
	}

	public List<WfDoc> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfDoc> list = new ArrayList<WfDoc>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfDoc wfDoc = new WfDoc();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("DocId") || _fields[i].equals("WfDoc.DocId"))
						wfDoc.setDocId(rs.getInt("DocId"));
					else if(_fields[i].equals("TaskId") || _fields[i].equals("WfDoc.TaskId"))
						wfDoc.setTaskId(rs.getInt("TaskId"));
					else if(_fields[i].equals("Status") || _fields[i].equals("WfDoc.Status"))
						wfDoc.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfDoc.CreateBy"))
						wfDoc.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfDoc.LastUpd"))
						wfDoc.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("CateId") || _fields[i].equals("WfDoc.CateId"))
						wfDoc.setCateId(rs.getInt("CateId"));
					else if(_fields[i].equals("FlowId") || _fields[i].equals("WfDoc.FlowId"))
						wfDoc.setFlowId(rs.getInt("FlowId"));
					else if(_fields[i].equals("DeptDocId") || _fields[i].equals("WfDoc.DeptDocId"))
						wfDoc.setDeptDocId(rs.getInt("DeptDocId"));
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfDoc.WfNo"))
						wfDoc.setWfNo(rs.getString("WfNo"));
					else if(_fields[i].equals("UriLink") || _fields[i].equals("WfDoc.UriLink"))
						wfDoc.setUriLink(rs.getString("UriLink"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfDoc.CreateDate"))
						wfDoc.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfDoc.LastUpdDate"))
						wfDoc.setLastUpdDate(rs.getTimestamp("LastUpdDate"));
					else if(_fields[i].equals("DocName") || _fields[i].equals("WfDoc.DocName"))
						wfDoc.setDocName(rs.getString("DocName"));
					else if(_fields[i].equals("DocVer") || _fields[i].equals("WfDoc.DocVer"))
						wfDoc.setDocVer(rs.getString("DocVer"));
					
					
					else if(_fields[i].equals("ProjectNo") || _fields[i].equals("WfDoc.ProjectNo"))
						wfDoc.setProjectNo(rs.getString("ProjectNo"));
					
					else if(_fields[i].equals("DocType") || _fields[i].equals("WfDoc.DocType"))
						wfDoc.setDocType(rs.getInt("DocType"));

				}
				list.add(wfDoc);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfDocHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfDoc("+fields.replaceAll("WfDoc\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfDoc wfDoc,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("DocId") || _fields[i].equals("WfDoc.DocId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfDoc.getDocId());
					}
					else if(_fields[i].equals("FileNo") || _fields[i].equals("WfDoc.FileNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfDoc.getFileNo());
					}
					else if(_fields[i].equals("TaskId") || _fields[i].equals("WfDoc.TaskId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfDoc.getTaskId());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("WfDoc.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfDoc.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfDoc.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfDoc.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfDoc.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfDoc.getLastUpd());
					}
					else if(_fields[i].equals("CateId") || _fields[i].equals("WfDoc.CateId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfDoc.getCateId());
					}
					else if(_fields[i].equals("FlowId") || _fields[i].equals("WfDoc.FlowId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfDoc.getFlowId());
					}
					else if(_fields[i].equals("DeptDocId") || _fields[i].equals("WfDoc.DeptDocId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfDoc.getDeptDocId());
					}
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfDoc.WfNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfDoc.getWfNo());
					}
					else if(_fields[i].equals("UriLink") || _fields[i].equals("WfDoc.UriLink")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfDoc.getUriLink());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfDoc.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfDoc.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfDoc.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfDoc.getLastUpdDate().getTime()));
					}
					else if(_fields[i].equals("DocName") || _fields[i].equals("WfDoc.DocName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfDoc.getDocName());
					}
					else if(_fields[i].equals("DocVer") || _fields[i].equals("WfDoc.DocVer")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfDoc.getDocVer());
					}
					else if(_fields[i].equals("ProjectNo") || _fields[i].equals("WfDoc.ProjectNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfDoc.getProjectNo());
					}
					
					
					else if(_fields[i].equals("DocType") || _fields[i].equals("WfDoc.DocType")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfDoc.getDocType());
					}
					

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfDocHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfDoc set ";
		String[] _fields = fields.replaceAll("WfDoc\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Status") || _fields[i].equals("WfDoc.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("WfDoc.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("WfDoc.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CateId") || _fields[i].equals("WfDoc.CateId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("FlowId") || _fields[i].equals("WfDoc.FlowId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("DeptDocId") || _fields[i].equals("WfDoc.DeptDocId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("UriLink") || _fields[i].equals("WfDoc.UriLink"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("WfDoc.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfDoc.LastUpdDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("DocName") || _fields[i].equals("WfDoc.DocName"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("DocVer") || _fields[i].equals("WfDoc.DocVer"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("ProjectNo") || _fields[i].equals("WfDoc.ProjectNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("DocType") || _fields[i].equals("WfDoc.DocType"))
						sql += _fields[i] + " = ?,";
					
				

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfDoc wfDoc,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Status") || _fields[i].equals("WfDoc.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfDoc.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfDoc.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfDoc.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfDoc.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfDoc.getLastUpd());
					}
					else if(_fields[i].equals("CateId") || _fields[i].equals("WfDoc.CateId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfDoc.getCateId());
					}
					else if(_fields[i].equals("FlowId") || _fields[i].equals("WfDoc.FlowId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfDoc.getFlowId());
					}
					else if(_fields[i].equals("DeptDocId") || _fields[i].equals("WfDoc.DeptDocId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfDoc.getDeptDocId());
					}
					else if(_fields[i].equals("UriLink") || _fields[i].equals("WfDoc.UriLink")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfDoc.getUriLink());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfDoc.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfDoc.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfDoc.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfDoc.getLastUpdDate().getTime()));
					}
					else if(_fields[i].equals("DocName") || _fields[i].equals("WfDoc.DocName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfDoc.getDocName());
					}
					else if(_fields[i].equals("DocVer") || _fields[i].equals("WfDoc.DocVer")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfDoc.getDocVer());
					}
					else if(_fields[i].equals("ProjectNo") || _fields[i].equals("WfDoc.ProjectNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfDoc.getProjectNo());
					}
					
					else if(_fields[i].equals("DocType") || _fields[i].equals("WfDoc.DocType")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfDoc.getDocType());
					}
					
			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfDocHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfDoc wfDoc) {
		String _fields = "";
		if(null != wfDoc.getDocId())
			_fields += "WfDoc.DocId,";
		if(null != wfDoc.getFileNo())
			_fields += "WfDoc.FileNo,";
		if(null != wfDoc.getTaskId())
			_fields += "WfDoc.TaskId,";
		if(null != wfDoc.getStatus())
			_fields += "WfDoc.Status,";
		if(null != wfDoc.getCreateBy())
			_fields += "WfDoc.CreateBy,";
		if(null != wfDoc.getLastUpd())
			_fields += "WfDoc.LastUpd,";
		if(null != wfDoc.getCateId())
			_fields += "WfDoc.CateId,";
		if(null != wfDoc.getFlowId())
			_fields += "WfDoc.FlowId,";
		if(null != wfDoc.getDeptDocId())
			_fields += "WfDoc.DeptDocId,";
		if(null != wfDoc.getWfNo())
			_fields += "WfDoc.WfNo,";
		if(null != wfDoc.getUriLink())
			_fields += "WfDoc.UriLink,";
		if(null != wfDoc.getCreateDate())
			_fields += "WfDoc.CreateDate,";
		if(null != wfDoc.getLastUpdDate())
			_fields += "WfDoc.LastUpdDate,";
		if(null != wfDoc.getDocName())
			_fields += "WfDoc.DocName,";
		if(null != wfDoc.getDocVer())
			_fields += "WfDoc.DocVer,";
		if(null != wfDoc.getProjectNo())
			_fields += "WfDoc.ProjectNo,";
		if(null != wfDoc.getDocType())
			_fields += "WfDoc.DocType,";
		

		return _fields.substring(0, _fields.length()-1);
	}
}