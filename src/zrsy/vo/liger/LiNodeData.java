package zrsy.vo.liger;

import java.util.ArrayList;
import java.util.List;

public class LiNodeData {
	
	public LiNodeData() {
		
	}
	
	public LiNodeData(String id,String text,String url) {
		this.id = id;
		this.text = text;
		this.url = url;
	}

	public LiNodeData(String id,String text,String url,List<LiNodeData> children) {
		this.id = id;
		this.text = text;
		this.url = url;
		this.children = children;
	}
	
	public String id;
	public String text;
	public String url;
	
	public List<LiNodeData> children;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<LiNodeData> getChildren() {
		return children;
	}
	public void setChildren(List<LiNodeData> children) {
		this.children = children;
	}
	public void addtoChildren(LiNodeData children) {
		if(getChildren() == null) setChildren(new ArrayList<LiNodeData>());
		getChildren().add(children);
	}
}
