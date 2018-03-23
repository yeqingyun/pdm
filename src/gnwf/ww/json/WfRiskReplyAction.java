package gnwf.ww.json;

import java.io.File;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tools.ant.util.DateUtils;




import zrsy.facade.UsrFacade;
import zrsy.vo.Usr;
import gnwf.ww.BasicAction;
import gnwf.ww.MSG;
import gnwf.ww.workflow.WFUtil;
import gnwf.facade.WfRiskFacade;
import gnwf.facade.WfRiskReplyFacade;
import gnwf.vo.WfRisk;
import gnwf.vo.WfRiskReply;

public class WfRiskReplyAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private File fileInp;
	private String fileNo;
	private String fileName;
	private String tempParams;
	private String usrId;
	private String syId;
	private String syNm;
	private String usrNm;
	private WfRiskReply wfRiskReply;
	private WfRisk wfRisk;
	/**
	 * 提交解决措施
	 * @return
	 */
	public String saveMeasures() {
		try{
			wfRiskReply.setStatus(MSG.WFRISKREPLY_STATUS_3);
			wfRiskReply.setMeasuresDate(new Date());
			wfRiskReply.setVerifyResult("");
			wfRiskReply.setLastUpdate(new Date());
			wfRiskReply.setLastUpdateUserId(getUsrSession().getId());
			new WfRiskReplyFacade().update(wfRiskReply);
			wfRisk = new WfRiskFacade().findById(wfRisk);
			String res_url = getAppcationURL();
			StringBuffer title = new StringBuffer();
			title.append("风险《").append(this.wfRisk.getTitle()).append("》的解决措施已给出通知！");
			StringBuffer content = new StringBuffer();
			content.append("尊敬的同事，您好:<p>您提出的风险《")
			.append(this.wfRisk.getTitle()).append("》，我已经给出了相关解决措施，请尽快验证！</p>链接地址：<a href=")
			.append(res_url)
			.append("/WfRisk!managerRisk.shtml?wfRisk.riskId=").append(this.wfRisk.getRiskId())
			.append(">")
			.append(res_url)
			.append("/WfRisk!managerRisk.shtml?wfRisk.riskId=")
			.append(this.wfRisk.getRiskId())
			.append("</a>");
			List<Usr> userList = new ArrayList<Usr>();
			Usr u = new Usr();
			u.setId(this.wfRisk.getCreateUserId());
			userList.add(u);
			WFUtil.sendMailByIT(title.toString(), content.toString(), userList);
			setMsg(MSG.S_UPD);
		}catch(Exception e){
			e.printStackTrace();
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfRiskReplyAction saveMeasures Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	
	/**
	 * 风险催办
	 * 
	 * @return
	 * @throws Exception
	 */
	public String rush() throws Exception {
		StringBuffer json = new StringBuffer("{\"msg\":");
		Date rushDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Usr usr = new Usr();
		usr.setId(this.wfRiskReply.getResponsibleUserId());
		try {
			this.wfRiskReply.setRushDate(rushDate);
			new WfRiskReplyFacade().update(this.wfRiskReply);
			
			String res_url = getAppcationURL();
			// 给问题责任人发送邮件
			usr =  new UsrFacade().findById(usr);
			wfRisk = new WfRiskFacade().findById(wfRisk);
			StringBuffer title = new StringBuffer("关于加快解决风险《");
			title.append(this.wfRisk.getTitle()).append("》的通知");
			StringBuffer content = new StringBuffer("尊敬的").append(usr.getUsrName()).append("先生/小姐:<p>此风险提出已有一段时间了，请尽快解决，谢谢!</p>链接地址：<a href=").append(res_url).append("/WfRisk!managerRisk.shtml?wfRisk.riskId=").append(this.wfRisk.getRiskId()).append(">").append(res_url).append("/WfRisk!managerRisk.shtml?wfRisk.riskId=").append(this.wfRisk.getRiskId()).append("</a>");
			;
			List<Usr> userList = new ArrayList<Usr>();
			userList.add(usr);
			WFUtil.sendMailByIT(title.toString(), content.toString(), userList);
			json.append("\"已催办\"").append(",\"rushDate\":\"").append(sdf.format(rushDate)).append("\"}");
			setJson(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
			json.append("\"催办异常\"}");
			setJson(json.toString());
		}
		return PGLIS;
	}
	/**
	 * 验证无效
	 * @return
	 * @throws Exception
	 */
	public String reject() throws Exception{
		try{
			this.wfRiskReply.setStatus(MSG.WFRISKREPLY_STATUS_DISABLE);
			this.wfRiskReply.setLastUpdateUserId(getUsrSession().getId());
			this.wfRiskReply.setVerifyDate(new Date());
			this.wfRiskReply.setLastUpdate(new Date());
			new WfRiskReplyFacade().update(this.wfRiskReply);
			this.wfRisk = new WfRiskFacade().findById(wfRisk);
			String res_url = getAppcationURL();
			String title = "风险《" + this.wfRisk.getTitle() + "》-解决措施验证无效通知";
			String content = "尊敬的同事，您好：<p>您给的关于风险《"+wfRisk.getTitle()+"》解决措施已被验证为无效，请从新给出新的解决措施。"
					+ "<p>链接地址 ： <a href=" + res_url
					+ "/WfRisk!managerRisk.shtml?wfRisk.riskId="
					+ wfRisk.getRiskId() + ">" + res_url
					+ "/WfRisk!managerRisk.shtml?wfRisk.riskId="
					+ wfRisk.getRiskId() + "</a>";
			
			 List<Usr> userList = new ArrayList<Usr>();
			 Usr u = new Usr();
			 u.setId(this.wfRiskReply.getResponsibleUserId());
			 userList.add(u);
			 WFUtil.sendMailByIT(title, content, userList);
			 setMsg(MSG.S_UPD);
		}catch(Exception e) {
			e.printStackTrace();
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfRiskReplyAction reject Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	/**
	 * 验证有效
	 * @return
	 * @throws Exception
	 */
	public String approve() throws Exception {
		try{
			this.wfRiskReply.setStatus(MSG.WFRISKREPLY_STATUS_2);
			this.wfRiskReply.setLastUpdateUserId(getUsrSession().getId());
			this.wfRiskReply.setVerifyDate(new Date());
			this.wfRiskReply.setLastUpdate(new Date());
			new WfRiskReplyFacade().update(this.wfRiskReply);
			String riskReplySql = "select Status from WfRiskReply where riskId = " + this.wfRisk.getRiskId();
			List<WfRiskReply> wfRiskReplyList = new WfRiskReplyFacade().find(riskReplySql, "WfRiskReply.Status");
			if(isAllWfRiskReplyApprove(wfRiskReplyList)) {
				this.wfRisk.setStatus(3);
				new WfRiskFacade().update(wfRisk);
			}
			setMsg(MSG.S_UPD);
		}catch(Exception e) {
			e.printStackTrace();
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfRiskReplyAction reject Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	/**
	 * 判断风险的解决措施是否全部给出有效的解决措施
	 * @param wfRiskReplyList
	 * @return
	 */
	private boolean isAllWfRiskReplyApprove(List<WfRiskReply> wfRiskReplyList) {
		if(wfRiskReplyList == null || wfRiskReplyList.size() == 0) {
			return false;
		}
		for(WfRiskReply wr : wfRiskReplyList){
			if(wr.getStatus() != 2) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 上传措施附件，验证附件
	 */
	public void afterUploadFile(){
		try{
			String id =null;
			String upFielType  =null;
			setFileName(URLDecoder.decode(getFileName(), "UTF-8"));
			setTempParams(URLDecoder.decode(getTempParams(), "UTF-8"));
			if(getFileNo()!=null){
				String sql =null;
				if (getTempParams() != null){
					String s[] = getTempParams().split(",");
					for(int i = 0;i<s.length;i++){
						 String ss[]=s[i].split(":");
						 if(ss[0].equals("wfRiskReply.id")){
							 id = ss[1];
						 }
						 if(ss[0].equals("upFielType")){
							 upFielType = ss[1];
						 }
					}
					if(upFielType.equals("measuresFile")){
						sql = "update WfRiskReply set MeasuresFileName ='"+getFileName()+"', MeasuresFileNo = '"+getFileNo()+"', LastUpdateUserId="+usrId+", LastUpdate ='"
								+DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss")+"' where Id="+id;
					}else if(upFielType.equals("verifyResultFile")){
						sql = "update WfRiskReply set VerifyFileName ='"+getFileName()+"', VerifyFileNo = '"+getFileNo()+"', LastUpdateUserId="+usrId+", LastUpdate ='"
								+DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss")+"' where Id="+id;
					}
				}
				if(id!=null){
					new WfRiskReplyFacade().update(sql);
				}
			}else {
				setMsg("上传附件失败");
			}
		} catch (Exception e) {
			setMsg("上传附件失败");
			Logger.getLogger(this.getClass()).error("WfRiskReplyAction uploadResultFile Exception", e);
			e.printStackTrace();
		}
	}
	
	public File getFileInp() {
		return fileInp;
	}
	public void setFileInp(File fileInp) {
		this.fileInp = fileInp;
	}
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getTempParams() {
		return tempParams;
	}
	public void setTempParams(String tempParams) {
		this.tempParams = tempParams;
	}
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
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
	public WfRiskReply getWfRiskReply() {
		return wfRiskReply;
	}
	public void setWfRiskReply(WfRiskReply wfRiskReply) {
		this.wfRiskReply = wfRiskReply;
	}


	public WfRisk getWfRisk() {
		return wfRisk;
	}


	public void setWfRisk(WfRisk wfRisk) {
		this.wfRisk = wfRisk;
	}
	
	
	

}