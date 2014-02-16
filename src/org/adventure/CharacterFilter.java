package org.adventure;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class CharacterFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
		String cId = request.getParameter("cId");
		request.setAttribute("cId", cId);
		HttpServletResponseWrapper r = new HttpServletResponseWrapper((HttpServletResponse)response);
		r.addHeader("cId", cId);
		fc.doFilter(request, r);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
