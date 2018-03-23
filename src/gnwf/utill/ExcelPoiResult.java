package gnwf.utill;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.Result;

public class ExcelPoiResult implements Result {

	private static final long serialVersionUID = 123819489031138655L;

	private Workbook workbookpoi;

	private String fileName;

	private String contenttype;

	@Override
	public void execute(ActionInvocation invocation) throws Exception {
		if (contenttype == null){
			contenttype = "application/ms-excel";
		}
		if (workbookpoi == null){
			Object object = invocation.getStack().findValue("Workbookpoi");
			if(object instanceof HSSFWorkbook) {
				workbookpoi = (HSSFWorkbook) object;
			}else if(object instanceof XSSFWorkbook) {
				workbookpoi = (XSSFWorkbook) object;
			}
		}
		if(fileName==null) {
			fileName = (String)invocation.getStack().findValue("fileName");
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType(contenttype);
		if(workbookpoi instanceof HSSFWorkbook) {
			response.setHeader("Content-Disposition", "attachment;Filename=" + fileName + ".xls");
		}else if(workbookpoi instanceof XSSFWorkbook) {
			response.setHeader("Content-Disposition", "attachment;Filename=" + fileName + ".xlsx");
		}
		OutputStream os = response.getOutputStream();
		workbookpoi.write(os);
		os.flush();
		os.close();
	}

	public void setWorkbookpoi(Workbook workbookpoi) {
		this.workbookpoi = workbookpoi;
	}

	public void setFilename(String filename) {
		this.fileName = filename;
	}

	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}
}
