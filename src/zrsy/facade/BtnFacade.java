package zrsy.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrsy.dao.BtnDAO;
import zrsy.dao.helper.BtnHelper;
import zrsy.vo.Btn;

public class BtnFacade {

	public void save(Btn btn) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(BtnDAO.class).insert(btn);

			if(btn.getGpBtns() != null && btn.getGpBtns().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.GpBtnDAO.class).delete("delete from GpBtn where GpBtn.BtnId = " +btn.getId());
				for(int i=0; i<btn.getGpBtns().size(); i++) {
					if(btn.getGpBtns().get(i) != null && btn.getGpBtns().get(i).getBtnId() != null) {
						btn.getGpBtns().get(i).setBtnId(btn.getId());
						DAOFactory.getDAO(zrsy.dao.GpBtnDAO.class).insert(btn.getGpBtns().get(i));
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

	public void update(Btn btn) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();

			DAOFactory.getDAO(BtnDAO.class).update(btn);
			if(btn.getGpBtns() != null && btn.getGpBtns().size() > 0) {
				DAOFactory.getDAO(zrsy.dao.GpBtnDAO.class).delete("delete from GpBtn where GpBtn.BtnId = " +btn.getId());
				for(int i=0; i<btn.getGpBtns().size(); i++) {
					if(btn.getGpBtns().get(i) != null && btn.getGpBtns().get(i).getBtnId() != null) {
						btn.getGpBtns().get(i).setBtnId(btn.getId());
						DAOFactory.getDAO(zrsy.dao.GpBtnDAO.class).insert(btn.getGpBtns().get(i));
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
			DAOFactory.getDAO(BtnDAO.class).update(sql);
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

	public void submit(Btn btn) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(BtnDAO.class).update(btn);
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

	public void review(Btn btn) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(BtnDAO.class).update(btn);
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

	public void confirm(Btn btn) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(BtnDAO.class).update(btn);
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
			DAOFactory.getDAO(BtnDAO.class).delete(sql);
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

	public void remove(Btn btn) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(BtnDAO.class).delete(new BtnHelper().getSql4Delete(btn));
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

	public Btn findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (Btn)DAOFactory.getDAO(BtnDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public Btn findById(Btn btn) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (Btn)DAOFactory.getDAO(BtnDAO.class).load(btn);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public Btn findBy(Btn btn) throws Exception {
		String sql = SqlUtil.getSql4All(BtnHelper.class,btn,Btn.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (Btn)DAOFactory.getDAO(BtnDAO.class).load(sql,Btn.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Btn> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Btn>)DAOFactory.getDAO(BtnDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Btn> findAll(Btn btn) throws Exception {
		String sql = SqlUtil.getSql4All(BtnHelper.class,btn,Btn.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Btn>)DAOFactory.getDAO(BtnDAO.class).query(sql, Btn.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Btn> find(Btn btn,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(BtnHelper.class,btn,pageVO,Btn.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Btn>)DAOFactory.getDAO(BtnDAO.class).query(sql, Btn.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Btn> find(Btn btn) throws Exception {
		String sql = SqlUtil.getSql4All(BtnHelper.class,btn,Btn.LIST_FIELDS);
		DbConnUtil.buildDbconn(0);
		try {
			return (List<Btn>)DAOFactory.getDAO(BtnDAO.class).query(sql, Btn.LIST_FIELDS);
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
			return DAOFactory.getDAO(BtnDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(Btn btn) throws Exception {
		DbConnUtil.buildDbconn(0);
		try {
			return DAOFactory.getDAO(BtnDAO.class).amount(new BtnHelper().getSql4Amount(btn));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}