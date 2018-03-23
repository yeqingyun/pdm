package gnwf.ww.workflow;

import gnwf.vo.WfRdField;

import java.util.ArrayList;
import java.util.List;


public class Row {
	
	private int rowId;
	private List<WfRdField> fileds;

	public Row(int rowId){
		this.rowId = rowId;
	}
	
	public boolean addField(WfRdField f){
		if(f.getRowId() == rowId){
			if(getFileds()==null){
				fileds = new ArrayList<WfRdField>();
			}
			fileds.add(f);
			return true;
		}
		return false;
	}
	
	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	public List<WfRdField> getFileds() {
		return fileds;
	}

	public void setFileds(List<WfRdField> fileds) {
		this.fileds = fileds;
	}
	
}
