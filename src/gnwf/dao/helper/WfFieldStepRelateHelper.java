package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WfFieldStepRelate;

public class WfFieldStepRelateHelper extends BasicWfFieldStepRelateHelper {
	public String getSqlString() {
		return " from WfFieldStepRelate " +

               " where 1=1 ";
	}

	public List<WfFieldStepRelate> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfFieldStepRelate> list = new ArrayList<WfFieldStepRelate>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfFieldStepRelate wfFieldStepRelate = new WfFieldStepRelate();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("FieldId") || _fields[i].equals("WfFieldStepRelate.FieldId"))
						wfFieldStepRelate.setFieldId(rs.getInt("FieldId"));
					else if(_fields[i].equals("StepId") || _fields[i].equals("WfFieldStepRelate.StepId"))
						wfFieldStepRelate.setStepId(rs.getInt("StepId"));


				}
				list.add(wfFieldStepRelate);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfFieldStepRelateHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}