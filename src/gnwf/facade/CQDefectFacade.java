package gnwf.facade;

import gnwf.dao.CQDefectDAO;
import gnwf.dao.helper.CQDefectHelper;
import gnwf.vo.CQDefect;
import java.util.List;
import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

public class CQDefectFacade {
	
	public CQDefect findById(String sql, String fields) throws Exception {
		DbConnUtil.buildDbconn(6);
		try {
			return (CQDefect) DAOFactory.getDAO(CQDefectDAO.class).load(sql, fields);
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}
	
	public CQDefect findById(CQDefect cqDefect) throws Exception {
		DbConnUtil.buildDbconn(6);
		try {
			CQDefect defect = (CQDefect) DAOFactory.getDAO(CQDefectDAO.class).load(cqDefect);
			return defect;
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	public CQDefect findBy(CQDefect cqDefect) throws Exception {
		String sql = SqlUtil.getSql4All(CQDefectHelper.class, cqDefect,CQDefect.LIST_FIELDS);
		DbConnUtil.buildDbconn(6);
		try {
			return (CQDefect) DAOFactory.getDAO(CQDefectDAO.class).load(sql,CQDefect.LIST_FIELDS);
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<CQDefect> find(String sql, String fields) throws Exception {
		DbConnUtil.buildDbconn(6);
		try {
			return (List<CQDefect>) DAOFactory.getDAO(CQDefectDAO.class).query(sql,fields);
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<CQDefect> findAll(CQDefect cqDefect) throws Exception {
		String sql = SqlUtil.getSql4All(CQDefectHelper.class, cqDefect,CQDefect.LIST_FIELDS);
		DbConnUtil.buildDbconn(6);
		try {
			return (List<CQDefect>) DAOFactory.getDAO(CQDefectDAO.class).query(sql,CQDefect.LIST_FIELDS);
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<CQDefect> find(CQDefect cqDefect, PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(CQDefectHelper.class, cqDefect, pageVO,CQDefect.LIST_FIELDS);
		System.out.println(sql);
		DbConnUtil.buildDbconn(6);
		try {
			return (List<CQDefect>) DAOFactory.getDAO(CQDefectDAO.class).query(sql,CQDefect.LIST_FIELDS);
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<CQDefect> findmy(String conditionsql, PageVO pageVO) throws Exception {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize * pageVO.getPage() - pageSize;
		String fields = CQDefect.LIST_FIELDS;
		String mstr = "select top " + pages + " Defect.Id " + conditionsql
				+ " order by Defect.Id ";
		String sql = "select top " + pageSize + " " + fields + conditionsql
				+ " and Defect.Id not in(" + mstr + ") "
				+ " order by Defect.Id ";
		System.out.println(sql);
		DbConnUtil.buildDbconn(6);
		try {
			return (List<CQDefect>) DAOFactory.getDAO(CQDefectDAO.class).query(sql,fields);
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<CQDefect> findmy(String conditionsql) throws Exception {
		String sql = "select " + CQDefect.SELF_FIELDS + conditionsql;
		DbConnUtil.buildDbconn(6);
		try {
			return (List<CQDefect>) DAOFactory.getDAO(CQDefectDAO.class).query(sql,CQDefect.SELF_FIELDS);
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<CQDefect> find(CQDefect cqDefect) throws Exception {
		String sql = SqlUtil.getSql4All(CQDefectHelper.class, cqDefect,CQDefect.LIST_FIELDS);
		DbConnUtil.buildDbconn(6);
		try {
			return (List<CQDefect>) DAOFactory.getDAO(CQDefectDAO.class).query(sql,CQDefect.LIST_FIELDS);
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(String sql) throws Exception {
		DbConnUtil.buildDbconn(6);
		try {
			return DAOFactory.getDAO(CQDefectDAO.class).amount(sql);
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(CQDefect cqDefect) throws Exception {
		DbConnUtil.buildDbconn(6);
		try {
			return DAOFactory.getDAO(CQDefectDAO.class).amount(new CQDefectHelper().getSql4Amount(cqDefect));
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}
}