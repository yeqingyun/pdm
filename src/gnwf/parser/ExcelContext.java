package gnwf.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

public class ExcelContext<T> {
	
	private ExcelParser<T> parser;

	public ExcelContext(ExcelParser<T> parser) {
		this.parser = parser;
	}
	
	public List<T> parser(String file) throws FileNotFoundException,IOException, ExcelFormatIncorrectException {
		InputStream is = null;
		try {
			is = new FileInputStream(new File(file));
			if(ExcelUtil.EXCEL_FIX_2003.equals(ExcelUtil.getExcelFileFix(file))) {
				return parser.parser2003(is);
			}else if(ExcelUtil.EXCEL_FIX_2007.equals(ExcelUtil.getExcelFileFix(file))) {
				return parser.parser2007(is);
			}else {
				throw new ExcelFormatIncorrectException("格式不正确");
			}
		} catch(FileNotFoundException e){
			e.printStackTrace();
			throw e;
		}catch (IOException e) {
			e.printStackTrace();
			throw e;
		}finally{
			if(is != null)is.close();
		}
	}
	
	public Workbook create(List<T> entityList,String type) throws Exception {
		if("xls".equals(type)) {
			return parser.create2003(entityList);
		}
		return null;
	}
}
