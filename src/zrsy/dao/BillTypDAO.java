package zrsy.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrsy.dao.helper.BillTypHelper;
import zrsy.vo.BillTyp;

public class BillTypDAO extends BasicDAO implements GenericDAO {

	private BillTypHelper billTypHelper = new BillTypHelper();

	public void insert(Object billTyp) throws java.sql.SQLException {
		BillTyp _billTyp = (BillTyp)billTyp;
		String fields = billTypHelper.getFields(_billTyp);
		String sql = billTypHelper.getInsertSql(fields);
		try {
			billTypHelper.pstmtInsert(_billTyp, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("BillTypDAO.insert", e);
			throw e;
		}
	}

	public void update(Object billTyp) throws java.sql.SQLException {
		BillTyp _billTyp = (BillTyp)billTyp;
		String fields = billTypHelper.getFields(_billTyp);
		String sql = billTypHelper.getUpdateSql(fields, "BillTyp.Id" ,_billTyp.getId().toString());
		try {
			billTypHelper.pstmtUpdate(_billTyp, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("BillTypDAO.update", e);
			throw e;
		}
	}

	public List<BillTyp> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return billTypHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("BillTypDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<BillTyp> list = billTypHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (BillTyp)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("BillTypDAO.load", e);
			throw e;
		}
	}

	public Object load(Object billTyp) throws java.sql.SQLException {
		BillTyp _billTyp = (BillTyp)billTyp;
		String sql = "select "+BillTyp.ALL_FIELDS+billTypHelper.getSqlString()+" and BillTyp.Id = '"+_billTyp.getId()+"'";
		try {
			List<BillTyp> list = billTypHelper.getQueryList(query(sql),BillTyp.ALL_FIELDS);
			if(list.size() > 0)
				return (BillTyp)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("BillTypDAO.load", e);
			throw e;
		}
	}
}