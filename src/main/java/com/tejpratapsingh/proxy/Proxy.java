package com.tejpratapsingh.proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Proxy extends HttpServlet {

	@Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response)
	      throws IOException, ServletException {
		
		if (request.getParameter("api_key") == null
				|| request.getParameter("api_key").equals("tejpratapsingh") == false) {
		    response.setContentType("application/json");
		    response.getWriter().append("{\"error\": true, \"message\": \"api_key is invalid\"}");
			return;
		}

	    URL url = new URL(request.getParameter("url"));
	    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
	    StringBuffer proxyResponse = new StringBuffer();
	    String line;

	    while ((line = reader.readLine()) != null) {
	      proxyResponse.append(line);
	    }
	    reader.close();

	    response.setContentType("application/json");
	    response.getWriter().append(proxyResponse);
	  }
}