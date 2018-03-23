package gnmail.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnmail.dao.MailGroupDAO;
import gnmail.dao.helper.MailGroupHelper;
import gnmail.vo.MailGroup;

public class MailGroupFacade {

	public void save(MailGroup mailGroup) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailGroupDAO.class).insert(mailGroup);
			if(mailGroup.getMailBooks() != null && mailGroup.getMailBooks().size() > 0) {
				DAOFactory.getDAO(gnmail.dao.MailBookDAO.class).delete("delete from MailBook where MailBook.GroupId = " +mailGroup.getGroupId());
				for(int i=0; i<mailGroup.getMailBooks().size(); i++) {
					if(mailGroup.getMailBooks().get(i) != null && mailGroup.getMailBooks().get(i).getGroupId() != null) {
						mailGroup.getMailBooks().get(i).setGroupId(mailGroup.getGroupId());
						DAOFactory.getDAO(gnmail.dao.MailBookDAO.class).insert(mailGroup.getMailBooks().get(i));
					}
				}
			}
			if(mailGroup.getMailTmplGs() != null && mailGroup.getMailTmplGs().size() > 0) {
				DAOFactory.getDAO(gnmail.dao.MailTmplGDAO.class).delete("delete from MailTmplG where MailTmplG.GroupId = " +mailGroup.getGroupId());
				for(int i=0; i<mailGroup.getMailTmplGs().size(); i++) {
					if(mailGroup.getMailTmplGs().get(i) != null && mailGroup.getMailTmplGs().get(i).getGroupId() != null) {
						mailGroup.getMailTmplGs().get(i).setGroupId(mailGroup.getGroupId());
						DAOFactory.getDAO(gnmail.dao.MailTmplGDAO.class).insert(mailGroup.getMailTmplGs().get(i));
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

	public void update(MailGroup mailGroup) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailGroupDAO.class).update(mailGroup);
			if(mailGroup.getMailBooks() != null && mailGroup.getMailBooks().size() > 0) {
				DAOFactory.getDAO(gnmail.dao.MailBookDAO.class).delete("delete from MailBook where MailBook.GroupId = " +mailGroup.getGroupId());
				for(int i=0; i<mailGroup.getMailBooks().size(); i++) {
					if(mailGroup.getMailBooks().get(i) != null && mailGroup.getMailBooks().get(i).getGroupId() != null) {
						mailGroup.getMailBooks().get(i).setGroupId(mailGroup.getGroupId());
						DAOFactory.getDAO(gnmail.dao.MailBookDAO.class).insert(mailGroup.getMailBooks().get(i));
					}
				}
			}
			if(mailGroup.getMailTmplGs() != null && mailGroup.getMailTmplGs().size() > 0) {
				DAOFactory.getDAO(gnmail.dao.MailTmplGDAO.class).delete("delete from MailTmplG where MailTmplG.GroupId = " +mailGroup.getGroupId());
				for(int i=0; i<mailGroup.getMailTmplGs().size(); i++) {
					if(mailGroup.getMailTmplGs().get(i) != null && mailGroup.getMailTmplGs().get(i).getGroupId() != null) {
						mailGroup.getMailTmplGs().get(i).setGroupId(mailGroup.getGroupId());
						DAOFactory.getDAO(gnmail.dao.MailTmplGDAO.class).insert(mailGroup.getMailTmplGs().get(i));
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
			DAOFactory.getDAO(MailGroupDAO.class).update(sql);
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

	public void submit(MailGroup mailGroup) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailGroupDAO.class).update(mailGroup);
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

	public void review(MailGroup mailGroup) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailGroupDAO.class).update(mailGroup);
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

	public void confirm(MailGroup mailGroup) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailGroupDAO.class).update(mailGroup);
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
			DAOFactory.getDAO(MailGroupDAO.class).delete(sql);
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

	public void remove(MailGroup mailGroup) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(MailGroupDAO.class).delete(new MailGroupHelper().getSql4Delete(mailGroup));
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

	public MailGroup findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return (MailGroup)DAOFactory.getDAO(MailGroupDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public MailGroup findById(MailGroup mailGroup) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return (MailGroup)DAOFactory.getDAO(MailGroupDAO.class).load(mailGroup);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public MailGroup findBy(MailGroup mailGroup) throws Exception {
		String sql = SqlUtil.getSql4All(MailGroupHelper.class,mailGroup,MailGroup.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (MailGroup)DAOFactory.getDAO(MailGroupDAO.class).load(sql,MailGroup.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailGroup> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailGroup>)DAOFactory.getDAO(MailGroupDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailGroup> findAll(MailGroup mailGroup) throws Exception {
		String sql = SqlUtil.getSql4All(MailGroupHelper.class,mailGroup,MailGroup.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailGroup>)DAOFactory.getDAO(MailGroupDAO.class).query(sql, MailGroup.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailGroup> find(MailGroup mailGroup,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(MailGroupHelper.class,mailGroup,pageVO,MailGroup.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailGroup>)DAOFactory.getDAO(MailGroupDAO.class).query(sql, MailGroup.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailGroup> find(MailGroup mailGroup) throws Exception {
		String sql = SqlUtil.getSql4All(MailGroupHelper.class,mailGroup,MailGroup.LIST_FIELDS);
		DbConnUtil.buildDbconn(4);
		try {
			return (List<MailGroup>)DAOFactory.getDAO(MailGroupDAO.class).query(sql, MailGroup.LIST_FIELDS);
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
			return DAOFactory.getDAO(MailGroupDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(MailGroup mailGroup) throws Exception {
		DbConnUtil.buildDbconn(4);
		try {
			return DAOFactory.getDAO(MailGroupDAO.class).amount(new MailGroupHelper().getSql4Amount(mailGroup));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}