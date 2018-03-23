package gnwf.facade;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;

import gnwf.dao.WfQuesDAO;
import gnwf.dao.helper.WfQuesHelper;
import gnwf.vo.WfQues;
import gnwf.vo.WfQuesTestItem;

public class WfQuesTestItemFacade extends WfQuesTestItem {

//	public WfQuesTestItem findBy(WfQuesTestItem wfQuesTestItem) throws Exception {
//		String sql = SqlUtil.getSql4All(WfQuesTestItemHelper.class,wfQuesTestItem,WfQuesTestItem.SELF_FIELDS);
//		DbConnUtil.buildDbconn(3);
//		try {
//			return (WfQuesTestItem)DAOFactory.getDAO(WfQuesTestItemDAO.class).load(sql,WfQuesTestItem.SELF_FIELDS);
//		}
//		catch(Exception e) {
//			throw e;
//		}
//		finally {
//			DbConnUtil.closeDbconn();
//		}
//	}
		
}
