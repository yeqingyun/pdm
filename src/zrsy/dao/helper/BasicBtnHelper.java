package zrsy.dao.helper;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import org.frm.jdbc.DbConnUtil;
import org.frm.jdbc.SqlHelper;
import org.frm.vo.PageVO;

import zrsy.vo.Btn;

public class BasicBtnHelper implements SqlHelper {
	public String getSqlString() {
		return " from Btn where 1=1";
	}

	public String getOrderBy() {
		return " order by Btn.Id";
	}

	public String getSql4Amount(Object object) {
		return getSql4Amount((Btn)object);
	}

	public String getSql4Amount(Btn btn) {
		return "select count(*) as amount "+getSqlString()+getSqlCondition(btn);
	}

	public String getSql4Delete(Object object) {
		return getSql4Delete((Btn)object);
	}

	public String getSql4Delete(Btn btn) {
		return "delete from Btn where 1=1"+getSqlCondition(btn);
	}

	public String getSql4All(Object object, String fields) {
		return getSql4All((Btn)object,fields);
	}

	public String getSql4All(Btn btn, String fields) {
		return "select " + fields + getSqlString()+getSqlCondition(btn)+getOrderBy();
	}

	public String getSql4Pages(Object object,PageVO pageVO,String fields) {
		return getSql4Pages((Btn)object,pageVO,fields);
	}

	public String getSql4Pages(Btn btn,PageVO pageVO, String fields) {
		int pageSize = pageVO.getPageSize();
		int pages = pageSize*pageVO.getPage()-pageSize;
		String mstr = "select top "+pages+" Btn.Id "+ getSqlString()+getSqlCondition(btn)+getOrderBy();
		String sql = "select top "+pageSize+" "+fields+getSqlString() + getSqlCondition(btn)+" and Btn.Id not in("+mstr+") "+getOrderBy();

		return sql;
	}

	public String getSqlCondition(Object object) {
		return getSqlCondition((Btn)object);
	}

	public String getSqlCondition(Btn btn) {
		String sql = "";
		if(null != btn.getId())
			sql += " and Btn.Id = '"+btn.getId()+"'";
		if(null != btn.getSyId())
			sql += " and Btn.SyId = '"+btn.getSyId()+"'";
		if(null != btn.getLine())
			sql += " and Btn.Line = '"+btn.getLine()+"'";
		if(null != btn.getDisable())
			sql += " and Btn.Disable = '"+btn.getDisable()+"'";
		if(null != btn.getSort())
			sql += " and Btn.Sort = '"+btn.getSort()+"'";
		if(null != btn.getText() && !btn.getText().trim().equals(""))
			sql += " and Btn.Text = '"+btn.getText().trim()+"'";
		if(null != btn.getClick() && !btn.getClick().trim().equals(""))
			sql += " and Btn.Click = '"+btn.getClick().trim()+"'";
		if(null != btn.getIcon() && !btn.getIcon().trim().equals(""))
			sql += " and Btn.Icon = '"+btn.getIcon().trim()+"'";
		if(null != btn.getIcon() && !btn.getImg().trim().equals(""))
			sql += " and Btn.Img = '"+btn.getImg().trim()+"'";

		return sql;
	}

	public List<Btn> getQueryList(ResultSet rs,String fields) throws java.sql.SQLException {
		ArrayList<Btn> list = new ArrayList<Btn>();
		String[] _fields = fields.split(",");
		try {
			while(rs.next()) {
				Btn btn = new Btn();
				for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Id") || _fields[i].equals("Btn.Id"))
						btn.setId(rs.getInt("Id"));
					if(_fields[i].equals("SyId") || _fields[i].equals("Btn.SyId"))
						btn.setSyId(rs.getInt("SyId"));
					if(_fields[i].equals("Line") || _fields[i].equals("Btn.Line"))
						btn.setLine(rs.getInt("Line"));
					if(_fields[i].equals("Disable") || _fields[i].equals("Btn.Disable"))
						btn.setDisable(rs.getInt("Disable"));
					if(_fields[i].equals("Sort") || _fields[i].equals("Btn.Sort"))
						btn.setSort(rs.getInt("Sort"));
					if(_fields[i].equals("Text") || _fields[i].equals("Btn.Text"))
						btn.setText(rs.getString("Text"));
					if(_fields[i].equals("Click") || _fields[i].equals("Btn.Click"))
						btn.setClick(rs.getString("Click"));
					if(_fields[i].equals("Icon") || _fields[i].equals("Btn.Icon"))
						btn.setIcon(rs.getString("Icon"));
					if(_fields[i].equals("Img") || _fields[i].equals("Btn.Img"))
						btn.setImg(rs.getString("Img"));

				}
				list.add(btn);
			}
		}
		catch(java.sql.SQLException e) {
			Logger.getLogger(this.getClass()).error("BtnHelper.getQueryList SQLException", e);
			throw e;
		}
		return list;
	}

	public String getInsertSql(String fields) {
		String sql = "insert into Btn("+fields.replaceAll("Btn\\.", "")+")values(";
		int length = fields.split(",").length;
		for(int i=0;i<length-1;i++) {
			sql += "?,";
		}
		sql += "?)";
		return sql;
	}

	public void pstmtInsert(Btn btn,String sql,String fields) throws java.sql.SQLException {
		int index = 0;
		String[] _fields = fields.split(",");
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("Id") || _fields[i].equals("Btn.Id")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, btn.getId());
					}
					else if(_fields[i].equals("SyId") || _fields[i].equals("Btn.SyId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, btn.getSyId());
					}
					else if(_fields[i].equals("Line") || _fields[i].equals("Btn.Line")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, btn.getLine());
					}
					else if(_fields[i].equals("Disable") || _fields[i].equals("Btn.Disable")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, btn.getDisable());
					}
					else if(_fields[i].equals("Sort") || _fields[i].equals("Btn.Sort")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, btn.getSort());
					}
					else if(_fields[i].equals("Text") || _fields[i].equals("Btn.Text")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, btn.getText());
					}
					else if(_fields[i].equals("Click") || _fields[i].equals("Btn.Click")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, btn.getClick());
					}
					else if(_fields[i].equals("Icon") || _fields[i].equals("Btn.Icon")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, btn.getIcon());
					}
					else if(_fields[i].equals("Img") || _fields[i].equals("Btn.Img")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, btn.getImg());
					}

			}
			DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("BtnHelper.pstmtInsert SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getUpdateSql(String fields,String key,String keyValue) {
		String sql = "update Btn set ";
		String[] _fields = fields.replaceAll("Btn\\.", "").split(",");
		for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SyId") || _fields[i].equals("Btn.SyId"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Line") || _fields[i].equals("Btn.Line"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Disable") || _fields[i].equals("Btn.Disable"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Sort") || _fields[i].equals("Btn.Sort"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Text") || _fields[i].equals("Btn.Text"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Click") || _fields[i].equals("Btn.Click"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Icon") || _fields[i].equals("Btn.Icon"))
						sql += _fields[i] + " = ?,";
					if(_fields[i].equals("Img") || _fields[i].equals("Btn.Img"))
						sql += _fields[i] + " = ?,";

		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where "+key+" = '"+keyValue+"'";
		return sql;
	}

	public void pstmtUpdate(Btn btn,String sql,String fields) throws java.sql.SQLException {
		String[] _fields = fields.split(",");
		int index = 0;
		try {
			DbConnUtil.getDbconn().buildPreparedStatement(sql);
			for(int i=0;i<_fields.length;i++) {
					if(_fields[i].equals("SyId") || _fields[i].equals("Btn.SyId")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, btn.getSyId());
					}
					else if(_fields[i].equals("Line") || _fields[i].equals("Btn.Line")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, btn.getLine());
					}
					else if(_fields[i].equals("Disable") || _fields[i].equals("Btn.Disable")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, btn.getDisable());
					}
					else if(_fields[i].equals("Sort") || _fields[i].equals("Btn.Sort")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setInt(index, btn.getSort());
					}
					else if(_fields[i].equals("Text") || _fields[i].equals("Btn.Text")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, btn.getText());
					}
					else if(_fields[i].equals("Click") || _fields[i].equals("Btn.Click")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, btn.getClick());
					}
					else if(_fields[i].equals("Icon") || _fields[i].equals("Btn.Icon")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, btn.getIcon());
					}
					else if(_fields[i].equals("Img") || _fields[i].equals("Btn.Img")) {
						index++;
						DbConnUtil.getDbconn().getCurrentPstmt().setString(index, btn.getImg());
					}

			}
		DbConnUtil.getDbconn().getCurrentPstmt().execute();
		}
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("BtnHelper.pstmtUpdate SQLException", e);
			throw new java.sql.SQLException();
		}
	}

	public String getFields(Btn btn) {
		String _fields = "";
		if(null != btn.getId())
			_fields += "Btn.Id,";
		if(null != btn.getSyId())
			_fields += "Btn.SyId,";
		if(null != btn.getLine())
			_fields += "Btn.Line,";
		if(null != btn.getDisable())
			_fields += "Btn.Disable,";
		if(null != btn.getSort())
			_fields += "Btn.Sort,";
		if(null != btn.getText())
			_fields += "Btn.Text,";
		if(null != btn.getClick())
			_fields += "Btn.Click,";
		if(null != btn.getIcon())
			_fields += "Btn.Icon,";
		if(null != btn.getImg())
			_fields += "Btn.Img,";

		return _fields.substring(0, _fields.length()-1);
	}
}