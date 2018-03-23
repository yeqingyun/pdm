package zrsy.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrsy.dao.SyDefDAO;
import zrsy.dao.helper.SyDefHelper;
import zrsy.vo.SyDef;

public class SyDefFacade {

	public void save(SyDef syDef) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SyDefDAO.class).insert(syDef);
			if(syDef.getGps() != null && syDef.getGps().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.GpDAO.class).delete("delete from Gp where Gp.SyId = " +syDef.getSyId());
				for(int i=0; i<syDef.getGps().size(); i++) {
					if(syDef.getGps().get(i) != null && syDef.getGps().get(i).getSyId() != null) {
						syDef.getGps().get(i).setSyId(syDef.getSyId());
						DAOFactory.getDAO(zrsy.dao.GpDAO.class).insert(syDef.getGps().get(i));
					}
				}
			}
			if(syDef.getMenus() != null && syDef.getMenus().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.MenuDAO.class).delete("delete from Menu where Menu.SyId = " +syDef.getSyId());
				for(int i=0; i<syDef.getMenus().size(); i++) {
					if(syDef.getMenus().get(i) != null && syDef.getMenus().get(i).getSyId() != null) {
						syDef.getMenus().get(i).setSyId(syDef.getSyId());
						DAOFactory.getDAO(zrsy.dao.MenuDAO.class).insert(syDef.getMenus().get(i));
					}
				}
			}
			if(syDef.getNodes() != null && syDef.getNodes().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.NodeDAO.class).delete("delete from Node where Node.SyId = " +syDef.getSyId());
				for(int i=0; i<syDef.getNodes().size(); i++) {
					if(syDef.getNodes().get(i) != null && syDef.getNodes().get(i).getSyId() != null) {
						syDef.getNodes().get(i).setSyId(syDef.getSyId());
						DAOFactory.getDAO(zrsy.dao.NodeDAO.class).insert(syDef.getNodes().get(i));
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

	public void update(SyDef syDef) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SyDefDAO.class).update(syDef);
			if(syDef.getGps() != null && syDef.getGps().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.GpDAO.class).delete("delete from Gp where Gp.SyId = " +syDef.getSyId());
				for(int i=0; i<syDef.getGps().size(); i++) {
					if(syDef.getGps().get(i) != null && syDef.getGps().get(i).getSyId() != null) {
						syDef.getGps().get(i).setSyId(syDef.getSyId());
						DAOFactory.getDAO(zrsy.dao.GpDAO.class).insert(syDef.getGps().get(i));
					}
				}
			}
			if(syDef.getMenus() != null && syDef.getMenus().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.MenuDAO.class).delete("delete from Menu where Menu.SyId = " +syDef.getSyId());
				for(int i=0; i<syDef.getMenus().size(); i++) {
					if(syDef.getMenus().get(i) != null && syDef.getMenus().get(i).getSyId() != null) {
						syDef.getMenus().get(i).setSyId(syDef.getSyId());
						DAOFactory.getDAO(zrsy.dao.MenuDAO.class).insert(syDef.getMenus().get(i));
					}
				}
			}
			if(syDef.getNodes() != null && syDef.getNodes().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.NodeDAO.class).delete("delete from Node where Node.SyId = " +syDef.getSyId());
				for(int i=0; i<syDef.getNodes().size(); i++) {
					if(syDef.getNodes().get(i) != null && syDef.getNodes().get(i).getSyId() != null) {
						syDef.getNodes().get(i).setSyId(syDef.getSyId());
						DAOFactory.getDAO(zrsy.dao.NodeDAO.class).insert(syDef.getNodes().get(i));
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
			DAOFactory.getDAO(SyDefDAO.class).update(sql);
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

	public void submit(SyDef syDef) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SyDefDAO.class).update(syDef);
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

	public void review(SyDef syDef) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SyDefDAO.class).update(syDef);
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

	public void confirm(SyDef syDef) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SyDefDAO.class).update(syDef);
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
			DAOFactory.getDAO(SyDefDAO.class).delete(sql);
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

	public void remove(SyDef syDef) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SyDefDAO.class).delete(new SyDefHelper().getSql4Delete(syDef));
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

	public SyDef findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (SyDef)DAOFactory.getDAO(SyDefDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public SyDef findById(SyDef syDef) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (SyDef)DAOFactory.getDAO(SyDefDAO.class).load(syDef);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public SyDef findBy(SyDef syDef) throws Exception {
		String sql = SqlUtil.getSql4All(SyDefHelper.class,syDef,SyDef.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (SyDef)DAOFactory.getDAO(SyDefDAO.class).load(sql,SyDef.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<SyDef> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (List<SyDef>)DAOFactory.getDAO(SyDefDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<SyDef> findAll(SyDef syDef) throws Exception {
		String sql = SqlUtil.getSql4All(SyDefHelper.class,syDef,SyDef.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<SyDef>)DAOFactory.getDAO(SyDefDAO.class).query(sql, SyDef.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<SyDef> find(SyDef syDef,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(SyDefHelper.class,syDef,pageVO,SyDef.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<SyDef>)DAOFactory.getDAO(SyDefDAO.class).query(sql, SyDef.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<SyDef> find(SyDef syDef) throws Exception {
		String sql = SqlUtil.getSql4All(SyDefHelper.class,syDef,SyDef.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<SyDef>)DAOFactory.getDAO(SyDefDAO.class).query(sql, SyDef.LIST_FIELDS);
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
			return DAOFactory.getDAO(SyDefDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(SyDef syDef) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return DAOFactory.getDAO(SyDefDAO.class).amount(new SyDefHelper().getSql4Amount(syDef));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}