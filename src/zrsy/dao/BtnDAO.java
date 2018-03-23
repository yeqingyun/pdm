package zrsy.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrsy.dao.helper.BtnHelper;
import zrsy.vo.Btn;

public class BtnDAO extends BasicDAO implements GenericDAO {

	private BtnHelper btnHelper = new BtnHelper();

	public void insert(Object btn) throws java.sql.SQLException {
		Btn _btn = (Btn)btn;
		String fields = btnHelper.getFields(_btn);
		String sql = btnHelper.getInsertSql(fields);
		try {
			btnHelper.pstmtInsert(_btn, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("BtnDAO.insert", e);
			throw e;
		}
	}

	public void update(Object btn) throws java.sql.SQLException {
		Btn _btn = (Btn)btn;
		String fields = btnHelper.getFields(_btn);
		String sql = btnHelper.getUpdateSql(fields, "Btn.Id" ,_btn.getId().toString());
		try {
			btnHelper.pstmtUpdate(_btn, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("BtnDAO.update", e);
			throw e;
		}
	}

	public List<Btn> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return btnHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("BtnDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<Btn> list = btnHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (Btn)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("BtnDAO.load", e);
			throw e;
		}
	}

	public Object load(Object btn) throws java.sql.SQLException {
		Btn _btn = (Btn)btn;
		String sql = "select "+Btn.ALL_FIELDS+btnHelper.getSqlString()+" and Btn.Id = '"+_btn.getId()+"'";
		try {
			List<Btn> list = btnHelper.getQueryList(query(sql),Btn.ALL_FIELDS);
			if(list.size() > 0)
				return (Btn)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("BtnDAO.load", e);
			throw e;
		}
	}
}