package zrsy.vo.json;

import java.util.ArrayList;
import java.util.List;

public class TreeJson {
	int id;
	String text;
	List<TreeJson> children;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<TreeJson> getChildren() {
		return children;
	}
	public void setChildren(List<TreeJson> children) {
		this.children = children;
	}
	public void addtoChildren(zrsy.vo.json.TreeJson treeJson) {
		if(getChildren() == null) setChildren(new ArrayList<zrsy.vo.json.TreeJson>());
		getChildren().add(treeJson);
	}

}
