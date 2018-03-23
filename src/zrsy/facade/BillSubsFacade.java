package zrsy.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrsy.dao.BillSubsDAO;
import zrsy.dao.helper.BillSubsHelper;
import zrsy.vo.BillSubs;

public class BillSubsFacade {

	public void save(BillSubs billSubs) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(BillSubsDAO.class).insert(billSubs);
			if(billSubs.getBillTyps() != null && billSubs.getBillTyps().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.BillTypDAO.class).delete("delete from BillTyp where BillTyp.SubsId = " +billSubs.getId());
				for(int i=0; i<billSubs.getBillTyps().size(); i++) {
					if(billSubs.getBillTyps().get(i) != null && billSubs.getBillTyps().get(i).getSubsId() != null) {
						billSubs.getBillTyps().get(i).setSubsId(billSubs.getId());
						DAOFactory.getDAO(zrsy.dao.BillTypDAO.class).insert(billSubs.getBillTyps().get(i));
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

	public void update(BillSubs billSubs) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(BillSubsDAO.class).update(billSubs);
			if(billSubs.getBillTyps() != null && billSubs.getBillTyps().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.BillTypDAO.class).delete("delete from BillTyp where BillTyp.SubsId = " +billSubs.getId());
				for(int i=0; i<billSubs.getBillTyps().size(); i++) {
					if(billSubs.getBillTyps().get(i) != null && billSubs.getBillTyps().get(i).getSubsId() != null) {
						billSubs.getBillTyps().get(i).setSubsId(billSubs.getId());
						DAOFactory.getDAO(zrsy.dao.BillTypDAO.class).insert(billSubs.getBillTyps().get(i));
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
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(BillSubsDAO.class).update(sql);
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

	public void submit(BillSubs billSubs) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(BillSubsDAO.class).update(billSubs);
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

	public void review(BillSubs billSubs) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(BillSubsDAO.class).update(billSubs);
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

	public void confirm(BillSubs billSubs) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(BillSubsDAO.class).update(billSubs);
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
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(BillSubsDAO.class).delete(sql);
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

	public void remove(BillSubs billSubs) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(BillSubsDAO.class).delete(new BillSubsHelper().getSql4Delete(billSubs));
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

	public BillSubs findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (BillSubs)DAOFactory.getDAO(BillSubsDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public BillSubs findById(BillSubs billSubs) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (BillSubs)DAOFactory.getDAO(BillSubsDAO.class).load(billSubs);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public BillSubs findBy(BillSubs billSubs) throws Exception {
		String sql = SqlUtil.getSql4All(BillSubsHelper.class,billSubs,BillSubs.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (BillSubs)DAOFactory.getDAO(BillSubsDAO.class).load(sql,BillSubs.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<BillSubs> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (List<BillSubs>)DAOFactory.getDAO(BillSubsDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<BillSubs> findAll(BillSubs billSubs) throws Exception {
		String sql = SqlUtil.getSql4All(BillSubsHelper.class,billSubs,BillSubs.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<BillSubs>)DAOFactory.getDAO(BillSubsDAO.class).query(sql, BillSubs.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<BillSubs> find(BillSubs billSubs,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(BillSubsHelper.class,billSubs,pageVO,BillSubs.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<BillSubs>)DAOFactory.getDAO(BillSubsDAO.class).query(sql, BillSubs.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<BillSubs> find(BillSubs billSubs) throws Exception {
		String sql = SqlUtil.getSql4All(BillSubsHelper.class,billSubs,BillSubs.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<BillSubs>)DAOFactory.getDAO(BillSubsDAO.class).query(sql, BillSubs.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(String sql) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return DAOFactory.getDAO(BillSubsDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(BillSubs billSubs) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return DAOFactory.getDAO(BillSubsDAO.class).amount(new BillSubsHelper().getSql4Amount(billSubs));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}