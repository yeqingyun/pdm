package zrsy.vo.liger;

import java.util.HashMap;

public class LiToolBarData  extends HashMap<String, Object> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LiToolBarData() {
		
	}
	
	public LiToolBarData(String id,String text,String click,String icon,Boolean line,Boolean disable) {
		super();
		if(id != null) 
			this.put("id", id);
		if(text != null) 
			this.put("text", text);
		if(click != null) 
			this.put("click",new LiFunction(click));
		if(icon != null) 
			this.put("icon", icon);
		if(line != null) 
			this.put("line", line);
		if(disable != null) 
			this.put("disable", disable);
	}

	public LiToolBarData(String id,String text,String click,String icon,String img,Boolean line,Boolean disable) {
		super();
		if(id != null) 
			this.put("id", id);
		if(text != null) 
			this.put("text", text);
		if(click != null) 
			this.put("click",new LiFunction(click));
		if(icon != null) 
			this.put("icon", icon);
		if(img != null) 
				this.put("img", img);
		if(line != null) 
			this.put("line", line);
		if(disable != null) 
			this.put("disable", disable);
	}
}
