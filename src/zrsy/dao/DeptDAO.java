package zrsy.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrsy.dao.helper.DeptHelper;
import zrsy.vo.Dept;

public class DeptDAO extends BasicDAO implements GenericDAO {

	private DeptHelper deptHelper = new DeptHelper();

	public void insert(Object dept) throws java.sql.SQLException {
		Dept _dept = (Dept)dept;
		String fields = deptHelper.getFields(_dept);
		String sql = deptHelper.getInsertSql(fields);
		try {
			deptHelper.pstmtInsert(_dept, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DeptDAO.insert", e);
			throw e;
		}
	}

	public void update(Object dept) throws java.sql.SQLException {
		Dept _dept = (Dept)dept;
		String fields = deptHelper.getFields(_dept);
		String sql = deptHelper.getUpdateSql(fields, "Dept.DeptId" ,_dept.getDeptId().toString());
		try {
			deptHelper.pstmtUpdate(_dept, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DeptDAO.update", e);
			throw e;
		}
	}

	public List<Dept> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return deptHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DeptDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<Dept> list = deptHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (Dept)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DeptDAO.load", e);
			throw e;
		}
	}

	public Object load(Object dept) throws java.sql.SQLException {
		Dept _dept = (Dept)dept;
		String sql = "select "+Dept.ALL_FIELDS+deptHelper.getSqlString()+" and Dept.DeptId = '"+_dept.getDeptId()+"'";
		try {
			List<Dept> list = deptHelper.getQueryList(query(sql),Dept.ALL_FIELDS);
			if(list.size() > 0)
				return (Dept)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DeptDAO.load", e);
			throw e;
		}
	}
}