package zrsy.task;

import java.util.TimerTask;
import org.apache.log4j.Logger;

import zrsy.facade.SyScheFacade;


public class TestScheTask extends TimerTask {

	private String scheNo = "TEST0001";
	
	@Override
	public void run() {
		
		try {
			int amount = new SyScheFacade().amount("select count(*) as amount from SySche where SySche.Status = 1 " +
			 		" and SySche.ScheNo = '"+scheNo+"' ");
			
			if(amount > 0) //当前任务状态为有效，执行
				System.out.println("this is test com.gnpdm.task.TestScheTask. status is 1 ");
			else //当前任务状态为无效，不执行
				System.out.println("this is test com.gnpdm.task.TestScheTask. status is 0 ");
		}
		catch(Exception e) {
			e.printStackTrace();
			Logger.getLogger(this.getClass()).error("TestScheTask Exception", e);
		}
	}
}
