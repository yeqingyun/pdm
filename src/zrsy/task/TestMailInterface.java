package zrsy.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TestMailInterface {

    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");          
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded;charset=UTF-8"); 
            
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    } 
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		for(int i=0;i<1;i++) {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			String curDate = sdf.format(new Date());
			String title = "测试邮件标题,测试时间:" + curDate;
			String content = "测试邮件内容,测试时间:" + curDate;
	
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
			
					
			/*String msg1 = TestMailInterface.sendPost("http://18.8.19.36:8080/msg/send_mail/sendByUsrId.json", 
					"title=" + title + "&content=" + content + "&userList=" + userList);*/
			
			/*String msg1 = TestMailInterface.sendPost("http://msg.gionee.com/send_mail/sendByUsrId.json", 
					"title=" + title + "&content=" + content + "&userList=" + userList);*/
			
			String msg1 = TestMailInterface.sendPost("http://msg.gionee.com/send_mail/sendByAddressOnly.json", 
					"title=" + title + "&content=" + content + "&addressList=" + userList);
			
			/*String msg1 = TestMailInterface.sendPost("http://18.8.19.36:8080/msg/send_mail/sendByUsrId.json", 
					"title=" + title + "&content=" + content + "&userList=" + userList + "&toType=0");*/
			
			/*String msg2 = TestMailInterface.sendPost("http://18.8.19.36:8080/msg/send_mail/sendByAddress.json", 
					"title=" + title + "&content=" + content + "&addressList=" + userList);*/
			
			/*String msg1 = TestMailInterface.sendPost("http://18.8.19.36:8080/msg/send_mail/createMail.json", 
					"userID=15785&title=" + title +"&content=" + content + "&sender=test");*/
			
			System.out.println(msg1);
			
			//System.out.println(msg2);
			
			
			/*String addressList = "   ,,  15785  ,,  00000867  ,,   ,  quezg@gionee.com  ,,  28159  ,,15785,,15441,  zhuzb0@gionee.com  ,aa@aa.com,11@11.com,"
					+ "DUDX@GIONEE.COM,17538,15079,28516,28515,,16076,15870,   ZHUZB@GIONEE.COM    ,20958,148851,  shixian@gionee.com ,,,,  ,, "
					+ ",  zhuzb@gionee.com  ,47673,xiejianlong@gionee.com,-1,  dudx@gionee.com,  00000867 ,aaacf12 ,20903,,  @2   3@  ";
			
			String msg2 = TestMailInterface.sendPost("http://18.8.19.36:8080/msg/send_mail/sendByAddress.json", 
					"title=" + title + "&content=" + content + "&addressList=" + addressList);
			
			System.out.println(msg2);*/
		
		}
			
	}

}
