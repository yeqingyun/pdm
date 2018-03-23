package gnwf.facade;

import gnwf.dao.WfPpReportDAO;
import gnwf.dao.WfQuesDtlDAO;
import gnwf.vo.WfCfg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;

public class WfPpReportFacade {

	public String findAllRepor(String sql) throws Exception {
		//
		DbConnUtil.buildDbconn(1);
		try {

			Object ob = ((WfPpReportDAO) DAOFactory.getDAO(WfPpReportDAO.class))
					.queryALL(sql);
			Map map = new HashMap();
			map.put("success", true);
			map.put("data", ob);
			return JSONObject.fromObject(map).toString();
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	public Object findObject(String sql, int dbConnIndex) throws Exception {
		DbConnUtil.buildDbconn(dbConnIndex);
		try {
			return ((WfPpReportDAO) DAOFactory.getDAO(WfPpReportDAO.class))
					.queryALL(sql);
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	public String findWfPpRepByPrjtNo(String prjtNo, Integer userId)
			throws Exception {
		DbConnUtil.buildDbconn(1);
		String sql = "Select  PrjtNo, TypId ,PrjtNm ,Leve , Scope , TaskVersion , "
				+ "Perce , "
				+ "CONVERT(varchar(4), createDate,21) as staDatey , "
				+ "(select top 1 1 from UserID_PrjtNo ac where UsrId=("
				+ userId
				+ ") and ac.PrjtNo=a.PrjtNo ) remdByuser ,"
				+ "PlanStaDate,PlanOveDate , StaDate , OveDate, Remark , Status ,"
				+ " CreateBy , CreateDate, LastUpd , LastDate , PrjtDefDocFileNo ,"
				+ " PrjtDefDocFileName , PrjtTaskFileNo , "
				+ "PrjtTaskFileName , DevDeptName , "
				+ "DevDeptNameID  from PrjtDef  a where PrjtNo=('"
				+ prjtNo
				+ "')";

		// 项目本身
		Object project = findObject(sql, 3);
		// 风险统计 
		String fendxianSQL = "select  count(b.QuesId)as sumWfQues ,"
				+ "(select sum(case when a.Status='20' then 1 else 0 end) as zuofenxian  from WfQues a INNER JOIN WfRd c ON a.WfNo = c.WfNo where c.ProjectNo = d.ProjectNo) zuofenxian,"
				+ "(select sum(case when a.Status='21'  "
				+ "or a.Status = '19'or a.Status = '11' or a.Status = '10' or a.Status = '1'or a.Status = '40'then 1 else 0 end) as weiguanbi   from WfQues a INNER JOIN WfRd c ON a.WfNo = c.WfNo where c.ProjectNo = d.ProjectNo) weiguanbi,"
				+ "(select sum(case when a.Status='30' then 1 else 0 end) as guanbi from WfQues a INNER JOIN WfRd c ON a.WfNo = c.WfNo where c.ProjectNo = d.ProjectNo )  guanbi,"
				//没有数据以后要改！
				+ "(select sum(case when  a.Status = '1' then 1 else 0 end) as fendXian from WfQues a INNER JOIN WfRd c ON a.WfNo = c.WfNo where c.ProjectNo = d.ProjectNo ) fendXian,"
				+ "(select sum(case when a.Status='30' then 1 else 0 end) as gubifenxian  from WfQues a INNER JOIN WfRd c ON a.WfNo = c.WfNo where c.ProjectNo = d.ProjectNo  ) gubifenxian,"
				+ "(select sum(case when a.Status='40'  then 1 else 0 end) as weiguanfenxian from WfQues a INNER JOIN WfRd c ON a.WfNo = c.WfNo where c.ProjectNo = d.ProjectNo ) weiguanfenxian " 
				+ " from WfQues b   INNER JOIN WfRd d ON b.WfNo = d.WfNo  where d.ProjectNo=('" + prjtNo + "')"
				+ " GROUP BY d.ProjectNo  ";
		//
		Object projectFengXian = findObject(fendxianSQL, 3);
		// 总进度
		String jingSQL="select CONVERT(varchar(5), PlanStaDate,110) PlanStaDate,CONVERT(varchar(5), PlanOveDate ,110)PlanOveDate,isnull(Status,0)Status,LTRIM(RTRIM(TaskNm)) TaskNm from task where PrjtNo =  ('"+prjtNo+"') and parent=-1 order by taskno desc  ";
		List jingdu = (List)findObject(jingSQL, 3);
		List Jindulist=toRigthList(jingdu);
		
		
		
		// 时度明级
		

			//递归查询
		   	String jinduMxSQL="with stepCTE AS ( SELECT G.taskno,G.parent,g.PrjtNo,g.Status,g.planDuration, g.SchID,g.PlanStaDate,g.PlanOveDate,g.StaDate,g.OveDate,g.taskNm,1  AS Lev FROM task G "+
			" WHERE G.taskno IN (SELECT parent FROM task WHERE PrjtNo = ('"+prjtNo+"'))"+
			"  UNION ALL SELECT a.taskno,a.parent,a.PrjtNo,a.Status,a.planDuration, a.SchID,a.PlanStaDate,a.PlanOveDate,a.StaDate,a.OveDate,a.taskNm ,CTE.Lev + 1"+
			"  FROM task a inner join StepCTE CTE ON CTE.taskno = a.parent )"+
			
			//查询
			" 	SELECT    taskno,stepCTE.TaskNm,WfRd.WfNo , stepCTE.PrjtNo, stepCTE.Status,planDuration , CONVERT(varchar(10),stepCTE.PlanStaDate,23)PlanStaDate,"+
			" 	CONVERT(varchar(10),PlanOveDate,23)PlanOveDate, CONVERT(varchar(10),stepCTE.StaDate,23)StaDate,  CONVERT(varchar(10),stepCTE.OveDate,23)OveDate ,stepCTE.parent"+
			//要修改 延期怎么算，现在是实际开始时间》计划结束时间就算延期或者实际使用时间超过计划使用时间
			" ,(case when( ((datediff( dd,PlanStaDate  ,PlanOveDate)<datediff( dd,StaDate  ,OveDate))or datediff(day,PlanOveDate,StaDate)>0 )) then 1 else 0 end) as yanqi "+
			//"  ,datediff(day,PlanOveDate,StaDate) yanqi "+
			// 问题(已关闭/未关闭)
			", (cast((select  count(wq.QuesId) from Task t left JOIN WfQues wq ON wq.PrjtNo = t.PrjtNo and wq.scheID=t.SchID where  wq.Status='30' and wq.scheID = stepCTE.SchID and t.SchID = stepCTE.SchID and t.PrjtNo =('"+prjtNo+"') )  as nvarchar(100)) +'/'+cast((select  count(wq.QuesId) from Task t left JOIN WfQues wq ON wq.PrjtNo = t.PrjtNo and wq.scheID=t.SchID where  wq.Status<>'30' and wq.Status <> '0' and wq.scheID = stepCTE.SchID and t.SchID = stepCTE.SchID and t.PrjtNo = ('"+prjtNo+"') ) as nvarchar(100))) wenti"+
			//要修改 风险
			",'' fengxian ,	stepCTE.SchID "+
			" 	FROM   stepCTE  left JOIN   WfRd ON WfRd.ProjectNo =stepCTE.PrjtNo and stepCTE.SchID = WfRd.ScheID "+
			"where  stepCTE.PrjtNo = ('"+prjtNo+"') and PlanStaDate is not null "+
			"   group by  stepCTE.PrjtNo , stepCTE.SchID, WfRd.WfNo,stepCTE.PrjtNo, stepCTE.TaskNm, stepCTE.Status, stepCTE.planDuration, stepCTE.PlanStaDate,"
			+"stepCTE.PlanOveDate, stepCTE.StaDate, stepCTE.OveDate,taskno ,stepCTE.parent order by taskno";

		System.out.println(jinduMxSQL);
		Object jdMx= findObject(jinduMxSQL, 3);
		Map mapData = new HashMap();
		Map map = new HashMap();
		mapData.put("projectFengXian", projectFengXian);
		mapData.put("project", project);
		mapData.put("jingdu", Jindulist);
		mapData.put("jdMx", jdMx);
		map.put("success", true);
		map.put("data", mapData);
		return JSONObject.fromObject(map).toString();
	}

	private List toRigthList(List objList) {
		Map map7=toGetMapList(objList,"量产",0,null);
		Map map6=toGetMapList(objList,"中批试产",0,map7);
		Map map5=toGetMapList(objList,"小批试产",0,map6);
		Map map8=toZhong(map5,map6);
		Map map4=toGetMapList(objList,"开发",0,map8);
		Map map3=toGetMapList(objList,"预立项",0,map4);
		Map map2=toGetMapList(objList,"预研",0,map3);
		Map map1=toGetMapList(objList,"概念",3,null);
		List list=new ArrayList();
		list.add(map1);list.add(map2);list.add(map3);list.add(map4);list.add(map8);list.add(map7);
	    return list;
   }

	private Map toZhong(Map map5, Map map6) {
		Map map=new HashMap();
		int status1=(Integer)map5.get("status");
		int status2=(Integer) map6.get("status");
		String planstadate1=(String)map5.get("planstadate");
		String planstadate2=(String)map6.get("planstadate");
		String planovedate1=(String)map5.get("planovedate");
		String planovedate2=(String)map6.get("planovedate");
		if(planstadate1!=null){
			map.put("planstadate", planstadate1);	
		}else{
			map.put("planstadate", planstadate2);	
		}
		if(planovedate1!=null){
			map.put("planovedate", planovedate1);	
		}else{
			map.put("planovedate", planovedate2);	
		}
		if(3==status2){
			map.put("status", status2);
		}else{
			if(2==status2){
				map.put("status", status2);
			}else{
				List list=new ArrayList();
				list.add(status2);
				list.add(status1);
				Collections.sort(list);
				map.put("status", list.get(0));
			}
		}
		map.put("tasknm", "试产");
		return map;
	}

	private Map toGetMapList(List objList,String name,int defut, Map flage) {
		Map map=new HashMap();
		if(objList!=null){
			boolean isDo=false;
		  for(int i=0;i<objList.size();i++){
			 Map mapOb=(Map) objList.get(i);
			 if(mapOb.get("tasknm")!=null&&(mapOb.get("tasknm").toString().indexOf(name)>=0)){
				 map=mapOb;
				 if(flage!=null&&(2==(Integer)flage.get("status")||3==(Integer)flage.get("status")))
					{ defut=3;
			          map.put("status", defut);
			         }
			     objList.remove(i);
			     isDo=true;
			     break;
		    }
		  }
	     if(!isDo){
	    	 map.put("tasknm",name);
	    	 if(flage!=null&&(2==(Integer)flage.get("status")||3==(Integer)flage.get("status")))
				 defut=3;
			   map.put("status", defut); 
	     }
	   }else{
		   map.put("tasknm",name);
		   if(flage!=null&&(2==(Integer)flage.get("status")||3==(Integer)flage.get("status")))
			 defut=3;
		   map.put("status", defut);
	   }
	   return map;
	}

	public String shouAction(String prjtNo, Integer usrId,String actionType) throws Exception {
		//
		DbConnUtil.buildDbconn(1);
		try {
			DbConnUtil.beginTransaction();
			String sql="";
			if("add".equals(actionType)){
				//
				sql="INSERT INTO UserID_PrjtNo (UsrId,PrjtNo)" +
						" VALUES ("+usrId+",'"+prjtNo+"')";
				DAOFactory.getDAO(WfPpReportDAO.class).insert(sql);
			}else{
				sql="delete from UserID_PrjtNo where UsrId=("+usrId+") and PrjtNo=( '"+prjtNo+"')";
				DAOFactory.getDAO(WfPpReportDAO.class).delete(sql);
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
		Map map = new HashMap();
		map.put("success", true);
		map.put("data", '1');
		return  JSONObject.fromObject(map).toString();
	}

}
