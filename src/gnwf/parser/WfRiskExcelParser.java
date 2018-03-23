package gnwf.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Utils.DateUtil;
import gnwf.vo.WfRisk;

public class WfRiskExcelParser implements ExcelParser<WfRisk> {
	

	public static final String WFRISK_EXCEL_FIELD_DEPTNAME = "提出部门";
	public static final String WFRISK_EXCEL_FIELD_CATEGORY = "风险类别";
	public static final String WFRISK_EXCEL_FIELD_RISKCONSEQUENCE = "风险说明及风险后果";
	public static final String WFRISK_EXCEL_FIELD_PREVENTIVEMEASURES = "拟采取的预防措施";
	public static final String WFRISK_EXCEL_FIELD_IMPTIME = "拟导入时间";
	public static final String WFRISK_EXCEL_FIELD_USERNAME = "责任人";
	
	/*public static final String WFRISK_EXCEL_FIELD_ID = "风险编号";*/
	/*	public static final String WFRISK_EXCEL_FIELD_STATUS = "风险状态";
	public static final String WFRISK_EXCEL_FIELD_DESCRIPTION = "问题描述";*/
/*	public static final String WFRISK_EXCEL_FIELD_RISKMONITOR = "风险监控结果";
	public static final String WFRISK_EXCEL_FIELD_EXECUTIONDATE = "确认时间";*/
	
	public static final int WFRISK_EXCEL_FIELD_COUNT = 6;
	@Override
	public List<WfRisk> parser2003(InputStream is) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook(is);
		return read(workbook);
	}

	@Override
	public List<WfRisk> parser2007(InputStream is) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(is);
		return read(workbook);
	}
	
	
	private List<WfRisk> read(Workbook worbook) {
		List<WfRisk> wfRiskList = new ArrayList<WfRisk>();
		Map<String,Integer> position = getPosition(worbook);
		if(position.size() != WFRISK_EXCEL_FIELD_COUNT) {
			return null;
		}
		Sheet sheet = worbook.getSheetAt(0);
		//System.out.println(sheet.getPhysicalNumberOfRows());
		for(int i=1;i<sheet.getPhysicalNumberOfRows();i++) {
			WfRisk wfRisk = new WfRisk();
			Row row = sheet.getRow(i);
			Cell cell1 = row.getCell(position.get(WFRISK_EXCEL_FIELD_DEPTNAME));
			if(cell1 != null && cell1.getCellType() == Cell.CELL_TYPE_STRING) {
				wfRisk.setDeptName(cell1.getStringCellValue());
			}
			Cell cell2 = row.getCell(position.get(WFRISK_EXCEL_FIELD_CATEGORY));
			if(cell2 != null && cell2.getCellType() == Cell.CELL_TYPE_STRING) {
				wfRisk.setRiskCategory(cell2.getStringCellValue());
			}
		
			Cell cell3 = row.getCell(position.get(WFRISK_EXCEL_FIELD_RISKCONSEQUENCE));
			if(cell3 != null && cell3.getCellType() == Cell.CELL_TYPE_STRING) {
				wfRisk.setRiskConsequence(cell3.getStringCellValue());
			}
			Cell cell4 = row.getCell(position.get(WFRISK_EXCEL_FIELD_PREVENTIVEMEASURES));
			if(cell4 != null && cell4.getCellType() == Cell.CELL_TYPE_STRING) {
				wfRisk.setPreventiveMeasures(cell4.getStringCellValue());
			}
			Cell cell5 = row.getCell(position.get(WFRISK_EXCEL_FIELD_IMPTIME));
			if(cell5 != null && cell5.getCellType() == Cell.CELL_TYPE_STRING) {
				wfRisk.setImpTime(cell5.getStringCellValue());
			}else if(cell5 != null && cell5.getCellType() == Cell.CELL_TYPE_NUMERIC){
				if(HSSFDateUtil.isCellDateFormatted(cell5)) {
					wfRisk.setImpTime(DateUtil.format(cell5.getDateCellValue(), "yyyy-MM-dd"));
				}
			}
			Cell cell6 = row.getCell(position.get(WFRISK_EXCEL_FIELD_USERNAME));
			if(cell6 != null && cell6.getCellType() == Cell.CELL_TYPE_STRING) {
				wfRisk.setResponsibleUserName(cell6.getStringCellValue());
			}
			wfRiskList.add(wfRisk);
		}
		return wfRiskList;
	}

	
	/*Cell cell1 = row.getCell(position.get(WFRISK_EXCEL_FIELD_ID));
	if(cell1 != null && cell1.getCellType() == Cell.CELL_TYPE_NUMERIC) {
		wfRisk.setRiskId(String.valueOf(cell1.getNumericCellValue()));
	}else if(cell1 != null && cell1.getCellType() == Cell.CELL_TYPE_STRING){
		wfRisk.setRiskId(cell1.getStringCellValue());
	}*/
	/*Cell cell4 = row.getCell(position.get(WFRISK_EXCEL_FIELD_STATUS));
	if(cell4 != null && cell4.getCellType() == Cell.CELL_TYPE_STRING) {
		if (cell4.getStringCellValue().equals("OPEN")) {
			wfRisk.setStatus(1);
		} else if (cell4.getStringCellValue().equals("评估中")) {
			wfRisk.setStatus(2);
		}else if (cell4.getStringCellValue().equals("已评估")) {
			wfRisk.setStatus(3);
		}else if (cell4.getStringCellValue().equals("作废")) {
			wfRisk.setStatus(4);
		}else if (cell4.getStringCellValue().equals("CLOSE")) {
			wfRisk.setStatus(5);
		}
	}*/
	/*Cell cell5 = row.getCell(position.get(WFRISK_EXCEL_FIELD_DESCRIPTION));
	if(cell5 != null && cell5.getCellType() == Cell.CELL_TYPE_STRING) {
		wfRisk.setDescription(cell5.getStringCellValue());
	}*/
	/*Cell cell10 = row.getCell(position.get(WFRISK_EXCEL_FIELD_RISKMONITOR));
	if(cell10 != null && cell10.getCellType() == Cell.CELL_TYPE_STRING) {
		wfRisk.setRiskMonitor(cell10.getStringCellValue());
	}
	Cell cell11 = row.getCell(position.get(WFRISK_EXCEL_FIELD_EXECUTIONDATE));
	if(cell11 != null && cell11.getCellType() == Cell.CELL_TYPE_STRING) {
		wfRisk.setExecutionDate(cell11.getStringCellValue());
	}else if(cell11 != null && cell11.getCellType() == Cell.CELL_TYPE_NUMERIC){
		if(HSSFDateUtil.isCellDateFormatted(cell11)) {
			wfRisk.setExecutionDate(DateUtil.format(cell11.getDateCellValue(), "yyyy-MM-dd"));
		}
	}*/
	private Map<String,Integer> getPosition(Workbook worbook) {
		Map<String,Integer> positionMap = new HashMap<String, Integer>();
		Sheet sheet = worbook.getSheetAt(0);
		Row row = sheet.getRow(0);
		//System.out.println("row.getPhysicalNumberOfCells()"+row.getPhysicalNumberOfCells());
		if(row != null) {
			for(int i=0;i<row.getPhysicalNumberOfCells();i++) {
				Cell cell = row.getCell(i);
				if(cell != null) {
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						if(cell.getStringCellValue().contains(WFRISK_EXCEL_FIELD_DEPTNAME)) {
							positionMap.put(WFRISK_EXCEL_FIELD_DEPTNAME, i);
						}else if(cell.getStringCellValue().contains(WFRISK_EXCEL_FIELD_CATEGORY)) {
							positionMap.put(WFRISK_EXCEL_FIELD_CATEGORY, i);
						}else if(cell.getStringCellValue().contains(WFRISK_EXCEL_FIELD_RISKCONSEQUENCE)) {
							positionMap.put(WFRISK_EXCEL_FIELD_RISKCONSEQUENCE, i);
						}else if(cell.getStringCellValue().contains(WFRISK_EXCEL_FIELD_PREVENTIVEMEASURES)) {
							positionMap.put(WFRISK_EXCEL_FIELD_PREVENTIVEMEASURES, i);
						}else if(cell.getStringCellValue().contains(WFRISK_EXCEL_FIELD_IMPTIME)) {
							positionMap.put(WFRISK_EXCEL_FIELD_IMPTIME, i);
						}else if(cell.getStringCellValue().contains(WFRISK_EXCEL_FIELD_USERNAME)) {
							positionMap.put(WFRISK_EXCEL_FIELD_USERNAME, i);
						}
						break;
					}
				}
			}
		}
		return positionMap;
	}
	/*if(cell.getStringCellValue().contains(WFRISK_EXCEL_FIELD_ID)) {
	positionMap.put(WFRISK_EXCEL_FIELD_ID, i);
}else*/ 
/*}else if(cell.getStringCellValue().contains(WFRISK_EXCEL_FIELD_STATUS)) {
	positionMap.put(WFRISK_EXCEL_FIELD_STATUS, i);
}else if(cell.getStringCellValue().contains(WFRISK_EXCEL_FIELD_DESCRIPTION)) {
	positionMap.put(WFRISK_EXCEL_FIELD_DESCRIPTION, i);*/
/*}else if(cell.getStringCellValue().contains(WFRISK_EXCEL_FIELD_RISKMONITOR)) {
	positionMap.put(WFRISK_EXCEL_FIELD_RISKMONITOR, i);
}else if(cell.getStringCellValue().contains(WFRISK_EXCEL_FIELD_EXECUTIONDATE)) {
	positionMap.put(WFRISK_EXCEL_FIELD_EXECUTIONDATE, i);*/
	@Override
	public Workbook create2003(List<WfRisk> wfRiskList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Workbook create2007(List<WfRisk> entityList) {
		// TODO Auto-generated method stub
		return null;
	}
}
