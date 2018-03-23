package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.WFMatlCategoryHelper;
import gnwf.vo.WFMatlCategory;

public class WFMatlCategoryDAO extends BasicDAO implements GenericDAO {

	private WFMatlCategoryHelper wFMatlCategoryHelper = new WFMatlCategoryHelper();

	public void insert(Object wFMatlCategory) throws java.sql.SQLException {
		WFMatlCategory _wFMatlCategory = (WFMatlCategory)wFMatlCategory;
		String fields = wFMatlCategoryHelper.getFields(_wFMatlCategory);
		String sql = wFMatlCategoryHelper.getInsertSql(fields);
		try {
			wFMatlCategoryHelper.pstmtInsert(_wFMatlCategory, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WFMatlCategoryDAO.insert", e);
			throw e;
		}
	}

	public void update(Object wFMatlCategory) throws java.sql.SQLException {
		WFMatlCategory _wFMatlCategory = (WFMatlCategory)wFMatlCategory;
		String fields = wFMatlCategoryHelper.getFields(_wFMatlCategory);
		String sql = wFMatlCategoryHelper.getUpdateSql(fields, "WFMatlCategory.CategoryId" ,_wFMatlCategory.getCategoryId().toString());
		try {
			wFMatlCategoryHelper.pstmtUpdate(_wFMatlCategory, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WFMatlCategoryDAO.update", e);
			throw e;
		}
	}

	public List<WFMatlCategory> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return wFMatlCategoryHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WFMatlCategoryDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WFMatlCategory> list = wFMatlCategoryHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WFMatlCategory)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WFMatlCategoryDAO.load", e);
			throw e;
		}
	}

	public Object load(Object wFMatlCategory) throws java.sql.SQLException {
		WFMatlCategory _wFMatlCategory = (WFMatlCategory)wFMatlCategory;
		String sql = "select "+WFMatlCategory.ALL_FIELDS+wFMatlCategoryHelper.getSqlString()+" and WFMatlCategory.CategoryId = '"+_wFMatlCategory.getCategoryId()+"'";
		try {
			List<WFMatlCategory> list = wFMatlCategoryHelper.getQueryList(query(sql),WFMatlCategory.ALL_FIELDS);
			if(list.size() > 0)
				return (WFMatlCategory)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WFMatlCategoryDAO.load", e);
			throw e;
		}
	}
}