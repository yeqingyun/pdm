package zrprjt.vo;

public class PrjtRole extends BasicPrjtRole {
	public static final String ALL_FIELDS = "PrjtRole.Sort,PrjtRole.RoleTyp,PrjtRole.RoleId,PrjtRole.Status,PrjtRole.CreateBy,PrjtRole.LastUpd,PrjtRole.CreateDate,PrjtRole.LastDate,PrjtRole.PrjtTypId,PrjtRole.RoleNm,PrjtRole.Remark,PrjtRole.IsRead,PrjtTyp.TypNm as TypNm";
	public static final String LIST_FIELDS = "PrjtRole.Sort,PrjtRole.RoleTyp,PrjtRole.RoleId,PrjtRole.Status,PrjtRole.CreateBy,PrjtRole.LastUpd,PrjtRole.CreateDate,PrjtRole.LastDate,PrjtRole.PrjtTypId,PrjtRole.RoleNm,PrjtRole.Remark,PrjtRole.IsRead,PrjtTyp.TypNm as TypNm";

   private String typNm;

public String getTypNm() {
	return typNm;
}

public void setTypNm(String typNm) {
	this.typNm = typNm;
}



}