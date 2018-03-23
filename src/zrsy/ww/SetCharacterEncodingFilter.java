package zrsy.ww;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class SetCharacterEncodingFilter implements Filter {

	protected String encoding = null;

	protected FilterConfig filterConfig = null;

	protected boolean ignore = true;

	// --------------------------------------------------------- Public Methods

	public void destroy() {

		this.encoding = null;

		this.filterConfig = null;

	}

	public void doFilter(ServletRequest request, ServletResponse response,

	FilterChain chain)

	throws IOException, ServletException {

		// Conditionally select and set the character encoding to be used

		if (ignore || (request.getCharacterEncoding() == null)) {

			String encoding = selectEncoding(request);

			if (encoding != null) {

				HttpServletRequest httpServletRequest = (HttpServletRequest) request;

				if (httpServletRequest.getMethod().toLowerCase().equals("post")) {

					// 如果是POST方法

					request.setCharacterEncoding(encoding);

				}

				else {

					// 如果是GET方法

					// 非常抱歉，我还有没有找到很好的对应get方法的代码

					// 一旦完成了这部分代码，马上添加在这里。

					// ！·#￥%……—*（）——+|

				}

			}

		}

		// Pass control on to the next filter

		chain.doFilter(request, response);

	}

	public void init(FilterConfig filterConfig) throws ServletException {

		this.filterConfig = filterConfig;

		this.encoding = filterConfig.getInitParameter("encoding");

		String value = filterConfig.getInitParameter("ignore");

		if (value == null)

			this.ignore = true;

		else if (value.equalsIgnoreCase("true"))

			this.ignore = true;

		else if (value.equalsIgnoreCase("yes"))

			this.ignore = true;

		else

			this.ignore = false;

	}

	// ------------------------------------------------------ Protected Methods

	protected String selectEncoding(ServletRequest request) {

		return (this.encoding);

	}

}