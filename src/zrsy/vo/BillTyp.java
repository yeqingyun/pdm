package zrsy.vo;

public class BillTyp extends BasicBillTyp {
	public static final String ALL_FIELDS = "BillTyp.Id,BillTyp.SubsId,BillTyp.TypNm,BillTyp.Code,BillSubs.Id as Id";
	public static final String LIST_FIELDS = "BillTyp.Id,BillTyp.SubsId,BillTyp.TypNm,BillTyp.Code,BillSubs.Id as Id";


	private Integer id;


	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}

}