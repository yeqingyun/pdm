package gnhr.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnhr.dao.helper.HrJobCfgHelper;
import gnhr.vo.HrJobCfg;

public class HrJobCfgDAO extends BasicDAO implements GenericDAO {

	private HrJobCfgHelper hrJobCfgHelper = new HrJobCfgHelper();

	public void insert(Object hrJobCfg) throws java.sql.SQLException {
		HrJobCfg _hrJobCfg = (HrJobCfg)hrJobCfg;
		String fields = hrJobCfgHelper.getFields(_hrJobCfg);
		String sql = hrJobCfgHelper.getInsertSql(fields);
		try {
			hrJobCfgHelper.pstmtInsert(_hrJobCfg, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("HrJobCfgDAO.insert", e);
			throw e;
		}
	}

	public void update(Object hrJobCfg) throws java.sql.SQLException {
		HrJobCfg _hrJobCfg = (HrJobCfg)hrJobCfg;
		String fields = hrJobCfgHelper.getFields(_hrJobCfg);
		String sql = hrJobCfgHelper.getUpdateSql(fields, "HrJobCfg.JobId" ,_hrJobCfg.getJobId().toString());
		try {
			hrJobCfgHelper.pstmtUpdate(_hrJobCfg, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("HrJobCfgDAO.update", e);
			throw e;
		}
	}

	public List<HrJobCfg> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return hrJobCfgHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("HrJobCfgDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<HrJobCfg> list = hrJobCfgHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (HrJobCfg)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("HrJobCfgDAO.load", e);
			throw e;
		}
	}

	public Object load(Object hrJobCfg) throws java.sql.SQLException {
		HrJobCfg _hrJobCfg = (HrJobCfg)hrJobCfg;
		String sql = "select "+HrJobCfg.ALL_FIELDS+hrJobCfgHelper.getSqlString()+" and HrJobCfg.JobId = '"+_hrJobCfg.getJobId()+"'";
		try {
			List<HrJobCfg> list = hrJobCfgHelper.getQueryList(query(sql),HrJobCfg.ALL_FIELDS);
			if(list.size() > 0)
				return (HrJobCfg)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("HrJobCfgDAO.load", e);
			throw e;
		}
	}
}