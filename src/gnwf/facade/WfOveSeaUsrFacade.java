package gnwf.facade;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import com.alibaba.fastjson.JSON;
import com.opensymphony.webwork.ServletActionContext;

import zrprjt.ww.json.PrjtDefAction;
import zrsy.dao.ChnlDAO;
import gnwf.dao.WfOveSeaUsrDAO;
import gnwf.dao.WfPpReportDAO;
import gnwf.dao.WfQuesDAO;
import gnwf.dao.WfRdDAO;
import gnwf.dao.helper.WfRdHelper;
import gnwf.vo.WfOveSeaUsr;
import gnwf.vo.WfQues;
import gnwf.vo.WfRd;

public class WfOveSeaUsrFacade {

	public void save(WfOveSeaUsr oveSeaUsr) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			
			
			DAOFactory.getDAO(WfOveSeaUsrDAO.class).insert(oveSeaUsr);

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

	public WfOveSeaUsr findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfOveSeaUsr)DAOFactory.getDAO(WfOveSeaUsrDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public String findAllUsr(String sql)throws Exception  {
		//
		DbConnUtil.buildDbconn(3);
		try {

			Object allUsr = ((WfOveSeaUsrDAO) DAOFactory.getDAO(WfOveSeaUsrDAO.class))
					.queryALL(sql);
			System.out.println("222222222222"+sql);
			Map map = new HashMap();
			map.put("success", true);
			map.put("allUsr", allUsr);
			return JSONObject.fromObject(map).toString();
			
			
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}




}