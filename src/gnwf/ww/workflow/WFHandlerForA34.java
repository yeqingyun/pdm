package gnwf.ww.workflow;

import gnwf.facade.WfRdFieldFacade;
import gnwf.vo.WfRdField;
import gnwf.ww.MSG;
import gnwf.ww.json.WfRdViewAction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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



public class WFHandlerForA34 extends WFHandler {

	private static final long serialVersionUID = 4742884725585682821L;
	
	private Sheet sheet;
	
	public WFHandlerForA34(WfRdViewAction action) {
		super(action);
	}
	

	@Override
	public String exportXls()throws Exception{
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
		
		WritableCellFormat wcformatcwsj = new WritableCellFormat();
		wcformatcwsj.setAlignment(jxl.format.Alignment.RIGHT);
		
		WritableCellFormat format = new WritableCellFormat(fonts);
		format.setBorder(Border.ALL, BorderLineStyle.THIN); // 设置边框线

//		String title = "物料类型,物料组,物料编码,物料描述,大小量纲,特定工厂物料状态,内部型号,外部型号,颜色,基本单位,工厂,采购类型,特殊采购类,净重,毛重,重量单位,采购周期(天),最小批量,海关型号,海关物料号,采购单位,分子,舍入值,分母,部件报废率,MRP类型,MRP控制者,批量周期,最大库存水平,安全库存,生产调度员,外部采购地点,评估类,价格标识,单价*1000";
//		String engTitle = "MTART,MATKL,MATNR,MAKTX,GROES,MMSTA,FERTH,EXTWG,NORMT,MEINS,WERKS,BESKZ,SOBSL,NTGEW,BRGEW,GEWEI,PLIFZ,BSTMI,WRKST,STAWN,BSTME,BSTUZ,BSTRF,BSTUN,KAUSF,DISMM,DISPO,DISLS,MABST,EISBE,FEVOR,LGFSB,BKLAS,VPRSV,STPRS";
		
		String title = "物料组,物料组(新),物料编码,物料编码(新),物料描述,物料描述(新),物料描述(英文),物料描述(英文)(新),大小量纲,大小量纲(新),特定工厂物料状态,特定工厂物料状态(新),工厂,工厂(新),内部型号,内部型号(新),外部型号,外部型号(新),颜色,颜色(新),备注,备注(新)";
		String engTitle="MATKL,MATKL(N),MATNR,MATNR(N),MAKTX,MAKTX(N),MAKTX_EN,MAKTX_EN(N),GROES,GROES(N),MMSTA,MMSTA(N),WERKS,WERKS(N),FERTH,FERTH(N),EXTWG,EXTWG(N),NORMT,NORMT(N),REMARKS,REMARKS(N)";
		
		int index = 0;
		for(String t:title.split(",")){
			if(index%2==0){
				ws.addCell(new Label(index,0,t,wc));
			}else{
				ws.addCell(new Label(index,0,t,wc1));
			}
			
			ws.setColumnView(index,10);
			index++;
		}
		
		index = 0;
		for(String t:engTitle.split(",")){
			if(index%2==0){
				ws.addCell(new Label(index,1,t,wc));
			}else{
				ws.addCell(new Label(index,1,t,wc1));
			}
			ws.setColumnView(index,10);
			index++;
		}
		
		String extendSql = "select WfField.FieldId,FieldCode,FieldName,FieldType,a.FieldText as FieldText,RowId " +
				" from WfField left join (select * from WfRdField where wfno='"+wfRd.getWfNo()+"')a " +
				" on(WfField.FieldId=a.FieldId) where flowid="+cfg.getFlowId()+" and status=1" +
				" and WfField.FieldId in(select fieldid from WfFieldStepRelate where stepId!=0) order by RowId";
		List<WfRdField> fieldContents = new WfRdFieldFacade().find(extendSql, "FieldId,FieldCode,FieldName,FieldType,FieldText,RowId");
		List<Row> rows = WFUtil.genFieldRows(fieldContents);
		
		int count = 2;
		int j = 0;
		
		if(WFUtil.isNotNull(rows)){
			for(int i=0;i<rows.size();i++){
				if (j == index) {
					j = 0;
				}
				
				if(rows.get(i)!=null  && WFUtil.isNotNull(rows.get(i).getFileds())){
					List<WfRdField> fields = rows.get(i).getFileds();
					
					ws.addCell(new Label(j++, count, findValue(fields,"E1"),format));
					ws.addCell(new Label(j++, count, findValue(fields,"E11"),format));
					
					ws.addCell(new Label(j++, count, findValue(fields,"E2"),format));
					ws.addCell(new Label(j++, count, findValue(fields,"E12"),format));
					
					ws.addCell(new Label(j++, count, findValue(fields,"E3"),format));
					ws.addCell(new Label(j++, count, findValue(fields,"E13"),format));
					
					ws.addCell(new Label(j++, count, findValue(fields,"E21"),format));
					ws.addCell(new Label(j++, count, findValue(fields,"E22"),format));
					
					ws.addCell(new Label(j++, count, findValue(fields,"E4"),format));
					ws.addCell(new Label(j++, count, findValue(fields,"E14"),format));
					
					ws.addCell(new Label(j++, count, findValue(fields,"E5"),format));
					ws.addCell(new Label(j++, count, findValue(fields,"E15"),format));
					
					ws.addCell(new Label(j++, count, findValue(fields,"E6"),format));
					ws.addCell(new Label(j++, count, findValue(fields,"E16"),format));
					
					ws.addCell(new Label(j++, count, findValue(fields,"E7"),format));
					ws.addCell(new Label(j++, count, findValue(fields,"E17"),format));
					
					ws.addCell(new Label(j++, count, findValue(fields,"E8"),format));
					ws.addCell(new Label(j++, count, findValue(fields,"E18"),format));
					
					ws.addCell(new Label(j++, count, findValue(fields,"E9"),format));
					ws.addCell(new Label(j++, count, findValue(fields,"E19"),format));
					
					ws.addCell(new Label(j++, count, findValue(fields,"E10"),format));
					ws.addCell(new Label(j++, count, findValue(fields,"E20"),format));
					count++;
				}
			}
		}
		
		return null;
	}
	
	@Override
	public String importXls()throws Exception {
		String info = MSG.S_IMP;
		
		if(impfile == null) {
			info = "Excel文件为空";
		}else{
			//excel导入时特殊字符会乱码，需指定编码方式
			WorkbookSettings setting=new WorkbookSettings();
			setting.setEncoding("iso-8859-1"); 
			Workbook book = Workbook.getWorkbook(impfile,setting);
			sheet = book.getSheet(0);
			if(sheet==null){
				info = "Excel内无工作簿";
			}else{
				int rows = sheet.getRows();
				if(rows<=2){
					info = "Excel工作簿里内容为空";
				}else{
					String engTitle="MATKL,MATKL(N),MATNR,MATNR(N),MAKTX,MAKTX(N),MAKTX_EN,MAKTX_EN(N),GROES,GROES(N),MMSTA,MMSTA(N),WERKS,WERKS(N),FERTH,FERTH(N),EXTWG,EXTWG(N),NORMT,NORMT(N),REMARKS,REMARKS(N),";

					StringBuffer title = new StringBuffer();
					for(int i=0;i<22;i++){
						title.append(getContent(i,1)+",");
					}
					
					if(!engTitle.equalsIgnoreCase(title.toString())){
						info = "Excel工作簿里列名错误";
					}else{
						int checkForinfo = 0;
						List<WfRdField> list = new ArrayList<WfRdField>();
						
						for(int i=2;i<rows;i++){
							//int startFieldId = 1007;
							//int fieldCount = 20;
							//for(int j=0;j<fieldCount;j++){
							
							if (resetVO(i,4,971).getFieldText().length()>40) {
								info = "物料描述   不可以超过40个字符";
								break;
							}else if (resetVO(i,5,981).getFieldText().length()>40) {
								info = "物料描述   不可以超过40个字符";
								break;
							}else if (resetVO(i,6,1053).getFieldText().length()>40) {
								info = "物料描述(英文) 不可以超过 40个字符";
								break;
							}else if (resetVO(i,7,1054).getFieldText().length()>40) {
								info = "物料描述(英文) 不可以超过 40个字符";
								break;
							}else if (resetVO(i,8,972).getFieldText().length()>30) {
								info = "大小量纲    不可以超过 30个字符";
								break;
							}else if (resetVO(i,9,982).getFieldText().length()>30) {
								info = "大小量纲    不可以超过 30个字符";
								break;
							}else if (resetVO(i,14,975).getFieldText().length()>18) {
								info = "内部型号  不可以超过18个字符";
								break;
							}else if (resetVO(i,15,1047).getFieldText().length()>18) {
								info = "内部型号  不可以超过18个字符";
								break;
							}else if (resetVO(i,16,976).getFieldText().length()>18) {
								info = "外部型号  不可以超过18个字符";
								break;
							}else if (resetVO(i,17,1048).getFieldText().length()>18) {
								info = "外部型号  不可以超过18个字符";
								break;
							}else {
							
							
								list.add(resetVO(i,0,969));
								list.add(resetVO(i,1,979));
								
								list.add(resetVO(i,2,970));
								list.add(resetVO(i,3,980));
								
								list.add(resetVO(i,4,971));
								list.add(resetVO(i,5,981));
								
								list.add(resetVO(i,6,1053));
								list.add(resetVO(i,7,1054));
								
								list.add(resetVO(i,8,972));
								list.add(resetVO(i,9,982));
								
								list.add(resetVO(i,10,973));
								list.add(resetVO(i,11,1045));
								
								list.add(resetVO(i,12,974));
								list.add(resetVO(i,13,1046));
								
								list.add(resetVO(i,14,975));
								list.add(resetVO(i,15,1047));
								
								list.add(resetVO(i,16,976));
								list.add(resetVO(i,17,1048));
								
								list.add(resetVO(i,18,977));
								list.add(resetVO(i,19,1049));
								
								list.add(resetVO(i,20,978));
								list.add(resetVO(i,21,1050));
								//list.add(resetVO(i,j,startFieldId++));
								checkForinfo = 1;
							}
								
						}
						if (checkForinfo ==1) {
						new WfRdFieldFacade().saveAll(list, wfRd.getWfNo());
						}
					}
				}
			}
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
				info = f.getFieldText();
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
