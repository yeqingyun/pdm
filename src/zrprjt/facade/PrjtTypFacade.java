package zrprjt.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrprjt.dao.PrjtTypDAO;
import zrprjt.dao.helper.PrjtTypHelper;
import zrprjt.vo.PrjtTyp;

public class PrjtTypFacade {

	public void save(PrjtTyp prjtTyp) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtTypDAO.class).insert(prjtTyp);
			if(prjtTyp.getPrjtDefs() != null && prjtTyp.getPrjtDefs().size() > 0) {
				DAOFactory.getDAO(zrprjt.dao.PrjtDefDAO.class).delete("delete from PrjtDef where PrjtDef.TypId = " +prjtTyp.getTypId());
				for(int i=0; i<prjtTyp.getPrjtDefs().size(); i++) {
					if(prjtTyp.getPrjtDefs().get(i) != null && prjtTyp.getPrjtDefs().get(i).getTypId() != null) {
						prjtTyp.getPrjtDefs().get(i).setTypId(prjtTyp.getTypId());
						DAOFactory.getDAO(zrprjt.dao.PrjtDefDAO.class).insert(prjtTyp.getPrjtDefs().get(i));
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

	public void update(PrjtTyp prjtTyp) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtTypDAO.class).update(prjtTyp);
			if(prjtTyp.getPrjtDefs() != null && prjtTyp.getPrjtDefs().size() > 0) {
				DAOFactory.getDAO(zrprjt.dao.PrjtDefDAO.class).delete("delete from PrjtDef where PrjtDef.TypId = " +prjtTyp.getTypId());
				for(int i=0; i<prjtTyp.getPrjtDefs().size(); i++) {
					if(prjtTyp.getPrjtDefs().get(i) != null && prjtTyp.getPrjtDefs().get(i).getTypId() != null) {
						prjtTyp.getPrjtDefs().get(i).setTypId(prjtTyp.getTypId());
						DAOFactory.getDAO(zrprjt.dao.PrjtDefDAO.class).insert(prjtTyp.getPrjtDefs().get(i));
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
			DAOFactory.getDAO(PrjtTypDAO.class).update(sql);
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

	public void submit(PrjtTyp prjtTyp) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtTypDAO.class).update(prjtTyp);
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

	public void review(PrjtTyp prjtTyp) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtTypDAO.class).update(prjtTyp);
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

	public void confirm(PrjtTyp prjtTyp) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtTypDAO.class).update(prjtTyp);
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
			DAOFactory.getDAO(PrjtTypDAO.class).delete(sql);
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

	public void remove(PrjtTyp prjtTyp) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtTypDAO.class).delete(new PrjtTypHelper().getSql4Delete(prjtTyp));
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

	public PrjtTyp findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (PrjtTyp)DAOFactory.getDAO(PrjtTypDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public PrjtTyp findById(PrjtTyp prjtTyp) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (PrjtTyp)DAOFactory.getDAO(PrjtTypDAO.class).load(prjtTyp);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public PrjtTyp findBy(PrjtTyp prjtTyp) throws Exception {
		String sql = SqlUtil.getSql4All(PrjtTypHelper.class,prjtTyp,PrjtTyp.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (PrjtTyp)DAOFactory.getDAO(PrjtTypDAO.class).load(sql,PrjtTyp.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PrjtTyp> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (List<PrjtTyp>)DAOFactory.getDAO(PrjtTypDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PrjtTyp> findAll(PrjtTyp prjtTyp) throws Exception {
		String sql = SqlUtil.getSql4All(PrjtTypHelper.class,prjtTyp,PrjtTyp.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<PrjtTyp>)DAOFactory.getDAO(PrjtTypDAO.class).query(sql, PrjtTyp.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PrjtTyp> find(PrjtTyp prjtTyp,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(PrjtTypHelper.class,prjtTyp,pageVO,PrjtTyp.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<PrjtTyp>)DAOFactory.getDAO(PrjtTypDAO.class).query(sql, PrjtTyp.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PrjtTyp> find(PrjtTyp prjtTyp) throws Exception {
		String sql = SqlUtil.getSql4All(PrjtTypHelper.class,prjtTyp,PrjtTyp.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<PrjtTyp>)DAOFactory.getDAO(PrjtTypDAO.class).query(sql, PrjtTyp.LIST_FIELDS);
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
			return DAOFactory.getDAO(PrjtTypDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(PrjtTyp prjtTyp) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return DAOFactory.getDAO(PrjtTypDAO.class).amount(new PrjtTypHelper().getSql4Amount(prjtTyp));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}