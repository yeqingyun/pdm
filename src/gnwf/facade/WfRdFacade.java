package gnwf.facade;

import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrprjt.ww.json.PrjtDefAction;
import zrsy.dao.ChnlDAO;
import gnwf.dao.WfQuesDAO;
import gnwf.dao.WfRdDAO;
import gnwf.dao.helper.WfRdHelper;
import gnwf.vo.WfQues;
import gnwf.vo.WfRd;

public class WfRdFacade {

	public String save(WfRd wfRd) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			
			String wfNo = new ChnlDAO().callMonthNo(2, "WfRd"+wfRd.getFlowId());
			wfRd.setWfNo(wfNo);
			DAOFactory.getDAO(WfRdDAO.class).insert(wfRd);
//			if(wfRd.getWfDocs() != null && wfRd.getWfDocs().size() > 0) {
//				DAOFactory.getDAO(gnwf.dao.WfDocDAO.class).delete("delete from WfDoc where WfDoc.WfNo = " +wfRd.getWfNo());
//				for(int i=0; i<wfRd.getWfDocs().size(); i++) {
//					if(wfRd.getWfDocs().get(i) != null && wfRd.getWfDocs().get(i).getWfNo() != null) {
//						wfRd.getWfDocs().get(i).setWfNo(wfRd.getWfNo());
//						DAOFactory.getDAO(gnwf.dao.WfDocDAO.class).insert(wfRd.getWfDocs().get(i));
//					}
//				}
//			}
//			if(wfRd.getWfQuesRelates() != null && wfRd.getWfQuesRelates().size() > 0) {
//				DAOFactory.getDAO(gnwf.dao.WfQuesRelateDAO.class).delete("delete from WfQuesRelate where WfQuesRelate.WfNo = " +wfRd.getWfNo());
//				for(int i=0; i<wfRd.getWfQuesRelates().size(); i++) {
//					if(wfRd.getWfQuesRelates().get(i) != null && wfRd.getWfQuesRelates().get(i).getWfNo() != null) {
//						wfRd.getWfQuesRelates().get(i).setWfNo(wfRd.getWfNo());
//						DAOFactory.getDAO(gnwf.dao.WfQuesRelateDAO.class).insert(wfRd.getWfQuesRelates().get(i));
//					}
//				}
//			}
//			if(wfRd.getWfMatls() != null && wfRd.getWfMatls().size() > 0) {
//				DAOFactory.getDAO(gnwf.dao.WfMatlDAO.class).delete("delete from WfMatl where WfMatl.WfNo = " +wfRd.getWfNo());
//				for(int i=0; i<wfRd.getWfMatls().size(); i++) {
//					if(wfRd.getWfMatls().get(i) != null && wfRd.getWfMatls().get(i).getWfNo() != null) {
//						wfRd.getWfMatls().get(i).setWfNo(wfRd.getWfNo());
//						DAOFactory.getDAO(gnwf.dao.WfMatlDAO.class).insert(wfRd.getWfMatls().get(i));
//					}
//				}
//			}
//			if(wfRd.getWfRdTasks() != null && wfRd.getWfRdTasks().size() > 0) {
//				DAOFactory.getDAO(gnwf.dao.WfRdTaskDAO.class).delete("delete from WfRdTask where WfRdTask.WfNo = " +wfRd.getWfNo());
//				for(int i=0; i<wfRd.getWfRdTasks().size(); i++) {
//					if(wfRd.getWfRdTasks().get(i) != null && wfRd.getWfRdTasks().get(i).getWfNo() != null) {
//						wfRd.getWfRdTasks().get(i).setWfNo(wfRd.getWfNo());
//						DAOFactory.getDAO(gnwf.dao.WfRdTaskDAO.class).insert(wfRd.getWfRdTasks().get(i));
//					}
//				}
//			}
//			if(wfRd.getWfRdFields() != null && wfRd.getWfRdFields().size() > 0) {
//				DAOFactory.getDAO(gnwf.dao.WfRdFieldDAO.class).delete("delete from WfRdField where WfRdField.WfNo = " +wfRd.getWfNo());
//				for(int i=0; i<wfRd.getWfRdFields().size(); i++) {
//					if(wfRd.getWfRdFields().get(i) != null && wfRd.getWfRdFields().get(i).getWfNo() != null) {
//						wfRd.getWfRdFields().get(i).setWfNo(wfRd.getWfNo());
//						DAOFactory.getDAO(gnwf.dao.WfRdFieldDAO.class).insert(wfRd.getWfRdFields().get(i));
//					}
//				}
//			}
			DbConnUtil.commitTransaction();
			return wfNo;
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void update(WfRd wfRd) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdDAO.class).update(wfRd);
			if(wfRd.getWfDocs() != null && wfRd.getWfDocs().size() > 0) {
				DAOFactory.getDAO(gnwf.dao.WfDocDAO.class).delete("delete from WfDoc where WfDoc.WfNo = " +wfRd.getWfNo());
				for(int i=0; i<wfRd.getWfDocs().size(); i++) {
					if(wfRd.getWfDocs().get(i) != null && wfRd.getWfDocs().get(i).getWfNo() != null) {
						wfRd.getWfDocs().get(i).setWfNo(wfRd.getWfNo());
						DAOFactory.getDAO(gnwf.dao.WfDocDAO.class).insert(wfRd.getWfDocs().get(i));
					}
				}
			}
			if(wfRd.getWfQuesRelates() != null && wfRd.getWfQuesRelates().size() > 0) {
				DAOFactory.getDAO(gnwf.dao.WfQuesRelateDAO.class).delete("delete from WfQuesRelate where WfQuesRelate.WfNo = " +wfRd.getWfNo());
				for(int i=0; i<wfRd.getWfQuesRelates().size(); i++) {
					if(wfRd.getWfQuesRelates().get(i) != null && wfRd.getWfQuesRelates().get(i).getWfNo() != null) {
						wfRd.getWfQuesRelates().get(i).setWfNo(wfRd.getWfNo());
						DAOFactory.getDAO(gnwf.dao.WfQuesRelateDAO.class).insert(wfRd.getWfQuesRelates().get(i));
					}
				}
			}
			if(wfRd.getWfMatls() != null && wfRd.getWfMatls().size() > 0) {
				DAOFactory.getDAO(gnwf.dao.WfMatlDAO.class).delete("delete from WfMatl where WfMatl.WfNo = " +wfRd.getWfNo());
				for(int i=0; i<wfRd.getWfMatls().size(); i++) {
					if(wfRd.getWfMatls().get(i) != null && wfRd.getWfMatls().get(i).getWfNo() != null) {
						wfRd.getWfMatls().get(i).setWfNo(wfRd.getWfNo());
						DAOFactory.getDAO(gnwf.dao.WfMatlDAO.class).insert(wfRd.getWfMatls().get(i));
					}
				}
			}
			if(wfRd.getWfRdTasks() != null && wfRd.getWfRdTasks().size() > 0) {
				DAOFactory.getDAO(gnwf.dao.WfRdTaskDAO.class).delete("delete from WfRdTask where WfRdTask.WfNo = " +wfRd.getWfNo());
				for(int i=0; i<wfRd.getWfRdTasks().size(); i++) {
					if(wfRd.getWfRdTasks().get(i) != null && wfRd.getWfRdTasks().get(i).getWfNo() != null) {
						wfRd.getWfRdTasks().get(i).setWfNo(wfRd.getWfNo());
						DAOFactory.getDAO(gnwf.dao.WfRdTaskDAO.class).insert(wfRd.getWfRdTasks().get(i));
					}
				}
			}
			if(wfRd.getWfRdFields() != null && wfRd.getWfRdFields().size() > 0) {
				DAOFactory.getDAO(gnwf.dao.WfRdFieldDAO.class).delete("delete from WfRdField where WfRdField.WfNo = " +wfRd.getWfNo());
				for(int i=0; i<wfRd.getWfRdFields().size(); i++) {
					if(wfRd.getWfRdFields().get(i) != null && wfRd.getWfRdFields().get(i).getWfNo() != null) {
						wfRd.getWfRdFields().get(i).setWfNo(wfRd.getWfNo());
						DAOFactory.getDAO(gnwf.dao.WfRdFieldDAO.class).insert(wfRd.getWfRdFields().get(i));
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
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdDAO.class).update(sql);
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

	public void submit(WfRd wfRd) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdDAO.class).update(wfRd);
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

	public void review(WfRd wfRd) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdDAO.class).update(wfRd);
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

	public void confirm(WfRd wfRd) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdDAO.class).update(wfRd);
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
			DAOFactory.getDAO(WfRdDAO.class).delete(sql);
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

	public void remove(WfRd wfRd) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdDAO.class).delete(new WfRdHelper().getSql4Delete(wfRd));
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

	public WfRd findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfRd)DAOFactory.getDAO(WfRdDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfRd findById(WfRd wfRd) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfRd)DAOFactory.getDAO(WfRdDAO.class).load(wfRd);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfRd findBy(WfRd wfRd) throws Exception {
		String sql = SqlUtil.getSql4All(WfRdHelper.class,wfRd,WfRd.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfRd)DAOFactory.getDAO(WfRdDAO.class).load(sql,WfRd.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRd> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRd>)DAOFactory.getDAO(WfRdDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRd> findAll(WfRd wfRd) throws Exception {
		String sql = SqlUtil.getSql4All(WfRdHelper.class,wfRd,WfRd.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRd>)DAOFactory.getDAO(WfRdDAO.class).query(sql, WfRd.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRd> find(WfRd wfRd,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfRdHelper.class,wfRd,pageVO,WfRd.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			System.out.println(sql);
			return (List<WfRd>)DAOFactory.getDAO(WfRdDAO.class).query(sql, WfRd.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<WfRd> find4Ques(WfRd wfRd,PageVO pageVO) throws Exception {
 		String sql = SqlUtil.getSql4Pages(WfRdHelper.class,wfRd,pageVO,WfRd.TASK_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			if(WfRd.NOT_PROJECT.equals(wfRd.getProjectNo())){
				sql = sql.replace(new StringBuilder("and WfRd.ProjectNo like '%").append(WfRd.NOT_PROJECT).append("%'").toString(), "and (WfRd.ProjectNo ='' or WfRd.ProjectNo is null)");
			}
			return (List<WfRd>)DAOFactory.getDAO(WfRdDAO.class).query(sql, WfRd.TASK_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<WfRd> find4Ques(int type, String conditionsql,PageVO pageVO) throws Exception {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String fromSQL =" from WfRdTask "  
        +" left join WfRd on(WfRdTask.WfNo=WfRd.WfNo) " 
        +" left join usr on (WfRd.CreateBy = usr.id) " 
        +" left join WfStep on(WfStep.stepId=WfRdTask.stepId) "
		+" left join PrjtDef on(WfRd.ProjectNo=PrjtDef.PrjtNo) ";
		
		String mstr = "";
		String sql = "";
		if(type == 1) {
			mstr = "select top "+pages+ " WfRdTask.TaskId " +fromSQL+ " where 1=1 "+conditionsql+" order by  WfRdTask.CreateDate  desc ";
			sql = "select top "+pageSize+" "+WfRd.TASK_FIELDS+fromSQL+ " where 1=1 "+conditionsql+" and WfRdTask.TaskId not in("+mstr+") "+" order by  WfRdTask.CreateDate  desc ";
		} else {
			mstr = "select top "+pages+ " WfRdTask.TaskId " +fromSQL+ " where 1=1 "+conditionsql+" order by  WfRdTask.EndDate  desc ";
			sql = "select top "+pageSize+" "+WfRd.TASK_FIELDS+fromSQL+ " where 1=1 "+conditionsql+" and WfRdTask.TaskId not in("+mstr+") "+" order by  WfRdTask.EndDate  desc ";
		}

		System.out.println(sql);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRd>)DAOFactory.getDAO(WfRdDAO.class).query(sql, WfRd.TASK_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<WfRd> findNoPages(String conditionsql) throws Exception {
		String fromSQL =" from WfRdTask "  
        +" left join WfRd on(WfRdTask.WfNo=WfRd.WfNo) " 
        +" left join usr on (WfRd.CreateBy = usr.id) " 
        +" left join WfStep on(WfStep.stepId=WfRdTask.stepId) "
		+" left join PrjtDef on(WfRd.ProjectNo=PrjtDef.PrjtNo) ";
		
		String sql = "select  "+WfRd.TASK_FIELDS+fromSQL+ " where 1=1 "+conditionsql+"  order by WfRdTask.TaskId ";
		System.out.println(sql);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRd>)DAOFactory.getDAO(WfRdDAO.class).query(sql, WfRd.TASK_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<WfRd> find(WfRd wfRd) throws Exception {
		String sql = SqlUtil.getSql4All(WfRdHelper.class,wfRd,WfRd.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRd>)DAOFactory.getDAO(WfRdDAO.class).query(sql, WfRd.LIST_FIELDS);
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
			return DAOFactory.getDAO(WfRdDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfRd wfRd) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			String sql = new WfRdHelper().getSql4Amount(wfRd);
			if(WfRd.NOT_PROJECT.equals(wfRd.getProjectNo())){
				sql = sql.replace(new StringBuilder("and WfRd.ProjectNo like '%").append(WfRd.NOT_PROJECT).append("%'").toString(), "and (WfRd.ProjectNo ='' or WfRd.ProjectNo is null)");
			}
			return DAOFactory.getDAO(WfRdDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
	
	public int getRepeatSort(String projectNo,int scheId) throws Exception {
		try {
			WfRdDAO dao = new WfRdDAO();
			return dao.getRepeatSort(projectNo, scheId);
		}
		catch(Exception e) {
			throw e;
		}
	 }
}