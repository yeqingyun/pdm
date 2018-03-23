package gnwf.ww.json;

import gnwf.facade.ShareRelaFacade;
import gnwf.vo.ShareRela;
import gnwf.vo.json.ShareRelaJson;
import gnwf.ww.MSG;

import java.io.File;
import java.util.List;

import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;

import zrsy.ww.BasicAction;

import com.alibaba.fastjson.JSON;


public class ShareRelaAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private WritableWorkbook workbook;
	private File fileInp;
	private String choices;
	private List<ShareRela> shareRelas;
	private ShareRela shareRela = new ShareRela();

	public String execute() throws Exception {
		try {
			if(shareRela != null && shareRela.getWfDocId() != null&& shareRela.getUsrId() != null) {
				shareRela = new ShareRelaFacade().findById(shareRela);
				setJson(JSON.toJSONString(shareRela)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("ShareRelaAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}
	public String add() throws Exception {
		try {
			//if(shareRela != null && shareRela.getFileNo() != null) {
				//shareRela = new ShareRelaFacade().findById(shareRela);
				//setJson(JSON.toJSONString(shareRela)); 
			//}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("ShareRelaAction Exception", e);
			return ERROR;
		}
		return PGADD;
	}
	public String edit() throws Exception {
		try {
			if(shareRela != null  && shareRela.getWfDocId() != null&& shareRela.getUsrId() != null) {
				shareRela = new ShareRelaFacade().findById(shareRela);
				setJson(JSON.toJSONString(shareRela)); 
			}

		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("ShareRelaAction Exception", e);
			return ERROR;
		}
		return PGEDI;
	}
	public String view() throws Exception {
		try {
			if(shareRela != null  && shareRela.getWfDocId() != null&& shareRela.getUsrId() != null) {
				shareRela = new ShareRelaFacade().findById(shareRela);
				setJson(JSON.toJSONString(shareRela)); 
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("ShareRelaAction Exception", e);
			return ERROR;
		}
		return PGVIE;
	}
	public String list() throws Exception {
		try {
			if(shareRela == null) shareRela = new ShareRela();
			int total = new ShareRelaFacade().amount(shareRela);
			if(getPage() == null) {
				setPage(1);
				setPagesize(20);
			}
			getPageVO().setPage(this.getPage());
			getPageVO().setPageSize(this.getPagesize());
			getPageVO().setTotal(total);
			shareRelas = new ShareRelaFacade().find(shareRela,getPageVO());
			ShareRelaJson shareRelaJson = new ShareRelaJson();
			shareRelaJson.Rows = shareRelas;
			shareRelaJson.Total = total;
			setJson(JSON.toJSONString(shareRelaJson)); 
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("ShareRelaAction Exception", e);
			return ERROR;
		}
		return PGLIS;
	}
	public String sav() throws Exception {
		try {
			//shareRela.setCreateBy(getSession().getUserId());
			//shareRela.setCreateDate(new Date());
			//shareRela.setLastUpd(getSession().getUserId());
			//shareRela.setLastUpdDate(new Date());

			if(shareRela.getWfDocId() != null&& shareRela.getUsrId() != null)
				new ShareRelaFacade().save(shareRela);
			else
				new ShareRelaFacade().update(shareRela);
			setMsg(MSG.S_SAV);
		}
		catch(Exception e) {
			setMsg(MSG.F_SAV);
			Logger.getLogger(this.getClass()).error("ShareRelaAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String upd() throws Exception {
		try {
			//shareRela.setCreateBy(getSession().getUserId());
			//shareRela.setCreateDate(new Date());
			//shareRela.setLastUpd(getSession().getUserId());
			//shareRela.setLastUpdDate(new Date());
			new ShareRelaFacade().update(shareRela);
			setMsg(MSG.S_UPD);
		}
		catch(Exception e) {
			setMsg(MSG.F_UPD);
			Logger.getLogger(this.getClass()).error("ShareRelaAddAction add Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String voi() throws Exception {
		try {
			if(shareRelas != null && shareRelas.size() > 0) {
				for(int i=0; i<shareRelas.size();i++) {
					if(shareRelas.get(i) != null) {
						//shareRelas.get(i).setLastUpd(getSession().getUserId());
						//shareRelas.get(i).setLastUpdDate(new Date());
						new ShareRelaFacade().submit(shareRelas.get(i));
					}
				}
			}
			setMsg(MSG.S_VOI);
		}
		catch(Exception e) {
			setMsg(MSG.F_VOI);
			Logger.getLogger(this.getClass()).error("ShareRelaAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String can() throws Exception {
		try {
			if(shareRelas != null && shareRelas.size() > 0) {
				for(int i=0; i<shareRelas.size();i++) {
					if(shareRelas.get(i) != null) {
						//shareRelas.get(i).setLastUpd(getSession().getUserId());
						//shareRelas.get(i).setLastUpdDate(new Date());
						new ShareRelaFacade().submit(shareRelas.get(i));
					}
				}
			}
			setMsg(MSG.S_CAN);
		}
		catch(Exception e) {
			setMsg(MSG.F_CAN);
			Logger.getLogger(this.getClass()).error("ShareRelaAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String del() throws Exception {
		try {
			if(shareRelas != null && shareRelas.size() > 0) {
				for(int i=0; i<shareRelas.size();i++) {
					if(shareRelas.get(i) != null) {
						//shareRelas.get(i).setLastUpd(getSession().getUserId());
						//shareRelas.get(i).setLastUpdDate(new Date());
						new ShareRelaFacade().update(shareRelas.get(i));
					}
				}
			}
			setMsg(MSG.S_DEL);
		}
		catch(Exception e) {
			setMsg(MSG.F_DEL);
			Logger.getLogger(this.getClass()).error("ShareRelaAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sub() throws Exception {
		try {
			if(shareRelas != null && shareRelas.size() > 0) {
				for(int i=0; i<shareRelas.size();i++) {
					if(shareRelas.get(i) != null) {
						//shareRelas.get(i).setLastUpd(getSession().getUserId());
						//shareRelas.get(i).setLastUpdDate(new Date());
						new ShareRelaFacade().submit(shareRelas.get(i));
					}
				}
			}
			setMsg(MSG.S_SUB);
		}
		catch(Exception e) {
			setMsg(MSG.F_SUB);
			Logger.getLogger(this.getClass()).error("ShareRelaAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String sta() throws Exception {
		try {
			if(shareRelas != null && shareRelas.size() > 0) {
				for(int i=0; i<shareRelas.size();i++) {
					if(shareRelas.get(i) != null) {
						//shareRelas.get(i).setLastUpd(getSession().getUserId());
						//shareRelas.get(i).setLastUpdDate(new Date());
						new ShareRelaFacade().submit(shareRelas.get(i));
					}
				}
			}
			setMsg(MSG.S_STA);
		}
		catch(Exception e) {
			setMsg(MSG.F_STA);
			Logger.getLogger(this.getClass()).error("ShareRelaAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String loa() throws Exception {
		try {
			if(shareRelas != null && shareRelas.size() > 0) {
				for(int i=0; i<shareRelas.size();i++) {
					if(shareRelas.get(i) != null) {
						//shareRelas.get(i).setLastUpd(getSession().getUserId());
						//shareRelas.get(i).setLastUpdDate(new Date());
						new ShareRelaFacade().submit(shareRelas.get(i));
					}
				}
			}
			setMsg(MSG.S_LOA);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOA);
			Logger.getLogger(this.getClass()).error("ShareRelaAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String stp() throws Exception {
		try {
			if(shareRelas != null && shareRelas.size() > 0) {
				for(int i=0; i<shareRelas.size();i++) {
					if(shareRelas.get(i) != null) {
						//shareRelas.get(i).setLastUpd(getSession().getUserId());
						//shareRelas.get(i).setLastUpdDate(new Date());
						new ShareRelaFacade().submit(shareRelas.get(i));
					}
				}
			}
			setMsg(MSG.S_STP);
		}
		catch(Exception e) {
			setMsg(MSG.F_STP);
			Logger.getLogger(this.getClass()).error("ShareRelaAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String ove() throws Exception {
		try {
			if(shareRelas != null && shareRelas.size() > 0) {
				for(int i=0; i<shareRelas.size();i++) {
					if(shareRelas.get(i) != null){
						//shareRelas.get(i).setLastUpd(getSession().getUserId());
						//shareRelas.get(i).setLastUpdDate(new Date());
						new ShareRelaFacade().submit(shareRelas.get(i));
					}
				}
			}
			setMsg(MSG.S_OVE);
		}
		catch(Exception e) {
			setMsg(MSG.F_OVE);
			Logger.getLogger(this.getClass()).error("ShareRelaAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String chk() throws Exception {
		try {
			if(shareRelas != null && shareRelas.size() > 0) {
				for(int i=0; i<shareRelas.size();i++) {
					if(shareRelas.get(i) != null){
						//shareRelas.get(i).setLastUpd(getSession().getUserId());
						//shareRelas.get(i).setLastUpdDate(new Date());
						new ShareRelaFacade().review(shareRelas.get(i));
					}
				}
			}
			setMsg(MSG.S_CHK);
		}
		catch(Exception e) {
			setMsg(MSG.F_CHK);
			Logger.getLogger(this.getClass()).error("ShareRelaAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String rev() throws Exception {
		try {
			if(shareRelas != null && shareRelas.size() > 0) {
				for(int i=0; i<shareRelas.size();i++) {
					if(shareRelas.get(i) != null) {
						//shareRelas.get(i).setLastUpd(getSession().getUserId());
						//shareRelas.get(i).setLastUpdDate(new Date());
						new ShareRelaFacade().review(shareRelas.get(i));
					}
				}
			}
			setMsg(MSG.S_REV);
		}
		catch(Exception e) {
			setMsg(MSG.F_REV);
			Logger.getLogger(this.getClass()).error("ShareRelaAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String con() throws Exception {
		try {
			if(shareRelas != null && shareRelas.size() > 0) {
				for(int i=0; i<shareRelas.size();i++) {
					if(shareRelas.get(i) != null) {
						//shareRelas.get(i).setLastUpd(getSession().getUserId());
						//shareRelas.get(i).setLastUpdDate(new Date());
						new ShareRelaFacade().confirm(shareRelas.get(i));
					}
				}
			}
			setMsg(MSG.S_CON);
		}
		catch(Exception e) {
			setMsg(MSG.F_CON);
			Logger.getLogger(this.getClass()).error("ShareRelaAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String iss() throws Exception {
		try {
			if(shareRelas != null && shareRelas.size() > 0) {
				for(int i=0; i<shareRelas.size();i++) {
					if(shareRelas.get(i) != null) {
						//shareRelas.get(i).setLastUpd(getSession().getUserId());
						//shareRelas.get(i).setLastUpdDate(new Date());
						new ShareRelaFacade().confirm(shareRelas.get(i));
					}
				}
			}
			setMsg(MSG.S_ISS);
		}
		catch(Exception e) {
			setMsg(MSG.F_ISS);
			Logger.getLogger(this.getClass()).error("ShareRelaAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public String imp() throws Exception {
		try {
			if(fileInp != null) {
				if(shareRelas != null && shareRelas.size() > 0) {
					for(int i=0; i<shareRelas.size();i++) {
						if(shareRelas.get(i) != null) {
							//shareRelas.get(i).setLastUpd(getSession().getUserId());
							//shareRelas.get(i).setLastUpdDate(new Date());
							new ShareRelaFacade().confirm(shareRelas.get(i));
						}
					}
				}
				setMsg(MSG.S_IMP);
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_IMP);
			Logger.getLogger(this.getClass()).error("ShareRelaAction Exception", e);
			return ERROR;
		}
		return PGUPL;
	}
	
	public String prn() throws Exception {
		try {
			if(shareRelas != null && shareRelas.size() > 0) {
				for(int i=0; i<shareRelas.size();i++) {
					if(shareRelas.get(i) != null) {
						//shareRelas.get(i).setLastUpd(getSession().getUserId());
						//shareRelas.get(i).setLastUpdDate(new Date());
						new ShareRelaFacade().confirm(shareRelas.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("ShareRelaAction Exception", e);
			return ERROR;
		}
		return PRINT;
	}
        public String dow() throws Exception {
		try {
			if(shareRelas != null && shareRelas.size() > 0) {
				for(int i=0; i<shareRelas.size();i++) {
					if(shareRelas.get(i) != null) {
						//shareRelas.get(i).setLastUpd(getSession().getUserId());
						//shareRelas.get(i).setLastUpdDate(new Date());
						new ShareRelaFacade().confirm(shareRelas.get(i));
					}
				}
			}
			setMsg(MSG.S_PRN);
		}
		catch(Exception e) {
			setMsg(MSG.F_PRN);
			Logger.getLogger(this.getClass()).error("ShareRelaAction Exception", e);
			return ERROR;
		}
		return MESSAGE;
	}
	public List<ShareRela> getShareRelas() {
		return shareRelas;
	}
	public void setShareRelas(List<ShareRela> shareRelas) {
		this.shareRelas = shareRelas;
	}
	public ShareRela getShareRela() {
		return shareRela;
	}
	public void setShareRela(ShareRela shareRela) {
		this.shareRela = shareRela;
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