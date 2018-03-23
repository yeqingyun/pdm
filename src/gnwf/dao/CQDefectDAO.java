package gnwf.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;

import gnwf.dao.helper.CQDefectHelper;
import gnwf.vo.CQDefect;

public class CQDefectDAO extends BasicDAO implements GenericDAO {

	private CQDefectHelper cqDefectHelper = new CQDefectHelper();

	public List<CQDefect> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return cqDefectHelper.getQueryList(query(sql),fields);
		}catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("CQDefectDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<CQDefect> list = cqDefectHelper.getQueryList(query(sql),fields);
			if(list.size() > 0){
				return (CQDefect)list.get(0);
			}else{
				return null;
			}
		}catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("CQDefectDAO.load", e);
			throw e;
		}
	}

	public Object load(Object cqDefect) throws java.sql.SQLException {
		CQDefect _cqDefect = (CQDefect)cqDefect;
		String sql = "select " + CQDefect.LIST_FIELDS + cqDefectHelper.getSqlString() + 
				" and Defect.Id = '" + _cqDefect.getId() + "'";
		try {
			List<CQDefect> list = cqDefectHelper.getQueryList(query(sql),CQDefect.LIST_FIELDS);
			if(list.size() > 0){
				return (CQDefect)list.get(0);
			}else{
				return null;
			}
		}catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("CQDefectDAO.load", e);
			throw e;
		}
	}

	@Override
	public void insert(Object arg0) throws SQLException {
	}

	@Override
	public void update(Object arg0) throws SQLException {
	}
}