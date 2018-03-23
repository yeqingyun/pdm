package zrprjt.ww.json;

import java.io.File;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.write.WritableWorkbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

import zrprjt.ww.MSG;
import zrprjt.ww.BasicAction;

import zrprjt.facade.PrjtPointFacade;
import zrprjt.vo.PrjtPoint;
import zrprjt.vo.json.PrjtPointJson;

public class PrjtPointAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<PrjtPoint> PrjtPoints;
	private PrjtPoint PrjtPoint = new PrjtPoint();

	
	public String list() throws Exception {
		try {
			if(PrjtPoint == null) PrjtPoint = new PrjtPoint();
			int total = new PrjtPointFacade().amount(PrjtPoint);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			PrjtPoints = new PrjtPointFacade().find(PrjtPoint,getPageVO());
			PrjtPointJson PrjtPointJson = new PrjtPointJson();
			PrjtPointJson.Rows = PrjtPoints;
			PrjtPointJson.Total = total;
			setJson(JSON.toJSONString(PrjtPointJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("PrjtPointAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	
	
	public String sav() throws Exception {
		try {
			PrjtPoint.setCreateBy(getUsrSession().getId());
			PrjtPoint.setCreateDate(new Date());
			PrjtPoint.setLastUpd(getUsrSession().getId());
			PrjtPoint.setLastDate(new Date());
//			PrjtPoint.setPrjtPointMsgs(PrjtPointMsgs);
//			PrjtPoint.setPrjtPointWfs(PrjtPointWfs);

			new PrjtPointFacade().save(PrjtPoint);
//			if(PrjtPoint.getSchId() == null)
//			else
//				new PrjtPointFacade().update(PrjtPoint);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("PrjtPointAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	
	public List<PrjtPoint> getPrjtPoints() {
		return PrjtPoints;
	}
	public void setPrjtPoints(List<PrjtPoint> PrjtPoints) {
		this.PrjtPoints = PrjtPoints;
	}
	public PrjtPoint getPrjtPoint() {
		return PrjtPoint;
	}
	public void setPrjtPoint(PrjtPoint PrjtPoint) {
		this.PrjtPoint = PrjtPoint;
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

}