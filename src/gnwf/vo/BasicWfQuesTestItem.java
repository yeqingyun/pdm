package gnwf.vo;

public class BasicWfQuesTestItem {
	public static final String SELF_FIELDS = "WfQuesTestItem.TestItemName,WfQuesTestItem.TestItemID";
	
	
	private java.lang.String testItemName; //测试项的名字
	private java.lang.String testItemID;   //测试项的ID
	public java.lang.String getTestItemName() {
		return testItemName;
	}
	public void setTestItemName(java.lang.String testItemName) {
		this.testItemName = testItemName;
	}
	public java.lang.String getTestItemID() {
		return testItemID;
	}
	public void setTestItemID(java.lang.String testItemID) {
		this.testItemID = testItemID;
	}
	
	
}
