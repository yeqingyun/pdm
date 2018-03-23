package gnwf.parser;

public class ExcelFormatIncorrectException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public ExcelFormatIncorrectException(){
		
	}
	public ExcelFormatIncorrectException(String massege){
		super(massege);
	}
}
