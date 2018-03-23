package gnwf.vo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public class WfPpReport {
	public static final String SELF_FIELDS = "PrjtDef.devDeptNameID";

	private String devDeptNameID;
	//private String wfPpReports;

	

	public String getDevDeptNameID() {
		return devDeptNameID;
	}


	public void setDevDeptNameID(String devDeptNameID) {
		this.devDeptNameID = devDeptNameID;
	}


	public List<?> getQueryList(ResultSet query, String fields) {
		List<WfPpReport> s=new ArrayList<WfPpReport>();
		
		
		return s;
	
	}
}
