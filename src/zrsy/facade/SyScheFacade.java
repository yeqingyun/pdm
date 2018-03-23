package zrsy.facade;
import java.util.List;
import org.frm.dao.DAOFactory;
import org.frm.jdbc.DSN;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import zrsy.dao.SyScheDAO;
import zrsy.dao.helper.SyScheHelper;
import zrsy.vo.SySche;
public class SyScheFacade {

	public void save(SySche sySche) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SyScheDAO.class).insert(sySche);
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

	public void update(SySche sySche) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SyScheDAO.class).update(sySche);
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
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SyScheDAO.class).update(sql);
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

	public void submit(SySche sySche) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SyScheDAO.class).update(sySche);
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

	public void review(SySche sySche) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SyScheDAO.class).update(sySche);
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

	public void confirm(SySche sySche) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SyScheDAO.class).update(sySche);
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
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SyScheDAO.class).delete(sql);
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

	public void remove(SySche sySche) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(SyScheDAO.class).delete(new SyScheHelper().getSql4Delete(sySche));
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

	public SySche findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			return (SySche)DAOFactory.getDAO(SyScheDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public SySche findById(SySche sySche) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			return (SySche)DAOFactory.getDAO(SyScheDAO.class).load(sySche);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public SySche findBy(SySche sySche) throws Exception {
		String sql = SqlUtil.getSql4All(SyScheHelper.class,sySche,SySche.LIST_FIELDS);
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			return (SySche)DAOFactory.getDAO(SyScheDAO.class).load(sql,SySche.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<SySche> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			return (List<SySche>)DAOFactory.getDAO(SyScheDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<SySche> find(SySche sySche) throws Exception {
//		String sql = SqlUtil.getSql4Pages(SyScheHelper.class,sySche,SySche.LIST_FIELDS);
//		DbConnUtil.buildDbconn(DSN.DB_CONN);
//		try {
//			return (List<SySche>)DAOFactory.getDAO(SyScheDAO.class).query(sql, SySche.LIST_FIELDS);
//		}
//		catch(Exception e) {
//			throw e;
//		}
//		finally {
//			DbConnUtil.closeDbconn();
//		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<SySche> findAll(SySche sySche) throws Exception {
		String sql = SqlUtil.getSql4All(SyScheHelper.class,sySche,SySche.LIST_FIELDS);
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			return (List<SySche>)DAOFactory.getDAO(SyScheDAO.class).query(sql, SySche.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(String sql) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			return DAOFactory.getDAO(SyScheDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(SySche sySche) throws Exception {
		DbConnUtil.buildDbconn(DSN.DB_CONN);
		try {
			return DAOFactory.getDAO(SyScheDAO.class).amount(new SyScheHelper().getSql4Amount(sySche));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}
