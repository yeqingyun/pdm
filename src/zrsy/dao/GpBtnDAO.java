package zrsy.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrsy.dao.helper.GpBtnHelper;
import zrsy.vo.GpBtn;

public class GpBtnDAO extends BasicDAO implements GenericDAO {

	private GpBtnHelper gpBtnHelper = new GpBtnHelper();

	public void insert(Object gpBtn) throws java.sql.SQLException {
		GpBtn _gpBtn = (GpBtn)gpBtn;
		String fields = gpBtnHelper.getFields(_gpBtn);
		String sql = gpBtnHelper.getInsertSql(fields);
		try {
			gpBtnHelper.pstmtInsert(_gpBtn, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpBtnDAO.insert", e);
			throw e;
		}
	}

	public void update(Object gpBtn) throws java.sql.SQLException {
		GpBtn _gpBtn = (GpBtn)gpBtn;
		String fields = gpBtnHelper.getFields(_gpBtn);
		String sql = gpBtnHelper.getUpdateSql(fields, "GpBtn.GpId" ,_gpBtn.getGpId().toString());
		try {
			gpBtnHelper.pstmtUpdate(_gpBtn, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpBtnDAO.update", e);
			throw e;
		}
	}

	public List<GpBtn> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return gpBtnHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpBtnDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<GpBtn> list = gpBtnHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (GpBtn)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpBtnDAO.load", e);
			throw e;
		}
	}

	public Object load(Object gpBtn) throws java.sql.SQLException {
		GpBtn _gpBtn = (GpBtn)gpBtn;
		String sql = "select "+GpBtn.ALL_FIELDS+gpBtnHelper.getSqlString()+" and GpBtn.GpId = '"+_gpBtn.getGpId()+"'";
		try {
			List<GpBtn> list = gpBtnHelper.getQueryList(query(sql),GpBtn.ALL_FIELDS);
			if(list.size() > 0)
				return (GpBtn)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("GpBtnDAO.load", e);
			throw e;
		}
	}
}