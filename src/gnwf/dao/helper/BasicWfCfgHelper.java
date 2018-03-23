package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfCfg;

public class BasicWfCfgHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfCfg where 1=1";
	}

	public String getOrderBy() {
		return " order by WfCfg.FlowId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfCfg)object);
	}

	public String getSql4Amount(WfCfg wfCfg) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfCfg);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfCfg)object);
	}

	public String getSql4Delete(WfCfg wfCfg) {
		return "delete from WfCfg where 1=1"+getSqlCondition(wfCfg);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfCfg)object,fields);
	}

	public String getSql4All(WfCfg wfCfg, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfCfg)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfCfg)object,pageVO,fields);
	}

	public String getSql4Pages(WfCfg wfCfg,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfCfg.FlowId "+ getSqlString()+getSqlCondition(wfCfg)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfCfg)+" and WfCfg.FlowId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfCfg)object);
	}

	public String getSqlCondition(WfCfg wfCfg) {
		String sql = "";
		if(null != wfCfg.getFlowId())
			sql += " and WfCfg.FlowId = '"+wfCfg.getFlowId()+"'";
		if(null != wfCfg.getComId())
			sql += " and WfCfg.ComId = '"+wfCfg.getComId()+"'";
		if(null != wfCfg.getDeptId())
			sql += " and WfCfg.DeptId = '"+wfCfg.getDeptId()+"'";
		if(null != wfCfg.getCateId())
			sql += " and WfCfg.CateId = '"+wfCfg.getCateId()+"'";
		if(null != wfCfg.getIsFirstStep())
			sql += " and WfCfg.IsFirstStep = '"+wfCfg.getIsFirstStep()+"'";
		if(null != wfCfg.getSphere())
			sql += " and WfCfg.Sphere = '"+wfCfg.getSphere()+"'";
		if(null != wfCfg.getRelateId())
			sql += " and WfCfg.RelateId = '"+wfCfg.getRelateId()+"'";
		if(null != wfCfg.getLimit())
			sql += " and WfCfg.Limit = '"+wfCfg.getLimit()+"'";
		if(null != wfCfg.getStatus())
			sql += " and WfCfg.Status = '"+wfCfg.getStatus()+"'";
		if(null != wfCfg.getCreateBy())
			sql += " and WfCfg.CreateBy = '"+wfCfg.getCreateBy()+"'";
		if(null != wfCfg.getLastUpd())
			sql += " and WfCfg.LastUpd = '"+wfCfg.getLastUpd()+"'";
		if(null != wfCfg.getHasQuesMoudle())
			sql += " and WfCfg.HasQuesMoudle = '"+wfCfg.getHasQuesMoudle()+"'";
		if(null != wfCfg.getScheCfgId())
			sql += " and WfCfg.ScheCfgId = '"+wfCfg.getScheCfgId()+"'";
		if(null != wfCfg.getStartCreateDate()) 
			sql += " and WfCfg.CreateDate >= '"+GenericUtil.dateFormat(wfCfg.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfCfg.getEndCreateDate()) 
			sql += " and WfCfg.CreateDate <= '"+GenericUtil.dateFormat(wfCfg.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfCfg.getStartLastUpdDate()) 
			sql += " and WfCfg.LastUpdDate >= '"+GenericUtil.dateFormat(wfCfg.getStartLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfCfg.getEndLastUpdDate()) 
			sql += " and WfCfg.LastUpdDate <= '"+GenericUtil.dateFormat(wfCfg.getEndLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfCfg.getFlowDesc() && !wfCfg.getFlowDesc().trim().equals(""))
			sql += " and WfCfg.FlowDesc = '"+wfCfg.getFlowDesc().trim()+"'";
		if(null != wfCfg.getFlowName() && !wfCfg.getFlowName().trim().equals(""))
			sql += " and WfCfg.FlowName = '"+wfCfg.getFlowName().trim()+"'";
		if(null != wfCfg.getFlowUri() && !wfCfg.getFlowUri().trim().equals(""))
			sql += " and WfCfg.FlowUri = '"+wfCfg.getFlowUri().trim()+"'";
		if(null != wfCfg.getPrintUri() && !wfCfg.getPrintUri().trim().equals(""))
			sql += " and WfCfg.PrintUri = '"+wfCfg.getPrintUri().trim()+"'";
		if(null != wfCfg.getSpecialClass() && !wfCfg.getSpecialClass().trim().equals(""))
			sql += " and WfCfg.SpecialClass = '"+wfCfg.getSpecialClass().trim()+"'";
		if(null != wfCfg.getAddRdExtendUri() && !wfCfg.getAddRdExtendUri().trim().equals(""))
			sql += " and WfCfg.AddRdExtendUri = '"+wfCfg.getAddRdExtendUri().trim()+"'";
		if(null != wfCfg.getFlowCode() && !wfCfg.getFlowCode().trim().equals(""))
			sql += " and WfCfg.FlowCode = '"+wfCfg.getFlowCode().trim()+"'";
		
		if(null != wfCfg.getNeedQues())
			sql += " and WfCfg.NeedQues = '"+wfCfg.getNeedQues()+"'";
		

		return sql;
	}

	public List<WfCfg> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfCfg> list = new ArrayList<WfCfg>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfCfg wfCfg = new WfCfg();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("FlowId") || _fields[i].equals("WfCfg.FlowId"))
						wfCfg.setFlowId(rs.getInt("FlowId"));
					else if(_fields[i].equals("ComId") || _fields[i].equals("WfCfg.ComId"))
						wfCfg.setComId(rs.getInt("ComId"));
					else if(_fields[i].equals("DeptId") || _fields[i].equals("WfCfg.DeptId"))
						wfCfg.setDeptId(rs.getInt("DeptId"));
					else if(_fields[i].equals("CateId") || _fields[i].equals("WfCfg.CateId"))
						wfCfg.setCateId(rs.getInt("CateId"));
					else if(_fields[i].equals("IsFirstStep") || _fields[i].equals("WfCfg.IsFirstStep"))
						wfCfg.setIsFirstStep(rs.getInt("IsFirstStep"));
					else if(_fields[i].equals("Sphere") || _fields[i].equals("WfCfg.Sphere"))
						wfCfg.setSphere(rs.getInt("Sphere"));
					else if(_fields[i].equals("RelateId") || _fields[i].equals("WfCfg.RelateId"))
						wfCfg.setRelateId(rs.getInt("RelateId"));
					else if(_fields[i].equals("Limit") || _fields[i].equals("WfCfg.Limit"))
						wfCfg.setLimit(rs.getInt("Limit"));
					else if(_fields[i].equals("Status") || _fields[i].equals("WfCfg.Status"))
						wfCfg.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfCfg.CreateBy"))
						wfCfg.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfCfg.LastUpd"))
						wfCfg.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("HasQuesMoudle") || _fields[i].equals("WfCfg.HasQuesMoudle"))
						wfCfg.setHasQuesMoudle(rs.getInt("HasQuesMoudle"));
					else if(_fields[i].equals("ScheCfgId") || _fields[i].equals("WfCfg.ScheCfgId"))
						wfCfg.setScheCfgId(rs.getInt("ScheCfgId"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfCfg.CreateDate"))
						wfCfg.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfCfg.LastUpdDate"))
						wfCfg.setLastUpdDate(rs.getTimestamp("LastUpdDate"));
					else if(_fields[i].equals("FlowDesc") || _fields[i].equals("WfCfg.FlowDesc"))
						wfCfg.setFlowDesc(rs.getString("FlowDesc"));
					else if(_fields[i].equals("FlowName") || _fields[i].equals("WfCfg.FlowName"))
						wfCfg.setFlowName(rs.getString("FlowName"));
					else if(_fields[i].equals("FlowUri") || _fields[i].equals("WfCfg.FlowUri"))
						wfCfg.setFlowUri(rs.getString("FlowUri"));
					else if(_fields[i].equals("PrintUri") || _fields[i].equals("WfCfg.PrintUri"))
						wfCfg.setPrintUri(rs.getString("PrintUri"));
					else if(_fields[i].equals("SpecialClass") || _fields[i].equals("WfCfg.SpecialClass"))
						wfCfg.setSpecialClass(rs.getString("SpecialClass"));
					else if(_fields[i].equals("AddRdExtendUri") || _fields[i].equals("WfCfg.AddRdExtendUri"))
						wfCfg.setAddRdExtendUri(rs.getString("AddRdExtendUri"));
					else if(_fields[i].equals("FlowCode") || _fields[i].equals("WfCfg.FlowCode"))
						wfCfg.setFlowCode(rs.getString("FlowCode"));

					else if(_fields[i].equals("NeedQues") || _fields[i].equals("WfCfg.NeedQues"))
						wfCfg.setNeedQues(rs.getInt("NeedQues"));
					
				}
				list.add(wfCfg);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfCfgHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfCfg("+fields.replaceAll("WfCfg\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfCfg wfCfg,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("FlowId") || _fields[i].equals("WfCfg.FlowId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCfg.getFlowId());
					}
					else if(_fields[i].equals("ComId") || _fields[i].equals("WfCfg.ComId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCfg.getComId());
					}
					else if(_fields[i].equals("DeptId") || _fields[i].equals("WfCfg.DeptId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCfg.getDeptId());
					}
					else if(_fields[i].equals("CateId") || _fields[i].equals("WfCfg.CateId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCfg.getCateId());
					}
					else if(_fields[i].equals("IsFirstStep") || _fields[i].equals("WfCfg.IsFirstStep")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCfg.getIsFirstStep());
					}
					else if(_fields[i].equals("Sphere") || _fields[i].equals("WfCfg.Sphere")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCfg.getSphere());
					}
					else if(_fields[i].equals("RelateId") || _fields[i].equals("WfCfg.RelateId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCfg.getRelateId());
					}
					else if(_fields[i].equals("Limit") || _fields[i].equals("WfCfg.Limit")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCfg.getLimit());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("WfCfg.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCfg.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfCfg.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCfg.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfCfg.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCfg.getLastUpd());
					}
					else if(_fields[i].equals("HasQuesMoudle") || _fields[i].equals("WfCfg.HasQuesMoudle")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCfg.getHasQuesMoudle());
					}
					else if(_fields[i].equals("ScheCfgId") || _fields[i].equals("WfCfg.ScheCfgId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCfg.getScheCfgId());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfCfg.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfCfg.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfCfg.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfCfg.getLastUpdDate().getTime()));
					}
					else if(_fields[i].equals("FlowDesc") || _fields[i].equals("WfCfg.FlowDesc")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfCfg.getFlowDesc());
					}
					else if(_fields[i].equals("FlowName") || _fields[i].equals("WfCfg.FlowName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfCfg.getFlowName());
					}
					else if(_fields[i].equals("FlowUri") || _fields[i].equals("WfCfg.FlowUri")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfCfg.getFlowUri());
					}
					else if(_fields[i].equals("PrintUri") || _fields[i].equals("WfCfg.PrintUri")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfCfg.getPrintUri());
					}
					else if(_fields[i].equals("SpecialClass") || _fields[i].equals("WfCfg.SpecialClass")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfCfg.getSpecialClass());
					}
					else if(_fields[i].equals("AddRdExtendUri") || _fields[i].equals("WfCfg.AddRdExtendUri")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfCfg.getAddRdExtendUri());
					}
					else if(_fields[i].equals("FlowCode") || _fields[i].equals("WfCfg.FlowCode")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfCfg.getFlowCode());
					}
					
					else if(_fields[i].equals("NeedQues") || _fields[i].equals("WfCfg.NeedQues")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCfg.getNeedQues());
					}
					

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfCfgHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfCfg set ";
		String[] _fields = fields.replaceAll("WfCfg\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ComId") || _fields[i].equals("WfCfg.ComId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("DeptId") || _fields[i].equals("WfCfg.DeptId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CateId") || _fields[i].equals("WfCfg.CateId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IsFirstStep") || _fields[i].equals("WfCfg.IsFirstStep"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Sphere") || _fields[i].equals("WfCfg.Sphere"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("RelateId") || _fields[i].equals("WfCfg.RelateId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Limit") || _fields[i].equals("WfCfg.Limit"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("WfCfg.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("WfCfg.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("WfCfg.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("HasQuesMoudle") || _fields[i].equals("WfCfg.HasQuesMoudle"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("ScheCfgId") || _fields[i].equals("WfCfg.ScheCfgId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("WfCfg.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfCfg.LastUpdDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("FlowDesc") || _fields[i].equals("WfCfg.FlowDesc"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("FlowName") || _fields[i].equals("WfCfg.FlowName"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("FlowUri") || _fields[i].equals("WfCfg.FlowUri"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("PrintUri") || _fields[i].equals("WfCfg.PrintUri"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("SpecialClass") || _fields[i].equals("WfCfg.SpecialClass"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("AddRdExtendUri") || _fields[i].equals("WfCfg.AddRdExtendUri"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("FlowCode") || _fields[i].equals("WfCfg.FlowCode"))
						sql += _fields[i] + " = ?,";

					if(_fields[i].equals("NeedQues") || _fields[i].equals("WfCfg.NeedQues"))
						sql += _fields[i] + " = ?,";
					
		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfCfg wfCfg,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ComId") || _fields[i].equals("WfCfg.ComId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCfg.getComId());
					}
					else if(_fields[i].equals("DeptId") || _fields[i].equals("WfCfg.DeptId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCfg.getDeptId());
					}
					else if(_fields[i].equals("CateId") || _fields[i].equals("WfCfg.CateId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCfg.getCateId());
					}
					else if(_fields[i].equals("IsFirstStep") || _fields[i].equals("WfCfg.IsFirstStep")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCfg.getIsFirstStep());
					}
					else if(_fields[i].equals("Sphere") || _fields[i].equals("WfCfg.Sphere")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCfg.getSphere());
					}
					else if(_fields[i].equals("RelateId") || _fields[i].equals("WfCfg.RelateId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCfg.getRelateId());
					}
					else if(_fields[i].equals("Limit") || _fields[i].equals("WfCfg.Limit")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCfg.getLimit());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("WfCfg.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCfg.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfCfg.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCfg.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfCfg.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCfg.getLastUpd());
					}
					else if(_fields[i].equals("HasQuesMoudle") || _fields[i].equals("WfCfg.HasQuesMoudle")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCfg.getHasQuesMoudle());
					}
					else if(_fields[i].equals("ScheCfgId") || _fields[i].equals("WfCfg.ScheCfgId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCfg.getScheCfgId());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfCfg.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfCfg.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfCfg.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfCfg.getLastUpdDate().getTime()));
					}
					else if(_fields[i].equals("FlowDesc") || _fields[i].equals("WfCfg.FlowDesc")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfCfg.getFlowDesc());
					}
					else if(_fields[i].equals("FlowName") || _fields[i].equals("WfCfg.FlowName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfCfg.getFlowName());
					}
					else if(_fields[i].equals("FlowUri") || _fields[i].equals("WfCfg.FlowUri")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfCfg.getFlowUri());
					}
					else if(_fields[i].equals("PrintUri") || _fields[i].equals("WfCfg.PrintUri")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfCfg.getPrintUri());
					}
					else if(_fields[i].equals("SpecialClass") || _fields[i].equals("WfCfg.SpecialClass")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfCfg.getSpecialClass());
					}
					else if(_fields[i].equals("AddRdExtendUri") || _fields[i].equals("WfCfg.AddRdExtendUri")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfCfg.getAddRdExtendUri());
					}
					else if(_fields[i].equals("FlowCode") || _fields[i].equals("WfCfg.FlowCode")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfCfg.getFlowCode());
					}

					else if(_fields[i].equals("NeedQues") || _fields[i].equals("WfCfg.NeedQues")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfCfg.getNeedQues());
					}
					
			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfCfgHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfCfg wfCfg) {
		String _fields = "";
		if(null != wfCfg.getFlowId())
			_fields += "WfCfg.FlowId,";
		if(null != wfCfg.getComId())
			_fields += "WfCfg.ComId,";
		if(null != wfCfg.getDeptId())
			_fields += "WfCfg.DeptId,";
		if(null != wfCfg.getCateId())
			_fields += "WfCfg.CateId,";
		if(null != wfCfg.getIsFirstStep())
			_fields += "WfCfg.IsFirstStep,";
		if(null != wfCfg.getSphere())
			_fields += "WfCfg.Sphere,";
		if(null != wfCfg.getRelateId())
			_fields += "WfCfg.RelateId,";
		if(null != wfCfg.getLimit())
			_fields += "WfCfg.Limit,";
		if(null != wfCfg.getStatus())
			_fields += "WfCfg.Status,";
		if(null != wfCfg.getCreateBy())
			_fields += "WfCfg.CreateBy,";
		if(null != wfCfg.getLastUpd())
			_fields += "WfCfg.LastUpd,";
		if(null != wfCfg.getHasQuesMoudle())
			_fields += "WfCfg.HasQuesMoudle,";
		if(null != wfCfg.getScheCfgId())
			_fields += "WfCfg.ScheCfgId,";
		if(null != wfCfg.getCreateDate())
			_fields += "WfCfg.CreateDate,";
		if(null != wfCfg.getLastUpdDate())
			_fields += "WfCfg.LastUpdDate,";
		if(null != wfCfg.getFlowDesc())
			_fields += "WfCfg.FlowDesc,";
		if(null != wfCfg.getFlowName())
			_fields += "WfCfg.FlowName,";
		if(null != wfCfg.getFlowUri())
			_fields += "WfCfg.FlowUri,";
		if(null != wfCfg.getPrintUri())
			_fields += "WfCfg.PrintUri,";
		if(null != wfCfg.getSpecialClass())
			_fields += "WfCfg.SpecialClass,";
		if(null != wfCfg.getAddRdExtendUri())
			_fields += "WfCfg.AddRdExtendUri,";
		if(null != wfCfg.getFlowCode())
			_fields += "WfCfg.FlowCode,";
		if(null != wfCfg.getNeedQues())
			_fields += "WfCfg.NeedQues,";
		

		return _fields.substring(0, _fields.length()-1);
	}
}