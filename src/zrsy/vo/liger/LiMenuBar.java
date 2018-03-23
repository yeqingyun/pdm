package zrsy.vo.liger;

import java.util.ArrayList;
import java.util.List;

public class LiMenuBar {
	private List<LiMenu> items;

	public List<LiMenu> getItems() {
		return items;
	}
	public void setItems(List<LiMenu> items) {
		this.items = items;
	}
	public void addtoItems(LiMenu item) {
		if(getItems() == null) setItems(new ArrayList<LiMenu>());
		getItems().add(item);
	}
}
