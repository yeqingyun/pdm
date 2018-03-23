package gnwf.facade;

import gnwf.dao.WfDocDAO;
import gnwf.dao.WfScheCfgDocDAO;
import gnwf.dao.helper.WfDocHelper;
import gnwf.vo.WfDoc;
import gnwf.vo.WfScheCfgDoc;
import gnwf.vo.WfStep;
import gnwf.ww.workflow.WFUtil;

import java.util.ArrayList;
import java.util.List;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

import zrsy.dao.ChnlDAO;

public class WfDocFacade {

	public void saveAll(String wfNo, List<WfDoc> list) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();

			if (WFUtil.isNotNull(list)) {
				for (WfDoc d : list) {
					String docVer = new ChnlDAO().callDocV(wfNo, d.getDocName());
					d.setDocVer(docVer);
					DAOFactory.getDAO(WfDocDAO.class).insert(d);
				}
			}
			DbConnUtil.commitTransaction();
		} catch (Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void saveForVer(WfDoc wfDoc) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			String docVer = new ChnlDAO().callDocV(wfDoc.getWfNo(), wfDoc.getDocName());
			wfDoc.setDocVer(docVer);
			DAOFactory.getDAO(WfDocDAO.class).insert(wfDoc);
			DbConnUtil.commitTransaction();
		} catch (Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}
	
	public void saveForSurveyVer(WfDoc wfDoc) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			String docVer = new ChnlDAO().callSutveyDocV(wfDoc.getProjectNo(),wfDoc.getDocType());
			wfDoc.setDocVer(docVer);
			DAOFactory.getDAO(WfDocDAO.class).insert(wfDoc);
			DbConnUtil.commitTransaction();
		} catch (Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}
	
	public void saveForPrjtPointVer(WfDoc wfDoc) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			String docVer = new ChnlDAO().callPrjtPointDocV(wfDoc.getProjectNo(),wfDoc.getDocType());
			wfDoc.setDocVer(docVer);
			DAOFactory.getDAO(WfDocDAO.class).insert(wfDoc);
			DbConnUtil.commitTransaction();
		} catch (Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void savePrjtDefDoc(WfDoc wfDoc) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();

			if (null != wfDoc) {
				String docVer = new ChnlDAO().callDocV(wfDoc.getProjectNo(), wfDoc.getDocName());
				wfDoc.setDocVer(docVer);
				DAOFactory.getDAO(WfDocDAO.class).insert(wfDoc);
			}
			DbConnUtil.commitTransaction();
		} catch (Exception e) {
			DbConnUtil.rollbackTransaction();
			e.printStackTrace();
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void save(WfDoc wfDoc) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfDocDAO.class).insert(wfDoc);
			if (wfDoc.getWfDocRevs() != null && wfDoc.getWfDocRevs().size() > 0) {
				DAOFactory.getDAO(gnwf.dao.WfDocRevDAO.class).delete("delete from WfDocRev where WfDocRev.DocId = " + wfDoc.getDocId());
				for (int i = 0; i < wfDoc.getWfDocRevs().size(); i++) {
					if (wfDoc.getWfDocRevs().get(i) != null && wfDoc.getWfDocRevs().get(i).getDocId() != null) {
						wfDoc.getWfDocRevs().get(i).setDocId(wfDoc.getDocId());
						DAOFactory.getDAO(gnwf.dao.WfDocRevDAO.class).insert(wfDoc.getWfDocRevs().get(i));
					}
				}
			}

			DbConnUtil.commitTransaction();
		} catch (Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void update(WfDoc wfDoc) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfDocDAO.class).update(wfDoc);
			if (wfDoc.getWfDocRevs() != null && wfDoc.getWfDocRevs().size() > 0) {
				DAOFactory.getDAO(gnwf.dao.WfDocRevDAO.class).delete("delete from WfDocRev where WfDocRev.DocId = " + wfDoc.getDocId());
				for (int i = 0; i < wfDoc.getWfDocRevs().size(); i++) {
					if (wfDoc.getWfDocRevs().get(i) != null && wfDoc.getWfDocRevs().get(i).getDocId() != null) {
						wfDoc.getWfDocRevs().get(i).setDocId(wfDoc.getDocId());
						DAOFactory.getDAO(gnwf.dao.WfDocRevDAO.class).insert(wfDoc.getWfDocRevs().get(i));
					}
				}
			}

			DbConnUtil.commitTransaction();
		} catch (Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void update(String sql) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfDocDAO.class).update(sql);
			DbConnUtil.commitTransaction();
		} catch (Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void submit(WfDoc wfDoc) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfDocDAO.class).update(wfDoc);
			DbConnUtil.commitTransaction();
		} catch (Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void review(WfDoc wfDoc) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfDocDAO.class).update(wfDoc);
			DbConnUtil.commitTransaction();
		} catch (Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void confirm(WfDoc wfDoc) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfDocDAO.class).update(wfDoc);
			DbConnUtil.commitTransaction();
		} catch (Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void remove(String sql) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfDocDAO.class).delete(sql);
			DbConnUtil.commitTransaction();
		} catch (Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	public void remove(WfDoc wfDoc) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfDocDAO.class).delete(new WfDocHelper().getSql4Delete(wfDoc));
			DbConnUtil.commitTransaction();
		} catch (Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfDoc findById(String sql, String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfDoc) DAOFactory.getDAO(WfDocDAO.class).load(sql, fields);
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfDoc findById(WfDoc wfDoc) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfDoc) DAOFactory.getDAO(WfDocDAO.class).load(wfDoc);
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfDoc findBy(WfDoc wfDoc) throws Exception {
		String sql = SqlUtil.getSql4All(WfDocHelper.class, wfDoc, WfDoc.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfDoc) DAOFactory.getDAO(WfDocDAO.class).load(sql, WfDoc.LIST_FIELDS);
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfDoc> find(String sql, String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfDoc>) DAOFactory.getDAO(WfDocDAO.class).query(sql, fields);
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfDoc> findAll(WfDoc wfDoc) throws Exception {
		String sql = SqlUtil.getSql4All(WfDocHelper.class, wfDoc, WfDoc.LIST_FIELDS);
		System.out.println(sql);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfDoc>) DAOFactory.getDAO(WfDocDAO.class).query(sql, WfDoc.LIST_FIELDS);
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfDoc> find(WfDoc wfDoc, PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfDocHelper.class, wfDoc, pageVO, WfDoc.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfDoc>) DAOFactory.getDAO(WfDocDAO.class).query(sql, WfDoc.LIST_FIELDS);
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfDoc> find(WfDoc wfDoc) throws Exception {
		String sql = SqlUtil.getSql4All(WfDocHelper.class, wfDoc, WfDoc.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfDoc>) DAOFactory.getDAO(WfDocDAO.class).query(sql, WfDoc.LIST_FIELDS);
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(String sql) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfDocDAO.class).amount(sql);
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfDoc wfDoc) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfDocDAO.class).amount(new WfDocHelper().getSql4Amount(wfDoc));
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfDoc> find(WfDoc wfDoc, WfStep wfStep, PageVO pageVO) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			// 先查出WfScheCfgDoc, WfScheCfgDoc 有顺序
			StringBuilder sql = new StringBuilder();//sql server 分页
			sql.append("select WfScheCfgDoc.DocId,WfScheCfgDoc.DocName,row_number()over(order by WfScheCfgDoc.Sort,WfScheCfgDoc.DocId)RowNum ");
			sql.append("from WfScheCfgDoc ");
			sql.append("inner join WfCfg on WfCfg.FlowId=WfScheCfgDoc.CfgId ");
			sql.append("inner join WfRd on WfRd.FlowId=WfCfg.FlowId ");
			sql.append("where ");
			sql.append("WfScheCfgDoc.StepId=").append(wfStep.getStepId()).append(" ");
			sql.append("and WfRd.WfNo='").append(wfDoc.getWfNo()).append("' ");
			//sql.append("order by WfScheCfgDoc.Sort");
			String fields = "DocId,DocName";
			StringBuilder ssql= new StringBuilder();
			ssql.append("select t.DocId,t.DocName from (").append(sql).append(")t where t.RowNum between ").append((pageVO.getPage()-1)*pageVO.getPageSize()+1).append(" and ").append(pageVO.getPage()*pageVO.getPageSize());
			List<WfScheCfgDoc> wfScheCfgDocs = (List<WfScheCfgDoc>) DAOFactory.getDAO(WfScheCfgDocDAO.class).query(ssql.toString(), fields);

			List<WfDoc> wfDocs = new ArrayList<>(wfScheCfgDocs.size());
			StringBuilder subsql = new StringBuilder();
			subsql.append("select Max(WfDoc.DocId) as DocId ");
			subsql.append("from WfDoc ");
			subsql.append("inner join WfRd on WfRd.WfNo=WfDoc.WfNo ");
			subsql.append("inner join WfRdTask on WfRdTask.TaskId = WfDoc.TaskId and WfRdTask.WfNo = WfDoc.WfNo ");
			subsql.append("inner join WfScheCfgDoc on WfScheCfgDoc.StepId=WfRdTask.StepId and WfScheCfgDoc.CfgId=WfRd.FlowId and WfDoc.CateId=WfScheCfgDoc.DocId ");
			subsql.append("where WfDoc.Status=1 ");
			subsql.append("and WfRdTask.StepId=").append(wfStep.getStepId()).append(" ");
			subsql.append("and WfRdTask.WfNo='").append(wfDoc.getWfNo()).append("' ");
			for (int i = 0; i < wfScheCfgDocs.size(); i++) {
				// 得到 wfScheCfgDoc 组中, 最后添加的记录
				WfScheCfgDoc wfScheCfgDoc = wfScheCfgDocs.get(i);
				StringBuilder fsubsql = new StringBuilder(subsql);
				fsubsql.append("and WfScheCfgDoc.DocId=").append(wfScheCfgDoc.getDocId()).append(" ");
				WfDoc doc = (WfDoc) DAOFactory.getDAO(WfDocDAO.class).load(fsubsql.toString(), "WfDoc.DocId");
				if (doc == null||doc.getDocId()==null||doc.getDocId().intValue()==0) {
					doc = new WfDoc();
					doc.setCateName(wfScheCfgDoc.getDocName());
					doc.setCateId(wfScheCfgDoc.getDocId());
				} else {
					doc = (WfDoc) DAOFactory.getDAO(WfDocDAO.class).load(doc);
				}
				wfDocs.add(doc);
			}
			return wfDocs;
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfDoc wfDoc, WfStep wfStep) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select count(*) as amount ");
			sql.append("from WfScheCfgDoc ");
			//sql.append("inner join WfCfg on WfCfg.FlowId=WfScheCfgDoc.CfgId ");
			//sql.append("inner join WfRd on WfRd.FlowId=WfCfg.FlowId ");
			sql.append("where ");
			sql.append("WfScheCfgDoc.StepId=").append(wfStep.getStepId()).append(" ");
			//sql.append("and WfRd.WfNo='").append(wfDoc.getWfNo()).append("' ");
			return DAOFactory.getDAO(WfScheCfgDocDAO.class).amount(sql.toString());
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnUtil.closeDbconn();
		}
	}
}