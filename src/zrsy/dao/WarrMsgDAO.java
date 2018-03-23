package zrsy.dao;
import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import zrsy.dao.helper.WarrMsgHelper;
import zrsy.vo.WarrMsg;
public class WarrMsgDAO extends BasicDAO implements GenericDAO {

	private WarrMsgHelper warrMsgHelper = new WarrMsgHelper();

	public void insert(Object warrMsg) throws java.sql.SQLException {
		WarrMsg _warrMsg = (WarrMsg)warrMsg;
		String fields = warrMsgHelper.getFields(_warrMsg);
		String sql = warrMsgHelper.getInsertSql(fields);
		try {
			warrMsgHelper.pstmtInsert(_warrMsg, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WarrMsgDAO.insert", e);
			throw e;
		}
	}

	public void update(Object warrMsg) throws java.sql.SQLException {
		WarrMsg _warrMsg = (WarrMsg)warrMsg;
		String fields = warrMsgHelper.getFields(_warrMsg);
		String sql = warrMsgHelper.getUpdateSql(fields, "WarrMsg.MsgId" ,_warrMsg.getMsgId().toString());
		try {
			warrMsgHelper.pstmtUpdate(_warrMsg, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WarrMsgDAO.update", e);
			throw e;
		}
	}

	public List<WarrMsg> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return warrMsgHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WarrMsgDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<WarrMsg> list = warrMsgHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (WarrMsg)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WarrMsgDAO.load", e);
			throw e;
		}
	}

	public Object load(Object warrMsg) throws java.sql.SQLException {
		WarrMsg _warrMsg = (WarrMsg)warrMsg;
		String sql = "select "+WarrMsg.ALL_FIELDS+warrMsgHelper.getSqlString()+" and WarrMsg.MsgId = '"+_warrMsg.getMsgId()+"'";
		try {
			List<WarrMsg> list = warrMsgHelper.getQueryList(query(sql),WarrMsg.ALL_FIELDS);
			if(list.size() > 0)
				return (WarrMsg)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WarrMsgDAO.load", e);
			throw e;
		}
	}
}
