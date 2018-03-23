package gnwf.facade;

import gnwf.dao.WfRdDAO;
import gnwf.dao.WfRdFieldDAO;
import gnwf.dao.helper.WfRdFieldHelper;
import gnwf.vo.WfRd;
import gnwf.vo.WfRdField;
import gnwf.ww.MSG;
import gnwf.ww.workflow.B8MatlImpoRude;
import gnwf.ww.workflow.Row;
import gnwf.ww.workflow.WFUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.frm.dao.DAOFactory;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlUtil;
import org.frm.vo.PageVO;

public class WfRdFieldFacade {
	
	private WfRdFieldDAO dao;
	private WfRdDAO wfRdDao;
	
	public void saveAll(List<WfRdField> list,String wfNo) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			
			if(WFUtil.isNotNull(list)){
				List<WfRdField> all = new ArrayList<WfRdField>();
				Set<Integer> set = new HashSet<Integer>();
				WfRdFieldDAO dao = new WfRdFieldDAO();
				
				for(int i=0; i<list.size();i++) {
					if(list.get(i)!=null && list.get(i).getFieldId()!=null){
						WfRdField field = list.get(i);
						set.add(field.getFieldId());
						
						if(WFUtil.isNotNull(field.getFieldText()) || field.getRowId()!=0){
							field.setWfNo(wfNo);
							field.setFieldText(field.getFieldText().trim());
							all.add(field);
						}
					}
				}
				
				if(set.size()>0){
					StringBuffer buf = new StringBuffer("delete from WfRdField where WfNo='"+wfNo+"' and fieldId in (");
					Iterator<Integer> iter = set.iterator();
					while(iter.hasNext()){
						buf.append(iter.next()+",");
					}
					String str = buf.substring(0, buf.length()-1) + ");";
					dao.delete(str);						//删除
				}
				
				if(WFUtil.isNotNull(all)){
					for(int i=0; i<all.size();i++) {
						dao.insert(all.get(i));				//插入
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
	
	public void saveByDelRow(List<WfRdField> list,String wfNo) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			
			if(WFUtil.isNotNull(list)){
				List<WfRdField> all = new ArrayList<WfRdField>();
				StringBuffer buf = new StringBuffer();
				WfRdFieldDAO dao = new WfRdFieldDAO();
				
				for(int i=0; i<list.size();i++) {
					if(list.get(i)!=null && list.get(i).getFieldId()!=null){
						WfRdField field = list.get(i);
						buf.append("delete from WfRdField where WfNo='"+wfNo+"' " +
								" and fieldId="+field.getFieldId()+" and rowId="+field.getRowId()+";");	
						
						if(WFUtil.isNotNull(field.getFieldText()) || field.getRowId()!=0){
							field.setWfNo(wfNo);
							field.setFieldText(field.getFieldText().trim());
							all.add(field);
						}
					}
				}
				
				if(buf.length()>0){
					dao.delete(buf.toString());				//删除
				}
				
				if(WFUtil.isNotNull(all)){
					for(int i=0; i<all.size();i++) {
						dao.insert(all.get(i));				//插入
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
	
	public void saveFor14(List<WfRdField> list,String wfNo) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			
			if(WFUtil.isNotNull(list)){
				List<WfRdField> all = new ArrayList<WfRdField>();
				Set<Integer> set = new HashSet<Integer>();
				WfRdFieldDAO dao = new WfRdFieldDAO();
				
				for(int i=0; i<list.size();i++) {
					if(list.get(i)!=null && list.get(i).getFieldId()!=null){
						WfRdField field = list.get(i);
						
						if(WFUtil.isNotNull(field.getFieldText())){
							set.add(field.getFieldId());
							
							field.setWfNo(wfNo);
							field.setFieldText(field.getFieldText().trim());
							all.add(field);
						}
					}
				}
				
				if(set.size()>0){					//删除
					StringBuffer buf = new StringBuffer("delete from WfRdField where WfNo='"+wfNo+"' and fieldId in (");
					Iterator<Integer> iter = set.iterator();
					while(iter.hasNext()){
						buf.append(iter.next()+",");
					}
					String str = buf.substring(0, buf.length()-1) + ");";
					dao.delete(str);						
				}
				
				if(WFUtil.isNotNull(all)){			//插入
					for(int i=0; i<all.size();i++) {
						dao.insert(all.get(i));
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
	
	public List<WfRd> saveForA30(List<WfRdField> fieldContents,String wfNo)throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
		
			List<WfRd> result = new ArrayList<WfRd>();
			Set<Integer> set = new HashSet<Integer>();
			dao = new WfRdFieldDAO();
			
			for(int i=0; i<fieldContents.size();i++) {
				WfRdField field = fieldContents.get(i);
				if(field!=null && field.getFieldId()!=null){
					set.add(field.getFieldId());
				}
			}
			if(set.size()>0){
				System.out.println("set.size()>0");
				StringBuffer buf = new StringBuffer("delete from WfRdField where WfNo='"+wfNo+"' and fieldId in (");
				Iterator<Integer> iter = set.iterator();
				while(iter.hasNext()){
					buf.append(iter.next()+",");
				}
				String str = buf.substring(0, buf.length()-1) + ");";
				dao.delete(str);				//删除
			}
			
			
			
			List<Row> rows = WFUtil.genFieldRows(fieldContents);
			if(WFUtil.isNotNull(rows)){
				System.out.println("WFUtil.isNotNull(rows)");
				for(int i=0;i<rows.size();i++){
					List<WfRdField> list = rows.get(i).getFileds();
					
					String mtart = findValue(list, 989); //物料类型E1
					String matkl = findValue(list, 990); //物料组E2
					String maktx = findValue(list, 992); //物料描述E4
					String groes = findValue(list, 993); //大小量纲E5
					
					/*String mtart = findValue(list, 960); //物料类型E1
					String matkl = findValue(list, 961); //物料组E2
					String maktx = findValue(list, 963); //物料描述E4
					String groes = findValue(list, 964); //大小量纲E5
*/	
					//if(WFUtil.isNotNull(mtart) && WFUtil.isNotNull(matkl) && WFUtil.isNotNull(maktx)){
					if(WFUtil.isNotNull(mtart) && WFUtil.isNotNull(matkl) && WFUtil.isNotNull(maktx)){
						//List<WfRd> wfrds = check(mtart,matkl,maktx,groes,wfNo);
						List<WfRd> wfrds = checkNew(mtart,matkl,maktx,groes,wfNo,rows);
						if(WFUtil.isNotNull(wfrds)){	//存在重复
							result.addAll(wfrds);
						}else{
							System.out.println("saveFields");
							saveFields(rows.get(i).getFileds(),wfNo);
						}
					}
				}
			}
		
			DbConnUtil.commitTransaction();
			
			return result;
		}
		catch(Exception e) {
			DbConnUtil.rollbackTransaction();
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
	
	//保存扩展字段值
	protected void saveFields(List<WfRdField> all,String wfNo) throws SQLException {
		if(WFUtil.isNotNull(all)){
			System.out.println("saveFields里面");
			for(int i=0; i<all.size();i++) {
				WfRdField f = all.get(i);
				if(f!=null){
					f.setWfNo(wfNo);
					if(f.getFieldText()!=null){
						f.setFieldText(f.getFieldText().trim());
					}
					dao.insert(f);				//插入
				}
			}
		}
	}
	
	//物料类型E1, 物料组E2 ,物料描述E4,大小量纲E5
	protected List<WfRd> check(String mtart,String matkl,String maktx,String groes,String wfNo) throws Exception {
		List<WfRd> result = null;
		if(wfRdDao==null){
			wfRdDao = new WfRdDAO();
		}
		System.out.println(wfNo);
		B8MatlImpoRude rude = new B8MatlImpoRude();
		if(rude.isInGroup(matkl)){			//存在物料组中则不判断
			return null;
		}
		if(WFUtil.isNotNull(maktx)){
			maktx = maktx.trim().replaceAll("\"", "").replaceAll("'", "");
		}
		if(WFUtil.isNotNull(groes)){
			groes = groes.trim().replaceAll("\"", "").replaceAll("'", "");
		}
		if (rude.isIn181Group(matkl)) {
			if (WFUtil.isNull(groes)) {//如果181开头，物料大小量纲不能为空，是null的直接过滤掉
				String sql = "select distinct wfno from wfrdfield where wfrdfield.wfNo ='"+wfNo+"'";
				System.out.println(sql);
					result = wfRdDao.query(sql,"WfRd.WfNo");
			}else {
				if(rude.isBothUni(matkl)){
					String sql ="select distinct wfrdfield.wfno from wfrdfield," 
						+ "(select wfno,rowId from wfrdfield where fieldid=992 and fieldtext='"+maktx+"')a " 
						+ " where fieldid=993 and fieldtext='"+groes+"' and a.wfno=wfrdfield.wfno and a.rowId = wfrdfield.rowId " 
						+ " and wfrdfield.wfno not in(select distinct wfno from wfrd where wfno like 'B50%' and status="+MSG.OWFRD_STATUS_4+")";
					/*if(wfNo!=null){
						sql= sql+" and wfrdfield.wfNo!='"+wfNo+"'";
					}*/
					System.out.println("sql~~~~~~~~"+sql);
					result = wfRdDao.query(sql,"WfRd.WfNo");
				}
			}
		} else if (!rude.isInCageGroup(matkl)) {
			if(rude.isBothUni(matkl)){
				String sql ="select distinct wfrdfield.wfno from wfrdfield," 
					+ "(select wfno,rowId from wfrdfield where fieldid=992 and fieldtext='"+maktx+"')a " 
					+ " where fieldid=993 and fieldtext='"+groes+"' and a.wfno=wfrdfield.wfno and a.rowId = wfrdfield.rowId " 
					+ " and wfrdfield.wfno not in(select distinct wfno from wfrd where wfno like 'B50%' and status="+MSG.OWFRD_STATUS_4+")";
				/*if(wfNo!=null){
					sql= sql+" and wfrdfield.wfNo!='"+wfNo+"'";
				}*/
				System.out.println("sql~~~~~~~~"+sql);
				result = wfRdDao.query(sql,"WfRd.WfNo");
			}
		} else {
			if (WFUtil.isNull(groes)) {//如果1和2开头，物料大小量纲不能为空，是null的直接过滤掉
				System.out.println(wfNo);
				String sql = "select distinct wfno from wfrdfield where wfrdfield.wfNo ='"+wfNo+"'";

					System.out.println(sql);
						result = wfRdDao.query(sql,"WfRd.WfNo");
			}
			if(rude.isMaktxUni(matkl)){	//maktx 不可重复
				String sql = "select distinct wfno from wfrdfield where fieldid=992 and fieldtext='"+maktx+"' " +
						" and wfno not in(select distinct wfno from wfrd where wfno like 'B50%' and status="+MSG.OWFRD_STATUS_4+")";
				if(wfNo!=null){
					sql= sql+" and wfrdfield.wfNo!='"+wfNo+"'";
				}
				result = wfRdDao.query(sql,"WfRd.WfNo");
				if(WFUtil.isNotNull(result)){
					return result;
				}
			}
			
			if(rude.isGroesUni(matkl)){	//groes 不可重复
				if(groes!=null && !"".equals(groes)){
					System.out.println("groesgroesgroesgroes1111~~~"+groes);
					String sql = "select distinct wfno from wfrdfield where fieldid=993 and fieldtext='"+groes+"' " +
						" and wfno not in(select distinct wfno from wfrd where wfno like 'B50%' and status="+MSG.OWFRD_STATUS_4+")";
					if(wfNo!=null){
						sql= sql+" and wfrdfield.wfNo!='"+wfNo+"'";
					}
					result = wfRdDao.query(sql,"WfRd.WfNo");
				}
			}
		}
		
		/*if(!rude.isInCageGroup(matkl)){		//不存在物料类型组-两合起来不可重复
			if (WFUtil.isNull(groes)&&rude.isIn181Group(matkl)) {//如果181开头，物料大小量纲不能为空，是null的直接过滤掉
					String sql = "select distinct wfno from wfrdfield where wfrdfield.wfNo ='"+wfNo+"'";
					System.out.println(sql);
						result = wfRdDao.query(sql,"WfRd.WfNo");
			}
			
			if(rude.isBothUni(matkl)){
				String sql ="select distinct wfrdfield.wfno from wfrdfield," 
					+ "(select wfno,rowId from wfrdfield where fieldid=992 and fieldtext='"+maktx+"')a " 
					+ " where fieldid=993 and fieldtext='"+groes+"' and a.wfno=wfrdfield.wfno and a.rowId = wfrdfield.rowId " 
					+ " and wfrdfield.wfno not in(select distinct wfno from wfrd where wfno like 'B50%' and status="+MSG.OWFRD_STATUS_4+")";
				if(wfNo!=null){
					sql= sql+" and wfrdfield.wfNo!='"+wfNo+"'";
				}
				System.out.println("sql~~~~~~~~"+sql);
				result = wfRdDao.query(sql,"WfRd.WfNo");
			}
		}else{								//存在物料类型组-按规则走
			if (WFUtil.isNull(groes)) {//如果1和2开头，物料大小量纲不能为空，是null的直接过滤掉
				System.out.println(wfNo);
				String sql = "select distinct wfno from wfrdfield where wfrdfield.wfNo ='"+wfNo+"'";

					System.out.println(sql);
						result = wfRdDao.query(sql,"WfRd.WfNo");
			}
			if(rude.isMaktxUni(matkl)){	//maktx 不可重复
				String sql = "select distinct wfno from wfrdfield where fieldid=992 and fieldtext='"+maktx+"' " +
						" and wfno not in(select distinct wfno from wfrd where wfno like 'B50%' and status="+MSG.OWFRD_STATUS_4+")";
				if(wfNo!=null){
					sql= sql+" and wfrdfield.wfNo!='"+wfNo+"'";
				}
				result = wfRdDao.query(sql,"WfRd.WfNo");
				if(WFUtil.isNotNull(result)){
					return result;
				}
			}
			
			if(rude.isGroesUni(matkl)){	//groes 不可重复
				if(groes!=null && !"".equals(groes)){
					System.out.println("groesgroesgroesgroes1111~~~"+groes);
					String sql = "select distinct wfno from wfrdfield where fieldid=993 and fieldtext='"+groes+"' " +
						" and wfno not in(select distinct wfno from wfrd where wfno like 'B50%' and status="+MSG.OWFRD_STATUS_4+")";
					if(wfNo!=null){
						sql= sql+" and wfrdfield.wfNo!='"+wfNo+"'";
					}
					result = wfRdDao.query(sql,"WfRd.WfNo");
				}
			}
		}*/
		
		return result;
	}
	
	//物料类型E1, 物料组E2 ,物料描述E4,大小量纲E5
	protected List<WfRd> checkNew(String mtart,String matkl,String maktx,String groes,String wfNo,List<Row> rows) throws Exception {
		List<WfRd> result = null;
		if(wfRdDao==null){
			wfRdDao = new WfRdDAO();
		}
		System.out.println(wfNo);
		B8MatlImpoRude rude = new B8MatlImpoRude();
		if(rude.isInGroup(matkl)){			//存在物料组中则不判断
			return null;
		}
		if(WFUtil.isNotNull(maktx)){
			maktx = maktx.trim().replaceAll("\"", "").replaceAll("'", "");
		}
		if(WFUtil.isNotNull(groes)){
			groes = groes.trim().replaceAll("\"", "").replaceAll("'", "");
		}
		if (rude.isIn181Group(matkl)) { //181开头的电子料
			if (WFUtil.isNull(groes)) {//如果181开头，物料大小量纲不能为空，是null的直接过滤掉
				String sql = "select distinct wfno from wfrdfield where wfrdfield.wfNo ='"+wfNo+"'";
				System.out.println(sql);
					result = wfRdDao.query(sql,"WfRd.WfNo");
			}else {
				if(rude.isBothUni(matkl)){
					//与excel中的数据进行比较
					int countSame = 0;
					for(int i=0;i<rows.size();i++){
						List<WfRdField> list = rows.get(i).getFileds();
						String maktxNew = findValue(list, 992); //物料描述E4
						String groesNew = findValue(list, 993); //大小量纲E5
						
						if(maktx.equals(maktxNew) && groes.equals(groesNew)) {
							countSame++;
						}
					}
					if(countSame > 1) {
						WfRd wfrd = new WfRd();
						wfrd.setWfNo(wfNo+"xlsSame");
						List<WfRd> xlsSame = new ArrayList<WfRd>();				
						xlsSame.add(wfrd);
						return xlsSame;
					}
					
					String sql ="select distinct wfrdfield.wfno from wfrdfield," 
						+ "(select wfno,rowId from wfrdfield where fieldid=992 and fieldtext='"+maktx+"')a " 
						+ " where fieldid=993 and fieldtext='"+groes+"' and a.wfno=wfrdfield.wfno and a.rowId = wfrdfield.rowId " 
						+ " and wfrdfield.wfno not in(select distinct wfno from wfrd where wfno like 'B50%' and status="+MSG.OWFRD_STATUS_4+")";
					/*if(wfNo!=null){
						sql= sql+" and wfrdfield.wfNo!='"+wfNo+"'";
					}*/
					System.out.println("sql~~~~~~~~"+sql);
					result = wfRdDao.query(sql,"WfRd.WfNo");
				}
			}
		} else if (!rude.isInCageGroup(matkl)) { //不是1和2开头的电子料
			if(rude.isBothUni(matkl)){
				//与excel中的数据进行比较
				int countSame = 0;
				for(int i=0;i<rows.size();i++){
					List<WfRdField> list = rows.get(i).getFileds();
					String maktxNew = findValue(list, 992); //物料描述E4
					String groesNew = findValue(list, 993); //大小量纲E5
					
					if(maktx.equals(maktxNew) && groes.equals(groesNew)) {
						countSame++;
					}
				}
				if(countSame > 1) {
					WfRd wfrd = new WfRd();
					wfrd.setWfNo(wfNo+"xlsSame");
					List<WfRd> xlsSame = new ArrayList<WfRd>();				
					xlsSame.add(wfrd);
					return xlsSame;
				}
				
				String sql ="select distinct wfrdfield.wfno from wfrdfield," 
					+ "(select wfno,rowId from wfrdfield where fieldid=992 and fieldtext='"+maktx+"')a " 
					+ " where fieldid=993 and fieldtext='"+groes+"' and a.wfno=wfrdfield.wfno and a.rowId = wfrdfield.rowId " 
					+ " and wfrdfield.wfno not in(select distinct wfno from wfrd where wfno like 'B50%' and status="+MSG.OWFRD_STATUS_4+")";
				/*if(wfNo!=null){
					sql= sql+" and wfrdfield.wfNo!='"+wfNo+"'";
				}*/
				System.out.println("sql~~~~~~~~"+sql);
				result = wfRdDao.query(sql,"WfRd.WfNo");
			}
		} else { // 1和2开头的电子料
			//1899物料组排除在外,无任何限制，可以重复
			if(matkl.equals("1899")) {
				return null;
			}
			
			if (WFUtil.isNull(groes)) {//如果1和2开头，物料大小量纲不能为空，是null的直接过滤掉
				System.out.println(wfNo);
				String sql = "select distinct wfno from wfrdfield where wfrdfield.wfNo ='"+wfNo+"'";

					System.out.println(sql);
						result = wfRdDao.query(sql,"WfRd.WfNo");
			}
			
			if(rude.isMaktxUni(matkl)){	//maktx 不可重复
				String sql = "select distinct wfno from wfrdfield where fieldid=992 and fieldtext='"+maktx+"' " +
						" and wfno not in(select distinct wfno from wfrd where wfno like 'B50%' and status="+MSG.OWFRD_STATUS_4+")";
				if(wfNo!=null){
					sql= sql+" and wfrdfield.wfNo!='"+wfNo+"'";
				}
				result = wfRdDao.query(sql,"WfRd.WfNo");
				if(WFUtil.isNotNull(result)){
					return result;
				}
			}
			
			if(rude.isGroesUni(matkl)){	//groes 不可重复
				//与excel中的数据进行比较
				int countSame = 0;
				for(int i=0;i<rows.size();i++){
					List<WfRdField> list = rows.get(i).getFileds();
					String groesNew = findValue(list, 993); //大小量纲E5
					
					if(groes.equals(groesNew)) {
						countSame++;
					}
				}
				if(countSame > 1) {
					WfRd wfrd = new WfRd();
					wfrd.setWfNo(wfNo+"xlsSame");
					List<WfRd> xlsSame = new ArrayList<WfRd>();				
					xlsSame.add(wfrd);
					return xlsSame;
				}
				
				if(groes!=null && !"".equals(groes)){
					System.out.println("groesgroesgroesgroes1111~~~"+groes);
					String sql = "select distinct wfno from wfrdfield where fieldid=993 and fieldtext='"+groes+"' " +
						" and wfno not in(select distinct wfno from wfrd where wfno like 'B50%' and status="+MSG.OWFRD_STATUS_4+")";
					if(wfNo!=null){
						sql= sql+" and wfrdfield.wfNo!='"+wfNo+"'";
					}
					result = wfRdDao.query(sql,"WfRd.WfNo");
				}
			}
		}
		
		/*if(!rude.isInCageGroup(matkl)){		//不存在物料类型组-两合起来不可重复
			if (WFUtil.isNull(groes)&&rude.isIn181Group(matkl)) {//如果181开头，物料大小量纲不能为空，是null的直接过滤掉
					String sql = "select distinct wfno from wfrdfield where wfrdfield.wfNo ='"+wfNo+"'";
					System.out.println(sql);
						result = wfRdDao.query(sql,"WfRd.WfNo");
			}
			
			if(rude.isBothUni(matkl)){
				String sql ="select distinct wfrdfield.wfno from wfrdfield," 
					+ "(select wfno,rowId from wfrdfield where fieldid=992 and fieldtext='"+maktx+"')a " 
					+ " where fieldid=993 and fieldtext='"+groes+"' and a.wfno=wfrdfield.wfno and a.rowId = wfrdfield.rowId " 
					+ " and wfrdfield.wfno not in(select distinct wfno from wfrd where wfno like 'B50%' and status="+MSG.OWFRD_STATUS_4+")";
				if(wfNo!=null){
					sql= sql+" and wfrdfield.wfNo!='"+wfNo+"'";
				}
				System.out.println("sql~~~~~~~~"+sql);
				result = wfRdDao.query(sql,"WfRd.WfNo");
			}
		}else{								//存在物料类型组-按规则走
			if (WFUtil.isNull(groes)) {//如果1和2开头，物料大小量纲不能为空，是null的直接过滤掉
				System.out.println(wfNo);
				String sql = "select distinct wfno from wfrdfield where wfrdfield.wfNo ='"+wfNo+"'";

					System.out.println(sql);
						result = wfRdDao.query(sql,"WfRd.WfNo");
			}
			if(rude.isMaktxUni(matkl)){	//maktx 不可重复
				String sql = "select distinct wfno from wfrdfield where fieldid=992 and fieldtext='"+maktx+"' " +
						" and wfno not in(select distinct wfno from wfrd where wfno like 'B50%' and status="+MSG.OWFRD_STATUS_4+")";
				if(wfNo!=null){
					sql= sql+" and wfrdfield.wfNo!='"+wfNo+"'";
				}
				result = wfRdDao.query(sql,"WfRd.WfNo");
				if(WFUtil.isNotNull(result)){
					return result;
				}
			}
			
			if(rude.isGroesUni(matkl)){	//groes 不可重复
				if(groes!=null && !"".equals(groes)){
					System.out.println("groesgroesgroesgroes1111~~~"+groes);
					String sql = "select distinct wfno from wfrdfield where fieldid=993 and fieldtext='"+groes+"' " +
						" and wfno not in(select distinct wfno from wfrd where wfno like 'B50%' and status="+MSG.OWFRD_STATUS_4+")";
					if(wfNo!=null){
						sql= sql+" and wfrdfield.wfNo!='"+wfNo+"'";
					}
					result = wfRdDao.query(sql,"WfRd.WfNo");
				}
			}
		}*/
		
		return result;
	}
	
	
	//桌面的校验测试物料类型E1, 物料组E2 ,物料描述E4,大小量纲E5
		protected List<WfRd> checkjson(String mtart,String matkl,String maktx,String groes,String wfNo) throws Exception {
			List<WfRd> result = null;
			if(wfRdDao==null){
				wfRdDao = new WfRdDAO();
			}
			System.out.println(wfNo);
			B8MatlImpoRude rude = new B8MatlImpoRude();
			if(rude.isInGroup(matkl)){			//存在物料组中则不判断
				return null;
			}
			if(WFUtil.isNotNull(maktx)){
				maktx = maktx.trim().replaceAll("\"", "").replaceAll("'", "");
			}
			if(WFUtil.isNotNull(groes)){
				groes = groes.trim().replaceAll("\"", "").replaceAll("'", "");
			}
			if (rude.isIn181Group(matkl)) {
				if (WFUtil.isNull(groes)) {//如果181开头，物料大小量纲不能为空，是null的直接过滤掉
					String sql = "select distinct wfno from wfrdfield where wfrdfield.wfNo ='"+wfNo+"'";
					System.out.println(sql);
						result = wfRdDao.query(sql,"WfRd.WfNo");
				}else {
					
					if(rude.isBothUni(matkl)){
						String sql ="select distinct wfrdfield.wfno from wfrdfield," 
							+ "(select wfno,rowId from wfrdfield where fieldid=992 and fieldtext='"+maktx+"')a " 
							+ " where fieldid=993 and fieldtext='"+groes+"' and a.wfno=wfrdfield.wfno and a.rowId = wfrdfield.rowId " 
							+ " and wfrdfield.wfno not in(select distinct wfno from wfrd where wfno like 'B50%' and status="+MSG.OWFRD_STATUS_4+")";
						if(wfNo!=null){
							sql= sql+" and wfrdfield.wfNo!='"+wfNo+"'";
						}
						System.out.println("sql~~~~~~~~"+sql);
						result = wfRdDao.query(sql,"WfRd.WfNo");
					}
				}
			} else if (!rude.isInCageGroup(matkl)) {
				if(rude.isBothUni(matkl)){
					String sql ="select distinct wfrdfield.wfno from wfrdfield," 
						+ "(select wfno,rowId from wfrdfield where fieldid=992 and fieldtext='"+maktx+"')a " 
						+ " where fieldid=993 and fieldtext='"+groes+"' and a.wfno=wfrdfield.wfno and a.rowId = wfrdfield.rowId " 
						+ " and wfrdfield.wfno not in(select distinct wfno from wfrd where wfno like 'B50%' and status="+MSG.OWFRD_STATUS_4+")";
					if(wfNo!=null){
						sql= sql+" and wfrdfield.wfNo!='"+wfNo+"'";
					}
					System.out.println("sql~~~~~~~~"+sql);
					result = wfRdDao.query(sql,"WfRd.WfNo");
				}
			} else {
				if (WFUtil.isNull(groes)) {//如果1和2开头，物料大小量纲不能为空，是null的直接过滤掉
					System.out.println(wfNo);
					String sql = "select distinct wfno from wfrdfield where wfrdfield.wfNo ='"+wfNo+"'";

						System.out.println(sql);
							result = wfRdDao.query(sql,"WfRd.WfNo");
				}
				if(rude.isMaktxUni(matkl)){	//maktx 不可重复
					String sql = "select distinct wfno from wfrdfield where fieldid=992 and fieldtext='"+maktx+"' " +
							" and wfno not in(select distinct wfno from wfrd where wfno like 'B50%' and status="+MSG.OWFRD_STATUS_4+")";
					if(wfNo!=null){
						sql= sql+" and wfrdfield.wfNo!='"+wfNo+"'";
					}
					result = wfRdDao.query(sql,"WfRd.WfNo");
					if(WFUtil.isNotNull(result)){
						return result;
					}
				}
				
				if(rude.isGroesUni(matkl)){	//groes 不可重复
					if(groes!=null && !"".equals(groes)){
						System.out.println("groesgroesgroesgroes1111~~~"+groes);
						String sql = "select distinct wfno from wfrdfield where fieldid=993 and fieldtext='"+groes+"' " +
							" and wfno not in(select distinct wfno from wfrd where wfno like 'B50%' and status="+MSG.OWFRD_STATUS_4+")";
						if(wfNo!=null){
							sql= sql+" and wfrdfield.wfNo!='"+wfNo+"'";
						}
						result = wfRdDao.query(sql,"WfRd.WfNo");
					}
				}
			}
			
			
			return result;
		}
	
	public List<WfRd> checkRude(String mtart,String matkl,String maktx,String groes,String wfNo) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return checkjson(mtart,matkl,maktx,groes,wfNo);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
	
	protected String findValue(List<WfRdField> list,int fieldId){
		if(WFUtil.isNotNull(list)){
			Iterator<WfRdField> iter = list.iterator();
			System.out.println("fieldId"+fieldId);
			while(iter.hasNext()){
				WfRdField f = iter.next();
				if(f!=null && f.getFieldId()!=null && f.getFieldId()==fieldId){
					System.out.println("f.getFieldText()"+f.getFieldText());
					return f.getFieldText();
				}
			}
		}
		return null;
	}

	public void save(WfRdField wfRdField) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdFieldDAO.class).insert(wfRdField);

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

	public void update(WfRdField wfRdField) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdFieldDAO.class).update(wfRdField);

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
			DAOFactory.getDAO(WfRdFieldDAO.class).update(sql);
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

	public void submit(WfRdField wfRdField) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdFieldDAO.class).update(wfRdField);
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

	public void review(WfRdField wfRdField) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdFieldDAO.class).update(wfRdField);
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

	public void confirm(WfRdField wfRdField) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdFieldDAO.class).update(wfRdField);
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
			DAOFactory.getDAO(WfRdFieldDAO.class).delete(sql);
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

	public void remove(WfRdField wfRdField) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			DbConnUtil.beginTransaction();
			DAOFactory.getDAO(WfRdFieldDAO.class).delete(new WfRdFieldHelper().getSql4Delete(wfRdField));
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

	public WfRdField findById(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfRdField)DAOFactory.getDAO(WfRdFieldDAO.class).load(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfRdField findById(WfRdField wfRdField) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (WfRdField)DAOFactory.getDAO(WfRdFieldDAO.class).load(wfRdField);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public WfRdField findBy(WfRdField wfRdField) throws Exception {
		String sql = SqlUtil.getSql4All(WfRdFieldHelper.class,wfRdField,WfRdField.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (WfRdField)DAOFactory.getDAO(WfRdFieldDAO.class).load(sql,WfRdField.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRdField> find(String sql,String fields) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRdField>)DAOFactory.getDAO(WfRdFieldDAO.class).query(sql, fields);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRdField> findAll(WfRdField wfRdField) throws Exception {
		String sql = SqlUtil.getSql4All(WfRdFieldHelper.class,wfRdField,WfRdField.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRdField>)DAOFactory.getDAO(WfRdFieldDAO.class).query(sql, WfRdField.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRdField> find(WfRdField wfRdField,PageVO pageVO) throws Exception {
		String sql = SqlUtil.getSql4Pages(WfRdFieldHelper.class,wfRdField,pageVO,WfRdField.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRdField>)DAOFactory.getDAO(WfRdFieldDAO.class).query(sql, WfRdField.LIST_FIELDS);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WfRdField> find(WfRdField wfRdField) throws Exception {
		String sql = SqlUtil.getSql4All(WfRdFieldHelper.class,wfRdField,WfRdField.LIST_FIELDS);
		DbConnUtil.buildDbconn(3);
		try {
			return (List<WfRdField>)DAOFactory.getDAO(WfRdFieldDAO.class).query(sql, WfRdField.LIST_FIELDS);
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
			return DAOFactory.getDAO(WfRdFieldDAO.class).amount(sql);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}

	public int amount(WfRdField wfRdField) throws Exception {
		DbConnUtil.buildDbconn(3);
		try {
			return DAOFactory.getDAO(WfRdFieldDAO.class).amount(new WfRdFieldHelper().getSql4Amount(wfRdField));
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			DbConnUtil.closeDbconn();
		}
	}
}