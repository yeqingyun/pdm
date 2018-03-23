package gnwf.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;

import gnwf.dao.helper.CQStateHelper;
import gnwf.vo.CQState;

public class CQStateDAO extends BasicDAO implements GenericDAO {

	private CQStateHelper cqStateHelper = new CQStateHelper();

	public List<CQState> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return cqStateHelper.getQueryList(query(sql),fields);
		}catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("CQStateDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<CQState> list = cqStateHelper.getQueryList(query(sql),fields);
			if(list.size() > 0){
				return (CQState)list.get(0);
			}else{
				return null;
			}
		}catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("CQStateDAO.load", e);
			throw e;
		}
	}

	public Object load(Object cqState) throws java.sql.SQLException {
		CQState _cqState = (CQState)cqState;
		String sql = "select " + CQState.SELF_FIELDS + cqStateHelper.getSqlString() + 
				" and Statedef.Id = '" + _cqState.getId() + "'";
		try {
			List<CQState> list = cqStateHelper.getQueryList(query(sql),CQState.SELF_FIELDS);
			if(list.size() > 0){
				return (CQState)list.get(0);
			}else{
				return null;
			}
		}catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("CQStateDAO.load", e);
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