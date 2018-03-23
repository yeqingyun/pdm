package gnmail.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnmail.dao.helper.MailCfgHelper;
import gnmail.vo.MailCfg;

public class MailCfgDAO extends BasicDAO implements GenericDAO {

	private MailCfgHelper mailCfgHelper = new MailCfgHelper();

	public void insert(Object mailCfg) throws java.sql.SQLException {
		MailCfg _mailCfg = (MailCfg)mailCfg;
		String fields = mailCfgHelper.getFields(_mailCfg);
		String sql = mailCfgHelper.getInsertSql(fields);
		try {
			mailCfgHelper.pstmtInsert(_mailCfg, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailCfgDAO.insert", e);
			throw e;
		}
	}

	public void update(Object mailCfg) throws java.sql.SQLException {
		MailCfg _mailCfg = (MailCfg)mailCfg;
		String fields = mailCfgHelper.getFields(_mailCfg);
		String sql = mailCfgHelper.getUpdateSql(fields, "MailCfg.CfgId" ,_mailCfg.getCfgId().toString());
		try {
			mailCfgHelper.pstmtUpdate(_mailCfg, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailCfgDAO.update", e);
			throw e;
		}
	}

	public List<MailCfg> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return mailCfgHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailCfgDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<MailCfg> list = mailCfgHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (MailCfg)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailCfgDAO.load", e);
			throw e;
		}
	}

	public Object load(Object mailCfg) throws java.sql.SQLException {
		MailCfg _mailCfg = (MailCfg)mailCfg;
		String sql = "select "+MailCfg.ALL_FIELDS+mailCfgHelper.getSqlString()+" and MailCfg.CfgId = '"+_mailCfg.getCfgId()+"'";
		try {
			List<MailCfg> list = mailCfgHelper.getQueryList(query(sql),MailCfg.ALL_FIELDS);
			if(list.size() > 0)
				return (MailCfg)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("MailCfgDAO.load", e);
			throw e;
		}
	}
}