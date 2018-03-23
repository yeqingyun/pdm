package gnwf.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.comn.GenericUtil;
import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import gnwf.vo.WfMatlApply;

public class BasicWfMatlApplyHelper implements SqlHelper {
	public String getSqlString() {
		return " from WfMatlApply where 1=1";
	}

	public String getOrderBy() {
		return " order by WfMatlApply.MatlId";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((WfMatlApply)object);
	}

	public String getSql4Amount(WfMatlApply wfMatlApply) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(wfMatlApply);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((WfMatlApply)object);
	}

	public String getSql4Delete(WfMatlApply wfMatlApply) {
		return "delete from WfMatlApply where 1=1"+getSqlCondition(wfMatlApply);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((WfMatlApply)object,fields);
	}

	public String getSql4All(WfMatlApply wfMatlApply, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(wfMatlApply)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((WfMatlApply)object,pageVO,fields);
	}

	public String getSql4Pages(WfMatlApply wfMatlApply,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" WfMatlApply.MatlId "+ getSqlString()+getSqlCondition(wfMatlApply)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(wfMatlApply)+" and WfMatlApply.MatlId not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((WfMatlApply)object);
	}

	public String getSqlCondition(WfMatlApply wfMatlApply) {
		String sql = "";
		if(null != wfMatlApply.getMatlId())
			sql += " and WfMatlApply.MatlId = '"+wfMatlApply.getMatlId()+"'";
		if(null != wfMatlApply.getInfoId() && !wfMatlApply.getInfoId().trim().equals(""))
			sql += " and WfMatlApply.InfoId = '"+wfMatlApply.getInfoId().trim()+"'";
		if(null != wfMatlApply.getBSTUZ() && !wfMatlApply.getBSTUZ().trim().equals(""))
			sql += " and WfMatlApply.BSTUZ = '"+wfMatlApply.getBSTUZ().trim()+"'";
		if(null != wfMatlApply.getDISLS() && !wfMatlApply.getDISLS().trim().equals(""))
			sql += " and WfMatlApply.DISLS = '"+wfMatlApply.getDISLS().trim()+"'";
		if(null != wfMatlApply.getKAUSF() && !wfMatlApply.getKAUSF().trim().equals(""))
			sql += " and WfMatlApply.KAUSF = '"+wfMatlApply.getKAUSF().trim()+"'";
		if(null != wfMatlApply.getMATKL() && !wfMatlApply.getMATKL().trim().equals(""))
			sql += " and WfMatlApply.MATKL = '"+wfMatlApply.getMATKL().trim()+"'";
		if(null != wfMatlApply.getWRKST() && !wfMatlApply.getWRKST().trim().equals(""))
			sql += " and WfMatlApply.WRKST = '"+wfMatlApply.getWRKST().trim()+"'";
		if(null != wfMatlApply.getSOBSL() && !wfMatlApply.getSOBSL().trim().equals(""))
			sql += " and WfMatlApply.SOBSL = '"+wfMatlApply.getSOBSL().trim()+"'";
		if(null != wfMatlApply.getBKLAS() && !wfMatlApply.getBKLAS().trim().equals(""))
			sql += " and WfMatlApply.BKLAS = '"+wfMatlApply.getBKLAS().trim()+"'";
		if(null != wfMatlApply.getRemark() && !wfMatlApply.getRemark().trim().equals(""))
			sql += " and WfMatlApply.Remark = '"+wfMatlApply.getRemark().trim()+"'";
		if(null != wfMatlApply.getPLIFZ() && !wfMatlApply.getPLIFZ().trim().equals(""))
			sql += " and WfMatlApply.PLIFZ = '"+wfMatlApply.getPLIFZ().trim()+"'";
		if(null != wfMatlApply.getMTART() && !wfMatlApply.getMTART().trim().equals(""))
			sql += " and WfMatlApply.MTART = '"+wfMatlApply.getMTART().trim()+"'";
		if(null != wfMatlApply.getBSTMA() && !wfMatlApply.getBSTMA().trim().equals(""))
			sql += " and WfMatlApply.BSTMA = '"+wfMatlApply.getBSTMA().trim()+"'";
		if(null != wfMatlApply.getGEWEI() && !wfMatlApply.getGEWEI().trim().equals(""))
			sql += " and WfMatlApply.GEWEI = '"+wfMatlApply.getGEWEI().trim()+"'";
		if(null != wfMatlApply.getMAKTX() && !wfMatlApply.getMAKTX().trim().equals(""))
			sql += " and WfMatlApply.MAKTX = '"+wfMatlApply.getMAKTX().trim()+"'";
		if(null != wfMatlApply.getMEINS() && !wfMatlApply.getMEINS().trim().equals(""))
			sql += " and WfMatlApply.MEINS = '"+wfMatlApply.getMEINS().trim()+"'";
		if(null != wfMatlApply.getDISPO() && !wfMatlApply.getDISPO().trim().equals(""))
			sql += " and WfMatlApply.DISPO = '"+wfMatlApply.getDISPO().trim()+"'";
		if(null != wfMatlApply.getMMSTA() && !wfMatlApply.getMMSTA().trim().equals(""))
			sql += " and WfMatlApply.MMSTA = '"+wfMatlApply.getMMSTA().trim()+"'";
		if(null != wfMatlApply.getVOLEH() && !wfMatlApply.getVOLEH().trim().equals(""))
			sql += " and WfMatlApply.VOLEH = '"+wfMatlApply.getVOLEH().trim()+"'";
		if(null != wfMatlApply.getBSTME() && !wfMatlApply.getBSTME().trim().equals(""))
			sql += " and WfMatlApply.BSTME = '"+wfMatlApply.getBSTME().trim()+"'";
		if(null != wfMatlApply.getBSTMI() && !wfMatlApply.getBSTMI().trim().equals(""))
			sql += " and WfMatlApply.BSTMI = '"+wfMatlApply.getBSTMI().trim()+"'";
		if(null != wfMatlApply.getBRGEW() && !wfMatlApply.getBRGEW().trim().equals(""))
			sql += " and WfMatlApply.BRGEW = '"+wfMatlApply.getBRGEW().trim()+"'";
		if(null != wfMatlApply.getBSTUN() && !wfMatlApply.getBSTUN().trim().equals(""))
			sql += " and WfMatlApply.BSTUN = '"+wfMatlApply.getBSTUN().trim()+"'";
		if(null != wfMatlApply.getBSTRF() && !wfMatlApply.getBSTRF().trim().equals(""))
			sql += " and WfMatlApply.BSTRF = '"+wfMatlApply.getBSTRF().trim()+"'";
		if(null != wfMatlApply.getVOLUM() && !wfMatlApply.getVOLUM().trim().equals(""))
			sql += " and WfMatlApply.VOLUM = '"+wfMatlApply.getVOLUM().trim()+"'";
		if(null != wfMatlApply.getMATNR() && !wfMatlApply.getMATNR().trim().equals(""))
			sql += " and WfMatlApply.MATNR = '"+wfMatlApply.getMATNR().trim()+"'";
		if(null != wfMatlApply.getLGFSB() && !wfMatlApply.getLGFSB().trim().equals(""))
			sql += " and WfMatlApply.LGFSB = '"+wfMatlApply.getLGFSB().trim()+"'";
		if(null != wfMatlApply.getBESKZ() && !wfMatlApply.getBESKZ().trim().equals(""))
			sql += " and WfMatlApply.BESKZ = '"+wfMatlApply.getBESKZ().trim()+"'";
		if(null != wfMatlApply.getSTPRS() && !wfMatlApply.getSTPRS().trim().equals(""))
			sql += " and WfMatlApply.STPRS = '"+wfMatlApply.getSTPRS().trim()+"'";
		if(null != wfMatlApply.getWERKS() && !wfMatlApply.getWERKS().trim().equals(""))
			sql += " and WfMatlApply.WERKS = '"+wfMatlApply.getWERKS().trim()+"'";
		if(null != wfMatlApply.getGROES() && !wfMatlApply.getGROES().trim().equals(""))
			sql += " and WfMatlApply.GROES = '"+wfMatlApply.getGROES().trim()+"'";
		if(null != wfMatlApply.getDISMM() && !wfMatlApply.getDISMM().trim().equals(""))
			sql += " and WfMatlApply.DISMM = '"+wfMatlApply.getDISMM().trim()+"'";
		if(null != wfMatlApply.getSTAWN() && !wfMatlApply.getSTAWN().trim().equals(""))
			sql += " and WfMatlApply.STAWN = '"+wfMatlApply.getSTAWN().trim()+"'";
		if(null != wfMatlApply.getVPRSV() && !wfMatlApply.getVPRSV().trim().equals(""))
			sql += " and WfMatlApply.VPRSV = '"+wfMatlApply.getVPRSV().trim()+"'";
		if(null != wfMatlApply.getNTGEW() && !wfMatlApply.getNTGEW().trim().equals(""))
			sql += " and WfMatlApply.NTGEW = '"+wfMatlApply.getNTGEW().trim()+"'";
		if(null != wfMatlApply.getEXTWG() && !wfMatlApply.getEXTWG().trim().equals(""))
			sql += " and WfMatlApply.EXTWG = '"+wfMatlApply.getEXTWG().trim()+"'";
		if(null != wfMatlApply.getNORMT() && !wfMatlApply.getNORMT().trim().equals(""))
			sql += " and WfMatlApply.NORMT = '"+wfMatlApply.getNORMT().trim()+"'";
		if(null != wfMatlApply.getFERTH() && !wfMatlApply.getFERTH().trim().equals(""))
			sql += " and WfMatlApply.FERTH = '"+wfMatlApply.getFERTH().trim()+"'";

		return sql;
	}

	public List<WfMatlApply> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<WfMatlApply> list = new ArrayList<WfMatlApply>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				WfMatlApply wfMatlApply = new WfMatlApply();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("MatlId") || _fields[i].equals("WfMatlApply.MatlId"))
						wfMatlApply.setMatlId(rs.getInt("MatlId"));
					else if(_fields[i].equals("InfoId") || _fields[i].equals("WfMatlApply.InfoId"))
						wfMatlApply.setInfoId(rs.getString("InfoId"));
					else if(_fields[i].equals("BSTUZ") || _fields[i].equals("WfMatlApply.BSTUZ"))
						wfMatlApply.setBSTUZ(rs.getString("BSTUZ"));
					else if(_fields[i].equals("DISLS") || _fields[i].equals("WfMatlApply.DISLS"))
						wfMatlApply.setDISLS(rs.getString("DISLS"));
					else if(_fields[i].equals("KAUSF") || _fields[i].equals("WfMatlApply.KAUSF"))
						wfMatlApply.setKAUSF(rs.getString("KAUSF"));
					else if(_fields[i].equals("MATKL") || _fields[i].equals("WfMatlApply.MATKL"))
						wfMatlApply.setMATKL(rs.getString("MATKL"));
					else if(_fields[i].equals("WRKST") || _fields[i].equals("WfMatlApply.WRKST"))
						wfMatlApply.setWRKST(rs.getString("WRKST"));
					else if(_fields[i].equals("SOBSL") || _fields[i].equals("WfMatlApply.SOBSL"))
						wfMatlApply.setSOBSL(rs.getString("SOBSL"));
					else if(_fields[i].equals("BKLAS") || _fields[i].equals("WfMatlApply.BKLAS"))
						wfMatlApply.setBKLAS(rs.getString("BKLAS"));
					else if(_fields[i].equals("Remark") || _fields[i].equals("WfMatlApply.Remark"))
						wfMatlApply.setRemark(rs.getString("Remark"));
					else if(_fields[i].equals("PLIFZ") || _fields[i].equals("WfMatlApply.PLIFZ"))
						wfMatlApply.setPLIFZ(rs.getString("PLIFZ"));
					else if(_fields[i].equals("MTART") || _fields[i].equals("WfMatlApply.MTART"))
						wfMatlApply.setMTART(rs.getString("MTART"));
					else if(_fields[i].equals("BSTMA") || _fields[i].equals("WfMatlApply.BSTMA"))
						wfMatlApply.setBSTMA(rs.getString("BSTMA"));
					else if(_fields[i].equals("GEWEI") || _fields[i].equals("WfMatlApply.GEWEI"))
						wfMatlApply.setGEWEI(rs.getString("GEWEI"));
					else if(_fields[i].equals("MAKTX") || _fields[i].equals("WfMatlApply.MAKTX"))
						wfMatlApply.setMAKTX(rs.getString("MAKTX"));
					else if(_fields[i].equals("MEINS") || _fields[i].equals("WfMatlApply.MEINS"))
						wfMatlApply.setMEINS(rs.getString("MEINS"));
					else if(_fields[i].equals("DISPO") || _fields[i].equals("WfMatlApply.DISPO"))
						wfMatlApply.setDISPO(rs.getString("DISPO"));
					else if(_fields[i].equals("MMSTA") || _fields[i].equals("WfMatlApply.MMSTA"))
						wfMatlApply.setMMSTA(rs.getString("MMSTA"));
					else if(_fields[i].equals("VOLEH") || _fields[i].equals("WfMatlApply.VOLEH"))
						wfMatlApply.setVOLEH(rs.getString("VOLEH"));
					else if(_fields[i].equals("BSTME") || _fields[i].equals("WfMatlApply.BSTME"))
						wfMatlApply.setBSTME(rs.getString("BSTME"));
					else if(_fields[i].equals("BSTMI") || _fields[i].equals("WfMatlApply.BSTMI"))
						wfMatlApply.setBSTMI(rs.getString("BSTMI"));
					else if(_fields[i].equals("BRGEW") || _fields[i].equals("WfMatlApply.BRGEW"))
						wfMatlApply.setBRGEW(rs.getString("BRGEW"));
					else if(_fields[i].equals("BSTUN") || _fields[i].equals("WfMatlApply.BSTUN"))
						wfMatlApply.setBSTUN(rs.getString("BSTUN"));
					else if(_fields[i].equals("BSTRF") || _fields[i].equals("WfMatlApply.BSTRF"))
						wfMatlApply.setBSTRF(rs.getString("BSTRF"));
					else if(_fields[i].equals("VOLUM") || _fields[i].equals("WfMatlApply.VOLUM"))
						wfMatlApply.setVOLUM(rs.getString("VOLUM"));
					else if(_fields[i].equals("MATNR") || _fields[i].equals("WfMatlApply.MATNR"))
						wfMatlApply.setMATNR(rs.getString("MATNR"));
					else if(_fields[i].equals("LGFSB") || _fields[i].equals("WfMatlApply.LGFSB"))
						wfMatlApply.setLGFSB(rs.getString("LGFSB"));
					else if(_fields[i].equals("BESKZ") || _fields[i].equals("WfMatlApply.BESKZ"))
						wfMatlApply.setBESKZ(rs.getString("BESKZ"));
					else if(_fields[i].equals("STPRS") || _fields[i].equals("WfMatlApply.STPRS"))
						wfMatlApply.setSTPRS(rs.getString("STPRS"));
					else if(_fields[i].equals("WERKS") || _fields[i].equals("WfMatlApply.WERKS"))
						wfMatlApply.setWERKS(rs.getString("WERKS"));
					else if(_fields[i].equals("GROES") || _fields[i].equals("WfMatlApply.GROES"))
						wfMatlApply.setGROES(rs.getString("GROES"));
					else if(_fields[i].equals("DISMM") || _fields[i].equals("WfMatlApply.DISMM"))
						wfMatlApply.setDISMM(rs.getString("DISMM"));
					else if(_fields[i].equals("STAWN") || _fields[i].equals("WfMatlApply.STAWN"))
						wfMatlApply.setSTAWN(rs.getString("STAWN"));
					else if(_fields[i].equals("VPRSV") || _fields[i].equals("WfMatlApply.VPRSV"))
						wfMatlApply.setVPRSV(rs.getString("VPRSV"));
					else if(_fields[i].equals("NTGEW") || _fields[i].equals("WfMatlApply.NTGEW"))
						wfMatlApply.setNTGEW(rs.getString("NTGEW"));
					else if(_fields[i].equals("EXTWG") || _fields[i].equals("WfMatlApply.EXTWG"))
						wfMatlApply.setEXTWG(rs.getString("EXTWG"));
					else if(_fields[i].equals("NORMT") || _fields[i].equals("WfMatlApply.NORMT"))
						wfMatlApply.setNORMT(rs.getString("NORMT"));
					else if(_fields[i].equals("FERTH") || _fields[i].equals("WfMatlApply.FERTH"))
						wfMatlApply.setFERTH(rs.getString("FERTH"));

				}
				list.add(wfMatlApply);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("WfMatlApplyHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into WfMatlApply("+fields.replaceAll("WfMatlApply\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(WfMatlApply wfMatlApply,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("MatlId") || _fields[i].equals("WfMatlApply.MatlId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, wfMatlApply.getMatlId());
					}
					else if(_fields[i].equals("InfoId") || _fields[i].equals("WfMatlApply.InfoId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getInfoId());
					}
					else if(_fields[i].equals("BSTUZ") || _fields[i].equals("WfMatlApply.BSTUZ")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getBSTUZ());
					}
					else if(_fields[i].equals("DISLS") || _fields[i].equals("WfMatlApply.DISLS")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getDISLS());
					}
					else if(_fields[i].equals("KAUSF") || _fields[i].equals("WfMatlApply.KAUSF")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getKAUSF());
					}
					else if(_fields[i].equals("MATKL") || _fields[i].equals("WfMatlApply.MATKL")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getMATKL());
					}
					else if(_fields[i].equals("WRKST") || _fields[i].equals("WfMatlApply.WRKST")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getWRKST());
					}
					else if(_fields[i].equals("SOBSL") || _fields[i].equals("WfMatlApply.SOBSL")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getSOBSL());
					}
					else if(_fields[i].equals("BKLAS") || _fields[i].equals("WfMatlApply.BKLAS")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getBKLAS());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("WfMatlApply.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getRemark());
					}
					else if(_fields[i].equals("PLIFZ") || _fields[i].equals("WfMatlApply.PLIFZ")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getPLIFZ());
					}
					else if(_fields[i].equals("MTART") || _fields[i].equals("WfMatlApply.MTART")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getMTART());
					}
					else if(_fields[i].equals("BSTMA") || _fields[i].equals("WfMatlApply.BSTMA")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getBSTMA());
					}
					else if(_fields[i].equals("GEWEI") || _fields[i].equals("WfMatlApply.GEWEI")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getGEWEI());
					}
					else if(_fields[i].equals("MAKTX") || _fields[i].equals("WfMatlApply.MAKTX")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getMAKTX());
					}
					else if(_fields[i].equals("MEINS") || _fields[i].equals("WfMatlApply.MEINS")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getMEINS());
					}
					else if(_fields[i].equals("DISPO") || _fields[i].equals("WfMatlApply.DISPO")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getDISPO());
					}
					else if(_fields[i].equals("MMSTA") || _fields[i].equals("WfMatlApply.MMSTA")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getMMSTA());
					}
					else if(_fields[i].equals("VOLEH") || _fields[i].equals("WfMatlApply.VOLEH")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getVOLEH());
					}
					else if(_fields[i].equals("BSTME") || _fields[i].equals("WfMatlApply.BSTME")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getBSTME());
					}
					else if(_fields[i].equals("BSTMI") || _fields[i].equals("WfMatlApply.BSTMI")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getBSTMI());
					}
					else if(_fields[i].equals("BRGEW") || _fields[i].equals("WfMatlApply.BRGEW")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getBRGEW());
					}
					else if(_fields[i].equals("BSTUN") || _fields[i].equals("WfMatlApply.BSTUN")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getBSTUN());
					}
					else if(_fields[i].equals("BSTRF") || _fields[i].equals("WfMatlApply.BSTRF")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getBSTRF());
					}
					else if(_fields[i].equals("VOLUM") || _fields[i].equals("WfMatlApply.VOLUM")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getVOLUM());
					}
					else if(_fields[i].equals("MATNR") || _fields[i].equals("WfMatlApply.MATNR")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getMATNR());
					}
					else if(_fields[i].equals("LGFSB") || _fields[i].equals("WfMatlApply.LGFSB")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getLGFSB());
					}
					else if(_fields[i].equals("BESKZ") || _fields[i].equals("WfMatlApply.BESKZ")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getBESKZ());
					}
					else if(_fields[i].equals("STPRS") || _fields[i].equals("WfMatlApply.STPRS")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getSTPRS());
					}
					else if(_fields[i].equals("WERKS") || _fields[i].equals("WfMatlApply.WERKS")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getWERKS());
					}
					else if(_fields[i].equals("GROES") || _fields[i].equals("WfMatlApply.GROES")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getGROES());
					}
					else if(_fields[i].equals("DISMM") || _fields[i].equals("WfMatlApply.DISMM")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getDISMM());
					}
					else if(_fields[i].equals("STAWN") || _fields[i].equals("WfMatlApply.STAWN")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getSTAWN());
					}
					else if(_fields[i].equals("VPRSV") || _fields[i].equals("WfMatlApply.VPRSV")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getVPRSV());
					}
					else if(_fields[i].equals("NTGEW") || _fields[i].equals("WfMatlApply.NTGEW")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getNTGEW());
					}
					else if(_fields[i].equals("EXTWG") || _fields[i].equals("WfMatlApply.EXTWG")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getEXTWG());
					}
					else if(_fields[i].equals("NORMT") || _fields[i].equals("WfMatlApply.NORMT")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getNORMT());
					}
					else if(_fields[i].equals("FERTH") || _fields[i].equals("WfMatlApply.FERTH")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getFERTH());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfMatlApplyHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update WfMatlApply set ";
		String[] _fields = fields.replaceAll("WfMatlApply\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("BSTUZ") || _fields[i].equals("WfMatlApply.BSTUZ"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("DISLS") || _fields[i].equals("WfMatlApply.DISLS"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("KAUSF") || _fields[i].equals("WfMatlApply.KAUSF"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("MATKL") || _fields[i].equals("WfMatlApply.MATKL"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("WRKST") || _fields[i].equals("WfMatlApply.WRKST"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("SOBSL") || _fields[i].equals("WfMatlApply.SOBSL"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("BKLAS") || _fields[i].equals("WfMatlApply.BKLAS"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Remark") || _fields[i].equals("WfMatlApply.Remark"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("PLIFZ") || _fields[i].equals("WfMatlApply.PLIFZ"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("MTART") || _fields[i].equals("WfMatlApply.MTART"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("BSTMA") || _fields[i].equals("WfMatlApply.BSTMA"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("GEWEI") || _fields[i].equals("WfMatlApply.GEWEI"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("MAKTX") || _fields[i].equals("WfMatlApply.MAKTX"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("MEINS") || _fields[i].equals("WfMatlApply.MEINS"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("DISPO") || _fields[i].equals("WfMatlApply.DISPO"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("MMSTA") || _fields[i].equals("WfMatlApply.MMSTA"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("VOLEH") || _fields[i].equals("WfMatlApply.VOLEH"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("BSTME") || _fields[i].equals("WfMatlApply.BSTME"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("BSTMI") || _fields[i].equals("WfMatlApply.BSTMI"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("BRGEW") || _fields[i].equals("WfMatlApply.BRGEW"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("BSTUN") || _fields[i].equals("WfMatlApply.BSTUN"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("BSTRF") || _fields[i].equals("WfMatlApply.BSTRF"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("VOLUM") || _fields[i].equals("WfMatlApply.VOLUM"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("MATNR") || _fields[i].equals("WfMatlApply.MATNR"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("LGFSB") || _fields[i].equals("WfMatlApply.LGFSB"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("BESKZ") || _fields[i].equals("WfMatlApply.BESKZ"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("STPRS") || _fields[i].equals("WfMatlApply.STPRS"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("WERKS") || _fields[i].equals("WfMatlApply.WERKS"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("GROES") || _fields[i].equals("WfMatlApply.GROES"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("DISMM") || _fields[i].equals("WfMatlApply.DISMM"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("STAWN") || _fields[i].equals("WfMatlApply.STAWN"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("VPRSV") || _fields[i].equals("WfMatlApply.VPRSV"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("NTGEW") || _fields[i].equals("WfMatlApply.NTGEW"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("EXTWG") || _fields[i].equals("WfMatlApply.EXTWG"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("NORMT") || _fields[i].equals("WfMatlApply.NORMT"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("FERTH") || _fields[i].equals("WfMatlApply.FERTH"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(WfMatlApply wfMatlApply,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("BSTUZ") || _fields[i].equals("WfMatlApply.BSTUZ")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getBSTUZ());
					}
					else if(_fields[i].equals("DISLS") || _fields[i].equals("WfMatlApply.DISLS")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getDISLS());
					}
					else if(_fields[i].equals("KAUSF") || _fields[i].equals("WfMatlApply.KAUSF")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getKAUSF());
					}
					else if(_fields[i].equals("MATKL") || _fields[i].equals("WfMatlApply.MATKL")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getMATKL());
					}
					else if(_fields[i].equals("WRKST") || _fields[i].equals("WfMatlApply.WRKST")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getWRKST());
					}
					else if(_fields[i].equals("SOBSL") || _fields[i].equals("WfMatlApply.SOBSL")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getSOBSL());
					}
					else if(_fields[i].equals("BKLAS") || _fields[i].equals("WfMatlApply.BKLAS")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getBKLAS());
					}
					else if(_fields[i].equals("Remark") || _fields[i].equals("WfMatlApply.Remark")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getRemark());
					}
					else if(_fields[i].equals("PLIFZ") || _fields[i].equals("WfMatlApply.PLIFZ")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getPLIFZ());
					}
					else if(_fields[i].equals("MTART") || _fields[i].equals("WfMatlApply.MTART")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getMTART());
					}
					else if(_fields[i].equals("BSTMA") || _fields[i].equals("WfMatlApply.BSTMA")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getBSTMA());
					}
					else if(_fields[i].equals("GEWEI") || _fields[i].equals("WfMatlApply.GEWEI")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getGEWEI());
					}
					else if(_fields[i].equals("MAKTX") || _fields[i].equals("WfMatlApply.MAKTX")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getMAKTX());
					}
					else if(_fields[i].equals("MEINS") || _fields[i].equals("WfMatlApply.MEINS")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getMEINS());
					}
					else if(_fields[i].equals("DISPO") || _fields[i].equals("WfMatlApply.DISPO")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getDISPO());
					}
					else if(_fields[i].equals("MMSTA") || _fields[i].equals("WfMatlApply.MMSTA")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getMMSTA());
					}
					else if(_fields[i].equals("VOLEH") || _fields[i].equals("WfMatlApply.VOLEH")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getVOLEH());
					}
					else if(_fields[i].equals("BSTME") || _fields[i].equals("WfMatlApply.BSTME")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getBSTME());
					}
					else if(_fields[i].equals("BSTMI") || _fields[i].equals("WfMatlApply.BSTMI")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getBSTMI());
					}
					else if(_fields[i].equals("BRGEW") || _fields[i].equals("WfMatlApply.BRGEW")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getBRGEW());
					}
					else if(_fields[i].equals("BSTUN") || _fields[i].equals("WfMatlApply.BSTUN")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getBSTUN());
					}
					else if(_fields[i].equals("BSTRF") || _fields[i].equals("WfMatlApply.BSTRF")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getBSTRF());
					}
					else if(_fields[i].equals("VOLUM") || _fields[i].equals("WfMatlApply.VOLUM")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getVOLUM());
					}
					else if(_fields[i].equals("MATNR") || _fields[i].equals("WfMatlApply.MATNR")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getMATNR());
					}
					else if(_fields[i].equals("LGFSB") || _fields[i].equals("WfMatlApply.LGFSB")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getLGFSB());
					}
					else if(_fields[i].equals("BESKZ") || _fields[i].equals("WfMatlApply.BESKZ")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getBESKZ());
					}
					else if(_fields[i].equals("STPRS") || _fields[i].equals("WfMatlApply.STPRS")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getSTPRS());
					}
					else if(_fields[i].equals("WERKS") || _fields[i].equals("WfMatlApply.WERKS")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getWERKS());
					}
					else if(_fields[i].equals("GROES") || _fields[i].equals("WfMatlApply.GROES")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getGROES());
					}
					else if(_fields[i].equals("DISMM") || _fields[i].equals("WfMatlApply.DISMM")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getDISMM());
					}
					else if(_fields[i].equals("STAWN") || _fields[i].equals("WfMatlApply.STAWN")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getSTAWN());
					}
					else if(_fields[i].equals("VPRSV") || _fields[i].equals("WfMatlApply.VPRSV")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getVPRSV());
					}
					else if(_fields[i].equals("NTGEW") || _fields[i].equals("WfMatlApply.NTGEW")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getNTGEW());
					}
					else if(_fields[i].equals("EXTWG") || _fields[i].equals("WfMatlApply.EXTWG")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getEXTWG());
					}
					else if(_fields[i].equals("NORMT") || _fields[i].equals("WfMatlApply.NORMT")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getNORMT());
					}
					else if(_fields[i].equals("FERTH") || _fields[i].equals("WfMatlApply.FERTH")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, wfMatlApply.getFERTH());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("WfMatlApplyHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(WfMatlApply wfMatlApply) {
		String _fields = "";
		if(null != wfMatlApply.getMatlId())
			_fields += "WfMatlApply.MatlId,";
		if(null != wfMatlApply.getInfoId())
			_fields += "WfMatlApply.InfoId,";
		if(null != wfMatlApply.getBSTUZ())
			_fields += "WfMatlApply.BSTUZ,";
		if(null != wfMatlApply.getDISLS())
			_fields += "WfMatlApply.DISLS,";
		if(null != wfMatlApply.getKAUSF())
			_fields += "WfMatlApply.KAUSF,";
		if(null != wfMatlApply.getMATKL())
			_fields += "WfMatlApply.MATKL,";
		if(null != wfMatlApply.getWRKST())
			_fields += "WfMatlApply.WRKST,";
		if(null != wfMatlApply.getSOBSL())
			_fields += "WfMatlApply.SOBSL,";
		if(null != wfMatlApply.getBKLAS())
			_fields += "WfMatlApply.BKLAS,";
		if(null != wfMatlApply.getRemark())
			_fields += "WfMatlApply.Remark,";
		if(null != wfMatlApply.getPLIFZ())
			_fields += "WfMatlApply.PLIFZ,";
		if(null != wfMatlApply.getMTART())
			_fields += "WfMatlApply.MTART,";
		if(null != wfMatlApply.getBSTMA())
			_fields += "WfMatlApply.BSTMA,";
		if(null != wfMatlApply.getGEWEI())
			_fields += "WfMatlApply.GEWEI,";
		if(null != wfMatlApply.getMAKTX())
			_fields += "WfMatlApply.MAKTX,";
		if(null != wfMatlApply.getMEINS())
			_fields += "WfMatlApply.MEINS,";
		if(null != wfMatlApply.getDISPO())
			_fields += "WfMatlApply.DISPO,";
		if(null != wfMatlApply.getMMSTA())
			_fields += "WfMatlApply.MMSTA,";
		if(null != wfMatlApply.getVOLEH())
			_fields += "WfMatlApply.VOLEH,";
		if(null != wfMatlApply.getBSTME())
			_fields += "WfMatlApply.BSTME,";
		if(null != wfMatlApply.getBSTMI())
			_fields += "WfMatlApply.BSTMI,";
		if(null != wfMatlApply.getBRGEW())
			_fields += "WfMatlApply.BRGEW,";
		if(null != wfMatlApply.getBSTUN())
			_fields += "WfMatlApply.BSTUN,";
		if(null != wfMatlApply.getBSTRF())
			_fields += "WfMatlApply.BSTRF,";
		if(null != wfMatlApply.getVOLUM())
			_fields += "WfMatlApply.VOLUM,";
		if(null != wfMatlApply.getMATNR())
			_fields += "WfMatlApply.MATNR,";
		if(null != wfMatlApply.getLGFSB())
			_fields += "WfMatlApply.LGFSB,";
		if(null != wfMatlApply.getBESKZ())
			_fields += "WfMatlApply.BESKZ,";
		if(null != wfMatlApply.getSTPRS())
			_fields += "WfMatlApply.STPRS,";
		if(null != wfMatlApply.getWERKS())
			_fields += "WfMatlApply.WERKS,";
		if(null != wfMatlApply.getGROES())
			_fields += "WfMatlApply.GROES,";
		if(null != wfMatlApply.getDISMM())
			_fields += "WfMatlApply.DISMM,";
		if(null != wfMatlApply.getSTAWN())
			_fields += "WfMatlApply.STAWN,";
		if(null != wfMatlApply.getVPRSV())
			_fields += "WfMatlApply.VPRSV,";
		if(null != wfMatlApply.getNTGEW())
			_fields += "WfMatlApply.NTGEW,";
		if(null != wfMatlApply.getEXTWG())
			_fields += "WfMatlApply.EXTWG,";
		if(null != wfMatlApply.getNORMT())
			_fields += "WfMatlApply.NORMT,";
		if(null != wfMatlApply.getFERTH())
			_fields += "WfMatlApply.FERTH,";

		return _fields.substring(0, _fields.length()-1);
	}
}