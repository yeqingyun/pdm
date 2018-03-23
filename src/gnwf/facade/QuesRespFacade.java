package gnwf.facade;

import java.util.Date;
import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrprjt.dao.SchCfgDAO;
import zrprjt.vo.SchCfg;
import gnwf.dao.QuesRespDAO;
import gnwf.dao.WfQuesDAO;
import gnwf.dao.helper.QuesRespHelper;
import gnwf.vo.QuesResp;
import gnwf.vo.WfQues;
import gnwf.ww.MSG;

public class QuesRespFacade {

	public void save(QuesResp quesResp) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(QuesRespDAO.class).insert(quesResp);

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

	public void update(QuesResp quesResp) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(QuesRespDAO.class).update(quesResp);

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
			DAOFactory.getDAO(QuesRespDAO.class).update(sql);
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

	public void submit(QuesResp quesResp) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(QuesRespDAO.class).update(quesResp);
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

	public void review(QuesResp quesResp) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(QuesRespDAO.class).update(quesResp);
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

	public void confirm(QuesResp quesResp) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(QuesRespDAO.class).update(quesResp);
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
			DAOFactory.getDAO(QuesRespDAO.class).delete(sql);
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

	public void remove(QuesResp quesResp) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(QuesRespDAO.class).delete(new QuesRespHelper().getSql4Delete(quesResp));
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

	public QuesResp findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (QuesResp)DAOFactory.getDAO(QuesRespDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public QuesResp findById(QuesResp quesResp) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (QuesResp)DAOFactory.getDAO(QuesRespDAO.class).load(quesResp);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public QuesResp findBy(QuesResp quesResp) throws Exception {
		String sql = SqlUtil.getSql4All(QuesRespHelper.class,quesResp,QuesResp.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (QuesResp)DAOFactory.getDAO(QuesRespDAO.class).load(sql,QuesResp.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<QuesResp> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<QuesResp>)DAOFactory.getDAO(QuesRespDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<QuesResp> findAll(QuesResp quesResp) throws Exception {
		String sql = SqlUtil.getSql4All(QuesRespHelper.class,quesResp,QuesResp.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<QuesResp>)DAOFactory.getDAO(QuesRespDAO.class).query(sql, QuesResp.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<QuesResp> find(QuesResp quesResp,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(QuesRespHelper.class,quesResp,pageVO,QuesResp.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<QuesResp>)DAOFactory.getDAO(QuesRespDAO.class).query(sql, QuesResp.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<QuesResp> find(QuesResp quesResp) throws Exception {
		String sql = SqlUtil.getSql4All(QuesRespHelper.class,quesResp,QuesResp.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<QuesResp>)DAOFactory.getDAO(QuesRespDAO.class).query(sql, QuesResp.LIST_FIELDS);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(String sql) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(QuesRespDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(QuesResp quesResp) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(QuesRespDAO.class).amount(new QuesRespHelper().getSql4Amount(quesResp));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
	/**
	 * 批量验证无效
	 * @param quesRespIdArr
	 * @param userId
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void batchReject(String[] quesRespIdArr,Integer userId) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			for(String quesRespId : quesRespIdArr) {
				QuesResp qr = new QuesResp();
				qr.setId(Integer.valueOf(quesRespId));
				qr.setStatus(MSG.QUESRESP_STATUS_DISABLE);
				qr.setIdtfDate(new Date());
				qr.setLastUpd(userId);
				qr.setLastUpdDate(new Date());
				DAOFactory.getDAO(QuesRespDAO.class).update(qr);
				qr = (QuesResp)DAOFactory.getDAO(QuesRespDAO.class).load("select QuesId from QuesResp where Id = " + qr.getId(), "QuesResp.QuesId");
				WfQues wfques = new WfQues();
				wfques.setQuesId(qr.getQuesId());
				wfques.setLastUpd(userId);
				wfques.setLastUpdDate(new Date());
				List<QuesResp> quesrespList = (List<QuesResp>)DAOFactory.getDAO(QuesRespDAO.class).query("select Status from QuesResp where QuesResp.RespType = 1 and QuesId = '" + wfques.getQuesId()+"'", "QuesResp.Status");
				wfques.setStatus(WfQuesFacade.getWfQuesStatus(quesrespList));
				DAOFactory.getDAO(WfQuesDAO.class).update(wfques);
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
	/**
	 * 批量验证有效
	 * @param quesRespIdArr
	 * @param userId
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void batchApprove(String[] quesRespIdArr,Integer userId) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			for(String quesRespId : quesRespIdArr) {
				QuesResp qr = new QuesResp();
				qr.setId(Integer.valueOf(quesRespId));
				qr.setStatus(MSG.QUESRESP_STATUS_2);
				qr.setIdtfDate(new Date());
				qr.setLastUpd(userId);
				qr.setLastUpdDate(new Date());
				DAOFactory.getDAO(QuesRespDAO.class).update(qr);
				qr = (QuesResp)DAOFactory.getDAO(QuesRespDAO.class).load("select QuesId from QuesResp where Id = " + qr.getId(), "QuesResp.QuesId");
				WfQues wfques = new WfQues();
				wfques.setQuesId(qr.getQuesId());
				wfques.setLastUpd(userId);
				wfques.setLastUpdDate(new Date());
				/*List<QuesResp> quesrespList = (List<QuesResp>)DAOFactory.getDAO(QuesRespDAO.class).query("select Status from QuesResp where QuesResp.RespType = 1 and QuesId = '" + wfques.getQuesId()+"'", "QuesResp.Status");
				int status = WfQuesFacade.getWfQuesStatus(quesrespList);
				String sql = "select SchNm from SchCfg where SchId = "
						+ "(select Parent from SchCfg left join WfQues on "
						+ "(SchCfg.SchId = WfQues.ScheId) where WfQues.QuesId = '"+ qr.getQuesId()+"')";
				SchCfg sc = (SchCfg)DAOFactory.getDAO(SchCfgDAO.class).load(sql, "SchCfg.SchNm");
				if(null != sc.getSchNm() && status == MSG.WFQUES_STATUS_11){
					if(sc.getSchNm().equals("预研") || sc.getSchNm().equals("预立项") || sc.getSchNm().equals("开发")){
						status = MSG.WFQUES_STATUS_30;
					}
				}
				wfques.setStatus(status);*/
				wfques.setStatus(MSG.WFQUES_STATUS_11);
				DAOFactory.getDAO(WfQuesDAO.class).update(wfques);
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

	/**
	 * 验证有效
	 * @param ques
	 * @param resp
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void approve(WfQues ques,QuesResp resp) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(QuesRespDAO.class).update(resp);
			List<QuesResp> quesrespList = (List<QuesResp>)DAOFactory.getDAO(QuesRespDAO.class).query
					("select Status from QuesResp where QuesResp.RespType = 1 and QuesId = '" + ques.getQuesId() + "'", "QuesResp.Status");
			int status = WfQuesFacade.getWfQuesStatus(quesrespList);
			String sql = "select SchNm from SchCfg where SchId = "
					+ "(select Parent from SchCfg left join WfQues on "
					+ "(SchCfg.SchId = WfQues.ScheId) where WfQues.QuesId = '"+ ques.getQuesId() + "')";
			SchCfg sc = (SchCfg)DAOFactory.getDAO(SchCfgDAO.class).load(sql, "SchCfg.SchNm");
			if(null != sc.getSchNm() && status == MSG.WFQUES_STATUS_11){
				if(sc.getSchNm().equals("预研") || sc.getSchNm().equals("预立项") || sc.getSchNm().equals("开发")){
					status = MSG.WFQUES_STATUS_30;
				}
			}
			ques.setStatus(status);
			DAOFactory.getDAO(WfQuesDAO.class).update(ques);
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
	
	
	@SuppressWarnings("unchecked")
	public void reject(WfQues ques,QuesResp resp) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(QuesRespDAO.class).update(resp);
			List<QuesResp> quesrespList = (List<QuesResp>)DAOFactory.getDAO(QuesRespDAO.class).query
					("select Status from QuesResp where QuesResp.RespType = 1 and QuesId = '" + ques.getQuesId() + "'", "QuesResp.Status");
			ques.setStatus(WfQuesFacade.getWfQuesStatus(quesrespList));
			DAOFactory.getDAO(WfQuesDAO.class).update(ques);
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
	
	@SuppressWarnings("unchecked")
	public void returnQues(WfQues ques,QuesResp resp) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(QuesRespDAO.class).update(resp);
			List<QuesResp> quesrespList = (List<QuesResp>)DAOFactory.getDAO(QuesRespDAO.class).query
					("select Status from QuesResp where QuesResp.RespType = 1 and QuesId = '" + ques.getQuesId() + "'", "QuesResp.Status");
			ques.setStatus(MSG.WFQUES_STATUS_19);
			DAOFactory.getDAO(WfQuesDAO.class).update(ques);
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
}