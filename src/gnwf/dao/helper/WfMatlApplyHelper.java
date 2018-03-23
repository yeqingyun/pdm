package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.WfMatlApply;

public class WfMatlApplyHelper extends BasicWfMatlApplyHelper {
	public String getSqlString() {
		return " from WfMatlApply " +

               " where 1=1 ";
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
}