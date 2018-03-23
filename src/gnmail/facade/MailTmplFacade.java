package gnmail.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnmail.dao.MailTmplDAO;
import gnmail.dao.helper.MailTmplHelper;
import gnmail.vo.MailTmpl;

public class MailTmplFacade {

	public void save(MailTmpl mailTmpl) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailTmplDAO.class).insert(mailTmpl);
			if(mailTmpl.getMailTmplGs() != null && mailTmpl.getMailTmplGs().size() > 0) {
				DAOFactory.getDAO(gnmail.dao.MailTmplGDAO.class).delete("delete from MailTmplG where MailTmplG.TmplId = " +mailTmpl.getTmplId());
				for(int i=0; i<mailTmpl.getMailTmplGs().size(); i++) {
					if(mailTmpl.getMailTmplGs().get(i) != null && mailTmpl.getMailTmplGs().get(i).getTmplId() != null) {
						mailTmpl.getMailTmplGs().get(i).setTmplId(mailTmpl.getTmplId());
						DAOFactory.getDAO(gnmail.dao.MailTmplGDAO.class).insert(mailTmpl.getMailTmplGs().get(i));
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

	public void update(MailTmpl mailTmpl) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailTmplDAO.class).update(mailTmpl);
			if(mailTmpl.getMailTmplGs() != null && mailTmpl.getMailTmplGs().size() > 0) {
				DAOFactory.getDAO(gnmail.dao.MailTmplGDAO.class).delete("delete from MailTmplG where MailTmplG.TmplId = " +mailTmpl.getTmplId());
				for(int i=0; i<mailTmpl.getMailTmplGs().size(); i++) {
					if(mailTmpl.getMailTmplGs().get(i) != null && mailTmpl.getMailTmplGs().get(i).getTmplId() != null) {
						mailTmpl.getMailTmplGs().get(i).setTmplId(mailTmpl.getTmplId());
						DAOFactory.getDAO(gnmail.dao.MailTmplGDAO.class).insert(mailTmpl.getMailTmplGs().get(i));
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
			DAOFactory.getDAO(MailTmplDAO.class).update(sql);
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

	public void submit(MailTmpl mailTmpl) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailTmplDAO.class).update(mailTmpl);
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

	public void review(MailTmpl mailTmpl) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailTmplDAO.class).update(mailTmpl);
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

	public void confirm(MailTmpl mailTmpl) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailTmplDAO.class).update(mailTmpl);
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
			DAOFactory.getDAO(MailTmplDAO.class).delete(sql);
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

	public void remove(MailTmpl mailTmpl) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailTmplDAO.class).delete(new MailTmplHelper().getSql4Delete(mailTmpl));
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

	public MailTmpl findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return (MailTmpl)DAOFactory.getDAO(MailTmplDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public MailTmpl findById(MailTmpl mailTmpl) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return (MailTmpl)DAOFactory.getDAO(MailTmplDAO.class).load(mailTmpl);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public MailTmpl findBy(MailTmpl mailTmpl) throws Exception {
		String sql = SqlUtil.getSql4All(MailTmplHelper.class,mailTmpl,MailTmpl.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (MailTmpl)DAOFactory.getDAO(MailTmplDAO.class).load(sql,MailTmpl.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailTmpl> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailTmpl>)DAOFactory.getDAO(MailTmplDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailTmpl> findAll(MailTmpl mailTmpl) throws Exception {
		String sql = SqlUtil.getSql4All(MailTmplHelper.class,mailTmpl,MailTmpl.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailTmpl>)DAOFactory.getDAO(MailTmplDAO.class).query(sql, MailTmpl.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailTmpl> find(MailTmpl mailTmpl,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(MailTmplHelper.class,mailTmpl,pageVO,MailTmpl.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailTmpl>)DAOFactory.getDAO(MailTmplDAO.class).query(sql, MailTmpl.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailTmpl> find(MailTmpl mailTmpl) throws Exception {
		String sql = SqlUtil.getSql4All(MailTmplHelper.class,mailTmpl,MailTmpl.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailTmpl>)DAOFactory.getDAO(MailTmplDAO.class).query(sql, MailTmpl.LIST_FIELDS);
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
			return DAOFactory.getDAO(MailTmplDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(MailTmpl mailTmpl) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return DAOFactory.getDAO(MailTmplDAO.class).amount(new MailTmplHelper().getSql4Amount(mailTmpl));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}