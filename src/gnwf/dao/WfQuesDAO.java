package gnwf.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import org.frm.jdbc.DbConnUtil;

import gnwf.dao.helper.WfQuesHelper;
import gnwf.facade.QuesRespFacade;
import gnwf.vo.QuesResp;
import gnwf.vo.WfQues;
import gnwf.ww.MSG;

public class WfQuesDAO extends BasicDAO implements GenericDAO {

	private WfQuesHelper wfQuesHelper = new WfQuesHelper();

	public void insert(Object wfQues) throws java.sql.SQLException {
		WfQues _wfQues = (WfQues)wfQues;
		String fields = wfQuesHelper.getFields(_wfQues);
		String sql = wfQuesHelper.getInsertSql(fields);
		try {
			wfQuesHelper.pstmtInsert(_wfQues, sql, fields);
			System.out.println("sql~~~~~~~~~~~"+sql);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfQuesDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wfQues) throws java.sql.SQLException {
		WfQues _wfQues = (WfQues)wfQues;
		String fields = wfQuesHelper.getFields(_wfQues);
		String sql = wfQuesHelper.getUpdateSql(fields, "WfQues.QuesId" ,_wfQues.getQuesId().toString());
		try {
			wfQuesHelper.pstmtUpdate(_wfQues, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfQuesDAO.update", e);
			throw e;
		}
	}

	public List<WfQues> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wfQuesHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfQuesDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WfQues> list = wfQuesHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WfQues)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfQuesDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wfQues) throws java.sql.SQLException {
		WfQues _wfQues = (WfQues)wfQues;
		/*
		//获取带项目流程问题
		public String getSqlString2() {
			return " from WfQues " +
				   " left join Usr on WfQues.UserId = Usr.Id" +
				   " inner join WfRd on WfQues.WfNo = WfRd.WfNo" +
	               " where 1=1 and WfQues.Status <>"+MSG.WFQUES_STATUS_0;
		}*/
		String queSql= "";
		System.out.println(_wfQues.getSelType()+"3333333333333");
		/*if (_wfQues.getSelType() == 1) {*/
			//获取全部问题
			 queSql = " from WfQues left join Usr on WfQues.UserId = Usr.Id  where 1=1 and WfQues.Status <> 0";
		/*}else if (_wfQues.getSelType() == 2) {
			//获取带项目流程问题
			 queSql = " from WfQues left join Usr on WfQues.UserId = Usr.Id inner join WfRd on WfQues.WfNo = WfRd.WfNo where 1=1 and WfQues.Status <> 0";
		}else if (_wfQues.getSelType() == 3) {
			//获取不带项目流程问题
			 queSql = " from WfQues left join Usr on WfQues.UserId = Usr.Id  where 1=1 and WfQues.WfNo = ' ' and WfQues.Status <> 0";
					
		}*/
			 System.out.println(_wfQues.getWfNos());
		//String countSql = ",(select count(QuesId) from WfQues  where WfQues.Status = '30' where WfQues.WfNo IN ("+_wfQues.getWfNos()+")) AS QuesClose";
		/*",(select count(QuesId) from WfQues  where WfQues.Status = '21' or WfQues.Status = '1'or WfQues.Status = '9'or WfQues.Status = '10'or WfQues.Status = '11') AS QuesOpen" +
		",(select count(QuesId) from WfQues  where WfQues.Status = '30') AS QuesClose" +
		",(select count(QuesId) from WfQues  where WfQues.Status = '40') AS QuesRisk" +*/
		String sql = "select "+WfQues.ALL_LIST_FIELDS+queSql+" and WfQues.QuesId = '"+_wfQues.getQuesId()+"'";
		System.out.println(sql);
		try {
			List<WfQues> list = wfQuesHelper.getQueryList(query(sql),WfQues.ALL_LIST_FIELDS);
			if(list.size() > 0)
				return (WfQues)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfQuesDAO.load", e);
			throw e;
		}
	}
	
	public String findWfQuesScheNm (String sql ) throws Exception{
		String scheNm = null;
		try {
			DbConnUtil.buildDbconn(3);
			ResultSet  rs = query(sql);
			while(rs.next()){
				scheNm = rs.getString("SchNm");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	
	public int getMaxId2(String sql) throws Exception{
		int maxId =0;
		ResultSet  rs = query(sql);
		while(rs.next()){
			maxId = rs.getInt("maxid");
		}
		return maxId;
	}
	
	
}