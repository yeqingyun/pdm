package zrsy.ww.liger;

import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

import zrsy.facade.BtnFacade;
import zrsy.vo.Btn;
import zrsy.vo.liger.LiToolBar;
import zrsy.vo.liger.LiToolBarData;
import zrsy.ww.BasicAction;
import zrsy.ww.MSG;

public class ToolBar2Action extends BasicAction {
	private static final long serialVersionUID = 1L;
	
	private String parm;

	public String execute() throws Exception {
		try {
			LiToolBar ligerToolBar = new LiToolBar();
			
			List<Btn> btns = new BtnFacade().find("select distinct "+Btn.SELF_FIELDS+" from Btn " +
					" inner join GpBtn on(Btn.Id = GpBtn.BtnId) " +
					" inner join GpUsr on(GpUsr.GpId = GpBtn.GpId) " +
					" inner join Node on(GpBtn.NodeId = Node.Id) " +
					" inner join PgBtn on(PgBtn.NodeId = Node.Id and PgBtn.BtnId = Btn.Id and PgBtn.PgTyp = 3) " +
					" where Node.Url like '"+parm+".shtml%'" +
					"	and Btn.SyId = "+this.getUsrSession().getSyId() +
					" 	and GpUsr.UsrId = "+ getUsrSession().getId() +
					" order by Btn.sort ",Btn.SELF_FIELDS);
			
			for(int i=0; i<btns.size(); i++) {
				ligerToolBar.addtoItems(new LiToolBarData(btns.get(i).getId().toString(),btns.get(i).getText(),btns.get(i).getClick(),null,btns.get(i).getImg(),null, null));
			}
			
			setJson(JSON.toJSONString(ligerToolBar));
		}
		catch(Exception e) { 
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("IndexAction Exception", e);
			return ERROR;
		}
		return SUCCESS;
	}
	public String getParm() {
		return parm;
	}
	public void setParm(String parm) {
		this.parm = parm;
	}
}
