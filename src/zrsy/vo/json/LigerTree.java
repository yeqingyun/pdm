package zrsy.vo.json;

import java.util.HashMap;
import java.util.List;

import zrsy.vo.liger.LiFunction;
import zrsy.vo.liger.LiNodeData;


@SuppressWarnings("serial")
public class LigerTree extends HashMap<String, Object> {
	
	public LigerTree() {
		super();
	}
	
	public LigerTree(Boolean checkbox,List<LiNodeData> data,String onSelect) {
		super();
		this.put("nodeWidth", 140);
		this.put("checkbox", checkbox);
		this.put("data", data);
		this.put("onSelect",  new LiFunction(onSelect));
	}
}
