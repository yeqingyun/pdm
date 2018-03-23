package zrsy.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrsy.dao.ScoDAO;
import zrsy.dao.helper.ScoHelper;
import zrsy.vo.Sco;

public class ScoFacade {

	public void save(Sco sco) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ScoDAO.class).insert(sco);
			if(sco.getGpScos() != null && sco.getGpScos().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.GpScoDAO.class).delete("delete from GpSco where GpSco.ScoId = " +sco.getScoId());
				for(int i=0; i<sco.getGpScos().size(); i++) {
					if(sco.getGpScos().get(i) != null && sco.getGpScos().get(i).getScoId() != null) {
						sco.getGpScos().get(i).setScoId(sco.getScoId());
						DAOFactory.getDAO(zrsy.dao.GpScoDAO.class).insert(sco.getGpScos().get(i));
					}
				}
			}
			if(sco.getScoDtls() != null && sco.getScoDtls().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.ScoDtlDAO.class).delete("delete from ScoDtl where ScoDtl.ScoId = " +sco.getScoId());
				for(int i=0; i<sco.getScoDtls().size(); i++) {
					if(sco.getScoDtls().get(i) != null && sco.getScoDtls().get(i).getComId() != null&&sco.getScoDtls().get(i).getDetpId() != null) {
						sco.getScoDtls().get(i).setScoId(sco.getScoId());
						if(sco.getScoDtls().get(i).getUsrId()==null){
							sco.getScoDtls().get(i).setUsrId(-1);
						}
						DAOFactory.getDAO(zrsy.dao.ScoDtlDAO.class).insert(sco.getScoDtls().get(i));
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

	public void update(Sco sco) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ScoDAO.class).update(sco);
			if(sco.getGpScos() != null && sco.getGpScos().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.GpScoDAO.class).delete("delete from GpSco where GpSco.ScoId = " +sco.getScoId());
				for(int i=0; i<sco.getGpScos().size(); i++) {
					if(sco.getGpScos().get(i) != null && sco.getGpScos().get(i).getScoId() != null) {
						sco.getGpScos().get(i).setScoId(sco.getScoId());
						DAOFactory.getDAO(zrsy.dao.GpScoDAO.class).insert(sco.getGpScos().get(i));
					}
				}
			}
			
			if(sco.getScoDtls() != null && sco.getScoDtls().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.ScoDtlDAO.class).delete("delete from [ZrSy].[dbo].[ScoDtl] where ScoDtl.ScoId = " +sco.getScoId());
				for(int i=0; i<sco.getScoDtls().size(); i++) {
					if(sco.getScoDtls().get(i) != null && sco.getScoDtls().get(i).getComId() != null&&sco.getScoDtls().get(i).getDetpId() != null) {
						sco.getScoDtls().get(i).setScoId(sco.getScoId());
						if(sco.getScoDtls().get(i).getUsrId()==null){
							sco.getScoDtls().get(i).setUsrId(-1);
						}
						DAOFactory.getDAO(zrsy.dao.ScoDtlDAO.class).insert(sco.getScoDtls().get(i));
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
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ScoDAO.class).update(sql);
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

	public void submit(Sco sco) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ScoDAO.class).update(sco);
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

	public void review(Sco sco) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ScoDAO.class).update(sco);
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

	public void confirm(Sco sco) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ScoDAO.class).update(sco);
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
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ScoDAO.class).delete(sql);
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

	public void remove(Sco sco) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(ScoDAO.class).delete(new ScoHelper().getSql4Delete(sco));
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

	public Sco findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (Sco)DAOFactory.getDAO(ScoDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public Sco findById(Sco sco) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (Sco)DAOFactory.getDAO(ScoDAO.class).load(sco);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public Sco findBy(Sco sco) throws Exception {
		String sql = SqlUtil.getSql4All(ScoHelper.class,sco,Sco.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (Sco)DAOFactory.getDAO(ScoDAO.class).load(sql,Sco.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Sco> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Sco>)DAOFactory.getDAO(ScoDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Sco> findAll(Sco sco) throws Exception {
		String sql = SqlUtil.getSql4All(ScoHelper.class,sco,Sco.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Sco>)DAOFactory.getDAO(ScoDAO.class).query(sql, Sco.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Sco> find(Sco sco,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(ScoHelper.class,sco,pageVO,Sco.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Sco>)DAOFactory.getDAO(ScoDAO.class).query(sql, Sco.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Sco> find(Sco sco) throws Exception {
		String sql = SqlUtil.getSql4All(ScoHelper.class,sco,Sco.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Sco>)DAOFactory.getDAO(ScoDAO.class).query(sql, Sco.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(String sql) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return DAOFactory.getDAO(ScoDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(Sco sco) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return DAOFactory.getDAO(ScoDAO.class).amount(new ScoHelper().getSql4Amount(sco));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}