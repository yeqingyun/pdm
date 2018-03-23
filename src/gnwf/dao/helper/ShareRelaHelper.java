package gnwf.dao.helper;

import gnwf.vo.ShareRela;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;


public class ShareRelaHelper extends BasicShareRelaHelper {
	public String getSqlString() {
		return " from ShareRela " +

               " where 1=1 ";
	}

	public List<ShareRela> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<ShareRela> list = new ArrayList<ShareRela>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				ShareRela shareRela = new ShareRela();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("WfDocId") || _fields[i].equals("ShareRela.WfDocId"))
						shareRela.setWfDocId(rs.getInt("WfDocId"));
					else if(_fields[i].equals("UsrId") || _fields[i].equals("ShareRela.UsrId"))
						shareRela.setUsrId(rs.getInt("UsrId"));
					else if(_fields[i].equals("Remark") || _fields[i].equals("ShareRela.Remark"))
						shareRela.setRemark(rs.getString("Remark"));
					else if(_fields[i].equals("Status") || _fields[i].equals("ShareRela.Status"))
						shareRela.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("ShareRela.CreateBy"))
						shareRela.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("ShareRela.CreateDate"))
						shareRela.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("ShareRela.LastUpd"))
						shareRela.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("LastUpdDate") || _fields[i].equals("ShareRela.LastUpdDate"))
						shareRela.setLastDate(rs.getTimestamp("LastUpdDate"));
					else if(_fields[i].equals("UsrName") || _fields[i].equals("ShareRela.UsrName"))
						shareRela.setUsrName(rs.getString("UsrName"));


				}
				list.add(shareRela);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("ShareRelaHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}