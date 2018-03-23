<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<% 	
	ServletInputStream in = request.getInputStream(); 
	HttpServletResponse resp = response;
	resp.setHeader("Content-length", "" + 100000000);
	resp.setContentType("application/octet-stream");
	String saveFileName = "plusgantt.jpg";
	resp.setHeader("Content-Disposition","attachment; filename="+saveFileName);
	
	ServletOutputStream outStream = resp.getOutputStream();
	int b =0;
	while(b!= -1)
	{
	   //in.available();
	   b = in.read();
	   if(b!= -1)
	      outStream.write(b);
	}
	outStream.flush();
	outStream.close();		
%>