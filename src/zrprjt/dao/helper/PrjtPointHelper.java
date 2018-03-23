package zrprjt.dao.helper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import zrprjt.vo.PrjtPoint;

public class PrjtPointHelper extends BasicPrjtPointHelper {
	public String getSqlString() {
		return " from PrjtPoint " +
               " where 1=1 ";
	}

	public List<PrjtPoint> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<PrjtPoint> list = new ArrayList<PrjtPoint>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				PrjtPoint PrjtPoint = new PrjtPoint();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("PointId") || _fields[i].equals("PrjtPoint.PointId"))
						PrjtPoint.setPointId(rs.getInt("PointId"));
					if(_fields[i].equals("PointIndex") || _fields[i].equals("PrjtPoint.PointIndex"))
						PrjtPoint.setPointIndex(rs.getInt("PointIndex"));
					if(_fields[i].equals("PointName") || _fields[i].equals("PrjtPoint.PointName"))
						PrjtPoint.setPointName(rs.getString("PointName"));
					if(_fields[i].equals("PrjtNo") || _fields[i].equals("PrjtPoint.PrjtNo"))
						PrjtPoint.setPrjtNo(rs.getString("PrjtNo"));
					if(_fields[i].equals("Version") || _fields[i].equals("PrjtPoint.Version"))
						PrjtPoint.setVersion(rs.getString("Version"));
					
				//  "PrjtPoint.OriginalPlanOveDate,PrjtPoint.ActualOverDate,PrjtPoint.PlanOverDate,PrjtPoint.Leve," +
					if(_fields[i].equals("OriginalPlanOveDate") || _fields[i].equals("PrjtPoint.OriginalPlanOveDate"))
						PrjtPoint.setOriginalPlanOveDate(rs.getTimestamp("OriginalPlanOveDate"));
					if(_fields[i].equals("ActualOverDate") || _fields[i].equals("PrjtPoint.ActualOverDate"))
						PrjtPoint.setActualOverDate(rs.getTimestamp("ActualOverDate"));
					if(_fields[i].equals("PlanOverDate") || _fields[i].equals("PrjtPoint.PlanOverDate"))
						PrjtPoint.setPlanOverDate(rs.getTimestamp("PlanOverDate"));
					if(_fields[i].equals("Leve") || _fields[i].equals("PrjtPoint.Leve"))
						PrjtPoint.setLeve(rs.getInt("Leve"));
					
					//"PrjtPoint.Parent,PrjtPoint.ResponsUser,PrjtPoint.DelayTime,PrjtPoint.DelayReson,PrjtPoint.Status," +
					if(_fields[i].equals("Parent") || _fields[i].equals("PrjtPoint.Parent"))
						PrjtPoint.setParent(rs.getInt("Parent"));
					if(_fields[i].equals("ResponsUser") || _fields[i].equals("PrjtPoint.ResponsUser"))
						PrjtPoint.setResponsUser(rs.getString("ResponsUser"));
					if(_fields[i].equals("DelayTime") || _fields[i].equals("PrjtPoint.DelayTime"))
						PrjtPoint.setDelayTime(rs.getInt("DelayTime"));
					if(_fields[i].equals("DelayReson") || _fields[i].equals("PrjtPoint.DelayReson"))
						PrjtPoint.setDelayReson(rs.getString("DelayReson"));
					if(_fields[i].equals("Status") || _fields[i].equals("PrjtPoint.Status"))
						PrjtPoint.setStatus(rs.getInt("Status"));
					
				//  "PrjtPoint.Remark,PrjtPoint.CreateBy,PrjtPoint.CreateDate,PrjtPoint.LastUpd,PrjtPoint.LastDate";
					if(_fields[i].equals("Remark") || _fields[i].equals("PrjtPoint.Remark"))
						PrjtPoint.setRemark(rs.getString("Remark"));
					if(_fields[i].equals("CreateBy") || _fields[i].equals("PrjtPoint.CreateBy"))
						PrjtPoint.setCreateBy(rs.getInt("CreateBy"));
					if(_fields[i].equals("CreateDate") || _fields[i].equals("PrjtPoint.CreateDate"))
						PrjtPoint.setCreateDate(rs.getTimestamp("CreateDate"));
					if(_fields[i].equals("LastUpd") || _fields[i].equals("PrjtPoint.LastUpd"))
						PrjtPoint.setLastUpd(rs.getInt("LastUpd"));
					if(_fields[i].equals("LastDate") || _fields[i].equals("PrjtPoint.LastDate"))
						PrjtPoint.setLastDate(rs.getTimestamp("LastDate"));
					
				}
				list.add(PrjtPoint);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("PrjtPointHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}
}