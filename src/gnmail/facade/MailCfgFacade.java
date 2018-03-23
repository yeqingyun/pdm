package gnmail.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnmail.dao.MailCfgDAO;
import gnmail.dao.helper.MailCfgHelper;
import gnmail.vo.MailCfg;

public class MailCfgFacade {

	public void save(MailCfg mailCfg) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailCfgDAO.class).insert(mailCfg);
			if(mailCfg.getMails() != null && mailCfg.getMails().size() > 0) {
				DAOFactory.getDAO(gnmail.dao.MailDAO.class).delete("delete from Mail where Mail.CfgId = " +mailCfg.getCfgId());
				for(int i=0; i<mailCfg.getMails().size(); i++) {
					if(mailCfg.getMails().get(i) != null && mailCfg.getMails().get(i).getCfgId() != null) {
						mailCfg.getMails().get(i).setCfgId(mailCfg.getCfgId());
						DAOFactory.getDAO(gnmail.dao.MailDAO.class).insert(mailCfg.getMails().get(i));
					}
				}
			}
			if(mailCfg.getMailTmpls() != null && mailCfg.getMailTmpls().size() > 0) {
				DAOFactory.getDAO(gnmail.dao.MailTmplDAO.class).delete("delete from MailTmpl where MailTmpl.CfgId = " +mailCfg.getCfgId());
				for(int i=0; i<mailCfg.getMailTmpls().size(); i++) {
					if(mailCfg.getMailTmpls().get(i) != null && mailCfg.getMailTmpls().get(i).getCfgId() != null) {
						mailCfg.getMailTmpls().get(i).setCfgId(mailCfg.getCfgId());
						DAOFactory.getDAO(gnmail.dao.MailTmplDAO.class).insert(mailCfg.getMailTmpls().get(i));
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

	public void update(MailCfg mailCfg) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailCfgDAO.class).update(mailCfg);
			if(mailCfg.getMails() != null && mailCfg.getMails().size() > 0) {
				DAOFactory.getDAO(gnmail.dao.MailDAO.class).delete("delete from Mail where Mail.CfgId = " +mailCfg.getCfgId());
				for(int i=0; i<mailCfg.getMails().size(); i++) {
					if(mailCfg.getMails().get(i) != null && mailCfg.getMails().get(i).getCfgId() != null) {
						mailCfg.getMails().get(i).setCfgId(mailCfg.getCfgId());
						DAOFactory.getDAO(gnmail.dao.MailDAO.class).insert(mailCfg.getMails().get(i));
					}
				}
			}
			if(mailCfg.getMailTmpls() != null && mailCfg.getMailTmpls().size() > 0) {
				DAOFactory.getDAO(gnmail.dao.MailTmplDAO.class).delete("delete from MailTmpl where MailTmpl.CfgId = " +mailCfg.getCfgId());
				for(int i=0; i<mailCfg.getMailTmpls().size(); i++) {
					if(mailCfg.getMailTmpls().get(i) != null && mailCfg.getMailTmpls().get(i).getCfgId() != null) {
						mailCfg.getMailTmpls().get(i).setCfgId(mailCfg.getCfgId());
						DAOFactory.getDAO(gnmail.dao.MailTmplDAO.class).insert(mailCfg.getMailTmpls().get(i));
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
			DAOFactory.getDAO(MailCfgDAO.class).update(sql);
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

	public void submit(MailCfg mailCfg) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailCfgDAO.class).update(mailCfg);
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

	public void review(MailCfg mailCfg) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailCfgDAO.class).update(mailCfg);
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

	public void confirm(MailCfg mailCfg) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailCfgDAO.class).update(mailCfg);
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
			DAOFactory.getDAO(MailCfgDAO.class).delete(sql);
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

	public void remove(MailCfg mailCfg) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailCfgDAO.class).delete(new MailCfgHelper().getSql4Delete(mailCfg));
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

	public MailCfg findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return (MailCfg)DAOFactory.getDAO(MailCfgDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public MailCfg findById(MailCfg mailCfg) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return (MailCfg)DAOFactory.getDAO(MailCfgDAO.class).load(mailCfg);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public MailCfg findBy(MailCfg mailCfg) throws Exception {
		String sql = SqlUtil.getSql4All(MailCfgHelper.class,mailCfg,MailCfg.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (MailCfg)DAOFactory.getDAO(MailCfgDAO.class).load(sql,MailCfg.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailCfg> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailCfg>)DAOFactory.getDAO(MailCfgDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailCfg> findAll(MailCfg mailCfg) throws Exception {
		String sql = SqlUtil.getSql4All(MailCfgHelper.class,mailCfg,MailCfg.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailCfg>)DAOFactory.getDAO(MailCfgDAO.class).query(sql, MailCfg.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailCfg> find(MailCfg mailCfg,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(MailCfgHelper.class,mailCfg,pageVO,MailCfg.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailCfg>)DAOFactory.getDAO(MailCfgDAO.class).query(sql, MailCfg.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailCfg> find(MailCfg mailCfg) throws Exception {
		String sql = SqlUtil.getSql4All(MailCfgHelper.class,mailCfg,MailCfg.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailCfg>)DAOFactory.getDAO(MailCfgDAO.class).query(sql, MailCfg.LIST_FIELDS);
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
			return DAOFactory.getDAO(MailCfgDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(MailCfg mailCfg) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return DAOFactory.getDAO(MailCfgDAO.class).amount(new MailCfgHelper().getSql4Amount(mailCfg));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}