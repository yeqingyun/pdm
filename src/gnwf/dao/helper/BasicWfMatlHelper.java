package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfMatl;

public class BasicWfMatlHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfMatl where 1=1";
	}

	public String getOrderBy() {
		return " order by WfMatl.MatlId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfMatl)object);
	}

	public String getSql4Amount(WfMatl wfMatl) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfMatl);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfMatl)object);
	}

	public String getSql4Delete(WfMatl wfMatl) {
		return "delete from WfMatl where 1=1"+getSqlCondition(wfMatl);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfMatl)object,fields);
	}

	public String getSql4All(WfMatl wfMatl, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfMatl)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfMatl)object,pageVO,fields);
	}

	public String getSql4Pages(WfMatl wfMatl,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfMatl.MatlId "+ getSqlString()+getSqlCondition(wfMatl)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfMatl)+" and WfMatl.MatlId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfMatl)object);
	}

	public String getSqlCondition(WfMatl wfMatl) {
		String sql = "";
		if(null != wfMatl.getMatlId())
			sql += " and WfMatl.MatlId = '"+wfMatl.getMatlId()+"'";
		if(null != wfMatl.getMatlType())
			sql += " and WfMatl.MatlType = '"+wfMatl.getMatlType()+"'";
		if(null != wfMatl.getIsSubs())
			sql += " and WfMatl.IsSubs = '"+wfMatl.getIsSubs()+"'";
		if(null != wfMatl.getRisk())
			sql += " and WfMatl.Risk = '"+wfMatl.getRisk()+"'";
		if(null != wfMatl.getLotSize())
			sql += " and WfMatl.LotSize = '"+wfMatl.getLotSize()+"'";
		if(null != wfMatl.getIsPanel())
			sql += " and WfMatl.IsPanel = '"+wfMatl.getIsPanel()+"'";
		if(null != wfMatl.getStatus())
			sql += " and WfMatl.Status = '"+wfMatl.getStatus()+"'";
		if(null != wfMatl.getCreateBy())
			sql += " and WfMatl.CreateBy = '"+wfMatl.getCreateBy()+"'";
		if(null != wfMatl.getLastUpd())
			sql += " and WfMatl.LastUpd = '"+wfMatl.getLastUpd()+"'";
		if(null != wfMatl.getMatlNo() && !wfMatl.getMatlNo().trim().equals(""))
			sql += " and WfMatl.MatlNo = '"+wfMatl.getMatlNo().trim()+"'";
		if(null != wfMatl.getWfNo() && !wfMatl.getWfNo().trim().equals(""))
			sql += " and WfMatl.WfNo = '"+wfMatl.getWfNo().trim()+"'";
		if(null != wfMatl.getMatlLevel() && !wfMatl.getMatlLevel().trim().equals(""))
			sql += " and WfMatl.MatlLevel = '"+wfMatl.getMatlLevel().trim()+"'";
		if(null != wfMatl.gethistoryLevel() && !wfMatl.gethistoryLevel().trim().equals(""))
			sql += " and WfMatl.historyLevel = '"+wfMatl.gethistoryLevel().trim()+"'";
		if(null != wfMatl.getStartCreateDate()) 
			sql += " and WfMatl.CreateDate >= '"+GenericUtil.dateFormat(wfMatl.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfMatl.getEndCreateDate()) 
			sql += " and WfMatl.CreateDate <= '"+GenericUtil.dateFormat(wfMatl.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfMatl.getStartLastUpdDate()) 
			sql += " and WfMatl.LastUpdDate >= '"+GenericUtil.dateFormat(wfMatl.getStartLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfMatl.getEndLastUpdDate()) 
			sql += " and WfMatl.LastUpdDate <= '"+GenericUtil.dateFormat(wfMatl.getEndLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != wfMatl.getMatlName() && !wfMatl.getMatlName().trim().equals(""))
			sql += " and WfMatl.MatlName = '"+wfMatl.getMatlName().trim()+"'";
		if(null != wfMatl.getMatlDesc() && !wfMatl.getMatlDesc().trim().equals(""))
			sql += " and WfMatl.MatlDesc = '"+wfMatl.getMatlDesc().trim()+"'";
		if(null != wfMatl.getSupplier() && !wfMatl.getSupplier().trim().equals(""))
			sql += " and WfMatl.Supplier = '"+wfMatl.getSupplier().trim()+"'";
		if(null != wfMatl.getRemark() && !wfMatl.getRemark().trim().equals(""))
			sql += " and WfMatl.Remark = '"+wfMatl.getRemark().trim()+"'";
		if(null != wfMatl.getSupNo() && !wfMatl.getSupNo().trim().equals(""))
			sql += " and WfMatl.SupNo = '"+wfMatl.getSupNo().trim()+"'";
		if(null != wfMatl.getMatlEvalDesc() && !wfMatl.getMatlEvalDesc().trim().equals(""))
			sql += " and WfMatl.MatlEvalDesc = '"+wfMatl.getMatlEvalDesc().trim()+"'";

		return sql;
	}

	public List<WfMatl> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfMatl> list = new ArrayList<WfMatl>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfMatl wfMatl = new WfMatl();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("MatlId") || _fields[i].equals("WfMatl.MatlId"))
						wfMatl.setMatlId(rs.getInt("MatlId"));
					else if(_fields[i].equals("MatlType") || _fields[i].equals("WfMatl.MatlType"))
						wfMatl.setMatlType(rs.getInt("MatlType"));
					else if(_fields[i].equals("IsSubs") || _fields[i].equals("WfMatl.IsSubs"))
						wfMatl.setIsSubs(rs.getInt("IsSubs"));
					else if(_fields[i].equals("Risk") || _fields[i].equals("WfMatl.Risk"))
						wfMatl.setRisk(rs.getInt("Risk"));
					else if(_fields[i].equals("LotSize") || _fields[i].equals("WfMatl.LotSize"))
						wfMatl.setLotSize(rs.getInt("LotSize"));
					else if(_fields[i].equals("IsPanel") || _fields[i].equals("WfMatl.IsPanel"))
						wfMatl.setIsPanel(rs.getInt("IsPanel"));
					else if(_fields[i].equals("Status") || _fields[i].equals("WfMatl.Status"))
						wfMatl.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfMatl.CreateBy"))
						wfMatl.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfMatl.LastUpd"))
						wfMatl.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("MatlNo") || _fields[i].equals("WfMatl.MatlNo"))
						wfMatl.setMatlNo(rs.getString("MatlNo"));
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfMatl.WfNo"))
						wfMatl.setWfNo(rs.getString("WfNo"));
					else if(_fields[i].equals("MatlLevel") || _fields[i].equals("WfMatl.MatlLevel"))
						wfMatl.setMatlLevel(rs.getString("MatlLevel"));
					else if(_fields[i].equals("historyLevel") || _fields[i].equals("WfMatl.historyLevel"))
						wfMatl.sethistoryLevel(rs.getString("historyLevel"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfMatl.CreateDate"))
						wfMatl.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfMatl.LastUpdDate"))
						wfMatl.setLastUpdDate(rs.getTimestamp("LastUpdDate"));
					else if(_fields[i].equals("MatlName") || _fields[i].equals("WfMatl.MatlName"))
						wfMatl.setMatlName(rs.getString("MatlName"));
					else if(_fields[i].equals("MatlDesc") || _fields[i].equals("WfMatl.MatlDesc"))
						wfMatl.setMatlDesc(rs.getString("MatlDesc"));
					else if(_fields[i].equals("Supplier") || _fields[i].equals("WfMatl.Supplier"))
						wfMatl.setSupplier(rs.getString("Supplier"));
					else if(_fields[i].equals("Remark") || _fields[i].equals("WfMatl.Remark"))
						wfMatl.setRemark(rs.getString("Remark"));
					else if(_fields[i].equals("SupNo") || _fields[i].equals("WfMatl.SupNo"))
						wfMatl.setSupNo(rs.getString("SupNo"));
					else if(_fields[i].equals("MatlEvalDesc") || _fields[i].equals("WfMatl.MatlEvalDesc"))
						wfMatl.setMatlEvalDesc(rs.getString("MatlEvalDesc"));

				}
				list.add(wfMatl);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfMatlHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfMatl("+fields.replaceAll("WfMatl\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfMatl wfMatl,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("MatlId") || _fields[i].equals("WfMatl.MatlId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatl.getMatlId());
					}
					else if(_fields[i].equals("MatlType") || _fields[i].equals("WfMatl.MatlType")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatl.getMatlType());
					}
					else if(_fields[i].equals("IsSubs") || _fields[i].equals("WfMatl.IsSubs")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatl.getIsSubs());
					}
					else if(_fields[i].equals("Risk") || _fields[i].equals("WfMatl.Risk")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatl.getRisk());
					}
					else if(_fields[i].equals("LotSize") || _fields[i].equals("WfMatl.LotSize")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatl.getLotSize());
					}
					else if(_fields[i].equals("IsPanel") || _fields[i].equals("WfMatl.IsPanel")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatl.getIsPanel());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("WfMatl.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatl.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfMatl.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatl.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfMatl.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatl.getLastUpd());
					}
					else if(_fields[i].equals("MatlNo") || _fields[i].equals("WfMatl.MatlNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatl.getMatlNo());
					}
					else if(_fields[i].equals("WfNo") || _fields[i].equals("WfMatl.WfNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatl.getWfNo());
					}
					else if(_fields[i].equals("MatlLevel") || _fields[i].equals("WfMatl.MatlLevel")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatl.getMatlLevel());
					}
					else if(_fields[i].equals("historyLevel") || _fields[i].equals("WfMatl.historyLevel")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatl.gethistoryLevel());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfMatl.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfMatl.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfMatl.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfMatl.getLastUpdDate().getTime()));
					}
					else if(_fields[i].equals("MatlName") || _fields[i].equals("WfMatl.MatlName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatl.getMatlName());
					}
					else if(_fields[i].equals("MatlDesc") || _fields[i].equals("WfMatl.MatlDesc")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatl.getMatlDesc());
					}
					else if(_fields[i].equals("Supplier") || _fields[i].equals("WfMatl.Supplier")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatl.getSupplier());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("WfMatl.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatl.getRemark());
					}
					else if(_fields[i].equals("SupNo") || _fields[i].equals("WfMatl.SupNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatl.getSupNo());
					}
					else if(_fields[i].equals("MatlEvalDesc") || _fields[i].equals("WfMatl.MatlEvalDesc")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatl.getMatlEvalDesc());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfMatlHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfMatl set ";
		String[] _fields = fields.replaceAll("WfMatl\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("MatlType") || _fields[i].equals("WfMatl.MatlType"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IsSubs") || _fields[i].equals("WfMatl.IsSubs"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Risk") || _fields[i].equals("WfMatl.Risk"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LotSize") || _fields[i].equals("WfMatl.LotSize"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IsPanel") || _fields[i].equals("WfMatl.IsPanel"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("WfMatl.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("WfMatl.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("WfMatl.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("MatlNo") || _fields[i].equals("WfMatl.MatlNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("MatlLevel") || _fields[i].equals("WfMatl.MatlLevel"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("historyLevel") || _fields[i].equals("WfMatl.historyLevel"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("WfMatl.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfMatl.LastUpdDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("MatlName") || _fields[i].equals("WfMatl.MatlName"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("MatlDesc") || _fields[i].equals("WfMatl.MatlDesc"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Supplier") || _fields[i].equals("WfMatl.Supplier"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Remark") || _fields[i].equals("WfMatl.Remark"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("SupNo") || _fields[i].equals("WfMatl.SupNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("MatlEvalDesc") || _fields[i].equals("WfMatl.MatlEvalDesc"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfMatl wfMatl,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("MatlType") || _fields[i].equals("WfMatl.MatlType")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatl.getMatlType());
					}
					else if(_fields[i].equals("IsSubs") || _fields[i].equals("WfMatl.IsSubs")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatl.getIsSubs());
					}
					else if(_fields[i].equals("Risk") || _fields[i].equals("WfMatl.Risk")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatl.getRisk());
					}
					else if(_fields[i].equals("LotSize") || _fields[i].equals("WfMatl.LotSize")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatl.getLotSize());
					}
					else if(_fields[i].equals("IsPanel") || _fields[i].equals("WfMatl.IsPanel")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatl.getIsPanel());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("WfMatl.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatl.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("WfMatl.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatl.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("WfMatl.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatl.getLastUpd());
					}
					else if(_fields[i].equals("MatlNo") || _fields[i].equals("WfMatl.MatlNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatl.getMatlNo());
					}
					else if(_fields[i].equals("MatlLevel") || _fields[i].equals("WfMatl.MatlLevel")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatl.getMatlLevel());
					}
					else if(_fields[i].equals("historyLevel") || _fields[i].equals("WfMatl.historyLevel")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatl.gethistoryLevel());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("WfMatl.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfMatl.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("WfMatl.LastUpdDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(wfMatl.getLastUpdDate().getTime()));
					}
					else if(_fields[i].equals("MatlName") || _fields[i].equals("WfMatl.MatlName")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatl.getMatlName());
					}
					else if(_fields[i].equals("MatlDesc") || _fields[i].equals("WfMatl.MatlDesc")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatl.getMatlDesc());
					}
					else if(_fields[i].equals("Supplier") || _fields[i].equals("WfMatl.Supplier")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatl.getSupplier());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("WfMatl.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatl.getRemark());
					}
					else if(_fields[i].equals("SupNo") || _fields[i].equals("WfMatl.SupNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatl.getSupNo());
					}
					else if(_fields[i].equals("MatlEvalDesc") || _fields[i].equals("WfMatl.MatlEvalDesc")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatl.getMatlEvalDesc());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfMatlHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfMatl wfMatl) {
		String _fields = "";
		if(null != wfMatl.getMatlId())
			_fields += "WfMatl.MatlId,";
		if(null != wfMatl.getMatlType())
			_fields += "WfMatl.MatlType,";
		if(null != wfMatl.getIsSubs())
			_fields += "WfMatl.IsSubs,";
		if(null != wfMatl.getRisk())
			_fields += "WfMatl.Risk,";
		if(null != wfMatl.getLotSize())
			_fields += "WfMatl.LotSize,";
		if(null != wfMatl.getIsPanel())
			_fields += "WfMatl.IsPanel,";
		if(null != wfMatl.getStatus())
			_fields += "WfMatl.Status,";
		if(null != wfMatl.getCreateBy())
			_fields += "WfMatl.CreateBy,";
		if(null != wfMatl.getLastUpd())
			_fields += "WfMatl.LastUpd,";
		if(null != wfMatl.getMatlNo())
			_fields += "WfMatl.MatlNo,";
		if(null != wfMatl.getWfNo())
			_fields += "WfMatl.WfNo,";
		if(null != wfMatl.getMatlLevel())
			_fields += "WfMatl.MatlLevel,";
		if(null != wfMatl.gethistoryLevel())
			_fields += "WfMatl.historyLevel,";
		if(null != wfMatl.getCreateDate())
			_fields += "WfMatl.CreateDate,";
		if(null != wfMatl.getLastUpdDate())
			_fields += "WfMatl.LastUpdDate,";
		if(null != wfMatl.getMatlName())
			_fields += "WfMatl.MatlName,";
		if(null != wfMatl.getMatlDesc())
			_fields += "WfMatl.MatlDesc,";
		if(null != wfMatl.getSupplier())
			_fields += "WfMatl.Supplier,";
		if(null != wfMatl.getRemark())
			_fields += "WfMatl.Remark,";
		if(null != wfMatl.getSupNo())
			_fields += "WfMatl.SupNo,";
		if(null != wfMatl.getMatlEvalDesc())
			_fields += "WfMatl.MatlEvalDesc,";

		return _fields.substring(0, _fields.length()-1);
	}
}