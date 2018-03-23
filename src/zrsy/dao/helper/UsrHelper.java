package zrsy.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.frm.comn.GenericUtil;

import zrsy.vo.Usr;

public class UsrHelper extends BasicUsrHelper {
	public String getSqlString() {
		return " from Usr " +
               " left join Com on (Com.ComId = Usr.ComId) " + 
               " left join Dept on (Dept.DeptId = Usr.OrgNo) " + 

               " where 1=1 ";
	}

	public List<Usr> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<Usr> list = new ArrayList<Usr>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				Usr usr = new Usr();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Id") || _fields[i].equals("Usr.Id"))
						usr.setId(rs.getInt("Id"));
					if(_fields[i].equals("ComId") || _fields[i].equals("Usr.ComId"))
						usr.setComId(rs.getInt("ComId"));
					if(_fields[i].equals("DeptId") || _fields[i].equals("Usr.DeptId"))
						usr.setDeptId(rs.getInt("DeptId"));
					if(_fields[i].equals("Status") || _fields[i].equals("Usr.Status"))
						usr.setStatus(rs.getInt("Status"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("Usr.CreateBy"))
						usr.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("LastUpd") || _fields[i].equals("Usr.LastUpd"))
						usr.setLastUpd(rs.getInt("LastUpd"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("Usr.CreateDate"))
						usr.setCreateDate(rs.getTimestamp("CreateDate"));
					if(_fields[i].equals("LastDate") || _fields[i].equals("Usr.LastDate"))
						usr.setLastDate(rs.getTimestamp("LastDate"));
					if(_fields[i].equals("Login") || _fields[i].equals("Usr.Login"))
						usr.setLogin(rs.getString("Login"));
					if(_fields[i].equals("Pwd") || _fields[i].equals("Usr.Pwd"))
						usr.setPwd(rs.getString("Pwd"));
					if(_fields[i].equals("UsrNo") || _fields[i].equals("Usr.UsrNo"))
						usr.setUsrNo(rs.getString("UsrNo"));
					if(_fields[i].equals("UsrName") || _fields[i].equals("Usr.UsrName"))
						usr.setUsrName(rs.getString("UsrName"));
					if(_fields[i].equals("Email") || _fields[i].equals("Usr.Email"))
						usr.setEmail(rs.getString("Email"));
					if(_fields[i].equals("Remark") || _fields[i].equals("Usr.Remark"))
						usr.setRemark(rs.getString("Remark"));

					if(_fields[i].equals("ComNm") || _fields[i].equals("Com.ComNm as ComNm"))
						usr.setComNm(rs.getString("ComNm"));
					if(_fields[i].equals("DeptNm") || _fields[i].equals("Dept.DeptNm as DeptNm"))
						usr.setDeptNm(rs.getString("DeptNm"));
					
					if(_fields[i].equals("StepId") || _fields[i].equals("WfStepUserHis.StepId as StepId"))
						usr.setStepId(rs.getInt("StepId"));
					if(_fields[i].equals("TaskType") || _fields[i].equals("WfStepUserHis.TaskType as TaskType"))
						usr.setTaskType(rs.getInt("TaskType"));
					
					if(_fields[i].equals("OrgNo") || _fields[i].equals("Usr.OrgNo"))
						usr.setOrgNo(rs.getInt("OrgNo"));
					
					if(_fields[i].equals("DeptNm") || _fields[i].equals("(select dp.deptNm from UsrView uv inner join Dept dp on uv.OrgNo = dp.deptid ) as deptNm"))
						usr.setDeptNm(rs.getString("DeptNm"));
				}
				list.add(usr);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("UsrHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
	
	public String getSqlCondition(Usr usr) {
		String sql = "";
		if(null != usr.getId())
			sql += " and Usr.Id = '"+usr.getId()+"'";
		if(null != usr.getComId())
			sql += " and Usr.ComId = '"+usr.getComId()+"'";
		if(null != usr.getOrgNo())
			sql += " and Usr.OrgNo = '"+usr.getOrgNo()+"'";
		if(null != usr.getDeptId())
			sql += " and Usr.DeptId = '"+usr.getDeptId()+"'";
		if(null != usr.getLogin() && !usr.getLogin().trim().equals(""))
			sql += " and Usr.Login = '"+usr.getLogin().trim()+"'";
		if(null != usr.getPwd() && !usr.getPwd().trim().equals(""))
			sql += " and Usr.Pwd = '"+usr.getPwd().trim()+"'";
		if(null != usr.getUsrNo() && !usr.getUsrNo().trim().equals(""))
			sql += " and Usr.UsrNo = '"+usr.getUsrNo().trim()+"'";
		if(null != usr.getUsrName() && !usr.getUsrName().trim().equals(""))
			sql += " and Usr.UsrName like '"+usr.getUsrName().trim()+"%'";
		if(null != usr.getEmail() && !usr.getEmail().trim().equals(""))
			sql += " and Usr.Email = '"+usr.getEmail().trim()+"'";
		if(null != usr.getStatus())
			sql += " and Usr.Status = '"+usr.getStatus()+"'";
		if(null != usr.getRemark() && !usr.getRemark().trim().equals(""))
			sql += " and Usr.Remark = '"+usr.getRemark().trim()+"'";
		if(null != usr.getCreateBy())
			sql += " and Usr.CreateBy = '"+usr.getCreateBy()+"'";
		if(null != usr.getStartCreateDate()) 
			sql += " and Usr.CreateDate >= '"+GenericUtil.dateFormat(usr.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != usr.getEndCreateDate()) 
			sql += " and Usr.CreateDate <= '"+GenericUtil.dateFormat(usr.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != usr.getLastUpd())
			sql += " and Usr.LastUpd = '"+usr.getLastUpd()+"'";
		if(null != usr.getStartLastDate()) 
			sql += " and Usr.LastDate >= '"+GenericUtil.dateFormat(usr.getStartLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != usr.getEndLastDate()) 
			sql += " and Usr.LastDate <= '"+GenericUtil.dateFormat(usr.getEndLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != usr.getIsWide())
			sql += " and Usr.IsWide = '"+usr.getIsWide()+"'";

		return sql;
	}
}