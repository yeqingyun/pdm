package zrsy.vo.liger;

import java.util.HashMap;
import java.util.List;


@SuppressWarnings("serial")
public class LiMenuItem extends HashMap<String, Object> {
	
	public LiMenuItem() {
		super();
	}

	public LiMenuItem(String id,String text,Integer width,String click,String icon) {
		super();
		if(id != null) this.put("id", id);
		if(text != null) this.put("text", text);
		if(width != null) this.put("width", width);
		if(click != null) this.put("click", new LiFunction(click));
		if(icon != null) this.put("icon", icon);
	}

	public LiMenuItem(String id,String text,Integer width,String click,String icon,String parm) {
		super();
		if(id != null) this.put("id", id);
		if(text != null) this.put("text", text);
		if(width != null) this.put("width", width);
		if(click != null) this.put("click", new LiFunction(click));
		if(icon != null) this.put("icon", icon);
		if(parm != null) this.put("parm", parm);
	}
	public LiMenuItem(String id,String text,Integer width,String click,String icon,List<LiMenuItem> children) {
		super();
		if(id != null) this.put("id", id);
		if(text != null) this.put("text", text);
		if(width != null) this.put("width", width);
		if(click != null) this.put("click", new LiFunction(click));
		if(icon != null) this.put("icon", icon);
		if(children != null) this.put("children", children);
	}
	
}
