package gnwf.ww.json;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import gnwf.dao.WfOveSeaUsrDAO;
import gnwf.dao.WfPpReportDAO;
import gnwf.facade.WfDeptFacade;
import gnwf.facade.WfPpReportFacade;
import gnwf.vo.WfOveSeaUsr;
import gnwf.vo.WfPpReport;
import gnwf.ww.BasicAction;
import gnwf.ww.MSG;
import net.sf.json.JSONObject;

import org.apache.commons.lang.ObjectUtils.Null;
import org.apache.log4j.Logger;
import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.omg.CORBA.Request;

import zrsy.vo.BasicGp;
import zrsy.vo.Gp;

import com.alibaba.fastjson.JSON;
import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.webwork.components.ElseIf;
import com.sun.org.apache.xpath.internal.operations.And;

public class WfPpReportAction extends BasicAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String devDeptNameID;
	private String prjtNo;
	private String  wfPpReports;
	private String actionType;
	private String prjtStatus;
	private String prjtNm;
	private String taskNm;
	private String schid;
	private String taskNo;
	private String wfNo;
	
	private String schidNo;
	
	 	
	public String execute() throws Exception {
	     wfPpReports=(String) new WfPpReportFacade().findAllRepor("select distinct devDeptNameID,devDeptName from PrjtDef where devDeptNameID is not null order by  devDeptNameID   ");
	    return PGVIE;
	}
	//获取研发部项目列表
	public String findReportByDevid()throws Exception {
		Integer userid =getUsrSession().getId();
		//判断登录人是否为”超级用户“或”不随项目变更角色“
		Boolean selAllPrjts = false;
		for (Gp e : getUsrSession().getGps()) {
			if (e.getGpName().indexOf("超级用户") > -1) {
				selAllPrjts = true;
				break;
			} else if (e.getGpName().indexOf("不随项目变更角色") > -1) {
				selAllPrjts = true;
				break;
			}
		}
		
		
		System.out.println(selAllPrjts);
		if(devDeptNameID != null & userid != null & selAllPrjts) {
			System.out.println(userid);
			wfPpReports=new WfPpReportFacade().findAllRepor("select PrjtDef.PrjtNm, PrjtDef.PrjtNo,CONVERT(varchar(4), PrjtDef.createDate,21)as staDatey FROM  PrjtDef INNER JOIN PrjtUsr ON PrjtDef.PrjtNo = PrjtUsr.PrjtNo where PrjtDef.devDeptNameID="+devDeptNameID+"    group by PrjtDef.PrjtNm,PrjtDef.PrjtNo ,PrjtDef.createDate order by staDatey desc   ");
		}else  {
			wfPpReports=new WfPpReportFacade().findAllRepor("select PrjtDef.PrjtNm ,PrjtDef.PrjtNo,CONVERT(varchar(4), PrjtDef.createDate,21)as staDatey FROM  PrjtDef INNER JOIN PrjtUsr ON PrjtDef.PrjtNo = PrjtUsr.PrjtNo where PrjtDef.devDeptNameID="+devDeptNameID+"  and dbo.PrjtUsr.status = 1  and PrjtUsr.UsrId ="+userid+" group by PrjtDef.PrjtNm,PrjtDef.PrjtNo ,PrjtDef.createDate order by staDatey desc   ");
		}
		return "ReportOneByDev";
	}
	//获取登录人收藏列表
	public String findReportByShou()throws Exception {
		Integer userId=getUsrSession().getId();
		if(userId != null) {
			wfPpReports=new WfPpReportFacade().findAllRepor("select PrjtNm, PrjtNo,CONVERT(varchar(4), createDate,21)as staDatey from PrjtDef where PrjtNo in(select PrjtNo from UserID_PrjtNo where UsrId=('"+userId+"'))  order by staDatey desc ");
			//System.out.print("sss");
		}
		return "ReportOneByDev";
	}
	//根据项目编号查询项目详细
	public String findWfPpRepByPrjtNo()throws Exception {
		if(prjtNo != null) {
			Integer usrId=getUsrSession().getId();
			wfPpReports=new WfPpReportFacade().findWfPpRepByPrjtNo( prjtNo ,usrId );
		}
		return PGLIS;
	}
	//收藏项目
	public String shouChang()throws Exception {
		if(prjtNo != null&&actionType!=null) {
			Integer usrId=getUsrSession().getId();
			wfPpReports=new WfPpReportFacade().shouAction( prjtNo ,usrId ,actionType);
		}
		return PGLIS;
	}
	
	
	
	
	
	
//android接口
	

	//获取所有项目接口findReportByDevid  andrReportlist  andrReportList
		public void andrReportlist() throws Exception {
			Integer userid =getUsrSession().getId();
			//判断登录人是否为”超级用户“或”不随项目变更角色“
			Boolean selAllPrjts = false;
			for (Gp e : getUsrSession().getGps()) {
				if (e.getGpName().indexOf("超级用户") > -1) {
					selAllPrjts = true;
					break;
				} else if (e.getGpName().indexOf("不随项目变更角色") > -1) {
					selAllPrjts = true;
					break;
				}
			}
			String selectList = "PrjtDef.DevDeptNameID,PrjtDef.PrjtDefDocFileNo,PrjtDef.PrjtDefDocFileName,PrjtDef.PrjtTaskFileNo,PrjtDef.PrjtTaskFileName,PrjtDef.TaskVersion,PrjtDef.TypId,PrjtDef.Leve,PrjtDef.Scope,PrjtDef.Status as PrjtStatus,PrjtDef.CreateBy,PrjtDef.LastUpd,convert(varchar(12) , PrjtDef.PlanStaDate , 102)as PlanStaDate,convert(varchar(12) ,PrjtDef.PlanOveDate , 102)as PlanOveDate ,convert(varchar(12) ,PrjtDef.StaDate , 102)as StaDate,convert(varchar(12) ,PrjtDef.OveDate , 102)as OveDate ,convert(varchar(12) ,PrjtDef.CreateDate , 102)as CreateDate ,convert(varchar(12) , PrjtDef.LastDate , 102)as LastDate ,PrjtDef.PrjtNo,PrjtDef.PrjtNm,PrjtDef.Remark,PrjtDef.Perce,PrjtTyp.TypNm as projtTypeNm ";
			String selectGroupList = " group by	 PrjtDef.DevDeptNameID,PrjtDef.PrjtDefDocFileNo,PrjtDef.PrjtDefDocFileName,PrjtDef.PrjtTaskFileNo,PrjtDef.PrjtTaskFileName,PrjtDef.TaskVersion,PrjtDef.TypId,PrjtDef.Leve,PrjtDef.Scope,PrjtDef.Status,PrjtDef.CreateBy,PrjtDef.LastUpd,PrjtDef.PlanStaDate,PrjtDef.PlanOveDate,PrjtDef.StaDate,PrjtDef.OveDate,PrjtDef.CreateDate,PrjtDef.LastDate,PrjtDef.PrjtNo,PrjtDef.PrjtNm,PrjtDef.Remark,PrjtDef.Perce,PrjtTyp.TypNm ";
			String myprjtList = "and  PrjtUsr.UsrId ="+userid+" ";
			System.out.println(selAllPrjts);
			String sql = "";
			DbConnUtil.buildDbconn(3);
			try {
				if(devDeptNameID != null & userid != null & selAllPrjts ) {
					sql = "select "+selectList+" from PrjtDef  left join PrjtTyp on (PrjtTyp.TypId = PrjtDef.TypId)  INNER JOIN PrjtUsr ON PrjtDef.PrjtNo = PrjtUsr.PrjtNo where PrjtDef.DevDeptNameID like '%"+devDeptNameID+"%' and PrjtDef.Status like '%"+prjtStatus+"%'  "+selectGroupList+"  order by PrjtDef.PrjtNo desc";
					setJson(JSON.toJSONString(wfPpReports));
					
				}else   {
					sql = "select "+selectList+" from PrjtDef  left join PrjtTyp on (PrjtTyp.TypId = PrjtDef.TypId)  INNER JOIN PrjtUsr ON PrjtDef.PrjtNo = PrjtUsr.PrjtNo where PrjtDef.DevDeptNameID like '%"+devDeptNameID+"%' "+myprjtList+" and PrjtDef.Status like '%"+prjtStatus+"%' "+selectGroupList+"  order by PrjtDef.PrjtNo desc";
					setJson(JSON.toJSONString(wfPpReports));
				}
					System.out.println(sql);
					Object ob = ((WfPpReportDAO) DAOFactory.getDAO(WfPpReportDAO.class))
							.queryALL(sql);
					Map map = new HashMap();
					map.put("success", true);
					map.put("data", ob);
					HttpServletResponse response = ServletActionContext.getResponse(); 
					response.setHeader("Content-type", "text/html;charset=UTF-8");  
					response.setCharacterEncoding("UTF-8");
					PrintWriter out = response.getWriter();
					out.print(JSON.toJSONString(map));
					//return JSONObject.fromObject(map).toString();
				} catch (Exception e) {
					throw e;
				} finally {
					DbConnUtil.closeDbconn();
				}
		}
		
		//android获取登录人收藏列表
		public void andrReportByShou()throws Exception {
			DbConnUtil.buildDbconn(3);
			try {
				Integer userId=getUsrSession().getId();
				String sqlByShou = "";
				String selectList = "PrjtDef.DevDeptNameID,PrjtDef.PrjtDefDocFileNo,PrjtDef.PrjtDefDocFileName,PrjtDef.PrjtTaskFileNo,PrjtDef.PrjtTaskFileName,PrjtDef.TaskVersion,PrjtDef.TypId,PrjtDef.Leve,PrjtDef.Scope,PrjtDef.Status as PrjtStatus,PrjtDef.CreateBy,PrjtDef.LastUpd,PrjtDef.PlanStaDate,PrjtDef.PlanOveDate,PrjtDef.StaDate,PrjtDef.OveDate,PrjtDef.CreateDate,PrjtDef.LastDate,PrjtDef.PrjtNo,PrjtDef.PrjtNm,PrjtDef.Remark,PrjtDef.Perce ";
				//String selectGroupList = " group by	 PrjtDef.DevDeptNameID,PrjtDef.PrjtDefDocFileNo,PrjtDef.PrjtDefDocFileName,PrjtDef.PrjtTaskFileNo,PrjtDef.PrjtTaskFileName,PrjtDef.TaskVersion,PrjtDef.TypId,PrjtDef.Leve,PrjtDef.Scope,PrjtDef.Status,PrjtDef.CreateBy,PrjtDef.LastUpd,PrjtDef.PlanStaDate,PrjtDef.PlanOveDate,PrjtDef.StaDate,PrjtDef.OveDate,PrjtDef.CreateDate,PrjtDef.LastDate,PrjtDef.PrjtNo,PrjtDef.PrjtNm,PrjtDef.Remark,PrjtDef.Perce ";
				
				if(userId != null) {
					sqlByShou =  "select "+selectList+" from dbo.PrjtDef where dbo.PrjtDef.PrjtNo in (select dbo.UserID_PrjtNo.PrjtNo from dbo.UserID_PrjtNo where dbo.UserID_PrjtNo.UsrId = '"+userId+"') ";
					setJson(JSON.toJSONString(wfPpReports));
					//sqlByShou= "  SELECT    pd.PrjtNo, pd.PrjtNm, convert(varchar(12) , pd.PlanStaDate , 102)as PlanStaDate, pd.Status as PrjtStatus,convert(varchar(12) , pd.PlanOveDate , 102)as PlanOveDate, pd.DevDeptNameID,(select count(wq.QuesId) from PrjtDef p left JOIN WfQues wq ON wq.PrjtNo = p.PrjtNo where wq.PrjtNo = w.PrjtNo) as sumWfQues FROM  PrjtDef pd INNER JOIN PrjtUsr ON pd.PrjtNo = PrjtUsr.PrjtNo left JOIN WfQues w ON w.PrjtNo = pd.PrjtNo INNER JOIN UserID_PrjtNo ON UserID_PrjtNo.PrjtNo = pd.PrjtNo where 	UserID_PrjtNo.PrjtNo in (select UserID_PrjtNo.PrjtNo from UserID_PrjtNo where  UsrId=('"+userId+"')) and pd.Status like '%"+prjtStatus+"%' and pd.PrjtNm like '%"+prjtNm+"%'	group by pd.PrjtNo, pd.PrjtNm, pd.PlanStaDate,  pd.Status, pd.PlanOveDate, pd.DevDeptNameID ,w.PrjtNo order by pd.PrjtNo desc";	
					//sqlByShou="select "+selectList+"from PrjtDef  left join PrjtTyp on (PrjtTyp.TypId = PrjtDef.TypId)  INNER JOIN PrjtUsr ON PrjtDef.PrjtNo = PrjtUsr.PrjtNo" ;
					
					//setJson(JSON.toJSONString(wfPpReports));
				}
				Object ob = ((WfPpReportDAO) DAOFactory.getDAO(WfPpReportDAO.class))
						.queryALL(sqlByShou);
				Map map = new HashMap();
				map.put("success", true);
				map.put("data", ob);
				HttpServletResponse response = ServletActionContext.getResponse(); 
				response.setHeader("Content-type", "text/html;charset=UTF-8");  
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.print(JSON.toJSONString(map));
			} catch (Exception e) {
				throw e;
			} finally {
				DbConnUtil.closeDbconn();
			}
		}
		

		
		//android获取项目详细信息 接口（项目一总览）
		public void andrDetailedReportList()throws Exception {
			System.out.println("111111111111111111");
			String sql ="";
			//总问题数
			String sumWfQues = "count(QuesId)as sumWfQues ,";
			//总风险数
			String sumWfRisk = " sum(case when WfQues.Status='20' then 1 else 0 end) as sumWfRisk ";
			//收藏
			String collect = "";
			String sumWf ="";
			String sqlList = "";
			String taskSql = "";
			String taskNameID = "(case when (schID = '43') then '1'when (schID = '2') then '2'when (schID = '12') then '3'when (schID = '18') then '4' when (schID = '19') then '4' else  0 end) as 'taskNameID'";
			Integer usrId=getUsrSession().getId();
			DbConnUtil.buildDbconn(3);
			
			try {
			
				if (prjtNo != null) {
					
					taskSql="select "+taskNameID+", SchId,isnull(Status,0)Status,LTRIM(RTRIM(TaskNm)) TaskNm from task where PrjtNo =  ('"+prjtNo+"') and parent=-1 order by taskno desc ";
					
					//统计问题数和风险数
					 sumWf = "select "+sumWfQues+sumWfRisk+" from dbo.WfQues where WfQues.PrjtNo = '" + prjtNo+"'";	
					//是否收藏状态
					collect = " select (case when count(1)>0 then 1 else 0  end) as collectStatus from dbo.UserID_PrjtNo where UsrId = "+usrId+" and PrjtNo  = '" + prjtNo+"'";
					 sqlList = "select PrjtDef.Status,convert(varchar(12) , PrjtDef.PlanStaDate , 102)as PlanStaDate,convert(varchar(12) , PrjtDef.PlanOveDate , 102)as PlanOveDate,convert(varchar(12) , PrjtDef.StaDate , 102)as StaDate ,convert(varchar(12) , PrjtDef.OveDate , 102)as OveDate ,PrjtDef.PrjtNo,PrjtDef.PrjtNm,DATEDIFF( Day, PrjtDef.PlanStaDate,PrjtDef.PlanOveDate) as downtime from PrjtDef  where  PrjtDef.PrjtNo = '" + prjtNo+"'  order by PrjtDef.PrjtNo desc ";
					 setJson(JSON.toJSONString(wfPpReports));
				}
				Object tasklist = ((WfPpReportDAO) DAOFactory.getDAO(WfPpReportDAO.class))
						.queryALL(taskSql);
				Object sumWflist = ((WfPpReportDAO) DAOFactory.getDAO(WfPpReportDAO.class))
						.queryALL(sumWf);
				Object collectlist = ((WfPpReportDAO) DAOFactory.getDAO(WfPpReportDAO.class))
						.queryALL(collect);
				Object sqlListlist = ((WfPpReportDAO) DAOFactory.getDAO(WfPpReportDAO.class))
						.queryALL(sqlList);
			
				Map map = new HashMap();
				map.put("success", true);
				map.put("tasklist", tasklist);
				map.put("sumWflist", sumWflist);
				map.put("collectlist", collectlist);
				map.put("sqlListlist", sqlListlist);
				HttpServletResponse response = ServletActionContext.getResponse(); 
				response.setHeader("Content-type", "text/html;charset=UTF-8");  
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.print(JSON.toJSONString(map));
			} catch (Exception e) {
				throw e;
			}finally {
				DbConnUtil.closeDbconn();
			}
		}
		
		
		//android获取项目详细信息 接口（项目一项目组）
		//http://localhost:8080/zrprjt/PrjtDef!projectUsers.shtml?prjtDef.prjtNo=E0014000027&prjtDef.typId=1
		//select _AddrBook.Mobile as Mobile,_AddrBook.MailAddr as MailAddr,PrjtUsr.Id,PrjtTyp.TypNm as PrjtTypNm,PrjtRole.RoleTyp as RoleTyp,PrjtUsr.PrjtTypId,PrjtUsr.RoleId,PrjtUsr.UsrId,PrjtUsr.Status,PrjtUsr.CreateBy,PrjtUsr.LastUpd,PrjtUsr.CreateDate,PrjtUsr.LastDate,PrjtUsr.PrjtNo,PrjtRole.RoleNm as RoleNm,Usr.UsrName as UsrName,PrjtUsr.Priority  from PrjtUsr  left join PrjtDef on (PrjtDef.PrjtNo = PrjtUsr.PrjtNo)  inner join PrjtRole on (PrjtRole.RoleId = PrjtUsr.RoleId)  inner join Usr on (Usr.Id = PrjtUsr.UsrId)  left join _AddrBook on (Usr.Id = _AddrBook.UserId)  left join PrjtTyp on (PrjtTyp.TypId = PrjtUsr.PrjtTypId)   where PrjtUsr.PrjtTypId = 1 and PrjtUsr.PrjtNo = 'E0014000025' and PrjtRole.Status = 1 order by PrjtUsr.RoleId 
		
		public void andrprojectUsers()throws Exception {
						
						Integer usrId=getUsrSession().getId();
						
						//DbConnUtil.buildDbconn(3);
						String sql ="";
						System.out.println("prjtNo"+prjtNo);
						DbConnUtil.buildDbconn(1);
						try {
							System.out.println(schid);
							if ( prjtNo !=null  ) {
								sql="select _AddrBook.Mobile as Mobile,_AddrBook.MailAddr as MailAddr,PrjtUsr.Id,PrjtTyp.TypNm as PrjtTypNm,PrjtRole.RoleTyp as RoleTyp,PrjtUsr.PrjtTypId,PrjtUsr.RoleId,PrjtUsr.UsrId,PrjtUsr.Status,PrjtUsr.CreateBy,PrjtUsr.LastUpd,PrjtUsr.CreateDate,PrjtUsr.LastDate,PrjtUsr.PrjtNo,PrjtRole.RoleNm as RoleNm,Usr.UsrName as UsrName,PrjtUsr.Priority  from PrjtUsr  left join PrjtDef on (PrjtDef.PrjtNo = PrjtUsr.PrjtNo)  inner join PrjtRole on (PrjtRole.RoleId = PrjtUsr.RoleId)  inner join Usr on (Usr.Id = PrjtUsr.UsrId)  left join _AddrBook on (Usr.Id = _AddrBook.UserId)  left join PrjtTyp on (PrjtTyp.TypId = PrjtUsr.PrjtTypId)   where PrjtUsr.PrjtTypId = 1 and PrjtUsr.PrjtNo ='"+ prjtNo+"'  order by PrjtUsr.RoleId ";
								
							}
							
							Object sqlList = ((WfPpReportDAO) DAOFactory.getDAO(WfPpReportDAO.class))
									.queryALL(sql);
						
							Map map = new HashMap();
							//map.put("success", true);
							map.put("success", true);
							map.put("sqlList", sqlList);
							HttpServletResponse response = ServletActionContext.getResponse(); 
							response.setHeader("Content-type", "text/html;charset=UTF-8");  
							response.setCharacterEncoding("UTF-8");
							PrintWriter out = response.getWriter();
							out.print(JSON.toJSONString(map));
						} catch (Exception e) {
							throw e;
						}finally {
							DbConnUtil.closeDbconn();
						}
					}
		
		
		//android获取项目详细信息 接口（项目一项问题）
		//http://localhost:8080/zrprjt/WfQues!wfQuesManager.shtml?PrjtNo=E0014000027
		//android收藏项目
		public void andrshouChang()throws Exception {
			Integer usrId=getUsrSession().getId();
			DbConnUtil.buildDbconn(3);
			try {
				if(prjtNo != null&&actionType!=null) {
				DbConnUtil.beginTransaction();
				String sql="";
				if("add".equals(actionType)){
					//
					sql="INSERT INTO UserID_PrjtNo (UsrId,PrjtNo)" +
							" VALUES ('"+usrId+"','"+prjtNo+"')";
					DAOFactory.getDAO(WfPpReportDAO.class).insert(sql);
				}else{
					sql="delete from UserID_PrjtNo where UsrId=('"+usrId+"') and PrjtNo=( '"+prjtNo+"')";
					DAOFactory.getDAO(WfPpReportDAO.class).delete(sql);
				}
				
				DbConnUtil.commitTransaction();
			}}
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
			HttpServletResponse response = ServletActionContext.getResponse(); 
			response.setHeader("Content-type", "text/html;charset=UTF-8");  
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(JSON.toJSONString(map));
		}
		
		
		
		//android获取项目详细信息 接口（项目一开发）
				public void andrDetailedReport()throws Exception {
					
					Integer usrId=getUsrSession().getId();
					
					DbConnUtil.buildDbconn(3);
					String sql ="";
					String schidList = "";
					String selectList = "dbo.Task.TaskNm, dbo.Task.Status, dbo.Task.planDuration,convert(varchar(12) , dbo.Task.PlanStaDate , 102)as PlanStaDate , dbo.Task.PrjtNo, dbo.WfRd.WfNo,dbo.WfRd.ScheId";
					String selectGroupByList = "dbo.Task.TaskNm, dbo.Task.Status, dbo.Task.planDuration,convert(varchar(12) , dbo.Task.PlanStaDate , 102) , dbo.Task.PrjtNo, dbo.WfRd.WfNo,dbo.WfRd.ScheId";
					String sumWfQues =" ,count( dbo.WfQues.QuesId )as sumWfQues ";
					DbConnUtil.buildDbconn(3);
					try {
						System.out.println(schid);
						if ( prjtNo !=null & schid !=null ) {
								//预研
								if ("1".equals(schid)) {
									schidList = "(dbo.WfRd.ScheId = '3'or dbo.WfRd.ScheId = '5')";
								}else if ("2".equals(schid)) {//预立项
									schidList = "(dbo.WfRd.ScheId = '6'or dbo.WfRd.ScheId = '32'or dbo.WfRd.ScheId = '33'or dbo.WfRd.ScheId = '7' or dbo.WfRd.ScheId = '8' or dbo.WfRd.ScheId = '34' or dbo.WfRd.ScheId = '35' or dbo.WfRd.ScheId = '9' or dbo.WfRd.ScheId = '36' or dbo.WfRd.ScheId = '37' or dbo.WfRd.ScheId = '10' or dbo.WfRd.ScheId = '11')";
								}else if ("3".equals(schid)) {//开发
									schidList = "(dbo.WfRd.ScheId = '14'or dbo.WfRd.ScheId = '15'or dbo.WfRd.ScheId = '16'or dbo.WfRd.ScheId = '17')";
								}else if ("4".equals(schid)) {//试产
									schidList = "('21' <=dbo.WfRd.ScheId <= '31'or dbo.WfRd.ScheId = '38'or dbo.WfRd.ScheId = '39'or dbo.WfRd.ScheId = '41'or dbo.WfRd.ScheId = '42')";
								}
							
							sql = "SELECT " +selectList+sumWfQues+" FROM  dbo.WfRd INNER JOIN dbo.Task ON dbo.WfRd.ProjectNo = dbo.Task.PrjtNo AND dbo.WfRd.ScheId = dbo.Task.SchId  left join  dbo.WfQues on  dbo.WfQues.WfNo =  dbo.WfRd.WfNo where  dbo.Task.PrjtNo ='"+prjtNo+"' and "+schidList +  " group by " + selectGroupByList + " order by  dbo.Task.TaskNm";
							System.out.println(sql);
						}
						
						Object sqlList = ((WfPpReportDAO) DAOFactory.getDAO(WfPpReportDAO.class))
								.queryALL(sql);
					
						Map map = new HashMap();
						map.put("success", true);
						map.put("success", true);
						map.put("sqlList", sqlList);
						HttpServletResponse response = ServletActionContext.getResponse(); 
						response.setHeader("Content-type", "text/html;charset=UTF-8");  
						response.setCharacterEncoding("UTF-8");
						PrintWriter out = response.getWriter();
						out.print(JSON.toJSONString(map));
					} catch (Exception e) {
						throw e;
					}finally {
						DbConnUtil.closeDbconn();
					}
				}
				
		
				//android获取项目详细信息 接口（试产PCBA-总览-步骤）

				
				
				
				public void andrTaskList()throws Exception {
					
					Integer usrId=getUsrSession().getId();
					
					DbConnUtil.buildDbconn(3);
					String listSql ="";
					String list = " dbo.PrjtDef.PrjtNm, dbo.PrjtDef.PrjtNo,dbo.WfRd.WfNo,dbo.WfRd.CreateBy ,dbo.WfRd.status  ,Usr.UsrName ";
					String stepList = " dbo.WfStep.StepName, convert(varchar(12) , dbo.WfRdTask.enddate , 102)as endDate,dbo.WfRdTask.*, dbo.Usr.UsrName as createname, Usr_1.UsrName AS acceptname ";
					String stepSql = "";
					DbConnUtil.buildDbconn(3);
					try {
						System.out.println(schid);
						if ( wfNo !=null ) {
								
							//查询总览
							listSql = "select " + list+ " FROM   dbo.WfRd INNER JOIN dbo.PrjtDef ON dbo.WfRd.ProjectNo = dbo.PrjtDef.PrjtNo inner join dbo.Usr on Usr.Id = dbo.WfRd.CreateBy  where dbo.WfRd.WfNo= '"+wfNo+"' ";
							//查询步骤
							stepSql = " select "+stepList+ " FROM  dbo.WfRdTask  left JOIN dbo.WfStep  ON dbo.WfStep.StepId = dbo.WfRdTask.StepId left JOIN dbo.Usr ON dbo.WfRdTask.CreateBy = dbo.Usr.Id left JOIN dbo.Usr AS Usr_1 ON dbo.WfRdTask.AcceptBy = Usr_1.Id where dbo.WfRdTask.WfNo= '"+wfNo+"' ";
							System.out.println(listSql);
						}
						
						Object sqlList = ((WfPpReportDAO) DAOFactory.getDAO(WfPpReportDAO.class))
								.queryALL(listSql);
						Object sqlStep = ((WfPpReportDAO) DAOFactory.getDAO(WfPpReportDAO.class))
								.queryALL(stepSql);
					
						Map map = new HashMap();
						map.put("success", true);
						map.put("success", true);
						map.put("sqlList", sqlList);
						map.put("sqlStep", sqlStep);
						HttpServletResponse response = ServletActionContext.getResponse(); 
						response.setHeader("Content-type", "text/html;charset=UTF-8");  
						response.setCharacterEncoding("UTF-8");
						PrintWriter out = response.getWriter();
						out.print(JSON.toJSONString(map));
					} catch (Exception e) {
						throw e;
					}finally {
						DbConnUtil.closeDbconn();
					}
				}

		//4.流程详情
		public void andrQuestReport()throws Exception {
			String Stagelistsql = "";
			String Stagesql = "";
			DbConnUtil.buildDbconn(3);
			try {
				if ( prjtNo !=null & taskNo !=null ) {
					Stagesql = "SELECT     t.TaskNo, t.SchId, t.PrjtNo, t.Parent, t.TaskNm, t.planDuration,convert(varchar(12) , t.PlanStaDate , 102) as PlanStaDate, convert(varchar(12) , t.PlanOveDate , 102)as PlanOveDate, convert(varchar(12) ,t.OveDate , 102) as OveDate ,convert(varchar(12) , t.StaDate , 102)as StaDate , t.Status ,(datediff(dd,t.StaDate,t.OveDate))as spentTime,(select count(wq.QuesId) from Task t left JOIN WfQues wq ON wq.PrjtNo = t.PrjtNo  and wq.ScheId = t.SchId where wq.ScheId = w.ScheId and wq.PrjtNo = w.PrjtNo ) as sumWfQues FROM         dbo.Task  t left JOIN dbo.WfQues w ON w.ScheId = t.SchId  AND w.PrjtNo = t.PrjtNo where t.PrjtNo = ('"+ prjtNo+"') and  t.taskNo = ('"+taskNo+"') and parent <> -1 group by t.TaskNo, t.SchId, t.PrjtNo, t.Parent, t.TaskNm, t.planDuration, t.PlanStaDate,  t.PlanOveDate, t.StaDate, t.OveDate, t.Status , w.ScheId ,w.prjtno ";
					Stagelistsql="SELECT     dbo.Task.TaskNm, dbo.WfQues.PrjtNo, dbo.WfQues.QuesId,dbo.WfQues.Title, dbo.WfQues.QuesText, dbo.WfQues.Status , dbo.WfQues.CateId FROM         dbo.Task INNER JOIN  dbo.WfQues ON dbo.Task.SchId = dbo.WfQues.ScheId AND dbo.Task.PrjtNo = dbo.WfQues.PrjtNo   where  dbo.WfQues.prjtno = ('"+ prjtNo+"')  and dbo.Task.taskNo = ('"+taskNo+"') order by dbo.Task.TaskNm desc";
					}
				Object Stagelist = ((WfPpReportDAO) DAOFactory.getDAO(WfPpReportDAO.class))
						.queryALL(Stagesql);
				Object Questlist = ((WfPpReportDAO) DAOFactory.getDAO(WfPpReportDAO.class))
						.queryALL(Stagelistsql);
				Map map = new HashMap();
				map.put("success", true);
				map.put("Stagelist", Stagelist);
				map.put("Questlist", Questlist);
				
				HttpServletResponse response = ServletActionContext.getResponse(); 
				response.setHeader("Content-type", "text/html;charset=UTF-8");  
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.print(JSON.toJSONString(map));
				 
			} catch (Exception e) {
				DbConnUtil.rollbackTransaction();
				throw e;
			}finally {
				DbConnUtil.closeDbconn();
			}
		}

		
		public String OveSeaUsrList1() throws Exception {
			String wfNo = (String)getRequest().getAttribute("wfRd.wfNo");
				String wfOveSeaUsrList = "";	
			DbConnUtil.buildDbconn(3);
			try {
				if (wfNo != null) {
					System.out.println("---------------"+wfNo);
					String Usrlist =  WfOveSeaUsr.LIST_FIELDS;
					System.out.println("----sql-------select   Usr.UsrName, "+ Usrlist +" from   dbo.WfOveSeaUsr INNER JOIN dbo.Usr ON dbo.WfOveSeaUsr.WfUsrID = dbo.Usr.Id where WfOveSeaUsr.WfNo = '"+wfNo+"' order by WfOveSeaUsr.WfNo");
						wfOveSeaUsrList ="select   Usr.UsrName, "+ Usrlist +" from   dbo.WfOveSeaUsr INNER JOIN dbo.Usr ON dbo.WfOveSeaUsr.WfUsrID = dbo.Usr.Id where WfOveSeaUsr.WfNo = '"+wfNo+"' order by WfOveSeaUsr.WfNo";
					
				}
				Object allUsr = ((WfOveSeaUsrDAO) DAOFactory.getDAO(WfOveSeaUsrDAO.class))
						.queryALL(wfOveSeaUsrList);
				Map map = new HashMap();
				map.put("success", true);
				map.put("allUsr", allUsr);
				
				HttpServletResponse response = ServletActionContext.getResponse(); 
				response.setHeader("Content-type", "text/html;charset=UTF-8");  
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				//out.print(JSON.toJSONString(map));
				out.println(JSONObject.fromObject(map).toString());
				return JSONObject.fromObject(map).toString();
			} catch (Exception e) {
				DbConnUtil.rollbackTransaction();
				throw e;
			}finally {
				DbConnUtil.closeDbconn();
			}
	}
		
	//get set方法
	public String getDevDeptNameID() {
		return devDeptNameID;
	}
	public void setDevDeptNameID(String devDeptNameID) {
		this.devDeptNameID = devDeptNameID;
	}
	public String getPrjtNo() {
		return prjtNo;
	}
	public void setPrjtNo(String prjtNo) {
		this.prjtNo = prjtNo;
	}
	public String getWfPpReports() {
		return wfPpReports;
	}
	public void setWfPpReports(String wfPpReports) {
		this.wfPpReports = wfPpReports;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getPrjtStatus() {
		return prjtStatus;
	}
	public void setPrjtStatus(String prjtStatus) {
		this.prjtStatus = prjtStatus;
	}
	public String getPrjtNm() {
		return prjtNm;
	}
	public void setPrjtNm(String prjtNm) {
		this.prjtNm = prjtNm;
	}
	public String getTaskNm() {
		return taskNm;
	}
	public void setTaskNm(String taskNm) {
		this.taskNm = taskNm;
	}
	public String getSchid() {
		return schid;
	}
	public void setSchid(String schid) {
		this.schid = schid;
	}
	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	public String getSchidNo() {
		return schidNo;
	}
	public void setSchidNo(String schidNo) {
		this.schidNo = schidNo;
	}
	public String getWfNo() {
		return wfNo;
	}
	public void setWfNo(String wfNo) {
		this.wfNo = wfNo;
	}
	
}
	
