package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;
import Utils.DateUtil;
import gnwf.vo.CQDefect;

public class BasicCQDefectHelper implements SqlHelper {
	public String getSqlString() {
		return " from Defect where 1=1 and Defect.Id is not null";
	}

	public String getOrderBy() {
		return " order by Defect.Id";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((CQDefect)object);
	}

	public String getSql4Amount(CQDefect cQDefect) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(cQDefect);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((CQDefect)object);
	}

	public String getSql4Delete(CQDefect cQDefect) {
		return "delete from Defect where 1=1"+getSqlCondition(cQDefect);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((CQDefect)object,fields);
	}

	public String getSql4All(CQDefect cQDefect, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(cQDefect)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((CQDefect)object,pageVO,fields);
	}

	public String getSql4Pages(CQDefect cQDefect,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" Defect.Id "+ getSqlString()+getSqlCondition(cQDefect)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(cQDefect)+" and Defect.Id not in("+mstr+") "+getOrderBy();
		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((CQDefect)object);
	}

	public String getSqlCondition(CQDefect cQDefect) {
		String sql = "";
		if(null != cQDefect.getId() && !cQDefect.getId().isEmpty())
			sql += " and Defect.Id = '" + cQDefect.getId() + "'";
		if(null != cQDefect.getHeadline() && !cQDefect.getHeadline().isEmpty())
			sql += " and Defect.Headline LIKE '%" + cQDefect.getHeadline() + "%'";
		if(null != cQDefect.getState())
			sql += " and Defect.State = '" + cQDefect.getState()+"'";
		if(null != cQDefect.getOwner())
			sql += " and Defect.Owner = '" + cQDefect.getOwner() + "'";
		if(null != cQDefect.getProject()) 
			sql += " and Defect.Project = '" + cQDefect.getProject() + "'";
		if(null != cQDefect.getModuleitem() && !cQDefect.getModuleitem().isEmpty())
			sql += " and Defect.Moduleitem LIKE '%" + cQDefect.getModuleitem() + "%'";
		if(null != cQDefect.getSubmit_date())
			sql += " and CONVERT(varchar(100),Defect.Submit_date,23) = '" + DateUtil.format(cQDefect.getSubmit_date(), "yyyy-MM-dd") + "'";
		if(null != cQDefect.getProbability() && !cQDefect.getProbability().isEmpty())
			sql += " and Defect.Probability LIKE '%" + cQDefect.getProbability() + "%'";
		if(null != cQDefect.getPriority() && !cQDefect.getPriority().isEmpty())
			sql += " and Defect.Priority LIKE '%" + cQDefect.getPriority() + "%'";
		return sql;
	}

	public List<CQDefect> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<CQDefect> list = new ArrayList<CQDefect>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				CQDefect cqDefect = new CQDefect();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Id") || _fields[i].equals("Defect.Id"))
						cqDefect.setId(rs.getString("Id"));
					else if(_fields[i].equals("Headline") || _fields[i].equals("Defect.Headline"))
						cqDefect.setHeadline(rs.getString("Headline"));
					else if(_fields[i].equals("State") || _fields[i].equals("Defect.State"))
						cqDefect.setState(rs.getInt("State"));
					else if(_fields[i].equals("Owner") || _fields[i].equals("Defect.Owner"))
						cqDefect.setOwner(rs.getInt("Owner"));
					else if(_fields[i].equals("Project") || _fields[i].equals("Defect.Project"))
						cqDefect.setProject(rs.getInt("Project"));
					else if(_fields[i].equals("Moduleitem") || _fields[i].equals("Defect.Moduleitem"))
						cqDefect.setModuleitem(rs.getString("Moduleitem"));
					else if(_fields[i].equals("Submit_date") || _fields[i].equals("Defect.Submit_date"))
						cqDefect.setSubmit_date(rs.getTimestamp("Submit_date"));
					else if(_fields[i].equals("Probability") || _fields[i].equals("Defect.Probability"))
						cqDefect.setProbability(rs.getString("Probability"));
					else if(_fields[i].equals("Priority") || _fields[i].equals("Defect.Priority"))
						cqDefect.setPriority(rs.getString("Priority"));
				}
				list.add(cqDefect);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("BasicCQDefectHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}


	public String getFields(CQDefect cqDefect) {
		String _fields = "";
		if(null != cqDefect.getId())
			_fields += "Defect.Id,";
		if(null != cqDefect.getHeadline())
			_fields += "Defect.Headline,";
		if(null != cqDefect.getState())
			_fields += "Defect.State,";
		if(null != cqDefect.getOwner())
			_fields += "Defect.Owner,";
		if(null != cqDefect.getProject())
			_fields += "Defect.Project,";
		if(null != cqDefect.getModuleitem())
			_fields += "Defect.Moduleitem,";
		if(null != cqDefect.getProbability())
			_fields += "Defect.Probability,";
		if(null != cqDefect.getPriority())
			_fields += "Defect.Priority,";
		if(null != cqDefect.getSubmit_date())
			_fields += "Defect.Submit_date,";
		if(null != cqDefect.getSeverity())
			_fields += "Defect.Severity,";
		if(null != cqDefect.getReason())
			_fields += "Defect.Reason,";
		if(null != cqDefect.getOs())
			_fields += "Defect.Os,";
		if(null != cqDefect.getIsverified())
			_fields += "Defect.Isverified,";
		if(null != cqDefect.getReviewresult())
			_fields += "Defect.Reviewresult,";
		if(null != cqDefect.getBaseline())
			_fields += "Defect.Baseline,";
		if(null != cqDefect.getExpectedbaseline())
			_fields += "Defect.Expectedbaseline,";
		if(null != cqDefect.getFixedbaseline())
			_fields += "Defect.Fixedbaseline,";
		if(null != cqDefect.getSw_leader())
			_fields += "Defect.Sw_leader,";
		if(null != cqDefect.getDescription())
			_fields += "Defect.Description,";
		if(null != cqDefect.getSubmit_date())
			_fields += "Defect.Submit_date,";
		if(null != cqDefect.getNewnote())
			_fields += "Defect.Newnote,";
		if(null != cqDefect.getSubmitter())
			_fields += "Defect.Submitter,";
		if(null != cqDefect.getNotes())
			_fields += "Defect.Notes,";
		
		if(null != cqDefect.getSqa())
			_fields += "Defect.Sqa,";
		if(null != cqDefect.getBugfix())
			_fields += "Defect.Bugfix,";
		if(null != cqDefect.getPhase())
			_fields += "Defect.Phase,";
		if(null != cqDefect.getRom_used_prj())
			_fields += "Defect.Rom_used_prj,";
		if(null != cqDefect.getDrvleader())
			_fields += "Defect.Drvleader,";
		if(null != cqDefect.getReleatedcr())
			_fields += "Defect.Releatedcr,";
		if(null != cqDefect.getRelatedcase())
			_fields += "Defect.Relatedcase,";
		if(null != cqDefect.getBugtype())
			_fields += "Defect.Bugtype,";
		if(null != cqDefect.getZiceneirong())
			_fields += "Defect.Ziceneirong,";
		if(null != cqDefect.getZicejieguo())
			_fields += "Defect.Zicejieguo,";
		if(null != cqDefect.getTestnote())
			_fields += "Defect.Testnote,";
		if(null != cqDefect.getPhone())
			_fields += "Defect.Phone,";
		if(null != cqDefect.getAccessgroup())
			_fields += "Defect.Accessgroup,";
		if(null != cqDefect.getIs_duplicate())
			_fields += "Defect.Is_duplicate,";
		if(null != cqDefect.getDuplicate_original())
			_fields += "Defect.Duplicate_original,";
		if(null != cqDefect.getExternal_id())
			_fields += "Defect.External_id,";
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
}