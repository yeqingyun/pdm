package zrsy.ww;

public class MSG {

	public static final int CONST_STATUS_D 		= -1;	//删除
	public static final int CONST_STATUS_0 		= 0;	//无效、作废
	public static final int CONST_STATUS_1 		= 1;	//有效
	public static final int CONST_STATUS_2 		= 2;	//新增
	public static final int CONST_STATUS_3 		= 3;	//取消
	public static final int CONST_STATUS_4 		= 4;	//撤回
	public static final int CONST_STATUS_5 		= 5;	//中止
	public static final int CONST_STATUS_6 		= 6;	//提交
	public static final int CONST_STATUS_7 		= 7;	//审核
	public static final int CONST_STATUS_8 		= 8;	//复核
	public static final int CONST_STATUS_9 		= 9;	//确认
	public static final int CONST_STATUS_10     = 10;   //开始
	public static final int CONST_STATUS_11     = 11;   //中止
	public static final int CONST_STATUS_12     = 12 ;  //完成
	public static final int CONST_STATUS_13     = 13 ;  //发布
	
	public static final int CONST_OUT_PRIMARY 	= 0; 	//主办
	public static final int CONST_OUT_HELP 		= 1;	//协办
	public static final int CONST_OUT_READ 		= 2;	//阅知

	public static final int CONST_STATUS_I 		= 0;	//无效
	public static final int CONST_STATUS_V 		= 1;	//有效
	
	public static final String S_DEL		= "删除记录完成。";
	public static final String F_DEL		= "删除记录失败。";

	public static final String S_VOI		= "作废记录完成。";
	public static final String F_VOI		= "作废记录失败。";

	public static final String S_STP		= "中止记录完成。";
	public static final String F_STP		= "中止记录失败。";

	public static final String S_CAN		= "取消记录完成。";
	public static final String F_CAN		= "取消记录失败。";

	public static final String S_SEA		= "查询记录完成。";
	public static final String F_SEA		= "查询记录失败。";
	
	public static final String S_NEW		= "新增记录完成。";
	public static final String F_NEW		= "新增记录失败。";
	
	public static final String S_SAV		= "保存记录完成。";
	public static final String F_SAV		= "保存记录失败。";
	
	public static final String S_UPD		= "更新记录完成。";
	public static final String F_UPD		= "更新记录失败。";

	public static final String S_LOA		= "上传记录完成。";
	public static final String F_LOA		= "上传记录失败。";

	public static final String S_SUB		= "提交记录完成。";
	public static final String F_SUB		= "提交记录失败。";

	public static final String S_POS		= "发布记录完成。";
	public static final String F_POS		= "发布记录失败。";

	public static final String S_STA		= "开始记录完成。";
	public static final String F_STA		= "开始记录失败。";

	public static final String S_CHK		= "审核记录完成。";
	public static final String F_CHK		= "审核记录失败。";

	public static final String S_REV		= "复核记录完成。";
	public static final String F_REV		= "复核记录失败。";

	public static final String S_CON		= "确认记录完成。";
	public static final String F_CON		= "确认记录失败。";

	public static final String S_IMP		= "导入记录完成。";
	public static final String F_IMP		= "导入记录失败。";

	public static final String S_EXP		= "导出记录完成。";
	public static final String F_EXP		= "导出记录失败。";

	public static final String S_PRN		= "打印记录完成。";
	public static final String F_PRN		= "打印记录失败。";
	
	public static final String S_OVE		= "完成记录。";
	public static final String F_OVE		= "完成失败。";
	
	public static final String S_ISS		= "发布记录完成。";
	public static final String F_ISS		= "发布记录失败。";

	public static final String S_DC			= "数据收集完成。";
	public static final String F_DC			= "数据收集失败。";

	public static final String S_END		= "记录完成。";
	public static final String F_END		= "记录完成失败。";

	public static final String S_LOG		= "登录成功。";
	public static final String F_LOG		= "登录失败。";
	
	public static final String S_CANCLE		= "撤回成功。";
	public static final String F_CANCLE		= "撤回失败。";
	
	public static final String F_RESUBMIT 	= "因页面反复刷新，重复提交数据，此为非法操作，页面将跳转。";
	public static final String S_WELCOME	= "欢迎您访问金立办公系统。";
	
	
	public static final String S_APP			= "审批记录完成。";
	public static final String F_APP			= "审批记录失败。";
	
	public static final String S_CPWD = "密码修改成功。";
	public static final String F_CPWD= "密码修改失败。";
}
