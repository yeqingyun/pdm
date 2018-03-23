package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import gnwf.vo.CQDefect;

public class CQDefectHelper extends BasicCQDefectHelper {
	@Override
	public String getSqlString() {
		return " from Defect join Project on Project.Dbid = Defect.project join Users on Users.Dbid = Defect.Owner "
				+ "where 1=1 and Defect.Id is not null";
	}
	@Override
	public String getSqlCondition(CQDefect cqDefect) {
		String sql = super.getSqlCondition(cqDefect);
		if(null != cqDefect.getLogin_name() && !cqDefect.getLogin_name().isEmpty()) {
			sql += " and Users.login_name like '%" + cqDefect.getLogin_name() + "%'";
		}
		if(null != cqDefect.getPrjName() && !cqDefect.getPrjName().isEmpty()) {
			sql += " and Project.Prjname like '%" + cqDefect.getPrjName() + "%'";
		}
		return sql;
	}

	@Override
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
					else if(_fields[i].equals("Severity") || _fields[i].equals("Defect.Severity"))
						cqDefect.setSeverity(rs.getString("Severity"));
					else if(_fields[i].equals("Reason") || _fields[i].equals("Defect.Reason"))
						cqDefect.setReason(rs.getString("Reason"));
					else if(_fields[i].equals("Os") || _fields[i].equals("Defect.Os"))
						cqDefect.setOs(rs.getString("Os"));
					else if(_fields[i].equals("Isverified") || _fields[i].equals("Defect.Isverified"))
						cqDefect.setIsverified(rs.getString("Isverified"));
					else if(_fields[i].equals("Reviewresult") || _fields[i].equals("Defect.Reviewresult"))
						cqDefect.setReviewresult(rs.getString("Reviewresult"));
					else if(_fields[i].equals("Baseline") || _fields[i].equals("Defect.Baseline"))
						cqDefect.setBaseline(rs.getString("Baseline"));
					else if(_fields[i].equals("Expectedbaseline") || _fields[i].equals("Defect.Expectedbaseline"))
						cqDefect.setExpectedbaseline(rs.getString("Expectedbaseline"));
					else if(_fields[i].equals("Fixedbaseline") || _fields[i].equals("Defect.Fixedbaseline"))
						cqDefect.setFixedbaseline(rs.getString("Fixedbaseline"));
					else if(_fields[i].equals("Sw_leader") || _fields[i].equals("Defect.Sw_leader"))
						cqDefect.setSw_leader(rs.getInt("Sw_leader"));
					else if(_fields[i].equals("Description") || _fields[i].equals("Defect.Description"))
						cqDefect.setDescription(rs.getString("Description"));
					else if(_fields[i].equals("Newnote") || _fields[i].equals("Defect.Newnote"))
						cqDefect.setNewnote(rs.getString("Newnote"));
					else if(_fields[i].equals("Submitter") || _fields[i].equals("Defect.Submitter"))
						cqDefect.setSubmitter(rs.getInt("Submitter"));
					else if(_fields[i].equals("Notes") || _fields[i].equals("Defect.Notes"))
						cqDefect.setNotes(rs.getString("Notes"));
					
					else if(_fields[i].equals("Sqa") || _fields[i].equals("Defect.Sqa"))
						cqDefect.setSqa(rs.getInt("Sqa"));
					else if(_fields[i].equals("Bugfix") || _fields[i].equals("Defect.Bugfix"))
						cqDefect.setBugfix(rs.getString("Bugfix"));
					else if(_fields[i].equals("Phase") || _fields[i].equals("Defect.Phase"))
						cqDefect.setPhase(rs.getString("Phase"));
					else if(_fields[i].equals("Rom_used_prj") || _fields[i].equals("Defect.Rom_used_prj"))
						cqDefect.setRom_used_prj(rs.getInt("Rom_used_prj"));
					else if(_fields[i].equals("Drvleader") || _fields[i].equals("Defect.Drvleader"))
						cqDefect.setDrvleader(rs.getInt("Drvleader"));
					else if(_fields[i].equals("Releatedcr") || _fields[i].equals("Defect.Releatedcr"))
						cqDefect.setReleatedcr(rs.getString("Releatedcr"));
					else if(_fields[i].equals("Relatedcase") || _fields[i].equals("Defect.Relatedcase"))
						cqDefect.setRelatedcase(rs.getString("Relatedcase"));
					else if(_fields[i].equals("Bugtype") || _fields[i].equals("Defect.Bugtype"))
						cqDefect.setBugtype(rs.getString("Bugtype"));
					else if(_fields[i].equals("Ziceneirong") || _fields[i].equals("Defect.Ziceneirong"))
						cqDefect.setZiceneirong(rs.getString("Ziceneirong"));
					else if(_fields[i].equals("Zicejieguo") || _fields[i].equals("Defect.Zicejieguo"))
						cqDefect.setZicejieguo(rs.getString("Zicejieguo"));
					else if(_fields[i].equals("Testnote") || _fields[i].equals("Defect.Testnote"))
						cqDefect.setTestnote(rs.getString("Testnote"));
					else if(_fields[i].equals("Phone") || _fields[i].equals("Defect.Phone"))
						cqDefect.setPhone(rs.getString("Phone"));
					else if(_fields[i].equals("Accessgroup") || _fields[i].equals("Defect.Accessgroup"))
						cqDefect.setAccessgroup(rs.getInt("Accessgroup"));
					else if(_fields[i].equals("Is_duplicate") || _fields[i].equals("Defect.Is_duplicate"))
						cqDefect.setIs_duplicate(rs.getInt("Is_duplicate"));
					else if(_fields[i].equals("Duplicate_original") || _fields[i].equals("Defect.Duplicate_original"))
						cqDefect.setDuplicate_original(rs.getInt("Duplicate_original"));
					else if(_fields[i].equals("External_id") || _fields[i].equals("Defect.External_id"))
						cqDefect.setExternal_id(rs.getString("External_id"));
					
					else if(_fields[i].indexOf("AS Statename") > -1)
						cqDefect.setStatename(rs.getString("Statename"));
					else if(_fields[i].indexOf("AS PrjName") > -1)
						cqDefect.setPrjName(rs.getString("PrjName"));
					else if(_fields[i].indexOf("AS Login_name") > -1)
						cqDefect.setLogin_name(rs.getString("Login_name"));
					else if(_fields[i].indexOf("AS Platform") > -1)
						cqDefect.setPlatform(rs.getString("Platform"));
					else if(_fields[i].indexOf("AS Sw_leader_name") > -1)
						cqDefect.setSw_leader_name(rs.getString("Sw_leader_name"));
					else if(_fields[i].indexOf("AS Submitter_name") > -1)
						cqDefect.setSubmitter_name(rs.getString("Submitter_name"));
					
					else if(_fields[i].indexOf("AS Sqa_name") > -1)
						cqDefect.setSqa_name(rs.getString("Sqa_name"));
					else if(_fields[i].indexOf("AS  Drvleader_name") > -1)
						cqDefect.setDrvleader_name(rs.getString(" Drvleader_name"));
					else if(_fields[i].indexOf("AS Owner_phone") > -1)
						cqDefect.setOwner_phone(rs.getString("Owner_phone"));
				}
				list.add(cqDefect);
			}
		}catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("CQDefectHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}