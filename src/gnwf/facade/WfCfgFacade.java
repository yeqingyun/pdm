package gnwf.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import gnwf.dao.WfCfgDAO;
import gnwf.dao.helper.WfCfgHelper;
import gnwf.vo.WfCfg;
import gnwf.vo.WfDept;

public class WfCfgFacade {

	public void save(WfCfg wfCfg) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfCfgDAO.class).insert(wfCfg);
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

	public void update(WfCfg wfCfg) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfCfgDAO.class).update(wfCfg);
			
			DAOFactory.getDAO(gnwf.dao.WfDeptDAO.class).delete("delete from WfDept where WfDept.FlowId = " +wfCfg.getFlowId());
			if(wfCfg.getWfDepts() != null && wfCfg.getWfDepts().size() > 0) {
				for(int i=0; i<wfCfg.getWfDepts().size(); i++) {
					WfDept d = wfCfg.getWfDepts().get(i);
					if(d!= null && d.getDeptId()!=null) {
						d.setFlowId(wfCfg.getFlowId());
						DAOFactory.getDAO(gnwf.dao.WfDeptDAO.class).insert(d);
					}
				}
			}
//			if(wfCfg.getWfFields() != null && wfCfg.getWfFields().size() > 0) {
//				DAOFactory.getDAO(gnwf.dao.WfFieldDAO.class).delete("delete from WfField where WfField.FlowId = " +wfCfg.getFlowId());
//				for(int i=0; i<wfCfg.getWfFields().size(); i++) {
//					if(wfCfg.getWfFields().get(i) != null && wfCfg.getWfFields().get(i).getFlowId() != null) {
//						wfCfg.getWfFields().get(i).setFlowId(wfCfg.getFlowId());
//						DAOFactory.getDAO(gnwf.dao.WfFieldDAO.class).insert(wfCfg.getWfFields().get(i));
//					}
//				}
//			}
//			if(wfCfg.getWfAgents() != null && wfCfg.getWfAgents().size() > 0) {
//				DAOFactory.getDAO(gnwf.dao.WfAgentDAO.class).delete("delete from WfAgent where WfAgent.FlowId = " +wfCfg.getFlowId());
//				for(int i=0; i<wfCfg.getWfAgents().size(); i++) {
//					if(wfCfg.getWfAgents().get(i) != null && wfCfg.getWfAgents().get(i).getFlowId() != null) {
//						wfCfg.getWfAgents().get(i).setFlowId(wfCfg.getFlowId());
//						DAOFactory.getDAO(gnwf.dao.WfAgentDAO.class).insert(wfCfg.getWfAgents().get(i));
//					}
//				}
//			}
			
			DAOFactory.getDAO(gnwf.dao.WfLeaderDAO.class).delete("delete from WfLeader where WfLeader.FlowId = " +wfCfg.getFlowId());
			if(wfCfg.getWfLeaders() != null && wfCfg.getWfLeaders().size() > 0) {
				for(int i=0; i<wfCfg.getWfLeaders().size(); i++) {
					if(wfCfg.getWfLeaders().get(i) != null && wfCfg.getWfLeaders().get(i).getUserId() != null) {
						wfCfg.getWfLeaders().get(i).setFlowId(wfCfg.getFlowId());
						DAOFactory.getDAO(gnwf.dao.WfLeaderDAO.class).insert(wfCfg.getWfLeaders().get(i));
					}
				}
			}
//			if(wfCfg.getWfSteps() != null && wfCfg.getWfSteps().size() > 0) {
//				DAOFactory.getDAO(gnwf.dao.WfStepDAO.class).delete("delete from WfStep where WfStep.FlowId = " +wfCfg.getFlowId());
//				for(int i=0; i<wfCfg.getWfSteps().size(); i++) {
//					if(wfCfg.getWfSteps().get(i) != null && wfCfg.getWfSteps().get(i).getFlowId() != null) {
//						wfCfg.getWfSteps().get(i).setFlowId(wfCfg.getFlowId());
//						DAOFactory.getDAO(gnwf.dao.WfStepDAO.class).insert(wfCfg.getWfSteps().get(i));
//					}
//				}
//			}
			
			DAOFactory.getDAO(gnwf.dao.WfRelateDAO.class).delete("delete from WfCfgRelate where WfCfgRelate.FlowId = " +wfCfg.getFlowId());
			if(wfCfg.getWfRelates() != null && wfCfg.getWfRelates().size() > 0) {
				for(int i=0; i<wfCfg.getWfRelates().size(); i++) {
					if(wfCfg.getWfRelates().get(i) != null && wfCfg.getWfRelates().get(i).getRelateId() != null) {
						wfCfg.getWfRelates().get(i).setFlowId(wfCfg.getFlowId());
						DAOFactory.getDAO(gnwf.dao.WfCfgRelateDAO.class).insert(wfCfg.getWfRelates().get(i));
					}
				}
			}
//			if(wfCfg.getWfJobs() != null && wfCfg.getWfJobs().size() > 0) {
//				DAOFactory.getDAO(gnwf.dao.WfJobDAO.class).delete("delete from WfJob where WfJob.FlowId = " +wfCfg.getFlowId());
//				for(int i=0; i<wfCfg.getWfJobs().size(); i++) {
//					if(wfCfg.getWfJobs().get(i) != null && wfCfg.getWfJobs().get(i).getFlowId() != null) {
//						wfCfg.getWfJobs().get(i).setFlowId(wfCfg.getFlowId());
//						DAOFactory.getDAO(gnwf.dao.WfJobDAO.class).insert(wfCfg.getWfJobs().get(i));
//					}
//				}
//			}

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
			DAOFactory.getDAO(WfCfgDAO.class).update(sql);
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

	public void submit(WfCfg wfCfg) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfCfgDAO.class).update(wfCfg);
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

	public void review(WfCfg wfCfg) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfCfgDAO.class).update(wfCfg);
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

	public void confirm(WfCfg wfCfg) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfCfgDAO.class).update(wfCfg);
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
			DAOFactory.getDAO(WfCfgDAO.class).delete(sql);
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

	public void remove(WfCfg wfCfg) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfCfgDAO.class).delete(new WfCfgHelper().getSql4Delete(wfCfg));
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

	public WfCfg findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfCfg)DAOFactory.getDAO(WfCfgDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfCfg findById(WfCfg wfCfg) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfCfg)DAOFactory.getDAO(WfCfgDAO.class).load(wfCfg);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfCfg findBy(WfCfg wfCfg) throws Exception {
		String sql = SqlUtil.getSql4All(WfCfgHelper.class,wfCfg,WfCfg.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfCfg)DAOFactory.getDAO(WfCfgDAO.class).load(sql,WfCfg.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfCfg> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfCfg>)DAOFactory.getDAO(WfCfgDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfCfg> findAll(WfCfg wfCfg) throws Exception {
		String sql = SqlUtil.getSql4All(WfCfgHelper.class,wfCfg,WfCfg.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfCfg>)DAOFactory.getDAO(WfCfgDAO.class).query(sql, WfCfg.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfCfg> find(WfCfg wfCfg,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfCfgHelper.class,wfCfg,pageVO,WfCfg.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			System.out.println(sql);
			return (List<WfCfg>)DAOFactory.getDAO(WfCfgDAO.class).query(sql, WfCfg.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfCfg> find(WfCfg wfCfg) throws Exception {
		String sql = SqlUtil.getSql4All(WfCfgHelper.class,wfCfg,WfCfg.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfCfg>)DAOFactory.getDAO(WfCfgDAO.class).query(sql, WfCfg.LIST_FIELDS);
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
			return DAOFactory.getDAO(WfCfgDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfCfg wfCfg) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfCfgDAO.class).amount(new WfCfgHelper().getSql4Amount(wfCfg));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}