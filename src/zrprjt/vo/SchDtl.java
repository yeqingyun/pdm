package zrprjt.vo;

public class SchDtl extends BasicSchDtl {
	public static final String ALL_FIELDS = "SchDtl.SchDtlId,SchDtl.SchId,SchDtl.Status,SchDtl.CreateBy,SchDtl.LastUpd,SchDtl.CreateDate,SchDtl.LastDate,SchDtl.DocNm,SchDtl.Remark,SchCfg.SchNm as SchNm";
	public static final String LIST_FIELDS = "SchDtl.SchDtlId,SchDtl.SchId,SchDtl.Status,SchDtl.CreateBy,SchDtl.LastUpd,SchDtl.CreateDate,SchDtl.LastDate,SchDtl.DocNm,SchDtl.Remark,SchCfg.SchNm as SchNm";


   private 	String schNm;


public String getSchNm() {
	return schNm;
}


public void setSchNm(String schNm) {
	this.schNm = schNm;
}


}