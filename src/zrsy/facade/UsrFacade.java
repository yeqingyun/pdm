package zrsy.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrsy.dao.UsrDAO;
import zrsy.dao.helper.UsrHelper;
import zrsy.vo.Usr;

public class UsrFacade {

	public void save(Usr usr) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(UsrDAO.class).insert(usr);
			if(usr.getGpUsrs() != null && usr.getGpUsrs().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.GpUsrDAO.class).delete("delete from GpUsr where GpUsr.UsrId = " +usr.getId());
				for(int i=0; i<usr.getGpUsrs().size(); i++) {
					if(usr.getGpUsrs().get(i) != null && usr.getGpUsrs().get(i).getGpId() != null) {
						usr.getGpUsrs().get(i).setUsrId(usr.getId());
						DAOFactory.getDAO(zrsy.dao.GpUsrDAO.class).insert(usr.getGpUsrs().get(i));
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

	public void update(Usr usr) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(UsrDAO.class).update(usr);
			
			if(usr.getGpUsrs() != null && usr.getGpUsrs().size() > 0) {
				//DAOFactory.getDAO(zrsy.dao.GpUsrDAO.class).delete("delete from GpUsr where GpUsr.UsrId = " +usr.getId());
				System.out.println("delete GpUsr from GpUsr  inner join Gp on Gp.GpId=GpUsr.GpId inner join Usr on Usr.Id=GpUsr.UsrId where GpUsr.UsrId = " +usr.getId()+ " and Gp.SyId="+usr.getSyId());
				DAOFactory.getDAO(zrsy.dao.GpUsrDAO.class).delete("delete GpUsr from GpUsr  inner join Gp on Gp.GpId=GpUsr.GpId inner join Usr on Usr.Id=GpUsr.UsrId where GpUsr.UsrId = " +usr.getId()+ " and Gp.SyId="+usr.getSyId());
				for(int i=0; i<usr.getGpUsrs().size(); i++) {
					if(usr.getGpUsrs().get(i) != null && usr.getGpUsrs().get(i).getGpId() != null) {
						usr.getGpUsrs().get(i).setUsrId(usr.getId());
						DAOFactory.getDAO(zrsy.dao.GpUsrDAO.class).insert(usr.getGpUsrs().get(i));
					}
				}
			}
			if(usr.getUsrScos() != null && usr.getUsrScos().size() > 0) {
				//DAOFactory.getDAO(zrsy.dao.UsrScoDAO.class).delete("delete from UsrSco where UsrSco.UsrId = " +usr.getId());
				DAOFactory.getDAO(zrsy.dao.GpUsrDAO.class).delete("delete GpUsr from GpUsr  inner join Gp on Gp.GpId=GpUsr.GpId inner join Usr on Usr.Id=GpUsr.UsrId where GpUsr.UsrId = " +usr.getId()+ " and Gp.SyId="+usr.getSyId());
				for(int i=0; i<usr.getUsrScos().size(); i++) {
					if(usr.getUsrScos().get(i) != null && usr.getUsrScos().get(i).getScoId() != null) {
						usr.getUsrScos().get(i).setUsrId(usr.getId());
						DAOFactory.getDAO(zrsy.dao.UsrScoDAO.class).insert(usr.getUsrScos().get(i));
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
			DAOFactory.getDAO(UsrDAO.class).update(sql);
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

	public void submit(Usr usr) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(UsrDAO.class).update(usr);
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

	public void review(Usr usr) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(UsrDAO.class).update(usr);
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

	public void confirm(Usr usr) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(UsrDAO.class).update(usr);
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
			DAOFactory.getDAO(UsrDAO.class).delete(sql);
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

	public void remove(Usr usr) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(UsrDAO.class).delete(new UsrHelper().getSql4Delete(usr));
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

	public Usr findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (Usr)DAOFactory.getDAO(UsrDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public Usr findById(Usr usr) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (Usr)DAOFactory.getDAO(UsrDAO.class).load(usr);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public Usr findBy(Usr usr) throws Exception {
		String sql = SqlUtil.getSql4All(UsrHelper.class,usr,Usr.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (Usr)DAOFactory.getDAO(UsrDAO.class).load(sql,Usr.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Usr> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Usr>)DAOFactory.getDAO(UsrDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Usr> findAll(Usr usr) throws Exception {
		String sql = SqlUtil.getSql4All(UsrHelper.class,usr,Usr.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Usr>)DAOFactory.getDAO(UsrDAO.class).query(sql, Usr.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Usr> find(Usr usr,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(UsrHelper.class,usr,pageVO,Usr.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Usr>)DAOFactory.getDAO(UsrDAO.class).query(sql, Usr.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Usr> find(Usr usr) throws Exception {
		String sql = SqlUtil.getSql4All(UsrHelper.class,usr,Usr.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Usr>)DAOFactory.getDAO(UsrDAO.class).query(sql, Usr.LIST_FIELDS);
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
			return DAOFactory.getDAO(UsrDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(Usr usr) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return DAOFactory.getDAO(UsrDAO.class).amount(new UsrHelper().getSql4Amount(usr));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
	
	public Usr login(Usr usr) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			UsrDAO usrDAO = new UsrDAO();
			return (Usr)usrDAO.login(usr);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}