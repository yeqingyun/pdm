package zrsy.vo;
import com.alibaba.fastjson.annotation.JSONField;

public class BasicSyLog {
	public static final String SELF_FIELDS = "SyLog.LogId,SyLog.UserId,SyLog.LogCode,SyLog.LogNm,SyLog.LogText,SyLog.LogDate,SyLog.IpAddr,SyLog.LogType,SyLog.Remark";

	private java.lang.Integer logId;
	private java.lang.Integer userId;
	private java.lang.String logCode;
	private java.lang.String logNm;
	private java.lang.String logText;
	@JSONField(format="yyyy-MM-dd")
	private java.util.Date logDate;
	private java.util.Date startLogDate;
	private java.util.Date endLogDate;
	private java.lang.String ipAddr;
	private java.lang.Integer logType;
	private java.lang.String remark;

	public java.lang.Integer getLogId(){
		return logId;
	}
	public void setLogId(java.lang.Integer logId){
		this.logId = logId;
	}
	public java.lang.Integer getUserId(){
		return userId;
	}
	public void setUserId(java.lang.Integer userId){
		this.userId = userId;
	}
	public java.lang.String getLogCode(){
		return logCode;
	}
	public void setLogCode(java.lang.String logCode){
		this.logCode = logCode;
	}
	public java.lang.String getLogNm(){
		return logNm;
	}
	public void setLogNm(java.lang.String logNm){
		this.logNm = logNm;
	}
	public java.lang.String getLogText(){
		return logText;
	}
	public void setLogText(java.lang.String logText){
		this.logText = logText;
	}
	public java.util.Date getStartLogDate(){
		return startLogDate;
	}
	public void setStartLogDate(java.util.Date startLogDate){
		this.startLogDate = startLogDate;
	}
	public java.util.Date getEndLogDate(){
		return endLogDate;
	}
	public void setEndLogDate(java.util.Date endLogDate){
		this.endLogDate = endLogDate;
	}
	public java.util.Date getLogDate(){
		return logDate;
	}
	public void setLogDate(java.util.Date logDate){
		this.logDate = logDate;
	}
	public java.lang.String getIpAddr(){
		return ipAddr;
	}
	public void setIpAddr(java.lang.String ipAddr){
		this.ipAddr = ipAddr;
	}
	public java.lang.Integer getLogType(){
		return logType;
	}
	public void setLogType(java.lang.Integer logType){
		this.logType = logType;
	}
	public java.lang.String getRemark(){
		return remark;
	}
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}

}