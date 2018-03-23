package gnwf.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnwf.dao.WfFieldDAO;
import gnwf.dao.helper.WfFieldHelper;
import gnwf.vo.WfField;
import gnwf.ww.workflow.WFUtil;

public class WfFieldFacade {

	public void save(WfField wfField) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfFieldDAO.class).insert(wfField);
//			if(wfField.getWfRdFields() != null && wfField.getWfRdFields().size() > 0) {
//				DAOFactory.getDAO(gnwf.dao.WfRdFieldDAO.class).delete("delete from WfRdField where WfRdField.FieldId = " +wfField.getFieldId());
//				for(int i=0; i<wfField.getWfRdFields().size(); i++) {
//					if(wfField.getWfRdFields().get(i) != null && wfField.getWfRdFields().get(i).getFieldId() != null) {
//						wfField.getWfRdFields().get(i).setFieldId(wfField.getFieldId());
//						DAOFactory.getDAO(gnwf.dao.WfRdFieldDAO.class).insert(wfField.getWfRdFields().get(i));
//					}
//				}
//			}

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

	public void update(WfField wfField) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfFieldDAO.class).update(wfField);
			
//			if(wfField.getWfRdFields() != null && wfField.getWfRdFields().size() > 0) {
//				DAOFactory.getDAO(gnwf.dao.WfRdFieldDAO.class).delete("delete from WfRdField where WfRdField.FieldId = " +wfField.getFieldId());
//				for(int i=0; i<wfField.getWfRdFields().size(); i++) {
//					if(wfField.getWfRdFields().get(i) != null && wfField.getWfRdFields().get(i).getFieldId() != null) {
//						wfField.getWfRdFields().get(i).setFieldId(wfField.getFieldId());
//						DAOFactory.getDAO(gnwf.dao.WfRdFieldDAO.class).insert(wfField.getWfRdFields().get(i));
//					}
//				}
//			}
			
			DAOFactory.getDAO(gnwf.dao.WfFieldStepRelateDAO.class).delete("delete from WfFieldStepRelate where FieldId = " +wfField.getFieldId());
			if(WFUtil.isNotNull(wfField.getRelates())) {
				for(int i=0; i<wfField.getRelates().size(); i++) {
					if(wfField.getRelates().get(i) != null && wfField.getRelates().get(i).getStepId() != null) {
						wfField.getRelates().get(i).setFieldId(wfField.getFieldId());
						DAOFactory.getDAO(gnwf.dao.WfFieldStepRelateDAO.class).insert(wfField.getRelates().get(i));
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
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfFieldDAO.class).update(sql);
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

	public void submit(WfField wfField) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfFieldDAO.class).update(wfField);
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

	public void review(WfField wfField) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfFieldDAO.class).update(wfField);
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

	public void confirm(WfField wfField) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfFieldDAO.class).update(wfField);
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
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfFieldDAO.class).delete(sql);
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

	public void remove(WfField wfField) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfFieldDAO.class).delete(new WfFieldHelper().getSql4Delete(wfField));
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

	public WfField findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfField)DAOFactory.getDAO(WfFieldDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfField findById(WfField wfField) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfField)DAOFactory.getDAO(WfFieldDAO.class).load(wfField);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfField findBy(WfField wfField) throws Exception {
		String sql = SqlUtil.getSql4All(WfFieldHelper.class,wfField,WfField.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfField)DAOFactory.getDAO(WfFieldDAO.class).load(sql,WfField.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfField> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfField>)DAOFactory.getDAO(WfFieldDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfField> findAll(WfField wfField) throws Exception {
		String sql = SqlUtil.getSql4All(WfFieldHelper.class,wfField,WfField.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfField>)DAOFactory.getDAO(WfFieldDAO.class).query(sql, WfField.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfField> find(WfField wfField,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfFieldHelper.class,wfField,pageVO,WfField.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfField>)DAOFactory.getDAO(WfFieldDAO.class).query(sql, WfField.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfField> find(WfField wfField) throws Exception {
		String sql = SqlUtil.getSql4All(WfFieldHelper.class,wfField,WfField.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfField>)DAOFactory.getDAO(WfFieldDAO.class).query(sql, WfField.LIST_FIELDS);
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
			return DAOFactory.getDAO(WfFieldDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfField wfField) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfFieldDAO.class).amount(new WfFieldHelper().getSql4Amount(wfField));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}