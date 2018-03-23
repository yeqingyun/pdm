package zrprjt.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;

import zrprjt.dao.helper.TaskUpRdHelper;
import zrprjt.vo.TaskUpRd;

public class TaskUpRdDAO extends BasicDAO implements GenericDAO {

	private TaskUpRdHelper taskUpRdHelper = new TaskUpRdHelper(); 

	public void insert(Object taskUpRd) throws java.sql.SQLException { 
		TaskUpRd _TaskUpRd = (TaskUpRd)taskUpRd;
		String fields = taskUpRdHelper.getFields(_TaskUpRd);
		String sql = taskUpRdHelper.getInsertSql(fields);
		try {
			taskUpRdHelper.pstmtInsert(_TaskUpRd, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("TaskUpRdDAO.insert", e);
			throw e;
		}
	}

	public void update(Object taskUpRd) throws java.sql.SQLException { 
		//TODO
//		TaskUpRd _TaskUpRd = (TaskUpRd)TaskUpRd;
//		String fields = TaskUpRdHelper.getFields(_TaskUpRd);
//		String sql = TaskUpRdHelper.getUpdateSql(fields, "TaskUpRd.RoleId" ,_TaskUpRd.getRoleId().toString());
//		try {
//			TaskUpRdHelper.pstmtUpdate(_TaskUpRd, sql, fields);
//		}
//		catch(java.sql.SQLException e) {
//			Logger.getLogger(this.getClass()).error("TaskUpRdDAO.update", e);
//			throw e;
//		}
	}

	public List<TaskUpRd> query(String sql, String fields) throws java.sql.SQLException {
		try {
//			String usrSql = "select "+ Usr.SELF_FIELDS+" from Usr ";
//			List<Usr> usrs =  new UsrHelper().getQueryList(query(usrSql),Usr.SELF_FIELDS);
			List<TaskUpRd> taskUpRds =  taskUpRdHelper.getQueryList(query(sql),fields);
			 
//			for(TaskUpRd pu : TaskUpRds){
//				for(Usr u:usrs){
//					if(pu.getUsrId().intValue()==u.getId().intValue()){
//						pu.setUserName(u.getUsrName());
//					}
//				}
//			}
			return taskUpRds;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("TaskUpRdDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<TaskUpRd> list = taskUpRdHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (TaskUpRd)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("TaskUpRdDAO.load", e);
			throw e;
		}
	}

	public Object load(Object taskUpRd) throws java.sql.SQLException { 
		return null;
//		TaskUpRd _TaskUpRd = (TaskUpRd)TaskUpRd;
//		String sql = "select "+TaskUpRd.ALL_FIELDS+TaskUpRdHelper.getSqlString()
//				+" and TaskUpRd.Id = "+_TaskUpRd.getId();
//		
//		try {
//			List<TaskUpRd> list = TaskUpRdHelper.getQueryList(query(sql),TaskUpRd.ALL_FIELDS);
//			if(list.size() > 0)
//				return (TaskUpRd)list.get(0);
//			else
//				return null;
//		}
//		catch(java.sql.SQLException e) {
//			Logger.getLogger(this.getClass()).error("TaskUpRdDAO.load", e);
//			throw e;
//		}
	}
}