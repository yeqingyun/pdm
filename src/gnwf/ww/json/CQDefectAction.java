package gnwf.ww.json;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import gnwf.facade.CQDefectFacade;
import gnwf.facade.CQStateFacade;
import gnwf.facade.WfQuesFacade;
import gnwf.facade.WfRdFacade;
import gnwf.parser.CQDefectExcelParser;
import gnwf.parser.ExcelContext;
import gnwf.parser.ExcelFormatIncorrectException;
import gnwf.vo.CQDefect;
import gnwf.vo.CQState;
import gnwf.vo.WfQues;
import gnwf.vo.WfRd;
import gnwf.vo.json.CQDefectJson;
import gnwf.ww.BasicAction;
import gnwf.ww.MSG;
import gnwf.ww.workflow.WFUtil;

import org.apache.log4j.Logger;

import zrprjt.facade.PrjtUsrFacade;
import zrprjt.vo.PrjtUsr;
import zrsy.vo.Usr;

import com.alibaba.fastjson.JSON;
import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;



public class CQDefectAction extends BasicAction {
	private static final long serialVersionUID = -4260131180155543697L;
	public static final Logger log = Logger.getLogger(CQDefectAction.class);
	
	private CQDefect defect;
	private CQState state;
	private List<CQState> stateList;
	private File fileInp;
	private WfQues wfQues;
	private String action;
	
	/**
	 * 转到CQ缺陷管理界面
	 * @return
	 */
	public String defectManager() {
		try {
			if(this.state == null) {
				this.state = new CQState();
			}
			this.stateList = new CQStateFacade().findAll(state);
		} catch (Exception e) {
			e.printStackTrace();
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("CQDefectAction defectManager Exception", e);
			return ERROR;
		}
		return "defectManager";
	}
	
	/**
	 * 列表界面
	 * @return
	 */
	public String list() {
		try {
			if(this.defect == null) {
				this.defect = new CQDefect();
			}
			CQDefectFacade facade = new CQDefectFacade();
			int total = facade.amount(this.defect);
			if (getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			List<CQDefect> defectList = facade.find(defect, getPageVO());
			CQDefectJson defectJson = new CQDefectJson(defectList,total);
			String json = JSON.toJSONString(defectJson);
			System.out.println(json);
			setJson(json);
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("CQDefectAction list Exception", e);
			e.printStackTrace();
			return ERROR;
		}
		return PGLIS;
	}
	/**
	 * CQ视图界面
	 * @return
	 */
	public String managerDefect() {
		try {
			if(this.defect != null && this.defect.getId() != null) {
				this.defect = new CQDefectFacade().findById(defect);
			}
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			e.printStackTrace();
			Logger.getLogger(this.getClass()).error("CQDefectAction managerDefect Exception", e);
			return ERROR;
		}
		return "managerDefect";
	}
	
	/**
	 * 导入CQ问题
	 * @return
	 */
	 public String importNewCQQues() {
		 try {
				if (fileInp != null) {
					ExcelContext<CQDefect> context = new ExcelContext<CQDefect>(new CQDefectExcelParser());
					List<CQDefect> defectList = context.parser(fileInp.getAbsolutePath());
					if(defectList.size() == 0) {
						setMsg("问题Excel模板格式不正确或数据为空");
					}else {
						WfRd rd = new WfRd();
						rd.setWfNo(this.wfQues.getWfNo());
						rd = new WfRdFacade().findById(rd);
						List<Usr> usrlist = new ArrayList<Usr>();
						List<WfQues> saveList = new ArrayList<WfQues>();
						for(CQDefect cqdefect : defectList) {
							if(cqdefect.getId() == null || cqdefect.getHeadline() == null) {
								continue;
							}
							WfQues ques = new WfQues();
							List<WfQues> qlist = new WfQuesFacade().find("select WfQues.CrDefectId"
									+ " from WfQues where WfQues.PrjtNo = '" + rd.getProjectNo() + "'", "WfQues.CrDefectId");
							for(WfQues q : qlist) {
								if(cqdefect.getId().equals(q.getCrDefectId())) {
									action = "CQDefect!importNewCQQues.shtml";
									setMsg("不能重复导入！");
									return PGUPL;
								}
							}
							ques.setPrjtNo(rd.getProjectNo());
							ques.setWfNo(rd.getWfNo());
							ques.setScheId(rd.getScheId());
							ques.setCreateBy(getUsrSession().getId());
							ques.setIdtfBy(getUsrSession().getId());
							ques.setCreateDate(new Date());
							ques.setStatus(1);
							ques.setLastUpd(getUsrSession().getId());
							ques.setLastUpdDate(new Date());
							ques.setCrDefectId(cqdefect.getId());
							ques.setTitle(cqdefect.getHeadline());
							ques.setQuesText(" ");
							//交给DQA
							List<PrjtUsr> prjtUsrList = new PrjtUsrFacade().find("select PrjtUsr.UsrId from PrjtUsr join PrjtRole on PrjtUsr.RoleId = PrjtRole.RoleId" +
									" where PrjtRole.RoleNm = 'DQA' and PrjtUsr.PrjtNo = '" + rd.getProjectNo() + "'", "PrjtUsr.UsrId");
							Usr usr = new zrsy.facade.UsrFacade().findById("select UsrName from Usr where id = " + prjtUsrList.get(0).getUsrId(), "Usr.UsrName");
							ques.setResponsibleIds(String.valueOf(prjtUsrList.get(0).getUsrId()));
							ques.setUsrName(usr.getUsrName());
							usr = new Usr();
							usr.setId(Integer.valueOf(ques.getResponsibleIds()));
							usrlist.add(usr);
							
							saveList.add(ques);
						}
						ActionContext ctx = ActionContext.getContext();
						HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
						String impQuesIds = new WfQuesFacade().save(saveList,request);
						List<PrjtUsr> prjtUsrList = new PrjtUsrFacade().find("select PrjtUsr.UsrId from PrjtUsr join PrjtRole on PrjtUsr.RoleId = PrjtRole.RoleId"
		                         + " where PrjtRole.RoleNm in('DQA','项目经理') and PrjtUsr.PrjtNo = '"
		                         + rd.getProjectNo() + "'","PrjtUsr.UsrId");
						for(PrjtUsr pu : prjtUsrList) {
							Usr u = new Usr();
							u.setId(pu.getUsrId());
							usrlist.add(u);
						}
						String title = "工作流(" + rd.getWfName() + ")-新增CQ问题通知";
						String content = "尊敬的同事，您好：<p>此工作流新增了" + saveList.size() + "个问题,请您及时关注。</p>链接地址 ： <a href=" + getAppcationURL() + "/WfQues!taskList.shtml?prjtNo="
										+ rd.getProjectNo() + "&wfNo=" + this.wfQues.getWfNo() + ">点击进入</a><p>问题编号为:" + impQuesIds + "</p>";
						WFUtil.sendMailByIT(title.toString(), content.toString(), usrlist);
						setMsg(MSG.S_IMP + "(共导入" + saveList.size() + "条问题)");
					}
				}
			}catch(IOException e) {
				setMsg("IO异常");
				Logger.getLogger(this.getClass()).error("CQDefectAction importNewCQQues IOException",e);
				e.printStackTrace();
			} catch (ExcelFormatIncorrectException e) {
				setMsg("文件不是Excel格式");
				Logger.getLogger(this.getClass()).error("CQDefectAction importNewCQQues ExcelFormatIncorrectException", e);
				e.printStackTrace();
			} catch (Exception e) {
				setMsg("数据库访问出错");
				Logger.getLogger(this.getClass()).error("CQDefectAction importNewCQQues Exception", e);
				e.printStackTrace();
			}
		 	action = "CQDefect!importNewCQQues.shtml";
			return PGUPL;
	 }
	

	public CQDefect getDefect() {
		return defect;
	}

	public void setDefect(CQDefect defect) {
		this.defect = defect;
	}

	public CQState getState() {
		return state;
	}

	public void setState(CQState state) {
		this.state = state;
	}

	public List<CQState> getStateList() {
		return stateList;
	}

	public void setStateList(List<CQState> stateList) {
		this.stateList = stateList;
	}

	public File getFileInp() {
		return fileInp;
	}

	public void setFileInp(File fileInp) {
		this.fileInp = fileInp;
	}

	

	public WfQues getWfQues() {
		return wfQues;
	}

	public void setWfQues(WfQues wfQues) {
		this.wfQues = wfQues;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
}