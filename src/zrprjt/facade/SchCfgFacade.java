package zrprjt.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrprjt.dao.SchCfgDAO;
import zrprjt.dao.helper.SchCfgHelper;
import zrprjt.vo.SchCfg;

public class SchCfgFacade {

	public void save(SchCfg schCfg) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SchCfgDAO.class).insert(schCfg);
			if(schCfg.getSchDtls() != null && schCfg.getSchDtls().size() > 0) {
				DAOFactory.getDAO(zrprjt.dao.SchDtlDAO.class).delete("delete from SchDtl where SchDtl.SchId = " +schCfg.getSchId());
				for(int i=0; i<schCfg.getSchDtls().size(); i++) {
					if(schCfg.getSchDtls().get(i) != null && schCfg.getSchDtls().get(i).getSchId() != null) {
						schCfg.getSchDtls().get(i).setSchId(schCfg.getSchId());
						DAOFactory.getDAO(zrprjt.dao.SchDtlDAO.class).insert(schCfg.getSchDtls().get(i));
					}
				}
			}
			if(schCfg.getSchWfs() != null && schCfg.getSchWfs().size() > 0) {
				DAOFactory.getDAO(zrprjt.dao.SchWfDAO.class).delete("delete from SchWf where SchWf.SchId = " +schCfg.getSchId());
				for(int i=0; i<schCfg.getSchWfs().size(); i++) {
					if(schCfg.getSchWfs().get(i) != null && schCfg.getSchWfs().get(i).getSchId() != null) {
						schCfg.getSchWfs().get(i).setSchId(schCfg.getSchId());
						DAOFactory.getDAO(zrprjt.dao.SchWfDAO.class).insert(schCfg.getSchWfs().get(i));
					}
				}
			}
			if(schCfg.getTasks() != null && schCfg.getTasks().size() > 0) {
				DAOFactory.getDAO(zrprjt.dao.TaskDAO.class).delete("delete from Task where Task.SchId = " +schCfg.getSchId());
				for(int i=0; i<schCfg.getTasks().size(); i++) {
					if(schCfg.getTasks().get(i) != null && schCfg.getTasks().get(i).getSchId() != null) {
						schCfg.getTasks().get(i).setSchId(schCfg.getSchId());
						DAOFactory.getDAO(zrprjt.dao.TaskDAO.class).insert(schCfg.getTasks().get(i));
					}
				}
			}
			
			if(schCfg.getSchWf()!=null){
				DAOFactory.getDAO(zrprjt.dao.SchWfDAO.class).delete("delete from SchWf where SchWf.SchId = " +schCfg.getSchId());
				DAOFactory.getDAO(zrprjt.dao.SchWfDAO.class).insert(schCfg.getSchWf());
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

	public void update(SchCfg schCfg) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SchCfgDAO.class).update(schCfg);
			if(schCfg.getSchDtls() != null && schCfg.getSchDtls().size() > 0) {
				DAOFactory.getDAO(zrprjt.dao.SchDtlDAO.class).delete("delete from SchDtl where SchDtl.SchId = " +schCfg.getSchId());
				for(int i=0; i<schCfg.getSchDtls().size(); i++) {
					if(schCfg.getSchDtls().get(i) != null && schCfg.getSchDtls().get(i).getSchId() != null) {
						schCfg.getSchDtls().get(i).setSchId(schCfg.getSchId());
						DAOFactory.getDAO(zrprjt.dao.SchDtlDAO.class).insert(schCfg.getSchDtls().get(i));
					}
				}
			}
			if(schCfg.getSchWfs() != null && schCfg.getSchWfs().size() > 0) {
				DAOFactory.getDAO(zrprjt.dao.SchWfDAO.class).delete("delete from SchWf where SchWf.SchId = " +schCfg.getSchId());
				for(int i=0; i<schCfg.getSchWfs().size(); i++) {
					if(schCfg.getSchWfs().get(i) != null && schCfg.getSchWfs().get(i).getSchId() != null) {
						schCfg.getSchWfs().get(i).setSchId(schCfg.getSchId());
						DAOFactory.getDAO(zrprjt.dao.SchWfDAO.class).insert(schCfg.getSchWfs().get(i));
					}
				}
			}
			if(schCfg.getTasks() != null && schCfg.getTasks().size() > 0) {
				DAOFactory.getDAO(zrprjt.dao.TaskDAO.class).delete("delete from Task where Task.SchId = " +schCfg.getSchId());
				for(int i=0; i<schCfg.getTasks().size(); i++) {
					if(schCfg.getTasks().get(i) != null && schCfg.getTasks().get(i).getSchId() != null) {
						schCfg.getTasks().get(i).setSchId(schCfg.getSchId());
						DAOFactory.getDAO(zrprjt.dao.TaskDAO.class).insert(schCfg.getTasks().get(i));
					}
				}
			}
			
			if(schCfg.getSchWf()!=null){
				DAOFactory.getDAO(zrprjt.dao.SchWfDAO.class).delete("delete from SchWf where SchWf.SchId = " +schCfg.getSchId());
				DAOFactory.getDAO(zrprjt.dao.SchWfDAO.class).insert(schCfg.getSchWf());
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
			DAOFactory.getDAO(SchCfgDAO.class).update(sql);
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

	public void submit(SchCfg schCfg) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SchCfgDAO.class).update(schCfg);
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

	public void review(SchCfg schCfg) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SchCfgDAO.class).update(schCfg);
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

	public void confirm(SchCfg schCfg) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SchCfgDAO.class).update(schCfg);
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
			DAOFactory.getDAO(SchCfgDAO.class).delete(sql);
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

	public void remove(SchCfg schCfg) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(zrprjt.dao.SchWfDAO.class).delete("delete from SchWf where SchWf.SchId = " +schCfg.getSchId());
			DAOFactory.getDAO(SchCfgDAO.class).delete(new SchCfgHelper().getSql4Delete(schCfg));
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

	public SchCfg findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (SchCfg)DAOFactory.getDAO(SchCfgDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public SchCfg findById(SchCfg schCfg) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (SchCfg)DAOFactory.getDAO(SchCfgDAO.class).load(schCfg);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public SchCfg findBy(SchCfg schCfg) throws Exception {
		String sql = SqlUtil.getSql4All(SchCfgHelper.class,schCfg,SchCfg.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (SchCfg)DAOFactory.getDAO(SchCfgDAO.class).load(sql,SchCfg.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<SchCfg> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (List<SchCfg>)DAOFactory.getDAO(SchCfgDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<SchCfg> findAll(SchCfg schCfg) throws Exception {
		String sql = SqlUtil.getSql4All(SchCfgHelper.class,schCfg,SchCfg.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<SchCfg>)DAOFactory.getDAO(SchCfgDAO.class).query(sql, SchCfg.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<SchCfg> find(SchCfg schCfg,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(SchCfgHelper.class,schCfg,pageVO,SchCfg.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<SchCfg>)DAOFactory.getDAO(SchCfgDAO.class).query(sql, SchCfg.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<SchCfg> find(SchCfg schCfg) throws Exception {
		String sql = SqlUtil.getSql4All(SchCfgHelper.class,schCfg,SchCfg.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<SchCfg>)DAOFactory.getDAO(SchCfgDAO.class).query(sql, SchCfg.LIST_FIELDS);
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
			return DAOFactory.getDAO(SchCfgDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(SchCfg schCfg) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return DAOFactory.getDAO(SchCfgDAO.class).amount(new SchCfgHelper().getSql4Amount(schCfg));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}