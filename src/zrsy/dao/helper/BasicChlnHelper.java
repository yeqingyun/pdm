package zrsy.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrsy.vo.Chln;

public class BasicChlnHelper implements SqlHelper {
	public String getSqlString() {
		return " from Chln where 1=1";
	}

	public String getOrderBy() {
		return " order by Chln.Year";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((Chln)object);
	}

	public String getSql4Amount(Chln chln) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(chln);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((Chln)object);
	}

	public String getSql4Delete(Chln chln) {
		return "delete from Chln where 1=1"+getSqlCondition(chln);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((Chln)object,fields);
	}

	public String getSql4All(Chln chln, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(chln)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((Chln)object,pageVO,fields);
	}

	public String getSql4Pages(Chln chln,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" Chln.Year "+ getSqlString()+getSqlCondition(chln)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(chln)+" and Chln.Year not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((Chln)object);
	}

	public String getSqlCondition(Chln chln) {
		String sql = "";
		if(null != chln.getYear())
			sql += " and Chln.Year = '"+chln.getYear()+"'";
		if(null != chln.getMonth())
			sql += " and Chln.Month = '"+chln.getMonth()+"'";
		if(null != chln.getDay())
			sql += " and Chln.Day = '"+chln.getDay()+"'";
		if(null != chln.getChlnNo())
			sql += " and Chln.ChlnNo = '"+chln.getChlnNo()+"'";
		if(null != chln.getCreateBy())
			sql += " and Chln.CreateBy = '"+chln.getCreateBy()+"'";
		if(null != chln.getLastUpd())
			sql += " and Chln.LastUpd = '"+chln.getLastUpd()+"'";
		if(null != chln.getStartCreateDate()) 
			sql += " and Chln.CreateDate >= '"+GenericUtil.dateFormat(chln.getStartCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != chln.getEndCreateDate()) 
			sql += " and Chln.CreateDate <= '"+GenericUtil.dateFormat(chln.getEndCreateDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != chln.getStartLastDate()) 
			sql += " and Chln.LastDate >= '"+GenericUtil.dateFormat(chln.getStartLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != chln.getEndLastDate()) 
			sql += " and Chln.LastDate <= '"+GenericUtil.dateFormat(chln.getEndLastDate(), "yyyy-MM-dd 00:00:00")+"'";
		if(null != chln.getChlnTyp() && !chln.getChlnTyp().trim().equals(""))
			sql += " and Chln.ChlnTyp = '"+chln.getChlnTyp().trim()+"'";

		return sql;
	}

	public List<Chln> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<Chln> list = new ArrayList<Chln>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				Chln chln = new Chln();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Year") || _fields[i].equals("Chln.Year"))
						chln.setYear(rs.getInt("Year"));
					else if(_fields[i].equals("Month") || _fields[i].equals("Chln.Month"))
						chln.setMonth(rs.getInt("Month"));
					else if(_fields[i].equals("Day") || _fields[i].equals("Chln.Day"))
						chln.setDay(rs.getInt("Day"));
					else if(_fields[i].equals("ChlnNo") || _fields[i].equals("Chln.ChlnNo"))
						chln.setChlnNo(rs.getInt("ChlnNo"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("Chln.CreateBy"))
						chln.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("Chln.LastUpd"))
						chln.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("Chln.CreateDate"))
						chln.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastDate") || _fields[i].equals("Chln.LastDate"))
						chln.setLastDate(rs.getTimestamp("LastDate"));
					else if(_fields[i].equals("ChlnTyp") || _fields[i].equals("Chln.ChlnTyp"))
						chln.setChlnTyp(rs.getString("ChlnTyp"));

				}
				list.add(chln);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ChlnHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into Chln("+fields.replaceAll("Chln\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(Chln chln,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Year") || _fields[i].equals("Chln.Year")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, chln.getYear());
					}
					else if(_fields[i].equals("Month") || _fields[i].equals("Chln.Month")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, chln.getMonth());
					}
					else if(_fields[i].equals("Day") || _fields[i].equals("Chln.Day")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, chln.getDay());
					}
					else if(_fields[i].equals("ChlnNo") || _fields[i].equals("Chln.ChlnNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, chln.getChlnNo());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("Chln.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, chln.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("Chln.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, chln.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("Chln.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(chln.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("Chln.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(chln.getLastDate().getTime()));
					}
					else if(_fields[i].equals("ChlnTyp") || _fields[i].equals("Chln.ChlnTyp")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, chln.getChlnTyp());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("ChlnHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update Chln set ";
		String[] _fields = fields.replaceAll("Chln\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ChlnNo") || _fields[i].equals("Chln.ChlnNo"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateBy") || _fields[i].equals("Chln.CreateBy"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastUpd") || _fields[i].equals("Chln.LastUpd"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("CreateDate") || _fields[i].equals("Chln.CreateDate"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LastDate") || _fields[i].equals("Chln.LastDate"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(Chln chln,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("ChlnNo") || _fields[i].equals("Chln.ChlnNo")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, chln.getChlnNo());
					}
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("Chln.CreateBy")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, chln.getCreateBy());
					}
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("Chln.LastUpd")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, chln.getLastUpd());
					}
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("Chln.CreateDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(chln.getCreateDate().getTime()));
					}
					else if(_fields[i].equals("LastDate") || _fields[i].equals("Chln.LastDate")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setTimestamp(index, new java.sql.Timestamp(chln.getLastDate().getTime()));
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("ChlnHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(Chln chln) {
		String _fields = "";
		if(null != chln.getYear())
			_fields += "Chln.Year,";
		if(null != chln.getMonth())
			_fields += "Chln.Month,";
		if(null != chln.getDay())
			_fields += "Chln.Day,";
		if(null != chln.getChlnNo())
			_fields += "Chln.ChlnNo,";
		if(null != chln.getCreateBy())
			_fields += "Chln.CreateBy,";
		if(null != chln.getLastUpd())
			_fields += "Chln.LastUpd,";
		if(null != chln.getCreateDate())
			_fields += "Chln.CreateDate,";
		if(null != chln.getLastDate())
			_fields += "Chln.LastDate,";
		if(null != chln.getChlnTyp())
			_fields += "Chln.ChlnTyp,";

		return _fields.substring(0, _fields.length()-1);
	}
}