package gnwf.ww.json;
import gnwf.vo.AddrBook;
import gnwf.vo.MailTo;
import gnwf.ww.BasicAction;
import gnwf.ww.MSG;

import java.util.List;

import org.apache.log4j.Logger;

import zrsy.facade.ComFacade;
import zrsy.facade.DeptFacade;
import zrsy.vo.Com;
import zrsy.vo.Dept;

public class MailToAddAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	private MailTo mailTo = new MailTo();
	private List<Com> baComs;
	private List<Dept> baDepts;
	private List<AddrBook> addrBooks;
	
	
	private String userIdEleId;
	private String userNameEleId;
	private int selectType;
	
	public String execute() throws Exception {

//		baComs = new ComFacade().find("select "+Com.SELF_FIELDS+" " +
//				" from Com where status = 1 ",Com.SELF_FIELDS);
//
//		baDepts = new DeptFacade().find("select "+Dept.SELF_FIELDS+" " +
//				" from Dept where status = 1 ",Dept.SELF_FIELDS);
		
		//公司部门
		baComs = new ComFacade().find("select "+Com.SELF_FIELDS+" " +
				" from Com where status = 2 ",Com.SELF_FIELDS);

		String deptsql = "WITH CTE as" +
				" (" +
				" SELECT DeptId,Parent,DeptNo,DeptNm,leve,Status FROM Dept WHERE Dept.parent = '10100001' " +
				" UNION ALL" +
				" SELECT A.DeptId,A.Parent,A.DeptNo,A.DeptNm,A.leve,A.Status FROM Dept A,CTE " +
				" where A.parent = CTE.deptId" +
				" ) " +
				" SELECT *  from CTE";
		baDepts = new DeptFacade().find(deptsql,"DeptId,Parent,DeptNo,DeptNm,Leve,Status");
		
		AddrBook addrBook = new AddrBook();
		addrBook.setStatus(MSG.CONST_STATUS_1);
		
		//addrBooks = new AddrBookFacade().findAll(addrBook);
		
		for(int i=0; i<baComs.size(); i++) {
			for(int j=0; j<baDepts.size(); j++) {
				if(baComs.get(i).getComId().equals(baDepts.get(j).getComId()))
					baComs.get(i).addtoDepts(baDepts.get(j));
			}
		}
		 //System.out.println("userIdEleId"+userIdEleId);
	     //System.out.println("userNameEleId"+userNameEleId);
	     //System.out.println("selectType"+selectType);
		return INITIALIZES;
	}
	public String sav() throws Exception {
		try {
			//mailTo.setCreateBy(getSession().getUserId());
			//mailTo.setCreateDate(new Date());
			//mailTo.setLastUpd(getSession().getUserId());
			//mailTo.setLastUpdDate(new Date());
			
			//new MailToFacade().save(mailTo);
			this.setMsg(MSG.S_NEW);
		}
		catch(Exception e) {
			this.setMsg(MSG.F_NEW);
			Logger.getLogger(this.getClass()).error("MailToAddAction add Exception", e);
			return ERROR;
		}
		return SUCCESS;
	}
	public MailTo getMailTo() {
		return mailTo;
	}
	public void setMailTo(MailTo mailTo) {
		this.mailTo = mailTo;
	}
	public List<Com> getBaComs() {
		return baComs;
	}
	public void setBaComs(List<Com> baComs) {
		this.baComs = baComs;
	}
	public List<Dept> getBaDepts() {
		return baDepts;
	}
	public void setBaDepts(List<Dept> baDepts) {
		this.baDepts = baDepts;
	}
	public List<AddrBook> getAddrBooks() {
		return addrBooks;
	}
	public void setAddrBooks(List<AddrBook> addrBooks) {
		this.addrBooks = addrBooks;
	}
	public void setUserIdEleId(String userIdEleId) {
		this.userIdEleId = userIdEleId;
	}
	public String getUserIdEleId() {
		return userIdEleId;
	}
	public void setUserNameEleId(String userNameEleId) {
		this.userNameEleId = userNameEleId;
	}
	public String getUserNameEleId() {
		return userNameEleId;
	}
	public void setSelectType(int selectType) {
		this.selectType = selectType;
	}
	public int getSelectType() {
		return selectType;
	}
}
