package zrsy.ww;
    import java.io.IOException;  
import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletContext;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  


import zrsy.vo.Usr;
      
    public class SessionTimeOutfilter implements Filter{  
          
        public void destroy() {  
              
        }  
          
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,  
                FilterChain filterchain) throws IOException, ServletException {  
          
            HttpServletResponse response = (HttpServletResponse) servletResponse;  
            HttpServletRequest request = (HttpServletRequest)servletRequest;  
            
    		//移动终端 访问拦截判断
    		Object logCode =  request.getParameter("logCode");
//    		if(StringUtils.isNotBlank(logCode)) {
//    			SyLog syLog = new SyLog();
//    			syLog.setLogCode(logCode);
//    			try {
//    				List<SyLog> sls = new SyLogFacade().find(syLog);
//    				if(sls != null && sls.size() > 0) {
//    					return PERMISSION;
//    				}
//    			} catch (Exception e) {
//    				e.printStackTrace();
//    			}
//    		}
            System.out.println(String.valueOf(logCode));
    		if(!(logCode!=null&&!String.valueOf(logCode).equals("") )){ //如果是web访问
    			   
                
                
                HttpSession session=request.getSession();
                
                
                Usr userSession = new Usr();
                userSession = (Usr) session.getAttribute("SYUSR");
                
//                String haslogin = (String)session.getAttribute("operno");
                System.out.println(userSession);
                System.out.println("request.getRequestURI()"+request.getRequestURI());
                
                if(!(request.getRequestURI().indexOf("login")>-1)&&!(request.getRequestURI().indexOf("include")>-1)){
                	
                	if(userSession==null|| "".equals(userSession.getGpNames())){
                		//可以在这里设置下，把其后台的“您的操作已过时，请重新登陆” 传递。
                		response.sendRedirect("./login.jsp");
                	}
                }
    		}
    		
            
            
            
            
            
         
           
            //需要过滤的代码         
            filterchain.doFilter(servletRequest, servletResponse);  
        }  
          
        public void init(FilterConfig config) throws ServletException {  
        	
        }  
    }  