package gnwf.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExcelUtil {
	
	public static final String EXCEL_TYPE_2003 = "application/vnd.ms-excel";
	public static final String EXCEL_TYPE_2007 = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	public static final String EXCEL_FIX_2003 = "xls";
	public static final String EXCEL_FIX_2007 = "xlsx";
	
	public static String getExcelFiletype(String file){
		Path path = Paths.get(file);
		String contentType = null;
		try {
			contentType = Files.probeContentType(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentType;
	}
	
	public static String getExcelFileFix(String file) {
		String fix = null;
		fix = file.substring(file.lastIndexOf(".") + 1, file.length());
		return fix;
	}
	
}
