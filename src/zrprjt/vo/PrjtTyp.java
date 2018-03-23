package zrprjt.vo;

public class PrjtTyp extends BasicPrjtTyp {
	public static final String ALL_FIELDS = "PrjtTyp.TypId,PrjtTyp.Status,PrjtTyp.CreateBy,PrjtTyp.LastUpd,PrjtTyp.CreateDate,PrjtTyp.LastDate,PrjtTyp.TypNm,PrjtTyp.Remark";
	public static final String LIST_FIELDS = "PrjtTyp.TypId,PrjtTyp.Status,PrjtTyp.CreateBy,PrjtTyp.LastUpd,PrjtTyp.CreateDate,PrjtTyp.LastDate,PrjtTyp.TypNm,PrjtTyp.Remark";

	private java.util.List<zrprjt.vo.PrjtDef> prjtDefs;


	public java.util.List<zrprjt.vo.PrjtDef> getPrjtDefs() {
		return prjtDefs;
	}
	public void setPrjtDefs(java.util.List<zrprjt.vo.PrjtDef> prjtDefs){
		this.prjtDefs = prjtDefs;
	}
	public void addtoPrjtDefs(zrprjt.vo.PrjtDef prjtDef){
		if(getPrjtDefs() == null) setPrjtDefs(new java.util.ArrayList<zrprjt.vo.PrjtDef>());
			getPrjtDefs().add(prjtDef);
	}


}