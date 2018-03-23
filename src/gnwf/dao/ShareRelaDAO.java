package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;

import gnwf.dao.helper.ShareRelaHelper;
import gnwf.vo.ShareRela;

public class ShareRelaDAO extends BasicDAO implements GenericDAO {

	private ShareRelaHelper shareRelaHelper = new ShareRelaHelper();

	public void insert(Object shareRela) throws java.sql.SQLException {
		ShareRela _shareRela = (ShareRela)shareRela;
		String fields = shareRelaHelper.getFields(_shareRela);
		String sql = shareRelaHelper.getInsertSql(fields);
		try {
			shareRelaHelper.pstmtInsert(_shareRela, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ShareRelaDAO.insert", e);
			throw e;
		}
	}

	public void update(Object shareRela) throws java.sql.SQLException {
		ShareRela _shareRela = (ShareRela)shareRela;
		String fields = shareRelaHelper.getFields(_shareRela);
		String sql = shareRelaHelper.getUpdateSql(fields, _shareRela);
		try {
			shareRelaHelper.pstmtUpdate(_shareRela, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ShareRelaDAO.update", e);
			throw e;
		}
	}

	public List<ShareRela> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return shareRelaHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ShareRelaDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<ShareRela> list = shareRelaHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (ShareRela)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ShareRelaDAO.load", e);
			throw e;
		}
	}

	public Object load(Object shareRela) throws java.sql.SQLException {
		ShareRela _shareRela = (ShareRela)shareRela;
		String sql = "select "+ShareRela.ALL_FIELDS+shareRelaHelper.getSqlString()+" and ShareRela.WfDocId = '"+_shareRela.getWfDocId()+"'"+" and ShareRela.UsrId = '"+_shareRela.getUsrId()+"'";
		try {
			List<ShareRela> list = shareRelaHelper.getQueryList(query(sql),ShareRela.ALL_FIELDS);
			if(list.size() > 0)
				return (ShareRela)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ShareRelaDAO.load", e);
			throw e;
		}
	}
}