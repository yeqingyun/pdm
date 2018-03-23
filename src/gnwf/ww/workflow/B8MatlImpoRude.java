package gnwf.ww.workflow;

public class B8MatlImpoRude {
	
	//private String matlGroup = "1810,1811";		//此类数据组不用过滤，可以重复
	private String matlGroup = "1899";		//此类数据组不用过滤，可以重复
	private String matlCate = "rw01=101";		//"rw01=101|rw02=110"	第1位物料描述,2大小量纲,3两者(1可以重复,0不可重复)
	
	private String matklCate = "1=100|2=100";  //物料组1和2开头的电子料，大小量纲不能重复，物料描述可以重复；其他物料大小量纲和物料描述不能同时重复。
	
	//是否存在数据组,存在则不用过滤
	public boolean isInGroup(String group){
		if(group!=null && !"".equals(group) && matlGroup.contains(group.toLowerCase())){
			return true;
		}
		return false;
	}
	
	//是否数据组为1，2开头
	public boolean ismatklCate(String cate){
		
		 char first = cate.charAt(0);
		 System.out.println(String.valueOf(first));
		if(cate==null || "".equals(cate)){
			return false;
		}else{
			String[] strs = matklCate.split("\\|");
				String s = strs[0];
				if(s.split("=")[0].equalsIgnoreCase(String.valueOf(first))){
					return true;
			}
		}
		return false;
	}
	
	
	//是否数据组为1，2开头
	public boolean isInCageGroup(String cate){
//		if(cate!=null && !"".equals(cate) && matlCate.contains(cate.toLowerCase())){
//			return true;
//		}
		char first = cate.charAt(0);
		 System.out.println(String.valueOf(first));
		/*if(cate.charAt(0)=='1' && cate.charAt(1)=='8' &&cate.charAt(2)=='1'){//181开头描述重复时，量纲不可以重复；量纲重复时，描述不可以重复
			return false;
		}*/
		if(cate==null || "".equals(cate)){
			return false;
		}else{
			String[] strs = matklCate.split("\\|");
			for(int i=0;i<strs.length;i++){
				String s = strs[i];
				if(s.split("=")[0].equalsIgnoreCase(String.valueOf(first))){
					return true;
				}
			}
		}
		
		return false;
	}
	
	//判断是否181开头
	public boolean isIn181Group(String cate){

		char first = cate.charAt(0);
		 System.out.println(String.valueOf(first));
		if(cate.charAt(0)=='1' && cate.charAt(1)=='8' &&cate.charAt(2)=='1'){//181开头描述重复时，量纲不可以重复；量纲重复时，描述不可以重复
			return true;
		}else{
			return false;
		}
		
		
	}
	
	
	//物料描述-为true不可重复
	public boolean isMaktxUni(String cate) {
		if(isInCageGroup(cate)){
			String str = getRude(cate);
			if("0".equals(str.split("|")[1])){	//匹配第一位
				return true;
			}
		}
		return false;
	}
	
	//大小量纲-为true不可重复
	public boolean isGroesUni(String cate) {
		if(isInCageGroup(cate)){
			String str = getRude(cate);
			if("0".equals(str.split("|")[2])){	//匹配第二位
				return true;
			}
		}
		return false;
	}
	
	//物料描述&大小量纲-为true两者不可重复
	public boolean isBothUni(String cate) {
		if(isInCageGroup(cate)){
			String str = getRude(cate);
			if("0".equals(str.split("|")[3])){	//匹配第三位
				return true;
			}else{
				return false;
			}
		}
		return true;
	}
	
	//拆分取规则
	protected String getRude(String cate) {
		String[] str = matklCate.split("\\|");
		for(String s:str){
			//if(s.contains(cate.toLowerCase())){
				System.out.println(s.split("=")[1]);
				return s.split("=")[1];
			//}
		}
		return null;
	}
	
	public static void main(String args[]){
//		String a = "123456=7890";
//		for(String s:a.split("|")){
//			System.out.println(s);
//		}
//		System.out.println(a.split("=")[1]);
		
		B8MatlImpoRude rude = new B8MatlImpoRude();
		System.out.println(rude.isMaktxUni("RW01"));
		System.out.println(rude.isGroesUni("RW01"));
		System.out.println(rude.isBothUni("RW01"));
	}
	
}
