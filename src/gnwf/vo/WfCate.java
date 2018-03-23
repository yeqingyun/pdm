package gnwf.vo;

import java.util.ArrayList;
import java.util.List;

import zrsy.vo.liger.LiNodeData;

public class WfCate extends BasicWfCate {
	public static final String ALL_FIELDS = "WfCate.Sort,WfCate.CateId,WfCate.CateParent,WfCate.CateLevel,WfCate.Status,WfCate.CreateBy,WfCate.LastUpd,WfCate.CreateDate,WfCate.LastUpdDate,WfCate.CateName";
	public static final String LIST_FIELDS = "WfCate.Sort,WfCate.CateParent,WfCate.CateLevel,WfCate.Status,WfCate.CreateBy,WfCate.LastUpd,WfCate.CreateDate,WfCate.LastUpdDate,WfCate.CateName";

	//private java.util.List<gnwf.vo.WfCfg> wfCfgs;
	public List<LiNodeData> children;

//	public java.util.List<gnwf.vo.WfCfg> getWfCfgs() {
//		return wfCfgs;
//	}
//	public void setWfCfgs(java.util.List<gnwf.vo.WfCfg> wfCfgs){
//		this.wfCfgs = wfCfgs;
//	}
//	public void addtoWfCfgs(gnwf.vo.WfCfg wfCfg){
//		if(getWfCfgs() == null) setWfCfgs(new java.util.ArrayList<gnwf.vo.WfCfg>());
//			getWfCfgs().add(wfCfg);
//	}
	
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