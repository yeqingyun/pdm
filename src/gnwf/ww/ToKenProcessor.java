package gnwf.ww;
import java.security.MessageDigest;

import javax.servlet.http.HttpSession;

import  com.opensymphony.webwork.ServletActionContext;
public class ToKenProcessor extends BasicAction  {
	private static final long serialVersionUID = 1L;
	static final String TOKEN_KEY="checkReSub";
	private String tokenkey;
	private static ToKenProcessor instance=new ToKenProcessor();
	public static ToKenProcessor getInstance(){
		return instance;
	}
	private long previous;
	
	public synchronized String getCurToken() throws Exception{
		HttpSession session=	ServletActionContext.getRequest().getSession(false);
		if(session==null){
			return null;
		}
		String saved=(String) session.getAttribute(TOKEN_KEY);

		return saved;
		
	}
	public synchronized void resetToken(){
		HttpSession session=	ServletActionContext.getRequest().getSession();
		if(session==null){
			return ;
		}
		session.removeAttribute(TOKEN_KEY);
	}
	
	public synchronized void saveToken(){
		HttpSession session=	ServletActionContext.getRequest().getSession();
		if(session==null){
			return ;
		}
		String token=generateToken();
		if(token!=null){
			session.setAttribute(TOKEN_KEY, token);
		}
	}
	
	
	public synchronized String  generateToken(){
		HttpSession session=	ServletActionContext.getRequest().getSession();
		try{
			byte id[]=session.getId().getBytes();
			long current=System.currentTimeMillis();
			if(session.getAttribute("PREVIOUS")!=null){
			previous=(Long) session.getAttribute("PREVIOUS");
			}
			if(current==previous){
				current++;
			}
			previous=current;
			session.setAttribute("PREVIOUS", (Long)previous);
			byte now[]=new Long(current).toString().getBytes();
			MessageDigest md=MessageDigest.getInstance("MD5");
			md.update(id);
			md.update(now);
			return toHex(md.digest());

		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	

	
	public String toHex(byte buffer[]){
		StringBuffer sb=new StringBuffer(buffer.length*2);
		for(int i=0;i<buffer.length;i++){
			if (((int)buffer[i] & 0xff) < 0x10) {
				sb.append("0");
			}
			sb.append(Long.toString((int)buffer[i] & 0xff, 16));
		}
		return sb.toString();
	}
	
	
	
	public synchronized String getToken()  throws Exception{
		HttpSession session=	ServletActionContext.getRequest().getSession();
		if(session==null){
			return null;
		}
		String token=(String) session.getAttribute(TOKEN_KEY);
		if(token!=null){
		}
		if(null==token){
			token=generateToken();

			if(token!=null){
				session.setAttribute(TOKEN_KEY, token);
				return token;
			}else{
				return null;
			}
		}
		return token;
		
	}
	public String getTokenkey() {
		return tokenkey;
	}
	public void setTokenkey(String tokenkey) {
		this.tokenkey = tokenkey;
	}


	
}
