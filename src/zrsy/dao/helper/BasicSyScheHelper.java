package zrsy.dao.helper;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;
import org.frm.comn.GenericUtil;
import zrsy.vo.SySche;
public class BasicSyScheHelper implements SqlHelper {
	public String getSqlString() {
		return " from SySche where 1=1";
	}

	public String getOrderBy() {
		return " order by SySche.ScheId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((SySche)object);
	}

	public String getSql4Amount(SySche sySche) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(sySche);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((SySche)object);
	}

	public String getSql4Delete(SySche sySche) {
		return "delete from SySche where 1=1"+getSqlCondition(sySche);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((SySche)object,fields);
	}

	public String getSql4All(SySche sySche, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(sySche)+getOrderBy();
	}

	public String getSql4Pages(Object object, String fields) {
		return getSql4Pages((SySche)object,fields);
	}

	public String getSql4Pages(SySche sySche, String fields) {
		int currentPage = sySche.getPageNext().getCurrentPage();
		int pageSize = sySche.getPageNext().getPageSize();
		int pages = pageSize*currentPage-pageSize;
		String mstr = "select top "+pages+" SySche.ScheId "+ getSqlString()+getSqlCondition(sySche)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(sySche)+" and SySche.ScheId not in("+mstr+") "+getOrderBy();
		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((SySche)object);
	}

	public String getSqlCondition(SySche sySche) {
		String sql = "";
		if(null != sySche.getScheId())
			sql += " and SySche.ScheId = "+sySche.getScheId();
		if(null != sySche.getScheNo() && !sySche.getScheNo().trim().equals(""))
			sql += " and SySche.ScheNo = '"+sySche.getScheNo().trim()+"'";
		if(null != sySche.getScheName() && !sySche.getScheName().trim().equals(""))
			sql += " and SySche.ScheName = '"+sySche.getScheName().trim()+"'";
		if(null != sySche.getScheUri() && !sySche.getScheUri().trim().equals(""))
			sql += " and SySche.ScheUri = '"+sySche.getScheUri().trim()+"'";
		if(null != sySche.getScheDay())
			sql += " and SySche.ScheDay = "+sySche.getScheDay();
		if(null != sySche.getScheUnit())
			sql += " and SySche.ScheUnit = "+sySche.getScheUnit();
		if(null != sySche.getStartStartTime())
			sql += " and SySche.StartTime >= '"+GenericUtil.dateFormat(sySche.getStartStartTime(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != sySche.getEndStartTime())
			sql += " and SySche.StartTime <= '"+GenericUtil.dateFormat(sySche.getEndStartTime(), "yyyy-MM-dd 59:59:00")+"'";
		if(null != sySche.getStatus())
			sql += " and SySche.Status = "+sySche.getStatus();
		if(null != sySche.getCreateBy())
			sql += " and SySche.CreateBy = "+sySche.getCreateBy();
		if(null != sySche.getStartCreateDate())
			sql += " and SySche.CreateDate >= '"+GenericUtil.dateFormat(sySche.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != sySche.getEndCreateDate())
			sql += " and SySche.CreateDate <= '"+GenericUtil.dateFormat(sySche.getEndCreateDate(), "yyyy-MM-dd 59:59:00")+"'";
		if(null != sySche.getLastUpd())
			sql += " and SySche.LastUpd = "+sySche.getLastUpd();
		if(null != sySche.getStartLastUpdDate())
			sql += " and SySche.LastUpdDate >= '"+GenericUtil.dateFormat(sySche.getStartLastUpdDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != sySche.getEndLastUpdDate())
			sql += " and SySche.LastUpdDate <= '"+GenericUtil.dateFormat(sySche.getEndLastUpdDate(), "yyyy-MM-dd 59:59:00")+"'";
		return sql;
	}

	public List<SySche> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<SySche> list = new ArrayList<SySche>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				SySche sySche = new SySche();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ScheId") || _fields[i].equals("SySche.ScheId"))
						sySche.setScheId(new Integer(rs.getInt("ScheId")));
					else if(_fields[i].equals("ScheNo") || _fields[i].equals("SySche.ScheNo"))
						sySche.setScheNo(rs.getString("ScheNo"));
					else if(_fields[i].equals("ScheName") || _fields[i].equals("SySche.ScheName"))
						sySche.setScheName(rs.getString("ScheName"));
					else if(_fields[i].equals("ScheUri") || _fields[i].equals("SySche.ScheUri"))
						sySche.setScheUri(rs.getString("ScheUri"));
					else if(_fields[i].equals("ScheDay") || _fields[i].equals("SySche.ScheDay"))
						sySche.setScheDay(new Integer(rs.getInt("ScheDay")));
					else if(_fields[i].equals("ScheUnit") || _fields[i].equals("SySche.ScheUnit"))
						sySche.setScheUnit(new Integer(rs.getInt("ScheUnit")));
					else if(_fields[i].equals("StartTime") || _fields[i].equals("SySche.StartTime"))
						sySche.setStartTime(rs.getTimestamp("StartTime"));
					else if(_fields[i].equals("Status") || _fields[i].equals("SySche.Status"))
						sySche.setStatus(new Integer(rs.getInt("Status")));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("SySche.CreateBy"))
						sySche.setCreateBy(new Integer(rs.getInt("CreateBy")));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("SySche.CreateDate"))
						sySche.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("SySche.LastUpd"))
						sySche.setLastUpd(new Integer(rs.getInt("LastUpd")));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("SySche.LastUpdDate"))
						sySche.setLastUpdDate(rs.getTimestamp("LastUpdDate"));
				}
				list.add(sySche);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("SyScheHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into SySche("+fields.replaceAll("SySche\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(SySche sySche,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
				if(_fields[i].equals("ScheId") || _fields[i].equals("SySche.ScheId")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, sySche.getScheId().intValue());
				}
				else if(_fields[i].equals("ScheNo") || _fields[i].equals("SySche.ScheNo")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, sySche.getScheNo());
				}
				else if(_fields[i].equals("ScheName") || _fields[i].equals("SySche.ScheName")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, sySche.getScheName());
				}
				else if(_fields[i].equals("ScheUri") || _fields[i].equals("SySche.ScheUri")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, sySche.getScheUri());
				}
				else if(_fields[i].equals("ScheDay") || _fields[i].equals("SySche.ScheDay")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, sySche.getScheDay().intValue());
				}
				else if(_fields[i].equals("ScheUnit") || _fields[i].equals("SySche.ScheUnit")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, sySche.getScheUnit().intValue());
				}
				else if(_fields[i].equals("StartTime") || _fields[i].equals("SySche.StartTime")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(sySche.getStartTime().getTime()));
				}
				else if(_fields[i].equals("Status") || _fields[i].equals("SySche.Status")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, sySche.getStatus().intValue());
				}
				else if(_fields[i].equals("CreateBy") || _fields[i].equals("SySche.CreateBy")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, sySche.getCreateBy().intValue());
				}
				else if(_fields[i].equals("CreateDate") || _fields[i].equals("SySche.CreateDate")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(sySche.getCreateDate().getTime()));
				}
				else if(_fields[i].equals("LastUpd") || _fields[i].equals("SySche.LastUpd")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, sySche.getLastUpd().intValue());
				}
				else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("SySche.LastUpdDate")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(sySche.getLastUpdDate().getTime()));
				}
			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("SyScheHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update SySche set ";
		String[] _fields = fields.replaceAll("SySche\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
				if(_fields[i].equals("ScheNo") || _fields[i].equals("SySche.ScheNo"))
					sql += _fields[i] + " = ?,";
				if(_fields[i].equals("ScheName") || _fields[i].equals("SySche.ScheName"))
					sql += _fields[i] + " = ?,";
				if(_fields[i].equals("ScheUri") || _fields[i].equals("SySche.ScheUri"))
					sql += _fields[i] + " = ?,";
				if(_fields[i].equals("ScheDay") || _fields[i].equals("SySche.ScheDay"))
					sql += _fields[i] + " = ?,";
				if(_fields[i].equals("ScheUnit") || _fields[i].equals("SySche.ScheUnit"))
					sql += _fields[i] + " = ?,";
				if(_fields[i].equals("StartTime") || _fields[i].equals("SySche.StartTime"))
					sql += _fields[i] + " = ?,";
				if(_fields[i].equals("Status") || _fields[i].equals("SySche.Status"))
					sql += _fields[i] + " = ?,";
				if(_fields[i].equals("CreateBy") || _fields[i].equals("SySche.CreateBy"))
					sql += _fields[i] + " = ?,";
				if(_fields[i].equals("CreateDate") || _fields[i].equals("SySche.CreateDate"))
					sql += _fields[i] + " = ?,";
				if(_fields[i].equals("LastUpd") || _fields[i].equals("SySche.LastUpd"))
					sql += _fields[i] + " = ?,";
				if(_fields[i].equals("LastUpdDate") || _fields[i].equals("SySche.LastUpdDate"))
					sql += _fields[i] + " = ?,";
		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(SySche sySche,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
				if(_fields[i].equals("ScheNo") || _fields[i].equals("SySche.ScheNo")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, sySche.getScheNo());
				}
				if(_fields[i].equals("ScheName") || _fields[i].equals("SySche.ScheName")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, sySche.getScheName());
				}
				else if(_fields[i].equals("ScheUri") || _fields[i].equals("SySche.ScheUri")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setString(index, sySche.getScheUri());
				}
				else if(_fields[i].equals("ScheDay") || _fields[i].equals("SySche.ScheDay")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, sySche.getScheDay().intValue());
				}
				else if(_fields[i].equals("ScheUnit") || _fields[i].equals("SySche.ScheUnit")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, sySche.getScheUnit().intValue());
				}
				else if(_fields[i].equals("StartTime") || _fields[i].equals("SySche.StartTime")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(sySche.getStartTime().getTime()));
				}
				else if(_fields[i].equals("Status") || _fields[i].equals("SySche.Status")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, sySche.getStatus().intValue());
				}
				else if(_fields[i].equals("CreateBy") || _fields[i].equals("SySche.CreateBy")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, sySche.getCreateBy().intValue());
				}
				else if(_fields[i].equals("CreateDate") || _fields[i].equals("SySche.CreateDate")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(sySche.getCreateDate().getTime()));
				}
				else if(_fields[i].equals("LastUpd") || _fields[i].equals("SySche.LastUpd")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, sySche.getLastUpd().intValue());
				}
				else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("SySche.LastUpdDate")) {
					index++;
					DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(sySche.getLastUpdDate().getTime()));
				}
			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("SyScheHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(SySche sySche) {
		String _fields = "";
		if(null != sySche.getScheId())
			_fields += "SySche.ScheId,";
		if(null != sySche.getScheNo() && !sySche.getScheNo().trim().equals(""))
			_fields += "SySche.ScheNo,";
		if(null != sySche.getScheName() && !sySche.getScheName().trim().equals(""))
			_fields += "SySche.ScheName,";
		if(null != sySche.getScheUri() && !sySche.getScheUri().trim().equals(""))
			_fields += "SySche.ScheUri,";
		if(null != sySche.getScheDay())
			_fields += "SySche.ScheDay,";
		if(null != sySche.getScheUnit())
			_fields += "SySche.ScheUnit,";
		if(null != sySche.getStartTime())
			_fields += "SySche.StartTime,";
		if(null != sySche.getStatus())
			_fields += "SySche.Status,";
		if(null != sySche.getCreateBy())
			_fields += "SySche.CreateBy,";
		if(null != sySche.getCreateDate())
			_fields += "SySche.CreateDate,";
		if(null != sySche.getLastUpd())
			_fields += "SySche.LastUpd,";
		if(null != sySche.getLastUpdDate())
			_fields += "SySche.LastUpdDate,";
		return _fields.substring(0, _fields.length()-1);
	}

	@Override
	public String getSql4Pages(Object arg0, PageVO arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}
}