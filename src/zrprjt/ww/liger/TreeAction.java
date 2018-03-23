package zrprjt.ww.liger;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

import zrsy.facade.NodeFacade;
import zrsy.vo.Node;
import zrsy.vo.liger.LiNodeData;
//import zrsy.vo.json.LigerTree;
import zrsy.ww.BasicAction;
import zrsy.ww.MSG;

public class TreeAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String parm;

	public String execute() throws Exception {
		try {
			List<LiNodeData> data = new ArrayList<LiNodeData>();

			if(id != null) {
				id = " and Node.MenuId = "+id;
			}
			
			List<Node> nodes = new NodeFacade().find("select distinct "+Node.SELF_FIELDS+" from Node " +
					" inner join GpNode on(GpNode.NodeId = Node.Id) " +
					" inner join GpUsr on(GpUsr.GpId = GpNode.GpId) " +
					" inner join GpSy on(GpUsr.GpId = GpSy.GpId and GpSy.SyId = Node.SyId) " +
					" where GpUsr.UsrId = "+ getUsrSession().getId() +
					" 	and GpSy.SyId = " + this.getUsrSession().getSyId() +
					"	and Node.Leve = 0 " + id +
					" order by Node.sort ",Node.SELF_FIELDS);
			
			List<Node> subs = new NodeFacade().find("select distinct "+Node.SELF_FIELDS+" from Node " +
					" inner join GpNode on(GpNode.NodeId = Node.Id) " +
					" inner join GpUsr on(GpUsr.GpId = GpNode.GpId) " +
					" inner join GpSy on(GpUsr.GpId = GpSy.GpId and GpSy.SyId = Node.SyId) " +
					" where GpUsr.UsrId = "+ getUsrSession().getId() +
					" 	and GpSy.SyId = " + this.getUsrSession().getSyId() +
					"	and Node.Leve = 1 " +
					" order by Node.sort ",Node.SELF_FIELDS);

			for(int i=0; i<nodes.size(); i++) {
				List<LiNodeData> children = new ArrayList<LiNodeData>();
				
				for(int j=0; j<subs.size(); j++) {
					if(nodes.get(i).getId().equals(subs.get(j).getParent())) {
						children.add(new LiNodeData(subs.get(j).getId().toString(),subs.get(j).getText(),subs.get(j).getUrl()+parm));
					}
				}
				if(children.size() > 0)
					data.add(new LiNodeData(nodes.get(i).getId().toString(),nodes.get(i).getText(),nodes.get(i).getUrl(),children));
				else
					data.add(new LiNodeData(nodes.get(i).getId().toString(),nodes.get(i).getText(),nodes.get(i).getUrl()));
			}
			
			setJson(JSON.toJSONString(data));
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("IndexAction Exception", e);
			return ERROR;
		}
		return SUCCESS;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParm() {
		return parm;
	}
	public void setParm(String parm) {
		this.parm = parm;
	}
}
