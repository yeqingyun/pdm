package zrsy.dao;

import java.sql.SQLException;
import java.util.Date;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;

import zrsy.vo.Chln;
import zrsy.vo.ChlnV;

public class ChnlDAO {

	/**
	 * 
	 * @param chlnTyp 文档类型
	 * @param docName 文件名称
	 * @return 3位流水
	 */
	public String getChnlDocV(String chlnTyp,String docName) {

		String chnNo = "0000";
		
		ChlnVDAO chlnVDAO = new ChlnVDAO();
		
		ChlnV chln = new ChlnV();
		chln.setFileNm(docName);
		chln.setChlnTyp(chlnTyp);
		try {
			Chln ochnl = (Chln)chlnVDAO.load(chln);
			
			if(ochnl==null) {
				chln.setChlnNo(1);
				chln.setCreateDate(new Date());
				chln.setLastDate(new Date());
				
				chlnVDAO.insert(chln);
				
				chnNo = chlnStr(chln.getChlnNo(),3);
			}
			else {
				chln.setChlnNo(ochnl.getChlnNo()+1);
				chln.setLastDate(new Date());
				
				chlnVDAO.update(chln);
				
				chnNo = chlnStr(chln.getChlnNo(),3);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chnNo;
	}
	/**
	 * 
	 * @param chlnTyp 文档类型
	 * @param docName 文件名称
	 * @return 3位流水
	 */
	public String callDocV(String chlnTyp,String docName) {
		String chnNo = "0000";
		
		ChlnDAO chlnDAO = new ChlnDAO();

		try {
			chlnDAO.callProc("{call GnSy.dbo.pro_get_docv(?,?,?)}");

			DbConnUtil.getDbconn().getCurrentCstmt().setString(1, chlnTyp);
			DbConnUtil.getDbconn().getCurrentCstmt().setString(2, docName);
			
			DbConnUtil.getDbconn().getCurrentCstmt().registerOutParameter(3, java.sql.Types.VARCHAR);
			
			DbConnUtil.getDbconn().getCurrentCstmt().execute();
			
			chnNo = DbConnUtil.getDbconn().getCurrentCstmt().getString(3);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chnNo;
	}
	
	/**
	 * 
	 * @param chlnTyp 文档说明书类型
	 * @return 3位流水
	 */
	public String callSutveyDocV(String chlnTyp,Integer docType) {
		String chnNo = "0000";
		
		ChlnDAO chlnDAO = new ChlnDAO();

		try {
			chlnDAO.callProc("{call GnSy.dbo.pro_get_docv(?,?,?)}");

			DbConnUtil.getDbconn().getCurrentCstmt().setString(1, chlnTyp);
			//DbConnUtil.getDbconn().getCurrentCstmt().setString(2, docName);
			DbConnUtil.getDbconn().getCurrentCstmt().setInt(2, docType);
			
			DbConnUtil.getDbconn().getCurrentCstmt().registerOutParameter(3, java.sql.Types.VARCHAR);
			
			DbConnUtil.getDbconn().getCurrentCstmt().execute();
			
			chnNo = DbConnUtil.getDbconn().getCurrentCstmt().getString(3);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chnNo;
	}
	/**
	 * 
	 * @param chlnTyp 项目进度表文档类型
	 * @return 3位流水
	 */
	public String callPrjtPointDocV(String chlnTyp,Integer docType) {
		String chnNo = "0000";
		
		ChlnDAO chlnDAO = new ChlnDAO();

		try {
			chlnDAO.callProc("{call GnSy.dbo.pro_get_docv(?,?,?)}");

			DbConnUtil.getDbconn().getCurrentCstmt().setString(1, chlnTyp);
			//DbConnUtil.getDbconn().getCurrentCstmt().setString(2, docName);
			DbConnUtil.getDbconn().getCurrentCstmt().setInt(2, docType);
			
			DbConnUtil.getDbconn().getCurrentCstmt().registerOutParameter(3, java.sql.Types.VARCHAR);
			
			DbConnUtil.getDbconn().getCurrentCstmt().execute();
			
			chnNo = DbConnUtil.getDbconn().getCurrentCstmt().getString(3);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chnNo;
	}
	/**
	 * 
	 * 取月份为2位的流水号 
	 * 单据类型（2）+年份（2）+月份（2）+5位流水号
	 * @param chlnTyp 单据类型
	 * @return 11位流水号
	 */
	public String callMonth2No(Integer syId,String chlnTyp) {
        String chnNo = "0000";
		
		ChlnDAO chlnDAO = new ChlnDAO();

		try {
			chlnDAO.callProc("{call GnSy.dbo.pro_get_m2_billno(?,?,?)}");

			DbConnUtil.getDbconn().getCurrentCstmt().setInt(1, syId);
			DbConnUtil.getDbconn().getCurrentCstmt().setString(2, chlnTyp);
			
			DbConnUtil.getDbconn().getCurrentCstmt().registerOutParameter(3, java.sql.Types.VARCHAR);
			
			DbConnUtil.getDbconn().getCurrentCstmt().execute();
			
			chnNo = DbConnUtil.getDbconn().getCurrentCstmt().getString(3);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chnNo;
	}
	
	
	/**
	 * 按年份取流水号,5位流水号
	 * 单据类型（2）+年份（2）+5位流水号
	 * @param chlnTyp 单据类型
	 * @return 9位流水号
	 */
	public String getYearNo(String chlnTyp) {
		String chnNo = "0000";
		
		ChlnDAO chlnDAO = new ChlnDAO();
		
		Chln chln = new Chln();
		chln.setChlnTyp(chlnTyp);
		chln.setYear(Integer.valueOf(GenericUtil.getYear()));
		chln.setMonth(Integer.valueOf(-1));
		
		try {
			Chln ochnl = (Chln)chlnDAO.load(chln);
			
			if(ochnl==null) {
				chln.setChlnNo(1);
				chln.setCreateDate(new Date());
				chln.setLastDate(new Date());
				
				chlnDAO.insert(chln);
				
				chnNo = chln.getChlnTyp()+subYear(chln.getYear())+chlnStr(chln.getChlnNo(),5);
			}
			else {
				chln.setChlnNo(ochnl.getChlnNo()+1);
				chln.setLastDate(new Date());
				
				chlnDAO.update(chln);
				
				chnNo = chln.getChlnTyp()+subYear(chln.getYear())+chlnStr(chln.getChlnNo(),5);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chnNo;
	}

	/**
	 * 通过存储过程，按年份取号
	 * 按年份取流水号,5位流水号
	 * 单据类型（3）+年份（2）+6位流水号
	 * @param syId 系统Id
	 * @param chlnTyp 单据类型
	 * @return 11位流水号
	 */
	public String callYearNo(Integer syId,String chlnTyp) {
		String chnNo = "0000";
		
		ChlnDAO chlnDAO = new ChlnDAO();

		try {
			chlnDAO.callProc("{call GnSy.dbo.pro_get_y_billno(?,?,?)}");

			DbConnUtil.getDbconn().getCurrentCstmt().setInt(1, syId);
			DbConnUtil.getDbconn().getCurrentCstmt().setString(2, chlnTyp);
			
			DbConnUtil.getDbconn().getCurrentCstmt().registerOutParameter(3, java.sql.Types.VARCHAR);
			
			DbConnUtil.getDbconn().getCurrentCstmt().execute();
			
			chnNo = DbConnUtil.getDbconn().getCurrentCstmt().getString(3);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chnNo;
	}

	/**
	 * 通过存储过程，按年份,月份取号
	 * 按年份取流水号,5位流水号
	 * 按月份取流水号，单据类型（3）+年份（2）+月份（1）+5位流水号
	 * @param syId 系统Id
	 * @param chlnTyp 单据类型
	 * @return 11位流水号
	 */
	public String callMonthNo(Integer syId,String chlnTyp) {
		String chnNo = "0000";
		
		ChlnDAO chlnDAO = new ChlnDAO();

		try {
			chlnDAO.callProc("{call GnSy.dbo.pro_get_m_billno(?,?,?)}");

			DbConnUtil.getDbconn().getCurrentCstmt().setInt(1, syId);
			DbConnUtil.getDbconn().getCurrentCstmt().setString(2, chlnTyp);
			
			DbConnUtil.getDbconn().getCurrentCstmt().registerOutParameter(3, java.sql.Types.VARCHAR);
			
			DbConnUtil.getDbconn().getCurrentCstmt().execute();
			
			chnNo = DbConnUtil.getDbconn().getCurrentCstmt().getString(3);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chnNo;
	}
	/**
	 * 通过存储过程，按年份,月份，日取号
	 * 按年份取流水号,5位流水号
	 * 按月份取流水号，单据类型（3）+年份（2）+月份（1）+日（2）+5位流水号
	 * @param syId 系统Id
	 * @param chlnTyp 单据类型
	 * @return 13位流水号
	 */
	public String callDayNo(Integer syId,String chlnTyp) {
		String chnNo = "0000";
		
		ChlnDAO chlnDAO = new ChlnDAO();

		try {
			chlnDAO.callProc("{call GnSy.dbo.pro_get_d_billno(?,?,?)}");

			DbConnUtil.getDbconn().getCurrentCstmt().setInt(1, syId);
			DbConnUtil.getDbconn().getCurrentCstmt().setString(2, chlnTyp);
			
			DbConnUtil.getDbconn().getCurrentCstmt().registerOutParameter(3, java.sql.Types.VARCHAR);
			
			DbConnUtil.getDbconn().getCurrentCstmt().execute();
			
			chnNo = DbConnUtil.getDbconn().getCurrentCstmt().getString(3);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chnNo;
	}
	
	/**
	 * 按月份取流水号，单据类型（2）+年份（2）+月份（1）+4位流水号
	 * @param chlnTyp 单据类型
	 * @return 9位流水号
	 */
	public String getMonthNo(String chlnTyp) {
		String chnNo = "0000";
		
		ChlnDAO chlnDAO = new ChlnDAO();
		
		Chln chln = new Chln();
		chln.setChlnTyp(chlnTyp);
		chln.setYear(Integer.valueOf(GenericUtil.getYear()));
		chln.setMonth(Integer.valueOf(GenericUtil.getMonth()));

		try {
			Chln ochnl = (Chln)chlnDAO.load(chln);
			
			if(ochnl==null) {
				chln.setChlnNo(1);
				chln.setCreateDate(new Date());
				chln.setLastDate(new Date());
				
				chlnDAO.insert(chln);
				
				chnNo = chln.getChlnTyp()+subYear(chln.getYear())+subMonth(chln.getMonth())+chlnStr(chln.getChlnNo());
			}
			else {
				chln.setChlnNo(ochnl.getChlnNo()+1);
				chln.setLastDate(new Date());
				
				chlnDAO.update(chln);
				
				chnNo = chln.getChlnTyp()+subYear(chln.getYear())+subMonth(chln.getMonth())+chlnStr(chln.getChlnNo());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chnNo;
	}
	
	
	private String chlnStr(Integer chln) {
		return chlnStr(chln,4);
	}
	
	private String chlnStr(Integer chln,int length) {
		String cno = "";
		String no = String.valueOf(chln);
		for(int i=0; i<length-no.length(); i++) {
			cno+="0";
		}
		return cno+no;
	}
	
	private String subYear(Integer year) {
		String yy = year.toString();
		return yy.substring(2);
	}
	
	private String subMonth(Integer month) {
		String yy = month.toString();
		
		if(month.intValue()==10)
			return "A";
		else if(month.intValue()==11)
			return "B";
		else if(month.intValue()==12)
			return "C";
		
		return yy;
	}
	
	/**
	 * 单元测试
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChnlDAO chnldao = new ChnlDAO();
		System.out.println(chnldao.callYearNo(1,"Test"));
	}
}
