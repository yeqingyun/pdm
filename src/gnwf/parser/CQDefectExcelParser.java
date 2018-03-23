package gnwf.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import gnwf.vo.CQDefect;

public class CQDefectExcelParser implements ExcelParser<CQDefect> {
	
	public static final int CQDEFECT_EXCEL_FIELD_COUNT = 2;
	
	public static final String CQDEFECT_EXCEL_FIELD_ID = "ID";
	public static final String CQDEFECT_EXCEL_FIELD_HEADLINE = "HEADLINE";

	@Override
	public List<CQDefect> parser2003(InputStream is) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook(is);
		return read(workbook);
	}

	@Override
	public List<CQDefect> parser2007(InputStream is) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(is);
		return read(workbook);
	}
	
	private List<CQDefect> read(Workbook workbook) {
		List<CQDefect> defectList = new ArrayList<CQDefect>();
		Map<String,Integer> position = getPosition(workbook);
		if(position.size() < CQDEFECT_EXCEL_FIELD_COUNT) {
			return defectList;
		}
		Sheet sheet = workbook.getSheetAt(0);
		for(int i=1;i<sheet.getPhysicalNumberOfRows();i++) {
			CQDefect defect = new CQDefect();
			Row row = sheet.getRow(i);
			Cell cell1 = row.getCell(position.get(CQDEFECT_EXCEL_FIELD_ID));
			if(cell1 != null && cell1.getCellType() == Cell.CELL_TYPE_STRING) {
				defect.setId(cell1.getStringCellValue());
			}
			Cell cell2 = row.getCell(position.get(CQDEFECT_EXCEL_FIELD_HEADLINE));
			if(cell2 != null && cell2.getCellType() == Cell.CELL_TYPE_STRING) {
				defect.setHeadline(cell2.getStringCellValue());
			}
			defectList.add(defect);
		}
		return defectList;
	}
	
	private Map<String, Integer> getPosition(Workbook workbook) {
		Map<String,Integer> positionMap = new HashMap<String, Integer>();
		Sheet sheet = workbook.getSheetAt(0);
		Row row = sheet.getRow(0);
		if(row!= null) {
			for(int i=0;i<row.getPhysicalNumberOfCells();i++) {
				Cell cell = row.getCell(i);
				if(cell != null) {
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						if(cell.getStringCellValue().toUpperCase().equals(CQDEFECT_EXCEL_FIELD_ID)) {
							positionMap.put(CQDEFECT_EXCEL_FIELD_ID, i);
						}else if(cell.getStringCellValue().toUpperCase().equals(CQDEFECT_EXCEL_FIELD_HEADLINE)) {
							positionMap.put(CQDEFECT_EXCEL_FIELD_HEADLINE, i);
						}
						break;
					}
				}
			}
		}
		return positionMap;
	}

	@Override
	public Workbook create2003(List<CQDefect> entityList) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Workbook create2007(List<CQDefect> entityList) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
