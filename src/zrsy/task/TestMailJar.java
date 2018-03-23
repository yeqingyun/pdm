package zrsy.task;

import java.text.SimpleDateFormat;
import java.util.Date;


import com.gionee.gnif.mail.biz.base.MailSender;

public class TestMailJar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		for(int i=0;i<1;i++) {
    	
    	
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			String curDate = sdf.format(new Date());
			String title = "测试邮件标题,测试时间:" + curDate;
			String content = "测试邮件内容,测试时间:" + curDate;
			
			//String userList = "1045026444@qq.com,812020887@qq.com";
	
			String userList = "   ,,  15785  ,,  00000867  ,,   ,  quezg@gionee.com  ,,  28159  ,,15785,,15441,  zhuzb0@gionee.com  ,aa@aa.com,11@11.com,@2   3@,"
					+ "DUDX@GIONEE.COM,17538,15079,28516,28515,,16076,15870,   ZHUZB@GIONEE.COM    ,20958,148851,  shixian@gionee.com ,,,,  ,15785, 15785,47673,"
					+ ",  zhuzb@gionee.com  ,xiejianlong@gionee.com,-1,  dudx@gionee.com,  00000867 ,aaacf12 ,20903,,  caojj@gionee.com,zhouxiaopei@gionee.com,"
					+ "291640457@qq.com,1045026444@qq.com,812020887@qq.com,875936906@qq.com,291640457@qq.com,1045026444@qq.com,812020887@qq.com,875936906@qq.com,"
					+ " , fyi325747@djs9tytg1.com,quezg1@gionee.com ,zhuzb1@gionee.com,dihsafihf@djsjf.com,djsajfd1123@4345cjis.com,0932afhs@djsoif.com,66@66.com,"
					+ "   ,,  15785  ,,  00000867  ,,   ,  quezg@gionee.com  ,,  28159  ,,15785,,15441,  zhuzb0@gionee.com  ,aa@aa.com,11@11.com,@2   3@,"
					+ "DUDX@GIONEE.COM,17538,15079,28516,28515,,16076,15870,   ZHUZB@GIONEE.COM    ,20958,148851,  shixian@gionee.com ,,,,  ,15785, 15785,47673,"
					+ ",  zhuzb@gionee.com  ,xiejianlong@gionee.com,-1,  dudx@gionee.com,  00000867 ,aaacf12 ,20903,,  caojj@gionee.com,zhouxiaopei@gionee.com,"
					+ "291640457@qq.com,1045026444@qq.com,812020887@qq.com,875936906@qq.com,291640457@qq.com,1045026444@qq.com,812020887@qq.com,875936906@qq.com,"
					+ " , fyi325747@djs9tytg1.com,quezg1@gionee.com ,zhuzb1@gionee.com, zhuzb11@gionee.com ,zhuzb6@gionee.com ,dsafiusahf@fdjsvh.com ";
			
			//String userList = "quezg@gionee.com";
	
			//MailSender.sendByUsrId(null, null, null, null);
			//MailSender.sendByUsrId("", "", "", null);	
			//MailSender.sendByUsrId(userList, title, content, null);
			//MailSender.sendByUsrId(userList, title, content, 0);
			//MailSender.sendByUsrId(userList, title, title, 0);
			//MailSender.sendByUsrId(userList, title, content, 1);
			//MailSender.sendByUsrId(userList, title, content, 2);
			//MailSender.sendByUsrId(userList, title, content, -277);
			
			//MailSender.sendByAddress(null, null, null, null);
			//MailSender.sendByAddress("", "", "", null);
			//MailSender.sendByAddress(userList, title, content, null);
			//MailSender.sendByAddress(userList, title, content, 0);
			//MailSender.sendByAddress(userList, title, content, 1);
			//MailSender.sendByAddress(userList, title, content, 2);
			//MailSender.sendByAddress(userList, title, content, -277);
			
			MailSender.saveAddressOnly(userList, title, content, 0);
			
			//MailSender.sendByAddress(userList, title, content, 0);

		
		}

	}

}
