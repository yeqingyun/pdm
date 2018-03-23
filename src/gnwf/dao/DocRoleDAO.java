package gnwf.dao;

import gnwf.dao.helper.DocRoleHelper;
import gnwf.vo.DocRole;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;

public class DocRoleDAO extends BasicDAO implements GenericDAO {

	private DocRoleHelper docRoleHelper = new DocRoleHelper();

	public void insert(Object docRole) throws java.sql.SQLException {
		DocRole _docRole = (DocRole)docRole;
		String fields = docRoleHelper.getFields(_docRole);
		String sql = docRoleHelper.getInsertSql(fields);
		try {
			docRoleHelper.pstmtInsert(_docRole, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DocRoleDAO.insert", e);
			throw e;
		}
	}

	public void update(Object docRole) throws java.sql.SQLException {
		try {
			docRoleHelper.update((DocRole)docRole,DocRole.UPDATE_FIELDS);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DocRoleDAO.update", e);
			throw e;
		}
	}

	public List<DocRole> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return docRoleHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DocRoleDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<DocRole> list = docRoleHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (DocRole)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DocRoleDAO.load", e);
			throw e;
		}
	}

	public Object load(Object docRole) throws java.sql.SQLException {
		DocRole _docRole = (DocRole)docRole;
		String sql = "select "+DocRole.ALL_FIELDS+docRoleHelper.getSqlString()+" and DocRole.DocCateId = '"+_docRole.getDocCateId()+"' and DocRole.PrjtRoleId='"+_docRole.getPrjtRoleId()+"' and DocRole.Type='"+_docRole.getType()+"'";
		try {
			List<DocRole> list = docRoleHelper.getQueryList(query(sql),DocRole.ALL_FIELDS);
			if(list.size() > 0)
				return (DocRole)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("DocRoleDAO.load", e);
			throw e;
		}
	}
}