package zrsy.vo;

public class ScoDtl extends BasicScoDtl {
	public static final String ALL_FIELDS = "ScoDtl.ScoId,ScoDtl.ComId,ScoDtl.DetpId,ScoDtl.UsrId,Sco.ScoId as ScoId";
	public static final String LIST_FIELDS = "ScoDtl.ScoId,ScoDtl.ComId,ScoDtl.DetpId,ScoDtl.UsrId,Sco.ScoId as ScoId";


	private Integer scoId;


	public Integer getScoId(){
		return scoId;
	}
	public void setScoId(Integer scoId){
		this.scoId = scoId;
	}

}