package gnwf.facade;

import gnwf.dao.DocRoleDAO;
import gnwf.dao.helper.DocRoleHelper;
import gnwf.vo.DocRole;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;


public class DocRoleFacade {

	public void save(DocRole docRole) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DocRoleDAO.class).insert(docRole);

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

	public void update(DocRole docRole) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DocRoleDAO.class).update(docRole);

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
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DocRoleDAO.class).update(sql);
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

//	public void submit(DocRole docRole) throws Exception {
//		DbConnUtil.buildDbconn(3);
//		try {
//			DbConnUtil.beginTransaction();
//			DAOFactory.getDAO(DocRoleDAO.class).update(docRole);
//			DbConnUtil.commitTransaction();
//		}
//		catch(Exception e) {
//			DbConnUtil.rollbackTransaction();
//			throw e;
//		}
//		finally {
//			DbConnUtil.closeDbconn();
//		}
//	}

//	public void review(DocRole docRole) throws Exception {
//		DbConnUtil.buildDbconn(3);
//		try {
//			DbConnUtil.beginTransaction();
//			DAOFactory.getDAO(DocRoleDAO.class).update(docRole);
//			DbConnUtil.commitTransaction();
//		}
//		catch(Exception e) {
//			DbConnUtil.rollbackTransaction();
//			throw e;
//		}
//		finally {
//			DbConnUtil.closeDbconn();
//		}
//	}

//	public void confirm(DocRole docRole) throws Exception {
//		DbConnUtil.buildDbconn(3);
//		try {
//			DbConnUtil.beginTransaction();
//			DAOFactory.getDAO(DocRoleDAO.class).update(docRole);
//			DbConnUtil.commitTransaction();
//		}
//		catch(Exception e) {
//			DbConnUtil.rollbackTransaction();
//			throw e;
//		}
//		finally {
//			DbConnUtil.closeDbconn();
//		}
//	}

	public void remove(String sql) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DocRoleDAO.class).delete(sql);
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

	public void remove(DocRole docRole) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DocRoleDAO.class).delete(new DocRoleHelper().getSql4Delete(docRole));
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

	public DocRole findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (DocRole)DAOFactory.getDAO(DocRoleDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public DocRole findById(DocRole docRole) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (DocRole)DAOFactory.getDAO(DocRoleDAO.class).load(docRole);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public DocRole findBy(DocRole docRole) throws Exception {
		String sql = SqlUtil.getSql4All(DocRoleHelper.class,docRole,DocRole.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (DocRole)DAOFactory.getDAO(DocRoleDAO.class).load(sql,DocRole.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<DocRole> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<DocRole>)DAOFactory.getDAO(DocRoleDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<DocRole> findAll(DocRole docRole) throws Exception {
		String sql = SqlUtil.getSql4All(DocRoleHelper.class,docRole,DocRole.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<DocRole>)DAOFactory.getDAO(DocRoleDAO.class).query(sql, DocRole.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<DocRole> find(DocRole docRole,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(DocRoleHelper.class,docRole,pageVO,DocRole.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<DocRole>)DAOFactory.getDAO(DocRoleDAO.class).query(sql, DocRole.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<DocRole> find(DocRole docRole) throws Exception {
		String sql = SqlUtil.getSql4All(DocRoleHelper.class,docRole,DocRole.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<DocRole>)DAOFactory.getDAO(DocRoleDAO.class).query(sql, DocRole.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(String sql) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(DocRoleDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(DocRole docRole) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(DocRoleDAO.class).amount(new DocRoleHelper().getSql4Amount(docRole));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
	
	/**删除旧的,添加新的*/
	public void edit(DocRole deleteDocRole,List<DocRole> docRoles) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(DocRoleDAO.class).delete(new DocRoleHelper().getSql4Delete(deleteDocRole));
			for(DocRole docRole:docRoles){
				DAOFactory.getDAO(DocRoleDAO.class).insert(docRole);
			}
			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}