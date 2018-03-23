package gnwf.ww;

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
	
	public static final int ITDEPT_MAIL_ID		= 1;						//信息部邮箱ID
	public static final String ITDEPT_MAIL		= "xinxibu@gionee.com";		//信息部邮箱
	
	//public static final String IP_GNFILE		= "http://gnfile.gionee.com:28080/gnfs";//文档平台链接
	
	public static final int MAIL_STATUS_0		= 0;	//邮件发送状态-待发送
	public static final int MAIL_STATUS_1		= 1;	//已发送
	
	public static final int OWFCFG_SPHERE_0 	= 0;	//可查看所有人
	public static final int OWFCFG_SPHERE_1 	= 1;	//只查看本人相关
	
	public static final int OWFRD_SELECT_TYPE_0 	= 0;	//全部流程
	public static final int OWFRD_SELECT_TYPE_1 	= 1;	//我的任务
	public static final int OWFRD_SELECT_TYPE_2 	= 2;	//我发起的流程  
	public static final int OWFRD_SELECT_TYPE_3 	= 3;	//我参与的流程
	public static final int OWFRD_SELECT_TYPE_4 	= 4;	//代理人办理的流程
	public static final int OWFRD_SELECT_TYPE_5 	= 5;	//我参与的流程已处理过的任务
	
	public static final int OWFRD_STATUS_0 	= 0;	//未启动-工作流记录状态
	public static final int OWFRD_STATUS_1 	= 1;	//进行中
	public static final int OWFRD_STATUS_2 	= 2;	//已完成
	public static final int OWFRD_STATUS_3 	= 3;	//已终止
	public static final int OWFRD_STATUS_4 	= 4;	//已作废
	
	public static final int OWFTASK_STATUS_0 	= 0;	//未接收
	public static final int OWFTASK_STATUS_1 	= 1;	//办理中
	public static final int OWFTASK_STATUS_2 	= 2;	//已完成
	public static final int OWFTASK_STATUS_3 	= 3;	//已关闭
	public static final int OWFTASK_STATUS_4 	= 4;	//已作废
	public static final int OWFTASK_STATUS_5 	= 5;	//已收回
	public static final int OWFTASK_STATUS_6 	= 6;	//已退回
	
	public static final int OWFTASK_TYPE_1 		= 1;	//主办
	public static final int OWFTASK_TYPE_2 		= 2;	//协办
	
	public static final int OWFSTEP_TYPE_1 		= 1;	//普通
	public static final int OWFSTEP_TYPE_2 		= 2;	//分支
	public static final int OWFSTEP_TYPE_3 		= 3;	//并发
	
	public static final String OWF_ICON_DOC 	= "doc.gif";	//doc图标
	public static final String OWF_ICON_PPT 	= "ppt.gif";
	public static final String OWF_ICON_XLS 	= "xls.gif";
	public static final String OWF_ICON_TXT 	= "txt.gif";
	public static final String OWF_ICON_PDF 	= "pdf.gif";
	public static final String OWF_ICON_JPG 	= "jpg.gif";
	public static final String OWF_ICON_RAR 	= "rar.gif";
	public static final String OWF_ICON_FILE 	= "file.png";
	
	public static final int PROJTASK_STATUS_0 		= 0;	//未配置
	public static final int PROJTASK_STATUS_1 		= 1;	//未启动
	public static final int PROJTASK_STATUS_2 		= 2;	//已启动
	public static final int PROJTASK_STATUS_3 		= 3;	//已完成
	/**********************问题的状态*************************************************************/
	public static final int WFQUES_STATUS_0 	= 0;		//问题已删除
	public static final int WFQUES_STATUS_1 	= 1;		//问题新增（待解决）
	public static final int WFQUES_STATUS_8	    = 8;		//验证退回
	public static final int WFQUES_STATUS_9	    = 9;		//验证未通过
	public static final int WFQUES_STATUS_10 	= 10;		//问题已回复（待验证）
	public static final int WFQUES_STATUS_11 	= 11;		//验证通过
	public static final int WFQUES_STATUS_19 	= 19;		//问题被退回
	public static final int WFQUES_STATUS_21 	= 21;		//问题已挂起
	public static final int WFQUES_STATUS_30 	= 30;		//问题已关闭
	public static final int WFQUES_STATUS_40 	= 40;		//问题转风险评估，但未启动风险流程
	/**********************问题回复的状态**************************************************************/
	public static final int QUESRESP_STATUS_RETURN = -2;	//验证为退回，效果跟无效一样
	public static final int QUESRESP_STATUS_DISABLE = -1;	//验证为无效
	public static final int QUESRESP_STATUS_0 	= 0;	    //新创建的状态，未给出解决措施
	public static final int QUESRESP_STATUS_1 	= 1;	    //责任人已经给出解决措施
	public static final int QUESRESP_STATUS_2 	= 2;	    //验证效果为有效
	public static final int QUESRESP_STATUS_3 	= 3;	    //挂起状态
	public static final int QUESRESP_STATUS_5 	= 5;	    //转风险评估
	/**********************风险的状态**************************************************************/
	public static final int WFRISK_STATUS_1 = 1;//未评估OPEN
	public static final int WFRISK_STATUS_2 = 2;//评估中
	public static final int WFRISK_STATUS_3 = 3;//已评估
	public static final int WFRISK_STATUS_4 = 4;//作废
	public static final int WFRISK_STATUS_5 = 5;//关闭Close
	
	public static final int WFQUES_RISK_0 	= 0;		//问题
	public static final int WFQUES_RISK_1 	= 1;		//风险问题
	
	public static final int WFQUES_SELECT_TYPE_0 	= 0;	//所有问题
	public static final int WFQUES_SELECT_TYPE_1 	= 1;	//待我解决问题
	public static final int WFQUES_SELECT_TYPE_2 	= 2;	//我已解决的问题
	public static final int WFQUES_SELECT_TYPE_3 	= 3;	//我提出的问题
	
	public static final int WFREPLY_TYPE_0 	= 0;	//解决措施
	public static final int WFREPLY_TYPE_1 	= 1;	//验证效果
	
	
	
	
	
	
	public static final int QUESRESP_TYPE_OLD 	= 0;	//记录类型
	public static final int QUESRESP_TYPE_NEW 	= 1;	//新的责任人说明
	public static final int QUESRESP_TYPR_RETURNBACK 	= 2;	//退回说明你类型
	public static final int QUESRESP_TYPE_HANGUP_REMARK 	= 3;	//挂起说明类型
	public static final int QUESRESP_TYPE_GORISK_REMARK 	= 4;	//走风险说明类型
	
	
	public static final int WFDOC_DOCTYPE_PROC = 1;//过程文档
	public static final int WFDOC_DOCTYPE_BASELIB = 2;//基线文档
	public static final int WFDOC_DOCTYPE_DEFINDOS = 3;//产品定义书文档
	public static final int WFDOC_DOCTYPE_PRJTPOINT = 4;//项目进度表文档
	
	public static final int WFDOC_STATUS_DELETED = -1;//已删除
	public static final int WFDOC_STATUS_DELETED_1 = 1;//有效文档
	public static final int WFDOC_STATUS_DELETED_2 = 2;//正在跑归档流程
	public static final int WFDOC_STATUS_DELETED_3 = 3;//已归档
	public static final int WFDOC_STATUS_DELETED_4 = 4;//正在跑更新流程
	public static final int WFDOC_STATUS_DELETED_5 = 5;//已更新
	public static final int WFDOC_STATUS_DELETED_6 = 6;//正在跑发布流程
	public static final int WFDOC_STATUS_DELETED_7 = 7;//已发布
	
	public static final int SHARE_LIB_USR_ID 	= 16388;	//基线库文档发布给金铭体系文档的李元元

	public static final int SHARE_RELA_STATUS_DELETE = -1;//删除
	public static final int SHARE_RELA_STATUS_0 = 0;//无效、作废
	public static final int SHARE_RELA_STATUS_1 = 1;//已发布
	public static final int SHARE_RELA_STATUS_2 = 2;//取消发布
	
	public static final int WF_GORISK_FLOWID = 43;//走风险流程
	
	public static final int WFRISKREPLY_STATUS_DISABLE = -1;//无效
	public static final int WFRISKREPLY_STATUS_1 = 1;//新建
	public static final int WFRISKREPLY_STATUS_2 = 2;//有效
	public static final int WFRISKREPLY_STATUS_3 = 3;//已给出解决措施
	
	
	public static final int WFFIELD_TYPE_1 = 1;//textarea  占多行
	public static final int WFFIELD_TYPE_2 = 2;//text_签名 只占半行
	public static final int WFFIELD_TYPE_3 = 3;//text_日期  只占半行
	public static final int WFFIELD_TYPE_4 = 4;//radio
	public static final int WFFIELD_TYPE_5 = 5;//checkbox
	public static final int WFFIELD_TYPE_6 = 6;//text_line 占一行
	
	public static final String NoSendMail_UserIDS[] = {"20408","14920","20407","15751","15533"};//任务不邮件通知  //徐黎   20408	//王磊 14920    //卢伟冰 20407 //邓莉 15751
	
	public static final String WfQues_SchCfg ="'小批试产(T1)','小批试产(T1-1)','小批试产(T1-2)','小批试产(T2)','小批试产(T2-1)','小批试产(T2-2)','中批试产(PP)','中批试产(PP-1)','中批试产(PP-2)','批量(MP)'";//可以提出问题的阶段
	
}
