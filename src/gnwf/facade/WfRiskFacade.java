package gnwf.facade;

import gnwf.dao.WfQuesDAO;
import gnwf.dao.WfRiskDAO;
import gnwf.dao.WfRiskReplyDAO;
import gnwf.dao.helper.WfRiskHelper;
import gnwf.vo.WfQues;
import gnwf.vo.WfRisk;
import gnwf.ww.MSG;

import java.util.Date;
import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrsy.dao.ChnlDAO;

public class WfRiskFacade {

	public String save(WfRisk wfRisk) throws Exception {
		DbConnUtil.buildDbconn(3);
		String riskId = new ChnlDAO().callMonth2No(2, "WfRisk");
		wfRisk.setRiskId(riskId);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRiskDAO.class).insert(wfRisk);
			DbConnUtil.commitTransaction();
			return riskId;
		}catch(Exception e) {
			e.printStackTrace();
			DbConnUtil.rollbackTransaction();
			throw e;
		}finally {
			DbConnUtil.closeDbconn();
		}
	}
	
	public String saveAndGetId(WfRisk wfRisk) throws Exception {
		String riskId = null;
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			riskId = new WfRiskDAO().insertAndGetId(wfRisk);
			DbConnUtil.commitTransaction();
		}catch(Exception e) {
			e.printStackTrace();
			DbConnUtil.rollbackTransaction();
			throw e;
		}finally {
			DbConnUtil.closeDbconn();
		}
		return riskId;
	}
	
	public void save(List<WfRisk> wfRiskList) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			for(WfRisk wr : wfRiskList) {
				DAOFactory.getDAO(WfRiskDAO.class).insert(wr);
			}
			DbConnUtil.commitTransaction();
		}catch(Exception e) {
			e.printStackTrace();
			DbConnUtil.rollbackTransaction();
			throw e;
		}finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void update(WfRisk wfRisk) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRiskDAO.class).update(wfRisk);
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
	
	public void update(List<WfRisk> wfRiskList) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			for(WfRisk wr : wfRiskList) {
				DAOFactory.getDAO(WfRiskDAO.class).update(wr);
			}
			DbConnUtil.commitTransaction();
		}catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}finally {
			DbConnUtil.closeDbconn();
		}
	}
	
	public void delete(WfRisk wfRisk) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRiskReplyDAO.class).delete("delete from WfRiskReply where WfRiskReply.RiskId = " +wfRisk.getRiskId());
			DAOFactory.getDAO(WfRiskDAO.class).delete("delete from WfRisk where WfRisk.RiskId = " +wfRisk.getRiskId());
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
			DAOFactory.getDAO(WfRiskDAO.class).update(sql);
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

	public void submit(WfRisk wfRisk) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRiskDAO.class).update(wfRisk);
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

	public void review(WfRisk wfRisk) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRiskDAO.class).update(wfRisk);
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

	public void confirm(WfRisk wfRisk) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRiskDAO.class).update(wfRisk);
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
			DAOFactory.getDAO(WfRiskDAO.class).delete(sql);
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

	public void remove(WfRisk wfRisk) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRiskDAO.class).delete(new WfRiskHelper().getSql4Delete(wfRisk));
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

	public WfRisk findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfRisk)DAOFactory.getDAO(WfRiskDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfRisk findById(WfRisk wfRisk) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			WfRisk wq = (WfRisk)DAOFactory.getDAO(WfRiskDAO.class).load(wfRisk);
			return wq;
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfRisk findBy(WfRisk wfRisk) throws Exception {
		String sql = SqlUtil.getSql4All(WfRiskHelper.class,wfRisk,WfRisk.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfRisk)DAOFactory.getDAO(WfRiskDAO.class).load(sql,WfRisk.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRisk> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRisk>)DAOFactory.getDAO(WfRiskDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRisk> findAll(WfRisk wfRisk) throws Exception {
		String sql = SqlUtil.getSql4All(WfRiskHelper.class,wfRisk,WfRisk.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRisk>)DAOFactory.getDAO(WfRiskDAO.class).query(sql, WfRisk.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRisk> find(WfRisk wfRisk,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfRiskHelper.class,wfRisk,pageVO,WfRisk.LIST_FIELDS);
		System.out.println(sql);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRisk>)DAOFactory.getDAO(WfRiskDAO.class).query(sql, WfRisk.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<WfRisk> find(String conditionsql,PageVO pageVO) throws Exception {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String sql = "select t.* from (select ROW_NUMBER() OVER (ORDER BY WfRisk.RiskId desc) AS rn," + WfRisk.LIST_FIELDS
				     + conditionsql + ") t where t.rn > " + pages + " and t.rn <= " + (pages + pageSize);
		System.out.println(sql);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRisk>)DAOFactory.getDAO(WfRiskDAO.class).query(sql, WfRisk.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<WfRisk> findmy(String conditionsql) throws Exception {
		String sql = "select "+WfRisk.SELF_FIELDS +conditionsql;
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRisk>)DAOFactory.getDAO(WfRiskDAO.class).query(sql, WfRisk.SELF_FIELDS);
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
			return DAOFactory.getDAO(WfRiskDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfRisk wfRisk) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			System.out.println(new WfRiskHelper().getSql4Amount(wfRisk));
			return DAOFactory.getDAO(WfRiskDAO.class).amount(new WfRiskHelper().getSql4Amount(wfRisk));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void invalidRisk(String riskIds,Integer usrId) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			List<WfRisk> wfRiskList = (List<WfRisk>)DAOFactory.getDAO(WfRiskDAO.class).query("select " + WfRisk.SELF_FIELDS + " from WfRisk where RiskId in ("
					+ riskIds + ")", WfRisk.SELF_FIELDS);
			for(WfRisk risk : wfRiskList) {
				risk.setStatus(MSG.WFRISK_STATUS_4);
				risk.setLastUpdate(new Date());
				risk.setLastUpdateUserId(usrId);
				DAOFactory.getDAO(WfRiskDAO.class).update(risk);
				if(risk.getQuesId() != null && !risk.getQuesId().trim().isEmpty()) {
					WfQues ques = new WfQues();
					ques.setQuesId(risk.getQuesId());
					ques = (WfQues)DAOFactory.getDAO(WfQuesDAO.class).load(ques);
					ques.setStatus(MSG.WFQUES_STATUS_30);
					ques.setLastUpd(usrId);
					ques.setLastUpdDate(new Date());
					DAOFactory.getDAO(WfQuesDAO.class).update(ques);
				}
			}
			DbConnUtil.commitTransaction();
		}catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}finally {
			DbConnUtil.closeDbconn();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void closeRisk(String riskIds,Integer usrId) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			List<WfRisk> wfRiskList = (List<WfRisk>)DAOFactory.getDAO(WfRiskDAO.class).query("select " + WfRisk.SELF_FIELDS + " from WfRisk where RiskId in ("
					+ riskIds + ")", WfRisk.SELF_FIELDS);
			for(WfRisk risk : wfRiskList) {
				risk.setStatus(MSG.WFRISK_STATUS_5);
				risk.setLastUpdate(new Date());
				risk.setLastUpdateUserId(usrId);
				DAOFactory.getDAO(WfRiskDAO.class).update(risk);
				/*if(risk.getQuesId() != null && !risk.getQuesId().trim().isEmpty()) {
					WfQues ques = new WfQues();
					ques.setQuesId(risk.getQuesId());
					ques = (WfQues)DAOFactory.getDAO(WfQuesDAO.class).load(ques);
					ques.setStatus(MSG.WFQUES_STATUS_30);
					ques.setLastUpd(usrId);
					ques.setLastUpdDate(new Date());
					DAOFactory.getDAO(WfQuesDAO.class).update(ques);
				}*/
			}
			DbConnUtil.commitTransaction();
		}catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void saveOrUpdate(List<WfRisk> wfRiskList) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			for(WfRisk wr : wfRiskList) {
				
				if(wr.getRiskId() == null) {
					String riskId = new ChnlDAO().callMonth2No(2, "WfRisk");
					wr.setRiskId(riskId);
					System.out.println("riskId"+riskId);
					System.out.println("wr.toString()"+wr.toString());
					DAOFactory.getDAO(WfRiskDAO.class).insert(wr);
				}else {
					DAOFactory.getDAO(WfRiskDAO.class).update(wr);
				}
			}
			DbConnUtil.commitTransaction();
		}catch(Exception e) {
			e.printStackTrace();
			DbConnUtil.rollbackTransaction();
			throw e;
		}finally {
			DbConnUtil.closeDbconn();
		}
	}
}