package zrsy.vo.liger;

import com.alibaba.fastjson.JSONAware;

public class LiFunction implements JSONAware {
	 private String functionString;

	 public LiFunction() {
	 }

	 public LiFunction(String functionString) {
		 this.functionString = functionString;
	 }

	 @Override
	 public String toJSONString() {
		 return this.functionString;
	 }

	 public String getFunctionString() {
		 return functionString;
	 }

	 public void setFunctionString(String functionString) {
		 this.functionString = functionString;
	 }
}