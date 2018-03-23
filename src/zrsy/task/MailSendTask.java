package zrsy.task;

import gnmail.facade.MailCfgFacade;
import gnmail.facade.MailFacade;
import gnmail.facade.MailToFacade;
import gnmail.vo.Mail;
import gnmail.vo.MailCfg;
import gnmail.vo.MailTo;

import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.frm.comn.CryptPwd;
import org.frm.mail.MailServ;

import zrsy.facade.SyScheFacade;
import zrsy.facade.WarrMsgFacade;
import zrsy.vo.WarrMsg;


public class MailSendTask extends TimerTask {
	
	private String scheNo = "EMAIL001";

	@Override
	public void run() {

		try {
			//System.out.println("exec mail task ...");
			
			int amount = new SyScheFacade().amount("select count(*) amount from SySche where SySche.Status = 1 " +
			 		" and SySche.ScheNo = '"+scheNo+"' ");
			
			if(amount > 0) { //当前任务状态为有效，执行
				
				//检查邮箱配置信息
				MailCfgFacade cfgFacade = new MailCfgFacade();
				MailFacade facade = new MailFacade();
				
				List<Mail> mails = new MailFacade().find("select " + Mail.SELF_FIELDS +
						" from Mail where Status = 0 ",Mail.SELF_FIELDS);
				
				for(int i=0; i<mails.size(); i++) {

					MailCfg cfg = new MailCfg();
					cfg.setUserId(mails.get(i).getSenderId());
					MailCfg sendCfg = cfgFacade.findBy(cfg);
					
					List<MailTo> mailtos = new MailToFacade().find("select " + MailTo.SELF_FIELDS +
							" from MailTo where MailId = " + mails.get(i).getMailId(),MailTo.SELF_FIELDS);
					
					String to = "";
					String cc = "";
					
					for(int n=0; n <mailtos.size(); n++) {
						if(mailtos.get(n).getToMail() != null && mailtos.get(n).getToMail().contains("@")) {  //排除邮箱地址不规范的邮件接收人
							if(mailtos.get(n).getToType().equals(0)) {
								if(to.equals(""))
									to += mailtos.get(n).getToName()+"<"+mailtos.get(n).getToMail()+">";
								else
									to += "," + mailtos.get(n).getToName()+"<"+mailtos.get(n).getToMail()+">";
							}
							else {
								if(cc.equals(""))
									cc += mailtos.get(n).getToName()+"<"+mailtos.get(n).getToMail()+">";
								else
									cc += "," + mailtos.get(n).getToName()+"<"+mailtos.get(n).getToMail()+">";
							}
						}
					}
					mails.get(i).setCc(cc);
					mails.get(i).setTo(to);
					
					//发送邮件
					CryptPwd cryptPwd = new CryptPwd();
					if(mails.get(i).getTo() != null && !mails.get(i).getTo().trim().equals("")) {
						if(sendCfg == null) {
							WarrMsg warrMsg = new WarrMsg();
							warrMsg.setCreateBy(0);
							warrMsg.setCreateDate(new Date());
							warrMsg.setLastUpd(0);
							warrMsg.setLastUpdDate(new Date());
							
							warrMsg.setAccept(mails.get(i).getSenderId());
							warrMsg.setTitle("系统提示：在金立办公自动化系统当中，您的邮箱设置没有设置，无法通过oa系统发出邮件。");
							warrMsg.setMsgText("请登录OA系统后,通过“程序--邮箱管理--邮箱设置--邮箱配置”，完成oa系统的邮箱设置。");
							
							new WarrMsgFacade().save(warrMsg);
						}
						else if(sendCfg.getServIpAddr() == null || sendCfg.getMailAddr() == null || sendCfg.getPwd() == null ) {
							WarrMsg warrMsg = new WarrMsg();
							warrMsg.setCreateBy(0);
							warrMsg.setCreateDate(new Date());
							warrMsg.setLastUpd(0);
							warrMsg.setLastUpdDate(new Date());
							
							warrMsg.setAccept(mails.get(i).getSenderId());
							warrMsg.setTitle("系统提示：在金立办公自动化系统当中，您的邮箱设置没有设置完成，无法通过oa系统发出邮件。");
							warrMsg.setMsgText("请登录OA系统后,通过“程序--邮箱管理--邮箱设置--邮箱配置”，完成oa系统的邮箱设置。");
							new WarrMsgFacade().save(warrMsg);
						}
						else if((sendCfg.getServIpAddr() != null && sendCfg.getServIpAddr().trim().equals(""))
								|| (sendCfg.getMailAddr() != null && sendCfg.getMailAddr().trim().equals(""))
								|| (sendCfg.getPwd() != null && sendCfg.getPwd().trim().equals(""))
								|| (sendCfg.getPwd() != null && sendCfg.getPwd().trim().equals("1"))) {
							
							WarrMsg warrMsg = new WarrMsg();
							warrMsg.setCreateBy(0);
							warrMsg.setCreateDate(new Date());
							warrMsg.setLastUpd(0);
							warrMsg.setLastUpdDate(new Date());
							
							warrMsg.setAccept(mails.get(i).getSenderId());
							warrMsg.setTitle("系统提示：在金立办公自动化系统当中，您的邮箱设置没有设置完成，无法通过oa系统发出邮件。");
							warrMsg.setMsgText("请登录OA系统后,通过“程序--邮箱管理--邮箱设置--邮箱配置”，完成oa系统的邮箱设置。");
							new WarrMsgFacade().save(warrMsg);
						}
						else {
							
							//发送邮件
							MailServ mailServ = new MailServ(sendCfg.getServIpAddr(),Integer.valueOf(sendCfg.getSmpt().trim()),sendCfg.getMailAddr(),sendCfg.getMailName(),cryptPwd.getDecry(sendCfg.getPwd()));
							//Mail mail = mails.get(i);
						//	System.out.println("Title:"+mail.getTitle()+"MailText:"+mail.getMailText());
							boolean b = mailServ.sendHtmlMail(mails.get(i).getTo(),mails.get(i).getCc(), mails.get(i).getTitle(), mails.get(i).getMailText(),true);
							
							if(b){
								facade.update("update Mail set Mail.Status = 1 where Mail.MailId = "+mails.get(i).getMailId());
							}
						}
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			Logger.getLogger(this.getClass()).error("MailCfgTask Exception", e);
		}
	}
}
