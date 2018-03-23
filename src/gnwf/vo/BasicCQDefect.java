package gnwf.vo;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class BasicCQDefect implements Serializable {
	
	private static final long serialVersionUID = 3790185795381369988L;
	
	public static final String SELF_FIELDS = "Defect.Id,Defect.Headline,Defect.State,Defect.Owner,"
			+ "Defect.Project,Defect.Moduleitem,Defect.Submit_date,Defect.Probability,Defect.Priority,"
			+ "Defect.Severity,Defect.Reason,Defect.Os,Defect.Isverified,Defect.Reviewresult,Defect.Baseline,"
			+ "Defect.Expectedbaseline,Defect.Fixedbaseline,Defect.Sw_leader,Defect.Description,"
			+ "Defect.Newnote,Defect.Submitter,Defect.Notes,Defect.Sqa,Defect.Bugfix,Defect.Phase,Defect.Rom_used_prj,"
			+ "Defect.Drvleader,Defect.Releatedcr,Defect.Relatedcase,Defect.Bugtype,Defect.Ziceneirong,"
			+ "Defect.Zicejieguo,Defect.Testnote,Defect.Phone,Defect.Accessgroup,Defect.Is_duplicate,"
			+ "Defect.Duplicate_original,Defect.External_id";
	
	
	private String id;
	private String headline;
	private Integer state;
	private Integer owner;
	private Integer project;
	private String moduleitem;
	@JSONField(format="yyyy-MM-dd")
	private Date submit_date;
	private String probability;
	private String priority;
	
	private String severity;
	private String reason;
	private String os;
	private String isverified;
	private String reviewresult;
	private String baseline;
	private String expectedbaseline;
	private String fixedbaseline;
	private Integer sw_leader;
	private String description;
	private String newnote;
	private Integer submitter;
	private String notes;
	
	private Integer sqa;
	private String bugfix;//address TVN
	private String phase;
	private Integer rom_used_prj;
	private Integer drvleader;
	private String  releatedcr;
	private String relatedcase;
	private String bugtype;
	private String ziceneirong;
	private String zicejieguo;
	private String testnote;
	private String phone;
	private Integer accessgroup;
	private Integer is_duplicate;
	private Integer duplicate_original;
	private String external_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getOwner() {
		return owner;
	}
	public void setOwner(Integer owner) {
		this.owner = owner;
	}
	public Integer getProject() {
		return project;
	}
	public void setProject(Integer project) {
		this.project = project;
	}
	public String getModuleitem() {
		return moduleitem;
	}
	public void setModuleitem(String moduleitem) {
		this.moduleitem = moduleitem;
	}
	public Date getSubmit_date() {
		return submit_date;
	}
	public void setSubmit_date(Date submit_date) {
		this.submit_date = submit_date;
	}
	public String getProbability() {
		return probability;
	}
	public void setProbability(String probability) {
		this.probability = probability;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getIsverified() {
		return isverified;
	}
	public void setIsverified(String isverified) {
		this.isverified = isverified;
	}
	public String getReviewresult() {
		return reviewresult;
	}
	public void setReviewresult(String reviewresult) {
		this.reviewresult = reviewresult;
	}
	public String getBaseline() {
		return baseline;
	}
	public void setBaseline(String baseline) {
		this.baseline = baseline;
	}
	public String getExpectedbaseline() {
		return expectedbaseline;
	}
	public void setExpectedbaseline(String expectedbaseline) {
		this.expectedbaseline = expectedbaseline;
	}
	public String getFixedbaseline() {
		return fixedbaseline;
	}
	public void setFixedbaseline(String fixedbaseline) {
		this.fixedbaseline = fixedbaseline;
	}
	public Integer getSw_leader() {
		return sw_leader;
	}
	public void setSw_leader(Integer sw_leader) {
		this.sw_leader = sw_leader;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNewnote() {
		return newnote;
	}
	public void setNewnote(String newnote) {
		this.newnote = newnote;
	}
	public Integer getSubmitter() {
		return submitter;
	}
	public void setSubmitter(Integer submitter) {
		this.submitter = submitter;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Integer getSqa() {
		return sqa;
	}
	public void setSqa(Integer sqa) {
		this.sqa = sqa;
	}
	public String getBugfix() {
		return bugfix;
	}
	public void setBugfix(String bugfix) {
		this.bugfix = bugfix;
	}
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	public Integer getRom_used_prj() {
		return rom_used_prj;
	}
	public void setRom_used_prj(Integer rom_used_prj) {
		this.rom_used_prj = rom_used_prj;
	}
	public Integer getDrvleader() {
		return drvleader;
	}
	public void setDrvleader(Integer drvleader) {
		this.drvleader = drvleader;
	}
	public String getReleatedcr() {
		return releatedcr;
	}
	public void setReleatedcr(String releatedcr) {
		this.releatedcr = releatedcr;
	}
	public String getRelatedcase() {
		return relatedcase;
	}
	public void setRelatedcase(String relatedcase) {
		this.relatedcase = relatedcase;
	}
	public String getBugtype() {
		return bugtype;
	}
	public void setBugtype(String bugtype) {
		this.bugtype = bugtype;
	}
	public String getZiceneirong() {
		return ziceneirong;
	}
	public void setZiceneirong(String ziceneirong) {
		this.ziceneirong = ziceneirong;
	}
	public String getZicejieguo() {
		return zicejieguo;
	}
	public void setZicejieguo(String zicejieguo) {
		this.zicejieguo = zicejieguo;
	}
	public String getTestnote() {
		return testnote;
	}
	public void setTestnote(String testnote) {
		this.testnote = testnote;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getAccessgroup() {
		return accessgroup;
	}
	public void setAccessgroup(Integer accessgroup) {
		this.accessgroup = accessgroup;
	}
	public Integer getIs_duplicate() {
		return is_duplicate;
	}
	public void setIs_duplicate(Integer is_duplicate) {
		this.is_duplicate = is_duplicate;
	}
	public Integer getDuplicate_original() {
		return duplicate_original;
	}
	public void setDuplicate_original(Integer duplicate_original) {
		this.duplicate_original = duplicate_original;
	}
	public String getExternal_id() {
		return external_id;
	}
	public void setExternal_id(String external_id) {
		this.external_id = external_id;
	}
	@Override
	public String toString() {
		return "BasicCQDefect [id=" + id + ", headline=" + headline
				+ ", state=" + state + ", owner=" + owner + ", project="
				+ project + ", moduleitem=" + moduleitem + ", submit_date="
				+ submit_date + ", probability=" + probability + ", priority="
				+ priority + ", severity=" + severity + ", reason=" + reason
				+ ", os=" + os + ", isverified=" + isverified
				+ ", reviewresult=" + reviewresult + ", baseline=" + baseline
				+ ", expectedbaseline=" + expectedbaseline + ", fixedbaseline="
				+ fixedbaseline + ", sw_leader=" + sw_leader + ", description="
				+ description + ", newnote=" + newnote + ", submitter="
				+ submitter + ", notes=" + notes + ", sqa=" + sqa + ", bugfix="
				+ bugfix + ", phase=" + phase + ", rom_used_prj="
				+ rom_used_prj + ", drvleader=" + drvleader + ", releatedcr="
				+ releatedcr + ", relatedcase=" + relatedcase + ", bugtype="
				+ bugtype + ", ziceneirong=" + ziceneirong + ", zicejieguo="
				+ zicejieguo + ", testnote=" + testnote + ", phone=" + phone
				+ ", accessgroup=" + accessgroup + ", is_duplicate="
				+ is_duplicate + ", duplicate_original=" + duplicate_original
				+ ", external_id=" + external_id + "]";
	}
}
