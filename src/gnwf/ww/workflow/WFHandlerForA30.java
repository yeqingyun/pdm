package gnwf.ww.workflow;


import gnwf.facade.WFMatlCategoryFacade;
import gnwf.facade.WfRdFieldFacade;
import gnwf.vo.WFMatlCategory;
import gnwf.vo.WfRd;
import gnwf.vo.WfRdField;
import gnwf.ww.MSG;
import gnwf.ww.json.WfRdViewAction;

import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tools.ant.taskdefs.Length;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;



public class WFHandlerForA30 extends WFHandler {

	private static final long serialVersionUID = 4742884725585682821L;
	
	private Sheet sheet;
	private List<WFMatlCategory> cateList;
	
	public WFHandlerForA30(WfRdViewAction action) {
		super(action);
	}
	
	
	@Override
	public void processExec() throws Exception {
		super.processExec();
		
		if(currentTask.getSort()==1){	//第一步骤
			System.out.println("第一步骤");
			String sql = "select WFMatlCategory.CategoryNo from WFMatlCategory where status="+MSG.CONST_STATUS_1+" order by WFMatlCategory.CategoryNo";
			cateList = new WFMatlCategoryFacade().find(sql, "WFMatlCategory.CategoryNo");
			StringBuffer buffer = new StringBuffer();
			if(WFUtil.isNotNull(cateList)){
				for(int i=0;i<cateList.size();i++){
					buffer.append("<option value=\""+cateList.get(i).getCategoryNo()+"\">"+cateList.get(i).getCategoryNo()+"</option>");
				}
			}
			//"<select id='cc' name='cc' onchange='A30fun1(this);'>"
			//buffer.append("</select>");
			
			action.setHiddenValue(buffer.toString());
			action.setCateList(cateList);
			//System.out.println("cateList"+cateList);
		}
	}
	

	@Override
	public void saveJob() throws Exception {
		//long s = System.currentTimeMillis();
		System.out.println("handerforA30的saveJob");
		if(WFUtil.isNotNull(fieldContents)){
			if(currentTask.getSort()==1){
				System.out.println("fieldContents"+fieldContents.toString());
				new WfRdFieldFacade().saveForA30(fieldContents,wfRd.getWfNo());
			}else{
				System.out.println("!=1");
				new WfRdFieldFacade().saveAll(fieldContents,wfRd.getWfNo());
			}
		}
		saveSign();
		//saveSign();
		//saveFile();
		//System.out.println("saveJob时间:"+(System.currentTimeMillis()-s));
		//action.setTips("任务保存成功。。");
	}


	@Override
	public String genAjaxInfo() throws Exception {		//ajax提示
		ajaxShowInfo = "haha";
		
//		if(WFUtil.isNotNull(ajaxList)){
//			for(int i=0;i<ajaxList.size();i++){
//				System.out.println(ajaxList.get(i));
//			}
//		}
		System.out.println("进入wfhandlerforA30-genAjaxInfo");
		if(WFUtil.isNotNull(ajaxList)){
			if("function1".equals(ajaxList.get(0))){			//按规则重复提示
				String mtart = ajaxList.get(1)==null?ajaxList.get(1):ajaxList.get(1).trim(); //物料类型E1
				String matkl = ajaxList.get(2)==null?ajaxList.get(2):ajaxList.get(2).trim(); //物料组E2
				String maktx = URLDecoder.decode(ajaxList.get(3),"UTF-8");					 //物料描述E4
				String groes = URLDecoder.decode(ajaxList.get(4),"UTF-8");					 //大小量纲E5
				
				List<WfRd> list = null;
				//System.out.println(maktx+"~~~~~~~~~~~~~~"+groes);
				if(maktx!=null || groes!=null){
					//System.out.println("maktx!=null || groes!=null");
					list = new WfRdFieldFacade().checkRude(mtart,matkl,maktx,groes,ajaxList.get(5));
				}
				
				if(WFUtil.isNotNull(list)){
					//System.out.println("~~~~~~~~~~~isNotNull~~~~~~~~~~~~~~~~~");
					StringBuffer str = new StringBuffer();
					for(WfRd r:list){
						str.append(r.getWfNo()+"、");	
					}
					ajaxShowInfo = str.substring(0,str.length()-1);
				}else{
					//System.out.println("~~~~~~~~~~~Null~~~~~~~~~~~~~~~~~");
					ajaxShowInfo = null;
				}
			}
			else if("function2".equals(ajaxList.get(0))){		//选物料组提示
				ajaxShowInfo = funcion2(ajaxList.get(1));
			}
		}
		System.out.println(ajaxShowInfo+"~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		return ajaxShowInfo;
	}
	
	protected String funcion2(String cateNo) throws Exception{
		if(cateNo==null || "".equals(cateNo.trim())){
			return null;
		}
		WFMatlCategory c = new WFMatlCategory();
		c.setCategoryNo(cateNo);
		WFMatlCategory cate = new WFMatlCategoryFacade().findBy(c);
		if(cate!=null){
			return cate.getDesc1();
		}
		return null;
	}


	
	@Override
	public String exportXls()throws Exception{
		System.out.println("进入导出方法");
			WritableSheet ws = workbook.createSheet("sheet0", 0);
		
		WritableFont fonts = new WritableFont(WritableFont.TIMES,10,WritableFont.NO_BOLD);
		WritableCellFormat wc = new WritableCellFormat(fonts);
		wc.setAlignment(Alignment.CENTRE); // 设置居中
		wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 设置边框线
		wc.setBackground(jxl.format.Colour.LIGHT_GREEN); // 设置单元格的背景颜色
		
		WritableCellFormat wc1 = new WritableCellFormat(fonts);
		wc1.setAlignment(Alignment.CENTRE);
		wc1.setBorder(Border.ALL, BorderLineStyle.THIN);
		wc1.setBackground(jxl.format.Colour.BRIGHT_GREEN); //背景颜色
		
		WritableCellFormat wc2 = new WritableCellFormat(fonts);
		wc2.setAlignment(Alignment.CENTRE); // 设置居中
		wc2.setBorder(Border.ALL, BorderLineStyle.THIN); // 设置边框线
		wc2.setBackground(jxl.format.Colour.RED); // 设置单元格的背景颜色
		
		WritableCellFormat wc3 = new WritableCellFormat(fonts);
		wc3.setAlignment(Alignment.CENTRE); // 设置居中
		wc3.setBorder(Border.ALL, BorderLineStyle.THIN); // 设置边框线
		wc3.setBackground(jxl.format.Colour.YELLOW); // 设置单元格的背景颜色
		
		WritableCellFormat wcformatcwsj = new WritableCellFormat();
		wcformatcwsj.setAlignment(jxl.format.Alignment.RIGHT);
		
		WritableCellFormat format = new WritableCellFormat(fonts);
		format.setBorder(Border.ALL, BorderLineStyle.THIN); // 设置边框线

		String title = "序号,物料类型,物料组,物料编码,物料描述,物料描述(英文),大小量纲,特定工厂物料状态,内部型号,外部型号,颜色,基本单位,工厂,采购类型,特殊采购类,净重,毛重,重量单位,采购周期(天),最小批量,海关型号,海关物料号,采购单位,分子,舍入值,分母,部件报废率,MRP类型,MRP控制者,批量周期,最大库存水平,安全库存,生产调度员,外部采购地点,旧物料号,产品组,体积,体积单位,采购组,装配报废率,重订货点,最大批量,反冲,收货处理时间(天),生产周期(天),生产批量,生产仓储地点,是否保税仓,策略组,消耗模式,向前消耗期间,向后消耗期间,独立/集中,计划边际码,生产计划参数文件,评估类,价格标识,单价*1000";
		String engTitle = "LINE,MTART,MATKL,MATNR,MAKTX,MAKTX_EN,GROES,MMSTA,FERTH,EXTWG,NORMT,MEINS,WERKS,BESKZ,SOBSL,NTGEW,BRGEW,GEWEI,PLIFZ,BSTMI,WRKST,STAWN,BSTME,BSTUZ,BSTRF,BSTUN,KAUSF,DISMM,DISPO,DISLS,MABST,EISBE,FEVOR,LGFSB,BISMT,SPART,VOLUM,VOLEH,EKGRP,AUSSS,MINBE,BSTMA,RGEKZ,WEBAZ,BEARZ,BASMG,LGPRO,YN,STRGR,VRMOD,VINT2,VINT1,SBDKZ,FHORI,SFCPF,BKLAS,VPRSV,STPRS";
	
		
		int index = 0;
		for(String t:title.split(",")){
			if(index<18){
				if(index == 0) {
					ws.addCell(new Label(index,0,t,wc2));
				} else if (index == 5) {
					ws.addCell(new Label(index,0,t,wc3));
				} else {
					ws.addCell(new Label(index,0,t,wc));
				}
			} else if (index >= 18 && index <= 33) {
				ws.addCell(new Label(index,0,t,wc1));
			} else if (index >= 34 && index <= 54) {
				if(index == 47) {
					ws.addCell(new Label(index,0,t,wc3));
				} else {
					ws.addCell(new Label(index,0,t,wc2));
				}
			} else {
				ws.addCell(new Label(index,0,t,wc1));
			}
			ws.setColumnView(index,10);
			index++;
		}
		
		index = 0;
		for(String t:engTitle.split(",")){
			if(index<18){
				if(index == 0) {
					ws.addCell(new Label(index,1,t,wc2));
				} else if (index == 5) {
					ws.addCell(new Label(index,1,t,wc3));
				} else {
					ws.addCell(new Label(index,1,t,wc));
				}
			} else if (index >= 18 && index <= 33) {
				ws.addCell(new Label(index,1,t,wc1));
			} else if (index >= 34 && index <= 54) {
				if(index == 47) {
					ws.addCell(new Label(index,1,t,wc3));
				} else {
					ws.addCell(new Label(index,1,t,wc2));
				}
			} else {
				ws.addCell(new Label(index,1,t,wc1));
			}
			ws.setColumnView(index,10);
			index++;
		}
		
		//查询任务是否处于DCC审核及数据维护、DCC上传SAP步骤
		String countTaskStepSql = "select count(TaskId) amount from WfRdTask where WfNo = '" + wfRd.getWfNo() + "' and StepId in (349,354) and Status in (0,1) ";
		int countTaskStep = new WfRdFieldFacade().amount(countTaskStepSql);
		
		String extendSql = "";
		//DCC审核及数据维护、DCC上传SAP可导出所有字段
		if(countTaskStep > 0){
			extendSql = "select WfField.FieldId,FieldCode,FieldName,FieldType,a.FieldText as FieldText,RowId " +
					" from WfField left join (select * from WfRdField where wfno='"+wfRd.getWfNo()+"')a " +
					" on(WfField.FieldId=a.FieldId) where flowid="+cfg.getFlowId()+" and status=1 order by RowId";
		} else {
			extendSql = "select WfField.FieldId,FieldCode,FieldName,FieldType,a.FieldText as FieldText,RowId " +
					" from WfField left join (select * from WfRdField where wfno='"+wfRd.getWfNo()+"')a " +
					" on(WfField.FieldId=a.FieldId) where flowid="+cfg.getFlowId()+" and status=1   " +
					" and WfField.FieldId in(select fieldid from WfFieldStepRelate where stepId!=0) order by RowId";
		}
		
		List<WfRdField> fieldContents = new WfRdFieldFacade().find(extendSql, "FieldId,FieldCode,FieldName,FieldType,FieldText,RowId");
		System.out.println("extendSql"+extendSql);
		List<Row> rows = WFUtil.genFieldRows(fieldContents);
		
		int count = 2;
		int j = 0;
		
		if(WFUtil.isNotNull(rows)){
			for(int i=0;i<rows.size();i++){
				String indexforString = String.valueOf(i+1);
				if (j == index) {
					j = 0;
				}
				
				if(rows.get(i)!=null  && WFUtil.isNotNull(rows.get(i).getFileds())){
					List<WfRdField> fields = rows.get(i).getFileds();
					
					
					//序号
					ws.addCell(new Label(j++, count, indexforString,format)); //序号
					//填写物料信息
					ws.addCell(new Label(j++, count, findValue(fields,"E1"),format)); //物料类型
					ws.addCell(new Label(j++, count, findValue(fields,"E2"),format)); //物料组	
					ws.addCell(new Label(j++, count, findValue(fields,"E3"),format)); //物料编码
					ws.addCell(new Label(j++, count, findValue(fields,"E4"),format)); //物料描述
					ws.addCell(new Label(j++, count, findValue(fields,"E58"),format)); //物料描述(英文)
					ws.addCell(new Label(j++, count, findValue(fields,"E5"),format)); //大小量纲
					ws.addCell(new Label(j++, count, findValue(fields,"E6"),format)); //特定工厂物料状态
					ws.addCell(new Label(j++, count, findValue(fields,"E7"),format)); //内部型号
					ws.addCell(new Label(j++, count, findValue(fields,"E8"),format)); //外部型号
					ws.addCell(new Label(j++, count, findValue(fields,"E9"),format)); //颜色
					ws.addCell(new Label(j++, count, findValue(fields,"E10"),format)); //基本单位
					ws.addCell(new Label(j++, count, findValue(fields,"E11"),format)); //工厂
					ws.addCell(new Label(j++, count, findValue(fields,"E12"),format)); //采购类型
					ws.addCell(new Label(j++, count, findValue(fields,"E13"),format)); //特殊采购类
					ws.addCell(new Label(j++, count, findValue(fields,"E14"),format)); //净重
					ws.addCell(new Label(j++, count, findValue(fields,"E15"),format)); //毛重
					ws.addCell(new Label(j++, count, findValue(fields,"E16"),format)); //重量单位
					//采购填单
					ws.addCell(new Label(j++, count, findValue(fields,"E19"),format)); //采购周期(天)
					ws.addCell(new Label(j++, count, findValue(fields,"E20"),format)); //最小批量
					ws.addCell(new Label(j++, count, findValue(fields,"E21"),format)); //海关型号
					ws.addCell(new Label(j++, count, findValue(fields,"E22"),format)); //海关物料号
					ws.addCell(new Label(j++, count, findValue(fields,"E23"),format)); //采购单位
					ws.addCell(new Label(j++, count, findValue(fields,"E24"),format)); //分子
					ws.addCell(new Label(j++, count, findValue(fields,"E25"),format)); //舍入值
					ws.addCell(new Label(j++, count, findValue(fields,"E26"),format)); //分母
					//计调填单
					ws.addCell(new Label(j++, count, findValue(fields,"E27"),format)); //部件报废率
					ws.addCell(new Label(j++, count, findValue(fields,"E28"),format)); //MRP类型
					ws.addCell(new Label(j++, count, findValue(fields,"E29"),format)); //MRP控制者
					ws.addCell(new Label(j++, count, findValue(fields,"E30"),format)); //批量周期
					ws.addCell(new Label(j++, count, findValue(fields,"E31"),format)); //最大库存水平
					ws.addCell(new Label(j++, count, findValue(fields,"E32"),format)); //安全库存
					ws.addCell(new Label(j++, count, findValue(fields,"E33"),format)); //生产调度员
					//采购填单
					ws.addCell(new Label(j++, count, findValue(fields,"E34"),format)); //外部采购地点
					ws.addCell(new Label(j++, count, findValue(fields,"E36"),format)); //旧物料号
					//默认不显示
					ws.addCell(new Label(j++, count, findValue(fields,"E37"),format)); //产品组
					ws.addCell(new Label(j++, count, findValue(fields,"E38"),format)); //体积
					ws.addCell(new Label(j++, count, findValue(fields,"E39"),format)); //体积单位
					ws.addCell(new Label(j++, count, findValue(fields,"E40"),format)); //采购组
					ws.addCell(new Label(j++, count, findValue(fields,"E41"),format)); //装配报废率
					ws.addCell(new Label(j++, count, findValue(fields,"E42"),format)); //重订货点
					ws.addCell(new Label(j++, count, findValue(fields,"E43"),format)); //最大批量
					ws.addCell(new Label(j++, count, findValue(fields,"E44"),format)); //反冲
					ws.addCell(new Label(j++, count, findValue(fields,"E17"),format)); //收货处理时间(天)
					ws.addCell(new Label(j++, count, findValue(fields,"E18"),format)); //生产周期(天)
					ws.addCell(new Label(j++, count, findValue(fields,"E45"),format)); //生产批量			
					//默认不显示
					ws.addCell(new Label(j++, count, findValue(fields,"E46"),format)); //生产仓储地点
					ws.addCell(new Label(j++, count, findValue(fields,"E35"),format)); //是否保税仓
					ws.addCell(new Label(j++, count, findValue(fields,"E47"),format)); //策略组
					ws.addCell(new Label(j++, count, findValue(fields,"E48"),format)); //消耗模式
					ws.addCell(new Label(j++, count, findValue(fields,"E49"),format)); //向前消耗期间
					ws.addCell(new Label(j++, count, findValue(fields,"E50"),format)); //向后消耗期间
					ws.addCell(new Label(j++, count, findValue(fields,"E51"),format)); //独立/集中
					ws.addCell(new Label(j++, count, findValue(fields,"E52"),format)); //计划边际码
					ws.addCell(new Label(j++, count, findValue(fields,"E53"),format)); //生产计划参数文件
					//财务填单 
					ws.addCell(new Label(j++, count, findValue(fields,"E54"),format)); //评估类
					ws.addCell(new Label(j++, count, findValue(fields,"E55"),format)); //价格标识
					ws.addCell(new Label(j++, count, findValue(fields,"E56"),format)); //单价*1000
					count++;
					//System.out.println("111111111111111"+ws);
				}
			}
		}
		
		return null;
	}
	
	@Override
	public String importXls()throws Exception {
		String info = MSG.S_IMP;
		System.out.println("进入导入");
		List<WfRd> wrongList = null;
		
		if(impfile == null) {
			info = "Excel文件为空";
		}else{
			System.out.println("Excel文件不为空");
			//excel导入时特殊字符会乱码，需指定编码方式
			WorkbookSettings setting=new WorkbookSettings();
			setting.setEncoding("iso-8859-1"); 
			Workbook book = Workbook.getWorkbook(impfile,setting);
			sheet = book.getSheet(0);
			System.out.println("sheet"+sheet);
			if(sheet==null){
				info = "Excel内无工作簿";
			}else{
				int rows = sheet.getRows();
				
				if(rows<=2){
					info = "Excel工作簿里内容为空";
				}else{
					String engTitle="LINE,MTART,MATKL,MATNR,MAKTX,MAKTX_EN,GROES,MMSTA,FERTH,EXTWG,NORMT,MEINS,WERKS,BESKZ,SOBSL,";
					StringBuffer title = new StringBuffer();
					for(int i=0;i<15;i++){
						title.append(getContent(i,1)+",");
					}
					
					if(!engTitle.equalsIgnoreCase(title.toString())){
						info = "Excel工作簿里列名错误";
						System.out.println(info);
					}else{
						int checkForinfo = 0;
						List<WfRdField> list = new ArrayList<WfRdField>();
						for(int i=2;i<rows;i++){
							if (resetVO(i,4,992).getFieldText().length()>40) {							
								info = "物料描述   不可以超过40个字符";
								break;
							}else if (resetVO(i,5,1052).getFieldText().length()>40) {								
								info = "物料描述(英文) 不可以超过 40个字符";
								break;
							}else if (resetVO(i,6,993).getFieldText().length()>30) {
								info = "大小量纲    不可以超过 30个字符";
								break;
							}else if (resetVO(i,8,995).getFieldText().length()>18) {
								info = "内部型号  不可以超过18个字符";
								break;
							}else if (resetVO(i,9,996).getFieldText().length()>18) {
								info = "外部型号  不可以超过18个字符";
								break;
							}else {
								list.add(resetVO(i,0,1051));  
								list.add(resetVO(i,1,989));
								list.add(resetVO(i,2,990));
								list.add(resetVO(i,3,991));
								list.add(resetVO(i,4,992));
								list.add(resetVO(i,5,1052));
								list.add(resetVO(i,6,993));
								list.add(resetVO(i,7,994));
								list.add(resetVO(i,8,995));
								list.add(resetVO(i,9,996));
								list.add(resetVO(i,10,997));
								list.add(resetVO(i,11,998));
								list.add(resetVO(i,12,999));
								list.add(resetVO(i,13,1000));
								list.add(resetVO(i,14,1001));
								list.add(resetVO(i,15,1002));
								list.add(resetVO(i,16,1003));
								list.add(resetVO(i,17,1004));
								list.add(resetVO(i,18,1007));
								list.add(resetVO(i,19,1008));
								list.add(resetVO(i,20,1009));
								list.add(resetVO(i,21,1010));
								list.add(resetVO(i,22,1011));
								list.add(resetVO(i,23,1012));
								list.add(resetVO(i,24,1013));
								list.add(resetVO(i,25,1014));
								list.add(resetVO(i,26,1015));
								list.add(resetVO(i,27,1016));
								list.add(resetVO(i,28,1017));
								list.add(resetVO(i,29,1018));
								list.add(resetVO(i,30,1019));
								list.add(resetVO(i,31,1020));
								list.add(resetVO(i,32,1021));
								list.add(resetVO(i,33,1022));
								list.add(resetVO(i,34,1024));
								list.add(resetVO(i,35,1025));
								list.add(resetVO(i,36,1026));
								list.add(resetVO(i,37,1027));
								list.add(resetVO(i,38,1028));
								list.add(resetVO(i,39,1029));
								list.add(resetVO(i,40,1030));
								list.add(resetVO(i,41,1031));
								list.add(resetVO(i,42,1032));
								list.add(resetVO(i,43,1005));
								list.add(resetVO(i,44,1006));
								list.add(resetVO(i,45,1033));
								list.add(resetVO(i,46,1034));
								list.add(resetVO(i,47,1023));
								list.add(resetVO(i,48,1035));
								list.add(resetVO(i,49,1036));
								list.add(resetVO(i,50,1037));
								list.add(resetVO(i,51,1038));
								list.add(resetVO(i,52,1039));
								list.add(resetVO(i,53,1040));
								list.add(resetVO(i,54,1041));
								list.add(resetVO(i,55,1042));
								list.add(resetVO(i,56,1043));
								list.add(resetVO(i,57,1044));
								
								checkForinfo = 1;
							}
							
							
						}
						System.out.println("Excel工作簿里列名正确");
						if (checkForinfo ==1) {
							wrongList = new WfRdFieldFacade().saveForA30(list, wfRd.getWfNo());
						}
						
					}
				}
			}
		}
		
		if(WFUtil.isNotNull(wrongList)){
			String isExcelSame = "";
			StringBuffer buffer = new StringBuffer();
			for(WfRd r:wrongList){
				if(r.getWfNo().equals(wfRd.getWfNo() + "xlsSame")) {
					isExcelSame = "yes";
					break;
				}
				
				if(buffer.indexOf(r.getWfNo())==-1){
					buffer.append(r.getWfNo()+"、");	
				}
			}
			if(isExcelSame.equals("yes")) {
				info = info + "<br>excel中某行的物料描述或大小量纲数据存在与别的行相重复的情况,请删除重复的数据再重新导入!<br>" +
						"<br>不可重复规则如下：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
						"<br>1.1和2开头的电子料，大小量纲必填且不能重复，物料描述可以重复；其他物料大小量纲和物料描述不能同时重复。"+
						"<br>2.181开头大小量纲必填，描述重复时，量纲不可以重复；量纲重复时，描述不可以重复"+
						"<br>3.1899物料组要排除在外";
			} else {
				String str = buffer.substring(0,buffer.length()-1);
				info = info + "<br>存在与别的流程重复,重复部分被过滤!<br>" +
						"(重复流程为："+str+")&nbsp;&nbsp;&nbsp;&nbsp;<br>" +
						"<br>不可重复规则如下：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
						"<br>1.1和2开头的电子料，大小量纲必填且不能重复，物料描述可以重复；其他物料大小量纲和物料描述不能同时重复。"+
						"<br>2.181开头大小量纲必填，描述重复时，量纲不可以重复；量纲重复时，描述不可以重复"+
						"<br>3.1899物料组要排除在外";
			}
			
		/*	"<br>1.物料类型RW01大小量纲不能重复。&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
			"<br>2.其它类型物料描述和大小量纲一起不能重复。" +
			"<br>3.物料组1810或1811都可以重复。&nbsp;&nbsp;&nbsp;"+*/
		}
		
		return info;
	}
	
	protected WfRdField resetVO(int row,int j,int fieldId) {
		WfRdField f = new WfRdField();
		f.setFieldId(fieldId);
		f.setFieldText(getContent(j,row));
		f.setRowId(row-1);
		return f;
	}
	
	protected String findValue(List<WfRdField> list,String code){
		if(WFUtil.isNull(list)){
			return null;
		}
		
		String info = null;
		Iterator<WfRdField> iter = list.iterator();
		while(iter.hasNext()){
			WfRdField f = iter.next();
			if(f!=null && code.equals(f.getFieldCode())){
				System.out.println(code+"----------"+f.getFieldCode());
				info = f.getFieldText();
				System.out.println("info"+info);
				iter.remove();
				break;
			}
		}
		return formatVal(info);
	}
	
	
	protected String formatVal(String info) {
		if(info!=null && "&nbsp;".equals(info)){
			return "";
		}
		return info;
	}
	
	protected String getContent(int j,int i) {
		if(sheet.getCell(j,i)!=null){
			return sheet.getCell(j,i).getContents().trim();
		}
		return null;
	}


	
}
