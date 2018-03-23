package gnwf.dao;

import gnwf.utill.ResultSetToObject;
import gnwf.vo.WfPpReport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;

public class WfPpReportDAO extends BasicDAO implements GenericDAO {
	private WfPpReport wfCfgHelper = new WfPpReport();
	public void insert(Object obj) throws SQLException {
		insert(obj);
	}

	public Object load(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object load(String s, String s1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<?> query(String sql, String fields) throws SQLException {
		try {
			System.out.println(sql);
			//query(sql);
			return wfCfgHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfCfgDAO.query", e);
			throw e;
		}
	}

	public void update(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	public Object queryALL(String sql) throws SQLException {
		try {
			Logger.getLogger(this.getClass()).info("query:sql="+sql);
			ResultSet resultSet= query(sql);
			return ResultSetToObject.turnToMap(resultSet);
			
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfCfgDAO.query", e);
			throw e;
		}
	}
	

}
