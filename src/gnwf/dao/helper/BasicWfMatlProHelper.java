package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfMatlPro;

public class BasicWfMatlProHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfMatlPro where 1=1";
	}

	public String getOrderBy() {
		return " order by WfMatlPro.MatlProId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfMatlPro)object);
	}

	public String getSql4Amount(WfMatlPro wfMatlPro) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfMatlPro);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfMatlPro)object);
	}

	public String getSql4Delete(WfMatlPro wfMatlPro) {
		return "delete from WfMatlPro where 1=1"+getSqlCondition(wfMatlPro);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfMatlPro)object,fields);
	}

	public String getSql4All(WfMatlPro wfMatlPro, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfMatlPro)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfMatlPro)object,pageVO,fields);
	}

	public String getSql4Pages(WfMatlPro wfMatlPro,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfMatlPro.MatlProId "+ getSqlString()+getSqlCondition(wfMatlPro)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfMatlPro)+" and WfMatlPro.MatlProId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfMatlPro)object);
	}

	public String getSqlCondition(WfMatlPro wfMatlPro) {
		String sql = "";
		if(null != wfMatlPro.getMatlProId())
			sql += " and WfMatlPro.MatlProId = '"+wfMatlPro.getMatlProId()+"'";
		if(null != wfMatlPro.getIsPurPass())
			sql += " and WfMatlPro.IsPurPass = '"+wfMatlPro.getIsPurPass()+"'";
		if(null != wfMatlPro.getIsMatPass())
			sql += " and WfMatlPro.IsMatPass = '"+wfMatlPro.getIsMatPass()+"'";
		if(null != wfMatlPro.getIsManagerPass())
			sql += " and WfMatlPro.IsManagerPass = '"+wfMatlPro.getIsManagerPass()+"'";
		if(null != wfMatlPro.getStartPurRevDate()) 
			sql += " and WfMatlPro.PurRevDate >= '"+GenericUtil.dateFormat(wfMatlPro.getStartPurRevDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfMatlPro.getEndPurRevDate()) 
			sql += " and WfMatlPro.PurRevDate <= '"+GenericUtil.dateFormat(wfMatlPro.getEndPurRevDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfMatlPro.getStartMatRevDate()) 
			sql += " and WfMatlPro.MatRevDate >= '"+GenericUtil.dateFormat(wfMatlPro.getStartMatRevDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfMatlPro.getEndMatRevDate()) 
			sql += " and WfMatlPro.MatRevDate <= '"+GenericUtil.dateFormat(wfMatlPro.getEndMatRevDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfMatlPro.getStartManagerRevDate()) 
			sql += " and WfMatlPro.ManagerRevDate >= '"+GenericUtil.dateFormat(wfMatlPro.getStartManagerRevDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfMatlPro.getEndManagerRevDate()) 
			sql += " and WfMatlPro.ManagerRevDate <= '"+GenericUtil.dateFormat(wfMatlPro.getEndManagerRevDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfMatlPro.getWfNo() && !wfMatlPro.getWfNo().trim().equals(""))
			sql += " and WfMatlPro.WfNo = '"+wfMatlPro.getWfNo().trim()+"'";
		if(null != wfMatlPro.getProName() && !wfMatlPro.getProName().trim().equals(""))
			sql += " and WfMatlPro.ProName = '"+wfMatlPro.getProName().trim()+"'";
		if(null != wfMatlPro.getDesignName() && !wfMatlPro.getDesignName().trim().equals(""))
			sql += " and WfMatlPro.DesignName = '"+wfMatlPro.getDesignName().trim()+"'";
		if(null != wfMatlPro.getManageName() && !wfMatlPro.getManageName().trim().equals(""))
			sql += " and WfMatlPro.ManageName = '"+wfMatlPro.getManageName().trim()+"'";
		if(null != wfMatlPro.getProDesc() && !wfMatlPro.getProDesc().trim().equals(""))
			sql += " and WfMatlPro.ProDesc = '"+wfMatlPro.getProDesc().trim()+"'";
		if(null != wfMatlPro.getCurStep() && !wfMatlPro.getCurStep().trim().equals(""))
			sql += " and WfMatlPro.CurStep = '"+wfMatlPro.getCurStep().trim()+"'";
		if(null != wfMatlPro.getManagerReview() && !wfMatlPro.getManagerReview().trim().equals(""))
			sql += " and WfMatlPro.ManagerReview = '"+wfMatlPro.getManagerReview().trim()+"'";
		if(null != wfMatlPro.getManagerRemark() && !wfMatlPro.getManagerRemark().trim().equals(""))
			sql += " and WfMatlPro.ManagerRemark = '"+wfMatlPro.getManagerRemark().trim()+"'";
		if(null != wfMatlPro.getCurVersion() && !wfMatlPro.getCurVersion().trim().equals(""))
			sql += " and WfMatlPro.CurVersion = '"+wfMatlPro.getCurVersion().trim()+"'";
		if(null != wfMatlPro.getProCost() && !wfMatlPro.getProCost().trim().equals(""))
			sql += " and WfMatlPro.ProCost = '"+wfMatlPro.getProCost().trim()+"'";
		if(null != wfMatlPro.getPurReview() && !wfMatlPro.getPurReview().trim().equals(""))
			sql += " and WfMatlPro.PurReview = '"+wfMatlPro.getPurReview().trim()+"'";
		if(null != wfMatlPro.getPurRemark() && !wfMatlPro.getPurRemark().trim().equals(""))
			sql += " and WfMatlPro.PurRemark = '"+wfMatlPro.getPurRemark().trim()+"'";
		if(null != wfMatlPro.getMatReveiw() && !wfMatlPro.getMatReveiw().trim().equals(""))
			sql += " and WfMatlPro.MatReveiw = '"+wfMatlPro.getMatReveiw().trim()+"'";
		if(null != wfMatlPro.getMatRemark() && !wfMatlPro.getMatRemark().trim().equals(""))
			sql += " and WfMatlPro.MatRemark = '"+wfMatlPro.getMatRemark().trim()+"'";

		return sql;
	}

	public List<WfMatlPro> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfMatlPro> list = new ArrayList<WfMatlPro>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfMatlPro wfMatlPro = new WfMatlPro();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("MatlProId") || _fields[i].equals("WfMatlPro.MatlProId"))
						wfMatlPro.setMatlProId(rs.getInt("MatlProId"));
					else if(_fields[i].equals("IsPurPass") || _fields[i].equals("WfMatlPro.IsPurPass"))
						wfMatlPro.setIsPurPass(rs.getInt("IsPurPass"));
					else if(_fields[i].equals("IsMatPass") || _fields[i].equals("WfMatlPro.IsMatPass"))
						wfMatlPro.setIsMatPass(rs.getInt("IsMatPass"));
					else if(_fields[i].equals("IsManagerPass") || _fields[i].equals("WfMatlPro.IsManagerPass"))
						wfMatlPro.setIsManagerPass(rs.getInt("IsManagerPass"));
					else if(_fields[i].equals("PurRevDate") || _fields[i].equals("WfMatlPro.PurRevDate"))
						wfMatlPro.setPurRevDate(rs.getTimestamp("PurRevDate"));
					else if(_fields[i].equals("MatRevDate") || _fields[i].equals("WfMatlPro.MatRevDate"))
						wfMatlPro.setMatRevDate(rs.getTimestamp("MatRevDate"));
					else if(_fields[i].equals("ManagerRevDate") || _fields[i].equals("WfMatlPro.ManagerRevDate"))
						wfMatlPro.setManagerRevDate(rs.getTimestamp("ManagerRevDate"));
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfMatlPro.WfNo"))
						wfMatlPro.setWfNo(rs.getString("WfNo"));
					else if(_fields[i].equals("ProName") || _fields[i].equals("WfMatlPro.ProName"))
						wfMatlPro.setProName(rs.getString("ProName"));
					else if(_fields[i].equals("DesignName") || _fields[i].equals("WfMatlPro.DesignName"))
						wfMatlPro.setDesignName(rs.getString("DesignName"));
					else if(_fields[i].equals("ManageName") || _fields[i].equals("WfMatlPro.ManageName"))
						wfMatlPro.setManageName(rs.getString("ManageName"));
					else if(_fields[i].equals("ProDesc") || _fields[i].equals("WfMatlPro.ProDesc"))
						wfMatlPro.setProDesc(rs.getString("ProDesc"));
					else if(_fields[i].equals("CurStep") || _fields[i].equals("WfMatlPro.CurStep"))
						wfMatlPro.setCurStep(rs.getString("CurStep"));
					else if(_fields[i].equals("ManagerReview") || _fields[i].equals("WfMatlPro.ManagerReview"))
						wfMatlPro.setManagerReview(rs.getString("ManagerReview"));
					else if(_fields[i].equals("ManagerRemark") || _fields[i].equals("WfMatlPro.ManagerRemark"))
						wfMatlPro.setManagerRemark(rs.getString("ManagerRemark"));
					else if(_fields[i].equals("CurVersion") || _fields[i].equals("WfMatlPro.CurVersion"))
						wfMatlPro.setCurVersion(rs.getString("CurVersion"));
					else if(_fields[i].equals("ProCost") || _fields[i].equals("WfMatlPro.ProCost"))
						wfMatlPro.setProCost(rs.getString("ProCost"));
					else if(_fields[i].equals("PurReview") || _fields[i].equals("WfMatlPro.PurReview"))
						wfMatlPro.setPurReview(rs.getString("PurReview"));
					else if(_fields[i].equals("PurRemark") || _fields[i].equals("WfMatlPro.PurRemark"))
						wfMatlPro.setPurRemark(rs.getString("PurRemark"));
					else if(_fields[i].equals("MatReveiw") || _fields[i].equals("WfMatlPro.MatReveiw"))
						wfMatlPro.setMatReveiw(rs.getString("MatReveiw"));
					else if(_fields[i].equals("MatRemark") || _fields[i].equals("WfMatlPro.MatRemark"))
						wfMatlPro.setMatRemark(rs.getString("MatRemark"));

				}
				list.add(wfMatlPro);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfMatlProHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfMatlPro("+fields.replaceAll("WfMatlPro\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfMatlPro wfMatlPro,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("MatlProId") || _fields[i].equals("WfMatlPro.MatlProId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatlPro.getMatlProId());
					}
					else if(_fields[i].equals("IsPurPass") || _fields[i].equals("WfMatlPro.IsPurPass")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatlPro.getIsPurPass());
					}
					else if(_fields[i].equals("IsMatPass") || _fields[i].equals("WfMatlPro.IsMatPass")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatlPro.getIsMatPass());
					}
					else if(_fields[i].equals("IsManagerPass") || _fields[i].equals("WfMatlPro.IsManagerPass")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatlPro.getIsManagerPass());
					}
					else if(_fields[i].equals("PurRevDate") || _fields[i].equals("WfMatlPro.PurRevDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfMatlPro.getPurRevDate().getTime()));
					}
					else if(_fields[i].equals("MatRevDate") || _fields[i].equals("WfMatlPro.MatRevDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfMatlPro.getMatRevDate().getTime()));
					}
					else if(_fields[i].equals("ManagerRevDate") || _fields[i].equals("WfMatlPro.ManagerRevDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfMatlPro.getManagerRevDate().getTime()));
					}
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfMatlPro.WfNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlPro.getWfNo());
					}
					else if(_fields[i].equals("ProName") || _fields[i].equals("WfMatlPro.ProName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlPro.getProName());
					}
					else if(_fields[i].equals("DesignName") || _fields[i].equals("WfMatlPro.DesignName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlPro.getDesignName());
					}
					else if(_fields[i].equals("ManageName") || _fields[i].equals("WfMatlPro.ManageName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlPro.getManageName());
					}
					else if(_fields[i].equals("ProDesc") || _fields[i].equals("WfMatlPro.ProDesc")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlPro.getProDesc());
					}
					else if(_fields[i].equals("CurStep") || _fields[i].equals("WfMatlPro.CurStep")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlPro.getCurStep());
					}
					else if(_fields[i].equals("ManagerReview") || _fields[i].equals("WfMatlPro.ManagerReview")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlPro.getManagerReview());
					}
					else if(_fields[i].equals("ManagerRemark") || _fields[i].equals("WfMatlPro.ManagerRemark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlPro.getManagerRemark());
					}
					else if(_fields[i].equals("CurVersion") || _fields[i].equals("WfMatlPro.CurVersion")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlPro.getCurVersion());
					}
					else if(_fields[i].equals("ProCost") || _fields[i].equals("WfMatlPro.ProCost")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlPro.getProCost());
					}
					else if(_fields[i].equals("PurReview") || _fields[i].equals("WfMatlPro.PurReview")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlPro.getPurReview());
					}
					else if(_fields[i].equals("PurRemark") || _fields[i].equals("WfMatlPro.PurRemark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlPro.getPurRemark());
					}
					else if(_fields[i].equals("MatReveiw") || _fields[i].equals("WfMatlPro.MatReveiw")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlPro.getMatReveiw());
					}
					else if(_fields[i].equals("MatRemark") || _fields[i].equals("WfMatlPro.MatRemark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlPro.getMatRemark());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfMatlProHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfMatlPro set ";
		String[] _fields = fields.replaceAll("WfMatlPro\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("IsPurPass") || _fields[i].equals("WfMatlPro.IsPurPass"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IsMatPass") || _fields[i].equals("WfMatlPro.IsMatPass"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IsManagerPass") || _fields[i].equals("WfMatlPro.IsManagerPass"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("PurRevDate") || _fields[i].equals("WfMatlPro.PurRevDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("MatRevDate") || _fields[i].equals("WfMatlPro.MatRevDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("ManagerRevDate") || _fields[i].equals("WfMatlPro.ManagerRevDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("WfNo") || _fields[i].equals("WfMatlPro.WfNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("ProName") || _fields[i].equals("WfMatlPro.ProName"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("DesignName") || _fields[i].equals("WfMatlPro.DesignName"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("ManageName") || _fields[i].equals("WfMatlPro.ManageName"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("ProDesc") || _fields[i].equals("WfMatlPro.ProDesc"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CurStep") || _fields[i].equals("WfMatlPro.CurStep"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("ManagerReview") || _fields[i].equals("WfMatlPro.ManagerReview"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("ManagerRemark") || _fields[i].equals("WfMatlPro.ManagerRemark"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CurVersion") || _fields[i].equals("WfMatlPro.CurVersion"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("ProCost") || _fields[i].equals("WfMatlPro.ProCost"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("PurReview") || _fields[i].equals("WfMatlPro.PurReview"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("PurRemark") || _fields[i].equals("WfMatlPro.PurRemark"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("MatReveiw") || _fields[i].equals("WfMatlPro.MatReveiw"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("MatRemark") || _fields[i].equals("WfMatlPro.MatRemark"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfMatlPro wfMatlPro,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("IsPurPass") || _fields[i].equals("WfMatlPro.IsPurPass")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatlPro.getIsPurPass());
					}
					else if(_fields[i].equals("IsMatPass") || _fields[i].equals("WfMatlPro.IsMatPass")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatlPro.getIsMatPass());
					}
					else if(_fields[i].equals("IsManagerPass") || _fields[i].equals("WfMatlPro.IsManagerPass")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatlPro.getIsManagerPass());
					}
					else if(_fields[i].equals("PurRevDate") || _fields[i].equals("WfMatlPro.PurRevDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfMatlPro.getPurRevDate().getTime()));
					}
					else if(_fields[i].equals("MatRevDate") || _fields[i].equals("WfMatlPro.MatRevDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfMatlPro.getMatRevDate().getTime()));
					}
					else if(_fields[i].equals("ManagerRevDate") || _fields[i].equals("WfMatlPro.ManagerRevDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfMatlPro.getManagerRevDate().getTime()));
					}
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfMatlPro.WfNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlPro.getWfNo());
					}
					else if(_fields[i].equals("ProName") || _fields[i].equals("WfMatlPro.ProName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlPro.getProName());
					}
					else if(_fields[i].equals("DesignName") || _fields[i].equals("WfMatlPro.DesignName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlPro.getDesignName());
					}
					else if(_fields[i].equals("ManageName") || _fields[i].equals("WfMatlPro.ManageName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlPro.getManageName());
					}
					else if(_fields[i].equals("ProDesc") || _fields[i].equals("WfMatlPro.ProDesc")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlPro.getProDesc());
					}
					else if(_fields[i].equals("CurStep") || _fields[i].equals("WfMatlPro.CurStep")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlPro.getCurStep());
					}
					else if(_fields[i].equals("ManagerReview") || _fields[i].equals("WfMatlPro.ManagerReview")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlPro.getManagerReview());
					}
					else if(_fields[i].equals("ManagerRemark") || _fields[i].equals("WfMatlPro.ManagerRemark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlPro.getManagerRemark());
					}
					else if(_fields[i].equals("CurVersion") || _fields[i].equals("WfMatlPro.CurVersion")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlPro.getCurVersion());
					}
					else if(_fields[i].equals("ProCost") || _fields[i].equals("WfMatlPro.ProCost")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlPro.getProCost());
					}
					else if(_fields[i].equals("PurReview") || _fields[i].equals("WfMatlPro.PurReview")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlPro.getPurReview());
					}
					else if(_fields[i].equals("PurRemark") || _fields[i].equals("WfMatlPro.PurRemark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlPro.getPurRemark());
					}
					else if(_fields[i].equals("MatReveiw") || _fields[i].equals("WfMatlPro.MatReveiw")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlPro.getMatReveiw());
					}
					else if(_fields[i].equals("MatRemark") || _fields[i].equals("WfMatlPro.MatRemark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlPro.getMatRemark());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfMatlProHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfMatlPro wfMatlPro) {
		String _fields = "";
		if(null != wfMatlPro.getMatlProId())
			_fields += "WfMatlPro.MatlProId,";
		if(null != wfMatlPro.getIsPurPass())
			_fields += "WfMatlPro.IsPurPass,";
		if(null != wfMatlPro.getIsMatPass())
			_fields += "WfMatlPro.IsMatPass,";
		if(null != wfMatlPro.getIsManagerPass())
			_fields += "WfMatlPro.IsManagerPass,";
		if(null != wfMatlPro.getPurRevDate())
			_fields += "WfMatlPro.PurRevDate,";
		if(null != wfMatlPro.getMatRevDate())
			_fields += "WfMatlPro.MatRevDate,";
		if(null != wfMatlPro.getManagerRevDate())
			_fields += "WfMatlPro.ManagerRevDate,";
		if(null != wfMatlPro.getWfNo())
			_fields += "WfMatlPro.WfNo,";
		if(null != wfMatlPro.getProName())
			_fields += "WfMatlPro.ProName,";
		if(null != wfMatlPro.getDesignName())
			_fields += "WfMatlPro.DesignName,";
		if(null != wfMatlPro.getManageName())
			_fields += "WfMatlPro.ManageName,";
		if(null != wfMatlPro.getProDesc())
			_fields += "WfMatlPro.ProDesc,";
		if(null != wfMatlPro.getCurStep())
			_fields += "WfMatlPro.CurStep,";
		if(null != wfMatlPro.getManagerReview())
			_fields += "WfMatlPro.ManagerReview,";
		if(null != wfMatlPro.getManagerRemark())
			_fields += "WfMatlPro.ManagerRemark,";
		if(null != wfMatlPro.getCurVersion())
			_fields += "WfMatlPro.CurVersion,";
		if(null != wfMatlPro.getProCost())
			_fields += "WfMatlPro.ProCost,";
		if(null != wfMatlPro.getPurReview())
			_fields += "WfMatlPro.PurReview,";
		if(null != wfMatlPro.getPurRemark())
			_fields += "WfMatlPro.PurRemark,";
		if(null != wfMatlPro.getMatReveiw())
			_fields += "WfMatlPro.MatReveiw,";
		if(null != wfMatlPro.getMatRemark())
			_fields += "WfMatlPro.MatRemark,";

		return _fields.substring(0, _fields.length()-1);
	}
}