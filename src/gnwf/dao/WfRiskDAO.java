package gnwf.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import org.frm.jdbc.DbConnUtil;

import gnwf.dao.helper.WfRiskHelper;
import gnwf.vo.WfRisk;

public class WfRiskDAO extends BasicDAO implements GenericDAO {

	private WfRiskHelper wfRiskHelper = new WfRiskHelper();

	public void insert(Object wfRisk) throws java.sql.SQLException {
		WfRisk _wfRisk = (WfRisk)wfRisk;
		String fields = wfRiskHelper.getFields(_wfRisk);
		String sql = wfRiskHelper.getInsertSql(fields);
		try {
			wfRiskHelper.pstmtInsert(_wfRisk, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRiskDAO.insert", e);
			throw e;
		}
	}
	
	public String insertAndGetId(Object wfRisk) throws java.sql.SQLException {
		WfRisk _wfRisk = (WfRisk)wfRisk;
		String fields = wfRiskHelper.getFields(_wfRisk);
		String sql = wfRiskHelper.getInsertSql(fields);
		try {
			return wfRiskHelper.pstmtInsert(sql, _wfRisk,fields);
		}catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRiskDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfRisk) throws java.sql.SQLException {
		WfRisk _wfRisk = (WfRisk)wfRisk;
		String fields = wfRiskHelper.getFields(_wfRisk);
		String sql = wfRiskHelper.getUpdateSql(fields, "WfRisk.RiskId" ,_wfRisk.getRiskId().toString());
		try {
			wfRiskHelper.pstmtUpdate(_wfRisk, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRiskDAO.update", e);
			throw e;
		}
	}

	public List<WfRisk> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfRiskHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRiskDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfRisk> list = wfRiskHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfRisk)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRiskDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfRisk) throws java.sql.SQLException {
		WfRisk _wfRisk = (WfRisk)wfRisk;
		String sql = "select "+WfRisk.SELF_FIELDS+wfRiskHelper.getSqlString()+" and WfRisk.RiskId = '"+_wfRisk.getRiskId()+"'";
		try {
			List<WfRisk> list = wfRiskHelper.getQueryList(query(sql),WfRisk.SELF_FIELDS);
			if(list.size() > 0)
				return (WfRisk)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRiskDAO.load", e);
			throw e;
		}
	}
	
	public String findWfQuesScheNm (String sql) throws Exception{
		String scheNm = null;
		try {
			DbConnUtil.buildDbconn(3);
			ResultSet  rs = query(sql);
			while(rs.next()){
				scheNm = rs.getString("SchNm");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbConnUtil.closeDbconn();
		}
		return scheNm;
		
	}
	
	public int getMaxId (String sql) throws Exception{
		int maxId =0;
		try {
			DbConnUtil.buildDbconn(3);
			ResultSet  rs = query(sql);
			while(rs.next()){
				maxId = rs.getInt("maxid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			DbConnUtil.closeDbconn();
		}
		return maxId;
	}
	
}