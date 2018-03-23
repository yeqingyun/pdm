package gnwf.facade;

import gnwf.dao.WfRdRiskDAO;
import gnwf.dao.WfRiskDAO;
import gnwf.dao.helper.WfRdRiskHelper;
import gnwf.vo.WfRdRisk;
import gnwf.vo.WfRisk;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

public class WfRdRiskFacade {

	public void save(WfRdRisk wfRdRisk) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdRiskDAO.class).insert(wfRdRisk);
			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			e.printStackTrace();
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void update(WfRdRisk wfRdRisk) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdRiskDAO.class).update(wfRdRisk);
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
	
	public void delete(WfRdRisk wfRdRisk) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdRiskDAO.class).delete("delete from WfRdRisk where WfRdRisk.WfNo = " +wfRdRisk.getWfNo());
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
			DAOFactory.getDAO(WfRdRiskDAO.class).update(sql);
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

	public void submit(WfRdRisk wfRdRisk) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdRiskDAO.class).update(wfRdRisk);
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

	public void review(WfRdRisk wfRdRisk) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdRiskDAO.class).update(wfRdRisk);
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

	public void confirm(WfRdRisk wfRdRisk) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdRiskDAO.class).update(wfRdRisk);
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
			DAOFactory.getDAO(WfRdRiskDAO.class).delete(sql);
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

	public void remove(WfRdRisk wfRdRisk) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdRiskDAO.class).delete(new WfRdRiskHelper().getSql4Delete(wfRdRisk));
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

	public WfRdRisk findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfRdRisk)DAOFactory.getDAO(WfRdRiskDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfRdRisk findById(WfRdRisk wfRdRisk) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			WfRdRisk wq = (WfRdRisk)DAOFactory.getDAO(WfRiskDAO.class).load(wfRdRisk);
			return wq;
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfRdRisk findBy(WfRdRisk wfRdRisk) throws Exception {
		String sql = SqlUtil.getSql4All(WfRdRiskHelper.class,wfRdRisk,WfRdRisk.SELF_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfRdRisk)DAOFactory.getDAO(WfRdRiskDAO.class).load(sql,WfRdRisk.SELF_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRdRisk> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRdRisk>)DAOFactory.getDAO(WfRdRiskDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRdRisk> findAll(WfRdRisk wfRdRisk) throws Exception {
		String sql = SqlUtil.getSql4All(WfRdRiskHelper.class,wfRdRisk,WfRdRisk.SELF_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRdRisk>)DAOFactory.getDAO(WfRdRiskDAO.class).query(sql, WfRdRisk.SELF_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRdRisk> find(WfRdRisk wfRdRisk,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfRdRiskHelper.class,wfRdRisk,pageVO,WfRdRisk.SELF_FIELDS);
		System.out.println(sql);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRdRisk>)DAOFactory.getDAO(WfRdRiskDAO.class).query(sql, WfRdRisk.SELF_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
	
	/*@SuppressWarnings("unchecked")
	public List<WfQues> findmy(String conditionsql,PageVO pageVO) throws Exception {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String fields = WfQues.SELF_FIELDS+
		",(select u.UsrName from Usr u where WfQues.CreateBy = u.Id) AS CreateUsr" +
		",(select sc.SchNm from SchCfg sc where WfQues.ScheId = sc.SchId) AS SchNm" +
		",(select pu.UsrId from SchCfg sc,PrjtRole pr,PrjtUsr pu where WfQues.ScheId = sc.SchId and WfQues.PrjtNo = pu.PrjtNo and pr.PrjtTypId = sc.TypId and pr.RoleId = pu.RoleId and pr.RoleNm = 'DQA') AS RoleDQAId" +
		",(select pt.TypNm from SchCfg sc,PrjtTyp pt where WfQues.ScheId = sc.SchId and pt.TypId = sc.TypId) AS TypNm" +
		",(select pd.PrjtNm from PrjtDef pd  where pd.PrjtNo = WfQues.PrjtNo) AS PrjtNm" +
		",(select u.UsrName from Usr u where WfQues.IdtfBy = u.Id) AS IdtfUsr" +
		",(select wr.WfName from WfRd wr where WfQues.WfNo = wr.WfNo) AS WfName";
		
		
		String mstr = "select top "+pages+ " WfQues.QuesId " + conditionsql+" order by WfQues.QuesId ";
		String sql = "select top "+pageSize+" "+fields+conditionsql+" and WfQues.QuesId not in("+mstr+") "+" order by WfQues.QuesId ";
		System.out.println(sql);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfQues>)DAOFactory.getDAO(WfQuesDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}*/
	
	@SuppressWarnings("unchecked")
	public List<WfRdRisk> findmy(String conditionsql) throws Exception {
		String sql = "select "+WfRisk.SELF_FIELDS +conditionsql;
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRdRisk>)DAOFactory.getDAO(WfRdRiskDAO.class).query(sql, WfRdRisk.SELF_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	/*@SuppressWarnings("unchecked")
	public List<WfRisk> find(WfRisk wfRisk) throws Exception {
		String sql="select WfQues.IdtfResFileName,WfQues.ResultFileName,WfQues.IdtfResFileNo,WfQues.ResultFileNo,WfQues.QuesId,WfQues.ScheId,WfQues.TaskId,WfQues.CateId,WfQues.ComId,WfQues.DeptId,WfQues.UserId,WfQues.QuesLevel,WfQues.Status,WfQues.CreateBy,WfQues.LastUpd,WfQues.WfNo,WfQues.IsRisk,WfQues.CreateDate,WfQues.LastUpdDate,WfQues.QuesText,WfQues.Result,WfQues.Title,WfQues.RyUsrs,Usr.UsrName,WfRd.FlowId,WfQues.IdtfBy,WfQues.IdtfDate,WfQues.IdtfRes,WfRd.ProjectNo as PrjtNo,(select u.UsrName from Usr u where WfQues.CreateBy = u.Id) AS CreateUsr,(select sc.SchNm from SchCfg sc where WfQues.ScheId = sc.SchId) AS SchNm,(select pu.UsrId from SchCfg sc,PrjtRole pr,PrjtUsr pu where WfQues.ScheId = sc.SchId and WfRd.ProjectNo = pu.PrjtNo and pr.PrjtTypId = sc.TypId and pr.RoleId = pu.RoleId and pr.RoleNm = 'DQA') AS RoleDQAId,(select pt.TypNm from SchCfg sc,PrjtTyp pt where WfQues.ScheId = sc.SchId and pt.TypId = sc.TypId) AS TypNm,(select pd.PrjtNm from PrjtDef pd where pd.PrjtNo = WfRd.ProjectNo) AS PrjtNm,(select u.UsrName from Usr u where WfQues.IdtfBy = u.Id) AS IdtfUsr from WfQues  left join Usr on WfQues.UserId = Usr.Id inner join WfRd on WfQues.WfNo = WfRd.WfNo where 1=1 and WfQues.QuesId in("+wfQues.getWfId()+") order by WfQues.QuesId";
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfQues>)DAOFactory.getDAO(WfQuesDAO.class).query(sql, WfQues.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}*/

	public int amount(String sql) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfRdRiskDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfRdRisk wfRdRisk) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			System.out.println(new WfRdRiskHelper().getSql4Amount(wfRdRisk));
			return DAOFactory.getDAO(WfRdRiskDAO.class).amount(new WfRdRiskHelper().getSql4Amount(wfRdRisk));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	/*public String getPrjtTaksIds(String prjtNo) throws Exception {
		String wfNos = "";
		DbConnUtil.buildDbconn(3);
		try {
			ResultSet rs = new WfRdDAO().query("select WfRd.WfNo from WfRd" +
					" where WfRd.ProjectNo = '" +prjtNo+ "'");
			while(rs.next()) {
				wfNos += "'" + rs.getString("WfNo") + "',";
			}
			if(wfNos.lastIndexOf(",") > -1) {
				wfNos = wfNos.substring(0,wfNos.lastIndexOf(","));
			}
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
		return wfNos;
	}

	public void rsave(WfRd wfRd) throws Exception{
		String wfNo = WFUtil.createQuesWfRd(wfRd.getProjectNo(), wfRd.getScheId(), wfRd.getWfName(),wfRd.getCreateBy(), wfRd.getPlanEDate());
		DbConnUtil.buildDbconn(3);
		try {
			if(wfRd.getQuesIds() != null && wfRd.getQuesIds().length() > 0) {
				DAOFactory.getDAO(gnwf.dao.WfQuesDAO.class).update("update WfQues set IsRisk = '"+wfNo+"',Status = 20 where QuesId in ("+wfRd.getQuesIds()+")" );
			}
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void updateResponers(WfQues wfQues,int sessionUsrId,String delIds, List<QuesResp> addQuesRsps) throws Exception {
		
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			String updateSQL = "update QuesResp set RespType = 0 , LastUpd ="+sessionUsrId+
					",LastUpdDate='"+GenericUtil.dateFormat(new Date(), "yyyy-MM-dd 00:00:00")+"'where Id in ( "+delIds+" )";
			DAOFactory.getDAO(QuesRespDAO.class).update(updateSQL);
			
			for(QuesResp e:addQuesRsps){
				DAOFactory.getDAO(QuesRespDAO.class).insert(e);
			}
			DAOFactory.getDAO(WfQuesDAO.class).update(wfQues);

			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			e.printStackTrace();
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}*/
}