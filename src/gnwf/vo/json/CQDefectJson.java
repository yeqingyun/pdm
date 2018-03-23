package gnwf.vo.json;
import java.util.List;

import gnwf.vo.CQDefect;
public class CQDefectJson {
	public List<CQDefect> Rows;
	public Integer Total;
	public CQDefectJson(){
		
	}
	public CQDefectJson(List<CQDefect> rows, Integer total) {
		Rows = rows;
		Total = total;
	}
}