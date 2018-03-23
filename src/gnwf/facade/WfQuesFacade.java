package gnwf.facade;

import gnwf.dao.QuesRespDAO;
import gnwf.dao.WfQuesDAO;
import gnwf.dao.WfRdDAO;
import gnwf.dao.WfRiskDAO;
import gnwf.dao.helper.WfQuesHelper;
import gnwf.vo.QuesResp;
import gnwf.vo.WfQues;
import gnwf.vo.WfRd;
import gnwf.vo.WfRisk;
import gnwf.ww.MSG;
import gnwf.ww.workflow.WFUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.frm.comn.GenericUtil;
import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import Utils.DateUtil;
import zrsy.dao.ChnlDAO;
import zrsy.vo.Usr;

public class WfQuesFacade {

	public void save(WfQues wfQues) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			String quesId = new ChnlDAO().callMonth2No(2, "WfQues");
			wfQues.setQuesId(quesId);
			
			//流程常见第一步 的主办人
			if(wfQues.getQuesRespList()==null||wfQues.getQuesRespList().size()==0){
				String sql = "select usr.UsrName from Usr usr where id = " + wfQues.getResponsibleIds();
				ResultSet rs = new WfQuesDAO().query(sql);
				List<QuesResp> quesRespList  = new ArrayList<QuesResp>();
				if(rs.next()) {
					QuesResp qs = new QuesResp();
					qs.setQuesId(wfQues.getQuesId());
					qs.setUsrId(Integer.valueOf(wfQues.getResponsibleIds()));
					qs.setCreateBy(wfQues.getCreateBy());
					qs.setCreateDate(new Date());
					qs.setLastUpd(wfQues.getCreateBy());
					qs.setLastUpdDate(new Date());
					qs.setRespType(MSG.QUESRESP_TYPE_NEW);
					qs.setStatus(MSG.QUESRESP_STATUS_0);
					quesRespList.add(qs);
					qs.setIdtfBy(wfQues.getCreateBy());
					wfQues.setUsrName(rs.getString("UsrName"));
				}
				wfQues.setQuesRespList(quesRespList);
			}
			
			
			DAOFactory.getDAO(WfQuesDAO.class).insert(wfQues);
			for(QuesResp e:wfQues.getQuesRespList()){
				e.setQuesId(wfQues.getQuesId());
				DAOFactory.getDAO(QuesRespDAO.class).insert(e);
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
	
/*	public String save(List<WfQues> wfQuesList,HttpServletRequest request) throws Exception {
		DbConnUtil.buildDbconn(3);
		StringBuffer impQuesIds = new StringBuffer();
		BufferedOutputStream bos = null;
		try {
			DbConnUtil.beginTransaction();
			for(WfQues wfQues : wfQuesList) {
				String quesId = new ChnlDAO().callMonth2No(2, "WfQues");
				if(wfQues.getQuesRespList()==null||wfQues.getQuesRespList().size()==0){
					List<QuesResp> quesRespList  = new ArrayList<QuesResp>();
					wfQues.setQuesId(quesId);
					QuesResp qs = new QuesResp();
					qs.setQuesId(wfQues.getQuesId());
					qs.setUsrId(Integer.valueOf(wfQues.getResponsibleIds()));
					qs.setCreateBy(wfQues.getCreateBy());
					qs.setCreateDate(new Date());
					qs.setLastUpd(wfQues.getCreateBy());
					qs.setLastUpdDate(new Date());
					qs.setRespType(MSG.QUESRESP_TYPE_NEW);
					if (wfQues.getQuesAnalysis() == null) {
						qs.setStatus(MSG.QUESRESP_STATUS_0);
					}else {
						qs.setStatus(MSG.QUESRESP_STATUS_1);
					}
					
					qs.setResult(wfQues.getQuesMeasures());
					qs.setQuesAnalysis(wfQues.getQuesAnalysis());
					quesRespList.add(qs);
					qs.setIdtfBy(wfQues.getCreateBy());
					wfQues.setQuesRespList(quesRespList);
				}
				impQuesIds.append(quesId).append(",");
				if(wfQues.getPicture() != null) {
					String relativePath = "/images/wfQues/" + DateUtil.format(new Date(), "yyyy/MM/dd");//相对路径
					String savePath = request.getSession().getServletContext().getRealPath(relativePath);//绝对路径
					File savePathFile = new File(savePath);
					if(!savePathFile.exists()) {
						savePathFile.mkdirs();
					}
					bos = new BufferedOutputStream(new FileOutputStream(new File(savePath + "/" + quesId + ".jpg")));
					bos.write(wfQues.getPicture());
					bos.flush();
					wfQues.setFileNo(relativePath);
					wfQues.setFileName(quesId + ".jpg");
				}
				DAOFactory.getDAO(WfQuesDAO.class).insert(wfQues);
				for(QuesResp e:wfQues.getQuesRespList()){
					DAOFactory.getDAO(QuesRespDAO.class).insert(e);
				}
			}
			DbConnUtil.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			impQuesIds = new StringBuffer();
			DbConnUtil.rollbackTransaction();
			throw e;
		}finally {
			if(bos != null) {
				bos.close();
			}
			DbConnUtil.closeDbconn();
		}
		impQuesIds.deleteCharAt(impQuesIds.length() - 1);
		return impQuesIds.toString();
	}*/
	
	
	public String save(List<WfQues> wfQuesList,HttpServletRequest request) throws Exception {
		DbConnUtil.buildDbconn(3);
		StringBuffer impQuesIds = new StringBuffer();
		BufferedOutputStream bos = null;
		try {
			DbConnUtil.beginTransaction();
			for(WfQues wfQues : wfQuesList) {
				String quesId = new ChnlDAO().callMonth2No(2, "WfQues");
				if(wfQues.getQuesRespList()==null||wfQues.getQuesRespList().size()==0){
					String[] strArray = null;   
					String resp = wfQues.getResponsibleIds().replace("[","").replace("]","").replace(" ","");
			        strArray = resp.split(",");
					System.out.println(strArray);
			        for(int i=0;i<strArray.length;i++){
			    
					List<QuesResp> quesRespList  = new ArrayList<QuesResp>();
					wfQues.setQuesId(quesId);
					QuesResp qs = new QuesResp();
					
					qs.setUsrId(Integer.valueOf(strArray[i]));
					//qs.setUsrId(Integer.valueOf(wfQues.getResponsibleIds()));
					
					qs.setQuesId(wfQues.getQuesId());
					qs.setCreateBy(wfQues.getCreateBy());
					qs.setCreateDate(new Date());
					qs.setLastUpd(wfQues.getCreateBy());
					qs.setLastUpdDate(new Date());
					qs.setRespType(MSG.QUESRESP_TYPE_NEW);
					if (wfQues.getQuesAnalysis() == null) {
						qs.setStatus(MSG.QUESRESP_STATUS_0);
					}else {
						qs.setStatus(MSG.QUESRESP_STATUS_1);
					}
					
					qs.setResult(wfQues.getQuesMeasures());
					qs.setQuesAnalysis(wfQues.getQuesAnalysis());
					quesRespList.add(qs);
					qs.setIdtfBy(wfQues.getIdtfBy());
					wfQues.setQuesRespList(quesRespList);
					for(QuesResp e:wfQues.getQuesRespList()){
						DAOFactory.getDAO(QuesRespDAO.class).insert(e);
					}
				}
				}
				impQuesIds.append(quesId).append(",");
				if(wfQues.getPicture() != null) {
					String relativePath = "/images/wfQues/" + DateUtil.format(new Date(), "yyyy/MM/dd");//相对路径
					String savePath = request.getSession().getServletContext().getRealPath(relativePath);//绝对路径
					File savePathFile = new File(savePath);
					if(!savePathFile.exists()) {
						savePathFile.mkdirs();
					}
					bos = new BufferedOutputStream(new FileOutputStream(new File(savePath + "/" + quesId + ".jpg")));
					bos.write(wfQues.getPicture());
					bos.flush();
					wfQues.setFileNo(relativePath);
					wfQues.setFileName(quesId + ".jpg");
				}
				DAOFactory.getDAO(WfQuesDAO.class).insert(wfQues);
				
			}
			DbConnUtil.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			impQuesIds = new StringBuffer();
			DbConnUtil.rollbackTransaction();
			throw e;
		}finally {
			if(bos != null) {
				bos.close();
			}
			DbConnUtil.closeDbconn();
		}
		impQuesIds.deleteCharAt(impQuesIds.length() - 1);
		return impQuesIds.toString();
	}

	public void update(WfQues wfQues) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfQuesDAO.class).update(wfQues);
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
	
	public void del(WfQues wfQues) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(gnwf.dao.QuesRespDAO.class).delete("delete from QuesResp where QuesResp.QuesId = '" +wfQues.getQuesId()+"'");
			DAOFactory.getDAO(gnwf.dao.WfQuesDAO.class).delete("delete from WfQues where WfQues.QuesId = '" +wfQues.getQuesId()+"'");
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
			DAOFactory.getDAO(WfQuesDAO.class).update(sql);
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

	public void submit(WfQues wfQues) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfQuesDAO.class).update(wfQues);
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

	public void review(WfQues wfQues) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfQuesDAO.class).update(wfQues);
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

	public void confirm(WfQues wfQues) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfQuesDAO.class).update(wfQues);
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
			DAOFactory.getDAO(WfQuesDAO.class).delete(sql);
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

	public void remove(WfQues wfQues) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfQuesDAO.class).delete(new WfQuesHelper().getSql4Delete(wfQues));
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

	public WfQues findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfQues)DAOFactory.getDAO(WfQuesDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfQues findById(WfQues wfQues) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			WfQues wq = (WfQues)DAOFactory.getDAO(WfQuesDAO.class).load(wfQues);
			if(wq!=null){
				String rqUsrs = wq.getRyUsrs();
				if(rqUsrs!=null && rqUsrs.length() > 0 ) {
					@SuppressWarnings("unchecked")
					List<Usr> list = (List<Usr>)DAOFactory.getDAO(zrsy.dao.UsrDAO.class).query("select UsrName from Usr WHERE Id in ("+rqUsrs+")", "UsrName");
					if(list != null && list.size() > 0) {
						StringBuffer buf = new StringBuffer();
						for(Usr u : list) {
							buf.append("," + u.getUsrName());
						}
						wq.setRyUsrNms(buf.toString().substring(1));
					}
				}
			}
			return wq;
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfQues findBy(WfQues wfQues) throws Exception {
		String sql = SqlUtil.getSql4All(WfQuesHelper.class,wfQues,WfQues.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfQues)DAOFactory.getDAO(WfQuesDAO.class).load(sql,WfQues.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfQues> find(String sql,String fields) throws Exception {
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
	}

	@SuppressWarnings("unchecked")
	public List<WfQues> findAll(WfQues wfQues) throws Exception {
		String sql = SqlUtil.getSql4All(WfQuesHelper.class,wfQues,WfQues.LIST_FIELDS);
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
	}

	@SuppressWarnings("unchecked")
	public List<WfQues> find(WfQues wfQues,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfQuesHelper.class,wfQues,pageVO,WfQues.LIST_FIELDS);
		System.out.println("`````打印条件查询sql````````"+sql);
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
	}
	
	@SuppressWarnings("unchecked")
	public List<WfQues> findmy(String conditionsql,PageVO pageVO) throws Exception {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String fields = WfQues.WFQUES_QUESRESP2+
		",(select u.UsrName from Usr u where WfQues.CreateBy = u.Id) AS CreateUsr" +
		",(select sc.SchNm from SchCfg sc where WfQues.ScheId = sc.SchId) AS SchNm" +
		",(select pu.UsrId from SchCfg sc,PrjtRole pr,PrjtUsr pu where WfQues.ScheId = sc.SchId and WfQues.PrjtNo = pu.PrjtNo and pr.PrjtTypId = sc.TypId and pr.RoleId = pu.RoleId and pr.RoleNm = 'DQA') AS RoleDQAId" +
		",(select pt.TypNm from SchCfg sc,PrjtTyp pt where WfQues.ScheId = sc.SchId and pt.TypId = sc.TypId) AS TypNm" +
		",(select pd.PrjtNm from PrjtDef pd  where pd.PrjtNo = WfQues.PrjtNo) AS PrjtNm" +
		",(select u.UsrName from Usr u where WfQues.IdtfBy = u.Id) AS IdtfUsr" +
		",(select wr.WfName from WfRd wr where WfQues.WfNo = wr.WfNo) AS WfName";
		
		
		String mstr = "select top "+pages+ " WfQues.QuesId " + conditionsql+" order by WfQues.QuesId ";
		String sql = "select top "+pageSize+" "+fields+conditionsql+" and WfQues.QuesId not in("+mstr+") "+" order by WfQues.QuesId ";
		System.out.println("~~~~~~~~~~~~~~"+sql);
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
	}
	
	@SuppressWarnings("unchecked")
	public List<WfQues> findmy(String conditionsql) throws Exception {
		String sql = "select "+WfQues.SELF_FIELDS +conditionsql;
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfQues>)DAOFactory.getDAO(WfQuesDAO.class).query(sql, WfQues.SELF_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfQues> find(WfQues wfQues) throws Exception {
		String sql = SqlUtil.getSql4All(WfQuesHelper.class,wfQues,WfQues.LIST_FIELDS);
		//String sql="select WfQues.IdtfResFileName,WfQues.ResultFileName,WfQues.IdtfResFileNo,WfQues.ResultFileNo,WfQues.QuesId,WfQues.ScheId,WfQues.TaskId,WfQues.CateId,WfQues.ComId,WfQues.DeptId,WfQues.UserId,WfQues.QuesLevel,WfQues.Status,WfQues.CreateBy,WfQues.LastUpd,WfQues.WfNo,WfQues.IsRisk,WfQues.CreateDate,WfQues.LastUpdDate,WfQues.QuesText,WfQues.Result,WfQues.Title,WfQues.RyUsrs,Usr.UsrName,WfRd.FlowId,WfQues.IdtfBy,WfQues.IdtfDate,WfQues.IdtfRes,WfRd.ProjectNo as PrjtNo,(select u.UsrName from Usr u where WfQues.CreateBy = u.Id) AS CreateUsr,(select sc.SchNm from SchCfg sc where WfQues.ScheId = sc.SchId) AS SchNm,(select pu.UsrId from SchCfg sc,PrjtRole pr,PrjtUsr pu where WfQues.ScheId = sc.SchId and WfRd.ProjectNo = pu.PrjtNo and pr.PrjtTypId = sc.TypId and pr.RoleId = pu.RoleId and pr.RoleNm = 'DQA') AS RoleDQAId,(select pt.TypNm from SchCfg sc,PrjtTyp pt where WfQues.ScheId = sc.SchId and pt.TypId = sc.TypId) AS TypNm,(select pd.PrjtNm from PrjtDef pd where pd.PrjtNo = WfRd.ProjectNo) AS PrjtNm,(select u.UsrName from Usr u where WfQues.IdtfBy = u.Id) AS IdtfUsr from WfQues  left join Usr on WfQues.UserId = Usr.Id inner join WfRd on WfQues.WfNo = WfRd.WfNo where 1=1 and WfQues.QuesId in("+wfQues.getWfId()+") order by WfQues.QuesId";
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
	}

	public int amount(String sql) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfQuesDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfQues wfQues) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			//System.out.println(new WfQuesHelper().getSql4Amount(wfQues));
			return DAOFactory.getDAO(WfQuesDAO.class).amount(new WfQuesHelper().getSql4Amount(wfQues));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public String getPrjtTaksIds(String prjtNo) throws Exception {
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
	}
	/**
	 * 更新问题和问题回复实体
	 * @param wfQues
	 * @param quesResp
	 * @throws Exception
	 */
	public void updateWfQuesAndQuesResp(WfQues wfQues,QuesResp quesResp) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfQuesDAO.class).update(wfQues);
			if(quesResp.getId() == null) {
				StringBuffer updateQuesRespSql = new StringBuffer("update QuesResp set Result = '")
				.append(quesResp.getResult()).append("',ResultDate = '").append(sdf.format(quesResp.getResultDate()))
				.append("',IdtfRes = '',Status = ").append(quesResp.getStatus()).append(",LastUpd = ").append(quesResp.getLastUpd())
				.append(",LastUpdDate = '").append(sdf.format(new Date()))
				.append("' where QuesId = ").append(quesResp.getQuesId())
				.append(" and UsrId = ").append(quesResp.getUsrId());
				DAOFactory.getDAO(QuesRespDAO.class).update(updateQuesRespSql.toString());
			}else {
				DAOFactory.getDAO(QuesRespDAO.class).update(quesResp);
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
	/**在“我的问题”导入解决措施
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void updateWfQuesAndQuesResp(List<WfQues> wfQuesList,List<QuesResp> quesRespList) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			
			for(QuesResp quesResp : quesRespList) {
				StringBuffer updateQuesRespSql = new StringBuffer("update QuesResp set Result = '")
				.append(quesResp.getResult()).append("',ResultDate = '").append(sdf.format(quesResp.getResultDate()))
				.append("',IdtfRes = '',Status = ").append(quesResp.getStatus()).append(",LastUpd = ").append(quesResp.getLastUpd())
				.append(",LastUpdDate = '").append(sdf.format(new Date()))
				.append("',QuesAnalysis = '").append(quesResp.getQuesAnalysis())
				.append("' where QuesId = '").append(quesResp.getQuesId()).append("'")
				.append(" and UsrId = ").append(quesResp.getUsrId());
				System.out.println(updateQuesRespSql.toString());
				DAOFactory.getDAO(QuesRespDAO.class).update(updateQuesRespSql.toString());
				updateQuesRespSql = null;
			}
			for(WfQues wfQues : wfQuesList) {
				String sql = "select " + QuesResp.SELF_FIELDS + " from QuesResp where RespType = 1 and quesId = '" + wfQues.getQuesId()+"'";
				List<QuesResp> quesResps = (List<QuesResp>)DAOFactory.getDAO(QuesRespDAO.class).query(sql, QuesResp.SELF_FIELDS);
				wfQues.setStatus(getWfQuesStatus(quesResps));
				DAOFactory.getDAO(WfQuesDAO.class).update(wfQues);
				sql = null;
			}
			DbConnUtil.commitTransaction();
		}catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
	
	/**在“问题管理”DQA导入解决措施
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void updateWfQuesAndQuesRespByDQA(List<WfQues> wfQuesList,List<QuesResp> quesRespList) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			
			for(QuesResp quesResp : quesRespList) {
				StringBuffer updateQuesRespSql = new StringBuffer("update QuesResp set Result = '")
				.append(quesResp.getResult()).append("',ResultDate = '").append(sdf.format(quesResp.getResultDate()))
				.append("',IdtfRes = '',Status = ").append(quesResp.getStatus()).append(",LastUpd = ").append(quesResp.getLastUpd())
				.append(",LastUpdDate = '").append(sdf.format(new Date()))
				.append("',QuesAnalysis = '").append(quesResp.getQuesAnalysis())
				.append("' where QuesId = '").append(quesResp.getQuesId()).append("'");
				System.out.println(updateQuesRespSql.toString());
				DAOFactory.getDAO(QuesRespDAO.class).update(updateQuesRespSql.toString());
				updateQuesRespSql = null;
			}
			for(WfQues wfQues : wfQuesList) {
				String sql = "select " + QuesResp.SELF_FIELDS + " from QuesResp where RespType = 1 and quesId = '" + wfQues.getQuesId()+"'";
				List<QuesResp> quesResps = (List<QuesResp>)DAOFactory.getDAO(QuesRespDAO.class).query(sql, QuesResp.SELF_FIELDS);
				wfQues.setStatus(getWfQuesStatus(quesResps));
				DAOFactory.getDAO(WfQuesDAO.class).update(wfQues);
				sql = null;
			}
			DbConnUtil.commitTransaction();
		}catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
	/**
	 * DQA和项目经理在问题管理处进行问题导入提交解决措施
	 * @param wfQues
	 * @param quesResp
	 * @throws Exception
	 */
	public void updateWfQuesAndQuesRespForFromQuesManager(WfQues wfQues,QuesResp quesResp) throws Exception{
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfQuesDAO.class).update(wfQues);
			String sql = "delete from QuesResp where QuesId = " + wfQues.getQuesId();
			DAOFactory.getDAO(QuesRespDAO.class).delete(sql);
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
	/**
	 * 
	 * @param wfQuesList
	 * @throws Exception
	 */
	public void saveList(List<WfQues> wfQuesList) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			for(WfQues wfQues : wfQuesList) {
				if(wfQues.getQuesRespList()==null||wfQues.getQuesRespList().size()==0){
					String sql = "select usr.UsrName from Usr usr where id = " + wfQues.getResponsibleIds();
					ResultSet rs = new WfQuesDAO().query(sql);
					List<QuesResp> quesRespList  = new ArrayList<QuesResp>();
					if(rs.next()) {
						QuesResp qs = new QuesResp();
						qs.setQuesId(wfQues.getQuesId());
						qs.setUsrId(Integer.valueOf(wfQues.getResponsibleIds()));
						qs.setCreateBy(wfQues.getCreateBy());
						qs.setCreateDate(new Date());
						qs.setLastUpd(wfQues.getCreateBy());
						qs.setLastUpdDate(new Date());
						qs.setRespType(MSG.QUESRESP_TYPE_NEW);
						qs.setStatus(MSG.QUESRESP_STATUS_0);
						quesRespList.add(qs);
						qs.setIdtfBy(wfQues.getCreateBy());
						wfQues.setUsrName(rs.getString("UsrName"));
					}
					wfQues.setQuesRespList(quesRespList);
				}
				DAOFactory.getDAO(WfQuesDAO.class).insert(wfQues);
				for(QuesResp e : wfQues.getQuesRespList()){
					DAOFactory.getDAO(QuesRespDAO.class).insert(e);
				}
			}
			DbConnUtil.commitTransaction();
		}
		catch(Exception e) {
			e.printStackTrace();
			DbConnUtil.rollbackTransaction();
			throw e;
		}finally {
			DbConnUtil.closeDbconn();
		}
	}
	
	public void updateQuesAndSaveRisk(WfQues wfQues,QuesResp quesResp,WfRisk wfRisk) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			String riskId = new ChnlDAO().callMonth2No(2, "WfRisk");
			wfRisk.setRiskId(riskId);
			DAOFactory.getDAO(WfQuesDAO.class).update(wfQues);
			DAOFactory.getDAO(WfRiskDAO.class).insert(wfRisk);
			DAOFactory.getDAO(QuesRespDAO.class).update(quesResp);
			DbConnUtil.commitTransaction();
		}catch(Exception e) {
			e.printStackTrace();
			DbConnUtil.rollbackTransaction();
			throw e;
		}finally {
			DbConnUtil.closeDbconn();
		}
	}
	/**
	 * 根据解决措施的状态判断问题的状态
	 * @param quesResps
	 * @return
	 * @throws Exception
	 */
	public static int getWfQuesStatus(List<QuesResp> quesResps) throws Exception {
		int quesResp_status_invalid = 0;//无效
		int quesResp_status_0 = 0;//新建
		int quesResp_status_2 = 0;//有效
		int quesResp_status_3 = 0;//挂起
		int quesResp_status_4 = 0;//退回
		for(QuesResp qr : quesResps) {
			if(qr.getStatus() == -1) {
				quesResp_status_invalid++;
			}else if(qr.getStatus() == 0) {
				quesResp_status_0++;
			}else if(qr.getStatus() == 2) {
				quesResp_status_2++;
			}else if(qr.getStatus() == 3) {
				quesResp_status_3++;
			}else if(qr.getStatus() == -2) {
				quesResp_status_4++;
			}
		}
		if(quesResp_status_3 > 0) {
			return MSG.WFQUES_STATUS_21;
		}else if(quesResp_status_invalid > 0) {
			return MSG.WFQUES_STATUS_9;
		}else if(quesResp_status_2 == quesResps.size()) {
			return MSG.WFQUES_STATUS_11;
		}else if(quesResp_status_0 == quesResps.size()) {
			return MSG.WFQUES_STATUS_1;
		}else if(quesResp_status_2 > 0 && quesResp_status_0 > 0 && (quesResp_status_2 + quesResp_status_0) == quesResps.size()) {
			return MSG.WFQUES_STATUS_1;
		}
		return MSG.WFQUES_STATUS_10;
	}

	/**
	 * 批量转交责任人
	 * @param updateQuesIdsArr 问题ID数组
	 * @param usrName 新责任人名字
	 * @param responsibleUIDArr 新责任人ID数组
	 * @param id 当前用户ID
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void bathUpdateResponers(String[] updateQuesIdsArr, String usrName,String[] responsibleUIDArr, Integer id) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			for(String quesid : updateQuesIdsArr) {
				WfQues wfQues = (WfQues)DAOFactory.getDAO(WfQuesDAO.class).load("select " + WfQues.SELF_FIELDS +
						" from WfQues where quesId = '" + quesid + "'",WfQues.SELF_FIELDS);
				wfQues.setUsrName(usrName);
				wfQues.setStatus(MSG.WFQUES_STATUS_1);
				wfQues.setLastUpd(id);
				wfQues.setLastUpdDate(new Date());
				DAOFactory.getDAO(WfQuesDAO.class).update(wfQues);
				List<QuesResp> qrs = (List<QuesResp>)DAOFactory.getDAO(QuesRespDAO.class).query("select " + QuesResp.ALL_FIELDS + 
						" from QuesResp where QuesResp.RespType = 1 and quesid = '" + quesid + "'", QuesResp.ALL_FIELDS);
				
				String oldQuesAnalysis = "";
				String oldResult = "";

				for(QuesResp qr : qrs) {
					if (qr.getQuesAnalysis() != null) {
						if (qr.getQuesAnalysis().length() != 0) {
							oldQuesAnalysis = oldQuesAnalysis + qr.getQuesAnalysis() + "--"+qr.getUsrName()+"（转交前记录）";
						}
					}
					if (qr.getResult() != null) {
						if (qr.getResult().length() != 0) {
							oldResult = oldResult + qr.getResult() + "--"+qr.getUsrName()+"（转交前记录）";
						}
					}
					
					qr.setRespType(0);
					qr.setLastUpd(id);
					qr.setLastUpdDate(new Date());
					DAOFactory.getDAO(QuesRespDAO.class).update(qr);
				}
				for(String responsibleId : responsibleUIDArr) {
					QuesResp qr = new QuesResp();
					qr.setQuesAnalysis(oldQuesAnalysis);
					qr.setResult(oldResult);
					qr.setQuesId(quesid);
					qr.setUsrId(Integer.valueOf(responsibleId));
					qr.setStatus(MSG.QUESRESP_STATUS_0);
					qr.setRespType(1);
					qr.setLastUpd(id);
					qr.setLastUpdDate(new Date());
					qr.setIdtfBy(wfQues.getCreateBy());
					qr.setCreateBy(id);
					qr.setCreateDate(new Date());
					DAOFactory.getDAO(QuesRespDAO.class).insert(qr);
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

	/**
	 * 分页查询（查询WfQues不重复的情况）
	 * @param conditionSQL 条件sql
	 * @param queryFieds 查询字段
	 * @param pageVo 分页对象
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<WfQues> getPageBean(String conditionSQL, String queryFieds,PageVO pageVo) throws Exception {
		int pageSize = pageVo.getPageSize();
		int pages = pageSize*pageVo.getPage()-pageSize;
		StringBuffer mstr = new StringBuffer("select top ")
			.append(pages)
			.append(" WfQues.QuesId ")
			.append(conditionSQL)
			.append(" order by WfQues.QuesId");
		StringBuffer sql = new StringBuffer("select top ")
			.append(pageSize)
			.append(" ")
			.append(queryFieds)
			.append(conditionSQL)
			.append(" and WfQues.QuesId not in(")
			.append(mstr)
			.append(")  order by WfQues.QuesId");
		System.out.println(sql);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfQues>)DAOFactory.getDAO(WfQuesDAO.class).query(sql.toString(), queryFieds);
		}catch(Exception e) {
			throw e;
		}finally {
			DbConnUtil.closeDbconn();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<WfQues> getPageBeanRepeat(String conditionSQL, String queryFieds,PageVO pageVo) throws Exception {
		int pageSize = pageVo.getPageSize();
		int pages = pageSize*pageVo.getPage()-pageSize;
		StringBuffer mstr = new StringBuffer("select top ")
			.append(pages)
			.append(" QuesResp.Id ")
			.append(conditionSQL)
			.append(" order by WfQues.QuesId");
		StringBuffer sql = new StringBuffer("select top ")
			.append(pageSize)
			.append(" ")
			.append(queryFieds)
			.append(conditionSQL)
			.append(" and QuesResp.Id not in(")
			.append(mstr)
			.append(")  order by WfQues.QuesId");
		System.out.println(sql);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfQues>)DAOFactory.getDAO(WfQuesDAO.class).query(sql.toString(), queryFieds);
		}catch(Exception e) {
			throw e;
		}finally {
			DbConnUtil.closeDbconn();
		}
	}
}