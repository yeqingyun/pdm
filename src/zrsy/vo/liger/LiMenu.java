package zrsy.vo.liger;

import java.util.HashMap;
import java.util.List;

public class LiMenu {
	
	public LiMenu() {}
	
	public LiMenu(String text,Integer width,List<LiMenuItem> items) {
		this.text = text;
		menu = new HashMap<String, Object>();
		menu.put("width", width);
		menu.put("items", items);
	}

	public String text;
	public  HashMap<String, Object> menu;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
