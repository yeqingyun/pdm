package gnmail.facade;

import gnmail.dao.MailCfgDAO;
import gnmail.dao.MailDAO;
import gnmail.dao.MailToDAO;
import gnmail.dao.helper.MailHelper;
import gnmail.vo.Mail;
import gnmail.vo.MailTo;
import gnwf.ww.workflow.WFUtil;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrsy.dao.AddrBookDAO;
import zrsy.vo.AddrBook;
import zrsy.vo.Usr;

public class MailFacade {

	public void save(Mail mail) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			//DAOFactory.getDAO(MailDAO.class).insert(mail);
			int mailId = new MailDAO().save(mail);
			
//			if(mail.getMailAutoMs() != null && mail.getMailAutoMs().size() > 0) {
//				DAOFactory.getDAO(gnmail.dao.MailAutoMDAO.class).delete("delete from MailAutoM where MailAutoM.MailId = " +mail.getMailId());
//				for(int i=0; i<mail.getMailAutoMs().size(); i++) {
//					if(mail.getMailAutoMs().get(i) != null && mail.getMailAutoMs().get(i).getMailId() != null) {
//						mail.getMailAutoMs().get(i).setMailId(mail.getMailId());
//						DAOFactory.getDAO(gnmail.dao.MailAutoMDAO.class).insert(mail.getMailAutoMs().get(i));
//					}
//				}
//			}
//			if(mail.getMailDocs() != null && mail.getMailDocs().size() > 0) {
//				DAOFactory.getDAO(gnmail.dao.MailDocDAO.class).delete("delete from MailDoc where MailDoc.MailId = " +mail.getMailId());
//				for(int i=0; i<mail.getMailDocs().size(); i++) {
//					if(mail.getMailDocs().get(i) != null && mail.getMailDocs().get(i).getMailId() != null) {
//						mail.getMailDocs().get(i).setMailId(mail.getMailId());
//						DAOFactory.getDAO(gnmail.dao.MailDocDAO.class).insert(mail.getMailDocs().get(i));
//					}
//				}
//			}
			
			
			String[] tos = mail.getTo().split(",");
			for(int i=0; i<tos.length; i++) {
				String toName = tos[i];
				String toMail = tos[i];
				
				if(tos[i].indexOf("<") > 0) {
					toName = tos[i].split("<")[0];
					toMail = tos[i].split("<")[1].split(">")[0];
				}

				AddrBook abook = (AddrBook)DAOFactory.getDAO(AddrBookDAO.class).load("select "+AddrBook.SELF_FIELDS+" from AddrBook where AddrBook.MailAddr = '"+toMail+"'", AddrBook.SELF_FIELDS);
				
				MailTo mailTo = new MailTo();
				
				mailTo.setMailId(mailId);
				mailTo.setToType(0);
				mailTo.setToName(toName);
				//TODO Ϊ��
				mailTo.setToId(abook.getUserId());
				mailTo.setToMail(toMail);
				
				DAOFactory.getDAO(MailToDAO.class).insert(mailTo);
			}
			if(mail.getCc() != null && !mail.getCc().trim().equals("")) {
				String[] ccs = mail.getCc().split(",");
				
				for(int i=0; i<ccs.length; i++) {
					String toName = ccs[i];
					String toMail = ccs[i];
					
					if(tos[i].indexOf("<") > 0) {

						toName = ccs[i].split("<")[0];
						toMail = ccs[i].split("<")[1].split(">")[0];
						
					}

					AddrBook abook = (AddrBook)DAOFactory.getDAO(AddrBookDAO.class).load("select "+AddrBook.SELF_FIELDS+" from AddrBook where AddrBook.MailAddr = '"+toMail+"'", AddrBook.SELF_FIELDS);
					//MailCfg cfg = (MailCfg)DAOFactory.getDAO(MailCfgDAO.class).load("select "+MailCfg.SELF_FIELDS+" from MailCfg where MailCfg.MailAddr = '"+toMail+"'", MailCfg.SELF_FIELDS);
					
					MailTo mailTo = new MailTo();
					mailTo.setMailId(mailId);
					mailTo.setToId(abook.getUserId());
					mailTo.setToMail(toMail);
					mailTo.setToName(toName);
					mailTo.setToType(1);
					
					DAOFactory.getDAO(MailToDAO.class).insert(mailTo);
				}
			}
			
			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
	
	public void saveSingleMail(Mail mail)throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			
			if(mail!=null && mail.getSender()!=null && mail.getAccept()!=null && mail.getAccept().matches("\\w+@\\w+\\.\\w+")){
				int mailId = new MailDAO().save(mail);
				String mailSql = "insert into mailTo values("+mailId+",-1,'"+mail.getAccept()+"',0,null);";
				DAOFactory.getDAO(MailToDAO.class).update(mailSql);
			}
			
			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
	
	public void saveMailList(List<Mail> mailList)throws Exception{
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			
			for(int i=0;i<mailList.size();i++){
				Mail mail = mailList.get(i);
				
				if(mail!=null && mail.getAccept()==null && mail.getAcceptId()!=null && mail.getAcceptId()!=0){
					String field = "UserId,MailAddr,AddrName";
					String mailSql = "select "+field+" from AddrBook where userId ="+mail.getAcceptId() ;
					AddrBook mailCfg = (AddrBook) new MailCfgDAO().load(mailSql, field);
					
					if(mailCfg!=null && mailCfg.getMailAddr()!=null){
						mail.setAccept(mailCfg.getMailAddr());
					}
				}
				
				if(mail!=null && mail.getSender()!=null && mail.getAccept()!=null && mail.getAccept().matches("\\w+@\\w+\\.\\w+")){
					int mailId = new MailDAO().save(mail);
					String mailSql = "insert into mailTo values("+mailId+",-1,'"+mail.getAccept()+"',0,null);";
					DAOFactory.getDAO(MailToDAO.class).update(mailSql);
				}
			}
			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
	
	public void save(Mail mail,List<Usr> list) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			if(WFUtil.isNotNull(list)){
				int mailId = new MailDAO().save(mail);
				
				StringBuffer str = new StringBuffer();
				for(int i=0;i<list.size();i++){
					if(list.get(i)!=null){
						str.append(list.get(i).getId()+",");
					}
				}
				
				String mailSql = "select AddrBook.UserId,AddrBook.MailAddr,AddrBook.AddrName from AddrBook " +
						" where userId in("+str.substring(0,str.length()-1) + ")"+"group by AddrBook.UserId,AddrBook.MailAddr,AddrBook.AddrName";
				List<AddrBook> all = new AddrBookDAO().query(mailSql, "AddrBook.UserId,AddrBook.MailAddr,AddrBook.AddrName");

				if(WFUtil.isNotNull(all)){
					StringBuffer buf = new StringBuffer();
					for(int i=0;i<all.size();i++){
						AddrBook c = all.get(i);
						/*System.out.println(c.getMailAddr());
						if (c.getMailAddr().matches("\\w+@\\w+\\.\\w+")) {
							System.out.println("1111111");
						}*/
						if(c!=null && c.getMailAddr()!=null ){
							buf.append("insert into mailto values("+mailId+","+c.getUserId()
									+",'"+c.getMailAddr()+"',0,'"+c.getAddrName()+"');");
						}
					}
					System.out.println(buf.toString());
					if(buf.length()>0){
						DAOFactory.getDAO(MailToDAO.class).update(buf.toString());
					}
				}
			}
			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void update(Mail mail) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailDAO.class).update(mail);
			if(mail.getMailAutoMs() != null && mail.getMailAutoMs().size() > 0) {
				DAOFactory.getDAO(gnmail.dao.MailAutoMDAO.class).delete("delete from MailAutoM where MailAutoM.MailId = " +mail.getMailId());
				for(int i=0; i<mail.getMailAutoMs().size(); i++) {
					if(mail.getMailAutoMs().get(i) != null && mail.getMailAutoMs().get(i).getMailId() != null) {
						mail.getMailAutoMs().get(i).setMailId(mail.getMailId());
						DAOFactory.getDAO(gnmail.dao.MailAutoMDAO.class).insert(mail.getMailAutoMs().get(i));
					}
				}
			}
			if(mail.getMailDocs() != null && mail.getMailDocs().size() > 0) {
				DAOFactory.getDAO(gnmail.dao.MailDocDAO.class).delete("delete from MailDoc where MailDoc.MailId = " +mail.getMailId());
				for(int i=0; i<mail.getMailDocs().size(); i++) {
					if(mail.getMailDocs().get(i) != null && mail.getMailDocs().get(i).getMailId() != null) {
						mail.getMailDocs().get(i).setMailId(mail.getMailId());
						DAOFactory.getDAO(gnmail.dao.MailDocDAO.class).insert(mail.getMailDocs().get(i));
					}
				}
			}

			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void update(String sql) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailDAO.class).update(sql);
			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void submit(Mail mail) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailDAO.class).update(mail);
			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void review(Mail mail) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailDAO.class).update(mail);
			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void confirm(Mail mail) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailDAO.class).update(mail);
			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void remove(String sql) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailDAO.class).delete(sql);
			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void remove(Mail mail) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailDAO.class).delete(new MailHelper().getSql4Delete(mail));
			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public Mail findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return (Mail)DAOFactory.getDAO(MailDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public Mail findById(Mail mail) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return (Mail)DAOFactory.getDAO(MailDAO.class).load(mail);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public Mail findBy(Mail mail) throws Exception {
		String sql = SqlUtil.getSql4All(MailHelper.class,mail,Mail.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (Mail)DAOFactory.getDAO(MailDAO.class).load(sql,Mail.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Mail> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			System.out.println(sql);
			return (List<Mail>)DAOFactory.getDAO(MailDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Mail> findAll(Mail mail) throws Exception {
		String sql = SqlUtil.getSql4All(MailHelper.class,mail,Mail.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (List<Mail>)DAOFactory.getDAO(MailDAO.class).query(sql, Mail.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Mail> find(Mail mail,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(MailHelper.class,mail,pageVO,Mail.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (List<Mail>)DAOFactory.getDAO(MailDAO.class).query(sql, Mail.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Mail> find(Mail mail) throws Exception {
		String sql = SqlUtil.getSql4All(MailHelper.class,mail,Mail.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (List<Mail>)DAOFactory.getDAO(MailDAO.class).query(sql, Mail.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(String sql) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return DAOFactory.getDAO(MailDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(Mail mail) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return DAOFactory.getDAO(MailDAO.class).amount(new MailHelper().getSql4Amount(mail));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}