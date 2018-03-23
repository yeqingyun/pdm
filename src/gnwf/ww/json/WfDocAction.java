package gnwf.ww.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.gionee.gnif.file.util.CalcUtil;
import com.gionee.gnif.file.web.message.Message;
import com.gionee.oss.api.util.EncryptUtil;
import com.opensymphony.webwork.ServletActionContext;
import gnwf.dao.helper.WfDocHelper;
import gnwf.facade.*;
import gnwf.vo.*;
import gnwf.vo.json.WfDocJson;
import gnwf.ww.BasicAction;
import gnwf.ww.MSG;
import gnwf.ww.workflow.WFUtil;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.frm.vo.PageVO;
import upload.OssConstant;
import zrprjt.facade.PrjtDefFacade;
import zrprjt.facade.PrjtUsrFacade;
import zrprjt.vo.PrjtDef;
import zrprjt.vo.PrjtUsr;
import zrsy.vo.Gp;
import zrsy.vo.Usr;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WfDocAction extends BasicAction {
    private static final long serialVersionUID = 1L;
    private WritableWorkbook workbook;
    private File fileInp;
    private String choices;
    private List<WfDoc> wfDocs;
    private WfDoc wfDoc = new WfDoc();
    private WfStep wfStep = new WfStep();
    private java.util.List<gnwf.vo.WfDocRev> wfDocRevs;
    private java.util.List<gnwf.vo.WfRdTask> wfRdTasks;
    private java.util.List<gnwf.vo.WfRd> wfRds;

    private String contentType;
    private String fileName;
    private InputStream inputStream;

    private String projectNo;
    private Integer scheId;

    private String fileNo;
    private String usrId;
    private String tempParams;

    public String loadDocCates() {

        try {

            String sql = "select WfScheCfgDoc.DocId,WfScheCfgDoc.DocName from WfScheCfgDoc ";
            // +" where stepId="+currentTask.getStepId();
            List<WfScheCfgDoc> docCates = new WfScheCfgDocFacade().find(sql, "WfScheCfgDoc.DocId,WfScheCfgDoc.DocName");
            this.setJson(JSON.toJSONString(docCates));
        } catch (Exception e) {
            setMsg(MSG.F_SEA);
            Logger.getLogger(this.getClass()).error("SchCfgAction Exception", e);
            return ERROR;
        }
        return PGLIS;
    }

    public String execute() throws Exception {
        try {
            if (wfDoc != null && wfDoc.getDocId() != null) {
                wfDoc = new WfDocFacade().findById(wfDoc);
                setJson(JSON.toJSONString(wfDoc));
            }
            wfRdTasks = new gnwf.facade.WfRdTaskFacade().find("select " + gnwf.vo.WfRdTask.SELF_FIELDS + " from WfRdTask", gnwf.vo.WfRdTask.SELF_FIELDS);
            wfRds = new gnwf.facade.WfRdFacade().find("select " + gnwf.vo.WfRd.SELF_FIELDS + " from WfRd", gnwf.vo.WfRd.SELF_FIELDS);

        } catch (Exception e) {
            setMsg(MSG.F_SEA);
            Logger.getLogger(this.getClass()).error("WfDocAction Exception", e);
            return ERROR;
        }
        return INITIALIZES;
    }

    public String add() throws Exception {
        try {
            // if(wfDoc != null && wfDoc.getDocId() != null) {
            // wfDoc = new WfDocFacade().findById(wfDoc);
            // setJson(JSON.toJSONString(wfDoc));
            // }
            wfRdTasks = new gnwf.facade.WfRdTaskFacade().find("select " + gnwf.vo.WfRdTask.SELF_FIELDS + " from WfRdTask", gnwf.vo.WfRdTask.SELF_FIELDS);
            wfRds = new gnwf.facade.WfRdFacade().find("select " + gnwf.vo.WfRd.SELF_FIELDS + " from WfRd", gnwf.vo.WfRd.SELF_FIELDS);

        } catch (Exception e) {
            setMsg(MSG.F_SEA);
            Logger.getLogger(this.getClass()).error("WfDocAction add Exception", e);
            return ERROR;
        }
        return PGADD;
    }

    // TODO
    public String addfile() throws Exception {
        try {

            tempParams = URLDecoder.decode(tempParams, "UTF-8");
            fileName = URLDecoder.decode(fileName, "UTF-8");

            System.out.println(tempParams);

            String prjtNo = null;
            String wfNo = null;
            String taskId = null;
            String cateId = null;
            Integer docType = null;
            Integer syId = null;
            String flowid = null;

            String s[] = tempParams.split(",");
            for (int i = 0; i < s.length; i++) {
                String ss[] = s[i].split(":");
                if ("wfNo".equalsIgnoreCase(ss[0]) || "wfDoc.wfNo".equalsIgnoreCase(ss[0])) {
                    wfNo = ss[1];
                } else if ("cateId".equalsIgnoreCase(ss[0]) || "wfDoc.cateId".equalsIgnoreCase(ss[0])) {
                    cateId = ss[1];
                } else if ("taskId".equalsIgnoreCase(ss[0]) || "wfDoc.taskId".equalsIgnoreCase(ss[0])) {
                    taskId = ss[1];
                } else if ("prjtNo".equalsIgnoreCase(ss[0]) || "wfDoc.projectNo".equalsIgnoreCase(ss[0])) {
                    prjtNo = ss[1];
                } else if ("flowid".equalsIgnoreCase(ss[0])) {
                    flowid = ss[1];
                } else if ("syId".equalsIgnoreCase(ss[0]) || "wfDoc.syId".equalsIgnoreCase(ss[0])) {
                    syId = Integer.parseInt(ss[1]);
                } else if ("uploadType".equalsIgnoreCase(ss[0])) {
                    String uploadType = ss[1];
                    if (uploadType.trim().equals("ProcFile")) {
                        docType = MSG.WFDOC_DOCTYPE_PROC;
                    } else if (uploadType.trim().equals("BaseLib")) {
                        docType = MSG.WFDOC_DOCTYPE_BASELIB;
                    }
                }
            }

            System.out.println("--------" + fileNo + "-----" + usrId + "----" + fileName);
            System.out.println("--" + wfNo + "----" + cateId + "--" + taskId + "---" + prjtNo);


//			WfRd oleWfRd = new WfRd();
//			oleWfRd.setWfNo(wfNo);
//			oleWfRd = new WfRdFacade().findById(oleWfRd);

            if (flowid != null && "42".equals(flowid.trim())) {//如果是文档更新流程
                // 启动一个新流程
                // 42 归档文档更新流程

                // 创建流程和第一步骤
                Integer newFlowid = 42;
                WfCfg wc = new WfCfg();
                wc.setFlowId(newFlowid);
                wc = new WfCfgFacade().findById(wc);

                WfCfg wfCfg = new WfCfg();
                wfCfg.setFlowId(newFlowid);
                wfCfg = new WfCfgFacade().findById(wfCfg);

                // 生成流程
                WfRd wfRd = new WfRd();
                wfRd.setFlowId(newFlowid);
                wfRd.setNeedQues(wc.getNeedQues());
                wfRd.setWfName("文档更新");
                wfRd.setProjectNo(projectNo);
                wfRd.setScheId(scheId);
                wfRd.setDocCateId(cateId);
                wfRd.setPlanSDate(new Date());
                wfRd.setPlanEDate(new Date());
                wfRd.setCreateBy(syId);
                wfRd.setCreateDate(new Date());
                wfRd.setStatus(MSG.WFDOC_STATUS_DELETED_4);
                String newWfNo = new WfRdFacade().save(wfRd);
                wfRd = new WfRd();
                wfRd.setWfNo(newWfNo);
                wfRd = new WfRdFacade().findById(wfRd);

                // 得到第一个步骤
                WfStep step = new WfStep();
                step.setFlowId(newFlowid);
                step.setSort(1);
                step = new WfStepFacade().findBy(step);

                // 自动下一步
                List<WfRdTask> wfRdTasks = autoSendTask(step.getStepId(), null, wfCfg, wfRd, syId);
                if (!wfRdTasks.isEmpty()) {
                    // 更改文档信息
                    WfDoc doc = new WfDoc();
                    doc.setFileNo(fileNo);
                    doc.setDocName(fileName);
                    doc.setWfNo(wfRd.getWfNo());
                    doc.setProjectNo(prjtNo);
                    doc.setDocType(docType);
                    if (doc.getDocType() == MSG.WFDOC_DOCTYPE_PROC) {
                        doc.setStatus(MSG.WFDOC_STATUS_DELETED_1);
                    } else if (doc.getDocType() == MSG.WFDOC_DOCTYPE_BASELIB) {
                        doc.setStatus(MSG.WFDOC_STATUS_DELETED_4);
                    } else {
                        setMsg("doc type is null");
                        return ERROR;
                    }

                    doc.setCreateBy(Integer.parseInt(usrId));
                    doc.setCreateDate(new Date());

                    doc.setTaskId(wfRdTasks.get(0).getTaskId());
                    if (WFUtil.isNotNull(cateId)) {
                        doc.setCateId(Integer.parseInt(cateId));
                    }

                    new WfDocFacade().saveForVer(doc);
                }
            } else {


                WfDoc doc = new WfDoc();
                doc.setFileNo(fileNo);
                doc.setDocName(fileName);
                doc.setWfNo(wfNo);
                doc.setProjectNo(prjtNo);
                doc.setDocType(docType);
                if (doc.getDocType() == MSG.WFDOC_DOCTYPE_PROC) {
                    doc.setStatus(MSG.WFDOC_STATUS_DELETED_1);
                } else if (doc.getDocType() == MSG.WFDOC_DOCTYPE_BASELIB) {
                    doc.setStatus(MSG.WFDOC_STATUS_DELETED_2);
                } else {
                    setMsg("doc type is null");
                    return ERROR;
                }
                doc.setCreateBy(Integer.parseInt(usrId));
                doc.setCreateDate(new Date());

                if (WFUtil.isNotNull(taskId)) {
                    doc.setTaskId(Integer.parseInt(taskId));
                }
                if (WFUtil.isNotNull(cateId)) {
                    doc.setCateId(Integer.parseInt(cateId));
                }

                new WfDocFacade().saveForVer(doc);

            }

        } catch (Exception e) {
            setMsg(MSG.F_SEA);
            Logger.getLogger(this.getClass()).error("WfDocAction addto Exception", e);
            e.printStackTrace();
            return ERROR;
        }
        return null;
    }

    //TODO 自动流转部分
    //只有一步,查当前步骤
    protected List<WfRdTask> autoSendTask(int currentStepId, String taskId, WfCfg wfCfg, WfRd wfRd, Integer syId) throws Exception {
        List<WfStepUser> autos = null;

        if (WFUtil.isNull(autos)) {        //先查询项目角色对应者
            String prjt = wfRd.getProjectNo() == null ? "" : " or prjtNo='" + wfRd.getProjectNo() + "'";
            String autoSql = "select StepId,A.UsrId as UserId,UserType,PrjtRoleId from WfStepUser " +
                    " left join " +
                    " (select PrjtRole.RoleId,PrjtRole.roleNm,usrId from PrjtRole,PrjtUsr " +
                    " where PrjtUsr.RoleId = PrjtRole.RoleId and PrjtUsr.status!=0 and (roletyp=0 " + prjt + "))A " +
                    " on (WfStepUser.PrjtRoleId = A.RoleId)" +
                    " where stepId =" + currentStepId + " and (UserId>0 or WfStepUser.PrjtRoleId>0)";
            autos = new WfStepUserFacade().find(autoSql, "StepId,PrjtUsr.UsrId as UserId,UserType,WfStepUser.PrjtRoleId");
        }

        if (WFUtil.isNull(autos)) {        //如果为空，则查询自动流转用户
            String autoSql = "select StepId,UserId,UserType,PrjtRoleId from WfStepUser where stepId =" + currentStepId + " and (UserId>0 or WfStepUser.PrjtRoleId>0)";
            autos = new WfStepUserFacade().find(autoSql, "StepId,UserId,UserType,WfStepUser.PrjtRoleId");
        }

        //将角色换成具体的人
        for (int i = 0; i < autos.size(); i++) {
            WfStepUser user = autos.get(i);
            if (user != null && (user.getUserId() == null || user.getUserId().intValue() < 1) && user.getPrjtRoleId() != null && user.getPrjtRoleId().intValue() > 0) {
                autos.remove(i);
                i--;

                String roleSql = "select UsrId from PrjtUsr where status>0 and RoleId=" + user.getPrjtRoleId();
                if (wfRd.getProjectNo() != null && !"".equals(wfRd.getProjectNo())) {
                    roleSql += " and PrjtNo='" + wfRd.getProjectNo() + "'";
                }
                List<PrjtUsr> users = new PrjtUsrFacade().find(roleSql, "UsrId");
                if (users != null) {
                    for (PrjtUsr pusr : users) {
                        WfStepUser wsu = new WfStepUser();
                        wsu.setStepId(user.getStepId());
                        wsu.setUserType(user.getUserType());
                        wsu.setUserId(pusr.getUsrId());
                        autos.add(wsu);
                    }
                }
            }
        }

        List<WfRdTask> autoList = new ArrayList<WfRdTask>();
        List<Usr> userList = new ArrayList<Usr>();
        if (WFUtil.isNotNull(autos)) {        //不为空则自动转交
            for (int i = 0; i < autos.size(); i++) {
                WfStepUser s = autos.get(i);
                if (s != null && WFUtil.isNotNull(s.getStepId())) {
                    // 如果指定的是用户
                    if (WFUtil.isNotNull(s.getUserId())) {
                        WfRdTask t = createTask(null, syId, null, wfRd.getWfNo());
                        t.setAcceptBy(s.getUserId());
                        t.setStepId(s.getStepId());
                        t.setTaskType(s.getUserType());

                        t.setPreTaskId(null);
                        autoList.add(t);

                        Usr u = new Usr();
                        u.setId(s.getUserId());
                        userList.add(u);
                    }
                }
            }
        }

        if (WFUtil.isNotNull(autoList)) {        //不为空有自动流转
            new WfRdTaskFacade().saveAll(autoList);
            sendMail(userList, wfCfg, wfRd);
            return autoList;
        }

        //无自动流转返回false
        return autoList;
    }

    protected WfRdTask createTask(WfRdTask t, Integer userId, Integer preTaskId, String wfNo) {        //创建任务
        if (t == null) {
            t = new WfRdTask();
        }
        if (t.getReqDate() == null) {
            t.setReqDate(new Date());
        } else {
            if (WFUtil.diffDate(new Date(), t.getReqDate()) == null) {
                t.setReqDate(new Date());
            }
        }
        t.setCreateBy(userId);
        t.setCreateDate(new Date());
        t.setPreTaskId(preTaskId);
        t.setWfNo(wfNo);
        t.setStatus(MSG.OWFTASK_STATUS_0);
        return t;
    }

    //任务发邮件,提示提醒
    protected void sendMail(List<Usr> userList, WfCfg cfg, WfRd wfRd) throws Exception {
        if (WFUtil.isNotNull(userList)) {
            String webUrl = getWebUrl(wfRd);//+"/index.shtml?wfRd.wfNo="+wfRd.getWfNo();
            String title = "工作流任务--" + cfg.getFlowName() + "：" + wfRd.getWfNo();
            String content = "尊敬的同事，您好：" +
                    "<p>您有一条《" + cfg.getFlowName() + "》流程的任务需要办理。" +
                    "<p>工作流编号为：" + wfRd.getWfNo() + "，工作流名称：" + wfRd.getWfName() + "，请尽快处理，谢谢!" +
                    "<p>任务办理链接地址 ： <a href=" + webUrl + ">" + webUrl + "</a>";

            WFUtil.sendMailByIT(title, content, userList);
        }
    }

    /**
     * 得到URL
     */
    protected String getWebUrl(WfRd wfRd) {
        String basePath = getSysWebUrl() + "/index.shtml?wfRd.wfNo=" + wfRd.getWfNo();
        return basePath;
    }

    /**
     * 得到URL
     */
    protected String getSysWebUrl() {
        String basePath = ServletActionContext.getServletContext().getInitParameter("server_URL");
        return basePath;
    }

    public String edit() throws Exception {
        try {
            if (wfDoc != null && wfDoc.getDocId() != null) {
                wfDoc = new WfDocFacade().findById(wfDoc);
                setJson(JSON.toJSONString(wfDoc));
            }
            wfRdTasks = new gnwf.facade.WfRdTaskFacade().find("select " + gnwf.vo.WfRdTask.SELF_FIELDS + " from WfRdTask", gnwf.vo.WfRdTask.SELF_FIELDS);
            wfRds = new gnwf.facade.WfRdFacade().find("select " + gnwf.vo.WfRd.SELF_FIELDS + " from WfRd", gnwf.vo.WfRd.SELF_FIELDS);

        } catch (Exception e) {
            setMsg(MSG.F_SEA);
            Logger.getLogger(this.getClass()).error("WfDocAction Exception", e);
            return ERROR;
        }
        return PGEDI;
    }

    public String view() throws Exception {
        try {
            if (wfDoc != null && wfDoc.getDocId() != null) {
                wfDoc = new WfDocFacade().findById(wfDoc);
                setJson(JSON.toJSONString(wfDoc));
            }
        } catch (Exception e) {
            setMsg(MSG.F_SEA);
            Logger.getLogger(this.getClass()).error("WfDocAction Exception", e);
            return ERROR;
        }
        return PGVIE;
    }

    // public String list() throws Exception {
    // try {
    // if (wfDoc == null) wfDoc = new WfDoc();
    // int total = new WfDocFacade().amount(wfDoc);
    // if (getPage() == null) {
    // setPage(1);
    // setPagesize(20);
    // }
    // getPageVO().setPage(this.getPage());
    // getPageVO().setPageSize(this.getPagesize());
    // getPageVO().setTotal(total);
    // wfDocs = new WfDocFacade().find(wfDoc, getPageVO());
    // WfDocJson wfDocJson = new WfDocJson();
    // wfDocJson.Rows = wfDocs;
    // wfDocJson.Total = total;
    // setJson(JSON.toJSONString(wfDocJson));
    // } catch (Exception e) {
    // setMsg(MSG.F_SEA);
    // Logger.getLogger(this.getClass()).error("WfDocAction Exception", e);
    // return ERROR;
    // }
    // return PGLIS;
    // }

    public String list() throws Exception {
        try {
            if (wfDoc == null) wfDoc = new WfDoc();
            if (getPage() == null) {
                setPage(1);
                setPagesize(20);
            }
            if (wfStep == null) wfStep = new WfStep();

            int total = new WfDocFacade().amount(wfDoc, wfStep);
            getPageVO().setPage(this.getPage());
            getPageVO().setPageSize(this.getPagesize());
            getPageVO().setTotal(total);

            wfDocs = new WfDocFacade().find(wfDoc, wfStep, getPageVO());
            WfDocJson wfDocJson = new WfDocJson();
            wfDocJson.Rows = wfDocs;
            wfDocJson.Total = total;
            String jsonStr = JSON.toJSONString(wfDocJson);
            // System.out.println(jsonStr);
            setJson(jsonStr);
        } catch (Exception e) {
            setMsg(MSG.F_SEA);
            Logger.getLogger(this.getClass()).error("WfDocAction Exception", e);
            return ERROR;
        }
        return PGLIS;
    }

    /**
     * 文档管理相关的方法
     */

    public String main() {
        syId = String.valueOf(getUsrSession().getSyId());
        syNm = getUsrSession().getSyNm();
        usrId = String.valueOf(getUsrSession().getId());
        usrNm = getUsrSession().getUsrName();
        setPrjtRole();
        return "main";
    }

    public String pDoc() {
        return "pdoc";
    }

    private String syId;
    private String syNm;
    private String usrNm;
    private String tjson;

    public String bDoc() {
        syId = String.valueOf(getUsrSession().getSyId());
        syNm = getUsrSession().getSyNm();
        usrId = String.valueOf(getUsrSession().getId());
        usrNm = getUsrSession().getUsrName();
        setPrjtRole();
        return "bdoc";
    }

    public String sLib() {
        return "slib";
    }

    public String openPigeonholeWin() {
        try {
            if (tjson != null) {
                // 找到cateId
                StringBuilder cateIds = new StringBuilder();
                JSONArray arr = JSONArray.fromObject(tjson);
                for (int i = 0; i < arr.size(); i++) {
                    JSONObject obj = arr.getJSONObject(i);
                    int cateId = obj.getInt("cateId");
                    if (cateId > 0) {
                        if (cateIds.length() > 0) {
                            cateIds.append(",");
                        }
                        cateIds.append(cateId);
                    }

                }
                if (cateIds.length() > 0) {
                    StringBuilder sql = new StringBuilder();
                    sql.append("select ").append(WfScheCfgDoc.DOC_FIELDS).append(" FROM WfScheCfgDoc left join Usr on exists(select * from PrjtUsr where usr.id=prjtusr.usrid and WfScheCfgDoc.UserRole=PrjtUsr.RoleId and PrjtUsr.PrjtNo='").append(wfDoc.getProjectNo()).append("')");
                    sql.append(" where WfScheCfgDoc.DocId in(").append(cateIds).append(")");
                    List<WfScheCfgDoc> docs = new WfScheCfgDocFacade().find(sql.toString(), WfScheCfgDoc.DOC_FIELDS);
                    JSONArray outArr = new JSONArray();
                    for (WfScheCfgDoc doc : docs) {
                        JSONObject obj = new JSONObject();
                        obj.put("cateId", doc.getDocId());
                        obj.put("cateName", doc.getDocName());
                        obj.put("userRole", doc.getUserRole());
                        obj.put("usrId", doc.getUsrId());
                        obj.put("usrName", doc.getUsrName());
                        outArr.add(obj);
                    }
                    tjson = outArr.toString();
                }
            }
        } catch (Exception e) {
            setMsg("打开归档流程窗口失败");
            e.printStackTrace();
            setMsg(MSG.F_SAV);
            Logger.getLogger(this.getClass()).error("WfRdAddAction add Exception", e);
            return ERROR;
        }
        return "openPigeonholeWin";
    }

    public String openShareWin() {
        System.out.println(wfDoc);
        return "openShareWin";
    }

    public String openUpdateWin() {
        return "openUpdateWin";
    }

    public String openBSharaDetlWin() {
        return "openBSharaDetlWin";
    }


    String WfDocFields = "WfDoc.TaskId,WfDoc.DocId,WfDoc.FileNo,WfDoc.ProjectNo,WfDoc.Status," + "WfDoc.LastUpd,WfDoc.CateId,WfDoc.CreateBy,WfDoc.FlowId,WfDoc.DeptDocId," + "WfDoc.WfNo,WfDoc.UriLink,WfDoc.CreateDate,WfDoc.LastUpdDate,WfDoc.DocName,WfDoc.DocVer," + "Usr.UsrName,WfScheCfgDoc.DocName as CateName,WfRd.WfName as WfName";
    //String downloadSql = "(case when WfScheCfgDoc.DocId IN (SELECT DocCateId FROM GnWf.dbo.DocRole WHERE PrjtRoleId IN(SELECT RoleId FROM GnWf.dbo.PrjtUsr WHERE dbo.PrjtUsr.UsrId = '15441')) then 1 else 0 end)as downloadStatus, ";

    // 查找出项目的过程文档
    public String prjtProceFlieList() throws Exception {
        Integer userid = getUsrSession().getId();
        Boolean ProjectManager = false;
        for (Gp e : getUsrSession().getGps()) {
            if (e.getGpName().indexOf("项目经理") > -1) {
                ProjectManager = true;
                break;
            }
        }
        String PrjtUsrPrjtNo = wfDoc.getProjectNo();
        //用downloadStatus判断“项目经理是否能下载”2为可以下载3为不能下载
        String downloadSql = ",(case when (WfScheCfgDoc.DocId IN (SELECT DocCateId FROM GnWf.dbo.DocRole WHERE PrjtRoleId IN(SELECT RoleId FROM GnWf.dbo.PrjtUsr PrU WHERE PrU.UsrId  = '" + userid + "' and PrU.PrjtNo='" + PrjtUsrPrjtNo + "')or WfScheCfgDoc.DocId IN (select CateId  from WfDoc where WfDoc.CreateBy = '" + userid + "' and WfDoc.ProjectNo='" + PrjtUsrPrjtNo + "' ))) then 2 else 3 end)as downloadStatus ";

        String WfDocFields1 = WfDocFields + downloadSql;
        //区分是否可以看到下载文件
        String DocIdSQL = " ( WfScheCfgDoc.DocId IN (SELECT DocCateId FROM DocRole  WHERE DocRole.PrjtRoleId IN(SELECT RoleId FROM PrjtUsr WHERE PrjtUsr.UsrId = '" + userid + "' and PrjtUsr.PrjtNo='" + PrjtUsrPrjtNo + "')) or WfScheCfgDoc.DocId IN (select CateId  from WfDoc where WfDoc.CreateBy = '" + userid + "' and WfDoc.ProjectNo='" + PrjtUsrPrjtNo + "' ))";


        String sql = "";
        //区分是否可以下载
        System.out.println("判断是否项目经理-----" + ProjectManager);
        if (!ProjectManager) {
            sql = "select " + WfDocFields1 + " from WfDoc " + " left join usr on (WfDoc.CreateBy = usr.id) " + " left join WfScheCfgDoc on(WfScheCfgDoc.DocId=WfDoc.CateId) " + " left join WfRd on (WfRd.WfNo = WfDoc.WfNo) " + " left join WfCfg on (WfRd.FlowId = WfCfg.FlowId) " + " where 1=1  and" + DocIdSQL + " and WfDoc.Status=1 and WfDoc.DocType = " + MSG.WFDOC_DOCTYPE_PROC;
            sql += getQueryCondition();
        } else {
            sql = "select " + WfDocFields1 + " from WfDoc " + " left join usr on (WfDoc.CreateBy = usr.id) " + " left join WfScheCfgDoc on(WfScheCfgDoc.DocId=WfDoc.CateId) " + " left join WfRd on (WfRd.WfNo = WfDoc.WfNo) " + " left join WfCfg on (WfRd.FlowId = WfCfg.FlowId) " + " where 1=1  and WfDoc.Status=1 and WfDoc.DocType = " + MSG.WFDOC_DOCTYPE_PROC;
            sql += getQueryCondition() + " order by downloadStatus ";
        }

        System.out.println("显示下载的sql-------------" + sql);
        wfDocs = new WfDocFacade().find(sql, WfDocFields1 + ",downloadStatus");
        WfDocJson wfDocJson = new WfDocJson();
        wfDocJson.Rows = wfDocs;
        wfDocJson.Total = wfDocs.size();
        setJson(JSON.toJSONString(wfDocJson));
        System.out.println(JSON.toJSONString(wfDocJson));
        return PGLIS;
    }

    // 查找出项目的产品定义书附件
    public String prjtSurveyFlieList() throws Exception {
        Integer userid = getUsrSession().getId();
        Boolean ProjectManager = false;
        for (Gp e : getUsrSession().getGps()) {
            if (e.getGpName().indexOf("项目经理") > -1) {
                ProjectManager = true;
                break;
            }
        }
        String PrjtUsrPrjtNo = wfDoc.getProjectNo();
        //用downloadStatus判断“项目经理是否能下载”2为可以下载3为不能下载
        //String downloadSql = ",(case when (WfScheCfgDoc.DocId IN (SELECT DocCateId FROM GnWf.dbo.DocRole WHERE PrjtRoleId IN(SELECT RoleId FROM GnWf.dbo.PrjtUsr PrU WHERE PrU.UsrId  = '"+userid+"' and PrU.PrjtNo='"+PrjtUsrPrjtNo+"')or WfScheCfgDoc.DocId IN (select CateId  from WfDoc where WfDoc.CreateBy = '"+userid+"' and WfDoc.ProjectNo='"+PrjtUsrPrjtNo+"' ))) then 2 else 3 end)as downloadStatus ";

        String WfDocFields1 = WfDocFields;
        //区分是否可以看到下载文件
        String DocIdSQL = " ( WfScheCfgDoc.DocId IN (SELECT DocCateId FROM DocRole  WHERE DocRole.PrjtRoleId IN(SELECT RoleId FROM PrjtUsr WHERE PrjtUsr.UsrId = '" + userid + "' and PrjtUsr.PrjtNo='" + PrjtUsrPrjtNo + "')) or WfScheCfgDoc.DocId IN (select CateId  from WfDoc where WfDoc.CreateBy = '" + userid + "' and WfDoc.ProjectNo='" + PrjtUsrPrjtNo + "' ))";


        String sql = "";
        //区分是否可以下载
            /*System.out.println("判断是否项目经理-----"+ProjectManager);
            if(! ProjectManager) {
				 sql = "select " + WfDocFields1 +  " from WfDoc " + " left join usr on (WfDoc.CreateBy = usr.id) " + " left join WfScheCfgDoc on(WfScheCfgDoc.DocId=WfDoc.CateId) " + " left join WfRd on (WfRd.WfNo = WfDoc.WfNo) " + " left join WfCfg on (WfRd.FlowId = WfCfg.FlowId) " + " where 1=1  and"+ DocIdSQL +" and WfDoc.Status=1 and WfDoc.DocType = " + MSG.WFDOC_DOCTYPE_PROC;
				 sql += getQueryCondition();
			}else {*/
        sql = "select " + WfDocFields1 + " from WfDoc " + " left join usr on (WfDoc.CreateBy = usr.id) " + " left join WfScheCfgDoc on(WfScheCfgDoc.DocId=WfDoc.CateId) " + " left join WfRd on (WfRd.WfNo = WfDoc.WfNo) " + " left join WfCfg on (WfRd.FlowId = WfCfg.FlowId) " + " where 1=1  and WfDoc.Status=1 and WfDoc.DocType = " + MSG.WFDOC_DOCTYPE_DEFINDOS;
        sql += "and  wfDoc.projectNo = '" + PrjtUsrPrjtNo + "' order by WfDoc.CreateDate desc";
            /*}*/

        System.out.println("显示下载的sql-------------" + sql);
        wfDocs = new WfDocFacade().find(sql, WfDocFields1);
        WfDocJson wfDocJson = new WfDocJson();
        wfDocJson.Rows = wfDocs;
        wfDocJson.Total = wfDocs.size();
        setJson(JSON.toJSONString(wfDocJson));
        System.out.println(JSON.toJSONString(wfDocJson));
        return PGLIS;
    }

    // 查找出项目的项目进度表附件
    public String prjtPointFlieList() throws Exception {
        Integer userid = getUsrSession().getId();
        Boolean ProjectManager = false;
        for (Gp e : getUsrSession().getGps()) {
            if (e.getGpName().indexOf("项目经理") > -1) {
                ProjectManager = true;
                break;
            }
        }
        String PrjtUsrPrjtNo = wfDoc.getProjectNo();
        //用downloadStatus判断“项目经理是否能下载”2为可以下载3为不能下载
        //String downloadSql = ",(case when (WfScheCfgDoc.DocId IN (SELECT DocCateId FROM GnWf.dbo.DocRole WHERE PrjtRoleId IN(SELECT RoleId FROM GnWf.dbo.PrjtUsr PrU WHERE PrU.UsrId  = '"+userid+"' and PrU.PrjtNo='"+PrjtUsrPrjtNo+"')or WfScheCfgDoc.DocId IN (select CateId  from WfDoc where WfDoc.CreateBy = '"+userid+"' and WfDoc.ProjectNo='"+PrjtUsrPrjtNo+"' ))) then 2 else 3 end)as downloadStatus ";

        String WfDocFields1 = WfDocFields;
        //区分是否可以看到下载文件
        String DocIdSQL = " ( WfScheCfgDoc.DocId IN (SELECT DocCateId FROM DocRole  WHERE DocRole.PrjtRoleId IN(SELECT RoleId FROM PrjtUsr WHERE PrjtUsr.UsrId = '" + userid + "' and PrjtUsr.PrjtNo='" + PrjtUsrPrjtNo + "')) or WfScheCfgDoc.DocId IN (select CateId  from WfDoc where WfDoc.CreateBy = '" + userid + "' and WfDoc.ProjectNo='" + PrjtUsrPrjtNo + "' ))";


        String sql = "";
        //区分是否可以下载
                    /*System.out.println("判断是否项目经理-----"+ProjectManager);
                    if(! ProjectManager) {
						 sql = "select " + WfDocFields1 +  " from WfDoc " + " left join usr on (WfDoc.CreateBy = usr.id) " + " left join WfScheCfgDoc on(WfScheCfgDoc.DocId=WfDoc.CateId) " + " left join WfRd on (WfRd.WfNo = WfDoc.WfNo) " + " left join WfCfg on (WfRd.FlowId = WfCfg.FlowId) " + " where 1=1  and"+ DocIdSQL +" and WfDoc.Status=1 and WfDoc.DocType = " + MSG.WFDOC_DOCTYPE_PROC;
						 sql += getQueryCondition();
					}else {*/
        sql = "select " + WfDocFields1 + " from WfDoc " + " left join usr on (WfDoc.CreateBy = usr.id) " + " left join WfScheCfgDoc on(WfScheCfgDoc.DocId=WfDoc.CateId) " + " left join WfRd on (WfRd.WfNo = WfDoc.WfNo) " + " left join WfCfg on (WfRd.FlowId = WfCfg.FlowId) " + " where 1=1  and WfDoc.Status=1 and WfDoc.DocType = " + MSG.WFDOC_DOCTYPE_PRJTPOINT;
        sql += "and  wfDoc.projectNo = '" + PrjtUsrPrjtNo + "' order by WfDoc.CreateDate desc";
                    /*}*/

        System.out.println("显示下载的sql-------------" + sql);
        wfDocs = new WfDocFacade().find(sql, WfDocFields1);
        WfDocJson wfDocJson = new WfDocJson();
        wfDocJson.Rows = wfDocs;
        wfDocJson.Total = wfDocs.size();
        setJson(JSON.toJSONString(wfDocJson));
        System.out.println(JSON.toJSONString(wfDocJson));
        return PGLIS;
    }

    // 查找出项目的产品定义书附件当前最新版本
    public String prjtSurveyNewFlie() throws Exception {
        Integer userid = getUsrSession().getId();
        Boolean ProjectManager = false;
        for (Gp e : getUsrSession().getGps()) {
            if (e.getGpName().indexOf("项目经理") > -1) {
                ProjectManager = true;
                break;
            }
        }
        String PrjtUsrPrjtNo = wfDoc.getProjectNo();
        //用downloadStatus判断“项目经理是否能下载”2为可以下载3为不能下载
        //String downloadSql = ",(case when (WfScheCfgDoc.DocId IN (SELECT DocCateId FROM GnWf.dbo.DocRole WHERE PrjtRoleId IN(SELECT RoleId FROM GnWf.dbo.PrjtUsr PrU WHERE PrU.UsrId  = '"+userid+"' and PrU.PrjtNo='"+PrjtUsrPrjtNo+"')or WfScheCfgDoc.DocId IN (select CateId  from WfDoc where WfDoc.CreateBy = '"+userid+"' and WfDoc.ProjectNo='"+PrjtUsrPrjtNo+"' ))) then 2 else 3 end)as downloadStatus ";

        String WfDocFields1 = WfDocFields;
        //区分是否可以看到下载文件
        String DocIdSQL = " ( WfScheCfgDoc.DocId IN (SELECT DocCateId FROM DocRole  WHERE DocRole.PrjtRoleId IN(SELECT RoleId FROM PrjtUsr WHERE PrjtUsr.UsrId = '" + userid + "' and PrjtUsr.PrjtNo='" + PrjtUsrPrjtNo + "')) or WfScheCfgDoc.DocId IN (select CateId  from WfDoc where WfDoc.CreateBy = '" + userid + "' and WfDoc.ProjectNo='" + PrjtUsrPrjtNo + "' ))";


        String sql = "";
        //区分是否可以下载
					/*System.out.println("判断是否项目经理-----"+ProjectManager);
					if(! ProjectManager) {
						 sql = "select " + WfDocFields1 +  " from WfDoc " + " left join usr on (WfDoc.CreateBy = usr.id) " + " left join WfScheCfgDoc on(WfScheCfgDoc.DocId=WfDoc.CateId) " + " left join WfRd on (WfRd.WfNo = WfDoc.WfNo) " + " left join WfCfg on (WfRd.FlowId = WfCfg.FlowId) " + " where 1=1  and"+ DocIdSQL +" and WfDoc.Status=1 and WfDoc.DocType = " + MSG.WFDOC_DOCTYPE_PROC;
						 sql += getQueryCondition();
					}else {*/
        sql = "select top 1 " + WfDocFields1 + " from WfDoc " + " left join usr on (WfDoc.CreateBy = usr.id) " + " left join WfScheCfgDoc on(WfScheCfgDoc.DocId=WfDoc.CateId) " + " left join WfRd on (WfRd.WfNo = WfDoc.WfNo) " + " left join WfCfg on (WfRd.FlowId = WfCfg.FlowId) " + " where 1=1  and WfDoc.Status=1 and WfDoc.DocType = " + MSG.WFDOC_DOCTYPE_DEFINDOS;
        sql += " and  wfDoc.projectNo = '" + PrjtUsrPrjtNo + "' order by WfDoc.CreateDate desc";
					/*}*/

        System.out.println("显示下载的sql-------------" + sql);
        wfDocs = new WfDocFacade().find(sql, WfDocFields1);
        WfDocJson wfDocJson = new WfDocJson();
        wfDocJson.Rows = wfDocs;
        wfDocJson.Total = wfDocs.size();
        setJson(JSON.toJSONString(wfDocJson));
        System.out.println(JSON.toJSONString(wfDocJson));
        return PGLIS;
    }


    // 查找出项目的基线库文档
    public String prjtBaseLibList() throws Exception {

        Integer userid = getUsrSession().getId();
        wfDocs = new ArrayList<>();
        //System.out.println("userID---------"+userid);
        //判断是否为“项目经理角色”是的话全部显示出来

        Boolean ProjectManager = false;
        for (Gp e : getUsrSession().getGps()) {
            if (e.getGpName().indexOf("项目经理") > -1) {
                ProjectManager = true;
                break;
            }
        }
        //判断只能下载自己创建和自己拥有权限的文档
        String creatBySql = "( WfScheCfgDoc.DocId IN (SELECT DocCateId FROM GnWf.dbo.DocRole WHERE PrjtRoleId IN(SELECT RoleId FROM GnWf.dbo.PrjtUsr WHERE dbo.PrjtUsr.UsrId = " + userid + "and PrjtUsr.PrjtNo='" + wfDoc.getProjectNo() + "'))or WfScheCfgDoc.DocId IN (select CateId  from WfDoc where WfDoc.CreateBy = '" + userid + "' and WfDoc.ProjectNo='" + wfDoc.getProjectNo() + "' ))";
        String sql = "";
        if (!ProjectManager) {
            sql = "SELECT DocId ,DocName FROM dbo.WfScheCfgDoc WHERE " + creatBySql + (wfDoc.getCateId() != null ? " and WfScheCfgDoc.DocId=" + wfDoc.getCateId() : "") + "group by dbo.WfScheCfgDoc.DocName, dbo.WfScheCfgDoc.DocId";
        } else {
            sql = "SELECT DocId ,DocName FROM dbo.WfScheCfgDoc " + (wfDoc.getCateId() != null ? " and WfScheCfgDoc.DocId=" + wfDoc.getCateId() : "") + "group by dbo.WfScheCfgDoc.DocName, dbo.WfScheCfgDoc.DocId";

        }
        //System.out.println("-----sql------"+sql);
        // 选出文档类型 WfScheCfgDoc判断是否有该角色权限，有才会显示出来
        //List<WfScheCfgDoc> wfScheCfgDocs = new WfScheCfgDocFacade().find("select WfScheCfgDoc.DocId,WfScheCfgDoc.DocName from WfScheCfgDoc" + (wfDoc.getCateId() != null ? " where WfScheCfgDoc.DocId=" + wfDoc.getCateId() : ""), "WfScheCfgDoc.DocId,WfScheCfgDoc.DocName");
        List<WfScheCfgDoc> wfScheCfgDocs = new WfScheCfgDocFacade().find(sql, "WfScheCfgDoc.DocId,WfScheCfgDoc.DocName");

        for (WfScheCfgDoc wscd : wfScheCfgDocs) {
            // 得到类别 wscd 中, 版本DocVer 最大的 WfDoc.DocId
            StringBuilder sb = new StringBuilder();
            sb.append("select top 1 ").append(WfDocFields).append(" from WfDoc ");
            sb.append("left join usr on (WfDoc.CreateBy = usr.id) ");
            sb.append("left join WfScheCfgDoc on(WfScheCfgDoc.DocId=WfDoc.CateId) ");
            // sb.append("inner join BaseLib on(WfDoc.FileNo = BaseLib.FileNo) ");//
            sb.append("left join WfRd on (WfRd.WfNo = WfDoc.WfNo) ");
            sb.append("left join WfCfg on (WfRd.FlowId = WfCfg.FlowId) ");
            sb.append("where 1=1 ");
            sb.append("and WfDoc.DocType = " + MSG.WFDOC_DOCTYPE_BASELIB);
            sb.append(getQueryCondition());
            sb.append(" and WfScheCfgDoc.DocId=").append(wscd.getDocId());
            sb.append(" order by WfDoc.DocVer desc");
            List<WfDoc> wfDocs1 = new WfDocFacade().find(sb.toString(), WfDocFields);
            if (wfDocs1 != null && !wfDocs1.isEmpty()) {
                wfDocs.add(wfDocs1.get(0));
            } else {
                WfDoc d1 = new WfDoc();
                d1.setCateName(wscd.getDocName());
                d1.setCateId(wscd.getDocId());
                wfDocs.add(d1);
            }
        }
        WfDocJson wfDocJson = new WfDocJson();
        wfDocJson.Rows = wfDocs;
        wfDocJson.Total = wfDocs.size();
        setJson(JSON.toJSONString(wfDocJson));
        System.out.println(JSON.toJSONString(wfDocJson));
        return PGLIS;
    }

    /**
     * 查它的基线库文档所有版本 WfDoc.projectNo,wfDoc.cateId
     */
    public String prjtBaseLib1Dtl() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("select ").append(WfDocFields).append(" from WfDoc ");
        sb.append("left join usr on (WfDoc.CreateBy = usr.id) ");
        sb.append("left join WfScheCfgDoc on(WfScheCfgDoc.DocId=WfDoc.CateId) ");
        // sb.append("inner join BaseLib on(WfDoc.FileNo = BaseLib.FileNo) ");//
        sb.append("left join WfRd on (WfRd.WfNo = WfDoc.WfNo) ");
        sb.append("left join WfCfg on (WfRd.FlowId = WfCfg.FlowId) ");
        sb.append("where 1=1 ");
        sb.append("and WfDoc.DocType=").append(MSG.WFDOC_STATUS_DELETED_2).append(" ");
        sb.append(getQueryCondition());
        sb.append(" order by WfDoc.DocVer");
        List<WfDoc> wfDocs = new WfDocFacade().find(sb.toString(), WfDocFields);
        WfDocJson wfDocJson = new WfDocJson();
        wfDocJson.Rows = wfDocs;
        wfDocJson.Total = wfDocs.size();
        setJson(JSON.toJSONString(wfDocJson));
        System.out.println(JSON.toJSONString(wfDocJson));
        return PGLIS;
    }

    private String getQueryCondition() {
        StringBuilder sb = new StringBuilder();
        if (null != wfDoc.getProjectNo() && !wfDoc.getProjectNo().trim().isEmpty()) {
            sb.append(" and  WfDoc.projectNo='").append(wfDoc.getProjectNo().trim()).append("' ");
        }
        if (null != wfDoc.getDocName() && !wfDoc.getDocName().trim().isEmpty()) {
            sb.append(" and  WfDoc.DocName like '%").append(wfDoc.getDocName().trim()).append("%' ");
        }
        if (null != wfDoc.getCateId()) {
            sb.append(" and  WfScheCfgDoc.DocId  =").append(wfDoc.getCateId());
        }
        if (null != wfDoc.getFlowId()) {
            sb.append(" and  WfCfg.FlowId =").append(wfDoc.getFlowId());
        }
        return sb.toString();
    }

    private WfRd wfRd;
    private String userMap;// 文档类型和归档人对应的数据
    private String prjtNo;

    // 归档基线库，启动归档流程
    public String archiveBaseLib() throws Exception {
        try {
            PrjtDef prjt = new PrjtDef();
            prjt.setPrjtNo(prjtNo);
            prjt = new PrjtDefFacade().findById(prjt);
            // if(userMap!=null){
            JSONArray array = JSONArray.fromObject(userMap);// 先读取串数组
            Object[] fs = array.toArray();
            // 转成对像数组
            for (int i = 0; i < fs.length; i++) {
                JSONObject obj = JSONObject.fromObject(fs[i]);// 再使用JsonObject遍历一个个的对像
                String cate_Id = String.valueOf(obj.get("cateId"));
                String task_UsrId = String.valueOf(obj.get("usrId"));
                String wfName = "项目" + prjt.getPrjtNm() + String.valueOf(obj.get("cateName")) + "归档流程";

                wfRd = new WfRd();
                wfRd.setFlowId(39); // 归档流程flowId =39
                wfRd.setWfName(wfName);
                wfRd.setStatus(MSG.CONST_STATUS_1);
                wfRd.setCreateBy(getUsrSession().getId());
                wfRd.setCreateDate(new Date());
                wfRd.setDocCateId(cate_Id);
                wfRd.setProjectNo(prjtNo);
                new WfRdFacade().save(wfRd);

                // 发第一个任务、跳转回任务页面
                WfRdTask task = new WfRdTask();
                task.setCreateBy(-1);
                task.setCreateDate(new Date());
                task.setReqDate(new Date());
                task.setAcceptBy(Integer.valueOf(task_UsrId));
                // task.setAcceptDate(new Date());
                task.setStatus(MSG.OWFTASK_STATUS_1);
                task.setTaskType(MSG.OWFTASK_TYPE_1);
                task.setWfNo(wfRd.getWfNo());
                task.setDocCateId(cate_Id);

                WfStep step = new WfStep();
                step.setFlowId(wfRd.getFlowId());
                step.setSort(1);
                step = new WfStepFacade().findBy(step);
                if (step != null) {
                    task.setStepId(step.getStepId());
                    new WfRdTaskFacade().save(task);
                }
            }
            // this.sendMessage(MSG.S_SAV, "WfRdView!wfRdView.shtml?wfRd.wfNo=" + wfRd.getWfNo());
            setMsg("归档流程已启动");
            return MESSAGE;
            // }else{
            // th
            // }

            // else { // 更改
            // if (wfRd != null && wfRd.getStatus() != null && wfRd.getStatus() == MSG.OWFRD_STATUS_2) {
            // wfRd.setEndFactEDate(new Date());
            // }
            //
            // wfRd.setLastUpd(getUsrSession().getId());
            // wfRd.setLastUpdDate(new Date());
            // new WfRdFacade().update(wfRd);
            // setMsg(MSG.S_SAV);
            // return "msg";
            // }
        } catch (Exception e) {
            setMsg("归档流程启动失败");
            e.printStackTrace();
            setMsg(MSG.F_SAV);
            Logger.getLogger(this.getClass()).error("WfRdAddAction add Exception", e);
            return ERROR;
        }
    }

    // 发布文档给某些人下载
    private String selectUsrs;

    public String releaseBaseLib() throws Exception {

        try {
            wfDoc = new WfDocFacade().findById(wfDoc);
            if (wfDoc == null) {
                setMsg("不存在这个文档");
                return ERROR;
            }
            String usr[] = selectUsrs.split(",");
            ShareRelaFacade facade = new ShareRelaFacade();
            for (int i = 0; i < usr.length; i++) {
                ShareRela qShareRela = new ShareRela();
                qShareRela.setWfDocId(wfDoc.getDocId());
                qShareRela.setUsrId(Integer.valueOf(usr[i]));
                ShareRela dbShareRela = facade.findBy(qShareRela);
                if (dbShareRela == null) {// 不存在 添加
                    ShareRela sShareRela = new ShareRela();
                    sShareRela.setWfDocId(wfDoc.getDocId());
                    sShareRela.setUsrId(Integer.valueOf(usr[i]));
                    sShareRela.setStatus(MSG.SHARE_RELA_STATUS_1);
                    sShareRela.setCreateBy(getUsrSession().getId());
                    sShareRela.setCreateDate(new Date());
                    facade.save(sShareRela);
                } else {// 存在, 修改
                    dbShareRela.setStatus(MSG.SHARE_RELA_STATUS_1);
                    dbShareRela.setLastUpd(getUsrSession().getId());
                    dbShareRela.setLastDate(new Date());
                    facade.update(dbShareRela);
                }
            }
            wfDoc.setStatus(MSG.WFDOC_STATUS_DELETED_7);
            new WfDocFacade().update(wfDoc);
            setMsg("发布成功");
        } catch (Exception e) {
            setMsg("发布失败");
            Logger.getLogger(this.getClass()).error("transToBasicLib Exception", e);
            e.printStackTrace();
            return ERROR;
        }
        return MESSAGE;

    }

    // 查找出发布给自己的文档
    public String prjtShareLib() throws Exception {

        String sql = "select " + WfDocFields + " " + " from ShareRela " + " left join WfDoc on(WfDoc.DocId = ShareRela.WfDocId) " + " left join usr on (WfDoc.CreateBy = usr.id) " + " left join WfScheCfgDoc on(WfScheCfgDoc.DocId=WfDoc.CateId) " + " left join WfRd on (WfRd.WfNo = WfDoc.WfNo) " + " left join WfCfg on (WfRd.FlowId = WfCfg.FlowId) " + " where 1=1 and ShareRela.UsrId = " + getUsrSession().getId() + " and WfDoc.DocType = " + MSG.WFDOC_DOCTYPE_BASELIB;
        if (null != wfDoc.getProjectNo() && !wfDoc.getProjectNo().trim().isEmpty()) {
            sql += " and  WfDoc.projectNo='" + wfDoc.getProjectNo().trim() + "' ";
        }
        sql += getQueryCondition();

        wfDocs = new WfDocFacade().find(sql, WfDocFields);

        WfDocJson wfDocJson = new WfDocJson();
        wfDocJson.Rows = wfDocs;
        wfDocJson.Total = wfDocs.size();
        setJson(JSON.toJSONString(wfDocJson));
        System.out.println(JSON.toJSONString(wfDocJson));
        return PGLIS;
    }

    /**
     * 文档管理相关的方法
     */

    // TODO 锟斤拷目锟斤拷锟揭癸拷锟斤拷锟斤拷锟斤拷锟斤拷
    public String selectPrjtWfDoc() throws Exception {
        if ((null == projectNo)) {
            return null;
        }

        if (projectNo.trim().isEmpty()) {
            return null;
        }

        String str = "";
        if (null != scheId) {
            str = " and scheid=" + scheId;
        }
        String sql = "select " + WfDoc.LIST_FIELDS + " " + new WfDocHelper().getSqlString() + " and wfrdtask.wfno in(select distinct wfrd.wfno from wfrd where wfrd.projectNo='" + projectNo + "' " + str + ");";
        wfDocs = new WfDocFacade().find(sql, WfDoc.LIST_FIELDS);

        WfDocJson wfDocJson = new WfDocJson();
        wfDocJson.Rows = wfDocs;
        wfDocJson.Total = wfDocs.size();
        setJson(JSON.toJSONString(wfDocJson));
        System.out.println(JSON.toJSONString(wfDocJson));
        return PGLIS;
    }

    public String sav() throws Exception {
        try {
            // wfDoc.setCreateBy(getSession().getUserId());
            // wfDoc.setCreateDate(new Date());
            // wfDoc.setLastUpd(getSession().getUserId());
            // wfDoc.setLastUpdDate(new Date());
            wfDoc.setWfDocRevs(wfDocRevs);

            if (wfDoc.getDocId() == null) new WfDocFacade().save(wfDoc);
            else new WfDocFacade().update(wfDoc);
            setMsg(MSG.S_SAV);
        } catch (Exception e) {
            setMsg(MSG.F_SAV);
            Logger.getLogger(this.getClass()).error("WfDocAddAction add Exception", e);
            return ERROR;
        }
        return MESSAGE;
    }

    public String upd() throws Exception {
        try {
            // wfDoc.setCreateBy(getSession().getUserId());
            // wfDoc.setCreateDate(new Date());
            // wfDoc.setLastUpd(getSession().getUserId());
            // wfDoc.setLastUpdDate(new Date());
            new WfDocFacade().update(wfDoc);
            setMsg(MSG.S_UPD);
        } catch (Exception e) {
            setMsg(MSG.F_UPD);
            Logger.getLogger(this.getClass()).error("WfDocAddAction add Exception", e);
            return ERROR;
        }
        return MESSAGE;
    }

    public String voi() throws Exception {
        try {
            if (wfDocs != null && wfDocs.size() > 0) {
                for (int i = 0; i < wfDocs.size(); i++) {
                    if (wfDocs.get(i) != null) {
                        // wfDocs.get(i).setLastUpd(getSession().getUserId());
                        // wfDocs.get(i).setLastUpdDate(new Date());
                        new WfDocFacade().submit(wfDocs.get(i));
                    }
                }
            }
            setMsg(MSG.S_VOI);
        } catch (Exception e) {
            setMsg(MSG.F_VOI);
            Logger.getLogger(this.getClass()).error("WfDocAction Exception", e);
            return ERROR;
        }
        return MESSAGE;
    }

    public String can() throws Exception {
        try {
            if (wfDocs != null && wfDocs.size() > 0) {
                for (int i = 0; i < wfDocs.size(); i++) {
                    if (wfDocs.get(i) != null) {
                        // wfDocs.get(i).setLastUpd(getSession().getUserId());
                        // wfDocs.get(i).setLastUpdDate(new Date());
                        new WfDocFacade().submit(wfDocs.get(i));
                    }
                }
            }
            setMsg(MSG.S_CAN);
        } catch (Exception e) {
            setMsg(MSG.F_CAN);
            Logger.getLogger(this.getClass()).error("WfDocAction Exception", e);
            return ERROR;
        }
        return MESSAGE;
    }

    public String del() throws Exception {
        try {
            if (wfDocs != null && wfDocs.size() > 0) {
                for (int i = 0; i < wfDocs.size(); i++) {
                    if (wfDocs.get(i) != null) {
                        // wfDocs.get(i).setLastUpd(getSession().getUserId());
                        // wfDocs.get(i).setLastUpdDate(new Date());
                        new WfDocFacade().update(wfDocs.get(i));
                    }
                }
            }
            setMsg(MSG.S_DEL);
        } catch (Exception e) {
            setMsg(MSG.F_DEL);
            Logger.getLogger(this.getClass()).error("WfDocAction Exception", e);
            return ERROR;
        }
        return MESSAGE;
    }

    public String sub() throws Exception {
        try {
            if (wfDocs != null && wfDocs.size() > 0) {
                for (int i = 0; i < wfDocs.size(); i++) {
                    if (wfDocs.get(i) != null) {
                        // wfDocs.get(i).setLastUpd(getSession().getUserId());
                        // wfDocs.get(i).setLastUpdDate(new Date());
                        new WfDocFacade().submit(wfDocs.get(i));
                    }
                }
            }
            setMsg(MSG.S_SUB);
        } catch (Exception e) {
            setMsg(MSG.F_SUB);
            Logger.getLogger(this.getClass()).error("WfDocAction Exception", e);
            return ERROR;
        }
        return MESSAGE;
    }

    public String sta() throws Exception {
        try {
            if (wfDocs != null && wfDocs.size() > 0) {
                for (int i = 0; i < wfDocs.size(); i++) {
                    if (wfDocs.get(i) != null) {
                        // wfDocs.get(i).setLastUpd(getSession().getUserId());
                        // wfDocs.get(i).setLastUpdDate(new Date());
                        new WfDocFacade().submit(wfDocs.get(i));
                    }
                }
            }
            setMsg(MSG.S_STA);
        } catch (Exception e) {
            setMsg(MSG.F_STA);
            Logger.getLogger(this.getClass()).error("WfDocAction Exception", e);
            return ERROR;
        }
        return MESSAGE;
    }

    public String loa() throws Exception {
        try {
            if (wfDocs != null && wfDocs.size() > 0) {
                for (int i = 0; i < wfDocs.size(); i++) {
                    if (wfDocs.get(i) != null) {
                        // wfDocs.get(i).setLastUpd(getSession().getUserId());
                        // wfDocs.get(i).setLastUpdDate(new Date());
                        new WfDocFacade().submit(wfDocs.get(i));
                    }
                }
            }
            setMsg(MSG.S_LOA);
        } catch (Exception e) {
            setMsg(MSG.F_LOA);
            Logger.getLogger(this.getClass()).error("WfDocAction Exception", e);
            return ERROR;
        }
        return MESSAGE;
    }

    public String stp() throws Exception {
        try {
            if (wfDocs != null && wfDocs.size() > 0) {
                for (int i = 0; i < wfDocs.size(); i++) {
                    if (wfDocs.get(i) != null) {
                        // wfDocs.get(i).setLastUpd(getSession().getUserId());
                        // wfDocs.get(i).setLastUpdDate(new Date());
                        new WfDocFacade().submit(wfDocs.get(i));
                    }
                }
            }
            setMsg(MSG.S_STP);
        } catch (Exception e) {
            setMsg(MSG.F_STP);
            Logger.getLogger(this.getClass()).error("WfDocAction Exception", e);
            return ERROR;
        }
        return MESSAGE;
    }

    public String ove() throws Exception {
        try {
            if (wfDocs != null && wfDocs.size() > 0) {
                for (int i = 0; i < wfDocs.size(); i++) {
                    if (wfDocs.get(i) != null) {
                        // wfDocs.get(i).setLastUpd(getSession().getUserId());
                        // wfDocs.get(i).setLastUpdDate(new Date());
                        new WfDocFacade().submit(wfDocs.get(i));
                    }
                }
            }
            setMsg(MSG.S_OVE);
        } catch (Exception e) {
            setMsg(MSG.F_OVE);
            Logger.getLogger(this.getClass()).error("WfDocAction Exception", e);
            return ERROR;
        }
        return MESSAGE;
    }

    public String chk() throws Exception {
        try {
            if (wfDocs != null && wfDocs.size() > 0) {
                for (int i = 0; i < wfDocs.size(); i++) {
                    if (wfDocs.get(i) != null) {
                        // wfDocs.get(i).setLastUpd(getSession().getUserId());
                        // wfDocs.get(i).setLastUpdDate(new Date());
                        new WfDocFacade().review(wfDocs.get(i));
                    }
                }
            }
            setMsg(MSG.S_CHK);
        } catch (Exception e) {
            setMsg(MSG.F_CHK);
            Logger.getLogger(this.getClass()).error("WfDocAction Exception", e);
            return ERROR;
        }
        return MESSAGE;
    }

    public String rev() throws Exception {
        try {
            if (wfDocs != null && wfDocs.size() > 0) {
                for (int i = 0; i < wfDocs.size(); i++) {
                    if (wfDocs.get(i) != null) {
                        // wfDocs.get(i).setLastUpd(getSession().getUserId());
                        // wfDocs.get(i).setLastUpdDate(new Date());
                        new WfDocFacade().review(wfDocs.get(i));
                    }
                }
            }
            setMsg(MSG.S_REV);
        } catch (Exception e) {
            setMsg(MSG.F_REV);
            Logger.getLogger(this.getClass()).error("WfDocAction Exception", e);
            return ERROR;
        }
        return MESSAGE;
    }

    public String con() throws Exception {
        try {
            if (wfDocs != null && wfDocs.size() > 0) {
                for (int i = 0; i < wfDocs.size(); i++) {
                    if (wfDocs.get(i) != null) {
                        // wfDocs.get(i).setLastUpd(getSession().getUserId());
                        // wfDocs.get(i).setLastUpdDate(new Date());
                        new WfDocFacade().confirm(wfDocs.get(i));
                    }
                }
            }
            setMsg(MSG.S_CON);
        } catch (Exception e) {
            setMsg(MSG.F_CON);
            Logger.getLogger(this.getClass()).error("WfDocAction Exception", e);
            return ERROR;
        }
        return MESSAGE;
    }

    public String iss() throws Exception {
        try {
            if (wfDocs != null && wfDocs.size() > 0) {
                for (int i = 0; i < wfDocs.size(); i++) {
                    if (wfDocs.get(i) != null) {
                        // wfDocs.get(i).setLastUpd(getSession().getUserId());
                        // wfDocs.get(i).setLastUpdDate(new Date());
                        new WfDocFacade().confirm(wfDocs.get(i));
                    }
                }
            }
            setMsg(MSG.S_ISS);
        } catch (Exception e) {
            setMsg(MSG.F_ISS);
            Logger.getLogger(this.getClass()).error("WfDocAction Exception", e);
            return ERROR;
        }
        return MESSAGE;
    }

    public String imp() throws Exception {
        try {
            if (fileInp != null) {
                if (wfDocs != null && wfDocs.size() > 0) {
                    for (int i = 0; i < wfDocs.size(); i++) {
                        if (wfDocs.get(i) != null) {
                            // wfDocs.get(i).setLastUpd(getSession().getUserId());
                            // wfDocs.get(i).setLastUpdDate(new Date());
                            new WfDocFacade().confirm(wfDocs.get(i));
                        }
                    }
                }
                setMsg(MSG.S_IMP);
            }
        } catch (Exception e) {
            setMsg(MSG.F_IMP);
            Logger.getLogger(this.getClass()).error("WfDocAction Exception", e);
            return ERROR;
        }
        return PGUPL;
    }

    public String exp() throws Exception {
        try {
            List<WfDoc> wfDocs = new WfDocFacade().find(wfDoc);
            if (wfDocs != null && wfDocs.size() > 0) {
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

                ws.addCell(new Label(index, 1, "�ĵ�ID", wcformat));
                ws.setColumnView(index, 20);
                index++;
                ws.addCell(new Label(index, 1, "����ID", wcformat));
                ws.setColumnView(index, 20);
                index++;
                ws.addCell(new Label(index, 1, "״̬", wcformat));
                ws.setColumnView(index, 20);
                index++;
                ws.addCell(new Label(index, 1, "������", wcformat));
                ws.setColumnView(index, 20);
                index++;
                ws.addCell(new Label(index, 1, "������", wcformat));
                ws.setColumnView(index, 20);
                index++;
                ws.addCell(new Label(index, 1, "null", wcformat));
                ws.setColumnView(index, 20);
                index++;
                ws.addCell(new Label(index, 1, "null", wcformat));
                ws.setColumnView(index, 20);
                index++;
                ws.addCell(new Label(index, 1, "null", wcformat));
                ws.setColumnView(index, 20);
                index++;
                ws.addCell(new Label(index, 1, "���������", wcformat));
                ws.setColumnView(index, 20);
                index++;
                ws.addCell(new Label(index, 1, "URI����", wcformat));
                ws.setColumnView(index, 20);
                index++;
                ws.addCell(new Label(index, 1, "��������", wcformat));
                ws.setColumnView(index, 20);
                index++;
                ws.addCell(new Label(index, 1, "��������", wcformat));
                ws.setColumnView(index, 20);
                index++;
                ws.addCell(new Label(index, 1, "�ĵ����", wcformat));
                ws.setColumnView(index, 20);
                index++;
                ws.addCell(new Label(index, 1, "�汾��", wcformat));
                ws.setColumnView(index, 20);
                index++;

                int row = 2;
                for (int i = 0; i < wfDocs.size(); i++) {
                    row++;
                    int m = 0;
                    if (wfDocs.get(i).getDocId() != null)
                        ws.addCell(new jxl.write.Number(m, row, wfDocs.get(i).getDocId(), wcformat));
                    m++;
                    if (wfDocs.get(i).getTaskId() != null)
                        ws.addCell(new jxl.write.Number(m, row, wfDocs.get(i).getTaskId(), wcformat));
                    m++;
                    if (wfDocs.get(i).getStatus() != null)
                        ws.addCell(new jxl.write.Number(m, row, wfDocs.get(i).getStatus(), wcformat));
                    m++;
                    if (wfDocs.get(i).getCreateBy() != null)
                        ws.addCell(new jxl.write.Number(m, row, wfDocs.get(i).getCreateBy(), wcformat));
                    m++;
                    if (wfDocs.get(i).getLastUpd() != null)
                        ws.addCell(new jxl.write.Number(m, row, wfDocs.get(i).getLastUpd(), wcformat));
                    m++;
                    if (wfDocs.get(i).getCateId() != null)
                        ws.addCell(new jxl.write.Number(m, row, wfDocs.get(i).getCateId(), wcformat));
                    m++;
                    if (wfDocs.get(i).getFlowId() != null)
                        ws.addCell(new jxl.write.Number(m, row, wfDocs.get(i).getFlowId(), wcformat));
                    m++;
                    if (wfDocs.get(i).getDeptDocId() != null)
                        ws.addCell(new jxl.write.Number(m, row, wfDocs.get(i).getDeptDocId(), wcformat));
                    m++;
                    if (wfDocs.get(i).getWfNo() != null)
                        ws.addCell(new jxl.write.Label(m, row, wfDocs.get(i).getWfNo(), wcformat));
                    m++;
                    if (wfDocs.get(i).getUriLink() != null)
                        ws.addCell(new jxl.write.Label(m, row, wfDocs.get(i).getUriLink(), wcformat));
                    m++;
                    if (wfDocs.get(i).getCreateDate() != null)
                        ws.addCell(new jxl.write.DateTime(m, row, wfDocs.get(i).getCreateDate(), wcformat));
                    m++;
                    if (wfDocs.get(i).getLastUpdDate() != null)
                        ws.addCell(new jxl.write.DateTime(m, row, wfDocs.get(i).getLastUpdDate(), wcformat));
                    m++;
                    if (wfDocs.get(i).getDocName() != null)
                        ws.addCell(new jxl.write.Label(m, row, wfDocs.get(i).getDocName(), wcformat));
                    m++;
                    if (wfDocs.get(i).getDocVer() != null)
                        ws.addCell(new jxl.write.Label(m, row, wfDocs.get(i).getDocVer(), wcformat));
                    m++;

                }
            }
            this.setMsg(MSG.S_EXP);
        } catch (Exception e) {
            this.setMsg(MSG.F_EXP);
            Logger.getLogger(this.getClass()).error("WfDocListAction execute Exception", e);
            return ERROR;
        }
        return EXCEL;
    }

    public String prn() throws Exception {
        try {
            if (wfDocs != null && wfDocs.size() > 0) {
                for (int i = 0; i < wfDocs.size(); i++) {
                    if (wfDocs.get(i) != null) {
                        // wfDocs.get(i).setLastUpd(getSession().getUserId());
                        // wfDocs.get(i).setLastUpdDate(new Date());
                        new WfDocFacade().confirm(wfDocs.get(i));
                    }
                }
            }
            setMsg(MSG.S_PRN);
        } catch (Exception e) {
            setMsg(MSG.F_PRN);
            Logger.getLogger(this.getClass()).error("WfDocAction Exception", e);
            return ERROR;
        }
        return PRINT;
    }

    // public String dow() throws Exception {
    // try {
    // wfDoc = new WfDocFacade().findById(wfDoc);
    // contentType="application/octet-stream;charset=gb2312";
    // inputStream = new FileInputStream(new File(this.getServPath()+wfDoc.getUriLink()));
    // fileName = new String(wfDoc.getDocName().getBytes("GBK"),"ISO-8859-1");
    // return "download";
    // }
    // catch(FileNotFoundException e) {
    // setMsg("ϵͳ�Ҳ����ļ���");
    // Logger.getLogger(this.getClass()).error("WfDocAction dow Exception", e);
    // return ERROR;
    // }
    // catch(Exception e) {
    // setMsg("���ؼ�¼ʧ��");
    // Logger.getLogger(this.getClass()).error("WfDocAction dow Exception", e);
    // return ERROR;
    // }
    // }

    // TODO
    public String dow() throws Exception {
        wfDoc = new WfDocFacade().findById(wfDoc);
        Long fileNo = 0l;
        PostMethod postmethod = null;
        int sendStatus = 0;
        try {
            fileNo = Long.parseLong(wfDoc.getFileNo().trim());
        } catch (NumberFormatException e) {
            Logger.getLogger(this.getClass()).info(wfDoc.getFileNo() + " DownLoad on gnfile", e);
        } catch (Exception e) {
            setMsg("下载失败");
            Logger.getLogger(this.getClass()).error("WfDocAction dow Exception", e);
            return ERROR;
        }

        try {
            HttpClient httpclient = new HttpClient();
            String gngile_download_URL;
            if (fileNo == 0l) {
                gngile_download_URL = ServletActionContext.getServletContext().getInitParameter("gngile_download_URL");
                postmethod = new PostMethod(gngile_download_URL + "?fileNo=" + wfDoc.getFileNo());
            } else {
                gngile_download_URL = OssConstant.server.getValue();
                StringBuilder url = new StringBuilder(gngile_download_URL);
                String policy = EncryptUtil.getPolicy("0", "0", wfDoc.getFileNo());
                url.append(OssConstant.downloadURL.getValue()).append("?").append("policy=").append(URLEncoder.encode(CalcUtil.getBase64(policy), "UTF-8")).append("&");
                url.append("signature=").append(EncryptUtil.signature(OssConstant.key.getValue(), policy)).append("&").append("code=").append(OssConstant.code.getValue());
                postmethod = new PostMethod(url.toString());
            }

            httpclient.executeMethod(postmethod);
            sendStatus = postmethod.getStatusCode();
            if (fileNo != 0l) {
                Message message = JSON.toJavaObject(JSON.parseObject(postmethod.getResponseBodyAsString()), Message.class);
                if (!message.getIsSuccess()) {//下载失败
                    gngile_download_URL = ServletActionContext.getServletContext().getInitParameter("gngile_download_URL");
                    postmethod = new PostMethod(gngile_download_URL + "?fileNo=" + wfDoc.getFileNo());
                    httpclient.executeMethod(postmethod);
                    sendStatus = postmethod.getStatusCode();
                }
                return setDowInputSteam(postmethod, sendStatus);
            } else {
                return setDowInputSteam(postmethod, sendStatus);
            }
        } catch (JSONException e) {
            return setDowInputSteam(postmethod, sendStatus);
        } catch (Exception e) {
            setMsg("下载失败");
            Logger.getLogger(this.getClass()).error("WfDocAction dow Exception", e);
            return ERROR;
        }
    }


    String setDowInputSteam(PostMethod postmethod, int sendStatus) throws IOException {
        if (sendStatus == 200) {
            inputStream = postmethod.getResponseBodyAsStream();
            fileName = new String(wfDoc.getDocName().getBytes("GBK"), "ISO-8859-1");
            return "download";
        } else {
            setMsg("HTTP请求异常");
            return ERROR;
        }
    }


//	public String dowPrjtDoc() throws Exception {
//		try {
//			
//				wfDoc = new WfDocFacade().findById(wfDoc);
//		    if(checkDocAuth()){
//				System.out.println(wfDoc.getDocId());
//				System.out.println(wfDoc.getFileNo());
//				HttpClient httpclient = new HttpClient();
//				String gngile_download_URL = ServletActionContext.getServletContext().getInitParameter("gngile_download_URL");
//				PostMethod postmethod = new PostMethod(gngile_download_URL + "?fileNo=" + wfDoc.getFileNo());
//				int sendStatus = 0;
//				sendStatus = httpclient.executeMethod(postmethod);
//				sendStatus = postmethod.getStatusCode();
//				System.out.println("----" + sendStatus);
//				if (sendStatus == 200) {
//					inputStream = (InputStream) postmethod.getResponseBodyAsStream();
//					fileName = new String(wfDoc.getDocName().getBytes("GBK"), "ISO-8859-1");
//					return "download";
//				} else {
//					setJson(JSON.toJSONString("下载错误"));
//				}
//				
//			}else{
//				setJson(JSON.toJSONString("没有权限下载"));
//			}
//			
//		} catch (Exception e) {
//			setJson(JSON.toJSONString("下载错误"));
//			Logger.getLogger(this.getClass()).error("WfDocAction dow Exception", e);
//		}
//		return PGLIS;
//	}


    public String checkDocAuth() {

        String resultJson = "{\"result\":0}";
        try {
            wfDoc = new WfDocFacade().findById(wfDoc);

            if (wfDoc.getWfNo() != null) {
                //如果是不和项目挂钩的流程则流程相关人都可以下载
                if (wfDoc.getProjectNo() == null) {
                    WfRdTask wfRdTask = new WfRdTask();
                    wfRdTask.setWfNo(wfDoc.getWfNo());
                    List<WfRdTask> wfRdTaskList = new WfRdTaskFacade().find(wfRdTask);
                    for (WfRdTask e : wfRdTaskList) {
                        if (e.getAcceptBy().intValue() == getUsrSession().getId().intValue()) {
                            resultJson = "{\"result\":1}";
                            setJson(resultJson);
                            return PGLIS;
                        }
                    }
                }
            }
            if (null == wfDoc.getCateId() || wfDoc.getCateId() == 0) {//如果为额外附件  则直接可以下载
                resultJson = "{\"result\":1}";
                setJson(resultJson);
                return PGLIS;
            }

            if (getUsrSession().getId().intValue() == wfDoc.getCreateBy().intValue()) {//如果是附件的上传者 直接可以下载
                resultJson = "{\"result\":1}";
                setJson(resultJson);
                return PGLIS;
            }


            List<PrjtUsr> prjtUs = new ArrayList<PrjtUsr>();
            String prjtUsSQL = "select " + PrjtUsr.SELF_FIELDS + " from PrjtUsr where PrjtUsr.UsrId = '" + getUsrSession().getId() + "' and PrjtUsr.PrjtNo = '" + wfDoc.getProjectNo() + "'";
            prjtUs = new PrjtUsrFacade().find(prjtUsSQL, PrjtUsr.SELF_FIELDS);

            if (prjtUs != null && prjtUs.size() > 0) {
                String roleIds = "";
                for (PrjtUsr e : prjtUs) {
                    roleIds = roleIds + "," + e.getRoleId();
                }
                roleIds = roleIds.substring(1, roleIds.length());
                String SQL = "select  count(*) as amount from DocRole where PrjtRoleId in ( " + roleIds + ") and DocCateId = " + wfDoc.getCateId();
                int amout = new DocRoleFacade().amount(SQL);
                if (amout > 0) {
                    resultJson = "{\"result\":1}";
                    setJson(resultJson);
                    return PGLIS;
                } else {

                    setJson(resultJson);
                    return PGLIS;
                }
            } else {
                setJson(resultJson);
                return PGLIS;
            }


        } catch (Exception e) {
            e.printStackTrace();
            resultJson = "{\"result\":-1}";
            setJson(resultJson);
            Logger.getLogger(this.getClass()).error("WfDocAction checkDocAuth Exception", e);
        }
        return PGLIS;

    }

    public String baseSharaUserList() throws Exception {
        try {
            if (wfDoc == null) {
                wfDoc = new WfDoc();
            }
            if (getPage() == null) {
                setPage(1);
            }
            if (getPageSize() == null) {
                setPageSize(20);
            }
            if (getPagesize() != null) {
                setPageSize(getPagesize());
            }

            if (getPageVO() == null) {
                setPageVO(new PageVO());
            }
            if (getPageVO().getPage() == null) {
                getPageVO().setPage(getPage());
            }
            if (getPageVO().getPageSize() == null) {
                getPageVO().setPageSize(getPageSize());
            }

            StringBuilder cond = new StringBuilder(" ShareRela.Status>0");
            if (wfDoc.getDocId() != null) {
                cond.append(" and ShareRela.WfDocId=").append(wfDoc.getDocId());
            }
            // String joins =
            // " inner join Usr on (Usr.Id=ShareRela.UsrId) inner join PrjtRole on (exists(select * from PrjtUsr where PrjtRole.RoleId=PrjtUsr.RoleId and ShareRela.UsrId=PrjtUsr.UsrId)) ";
            String joins = " inner join Usr on (Usr.Id=ShareRela.UsrId) ";

            Integer total = new ShareRelaFacade().amount("select count(*) amount from ShareRela " + joins + " where " + cond.toString());

            if (getPageVO().getTotal() == null) {
                getPageVO().setTotal(total);
            }

            String sql1 = pageQuery("ShareRela", joins, ShareRela.LIST_FIELDS_WF, cond.toString(), "order by ShareRela.WfDocId,ShareRela.UsrId", getPageVO());
            List<ShareRela> relas = new ShareRelaFacade().find(sql1, ShareRela.LIST_FIELDS_WF);
            JSONObject obj = new JSONObject();
            JSONArray rows = new JSONArray();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0; i < relas.size(); i++) {
                JSONObject obj1 = JSONObject.fromObject(relas.get(i));
                if (relas.get(i).getCreateDate() != null) {
                    obj1.put("createDate", df.format(relas.get(i).getCreateDate()));
                }
                rows.add(obj1);
            }
            obj.put("Rows", rows);
            obj.put("Total", total);
            String jsonStr = obj.toString();
            System.out.println(jsonStr);
            setJson(jsonStr);
        } catch (Exception e) {
            setMsg(MSG.F_SEA);
            Logger.getLogger(this.getClass()).error("WfDocAction Exception", e);
            return ERROR;
        }
        return PGLIS;
    }

    private ShareRela shareRela;

    private String pageQuery(String table, String joins, String fields, String cond, String orderByStr, PageVO pageVO) {
        int pageStart = (pageVO.getPage() - 1) * pageVO.getPageSize();
        int pageEnd = Math.min(pageVO.getPage() * pageVO.getPageSize(), pageVO.getTotal());
        StringBuilder bsql = new StringBuilder();
        bsql.append("select ").append(fields);
        bsql.append(" from (select ").append("row_number() over(").append(orderByStr).append(") as row_number,").append(fields);
        bsql.append(" from ").append(table);
        if (joins != null && !"".equals(joins)) {
            bsql.append(" ").append(joins);
        }
        bsql.append(" where ").append(cond).append(" ").append(")").append(table);
        bsql.append(" where row_number>").append(pageStart).append(" and row_number<=").append(pageEnd);
        bsql.append(" ").append(orderByStr);
        return bsql.toString();
    }

    public String sharaStatus() {
        if (shareRela == null) {
            return ERROR;
        }
        if (shareRela.getWfDocId() == null || shareRela.getStatus() == null || shareRela.getUsrId() == null) {
            return ERROR;
        }
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("update ShareRela set ShareRela.Status=").append(shareRela.getStatus());
            sb.append(" where ShareRela.WfDocId=").append(shareRela.getWfDocId());
            sb.append(" and ShareRela.UsrId=").append(shareRela.getUsrId());
            new ShareRelaFacade().update(sb.toString());
            if (shareRela.getStatus().intValue() == 1) {
                setMsg("发布成功");
            } else if (shareRela.getStatus().intValue() == 2) {
                setMsg("收回成功");
            }
        } catch (Exception e) {
            setMsg(MSG.F_SEA);
            Logger.getLogger(this.getClass()).error("WfDocAction Exception", e);
            if (shareRela.getStatus().intValue() == 1) {
                setMsg("发布失败");
            } else if (shareRela.getStatus().intValue() == 2) {
                setMsg("收回失败");
            }
            return ERROR;
        }
        return MESSAGE;
    }

    /**
     * 归档权限
     */
    private boolean pigeonBl;
    /**
     * 发布权限
     */
    private boolean shareBt;
    /**
     * 接收发布权限
     */
    private boolean reShareBl;

    /**
     * 设置权限
     */
    private void setPrjtRole() {
        pigeonBl = false;
        shareBt = false;
        for (Gp e : getUsrSession().getGps()) {
            if (e.getGpName().indexOf("CMO") > -1) {
                pigeonBl = true;
                shareBt = true;
                //} else if (e.getGpName().indexOf("CMO") > -1) {
            }
        }
        reShareBl = getUsrSession().getId() == MSG.SHARE_LIB_USR_ID;
    }

    public List<WfDoc> getWfDocs() {
        return wfDocs;
    }

    public void setWfDocs(List<WfDoc> wfDocs) {
        this.wfDocs = wfDocs;
    }

    public WfDoc getWfDoc() {
        return wfDoc;
    }

    public void setWfDoc(WfDoc wfDoc) {
        this.wfDoc = wfDoc;
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

    public java.util.List<gnwf.vo.WfDocRev> getWfDocRevs() {
        return wfDocRevs;
    }

    public void setWfDocRevs(java.util.List<gnwf.vo.WfDocRev> wfDocRevs) {
        this.wfDocRevs = wfDocRevs;
    }

    public void addtoWfDocRevs(gnwf.vo.WfDocRev wfDocRev) {
        if (getWfDocRevs() == null) setWfDocRevs(new java.util.ArrayList<gnwf.vo.WfDocRev>());
        getWfDocRevs().add(wfDocRev);
    }

    public java.util.List<gnwf.vo.WfRdTask> getWfRdTasks() {
        return wfRdTasks;
    }

    public void setWfRdTasks(java.util.List<gnwf.vo.WfRdTask> wfRdTasks) {
        this.wfRdTasks = wfRdTasks;
    }

    public void addtoWfRdTasks(gnwf.vo.WfRdTask wfRdTask) {
        if (getWfRdTasks() == null) setWfRdTasks(new java.util.ArrayList<gnwf.vo.WfRdTask>());
        getWfRdTasks().add(wfRdTask);
    }

    public java.util.List<gnwf.vo.WfRd> getWfRds() {
        return wfRds;
    }

    public void setWfRds(java.util.List<gnwf.vo.WfRd> wfRds) {
        this.wfRds = wfRds;
    }

    public void addtoWfRds(gnwf.vo.WfRd wfRd) {
        if (getWfRds() == null) setWfRds(new java.util.ArrayList<gnwf.vo.WfRd>());
        getWfRds().add(wfRd);
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public Integer getScheId() {
        return scheId;
    }

    public void setScheId(Integer scheId) {
        this.scheId = scheId;
    }

    public String getFileNo() {
        return fileNo;
    }

    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }

    public String getUsrId() {
        return usrId;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getTempParams() {
        return tempParams;
    }

    public void setTempParams(String tempParams) {
        this.tempParams = tempParams;
    }

    public WfStep getWfStep() {
        return wfStep;
    }

    public String getSelectUsrs() {
        return selectUsrs;
    }

    public void setSelectUsrs(String selectUsrs) {
        this.selectUsrs = selectUsrs;
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

    public String getUsrNm() {
        return usrNm;
    }

    public void setUsrNm(String usrNm) {
        this.usrNm = usrNm;
    }

    public WfRd getWfRd() {
        return wfRd;
    }

    public void setWfRd(WfRd wfRd) {
        this.wfRd = wfRd;
    }

    public String getUserMap() {
        return userMap;
    }

    public void setUserMap(String userMap) {
        this.userMap = userMap;
    }

    public String getPrjtNo() {
        return prjtNo;
    }

    public void setPrjtNo(String prjtNo) {
        this.prjtNo = prjtNo;
    }

    public String getTjson() {
        return tjson;
    }

    public void setTjson(String tjson) {
        this.tjson = tjson;
    }

    public ShareRela getShareRela() {
        return shareRela;
    }

    public void setShareRela(ShareRela shareRela) {
        this.shareRela = shareRela;
    }

    public boolean isPigeonBl() {
        return pigeonBl;
    }

    public void setPigeonBl(boolean pigeonBl) {
        this.pigeonBl = pigeonBl;
    }

    public boolean isShareBt() {
        return shareBt;
    }

    public void setShareBt(boolean shareBt) {
        this.shareBt = shareBt;
    }

    public boolean isReShareBl() {
        return reShareBl;
    }

    public void setReShareBl(boolean reShareBl) {
        this.reShareBl = reShareBl;
    }

}