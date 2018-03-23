package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfStep;

public class BasicWfStepHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfStep where 1=1";
	}

	public String getOrderBy() {
		return " order by WfStep.StepId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfStep)object);
	}

	public String getSql4Amount(WfStep wfStep) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfStep);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfStep)object);
	}

	public String getSql4Delete(WfStep wfStep) {
		return "delete from WfStep where 1=1"+getSqlCondition(wfStep);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfStep)object,fields);
	}

	public String getSql4All(WfStep wfStep, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfStep)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfStep)object,pageVO,fields);
	}

	public String getSql4Pages(WfStep wfStep,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfStep.StepId "+ getSqlString()+getSqlCondition(wfStep)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfStep)+" and WfStep.StepId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfStep)object);
	}

	public String getSqlCondition(WfStep wfStep) {
		String sql = "";
		if(null != wfStep.getStepId())
			sql += " and WfStep.StepId = '"+wfStep.getStepId()+"'";
		if(null != wfStep.getFlowId())
			sql += " and WfStep.FlowId = '"+wfStep.getFlowId()+"'";
		if(null != wfStep.getPreStep())
			sql += " and WfStep.PreStep = '"+wfStep.getPreStep()+"'";
		if(null != wfStep.getStepType())
			sql += " and WfStep.StepType = '"+wfStep.getStepType()+"'";
		if(null != wfStep.getSort())
			sql += " and WfStep.Sort = '"+wfStep.getSort()+"'";
		if(null != wfStep.getIsAuto())
			sql += " and WfStep.IsAuto = '"+wfStep.getIsAuto()+"'";
		if(null != wfStep.getIsUpdForm())
			sql += " and WfStep.IsUpdForm = '"+wfStep.getIsUpdForm()+"'";
		if(null != wfStep.getIsSysFinsh())
			sql += " and WfStep.IsSysFinsh = '"+wfStep.getIsSysFinsh()+"'";
		if(null != wfStep.getTimeQty())
			sql += " and WfStep.TimeQty = '"+wfStep.getTimeQty()+"'";
		if(null != wfStep.getSelectCom())
			sql += " and WfStep.SelectCom = '"+wfStep.getSelectCom()+"'";
		if(null != wfStep.getIsLastStep())
			sql += " and WfStep.IsLastStep = '"+wfStep.getIsLastStep()+"'";
		if(null != wfStep.getStatus())
			sql += " and WfStep.Status = '"+wfStep.getStatus()+"'";
		if(null != wfStep.getCreateBy())
			sql += " and WfStep.CreateBy = '"+wfStep.getCreateBy()+"'";
		if(null != wfStep.getLastUpd())
			sql += " and WfStep.LastUpd = '"+wfStep.getLastUpd()+"'";
		if(null != wfStep.getSelectDept())
			sql += " and WfStep.SelectDept = '"+wfStep.getSelectDept()+"'";
		if(null != wfStep.getWaitAuxiliary())
			sql += " and WfStep.WaitAuxiliary = '"+wfStep.getWaitAuxiliary()+"'";
		if(null != wfStep.getStartCreateDate()) 
			sql += " and WfStep.CreateDate >= '"+GenericUtil.dateFormat(wfStep.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfStep.getEndCreateDate()) 
			sql += " and WfStep.CreateDate <= '"+GenericUtil.dateFormat(wfStep.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfStep.getStartLastUpdDate()) 
			sql += " and WfStep.LastUpdDate >= '"+GenericUtil.dateFormat(wfStep.getStartLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfStep.getEndLastUpdDate()) 
			sql += " and WfStep.LastUpdDate <= '"+GenericUtil.dateFormat(wfStep.getEndLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfStep.getStepName() && !wfStep.getStepName().trim().equals(""))
			sql += " and WfStep.StepName = '"+wfStep.getStepName().trim()+"'";
		if(null != wfStep.getStepDesc() && !wfStep.getStepDesc().trim().equals(""))
			sql += " and WfStep.StepDesc = '"+wfStep.getStepDesc().trim()+"'";
		if(null != wfStep.getStepUri() && !wfStep.getStepUri().trim().equals(""))
			sql += " and WfStep.StepUri = '"+wfStep.getStepUri().trim()+"'";

		return sql;
	}

	public List<WfStep> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfStep> list = new ArrayList<WfStep>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfStep wfStep = new WfStep();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("StepId") || _fields[i].equals("WfStep.StepId"))
						wfStep.setStepId(rs.getInt("StepId"));
					else if(_fields[i].equals("FlowId") || _fields[i].equals("WfStep.FlowId"))
						wfStep.setFlowId(rs.getInt("FlowId"));
					else if(_fields[i].equals("PreStep") || _fields[i].equals("WfStep.PreStep"))
						wfStep.setPreStep(rs.getInt("PreStep"));
					else if(_fields[i].equals("StepType") || _fields[i].equals("WfStep.StepType"))
						wfStep.setStepType(rs.getInt("StepType"));
					else if(_fields[i].equals("Sort") || _fields[i].equals("WfStep.Sort"))
						wfStep.setSort(rs.getInt("Sort"));
					else if(_fields[i].equals("IsAuto") || _fields[i].equals("WfStep.IsAuto"))
						wfStep.setIsAuto(rs.getInt("IsAuto"));
					else if(_fields[i].equals("IsUpdForm") || _fields[i].equals("WfStep.IsUpdForm"))
						wfStep.setIsUpdForm(rs.getInt("IsUpdForm"));
					else if(_fields[i].equals("IsSysFinsh") || _fields[i].equals("WfStep.IsSysFinsh"))
						wfStep.setIsSysFinsh(rs.getInt("IsSysFinsh"));
					else if(_fields[i].equals("TimeQty") || _fields[i].equals("WfStep.TimeQty"))
						wfStep.setTimeQty(rs.getInt("TimeQty"));
					else if(_fields[i].equals("SelectCom") || _fields[i].equals("WfStep.SelectCom"))
						wfStep.setSelectCom(rs.getInt("SelectCom"));
					else if(_fields[i].equals("IsLastStep") || _fields[i].equals("WfStep.IsLastStep"))
						wfStep.setIsLastStep(rs.getInt("IsLastStep"));
					else if(_fields[i].equals("Status") || _fields[i].equals("WfStep.Status"))
						wfStep.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfStep.CreateBy"))
						wfStep.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfStep.LastUpd"))
						wfStep.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("SelectDept") || _fields[i].equals("WfStep.SelectDept"))
						wfStep.setSelectDept(rs.getInt("SelectDept"));
					else if(_fields[i].equals("WaitAuxiliary") || _fields[i].equals("WfStep.WaitAuxiliary"))
						wfStep.setWaitAuxiliary(rs.getInt("WaitAuxiliary"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfStep.CreateDate"))
						wfStep.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfStep.LastUpdDate"))
						wfStep.setLastUpdDate(rs.getTimestamp("LastUpdDate"));
					else if(_fields[i].equals("StepName") || _fields[i].equals("WfStep.StepName"))
						wfStep.setStepName(rs.getString("StepName"));
					else if(_fields[i].equals("StepDesc") || _fields[i].equals("WfStep.StepDesc"))
						wfStep.setStepDesc(rs.getString("StepDesc"));
					else if(_fields[i].equals("StepUri") || _fields[i].equals("WfStep.StepUri"))
						wfStep.setStepUri(rs.getString("StepUri"));

				}
				list.add(wfStep);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfStepHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfStep("+fields.replaceAll("WfStep\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfStep wfStep,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("StepId") || _fields[i].equals("WfStep.StepId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getStepId());
					}
					else if(_fields[i].equals("FlowId") || _fields[i].equals("WfStep.FlowId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getFlowId());
					}
					else if(_fields[i].equals("PreStep") || _fields[i].equals("WfStep.PreStep")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getPreStep());
					}
					else if(_fields[i].equals("StepType") || _fields[i].equals("WfStep.StepType")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getStepType());
					}
					else if(_fields[i].equals("Sort") || _fields[i].equals("WfStep.Sort")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getSort());
					}
					else if(_fields[i].equals("IsAuto") || _fields[i].equals("WfStep.IsAuto")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getIsAuto());
					}
					else if(_fields[i].equals("IsUpdForm") || _fields[i].equals("WfStep.IsUpdForm")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getIsUpdForm());
					}
					else if(_fields[i].equals("IsSysStartUp") || _fields[i].equals("WfStep.IsSysStartUp")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getIsSysStartUp());
					}
					else if(_fields[i].equals("IsSysFinsh") || _fields[i].equals("WfStep.IsSysFinsh")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getIsSysFinsh());
					}
					else if(_fields[i].equals("TimeQty") || _fields[i].equals("WfStep.TimeQty")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getTimeQty());
					}
					else if(_fields[i].equals("SelectCom") || _fields[i].equals("WfStep.SelectCom")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getSelectCom());
					}
					else if(_fields[i].equals("IsLastStep") || _fields[i].equals("WfStep.IsLastStep")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getIsLastStep());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("WfStep.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfStep.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfStep.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getLastUpd());
					}
					else if(_fields[i].equals("SelectDept") || _fields[i].equals("WfStep.SelectDept")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getSelectDept());
					}
					else if(_fields[i].equals("WaitAuxiliary") || _fields[i].equals("WfStep.WaitAuxiliary")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getWaitAuxiliary());
					}
					else if(_fields[i].equals("IsFillQues") || _fields[i].equals("WfStep.IsFillQues")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getIsFillQues());
					}
					else if(_fields[i].equals("IsDQAJob") || _fields[i].equals("WfStep.IsDQAJob")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getIsDQAJob());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfStep.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfStep.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfStep.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfStep.getLastUpdDate().getTime()));
					}
					else if(_fields[i].equals("StepName") || _fields[i].equals("WfStep.StepName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfStep.getStepName());
					}
					else if(_fields[i].equals("StepDesc") || _fields[i].equals("WfStep.StepDesc")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfStep.getStepDesc());
					}
					else if(_fields[i].equals("StepUri") || _fields[i].equals("WfStep.StepUri")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfStep.getStepUri());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfStepHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfStep set ";
		String[] _fields = fields.replaceAll("WfStep\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("PreStep") || _fields[i].equals("WfStep.PreStep"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("StepType") || _fields[i].equals("WfStep.StepType"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Sort") || _fields[i].equals("WfStep.Sort"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IsAuto") || _fields[i].equals("WfStep.IsAuto"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IsUpdForm") || _fields[i].equals("WfStep.IsUpdForm"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IsSysFinsh") || _fields[i].equals("WfStep.IsSysFinsh"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IsSysStartUp") || _fields[i].equals("WfStep.IsSysStartUp"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("TimeQty") || _fields[i].equals("WfStep.TimeQty"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("SelectCom") || _fields[i].equals("WfStep.SelectCom"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IsLastStep") || _fields[i].equals("WfStep.IsLastStep"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("WfStep.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("WfStep.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("WfStep.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("SelectDept") || _fields[i].equals("WfStep.SelectDept"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("WaitAuxiliary") || _fields[i].equals("WfStep.WaitAuxiliary"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IsFillQues") || _fields[i].equals("WfStep.IsFillQues"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IsDQAJob") || _fields[i].equals("WfStep.IsDQAJob"))
						sql += _fields[i] + " = ?,";
					
					if(_fields[i].equals("CreateDate") || _fields[i].equals("WfStep.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfStep.LastUpdDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("StepName") || _fields[i].equals("WfStep.StepName"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("StepDesc") || _fields[i].equals("WfStep.StepDesc"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("StepUri") || _fields[i].equals("WfStep.StepUri"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfStep wfStep,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("PreStep") || _fields[i].equals("WfStep.PreStep")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getPreStep());
					}
					else if(_fields[i].equals("StepType") || _fields[i].equals("WfStep.StepType")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getStepType());
					}
					else if(_fields[i].equals("Sort") || _fields[i].equals("WfStep.Sort")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getSort());
					}
					else if(_fields[i].equals("IsAuto") || _fields[i].equals("WfStep.IsAuto")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getIsAuto());
					}
					else if(_fields[i].equals("IsUpdForm") || _fields[i].equals("WfStep.IsUpdForm")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getIsUpdForm());
					}
					else if(_fields[i].equals("IsSysStartUp") || _fields[i].equals("WfStep.IsSysStartUp")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getIsSysStartUp());
					}
					else if(_fields[i].equals("IsSysFinsh") || _fields[i].equals("WfStep.IsSysFinsh")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getIsSysFinsh());
					}
					else if(_fields[i].equals("TimeQty") || _fields[i].equals("WfStep.TimeQty")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getTimeQty());
					}
					else if(_fields[i].equals("SelectCom") || _fields[i].equals("WfStep.SelectCom")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getSelectCom());
					}
					else if(_fields[i].equals("IsLastStep") || _fields[i].equals("WfStep.IsLastStep")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getIsLastStep());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("WfStep.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfStep.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfStep.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getLastUpd());
					}
					else if(_fields[i].equals("SelectDept") || _fields[i].equals("WfStep.SelectDept")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getSelectDept());
					}
					else if(_fields[i].equals("WaitAuxiliary") || _fields[i].equals("WfStep.WaitAuxiliary")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getWaitAuxiliary());
					}
					else if(_fields[i].equals("IsFillQues") || _fields[i].equals("WfStep.IsFillQues")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getIsFillQues());
					}
					else if(_fields[i].equals("IsDQAJob") || _fields[i].equals("WfStep.IsDQAJob")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfStep.getIsDQAJob());
					}
					
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfStep.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfStep.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfStep.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfStep.getLastUpdDate().getTime()));
					}
					else if(_fields[i].equals("StepName") || _fields[i].equals("WfStep.StepName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfStep.getStepName());
					}
					else if(_fields[i].equals("StepDesc") || _fields[i].equals("WfStep.StepDesc")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfStep.getStepDesc());
					}
					else if(_fields[i].equals("StepUri") || _fields[i].equals("WfStep.StepUri")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfStep.getStepUri());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfStepHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfStep wfStep) {
		String _fields = "";
		if(null != wfStep.getStepId())
			_fields += "WfStep.StepId,";
		if(null != wfStep.getFlowId())
			_fields += "WfStep.FlowId,";
		if(null != wfStep.getPreStep())
			_fields += "WfStep.PreStep,";
		if(null != wfStep.getStepType())
			_fields += "WfStep.StepType,";
		if(null != wfStep.getSort())
			_fields += "WfStep.Sort,";
		if(null != wfStep.getIsAuto())
			_fields += "WfStep.IsAuto,";
		if(null != wfStep.getIsUpdForm())
			_fields += "WfStep.IsUpdForm,";
		if(null != wfStep.getIsSysStartUp())
			_fields += "WfStep.IsSysStartUp,";
		if(null != wfStep.getIsSysFinsh())
			_fields += "WfStep.IsSysFinsh,";
		if(null != wfStep.getTimeQty())
			_fields += "WfStep.TimeQty,";
		if(null != wfStep.getSelectCom())
			_fields += "WfStep.SelectCom,";
		if(null != wfStep.getIsLastStep())
			_fields += "WfStep.IsLastStep,";
		if(null != wfStep.getStatus())
			_fields += "WfStep.Status,";
		if(null != wfStep.getCreateBy())
			_fields += "WfStep.CreateBy,";
		if(null != wfStep.getLastUpd())
			_fields += "WfStep.LastUpd,";
		if(null != wfStep.getSelectDept())
			_fields += "WfStep.SelectDept,";
		if(null != wfStep.getWaitAuxiliary())
			_fields += "WfStep.WaitAuxiliary,";
		if(null != wfStep.getIsFillQues())
			_fields += "WfStep.IsFillQues,";
		if(null != wfStep.getIsDQAJob())
			_fields += "WfStep.IsDQAJob,";
		if(null != wfStep.getCreateDate())
			_fields += "WfStep.CreateDate,";
		if(null != wfStep.getLastUpdDate())
			_fields += "WfStep.LastUpdDate,";
		if(null != wfStep.getStepName())
			_fields += "WfStep.StepName,";
		if(null != wfStep.getStepDesc())
			_fields += "WfStep.StepDesc,";
		if(null != wfStep.getStepUri())
			_fields += "WfStep.StepUri,";

		return _fields.substring(0, _fields.length()-1);
	}
}