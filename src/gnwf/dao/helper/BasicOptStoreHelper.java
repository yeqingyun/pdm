package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.OptStore;

public class BasicOptStoreHelper implements SqlHelper {
	public String getSqlString() {
		return " from OptStore where 1=1";
	}

	public String getOrderBy() {
		return " order by OptStore.MatlNo";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((OptStore)object);
	}

	public String getSql4Amount(OptStore optStore) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(optStore);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((OptStore)object);
	}

	public String getSql4Delete(OptStore optStore) {
		return "delete from OptStore where 1=1"+getSqlCondition(optStore);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((OptStore)object,fields);
	}

	public String getSql4All(OptStore optStore, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(optStore)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((OptStore)object,pageVO,fields);
	}

	public String getSql4Pages(OptStore optStore,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" OptStore.MatlNo "+ getSqlString()+getSqlCondition(optStore)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(optStore)+" and OptStore.MatlNo not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((OptStore)object);
	}

	public String getSqlCondition(OptStore optStore) {
		String sql = "";
		if(null != optStore.getMatlNo() && !optStore.getMatlNo().trim().equals(""))
			sql += " and OptStore.MatlNo = '"+optStore.getMatlNo().trim()+"'";
		if(null != optStore.getGpCode() && !optStore.getGpCode().trim().equals(""))
			sql += " and OptStore.GpCode = '"+optStore.getGpCode().trim()+"'";
		if(null != optStore.getMatlRev() && !optStore.getMatlRev().trim().equals(""))
			sql += " and OptStore.MatlRev = '"+optStore.getMatlRev().trim()+"'";
		if(null != optStore.getMatlNm() && !optStore.getMatlNm().trim().equals(""))
			sql += " and OptStore.MatlNm = '"+optStore.getMatlNm().trim()+"'";
		if(null != optStore.getMatlDesc() && !optStore.getMatlDesc().trim().equals(""))
			sql += " and OptStore.MatlDesc = '"+optStore.getMatlDesc().trim()+"'";
		if(null != optStore.getOptTyp())
			sql += " and OptStore.OptTyp = '"+optStore.getOptTyp()+"'";
		if(null != optStore.getMatlTyp())
			sql += " and OptStore.MatlTyp = '"+optStore.getMatlTyp()+"'";
		if(null != optStore.getLotSize())
			sql += " and OptStore.LotSize = '"+optStore.getLotSize()+"'";
		if(null != optStore.getIsPanel())
			sql += " and OptStore.IsPanel = '"+optStore.getIsPanel()+"'";
		if(null != optStore.getStatus())
			sql += " and OptStore.Status = '"+optStore.getStatus()+"'";
		if(null != optStore.getCreateBy())
			sql += " and OptStore.CreateBy = '"+optStore.getCreateBy()+"'";
		if(null != optStore.getStartCreateDate()) 
			sql += " and OptStore.CreateDate >= '"+GenericUtil.dateFormat(optStore.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != optStore.getEndCreateDate()) 
			sql += " and OptStore.CreateDate <= '"+GenericUtil.dateFormat(optStore.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != optStore.getLastUpd())
			sql += " and OptStore.LastUpd = '"+optStore.getLastUpd()+"'";
		if(null != optStore.getStartLastDate()) 
			sql += " and OptStore.LastDate >= '"+GenericUtil.dateFormat(optStore.getStartLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != optStore.getEndLastDate()) 
			sql += " and OptStore.LastDate <= '"+GenericUtil.dateFormat(optStore.getEndLastDate(), "yyyy-MM-dd 00:00:00")+"'";

		return sql;
	}

	public List<OptStore> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<OptStore> list = new ArrayList<OptStore>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				OptStore optStore = new OptStore();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("MatlNo") || _fields[i].equals("OptStore.MatlNo"))
						optStore.setMatlNo(rs.getString("MatlNo"));
					else if(_fields[i].equals("GpCode") || _fields[i].equals("OptStore.GpCode"))
						optStore.setGpCode(rs.getString("GpCode"));
					else if(_fields[i].equals("MatlRev") || _fields[i].equals("OptStore.MatlRev"))
						optStore.setMatlRev(rs.getString("MatlRev"));
					else if(_fields[i].equals("MatlNm") || _fields[i].equals("OptStore.MatlNm"))
						optStore.setMatlNm(rs.getString("MatlNm"));
					else if(_fields[i].equals("MatlDesc") || _fields[i].equals("OptStore.MatlDesc"))
						optStore.setMatlDesc(rs.getString("MatlDesc"));
					else if(_fields[i].equals("OptTyp") || _fields[i].equals("OptStore.OptTyp"))
						optStore.setOptTyp(rs.getInt("OptTyp"));
					else if(_fields[i].equals("MatlTyp") || _fields[i].equals("OptStore.MatlTyp"))
						optStore.setMatlTyp(rs.getInt("MatlTyp"));
					else if(_fields[i].equals("LotSize") || _fields[i].equals("OptStore.LotSize"))
						optStore.setLotSize(rs.getInt("LotSize"));
					else if(_fields[i].equals("IsPanel") || _fields[i].equals("OptStore.IsPanel"))
						optStore.setIsPanel(rs.getInt("IsPanel"));
					else if(_fields[i].equals("Status") || _fields[i].equals("OptStore.Status"))
						optStore.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("OptStore.CreateBy"))
						optStore.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("OptStore.CreateDate"))
						optStore.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("OptStore.LastUpd"))
						optStore.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("LastDate") || _fields[i].equals("OptStore.LastDate"))
						optStore.setLastDate(rs.getTimestamp("LastDate"));

				}
				list.add(optStore);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("OptStoreHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into OptStore("+fields.replaceAll("OptStore\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(OptStore optStore,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("MatlNo") || _fields[i].equals("OptStore.MatlNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, optStore.getMatlNo());
					}
					else if(_fields[i].equals("GpCode") || _fields[i].equals("OptStore.GpCode")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, optStore.getGpCode());
					}
					else if(_fields[i].equals("MatlRev") || _fields[i].equals("OptStore.MatlRev")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, optStore.getMatlRev());
					}
					else if(_fields[i].equals("MatlNm") || _fields[i].equals("OptStore.MatlNm")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, optStore.getMatlNm());
					}
					else if(_fields[i].equals("MatlDesc") || _fields[i].equals("OptStore.MatlDesc")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, optStore.getMatlDesc());
					}
					else if(_fields[i].equals("OptTyp") || _fields[i].equals("OptStore.OptTyp")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, optStore.getOptTyp());
					}
					else if(_fields[i].equals("MatlTyp") || _fields[i].equals("OptStore.MatlTyp")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, optStore.getMatlTyp());
					}
					else if(_fields[i].equals("LotSize") || _fields[i].equals("OptStore.LotSize")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, optStore.getLotSize());
					}
					else if(_fields[i].equals("IsPanel") || _fields[i].equals("OptStore.IsPanel")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, optStore.getIsPanel());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("OptStore.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, optStore.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("OptStore.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, optStore.getCreateBy());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("OptStore.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(optStore.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("OptStore.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, optStore.getLastUpd());
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("OptStore.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(optStore.getLastDate().getTime()));
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("OptStoreHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update OptStore set ";
		String[] _fields = fields.replaceAll("OptStore\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("GpCode") || _fields[i].equals("OptStore.GpCode"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("MatlRev") || _fields[i].equals("OptStore.MatlRev"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("MatlNm") || _fields[i].equals("OptStore.MatlNm"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("MatlDesc") || _fields[i].equals("OptStore.MatlDesc"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("OptTyp") || _fields[i].equals("OptStore.OptTyp"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("MatlTyp") || _fields[i].equals("OptStore.MatlTyp"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LotSize") || _fields[i].equals("OptStore.LotSize"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("IsPanel") || _fields[i].equals("OptStore.IsPanel"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Status") || _fields[i].equals("OptStore.Status"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("OptStore.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("OptStore.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("OptStore.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastDate") || _fields[i].equals("OptStore.LastDate"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(OptStore optStore,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("GpCode") || _fields[i].equals("OptStore.GpCode")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, optStore.getGpCode());
					}
					else if(_fields[i].equals("MatlRev") || _fields[i].equals("OptStore.MatlRev")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, optStore.getMatlRev());
					}
					else if(_fields[i].equals("MatlNm") || _fields[i].equals("OptStore.MatlNm")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, optStore.getMatlNm());
					}
					else if(_fields[i].equals("MatlDesc") || _fields[i].equals("OptStore.MatlDesc")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, optStore.getMatlDesc());
					}
					else if(_fields[i].equals("OptTyp") || _fields[i].equals("OptStore.OptTyp")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, optStore.getOptTyp());
					}
					else if(_fields[i].equals("MatlTyp") || _fields[i].equals("OptStore.MatlTyp")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, optStore.getMatlTyp());
					}
					else if(_fields[i].equals("LotSize") || _fields[i].equals("OptStore.LotSize")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, optStore.getLotSize());
					}
					else if(_fields[i].equals("IsPanel") || _fields[i].equals("OptStore.IsPanel")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, optStore.getIsPanel());
					}
					else if(_fields[i].equals("Status") || _fields[i].equals("OptStore.Status")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, optStore.getStatus());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("OptStore.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, optStore.getCreateBy());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("OptStore.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(optStore.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("OptStore.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, optStore.getLastUpd());
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("OptStore.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(optStore.getLastDate().getTime()));
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("OptStoreHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(OptStore optStore) {
		String _fields = "";
		if(null != optStore.getMatlNo())
			_fields += "OptStore.MatlNo,";
		if(null != optStore.getGpCode())
			_fields += "OptStore.GpCode,";
		if(null != optStore.getMatlRev())
			_fields += "OptStore.MatlRev,";
		if(null != optStore.getMatlNm())
			_fields += "OptStore.MatlNm,";
		if(null != optStore.getMatlDesc())
			_fields += "OptStore.MatlDesc,";
		if(null != optStore.getOptTyp())
			_fields += "OptStore.OptTyp,";
		if(null != optStore.getMatlTyp())
			_fields += "OptStore.MatlTyp,";
		if(null != optStore.getLotSize())
			_fields += "OptStore.LotSize,";
		if(null != optStore.getIsPanel())
			_fields += "OptStore.IsPanel,";
		if(null != optStore.getStatus())
			_fields += "OptStore.Status,";
		if(null != optStore.getCreateBy())
			_fields += "OptStore.CreateBy,";
		if(null != optStore.getCreateDate())
			_fields += "OptStore.CreateDate,";
		if(null != optStore.getLastUpd())
			_fields += "OptStore.LastUpd,";
		if(null != optStore.getLastDate())
			_fields += "OptStore.LastDate,";

		return _fields.substring(0, _fields.length()-1);
	}
}