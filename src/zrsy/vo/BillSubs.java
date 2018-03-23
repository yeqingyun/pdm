package zrsy.vo;

public class BillSubs extends BasicBillSubs {
	public static final String ALL_FIELDS = "BillSubs.Id,BillSubs.SyId,BillSubs.Subs,BillSubs.CurrSn,BillSubs.State,SyDef.SyId as SyId";
	public static final String LIST_FIELDS = "BillSubs.Id,BillSubs.SyId,BillSubs.Subs,BillSubs.CurrSn,BillSubs.State,SyDef.SyId as SyId";

	private java.util.List<zrsy.vo.BillTyp> billTyps;

	private Integer syId;

	public java.util.List<zrsy.vo.BillTyp> getBillTyps() {
		return billTyps;
	}
	public void setBillTyps(java.util.List<zrsy.vo.BillTyp> billTyps){
		this.billTyps = billTyps;
	}
	public void addtoBillTyps(zrsy.vo.BillTyp billTyp){
		if(getBillTyps() == null) setBillTyps(new java.util.ArrayList<zrsy.vo.BillTyp>());
			getBillTyps().add(billTyp);
	}

	public Integer getSyId(){
		return syId;
	}
	public void setSyId(Integer syId){
		this.syId = syId;
	}

}