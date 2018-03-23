package zrsy.ww.liger;

import gnwf.facade.WfCateFacade;
import gnwf.facade.WfCfgFacade;
import gnwf.vo.WfCate;
import gnwf.vo.WfCfg;
import gnwf.ww.workflow.WFUtil;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

import zrsy.facade.NodeFacade;
import zrsy.vo.Gp;
import zrsy.vo.Node;
import zrsy.vo.liger.LiNodeData;
//import zrsy.vo.json.LigerTree;
import zrsy.ww.BasicAction;
import zrsy.ww.MSG;

public class TreeAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private List<WfCate> wfCates;
	private List<WfCfg> wfCfgs;

	public String execute() throws Exception {
		try {
			List<LiNodeData> data = new ArrayList<LiNodeData>();
			
			String menuId = id;
			
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
						children.add(new LiNodeData(subs.get(j).getId().toString(),subs.get(j).getText(),subs.get(j).getUrl()));
					}
				}
				if(children.size() > 0)
					data.add(new LiNodeData(nodes.get(i).getId().toString(),nodes.get(i).getText(),nodes.get(i).getUrl(),children));
				else
					data.add(new LiNodeData(nodes.get(i).getId().toString(),nodes.get(i).getText(),nodes.get(i).getUrl()));
			}
			
//			if(id!=null && ("164".equals(menuId) || "18".equals(menuId))){
//				wfCates = new WfCateFacade().find("select " +WfCate.SELF_FIELDS + " from WfCate ",WfCate.SELF_FIELDS);
//	
//				String sql = "select " +WfCfg.SELF_FIELDS + 
//								" from WfCfg where flowId in(select WfJob.flowId from WfJob " +
//								" inner join WfJobUserRelte on(WfJobUserRelte.jobId = WfJob.jobId) " +
//								" where WfJobUserRelte.userId = "+getUsrSession().getId()+") or WfCfg.limit!=0 ";
//				wfCfgs = new WfCfgFacade().find(sql,WfCfg.SELF_FIELDS);
//				
//				for(int i=0; i<wfCates.size(); i++) {
//					LiNodeData d = new LiNodeData(wfCates.get(i).getCateId()+"",wfCates.get(i).getCateName(),"#");
//					for(int j=0; j<wfCfgs.size(); j++) {
//						if(wfCates.get(i).getCateId().equals(wfCfgs.get(j).getCateId())) {
//							LiNodeData subd = new LiNodeData(wfCfgs.get(j).getFlowId()+"",wfCfgs.get(j).getFlowName(),"WfRd.shtml?wfRd.flowId="+wfCfgs.get(j).getFlowId());
//							d.addtoChildren(subd);
//						}
//					}
//					if(WFUtil.isNotNull(d.getChildren())){
//						data.add(d);
//					}
//				}
//			}
			
			setJson(JSON.toJSONString(data));
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("IndexAction Exception", e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String loadWorkFlowTree()throws Exception{
		try{
			List<LiNodeData> data = new ArrayList<LiNodeData>();
			
			//判断是否为海外研发人员权限，
			/*boolean isNoChangeRoleByProject = false;
			boolean isOverSeaPerson = false;
			for(Gp gp:getUsrSession().getGps()){
				if("不随项目变更角色".equals(gp.getGpName())){
					isNoChangeRoleByProject = true;
				} else if ("海外项目人员".equals(gp.getGpName())) {
					isOverSeaPerson = true;				
				}
			}
			
			if(isNoChangeRoleByProject) {
				wfCates = new WfCateFacade().find("select " +WfCate.SELF_FIELDS + " from WfCate  order by Sort",WfCate.SELF_FIELDS);
			} else if(isOverSeaPerson){
				wfCates = new WfCateFacade().find("select " +WfCate.SELF_FIELDS + " from WfCate where cateid >= 8 order by Sort",WfCate.SELF_FIELDS);
			} else {
				wfCates = new WfCateFacade().find("select " +WfCate.SELF_FIELDS + " from WfCate where cateid < 8 order by Sort",WfCate.SELF_FIELDS);
			}*/
			
			for(Gp gp:getUsrSession().getGps()){
				if(gp.getSyId().equals(6)) {
					if("不随项目变更角色".equals(gp.getGpName())){
						wfCates = new WfCateFacade().find("select " +WfCate.SELF_FIELDS + " from WfCate  order by Sort",WfCate.SELF_FIELDS);
						System.out.println("11111111333"+"select " +WfCate.SELF_FIELDS + " from WfCate  order by Sort");
					}else if ("海外项目人员".equals(gp.getGpName())) 
					{
						wfCates = new WfCateFacade().find("select " +WfCate.SELF_FIELDS + " from WfCate where cateid >= 8 order by Sort",WfCate.SELF_FIELDS);
						
					}else if ("供应链角色".equals(gp.getGpName())) 
					{
						wfCates = new WfCateFacade().find("select " +WfCate.SELF_FIELDS + " from WfCate where cateid >= 8 order by Sort",WfCate.SELF_FIELDS);
						
					}else {
						wfCates = new WfCateFacade().find("select " +WfCate.SELF_FIELDS + " from WfCate where cateid < 8 order by Sort",WfCate.SELF_FIELDS);
						
					}
				}
			}
			
			WfCate remove =  null;
			for(WfCate wfCate : wfCates) {
				if("概念".equals(wfCate.getCateName())) {
					remove = wfCate;
					break;
				}
			}
			wfCates.remove(remove);
			String sql = "select " +WfCfg.SELF_FIELDS + 
							" from WfCfg where flowId in(select WfJob.flowId from WfJob " +
							" inner join WfJobUserRelte on(WfJobUserRelte.jobId = WfJob.jobId) " +
							" where WfJobUserRelte.userId = "+getUsrSession().getId()+") or WfCfg.limit!=0 ";
			wfCfgs = new WfCfgFacade().find(sql,WfCfg.SELF_FIELDS);
			
			for(int i=0; i<wfCates.size(); i++) {
				LiNodeData d = new LiNodeData(wfCates.get(i).getCateId()+"",wfCates.get(i).getCateName(),"#");
				for(int j=0; j<wfCfgs.size(); j++) {
					if(wfCates.get(i).getCateId().equals(wfCfgs.get(j).getCateId())) {
						LiNodeData subd = new LiNodeData(wfCfgs.get(j).getFlowId()+"",wfCfgs.get(j).getFlowName(),"WfRd.shtml?wfRd.flowId="+wfCfgs.get(j).getFlowId());
						d.addtoChildren(subd);
					}
				}
				if(WFUtil.isNotNull(d.getChildren())){
					data.add(d);
				}
			}
			
			setJson(JSON.toJSONString(data));
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("loadWorkFlowTree Exception", e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	public String loadWorkFlowTree1()throws Exception{
		try{
			List<LiNodeData> data = new ArrayList<LiNodeData>();
			
			
			wfCates = new WfCateFacade().find("select " +WfCate.SELF_FIELDS + " from WfCate  order by Sort",WfCate.SELF_FIELDS);
			
			WfCate remove =  null;
			for(WfCate wfCate : wfCates) {
				if("概念".equals(wfCate.getCateName())) {
					remove = wfCate;
					break;
				}
			}
			wfCates.remove(remove);
			String sql = "select " +WfCfg.SELF_FIELDS + 
							" from WfCfg where flowId in(select WfJob.flowId from WfJob " +
							" inner join WfJobUserRelte on(WfJobUserRelte.jobId = WfJob.jobId) " +
							" where WfJobUserRelte.userId = "+getUsrSession().getId()+") or WfCfg.limit!=0 ";
			wfCfgs = new WfCfgFacade().find(sql,WfCfg.SELF_FIELDS);
			
			for(int i=0; i<wfCates.size(); i++) {
				LiNodeData d = new LiNodeData(wfCates.get(i).getCateId()+"",wfCates.get(i).getCateName(),"#");
				for(int j=0; j<wfCfgs.size(); j++) {
					if(wfCates.get(i).getCateId().equals(wfCfgs.get(j).getCateId())) {
						LiNodeData subd = new LiNodeData(wfCfgs.get(j).getFlowId()+"",wfCfgs.get(j).getFlowName(),"WfRd.shtml?wfRd.flowId="+wfCfgs.get(j).getFlowId());
						d.addtoChildren(subd);
					}
				}
				if(WFUtil.isNotNull(d.getChildren())){
					data.add(d);
				}
			}
			
			setJson(JSON.toJSONString(data));
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("loadWorkFlowTree Exception", e);
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
	public List<WfCate> getWfCates() {
		return wfCates;
	}
	public void setWfCates(List<WfCate> wfCates) {
		this.wfCates = wfCates;
	}
	public List<WfCfg> getWfCfgs() {
		return wfCfgs;
	}
	public void setWfCfgs(List<WfCfg> wfCfgs) {
		this.wfCfgs = wfCfgs;
	}
	
}
