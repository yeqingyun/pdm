package gnwf.facade;

import gnwf.dao.CQDefectDAO;
import gnwf.dao.CQStateDAO;
import gnwf.dao.helper.CQStateHelper;
import gnwf.vo.CQState;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

public class CQStateFacade {
	
	public CQState findById(String sql, String fields) throws Exception {
		DbConnUtil.buildDbconn(6);
		try {
			return (CQState) DAOFactory.getDAO(CQStateDAO.class).load(sql, fields);
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}
	
	public CQState findById(CQState state) throws Exception {
		DbConnUtil.buildDbconn(6);
		try {
			CQState cqState = (CQState) DAOFactory.getDAO(CQDefectDAO.class).load(state);
			return cqState;
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	public CQState findBy(CQState state) throws Exception {
		String sql = SqlUtil.getSql4All(CQStateHelper.class, state,CQState.SELF_FIELDS);
		DbConnUtil.buildDbconn(6);
		try {
			return (CQState) DAOFactory.getDAO(CQStateDAO.class).load(sql,CQState.SELF_FIELDS);
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<CQState> find(String sql, String fields) throws Exception {
		DbConnUtil.buildDbconn(6);
		try {
			return (List<CQState>) DAOFactory.getDAO(CQStateDAO.class).query(sql,fields);
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<CQState> findAll(CQState state) throws Exception {
		String sql = SqlUtil.getSql4All(CQStateHelper.class, state,CQState.SELF_FIELDS);
		DbConnUtil.buildDbconn(6);
		try {
			return (List<CQState>) DAOFactory.getDAO(CQStateDAO.class).query(sql,CQState.SELF_FIELDS);
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<CQState> find(CQState state, PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(CQStateHelper.class, state, pageVO,CQState.SELF_FIELDS);
		System.out.println(sql);
		DbConnUtil.buildDbconn(6);
		try {
			return (List<CQState>) DAOFactory.getDAO(CQStateDAO.class).query(sql,CQState.SELF_FIELDS);
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<CQState> findmy(String conditionsql, PageVO pageVO) throws Exception {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize * pageVO.getPage() - pageSize;
		String fields = CQState.SELF_FIELDS;
		String mstr = "select top " + pages + " State.Id " + conditionsql
				+ " order by State.Id ";
		String sql = "select top " + pageSize + " " + fields + conditionsql
				+ " and State.Id not in(" + mstr + ") "
				+ " order by State.Id ";
		System.out.println(sql);
		DbConnUtil.buildDbconn(6);
		try {
			return (List<CQState>) DAOFactory.getDAO(CQStateDAO.class).query(sql,fields);
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<CQState> findmy(String conditionsql) throws Exception {
		String sql = "select " + CQState.SELF_FIELDS + conditionsql;
		DbConnUtil.buildDbconn(6);
		try {
			return (List<CQState>) DAOFactory.getDAO(CQStateDAO.class).query(sql,CQState.SELF_FIELDS);
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<CQState> find(CQState state) throws Exception {
		String sql = SqlUtil.getSql4All(CQStateHelper.class, state,CQState.SELF_FIELDS);
		DbConnUtil.buildDbconn(6);
		try {
			return (List<CQState>) DAOFactory.getDAO(CQStateDAO.class).query(sql,CQState.SELF_FIELDS);
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(String sql) throws Exception {
		DbConnUtil.buildDbconn(6);
		try {
			return DAOFactory.getDAO(CQStateDAO.class).amount(sql);
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(CQState state) throws Exception {
		DbConnUtil.buildDbconn(6);
		try {
			return DAOFactory.getDAO(CQStateDAO.class).amount(new CQStateHelper().getSql4Amount(state));
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}
}