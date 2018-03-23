package gnwf.ww.json;

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

import gnwf.ww.MSG;
import gnwf.ww.BasicAction;
import gnwf.ww.workflow.WFUtil;

import gnwf.facade.WfCfgFacade;
import gnwf.facade.WfFieldFacade;
import gnwf.facade.WfStepFacade;
import gnwf.vo.WfCfg;
import gnwf.vo.WfField;
import gnwf.vo.WfFieldStepRelate;
import gnwf.vo.WfStep;
import gnwf.vo.json.WfFieldJson;

public class WfFieldAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<WfField> wfFields;
	private WfField wfField = new WfField();
	private java.util.List<gnwf.vo.WfRdField> wfRdFields;
	private java.util.List<gnwf.vo.WfCfg> wfCfgs;
	private List<WfStep> steps;
	private List<WfFieldStepRelate> relates;

	public String execute() throws Exception {
		try {
			if(wfField != null && wfField.getFieldId() != null) {
				wfField = new WfFieldFacade().findById(wfField);
				setJson(JSON.toJSONString(wfField)); 
			}
			wfCfgs = new gnwf.facade.WfCfgFacade().find("select "+gnwf.vo.WfCfg.SELF_FIELDS+" from WfCfg",gnwf.vo.WfCfg.SELF_FIELDS);

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfFieldAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			WfCfg cfg = new WfCfg();
			cfg.setStatus(MSG.CONST_STATUS_1);
			wfCfgs = new WfCfgFacade().findAll(cfg);
			
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfFieldAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		return view();
	}
	public String view() throws Exception {
		try {
			wfField = new WfFieldFacade().findById(wfField);
			
			WfCfg cfg = new WfCfg();
			cfg.setStatus(MSG.CONST_STATUS_1);
			wfCfgs = new WfCfgFacade().findAll(cfg);
			
			//选中步骤
			String sql = "select wfstep.StepId,StepName,a.stepId as ischecked from wfstep " +
				"left join(select stepId from WfFieldStepRelate where fieldId="+wfField.getFieldId()+")a on (wfstep.stepId=a.stepId) " +
				"where wfstep.status="+MSG.CONST_STATUS_1+" and wfstep.flowid="+wfField.getFlowId()+" order by wfstep.Sort";
			steps = new WfStepFacade().find(sql,"StepId,StepName,ischecked");
			
			//如果不属于任何步骤，代表属于新增界面扩展字段
			String c = "checked";
			for(int i=0;i<steps.size();i++){
				if(steps.get(i)!=null && WFUtil.isNotNull(steps.get(i).getIsChecked())){
					c = "";
				}
			}
			WfStep ext = new WfStep();
			ext.setStepId(0);
			ext.setStepName("新增界面扩展字段");
			ext.setIsChecked(c);
			steps.add(0,ext);
			
			if(wfField != null && wfField.getFieldId() != null) {
				setJson(JSON.toJSONString(wfField)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfFieldAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			WfCfg cfg = new WfCfg();
			cfg.setStatus(MSG.CONST_STATUS_1);
			wfCfgs = new WfCfgFacade().findAll(cfg);
			
			if(wfField == null) wfField = new WfField();
			int total = new WfFieldFacade().amount(wfField);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			wfFields = new WfFieldFacade().find(wfField,getPageVO());
			WfFieldJson wfFieldJson = new WfFieldJson();
			wfFieldJson.Rows = wfFields;
			wfFieldJson.Total = total;
			setJson(JSON.toJSONString(wfFieldJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("WfFieldAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
//			wfField.setWfRdFields(wfRdFields);
			wfField.setRelates(relates);

			if(wfField.getFieldId() == null){
				wfField.setCreateBy(getUsrSession().getId());
				wfField.setCreateDate(new Date());
				wfField.setStatus(MSG.CONST_STATUS_1);
				new WfFieldFacade().save(wfField);
			}
			else{
				wfField.setLastUpd(getUsrSession().getId());
				wfField.setLastUpdDate(new Date());
				new WfFieldFacade().update(wfField);
			}
				
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("WfFieldAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//wfField.setCreateBy(getSession().getUserId());
			//wfField.setCreateDate(new Date());
			//wfField.setLastUpd(getSession().getUserId());
			//wfField.setLastUpdDate(new Date());
			new WfFieldFacade().update(wfField);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("WfFieldAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(wfFields != null && wfFields.size() > 0) {
				for(int i=0; i<wfFields.size();i++) {
					if(wfFields.get(i) != null) {
						//wfFields.get(i).setLastUpd(getSession().getUserId());
						//wfFields.get(i).setLastUpdDate(new Date());
						new WfFieldFacade().submit(wfFields.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("WfFieldAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(wfFields != null && wfFields.size() > 0) {
				for(int i=0; i<wfFields.size();i++) {
					if(wfFields.get(i) != null) {
						//wfFields.get(i).setLastUpd(getSession().getUserId());
						//wfFields.get(i).setLastUpdDate(new Date());
						new WfFieldFacade().submit(wfFields.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("WfFieldAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(wfFields != null && wfFields.size() > 0) {
				for(int i=0; i<wfFields.size();i++) {
					if(wfFields.get(i) != null) {
						//wfFields.get(i).setLastUpd(getSession().getUserId());
						//wfFields.get(i).setLastUpdDate(new Date());
						new WfFieldFacade().update(wfFields.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("WfFieldAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(wfFields != null && wfFields.size() > 0) {
				for(int i=0; i<wfFields.size();i++) {
					if(wfFields.get(i) != null) {
						//wfFields.get(i).setLastUpd(getSession().getUserId());
						//wfFields.get(i).setLastUpdDate(new Date());
						new WfFieldFacade().submit(wfFields.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("WfFieldAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(wfFields != null && wfFields.size() > 0) {
				for(int i=0; i<wfFields.size();i++) {
					if(wfFields.get(i) != null) {
						//wfFields.get(i).setLastUpd(getSession().getUserId());
						//wfFields.get(i).setLastUpdDate(new Date());
						new WfFieldFacade().submit(wfFields.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("WfFieldAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(wfFields != null && wfFields.size() > 0) {
				for(int i=0; i<wfFields.size();i++) {
					if(wfFields.get(i) != null) {
						//wfFields.get(i).setLastUpd(getSession().getUserId());
						//wfFields.get(i).setLastUpdDate(new Date());
						new WfFieldFacade().submit(wfFields.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("WfFieldAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(wfFields != null && wfFields.size() > 0) {
				for(int i=0; i<wfFields.size();i++) {
					if(wfFields.get(i) != null) {
						//wfFields.get(i).setLastUpd(getSession().getUserId());
						//wfFields.get(i).setLastUpdDate(new Date());
						new WfFieldFacade().submit(wfFields.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("WfFieldAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(wfFields != null && wfFields.size() > 0) {
				for(int i=0; i<wfFields.size();i++) {
					if(wfFields.get(i) != null){
						//wfFields.get(i).setLastUpd(getSession().getUserId());
						//wfFields.get(i).setLastUpdDate(new Date());
						new WfFieldFacade().submit(wfFields.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("WfFieldAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(wfFields != null && wfFields.size() > 0) {
				for(int i=0; i<wfFields.size();i++) {
					if(wfFields.get(i) != null){
						//wfFields.get(i).setLastUpd(getSession().getUserId());
						//wfFields.get(i).setLastUpdDate(new Date());
						new WfFieldFacade().review(wfFields.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("WfFieldAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(wfFields != null && wfFields.size() > 0) {
				for(int i=0; i<wfFields.size();i++) {
					if(wfFields.get(i) != null) {
						//wfFields.get(i).setLastUpd(getSession().getUserId());
						//wfFields.get(i).setLastUpdDate(new Date());
						new WfFieldFacade().review(wfFields.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("WfFieldAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(wfFields != null && wfFields.size() > 0) {
				for(int i=0; i<wfFields.size();i++) {
					if(wfFields.get(i) != null) {
						//wfFields.get(i).setLastUpd(getSession().getUserId());
						//wfFields.get(i).setLastUpdDate(new Date());
						new WfFieldFacade().confirm(wfFields.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("WfFieldAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(wfFields != null && wfFields.size() > 0) {
				for(int i=0; i<wfFields.size();i++) {
					if(wfFields.get(i) != null) {
						//wfFields.get(i).setLastUpd(getSession().getUserId());
						//wfFields.get(i).setLastUpdDate(new Date());
						new WfFieldFacade().confirm(wfFields.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("WfFieldAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(wfFields != null && wfFields.size() > 0) {
					for(int i=0; i<wfFields.size();i++) {
						if(wfFields.get(i) != null) {
							//wfFields.get(i).setLastUpd(getSession().getUserId());
							//wfFields.get(i).setLastUpdDate(new Date());
							new WfFieldFacade().confirm(wfFields.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("WfFieldAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	public String exp() throws Exception {
		try {
			List<WfField> wfFields = new WfFieldFacade().find(wfField);
			if(wfFields != null && wfFields.size() > 0) {
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
				
					ws.addCell(new Label(index,1,"字段ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"工作流ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"步骤ID",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"字段类型",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"是否集合",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"是否允许空",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"状态",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"创建人",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"最后更新",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"创建日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"更新日期",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"字段名",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"中文名",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"Sql配置",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"JS函数",wcformat));
					ws.setColumnView(index,20);
					index++;
					ws.addCell(new Label(index,1,"null",wcformat));
					ws.setColumnView(index,20);
					index++;


				int row = 2;
				for(int i=0; i<wfFields.size();i++) {
					row++;
					int m = 0;
					if(wfFields.get(i).getFieldId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfFields.get(i).getFieldId(),wcformat));
					m++;
					if(wfFields.get(i).getFlowId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfFields.get(i).getFlowId(),wcformat));
					m++;
					if(wfFields.get(i).getStepId() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfFields.get(i).getStepId(),wcformat));
					m++;
					if(wfFields.get(i).getFieldType() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfFields.get(i).getFieldType(),wcformat));
					m++;
					if(wfFields.get(i).getIsGather() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfFields.get(i).getIsGather(),wcformat));
					m++;
					if(wfFields.get(i).getIsNull() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfFields.get(i).getIsNull(),wcformat));
					m++;
					if(wfFields.get(i).getStatus() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfFields.get(i).getStatus(),wcformat));
					m++;
					if(wfFields.get(i).getCreateBy() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfFields.get(i).getCreateBy(),wcformat));
					m++;
					if(wfFields.get(i).getLastUpd() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfFields.get(i).getLastUpd(),wcformat));
					m++;
					if(wfFields.get(i).getIsList() != null) 
						ws.addCell(new jxl.write.Number(m,row,wfFields.get(i).getIsList(),wcformat));
					m++;
					if(wfFields.get(i).getCreateDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,wfFields.get(i).getCreateDate(),wcformat));
					m++;
					if(wfFields.get(i).getLastUpdDate() != null) 
						ws.addCell(new jxl.write.DateTime(m,row,wfFields.get(i).getLastUpdDate(),wcformat));
					m++;
					if(wfFields.get(i).getFieldCode() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfFields.get(i).getFieldCode(),wcformat));
					m++;
					if(wfFields.get(i).getFieldName() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfFields.get(i).getFieldName(),wcformat));
					m++;
					if(wfFields.get(i).getFieldSql() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfFields.get(i).getFieldSql(),wcformat));
					m++;
					if(wfFields.get(i).getFieldJs() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfFields.get(i).getFieldJs(),wcformat));
					m++;
					if(wfFields.get(i).getDefaultValue() != null) 
						ws.addCell(new jxl.write.Label(m,row,wfFields.get(i).getDefaultValue(),wcformat));
					m++;

				}
			}
			this.setMsg(MSG.S_EXP);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_EXP);
			Logger.getLogger(this.getClass()).error("WfFieldListAction execute Exception", e);
			return ERROR;
		}
		return EXCEL;
	}
	public String prn() throws Exception {
		try {
			if(wfFields != null && wfFields.size() > 0) {
				for(int i=0; i<wfFields.size();i++) {
					if(wfFields.get(i) != null) {
						//wfFields.get(i).setLastUpd(getSession().getUserId());
						//wfFields.get(i).setLastUpdDate(new Date());
						new WfFieldFacade().confirm(wfFields.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfFieldAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(wfFields != null && wfFields.size() > 0) {
				for(int i=0; i<wfFields.size();i++) {
					if(wfFields.get(i) != null) {
						//wfFields.get(i).setLastUpd(getSession().getUserId());
						//wfFields.get(i).setLastUpdDate(new Date());
						new WfFieldFacade().confirm(wfFields.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("WfFieldAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<WfField> getWfFields() {
		return wfFields;
	}
	public void setWfFields(List<WfField> wfFields) {
		this.wfFields = wfFields;
	}
	public WfField getWfField() {
		return wfField;
	}
	public void setWfField(WfField wfField) {
		this.wfField = wfField;
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
	public java.util.List<gnwf.vo.WfRdField> getWfRdFields() {
		return wfRdFields;
	}
	public void setWfRdFields(java.util.List<gnwf.vo.WfRdField> wfRdFields){
		this.wfRdFields = wfRdFields;
	}
	public void addtoWfRdFields(gnwf.vo.WfRdField wfRdField){
		if(getWfRdFields() == null) setWfRdFields(new java.util.ArrayList<gnwf.vo.WfRdField>());
			getWfRdFields().add(wfRdField);
	}
	public java.util.List<gnwf.vo.WfCfg> getWfCfgs() {
		return wfCfgs;
	}
	public void setWfCfgs(java.util.List<gnwf.vo.WfCfg> wfCfgs){
		this.wfCfgs = wfCfgs;
	}
	public void addtoWfCfgs(gnwf.vo.WfCfg wfCfg){
		if(getWfCfgs() == null) setWfCfgs(new java.util.ArrayList<gnwf.vo.WfCfg>());
			getWfCfgs().add(wfCfg);
	}
	public List<WfStep> getSteps() {
		return steps;
	}
	public void setSteps(List<WfStep> steps) {
		this.steps = steps;
	}
	public List<WfFieldStepRelate> getRelates() {
		return relates;
	}
	public void setRelates(List<WfFieldStepRelate> relates) {
		this.relates = relates;
	}

}