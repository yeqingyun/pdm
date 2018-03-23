package gnwf.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.frm.dao.BasicDAO;
import org.frm.dao.GenericDAO;
import gnwf.dao.helper.QuesRespHelper;
import gnwf.vo.QuesResp;

public class QuesRespDAO extends BasicDAO implements GenericDAO {

	private QuesRespHelper quesRespHelper = new QuesRespHelper();

	public void insert(Object quesResp) throws java.sql.SQLException {
		QuesResp _quesResp = (QuesResp)quesResp;
		String fields = quesRespHelper.getFields(_quesResp);
		String sql = quesRespHelper.getInsertSql(fields);
		try {
			quesRespHelper.pstmtInsert(_quesResp, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("QuesRespDAO.insert", e);
			throw e;
		}
	}

	public void update(Object quesResp) throws java.sql.SQLException {
		QuesResp _quesResp = (QuesResp)quesResp;
		String fields = quesRespHelper.getFields(_quesResp);
		String sql = quesRespHelper.getUpdateSql(fields, "QuesResp.Id" ,_quesResp.getId().toString());
		try {
			quesRespHelper.pstmtUpdate(_quesResp, sql, fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("QuesRespDAO.update", e);
			throw e;
		}
	}

	public List<QuesResp> query(String sql, String fields) throws java.sql.SQLException {
		try {
			return quesRespHelper.getQueryList(query(sql),fields);
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("QuesRespDAO.query", e);
			throw e;
		}
	}

	public Object load(String sql, String fields) throws java.sql.SQLException {
		try {
			List<QuesResp> list = quesRespHelper.getQueryList(query(sql),fields);
			if(list.size() > 0)
				return (QuesResp)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("QuesRespDAO.load", e);
			throw e;
		}
	}

	public Object load(Object quesResp) throws java.sql.SQLException {
		QuesResp _quesResp = (QuesResp)quesResp;
		String sql = "select "+QuesResp.ALL_FIELDS+quesRespHelper.getSqlString()+" and QuesResp.QuesId = '"+_quesResp.getQuesId() + "'";
		try {
			List<QuesResp> list = quesRespHelper.getQueryList(query(sql),QuesResp.ALL_FIELDS);
			if(list.size() > 0)
				return (QuesResp)list.get(0);
			else
				return null;
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("QuesRespDAO.load", e);
			throw e;
		}
	}
}