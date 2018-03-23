package gnwf.ww.workflow;

public class WFAdapter implements WFCenter {

	@Override
	public void previewExec() throws Exception {
		
	}

	@Override
	public void processExec() throws Exception {
		
	}

	@Override
	public boolean isHasJob() throws Exception {
		return false;
	}

	@Override
	public void acceptTask() throws Exception {

	}


	@Override
	public void saveJob() throws Exception {

	}

	@Override
	public boolean nextStepPage() throws Exception {
		return false;
	}

	@Override
	public String exportXls()throws Exception {
		return null;
	}

	@Override
	public String importXls()throws Exception {
		return null;
	}

	@Override
	public String genAjaxInfo() throws Exception {
		return null;
	}

	@Override
	public void print() throws Exception {

	}

	@Override
	public boolean sendTask() throws Exception {
		return false;
	}

	@Override
	public String rejectTask(int taskStatus) throws Exception {
		return null;
	}

	@Override
	public String backTask(int taskStatus) throws Exception {
		return null;
	}
	
	@Override
	public void addFiles() throws Exception {
		
	}

	@Override
	public boolean needSpecifyNext() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
