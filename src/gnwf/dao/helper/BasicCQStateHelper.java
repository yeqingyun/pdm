package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

import org.apache.log4j.Logger;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;
import gnwf.vo.CQState;

public class BasicCQStateHelper implements SqlHelper {
	public String getSqlString() {
		return " from Statedef where 1=1";
	}

	public String getOrderBy() {
		return " order by Statedef.Id";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((CQState)object);
	}

	public String getSql4Amount(CQState state) {
		return "select count(*) as amount " + getSqlString() + getSqlCondition(state);
	}


	public String getSql4All(Object object, String fields) {
		return getSql4All((CQState)object,fields);
	}

	public String getSql4All(CQState state, String fields) {
		return "select " + fields + getSqlString() + getSqlCondition(state)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((CQState)object,pageVO,fields);
	}

	public String getSql4Pages(CQState state,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" Statedef.Id "+ getSqlString()+getSqlCondition(state)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(state)+" and Statedef.Id not in("+mstr+") "+getOrderBy();
		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((CQState)object);
	}

	public String getSqlCondition(CQState state) {
		String sql = "";
		if(null != state.getId())
			sql += " and Statedef.Id = '" + state.getId() + "'";
		if(null != state.getName() && !state.getName().isEmpty())
			sql += " and Statedef.Name LIKE '%" + state.getName() + "%'";
		return sql;
	}

	public List<CQState> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<CQState> list = new ArrayList<CQState>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				CQState state = new CQState();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Id") || _fields[i].equals("Statedef.Id"))
						state.setId(rs.getInt("Id"));
					else if(_fields[i].equals("Name") || _fields[i].equals("Statedef.Name"))
						state.setName(rs.getString("Name"));
				}
				list.add(state);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("BasicCQStateHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}


	public String getFields(CQState state) {
		String _fields = "";
		if(null != state.getId())
			_fields += "Statedef.Id,";
		if(null != state.getName())
			_fields += "Statedef.Name,";
		return _fields.substring(0, _fields.length()-1);
	}

	@Override
	public String getInsertSql(String arg0) {
		return null;
	}
	@Override
	public String getUpdateSql(String arg0, String arg1, String arg2) {
		return null;
	}

	@Override
	public String getSql4Delete(Object arg0) {
		return null;
	}
}