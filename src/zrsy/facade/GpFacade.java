package zrsy.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrsy.dao.GpDAO;
import zrsy.dao.helper.GpHelper;
import zrsy.vo.Gp;

public class GpFacade {

	public void save(Gp gp) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpDAO.class).insert(gp);

			if(gp.getGpSys() != null && gp.getGpSys().size() > 0) {
				
				for(int i=0; i<gp.getGpSys().size(); i++) {
					if(gp.getGpSys().get(i) != null && gp.getGpSys().get(i).getSyId() != null) {
						DAOFactory.getDAO(zrsy.dao.GpSyDAO.class).delete("delete GpSy from GpSy where GpSy.GpId = " +gp.getGpId() +" and GpSy.SyId = " +gp.getGpSys().get(i).getSyId());
						
						gp.getGpSys().get(i).setGpId(gp.getGpId());
						DAOFactory.getDAO(zrsy.dao.GpSyDAO.class).insert(gp.getGpSys().get(i));
					}
				}
			}
			if(gp.getGpBtns() != null && gp.getGpBtns().size() > 0) {

				DAOFactory.getDAO(zrsy.dao.GpBtnDAO.class).delete("delete GpBtn from GpBtn  " +
						" inner join Btn on(GpBtn.BtnId = Btn.Id) " +
						" inner join GpSy on(GpSy.GpId = GpBtn.GpId and Btn.SyId = GpSy.SyId) " +
						" where GpSy.SyId = " + gp.getSyId() +
						" 	and GpBtn.GpId = " +gp.getGpId());

				for(int i=0; i<gp.getGpBtns().size(); i++) {
					if(gp.getGpBtns().get(i) != null && gp.getGpBtns().get(i).getNodeBtnId() != null) {
						String[] ids =gp.getGpBtns().get(i) .getNodeBtnId().split(",");

						gp.getGpBtns().get(i).setGpId(gp.getGpId());
						gp.getGpBtns().get(i).setNodeId(Integer.valueOf(ids[0]));
						gp.getGpBtns().get(i).setBtnId(Integer.valueOf(ids[1]));
						DAOFactory.getDAO(zrsy.dao.GpBtnDAO.class).insert(gp.getGpBtns().get(i));
					}
				}
			}
			if(gp.getGpMenus() != null && gp.getGpMenus().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.GpMenuDAO.class).delete("delete GpMenu from GpMenu " +
						" inner join Menu on(GpMenu.MenuId = Menu.Id) " +
						" where Menu.SyId = " + gp.getSyId() +
						" 	and GpMenu.GpId = " +gp.getGpId());

				for(int i=0; i<gp.getGpMenus().size(); i++) {
					if(gp.getGpMenus().get(i) != null && gp.getGpMenus().get(i).getMenuId() != null) {
						gp.getGpMenus().get(i).setGpId(gp.getGpId());
						
						if(!gp.getGpMenus().get(i).getMenuId().equals(1)) {
							DAOFactory.getDAO(zrsy.dao.GpMenuDAO.class).insert(gp.getGpMenus().get(i));	
						}
						else {
							int amount = DAOFactory.getDAO(zrsy.dao.GpMenuDAO.class).amount("select count(*) amount from GpMenu " +
									" where GpMenu.MenuId =  " +gp.getGpMenus().get(i).getMenuId()+
									"	and GpMenu.GpId = "+gp.getGpId());

							if(amount < 1)
								DAOFactory.getDAO(zrsy.dao.GpMenuDAO.class).insert(gp.getGpMenus().get(i));	
						}
							
					}
				}
			}
			if(gp.getGpNodes() != null && gp.getGpNodes().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.GpNodeDAO.class).delete("delete GpNode from GpNode " +
						" inner join Node on(GpNode.NodeId = Node.Id) " +
						" where Node.SyId =  " + gp.getSyId() +
						"	and GpNode.GpId = " +gp.getGpId());
				
				for(int i=0; i<gp.getGpNodes().size(); i++) {
					if(gp.getGpNodes().get(i) != null && gp.getGpNodes().get(i).getNodeId() != null) {
						gp.getGpNodes().get(i).setGpId(gp.getGpId());
						DAOFactory.getDAO(zrsy.dao.GpNodeDAO.class).insert(gp.getGpNodes().get(i));
					}
				}
			}
			if(gp.getGpUsrs() != null && gp.getGpUsrs().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.GpUsrDAO.class).delete("delete GpUsr from GpUsr where GpUsr.GpId = " +gp.getGpId());
				for(int i=0; i<gp.getGpUsrs().size(); i++) {
					if(gp.getGpUsrs().get(i) != null && gp.getGpUsrs().get(i).getUsrId() != null) {
						gp.getGpUsrs().get(i).setGpId(gp.getGpId());
						DAOFactory.getDAO(zrsy.dao.GpUsrDAO.class).insert(gp.getGpUsrs().get(i));
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

	public void update(Gp gp) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpDAO.class).update(gp);
			
			if(gp.getGpSys() != null && gp.getGpSys().size() > 0) {
				
				for(int i=0; i<gp.getGpSys().size(); i++) {
					if(gp.getGpSys().get(i) != null && gp.getGpSys().get(i).getSyId() != null) {
						DAOFactory.getDAO(zrsy.dao.GpSyDAO.class).delete("delete GpSy from GpSy where GpSy.GpId = " +gp.getGpId() +" and GpSy.SyId = " +gp.getGpSys().get(i).getSyId());
						
						gp.getGpSys().get(i).setGpId(gp.getGpId());
						DAOFactory.getDAO(zrsy.dao.GpSyDAO.class).insert(gp.getGpSys().get(i));
					}
				}
			}
			if(gp.getGpBtns() != null && gp.getGpBtns().size() > 0) {

				DAOFactory.getDAO(zrsy.dao.GpBtnDAO.class).delete("delete GpBtn from GpBtn  " +
						" inner join Btn on(GpBtn.BtnId = Btn.Id) " +
						" inner join GpSy on(GpSy.GpId = GpBtn.GpId and Btn.SyId = GpSy.SyId) " +
						" where GpSy.SyId = " + gp.getSyId() +
						" 	and GpBtn.GpId = " +gp.getGpId());

				for(int i=0; i<gp.getGpBtns().size(); i++) {
					if(gp.getGpBtns().get(i) != null && gp.getGpBtns().get(i).getNodeBtnId() != null) {
						String[] ids =gp.getGpBtns().get(i) .getNodeBtnId().split(",");

						gp.getGpBtns().get(i).setGpId(gp.getGpId());
						gp.getGpBtns().get(i).setNodeId(Integer.valueOf(ids[0]));
						gp.getGpBtns().get(i).setBtnId(Integer.valueOf(ids[1]));
						DAOFactory.getDAO(zrsy.dao.GpBtnDAO.class).insert(gp.getGpBtns().get(i));
					}
				}
			}
			if(gp.getGpMenus() != null && gp.getGpMenus().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.GpMenuDAO.class).delete("delete GpMenu from GpMenu " +
						" inner join Menu on(GpMenu.MenuId = Menu.Id) " +
						" where Menu.SyId = " + gp.getSyId() +
						" 	and GpMenu.GpId = " +gp.getGpId());

				for(int i=0; i<gp.getGpMenus().size(); i++) {
					if(gp.getGpMenus().get(i) != null && gp.getGpMenus().get(i).getMenuId() != null) {
						gp.getGpMenus().get(i).setGpId(gp.getGpId());
						
						if(!gp.getGpMenus().get(i).getMenuId().equals(1)) {
							DAOFactory.getDAO(zrsy.dao.GpMenuDAO.class).insert(gp.getGpMenus().get(i));	
						}
						else {
							int amount = DAOFactory.getDAO(zrsy.dao.GpMenuDAO.class).amount("select count(*) amount from GpMenu " +
									" where GpMenu.MenuId =  " +gp.getGpMenus().get(i).getMenuId()+
									"	and GpMenu.GpId = "+gp.getGpId());

							if(amount < 1)
								DAOFactory.getDAO(zrsy.dao.GpMenuDAO.class).insert(gp.getGpMenus().get(i));	
						}
					}
				}
			}
			if(gp.getGpNodes() != null && gp.getGpNodes().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.GpNodeDAO.class).delete("delete GpNode from GpNode " +
						" inner join Node on(GpNode.NodeId = Node.Id) " +
						" where Node.SyId =  " + gp.getSyId() +
						"	and GpNode.GpId = " +gp.getGpId());
				
				for(int i=0; i<gp.getGpNodes().size(); i++) {
					if(gp.getGpNodes().get(i) != null && gp.getGpNodes().get(i).getNodeId() != null) {
						gp.getGpNodes().get(i).setGpId(gp.getGpId());
						DAOFactory.getDAO(zrsy.dao.GpNodeDAO.class).insert(gp.getGpNodes().get(i));
					}
				}
			}
			if(gp.getGpUsrs() != null && gp.getGpUsrs().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.GpUsrDAO.class).delete("delete GpUsr from GpUsr where GpUsr.GpId = " +gp.getGpId());
				for(int i=0; i<gp.getGpUsrs().size(); i++) {
					if(gp.getGpUsrs().get(i) != null && gp.getGpUsrs().get(i).getUsrId() != null) {
						gp.getGpUsrs().get(i).setGpId(gp.getGpId());
						DAOFactory.getDAO(zrsy.dao.GpUsrDAO.class).insert(gp.getGpUsrs().get(i));
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
			DAOFactory.getDAO(GpDAO.class).update(sql);
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

	public void submit(Gp gp) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpDAO.class).update(gp);
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

	public void review(Gp gp) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpDAO.class).update(gp);
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

	public void confirm(Gp gp) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpDAO.class).update(gp);
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
			DAOFactory.getDAO(GpDAO.class).delete(sql);
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

	public void remove(Gp gp) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(GpDAO.class).delete(new GpHelper().getSql4Delete(gp));
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

	public Gp findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (Gp)DAOFactory.getDAO(GpDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public Gp findById(Gp gp) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (Gp)DAOFactory.getDAO(GpDAO.class).load(gp);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public Gp findBy(Gp gp) throws Exception {
		String sql = SqlUtil.getSql4All(GpHelper.class,gp,Gp.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (Gp)DAOFactory.getDAO(GpDAO.class).load(sql,Gp.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Gp> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Gp>)DAOFactory.getDAO(GpDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Gp> findAll(Gp gp) throws Exception {
		String sql = SqlUtil.getSql4All(GpHelper.class,gp,Gp.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Gp>)DAOFactory.getDAO(GpDAO.class).query(sql, Gp.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Gp> find(Gp gp,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(GpHelper.class,gp,pageVO,Gp.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Gp>)DAOFactory.getDAO(GpDAO.class).query(sql, Gp.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Gp> find(Gp gp) throws Exception {
		String sql = SqlUtil.getSql4All(GpHelper.class,gp,Gp.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Gp>)DAOFactory.getDAO(GpDAO.class).query(sql, Gp.LIST_FIELDS);
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
			return DAOFactory.getDAO(GpDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(Gp gp) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return DAOFactory.getDAO(GpDAO.class).amount(new GpHelper().getSql4Amount(gp));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}