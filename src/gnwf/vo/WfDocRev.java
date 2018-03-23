package gnwf.vo;

public class WfDocRev extends BasicWfDocRev {
	public static final String ALL_FIELDS = "WfDocRev.DocId,WfDocRev.TaskId,WfDocRev.CreateBy,WfDocRev.WfNo,WfDocRev.CreateDate,WfDocRev.RevText,WfDocRev.RevId,WfDoc.DocId as DocName";
	public static final String LIST_FIELDS = "WfDocRev.DocId,WfDocRev.TaskId,WfDocRev.CreateBy,WfDocRev.WfNo,WfDocRev.CreateDate,WfDocRev.RevText,WfDocRev.RevId,WfDoc.DocId as DocName";


	private String docName;


	public String getDocName(){
		return docName;
	}
	public void setDocName(String docName){
		this.docName = docName;
	}

}