package gnwf.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import org.frm.jdbc.DbConnUtil;

import gnwf.dao.helper.WfOveSeaUsrHelper;
import gnwf.dao.helper.WfRdHelper;
import gnwf.utill.ResultSetToObject;
import gnwf.vo.WfOveSeaUsr;
import gnwf.vo.WfRd;

public class WfOveSeaUsrDAO extends BasicDAO implements GenericDAO {

	private WfRdHelper wfRdHelper = new WfRdHelper();

	private WfOveSeaUsrHelper wfOveSeaUsrHelper = new WfOveSeaUsrHelper();
	
	public void insert(Object wfOveSeaUsr) throws java.sql.SQLException {
		WfOveSeaUsr _wfOveSeaUsr = (WfOveSeaUsr)wfOveSeaUsr;
		//WfRd _wfRd = (WfRd)wfRd;
		String fields = wfOveSeaUsrHelper.getFields(_wfOveSeaUsr);
		String sql = wfOveSeaUsrHelper.getInsertSql(fields);
		System.out.println("11111113331111111"+sql);
		try {
			wfOveSeaUsrHelper.pstmtInsert(_wfOveSeaUsr, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfOveSeaUsrDAO.insert", e);
			throw e;
		}
	}

	
	
	public Object queryALL(String sql) throws SQLException {
		try {
			Logger.getLogger(this.getClass()).info("query:sql="+sql);
			ResultSet resultSet= query(sql);
			return ResultSetToObject.turnToMap(resultSet);
			
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfOveSeaUsrDAO.query", e);
			throw e;
		}
	}
	
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	
	
	
	public void update(Object wfRd) throws java.sql.SQLException {
		WfRd _wfRd = (WfRd)wfRd;
		String fields = wfRdHelper.getFields(_wfRd);
		String sql = wfRdHelper.getUpdateSql(fields, "WfRd.WfNo" ,_wfRd.getWfNo().toString());
		try {
			wfRdHelper.pstmtUpdate(_wfRd, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdDAO.update", e);
			throw e;
		}
	}

	public List<WfOveSeaUsr> query(String sql, String fields) throws java.sql.SQLException {
		/*try {
			return WfOveSeaUsrHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfOveSeaUsrDAO.query", e);
			throw e;
		}*/
		return null;
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfRd> list = wfRdHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfRd)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfRd) throws java.sql.SQLException {
		WfRd _wfRd = (WfRd)wfRd;
		String sql = "select "+WfRd.ALL_FIELDS+wfRdHelper.getSqlString()+" and WfRd.WfNo = '"+_wfRd.getWfNo()+"'";
		try {
			List<WfRd> list = wfRdHelper.getQueryList(query(sql),WfRd.ALL_FIELDS);
			if(list.size() > 0)
				return (WfRd)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfRdDAO.load", e);
			throw e;
		}
	}
	
	public int getRepeatSort (String projectNo,int scheId) throws Exception{
		int repeatSort =1; 
		String sql = "select max(RepeatSort) as RepeatSort from WfRd where ProjectNo = '"+projectNo +"' and ScheId = "+scheId;
		try {
			DbConnUtil.buildDbconn(3); 
			ResultSet  rs = query(sql);
			while(rs.next()){
				repeatSort = rs.getInt("RepeatSort")+1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbConnUtil.closeDbconn();
		}
		return repeatSort;
	}
	
	
	
}