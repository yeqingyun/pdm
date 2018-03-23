package zrsy.task;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.gionee.gnif.mail.biz.base.MailSender;

public class TestMailSend {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		for(int i=0 ; i<1; i++) {
			
			HttpClientBuilder httpBuilder = HttpClientBuilder.create(); 
			CloseableHttpClient httpClient = httpBuilder.build();// 创建默认的httpClient实例	
			RequestConfig.custom().setConnectTimeout(10000).setConnectionRequestTimeout(20000);
			
			//String reqURL = "http://18.8.19.36:8080/msg/send_mail/sendByUsrId.json";  //测试环境
			
			//String reqURL = "http://msg.gionee.com/send_mail/sendByUsrId.json";  //正式环境
			
			//String reqURL = "http://msg.gionee.com/send_mail/sendByAddress.json";  //正式环境
			
			//String reqURL = "http://18.8.19.36:8080/msg/send_mail/sendByAddress.json";  //测试环境
			
			//String reqURL = "http://18.8.19.36:8080/msg/send_mail/sendByAddressOnly.json";  //测试环境
			
			String reqURL = "http://msg.gionee.com/send_mail/sendByAddressOnly.json";  //正式环境
			
			HttpPost httpPost = new HttpPost(reqURL);
			httpPost.setHeader(HTTP.CONTENT_TYPE,"application/x-www-form-urlencoded; charset=UTF-8");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			String curDate = sdf.format(new Date());
			String title = "测试邮件标题,测试时间:" + curDate;
			String content = "测试邮件内容,测试时间:" + curDate;
			//String userList = "15785,28159";
			String userList = "   ,,  15785  ,,  00000867  ,,   ,  quezg@gionee.com  ,,  28159  ,,15785,,15441,  zhuzb0@gionee.com  ,aa@aa.com,11@11.com,@2   3@,"
					+ "DUDX@GIONEE.COM,17538,15079,28516,28515,,16076,15870,   ZHUZB@GIONEE.COM    ,20958,148851,  shixian@gionee.com ,,,,  ,15785, 15785,47673,"
					+ ",  zhuzb@gionee.com  ,xiejianlong@gionee.com,-1,  dudx@gionee.com,  00000867 ,aaacf12 ,20903,,  caojj@gionee.com,zhouxiaopei@gionee.com,"
					+ "291640457@qq.com,1045026444@qq.com,812020887@qq.com,875936906@qq.com,291640457@qq.com,1045026444@qq.com,812020887@qq.com,875936906@qq.com,"
					+ " , fyi325747@djs9tytg1.com,quezg1@gionee.com ,zhuzb1@gionee.com,dihsafihf@djsjf.com,djsajfd1123@4345cjis.com,0932afhs@djsoif.com,66@66.com,"
					+ "   ,,  15785  ,,  00000867  ,,   ,  quezg@gionee.com  ,,  28159  ,,15785,,15441,  zhuzb0@gionee.com  ,aa@aa.com,11@11.com,@2   3@,"
					+ "DUDX@GIONEE.COM,17538,15079,28516,28515,,16076,15870,   ZHUZB@GIONEE.COM    ,20958,148851,  shixian@gionee.com ,,,,  ,15785, 15785,47673,"
					+ ",  zhuzb@gionee.com  ,xiejianlong@gionee.com,-1,  dudx@gionee.com,  00000867 ,aaacf12 ,20903,,  caojj@gionee.com,zhouxiaopei@gionee.com,"
					+ "291640457@qq.com,1045026444@qq.com,812020887@qq.com,875936906@qq.com,291640457@qq.com,1045026444@qq.com,812020887@qq.com,875936906@qq.com,"
					+ " , fyi325747@djs9tytg1.com,quezg1@gionee.com ,zhuzb1@gionee.com, zhuzb11@gionee.com ,zhuzb6@gionee.com ,dsafiusahf@fdjsvh.com ";
			
			
			//String userList = "aa@aa.com,11@11.com,yi32574ds32fd7@djs9tytgeqwfr1.com,shixian@gionee123.com";
			
			Map<String, String> reqData = new HashMap<String, String>();
			reqData.put("title", title);
			reqData.put("content", content);
			//reqData.put("userList", userList);
			reqData.put("addressList", userList);
			//reqData.put("toType", "0");
			
			String reseContent = "";
					
			try {
				List<NameValuePair> params=new ArrayList<NameValuePair>();
				
				for (Map.Entry<String, String> entry : reqData.entrySet()) {
					params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
				
				//设置编码
				httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
				HttpResponse response = httpClient.execute(httpPost);
				HttpEntity entity = response.getEntity();
				if (null != entity) {
					reseContent = EntityUtils.toString(entity, ContentType.getOrDefault(entity).getCharset());
					EntityUtils.consume(entity);
				}
			} catch (ConnectTimeoutException cte) {
				cte.printStackTrace();
			} catch (SocketTimeoutException ste) {
				ste.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			System.out.println(reseContent);
			
			try {
				JSONObject jsonObj = JSONObject.parseObject(reseContent);
				if(!(jsonObj != null && jsonObj.getBoolean("success") != null && jsonObj.getBoolean("success") == true)) {
					System.out.println("insert 2");
					//MailSender.sendByUsrId(userList, title, content, 0);	
					//MailSender.sendByAddress(userList, title, content, 0);
					MailSender.saveAddressOnly(userList, title, content, 0);
				}
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("insert 3");
				//MailSender.sendByUsrId(userList, title, content, 0);	
				//MailSender.sendByAddress(userList, title, content, 0);
				MailSender.saveAddressOnly(userList, title, content, 0);
			}

		}	

	}

}
