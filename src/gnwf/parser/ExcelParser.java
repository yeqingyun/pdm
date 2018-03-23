package gnwf.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

public interface ExcelParser<T> {
	/**
	 * 解析2003Excel
	 * @param is 文件输入流
	 * @return
	 * @throws IOException
	 */
	public List<T> parser2003(InputStream is)throws IOException;
	/**
	 * 解析2007Excel
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public List<T> parser2007(InputStream is)throws IOException;
	/**
	 * 创建2003Excel
	 * @param entityList
	 * @return
	 */
	public Workbook create2003(List<T> entityList) throws Exception;
	/**
	 * 创建2007Excel
	 * @param entityList
	 * @return
	 */
	public Workbook create2007(List<T> entityList)throws Exception;
	
}
