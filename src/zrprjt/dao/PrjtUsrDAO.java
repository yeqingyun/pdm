package zrprjt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import org.frm.jdbc.DbConnUtil;

import zrprjt.dao.helper.PrjtUsrHelper;
import zrprjt.vo.PrjtUsr;
import zrprjt.vo.SchCfg;

public class PrjtUsrDAO extends BasicDAO implements GenericDAO {
	private PrjtUsrHelper prjtUsrHelper = new PrjtUsrHelper();

	public void insert(Object prjtUsr) throws java.sql.SQLException {
		PrjtUsr _prjtUsr = (PrjtUsr)prjtUsr;
		String fields = prjtUsrHelper.getFields(_prjtUsr);
		String sql = prjtUsrHelper.getInsertSql(fields);
		try {
			prjtUsrHelper.pstmtInsert(_prjtUsr, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtUsrDAO.insert", e);
			throw e;
		}
	}

	public void update(Object prjtUsr) throws java.sql.SQLException {
		PrjtUsr _prjtUsr = (PrjtUsr)prjtUsr;
		String fields = prjtUsrHelper.getFields(_prjtUsr);
		String sql = prjtUsrHelper.getUpdateSql(fields, "PrjtUsr.Id" ,String.valueOf(_prjtUsr.getId()));
		try {
			prjtUsrHelper.pstmtUpdate(_prjtUsr, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtUsrDAO.update", e);
			throw e;
		}
	}

	public List<PrjtUsr> query(String sql, String fields) throws java.sql.SQLException {
		try {
//			String usrSql = "select "+ Usr.SELF_FIELDS+" from Usr ";
//			List<Usr> usrs =  new UsrHelper().getQueryList(query(usrSql),Usr.SELF_FIELDS);
			List<PrjtUsr> prjtUsrs =  prjtUsrHelper.getQueryList(query(sql),fields);
			
//			for(PrjtUsr pu : prjtUsrs){
//				for(Usr u:usrs){
//					if(pu.getUsrId().intValue()==u.getId().intValue()){
//						pu.setUserName(u.getUsrName());
//					}
//				}
//			}
			return prjtUsrs;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtUsrDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<PrjtUsr> list = prjtUsrHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (PrjtUsr)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtUsrDAO.load", e);
			throw e;
		}
	}

	public Object load(Object prjtUsr) throws java.sql.SQLException {
		PrjtUsr _prjtUsr = (PrjtUsr)prjtUsr;
		String sql = "select "+PrjtUsr.ALL_FIELDS+prjtUsrHelper.getSqlString()
				+" and PrjtUsr.Id = "+_prjtUsr.getId();
		
		try {
			List<PrjtUsr> list = prjtUsrHelper.getQueryList(query(sql),PrjtUsr.ALL_FIELDS);
			if(list.size() > 0)
				return (PrjtUsr)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtUsrDAO.load", e);
			throw e;
		}
	}
	
	public int getMaxId (String sql) throws Exception{
		int maxId =0;
		try {
			DbConnUtil.buildDbconn(1);
			ResultSet  rs = query(sql);
			while(rs.next()){
				maxId = rs.getInt("maxid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maxId;
	}
}