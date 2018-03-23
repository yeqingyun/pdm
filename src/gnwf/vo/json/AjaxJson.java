/**
 * @(#) AjaxJson.java Created on 2014年4月9日
 *
 * Copyright (c) 2014 GIONEE. All Rights Reserved
 */
package gnwf.vo.json;

import java.util.Map;

/**
 * The class <code>AjaxJson</code>
 *
 * @author lipw
 * @version 1.0
 */
public class AjaxJson {
	private boolean success = true;// 是否成功
	
	private String msg;// 提示信息
	
	private Object obj;// 其他信息
	
	private Map<String, Object> attributes;// 其他参数
	

	public AjaxJson() {
		super();
	}
	
	public AjaxJson(boolean success) {
		super();
		this.success = success;
	}

	public AjaxJson(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
}
