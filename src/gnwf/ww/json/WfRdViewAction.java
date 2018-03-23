package gnwf.ww.json;

import gnmail.vo.Mail;
import gnwf.facade.WfCfgFacade;
import gnwf.facade.WfDocFacade;
import gnwf.facade.WfFieldFacade;
import gnwf.facade.WfQuesFacade;
import gnwf.facade.WfRdFacade;
import gnwf.facade.WfRdFieldFacade;
import gnwf.facade.WfRdSignFacade;
import gnwf.facade.WfRdTaskFacade;
import gnwf.facade.WfRiskFacade;
import gnwf.facade.WfScheCfgDocFacade;
import gnwf.facade.WfStepFacade;
import gnwf.facade.WfStepNextFacade;
import gnwf.vo.WFMatlCategory;
import gnwf.vo.WfCfg;
import gnwf.vo.WfCfgRelate;
import gnwf.vo.WfDoc;
import gnwf.vo.WfField;
import gnwf.vo.WfItem;
import gnwf.vo.WfMatl;
import gnwf.vo.WfQues;
import gnwf.vo.WfRd;
import gnwf.vo.WfRdField;
import gnwf.vo.WfRdSign;
import gnwf.vo.WfRdTask;
import gnwf.vo.WfRisk;
import gnwf.vo.WfScheCfgDoc;
import gnwf.vo.WfStep;
import gnwf.vo.WfStepNext;
import gnwf.vo.json.AjaxJson;
import gnwf.vo.json.WfCfgJson;
import gnwf.vo.json.WfDocJson;
import gnwf.vo.json.WfQuesJson;
import gnwf.vo.json.WfRdFieldJson;
import gnwf.vo.json.WfRiskJson;
import gnwf.ww.BasicAction;
import gnwf.ww.MSG;
import gnwf.ww.ToKenProcessor;
import gnwf.ww.workflow.Row;
import gnwf.ww.workflow.WFCenter;
import gnwf.ww.workflow.WFUtil;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import zrprjt.facade.PrjtDefFacade;
import zrprjt.vo.PrjtDef;
import zrprjt.vo.Task;
import zrsy.facade.DeptFacade;
import zrsy.vo.Dept;
import zrsy.vo.Usr;
import Utils.SendMailUtil;
import Utils.StringUtils;

import com.alibaba.fastjson.JSON;
import com.opensymphony.webwork.ServletActionContext;

public class WfRdViewAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<WfRd> wfRds;
	private WfRd wfRd = new WfRd();
	private java.util.List<gnwf.vo.WfDoc> wfDocs;
	private java.util.List<gnwf.vo.WfQuesRelate> wfQuesRelates;
	private java.util.List<gnwf.vo.WfMatl> wfMatls;
	private java.util.List<gnwf.vo.WfRdTask> wfRdTasks;
	private java.util.List<gnwf.vo.WfRdField> wfRdFields;
	private WfStep currentStep = new WfStep();
	private WfCfg wfCfg;
	private int isLeader = 0;
	private int isForFlow = 0;//是否为flow提交的表单，0：不是，1：是

	private WfCfg cfg;
	private WFCenter center; // 中心处理接口
	private File impfile; // 导入文件
	private Usr user; // 当前用户
	private Integer flowId; // 流程ID
	private Integer taskId; // 任务ID
	private WfRdTask unAcceptTask; // 未接收任务
	private WfRdTask currentTask; // 当前任务
	private List<WfRdSign> signList; // 会签List
	private List<WfCfgRelate> relateFlows; // 可触发的相关流程
	private List<WfRd> childList; // 子流程
	private List<WfRdTask> taskList; // 任务进度
	private List<File> files; // 附件上传
	private List<Integer> fileCates; // 附件类别
	private List<WfScheCfgDoc> docCates; // 本流程需上传文档类别
	private List<WfDoc> docList; // 附件集
	private WfDoc wfDoc;
	private WfRdSign wfRdSign; // 会签意见内容
	private List<WfRdField> fieldContents; // 表单内容值
	private List<Row> rows; // 列表扩展字段集合
	private List<String> ajaxList; // ajax接受值
	private String ajaxShowInfo; // ajax提示内容
	private String includeJspUri; // 包含页uri
	private List<WfStep> nextSteps; // 需转交下一步骤
	private List<WfRdTask> wfTasks; // 需转交任务
	private List<WfStep> allSteps; // 本流程全部步骤
	private List<WfStepNext> allNextSteps; // 对应下一步步骤列表
	private List<Dept> deptList;
	private int taskCount; // 计数
	private String tips; // 提示
	private String isReloadGrid; // 是否更新父页面grid
	private List<WfMatl> matlList; // 物料集
	private List<WfQues> quesList; // 问题集
	private Mail mail; // 邮件
	private String currentUserName; // 当前用户名
	private Date currentDate; // 当天日期
	private String mailName; // 最后一步通知申请人
	private Integer agentBy; // 任务代办人

	private String hiddenValue; // 隐藏域内容
	private List<WFMatlCategory> cateList; // A30物料组
	private List<WfItem> itemList; // A48测试类
	private Set<WfStep> imgStepList; // 画图进度
	protected List<WfRdTask> backTaskssList; // 会签List
	private String flagDcc;
	public List<WfRdTask> getBackTaskssList() {
		return backTaskssList;
	}


	public void setBackTaskssList(List<WfRdTask> backTaskssList) {
		this.backTaskssList = backTaskssList;
	}

	private int isfromPrjtDef = 0;

	public String execute() throws Exception {
		try {
			initWorkFlow();
			checkReSub = getTokenKey(); // 防重复

			if (center.isHasJob()) { // 有办理中任务
				center.processExec();
				return "job";
			} else { // 无办理中任务
				center.previewExec();
				if (isfromPrjtDef == 1) {
					return "WfInforView";
				}
				return "view";
			}
		} catch (Exception e) {
			sendMessage(MSG.F_SEA, "WfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo());
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
	}

	/**
	 * 处理任务
	 * 
	 * @return
	 * @throws Exception
	 */
	private boolean needSpecifyNext;

	public String wfTaskView() throws Exception {
		try {
			initWorkFlow();
			checkReSub = getTokenKey(); // 防重复
			System.out.println("中央控制器:::" + center);
			center.processExec();
			needSpecifyNext = center.needSpecifyNext();
			// System.out.println(nextSteps.toString());

			return "wfTaskView";
		} catch (Exception e) {
			// sendMessage(MSG.F_SEA,"WfRdView.shtml?wfRd.wfNo="+wfRd.getWfNo());
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			e.printStackTrace();
			return ERROR;
		}
	}

	public String wfTaskViewForFlow() throws Exception {
		try {
			initWorkFlow();
			checkReSub = getTokenKey(); // 防重复
			System.out.println("中央控制器:::" + center);
			center.processExec();
			needSpecifyNext = center.needSpecifyNext();
			// System.out.println(nextSteps.toString());

			return "wfTaskViewForFlow";
		} catch (Exception e) {
			// sendMessage(MSG.F_SEA,"WfRdView.shtml?wfRd.wfNo="+wfRd.getWfNo());
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			e.printStackTrace();
			return ERROR;
		}
	}
	// 查看工作流记录
	public String wfRdView() throws Exception {
		try {
			initWorkFlow();
			checkReSub = getTokenKey(); // 防重复
			center.previewExec();
			// if(isfromPrjtDef==1){
			// return "WfInforView";
			// }
			return "wfRdView";
		} catch (Exception e) {
			sendMessage(MSG.F_SEA, "WfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo());
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 查看文档
	 * 
	 * @return
	 */
	public String wfRdViewWfDoc() {
		try {
			syId = String.valueOf(getUsrSession().getSyId());
			syNm = getUsrSession().getSyNm();
			usrId = String.valueOf(getUsrSession().getId());
			usrNm = getUsrSession().getUsrName();

			wfRd = new WfRdFacade().findById(wfRd);
			String sleDocFileds = "WfDoc.DocType,WfDoc.DocId,WfDoc.TaskId,WfDoc.FileNo,WfDoc.ProjectNo,WfDoc.Status,"
					+ "WfDoc.CreateBy,WfDoc.LastUpd,WfDoc.CateId,WfDoc.FlowId,WfDoc.DeptDocId,WfDoc.WfNo,WfDoc.UriLink,"
					+ "WfDoc.CreateDate,WfDoc.LastUpdDate,WfDoc.DocName,WfDoc.DocVer,"
					+ "WfRdTask.TaskId as TaskId,WfStep.StepName,Usr.UsrName";
			// 查找出流程相关的文档
			// 如果不是归档流程的任务
			if (StringUtils.isEmpty(wfRd.getDocCateId())) {
				uploadType = "ProcFile";
				String WfScheCfgDoc_fields = "DocId,DocName,WfScheCfgDoc.StepId";

				// 查找出当前流程需要多少交付件
				String wfscfSQL = "select WfScheCfgDoc.DocId,WfScheCfgDoc.DocName,WfScheCfgDoc.StepId from WfScheCfgDoc "
						+ "where WfScheCfgDoc.StepId in (select StepId from WfStep where FlowId =  " + wfRd.getFlowId()
						+ ")";
				List<WfScheCfgDoc> wfScheCfgDocs = new WfScheCfgDocFacade().find(wfscfSQL, WfScheCfgDoc_fields);
				wfDocs = new ArrayList<>(wfScheCfgDocs.size());

				String sleDocSQL = "select top 1 " + sleDocFileds + " from WfDoc "
						+ " left join WfRdTask on (WfRdTask.TaskId = WfDoc.TaskId) "
						+ " left join WfStep on(WfRdTask.StepId=WfStep.StepId) " +
						// " left join usr on (WfDoc.CreateBy = usr.id) " +
						" left join ( select id uid, Usr.UsrName FROM Usr WHERE Id in "
						+ "( SELECT ww.CreateBy FROM WfDoc ww WHERE ww.WfNo =ww.WfNo  ) ) Usr on Usr.uid = WfDoc.CreateBy"
						+ " where WfDoc.Status=1 and WfDoc.WfNo='" + wfRd.getWfNo() + "' ";
				for (int i = 0; i < wfScheCfgDocs.size(); i++) {
					String SQL = "";
					SQL = sleDocSQL + " and WfDoc.CateId=" + wfScheCfgDocs.get(i).getDocId() + " order by DocId desc ";
					// System.out.println("sleDocSQLsleDocSQLsleDocSQL:::"+SQL);
					WfDoc doc = new WfDocFacade().findById(SQL, sleDocFileds);

					if (doc == null || doc.getDocId() == null || doc.getDocId().intValue() == 0) {// 如果没有找到已经存在的文档
						doc = new WfDoc();
					}

					// if(wfScheCfgDocs.get(i).getStepId().intValue()<=taskStepId.intValue()){
					// //如果是当前步奏之后要上传的附件九不在页面显示

					// if(wfScheCfgDocs.get(i).getStepId().intValue()==taskStepId.intValue()){
					// doc.setIsCurrntDoc(1);
					// }
					doc.setCateName(wfScheCfgDocs.get(i).getDocName());
					doc.setCateId(wfScheCfgDocs.get(i).getDocId());
					wfDocs.add(doc);
					// }
				}

			} else {
				uploadType = "BaseLib";
				wfDocs = new ArrayList<>();
				String SQL = "select WfScheCfgDoc.DocId,WfScheCfgDoc.DocName from WfScheCfgDoc "
						+ "where WfScheCfgDoc.DocId =" + wfRd.getDocCateId();
				List<WfScheCfgDoc> wfScheCfgDocs = new WfScheCfgDocFacade().find(SQL, "DocId,DocName");
				String subSQL = "select Max(WfDoc.DocId) as DocId from WfDoc " + "where WfDoc.Status>0 and WfDoc.WfNo='"
						+ wfRd.getWfNo() + "' ";
				String docSQL = subSQL + " and WfDoc.CateId=" + wfRd.getDocCateId();
				WfDoc doc = new WfDocFacade().findById(docSQL, "WfDoc.DocId");
				if (doc == null || doc.getDocId() == null || doc.getDocId().intValue() == 0) {
					doc = new WfDoc();
					doc.setCateName(wfScheCfgDocs.get(0).getDocName());
					doc.setCateId(wfScheCfgDocs.get(0).getDocId());
					doc.setIsCurrntDoc(1);
				} else {
					doc = new WfDocFacade().findBy(doc);
					wfDocId = doc.getDocId();// 归档流程 文档ID
				}
				wfDocs.add(doc);
			}

			/**
			 * 查找出额外附件
			 */
			String sqlExtent = "select  " + sleDocFileds + " from WfDoc "
					+ " left join WfRdTask on (WfRdTask.TaskId = WfDoc.TaskId) "
					+ " left join WfStep on(WfRdTask.StepId=WfStep.StepId) " +
					// " left join usr on (WfDoc.CreateBy = usr.id) " +
					"LEFT JOIN ( select id uid, Usr.UsrName FROM Usr WHERE Id in "
					+ "( SELECT ww.CreateBy FROM WfDoc ww WHERE ww.WfNo =ww.WfNo  ) ) Usr on Usr.uid = WfDoc.CreateBy"
					+ " where WfDoc.Status=1 and WfDoc.CateId is null  and WfDoc.WfNo='" + wfRd.getWfNo() + "' ";
			System.out.println("wfdocJson::" + sqlExtent);
			List<WfDoc> extendDocs = new ArrayList<WfDoc>();
			extendDocs = new WfDocFacade().find(sqlExtent, sleDocFileds);
			if (extendDocs != null && extendDocs.size() > 0) {
				for (WfDoc wd : extendDocs) {
					wfDocs.add(wd);
				}
			}
			/**
			 * 查找出额外附件
			 */

			WfDocJson wfDocJson = new WfDocJson();
			wfDocJson.Rows = wfDocs;
			wfDocJson.Total = wfDocs.size();
			System.out.println(JSON.toJSONString(wfDocJson));

			return "wfRdViewWfDoc";
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error("WfRdViewAction Exception", e);
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 查看文档
	 * 
	 * @return
	 */
	public String viewDocCate() {
		try {
			syId = String.valueOf(getUsrSession().getSyId());
			syNm = getUsrSession().getSyNm();
			usrId = String.valueOf(getUsrSession().getId());
			usrNm = getUsrSession().getUsrName();

			// TODO
			currentStep.setStepId(taskStepId);
			currentStep = new WfStepFacade().findById(currentStep);

			wfRd = new WfRdFacade().findById(wfRd);
			String sleDocFileds = "WfDoc.DocType,WfDoc.DocId,WfDoc.TaskId,WfDoc.FileNo,WfDoc.ProjectNo,WfDoc.Status,"
					+ "WfDoc.CreateBy,WfDoc.LastUpd,WfDoc.CateId,WfDoc.FlowId,WfDoc.DeptDocId,WfDoc.WfNo,WfDoc.UriLink,"
					+ "WfDoc.CreateDate,WfDoc.LastUpdDate,WfDoc.DocName,WfDoc.DocVer,"
					+ "WfRdTask.TaskId as TaskId,WfStep.StepName,Usr.UsrName";
			// 查找出流程相关的文档
			// 如果不是归档流程的任务
			if (StringUtils.isEmpty(wfRd.getDocCateId())) {
				uploadType = "ProcFile";
				String WfScheCfgDoc_fields = "DocId,DocName,WfScheCfgDoc.StepId,WfStep.Sort as Sort";

				// 查找出当前流程需要多少交付件
				String wfscfSQL = "select WfScheCfgDoc.DocId,WfScheCfgDoc.DocName,WfScheCfgDoc.StepId,WfStep.Sort as Sort from WfScheCfgDoc left join WfStep on WfScheCfgDoc.StepId = WfStep.StepId "
						+ "where WfScheCfgDoc.StepId in (select StepId from WfStep where FlowId =  " + wfRd.getFlowId()
						+ ")";
				List<WfScheCfgDoc> wfScheCfgDocs = new WfScheCfgDocFacade().find(wfscfSQL, WfScheCfgDoc_fields);
				wfDocs = new ArrayList<>(wfScheCfgDocs.size());

				String sleDocSQL = "select top 1 " + sleDocFileds + " from WfDoc "
						+ " left join WfRdTask on (WfRdTask.TaskId = WfDoc.TaskId) "
						+ " left join WfStep on(WfRdTask.StepId=WfStep.StepId) "
						+ " left join usr on (WfDoc.CreateBy = usr.id) " + " where WfDoc.Status=1 and WfDoc.WfNo='"
						+ wfRd.getWfNo() + "' ";

				for (int i = 0; i < wfScheCfgDocs.size(); i++) {
					String SQL = "";
					SQL = sleDocSQL + " and WfDoc.CateId=" + wfScheCfgDocs.get(i).getDocId() + " order by DocId desc ";
					WfDoc doc = new WfDocFacade().findById(SQL, sleDocFileds);

					if (doc == null || doc.getDocId() == null || doc.getDocId().intValue() == 0) {// 如果没有找到已经存在的文档
						doc = new WfDoc();
					}

					if (wfScheCfgDocs.get(i).getSort().intValue() <= currentStep.getSort().intValue()) { // 如果是当前步奏之后要上传的附件九不在页面显示

						if (wfScheCfgDocs.get(i).getSort().intValue() == currentStep.getSort().intValue()) {
							doc.setIsCurrntDoc(1);
						}
						doc.setCateName(wfScheCfgDocs.get(i).getDocName());
						doc.setCateId(wfScheCfgDocs.get(i).getDocId());
						wfDocs.add(doc);
					}
				}

			} else {
				uploadType = "BaseLib";
				wfDocs = new ArrayList<>();
				String SQL = "select WfScheCfgDoc.DocId,WfScheCfgDoc.DocName from WfScheCfgDoc "
						+ "where WfScheCfgDoc.DocId =" + wfRd.getDocCateId();
				List<WfScheCfgDoc> wfScheCfgDocs = new WfScheCfgDocFacade().find(SQL, "DocId,DocName");
				String subSQL = "select Max(WfDoc.DocId) as DocId from WfDoc " + "where WfDoc.Status>0 and WfDoc.WfNo='"
						+ wfRd.getWfNo() + "' ";
				String docSQL = subSQL + " and WfDoc.CateId=" + wfRd.getDocCateId();
				WfDoc doc = new WfDocFacade().findById(docSQL, "WfDoc.DocId");
				if (doc == null || doc.getDocId() == null || doc.getDocId().intValue() == 0) {
					doc = new WfDoc();
					doc.setCateName(wfScheCfgDocs.get(0).getDocName());
					doc.setCateId(wfScheCfgDocs.get(0).getDocId());
					doc.setIsCurrntDoc(1);
				} else {
					doc = new WfDocFacade().findBy(doc);
					wfDocId = doc.getDocId();// 归档流程 文档ID
				}
				wfDocs.add(doc);
			}

			/**
			 * 查找出额外附件
			 */
			String sqlExtent = "select  " + sleDocFileds + " from WfDoc "
					+ " left join WfRdTask on (WfRdTask.TaskId = WfDoc.TaskId) "
					+ " left join WfStep on(WfRdTask.StepId=WfStep.StepId) "
					+ " left join usr on (WfDoc.CreateBy = usr.id) "
					+ " where WfDoc.Status=1 and WfDoc.CateId is null  and WfDoc.WfNo='" + wfRd.getWfNo() + "' ";

			List<WfDoc> extendDocs = new ArrayList<WfDoc>();
			extendDocs = new WfDocFacade().find(sqlExtent, sleDocFileds);
			if (extendDocs != null && extendDocs.size() > 0) {
				for (WfDoc wd : extendDocs) {
					wfDocs.add(wd);
				}
			}
			/**
			 * 查找出额外附件
			 */

			WfDocJson wfDocJson = new WfDocJson();
			wfDocJson.Rows = wfDocs;
			wfDocJson.Total = wfDocs.size();
			System.out.println("文档数据");
			System.out.println(JSON.toJSONString(wfDocJson));

			return "wfDocCateView";
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error("WfRdViewAction Exception", e);
			e.printStackTrace();
			return ERROR;
		}
	}

	// 预览
	public String view() throws Exception {
		try {
			initWorkFlow();
			center.previewExec();
		} catch (Exception e) {
			sendMessage(MSG.F_SEA, "WfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo());
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}

	// 进下一步转交页面
	public String nextStepPage() throws Exception {
		try {

			// HttpServletResponse response =
			// (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
			// response.setContentType("text/html;charset=utf-8");

			if (!isfromeAndroid) {
				if (!checkReSub()) {
					this.sendMessage("请勿重复提交任务！", "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo()
							+ "&currentTaskId=" + currentTaskId + "&taskStepId=" + taskStepId);
					return ERROR;
				}

				checkReSub = getTokenKey(); // 防重复
			}

			// 防止多浏览器同时重复提交相同任务
			currentTask = new WfRdTaskFacade().findById(currentTask);
			if (currentTask.getStatus() == MSG.OWFTASK_STATUS_2) {
				this.sendMessage("请勿重复提交任务！", "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo()
						+ "&currentTaskId=" + currentTaskId + "&taskStepId=" + taskStepId);
				return ERROR;
			}

			initWorkFlow();
			center.saveJob();

			// currentTask = new WfRdTaskFacade().findById(currentTask);
			if (currentTask.getTaskType() == MSG.OWFTASK_TYPE_1 && currentTask.getWaitAuxiliary() == 1) { // 主办,且须等待协办时
				String sql = "select count(*) as amount from WfRdTask where WfRdTask.preTaskId="
						+ currentTask.getPreTaskId() + " and WfRdTask.taskType=" + MSG.OWFTASK_TYPE_2
						+ " and WfRdTask.status<=" + MSG.OWFTASK_STATUS_1;
				int count = new WfRdTaskFacade().amount(sql);
				if (count > 0) {
					sendMessage("不能转交，请先让各协办人完成任务后再转交", "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo()
							+ "&currentTaskId=" + currentTaskId + "&taskStepId=" + taskStepId);
					return ERROR;
				}
			}

			// 如果是文档 归档流程 或者 更新流程的最后一步需要更改WfDoc 的状态
			wfRd = new WfRd();
			wfRd.setWfNo(currentTask.getWfNo());
			wfRd = new WfRdFacade().findById(wfRd);
			if (wfRd.getFlowId() == 39) {// 如果是归档流程
				WfStep step = new WfStep();
				step.setStepId(currentTask.getStepId());
				step = new WfStepFacade().findById(step);
				if (step.getIsLastStep() == 1) {
					// WfDoc doc = new WfDoc();

					String subSQL = "select Max(WfDoc.DocId) as DocId from WfDoc "
							+ "where WfDoc.Status>0 and WfDoc.WfNo='" + wfRd.getWfNo() + "' ";
					String docSQL = subSQL + " and WfDoc.CateId=" + wfRd.getDocCateId();
					WfDoc doc = new WfDocFacade().findById(docSQL, "WfDoc.DocId");

					// doc.setDocId(wfDocId);
					doc.setStatus(MSG.WFDOC_STATUS_DELETED_3);
					new WfDocFacade().update(doc);
				}
			}
			if (wfRd.getFlowId() == 42) {// 如果是文档更新流程
				WfStep step = new WfStep();
				step.setStepId(currentTask.getStepId());
				step = new WfStepFacade().findById(step);
				if (step.getIsLastStep() == 1) {
					// WfDoc doc = new WfDoc();
					// doc.setDocId(wfDocId);
					String subSQL = "select Max(WfDoc.DocId) as DocId from WfDoc "
							+ "where WfDoc.Status>0 and WfDoc.WfNo='" + wfRd.getWfNo() + "' ";
					String docSQL = subSQL + " and WfDoc.CateId=" + wfRd.getDocCateId();
					WfDoc doc = new WfDocFacade().findById(docSQL, "WfDoc.DocId");

					doc.setStatus(MSG.WFDOC_STATUS_DELETED_5);
					new WfDocFacade().update(doc);
				}
			}
			/*
			 * else if(wfRd.getFlowId() == 21) { WfStep step = new WfStep();
			 * step.setStepId(currentTask.getStepId()); step = new
			 * WfStepFacade().findById(step); if(step.getIsLastStep() == 1) {
			 * WfQues wfQues = new WfQues(); wfQues.setQuesId(wfRd.getQuesId());
			 * WfQuesFacade wf = new WfQuesFacade(); wfQues = wf.findBy(wfQues);
			 * wfQues.setStatus(MSG.WFQUES_STATUS_30); wf.update(wfQues); } }
			 */

			boolean flag = center.nextStepPage();
			if (flag) {
				return "nextStepPage";
			} else {
				isReloadGrid = "true";

				// System.out.println("WfRdView!wfTaskView.shtml?wfRd.wfNo="+wfRd.getWfNo()+"&currentTaskId="+currentTaskId+"&taskStepId="+taskStepId);
				this.sendMessage("任务完成", "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo() + "&currentTaskId="
						+ currentTaskId + "&taskStepId=" + taskStepId);

				// WfRdView!wfTaskView.shtml?wfRd.wfNo=B0914100021&currentTaskId=3530&taskStepId=204
				// setMsg("任务完成");
				return SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.sendMessage(MSG.F_SEA, "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo() + "&currentTaskId="
					+ currentTaskId + "&taskStepId=" + taskStepId);
			Logger.getLogger(this.getClass()).error("WfRdViewAction nextStepPage Exception", e);
			return ERROR;
		}
	}

	// 转交任务
	public String sendTask() throws Exception {
		try {

			if (!isfromeAndroid) {
				if (!checkReSub()) {
					// WfRdView!wfTaskView.shtml?wfRd.wfNo=B0914100021&currentTaskId=3531&taskStepId=205
					this.sendMessage("请勿重复提交任务！", "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo()
							+ "&currentTaskId=" + currentTaskId + "&taskStepId=" + taskStepId);
					return ERROR;
				}
			}

			// 防止多浏览器同时重复提交相同任务
			currentTask = new WfRdTaskFacade().findById(currentTask);
			if (currentTask.getStatus() == MSG.OWFTASK_STATUS_2) {
				this.sendMessage("请勿重复提交任务！", "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo()
						+ "&currentTaskId=" + currentTaskId + "&taskStepId=" + taskStepId);
				return ERROR;
			}

			initWorkFlow();
			boolean flag = center.sendTask();
			if (flag) {
				isReloadGrid = "true";
				// System.out.println("WfRdView!wfTaskView.shtml?wfRd.wfNo="+wfRd.getWfNo()+"&currentTaskId="+currentTaskId+"&taskStepId="+taskStepId);
				this.sendMessage("任务完成成功", "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo() + "&currentTaskId="
						+ currentTaskId + "&taskStepId=" + taskStepId);
			} else {
				this.sendMessage("有下一步骤,请勾选要转交步骤", "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo()
						+ "&currentTaskId=" + currentTaskId + "&taskStepId=" + taskStepId);
				return ERROR;
			}
		} catch (Exception e) {
			this.sendMessage(MSG.F_SAV, "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo() + "&currentTaskId="
					+ currentTaskId + "&taskStepId=" + taskStepId);
			Logger.getLogger(this.getClass()).error("WfRdViewAction sentTask Exception", e);
			return ERROR;
		}
		return SUCCESS;
	}

	public String saveAndSendTask() {
		AjaxJson ajaxJson = new AjaxJson();
		try {

			if (!checkReSub()) {
				this.sendMessage("请勿重复提交任务！", "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo()
						+ "&currentTaskId=" + currentTaskId + "&taskStepId=" + taskStepId);
				if(isForFlow == 0){
					return ERROR;
				}else{
					ajaxJson.setSuccess(false);
					ajaxJson.setMsg(this.getMsg());
					this.setJson(JSON.toJSONString(ajaxJson));
					return PGJSON;
				}
			}

			checkReSub = getTokenKey(); // 防重复

			// 防止多浏览器同时重复提交相同任务
			currentTask = new WfRdTaskFacade().findById(currentTask);
			if (currentTask.getStatus() == MSG.OWFTASK_STATUS_2) {
				this.sendMessage("请勿重复提交任务！", "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo()
						+ "&currentTaskId=" + currentTaskId + "&taskStepId=" + taskStepId);
				if(isForFlow == 0){
					return ERROR;
				}else{
					ajaxJson.setSuccess(false);
					ajaxJson.setMsg(this.getMsg());
					this.setJson(JSON.toJSONString(ajaxJson));
					return PGJSON;
				}
			}

			String msgsString = "任务完成";
			initWorkFlow();
			center.saveJob();
			// currentTask = new WfRdTaskFacade().findById(currentTask);
			if (currentTask.getTaskType() == MSG.OWFTASK_TYPE_1) { // 主办,且须等待协办时
				String sql = "select count(*) as amount from WfRdTask where WfRdTask.preTaskId="
						+ currentTask.getPreTaskId() + " and WfRdTask.taskType=" + MSG.OWFTASK_TYPE_2
						+ " and WfRdTask.status<=" + MSG.OWFTASK_STATUS_1;
				int count = new WfRdTaskFacade().amount(sql);
				if (count > 0) {
					sendMessage("不能转交，请先让各协办人完成任务后再转交", "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo()
							+ "&currentTaskId=" + currentTaskId + "&taskStepId=" + taskStepId);
					if(isForFlow == 0){
						return ERROR;
					}else{
						ajaxJson.setSuccess(false);
						ajaxJson.setMsg(this.getMsg());
						this.setJson(JSON.toJSONString(ajaxJson));
						return PGJSON;
					}
				}
			}

			// 如果是文档 归档流程 或者 更新流程的最后一步需要更改WfDoc 的状态
			// wfRd = new WfRd();
			wfRd.setWfNo(currentTask.getWfNo());
			wfRd = new WfRdFacade().findById(wfRd);
			WfCfg wcfg = new WfCfg();
			wcfg.setFlowName("归档流程");
			WfCfg wcgdlc = new WfCfgFacade().findBy(wcfg);
			// WfCfg wcfg1 = new WfCfg();
			wcfg.setFlowName("翻单变更流程");
			WfCfg wcfdbg = new WfCfgFacade().findBy(wcfg);
			// WfCfg wcfg2 = new WfCfg();
			wcfg.setFlowName("问题转风险评审");
			WfCfg wcwtzfxsp = new WfCfgFacade().findBy(wcfg);
			// 材料认证部的“新物料认证”、“替代物料认证”、“定制物料认证”流程着
			wcfg.setFlowName("新物料认证");
			WfCfg xinwuliao = new WfCfgFacade().findBy(wcfg);
			wcfg.setFlowName("替代物料认证");
			WfCfg tedaiwuliao = new WfCfgFacade().findBy(wcfg);
			wcfg.setFlowName("定制物料认证");
			WfCfg dingzhiwuliao = new WfCfgFacade().findBy(wcfg);
			// SAP主数据维护流程
			wcfg.setFlowName("SAP主数据维护流程");
			WfCfg sapzhushujuweihu = new WfCfgFacade().findBy(wcfg);
			System.out
					.println(wcgdlc.getFlowId() + "~~~~~" + wcfdbg.getFlowId() + "~~~~~~~~~~" + wcwtzfxsp.getFlowId());

			// ECN维护流程
			wcfg.setFlowName("ECN维护流程");
			WfCfg ecnweihu = new WfCfgFacade().findBy(wcfg);
			// BOM新增维护流程
			wcfg.setFlowName("BOM新增维护流程");
			WfCfg bomaddweihu = new WfCfgFacade().findBy(wcfg);
			// 试产BOM变更维护
			wcfg.setFlowName("试产BOM变更维护");
			WfCfg bomchangeweihu = new WfCfgFacade().findBy(wcfg);
			// DCC其他变更流程
			wcfg.setFlowName("DCC其他变更流程");
			WfCfg dccotherchange = new WfCfgFacade().findBy(wcfg);
			String nexttTask = "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo() + "&currentTaskId="
					+ currentTaskId + "&taskStepId=" + taskStepId;
			if (wfRd.getFlowId() == wcgdlc.getFlowId() || wfRd.getFlowId() == wcfdbg.getFlowId()
					|| wfRd.getFlowId() == wcwtzfxsp.getFlowId()) {

				/*
				 * if (wfRd.getFlowId() == wcgdlc.getFlowId()) {// 如果是归档流程
				 * WfStep step = new WfStep();
				 * step.setStepId(currentTask.getStepId()); step = new
				 * WfStepFacade().findById(step); if (step.getIsLastStep() == 1)
				 * { String subSQL =
				 * "select Max(WfDoc.DocId) as DocId from WfDoc " +
				 * "where WfDoc.Status>0 and WfDoc.WfNo='" + wfRd.getWfNo() +
				 * "' "; String docSQL = subSQL + " and WfDoc.CateId=" +
				 * wfRd.getDocCateId(); WfDoc doc = new
				 * WfDocFacade().findById(docSQL, "WfDoc.DocId");
				 * doc.setStatus(MSG.WFDOC_STATUS_DELETED_3); new
				 * WfDocFacade().update(doc); center.nextStepPage();
				 * isReloadGrid = "true"; }else { center.nextStepPage();
				 * isReloadGrid = "true"; center.sendTask();
				 * 
				 * } }
				 */
				/*
				 * if (wfRd.getFlowId() == wcfdbg.getFlowId()) {// 如果是文档更新流程
				 * WfStep step = new WfStep();
				 * step.setStepId(currentTask.getStepId()); step = new
				 * WfStepFacade().findById(step); if (step.getIsLastStep() == 1)
				 * { String subSQL =
				 * "select Max(WfDoc.DocId) as DocId from WfDoc " +
				 * "where WfDoc.Status>0 and WfDoc.WfNo='" + wfRd.getWfNo() +
				 * "' "; String docSQL = subSQL + " and WfDoc.CateId=" +
				 * wfRd.getDocCateId(); WfDoc doc = new
				 * WfDocFacade().findById(docSQL, "WfDoc.DocId");
				 * doc.setStatus(MSG.WFDOC_STATUS_DELETED_5); new
				 * WfDocFacade().update(doc); center.nextStepPage();
				 * isReloadGrid = "true"; }else { center.nextStepPage();
				 * isReloadGrid = "true"; center.sendTask();
				 * 
				 * } }
				 */
				// 如果是风险评估流程的最后一步，需要修改风险的状态
				if (wfRd.getFlowId() == wcwtzfxsp.getFlowId()) {
					WfStep step = new WfStep();
					step.setStepId(currentTask.getStepId());
					step = new WfStepFacade().findById(step);
					if (step.getIsLastStep() == 1) {
						String riskSQL = "UPDATE WfRisk SET WfRisk.Status = " + MSG.WFRISK_STATUS_1
								+ "where WfRisk.PrjtNo = '" + wfRd.getProjectNo() + "' and WfRisk.WfNo='"
								+ wfRd.getWfNo() + "' ";
						// WfRisk wRisk = new WfRiskFacade().findById(subSQL,
						// "WfRisk.Status");
						// wRisk.setStatus(MSG.WFRISK_STATUS_1);
						new WfRiskFacade().update(riskSQL);
						center.nextStepPage();
						isReloadGrid = "true";
					} else {
						center.nextStepPage();
						isReloadGrid = "true";
						center.sendTask();

					}
				}
				// 材料认证部的“新物料认证”、“替代物料认证”、“定制物料认证”流程结束直接新建DCC其他变更流程
			} else if (wfRd.getFlowId() == xinwuliao.getFlowId() || wfRd.getFlowId() == tedaiwuliao.getFlowId()
					|| wfRd.getFlowId() == dingzhiwuliao.getFlowId()) {
				center.nextStepPage();
				isReloadGrid = "true";
				WfStep step = new WfStep();
				step.setStepId(currentTask.getStepId());
				step = new WfStepFacade().findById(step);
				if (step.getIsLastStep() != 1) {
					center.sendTask();

				} else {
					if (currentTask.getTaskType() == MSG.OWFTASK_TYPE_1) { // 最后一步只有主办人完成后才新建DCC其他变更流程
						nexttTask = "WfRd!add.shtml?wfRd.flowId=54";
					}
				}
				// SAP主数据维护流程最后一步主办人完成后若料号申请单包含TP、LCM物料给材料认证部相关用户发送编码完成邮件通知
			} else if (wfRd.getFlowId() == sapzhushujuweihu.getFlowId()) {
				center.nextStepPage();
				isReloadGrid = "true";
				WfStep step = new WfStep();
				step.setStepId(currentTask.getStepId());
				step = new WfStepFacade().findById(step);
				if (step.getIsLastStep() != 1) {
					center.sendTask();
					if (currentTask.getTaskType() == MSG.OWFTASK_TYPE_1) { // 主办人完成后发邮件给发起人
						String url = "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo() + "&currentTaskId="
								+ currentTask.getTaskId() + "&taskStepId=" + taskStepId;
						String webUrl = getSysWebUrl() + "/" + url;
						WfCfg wfCfglist = new WfCfg();
						wfCfglist.setFlowId(wfRd.getFlowId());
						WfCfg wfCfglists = new WfCfgFacade().findById(wfCfglist);
						String title = "工作流《 " + wfCfglists.getFlowName() + "》流程的任务阅知通知";
						String content = "尊敬的同事，您好：" + "<p>您有一条《" + wfRd.getWfName() + "》流程的任务阅知通知。" + "<p>工作流编号为："
								+ wfRd.getWfNo() + "，工作流名称：" + wfRd.getWfName() + "，任务名称：" + step.getStepName()
								+ "，请尽快办理!" + "<p>任务办理链接地址 ： <a href=" + webUrl + ">" + webUrl + "</a>";
						String userList = wfRd.getCreateBy().toString();
						if(userList.equals("15129")){
							userList="";
						}
						SendMailUtil.sendMailByUsrId(title, content, userList);
					}
				} else {
					if (currentTask.getTaskType() == MSG.OWFTASK_TYPE_1) { // 最后一步主办人完成后若料号申请单包含TP、LCM物料给材料认证部相关用户发送编码完成邮件通知
						String countItemGpSql = "select count(WfId) amount from WfRdField where WfNo = '"
								+ wfRd.getWfNo() + "' and FieldId = 990 "
								+ "and FieldText in ('1600','1601','1602','1603','1660','1661','1662','1663','1664')";
						int countItemGp = new WfRdFieldFacade().amount(countItemGpSql);
						if (countItemGp > 0) {
							String title = wfRd.getWfName() + "编码已完成";
							String content = "尊敬的同事，您好：<p>工作流编号：" + wfRd.getWfNo() + "，工作流名称：" + wfRd.getWfName()
									+ "，编码已完成。";
							List<Usr> usrList = new ArrayList<Usr>();
							int[] sendMaterialAuthUserIDS = { 15312, 15146, 15275, 15204, 46675 }; // 郭晓琴,15312;杨志影,15146;李龙1,15275;谢李金,15204;曾秋香,46675
							for (int UserId : sendMaterialAuthUserIDS) {
								Usr u = new Usr();
								u.setId(UserId);
								usrList.add(u);
							}
							WFUtil.sendMailByIT(title, content, usrList);
						}

						// 最后一步完成时发邮件给发起人
						String url = "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo() + "&currentTaskId="
								+ currentTask.getTaskId() + "&taskStepId=" + taskStepId;
						String webUrl = getSysWebUrl() + "/" + url;
						WfCfg wfCfglist = new WfCfg();
						wfCfglist.setFlowId(wfRd.getFlowId());
						WfCfg wfCfglists = new WfCfgFacade().findById(wfCfglist);
						String title = "工作流《 " + wfCfglists.getFlowName() + "》流程的任务阅知通知";
						String content = "尊敬的同事，您好：" + "<p>您有一条《" + wfRd.getWfName() + "》已审批完成的任务阅知通知。" + "<p>工作流编号为："
								+ wfRd.getWfNo() + "，工作流名称：" + wfRd.getWfName() + "，任务名称：" + step.getStepName()
								+ "，请尽快办理!" + "<p>任务办理链接地址 ： <a href=" + webUrl + ">" + webUrl + "</a>";
						String userList = wfRd.getCreateBy().toString();
						if(userList.equals("15129")){
							userList="";
						}
						SendMailUtil.sendMailByUsrId(title, content, userList);
					}
				}
			} else if (wfRd.getFlowId() == ecnweihu.getFlowId() || wfRd.getFlowId() == bomaddweihu.getFlowId()
					|| wfRd.getFlowId() == bomchangeweihu.getFlowId()
					|| wfRd.getFlowId() == dccotherchange.getFlowId()) {
				center.nextStepPage();
				isReloadGrid = "true";
				WfStep step = new WfStep();
				step.setStepId(currentTask.getStepId());
				step = new WfStepFacade().findById(step);
				if (step.getIsLastStep() != 1) {
					center.sendTask();
				} else {
					// 最后一步主办人完成时发邮件给流程发起人
					if (currentTask.getTaskType() == MSG.OWFTASK_TYPE_1) {
						String url = "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo() + "&currentTaskId="
								+ currentTask.getTaskId() + "&taskStepId=" + taskStepId;
						String webUrl = getSysWebUrl() + "/" + url;
						WfCfg wfCfglist = new WfCfg();
						wfCfglist.setFlowId(wfRd.getFlowId());
						WfCfg wfCfglists = new WfCfgFacade().findById(wfCfglist);
						String title = "工作流《 " + wfCfglists.getFlowName() + "》流程的任务阅知通知";
						String content = "尊敬的同事，您好：" + "<p>您有一条《" + wfRd.getWfName() + "》已审批完成的任务阅知通知。" + "<p>工作流编号为："
								+ wfRd.getWfNo() + "，工作流名称：" + wfRd.getWfName() + "，任务名称：" + step.getStepName()
								+ "，请尽快办理!" + "<p>任务办理链接地址 ： <a href=" + webUrl + ">" + webUrl + "</a>";
						String userList = wfRd.getCreateBy().toString();
						if(userList.equals("15129")){
							userList="";
						}
						SendMailUtil.sendMailByUsrId(title, content, userList);
					}
				}
			} else {
				center.nextStepPage();
				isReloadGrid = "true";
				WfStep step = new WfStep();
				step.setStepId(currentTask.getStepId());
				step = new WfStepFacade().findById(step);
				if (step.getIsLastStep() != 1) {
					/* if(isNeedSpecifyNext()){ */
					center.sendTask();
					/*
					 * if (center.sendTask()==false) { msgsString =
					 * "主办人没有填写，请填写主办人再转交下一步！"; }
					 */

					/* } */
				}

			}

			this.sendMessage(msgsString, nexttTask);
			if(isForFlow == 0){
				return SUCCESS;
			}else{
				ajaxJson.setSuccess(true);
				ajaxJson.setMsg(this.getMsg());
				this.setJson(JSON.toJSONString(ajaxJson));
				return PGJSON;
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.sendMessage(MSG.F_SEA, "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo() + "&currentTaskId="
					+ currentTaskId + "&taskStepId=" + taskStepId);
			Logger.getLogger(this.getClass()).error("WfRdViewAction nextStepPage Exception", e);
			if(isForFlow == 0){
				return ERROR;
			}else{
				ajaxJson.setSuccess(false);
				ajaxJson.setMsg(this.getMsg());
				this.setJson(JSON.toJSONString(ajaxJson));
				return PGJSON;
			}
		}
	}

	public String saveAndSendTaskForNextPage() {
		AjaxJson ajaxJson = new AjaxJson();
		try {

			/*
			 * if(!checkReSub()){ this.sendMessage("请勿重复提交任务！",
			 * "WfRdView!wfTaskView.shtml?wfRd.wfNo="+wfRd.getWfNo()+
			 * "&currentTaskId="+currentTaskId+"&taskStepId="+taskStepId);
			 * return ERROR; }
			 */
			// http://localhost:8080/zrprjt/WfRdView!wfTaskView.shtml?wfRd.wfNo=B4315200020&currentTaskId=5112&taskStepId=349

			// 防止多浏览器同时重复提交相同任务
			currentTask = new WfRdTaskFacade().findById(currentTask);
			if (currentTask.getStatus() == MSG.OWFTASK_STATUS_2) {
				this.sendMessage("请勿重复提交任务！", "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo()
						+ "&currentTaskId=" + currentTaskId + "&taskStepId=" + taskStepId);
				if(isForFlow == 0){
					return ERROR;
				}else{
					ajaxJson.setSuccess(false);
					ajaxJson.setMsg(this.getMsg());
					this.setJson(JSON.toJSONString(ajaxJson));
					return PGJSON;
				}
			}

			String nexttTask = "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo() + "&currentTaskId="
					+ currentTaskId + "&taskStepId=" + taskStepId;
			// checkReSub=getTokenKey(); //防重复
			String msgsString = "任务完成";
			initWorkFlow();
			center.saveJob();
			currentTask = new WfRdTaskFacade().findById(currentTask);
			if (currentTask.getTaskType() == MSG.OWFTASK_TYPE_1 && currentTask.getWaitAuxiliary() == 1) { // 主办,且须等待协办时
				String sql = "select count(*) as amount from WfRdTask where WfRdTask.preTaskId="
						+ currentTask.getPreTaskId() + " and WfRdTask.taskType=" + MSG.OWFTASK_TYPE_2
						+ " and WfRdTask.status<=" + MSG.OWFTASK_STATUS_1;
				int count = new WfRdTaskFacade().amount(sql);
				if (count > 0) {
					sendMessage("不能转交，请先让各协办人完成任务后再转交", "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo()
							+ "&currentTaskId=" + currentTaskId + "&taskStepId=" + taskStepId);
					if(isForFlow == 0){
						return ERROR;
					}else{
						ajaxJson.setSuccess(false);
						ajaxJson.setMsg(this.getMsg());
						this.setJson(JSON.toJSONString(ajaxJson));
						return PGJSON;
					}
				}
			}
			// center.sendTask();
			msgsString = "没有选择下一步!</br>流程会直接更新为'已完成'状态,如需要修改状态请点击'编辑'按钮！";
			currentTask.setStatus(MSG.OWFTASK_STATUS_2);
			currentTask.setEndDate(new Date());
			new WfRdTaskFacade().update(currentTask);
			String sql = "update WfRd set status=" + MSG.OWFRD_STATUS_2
					+ ",FactEDate=getdate() where status >=1  and WfNo='" + wfRd.getWfNo() + "'";
			new WfRdFacade().update(sql);
			WFUtil.updateProjtTask(wfRd.getProjectNo(), wfRd.getScheId(), user.getId(), null, new Date(),
					MSG.PROJTASK_STATUS_3);
			center.nextStepPage();
			this.sendMessage(msgsString, nexttTask);
			if(isForFlow == 0){
				return SUCCESS;
			}else{
				ajaxJson.setSuccess(true);
				ajaxJson.setMsg(this.getMsg());
				this.setJson(JSON.toJSONString(ajaxJson));
				return PGJSON;
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.sendMessage(MSG.F_SEA, "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo() + "&currentTaskId="
					+ currentTaskId + "&taskStepId=" + taskStepId);
			Logger.getLogger(this.getClass()).error("WfRdViewAction nextStepPage Exception", e);
			if(isForFlow == 0){
				return ERROR;
			}else{
				ajaxJson.setSuccess(false);
				ajaxJson.setMsg(this.getMsg());
				this.setJson(JSON.toJSONString(ajaxJson));
				return PGJSON;
			}
		}
	}

	// 设置代办人"./WfRdView!wfRdView.shtml?wfRd.wfNo="
	public String setAgentBy() throws Exception {
		AjaxJson ajaxJson = new AjaxJson();
		String url = "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo() + "&currentTaskId="
				+ currentTask.getTaskId() + "&taskStepId=" + taskStepId;
		;
		try {
			if (WFUtil.isNull(agentBy)) {
				this.sendMessage("任务代办人不能为空", url);
				if(isForFlow == 0){
					return ERROR;
				}else{
					ajaxJson.setSuccess(false);
					ajaxJson.setMsg(this.getMsg());
					this.setJson(JSON.toJSONString(ajaxJson));
					return PGJSON;
				}
			}
			initWorkFlow();
			currentTask.setStepId(taskStepId);
			currentTask.setAcceptBy(agentBy);
			currentTask.setAcceptDate(new Date());
			new WfRdTaskFacade().update(currentTask);
			// TODO 邮件
			String webUrl = getSysWebUrl() + "/" + url;
			WfStep step = new WfStepFacade().findById(
					"select WfStep.StepName from WfStep where StepId = " + currentTask.getStepId(), "WfStep.StepName");
			String title = "工作流《 " + wfRd.getWfName() + "》的任务《" + step.getStepName() + "》代办通知";
			String content = "尊敬的同事，您好：" + "<p>您有一条《" + wfRd.getWfName() + "》流程的代办任务。" + "<p>工作流编号为：" + wfRd.getWfNo()
					+ "，工作流名称：" + wfRd.getWfName() + "，任务名称：" + step.getStepName() + "，请尽快办理!"
					+ "<p>任务办理链接地址 ： <a href=" + webUrl + ">" + webUrl + "</a>";
			List<Usr> userList = new ArrayList<Usr>();
			Usr u = new Usr();
			u.setId(agentBy);
			userList.add(u);
			WFUtil.sendMailByIT(title, content, userList);
			url = "WfRdView!wfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo();
			this.sendMessage("设置任务代办人成功", url);
		} catch (Exception e) {
			this.sendMessage(MSG.F_SAV, url);
			Logger.getLogger(this.getClass()).error("WfRdViewAction sentTask Exception", e);
			e.printStackTrace();
			if(isForFlow == 0){
				return ERROR;
			}else{
				ajaxJson.setSuccess(false);
				ajaxJson.setMsg(this.getMsg());
				this.setJson(JSON.toJSONString(ajaxJson));
				return PGJSON;
			}
		}
		if(isForFlow == 0){
			return SUCCESS;
		}else{
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg(this.getMsg());
			this.setJson(JSON.toJSONString(ajaxJson));
			return PGJSON;
		}
	}

	/**
	 * 设置代办人界面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String setAgentByUI() throws Exception {
		return "setAgentByUI";
	}

	// 接收任务
	public String accept() throws Exception {
		try {
			initWorkFlow();
			checkReSub = getTokenKey(); // 防重复
			center.acceptTask();
			center.processExec();
		} catch (Exception e) {
			System.out.println();
			// this.sendMessage(MSG.F_SEA,"WfRdView!wfTaskView.shtml?wfRd.wfNo="+wfRd.getWfNo()+"&currentTaskId="+currentTaskId+"&taskStepId="+taskStepId);
			Logger.getLogger(this.getClass()).error("WfRdViewAction accept Exception", e);
			return ERROR;
		}

		this.sendMessage("已接收", "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo() + "&currentTaskId="
				+ currentTaskId + "&taskStepId=" + taskStepId);
		return SUCCESS;
		// return "job";
	}

	// 收回任务
	public String backJob() throws Exception {
		try {
			initWorkFlow();
			String info = center.rejectTask(MSG.OWFTASK_STATUS_5);
			if (info != null) {
				this.sendMessage(info, "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo() + "&currentTaskId="
						+ currentTaskId + "&taskStepId=" + taskStepId);
				return ERROR;
			}
			this.sendMessage("任务收回来了", "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo() + "&currentTaskId="
					+ currentTaskId + "&taskStepId=" + taskStepId);
		} catch (Exception e) {
			this.sendMessage(MSG.F_SEA, "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo() + "&currentTaskId="
					+ currentTaskId + "&taskStepId=" + taskStepId);
			Logger.getLogger(this.getClass()).error("WfRdViewAction backJob Exception", e);
			return ERROR;
		}
		return SUCCESS;
	}

	// 退回任务
	public String reject() throws Exception {
		AjaxJson ajaxJson = new AjaxJson();
		try {
			isReloadGrid = "true";
			System.out.println(currentTask.getRemark());
			System.out.println(currentTask.getTaskId());
			WfRdTask saveRemark = new WfRdTask();
			saveRemark.setRemark(currentTask.getRemark());
			saveRemark.setTaskId(currentTask.getTaskId());
			new WfRdTaskFacade().update(saveRemark);
			initWorkFlow();
			center.saveJob();
			String info = center.rejectTask(MSG.OWFTASK_STATUS_6);
			if (info != null) {
				this.sendMessage(info, "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo() + "&currentTaskId="
						+ currentTaskId + "&taskStepId=" + taskStepId);
				if(isForFlow == 0){
					return ERROR;
				}else{
					ajaxJson.setSuccess(false);
					ajaxJson.setMsg(this.getMsg());
					this.setJson(JSON.toJSONString(ajaxJson));
					return PGJSON;
				}
			}
			this.sendMessage("任务退回了,系统会发邮件通知该同事", "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo()
					+ "&currentTaskId=" + currentTaskId + "&taskStepId=" + taskStepId);
		} catch (Exception e) {
			this.sendMessage(MSG.F_SEA, "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo() + "&currentTaskId="
					+ currentTaskId + "&taskStepId=" + taskStepId);
			Logger.getLogger(this.getClass()).error("WfRdViewAction reject Exception", e);
			if(isForFlow == 0){
				return ERROR;
			}else{
				ajaxJson.setSuccess(false);
				ajaxJson.setMsg(this.getMsg());
				this.setJson(JSON.toJSONString(ajaxJson));
				return PGJSON;
			}
		}
		if(isForFlow == 0){
			return SUCCESS;
		}else{
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg(this.getMsg());
			this.setJson(JSON.toJSONString(ajaxJson));
			return PGJSON;
		}
	}

	// 收回任务
	public String newBackJob() throws Exception {
		try {

			/*
			 * WfRdTask saveRemark = new WfRdTask();
			 * saveRemark.setTaskId(currentTask.getTaskId()); new
			 * WfRdTaskFacade().update(saveRemark);
			 */
			String sql = " select Status from WfRdTask where TaskId = '" + taskId + "'";
			WfRdTask wtaskRdTask = new WfRdTaskFacade().findById(sql, "Status");
			System.out.println(wtaskRdTask.getStatus());
			initWorkFlow();
			center.saveJob();
			if (wtaskRdTask.getStatus() == 1) {
				String info = "该主办人正在处理中，不允许收回任务！。";
				if (info != null) {
					this.sendMessage(info, "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo() + "&currentTaskId="
							+ currentTaskId + "&taskStepId=" + taskStepId);
					return ERROR;
				}

			} else {
				String info = center.backTask(MSG.OWFTASK_STATUS_5);
				if (info != null) {
					this.sendMessage(info, "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo() + "&currentTaskId="
							+ currentTaskId + "&taskStepId=" + taskStepId);
					return ERROR;
				}
				this.sendMessage("任务已收回了", "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo() + "&currentTaskId="
						+ currentTaskId + "&taskStepId=" + taskStepId);

			}

			// return "并发类任务，不允许收回/退回。";

		} catch (Exception e) {
			this.sendMessage(MSG.F_SEA, "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo() + "&currentTaskId="
					+ currentTaskId + "&taskStepId=" + taskStepId);
			Logger.getLogger(this.getClass()).error("WfRdViewAction reject Exception", e);
			return ERROR;
		}
		return SUCCESS;
	}

	// 流程管理收回任务
	public String newBackJobforma() throws Exception {
		try {
			System.out.println(taskId);
			/*
			 * WfRdTask saveRemark = new WfRdTask();
			 * saveRemark.setTaskId(currentTask.getTaskId()); new
			 * WfRdTaskFacade().update(saveRemark);
			 */
			String sql = " select Status from WfRdTask where TaskId = '" + taskId + "'";
			WfRdTask wtaskRdTask = new WfRdTaskFacade().findById(sql, "Status");
			System.out.println(wtaskRdTask.getStatus());
			initWorkFlow();
			center.saveJob();
			if (wtaskRdTask.getStatus() == 1) {
				String info = "该主办人正在处理中，不允许收回任务！。";
				if (info != null) {
					this.sendMessage(info, "WfRdView!wfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo());
					return ERROR;
				}

			} else {
				System.out.println(wfRd.getWfNo());
				String info = backTaskforma(MSG.OWFTASK_STATUS_5);
				if (info != null) {
					this.sendMessage(info, "WfRdView!wfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo());
					return ERROR;
				}
				this.sendMessage("任务已收回了", "WfRdView!wfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo());

			}

			// return "并发类任务，不允许收回/退回。";

		} catch (Exception e) {
			this.sendMessage(MSG.F_SEA, "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo());
			Logger.getLogger(this.getClass()).error("WfRdViewAction reject Exception", e);
			return ERROR;
		}
		return SUCCESS;
	}

	public String backTaskforma(int taskStatus) throws Exception { // 收回任务
		System.out.println(wfRd.getWfNo());
		String sql = "select count(*) as amount from WfRdTask where "
				+ "preTaskId=(select preTaskId from WfRdTask where taskId=( select top 1 taskId  from WfRdTask where  WfNo='"
				+ wfRd.getWfNo() + "' order by taskId desc )) "
				+ "and taskId!= ( select top 1 taskId  from WfRdTask where  WfNo='" + wfRd.getWfNo()
				+ "' order by taskId desc ) and WfNo='" + wfRd.getWfNo() + "' and taskType=" + MSG.OWFTASK_TYPE_1
				+ " and status<=" + MSG.OWFTASK_STATUS_2;
		System.out.println(sql);
		String top1sql = " select top 1 *  from WfRdTask where  WfNo='" + wfRd.getWfNo() + "' order by taskId desc ";

		int count = new WfRdTaskFacade().amount(sql);

		// System.out.println(sql);
		if (count > 0) {
			return "并发类任务，不允许收回/退回。";
		}

		/*
		 * if (currentTask.getStatus() == 1) { return "改主办人在处理中，不允许收回任务！"; }
		 */
		WfRdTask wTask = new WfRdTaskFacade().findById(top1sql, "WfRdTask.TaskId");
		// System.out.println(wTask.getTaskId());
		int top1taskID = wTask.getTaskId();
		// System.out.println(top1taskID+"top1taskID");
		if (wfRd.getStatus() < MSG.OWFRD_STATUS_2) { // 流程办理中
			WfRdTask task = new WfRdTask();
			task.setTaskId(top1taskID);
			task.setEndDate(new Date());
			task.setStatus(taskStatus);
			new WfRdTaskFacade().rejectTask(task);

		} else {
			return "流程状态已结束办理，不可退回。";
		}
		return null;
	}

	// 保存任务
	public String saveJob() throws Exception {
		AjaxJson ajaxJson = new AjaxJson();
		try {
			initWorkFlow();

			checkReSub = getTokenKey(); // 防重复
			center.saveJob();
			// center.processExec();
			this.sendMessage(MSG.S_SAV, "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo() + "&currentTaskId="
					+ currentTaskId + "&taskStepId=" + taskStepId);
		} catch (Exception e) {
			this.sendMessage(MSG.F_SAV, "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo() + "&currentTaskId="
					+ currentTaskId + "&taskStepId=" + taskStepId);
			Logger.getLogger(this.getClass()).error("WfRdViewAction saveJob Exception", e);
			if(isForFlow == 0){
				return ERROR;
			}else{
				ajaxJson.setSuccess(false);
				ajaxJson.setMsg(this.getMsg());
				this.setJson(JSON.toJSONString(ajaxJson));
				return PGJSON;
			}
		}
		if(isForFlow == 0){
			return MESSAGE;
		}else{
			ajaxJson.setSuccess(true);
			ajaxJson.setMsg(this.getMsg());
			this.setJson(JSON.toJSONString(ajaxJson));
			return PGJSON;
		}
	}

	// 初始化

	private Integer taskStepId;
	private Integer currentTaskId;

	private String syId;
	private String syNm;
	private String usrId;
	private String usrNm;

	private String uploadType;
	private Integer wfDocId;
	private Integer isLastStep;

	public Integer getIsLastStep() {
		return isLastStep;
	}

	public void setIsLastStep(Integer isLastStep) {
		this.isLastStep = isLastStep;
	}

	private Boolean showDoc;
	private PrjtDef prjtDef;

	protected void initWorkFlow() throws Exception {

		syId = String.valueOf(getUsrSession().getSyId());
		syNm = getUsrSession().getSyNm();
		usrId = String.valueOf(getUsrSession().getId());
		usrNm = getUsrSession().getUsrName();

		// 查找出当前的任务
		if (currentTaskId != null) {
			currentTask = new WfRdTask();
			currentTask.setTaskId(currentTaskId);
			currentTask = new WfRdTaskFacade().findById(currentTask);
			// String wfrdc = currentTask.getWfNo();
			WfStep ws = new WfStep();
			ws.setStepId(currentTask.getStepId());
			ws = new WfStepFacade().findBy(ws);
			isLastStep = ws.getIsLastStep();
		}
		String wfno = ServletActionContext.getRequest().getParameter("wfRd.wfNo");
		System.out.println(wfno);
		user = getUsrSession();
		currentDate = new Date();
		currentUserName = getUsrSession().getUsrName();
		// System.out.println(currentTask.getWfNo());
		wfRd.setWfNo(wfno);
		wfRd = new WfRdFacade().findById(wfRd);
		initWorkFlow(wfRd.getFlowId());

		showDoc = false;
		if (StringUtils.isEmpty(wfRd.getDocCateId())) {
			uploadType = "ProcFile";
			String WfScheCfgDoc_fields = "DocId,DocName,WfScheCfgDoc.StepId";
			String wfscfSQL = "select WfScheCfgDoc.DocId,WfScheCfgDoc.DocName,WfScheCfgDoc.StepId from WfScheCfgDoc "
					+ "where WfScheCfgDoc.StepId in (select StepId from WfStep where FlowId =  " + wfRd.getFlowId()
					+ ")";
			List<WfScheCfgDoc> wfScheCfgDocs = new WfScheCfgDocFacade().find(wfscfSQL, WfScheCfgDoc_fields);
			if (wfScheCfgDocs != null && wfScheCfgDocs.size() > 0) {
				showDoc = true;
			}
		} else {
			uploadType = "BaseLib";
			showDoc = true;
		}
		prjtDef = new PrjtDef();
		prjtDef.setPrjtNo(wfRd.getProjectNo());
		prjtDef = new PrjtDefFacade().findById(prjtDef);

		String showWfFieldSQL = "select  WfField.FieldId  from  WfField inner join WfFieldStepRelate on WfField.FieldId = WfFieldStepRelate.FieldId "
				+ "where WfFieldStepRelate.StepId in (select WfRdTask.StepId from WfRdTask where WfRdTask.WfNo = '"
				+ wfRd.getWfNo() + "') ";
		System.out.println(showWfFieldSQL);
		List<WfField> wfFields = new WfFieldFacade().find(showWfFieldSQL, "WfField.FieldId");
		if (wfFields != null && wfFields.size() > 0) {
			showFiledIds = "";
			for (WfField e : wfFields) {
				showFiledIds = showFiledIds + "," + e.getFieldId();
			}
		}

		System.out.println("showFiledIds---" + showFiledIds);

	}

	private String showFiledIds;

	// 初始化
	protected void initWorkFlow(int cfgId) throws Exception {
		cfg = new WfCfg();
		cfg.setFlowId(cfgId);
		cfg = new WfCfgFacade().findById(cfg);
		center = WFUtil.genWFCenter(WfRdViewAction.this);
		System.out.println("中央控制器:" + center);
	}

	// 取登录地址如 http://192.168.0.6:8080/oa
	public String getSysWebUrl() {
		String basePath = ServletActionContext.getServletContext().getInitParameter("server_URL");
		return basePath;
	}

	public String getWebUrl() {
		String basePath = getSysWebUrl() + "/index.shtml?wfRd.wfNo=" + wfRd.getWfNo();
		return basePath;
	}

	// 中止本流程
	public String stopWfinfo() throws Exception {
		AjaxJson ajaxJson = new AjaxJson();
		try {
			initWorkFlow();
			center.saveJob();

			String sql = "update wfrd set stopdate=getdate(),restartdate=null,lastupddate=getdate()" + ",lastupd="
					+ getUsrSession().getId() + ",status=" + MSG.OWFRD_STATUS_3 + " where wfno='" + wfRd.getWfNo()
					+ "'";
			new WfRdFacade().update(sql);

			String sql2 = "update WfRdTask set EndDate=getdate()" + ",status=" + MSG.OWFTASK_STATUS_3 + " where WfNo='"
					+ wfRd.getWfNo() + "'";
			new WfRdFacade().update(sql2);

			// 邮件通知流程相关人员
			wfRd = new WfRdFacade().findById(wfRd);
			// currentTask = new WfRdTaskFacade().findById(currentTask);
			String url = "WfRdView!wfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo();
			// + "&currentTaskId="
			// + currentTask.getTaskId() + "&taskStepId="+
			// currentTask.getStepId();;
			String webUrl = getSysWebUrl() + "/" + url;
			String title = "工作流《" + wfRd.getWfName() + "》流程终止通知！";
			String content = "工作流编号：" + wfRd.getWfNo() + "，工作流名称：" + wfRd.getWfName() + "，流程已终止!"
					+ "<p>查看流程链接地址 ： <a href=" + webUrl + ">" + webUrl + "</a>";
			List<Usr> usrList = new ArrayList<Usr>();
			// String fileds =
			// "Usr.Id,Usr.ComId,Usr.DeptId,Usr.Status,Usr.CreateBy,Usr.LastUpd,Usr.CreateDate,Usr.LastDate,Usr.Login,Usr.Pwd,Usr.UsrNo,Usr.UsrName,Usr.Email,Usr.Remark,Usr.IsWide";
			// String findWfUsrsSQl = "select "+fileds+" from Usr where Id in (
			// select [AcceptBy] from [GnWf].[dbo].[WfRdTask] where [WfNo] =
			// '"+wfRd.getWfNo()+"')";
			// usrList = new UsrF.find(findWfUsrsSQl, fileds);

			WfRdTask wftask = new WfRdTask();
			wftask.setWfNo(wfRd.getWfNo());
			List<WfRdTask> wfRdTasks = new ArrayList<WfRdTask>();
			wfRdTasks = new WfRdTaskFacade().find(wftask);
			for (WfRdTask e : wfRdTasks) {
				Usr u = new Usr();
				u.setId(e.getAcceptBy());
				usrList.add(u);
			}

			WFUtil.sendMailByIT(title, content, usrList);

			this.sendMessage("中止流程完成", "WfRdView!wfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo());
		} catch (Exception e) {
			e.printStackTrace();
			this.sendMessage("中止流程失败。", "WfRdView!wfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo());
			Logger.getLogger(this.getClass()).error("WfRdViewAction saveJob Exception", e);
			if(isForFlow == 0){
				return ERROR;
			}else{
				ajaxJson.setSuccess(false);
				ajaxJson.setMsg(this.getMsg());
				this.setJson(JSON.toJSONString(ajaxJson));
				return PGJSON;
			}
		}
		if(isForFlow == 0){
			return SUCCESS;
		}else{
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg(this.getMsg());
			this.setJson(JSON.toJSONString(ajaxJson));
			return PGJSON;
		}
	}

	// 重启本流程
	public String restartWfinfo() throws Exception {
		try {
			initWorkFlow();
			String sql = "update wfrd set restartdate=getdate(),stopdatenum=datediff(day,stopdate,getdate())"
					+ ",lastupddate=getdate(),lastupd=" + getUsrSession().getId() + ",status=" + MSG.OWFRD_STATUS_1
					+ " where wfno='" + wfRd.getWfNo() + "'";
			new WfRdFacade().update(sql);
			this.sendMessage("流程重新启动成功", "WfRdView!wfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo());
		} catch (Exception e) {
			this.sendMessage("流程重新启动失败。", "WfRdView!wfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo());
			Logger.getLogger(this.getClass()).error("WfRdViewAction saveJob Exception", e);
			return ERROR;
		}
		return SUCCESS;
	}

	public String addFiles() throws Exception {
		try {
			initWorkFlow();
			center.addFiles();
			this.sendMessage("添加附件成功", "WfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo());
		} catch (Exception e) {
			this.sendMessage("添加附件失败", "WfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo());
			Logger.getLogger(this.getClass()).error("WfRdViewAction addFiles Exception", e);
			return ERROR;
		}
		return SUCCESS;
	}

	// 删除附件
	public String deleteFile() throws Exception {
		try {
			// initWorkFlow();
			// if(wfRd.getStatus() >= MSG.OWFRD_STATUS_2){
			// this.sendMessage("流程已完成,不可删除","WfRdView.shtml?wfRd.wfNo="+wfRd.getWfNo());
			// return ERROR;
			// }

			// 删除附件
			wfDoc = new WfDocFacade().findById(wfDoc);

			if (wfDoc != null && WFUtil.isNotNull(wfDoc.getDocId())) {
				if (wfDoc.getCreateBy().equals(getUsrSession().getId())) {
					String sql = "update wfdoc set status=" + MSG.CONST_STATUS_D + " where docid=" + wfDoc.getDocId();
					new WfDocFacade().update(sql);
					// this.sendMessage("删除成功","WfRdView.shtml?wfRd.wfNo="+wfRd.getWfNo());
					setJson("删除成功");
				} else {
					// this.sendMessage("文件上传本人才能删除","WfRdView.shtml?wfRd.wfNo="+wfRd.getWfNo());
					setJson("文件上传本人才能删除");
				}
			} else {
				// this.sendMessage("未查到此文件","WfRdView.shtml?wfRd.wfNo="+wfRd.getWfNo());
				setJson("未查到此文件");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// this.sendMessage(MSG.F_DEL,"WfRdView.shtml?wfRd.wfNo="+wfRd.getWfNo());
			setJson("删除失败");
			Logger.getLogger(this.getClass()).error("WfRdViewAction deleteFile Exception", e);
			return ERROR;
		}
		// return SUCCESS;
		return PGLIS;
	}

	// 附件归档
	public String baseFile() throws Exception {
		try {
			initWorkFlow();
			// if(wfRd.getStatus() >= MSG.OWFRD_STATUS_2){
			// this.sendMessage("流程已完成,不可归档","WfRdView.shtml?wfRd.wfNo="+wfRd.getWfNo());
			// return ERROR;
			// }

			String gngile_trans_URL = ServletActionContext.getServletContext().getInitParameter("gngile_trans_URL");

			wfDoc = new WfDocFacade().findById(wfDoc);
			if (wfDoc != null && WFUtil.isNotNull(wfDoc.getDocId())) {
				HttpClient httpclient = new HttpClient();
				PostMethod postmethod = new PostMethod(
						gngile_trans_URL + "?fileNo=" + wfDoc.getFileNo().trim() + "&usrId=" + user.getId());

				int sendStatus = 0;
				sendStatus = httpclient.executeMethod(postmethod);
				sendStatus = postmethod.getStatusCode();
				System.out.println("----" + sendStatus);
				if (sendStatus == 200) {
					this.sendMessage("归档成功", "WfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo());
				} else {
					this.sendMessage("归档失败", "WfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo());
				}
			} else {
				this.sendMessage("未查找到附件", "WfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo());
				return ERROR;
			}
		} catch (Exception e) {
			this.sendMessage("归档失败", "WfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo());
			Logger.getLogger(this.getClass()).error("WfRdViewAction baseFile Exception", e);
			return ERROR;
		}
		return SUCCESS;
	}

	// 本流程描述
	public String previewDesc() throws Exception {
		try {
			if (WFUtil.isNull(wfRd.getFlowId())) {
				this.sendMessage("请选择工作流类别", "WfCfgList.shtml");
				return ERROR;
			}
			user = getUsrSession();

			WfCfg c = new WfCfg();
			c.setFlowId(wfRd.getFlowId());
			cfg = new WfCfgFacade().findById(c);

			// 全部步骤
			WfStep wfStep = new WfStep();
			wfStep.setFlowId(wfRd.getFlowId());
			wfStep.setStatus(MSG.CONST_STATUS_1);
			allSteps = new WfStepFacade().find(wfStep);

			// 预览全部步骤--对应下一步的步骤
			String nextSql = "select wfstepnext.stepId,wfstepnext.nextId,WfStep.Sort,WfStep.StepType from wfstepnext "
					+ "left join wfstep on(wfstepnext.nextId = wfstep.stepId) " + "where wfstep.flowId="
					+ wfRd.getFlowId();
			allNextSteps = new WfStepNextFacade().find(nextSql,
					"WfStepNext.StepId,WfStepNext.NextId,WfStep.Sort,WfStep.StepType");

			// 附件
			WfDoc wfDoc = new WfDoc();
			wfDoc.setFlowId(wfRd.getFlowId());
			docList = new WfDocFacade().find(wfDoc);

			// 相关部门
			String deptSql = "select dept.DeptId,dept.parent,dept.DeptNm from dept "
					+ " where deptid in(select deptid from WfDept where flowId=" + wfRd.getFlowId() + ")";
			deptList = new DeptFacade().find(deptSql, "DeptId,Parent,DeptNm");

		} catch (Exception e) {
			this.sendMessage(MSG.F_SEA, "");
			Logger.getLogger(this.getClass()).error("WfCfgViewAction previewDesc Exception", e);
			return ERROR;
		}
		return "wfgDesc";
	}

	// 本流程 流程图上传
	private String tempParams;
	private String fileName;
	private String fileNo;

	public String setRdExtendUri() throws Exception {
		try {

			tempParams = URLDecoder.decode(tempParams, "UTF-8");
			fileName = URLDecoder.decode(fileName, "UTF-8");
			fileNo = URLDecoder.decode(fileNo, "UTF-8");

			System.out.println(tempParams);

			String flowId = null;
			String userId = null;

			String s[] = tempParams.split(",");
			for (int i = 0; i < s.length; i++) {
				String ss[] = s[i].split(":");
				if (ss.length > 1) {
					if ("flowId".equalsIgnoreCase(ss[0])) {
						flowId = ss[1];
					}
					if ("userId".equalsIgnoreCase(ss[0])) {
						userId = ss[1];
					}
				}
			}

			System.out.println("--------" + flowId);
			// WfCfg c = new WfCfg();
			// c.setFlowId(Integer.parseInt(flowId));
			// cfg = new WfCfgFacade().findById(c);
			// //原来存在的
			// if(cfg.getAddRdExtendUri()!=null&&cfg.getAddRdExtendUri().length()>0){
			//
			// }

			java.text.SimpleDateFormat dFormat = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			StringBuilder sql = new StringBuilder("update WfCfg set ");
			sql.append("WfCfg.AddRdExtendUri='").append(fileNo).append("' ");
			sql.append(",WfCfg.LastUpdDate=CONVERT(DATETIME,'").append(dFormat.format(new Date())).append("',120) ");
			sql.append(",WfCfg.LastUpd='").append(userId).append("' ");
			sql.append("where WfCfg.FlowId=").append(flowId);
			new WfCfgFacade().update(sql.toString());
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdViewAction setRdExtendUri Exception", e);
			return ERROR;
		}
		return null;
	}

	public String getAddRdExtendUri() {
		try {
			WfCfg c = new WfCfg();
			c.setFlowId(wfRd.getFlowId());
			cfg = new WfCfgFacade().findById(c);
			if (cfg == null)
				return ERROR;
			WfCfgJson wfCfgJson = new WfCfgJson();
			java.util.ArrayList<WfCfg> lists = new java.util.ArrayList<>();
			if (cfg != null)
				lists.add(cfg);
			wfCfgJson.Rows = lists;
			wfCfgJson.Total = lists.size();
			setJson(JSON.toJSONString(wfCfgJson));
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return "addRdExtendUri";
	}

	// ajax提示

	public void showAjaxInfo() throws Exception {
		try {
			ajaxShowInfo = "";
			// System.out.println(flowId);
			initWorkFlow(flowId);
			ajaxShowInfo = center.genAjaxInfo();

			if (ajaxShowInfo != null && !"null".equals(ajaxShowInfo)) {
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html");
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				out.print(ajaxShowInfo);
				out.close();
			}

		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error("WfRdViewAction showAjaxInfo Exception", e);
		}

	}

	public String showAjaxInfo2() throws Exception {
		try {
			ajaxShowInfo = "";
			initWorkFlow(flowId);
			ajaxShowInfo = center.genAjaxInfo();
			setJson(JSON.toJSONString(ajaxShowInfo));
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error("WfRdViewAction showAjaxInfo Exception", e);
			return ERROR;
		}
		return PGLIS;
	}

	/**
	 * 催办发送邮件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sendMail() throws Exception {
		try {
			wfRd = new WfRdFacade().findById(wfRd);
			currentTask = new WfRdTaskFacade().findById(currentTask);
			String url = "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo() + "&currentTaskId="
					+ currentTask.getTaskId() + "&taskStepId=" + currentTask.getStepId();
			;
			String webUrl = getSysWebUrl() + "/" + url;
			String title = "工作流《" + wfRd.getWfName() + "》任务催办通知！";
			String content = "工作流编号：" + wfRd.getWfNo() + "，工作流名称：" + wfRd.getWfName() + "，请尽快办理!"
					+ "<p>任务办理链接地址 ： <a href=" + webUrl + ">" + webUrl + "</a>";
			List<Usr> usrList = new ArrayList<Usr>();
			Usr u = new Usr();
			u.setId(currentTask.getAcceptBy());
			usrList.add(u);
			WFUtil.sendMailByIT(title, content, usrList);
			setMsg("催办完成");
		} catch (Exception e) {
			setMsg("催办失败");
			Logger.getLogger(this.getClass()).error("WfRdViewAction sentTask Exception", e);
			e.printStackTrace();
		}
		return "msg";
	}

	public String imp1() throws Exception {
		try {
			initWorkFlow1();
			String info = center.importXls();
			System.out.println(getCurrentTaskId());
			System.out.println(taskStepId);
			/*
			 * http://localhost:8080/zrprjt/WfRdView!wfTaskView.shtml?wfRd.wfNo=
			 * B5014800021&currentTaskId=5362&taskStepId=346
			 */ this.sendMessage(info, "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo() + "&currentTaskId="
					+ currentTaskId + "&taskStepId=" + taskStepId);
		} catch (Exception e) {
			// this.sendMessage(MSG.F_IMP,
			// "WfRdView!wfTaskView.shtml?wfRd.wfNo="+wfRd.getWfNo()+"&currentTaskId="+currentTaskId+"&taskStepId="+taskStepId);

			this.sendMessage("导入记录失败,请检查导入模版是否正确！", "WfRdView!wfTaskView.shtml?wfRd.wfNo=" + wfRd.getWfNo()
					+ "&currentTaskId=" + currentTaskId + "&taskStepId=" + taskStepId);
			Logger.getLogger(this.getClass()).error("WfRdViewAction imp Exception", e);
			return ERROR;
		}
		return SUCCESS;
	}

	public String imp() throws Exception {
		try {
			if (fileInp != null) {
				if (wfRds != null && wfRds.size() > 0) {
					for (int i = 0; i < wfRds.size(); i++) {
						if (wfRds.get(i) != null) {
							new WfRdFacade().confirm(wfRds.get(i));
						}
					}
				}
				sendMessage(MSG.S_IMP, "WfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo());
			}
		} catch (Exception e) {
			sendMessage(MSG.F_IMP, "WfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo());
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}

	protected void initWorkFlow1() throws Exception {
		currentDate = new Date();
		wfRd = new WfRdFacade().findById(wfRd);
		System.out.println(wfRd.getFlowId() + "wfRd.getFlowId()");
		initWorkFlow(wfRd.getFlowId());
	}

	public String exp1() throws Exception {
		try {
			workbook = Workbook.createWorkbook(getOutputStream());
			/*
			 * String fname = "统计表.xls";//Excel文件名字 HttpServletResponse response
			 * = ServletActionContext.getResponse(); OutputStream os =
			 * getOutputStream(); response.reset();
			 * response.setHeader("Content-disposition",
			 * "attachment;filename="+new
			 * String(fname.getBytes("GBK"),"ISO8859-1"));
			 * response.setContentType("application/msexcel;charset=UTF-8");
			 * 
			 * 
			 * WritableWorkbook wwb = Workbook.createWorkbook(os);//创建Excel文件
			 */
			initWorkFlow1();
			center.exportXls();
		} catch (Exception e) {
			this.sendMessage(MSG.F_EXP, "WfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo());
			Logger.getLogger(this.getClass()).error("WfRdViewAction exp Exception", e);
			return ERROR;
		}
		return "exportExcel";
	}

	public String exp() throws Exception {
		try {
			List<WfRd> wfRds = new WfRdFacade().find(wfRd);
			if (wfRds != null && wfRds.size() > 0) {
				WritableCellFormat wcformat = new WritableCellFormat();
				wcformat.setAlignment(jxl.format.Alignment.CENTRE);
				wcformat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				wcformat.setBorder(Border.LEFT, BorderLineStyle.THIN);
				wcformat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
				wcformat.setBorder(Border.TOP, BorderLineStyle.THIN);
				wcformat.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
				wcformat.setWrap(true);
				OutputStream os = getOutputStream();
				workbook = Workbook.createWorkbook(os);
				WritableSheet ws = workbook.createSheet("sheet0", 0);
				int index = 0;

				ws.addCell(new Label(index, 1, "进度任务", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "工作流定义ID", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "状态", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "创建人", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "更新人", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "工作流编号", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "项目编号", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "上级工作流", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "计划开始时间", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "计划完成时间", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "实际开始时间", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "实际完成时间", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "创建日期", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "更新日期", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "工作流标题", wcformat));
				ws.setColumnView(index, 20);
				index++;
				ws.addCell(new Label(index, 1, "工作流内容", wcformat));
				ws.setColumnView(index, 20);
				index++;

				int row = 2;
				for (int i = 0; i < wfRds.size(); i++) {
					row++;
					int m = 0;
					if (wfRds.get(i).getScheId() != null)
						ws.addCell(new jxl.write.Number(m, row, wfRds.get(i).getScheId(), wcformat));
					m++;
					if (wfRds.get(i).getFlowId() != null)
						ws.addCell(new jxl.write.Number(m, row, wfRds.get(i).getFlowId(), wcformat));
					m++;
					if (wfRds.get(i).getStatus() != null)
						ws.addCell(new jxl.write.Number(m, row, wfRds.get(i).getStatus(), wcformat));
					m++;
					if (wfRds.get(i).getCreateBy() != null)
						ws.addCell(new jxl.write.Number(m, row, wfRds.get(i).getCreateBy(), wcformat));
					m++;
					if (wfRds.get(i).getLastUpd() != null)
						ws.addCell(new jxl.write.Number(m, row, wfRds.get(i).getLastUpd(), wcformat));
					m++;
					if (wfRds.get(i).getWfNo() != null)
						ws.addCell(new jxl.write.Label(m, row, wfRds.get(i).getWfNo(), wcformat));
					m++;
					if (wfRds.get(i).getProjectNo() != null)
						ws.addCell(new jxl.write.Label(m, row, wfRds.get(i).getProjectNo(), wcformat));
					m++;
					if (wfRds.get(i).getPreWfNo() != null)
						ws.addCell(new jxl.write.Label(m, row, wfRds.get(i).getPreWfNo(), wcformat));
					m++;
					if (wfRds.get(i).getPlanSDate() != null)
						ws.addCell(new jxl.write.DateTime(m, row, wfRds.get(i).getPlanSDate(), wcformat));
					m++;
					if (wfRds.get(i).getPlanEDate() != null)
						ws.addCell(new jxl.write.DateTime(m, row, wfRds.get(i).getPlanEDate(), wcformat));
					m++;
					if (wfRds.get(i).getFactSDate() != null)
						ws.addCell(new jxl.write.DateTime(m, row, wfRds.get(i).getFactSDate(), wcformat));
					m++;
					if (wfRds.get(i).getFactEDate() != null)
						ws.addCell(new jxl.write.DateTime(m, row, wfRds.get(i).getFactEDate(), wcformat));
					m++;
					if (wfRds.get(i).getCreateDate() != null)
						ws.addCell(new jxl.write.DateTime(m, row, wfRds.get(i).getCreateDate(), wcformat));
					m++;
					if (wfRds.get(i).getLastUpdDate() != null)
						ws.addCell(new jxl.write.DateTime(m, row, wfRds.get(i).getLastUpdDate(), wcformat));
					m++;
					if (wfRds.get(i).getWfName() != null)
						ws.addCell(new jxl.write.Label(m, row, wfRds.get(i).getWfName(), wcformat));
					m++;
					if (wfRds.get(i).getWfDesc() != null)
						ws.addCell(new jxl.write.Label(m, row, wfRds.get(i).getWfDesc(), wcformat));
					m++;

				}
			}
			this.sendMessage(MSG.S_EXP, "WfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo());
		} catch (Exception e) {
			this.sendMessage(MSG.F_EXP, "WfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo());
			Logger.getLogger(this.getClass()).error("WfRdListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}

	public String prn() throws Exception {
		try {
			initWorkFlow();
			center.print();
			fieldContents.size();
		} catch (Exception e) {
			e.printStackTrace();
			sendMessage(MSG.F_PRN, "WfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo());
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return PRINT;

	}

	public String showForm() throws Exception {
		try {
			initWorkFlow();
			center.print();
			fieldContents.size();
		} catch (Exception e) {
			sendMessage(MSG.F_PRN, "WfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo());
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return "showForm";
	}

	/*
	 * public String showForm() throws Exception { try { initWorkFlow();
	 * center.print(); fieldContents.size(); } catch(Exception e) {
	 * sendMessage(MSG.F_PRN,"WfRdView.shtml?wfRd.wfNo="+wfRd.getWfNo());
	 * Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
	 * return ERROR; } return "showForm"; }
	 */

	public String dow() throws Exception {
		try {
			if (wfRds != null && wfRds.size() > 0) {
				for (int i = 0; i < wfRds.size(); i++) {
					if (wfRds.get(i) != null) {
						// wfRds.get(i).setLastUpd(getSession().getUserId());
						// wfRds.get(i).setLastUpdDate(new Date());
						new WfRdFacade().confirm(wfRds.get(i));
					}
				}
			}
			sendMessage(MSG.S_PRN, "WfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo());
		} catch (Exception e) {
			sendMessage(MSG.F_PRN, "WfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo());
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}

	public List<WfRd> getWfRds() {
		return wfRds;
	}

	public void setWfRds(List<WfRd> wfRds) {
		this.wfRds = wfRds;
	}

	public WfRd getWfRd() {
		return wfRd;
	}

	public void setWfRd(WfRd wfRd) {
		this.wfRd = wfRd;
	}

	public WritableWorkbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(WritableWorkbook workbook) {
		this.workbook = workbook;
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

	public java.util.List<gnwf.vo.WfDoc> getWfDocs() {
		return wfDocs;
	}

	public void setWfDocs(java.util.List<gnwf.vo.WfDoc> wfDocs) {
		this.wfDocs = wfDocs;
	}

	public void addtoWfDocs(gnwf.vo.WfDoc wfDoc) {
		if (getWfDocs() == null)
			setWfDocs(new java.util.ArrayList<gnwf.vo.WfDoc>());
		getWfDocs().add(wfDoc);
	}

	public java.util.List<gnwf.vo.WfQuesRelate> getWfQuesRelates() {
		return wfQuesRelates;
	}

	public void setWfQuesRelates(java.util.List<gnwf.vo.WfQuesRelate> wfQuesRelates) {
		this.wfQuesRelates = wfQuesRelates;
	}

	public void addtoWfQuesRelates(gnwf.vo.WfQuesRelate wfQuesRelate) {
		if (getWfQuesRelates() == null)
			setWfQuesRelates(new java.util.ArrayList<gnwf.vo.WfQuesRelate>());
		getWfQuesRelates().add(wfQuesRelate);
	}

	public java.util.List<gnwf.vo.WfMatl> getWfMatls() {
		return wfMatls;
	}

	public void setWfMatls(java.util.List<gnwf.vo.WfMatl> wfMatls) {
		this.wfMatls = wfMatls;
	}

	public void addtoWfMatls(gnwf.vo.WfMatl wfMatl) {
		if (getWfMatls() == null)
			setWfMatls(new java.util.ArrayList<gnwf.vo.WfMatl>());
		getWfMatls().add(wfMatl);
	}

	public java.util.List<gnwf.vo.WfRdTask> getWfRdTasks() {
		return wfRdTasks;
	}

	public void setWfRdTasks(java.util.List<gnwf.vo.WfRdTask> wfRdTasks) {
		this.wfRdTasks = wfRdTasks;
	}

	public void addtoWfRdTasks(gnwf.vo.WfRdTask wfRdTask) {
		if (getWfRdTasks() == null)
			setWfRdTasks(new java.util.ArrayList<gnwf.vo.WfRdTask>());
		getWfRdTasks().add(wfRdTask);
	}

	public java.util.List<gnwf.vo.WfRdField> getWfRdFields() {
		return wfRdFields;
	}

	public void setWfRdFields(java.util.List<gnwf.vo.WfRdField> wfRdFields) {
		this.wfRdFields = wfRdFields;
	}

	public void addtoWfRdFields(gnwf.vo.WfRdField wfRdField) {
		if (getWfRdFields() == null)
			setWfRdFields(new java.util.ArrayList<gnwf.vo.WfRdField>());
		getWfRdFields().add(wfRdField);
	}

	public WfCfg getWfCfg() {
		return wfCfg;
	}

	public void setWfCfg(WfCfg wfCfg) {
		this.wfCfg = wfCfg;
	}

	public int getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(int isLeader) {
		this.isLeader = isLeader;
	}

	public int getFlowId() {
		return flowId;
	}

	public void setFlowId(int flowId) {
		this.flowId = flowId;
	}

	public WfCfg getCfg() {
		return cfg;
	}

	public void setCfg(WfCfg cfg) {
		this.cfg = cfg;
	}

	public WFCenter getCenter() {
		return center;
	}

	public void setCenter(WFCenter center) {
		this.center = center;
	}

	public File getImpfile() {
		return impfile;
	}

	public void setImpfile(File impfile) {
		this.impfile = impfile;
	}

	public Usr getUser() {
		return user;
	}

	public void setUser(Usr user) {
		this.user = user;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public WfRdTask getUnAcceptTask() {
		return unAcceptTask;
	}

	public void setUnAcceptTask(WfRdTask unAcceptTask) {
		this.unAcceptTask = unAcceptTask;
	}

	public WfRdTask getCurrentTask() {
		return currentTask;
	}

	public void setCurrentTask(WfRdTask currentTask) {
		this.currentTask = currentTask;
	}

	public List<WfRdSign> getSignList() {
		return signList;
	}

	public void setSignList(List<WfRdSign> signList) {
		this.signList = signList;
	}

	public List<WfCfgRelate> getRelateFlows() {
		return relateFlows;
	}

	public void setRelateFlows(List<WfCfgRelate> relateFlows) {
		this.relateFlows = relateFlows;
	}

	public List<WfRd> getChildList() {
		return childList;
	}

	public void setChildList(List<WfRd> childList) {
		this.childList = childList;
	}

	public List<WfRdTask> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<WfRdTask> taskList) {
		this.taskList = taskList;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public List<Integer> getFileCates() {
		return fileCates;
	}

	public void setFileCates(List<Integer> fileCates) {
		this.fileCates = fileCates;
	}

	public List<WfScheCfgDoc> getDocCates() {
		return docCates;
	}

	public void setDocCates(List<WfScheCfgDoc> docCates) {
		this.docCates = docCates;
	}

	public List<WfDoc> getDocList() {
		return docList;
	}

	public void setDocList(List<WfDoc> docList) {
		this.docList = docList;
	}

	public WfDoc getWfDoc() {
		return wfDoc;
	}

	public void setWfDoc(WfDoc wfDoc) {
		this.wfDoc = wfDoc;
	}

	public WfRdSign getWfRdSign() {
		return wfRdSign;
	}

	public void setWfRdSign(WfRdSign wfRdSign) {
		this.wfRdSign = wfRdSign;
	}

	public List<WfRdField> getFieldContents() {
		return fieldContents;
	}

	public void setFieldContents(List<WfRdField> fieldContents) {
		this.fieldContents = fieldContents;
	}

	public List<Row> getRows() {
		return rows;
	}

	public void setRows(List<Row> rows) {
		this.rows = rows;
	}

	public List<String> getAjaxList() {
		return ajaxList;
	}

	public void setAjaxList(List<String> ajaxList) {
		this.ajaxList = ajaxList;
	}

	public String getAjaxShowInfo() {
		return ajaxShowInfo;
	}

	public void setAjaxShowInfo(String ajaxShowInfo) {
		this.ajaxShowInfo = ajaxShowInfo;
	}

	public String getIncludeJspUri() {
		return includeJspUri;
	}

	public void setIncludeJspUri(String includeJspUri) {
		this.includeJspUri = includeJspUri;
	}

	public List<WfStep> getNextSteps() {
		return nextSteps;
	}

	public void setNextSteps(List<WfStep> nextSteps) {
		this.nextSteps = nextSteps;
	}

	public List<WfRdTask> getWfTasks() {
		return wfTasks;
	}

	public void setWfTasks(List<WfRdTask> wfTasks) {
		this.wfTasks = wfTasks;
	}

	public List<WfStep> getAllSteps() {
		return allSteps;
	}

	public void setAllSteps(List<WfStep> allSteps) {
		this.allSteps = allSteps;
	}

	public List<WfStepNext> getAllNextSteps() {
		return allNextSteps;
	}

	public void setAllNextSteps(List<WfStepNext> allNextSteps) {
		this.allNextSteps = allNextSteps;
	}

	public List<Dept> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<Dept> deptList) {
		this.deptList = deptList;
	}

	public int getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(int taskCount) {
		this.taskCount = taskCount;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public List<WfMatl> getMatlList() {
		return matlList;
	}

	public void setMatlList(List<WfMatl> matlList) {
		this.matlList = matlList;
	}

	public List<WfQues> getQuesList() {
		return quesList;
	}

	public void setQuesList(List<WfQues> quesList) {
		this.quesList = quesList;
	}

	public Mail getMail() {
		return mail;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public String getMailName() {
		return mailName;
	}

	public void setMailName(String mailName) {
		this.mailName = mailName;
	}

	public String getHiddenValue() {
		return hiddenValue;
	}

	public void setHiddenValue(String hiddenValue) {
		this.hiddenValue = hiddenValue;
	}

	public List<WFMatlCategory> getCateList() {
		return cateList;
	}

	public void setCateList(List<WFMatlCategory> cateList) {
		this.cateList = cateList;
	}

	public List<WfItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<WfItem> itemList) {
		this.itemList = itemList;
	}

	public void setFlowId(Integer flowId) {
		this.flowId = flowId;
	}

	public Integer getAgentBy() {
		return agentBy;
	}

	public void setAgentBy(Integer agentBy) {
		this.agentBy = agentBy;
	}

	public String getCurrentUserName() {
		return currentUserName;
	}

	public void setCurrentUserName(String currentUserName) {
		this.currentUserName = currentUserName;
	}

	public Set<WfStep> getImgStepList() {
		return imgStepList;
	}

	public void setImgStepList(Set<WfStep> imgStepList) {
		this.imgStepList = imgStepList;
	}

	public String getIsReloadGrid() {
		return isReloadGrid;
	}

	public void setIsReloadGrid(String isReloadGrid) {
		this.isReloadGrid = isReloadGrid;
	}

	public int getIsfromPrjtDef() {
		return isfromPrjtDef;
	}

	public void setIsfromPrjtDef(int isfromPrjtDef) {
		this.isfromPrjtDef = isfromPrjtDef;
	}

	public Integer getTaskStepId() {
		return taskStepId;
	}

	public void setTaskStepId(Integer taskStepId) {
		this.taskStepId = taskStepId;
	}

	public String getSyId() {
		return syId;
	}

	public void setSyId(String syId) {
		this.syId = syId;
	}

	public String getSyNm() {
		return syNm;
	}

	public void setSyNm(String syNm) {
		this.syNm = syNm;
	}

	public String getUsrId() {
		return usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	public String getUsrNm() {
		return usrNm;
	}

	public void setUsrNm(String usrNm) {
		this.usrNm = usrNm;
	}

	public Integer getCurrentTaskId() {
		return currentTaskId;
	}

	public void setCurrentTaskId(Integer currentTaskId) {
		this.currentTaskId = currentTaskId;
	}

	public String getTempParams() {
		return tempParams;
	}

	public void setTempParams(String tempParams) {
		this.tempParams = tempParams;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public String getUploadType() {
		return uploadType;
	}

	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}

	protected JSONObject andrInitWorkFlow() throws Exception {
		initWorkFlow();

		JSONObject obj = new JSONObject();
		obj.put("syId", syId);// 系统id
		obj.put("syNm", syNm);// 当前系统名
		obj.put("usrId", usrId);// 登录用户ID
		obj.put("usrNm", usrNm);// 登录用户名称
		obj.put("currentTask", currentTask);// 当前任务
		obj.put("user", user);// 登录用户
		obj.put("currentDate", currentDate);// 当前时间
		obj.put("currentUserName", currentUserName);// 登录用户名称
		obj.put("wfRd", wfRd);// 流程
		obj.put("cfg", cfg);// 流程类别
		// obj.put("center", center);
		obj.put("wfRdTasks", wfRdTasks);// 流程进度
		obj.put("uploadType", uploadType);// 上传时,文档类别
		obj.put("wfDocs", wfDocs);// 上传的的附件
		return obj;
	}

	// 获取我的任务信息 接口
	/**
	 * public String andrWfTaskView() throws Exception { try { JSONObject obj =
	 * andrInitWorkFlow();
	 * 
	 * checkReSub = getTokenKey(); // 防重复 center.processExec();
	 * obj.put("checkReSub", checkReSub);// 防重复 // obj.put("center", center);
	 * obj.put("includeJspUri", includeJspUri);// 画 word 的 jsp 页面
	 * obj.put("fieldContents", fieldContents);// word 字段的值 obj.put("rows",
	 * rows); obj.put("taskList", taskList);// 任务进度 obj.put("imgStepList",
	 * imgStepList);// 画图进度 obj.put("wfRdSign", wfRdSign);// 本人会签意见
	 * obj.put("signList", signList);// 会签List //
	 * currentTask.setBackTasks(backTasks); //退回原因集 obj.put("relateFlows",
	 * relateFlows);// 可触发子流程 obj.put("childList", childList);// 已有子流程列表
	 * obj.put("docList", docList);// 附件列表
	 * 
	 * // 不再用 // obj.put("docCates", docCates);//本流程需上传文档类别
	 * //currentTask.setDocNames
	 * 
	 * obj.put("nextSteps", nextSteps); obj.put("allNextSteps", allNextSteps);
	 * obj.put("allSteps", allSteps); obj.put("unAcceptTask", unAcceptTask);
	 * obj.put("files", files); obj.put("wfDoc", wfDoc); obj.put("taskCount",
	 * taskCount); obj.put("quesList", quesList);
	 * 
	 * setJson(obj.toString()); return PGJSON; } catch (Exception e) { //
	 * sendMessage(MSG.F_SEA,"WfRdView.shtml?wfRd.wfNo="+wfRd.getWfNo());
	 * Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
	 * e.printStackTrace(); return ERROR; } }
	 **/

	// 获取我的任务信息 接口
	public String andrWfFlowSteps() throws Exception {
		try {
			wfTaskView();
			JSONObject obj = new JSONObject();
			WfRdTask task = new WfRdTask();
			task.setWfNo(wfRd.getWfNo());
			taskList = new WfRdTaskFacade().findAll(task);
			obj.put("taskList", taskList);// 任务进度
			System.out.println(obj.toString());
			setJson(obj.toString());
			return PGJSON;
		} catch (Exception e) {
			// sendMessage(MSG.F_SEA,"WfRdView.shtml?wfRd.wfNo="+wfRd.getWfNo());
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			e.printStackTrace();
			return ERROR;
		}
	}

	// 获取我的任务信息 接口
	public String andrWfTaskView() throws Exception {
		try {
			wfTaskView();
			JSONObject obj = new JSONObject();
			WfRd wfrdObject = new WfRd();
			wfrdObject.setPrjtNm(prjtDef.getPrjtNm());
			wfrdObject.setProjectNo(prjtDef.getPrjtNo());
			wfrdObject.setFlowName(cfg.getFlowName());
			wfrdObject.setWfNo(wfRd.getWfNo());
			wfrdObject.setTaskStatus(currentTask.getStatus());
			if (wfRd.getCreateBy() == -1) {
				wfrdObject.setCreateName("系统");
			} else {
				wfrdObject.setCreateName(wfRd.getCreateName());
			}
			wfrdObject.setStatus(wfRd.getStatus());

			obj.put("WfRd", wfrdObject);
			obj.put("imgStepList", imgStepList);
			obj.put("taskList", taskList);// 任务进度
			System.out.println(obj.toString());
			setJson(obj.toString());
			return PGJSON;
		} catch (Exception e) {
			// sendMessage(MSG.F_SEA,"WfRdView.shtml?wfRd.wfNo="+wfRd.getWfNo());
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			e.printStackTrace();
			return ERROR;
		}
	}

	// 获取我的任务表单字段接口

	public String andrWfTaskGetForm() throws Exception {
		try {
			wfTaskView();

			JSONObject obj = new JSONObject();
			List<WfRdField> currentFileds = new ArrayList<WfRdField>();
			for (WfRdField e : fieldContents) {
				if (e.getIsEdit() == 1 && e.getNeedFilledOnAPP() == 1) {
					currentFileds.add(e);
				}
			}
			WfRdFieldJson wfRdFieldJson = new WfRdFieldJson();
			wfRdFieldJson.Rows = currentFileds;
			wfRdFieldJson.Total = currentFileds.size();
			obj.put("fieldContents", JSON.toJSONString(wfRdFieldJson));
			obj.put("includeJspUri", includeJspUri);
			setJson(obj.toString());
			System.out.println(obj.toString());
			return PGJSON;
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			e.printStackTrace();
			return ERROR;
		}
	}

	// 提交表单字段接口
	private String data;

	public String andrWfTaskSubmitFeilds() throws Exception {
		try {
			System.out.println(data);
			fieldContents = new ArrayList<WfRdField>();
			JSONArray array = JSONArray.fromObject(data);// 先读取串数组
			Object[] fs = array.toArray();
			// 转成对像数组
			for (int i = 0; i < fs.length; i++) {
				JSONObject obj = JSONObject.fromObject(fs[i]);// 再使用JsonObject遍历一个个的对像
				WfRdField w = (WfRdField) JSONObject.toBean(obj, WfRdField.class);// 指定转换的类型，但仍需要强制转化-成功
				// f.setCreateBy(getUsrSession().getId());
				// f.setCreateDate(new Date());
				// f.setLastUpd(getUsrSession().getId());
				// f.setLastDate(new Date());

				fieldContents.add(w);
			}
			new WfRdFieldFacade().saveAll(fieldContents, wfRd.getWfNo());
			return andrWfTaskGetForm();
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			e.printStackTrace();
			return ERROR;
		}
	}

	// 获取任务办理时文档接口
	public String andrWfTaskGetDocs() throws Exception {
		try {
			viewDocCate();
			WfDocJson wfDocJson = new WfDocJson();
			wfDocJson.Rows = wfDocs;
			wfDocJson.Total = wfDocs.size();
			setJson(JSON.toJSONString(wfDocJson));
			return PGJSON;
		} catch (Exception e) {
			// sendMessage(MSG.F_SEA,"WfRdView.shtml?wfRd.wfNo="+wfRd.getWfNo());
			Logger.getLogger(this.getClass()).error("WfRdAction Exception", e);
			e.printStackTrace();
			return ERROR;
		}
	}

	// wfRd.wfNo
	// fieldContents //表单, word 的值, 多个值
	// wfRdSign.signText //会签的意见
	// files //上传的File文件, 多个值
	// fileCates //文件类别, 多个值

	// 中止本流程 //中止任务
	public String andrStopWfinfo() throws Exception {
		String str = stopWfinfo();
		JSONObject obj = new JSONObject();
		if (SUCCESS.equals(str)) {
			obj.put("msg", "任务中止完成");
			obj.put("result", 1);
		} else {
			obj.put("msg", "任务中止失败");
			obj.put("result", 0);
		}
		setJson(obj.toString());
		return PGJSON;
	}

	// wfRd.wfNo
	// currentTaskId
	// 退回任务
	public String andrReject() throws Exception {
		String str = reject();
		JSONObject obj = new JSONObject();
		if (SUCCESS.equals(str)) {
			obj.put("msg", "任务退回了,系统会发邮件通知同事");
			obj.put("result", 1);
		} else {
			obj.put("msg", "退回失败");
			obj.put("result", 0);
		}
		setJson(obj.toString());
		return PGJSON;
	}

	// wfRd.wfNo
	// currentTaskId
	// taskStepId
	// agentBy //任务代办人
	// fieldContents //表单, word 的值, 多个值
	// wfRdSign.signText //会签的意见
	// files //上传的File文件, 多个值
	// fileCates //文件类别, 多个值
	// 设置代办人
	public String andrSetAgentBy() throws Exception {
		String str = setAgentBy();
		JSONObject obj = new JSONObject();
		if (SUCCESS.equals(str)) {
			obj.put("msg", "设置任务代办人成功");
			obj.put("result", 1);
		} else {
			if (WFUtil.isNull(agentBy)) {
				obj.put("msg", "任务代办人不能为空");
			} else {
				obj.put("msg", "设置任务代办人失败");
			}
			obj.put("result", 0);
		}
		setJson(obj.toString());
		return PGJSON;
	}

	// // logCode //登录代码
	// // wfRd.wfNo //流程编号
	// // currentTaskID //当前任务ID
	// // taskStepId //任务的步骤ID
	// // agentBy //任务代办人ID返回json
	// // checkReSub //防重复提交
	//  wfDocId //归档流程中,文件的ID
	//  fieldContents //表单, word 的值, 多个值
	//  wfRdSign.signText //会签的意见
	//  files //上传的File文件, 多个值
	//  fileCates //文件类别, 多个值

	// 完成任务,下一步任务
	// 进下一步转交页面
	boolean isfromeAndroid = false;

	public String andrNextStepPage() throws Exception {
		// setCheckReSub(checkReSub);
		isfromeAndroid = true;
		String str = nextStepPage();
		JSONObject obj = new JSONObject();
		obj.put("hashnext", 0);
		obj.put("result", 0);
		if (SUCCESS.equals(str)) {
			obj.put("result", 1);
			obj.put("msg", "任务完成成功");
		} else if ("nextStepPage".equals(str)) {
			obj.put("msg", "进入下一步.");
			obj.put("nextSteps", nextSteps);
			obj.put("result", 1);
			obj.put("hashnext", 1);
		} else {
			obj.put("msg", getMsg());
		}
		setJson(obj.toString());
		return PGJSON;

	}

	// 下一步任务前检查是否上传了相应的报告文档
	public String checkDocSize() throws Exception {
		String sqlExtent = "select  WfDoc.DocId  from WfDoc "
				+ " left join WfRdTask on (WfRdTask.TaskId = WfDoc.TaskId) "
				+ " left join WfStep on(WfRdTask.StepId=WfStep.StepId) "
				+ " left join usr on (WfDoc.CreateBy = usr.id) "
				+ " where WfDoc.Status=1 and WfDoc.CateId is null  and WfDoc.WfNo='" + wfRd.getWfNo() + "' "
				+ "  AND WfStep.StepId ='" + taskStepId + "'";
		System.out.println(sqlExtent);
		List<WfDoc> extendDocs = new WfDocFacade().find(sqlExtent, "WfDoc.DocId");

		String stepSql = "SELECT * FROM  GnWf.[dbo].[WfStep] where WfStep.StepId='" + taskStepId + "'";

		WfStep wf = new WfStepFacade().findById(stepSql, WfStep.LIST);
		String resultJson = "";
		if (extendDocs.size() >= wf.getUploadSize()) {
			resultJson = "{\"result\":1}";
		} else {
			resultJson = "{\"result\":0,\"info\":\"" + wf.getDocName() + "\"}";
		}
		setJson(resultJson);
		return PGLIS;
	}

	// checkReSub:1d35cdec71ecd1c99e87f735f4d1d0e1
	// wfRd.wfNo=B0914100016
	// currentTaskId=3496
	// taskStepId=62
	// wfTasks[0].stepId=222
	// wfTasks[0].acceptBy=15441
	// wfTasks[0].taskType=1
	// nextSteps[2].taskTime=2014-3-13
	// nextSteps[0].stepId=222

	// wfRd.wfNo
	// currentTaskId //可选
	// checkReSub 防重复提交
	//
	// 转交任务
	public String andrSendTask() throws Exception {
		// setCheckReSub(checkReSub);
		isfromeAndroid = true;
		String str = sendTask();
		JSONObject obj = new JSONObject();
		obj.put("result", 0);
		try {
			if (SUCCESS.equals(str)) {
				obj.put("result", 1);
				obj.put("msg", "任务完成成功");
			} else {
				obj.put("msg", getMsg());
			}
		} catch (Exception e) {
			obj.put("msg", MSG.F_SAV);
		}
		setJson(obj.toString());
		return PGJSON;
	}

	// 阅知发送邮件
	public String sendMailForReaderNew() throws Exception {
		String url = "WfRdView!wfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo();
		WfRd wfRdlists = new WfRdFacade().findById(wfRd);
		WfCfg wfCfglist = new WfCfg();
		wfCfglist.setFlowId(wfRdlists.getFlowId());
		WfCfg wfCfglists = new WfCfgFacade().findById(wfCfglist);
		String webUrl = getSysWebUrl() + url;
		String title = "工作流《 " + wfCfglists.getFlowName() + "》流程的任务阅知通知";
		String content = "尊敬的同事，您好：" + "<p>您有一条《" + wfRdlists.getWfName() + "》流程的任务阅知通知。" + "<p>工作流编号为："
				+ wfRd.getWfNo() + "，工作流名称：" + wfRdlists.getWfName() + "，请尽快办理!" + "<p>任务办理链接地址 ： <a href=" + webUrl
				+ ">" + webUrl + "</a>";
		if( wfRd.getUsrIDs().equals("15129")){
			 wfRd.setUsrIDs("");
		}
		SendMailUtil.sendMailByUsrId(title, content, wfRd.getUsrIDs());

		return SUCCESS;
	}

	public Boolean getShowDoc() {
		return showDoc;
	}

	public void setShowDoc(Boolean showDoc) {
		this.showDoc = showDoc;
	}

	public PrjtDef getPrjtDef() {
		return prjtDef;
	}

	public void setPrjtDef(PrjtDef prjtDef) {
		this.prjtDef = prjtDef;
	}

	public String getShowFiledIds() {
		return showFiledIds;
	}

	public void setShowFiledIds(String showFiledIds) {
		this.showFiledIds = showFiledIds;
	}

	public Integer getWfDocId() {
		return wfDocId;
	}

	public void setWfDocId(Integer wfDocId) {
		this.wfDocId = wfDocId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public WfStep getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(WfStep currentStep) {
		this.currentStep = currentStep;
	}

	public boolean isNeedSpecifyNext() {
		return needSpecifyNext;
	}

	public void setNeedSpecifyNext(boolean needSpecifyNext) {
		this.needSpecifyNext = needSpecifyNext;
	}
	public String getFlagDcc() {
		return flagDcc;
	}

	public void setFlagDcc(String flagDcc) {
		this.flagDcc = flagDcc;
	}


	public void setIsForFlow(int isForFlow) {
		this.isForFlow = isForFlow;
	}


	public int getIsForFlow() {
		return isForFlow;
	}
	
}