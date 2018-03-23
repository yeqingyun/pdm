package zrsy.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrsy.dao.helper.BillSubsHelper;
import zrsy.vo.BillSubs;

public class BillSubsDAO extends BasicDAO implements GenericDAO {

	private BillSubsHelper billSubsHelper = new BillSubsHelper();

	public void insert(Object billSubs) throws java.sql.SQLException {
		BillSubs _billSubs = (BillSubs)billSubs;
		String fields = billSubsHelper.getFields(_billSubs);
		String sql = billSubsHelper.getInsertSql(fields);
		try {
			billSubsHelper.pstmtInsert(_billSubs, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("BillSubsDAO.insert", e);
			throw e;
		}
	}

	public void update(Object billSubs) throws java.sql.SQLException {
		BillSubs _billSubs = (BillSubs)billSubs;
		String fields = billSubsHelper.getFields(_billSubs);
		String sql = billSubsHelper.getUpdateSql(fields, "BillSubs.Id" ,_billSubs.getId().toString());
		try {
			billSubsHelper.pstmtUpdate(_billSubs, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("BillSubsDAO.update", e);
			throw e;
		}
	}

	public List<BillSubs> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return billSubsHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("BillSubsDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<BillSubs> list = billSubsHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (BillSubs)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("BillSubsDAO.load", e);
			throw e;
		}
	}

	public Object load(Object billSubs) throws java.sql.SQLException {
		BillSubs _billSubs = (BillSubs)billSubs;
		String sql = "select "+BillSubs.ALL_FIELDS+billSubsHelper.getSqlString()+" and BillSubs.Id = '"+_billSubs.getId()+"'";
		try {
			List<BillSubs> list = billSubsHelper.getQueryList(query(sql),BillSubs.ALL_FIELDS);
			if(list.size() > 0)
				return (BillSubs)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("BillSubsDAO.load", e);
			throw e;
		}
	}
}