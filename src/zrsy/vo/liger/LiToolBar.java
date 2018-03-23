package zrsy.vo.liger;

import java.util.ArrayList;
import java.util.List;

public class LiToolBar {
	
	private List<LiToolBarData> items;

	public List<LiToolBarData> getItems() {
		return items;
	}
	public void setItems(List<LiToolBarData> items) {
		this.items = items;
	}
	public void addtoItems(LiToolBarData item) {
		if(getItems() == null) setItems(new ArrayList<LiToolBarData>());
		getItems().add(item);
	}
}
