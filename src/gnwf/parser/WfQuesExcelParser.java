package gnwf.parser;

import gnwf.facade.QuesRespFacade;
import gnwf.facade.WfQuesFacade;
import gnwf.facade.WfQuesTestItemFacade;
import gnwf.vo.PicXY;
import gnwf.vo.QuesResp;
import gnwf.vo.WfQues;
import gnwf.vo.WfQuesTestItem;
import gnwf.ww.MSG;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Utils.DateUtil;
import zrprjt.facade.SchCfgFacade;
import zrprjt.vo.SchCfg;
import zrsy.vo.Usr;

public class WfQuesExcelParser implements ExcelParser<WfQues> {
	
	public static final int WFQUES_EXCEL_FIELD_COUNT = 24;
	
	public static final String WFQUES_EXCEL_FIELD_TITLE = "问题标题";
	public static final String WFQUES_EXCEL_FIELD_COMPLETEDDATE = "要求完成日期";
	public static final String WFQUES_EXCEL_FIELD_WFNAME = "工作流名称";
	
	public static final String WFQUES_EXCEL_FIELD_ID = "问题编号";
	public static final String WFQUES_EXCEL_FIELD_FINAL_STATUS = "最终状态";
	public static final String WFQUES_EXCEL_FIELD_CATE = "等级";
	public static final String WFQUES_EXCEL_FIELD_SCHEU = "阶段";
	public static final String WFQUES_EXCEL_FIELD_QUESTEXT = "问题描述";
	public static final String WFQUES_EXCEL_FIELD_PIC = "图片";
	public static final String WFQUES_EXCEL_FIELD_QUESANALYSIS= "原因分析";
	public static final String WFQUES_EXCEL_FIELD_RESULT = "改善措施";
	public static final String WFQUES_EXCEL_FIELD_DEPTNM = "责任部门";
	public static final String WFQUES_EXCEL_FIELD_USER = "责任人";
	public static final String WFQUES_EXCEL_FIELD_CREATEDATE = "提交时间";
	public static final String WFQUES_EXCEL_FIELD_SLOV_TIME = "解决时长";
	public static final String WFQUES_EXCEL_FIELD_SLOV_QUESTYPE= "分类";
	public static final String WFQUES_EXCEL_FIELD_SLOV_SOURCE= "来源";
	public static final String WFQUES_EXCEL_FIELD_STATUS = "解决状态";
	public static final String WFQUES_EXCEL_FIELD_IDTFUSER = "提交人";
	public static final String WFQUES_EXCEL_FIELD_IDTFDATE = "验证日期";
	public static final String WFQUES_EXCEL_FIELD_PRJTNM = "项目名称";
	
	public static final String WFQUES_EXCEL_FIELD_IDTFRES = "验证说明";
	public static final String WFQUES_EXCEL_FIELD_FRACTION_DEFECTIVE = "不良比例";
	
	public static final String WFQUES_EXCEL_FIELD_TESTITEM = "测试项";
	public static final String WFQUES_EXCEL_FIELD_SERIAL = "序号";

/*	public static final String[] WFQUES_EXCEL_FIELD = {"问题编号","最终状态","问题等级","提出阶段","问题描述","图片","原因分析","改善措施","责任部门","责任人","提交时间",
		"解决时长","问题类别","问题来源","解决状态","验证人","验证日期","项目名称","验证说明","不良比例"};*/
	//导出模版字段排序
//2015-5-25
//	public static final String[] WFQUES_EXCEL_FIELD = {"最终状态","等级","解决状态","阶段","问题描述","不良比例","原因分析","改善措施","责任人","责任部门","分类","来源","提交时间",
//		"验证人","验证日期","解决时长","问题编号","项目名称"};
	
	public static final String[] WFQUES_EXCEL_FIELD = {"序号","阶段","测试项","问题描述","不良比例","等级","分类","原因分析","改善措施","责任人","提交人","来源","问题编号"};
	//设置导出模板的每个字段的高和宽
	public static final float ROW_TITLE_HEIGTH = 40.00F;
	public static final float ROW_CONTENT_HEIGTH = 35.00F;
	//设置通用字段宽
	public static final int CELL_TITLE1_WIGTH = 13*256;
	//设置导出图片宽
	public static final int CELL_TITLE2_WIGTH = 72*256;
	
	@Override
	public List<WfQues> parser2003(InputStream is) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook(is);
		return read(workbook);
	}

	@Override
	public List<WfQues> parser2007(InputStream is) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(is);
		return read(workbook);
	}
	/**
	 * 读取Excel
	 * @param worbook
	 * @return
	 */
	private List<WfQues> read (Workbook worbook) {
		List<WfQues> wfQuesList = new ArrayList<WfQues>();
		Map<String,Integer> position = getPosition(worbook);
		
//		if(position.size() != WFQUES_EXCEL_FIELD_COUNT) {
//			return wfQuesList;
//		}
		
		Sheet sheet = worbook.getSheetAt(0);
		Map<Integer,Integer> picPosition = getPicPosition(worbook);
		List<HSSFPictureData> picDatas = null;
		if(picPosition.size() > 0){
			picDatas = ((HSSFWorkbook)worbook).getAllPictures();
		}
		
		List<SchCfg> schCfgs = null;
		try {
			schCfgs = new SchCfgFacade().find("select " + SchCfg.SELF_FIELDS + " from SchCfg where SchCfg.SchNm in ("+MSG.WfQues_SchCfg+")", SchCfg.SELF_FIELDS);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
		
		for(int i=1;i<sheet.getPhysicalNumberOfRows();i++) {
			WfQues wfQues = new WfQues();
			Row row = sheet.getRow(i);
			
			if(position.get(WFQUES_EXCEL_FIELD_ID)!=null){
				Cell cell1 = row.getCell(position.get(WFQUES_EXCEL_FIELD_ID));
				if(cell1 != null && cell1.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					wfQues.setQuesId(String.valueOf(cell1.getNumericCellValue()));
				}else if(cell1 != null && cell1.getCellType() == Cell.CELL_TYPE_STRING){
					wfQues.setQuesId(cell1.getStringCellValue());
				}
			}
			
            if(position.get(WFQUES_EXCEL_FIELD_TITLE)!=null){
            	Cell cell2 = row.getCell(position.get(WFQUES_EXCEL_FIELD_TITLE));
            	if(cell2 != null && cell2.getCellType() == Cell.CELL_TYPE_STRING) {
            		wfQues.setTitle(cell2.getStringCellValue());
            	}
			}
		
            if(position.get(WFQUES_EXCEL_FIELD_QUESTEXT)!=null){
            	Cell cell3 = row.getCell(position.get(WFQUES_EXCEL_FIELD_QUESTEXT));
            	if(cell3 != null && cell3.getCellType() == Cell.CELL_TYPE_STRING) {
            		wfQues.setQuesText(cell3.getStringCellValue());
            		
            		if(cell3.getStringCellValue()==null ||cell3.getStringCellValue().trim().isEmpty()){
            			return wfQuesList;
            		}
            	}else{
            		return wfQuesList;
            	}
			}
			
            if(position.get(WFQUES_EXCEL_FIELD_CATE)!=null){
            	Cell cell4 = row.getCell(position.get(WFQUES_EXCEL_FIELD_CATE));
            	if(cell4 != null && cell4.getCellType() == Cell.CELL_TYPE_STRING) {
                		String source = cell4.getStringCellValue();
                		Integer cateId = null;
                		if(source.equals("S")){
                			cateId = 1;
                		}else if(source.equals("A")){
                			cateId = 2;
                		}else if(source.equals("B")){
                			cateId = 3;
                		}else if(source.equals("C")){
                			cateId = 4;
                		}else if(source.equals("致命_S")){
                			cateId = 1;
                		}else if(source.equals("严重_A")){
                			cateId = 2;
                		}else if(source.equals("重_B")){
                			cateId = 3;
                		}else if(source.equals("轻_C")){
                			cateId = 4;
                		}
            		wfQues.setCateId(cateId);
            	}
			}
			
            if(position.get(WFQUES_EXCEL_FIELD_USER)!=null){
            	Cell cell5 = row.getCell(position.get(WFQUES_EXCEL_FIELD_USER));
            	if(cell5 != null && cell5.getCellType() == Cell.CELL_TYPE_STRING) {
            		wfQues.setUsrName(cell5.getStringCellValue());
            	}
			}
			
            if(position.get(WFQUES_EXCEL_FIELD_COMPLETEDDATE)!=null){
            	Cell cell6 = row.getCell(position.get(WFQUES_EXCEL_FIELD_COMPLETEDDATE));
            	if(cell6 != null && cell6.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            		
            		if(HSSFDateUtil.isCellDateFormatted(cell6)) {
            			wfQues.setCompletedDate(cell6.getDateCellValue());
            		}
            	}
			}
			
            if(position.get(WFQUES_EXCEL_FIELD_RESULT)!=null){
            	Cell cell7 = row.getCell(position.get(WFQUES_EXCEL_FIELD_RESULT));
            	if(cell7 != null && cell7.getCellType() == Cell.CELL_TYPE_STRING) {
            		wfQues.setQuesMeasures(cell7.getStringCellValue());
            	}
			}
			
            if(position.get(WFQUES_EXCEL_FIELD_SCHEU)!=null){
            	Cell cell8 = row.getCell(position.get(WFQUES_EXCEL_FIELD_SCHEU));
            	if(cell8 != null && cell8.getCellType() == Cell.CELL_TYPE_STRING) {
            		String scheu = cell8.getStringCellValue();
            		Integer scheId = null;
//				提出阶段选项的内容增加一下：小批试产（T1）,小批试产（T1-1）,小批试产（T1-2）,小批试产（T2）,小批试产（T2-1）,小批试产（T2-2）,
//				中批试产(PP),中批试产（PP-1）,中批试产(PP-2),批量（MP）
            		if(scheu.equals("小批试产(T1)")){
            			scheId = 23;
            		}else if(scheu.equals("T1")){
            			scheId =23 ;
            		}else if(scheu.equals("小批试产(T1-1)")){
            			scheId =44 ;
            		}else if(scheu.equals("T1-1")){
            			scheId =44 ;
            		}else if(scheu.equals("小批试产(T1-2)")){
            			scheId = 45;
            		}else if(scheu.equals("T1-2")){
            			scheId = 45;
            		}else if(scheu.equals("小批试产(T2)")){
            			scheId = 28;
            		}else if(scheu.equals("T2")){
            			scheId = 28;
            		}else if(scheu.equals("小批试产(T2-1)")){
            			scheId = 46;
            		}else if(scheu.equals("T2-1")){
            			scheId = 46;
            		}else if(scheu.equals("小批试产(T2-2)")){
            			scheId = 47;
            		}else if(scheu.equals("T2-2")){
            			scheId = 47;
            		}else if(scheu.equals("中批试产(PP)")){
            			scheId = 48;
            		}else if(scheu.equals("PP")){
            			scheId = 48;
            		}else if(scheu.equals("中批")){
            			scheId = 48;
            		}else if(scheu.equals("中批试产(PP-1)")){
            			scheId = 49;
            		}else if(scheu.equals("PP-1")){
            			scheId = 49;
            		}else if(scheu.equals("中批试产(PP-2)")){
            			scheId = 50;
            		}else if(scheu.equals("PP-2")){
            			scheId = 50;
            		}else if(scheu.equals("批量(MP)")){
            			scheId = 51;
            		}else if(scheu.equals("量产")){
            			scheId = 51;
            		}else if(scheu.equals("MP")){
            			scheId = 51;
            		}else if(scheu.equals("小批试产(T3)")){
            			scheId = 52;
            		}else if(scheu.equals("T3")){
            			scheId = 52;
            		}
            		/*for(SchCfg e:schCfgs){
            			if(e.getSchNm().equals(scheu)){
            				scheId = e.getSchId();
            			}
            		}*/
            		wfQues.setScheId(scheId);
            	}
			}
			
            if(position.get(WFQUES_EXCEL_FIELD_QUESANALYSIS)!=null){
            	Cell cell9 = row.getCell(position.get(WFQUES_EXCEL_FIELD_QUESANALYSIS));
            	if(cell9 != null && cell9.getCellType() == Cell.CELL_TYPE_STRING) {
            		wfQues.setQuesAnalysis(cell9.getStringCellValue());
            	}
			}
			
            if(position.get(WFQUES_EXCEL_FIELD_SLOV_SOURCE)!=null){
            	Cell cell10 = row.getCell(position.get(WFQUES_EXCEL_FIELD_SLOV_SOURCE));
            	if(cell10 != null && cell10.getCellType() == Cell.CELL_TYPE_STRING) {
            		String source = cell10.getStringCellValue();
            		Integer sourceId = null;
            		if(source.equals("硬件测试")){
            			sourceId = 1;
            		}else if(source.equals("试产组装")){
            			sourceId = 2;
            		}else if(source.equals("试产贴片")){
            			sourceId = 3;
            		}else if(source.equals("白盒测试")){
            			sourceId = 4;
            		}else if(source.equals("整机测试")){
            			sourceId = 5;
            		}else if(source.equals("工厂测试")){
            			sourceId = 6;
            		}
            		wfQues.setSourceID(sourceId);
            	}
			}
			
			
            if(position.get(WFQUES_EXCEL_FIELD_SLOV_QUESTYPE)!=null){
            	Cell cell11 = row.getCell(position.get(WFQUES_EXCEL_FIELD_SLOV_QUESTYPE));
            	if(cell11 != null && cell11.getCellType() == Cell.CELL_TYPE_STRING) {
            		String quesType = cell11.getStringCellValue();
            		Integer quesTypeID = null;
            		if(quesType.equals("基带")){
            			quesTypeID = 1;
            		}else if(quesType.equals("射频")){
            			quesTypeID = 2;
            		}else if(quesType.equals("音频")){
            			quesTypeID = 3;
            		}else if(quesType.equals("结构")){
            			quesTypeID = 4;
            		}else if(quesType.equals("外观工艺")){
            			quesTypeID = 5;
            		}else if(quesType.equals("软件")){
            			quesTypeID = 6;
            		}else if(quesType.equals("贴片工艺")){
            			quesTypeID = 7;
            		}else if(quesType.equals("组装工艺")){
            			quesTypeID = 8;
            		}else if(quesType.equals("物料")){
            			quesTypeID = 9;
            		}else if(quesType.equals("附配件测试")){
            			quesTypeID = 10;
            		}else if(quesType.equals("硬件")){
            			quesTypeID = 11;
            		}
            		
            		wfQues.setQuesTypeID(quesTypeID);
            	}
			}
            
            
            if(position.get(WFQUES_EXCEL_FIELD_IDTFUSER)!=null){
            	Cell cell12 = row.getCell(position.get(WFQUES_EXCEL_FIELD_IDTFUSER));
            	if(cell12 != null && cell12.getCellType() == Cell.CELL_TYPE_STRING) {
            		wfQues.setIdtfName(cell12.getStringCellValue());
            	}
			}
            
            if(position.get(WFQUES_EXCEL_FIELD_FRACTION_DEFECTIVE)!=null){
            	Cell cell13 = row.getCell(position.get(WFQUES_EXCEL_FIELD_FRACTION_DEFECTIVE));
            	if(cell13 != null && cell13.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            		NumberFormat num = NumberFormat.getPercentInstance(); 
            		num.setMaximumIntegerDigits(3); 
            		num.setMaximumFractionDigits(2); 
            		String cellString = String.valueOf(num.format(cell13.getNumericCellValue()));
            		wfQues.setFractionDefective(cellString);
            	}else if (cell13 != null && cell13.getCellType() == Cell.CELL_TYPE_STRING) {
            		wfQues.setFractionDefective(cell13.getStringCellValue());
				}
			}
            
            
            if(position.get(WFQUES_EXCEL_FIELD_TESTITEM)!=null){
            	Cell cell14 = row.getCell(position.get(WFQUES_EXCEL_FIELD_TESTITEM));
            	if(cell14 != null && cell14.getCellType() == Cell.CELL_TYPE_STRING) {
            		String testItemString = cell14.getStringCellValue();
            		
            		String testItemID = checkTheTestItem(testItemString);
            		if (testItemID.length() != 0) {
            			wfQues.setTestItemName(testItemString);
					}
            		wfQues.setTestItemID(testItemID);
            	}
			}
            
        	
			if(picDatas != null && picDatas.size() > 0) {
				if(picPosition.get(i) != null) {
					wfQues.setPicture(picDatas.get(picPosition.get(i)).getData());
					wfQues.setPicXY(getPicXY(worbook, picPosition.get(i)));
				}
			}
			wfQuesList.add(wfQues);
		}
		return wfQuesList;
	}
	
	
	//测试项对应表
	private String checkTheTestItem(String testItemString) {
//		WfQuesTestItem wfQuesTestItem = new WfQuesTestItem();
//		wfQuesTestItem.setTestItemName(testItemString);
		
		if(testItemString.equals("可靠性测试")){
			return  "1";
		}else if(testItemString.equals("电性能")){
			return  "1.1";
		}else if(testItemString.equals("软件功能")){
			return  "1.2";
		}else if(testItemString.equals("用户试用")){
			return  "1.3";
		}else if(testItemString.equals("场地测试")){
			return  "1.4";
		}else if(testItemString.equals("EMC")){
			return  "1.5";
		}else if(testItemString.equals("环境测试")){
			return  "1.6";
		}else if(testItemString.equals("低温储存")){
			return  "1.6.1";
		}else if(testItemString.equals("低温工作")){
			return  "1.6.2";
		}else if(testItemString.equals("高温储存")){
			return  "1.6.3";
		}else if(testItemString.equals("高温高湿")){
			return  "1.6.4";
		}else if(testItemString.equals("温度冲击")){
			return  "1.6.5";
		}else if(testItemString.equals("湿热循环")){
			return  "1.6.6";
		}else if(testItemString.equals("防尘")){
			return  "1.6.7";
		}else if(testItemString.equals("盐雾")){
			return  "1.6.8";
		}else if(testItemString.equals("紫外线照射")){
			return  "1.6.9";
		}else if(testItemString.equals("铁屑")){
			return  "1.6.10";
		}else if(testItemString.equals("抽真空试验")){
			return  "1.6.11";
		}else if(testItemString.equals("机械强度")){
			return  "1.7";
		}else if(testItemString.equals("跌落")){
			return  "1.7.1";
		}else if(testItemString.equals("软压")){
			return  "1.7.2";
		}else if(testItemString.equals("静压")){
			return  "1.7.3";
		}else if(testItemString.equals("随机振动")){
			return  "1.7.4";
		}else if(testItemString.equals("冲击")){
			return  "1.7.5";
		}else if(testItemString.equals("滚筒跌落_0.5米")){
			return  "1.7.6";
		}else if(testItemString.equals("滚筒跌落_1米")){
			return  "1.7.7";
		}else if(testItemString.equals("扭力")){
			return  "1.7.8";
		}else if(testItemString.equals("微跌")){
			return  "1.7.9";
		}else if(testItemString.equals("按键拉拔力")){
			return  "1.7.10";
		}else if(testItemString.equals("胶塞拉力")){
			return  "1.7.11";
		}else if(testItemString.equals("焊盘拉力")){
			return  "1.7.12";
		}else if(testItemString.equals("数据线接口压力")){
			return  "1.7.13";
		}else if(testItemString.equals("按键区域静压试验")){
			return  "1.7.14";
		}else if(testItemString.equals("数据线吊重试验")){
			return  "1.7.15";
		}else if(testItemString.equals("寿命试验")){
			return  "1.8";
		}else if(testItemString.equals("翻盖寿命")){
			return  "1.8.1";
		}else if(testItemString.equals("滴水翻盖寿命")){
			return  "1.8.2";
		}else if(testItemString.equals("旋盖寿命")){
			return  "1.8.3";
		}else if(testItemString.equals("滑盖寿命")){
			return  "1.8.4";
		}else if(testItemString.equals("负重滑盖寿命")){
			return  "1.8.5";
		}else if(testItemString.equals("机械按键寿命")){
			return  "1.8.6";
		}else if(testItemString.equals("波动开关寿命")){
			return  "1.8.7";
		}else if(testItemString.equals("SIM卡插拔寿命")){
			return  "1.8.8";
		}else if(testItemString.equals("外置内存卡插拔")){
			return  "1.8.9";
		}else if(testItemString.equals("耳机插孔插拔")){
			return  "1.8.10";
		}else if(testItemString.equals("数据接口插拔")){
			return  "1.8.11";
		}else if(testItemString.equals("电池插拔")){
			return  "1.8.12";
		}else if(testItemString.equals("电池盖插拔")){
			return  "1.8.13";
		}else if(testItemString.equals("SPK寿命")){
			return  "1.8.14";
		}else if(testItemString.equals("马达振动寿命")){
			return  "1.8.15";
		}else if(testItemString.equals("USB直插头摇摆寿命")){
			return  "1.8.16";
		}else if(testItemString.equals("底壳滑动装饰件寿命")){
			return  "1.8.17";
		}else if(testItemString.equals("出点按压寿命")){
			return  "1.8.18";
		}else if(testItemString.equals("螺母扭力测试")){
			return  "1.8.19";
		}else if(testItemString.equals("螺钉防松测试")){
			return  "1.8.20";
		}else if(testItemString.equals("外观工艺")){
			return  "1.9";
		}else if(testItemString.equals("工艺耐磨")){
			return  "1.9.1";
		}else if(testItemString.equals("工艺硬度")){
			return  "1.9.2";
		}else if(testItemString.equals("镜面/镜片硬度")){
			return  "1.9.3";
		}else if(testItemString.equals("附着力试验")){
			return  "1.9.4";
		}else if(testItemString.equals("人工汗液")){
			return  "1.9.5";
		}else if(testItemString.equals("丝印耐酒精试验")){
			return  "1.9.6";
		}else if(testItemString.equals("振动耐磨")){
			return  "1.9.7";
		}else if(testItemString.equals("水煮试验")){
			return  "1.9.8";
		}else if(testItemString.equals("钢丝绒摩擦")){
			return  "1.9.9";
		}else if(testItemString.equals("主标贴丝印试验")){
			return  "1.9.10";
		}else if(testItemString.equals("弯折试验")){
			return  "1.9.11";
		}else if(testItemString.equals("耐化妆品染色试验")){
			return  "1.9.12";
		}else if(testItemString.equals("摄像头镜片透过率")){
			return  "1.9.13";
		}else if(testItemString.equals("摄像头镜片水滴角度")){
			return  "1.9.14";
		}else if(testItemString.equals("摄像头镜片钢丝绒水滴角度")){
			return  "1.9.15";
		}else if(testItemString.equals("摄像头镜片橡皮摩擦水滴角度")){
			return  "1.9.16";
		}else if(testItemString.equals("温升")){
			return  "1.10";
		}else if(testItemString.equals("通话状态温升")){
			return  "1.10.1";
		}else if(testItemString.equals("摄像状态温升")){
			return  "1.10.2";
		}else if(testItemString.equals("游戏状态温升")){
			return  "1.10.3";
		}else if(testItemString.equals("播放3D动画温升")){
			return  "1.10.4";
		}else if(testItemString.equals("工作电流")){
			return  "1.11";
		}else if(testItemString.equals("待机电流")){
			return  "1.11.1";
		}else if(testItemString.equals("飞航模式")){
			return  "1.11.2";
		}else if(testItemString.equals("通话电流")){
			return  "1.11.3";
		}else if(testItemString.equals("LCD显示电流")){
			return  "1.11.4";
		}else if(testItemString.equals("播放MP3电流")){
			return  "1.11.5";
		}else if(testItemString.equals("FM电流")){
			return  "1.11.6";
		}else if(testItemString.equals("视频电流")){
			return  "1.11.7";
		}else if(testItemString.equals("数据传输电流")){
			return  "1.11.8";
		}else if(testItemString.equals("摄像电流")){
			return  "1.11.9";
		}else if(testItemString.equals("游戏电流")){
			return  "1.11.10";
		}else if(testItemString.equals("关机漏电流")){
			return  "1.11.11";
		}else if(testItemString.equals("充电及电量识别精度测试")){
			return  "1.12";
		}else if(testItemString.equals("充电电压检测精度")){
			return  "1.12.1";
		}else if(testItemString.equals("充电波形检测")){
			return  "1.12.2";
		}else if(testItemString.equals("充电功能试验")){
			return  "1.12.3";
		}else if(testItemString.equals("低温充电电量测试")){
			return  "1.12.4";
		}else if(testItemString.equals("高温充电电量测试")){
			return  "1.12.5";
		}else if(testItemString.equals("手机连接接口电气性能测试")){
			return  "1.12.6";
		}else if(testItemString.equals("高温电池安全性测试")){
			return  "1.12.7";
		}else if(testItemString.equals("开机电量识别精准度测试")){
			return  "1.12.8";
		}else if(testItemString.equals("关机充电电量测试")){
			return  "1.12.9";
		}else if(testItemString.equals("整机功能性能")){
			return  "1.13";
		}else if(testItemString.equals("音频")){
			return  "1.14";
		}else if(testItemString.equals("显示拍照效果")){
			return  "1.15";
		}else if(testItemString.equals("天线暗室")){
			return  "1.16";
		}else if(testItemString.equals("防水测试")){
			return  "1.17";
		}else if(testItemString.equals("附配件")){
			return  "1.18";
		}else if(testItemString.equals("白盒测试")){
			return  "2";
		}else if(testItemString.equals("硬件测试")){
			return  "3";
		}else {
			return null;
		}
		
	}


	public static int getCate(String cate) {
		int iCate = 2;
		if (StringUtils.isNotBlank(cate)) {
			if (cate.indexOf("致命_S") > -1) {
				iCate = 1;
			} else if (cate.indexOf("严重_A") > -1) {
				iCate = 2;
			} else if (cate.indexOf("重_B") > -1) {
				iCate = 3;
			} else if (cate.indexOf("轻_C") > -1) {
				iCate = 4;
			}else if (cate.indexOf("S") > -1) {
				iCate = 1;
			}else if (cate.indexOf("A") > -1) {
				iCate = 2;
			}else if (cate.indexOf("B") > -1) {
				iCate = 3;
			}else if (cate.indexOf("C") > -1) {
				iCate = 4;
			}
		}
		return iCate;
	}
	/**
	 * 获取Excel中问题的所有字段的位置
	 * @param worbook
	 * @return
	 */
	private Map<String, Integer> getPosition(Workbook worbook) {
		Map<String,Integer> positionMap = new HashMap<String, Integer>();
		Sheet sheet = worbook.getSheetAt(0);
		Row row = sheet.getRow(0);
		
//		public static final String[] WFQUES_EXCEL_FIELD = {"问题编号","最终状态","问题等级","提出阶段","问题描述","图片","原因分析","改善措施","责任部门","责任人","提交时间",
//			"解决时长","问题类别","问题来源","解决状态","验证人","验证日期","项目名称"};
		
		
		
//		public static final String WFQUES_EXCEL_FIELD_ID = "问题编号";
//		public static final String WFQUES_EXCEL_FIELD_FINAL_STATUS = "最终状态";
//		public static final String WFQUES_EXCEL_FIELD_CATE = "问题等级";
//		public static final String WFQUES_EXCEL_FIELD_SCHEU = "提出阶段";
//		public static final String WFQUES_EXCEL_FIELD_QUESTEXT = "问题描述";
//		public static final String WFQUES_EXCEL_FIELD_PIC = "图片";
//		public static final String WFQUES_EXCEL_FIELD_QUESANALYSIS= "原因分析";
//		public static final String WFQUES_EXCEL_FIELD_RESULT = "改善措施";
//		public static final String WFQUES_EXCEL_FIELD_DEPTNM = "责任部门";
//		public static final String WFQUES_EXCEL_FIELD_USER = "责任人";
//		public static final String WFQUES_EXCEL_FIELD_CREATEDATE = "提交时间";
//		public static final String WFQUES_EXCEL_FIELD_SLOV_TIME = "解决时长";
//		public static final String WFQUES_EXCEL_FIELD_SLOV_QUESTYPE= "问题类别";
//		public static final String WFQUES_EXCEL_FIELD_SLOV_SOURCE= "问题来源";
//		public static final String WFQUES_EXCEL_FIELD_STATUS = "解决状态";
//		public static final String WFQUES_EXCEL_FIELD_IDTFUSER = "验证人";
//		public static final String WFQUES_EXCEL_FIELD_IDTFDATE = "验证日期";
//		public static final String WFQUES_EXCEL_FIELD_PRJTNM = "项目名称";
		
		if(row!= null) {
			for(int i=0;i<row.getPhysicalNumberOfCells();i++) {
				Cell cell = row.getCell(i);
				if(cell != null) {
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						if(cell.getStringCellValue().contains(WFQUES_EXCEL_FIELD_CATE)) {
							positionMap.put(WFQUES_EXCEL_FIELD_CATE, i);
						}else if(cell.getStringCellValue().contains(WFQUES_EXCEL_FIELD_COMPLETEDDATE)) {
							positionMap.put(WFQUES_EXCEL_FIELD_COMPLETEDDATE, i);
						}else if(cell.getStringCellValue().contains(WFQUES_EXCEL_FIELD_ID)) {
							positionMap.put(WFQUES_EXCEL_FIELD_ID, i);
						}else if(cell.getStringCellValue().contains(WFQUES_EXCEL_FIELD_IDTFDATE)) {
							positionMap.put(WFQUES_EXCEL_FIELD_IDTFDATE, i);
						}else if(cell.getStringCellValue().contains(WFQUES_EXCEL_FIELD_IDTFUSER)) {
							positionMap.put(WFQUES_EXCEL_FIELD_IDTFUSER, i);
						}else if(cell.getStringCellValue().contains(WFQUES_EXCEL_FIELD_PRJTNM)) {
							positionMap.put(WFQUES_EXCEL_FIELD_PRJTNM, i);
						}else if(cell.getStringCellValue().contains(WFQUES_EXCEL_FIELD_QUESTEXT)) {
							positionMap.put(WFQUES_EXCEL_FIELD_QUESTEXT, i);
						}else if(cell.getStringCellValue().contains(WFQUES_EXCEL_FIELD_RESULT)) {
							positionMap.put(WFQUES_EXCEL_FIELD_RESULT, i);
						}else if(cell.getStringCellValue().contains(WFQUES_EXCEL_FIELD_STATUS)) {
							positionMap.put(WFQUES_EXCEL_FIELD_STATUS, i);
						}else if(cell.getStringCellValue().contains(WFQUES_EXCEL_FIELD_TITLE)) {
							positionMap.put(WFQUES_EXCEL_FIELD_TITLE, i);
						}else if(cell.getStringCellValue().contains(WFQUES_EXCEL_FIELD_USER)) {
							positionMap.put(WFQUES_EXCEL_FIELD_USER, i);
						}else if(cell.getStringCellValue().contains(WFQUES_EXCEL_FIELD_WFNAME)) {
							positionMap.put(WFQUES_EXCEL_FIELD_WFNAME, i);
						}else if(cell.getStringCellValue().contains(WFQUES_EXCEL_FIELD_PIC)) {
							positionMap.put(WFQUES_EXCEL_FIELD_PIC, i);
						}else if(cell.getStringCellValue().contains(WFQUES_EXCEL_FIELD_SCHEU)) {
							positionMap.put(WFQUES_EXCEL_FIELD_SCHEU, i);
						}else if(cell.getStringCellValue().contains(WFQUES_EXCEL_FIELD_QUESANALYSIS)) {
							positionMap.put(WFQUES_EXCEL_FIELD_QUESANALYSIS, i);
						}else if(cell.getStringCellValue().contains(WFQUES_EXCEL_FIELD_SLOV_QUESTYPE)) {
							positionMap.put(WFQUES_EXCEL_FIELD_SLOV_QUESTYPE, i);
						}else if(cell.getStringCellValue().contains(WFQUES_EXCEL_FIELD_SLOV_SOURCE)) {
							positionMap.put(WFQUES_EXCEL_FIELD_SLOV_SOURCE, i);
						}else if(cell.getStringCellValue().contains(WFQUES_EXCEL_FIELD_FRACTION_DEFECTIVE)) {
							positionMap.put(WFQUES_EXCEL_FIELD_FRACTION_DEFECTIVE, i);
						}else if(cell.getStringCellValue().contains(WFQUES_EXCEL_FIELD_IDTFUSER)) {
							positionMap.put(WFQUES_EXCEL_FIELD_IDTFUSER, i);
						}else if(cell.getStringCellValue().contains(WFQUES_EXCEL_FIELD_TESTITEM)) {
							positionMap.put(WFQUES_EXCEL_FIELD_TESTITEM, i);
						}
						break;
					}
				}
			}
		}
		return positionMap;
	}
	
	/**
	 * 获取图片所在的位置（只支持2003）
	 * @param worbook
	 * @return
	 * @throws IOException
	 */
	private Map<Integer, Integer> getPicPosition(Workbook worbook){
		Map<Integer, Integer> picPosition = new HashMap<Integer, Integer>();
		if(worbook instanceof HSSFWorkbook){
			HSSFWorkbook hWorbook = (HSSFWorkbook)worbook;
			HSSFSheet sheet = hWorbook.getSheetAt(0);
			HSSFPatriarch hssfPatriarch = sheet.getDrawingPatriarch();
			if(hssfPatriarch != null) {
				for (HSSFShape shape : hssfPatriarch.getChildren()) {
				    HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();
				    if (shape instanceof HSSFPicture) {
					     HSSFPicture picture = (HSSFPicture) shape;
					     int pictureIndex = picture.getPictureIndex() - 1;
					     picPosition.put(anchor.getRow1(), pictureIndex);
				    }
				}
			}
		}
		return picPosition;
	}
	/**
	 * 获取指定位置的图片的XY坐标（只支持2003）
	 * @param worbook
	 * @param row
	 * @return json 字符串
	 */ 
	private String getPicXY(Workbook worbook,int picIndex) {
		String result = null;
		if(worbook instanceof HSSFWorkbook){
			HSSFWorkbook hWorbook = (HSSFWorkbook)worbook;
			HSSFSheet sheet = hWorbook.getSheetAt(0);
			HSSFPatriarch hssfPatriarch = sheet.getDrawingPatriarch();
			if(hssfPatriarch != null) {
				for (HSSFShape shape : hssfPatriarch.getChildren()) {
				    HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();
				    if (shape instanceof HSSFPicture) {
					     HSSFPicture picture = (HSSFPicture) shape;
					     int pictureIndex = picture.getPictureIndex() - 1;
					     if(pictureIndex == picIndex) {
					    	 PicXY picXY = new PicXY(anchor.getDx1(), anchor.getDy1(), anchor.getDx2(), anchor.getDy2());
					    	 result = JSONObject.fromObject(picXY).toString();
					     }
				    }
				}
			}
		}
		return result;
	}
	
	@Override
	public Workbook create2003(List<WfQues> wfQuesList) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		product(workbook,wfQuesList);
		return workbook;
	}
	
	@Override
	public Workbook create2007(List<WfQues> entityList) throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();
		product(workbook,entityList);
		return workbook;
	}
	
	/**
	 * 创建样式
	 * @param workbook
	 * @param locked 是否需要锁定
	 * @return
	 */
	private CellStyle createCellStyle(Workbook workbook,boolean locked) {
		 CellStyle cs = workbook.createCellStyle();
		 cs.setBorderBottom(CellStyle.BORDER_THIN); //下边框
		 cs.setBorderLeft(CellStyle.BORDER_THIN);//左边框
		 cs.setBorderTop(CellStyle.BORDER_THIN);//上边框
		 cs.setBorderRight(CellStyle.BORDER_THIN);//右边框
		 cs.setAlignment(CellStyle.ALIGN_CENTER);
		 cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		 cs.setLocked(locked);
		 cs.setWrapText(true);
		 return cs;
	}
	/**
	 * 创建Excel
	 * @param workbook
	 * @param wfQuesList
	 */
	 /**
	 * @param workbook
	 * @param wfQuesList
	 * @throws Exception 
	 */
	private void product(Workbook workbook,List<WfQues> wfQuesList) throws Exception {
		 Sheet sheet = workbook.createSheet();
		 workbook.setSheetName(0, "问题列表");
		 Row row = sheet.createRow(0);
		 row.setHeightInPoints(ROW_TITLE_HEIGTH);
		 //字体
		 Font fontTile = workbook.createFont();
		 fontTile.setBoldweight(Font.BOLDWEIGHT_BOLD);
		 //样式
		 CellStyle csTitleLocked = createCellStyle(workbook, true);
		 csTitleLocked.setFont(fontTile);
		 
		 //创建表头
		 for(int i=0;i<WFQUES_EXCEL_FIELD.length;i++) {
			 Cell cell = row.createCell(i);
			 cell.setCellType(Cell.CELL_TYPE_STRING);
			 if(WFQUES_EXCEL_FIELD_RESULT.equals(WFQUES_EXCEL_FIELD[i])){
				 Font fontTitleExcep = workbook.createFont();
				 fontTitleExcep.setBoldweight(Font.BOLDWEIGHT_BOLD);
				 fontTitleExcep.setColor(HSSFColor.RED.index);
				 CellStyle csTitleLockedExcep = createCellStyle(workbook, true);
				 csTitleLockedExcep.setFont(fontTitleExcep);
				 cell.setCellStyle(csTitleLockedExcep);
				 cell.setCellValue("*" + WFQUES_EXCEL_FIELD[i]);
			 }else {
				 cell.setCellStyle(csTitleLocked);
				 cell.setCellValue(WFQUES_EXCEL_FIELD[i]);
			 }
			 if(WFQUES_EXCEL_FIELD_PIC.equals(WFQUES_EXCEL_FIELD[i])){
				 sheet.setColumnWidth(i, CELL_TITLE2_WIGTH);
			 }else {
				 sheet.setColumnWidth(i, CELL_TITLE1_WIGTH);
			 }
		 }
		 
		 if(wfQuesList != null && wfQuesList.size() > 0) {
			 QuesRespFacade qrf = new QuesRespFacade();
			 String QueryQuesRespSql = "select QuesResp.Result,QuesResp.IdtfDate,QuesResp.QuesAnalysis,QuesResp.IdtfRes from QuesResp " ;
			 //样式
			 CellStyle csContentLocked = createCellStyle(workbook, true);
			 CellStyle csContentNotLocked = createCellStyle(workbook, false);
			 List<SchCfg> schCfgs = null;
				try {
					schCfgs = new SchCfgFacade().find("select " + SchCfg.SELF_FIELDS + " from SchCfg where SchCfg.SchNm in ("+MSG.WfQues_SchCfg+")", SchCfg.SELF_FIELDS);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 for(int i=0;i<wfQuesList.size();i++) {
				 Row contentRow = sheet.createRow((i + 1));
				 contentRow.setHeightInPoints(ROW_CONTENT_HEIGTH);
				 for(int j=0;j<WFQUES_EXCEL_FIELD.length;j++) {
					 Cell cell = contentRow.createCell(j);
					 cell.setCellStyle(csContentLocked);
					 
					 if(WFQUES_EXCEL_FIELD_PIC.equals(WFQUES_EXCEL_FIELD[j])){
						  	
						 if(wfQuesList.get(i).getFileNo() != null && wfQuesList.get(i).getFileNo().contains("\\") ) {
							 String endstrJPG = ".jpg";
							    boolean retval1 = wfQuesList.get(i).getFileName().endsWith(endstrJPG);
							    if (retval1) {
									
								
							 if(workbook instanceof HSSFWorkbook) {
								ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
								String relativePath = new File(wfQuesList.get(i).getFileNo()).getAbsolutePath();//相对路径
								System.out.println("relativePath"+relativePath);
								BufferedImage bufferImg = ImageIO.read(new File(relativePath));
								ImageIO.write(bufferImg,"jpg",byteArrayOut);
								HSSFPatriarch patriarch = (HSSFPatriarch)sheet.createDrawingPatriarch();
								HSSFClientAnchor anchor = null;
								if(wfQuesList.get(i).getPicXY() != null){
									JSONObject jsonObject = JSONObject.fromObject(wfQuesList.get(i).getPicXY()); 
									PicXY picXY = (PicXY) JSONObject.toBean(jsonObject, PicXY.class);
									anchor = new HSSFClientAnchor(picXY.getDx1(), picXY.getDy1(), picXY.getDx2(), picXY.getDy2(), (short)j,
											(short)(i+1), (short)j, (short)(i + 1));
								}else {
									anchor = new HSSFClientAnchor(20, 20, 1000, 250, (short)j, (short)(i+1), (short)j, (short)(i + 1));
								}
								anchor.setAnchorType(2);
								patriarch.createPicture(anchor,workbook.addPicture(byteArrayOut.toByteArray(),HSSFWorkbook.PICTURE_TYPE_JPEG));
							 }
							    }
						 }
						 cell.setCellStyle(csContentNotLocked);
						 sheet.setColumnWidth(j, CELL_TITLE2_WIGTH);
					 }else {
						 cell.setCellType(Cell.CELL_TYPE_STRING);
						 if(WFQUES_EXCEL_FIELD[j].equals(WFQUES_EXCEL_FIELD_ID)) {
							 cell.setCellValue(wfQuesList.get(i).getQuesId());
						 } 
//						 else if(WFQUES_EXCEL_FIELD[j].equals(WFQUES_EXCEL_FIELD_TITLE)) {
//							 cell.setCellValue(wfQuesList.get(i).getTitle());
//						 }
						 else if(WFQUES_EXCEL_FIELD[j].equals(WFQUES_EXCEL_FIELD_QUESTEXT)) {
							 cell.setCellValue(wfQuesList.get(i).getQuesText());
						 }else if(WFQUES_EXCEL_FIELD[j].equals(WFQUES_EXCEL_FIELD_CATE)) {
							 if(wfQuesList.get(i).getCateId() != null) {
								 String value = "";
									 /*if (wfQuesList.get(i).getCateId() == 1) {
										value = "致命_S";
									} else if (wfQuesList.get(i).getCateId() == 2) {
										value = "严重_A";
									} else if (wfQuesList.get(i).getCateId() == 3) {
										value = "重_B";
									}else if (wfQuesList.get(i).getCateId() == 4) {
										value = "轻_C";
									}*/
								 	if (wfQuesList.get(i).getCateId() == 1) {
										value = "S";
									} else if (wfQuesList.get(i).getCateId() == 2) {
										value = "A";
									} else if (wfQuesList.get(i).getCateId() == 3) {
										value = "B";
									}else if (wfQuesList.get(i).getCateId() == 4) {
										value = "C";
									}
								cell.setCellValue(value);
							 }
						 }else if(WFQUES_EXCEL_FIELD[j].equals(WFQUES_EXCEL_FIELD_SCHEU)) {
							   String cellValue = "";
							   int scheId = wfQuesList.get(i).getScheId();
							    if(scheId == 23){
							    	cellValue="小批试产(T1)";
								}else if(scheId == 44){
									cellValue="小批试产(T1-1)";
								}else if(scheId == 45){
									cellValue="小批试产(T1-2)";
								}else if(scheId == 28){
									cellValue="小批试产(T2)";
								}else if(scheId == 46 ){
									cellValue="小批试产(T2-1)";
								}else if(scheId == 47){
									cellValue="小批试产(T2-2)";
								}else if(scheId == 48 ){
									cellValue="中批试产(PP)";
								}else if(scheId == 49){
									cellValue="中批试产(PP-1)";
								}else if(scheId == 50){
									cellValue="中批试产(PP-2)";
								}else if(scheId == 51){
									cellValue="批量(MP)";
								}else if(scheId == 52){
									cellValue="小批试产(T3)";
								}
							    
							  /* for(SchCfg e:schCfgs){
								   if(e.getSchId()==scheId){
									   cellValue = e.getSchNm();
								   }
							   }*/
							 cell.setCellValue(cellValue);
						 }else if(WFQUES_EXCEL_FIELD[j].equals(WFQUES_EXCEL_FIELD_STATUS)) {
							 if (wfQuesList.get(i).getStatus() != null) {
								int status = wfQuesList.get(i).getStatus();
								String sta_str = "";
								if (status == 21) {
									sta_str = "已挂起";
								} else if (status == 40) {
									sta_str = "转风险";
								} else if (status == 30) {
									sta_str = "已关闭";
								}else if(status == 1){
									sta_str = "待解决";
								}else if(status == 9){
									sta_str = "验证未通过";
								}else if(status == 10){
									sta_str = "待验证";
								}else if(status == 11){
									sta_str = "验证通过";
								}
								cell.setCellValue(sta_str);	
							 }
						 }else if(WFQUES_EXCEL_FIELD[j].equals(WFQUES_EXCEL_FIELD_FINAL_STATUS)){
							 if (wfQuesList.get(i).getStatus() != null) {
									int status = wfQuesList.get(i).getStatus();
									String sta_str = "";
									if (status == 21) {
										sta_str = "Open";
									} else if (status == 40) {
										sta_str = "Risk";
									} else if (status == 30) {
										sta_str = "Close";
									}else if(status == 1){
										sta_str = "Open";
									}else if(status == 9){
										sta_str = "Open";
									}else if(status == 10){
										sta_str = "Open";
									}else if(status == 11){
										sta_str = "Open";
									}
									cell.setCellValue(sta_str);	
								 }
						 }
						 else if(WFQUES_EXCEL_FIELD[j].equals(WFQUES_EXCEL_FIELD_USER)){
							 if(wfQuesList.get(i).getUserId() == null || wfQuesList.get(i).getUserId() == 0) {
								 cell.setCellValue(wfQuesList.get(i).getUsrName());
								}else {
									Usr usr = new zrsy.facade.UsrFacade().findById("select Usr.UsrName from Usr where id = " + wfQuesList.get(i).getUserId(), "Usr.UsrName");
									if (usr != null) cell.setCellValue(usr.getUsrName());
								}
						 }else if(WFQUES_EXCEL_FIELD[j].equals(WFQUES_EXCEL_FIELD_COMPLETEDDATE)) {
							 if(wfQuesList.get(i).getCompletedDate() != null)cell.setCellValue(DateUtil.format(wfQuesList.get(i).getCompletedDate(),"yyyy-MM-dd"));
						 }else if(WFQUES_EXCEL_FIELD[j].equals(WFQUES_EXCEL_FIELD_IDTFUSER)) {
							 cell.setCellValue(wfQuesList.get(i).getCreateUsr());
						 }else if(WFQUES_EXCEL_FIELD[j].equals(WFQUES_EXCEL_FIELD_PRJTNM)) {
							 cell.setCellValue(wfQuesList.get(i).getPrjtNm());
						 }else if(WFQUES_EXCEL_FIELD[j].equals(WFQUES_EXCEL_FIELD_DEPTNM)) {
							 cell.setCellValue(wfQuesList.get(i).getDeptNm());
						 }
//						 else if(WFQUES_EXCEL_FIELD[j].equals(WFQUES_EXCEL_FIELD_WFNAME)) {
//							 cell.setCellValue(wfQuesList.get(i).getWfName());
//						 }
						 else if(WFQUES_EXCEL_FIELD[j].equals(WFQUES_EXCEL_FIELD_SLOV_SOURCE)) {
							 String source = "";
							 if (wfQuesList.get(i).getSourceID()!= null) {
								 
									int sourceId  = wfQuesList.get(i).getSourceID();
									if(sourceId == 1){
										source="硬件测试";
									}else if(sourceId == 2 ){
										source="试产组装";
									}else if(sourceId == 3 ){
										source="试产贴片";
									}else if(sourceId == 4 ){
										source="白盒测试";
									}else if(sourceId == 5){
										source="整机测试";
									}else if(sourceId == 6 ){
										source="工厂测试";
									}
									 cell.setCellValue(source);
								 }
							 
							 
							   
							
						 }else if(WFQUES_EXCEL_FIELD[j].equals(WFQUES_EXCEL_FIELD_SLOV_QUESTYPE)) {
							     String quesType = "";
							     int quesTypeID=1;
							     if (wfQuesList.get(i).getQuesTypeID() != null) {
							    	  quesTypeID = wfQuesList.get(i).getQuesTypeID();
								}
								if(quesTypeID ==1){
									quesType = "基带";
								}else if(quesTypeID ==2){
									quesType = "射频";
								}else if(quesTypeID ==3){
									quesType = "音频";
								}else if(quesTypeID ==4){
									quesType = "结构";
								}else if(quesTypeID ==5){
									quesType = "外观工艺";
								}else if(quesTypeID ==6){
									quesType = "软件";
								}else if(quesTypeID ==7){
									quesType = "贴片工艺";
								}else if(quesTypeID ==8){
									quesType = "组装工艺";
								}else if(quesTypeID ==9){
									quesType = "物料";
								}else if(quesTypeID ==10){
									quesType = "附配件测试";
								}else if(quesTypeID ==11){
									quesType = "硬件";
								}
							 cell.setCellValue(quesType);
						 }else if(WFQUES_EXCEL_FIELD[j].equals(WFQUES_EXCEL_FIELD_SLOV_TIME)) {
							 cell.setCellValue(wfQuesList.get(i).getDatetimeforques());
						 }else if(WFQUES_EXCEL_FIELD[j].equals(WFQUES_EXCEL_FIELD_CREATEDATE)) {
							 cell.setCellValue(DateUtil.format(wfQuesList.get(i).getCreateDate(),"yyyy-MM-dd"));
						 }else if(WFQUES_EXCEL_FIELD[j].equals(WFQUES_EXCEL_FIELD_FRACTION_DEFECTIVE)) {
							 cell.setCellValue(wfQuesList.get(i).getFractionDefective());
						 }
						 else if(WFQUES_EXCEL_FIELD[j].equals(WFQUES_EXCEL_FIELD_TESTITEM)) {
							 cell.setCellValue(wfQuesList.get(i).getTestItemName());
						 }
						 else if(WFQUES_EXCEL_FIELD[j].equals(WFQUES_EXCEL_FIELD_IDTFUSER)) {
							 cell.setCellValue(wfQuesList.get(i).getIdtfUsr());
						 }
						 else if(WFQUES_EXCEL_FIELD[j].equals(WFQUES_EXCEL_FIELD_SERIAL)) {
							 cell.setCellValue(i+1);
						 }
						 else {
							List<QuesResp> quesRespList = qrf.find(QueryQuesRespSql + "where quesresp.quesid = '" + 
									 wfQuesList.get(i).getQuesId() + "' and quesresp.usrid = " + wfQuesList.get(i).getUserId()+" order by LastUpdDate desc", "QuesResp.Result,QuesResp.IdtfDate,QuesResp.QuesAnalysis,QuesResp.IdtfRes");
							if(WFQUES_EXCEL_FIELD[j].equals(WFQUES_EXCEL_FIELD_RESULT)) {
								 cell.setCellStyle(csContentNotLocked);
								 if(quesRespList != null && quesRespList.size() > 0 && quesRespList.get(0).getResult() != null){
									 cell.setCellValue(quesRespList.get(0).getResult());
								 }else {
									 cell.setCellValue(wfQuesList.get(0).getQuesMeasures());
								 }
							 }else if(WFQUES_EXCEL_FIELD[j].equals(WFQUES_EXCEL_FIELD_IDTFDATE)) {
								 cell.setCellStyle(csContentNotLocked);
								 if (quesRespList != null && quesRespList.size() > 0 && quesRespList.get(0).getIdtfDate() != null) {
									 cell.setCellValue(DateUtil.format(quesRespList.get(0).getIdtfDate(),"yyyy-MM-dd"));
								 }else {
									 if(wfQuesList.get(0).getIdtfDate() != null)cell.setCellValue(DateUtil.format(wfQuesList.get(0).getIdtfDate(),"yyyy-MM-dd"));
								 }
							 }else if(WFQUES_EXCEL_FIELD[j].equals(WFQUES_EXCEL_FIELD_QUESANALYSIS)) {
								 if(quesRespList != null && quesRespList.size() > 0 && quesRespList.get(0).getQuesAnalysis() != null){
									 cell.setCellValue(quesRespList.get(0).getQuesAnalysis());
								 }
							 }else if(WFQUES_EXCEL_FIELD[j].equals(WFQUES_EXCEL_FIELD_IDTFRES)) {
								 if(quesRespList != null && quesRespList.size() > 0 && quesRespList.get(0).getIdtfRes() != null){
									 cell.setCellValue(quesRespList.get(0).getIdtfRes());
								 }
							 }
						 }
						 
						 
						 sheet.setColumnWidth(j, CELL_TITLE1_WIGTH);
					 }
				 }
			 }
		 }
	 }
	
	/*public static void main(String[] args) throws Exception {
		WfQuesExcelParser wep = new WfQuesExcelParser();
		InputStream is = new FileInputStream(new File("e:/question_template.xls"));
		//OutputStream os = new FileOutputStream(new File("e:/question_template2003_1.xls"));
		//Workbook workbook = wep.create2003(null);
		//workbook.write(os);
		System.out.println(wep.parser2003(is));
		//wep.read(new HSSFWorkbook(is));
	}*/
}
