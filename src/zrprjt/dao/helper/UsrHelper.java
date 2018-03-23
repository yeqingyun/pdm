package zrprjt.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import zrprjt.vo.Usr;

public class UsrHelper extends BasicUsrHelper {
	public String getSqlString() {
		return " from Usr " +

               " where 1=1 ";
	}

	public List<Usr> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<Usr> list = new ArrayList<Usr>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				Usr usr = new Usr();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Id") || _fields[i].equals("Usr.Id"))
						usr.setId(rs.getInt("Id"));
					if(_fields[i].equals("ComId") || _fields[i].equals("Usr.ComId"))
						usr.setComId(rs.getInt("ComId"));
					if(_fields[i].equals("DeptId") || _fields[i].equals("Usr.DeptId"))
						usr.setDeptId(rs.getInt("DeptId"));
					if(_fields[i].equals("Status") || _fields[i].equals("Usr.Status"))
						usr.setStatus(rs.getInt("Status"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("Usr.CreateBy"))
						usr.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("LastUpd") || _fields[i].equals("Usr.LastUpd"))
						usr.setLastUpd(rs.getInt("LastUpd"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("Usr.CreateDate"))
						usr.setCreateDate(rs.getTimestamp("CreateDate"));
					if(_fields[i].equals("LastDate") || _fields[i].equals("Usr.LastDate"))
						usr.setLastDate(rs.getTimestamp("LastDate"));
					if(_fields[i].equals("Login") || _fields[i].equals("Usr.Login"))
						usr.setLogin(rs.getString("Login"));
					if(_fields[i].equals("Pwd") || _fields[i].equals("Usr.Pwd"))
						usr.setPwd(rs.getString("Pwd"));
					if(_fields[i].equals("UsrNo") || _fields[i].equals("Usr.UsrNo"))
						usr.setUsrNo(rs.getString("UsrNo"));
					if(_fields[i].equals("UsrName") || _fields[i].equals("Usr.UsrName"))
						usr.setUsrName(rs.getString("UsrName"));
					if(_fields[i].equals("Email") || _fields[i].equals("Usr.Email"))
						usr.setEmail(rs.getString("Email"));
					if(_fields[i].equals("Remark") || _fields[i].equals("Usr.Remark"))
						usr.setRemark(rs.getString("Remark"));

					if(_fields[i].equals("OrgNo") || _fields[i].equals("Usr.OrgNo"))
						usr.setOrgNo(rs.getInt("OrgNo"));

				}
				list.add(usr);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("UsrHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}