package gnwf.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import gnwf.vo.OptStore;

public class OptStoreHelper extends BasicOptStoreHelper {
	public String getSqlString() {
		return " from OptStore " +

               " where 1=1 ";
	}

	public List<OptStore> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<OptStore> list = new ArrayList<OptStore>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				OptStore optStore = new OptStore();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("MatlNo") || _fields[i].equals("OptStore.MatlNo"))
						optStore.setMatlNo(rs.getString("MatlNo"));
					else if(_fields[i].equals("GpCode") || _fields[i].equals("OptStore.GpCode"))
						optStore.setGpCode(rs.getString("GpCode"));
					else if(_fields[i].equals("MatlRev") || _fields[i].equals("OptStore.MatlRev"))
						optStore.setMatlRev(rs.getString("MatlRev"));
					else if(_fields[i].equals("MatlNm") || _fields[i].equals("OptStore.MatlNm"))
						optStore.setMatlNm(rs.getString("MatlNm"));
					else if(_fields[i].equals("MatlDesc") || _fields[i].equals("OptStore.MatlDesc"))
						optStore.setMatlDesc(rs.getString("MatlDesc"));
					else if(_fields[i].equals("OptTyp") || _fields[i].equals("OptStore.OptTyp"))
						optStore.setOptTyp(rs.getInt("OptTyp"));
					else if(_fields[i].equals("MatlTyp") || _fields[i].equals("OptStore.MatlTyp"))
						optStore.setMatlTyp(rs.getInt("MatlTyp"));
					else if(_fields[i].equals("LotSize") || _fields[i].equals("OptStore.LotSize"))
						optStore.setLotSize(rs.getInt("LotSize"));
					else if(_fields[i].equals("IsPanel") || _fields[i].equals("OptStore.IsPanel"))
						optStore.setIsPanel(rs.getInt("IsPanel"));
					else if(_fields[i].equals("Status") || _fields[i].equals("OptStore.Status"))
						optStore.setStatus(rs.getInt("Status"));
					else if(_fields[i].equals("CreateBy") || _fields[i].equals("OptStore.CreateBy"))
						optStore.setCreateBy(rs.getInt("CreateBy"));
					else if(_fields[i].equals("CreateDate") || _fields[i].equals("OptStore.CreateDate"))
						optStore.setCreateDate(rs.getTimestamp("CreateDate"));
					else if(_fields[i].equals("LastUpd") || _fields[i].equals("OptStore.LastUpd"))
						optStore.setLastUpd(rs.getInt("LastUpd"));
					else if(_fields[i].equals("LastDate") || _fields[i].equals("OptStore.LastDate"))
						optStore.setLastDate(rs.getTimestamp("LastDate"));


				}
				list.add(optStore);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("OptStoreHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}