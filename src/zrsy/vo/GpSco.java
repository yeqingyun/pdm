package zrsy.vo;

public class GpSco extends BasicGpSco {
	public static final String ALL_FIELDS = "GpSco.GpId,GpSco.ScoId,Gp.GpId as GpId,Sco.ScoId as ScoId";
	public static final String LIST_FIELDS = "GpSco.GpId,GpSco.ScoId,Gp.GpId as GpId,Sco.ScoId as ScoId";


	private Integer gpId;
	private Integer scoId;


	public Integer getGpId(){
		return gpId;
	}
	public void setGpId(Integer gpId){
		this.gpId = gpId;
	}
	public Integer getScoId(){
		return scoId;
	}
	public void setScoId(Integer scoId){
		this.scoId = scoId;
	}

}