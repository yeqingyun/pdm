package zrsy.ww.json;

import gnwf.ww.workflow.WFUtil;

import java.io.File;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableWorkbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

import zrprjt.vo.Dept;
import zrsy.ww.MSG;
import zrsy.ww.BasicAction;
import zrsy.dao.helper.UsrHelper;
import zrsy.facade.DeptFacade;
import zrsy.facade.GpFacade;
import zrsy.facade.GpUsrFacade;
import zrsy.facade.ScoFacade;
import zrsy.facade.UsrFacade;
import zrsy.facade.UsrScoFacade;
import zrsy.vo.GpUsr;
import zrsy.vo.Usr;
import zrsy.vo.UsrSco;
import zrsy.vo.json.UsrJson;

public class UsrAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<Usr> usrs;
	private Usr usr = new Usr();
	private java.util.List<zrsy.vo.GpUsr> gpUsrs;
	private java.util.List<zrsy.vo.Com> coms;
	private java.util.List<zrsy.vo.Dept> depts;
	private java.util.List<zrsy.vo.Gp> gps;
	private java.util.List<zrsy.vo.Sco> scos;
	private java.util.List<zrsy.vo.UsrSco> usrScos;
	public String execute() throws Exception {
		try {
			if(usr != null && usr.getId() != null) {
				usr = new UsrFacade().findById(usr);
				setJson(JSON.toJSONString(usr)); 
			}
			coms = new zrsy.facade.ComFacade().find("select "+zrsy.vo.Com.SELF_FIELDS+" from Com where status=1 ",zrsy.vo.Com.SELF_FIELDS);
			depts = new zrsy.facade.DeptFacade().find("select "+zrsy.vo.Dept.SELF_FIELDS+" from Dept where status=1",zrsy.vo.Dept.SELF_FIELDS);
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	
//	public String selectUser()throws Exception {
//		try{
//			if(WFUtil.isNotNull(usr.getUsrName())){
//				String name = URLDecoder.decode(usr.getUsrName(),"UTF-8");
//				usr.setUsrName(name);
//			}
//			
//			usrs = new UsrFacade().findAll(usr);
//			this.setJson(JSON.toJSONString(usrs));
//		}
//		catch(Exception e) {
//				setMsg(MSG.F_SEA);
//				Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
//				return ERROR;
//		}
//		return PGLIS;
//	}
	
	public String selectUser() throws Exception {
		try {
			if (WFUtil.isNotNull(usr.getUsrName())) {
				String name = URLDecoder.decode(usr.getUsrName(), "UTF-8");
				usr.setUsrName(name);
			}
			String fileds = Usr.SELF_FIELDS+",Dept.DeptNm as DeptNm";
			String sql = "select " + fileds
					+ " from UsrView Usr left join Dept on (Dept.DeptId = Usr.DeptId) where 1=1 and Usr.Status = 1 and Usr.IsInner = 1 "   //只取hr系统的数据
					+ new UsrHelper().getSqlCondition(usr);
			System.out.println("sql````"+sql);
			usrs = new UsrFacade().find(sql, fileds);
			
//			String sqlDept = "select " + Usr.SELF_DEPT_FIELDS
//					+ " from UsrView Usr left JOIN   Dept ON (Dept.deptno = Usr.OrgNo) where 1=1  "
//					+ new UsrHelper().getSqlCondition(usr);
			List<zrsy.vo.Dept> dept = new DeptFacade().find(sql, "Dept.DeptNm");
			Map map = new HashMap();
			map.put("usrs", usrs);
			map.put("dept", dept);
			System.out.println(map.toString());
			
			System.out.println("JSON.toJSONString(map)"+JSON.toJSONString(map));
			this.setJson(JSON.toJSONString(map));
		} catch (Exception e) {
			e.printStackTrace();
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass())
					.error("SchCfgAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	
	//选择人员部门名字ID
	public String selectUser1() throws Exception {
		try {
			if (WFUtil.isNotNull(usr.getUsrName())) {
				String name = URLDecoder.decode(usr.getUsrName(), "UTF-8");
				usr.setUsrName(name);
			}
			//select dp.deptNm from Usr us inner join Dept dp on us.OrgNo = dp.deptid where us.id = WfRd.CreateBy )
			String sql = "select " + Usr.SELF_FIELDS +" ,(select dp.deptNm from UsrView uv inner join Dept dp on uv.OrgNo = dp.deptid ) as deptNm"
					+ " from UsrView Usr where 1=1 "
					+ new UsrHelper().getSqlCondition(usr);
			String usrstring = Usr.SELF_FIELDS +",(select dp.deptNm from UsrView uv inner join Dept dp on uv.OrgNo = dp.deptid ) as deptNm";
			usrs = new UsrFacade().find(sql, usrstring);
			System.out.println("```````````````"+JSON.toJSONString(usrs));
			this.setJson(JSON.toJSONString(usrs));
		} catch (Exception e) {
			e.printStackTrace();
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass())
					.error("SchCfgAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	
	
	public String selectUserWithDept() throws Exception {
		try {
			if (WFUtil.isNotNull(usr.getUsrName())) {
				String name = URLDecoder.decode(usr.getUsrName(), "UTF-8");
				usr.setUsrName(name);
			}
			String fileds = Usr.SELF_FIELDS+",Dept.DeptNm as DeptNm";
			String sql = "select " + fileds
					+ " from UsrView Usr left join Dept on (Dept.DeptId = Usr.DeptId) where 1=1 AND Dept.DeptNm IS NOT NULL"
					+ new UsrHelper().getSqlCondition(usr);
			System.out.println("sql````"+sql);
			usrs = new UsrFacade().find(sql, fileds);
			this.setJson(JSON.toJSONString(usrs));
		} catch (Exception e) {
			e.printStackTrace();
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass())
					.error("SchCfgAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	
	
	public String loadAllUserWithDept() throws Exception {
		try {
//			if (WFUtil.isNotNull(usr.getUsrName())) {
//				String name = URLDecoder.decode(usr.getUsrName(), "UTF-8");
//				usr.setUsrName(name);
//			}
			//String fileds = Usr.SELF_FIELDS+",Dept.DeptNm as DeptNm";
			String fileds = "Usr.Id,Usr.ComId,Usr.DeptId,Usr.OrgNo,Usr.UsrName,Usr.Email,Usr.Status,Dept.DeptNm as DeptNm";
			String sql = "select " + fileds
					+ " from UsrView Usr left join Dept on (Dept.DeptId = Usr.DeptId) where 1=1 ";
					//+ new UsrHelper().getSqlCondition(usr);
			usrs = new UsrFacade().find(sql, fileds);
			System.out.println("-----usrs.size------"+usrs.size());
			this.setJson(JSON.toJSONString(usrs));
		} catch (Exception e) {
			e.printStackTrace();
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass())
					.error("SchCfgAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	
	
	
	
	public String add() throws Exception {
		try {
			//if(usr != null && usr.getId() != null) {
				//usr = new UsrFacade().findById(usr);
				//setJson(JSON.toJSONString(usr)); 
			//}
			coms = new zrsy.facade.ComFacade().find("select "+zrsy.vo.Com.SELF_FIELDS+" from Com where status=1",zrsy.vo.Com.SELF_FIELDS);
			depts = new zrsy.facade.DeptFacade().find("select "+zrsy.vo.Dept.SELF_FIELDS+" from Dept where status=1",zrsy.vo.Dept.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(usr != null && usr.getId() != null) {
				usr = new UsrFacade().findById(usr);
				usr.setSyId(getUsrSession().getId());
				setJson(JSON.toJSONString(usr)); 
			}
			coms = new zrsy.facade.ComFacade().find("select "+zrsy.vo.Com.SELF_FIELDS+" from Com",zrsy.vo.Com.SELF_FIELDS);
			depts = new zrsy.facade.DeptFacade().find("select "+zrsy.vo.Dept.SELF_FIELDS+" from Dept",zrsy.vo.Dept.SELF_FIELDS);
		}
		catch(Exception e) {
			e.printStackTrace();
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			
			
			if(usr != null && usr.getId() != null) {
				usr = new UsrFacade().findById(usr);
				usr.setSyId(getUsrSession().getSyId());
				setJson(JSON.toJSONString(usr)); 
			}
			coms = new zrsy.facade.ComFacade().find("select "+zrsy.vo.Com.SELF_FIELDS+" from Com",zrsy.vo.Com.SELF_FIELDS);
			depts = new zrsy.facade.DeptFacade().find("select "+zrsy.vo.Dept.SELF_FIELDS+" from Dept",zrsy.vo.Dept.SELF_FIELDS);
			gps = new GpFacade().find("select "+zrsy.vo.Gp.SELF_FIELDS+" from Gp where syId="+usr.getSyId(),zrsy.vo.Gp.SELF_FIELDS);
			
			List<GpUsr> gpUsrs =  new GpUsrFacade().find("select "+zrsy.vo.GpUsr.SELF_FIELDS+" from GpUsr " +
			
					"where GpUsr.UsrId = "+usr.getId(),zrsy.vo.GpUsr.SELF_FIELDS);
			
			
//			
//			if(usr != null && usr.getId() != null) {
//				usr = new UsrFacade().findById(usr);
//				setJson(JSON.toJSONString(usr)); 
//			}
//			coms = new zrsy.facade.ComFacade().find("select "+zrsy.vo.Com.SELF_FIELDS+" from Com",zrsy.vo.Com.SELF_FIELDS);
//			
//			depts = new zrsy.facade.DeptFacade().find("select "+zrsy.vo.Dept.SELF_FIELDS+" from Dept",zrsy.vo.Dept.SELF_FIELDS);
//			gps = new GpFacade().find("select "+zrsy.vo.Gp.SELF_FIELDS+" from Gp where SyId = "+ getUsrSession().getSyId(),zrsy.vo.Gp.SELF_FIELDS);
			
//			List<GpUsr> gpUsrs =  new GpUsrFacade().find("select "+zrsy.vo.GpUsr.SELF_FIELDS+" from GpUsr " +
//					"where GpUsr.UsrId = "+usr.getId(),zrsy.vo.GpUsr.SELF_FIELDS);
			for(int i=0; i<gps.size(); i++) {
				for(int j=0; j<gpUsrs.size(); j++) {
					if(gps.get(i).getGpId().equals(gpUsrs.get(j).getGpId())) {
						gps.get(i).setChecked("checked");
					}
				}
			}
			scos = new ScoFacade().find("select "+zrsy.vo.Sco.SELF_FIELDS+" from [GnSy].[dbo].[Sco] where Sco.Status=1",zrsy.vo.Sco.SELF_FIELDS);
			List<UsrSco> usrScos = new UsrScoFacade().find("select "+zrsy.vo.UsrSco.SELF_FIELDS+" from [GnSy].[dbo].[UsrSco] " +
					"where UsrSco.UsrId = "+usr.getId(),zrsy.vo.UsrSco.SELF_FIELDS);
			for(int i=0;i<scos.size();i++){
				for(int j=0;j<usrScos.size();j++){
					if(scos.get(i).getScoId().equals(usrScos.get(j).getScoId())){
						scos.get(i).setChecked("checked");
					}
				}
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String auth() throws Exception {
		try {
			if(usr != null && usr.getId() != null) {
				usr = new UsrFacade().findById(usr);
				setJson(JSON.toJSONString(usr)); 
			}
			coms = new zrsy.facade.ComFacade().find("select "+zrsy.vo.Com.SELF_FIELDS+" from Com",zrsy.vo.Com.SELF_FIELDS);
			
			depts = new zrsy.facade.DeptFacade().find("select "+zrsy.vo.Dept.SELF_FIELDS+" from Dept",zrsy.vo.Dept.SELF_FIELDS);
			gps = new GpFacade().find("select "+zrsy.vo.Gp.SELF_FIELDS+" from Gp",zrsy.vo.Gp.SELF_FIELDS);
			
			List<GpUsr> gpUsrs =  new GpUsrFacade().find("select "+zrsy.vo.GpUsr.SELF_FIELDS+" from GpUsr " +
					"where GpUsr.UsrId = "+usr.getId(),zrsy.vo.GpUsr.SELF_FIELDS);
			for(int i=0; i<gps.size(); i++) {
				for(int j=0; j<gpUsrs.size(); j++) {
					if(gps.get(i).getGpId().equals(gpUsrs.get(j).getGpId())) {
						gps.get(i).setChecked("checked");
					}
				}
			}
			scos = new ScoFacade().find("select "+zrsy.vo.Sco.SELF_FIELDS+" from [GnSy].[dbo].[Sco] where Sco.Status=1",zrsy.vo.Sco.SELF_FIELDS);
			List<UsrSco> usrScos = new UsrScoFacade().find("select "+zrsy.vo.UsrSco.SELF_FIELDS+" from [GnSy].[dbo].[UsrSco] " +
					"where UsrSco.UsrId = "+usr.getId(),zrsy.vo.UsrSco.SELF_FIELDS);
			for(int i=0;i<scos.size();i++){
				for(int j=0;j<usrScos.size();j++){
					if(scos.get(i).getScoId().equals(usrScos.get(j).getScoId())){
						scos.get(i).setChecked("checked");
					}
				}
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return "author";
	}
	public String list() throws Exception {
		try {
			if(usr == null) usr = new Usr();
			int total = new UsrFacade().amount(usr);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			usrs = new UsrFacade().find(usr,getPageVO());
			UsrJson usrJson = new UsrJson();
			usrJson.Rows = usrs;
			usrJson.Total = total;
			setJson(JSON.toJSONString(usrJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			usr.setGpUsrs(gpUsrs);
			usr.setUsrScos(usrScos);
			usr.setLastUpd(getUsrSession().getId());
			usr.setLastDate(new Date());
			usr.setSyId(getUsrSession().getSyId());
			if(usr.getId() == null){
				usr.setCreateBy(getUsrSession().getId());
				usr.setCreateDate(new Date());
				new UsrFacade().save(usr);
			}else{
				new UsrFacade().update(usr);
			}
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			e.printStackTrace();
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("UsrAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//usr.setCreateBy(getSession().getUserId());
			//usr.setCreateDate(new Date());
			//usr.setLastUpd(getSession().getUserId());
			//usr.setLastUpdDate(new Date());
			new UsrFacade().update(usr);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("UsrAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(usrs != null && usrs.size() > 0) {
				for(int i=0; i<usrs.size();i++) {
					if(usrs.get(i) != null) {
						//usrs.get(i).setLastUpd(getSession().getUserId());
						//usrs.get(i).setLastUpdDate(new Date());
						new UsrFacade().submit(usrs.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(usrs != null && usrs.size() > 0) {
				for(int i=0; i<usrs.size();i++) {
					if(usrs.get(i) != null) {
						//usrs.get(i).setLastUpd(getSession().getUserId());
						//usrs.get(i).setLastUpdDate(new Date());
						new UsrFacade().submit(usrs.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(usrs != null && usrs.size() > 0) {
				for(int i=0; i<usrs.size();i++) {
					if(usrs.get(i) != null) {
						//usrs.get(i).setLastUpd(getSession().getUserId());
						//usrs.get(i).setLastUpdDate(new Date());
						new UsrFacade().update(usrs.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(usrs != null && usrs.size() > 0) {
				for(int i=0; i<usrs.size();i++) {
					if(usrs.get(i) != null) {
						//usrs.get(i).setLastUpd(getSession().getUserId());
						//usrs.get(i).setLastUpdDate(new Date());
						new UsrFacade().submit(usrs.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(usrs != null && usrs.size() > 0) {
				for(int i=0; i<usrs.size();i++) {
					if(usrs.get(i) != null) {
						//usrs.get(i).setLastUpd(getSession().getUserId());
						//usrs.get(i).setLastUpdDate(new Date());
						new UsrFacade().submit(usrs.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(usrs != null && usrs.size() > 0) {
				for(int i=0; i<usrs.size();i++) {
					if(usrs.get(i) != null) {
						//usrs.get(i).setLastUpd(getSession().getUserId());
						//usrs.get(i).setLastUpdDate(new Date());
						new UsrFacade().submit(usrs.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(usrs != null && usrs.size() > 0) {
				for(int i=0; i<usrs.size();i++) {
					if(usrs.get(i) != null) {
						//usrs.get(i).setLastUpd(getSession().getUserId());
						//usrs.get(i).setLastUpdDate(new Date());
						new UsrFacade().submit(usrs.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(usrs != null && usrs.size() > 0) {
				for(int i=0; i<usrs.size();i++) {
					if(usrs.get(i) != null){
						//usrs.get(i).setLastUpd(getSession().getUserId());
						//usrs.get(i).setLastUpdDate(new Date());
						new UsrFacade().submit(usrs.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(usrs != null && usrs.size() > 0) {
				for(int i=0; i<usrs.size();i++) {
					if(usrs.get(i) != null){
						//usrs.get(i).setLastUpd(getSession().getUserId());
						//usrs.get(i).setLastUpdDate(new Date());
						new UsrFacade().review(usrs.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(usrs != null && usrs.size() > 0) {
				for(int i=0; i<usrs.size();i++) {
					if(usrs.get(i) != null) {
						//usrs.get(i).setLastUpd(getSession().getUserId());
						//usrs.get(i).setLastUpdDate(new Date());
						new UsrFacade().review(usrs.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(usrs != null && usrs.size() > 0) {
				for(int i=0; i<usrs.size();i++) {
					if(usrs.get(i) != null) {
						//usrs.get(i).setLastUpd(getSession().getUserId());
						//usrs.get(i).setLastUpdDate(new Date());
						new UsrFacade().confirm(usrs.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(usrs != null && usrs.size() > 0) {
				for(int i=0; i<usrs.size();i++) {
					if(usrs.get(i) != null) {
						//usrs.get(i).setLastUpd(getSession().getUserId());
						//usrs.get(i).setLastUpdDate(new Date());
						new UsrFacade().confirm(usrs.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				Workbook book = Workbook.getWorkbook(fileInp);   
		          Sheet st = book.getSheet(0); 
		          if(st.getCell(0, 0).getContents().trim().equals("Id") 
							&& st.getCell(1, 0).getContents().trim().equals("公司") 
							&& st.getCell(2, 0).getContents().trim().equals("部门") 
							&& st.getCell(3, 0).getContents().trim().equals("用户号") 
							&& st.getCell(4, 0).getContents().trim().equals("用户名称")
							&& st.getCell(5, 0).getContents().trim().equals("登录账号")
							&& st.getCell(6, 0).getContents().trim().equals("密码")
							&& st.getCell(7, 0).getContents().trim().equals("电子邮件")
							){
		          for (int row=1;row<st.getRows()
					&&!st.getCell(1, row).getContents().equals("")
					&&!st.getCell(2, row).getContents().equals("")
					&&!st.getCell(3, row).getContents().equals("")
					&&!st.getCell(4, row).getContents().equals("")
					&&!st.getCell(5, row).getContents().equals("")
					&&!st.getCell(6, row).getContents().equals("")
					&&!st.getCell(7, row).getContents().equals("")
					;row++) {
			        	  usr = new Usr();
			        	  coms = new zrsy.facade.ComFacade().find("select "+zrsy.vo.Com.SELF_FIELDS+" from Com where ComNm='"+st.getCell(1, row).getContents().trim().toString()+"'",zrsy.vo.Com.SELF_FIELDS);
			      		  depts = new zrsy.facade.DeptFacade().find("select "+zrsy.vo.Dept.SELF_FIELDS+" from Dept where DeptNm='"+st.getCell(2, row).getContents().trim().toString()+"'",zrsy.vo.Dept.SELF_FIELDS);
			        	  if(coms.size()>0&&depts.size()>0){
				      		  if(st.getCell(0,row).getContents().trim()!=null&&!st.getCell(0,row).getContents().equals("")){
				        		  usr.setId(Integer.valueOf(st.getCell(0,row).getContents().trim()));
					        	  usr = new UsrFacade().findById(usr);
				      		  }
				      		  if(usr!=null){
					        		  usr.setComId(coms.get(0).getComId());
					        		  usr.setDeptId(depts.get(0).getDeptId());
					        		  usr.setUsrNo(st.getCell(3,row).getContents().trim());
					        		  usr.setUsrName(st.getCell(4,row).getContents().trim());
					        		  usr.setLogin(st.getCell(5,row).getContents().trim());
					        		  usr.setPwd(st.getCell(6,row).getContents().trim());
					        		  usr.setEmail(st.getCell(7,row).getContents().trim());
					        		  usr.setLastUpd(getUsrSession().getId());
						      		usr.setLastDate(new Date());
					        		  new UsrFacade().update(usr);
					          }else{
					        	  usr.setComId(coms.get(0).getComId());
				        		  usr.setDeptId(depts.get(0).getDeptId());
				        		  usr.setUsrNo(st.getCell(3,row).getContents().trim());
				        		  usr.setUsrName(st.getCell(4,row).getContents().trim());
				        		  usr.setLogin(st.getCell(5,row).getContents().trim());
				        		  usr.setPwd(st.getCell(6,row).getContents().trim());
				        		  usr.setEmail(st.getCell(7,row).getContents().trim());
				        		  usr.setStatus(MSG.CONST_STATUS_1);
				        		usr.setLastUpd(getUsrSession().getId());
					      		usr.setLastDate(new Date());
				        		usr.setCreateBy(getUsrSession().getId());
				  				usr.setCreateDate(new Date());
				        		  new UsrFacade().save(usr);
				        	  }
			        	  }
			         }
					setMsg(MSG.S_IMP);
				}
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<Usr> usrs = new UsrFacade().find(usr);
			if(usrs != null && usrs.size() > 0) {
				WritableCellFormat wcformat = new WritableCellFormat();
				wcformat.setAlignment(jxl.format.Alignment.CENTRE);
				wcformat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				wcformat.setBorder(Border.LEFT,BorderLineStyle.THIN);
				wcformat.setBorder(Border.RIGHT,BorderLineStyle.THIN);
				wcformat.setBorder(Border.TOP,BorderLineStyle.THIN);
				wcformat.setBorder(Border.BOTTOM,BorderLineStyle.THIN);
				wcformat.setWrap(true);
				OutputStream os = getOutputStream();
				workbook = Workbook.createWorkbook(os);
				WritableSheet ws = workbook.createSheet("sheet0", 0);
				int index = 0;
					ws.addCell(new Label(index,0,"Id",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,0,"公司",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,0,"部门",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,0,"登录账号",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,0,"用户号",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,0,"用户名称",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,0,"状态",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,0,"电子邮箱",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,0,"创建日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,0,"更新日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,0,"备注",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 0;
				for(int i=0; i<usrs.size();i++) {
					row++;
					int m = 0;
					if(usrs.get(i).getId() != null) 
						ws.addCell(new jxl.write.Number(m,row,usrs.get(i).getId(),wcformat));
					m++;
					if(usrs.get(i).getComId() != null) 
						ws.addCell(new jxl.write.Label(m,row,usrs.get(i).getComNm(),wcformat));
					m++;
					if(usrs.get(i).getDeptId() != null) 
						ws.addCell(new jxl.write.Label(m,row,usrs.get(i).getDeptNm(),wcformat));
					m++;
					if(usrs.get(i).getLogin() != null) 
						ws.addCell(new jxl.write.Label(m,row,usrs.get(i).getLogin(),wcformat));
					m++;
					if(usrs.get(i).getUsrNo() != null) 
						ws.addCell(new jxl.write.Label(m,row,usrs.get(i).getUsrNo(),wcformat));
					m++;
					if(usrs.get(i).getUsrName() != null) 
						ws.addCell(new jxl.write.Label(m,row,usrs.get(i).getUsrName(),wcformat));
					m++;
					if(usrs.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,usrs.get(i).getStatus(),wcformat));
					m++;
					if(usrs.get(i).getEmail() != null) 
						ws.addCell(new jxl.write.Label(m,row,usrs.get(i).getEmail(),wcformat));
					m++;
					if(usrs.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,usrs.get(i).getCreateDate(),wcformat));
					m++;
					if(usrs.get(i).getLastDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,usrs.get(i).getLastDate(),wcformat));
					m++;
					if(usrs.get(i).getRemark() != null) 
						ws.addCell(new jxl.write.Label(m,row,usrs.get(i).getRemark(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("UsrListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(usrs != null && usrs.size() > 0) {
				for(int i=0; i<usrs.size();i++) {
					if(usrs.get(i) != null) {
						//usrs.get(i).setLastUpd(getSession().getUserId());
						//usrs.get(i).setLastUpdDate(new Date());
						new UsrFacade().confirm(usrs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(usrs != null && usrs.size() > 0) {
				for(int i=0; i<usrs.size();i++) {
					if(usrs.get(i) != null) {
						//usrs.get(i).setLastUpd(getSession().getUserId());
						//usrs.get(i).setLastUpdDate(new Date());
						new UsrFacade().confirm(usrs.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("UsrAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<Usr> getUsrs() {
		return usrs;
	}
	public void setUsrs(List<Usr> usrs) {
		this.usrs = usrs;
	}
	public Usr getUsr() {
		return usr;
	}
	public void setUsr(Usr usr) {
		this.usr = usr;
	}
	public WritableWorkbook getWorkbook() {
		return workbook;
	}
	public void setWorkbook(WritableWorkbook workbook) {
		this.workbook = workbook;
	}
	public java.util.List<zrsy.vo.Gp> getGps() {
		return gps;
	}
	public void setGps(java.util.List<zrsy.vo.Gp> gps) {
		this.gps = gps;
	}
	public void addtoGps(zrsy.vo.Gp gp){
		if(getGps() == null) setGps(new java.util.ArrayList<zrsy.vo.Gp>());
			getGps().add(gp);
	}
	public File getFileInp() {
		return fileInp;
	}
	public void setFileInp(File fileInp) {
		this.fileInp = fileInp;
	}
	public String getChoices() {
		return choices;
	}
	public void setChoices(String choices) {
		this.choices = choices;
	}
	public java.util.List<zrsy.vo.GpUsr> getGpUsrs() {
		return gpUsrs;
	}
	public void setGpUsrs(java.util.List<zrsy.vo.GpUsr> gpUsrs){
		this.gpUsrs = gpUsrs;
	}
	public void addtoGpUsrs(zrsy.vo.GpUsr gpUsr){
		if(getGpUsrs() == null) setGpUsrs(new java.util.ArrayList<zrsy.vo.GpUsr>());
			getGpUsrs().add(gpUsr);
	}
	public java.util.List<zrsy.vo.Com> getComs() {
		return coms;
	}
	public void setComs(java.util.List<zrsy.vo.Com> coms){
		this.coms = coms;
	}
	public void addtoComs(zrsy.vo.Com com){
		if(getComs() == null) setComs(new java.util.ArrayList<zrsy.vo.Com>());
			getComs().add(com);
	}
	public java.util.List<zrsy.vo.Dept> getDepts() {
		return depts;
	}
	public void setDepts(java.util.List<zrsy.vo.Dept> depts){
		this.depts = depts;
	}
	public void addtoDepts(zrsy.vo.Dept dept){
		if(getDepts() == null) setDepts(new java.util.ArrayList<zrsy.vo.Dept>());
			getDepts().add(dept);
	}
	public java.util.List<zrsy.vo.Sco> getScos() {
		return scos;
	}
	public void setScos(java.util.List<zrsy.vo.Sco> scos) {
		this.scos = scos;
	}
	public void addtoScos(zrsy.vo.Sco sco){
		if(getScos() == null) setScos(new java.util.ArrayList<zrsy.vo.Sco>());
			getScos().add(sco);
	}
	public java.util.List<zrsy.vo.UsrSco> getUsrScos() {
		return usrScos;
	}
	public void setUsrScos(java.util.List<zrsy.vo.UsrSco> usrScos) {
		this.usrScos = usrScos;
	}
	public void addtoUsrScos(zrsy.vo.UsrSco usrSco){
		if(getUsrScos() == null) setUsrScos(new java.util.ArrayList<zrsy.vo.UsrSco>());
		getUsrScos().add(usrSco);
	}
}