package zrprjt.facade;

import gnwf.dao.WfDocDAO;
import gnwf.facade.WfDocFacade;
import gnwf.vo.WfDoc;

import java.util.Date;
import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import com.opensymphony.webwork.ServletActionContext;

import zrprjt.dao.PrjtDefDAO;
import zrprjt.dao.PrjtRoleDAO;
import zrprjt.dao.PrjtUsrDAO;
import zrprjt.dao.PrjtUsrUpRecordDAO;
import zrprjt.dao.TaskDAO;
import zrprjt.dao.helper.PrjtDefHelper;
import zrprjt.dao.helper.TaskHelper;
import zrprjt.vo.PrjtDef;
import zrprjt.vo.PrjtPoint;
import zrprjt.vo.PrjtRole;
import zrprjt.vo.PrjtUsr;
import zrprjt.vo.PrjtUsrUpRecord;
import zrprjt.vo.Task;
import zrprjt.vo.TaskUpRd;
import zrsy.dao.ChnlDAO;

public class PrjtDefFacade {

	/**
	 * @param sysId
	 * @param prjtDef
	 * @throws Exception
	 */
	public void save(int sysId,PrjtDef prjtDef) throws Exception {
		try {
			
			
			DbConnUtil.buildDbconn(1);
			DbConnUtil.beginTransaction();
			
			prjtDef.setPrjtNo(new ChnlDAO().callYearNo(sysId,"PrjtDef"));
			if(prjtDef.getTasks() != null && prjtDef.getTasks().size() > 0) {
				for(int i=0; i<prjtDef.getTasks().size(); i++) {
					if(prjtDef.getTasks().get(i) != null && prjtDef.getPrjtNo() != null) {
						//prjtDef.getTasks().get(i).setTaskNo(new ChnlDAO().getYearNo("T"));
						prjtDef.getTasks().get(i).setPrjtNo(prjtDef.getPrjtNo());
					}
				}
			}
			DAOFactory.getDAO(PrjtDefDAO.class).insert(prjtDef);
			if(prjtDef.getPrjtAuths() != null && prjtDef.getPrjtAuths().size() > 0) {
				DAOFactory.getDAO(zrprjt.dao.PrjtAuthDAO.class).delete("delete from PrjtAuth where PrjtAuth.PrjtNo = " +prjtDef.getPrjtNo());
				for(int i=0; i<prjtDef.getPrjtAuths().size(); i++) {
					if(prjtDef.getPrjtAuths().get(i) != null && prjtDef.getPrjtAuths().get(i).getPrjtNo() != null) {
						prjtDef.getPrjtAuths().get(i).setPrjtNo(prjtDef.getPrjtNo());
						DAOFactory.getDAO(zrprjt.dao.PrjtAuthDAO.class).insert(prjtDef.getPrjtAuths().get(i));
					}
				}
			}
			/**if(prjtDef.getPrjtRoles() != null && prjtDef.getPrjtRoles().size() > 0) {
				DAOFactory.getDAO(zrprjt.dao.PrjtRoleDAO.class).delete("delete from PrjtRole where PrjtRole.PrjtNo = " +prjtDef.getPrjtNo());
				for(int i=0; i<prjtDef.getPrjtRoles().size(); i++) {
					if(prjtDef.getPrjtRoles().get(i) != null && prjtDef.getPrjtRoles().get(i).getPrjtTypId() != null) {
						prjtDef.getPrjtRoles().get(i).setPrjtTypId(prjtDef.getPrjtNo());
						DAOFactory.getDAO(zrprjt.dao.PrjtRoleDAO.class).insert(prjtDef.getPrjtRoles().get(i));
					}
				}
			}
			**/
			if(prjtDef.getPrjtUsrs() != null && prjtDef.getPrjtUsrs().size() > 0) {
				DAOFactory.getDAO(zrprjt.dao.PrjtUsrDAO.class).delete("delete from PrjtUsr where PrjtUsr.PrjtNo = " +prjtDef.getPrjtNo());
				for(int i=0; i<prjtDef.getPrjtUsrs().size(); i++) {
					if(prjtDef.getPrjtUsrs().get(i) != null && prjtDef.getPrjtUsrs().get(i).getPrjtNo() != null) {
						prjtDef.getPrjtUsrs().get(i).setPrjtNo(prjtDef.getPrjtNo());
						DAOFactory.getDAO(zrprjt.dao.PrjtUsrDAO.class).insert(prjtDef.getPrjtUsrs().get(i));
					}
				}
			}
			
			
			if(prjtDef.getTasks() != null && prjtDef.getTasks().size() > 0) {
				//DAOFactory.getDAO(zrprjt.dao.TaskDAO.class).delete("delete from Task where Task.PrjtNo = " +prjtDef.getPrjtNo());
				
				for(Task e : prjtDef.getTasks()) {
					if(e != null && e.getPrjtNo() != null) {
						e.setPrjtNo(prjtDef.getPrjtNo());
						
						if(e.getParent()!=null){
							Task parentTask = new Task();
							parentTask.setPrjtNo(prjtDef.getPrjtNo());
							parentTask.setSchId(Integer.valueOf(e.getParent()));
							String sql = SqlUtil.getSql4All(TaskHelper.class,parentTask,Task.LIST_FIELDS);
							parentTask= (Task)DAOFactory.getDAO(TaskDAO.class).load(sql,Task.LIST_FIELDS);
							if(parentTask!=null){
								e.setParent(parentTask.getTaskNo());
							}else{
								//System.out.println(sql);
							}
						}
						
						DAOFactory.getDAO(zrprjt.dao.TaskDAO.class).insert(e);
						
						/**
						TaskUpRd taskUpRd = new TaskUpRd();
						taskUpRd.setTaskId(e.getTaskNo());
						taskUpRd.setPlanDuration(e.getPlanDuration());
						taskUpRd.setPlanStaDate(e.getPlanStaDate());
						taskUpRd.setPlanOveDate(e.getPlanOveDate());
						//taskUpRd.setCreateBy(prjtDef.get);
						taskUpRd.setCreateDate(new Date());
						DAOFactory.getDAO(zrprjt.dao.TaskUpRdDAO.class).insert(taskUpRd);
						**/
					}
				}
			}
			
			
			if(prjtDef.getPrjtPoints() != null && prjtDef.getPrjtPoints().size() > 0) {
				for(PrjtPoint e : prjtDef.getPrjtPoints()) {
						e.setPrjtNo(prjtDef.getPrjtNo());
						DAOFactory.getDAO(zrprjt.dao.PrjtPointDAO.class).insert(e);
					}
			}
			
			
			String querySql = "select RoleId,RoleNm,RoleTyp from PrjtRole where RoleNm = '产品经理' or RoleNm = '项目经理'";
			List<PrjtRole> roles = new PrjtRoleDAO().query(querySql,"RoleId,RoleNm,RoleTyp");
			
			//保存产品经理
				/** 
				 PrjtUsr cpManager = new PrjtUsr();
				 String s = "select max(Id) as maxid from PrjtUsr "; 
				 int id = new PrjtUsrDAO().getMaxId(s)+1; 
				 
				 cpManager.setId(id);
				 cpManager.setUsrId(prjtDef.getCreateBy());
				 for(PrjtRole e:roles ){
					 if(e.getRoleNm().trim().equals("产品经理")){
						 cpManager.setRoleId(e.getRoleId());
					 }
				 }
				 cpManager.setCreateBy(prjtDef.getCreateBy());
				 cpManager.setCreateDate(new Date());
				 cpManager.setLastUpd(prjtDef.getCreateBy());
				 cpManager.setPriority(1);
				 cpManager.setLastDate(new Date());
				 cpManager.setPrjtTypId(prjtDef.getTypId());
				 cpManager.setPrjtNo(prjtDef.getPrjtNo());
				 cpManager.setStatus(1);
				
				
				    PrjtUsrUpRecord prjtUsrUpRecord = new PrjtUsrUpRecord();
				    prjtUsrUpRecord.setCreateBy(cpManager.getLastUpd());
				    prjtUsrUpRecord.setCreateDate(new Date());
				    prjtUsrUpRecord.setPrjtUsrId(cpManager.getId());
				    prjtUsrUpRecord.setUsrId(cpManager.getUsrId());
				    prjtUsrUpRecord.setPriority(cpManager.getPriority());
				    prjtUsrUpRecord.setUpTyp(1);
					DAOFactory.getDAO(PrjtUsrUpRecordDAO.class).insert(prjtUsrUpRecord);
				
				DAOFactory.getDAO(zrprjt.dao.PrjtUsrDAO.class).insert(cpManager);
			
			**/
			
			
			
			//保存项目经理
			//if(prjtDef.getPmUsrId()!=null){
				
				 PrjtUsr pmUsr = new PrjtUsr();
				 String sql2 = "select max(Id) as maxid from PrjtUsr "; 
				 int pmUsrId = new PrjtUsrDAO().getMaxId(sql2)+1;
				 
				 pmUsr.setId(pmUsrId);
				// pmUsr.setUsrId(prjtDef.getPmUsrId());
				 pmUsr.setUsrId(prjtDef.getCreateBy());
				 for(PrjtRole e:roles ){
					 if(e.getRoleNm().trim().equals("项目经理")){
						 pmUsr.setRoleId(e.getRoleId());
					 }
				 }
				 pmUsr.setCreateBy(prjtDef.getCreateBy());
				 pmUsr.setCreateDate(new Date());
				 pmUsr.setLastUpd(prjtDef.getCreateBy());
				 pmUsr.setPriority(1);
				 pmUsr.setLastDate(new Date());
				 pmUsr.setPrjtTypId(prjtDef.getTypId());
				 pmUsr.setPrjtNo(prjtDef.getPrjtNo());
				 pmUsr.setStatus(1);
				
				
				    PrjtUsrUpRecord puRecord = new PrjtUsrUpRecord(); 
				    puRecord.setCreateBy(pmUsr.getLastUpd());
				    puRecord.setCreateDate(new Date());
				    puRecord.setPrjtUsrId(pmUsr.getId());
				    puRecord.setUsrId(pmUsr.getUsrId());
				    puRecord.setPriority(pmUsr.getPriority());
				    puRecord.setUpTyp(1);
					DAOFactory.getDAO(PrjtUsrUpRecordDAO.class).insert(puRecord);
				
				DAOFactory.getDAO(zrprjt.dao.PrjtUsrDAO.class).insert(pmUsr);
			//}
			DbConnUtil.commitTransaction();
			
		
//			String docVer = new ChnlDAO().callDocV(wfDoc.getProjectNo(),wfDoc.getDocName());
//			wfDoc.setDocVer(docVer);
//			DAOFactory.getDAO(WfDocDAO.class).insert(wfDoc);
			
			

		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			e.printStackTrace();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
		
		//保存产品定义书
//		if(prjtDef.getWfDoc()!=null){
//			WfDoc wfDoc = prjtDef.getWfDoc();
//			wfDoc.setProjectNo(prjtDef.getPrjtNo());
//			new WfDocFacade().savePrjtDefDoc(wfDoc);
//		}
	}

	public void update(PrjtDef prjtDef) throws Exception {
		try {
			DbConnUtil.buildDbconn(1);
			DbConnUtil.beginTransaction();
			
			DAOFactory.getDAO(PrjtDefDAO.class).update(prjtDef);
		
			
			if(prjtDef.getPrjtAuths() != null && prjtDef.getPrjtAuths().size() > 0) {
				DAOFactory.getDAO(zrprjt.dao.PrjtAuthDAO.class).delete("delete from PrjtAuth where PrjtAuth.PrjtNo = " +prjtDef.getPrjtNo());
				for(int i=0; i<prjtDef.getPrjtAuths().size(); i++) {
					if(prjtDef.getPrjtAuths().get(i) != null && prjtDef.getPrjtAuths().get(i).getPrjtNo() != null) {
						prjtDef.getPrjtAuths().get(i).setPrjtNo(prjtDef.getPrjtNo());
						DAOFactory.getDAO(zrprjt.dao.PrjtAuthDAO.class).insert(prjtDef.getPrjtAuths().get(i));
					}
				}
			}
			/**
			if(prjtDef.getPrjtRoles() != null && prjtDef.getPrjtRoles().size() > 0) {
				DAOFactory.getDAO(zrprjt.dao.PrjtRoleDAO.class).delete("delete from PrjtRole where PrjtRole.PrjtNo = " +prjtDef.getPrjtNo());
				for(int i=0; i<prjtDef.getPrjtRoles().size(); i++) {
					if(prjtDef.getPrjtRoles().get(i) != null && prjtDef.getPrjtRoles().get(i).getPrjtNo() != null) {
						prjtDef.getPrjtRoles().get(i).setPrjtNo(prjtDef.getPrjtNo());
						DAOFactory.getDAO(zrprjt.dao.PrjtRoleDAO.class).insert(prjtDef.getPrjtRoles().get(i));
					}
				}
			}
			**/
			if(prjtDef.getPrjtUsrs() != null && prjtDef.getPrjtUsrs().size() > 0) {
				DAOFactory.getDAO(zrprjt.dao.PrjtUsrDAO.class).delete("delete from PrjtUsr where PrjtUsr.PrjtNo = " +prjtDef.getPrjtNo());
				for(int i=0; i<prjtDef.getPrjtUsrs().size(); i++) {
					if(prjtDef.getPrjtUsrs().get(i) != null && prjtDef.getPrjtUsrs().get(i).getPrjtNo() != null) {
						prjtDef.getPrjtUsrs().get(i).setPrjtNo(prjtDef.getPrjtNo());
						DAOFactory.getDAO(zrprjt.dao.PrjtUsrDAO.class).insert(prjtDef.getPrjtUsrs().get(i));
					}
				}
			}

			if(prjtDef.getTasks() != null && prjtDef.getTasks().size() > 0) {
				//DAOFactory.getDAO(zrprjt.dao.TaskDAO.class).delete("delete from Task where Task.PrjtNo = '" +prjtDef.getPrjtNo()+"'");
				for(int i=0; i<prjtDef.getTasks().size(); i++) {
					Task e = prjtDef.getTasks().get(i);
					DAOFactory.getDAO(zrprjt.dao.TaskDAO.class).update(prjtDef.getTasks().get(i));
					
					TaskUpRd taskUpRd = new TaskUpRd();
					taskUpRd.setVersion(prjtDef.getTaskVersion());
					taskUpRd.setTaskId(e.getTaskNo());
					taskUpRd.setPlanDuration(e.getPlanDuration());
					taskUpRd.setPlanStaDate(e.getPlanStaDate());
					taskUpRd.setPlanOveDate(e.getPlanOveDate());
					taskUpRd.setCreateBy(e.getLastUpd());
					taskUpRd.setCreateDate(new Date());
					DAOFactory.getDAO(zrprjt.dao.TaskUpRdDAO.class).insert(taskUpRd);
				}
			}
			
			
			if(prjtDef.getPrjtPoints() != null && prjtDef.getPrjtPoints().size() > 0) {
				
					for(PrjtPoint e : prjtDef.getPrjtPoints()) {
						e.setPrjtNo(prjtDef.getPrjtNo());
						if(e.getPointId()!=null){
							DAOFactory.getDAO(zrprjt.dao.PrjtPointDAO.class).update(e);
						}else{
							DAOFactory.getDAO(zrprjt.dao.PrjtPointDAO.class).insert(e);
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
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtDefDAO.class).update(sql);
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

	public void submit(PrjtDef prjtDef) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtDefDAO.class).update(prjtDef);
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

	public void review(PrjtDef prjtDef) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtDefDAO.class).update(prjtDef);
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

	public void confirm(PrjtDef prjtDef) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtDefDAO.class).update(prjtDef);
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
			DAOFactory.getDAO(PrjtDefDAO.class).delete(sql);
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

	public void remove(PrjtDef prjtDef) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(PrjtDefDAO.class).delete(new PrjtDefHelper().getSql4Delete(prjtDef));
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

	public PrjtDef findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (PrjtDef)DAOFactory.getDAO(PrjtDefDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public PrjtDef findById(PrjtDef prjtDef) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (PrjtDef)DAOFactory.getDAO(PrjtDefDAO.class).load(prjtDef);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public PrjtDef findBy(PrjtDef prjtDef) throws Exception {
		String sql = SqlUtil.getSql4All(PrjtDefHelper.class,prjtDef,PrjtDef.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (PrjtDef)DAOFactory.getDAO(PrjtDefDAO.class).load(sql,PrjtDef.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PrjtDef> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return (List<PrjtDef>)DAOFactory.getDAO(PrjtDefDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PrjtDef> findAll(PrjtDef prjtDef) throws Exception {
		String sql = SqlUtil.getSql4All(PrjtDefHelper.class,prjtDef,PrjtDef.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<PrjtDef>)DAOFactory.getDAO(PrjtDefDAO.class).query(sql, PrjtDef.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PrjtDef> find(PrjtDef prjtDef,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(PrjtDefHelper.class,prjtDef,pageVO,PrjtDef.LIST_FIELDS);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<PrjtDef>)DAOFactory.getDAO(PrjtDefDAO.class).query(sql, PrjtDef.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PrjtDef> find(PrjtDef prjtDef) throws Exception {
//		String sql = SqlUtil.getSql4All(PrjtDefHelper.class,prjtDef,PrjtDef.LIST_FIELDS);
//		DbConnUtil.buildDbconn(1);
//		try {
//			return (List<PrjtDef>)DAOFactory.getDAO(PrjtDefDAO.class).query(sql, PrjtDef.LIST_FIELDS);
//		}
//		catch(Exception e) {
//			throw e;
//		}
//		finally {
//			DbConnUtil.closeDbconn();
//		}
		String sql="select PrjtDef.DevDeptNameID,PrjtDef.PrjtDefDocFileNo,PrjtDef.PrjtDefDocFileName,PrjtDef.PrjtTaskFileNo,PrjtDef.PrjtTaskFileName,PrjtDef.TaskVersion,PrjtDef.TypId,PrjtDef.Leve,PrjtDef.Scope,PrjtDef.Status,PrjtDef.CreateBy,PrjtDef.LastUpd,PrjtDef.PlanStaDate,PrjtDef.PlanOveDate,PrjtDef.StaDate,PrjtDef.OveDate,PrjtDef.CreateDate,PrjtDef.LastDate,PrjtDef.PrjtNo,PrjtDef.PrjtNm,PrjtDef.Remark,PrjtDef.Perce,PrjtTyp.TypNm as projtTypeNm from PrjtDef  left join PrjtTyp on (PrjtTyp.TypId = PrjtDef.TypId)  where 1=1";
		if(null!=prjtDef.getPrjtNo()){
			sql+=" and PrjtDef.PrjtNo like '%"+prjtDef.getPrjtNo()+"%'";
		}if(null!=prjtDef.getPrjtNm()){
			sql+=" and PrjtDef.PrjtNm like '%"+prjtDef.getPrjtNm()+"%'";
		}if(null!=prjtDef.getTypId()){
			sql+=" and PrjtDef.TypeId = "+prjtDef.getTypId();
		}if(null!=prjtDef.getLeve()){
			sql+=" and PrjtDef.Leve = "+prjtDef.getLeve();
		}if(null!=prjtDef.getScope()){
			sql+=" and PrjtDef.scope = "+prjtDef.getScope();
		}
		if(null!=prjtDef.getCreateBy()){
			sql+=" and PrjtDef.CreateBy = '"+prjtDef.getCreateBy()+"'";
		}if(null!=prjtDef.getStatus()){
			sql+="and PrjtDef.Status = "+ prjtDef.getStatus();
		}
		sql+=" order by PrjtDef.PrjtNo desc";
		System.out.println("sql="+sql);
		DbConnUtil.buildDbconn(1);
		try {
			return (List<PrjtDef>)DAOFactory.getDAO(PrjtDefDAO.class).query(sql, PrjtDef.LIST_FIELDS);
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
			return DAOFactory.getDAO(PrjtDefDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(PrjtDef prjtDef) throws Exception {
		DbConnUtil.buildDbconn(1);
		try {
			return DAOFactory.getDAO(PrjtDefDAO.class).amount(new PrjtDefHelper().getSql4Amount(prjtDef));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

}